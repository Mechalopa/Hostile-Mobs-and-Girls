package com.github.mechalopa.hmag.world.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.world.entity.ai.goal.MeleeAttackGoal2;
import com.github.mechalopa.hmag.world.entity.ai.goal.RangedAttackGoal2;
import com.github.mechalopa.hmag.world.entity.projectile.PoisonSeedEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.network.NetworkHooks;

public class ArurauneEntity extends Monster implements IModMob, RangedAttackMob
{
	public ArurauneEntity(EntityType<? extends ArurauneEntity> type, Level worldIn)
	{
		super(type, worldIn);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(3, new MeleeAttackGoal2(this, 1.5D, false, -1.0F, 3.0F));
		this.goalSelector.addGoal(4, new RangedAttackGoal2(this, 1.0D, 40, 60, 9.0F, 4.0F, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		if (ModConfigs.cachedServer.ARURAUNE_ATTACK_VILLAGERS)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.12D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.98D);
	}

	@Override
	protected void customServerAiStep()
	{
		super.customServerAiStep();

		if (this.isAlive() && ModConfigs.cachedServer.ARURAUNE_REGEN)
		{
			if (this.isInWaterRainOrBubble() && !this.hasEffect(MobEffects.REGENERATION))
			{
				this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 10 * 20, 2));
			}
		}
	}

	@Override
	public boolean doHurtTarget(Entity entityIn)
	{
		if (super.doHurtTarget(entityIn))
		{
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
					((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.POISON, i * 20, 1));
					((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, i * 20, 1));
					((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, i * 20, 2));
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
		if (source.isFire())
		{
			amount = amount * 2.0F;
		}

		return super.hurt(source, amount);
	}

	@Override
	public boolean canBeAffected(MobEffectInstance potioneffectIn)
	{
		if (potioneffectIn.getEffect() == MobEffects.POISON || potioneffectIn.getEffect() == MobEffects.CONFUSION)
		{
			PotionEvent.PotionApplicableEvent event = new PotionEvent.PotionApplicableEvent(this, potioneffectIn);
			MinecraftForge.EVENT_BUS.post(event);
			return event.getResult() == Event.Result.ALLOW;
		}

		return super.canBeAffected(potioneffectIn);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor)
	{
		int c = this.getRandom().nextInt(3) + 1;

		for (int i = 0; i < c; ++i)
		{
			PoisonSeedEntity shot = new PoisonSeedEntity(this.level, this);
			double d0 = target.getEyeY() - 1.1F;
			double d1 = target.getX() - this.getX();
			double d2 = d0 - shot.getY();
			double d3 = target.getZ() - this.getZ();
			double d4 = Math.sqrt(d1 * d1 + d3 * d3) * 0.15F;
			shot.shoot(d1, d2 + d4, d3, 1.5F, 10.0F);
			shot.setDamage(4.0F);
			this.level.addFreshEntity(shot);
		}

		this.playSound(SoundEvents.LLAMA_SPIT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.swing(InteractionHand.MAIN_HAND);
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