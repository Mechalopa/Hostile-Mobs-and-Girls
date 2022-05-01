package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.effect.CombustionEffect;
import com.github.mechalopa.hmag.effect.EnderRageEffect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects
{
	private static final DeferredRegister<Effect> REGISTRY = DeferredRegister.create(ForgeRegistries.POTIONS, HMaG.MODID);

	public static final RegistryObject<Effect> ENDER_RAGE = REGISTRY.register("ender_rage", () -> new EnderRageEffect(EffectType.HARMFUL, 0xC55BD3));
	public static final RegistryObject<Effect> COMBUSTION = REGISTRY.register("combustion", () -> new CombustionEffect(EffectType.HARMFUL, 0xE66410));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}