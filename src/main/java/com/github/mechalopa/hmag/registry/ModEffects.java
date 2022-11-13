package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.world.effect.CombustionEffect;
import com.github.mechalopa.hmag.world.effect.DarknessResistanceEffect;
import com.github.mechalopa.hmag.world.effect.EnderRageEffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects
{
	private static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, HMaG.MODID);

	public static final RegistryObject<MobEffect> ENDER_RAGE = REGISTRY.register("ender_rage", () -> new EnderRageEffect(MobEffectCategory.HARMFUL, 0xC55BD3));
	public static final RegistryObject<MobEffect> COMBUSTION = REGISTRY.register("combustion", () -> new CombustionEffect(MobEffectCategory.HARMFUL, 0xE66410));
	public static final RegistryObject<MobEffect> DARKNESS_RESISTANCE = REGISTRY.register("darkness_resistance", () -> new DarknessResistanceEffect(MobEffectCategory.BENEFICIAL, 0xD5D8E4));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}