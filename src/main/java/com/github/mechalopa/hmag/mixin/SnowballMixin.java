package com.github.mechalopa.hmag.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.phys.EntityHitResult;

@Mixin(Snowball.class)
public abstract class SnowballMixin
{
	private int additionalDamage = 0;

	@Inject(method = "Lnet/minecraft/world/entity/projectile/Snowball;onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V", at = @At(value = "INVOKE", target="Lnet/minecraft/world/phys/EntityHitResult;getEntity()Lnet/minecraft/world/entity/Entity;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	public void hmag_onHitEntity(EntityHitResult entityHitResult, CallbackInfo info)
	{
		this.additionalDamage = entityHitResult != null && entityHitResult.getEntity() != null && entityHitResult.getEntity().getType().is(ModTags.EntityTypeTags.SNOWBALL_HURTS_TYPES) ? 3 : 0;
	}

	@ModifyVariable(method = "Lnet/minecraft/world/entity/projectile/Snowball;onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V", at = @At(value = "STORE"), ordinal = 0)
	public int modifyDamage(int i)
	{
		return i + this.additionalDamage;
	}
}