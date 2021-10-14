package hmag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hmag.registry.ModBlocks;
import hmag.registry.ModEffects;
import hmag.registry.ModEnchantments;
import hmag.registry.ModEntityTypes;
import hmag.registry.ModItems;
import hmag.registry.ModLootModifiers;
import hmag.registry.ModParticleTypes;
import hmag.registry.ModPotions;
import hmag.registry.ModRecipes;
import hmag.registry.ModSoundEvents;
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
//		modEventBus.addGenericListener(IRecipeSerializer.class, this::registerRecipeSerializers);

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
//		MinecraftForge.EVENT_BUS.register(this);
	}

//	private void registerRecipeSerializers(final RegistryEvent.Register<IRecipeSerializer<?>> event)
//	{
//		event.getRegistry().register(ItemTagShapedRecipe.SERIALIZER);
//		event.getRegistry().register(RemoveCurseRecipe.SERIALIZER);
//		event.getRegistry().register(EnchantmentUpgradeRecipe.SERIALIZER);
//	}
}