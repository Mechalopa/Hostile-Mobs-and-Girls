package hmag.entity;

import java.util.EnumSet;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import hmag.entity.goal.LeapAtTargetGoal2;
import hmag.registry.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.network.NetworkHooks;

public class HarpyEntity extends MonsterEntity implements IModMob
{
	private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(HarpyEntity.class, DataSerializers.INT);
	private int animationTick;
	private int animationTickO;

	public HarpyEntity(EntityType<? extends HarpyEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.maxUpStep = 1.5F;
		this.xpReward = 12;
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_VARIANT_ID, 0);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(3, new LeapAtTargetGoal2(this, 0.375F, 0.45F, 7.0F, 24));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new HarpyEntity.RandomWalkingGoal(this));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true).setUnseenMemoryTicks(120));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.295D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 20.0D);
	}

	@Override
	public void tick()
	{
		if (this.level.isClientSide)
		{
			this.animationTickO = this.animationTick;
		}

		super.tick();

		if (this.level.isClientSide)
		{
			if (!this.isOnGround() && !this.isInWaterOrBubble() && !this.isPassenger())
			{
				if (this.animationTick < 10)
				{
					++this.animationTick;
				}
			}
			else if (this.animationTick > 0)
			{
				this.animationTick = Math.max(this.animationTick - 5, 0);
			}
		}
	}

	@Override
	public void aiStep()
	{
		super.aiStep();

		Vector3d vector3d = this.getDeltaMovement();

		if (!this.isOnGround() && vector3d.y < 0.0D)
		{
			this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
		}
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier)
	{
		return false;
	}

	@Override
	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
	{
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

		if (this.getRandom().nextFloat() < 0.001F)
		{
			this.setVariant(6);
		}
		else
		{
			Optional<RegistryKey<Biome>> optional = worldIn.getBiomeName(this.blockPosition());

			if (optional.isPresent())
			{
				if (BiomeDictionary.hasType(optional.get(), BiomeDictionary.Type.COLD))
				{
					this.setVariant(this.getRandom().nextInt(4) == 0 ? (this.getRandom().nextInt(3) + 2) : 5);
				}
				else if (BiomeDictionary.hasType(optional.get(), BiomeDictionary.Type.MESA))
				{
					this.setVariant(this.getRandom().nextInt(5) == 0 ? 3 : (this.getRandom().nextInt(3) == 0 ? 0 : (this.getRandom().nextInt(2) + 1)));
				}
				else if (BiomeDictionary.hasType(optional.get(), BiomeDictionary.Type.SANDY))
				{
					this.setVariant(this.getRandom().nextInt(5) == 0 ? 5 : (this.getRandom().nextInt(3) == 0 ? 1 : (this.getRandom().nextBoolean() ? 0 : 2)));
				}
				else if (BiomeDictionary.hasType(optional.get(), BiomeDictionary.Type.SAVANNA))
				{
					this.setVariant(this.getRandom().nextInt(6) == 0 ? 4 : this.getRandom().nextInt(3));
				}
				else if (BiomeDictionary.hasType(optional.get(), BiomeDictionary.Type.PLAINS))
				{
					this.setVariant(this.getRandom().nextInt(3) == 0 ? (this.getRandom().nextBoolean() ? 1 : 4) : 3);
				}
				else
				{
					this.setVariant(this.getRandom().nextBoolean() ? 0 : 3);
				}
			}
			else
			{
				this.setVariant(this.getRandom().nextBoolean() ? 0 : 3);
			}
		}

		return spawnDataIn;
	}

	public int getVariant()
	{
		return this.entityData.get(DATA_VARIANT_ID);
	}

	private void setVariant(int typeIn)
	{
		if (typeIn < 0 || typeIn >= 7)
		{
			typeIn = this.getRandom().nextInt(6);
		}

		this.entityData.set(DATA_VARIANT_ID, typeIn);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getInt("Variant"));
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Variant", this.getVariant());
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 3;
	}

	@Override
	public double getMyRidingOffset()
	{
		return -0.45D;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return 1.74F;
	}

	@OnlyIn(Dist.CLIENT)
	public float getAnimationScale(float f)
	{
		return MathHelper.lerp(f, (float)this.animationTickO / 10.0F, (float)this.animationTick / 10.0F);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.GIRL_MOB_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.GIRL_MOB_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.GIRL_MOB_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private class RandomWalkingGoal extends WaterAvoidingRandomWalkingGoal
	{
		private boolean isLeaped = false;

		public RandomWalkingGoal(HarpyEntity mob)
		{
			super(mob, 1.0D);
			this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
		}

		@Override
		public void start()
		{
			super.start();
			this.isLeaped = false;
		}

		@Override
		public void stop()
		{
			super.stop();
			this.isLeaped = false;
		}

		@Override
		public boolean canContinueToUse()
		{
			return super.canContinueToUse() && (!this.isLeaped || !this.mob.isOnGround());
		}

		@Override
		public void tick()
		{
			super.tick();

			BlockPos pos = this.mob.getNavigation().getTargetPos();

			if (pos != null)
			{
				if (!this.isLeaped && this.mob.isOnGround() && this.mob.getRandom().nextInt(32) == 0)
				{
					if (Math.abs((double)pos.getY() - this.mob.getY()) <= 1.5D)
					{
						final double d0 = this.mob.distanceToSqr((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D);

						if (d0 <= 6.0D * 6.0D && d0 > 2.0D * 2.0D)
						{
							Vector3d vector3d = this.mob.getDeltaMovement();
							Vector3d vector3d1 = new Vector3d(((double)pos.getX() + 0.5D) - this.mob.getX(), 0.0D, ((double)pos.getZ() + 0.5D) - this.mob.getZ());

							if (vector3d1.lengthSqr() > 1.0E-7D)
							{
								vector3d1 = vector3d1.normalize().scale(0.4D + (double)(this.mob.getRandom().nextFloat() * 0.1F)).add(vector3d.scale(0.2D));
							}

							this.mob.setDeltaMovement(vector3d1.x, 0.4D + (double)(this.mob.getRandom().nextFloat() * 0.05F), vector3d1.z);
							this.isLeaped = true;
						}
					}
				}

				if (this.isLeaped)
				{
					this.mob.getLookControl().setLookAt((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, 30.0F, 30.0F);
				}
			}
		}
	}
}