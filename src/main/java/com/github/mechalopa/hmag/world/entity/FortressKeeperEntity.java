package com.github.mechalopa.hmag.world.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.world.entity.ai.goal.MeleeAttackGoal2;
import com.github.mechalopa.hmag.world.entity.ai.goal.RangedAttackGoal2;
import com.github.mechalopa.hmag.world.entity.projectile.MagmaBulletEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
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
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class FortressKeeperEntity extends Monster implements RangedAttackMob
{
	private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(FortressKeeperEntity.class, EntityDataSerializers.BYTE);
	private float attackAnimation;
	private float attackAnimationO;
	private float attackHandChangeAnimation;
	private float attackHandChangeAnimationO;

	public FortressKeeperEntity(EntityType<? extends FortressKeeperEntity> type, Level level)
	{
		super(type, level);
		this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
		this.setPathfindingMalus(BlockPathTypes.LAVA, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(3, new MeleeAttackGoal2(this, 1.2D, false, 2.0F / 3.0F, 4.0F));
		this.goalSelector.addGoal(4, new FortressKeeperEntity.RangedAttackGoal(this));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_FLAGS_ID, (byte)0);
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.22D)
				.add(Attributes.ATTACK_DAMAGE, 8.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 2.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.75D);
	}

	@Override
	public void tick()
	{
		if (this.level.isClientSide)
		{
			this.attackAnimationO = this.attackAnimation;
			this.attackHandChangeAnimationO = this.attackHandChangeAnimation;
		}

		super.tick();

		if (this.level.isClientSide)
		{
			if (this.isRangedAttacking())
			{
				this.attackAnimation = Math.min(1.0F, this.attackAnimation + 0.15F);
			}
			else
			{
				this.attackAnimation = Math.max(0.0F, this.attackAnimation - 0.08F);
			}

			if (this.isOffHandRangedAttack())
			{
				this.attackHandChangeAnimation = Math.min(1.0F, this.attackHandChangeAnimation + 0.05F);
			}
			else
			{
				this.attackHandChangeAnimation = Math.max(0.0F, this.attackHandChangeAnimation - 0.05F);
			}
		}
	}

	@Override
	public void aiStep()
	{
		if (this.level.isClientSide)
		{
			if (this.tickCount % 16 == 0 && !this.isInvisible() && !this.isInWaterOrBubble() && this.attackAnim <= 0.2F)
			{
				double d0 = this.getY() + 2.625D;

				if (this.attackAnimation <= 0.0F || (this.attackHandChangeAnimation >= 1.0F ^ !this.isLeftHanded()))
				{
					float f = Mth.cos(this.yBodyRot * ((float)Math.PI / 180.0F)) * (0.75F + (this.getRandom().nextFloat() - 0.5F) * 0.75F);
					float f1 = Mth.sin(this.yBodyRot * ((float)Math.PI / 180.0F)) * (0.75F + (this.getRandom().nextFloat() - 0.5F) * 0.75F);
					this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getX() + f, d0 - this.getRandom().nextDouble() * 0.1F, this.getZ() + f1, 0.0D, 0.0D, 0.0D);
				}

				if (this.attackAnimation <= 0.0F || (this.attackHandChangeAnimation <= 0.0F ^ !this.isLeftHanded()))
				{
					float f2 = Mth.cos(this.yBodyRot * ((float)Math.PI / 180.0F)) * (0.75F + (this.getRandom().nextFloat() - 0.5F) * 0.75F);
					float f3 = Mth.sin(this.yBodyRot * ((float)Math.PI / 180.0F)) * (0.75F + (this.getRandom().nextFloat() - 0.5F) * 0.75F);
					this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getX() - f2, d0 - this.getRandom().nextDouble() * 0.1F, this.getZ() - f3, 0.0D, 0.0D, 0.0D);
				}
			}
		}

		super.aiStep();
	}

	@Override
	public boolean doHurtTarget(Entity entity)
	{
		if (super.doHurtTarget(entity))
		{
			if (entity instanceof LivingEntity)
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
					((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, i * 20, 0));
				}

				this.level.broadcastEntityEvent(this, (byte)4);
				this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
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
		if (source.is(ModTags.DamageTypeTags.FORTRESS_KEEPER_RESISTANT_TO))
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor)
	{
		float f = Mth.cos(this.yBodyRot * ((float)Math.PI / 180.0F)) * 0.5625F;
		float f1 = Mth.sin(this.yBodyRot * ((float)Math.PI / 180.0F)) * 0.5625F;

		if (this.isOffHandRangedAttack() ^ !this.isLeftHanded())
		{
			f *= -1;
			f1 *= -1;
		}

		Vec3 vec3 = this.getViewVector(1.0F);
		f += vec3.x * 1.25F;
		f1 += vec3.z * 1.25F;
		double d0 = target.getX() - (this.getX() + f);
		double d1 = target.getY() + target.getEyeHeight() * 0.5D - (this.getY() + 0.75F);
		double d2 = target.getZ() - (this.getZ() + f1);
		double d3 = Math.sqrt(d0 * d0 + d2 * d2);
		MagmaBulletEntity bulletentity = new MagmaBulletEntity(this.level, this, d0 + this.getRandom().nextGaussian() * (d3 * 0.01F), d1 + d3 * 0.08F, d2 + this.getRandom().nextGaussian() * (d3 * 0.01F));
		bulletentity.setPos(bulletentity.getX() + f, this.getY() + 0.75F, bulletentity.getZ() + f1);
		bulletentity.setDamage(4.0F);
		this.level.addFreshEntity(bulletentity);

		for (int i = 0; i < 8; ++i)
		{
			((ServerLevel)this.level).sendParticles(ParticleTypes.POOF, bulletentity.getX(), bulletentity.getY(), bulletentity.getZ(), 1, this.getRandom().nextGaussian() * 0.05D, 0.005D, this.getRandom().nextGaussian() * 0.05D, 0.0D);
		}

		if (!this.isInWaterOrBubble())
		{
			((ServerLevel)this.level).sendParticles(ParticleTypes.LAVA, bulletentity.getX(), bulletentity.getY(), bulletentity.getZ(), 8, 0.0D, 0.0D, 0.0D, 0.0D);
		}

		if (!this.isSilent())
		{
			this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.SHULKER_SHOOT, this.getSoundSource(), 2.0F, (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.2F + 1.0F);
		}

		if (this.getRandom().nextInt(3) == 0)
		{
			this.setOffHandRangedAttack(!this.isOffHandRangedAttack());
		}
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 2;
	}

	@Override
	public int getMaxHeadXRot()
	{
		return 30;
	}

	@Override
	public int getMaxHeadYRot()
	{
		return 30;
	}

	@Override
	public double getMyRidingOffset()
	{
		return -0.1D;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size)
	{
		return 1.74F;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 4)
		{
			this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
			this.attackAnimation = 0.0F;
		}
		else
		{
			super.handleEntityEvent(id);
		}
	}

	public boolean isRangedAttacking()
	{
		return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
	}

	public void setRangedAttacking(boolean flag)
	{
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		this.entityData.set(DATA_FLAGS_ID, flag ? (byte)(b0 | 1) : (byte)(b0 & -2));
	}

	public boolean isOffHandRangedAttack()
	{
		return (this.entityData.get(DATA_FLAGS_ID) & 2) != 0;
	}

	public void setOffHandRangedAttack(boolean flag)
	{
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		this.entityData.set(DATA_FLAGS_ID, flag ? (byte)(b0 | 2) : (byte)(b0 & -3));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setOffHandRangedAttack(compound.getBoolean("isOffHandRangedAttack"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putBoolean("isOffHandRangedAttack", this.isOffHandRangedAttack());
	}

	@OnlyIn(Dist.CLIENT)
	public float getAttackAnimationScale(float f)
	{
		return Mth.lerp(f, this.attackAnimationO, this.attackAnimation);
	}

	@OnlyIn(Dist.CLIENT)
	public float getAttackHandChangeAnimationScale(float f)
	{
		return Mth.lerp(f, this.attackHandChangeAnimationO, this.attackHandChangeAnimation);
	}

	@Override
	public float getLightLevelDependentMagicValue()
	{
		return 1.0F;
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
		this.playSound(SoundEvents.NETHER_BRICKS_STEP, 0.5F, 0.25F);
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private class RangedAttackGoal extends RangedAttackGoal2
	{
		public RangedAttackGoal(RangedAttackMob mob)
		{
			super(mob, 0.9D, 40, 60, 9.0F, 4.0F, false);
		}

		@Override
		public void start()
		{
			super.start();
		}

		@Override
		public void stop()
		{
			super.stop();
			FortressKeeperEntity.this.setRangedAttacking(false);
		}

		@Override
		public void tick()
		{
			super.tick();

			FortressKeeperEntity attacker = FortressKeeperEntity.this;

			if (this.getAttackTime() >= 0 && this.getAttackTime() <= 20)
			{
				if (!attacker.isRangedAttacking())
				{
					attacker.setRangedAttacking(true);
				}
			}
			else if (attacker.isRangedAttacking() && (attacker.distanceToSqr(this.getTarget().getX(), this.getTarget().getY(), this.getTarget().getZ()) > 16.0F * 16.0F || !attacker.getSensing().hasLineOfSight(this.getTarget())))
			{
				attacker.setRangedAttacking(false);
			}
		}
	}
}