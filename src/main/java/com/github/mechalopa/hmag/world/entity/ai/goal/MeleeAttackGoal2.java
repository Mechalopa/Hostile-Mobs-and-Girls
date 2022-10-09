package com.github.mechalopa.hmag.world.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class MeleeAttackGoal2 extends MeleeAttackGoal
{
	private final float reachScale;
	private final float maxAttackDistance;
	private boolean useRaiseArm;
	private int raiseArmTicks;

	public MeleeAttackGoal2(PathfinderMob mob, double speedIn, boolean useLongMemory)
	{
		this(mob, speedIn, useLongMemory, -1.0F);
	}

	public MeleeAttackGoal2(PathfinderMob mob, double speedIn, boolean useLongMemory, float reachScaleIn)
	{
		this(mob, speedIn, useLongMemory, reachScaleIn, -1.0F);
	}

	public MeleeAttackGoal2(PathfinderMob mob, double speedIn, boolean useLongMemory, float reachScaleIn, float maxAttackDistance)
	{
		super(mob, speedIn, useLongMemory);
		this.reachScale = reachScaleIn;
		this.maxAttackDistance = maxAttackDistance;
	}

	public MeleeAttackGoal2 useRaiseArm()
	{
		this.useRaiseArm = true;
		return this;
	}

	@Override
	public boolean canUse()
	{
		return super.canUse() && this.isValidDistance(this.mob.getTarget());
	}

	@Override
	public boolean canContinueToUse()
	{
		return super.canContinueToUse() && this.isValidDistance(this.mob.getTarget());
	}

	@Override
	public void start()
	{
		super.start();

		if (this.useRaiseArm)
		{
			this.raiseArmTicks = 0;
		}
	}

	@Override
	public void stop()
	{
		super.stop();

		if (this.useRaiseArm)
		{
			this.mob.setAggressive(false);
		}
	}

	@Override
	public void tick()
	{
		super.tick();

		if (this.useRaiseArm)
		{
			++this.raiseArmTicks;

			if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2)
			{
				this.mob.setAggressive(true);
			}
			else
			{
				this.mob.setAggressive(false);
			}
		}
	}

	@Override
	protected double getAttackReachSqr(LivingEntity attackTarget)
	{
		if (this.reachScale > 0.0F)
		{
			final float f = this.mob.getBbWidth() * this.reachScale * 2.0F;
			return f * f + attackTarget.getBbWidth();
		}
		else
		{
			return super.getAttackReachSqr(attackTarget);
		}
	}

	protected boolean isValidDistance(LivingEntity attackTarget)
	{
		return this.maxAttackDistance <= 0.0F || ((double)this.maxAttackDistance * (double)this.maxAttackDistance) >= this.mob.distanceToSqr(attackTarget.getX(), attackTarget.getY(), attackTarget.getZ());
	}
}