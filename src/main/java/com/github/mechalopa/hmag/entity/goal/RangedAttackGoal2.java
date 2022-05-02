package com.github.mechalopa.hmag.entity.goal;

import java.util.EnumSet;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class RangedAttackGoal2 extends Goal
{
	private final Mob mob;
	private final RangedAttackMob rangedAttackMob;
	private LivingEntity target;
	private int rangedAttackTime = -1;
	private final double speedModifier;
	private int seeTime;
	private final int attackIntervalMin;
	private final int attackIntervalMax;
	private final float attackRadius;
	private final float maxAttackDistanceSqr;
	private final float minAttackDistanceSqr;
	private final boolean canStrafe;
	private boolean strafingClockwise;
	private boolean strafingBackwards;
	private int strafingTime = -1;

	public RangedAttackGoal2(RangedAttackMob attacker, double movespeed, int attackIntervalMin, float maxAttackDistance)
	{
		this(attacker, movespeed, attackIntervalMin, attackIntervalMin, maxAttackDistance);
	}

	public RangedAttackGoal2(RangedAttackMob attacker, double movespeed, int attackIntervalMin, int attackIntervalMax, float maxAttackDistance)
	{
		this(attacker, movespeed, attackIntervalMin, attackIntervalMax, maxAttackDistance, maxAttackDistance, false);
	}

	public RangedAttackGoal2(RangedAttackMob attacker, double movespeed, int attackIntervalMin, int attackIntervalMax, float maxAttackDistance, boolean canStrafe)
	{
		this(attacker, movespeed, attackIntervalMin, attackIntervalMax, maxAttackDistance, maxAttackDistance, canStrafe);
	}

	public RangedAttackGoal2(RangedAttackMob attacker, double movespeed, int attackIntervalMin, int attackIntervalMax, float maxAttackDistance, float minAttackDistance, boolean canStrafe)
	{
		if (!(attacker instanceof LivingEntity))
		{
			throw new IllegalArgumentException("RangedAttackGoal2 requires Mob implements RangedAttackMob");
		}
		else
		{
			this.rangedAttackMob = attacker;
			this.mob = (Mob)attacker;
			this.speedModifier = movespeed;
			this.attackIntervalMin = attackIntervalMin;
			this.attackIntervalMax = attackIntervalMax;
			this.attackRadius = maxAttackDistance;
			this.maxAttackDistanceSqr = maxAttackDistance * maxAttackDistance;
			this.minAttackDistanceSqr = minAttackDistance * minAttackDistance;
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
	public boolean requiresUpdateEveryTick()
	{
		return true;
	}

	@Override
	public void tick()
	{
		double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
		boolean flag = this.mob.getSensing().hasLineOfSight(this.target);

		if (flag)
		{
			++this.seeTime;
		}
		else
		{
			this.seeTime = 0;
		}

		if (!(d0 > this.maxAttackDistanceSqr || (d0 > this.minAttackDistanceSqr && this.rangedAttackTime > 10)) && this.seeTime >= (this.canStrafe ? 20 : 5))
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
			if (this.mob.getRandom().nextFloat() < 0.3D)
			{
				this.strafingClockwise = !this.strafingClockwise;
			}

			if (this.mob.getRandom().nextFloat() < 0.3D)
			{
				this.strafingBackwards = !this.strafingBackwards;
			}

			this.strafingTime = 0;
		}

		if (this.canStrafe && this.strafingTime > -1)
		{
			if (d0 > this.maxAttackDistanceSqr * 0.75F)
			{
				this.strafingBackwards = false;
			}
			else if (d0 < this.maxAttackDistanceSqr * 0.25F)
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

			float f = (float)Math.sqrt(d0) / this.attackRadius;
			float f1 = Mth.clamp(f, 0.1F, 1.0F);
			this.rangedAttackMob.performRangedAttack(this.target, f1);
			this.rangedAttackTime = Mth.floor(f * (this.attackIntervalMax - this.attackIntervalMin) + this.attackIntervalMin);
		}
		else if (this.rangedAttackTime < 0)
		{
			this.rangedAttackTime = Mth.floor(Mth.lerp(Math.sqrt(d0) / this.attackRadius, this.attackIntervalMin, this.attackIntervalMax));
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