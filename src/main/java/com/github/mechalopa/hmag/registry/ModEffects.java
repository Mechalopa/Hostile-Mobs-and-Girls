package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.effect.CombustionEffect;
import com.github.mechalopa.hmag.effect.EnderRageEffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects
{
	private static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, HMaG.MODID);

	public static final RegistryObject<MobEffect> ENDER_RAGE = REGISTRY.register("ender_rage", () -> new EnderRageEffect(EffectType.HARMFUL, 0xC55BD3));
	public static final RegistryObject<MobEffect> COMBUSTION = REGISTRY.register("combustion", () -> new CombustionEffect(EffectType.HARMFUL, 0xE66410));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}