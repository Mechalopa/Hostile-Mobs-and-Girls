package com.github.mechalopa.hmag.world.entity;

import java.util.EnumSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.world.entity.ai.goal.LeapAtTargetGoal2;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.NetworkHooks;

public class HarpyEntity extends Monster implements IModMob
{
	private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(HarpyEntity.class, EntityDataSerializers.INT);
	private int animationTick;
	private int animationTickO;

	public HarpyEntity(EntityType<? extends HarpyEntity> type, Level worldIn)
	{
		super(type, worldIn);
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
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(3, new LeapAtTargetGoal2(this, 0.39F, 0.45F, 7.0F, 24));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new HarpyEntity.RandomStrollGoal(this));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true).setUnseenMemoryTicks(120));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.295D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 20.0D)
				.add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.5D);
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
					this.animationTick = Math.min(this.animationTick + 2, 10);
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

		Vec3 vec3 = this.getDeltaMovement();

		if (!this.onGround && vec3.y < 0.0D)
		{
			this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
		}
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source)
	{
		return false;
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType spawnType, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag)
	{
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, spawnType, spawnDataIn, dataTag);

		RandomSource randomsource = worldIn.getRandom();

		if (randomsource.nextFloat() < 0.001F)
		{
			this.setVariant(6);
		}
		else
		{
//			ResourceKey<Biome> biomeKey = ResourceKey.create(ForgeRegistries.Keys.BIOMES, worldIn.getBiome(this.blockPosition()).value().getRegistryName());
//
//			if (biomeKey != null)
//			{
//				if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD))
//				{
//					this.setVariant(this.getRandom().nextInt(4) == 0 ? (this.getRandom().nextInt(3) + 2) : 5);
//				}
//				else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MESA))
//				{
//					this.setVariant(this.getRandom().nextInt(5) == 0 ? 3 : (this.getRandom().nextInt(3) == 0 ? 0 : (this.getRandom().nextInt(2) + 1)));
//				}
//				else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SANDY))
//				{
//					this.setVariant(this.getRandom().nextInt(5) == 0 ? 5 : (this.getRandom().nextInt(3) == 0 ? 1 : (this.getRandom().nextBoolean() ? 0 : 2)));
//				}
//				else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SAVANNA))
//				{
//					this.setVariant(this.getRandom().nextInt(6) == 0 ? 4 : this.getRandom().nextInt(3));
//				}
//				else if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.PLAINS))
//				{
//					this.setVariant(this.getRandom().nextInt(3) == 0 ? (this.getRandom().nextBoolean() ? 1 : 4) : 3);
//				}
//				else
//				{
//					this.setVariant(this.getRandom().nextBoolean() ? 0 : 3);
//				}
//			}
//			else
//			{
//				this.setVariant(randomsource.nextBoolean() ? 0 : 3);
//			}
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
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getInt("Variant"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
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
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn)
	{
		return 1.74F;
	}

	@OnlyIn(Dist.CLIENT)
	public float getAnimationScale(float f)
	{
		return Mth.lerp(f, this.animationTickO / 10.0F, this.animationTick / 10.0F);
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
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private class RandomStrollGoal extends WaterAvoidingRandomStrollGoal
	{
		private boolean isLeaped = false;

		public RandomStrollGoal(HarpyEntity mob)
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
					if (Math.abs(pos.getY() - this.mob.getY()) <= 1.5D)
					{
						final double d0 = this.mob.distanceToSqr(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);

						if (d0 <= 6.0D * 6.0D && d0 > 2.0D * 2.0D)
						{
							Vec3 vec3 = this.mob.getDeltaMovement();
							Vec3 vec31 = new Vec3((pos.getX() + 0.5D) - this.mob.getX(), 0.0D, (pos.getZ() + 0.5D) - this.mob.getZ());

							if (vec31.lengthSqr() > 1.0E-7D)
							{
								vec31 = vec31.normalize().scale(0.4D + this.mob.getRandom().nextFloat() * 0.1F).add(vec3.scale(0.2D));
							}

							this.mob.setDeltaMovement(vec31.x, 0.4D + this.mob.getRandom().nextFloat() * 0.05F, vec31.z);
							this.isLeaped = true;
						}
					}
				}

				if (this.isLeaped)
				{
					this.mob.getLookControl().setLookAt(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 30.0F, 30.0F);
				}
			}
		}
	}
}