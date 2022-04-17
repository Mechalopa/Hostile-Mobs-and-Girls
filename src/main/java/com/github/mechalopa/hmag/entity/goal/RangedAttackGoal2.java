package com.github.mechalopa.hmag.entity.goal;

import java.util.EnumSet;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;

public class RangedAttackGoal2 extends Goal
{
	private final MobEntity mob;
	private final IRangedAttackMob rangedAttackMob;
	private LivingEntity target;
	private int rangedAttackTime = -1;
	private final double speedModifier;
	private int seeTime;
	private final int attackIntervalMin;
	private final int maxRangedAttackTime;
	private final float attackRadius;
	private final float maxAttackDistance;
	private final float minAttackDistance;
	private final boolean canStrafe;
	private boolean strafingClockwise;
	private boolean strafingBackwards;
	private int strafingTime = -1;

	public RangedAttackGoal2(IRangedAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistance)
	{
		this(attacker, movespeed, maxAttackTime, maxAttackTime, maxAttackDistance);
	}

	public RangedAttackGoal2(IRangedAttackMob attacker, double movespeed, int attackInterval, int maxAttackTime, float maxAttackDistance)
	{
		this(attacker, movespeed, attackInterval, maxAttackTime, maxAttackDistance, maxAttackDistance, false);
	}

	public RangedAttackGoal2(IRangedAttackMob attacker, double movespeed, int attackInterval, int maxAttackTime, float maxAttackDistance, boolean canStrafe)
	{
		this(attacker, movespeed, attackInterval, maxAttackTime, maxAttackDistance, maxAttackDistance, canStrafe);
	}

	public RangedAttackGoal2(IRangedAttackMob attacker, double movespeed, int attackInterval, int maxAttackTime, float maxAttackDistance, float minAttackDistance, boolean canStrafe)
	{
		if (!(attacker instanceof LivingEntity))
		{
			throw new IllegalArgumentException("RangedAttackGoal2 requires Mob implements RangedAttackMob");
		}
		else
		{
			this.rangedAttackMob = attacker;
			this.mob = (MobEntity)attacker;
			this.speedModifier = movespeed;
			this.attackIntervalMin = attackInterval;
			this.maxRangedAttackTime = maxAttackTime;
			this.attackRadius = maxAttackDistance;
			this.maxAttackDistance = maxAttackDistance * maxAttackDistance;
			this.minAttackDistance = minAttackDistance * minAttackDistance;
			this.canStrafe = canStrafe;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}
	}

	@Override
	public boolean canUse()
	{
		LivingEntity livingentity = this.mob.getTarget();

		if (livingentity != null && livingentity.isAlive())
		{
			this.target = livingentity;
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean canContinueToUse()
	{
		return this.canUse() || !this.mob.getNavigation().isDone();
	}

	@Override
	public void stop()
	{
		this.target = null;
		this.seeTime = 0;
		this.rangedAttackTime = -1;
	}

	@Override
	public void tick()
	{
		double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
		boolean flag = this.mob.getSensing().canSee(this.target);

		if (flag)
		{
			++this.seeTime;
		}
		else
		{
			this.seeTime = 0;
		}

		if (!(d0 > (double)this.maxAttackDistance || (d0 > (double)this.minAttackDistance && this.rangedAttackTime > 10)) && this.seeTime >= (this.canStrafe ? 20 : 5))
		{
			this.mob.getNavigation().stop();

			if (this.canStrafe)
			{
				++this.strafingTime;
			}
		}
		else
		{
			this.mob.getNavigation().moveTo(this.target, this.speedModifier);

			if (this.canStrafe)
			{
				this.strafingTime = -1;
			}
		}

		if (this.canStrafe && this.strafingTime >= 20)
		{
			if ((double)this.mob.getRandom().nextFloat() < 0.3D)
			{
				this.strafingClockwise = !this.strafingClockwise;
			}

			if ((double)this.mob.getRandom().nextFloat() < 0.3D)
			{
				this.strafingBackwards = !this.strafingBackwards;
			}

			this.strafingTime = 0;
		}

		if (this.canStrafe && this.strafingTime > -1)
		{
			if (d0 > (double)(this.maxAttackDistance * 0.75F))
			{
				this.strafingBackwards = false;
			}
			else if (d0 < (double)(this.maxAttackDistance * 0.25F))
			{
				this.strafingBackwards = true;
			}

			this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
			this.mob.lookAt(this.target, 30.0F, 30.0F);
		}
		else
		{
			this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
		}

		if (--this.rangedAttackTime == 0)
		{
			if (!flag)
			{
				return;
			}

			float f = MathHelper.sqrt(d0) / this.attackRadius;
			float f1 = MathHelper.clamp(f, 0.1F, 1.0F);
			this.rangedAttackMob.performRangedAttack(this.target, f1);
			this.rangedAttackTime = MathHelper.floor(f * (float)(this.maxRangedAttackTime - this.attackIntervalMin) + (float)this.attackIntervalMin);
		}
		else if (this.rangedAttackTime < 0)
		{
			float f2 = MathHelper.sqrt(d0) / this.attackRadius;
			this.rangedAttackTime = MathHelper.floor(f2 * (float)(this.maxRangedAttackTime - this.attackIntervalMin) + (float)this.attackIntervalMin);
		}
	}

	public int getAttackTime()
	{
		return this.rangedAttackTime;
	}

	public LivingEntity getTarget()
	{
		return this.target;
	}
}