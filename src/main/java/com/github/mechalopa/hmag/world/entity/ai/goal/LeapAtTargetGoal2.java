package com.github.mechalopa.hmag.world.entity.ai.goal;

import java.util.EnumSet;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class LeapAtTargetGoal2 extends Goal
{
	private final Mob mob;
	private LivingEntity target;
	private final float yd;
	private final float xzd;
	private final float maxAttackDistance;
	private final int chance;

	public LeapAtTargetGoal2(Mob mob, float yd)
	{
		this(mob, yd, 0.4F);
	}

	public LeapAtTargetGoal2(Mob mob, float yd, float xzd)
	{
		this(mob, yd, xzd, 4.0F, 5);
	}

	public LeapAtTargetGoal2(Mob mob, float yd, float xzd, float maxAttackDistance, int chance)
	{
		this.mob = mob;
		this.yd = yd;
		this.xzd = xzd;
		this.maxAttackDistance = maxAttackDistance;
		this.chance = chance;
		this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse()
	{
		if (this.mob.isVehicle())
		{
			return false;
		}
		else
		{
			this.target = this.mob.getTarget();

			if (this.target == null)
			{
				return false;
			}
			else
			{
				double d0 = this.mob.distanceToSqr(this.target);

				if (!(d0 < 4.0D) && !(d0 > this.getMaxAttackDistanceSqr()))
				{
					if (!this.mob.isOnGround())
					{
						return false;
					}
					else
					{
						return this.mob.getRandom().nextInt(reducedTickDelay(this.chance)) == 0;
					}
				}
				else
				{
					return false;
				}
			}
		}
	}

	@Override
	public boolean canContinueToUse()
	{
		return !this.mob.isOnGround();
	}

	@Override
	public void start()
	{
		this.mob.setAggressive(true);
		Vec3 vec3 = this.mob.getDeltaMovement();
		Vec3 vec31 = new Vec3(this.target.getX() - this.mob.getX(), 0.0D, this.target.getZ() - this.mob.getZ());

		if (vec31.lengthSqr() > 1.0E-7D)
		{
			vec31 = vec31.normalize().scale(this.getXZD()).add(vec3.scale(0.2D));
		}

		this.mob.setDeltaMovement(vec31.x, this.getYD(), vec31.z);
	}

	@Override
	public void stop()
	{
		this.mob.setAggressive(false);
	}

	@Override
	public void tick()
	{
		if (this.target != null)
		{
			this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
		}

		super.tick();
	}

	private double getMaxAttackDistanceSqr()
	{
		return (double)this.getMaxAttackDistance() * (double)this.getMaxAttackDistance();
	}

	public float getMaxAttackDistance()
	{
		return this.maxAttackDistance;
	}

	public double getYD()
	{
		return this.yd;
	}

	public double getXZD()
	{
		return this.xzd;
	}
}