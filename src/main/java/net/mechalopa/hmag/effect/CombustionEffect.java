package net.mechalopa.hmag.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class CombustionEffect extends Effect
{
	public CombustionEffect(EffectType type, int color)
	{
		super(type, color);
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntityIn, int amplifier)
	{
		if (!livingEntityIn.level.isClientSide && !livingEntityIn.fireImmune() && !livingEntityIn.isInWaterRainOrBubble() && (!livingEntityIn.isOnFire() || livingEntityIn.getRemainingFireTicks() <= 1))
		{
			livingEntityIn.setRemainingFireTicks(livingEntityIn.getRemainingFireTicks() + 20);
			livingEntityIn.setSecondsOnFire(1);
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier)
	{
		return duration > 0;
	}
}