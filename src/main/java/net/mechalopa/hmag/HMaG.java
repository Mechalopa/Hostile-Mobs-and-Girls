package net.mechalopa.hmag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.mechalopa.hmag.registry.ModBlocks;
import net.mechalopa.hmag.registry.ModEffects;
import net.mechalopa.hmag.registry.ModEnchantments;
import net.mechalopa.hmag.registry.ModEntityTypes;
import net.mechalopa.hmag.registry.ModItems;
import net.mechalopa.hmag.registry.ModLootModifiers;
import net.mechalopa.hmag.registry.ModParticleTypes;
import net.mechalopa.hmag.registry.ModPotions;
import net.mechalopa.hmag.registry.ModRecipes;
import net.mechalopa.hmag.registry.ModSoundEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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

	public static final ItemGroup MODTAB = new ItemGroup(HMaG.MODID + ".tab")
	{
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon()
		{
			return new ItemStack(ModItems.EVIL_CRYSTAL.get());
		}
	};

	public HMaG()
	{
//		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfigs.CLIENT_CONFIG);
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

		MinecraftForge.EVENT_BUS.register(new ModEvents());
	}
}