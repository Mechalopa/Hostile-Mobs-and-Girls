package com.github.mechalopa.hmag.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.github.mechalopa.hmag.world.entity.KashaEntity;
import com.github.mechalopa.hmag.world.entity.MeltyMonsterEntity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

@Mixin(Snowball.class)
public abstract class SnowballMixin extends Projectile
{
	protected SnowballMixin(EntityType<? extends Projectile> type, Level level)
	{
		super(type, level);
	}

	@Inject(method = "onHitEntity", at = @At(value = "INVOKE", target="Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", shift = At.Shift.BEFORE), cancellable = true, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	public void hmag_onHitEntity(EntityHitResult entityHitResult, CallbackInfo info, Entity entity, int i)
	{
		if (entity != null && (entity instanceof MeltyMonsterEntity || entity instanceof KashaEntity))
		{
			entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float)(i + 3));
			info.cancel();
		}
	}
}