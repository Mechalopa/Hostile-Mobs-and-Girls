package com.github.mechalopa.hmag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.mechalopa.hmag.registry.ModBiomeModifiers;
import com.github.mechalopa.hmag.registry.ModBlocks;
import com.github.mechalopa.hmag.registry.ModEffects;
import com.github.mechalopa.hmag.registry.ModEnchantments;
import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.registry.ModItems;
import com.github.mechalopa.hmag.registry.ModLootModifiers;
import com.github.mechalopa.hmag.registry.ModParticleTypes;
import com.github.mechalopa.hmag.registry.ModPotions;
import com.github.mechalopa.hmag.registry.ModRecipes;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.registry.ModStructureModifiers;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HMaG.MODID)
@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HMaG
{
	public static final String MODID = "hmag";

	public static final Logger LOGGER = LogManager.getLogger();

	public HMaG()
	{
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.SERVER_CONFIG);

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(ModConfigs::loadConfig);

		ModBlocks.register(modEventBus);
		ModEffects.register(modEventBus);
		ModEnchantments.register(modEventBus);
		ModEntityTypes.register(modEventBus);
		ModItems.register(modEventBus);
		ModLootModifiers.register(modEventBus);
		ModParticleTypes.register(modEventBus);
		ModPotions.register(modEventBus);
		ModRecipes.register(modEventBus);
		ModSoundEvents.register(modEventBus);
		ModBiomeModifiers.register(modEventBus);
		ModStructureModifiers.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(new ModEvents());
	}
}