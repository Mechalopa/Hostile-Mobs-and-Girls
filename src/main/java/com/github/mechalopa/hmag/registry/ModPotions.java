package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPotions
{
	private static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(ForgeRegistries.POTION_TYPES, HMaG.MODID);

	public static final RegistryObject<Potion> KOBOLD = REGISTRY.register("hmag.kobold", () -> new Potion(new EffectInstance(Effects.DIG_SPEED, 180 * 20, 0), new EffectInstance(Effects.WEAKNESS, 180 * 20, 0)));
	public static final RegistryObject<Potion> KOBOLD_STRONG = REGISTRY.register("hmag.kobold_strong", () -> new Potion(new EffectInstance(Effects.DIG_SPEED, 90 * 20, 1), new EffectInstance(Effects.WEAKNESS, 90 * 20, 1)));
	public static final RegistryObject<Potion> HEALING_III = REGISTRY.register("hmag.healing_3", () -> new Potion(new EffectInstance(Effects.HEAL, 1, 2)));
	public static final RegistryObject<Potion> HARMING_III = REGISTRY.register("hmag.harming_3", () -> new Potion(new EffectInstance(Effects.HARM, 1, 2)));
	public static final RegistryObject<Potion> OGRE = REGISTRY.register("hmag.ogre", () -> new Potion(new EffectInstance(Effects.DAMAGE_BOOST, 120 * 20, 1), new EffectInstance(Effects.DIG_SPEED, 120 * 20, 0), new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 120 * 20, 1)));
	public static final RegistryObject<Potion> VORACITY = REGISTRY.register("hmag.voracity", () -> new Potion(new EffectInstance(Effects.DAMAGE_BOOST, 30 * 20, 2), new EffectInstance(Effects.HUNGER, 120 * 20, 2)));
	public static final RegistryObject<Potion> ENDER_RAGE = REGISTRY.register("hmag.ender_rage", () -> new Potion(new EffectInstance(ModEffects.ENDER_RAGE.get(), 90 * 20, 0)));
	public static final RegistryObject<Potion> ENDER_RAGE_LONG = REGISTRY.register("hmag.ender_rage_long", () -> new Potion(new EffectInstance(ModEffects.ENDER_RAGE.get(), 180 * 20, 0)));
	public static final RegistryObject<Potion> ENDER_RAGE_STRONG = REGISTRY.register("hmag.ender_rage_strong", () -> new Potion(new EffectInstance(ModEffects.ENDER_RAGE.get(), 45 * 20, 2)));
	public static final RegistryObject<Potion> COMBUSTION = REGISTRY.register("hmag.combustion", () -> new Potion(new EffectInstance(ModEffects.COMBUSTION.get(), 45 * 20, 0)));
	public static final RegistryObject<Potion> COMBUSTION_LONG = REGISTRY.register("hmag.combustion_long", () -> new Potion(new EffectInstance(ModEffects.COMBUSTION.get(), 90 * 20, 0)));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}