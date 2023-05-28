package com.github.mechalopa.hmag.world.entity;

import java.util.EnumSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public abstract class AbstractFlyingMonsterEntity extends Monster
{
	protected static final EntityDataAccessor<Byte> ATTACK_PHASE = SynchedEntityData.defineId(AbstractFlyingMonsterEntity.class, EntityDataSerializers.BYTE);
	private BlockPos boundOrigin;

	public AbstractFlyingMonsterEntity(EntityType<? extends AbstractFlyingMonsterEntity> type, Level level)
	{
		super(type, level);
		this.moveControl = new AbstractFlyingMonsterEntity.FlyingMonsterMoveControl(this);
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
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source)
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
			if (!this.level.isClientSide && !this.isNoAi() && source.getEntity() instanceof LivingEntity && this.isCharging() && this.getRandom().nextInt(3) == 0)
			{
				this.setAttackPhase(1);
			}

			return super.hurt(source, amount);
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		compound.putInt("AttackPhase", this.getAttackPhase());

		if (compound.contains("BoundX"))
		{
			this.boundOrigin = new BlockPos(compound.getInt("BoundX"), compound.getInt("BoundY"), compound.getInt("BoundZ"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
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
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Nullable
	public BlockPos getBoundOrigin()
	{
		return this.boundOrigin;
	}

	public void setBoundOrigin(@Nullable BlockPos boundOriginPos)
	{
		this.boundOrigin = boundOriginPos;
	}

	public int getAttackPhase()
	{
		return this.entityData.get(ATTACK_PHASE);
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

		public ChargeAttackGoal(double moveSpeed, float maxAttackDistance)
		{
			this(moveSpeed, maxAttackDistance, 4);
		}

		public ChargeAttackGoal(double moveSpeed, float maxAttackDistance, int chance)
		{
			this.moveSpeed = moveSpeed;
			this.attackRadius = maxAttackDistance;
			this.chance = chance;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse()
		{
			if (AbstractFlyingMonsterEntity.this.getTarget() != null && !AbstractFlyingMonsterEntity.this.getMoveControl().hasWanted() && AbstractFlyingMonsterEntity.this.getRandom().nextInt(this.chance) == 0)
			{
				return AbstractFlyingMonsterEntity.this.distanceToSqr(AbstractFlyingMonsterEntity.this.getTarget()) > this.attackRadius;
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

			if (AbstractFlyingMonsterEntity.this.hasLineOfSight(livingentity) || AbstractFlyingMonsterEntity.this.getAttackPhase() != 0)
			{
				Vec3 vec3 = livingentity.getEyePosition();
				AbstractFlyingMonsterEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y - 0.75D, vec3.z, this.moveSpeed);
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
				attacker.swing(InteractionHand.MAIN_HAND);
				attacker.doHurtTarget(livingentity);
				attacker.setAttackPhase(1);
			}
			else
			{
				if (attacker.hasLineOfSight(livingentity))
				{
					if (d0 < this.attackRadius + 15.0F)
					{
						Vec3 vec3 = livingentity.getEyePosition();
						attacker.moveControl.setWantedPosition(vec3.x, vec3.y - 0.75D, vec3.z, this.moveSpeed);
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
			return AbstractFlyingMonsterEntity.this.getBbWidth() * 2.0F * AbstractFlyingMonsterEntity.this.getBbWidth() * 2.0F + attackTarget.getBbWidth();
		}
	}

	protected class FlyingMonsterMoveControl extends MoveControl
	{
		public FlyingMonsterMoveControl(AbstractFlyingMonsterEntity mob)
		{
			super(mob);
		}

		@Override
		public void tick()
		{
			AbstractFlyingMonsterEntity flyingentity = AbstractFlyingMonsterEntity.this;

			if (this.operation == MoveControl.Operation.MOVE_TO)
			{
	            Vec3 vec3 = new Vec3(this.wantedX - flyingentity.getX(), this.wantedY - flyingentity.getY(), this.wantedZ - flyingentity.getZ());
	            double d0 = vec3.length();

				if (d0 < flyingentity.getBoundingBox().getSize() || !ModUtils.canReach(flyingentity, vec3.normalize(), Mth.ceil(d0)))
				{
					this.operation = MoveControl.Operation.WAIT;
					flyingentity.setDeltaMovement(flyingentity.getDeltaMovement().scale(0.5D));
				}
				else
				{
					float f = (float)flyingentity.getAttributeValue(Attributes.MOVEMENT_SPEED);
					flyingentity.setDeltaMovement(flyingentity.getDeltaMovement().add(vec3.scale((float)this.speedModifier * f * 0.2D / d0)));

					if (flyingentity.getTarget() == null)
					{
						Vec3 vec31 = flyingentity.getDeltaMovement();
						flyingentity.setYRot(-((float)Mth.atan2(vec31.x, vec31.z)) * (180.0F / (float)Math.PI));
						flyingentity.yBodyRot = flyingentity.getYRot();
					}
					else
					{
						double d2 = flyingentity.getTarget().getX() - flyingentity.getX();
						double d1 = flyingentity.getTarget().getZ() - flyingentity.getZ();
						flyingentity.setYRot(-((float)Mth.atan2(d2, d1)) * (180.0F / (float)Math.PI));
						flyingentity.yBodyRot = flyingentity.getYRot();
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

		public MoveRandomGoal(double moveSpeed)
		{
			this(moveSpeed, 6, 3, 2);
		}

		public MoveRandomGoal(double moveSpeed, int chance, int width, int height)
		{
			this.moveSpeed = moveSpeed;
			this.chance = chance;
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
					flyingentity.moveControl.setWantedPosition(blockpos1.getX() + 0.5D, blockpos1.getY() + 0.5D, blockpos1.getZ() + 0.5D, this.moveSpeed);

					if (flyingentity.getTarget() == null)
					{
						flyingentity.getLookControl().setLookAt(blockpos1.getX() + 0.5D, blockpos1.getY() + 0.5D, blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
					}

					break;
				}
			}
		}
	}
}