package com.github.mechalopa.hmag.entity;

import java.util.EnumSet;
import java.util.Random;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class GhastlySeekerEntity extends FlyingEntity implements IMob, IModMob
{
	private static final DataParameter<Integer> ATTACKING_TIME = EntityDataManager.defineId(GhastlySeekerEntity.class, DataSerializers.INT);
	private int explosionPower = 1;

	public GhastlySeekerEntity(EntityType<? extends GhastlySeekerEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 25;
		this.moveControl = new GhastlySeekerEntity.MoveHelperController(this);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new GhastlySeekerEntity.RandomFlyGoal(this));
		this.goalSelector.addGoal(2, new GhastlySeekerEntity.LookAroundGoal(this));
		this.goalSelector.addGoal(2, new GhastlySeekerEntity.FireballAttackGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p) -> {
			return Math.abs(p.getY() - this.getY()) <= 10.0D;
		}));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ATTACKING_TIME, -1);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MobEntity.createMobAttributes()
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

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (this.isInvulnerableTo(source))
		{
			return false;
		}
		else if (source.getDirectEntity() != null && source.getDirectEntity() instanceof FireballEntity && source.getEntity() != null && source.getEntity() instanceof PlayerEntity)
		{
			return super.hurt(source, amount * 2.0F);
		}
		else
		{
			return super.hurt(source, amount);
		}
	}

	@Override
	public SoundCategory getSoundSource()
	{
		return SoundCategory.HOSTILE;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.GHAST_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
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

	public static boolean checkGhastlySeekerSpawnRules(EntityType<GhastlySeekerEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		return worldIn.getDifficulty() != Difficulty.PEACEFUL && randomIn.nextDouble() < ModConfigs.cachedServer.GHSATLY_SHEEKER_SPAWN_CHANCE && MobEntity.checkMobSpawnRules(type, worldIn, reason, pos, randomIn);
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 1;
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("ExplosionPower", this.explosionPower);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);

		if (compound.contains("ExplosionPower", 99))
		{
			this.explosionPower = compound.getInt("ExplosionPower");
		}
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
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

			if ((target.distanceToSqr(this.parent) < d0 * d0 || this.attackTimer > 10) && this.parent.canSee(target))
			{
				World world = this.parent.level;
				++this.attackTimer;

				if (this.attackTimer == 10 && !this.parent.isSilent())
				{
					world.levelEvent((PlayerEntity)null, 1015, this.parent.blockPosition(), 0);
				}

				if (this.attackTimer == 20)
				{
					double d1 = 4.0D;
					Vector3d vector3d = this.parent.getViewVector(1.0F);
					double d2 = target.getX() - (this.parent.getX() + vector3d.x * d1);
					double d3 = target.getY() + (double)target.getEyeHeight() * 0.5D - this.parent.getY(0.5D) + 0.25D;
					double d4 = target.getZ() - (this.parent.getZ() + vector3d.z * d1);

					if (!this.parent.isSilent())
					{
						world.levelEvent((PlayerEntity)null, 1016, this.parent.blockPosition(), 0);
					}

					FireballEntity fireballentity = new FireballEntity(world, this.parent, d2, d3, d4);
					fireballentity.explosionPower = this.parent.getExplosionPower();
					fireballentity.setPos(this.parent.getX() + vector3d.x * 0.5D, this.parent.getY(0.5D) + 0.25D, fireballentity.getZ() + vector3d.z * 0.5D);
					world.addFreshEntity(fireballentity);
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
				Vector3d vector3d = this.parent.getDeltaMovement();
				this.parent.yRot = -((float)MathHelper.atan2(vector3d.x, vector3d.z)) * (180.0F / (float)Math.PI);
				this.parent.yBodyRot = this.parent.yRot;
			}
			else
			{
				LivingEntity target = this.parent.getTarget();

				if (target.distanceToSqr(this.parent) < 4096.0D)
				{
					double d0 = target.getX() - this.parent.getX();
					double d1 = target.getZ() - this.parent.getZ();
					this.parent.yRot = -((float)MathHelper.atan2(d0, d1)) * (180.0F / (float)Math.PI);
					this.parent.yBodyRot = this.parent.yRot;
				}
			}
		}
	}

	protected class MoveHelperController extends MovementController
	{
		private final GhastlySeekerEntity parent;
		private int courseChangeCooldown;

		public MoveHelperController(GhastlySeekerEntity mob)
		{
			super(mob);
			this.parent = mob;
		}

		@Override
		public void tick()
		{
			if (this.operation == MovementController.Action.MOVE_TO)
			{
				if (this.courseChangeCooldown-- <= 0)
				{
					this.courseChangeCooldown += this.parent.getRandom().nextInt(5) + 2;
					Vector3d vector3d = new Vector3d(this.wantedX - this.parent.getX(), this.wantedY - this.parent.getY(), this.wantedZ - this.parent.getZ());
					double d0 = vector3d.length();
					vector3d = vector3d.normalize();

					if (ModUtils.canReach(this.parent, vector3d, MathHelper.ceil(d0)))
					{
						this.parent.setDeltaMovement(this.parent.getDeltaMovement().add(vector3d.scale(0.1D)));
					}
					else
					{
						this.operation = MovementController.Action.WAIT;
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
			MovementController movementcontroller = this.parent.getMoveControl();

			if (!movementcontroller.hasWanted())
			{
				return true;
			}
			else
			{
				double d0 = movementcontroller.getWantedX() - this.parent.getX();
				double d1 = movementcontroller.getWantedY() - this.parent.getY();
				double d2 = movementcontroller.getWantedZ() - this.parent.getZ();
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
			Random random = this.parent.getRandom();
			boolean flag = false;

			if (this.parent.getTarget() != null)
			{
				LivingEntity target = this.parent.getTarget();
				double distance = target.distanceToSqr(this.parent);

				if (distance < 4096.0D && distance > 16.0D * 16.0D)
				{
					Vector3d vector3d = target.getEyePosition(1.0F);
					this.parent.getMoveControl().setWantedPosition(vector3d.x + (double)((random.nextFloat() * 2.0F - 1.0F) * 2.0F), vector3d.y - 1.0D, vector3d.z + (double)((random.nextFloat() * 2.0F - 1.0F) * 2.0F), 0.75D);
					flag = true;
				}
			}

			if (!flag)
			{
				double d0 = this.parent.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
				double d1 = this.parent.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
				double d2 = this.parent.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
				this.parent.getMoveControl().setWantedPosition(d0, d1, d2, 0.75D);
			}
		}
	}
}