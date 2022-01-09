package net.mechalopa.hmag.entity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.mechalopa.hmag.entity.goal.MeleeAttackGoal2;
import net.mechalopa.hmag.registry.ModSoundEvents;
import net.mechalopa.hmag.util.ModUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class StoneularEntity extends MonsterEntity implements IModMob
{
	private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("8FC423B1-4E9A-246D-5CD6-28DB031B3CF9");
	private static final AttributeModifier ARMOR_MODIFIER = new AttributeModifier(ARMOR_MODIFIER_UUID, "Armor bonus", 8.0D, AttributeModifier.Operation.ADDITION);
	private static final DataParameter<Byte> ACTION_PHASE = EntityDataManager.defineId(StoneularEntity.class, DataSerializers.BYTE);
	@Nullable
	private BlockPos targetPosition = null;
	private int toRiseTick = 0;
	private int riseTick = 0;
	private int animationTick;
	private int animationTickO;

	public StoneularEntity(EntityType<? extends StoneularEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 12;
		this.maxUpStep = 1.0F;
		this.moveControl = new StoneularEntity.MoveHelperController(this);
		this.setActionPhase(StoneularEntity.ActionPhase.WALK);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(4, new StoneularEntity.AttackGoal(this));
		this.goalSelector.addGoal(5, new StoneularEntity.MoveToTargetPositionGoal(this));
		this.goalSelector.addGoal(5, new StoneularEntity.RandomWalkingGoal(this));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 5, true, false, (p) -> {
			if (this.isWalking())
			{
				return true;
			}
			else if (this.getEyeY() >= p.getY())
			{
				final double d0 = p.getX() - this.getX();
				final double d1 = p.getZ() - this.getZ();
				return (double)MathHelper.sqrt(d0 * d0 + d1 * d1) <= 6.0D;
			}
			else
			{
				return false;
			}
		}));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ACTION_PHASE, (byte)0);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.29D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 2.0D)
				.add(Attributes.ARMOR, 2.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.75D);
	}

	@Override
	public void tick()
	{
		if (this.level.isClientSide)
		{
			if (this.firstTick)
			{
				if (!this.isWalking())
				{
					this.animationTick = 15;
				}
			}

			this.animationTickO = this.animationTick;
		}

		super.tick();

		if (this.level.isClientSide)
		{
			if (!this.isWalking())
			{
				if (this.animationTick < 15)
				{
					++this.animationTick;
				}
			}
			else if (this.animationTick > 0)
			{
				--this.animationTick;
			}
		}
	}

	@Override
	public void aiStep()
	{
		if (this.level.isClientSide)
		{
			if (this.isAwaiting() && this.getRandom().nextInt(24) == 0)
			{
				BlockPos pos = new BlockPos(this.blockPosition().getX(), this.blockPosition().getY() + this.getYSize(), this.blockPosition().getZ());
				BlockState blockstate = this.level.getBlockState(pos);

				if (blockstate != null && !this.level.isEmptyBlock(pos))
				{
					double d0 = this.getRandomX(0.5D);
					double d1 = this.getBoundingBox().maxY;
					double d2 = this.getRandomZ(0.5D);
					this.level.addParticle(new BlockParticleData(ParticleTypes.FALLING_DUST, blockstate), d0, d1, d2, 0.0D, 0.0D, 0.0D);
				}
			}
		}

		super.aiStep();
	}

	@Override
	protected void customServerAiStep()
	{
		super.customServerAiStep();

		if (this.isAlive())
		{
			if (this.isWalking())
			{
				if (!this.isVehicle() && !this.isPassenger())
				{
					if (this.hasLevitation())
					{
						if (this.toRiseTick < 20)
						{
							++this.toRiseTick;
						}
						else
						{
							if (!this.isInWaterOrBubble() && !this.isInLava() && this.findTargetPos())
							{
								this.setActionPhase(StoneularEntity.ActionPhase.RISE);
								this.toRiseTick = 0;
							}
							else
							{
								this.toRiseTick = 10;
							}
						}
					}
					else
					{
						if (this.getTarget() == null || !this.canSee(this.getTarget()))
						{
							if (this.isOnGround())
							{
								if (this.toRiseTick < 200)
								{
									++this.toRiseTick;
								}

								if (this.toRiseTick >= 200 && this.getRandom().nextInt(50) == 0)
								{
									if (this.findTargetPos())
									{
										this.setActionPhase(StoneularEntity.ActionPhase.RISE);
										this.toRiseTick = 0;
									}
								}
							}
							else if (this.toRiseTick > 0)
							{
								--this.toRiseTick;
							}
						}
						else if (this.toRiseTick > 0)
						{
							this.toRiseTick = 0;
						}
					}
				}
			}
			else
			{
				boolean flag = false;

				if (this.hasValidTargetPos() && !this.isVehicle() && !this.isPassenger() && !this.isInWaterOrBubble() && !this.isInLava())
				{
					BlockPos pos = this.blockPosition();

					if (this.isAwaiting())
					{
						if (this.getTarget() != null && this.canSee(this.getTarget()) && this.getEyeY() >= this.getTarget().getY() && !this.hasLevitation())
						{
							double d2 = this.getTarget().getX() - this.getX();
							double d3 = this.getTarget().getZ() - this.getZ();

							if ((double)MathHelper.sqrt(d2 * d2 + d3 * d3) <= 6.0D)
							{
								flag = true;
							}
						}

						if (!flag)
						{
							if (pos.getY() + this.getYSize() == this.getTargetPos().getY())
							{
								final int i = pos.getX();
								final int j = pos.getZ();
								final int k = this.getTargetPos().getX();
								final int l = this.getTargetPos().getZ();

								if (i != k || j != l)
								{
									if ((Math.abs(i - k) <= 1 && Math.abs(j - l) <= 1))
									{
										BlockPos pos1 = new BlockPos(i, this.getTargetPos().getY(), j);

										if (isRedstoneConductorBlock(this.level, pos1))
										{
											this.setTargetPos(pos1);
										}
										else
										{
											flag = true;
										}
									}
									else
									{
										flag = true;
									}
								}
							}
							else
							{
								flag = true;
							}
						}
					}
					else
					{
						BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() + this.getYSize(), pos.getZ());

						if (isRedstoneConductorBlock(this.level, pos1))
						{
							this.setTargetPos(pos1);
							this.setActionPhase(StoneularEntity.ActionPhase.AWAIT);
							this.riseTick = 0;
						}
						else if (!this.level.isEmptyBlock(pos1))
						{
							flag = true;
						}

						if (!flag)
						{
							if (this.riseTick < 30)
							{
								++this.riseTick;
							}
							else
							{
								flag = true;
							}
						}
					}
				}
				else
				{
					flag = true;
				}

				if (flag)
				{
					this.riseTick = 0;
					this.setActionPhase(StoneularEntity.ActionPhase.WALK);
					this.playFallSoundAndParticles();

					if (this.getTarget() == null)
					{
						this.toRiseTick = 150;
					}
				}
			}
		}
	}

	private boolean findTargetPos()
	{
		BlockPos.Mutable blockpos$mutable = this.blockPosition().mutable();

		int i = 0;

		while (this.level.isEmptyBlock(blockpos$mutable))
		{
			blockpos$mutable.move(Direction.UP);
			++i;

			if (i > 20)
			{
				return false;
			}
		}

		if (i >= 3 || (this.hasLevitation() && i >= 1))
		{
			BlockPos pos = blockpos$mutable.immutable();

			if (ModUtils.closerThan(this, pos, 32) && isRedstoneConductorBlock(this.level, pos))
			{
				this.setTargetPos(pos);
				return true;
			}
		}

		return false;
	}

	private boolean hasValidTargetPos()
	{
		return this.hasTargetPos() && ModUtils.closerThan(this, this.getTargetPos(), 32) && this.getTargetPos().getY() >= 1 && isRedstoneConductorBlock(this.level, this.getTargetPos()) && this.level.isEmptyBlock(this.getTargetPos().below());
	}

	private int getYSize()
	{
		return MathHelper.ceil(this.getBoundingBox().getYsize());
	}

	private boolean hasLevitation()
	{
		return this.hasEffect(Effects.LEVITATION);
	}

	private void playFallSoundAndParticles()
	{
		if (!this.level.isClientSide)
		{
			if (this.hasTargetPos())
			{
				BlockPos pos = this.getTargetPos();
				BlockState blockstate = this.level.getBlockState(pos);

				if (blockstate != null && !this.level.isEmptyBlock(pos))
				{
					((ServerWorld)this.level).sendParticles(new BlockParticleData(ParticleTypes.BLOCK, blockstate).setPos(pos), this.getX(), this.getBoundingBox().maxY, this.getZ(), 50, 0.0D, 0.0D, 0.0D, 0.05D);
				}
			}

			this.playSound(SoundEvents.STONE_BREAK, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.2F + 0.9F));
		}
	}

	private static boolean isRedstoneConductorBlock(World world, BlockPos pos)
	{
		return world.getBlockState(pos).isRedstoneConductor(world, pos);
	}

	@Override
	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
	{
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

		this.setActionPhase(StoneularEntity.ActionPhase.WALK);
		this.toRiseTick = 200;

		return spawnDataIn;
	}

	@Override
	protected boolean isMovementNoisy()
	{
		return !this.isAwaiting() && super.isMovementNoisy();
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier)
	{
		return false;
	}

	@Override
	protected void checkFallDamage(double p_184231_1_, boolean p_184231_3_, BlockState p_184231_4_, BlockPos p_184231_5_)
	{
		if (this.isWalking())
		{
			super.checkFallDamage(p_184231_1_, p_184231_3_, p_184231_4_, p_184231_5_);
		}
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (this.isInvulnerableTo(source))
		{
			return false;
		}
		else
		{
			if (source.isFire())
			{
				amount = amount * 0.5F;
			}
			else if (!source.isProjectile() && source instanceof EntityDamageSource && source.getEntity() != null && source.getEntity() instanceof LivingEntity)
			{
				ItemStack stack = ((LivingEntity)source.getEntity()).getMainHandItem();

				if (!stack.isEmpty() && stack.getItem() != null && stack.getItem().isCorrectToolForDrops(Blocks.STONE.defaultBlockState()))
				{
					amount = amount * 2.0F;
				}
			}

			if (!this.level.isClientSide && !this.isNoAi() && amount >= 2.0F && source != DamageSource.IN_WALL)
			{
				if (!this.isWalking())
				{
					if (!this.hasLevitation())
					{
						this.setActionPhase(StoneularEntity.ActionPhase.WALK);
						this.playFallSoundAndParticles();
					}
				}
				else
				{
					this.toRiseTick = 0;
				}
			}

			return super.hurt(source, amount);
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);

		this.setActionPhase(StoneularEntity.ActionPhase.byId(compound.getInt("ActionPhase")));

		if (compound.contains("TargetPos"))
		{
			this.targetPosition = NBTUtil.readBlockPos(compound.getCompound("TargetPos"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);

		compound.putInt("ActionPhase", this.getActionPhase().getId());

		if (this.hasTargetPos())
		{
			compound.put("TargetPos", NBTUtil.writeBlockPos(this.getTargetPos()));
		}
	}

	public StoneularEntity.ActionPhase getActionPhase()
	{
		return StoneularEntity.ActionPhase.byId((int)this.entityData.get(ACTION_PHASE));
	}

	private void setActionPhase(StoneularEntity.ActionPhase phase)
	{
		if (!this.level.isClientSide)
		{
			this.getAttribute(Attributes.ARMOR).removeModifier(ARMOR_MODIFIER);

			if (phase != StoneularEntity.ActionPhase.WALK)
			{
				this.getAttribute(Attributes.ARMOR).addPermanentModifier(ARMOR_MODIFIER);
			}
		}

		this.entityData.set(ACTION_PHASE, (byte)(phase.getId() & 255));
	}

	public boolean hasTargetPos()
	{
		return this.targetPosition != null;
	}

	public void setTargetPos(BlockPos pos)
	{
		this.targetPosition = pos;
	}

	@Nullable
	public BlockPos getTargetPos()
	{
		return this.targetPosition;
	}

	public boolean isWalking()
	{
		return !this.isAwaiting() && !this.isRising();
	}

	public boolean isRising()
	{
		return this.getActionPhase() == StoneularEntity.ActionPhase.RISE;
	}

	public boolean isAwaiting()
	{
		return this.getActionPhase() == StoneularEntity.ActionPhase.AWAIT;
	}

	@OnlyIn(Dist.CLIENT)
	public float getAnimationScale(float f)
	{
		return MathHelper.lerp(f, (float)this.animationTickO / 15.0F, (float)this.animationTick / 15.0F);
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 3;
	}

	@Override
	public double getMyRidingOffset()
	{
		return 0.14D;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return this.isWalking() ? ModSoundEvents.STONEULAR_IDLE.get() : null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.STONEULAR_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.STONEULAR_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		if (this.isWalking())
		{
			this.playSound(SoundEvents.STONE_STEP, 0.15F, 1.0F);
		}
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static enum ActionPhase
	{
		AWAIT(0),
		WALK(1),
		RISE(2);

		private final int id;
		private static final StoneularEntity.ActionPhase[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(StoneularEntity.ActionPhase::getId)).toArray((p) -> {
			return new StoneularEntity.ActionPhase[p];
		});

		private ActionPhase(int idIn)
		{
			this.id = idIn;
		}

		public int getId()
		{
			return this.id;
		}

		public static StoneularEntity.ActionPhase byId(int idIn)
		{
			if (idIn < 0 || idIn >= BY_ID.length)
			{
				idIn = 0;
			}

			return BY_ID[idIn];
		}
	}

	protected class MoveHelperController extends MovementController
	{
		private final StoneularEntity parent;

		public MoveHelperController(StoneularEntity mob)
		{
			super(mob);
			this.parent = mob;
		}

		@Override
		public void tick()
		{
			if (this.parent.isWalking())
			{
				this.parent.setNoGravity(false);
				super.tick();
			}
			else
			{
				this.parent.setNoGravity(true);

				if (this.operation == MovementController.Action.MOVE_TO)
				{
					Vector3d vector3d = new Vector3d(this.wantedX - this.parent.getX(), this.wantedY - this.parent.getY(), this.wantedZ - this.parent.getZ());
					double d0 = vector3d.length();

					if (d0 < this.parent.getBoundingBox().getSize() || (this.parent.isAwaiting() && !ModUtils.canReach(this.parent, vector3d.normalize(), MathHelper.ceil(d0))))
					{
						this.operation = MovementController.Action.WAIT;
						this.parent.setDeltaMovement(this.parent.getDeltaMovement().scale(0.5D));
					}
					else
					{
						this.parent.setDeltaMovement(this.parent.getDeltaMovement().add(vector3d.scale(0.01D)));
					}
				}
			}
		}
	}

	private class AttackGoal extends MeleeAttackGoal2
	{
		public AttackGoal(StoneularEntity mob)
		{
			super(mob, 1.0D, false, 2.0F / 3.0F);
		}

		@Override
		public boolean canUse()
		{
			return StoneularEntity.this.isWalking() && super.canUse();
		}

		@Override
		public boolean canContinueToUse()
		{
			return StoneularEntity.this.isWalking() && super.canContinueToUse();
		}
	}

	private class RandomWalkingGoal extends WaterAvoidingRandomWalkingGoal
	{
		public RandomWalkingGoal(StoneularEntity mob)
		{
			super(mob, 1.0D);
		}

		@Override
		public boolean canUse()
		{
			return StoneularEntity.this.isWalking() && super.canUse();
		}

		@Override
		public boolean canContinueToUse()
		{
			return StoneularEntity.this.isWalking() && super.canContinueToUse();
		}
	}

	private static class MoveToTargetPositionGoal extends Goal
	{
		private final StoneularEntity parent;

		public MoveToTargetPositionGoal(StoneularEntity mob)
		{
			this.parent = mob;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse()
		{
			return !this.parent.getMoveControl().hasWanted() && !this.parent.isWalking() && this.parent.hasValidTargetPos();
		}

		@Override
		public boolean canContinueToUse()
		{
			return false;
		}

		@Override
		public void start()
		{
			if (this.parent.hasTargetPos())
			{
				BlockPos pos2 = this.parent.getTargetPos();
				double d0 = pos2.getX();
				double d1 = pos2.getY();
				double d2 = pos2.getZ();
				this.parent.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
			}
		}
	}
}