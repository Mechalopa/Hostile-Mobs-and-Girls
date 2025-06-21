package com.github.mechalopa.hmag.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.google.common.collect.ImmutableMap;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.sensing.VillagerHostilesSensor;

@Mixin(VillagerHostilesSensor.class)
public abstract class VillagerHostilesSensorMixin
{
	private static final ImmutableMap<EntityType<?>, Float> MOD_ACCEPTABLE_DISTANCE_FROM_HOSTILES = ImmutableMap.<EntityType<?>, Float>builder().put(ModEntityTypes.ALRAUNE.get(), 10.0F).put(ModEntityTypes.CRIMSON_SLAUGHTERER.get(), 10.0F).put(ModEntityTypes.DROWNED_GIRL.get(), 8.0F).put(ModEntityTypes.GIANT_MUMMY.get(), 8.0F).put(ModEntityTypes.HUSK_GIRL.get(), 8.0F).put(ModEntityTypes.JIANGSHI.get(), 8.0F).put(ModEntityTypes.NECROTIC_REAPER.get(), 8.0F).put(ModEntityTypes.OGRE.get(), 10.0F).put(ModEntityTypes.REDCAP.get(), 8.0F).put(ModEntityTypes.ZOMBIE_GIRL.get(), 8.0F).build();

	@Inject(method = "Lnet/minecraft/world/entity/ai/sensing/VillagerHostilesSensor;isClose(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;)Z", at = @At(value = "HEAD"), cancellable = true)
	public void hmag_isClose(LivingEntity livingentity, LivingEntity livingentity1, CallbackInfoReturnable<Boolean> cir)
	{
		if (livingentity != null && livingentity1 != null && MOD_ACCEPTABLE_DISTANCE_FROM_HOSTILES.containsKey(livingentity1.getType()))
		{
			float f = MOD_ACCEPTABLE_DISTANCE_FROM_HOSTILES.get(livingentity1.getType());
			boolean flag = livingentity1.distanceToSqr(livingentity) <= (double)(f * f);
			cir.setReturnValue(flag);
		}
	}

	@Inject(method = "Lnet/minecraft/world/entity/ai/sensing/VillagerHostilesSensor;isHostile(Lnet/minecraft/world/entity/LivingEntity;)Z", at = @At(value = "HEAD"), cancellable = true)
	public void hmag_isHostile(LivingEntity livingentity, CallbackInfoReturnable<Boolean> cir)
	{
		if (livingentity != null)
		{
			if (MOD_ACCEPTABLE_DISTANCE_FROM_HOSTILES.containsKey(livingentity.getType()))
			{
				cir.setReturnValue(true);
			}
		}
	}
}