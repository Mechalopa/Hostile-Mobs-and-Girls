package com.github.mechalopa.hmag.entity;

import java.util.EnumSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class AbstractFlyingMonsterEntity extends MonsterEntity
{
	protected static final DataParameter<Byte> ATTACK_PHASE = EntityDataManager.defineId(AbstractFlyingMonsterEntity.class, DataSerializers.BYTE);
	private BlockPos boundOrigin;

	public AbstractFlyingMonsterEntity(EntityType<? extends AbstractFlyingMonsterEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.moveControl = new AbstractFlyingMonsterEntity.MoveHelperController(this);
	}

	@Override
	public void tick()
	{
		super.tick();
		this.setNoGravity(true);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ATTACK_PHASE, (byte)0);
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier)
	{
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (this.isInvulnerableTo(source))
		{
			return false;
		}
		else
		{
			if (!this.level.isClientSide && source.getEntity() instanceof LivingEntity && this.isCharging() && this.getRandom().nextInt(3) == 0)
			{
				this.setAttackPhase(1);
			}

			return super.hurt(source, amount);
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		compound.putInt("AttackPhase", this.getAttackPhase());

		if (compound.contains("BoundX"))
		{
			this.boundOrigin = new BlockPos(compound.getInt("BoundX"), compound.getInt("BoundY"), compound.getInt("BoundZ"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);
		this.setAttackPhase(compound.getInt("AttackPhase"));

		if (this.boundOrigin != null)
		{
			compound.putInt("BoundX", this.boundOrigin.getX());
			compound.putInt("BoundY", this.boundOrigin.getY());
			compound.putInt("BoundZ", this.boundOrigin.getZ());
		}
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Nullable
	public BlockPos getBoundOrigin()
	{
		return this.boundOrigin;
	}

	public void setBoundOrigin(@Nullable BlockPos boundOriginIn)
	{
		this.boundOrigin = boundOriginIn;
	}

	public int getAttackPhase()
	{
		return (int)this.entityData.get(ATTACK_PHASE);
	}

	public void setAttackPhase(int value)
	{
		int i = value;

		if (i < 0 || i >= 3)
		{
			i = 0;
		}

		this.entityData.set(ATTACK_PHASE, (byte)(i & 255));
	}

	public boolean isCharging()
	{
		return this.getAttackPhase() == 2;
	}

	protected class ChargeAttackGoal extends Goal
	{
		private final double moveSpeed;
		private final float attackRadius;
		private final int chance;
		protected int attackTime;

		public ChargeAttackGoal()
		{
			this(0.3D, 2.0F);
		}

		public ChargeAttackGoal(double moveSpeedIn, float maxAttackDistanceIn)
		{
			this(moveSpeedIn, maxAttackDistanceIn, 4);
		}

		public ChargeAttackGoal(double moveSpeedIn, float maxAttackDistanceIn, int chanceIn)
		{
			this.moveSpeed = moveSpeedIn;
			this.attackRadius = maxAttackDistanceIn;
			this.chance = chanceIn;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse()
		{
			if (AbstractFlyingMonsterEntity.this.getTarget() != null && !AbstractFlyingMonsterEntity.this.getMoveControl().hasWanted() && AbstractFlyingMonsterEntity.this.getRandom().nextInt(this.chance) == 0)
			{
				return AbstractFlyingMonsterEntity.this.distanceToSqr(AbstractFlyingMonsterEntity.this.getTarget()) > (double)this.attackRadius;
			}
			else
			{
				return false;
			}
		}

		@Override
		public boolean canContinueToUse()
		{
			return AbstractFlyingMonsterEntity.this.getMoveControl().hasWanted() && AbstractFlyingMonsterEntity.this.isCharging() && AbstractFlyingMonsterEntity.this.getTarget() != null && AbstractFlyingMonsterEntity.this.getTarget().isAlive();
		}

		@Override
		public void start()
		{
			LivingEntity livingentity = AbstractFlyingMonsterEntity.this.getTarget();

			if (AbstractFlyingMonsterEntity.this.canSee(livingentity) || AbstractFlyingMonsterEntity.this.getAttackPhase() != 0)
			{
				Vector3d vector3d = livingentity.getEyePosition(1.0F);
				AbstractFlyingMonsterEntity.this.moveControl.setWantedPosition(vector3d.x, vector3d.y - 0.75D, vector3d.z, this.moveSpeed);
				AbstractFlyingMonsterEntity.this.setAttackPhase(2);
			}
		}

		@Override
		public void stop()
		{
			AbstractFlyingMonsterEntity.this.setAttackPhase((AbstractFlyingMonsterEntity.this.getTarget() != null && AbstractFlyingMonsterEntity.this.getTarget().isAlive()) ? 1 : 0);
		}

		@Override
		public void tick()
		{
			AbstractFlyingMonsterEntity attacker = AbstractFlyingMonsterEntity.this;
			LivingEntity livingentity = attacker.getTarget();

			this.attackTime = Math.max(this.attackTime - 1, 0);
			attacker.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
			double d0 = attacker.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
			double d1 = this.getAttackReachSqr(livingentity);

			if (d0 <= d1 && this.attackTime <= 0)
			{
				this.attackTime = 20;
				attacker.swing(Hand.MAIN_HAND);
				attacker.doHurtTarget(livingentity);
				attacker.setAttackPhase(1);
			}
			else
			{
				if (attacker.canSee(livingentity))
				{
					if (d0 < (double)(this.attackRadius + 15.0F))
					{
						Vector3d vector3d = livingentity.getEyePosition(1.0F);
						attacker.moveControl.setWantedPosition(vector3d.x, vector3d.y - 0.75D, vector3d.z, this.moveSpeed);
					}
				}
				else if (attacker.getRandom().nextInt(16) == 0)
				{
					attacker.setAttackPhase(0);
				}
			}
		}

		protected double getAttackReachSqr(LivingEntity attackTarget)
		{
			return (double)(AbstractFlyingMonsterEntity.this.getBbWidth() * 2.0F * AbstractFlyingMonsterEntity.this.getBbWidth() * 2.0F + attackTarget.getBbWidth());
		}
	}

	protected class MoveHelperController extends MovementController
	{
		public MoveHelperController(AbstractFlyingMonsterEntity mob)
		{
			super(mob);
		}

		@Override
		public void tick()
		{
			AbstractFlyingMonsterEntity flyingentity = AbstractFlyingMonsterEntity.this;

			if (this.operation == MovementController.Action.MOVE_TO)
			{
				Vector3d vector3d = new Vector3d(this.wantedX - flyingentity.getX(), this.wantedY - flyingentity.getY(), this.wantedZ - flyingentity.getZ());
				double d0 = vector3d.length();

				if (d0 < flyingentity.getBoundingBox().getSize() || !ModUtils.canReach(flyingentity, vector3d.normalize(), MathHelper.ceil(d0)))
				{
					this.operation = MovementController.Action.WAIT;
					flyingentity.setDeltaMovement(flyingentity.getDeltaMovement().scale(0.5D));
				}
				else
				{
					float f = (float)flyingentity.getAttributeValue(Attributes.MOVEMENT_SPEED);
					flyingentity.setDeltaMovement(flyingentity.getDeltaMovement().add(vector3d.scale((float)this.speedModifier * f * 0.2D / d0)));

					if (flyingentity.getTarget() == null)
					{
						Vector3d vector3d1 = flyingentity.getDeltaMovement();
						flyingentity.yRot = -((float)MathHelper.atan2(vector3d1.x, vector3d1.z)) * (180.0F / (float)Math.PI);
						flyingentity.yBodyRot = flyingentity.yRot;
					}
					else
					{
						double d2 = flyingentity.getTarget().getX() - flyingentity.getX();
						double d1 = flyingentity.getTarget().getZ() - flyingentity.getZ();
						flyingentity.yRot = -((float)MathHelper.atan2(d2, d1)) * (180.0F / (float)Math.PI);
						flyingentity.yBodyRot = flyingentity.yRot;
					}
				}
			}
		}
	}

	protected class MoveRandomGoal extends Goal
	{
		private final double moveSpeed;
		private final int chance;
		private final int width;
		private final int height;

		public MoveRandomGoal()
		{
			this(0.25D);
		}

		public MoveRandomGoal(double moveSpeedIn)
		{
			this(moveSpeedIn, 6, 3, 2);
		}

		public MoveRandomGoal(double moveSpeedIn, int chanceIn, int width, int height)
		{
			this.moveSpeed = moveSpeedIn;
			this.chance = chanceIn;
			this.width = width;
			this.height = height;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse()
		{
			return !AbstractFlyingMonsterEntity.this.getMoveControl().hasWanted() && AbstractFlyingMonsterEntity.this.getRandom().nextInt(this.chance) == 0;
		}

		@Override
		public boolean canContinueToUse()
		{
			return false;
		}

		@Override
		public void start()
		{
			if (!(AbstractFlyingMonsterEntity.this.getTarget() != null && AbstractFlyingMonsterEntity.this.getTarget().isAlive()))
			{
				AbstractFlyingMonsterEntity.this.setAttackPhase(0);
			}
		}

		@Override
		public void tick()
		{
			AbstractFlyingMonsterEntity flyingentity = AbstractFlyingMonsterEntity.this;
			BlockPos blockpos = flyingentity.getBoundOrigin();

			if (blockpos == null)
			{
				blockpos = flyingentity.blockPosition();
			}

			for (int i = 0; i < 3; ++i)
			{
				BlockPos blockpos1 = blockpos.offset(flyingentity.getRandom().nextInt(this.width * 2 + 1) - this.width, flyingentity.getRandom().nextInt(this.height * 2 + 1) - this.height, flyingentity.getRandom().nextInt(this.width * 2 + 1) - this.width);

				if (flyingentity.level.isEmptyBlock(blockpos1))
				{
					flyingentity.moveControl.setWantedPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, this.moveSpeed);

					if (flyingentity.getTarget() == null)
					{
						flyingentity.getLookControl().setLookAt((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
					}

					break;
				}
			}
		}
	}
}