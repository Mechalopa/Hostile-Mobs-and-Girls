package com.github.mechalopa.hmag.world.effect;

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
	public void applyEffectTick(LivingEntity livingEntity, int amplifier)
	{
		if (!livingEntity.level().isClientSide() && !livingEntity.fireImmune() && !livingEntity.isInWaterRainOrBubble() && (!livingEntity.isOnFire() || livingEntity.getRemainingFireTicks() <= 1))
		{
			livingEntity.setRemainingFireTicks(livingEntity.getRemainingFireTicks() + 20);
			livingEntity.setSecondsOnFire(1);
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier)
	{
		return duration > 0;
	}
}