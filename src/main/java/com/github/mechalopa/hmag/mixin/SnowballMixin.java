package com.github.mechalopa.hmag.mixin;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.phys.EntityHitResult;

@Mixin(Snowball.class)
public abstract class SnowballMixin
{
	@Nullable
	private Entity hitEntity;

	@Inject(method = "Lnet/minecraft/world/entity/projectile/Snowball;onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V", at = @At(value = "INVOKE", target="Lnet/minecraft/world/phys/EntityHitResult;getEntity()Lnet/minecraft/world/entity/Entity;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	public void hmag_onHitEntity(EntityHitResult entityHitResult, CallbackInfo info)
	{
		this.hitEntity = entityHitResult != null ? entityHitResult.getEntity() : null;
	}

	@ModifyVariable(method = "Lnet/minecraft/world/entity/projectile/Snowball;onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V", at = @At(value = "STORE"), ordinal = 0)
	public int modifyDamage(int i)
	{
		return this.hitEntity != null && this.hitEntity.getType().is(ModTags.EntityTypeTags.SNOWBALL_HURTS_TYPES) ? i + 3 : i;
	}
}