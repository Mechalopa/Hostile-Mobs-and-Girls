package com.github.mechalopa.hmag.world.entity;

import java.util.EnumSet;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class GhastlySeekerEntity extends FlyingMob implements Enemy
{
	private static final EntityDataAccessor<Integer> ATTACKING_TIME = SynchedEntityData.defineId(GhastlySeekerEntity.class, EntityDataSerializers.INT);
	private int explosionPower = 1;

	public GhastlySeekerEntity(EntityType<? extends GhastlySeekerEntity> type, Level level)
	{
		super(type, level);
		this.xpReward = 25;
		this.moveControl = new GhastlySeekerEntity.GhastlySeekerMoveControl(this);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new GhastlySeekerEntity.RandomFlyGoal(this));
		this.goalSelector.addGoal(2, new GhastlySeekerEntity.LookAroundGoal(this));
		this.goalSelector.addGoal(2, new GhastlySeekerEntity.FireballAttackGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (p) -> {
			return Math.abs(p.getY() - this.getY()) <= 10.0D;
		}));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ATTACKING_TIME, -1);
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.ARMOR, 2.0D)
				.add(Attributes.FOLLOW_RANGE, 64.0D);
	}

	@Override
	public void aiStep()
	{
		if (this.level.isClientSide && this.tickCount % 3 == 0)
		{
			this.level.addParticle(ParticleTypes.FLAME, this.getRandomX(0.5D), this.getRandomY() - 0.8D, this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
		}

		super.aiStep();
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isAttacking()
	{
		return this.getAttackingTime() > 10;
	}

	@OnlyIn(Dist.CLIENT)
	public int getAttackingTime()
	{
		return this.entityData.get(ATTACKING_TIME);
	}

	private void setAttackingTime(int value)
	{
		this.entityData.set(ATTACKING_TIME, value);
	}

	public int getExplosionPower()
	{
		return this.explosionPower;
	}

	@Override
	protected boolean shouldDespawnInPeaceful()
	{
		return true;
	}

	private static boolean isReflectedFireball(DamageSource source)
	{
		return source.getDirectEntity() != null && source.getDirectEntity() instanceof LargeFireball && source.getEntity() instanceof Player;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source)
	{
		return !isReflectedFireball(source) && super.isInvulnerableTo(source);
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (isReflectedFireball(source))
		{
			return super.hurt(source, amount * 2.0F);
		}
		else
		{
			return this.isInvulnerableTo(source) ? false : super.hurt(source, amount);
		}
	}

	@Override
	public SoundSource getSoundSource()
	{
		return SoundSource.HOSTILE;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.GHAST_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource)
	{
		return SoundEvents.GHAST_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.GHAST_DEATH;
	}

	@Override
	protected float getSoundVolume()
	{
		return 5.0F;
	}

	public static boolean checkGhastlySeekerSpawnRules(EntityType<GhastlySeekerEntity> type, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random)
	{
		return levelAccessor.getDifficulty() != Difficulty.PEACEFUL && random.nextDouble() < ModConfigs.cachedServer.GHASTLY_SEEKER_SPAWN_CHANCE && checkMobSpawnRules(type, levelAccessor, spawnType, pos, random);
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 1;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("ExplosionPower", this.explosionPower);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);

		if (compound.contains("ExplosionPower", 99))
		{
			this.explosionPower = compound.getInt("ExplosionPower");
		}
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private static class FireballAttackGoal extends Goal
	{
		private final GhastlySeekerEntity parent;
		public int attackTimer;

		public FireballAttackGoal(GhastlySeekerEntity mob)
		{
			this.parent = mob;
		}

		@Override
		public boolean canUse()
		{
			return this.parent.getTarget() != null;
		}

		@Override
		public void start()
		{
			this.attackTimer = 0;
		}

		@Override
		public void stop()
		{
			this.parent.setAttackingTime(-1);
		}

		@Override
		public void tick()
		{
			LivingEntity target = this.parent.getTarget();
			double d0 = 24.0D;

			if ((target.distanceToSqr(this.parent) < d0 * d0 || this.attackTimer > 10) && this.parent.hasLineOfSight(target))
			{
				Level world = this.parent.level;
				++this.attackTimer;

				if (this.attackTimer == 10 && !this.parent.isSilent())
				{
					world.levelEvent((Player)null, 1015, this.parent.blockPosition(), 0);
				}

				if (this.attackTimer == 20)
				{
					double d1 = 4.0D;
					Vec3 vec3 = this.parent.getViewVector(1.0F);
					double d2 = target.getX() - (this.parent.getX() + vec3.x * d1);
					double d3 = target.getY() + target.getEyeHeight() * 0.5D - this.parent.getY(0.5D) + 0.25D;
					double d4 = target.getZ() - (this.parent.getZ() + vec3.z * d1);

					if (!this.parent.isSilent())
					{
						world.levelEvent((Player)null, 1016, this.parent.blockPosition(), 0);
					}

					LargeFireball largefireball = new LargeFireball(world, this.parent, d2, d3, d4, this.parent.getExplosionPower());
					largefireball.setPos(this.parent.getX() + vec3.x * 0.5D, this.parent.getY(0.5D) + 0.25D, largefireball.getZ() + vec3.z * 0.5D);
					world.addFreshEntity(largefireball);
					this.attackTimer = -50;
				}
			}
			else if (this.attackTimer > 0)
			{
				--this.attackTimer;
			}

			this.parent.setAttackingTime(this.attackTimer < 0 ? -1 : this.attackTimer);
		}
	}

	private static class LookAroundGoal extends Goal
	{
		private final GhastlySeekerEntity parent;

		public LookAroundGoal(GhastlySeekerEntity mob)
		{
			this.parent = mob;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse()
		{
			return true;
		}

		@Override
		public void tick()
		{
			if (this.parent.getTarget() == null)
			{
				Vec3 vec3 = this.parent.getDeltaMovement();
				this.parent.setYRot(-((float)Mth.atan2(vec3.x, vec3.z)) * (180.0F / (float)Math.PI));
				this.parent.yBodyRot = this.parent.getYRot();
			}
			else
			{
				LivingEntity target = this.parent.getTarget();

				if (target.distanceToSqr(this.parent) < 4096.0D)
				{
					double d0 = target.getX() - this.parent.getX();
					double d1 = target.getZ() - this.parent.getZ();
					this.parent.setYRot(-((float)Mth.atan2(d0, d1)) * (180.0F / (float)Math.PI));
					this.parent.yBodyRot = this.parent.getYRot();
				}
			}
		}
	}

	protected class GhastlySeekerMoveControl extends MoveControl
	{
		private final GhastlySeekerEntity parent;
		private int floatDuration;

		public GhastlySeekerMoveControl(GhastlySeekerEntity mob)
		{
			super(mob);
			this.parent = mob;
		}

		@Override
		public void tick()
		{
			if (this.operation == MoveControl.Operation.MOVE_TO)
			{
				if (this.floatDuration-- <= 0)
				{
					this.floatDuration += this.parent.getRandom().nextInt(5) + 2;
					Vec3 vec3 = new Vec3(this.wantedX - this.parent.getX(), this.wantedY - this.parent.getY(), this.wantedZ - this.parent.getZ());
					double d0 = vec3.length();
					vec3 = vec3.normalize();

					if (ModUtils.canReach(this.parent, vec3, Mth.ceil(d0)))
					{
						this.parent.setDeltaMovement(this.parent.getDeltaMovement().add(vec3.scale(0.1D)));
					}
					else
					{
						this.operation = MoveControl.Operation.WAIT;
					}
				}
			}
		}
	}

	private static class RandomFlyGoal extends Goal
	{
		private final GhastlySeekerEntity parent;

		public RandomFlyGoal(GhastlySeekerEntity mob)
		{
			this.parent = mob;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse()
		{
			MoveControl movecontrol = this.parent.getMoveControl();

			if (!movecontrol.hasWanted())
			{
				return true;
			}
			else
			{
				double d0 = movecontrol.getWantedX() - this.parent.getX();
				double d1 = movecontrol.getWantedY() - this.parent.getY();
				double d2 = movecontrol.getWantedZ() - this.parent.getZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0D || d3 > 3600.0D;
			}
		}

		@Override
		public boolean canContinueToUse()
		{
			return false;
		}

		@Override
		public void start()
		{
			RandomSource random = this.parent.getRandom();
			boolean flag = false;

			if (this.parent.getTarget() != null)
			{
				LivingEntity target = this.parent.getTarget();
				double distance = target.distanceToSqr(this.parent);

				if (distance < 4096.0D && distance > 16.0D * 16.0D)
				{
					Vec3 vec3 = target.getEyePosition(1.0F);
					this.parent.getMoveControl().setWantedPosition(vec3.x + (random.nextFloat() * 2.0F - 1.0F) * 2.0F, vec3.y - 1.0D, vec3.z + (random.nextFloat() * 2.0F - 1.0F) * 2.0F, 0.75D);
					flag = true;
				}
			}

			if (!flag)
			{
				double d0 = this.parent.getX() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
				double d1 = this.parent.getY() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
				double d2 = this.parent.getZ() + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
				this.parent.getMoveControl().setWantedPosition(d0, d1, d2, 0.75D);
			}
		}
	}
}