package com.github.mechalopa.hmag.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.mechalopa.hmag.registry.ModEntityTypes;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;

@Mixin(IronGolem.class)
public abstract class IronGolemMixin
{
	@Inject(method = "canAttackType", at = @At(value = "HEAD"), cancellable = true)
	public void hmag_canAttackType(EntityType<?> type, CallbackInfoReturnable<Boolean> cir)
	{
		if (type == ModEntityTypes.CREEPER_GIRL.get())
		{
			cir.setReturnValue(false);
		}
	}
}