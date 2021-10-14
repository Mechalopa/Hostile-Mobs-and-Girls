package hmag.entity.goal;

import java.util.EnumSet;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.world.GameRules;

public class HurtByTargetGoal2 extends TargetGoal
{
	private static final EntityPredicate HURT_BY_TARGETING = (new EntityPredicate()).allowUnseeable().ignoreInvisibilityTesting();
	private int timestamp;
	private final Class<?>[] toIgnoreDamage;

	public HurtByTargetGoal2(MobEntity mobEntityIn, Class<?>... toIgnoreDamageIn)
	{
		super(mobEntityIn, true);
		this.toIgnoreDamage = toIgnoreDamageIn;
		this.setFlags(EnumSet.of(Goal.Flag.TARGET));
	}

	@Override
	public boolean canUse()
	{
		int i = this.mob.getLastHurtByMobTimestamp();
		LivingEntity livingentity = this.mob.getLastHurtByMob();

		if (i != this.timestamp && livingentity != null)
		{
			if (livingentity.getType() == EntityType.PLAYER && this.mob.level.getGameRules().getBoolean(GameRules.RULE_UNIVERSAL_ANGER))
			{
				return false;
			}
			else
			{
				for (Class<?> oclass : this.toIgnoreDamage)
				{
					if (oclass.isAssignableFrom(livingentity.getClass()))
					{
						return false;
					}
				}

				return this.canAttack(livingentity, HURT_BY_TARGETING);
			}
		}
		else
		{
			return false;
		}
	}

	@Override
	public void start()
	{
		this.mob.setTarget(this.mob.getLastHurtByMob());
		this.targetMob = this.mob.getTarget();
		this.timestamp = this.mob.getLastHurtByMobTimestamp();
		this.unseenMemoryTicks = 300;

		super.start();
	}
}