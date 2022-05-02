package com.github.mechalopa.hmag.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.entity.goal.LeapAtTargetGoal2;
import com.github.mechalopa.hmag.registry.ModSoundEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
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
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

public class SnowCanineEntity extends Monster implements IModMob
{
	public SnowCanineEntity(EntityType<? extends SnowCanineEntity> type, Level worldIn)
	{
		super(type, worldIn);
		this.xpReward = 12;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(3, new LeapAtTargetGoal2(this, 0.375F, 0.5F, 5.0F, 8));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		if (ModConfigs.cachedServer.SNOW_CANINE_ATTACK_SHEEP)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Sheep.class, 10, true, false, (p) -> {
				return p.distanceToSqr(this) <= 6.0D * 6.0D;
			}));
		if (ModConfigs.cachedServer.SNOW_CANINE_ATTACK_RABBITS)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Rabbit.class, 10, true, false, (p) -> {
				return p.distanceToSqr(this) <= 6.0D * 6.0D;
			}));
		if (ModConfigs.cachedServer.SNOW_CANINE_ATTACK_BABY_TURTLES)
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.325D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.5D)
				.add(Attributes.ARMOR, 2.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.25D);
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source == DamageSource.FALL)
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
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
		this.playSound(SoundEvents.ZOMBIE_STEP, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}