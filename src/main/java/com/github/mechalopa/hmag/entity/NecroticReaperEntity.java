package com.github.mechalopa.hmag.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class NecroticReaperEntity extends MonsterEntity implements IModMob
{
	public NecroticReaperEntity(EntityType<? extends NecroticReaperEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true).setUnseenMemoryTicks(120));
		if (ModConfigs.cachedServer.NECROTIC_REAPER_ATTACK_VILLAGERS)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		if (ModConfigs.cachedServer.NECROTIC_REAPER_ATTACK_BABY_TURTLES)
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.31D)
				.add(Attributes.ATTACK_DAMAGE, 9.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.25D)
				.add(Attributes.FOLLOW_RANGE, 24.0D);
	}

	@Override
	public CreatureAttribute getMobType()
	{
		return CreatureAttribute.UNDEAD;
	}

	@Override
	public void aiStep()
	{
		if (this.level.isClientSide)
		{
			this.level.addParticle(ParticleTypes.MYCELIUM, this.getRandomX(0.5D), this.getRandomY() - 0.5D, this.getRandomZ(0.5D), (this.getRandom().nextDouble() - 0.5D) * 3.0D, -this.getRandom().nextDouble(), (this.getRandom().nextDouble() - 0.5D) * 3.0D);
		}

		ModUtils.burnInDay(this, this.getRandom(), this.isSunBurnTick(), 8);

		super.aiStep();
	}

	@Override
	public boolean doHurtTarget(Entity entityIn)
	{
		if (super.doHurtTarget(entityIn))
		{
			float f = this.level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();

			if (this.getMainHandItem().isEmpty() && this.isOnFire() && this.getRandom().nextFloat() < f * 0.3F)
			{
				entityIn.setSecondsOnFire(2 * (int)f);
			}

			if (entityIn instanceof LivingEntity)
			{
				int i = 0;

				if (this.level.getDifficulty() == Difficulty.NORMAL)
				{
					i = 7;
				}
				else if (this.level.getDifficulty() == Difficulty.HARD)
				{
					i = 15;
				}

				if (i > 0)
				{
					((LivingEntity)entityIn).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, i * 20, 0));
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
	public int getMaxSpawnClusterSize()
	{
		return 2;
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

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.HUSK_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.HUSK_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.HUSK_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.WITHER_SKELETON_STEP, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}