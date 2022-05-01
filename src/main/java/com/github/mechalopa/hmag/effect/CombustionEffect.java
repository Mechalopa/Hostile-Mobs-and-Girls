package com.github.mechalopa.hmag.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class CombustionEffect extends MobEffect
{
	public CombustionEffect(MobEffectCategory type, int color)
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