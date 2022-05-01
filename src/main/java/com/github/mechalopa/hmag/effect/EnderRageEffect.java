package com.github.mechalopa.hmag.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class EnderRageEffect extends Effect
{
	public EnderRageEffect(EffectType type, int color)
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