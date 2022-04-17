package com.github.mechalopa.hmag.entity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.registry.ModParticleTypes;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.network.NetworkHooks;

public class MonolithEntity extends FlyingEntity implements IMob, IModMob, IBeamAttackMob
{
	private static final DataParameter<Byte> ATTACK_PHASE = EntityDataManager.defineId(MonolithEntity.class, DataSerializers.BYTE);
	private static final DataParameter<Integer> ATTACK_TARGET = EntityDataManager.defineId(MonolithEntity.class, DataSerializers.INT);
	private LivingEntity targetedEntity;
	private int clientAttackTime;
	private int eyeCloseTimer = 0;

	public MonolithEntity(EntityType<? extends MonolithEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 15;
		this.moveControl = new MonolithEntity.MoveHelperController(this);
	}

	@Override
	protected BodyController createBodyControl()
	{
		return new MonolithEntity.BodyHelperController(this);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new MonolithEntity.RandomFlyGoal(this));
		this.goalSelector.addGoal(2, new MonolithEntity.RoarAttackGoal(this));
		this.goalSelector.addGoal(2, new MonolithEntity.BeamAttackGoal(this));
		this.goalSelector.addGoal(3, new MonolithEntity.LookTargetGoal(this));
		this.goalSelector.addGoal(4, new MonolithEntity.LookAroundGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, new MonolithEntity.TargetPredicate(this)));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ATTACK_TARGET, 0);
		this.entityData.define(ATTACK_PHASE, (byte)0);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MobEntity.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.16D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D)
				.add(Attributes.ARMOR, 8.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.75D)
				.add(Attributes.FOLLOW_RANGE, 24.0D);
	}

	@Override
	public void aiStep()
	{
		if (!this.level.isClientSide)
		{
			if (this.isAlive())
			{
				MonolithEntity.AttackPhase phase = this.getAttackPhase();

				if (phase == MonolithEntity.AttackPhase.BEAM_END || phase == MonolithEntity.AttackPhase.ROAR_END)
				{
					++this.eyeCloseTimer;

					if (this.eyeCloseTimer >= 10)
					{
						this.setAttackPhase(MonolithEntity.AttackPhase.WAIT);
						this.eyeCloseTimer = 0;
					}
				}
				else
				{
					this.eyeCloseTimer = 0;
				}
			}
		}
		else
		{
			if (this.isAlive())
			{
				if (this.hasActiveAttackTarget())
				{
					if (this.clientAttackTime < this.getAttackDuration())
					{
						++this.clientAttackTime;
					}

					LivingEntity target = this.getActiveAttackTarget();

					if (target != null)
					{
						this.getLookControl().setLookAt(target, 90.0F, 90.0F);
						this.getLookControl().tick();
					}
				}
				else
				{
					this.clientAttackTime = 0;
				}

				if (this.getAttackPhase().isBeamAttack())
				{
					double d0 = this.getRandomX(1.5D);
					double d1 = this.getRandomY() - 0.25D;
					double d2 = this.getRandomZ(1.5D);
					this.level.addParticle(ParticleTypes.REVERSE_PORTAL, d0, d1, d2, (d0 - this.getX()) * -0.025D, (d1 - (this.getY(0.5D) - 0.25D)) * -0.025D, (d2 - this.getZ()) * -0.025D);
				}
				else if (this.getAttackPhase().isRoarAttack())
				{
					for (int i = 0; i < 8; ++i)
					{
						double d0 = this.getRandomX(1.5D);
						double d1 = this.getRandomY() - 0.25D;
						double d2 = this.getRandomZ(1.5D);
						this.level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, (d0 - this.getX()) * 0.2D, (d1 - (this.getY(0.5D) - 0.25D)) * 0.2D, (d2 - this.getZ()) * 0.2D);
					}
				}
				else
				{
					if (this.tickCount % 12 == 0)
					{
						this.level.addParticle(ModParticleTypes.ENCHANTMENT_RUNE.get(), this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), (this.getRandom().nextDouble() - 0.5D) * 2.0D, this.getRandom().nextDouble() - 0.25D, (this.getRandom().nextDouble() - 0.5D) * 2.0D);
					}
				}
			}
			else
			{
				this.clientAttackTime = 0;
			}
		}

		super.aiStep();
	}

	public MonolithEntity.AttackPhase getAttackPhase()
	{
		return MonolithEntity.AttackPhase.byId((int)this.entityData.get(ATTACK_PHASE));
	}

	private void setAttackPhase(MonolithEntity.AttackPhase phase)
	{
		this.entityData.set(ATTACK_PHASE, (byte)(phase.getId() & 255));
	}

	@Override
	protected boolean shouldDespawnInPeaceful()
	{
		return true;
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.isProjectile() || source.isFire() || source.isMagic() || ModUtils.isThornsDamage(source))
		{
			amount = amount * 0.5F;
		}
		else if (source instanceof EntityDamageSource && source.getEntity() != null && source.getEntity() instanceof LivingEntity)
		{
			ItemStack stack = ((LivingEntity)source.getEntity()).getMainHandItem();

			if (!stack.isEmpty() && stack.getItem() != null && stack.getItem().isCorrectToolForDrops(Blocks.STONE.defaultBlockState()))
			{
				amount = amount * 2.0F;
			}
		}

		return super.hurt(source, amount);
	}

	@Override
	public boolean canBeAffected(EffectInstance potioneffectIn)
	{
		if (potioneffectIn.getEffect() == Effects.POISON || potioneffectIn.getEffect() == Effects.BLINDNESS)
		{
			PotionEvent.PotionApplicableEvent event = new PotionEvent.PotionApplicableEvent(this, potioneffectIn);
			MinecraftForge.EVENT_BUS.post(event);
			return event.getResult() == Event.Result.ALLOW;
		}

		return super.canBeAffected(potioneffectIn);
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
	{
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

		if (reason == SpawnReason.NATURAL)
		{
			Vector3d vector3d = this.getDeltaMovement();
			this.setDeltaMovement(new Vector3d(vector3d.x, 0.05D, vector3d.z));
		}

		return spawnDataIn;
	}

	@Override
	public void setTarget(@Nullable LivingEntity entitylivingbaseIn)
	{
		if (entitylivingbaseIn == null)
		{
			this.setActiveAttackTarget(0);
		}

		super.setTarget(entitylivingbaseIn);
	}

	@Override
	public float getBrightness()
	{
		return MathHelper.clamp(super.getBrightness() + 0.8F, 0.0F, 1.0F);
	}

	@Override
	public SoundCategory getSoundSource()
	{
		return SoundCategory.HOSTILE;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.DOLL_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.DOLL_DEATH.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return 1.0F;
	}

	public static boolean checkMonolithSpawnRules(EntityType<MonolithEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		if (worldIn.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(type, worldIn, reason, pos, randomIn))
		{
			if (ModUtils.isDarkEnoughToSpawn(worldIn, pos, randomIn))
			{
				return true;
			}
			else
			{
				BlockState state = worldIn.getBlockState(pos.mutable().move(Direction.DOWN));
				return state != null && state.getBlock() != null && ModTags.checkTagContains(ModTags.MONOLITH_SPAWNABLE_IN_LIGHT, state.getBlock());
			}
		}

		return false;
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 3;
	}

	@Override
	public void onSyncedDataUpdated(DataParameter<?> key)
	{
		super.onSyncedDataUpdated(key);

		if (ATTACK_TARGET.equals(key))
		{
			this.clientAttackTime = 0;
			this.targetedEntity = null;
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 15)
		{
			for (int i = 0; i < 32; ++i)
			{
				this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(1.0D), this.getRandomY() - 0.25D, this.getRandomZ(1.0D), this.getRandom().nextGaussian() * 0.2D, this.getRandom().nextGaussian() * 0.2D, this.getRandom().nextGaussian() * 0.2D);
			}
		}
		else
		{
			super.handleEntityEvent(id);
		}
	}

	@Override
	public int getAttackDuration()
	{
		return 60;
	}

	@Override
	public boolean attackEntityWithBeamAttack(LivingEntity target, float damage)
	{
		if (!this.isSilent())
		{
			this.level.playSound((PlayerEntity)null, target.getX(), target.getY(), target.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, this.getSoundSource(), 1.0F, this.getRandom().nextFloat() * 0.2F + 0.9F);
		}

		float f = damage;
		int i = 0;

		if (this.level.getDifficulty() == Difficulty.NORMAL)
		{
			i = 5;
		}
		else if (this.level.getDifficulty() == Difficulty.HARD)
		{
			f += 2.0F;
			i = 10;
		}

		final boolean flag = target.hurt(DamageSource.indirectMagic(this, this), f);
		final boolean flag1 = target.hurt(DamageSource.mobAttack(this), (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));

		if (flag || flag1)
		{
			if (i > 0)
			{
				final int j = this.getRandom().nextInt(3);
				target.addEffect(new EffectInstance(j == 2 ? Effects.WEAKNESS : (j == 1 ? Effects.MOVEMENT_SLOWDOWN : Effects.DIG_SLOWDOWN), i * 20, 0));
			}

			return true;
		}

		return false;
	}

	@Override
	public void setActiveAttackTarget(int entityId)
	{
		this.entityData.set(ATTACK_TARGET, entityId);
	}

	@Override
	public boolean hasActiveAttackTarget()
	{
		return this.entityData.get(ATTACK_TARGET) != 0;
	}

	@Nullable
	@Override
	public LivingEntity getActiveAttackTarget()
	{
		if (!this.hasActiveAttackTarget())
		{
			return null;
		}
		else if (this.level.isClientSide)
		{
			if (this.targetedEntity != null)
			{
				return this.targetedEntity;
			}
			else
			{
				Entity entity = this.level.getEntity(this.entityData.get(ATTACK_TARGET));

				if (entity instanceof LivingEntity)
				{
					this.targetedEntity = (LivingEntity)entity;
					return this.targetedEntity;
				}
				else
				{
					return null;
				}
			}
		}
		else
		{
			return this.getTarget();
		}
	}

	@Override
	public float getAttackAnimationScale(float f)
	{
		return ((float)this.clientAttackTime + f) / (float)this.getAttackDuration();
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static enum AttackPhase
	{
		WAIT(0, false, false),
		BEAM_CHARGE(1, true, false),
		BEAM_CHARGE2(2, true, false),
		BEAM_ATTACK(3, true, false),
		BEAM_END(4, false, false),
		ROAR_CHARGE(5, false, true),
		ROAR_ATTACK(6, false, true),
		ROAR_END(7, false, false);

		private final int id;
		private final boolean isBeam;
		private final boolean isRoar;
		private static final MonolithEntity.AttackPhase[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(MonolithEntity.AttackPhase::getId)).toArray((p) -> {
			return new MonolithEntity.AttackPhase[p];
		});

		private AttackPhase(int idIn, boolean isBeamIn, boolean isRoarIn)
		{
			this.id = idIn;
			this.isBeam = isBeamIn;
			this.isRoar = isRoarIn;
		}

		public int getId()
		{
			return this.id;
		}

		public boolean isWait()
		{
			return !this.isBeamAttack() && !this.isRoarAttack();
		}

		public boolean isBeamAttack()
		{
			return this.isBeam;
		}

		public boolean isRoarAttack()
		{
			return this.isRoar;
		}

		public static MonolithEntity.AttackPhase byId(int idIn)
		{
			if (idIn < 0 || idIn >= BY_ID.length)
			{
				idIn = 0;
			}

			return BY_ID[idIn];
		}
	}

	private static class RoarAttackGoal extends Goal
	{
		private final MonolithEntity parent;
		private int tickCounter;

		public RoarAttackGoal(MonolithEntity mob)
		{
			this.parent = mob;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse()
		{
			if (this.parent.getTarget() != null && this.parent.getTarget().isAlive() && this.parent.getAttackPhase().isWait() && this.parent.getAttackPhase() != MonolithEntity.AttackPhase.ROAR_END)
			{
				return this.parent.distanceToSqr(this.parent.getTarget()) <= 3.0D * 3.0D;
			}
			else
			{
				return false;
			}
		}

		@Override
		public boolean canContinueToUse()
		{
			if (this.parent.getTarget() != null && this.parent.getTarget().isAlive() && this.parent.getAttackPhase().isRoarAttack())
			{
				return this.parent.distanceToSqr(this.parent.getTarget()) < 4096D;
			}
			else
			{
				return false;
			}
		}

		@Override
		public void start()
		{
			this.tickCounter = -5;
			this.parent.setAttackPhase(MonolithEntity.AttackPhase.ROAR_CHARGE);
		}

		@Override
		public void stop()
		{
			this.parent.setAttackPhase(MonolithEntity.AttackPhase.ROAR_END);
		}

		@Override
		public void tick()
		{
			LivingEntity target = this.parent.getTarget();

			if (target == null || !this.parent.canSee(target))
			{
				this.parent.setAttackPhase(MonolithEntity.AttackPhase.ROAR_END);
				this.parent.setTarget((LivingEntity)null);
			}
			else
			{
				++this.tickCounter;

				if (this.tickCounter == 0)
				{
					this.parent.playSound(SoundEvents.EVOKER_CAST_SPELL, 1.0F, 1.0F);
					this.parent.setAttackPhase(MonolithEntity.AttackPhase.ROAR_ATTACK);
				}
				else if (this.tickCounter >= 15)
				{
					for (LivingEntity livingentity : this.parent.level.getEntitiesOfClass(LivingEntity.class, this.parent.getBoundingBox().inflate(2.0D), new MonolithEntity.RoarTargetPredicate()))
					{
						if (!(livingentity instanceof PlayerEntity && (((PlayerEntity)livingentity).isCreative() || ((PlayerEntity)livingentity).isSpectator())))
						{
							float f = 3.0F;
							int i = 0;

							if (this.parent.level.getDifficulty() == Difficulty.NORMAL)
							{
								i = 7;
							}
							else if (this.parent.level.getDifficulty() == Difficulty.HARD)
							{
								f += 2.0F;
								i = 12;
							}

							if (i > 0)
							{
								livingentity.addEffect(new EffectInstance(Effects.BLINDNESS, i * 10, 0));
								livingentity.addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, i * 20, 1));
								livingentity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, i * 20, 1));
								livingentity.addEffect(new EffectInstance(Effects.WEAKNESS, i * 20, 1));
							}

							livingentity.knockback(1.5F, this.parent.getX() - livingentity.getX(), this.parent.getZ() - livingentity.getZ());
							livingentity.hurt(DamageSource.indirectMagic(this.parent, this.parent), f);
						}
					}

					this.parent.playSound(SoundEvents.SHULKER_SHOOT, 1.0F, (this.parent.getRandom().nextFloat() - this.parent.getRandom().nextFloat()) * 0.2F + 1.0F);
					this.parent.level.broadcastEntityEvent(this.parent, (byte)15);
					Vector3d vector3d = this.parent.getDeltaMovement();
					Vector3d vector3d1 = (new Vector3d(target.getX() - this.parent.getX(), 0.0D, target.getZ() - this.parent.getZ())).normalize().scale(0.25D);
					this.parent.setDeltaMovement(vector3d.x / 2.0D - vector3d1.x, vector3d.y, vector3d.z / 2.0D - vector3d1.z);
					this.parent.setAttackPhase(MonolithEntity.AttackPhase.ROAR_END);
				}
			}
		}
	}

	private static class BeamAttackGoal extends Goal
	{
		private final MonolithEntity parent;
		private int tickCounter;

		public BeamAttackGoal(MonolithEntity mob)
		{
			this.parent = mob;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse()
		{
			return this.parent.getTarget() != null && this.parent.getTarget().isAlive() && this.parent.getAttackPhase().isWait();
		}

		@Override
		public boolean canContinueToUse()
		{
			if (this.parent.getTarget() != null && this.parent.getTarget().isAlive())
			{
				double d0 = this.parent.distanceToSqr(this.parent.getTarget());
				return d0 > 2.0D * 2.0D && d0 <= 10.0D * 10.0D;
			}
			else
			{
				return false;
			}
		}

		@Override
		public void start()
		{
			this.tickCounter = -20;
			this.parent.setAttackPhase(MonolithEntity.AttackPhase.BEAM_CHARGE);
		}

		@Override
		public void stop()
		{
			this.parent.setActiveAttackTarget(0);
			this.parent.setTarget((LivingEntity)null);
			this.parent.setAttackPhase(MonolithEntity.AttackPhase.BEAM_END);
		}

		@Override
		public void tick()
		{
			LivingEntity target = this.parent.getTarget();

			if (target == null || !this.parent.canSee(target))
			{
				this.parent.setAttackPhase(MonolithEntity.AttackPhase.BEAM_END);
				this.parent.setTarget((LivingEntity)null);
			}
			else
			{
				++this.tickCounter;

				if (this.tickCounter >= -5 && this.tickCounter < 0 && this.parent.getAttackPhase() != MonolithEntity.AttackPhase.BEAM_CHARGE2)
				{
					this.parent.setAttackPhase(MonolithEntity.AttackPhase.BEAM_CHARGE2);
				}
				else if (this.tickCounter == 0)
				{
					this.parent.setAttackPhase(MonolithEntity.AttackPhase.BEAM_ATTACK);
					this.parent.playSound(SoundEvents.EVOKER_CAST_SPELL, 1.0F, 1.0F);
					this.parent.setActiveAttackTarget(target.getId());

					if (this.parent.getRandom().nextFloat() < 0.4F)
					{
						Vector3d vector3d = target.getEyePosition(1.0F);
						this.parent.getMoveControl().setWantedPosition(vector3d.x + (double)((this.parent.getRandom().nextFloat() * 2.0F - 1.0F) * 3.0F), vector3d.y - (double)(0.5F + this.parent.getRandom().nextFloat()), vector3d.z + (double)((this.parent.getRandom().nextFloat() * 2.0F - 1.0F) * 3.0F), 0.2D);
					}
				}
				else if (this.tickCounter >= this.parent.getAttackDuration())
				{
					if (this.parent.getActiveAttackTarget() != null)
					{
						this.parent.attackEntityWithBeamAttack(this.parent.getActiveAttackTarget(), 1.0F);
					}

					this.parent.setAttackPhase(MonolithEntity.AttackPhase.BEAM_END);
					this.parent.setTarget((LivingEntity)null);
				}
			}
		}
	}

	private static class LookTargetGoal extends Goal
	{
		private final MonolithEntity parent;

		public LookTargetGoal(MonolithEntity mob)
		{
			this.parent = mob;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse()
		{
			LivingEntity target = this.parent.getTarget();
			return target != null && target.distanceToSqr(this.parent) < 4096.0D;
		}

		@Override
		public boolean canContinueToUse()
		{
			return this.canUse();
		}

		@Override
		public void tick()
		{
			LivingEntity target = this.parent.getTarget();

			if (target != null && target.distanceToSqr(this.parent) < 4096.0D)
			{
				double d0 = target.getX() - this.parent.getX();
				double d1 = target.getZ() - this.parent.getZ();
				this.parent.yRot = -((float)MathHelper.atan2(d0, d1)) * (180.0F / (float)Math.PI);
				this.parent.yBodyRot = this.parent.yRot;
			}
		}
	}

	private static class LookAroundGoal extends Goal
	{
		private final MonolithEntity parent;
		private double lookX;
		private double lookZ;
		private int idleTime;

		public LookAroundGoal(MonolithEntity mob)
		{
			this.parent = mob;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse()
		{
			return this.parent.getRandom().nextFloat() < 0.008F;
		}

		@Override
		public boolean canContinueToUse()
		{
			return this.idleTime >= 0;
		}

		@Override
		public void start()
		{
			double d0 = (Math.PI * 2.0D) * this.parent.getRandom().nextDouble();
			this.lookX = Math.cos(d0);
			this.lookZ = Math.sin(d0);
			this.idleTime = 20 + this.parent.getRandom().nextInt(20);
		}

		@Override
		public void tick()
		{
			--this.idleTime;
			this.parent.yRot = -((float)MathHelper.atan2(this.lookX, this.lookZ)) * (180.0F / (float)Math.PI);
			this.parent.yBodyRot = this.parent.yRot;
		}
	}

	protected class MoveHelperController extends MovementController
	{
		private final MonolithEntity parent;

		public MoveHelperController(MonolithEntity mob)
		{
			super(mob);
			this.parent = mob;
		}

		@Override
		public void tick()
		{
			if (this.operation == MovementController.Action.MOVE_TO)
			{
				Vector3d vector3d = new Vector3d(this.wantedX - this.parent.getX(), this.wantedY - this.parent.getY(), this.wantedZ - this.parent.getZ());
				double d0 = vector3d.length();

				if (d0 < this.parent.getBoundingBox().getSize() || !ModUtils.canReach(this.parent, vector3d.normalize(), MathHelper.ceil(d0)))
				{
					this.operation = MovementController.Action.WAIT;
					this.parent.setDeltaMovement(this.parent.getDeltaMovement().scale(0.5D));
				}
				else
				{
					float f = (float)this.parent.getAttributeValue(Attributes.MOVEMENT_SPEED);
					this.parent.setDeltaMovement(this.parent.getDeltaMovement().add(vector3d.scale((float)this.speedModifier * f * 0.2D / d0)));
				}
			}
		}
	}

	protected class BodyHelperController extends BodyController
	{
		public BodyHelperController(MobEntity mob)
		{
			super(mob);
		}

		@Override
		public void clientTick()
		{
			MonolithEntity.this.yHeadRot = MonolithEntity.this.yBodyRot;
			MonolithEntity.this.yBodyRot = MonolithEntity.this.yRot;
		}
	}

	private static class RandomFlyGoal extends Goal
	{
		private final MonolithEntity parent;

		public RandomFlyGoal(MonolithEntity mob)
		{
			this.parent = mob;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse()
		{
			return !this.parent.getMoveControl().hasWanted() && this.parent.getAttackPhase().isWait() && this.parent.getRandom().nextFloat() < 0.01F;
		}

		@Override
		public boolean canContinueToUse()
		{
			return false;
		}

		@Override
		public void start()
		{
			Random random = this.parent.getRandom();
			double d0 = this.parent.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 3.0F);
			double d1 = this.parent.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 3.0F);
			double d2 = this.parent.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 3.0F);
			this.parent.getMoveControl().setWantedPosition(d0, d1, d2, 0.15D);
		}
	}

	private static class TargetPredicate implements Predicate<LivingEntity>
	{
		private final MonolithEntity parent;

		public TargetPredicate(MonolithEntity mob)
		{
			this.parent = mob;
		}

		@Override
		public boolean test(@Nullable LivingEntity livingEntityIn)
		{
			if (livingEntityIn instanceof MonolithEntity)
			{
				return false;
			}
			else if (this.parent.getLastHurtByMob() != null && this.parent.getLastHurtByMob().equals(livingEntityIn) && !(livingEntityIn.getType() == EntityType.PLAYER && this.parent.level.getGameRules().getBoolean(GameRules.RULE_UNIVERSAL_ANGER)))
			{
				return livingEntityIn.distanceToSqr(this.parent) <= 9.0D * 9.0D;
			}
			else if (ModTags.checkTagContains(ModTags.MONOLITH_TARGET_BLACKLIST, livingEntityIn.getType()))
			{
				return false;
			}
			else if (livingEntityIn instanceof PlayerEntity || (livingEntityIn instanceof GolemEntity && ModConfigs.cachedServer.MONOLITH_ATTACK_GOLEMS) || (livingEntityIn instanceof AbstractVillagerEntity && ModConfigs.cachedServer.MONOLITH_ATTACK_VILLAGERS) || (livingEntityIn.getMobType() == CreatureAttribute.ILLAGER && ModConfigs.cachedServer.MONOLITH_ATTACK_ILLAGERS))
			{
				return livingEntityIn.distanceToSqr(this.parent) <= 9.0D * 9.0D;
			}
			else
			{
				return false;
			}
		}
	}

	private static class RoarTargetPredicate implements Predicate<LivingEntity>
	{
		@Override
		public boolean test(@Nullable LivingEntity livingEntityIn)
		{
			return livingEntityIn.isAlive() && !(livingEntityIn instanceof MonolithEntity);
		}
	}
}