package com.github.mechalopa.hmag.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.github.mechalopa.hmag.world.entity.EnderExecutorEntity;

import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.EntityHitResult;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin
{
	private boolean hitEnder = false;

	@Inject(method = "Lnet/minecraft/world/entity/projectile/AbstractArrow;onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V", at = @At(value = "INVOKE", target="Lnet/minecraft/world/phys/EntityHitResult;getEntity()Lnet/minecraft/world/entity/Entity;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	public void hmag_onHitEntity(EntityHitResult entityHitResult, CallbackInfo info)
	{
		this.hitEnder = entityHitResult != null && entityHitResult.getEntity() != null && entityHitResult.getEntity() instanceof EnderExecutorEntity;
	}

	@ModifyVariable(method = "Lnet/minecraft/world/entity/projectile/AbstractArrow;onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V", at = @At(value = "STORE"), ordinal = 0)
	public boolean modifyFlag(boolean flag)
	{
		return flag || this.hitEnder;
	}
}