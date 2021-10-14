package hmag.entity.goal;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class MeleeAttackGoal2 extends MeleeAttackGoal
{
	private final float reach;
	private final float maxAttackDistance;

	public MeleeAttackGoal2(CreatureEntity creature, double speedIn, boolean useLongMemory, float reachIn)
	{
		this(creature, speedIn, useLongMemory, reachIn, -1.0F);
	}

	public MeleeAttackGoal2(CreatureEntity creature, double speedIn, boolean useLongMemory, float reachIn, float maxAttackDistance)
	{
		super(creature, speedIn, useLongMemory);
		this.reach = reachIn;
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
		return (double)(this.reach * this.reach + attackTarget.getBbWidth());
	}

	protected boolean isValidDistance(LivingEntity attackTarget)
	{
		return this.maxAttackDistance <= 0.0F || ((double)this.maxAttackDistance * (double)this.maxAttackDistance) >= this.mob.distanceToSqr(attackTarget.getX(), attackTarget.getY(), attackTarget.getZ());
	}
}