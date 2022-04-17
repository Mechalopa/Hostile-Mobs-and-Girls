package com.github.mechalopa.hmag.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.entity.goal.MeleeAttackGoal2;
import com.github.mechalopa.hmag.entity.goal.RangedAttackGoal2;
import com.github.mechalopa.hmag.entity.projectile.MagmaBulletEntity;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class FortressKeeperEntity extends MonsterEntity implements IModMob, IRangedAttackMob
{
	private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(SavagefangEntity.class, DataSerializers.BYTE);
	private float attackAnimation;
	private float attackAnimationO;
	private float attackHandChangeAnimation;
	private float attackHandChangeAnimationO;

	public FortressKeeperEntity(EntityType<? extends FortressKeeperEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
		this.setPathfindingMalus(PathNodeType.LAVA, 8.0F);
		this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 0.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, 0.0F);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(3, new MeleeAttackGoal2(this, 1.2D, false, 2.0F / 3.0F, 4.0F));
		this.goalSelector.addGoal(4, new FortressKeeperEntity.RangedAttackGoal(this));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_FLAGS_ID, (byte)0);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
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
					float f = MathHelper.cos(this.yBodyRot * ((float)Math.PI / 180.0F)) * (0.75F + (this.getRandom().nextFloat() - 0.5F) * 0.75F);
					float f1 = MathHelper.sin(this.yBodyRot * ((float)Math.PI / 180.0F)) * (0.75F + (this.getRandom().nextFloat() - 0.5F) * 0.75F);
					this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getX() + (double)f, d0 - this.getRandom().nextDouble() * (double)0.1F, this.getZ() + (double)f1, 0.0D, 0.0D, 0.0D);
				}

				if (this.attackAnimation <= 0.0F || (this.attackHandChangeAnimation <= 0.0F ^ !this.isLeftHanded()))
				{
					float f2 = MathHelper.cos(this.yBodyRot * ((float)Math.PI / 180.0F)) * (0.75F + (this.getRandom().nextFloat() - 0.5F) * 0.75F);
					float f3 = MathHelper.sin(this.yBodyRot * ((float)Math.PI / 180.0F)) * (0.75F + (this.getRandom().nextFloat() - 0.5F) * 0.75F);
					this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getX() - (double)f2, d0 - this.getRandom().nextDouble() * (double)0.1F, this.getZ() - (double)f3, 0.0D, 0.0D, 0.0D);
				}
			}
		}

		super.aiStep();
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
		if (source.isProjectile() || source.isMagic() || source.isFire() || ModUtils.isThornsDamage(source))
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor)
	{
		float f = MathHelper.cos(this.yBodyRot * ((float)Math.PI / 180.0F)) * 0.5625F;
		float f1 = MathHelper.sin(this.yBodyRot * ((float)Math.PI / 180.0F)) * 0.5625F;

		if (this.isOffHandRangedAttack() ^ !this.isLeftHanded())
		{
			f *= -1;
			f1 *= -1;
		}

		Vector3d vector3d = this.getViewVector(1.0F);
		f += vector3d.x * 1.25F;
		f1 += vector3d.z * 1.25F;
		double d0 = target.getX() - (this.getX() + (double)f);
		double d1 = target.getY() + (double)target.getEyeHeight() * 0.5D - (this.getY() + (double)0.75F);
		double d2 = target.getZ() - (this.getZ() + (double)f1);
		float f2 = MathHelper.sqrt(d0 * d0 + d2 * d2);
		MagmaBulletEntity bulletentity = new MagmaBulletEntity(this.level, this, d0 + this.getRandom().nextGaussian() * (double)(f2 * 0.01F), d1 + (double)(f2 * 0.08F), d2 + this.getRandom().nextGaussian() * (double)(f2 * 0.01F));
		bulletentity.setPos(bulletentity.getX() + (double)f, this.getY() + (double)0.75F, bulletentity.getZ() + (double)f1);
		bulletentity.setDamage(4.0F);
		this.level.addFreshEntity(bulletentity);

		for (int i = 0; i < 8; ++i)
		{
			((ServerWorld)this.level).sendParticles(ParticleTypes.POOF, bulletentity.getX(), bulletentity.getY(), bulletentity.getZ(), 1, this.getRandom().nextGaussian() * 0.05D, 0.005D, this.getRandom().nextGaussian() * 0.05D, 0.0D);
		}

		if (!this.isInWaterOrBubble())
		{
			((ServerWorld)this.level).sendParticles(ParticleTypes.LAVA, bulletentity.getX(), bulletentity.getY(), bulletentity.getZ(), 8, 0.0D, 0.0D, 0.0D, 0.0D);
		}

		if (!this.isSilent())
		{
			this.level.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.SHULKER_SHOOT, this.getSoundSource(), 2.0F, (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.2F + 1.0F);
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
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
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
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setOffHandRangedAttack(compound.getBoolean("isOffHandRangedAttack"));
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putBoolean("isOffHandRangedAttack", this.isOffHandRangedAttack());
	}

	@OnlyIn(Dist.CLIENT)
	public float getAttackAnimationScale(float f)
	{
		return MathHelper.lerp(f, this.attackAnimationO, this.attackAnimation);
	}

	@OnlyIn(Dist.CLIENT)
	public float getAttackHandChangeAnimationScale(float f)
	{
		return MathHelper.lerp(f, this.attackHandChangeAnimationO, this.attackHandChangeAnimation);
	}

	@Override
	public float getBrightness()
	{
		return 1.0F;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.DOLL_AMBIENT.get();
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
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.NETHER_BRICKS_STEP, 0.5F, 0.25F);
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private class RangedAttackGoal extends RangedAttackGoal2
	{
		public RangedAttackGoal(IRangedAttackMob mob)
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
			else if (attacker.isRangedAttacking() && (attacker.distanceToSqr(this.getTarget().getX(), this.getTarget().getY(), this.getTarget().getZ()) > (double)(16.0F * 16.0F) || !attacker.getSensing().canSee(this.getTarget())))
			{
				attacker.setRangedAttacking(false);
			}
		}
	}
}