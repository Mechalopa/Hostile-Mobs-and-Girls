package com.github.mechalopa.hmag.entity.goal;

import java.util.EnumSet;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.vector.Vector3d;

public class LeapAtTargetGoal2 extends Goal
{
	private final MobEntity mob;
	private LivingEntity target;
	private final float leapMotionY;
	private final float leapMotionWidth;
	private final float maxAttackDistance;
	private final int chance;

	public LeapAtTargetGoal2(MobEntity mobEntityIn, float leapMotionYIn)
	{
		this(mobEntityIn, leapMotionYIn, 0.4F);
	}

	public LeapAtTargetGoal2(MobEntity mobEntityIn, float leapMotionYIn, float leapMotionWidthIn)
	{
		this(mobEntityIn, leapMotionYIn, leapMotionWidthIn, 4.0F, 5);
	}

	public LeapAtTargetGoal2(MobEntity mobEntityIn, float leapMotionYIn, float leapMotionWidthIn, float maxAttackDistanceIn, int chanceIn)
	{
		this.mob = mobEntityIn;
		this.leapMotionY = leapMotionYIn;
		this.leapMotionWidth = leapMotionWidthIn;
		this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
		this.chance = chanceIn;
		this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
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

				if (!(d0 < 4.0D) && !(d0 > (double)this.maxAttackDistance))
				{
					if (!this.mob.isOnGround())
					{
						return false;
					}
					else
					{
						return this.mob.getRandom().nextInt(this.chance) == 0;
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
		Vector3d vector3d = this.mob.getDeltaMovement();
		Vector3d vector3d1 = new Vector3d(this.target.getX() - this.mob.getX(), 0.0D, this.target.getZ() - this.mob.getZ());

		if (vector3d1.lengthSqr() > 1.0E-7D)
		{
			vector3d1 = vector3d1.normalize().scale((double)this.leapMotionWidth).add(vector3d.scale(0.2D));
		}

		this.mob.setDeltaMovement(vector3d1.x, (double)this.leapMotionY, vector3d1.z);
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
}