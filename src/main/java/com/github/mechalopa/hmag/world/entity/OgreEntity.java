package com.github.mechalopa.hmag.world.entity;

import java.util.Random;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;

public class OgreEntity extends Monster implements IModMob
{
	private static final EntityDataAccessor<Boolean> IS_ARM_SWING = SynchedEntityData.defineId(OgreEntity.class, EntityDataSerializers.BOOLEAN);
	private int armSwingTimer;
	private float attackAnimation;
	private float attackAnimationO;

	public OgreEntity(EntityType<? extends OgreEntity> type, Level worldIn)
	{
		super(type, worldIn);
		this.xpReward = 25;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackAndDestroyGoal(this, 1.0D, true));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		if (ModConfigs.cachedServer.OGRE_ATTACK_VILLAGERS)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		if (ModConfigs.cachedServer.OGRE_ATTACK_BABY_TURTLES)
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(IS_ARM_SWING, false);
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.285D)
				.add(Attributes.ATTACK_DAMAGE, 12.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 2.0D)
				.add(Attributes.ARMOR, 8.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
	}

	@Override
	public void tick()
	{
		if (this.level.isClientSide)
		{
			this.attackAnimationO = this.attackAnimation;
		}

		super.tick();

		if (this.level.isClientSide)
		{
			if (this.isArmSwinging())
			{
				this.attackAnimation = Math.min(1.0F, this.attackAnimation + 0.45F);
			}
			else
			{
				this.attackAnimation = Math.max(0.0F, this.attackAnimation - 0.08F);
			}
		}
	}

	@Override
	public void aiStep()
	{
		if (!this.level.isClientSide)
		{
			boolean flag = false;

			if (this.armSwingTimer > 0)
			{
				flag = true;
			}

			if (this.isArmSwinging() != flag)
			{
				this.setArmSwinging(flag);
			}
		}

		super.aiStep();

		if (!this.level.isClientSide)
		{
			if (this.armSwingTimer > 0)
			{
				--this.armSwingTimer;
			}
		}
	}

	@Override
	public boolean doHurtTarget(Entity entityIn)
	{
		this.armSwingTimer = 3;

		if (super.doHurtTarget(entityIn))
		{
			this.playArmSwingSound();

			if (entityIn instanceof LivingEntity)
			{
				int i = 0;

				if (this.level.getDifficulty() == Difficulty.NORMAL)
				{
					i = 5;
				}
				else if (this.level.getDifficulty() == Difficulty.HARD)
				{
					i = 10;
				}

				if (i > 0)
				{
					((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, i * 20, 3));
				}
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source == DamageSource.IN_WALL || source == DamageSource.CRAMMING || ModUtils.isThornsDamage(source) || ModUtils.isStalagmiteDamage(source))
		{
			amount = amount * 0.25F;

			if (!this.isNoAi() && !source.isNoAggro() && this.getRandom().nextInt(8) == 0)
			{
				this.destroyBlock();
			}
		}
		else if (source.isProjectile() || source.isExplosion() || source.isFire() || source == DamageSource.FALL || source == DamageSource.FALLING_BLOCK || source == DamageSource.FREEZE)
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	public static boolean checkOgreSpawnRules(EntityType<? extends OgreEntity> type, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, Random random)
	{
		return Monster.checkMonsterSpawnRules(type, levelAccessor, spawnType, pos, random) && (spawnType == MobSpawnType.SPAWNER || pos.getY() <= ModConfigs.cachedServer.OGRE_SPAWN_MAX_HEIGHT);
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 1;
	}

	@Override
	public double getMyRidingOffset()
	{
		return -0.6D;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn)
	{
		return 2.5F;
	}

	public boolean isArmSwinging()
	{
		return this.entityData.get(IS_ARM_SWING);
	}

	public void setArmSwinging(boolean flag)
	{
		this.entityData.set(IS_ARM_SWING, flag);
	}

	@OnlyIn(Dist.CLIENT)
	public float getAnimationScale(float f)
	{
		return Mth.lerp(f, this.attackAnimationO, this.attackAnimation);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.OGRE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.OGRE_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.OGRE_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.COW_STEP, 0.25F, 1.0F);
	}

	protected void playArmSwingSound()
	{
		this.playSound(SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR, 0.5F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
	}

	protected boolean destroyBlock()
	{
		if (ModConfigs.cachedServer.OGRE_DESTROY_BLOCKS && this.level.getDifficulty().getId() > 1 && ForgeEventFactory.getMobGriefingEvent(this.level, this))
		{
			int i1 = Mth.floor(this.getY());
			int l1 = Mth.floor(this.getX());
			int i2 = Mth.floor(this.getZ());
			boolean flag = false;

			for (int k2 = -1; k2 <= 1; ++k2)
			{
				for (int l2 = -1; l2 <= 1; ++l2)
				{
					for (int j = 0; j <= 3; ++j)
					{
						int i3 = l1 + k2;
						int k = i1 + j;
						int l = i2 + l2;
						BlockPos blockpos = new BlockPos(i3, k, l);
						BlockState blockstate = this.level.getBlockState(blockpos);

						if (this.canDestroyBlock(blockstate, this.level, blockpos, this, 5.0F))
						{
							flag = this.level.destroyBlock(blockpos, true, this) || flag;
						}
					}
				}
			}

			if (flag)
			{
				this.armSwingTimer = 5;
				this.playArmSwingSound();
			}

			return flag;
		}
		else
		{
			return false;
		}
	}

	private boolean canDestroyBlock(BlockState state, Level level, BlockPos pos, LivingEntity entityIn, float maxHardness)
	{
		if (state.is(ModTags.OGRE_IMMUNE) || state.isAir() || state.getMaterial().isLiquid() || !(state.canEntityDestroy(this.level, pos, this) && ForgeEventFactory.onEntityDestroyBlock(this, pos, state)))
		{
			return false;
		}
		else
		{
			float f = state.getDestroySpeed(level, pos);

			return f >= 0.0F && f <= maxHardness && f / maxHardness <= this.getRandom().nextFloat() + 0.05F && this.getRandom().nextBoolean();
		}
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private class MeleeAttackAndDestroyGoal extends MeleeAttackGoal
	{
		public MeleeAttackAndDestroyGoal(OgreEntity mob, double speedIn, boolean useLongMemory)
		{
			super(mob, speedIn, useLongMemory);
		}

		@Override
		protected void checkAndPerformAttack(LivingEntity attackTarget, double distToEnemySqr)
		{
			double d0 = this.getAttackReachSqr(attackTarget);

			if (this.getTicksUntilNextAttack() <= 0)
			{
				if (distToEnemySqr <= d0)
				{
					this.resetAttackCooldown();
					this.mob.swing(InteractionHand.MAIN_HAND);
					this.mob.doHurtTarget(attackTarget);
				}
				else if (((distToEnemySqr < 10.0D * 10.0D && !this.mob.getSensing().hasLineOfSight(attackTarget)) || distToEnemySqr < 3.0D * 3.0D) && this.mob.getRandom().nextInt(12) == 0)
				{
					OgreEntity.this.destroyBlock();
				}
			}
		}
	}
}