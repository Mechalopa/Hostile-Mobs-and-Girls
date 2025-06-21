package com.github.mechalopa.hmag.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.mechalopa.hmag.world.entity.CreeperGirlEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zoglin;

@Mixin(Zoglin.class)
public abstract class ZoglinMixin
{
	@Inject(method = "Lnet/minecraft/world/entity/monster/Zoglin;isTargetable(Lnet/minecraft/world/entity/LivingEntity;)Z", at = @At(value = "HEAD"), cancellable = true)
	public void hmag_isTargetable(LivingEntity livingentity, CallbackInfoReturnable<Boolean> cir)
	{
		if (livingentity != null && livingentity instanceof CreeperGirlEntity)
		{
			cir.setReturnValue(false);
		}
	}
}