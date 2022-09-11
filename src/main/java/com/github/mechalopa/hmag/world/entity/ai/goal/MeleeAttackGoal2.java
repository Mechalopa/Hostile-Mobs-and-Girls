package com.github.mechalopa.hmag.world.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class MeleeAttackGoal2 extends MeleeAttackGoal
{
	private final float reachScale;
	private final float maxAttackDistance;

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