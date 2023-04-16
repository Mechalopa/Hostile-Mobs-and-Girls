package com.github.mechalopa.hmag.world.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.registry.ModParticleTypes;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.world.entity.projectile.MagicBulletEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.NetworkHooks;

public class NightwalkerEntity extends Monster implements RangedAttackMob
{
	public NightwalkerEntity(EntityType<? extends NightwalkerEntity> type, Level level)
	{
		super(type, level);
		this.xpReward = 12;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(4, new RangedAttackGoal(this, 1.0D, 40, 60, 6.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 4.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.22D)
				.add(Attributes.ARMOR, 2.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
				.add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.0D);
	}

	@Override
	public void aiStep()
	{
		if (this.level.isClientSide && this.tickCount % 2 == 0)
		{
			this.level.addParticle(ModParticleTypes.NIGHTWALKER.get(), this.getRandomX(0.75D), this.getRandomY() - 0.25D, this.getRandomZ(0.75D), (this.getRandom().nextDouble() - 0.5D) * 3.0D, -this.getRandom().nextDouble(), (this.getRandom().nextDouble() - 0.5D) * 3.0D);
		}

		super.aiStep();
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.isMagic())
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distance)
	{
		double d1 = target.getX() - this.getX();
		double d2 = target.getY() + target.getEyeHeight() * 0.5D - this.getY(0.4D);
		double d3 = target.getZ() - this.getZ();
		double d4 = Math.sqrt(d1 * d1 + d3 * d3) * 0.04D;
		MagicBulletEntity bullet= new MagicBulletEntity(this.level, this, d1 + this.getRandom().nextGaussian() * d4, d2, d3 + this.getRandom().nextGaussian() * d4);
		bullet.setPos(bullet.getX(), this.getY(0.4D) + 0.25D, bullet.getZ());
		bullet.setDamage(4.0F);
		bullet.setEffectLevel((byte)1);
		bullet.setVariant(3);
		this.level.addFreshEntity(bullet);
		this.playSound(SoundEvents.SHULKER_SHOOT, 2.0F, (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.2F + 1.0F);
	}

	@Override
	public boolean isAlliedTo(Entity entity)
	{
		if (super.isAlliedTo(entity))
		{
			return true;
		}
		else if (entity instanceof NightwalkerEntity)
		{
			return this.getTeam() == null && entity.getTeam() == null;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 3;
	}

	@Override
	public int getMaxHeadXRot()
	{
		return 9;
	}

	@Override
	public int getMaxHeadYRot()
	{
		return 30;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size)
	{
		return 2.0F;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.DOLL_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource)
	{
		return ModSoundEvents.DOLL_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.DOLL_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block)
	{
		this.playSound(SoundEvents.STONE_STEP, 0.5F, 0.25F);
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}