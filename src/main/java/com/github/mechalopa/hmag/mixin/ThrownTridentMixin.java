package com.github.mechalopa.hmag.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.github.mechalopa.hmag.world.entity.EnderExecutorEntity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.phys.EntityHitResult;

@Mixin(ThrownTrident.class)
public abstract class ThrownTridentMixin
{
	@Inject(method = "Lnet/minecraft/world/entity/projectile/ThrownTrident;onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V", at = @At(value = "INVOKE", target="Lnet/minecraft/world/entity/Entity;getType()Lnet/minecraft/world/entity/EntityType;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
	public void hmag_onHitEntity(EntityHitResult entityHitResult, CallbackInfo info, Entity entity, float f, Entity entity1, DamageSource damagesource, SoundEvent soundevent)
	{
		if (entity != null && entity instanceof EnderExecutorEntity)
		{
			info.cancel();
		}
	}
}