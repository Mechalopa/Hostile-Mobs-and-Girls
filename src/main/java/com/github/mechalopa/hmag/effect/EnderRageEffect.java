package com.github.mechalopa.hmag.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class EnderRageEffect extends MobEffect
{
	public EnderRageEffect(MobEffectCategory type, int color)
	{
		super(type, color);
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntityIn, int amplifier){}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier)
	{
		return duration > 0;
	}
}