package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleTypes
{
	private static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HMaG.MODID);

	public static final RegistryObject<SimpleParticleType> ENCHANTMENT_RUNE = REGISTRY.register("enchantment_rune", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> NEMESIS_FLAME = REGISTRY.register("nemesis_flame", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> BLACK_MYCELIUM = REGISTRY.register("black_mycelium", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> NIGHTWALKER_BULLET = REGISTRY.register("nightwalker_bullet", () -> new SimpleParticleType(false));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}