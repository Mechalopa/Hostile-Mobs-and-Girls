package net.mechalopa.hmag.client;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.particle.EnchantmentRuneParticle;
import net.mechalopa.hmag.client.renderer.ArurauneRenderer;
import net.mechalopa.hmag.client.renderer.BansheeRenderer;
import net.mechalopa.hmag.client.renderer.CatoblepasRenderer;
import net.mechalopa.hmag.client.renderer.CreeperGirlRenderer;
import net.mechalopa.hmag.client.renderer.CrimsonSlaughtererRenderer;
import net.mechalopa.hmag.client.renderer.CursedDollRenderer;
import net.mechalopa.hmag.client.renderer.DodomekiRenderer;
import net.mechalopa.hmag.client.renderer.DoguRenderer;
import net.mechalopa.hmag.client.renderer.DrownedGirlRenderer;
import net.mechalopa.hmag.client.renderer.DullahanRenderer;
import net.mechalopa.hmag.client.renderer.DyssomniaRenderer;
import net.mechalopa.hmag.client.renderer.EnderExecutorRenderer;
import net.mechalopa.hmag.client.renderer.FortressKeeperRenderer;
import net.mechalopa.hmag.client.renderer.GhastlySeekerRenderer;
import net.mechalopa.hmag.client.renderer.GhostRenderer;
import net.mechalopa.hmag.client.renderer.HarpyRenderer;
import net.mechalopa.hmag.client.renderer.HornetRenderer;
import net.mechalopa.hmag.client.renderer.HuskGirlRenderer;
import net.mechalopa.hmag.client.renderer.ImpRenderer;
import net.mechalopa.hmag.client.renderer.JackFrostRenderer;
import net.mechalopa.hmag.client.renderer.KashaRenderer;
import net.mechalopa.hmag.client.renderer.KoboldRenderer;
import net.mechalopa.hmag.client.renderer.LichRenderer;
import net.mechalopa.hmag.client.renderer.MagicBulletRenderer;
import net.mechalopa.hmag.client.renderer.MagicalSlimeRenderer;
import net.mechalopa.hmag.client.renderer.MagmaBulletRenderer;
import net.mechalopa.hmag.client.renderer.MeltyMonsterRenderer;
import net.mechalopa.hmag.client.renderer.ModSpriteRenderer;
import net.mechalopa.hmag.client.renderer.MonolithRenderer;
import net.mechalopa.hmag.client.renderer.NecroticReaperRenderer;
import net.mechalopa.hmag.client.renderer.NemesisBulletRenderer;
import net.mechalopa.hmag.client.renderer.OgreRenderer;
import net.mechalopa.hmag.client.renderer.RedcapRenderer;
import net.mechalopa.hmag.client.renderer.SavagefangRenderer;
import net.mechalopa.hmag.client.renderer.ScorpionRenderer;
import net.mechalopa.hmag.client.renderer.SkeletonGirlRenderer;
import net.mechalopa.hmag.client.renderer.SlimeGirlRenderer;
import net.mechalopa.hmag.client.renderer.SnowCanineRenderer;
import net.mechalopa.hmag.client.renderer.SpiderNestRenderer;
import net.mechalopa.hmag.client.renderer.StoneularRenderer;
import net.mechalopa.hmag.client.renderer.StrayGirlRenderer;
import net.mechalopa.hmag.client.renderer.WitherGhostRenderer;
import net.mechalopa.hmag.client.renderer.WitherSkeletonGirlRenderer;
import net.mechalopa.hmag.client.renderer.ZombieGirlRenderer;
import net.mechalopa.hmag.client.util.ModClientUtils;
import net.mechalopa.hmag.registry.ModBlocks;
import net.mechalopa.hmag.registry.ModEntityTypes;
import net.mechalopa.hmag.registry.ModItems;
import net.mechalopa.hmag.registry.ModParticleTypes;
import net.mechalopa.hmag.util.ModUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ModClientEventBusSubscriber
{
	@SubscribeEvent
	public static void setupClient(final FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ZOMBIE_GIRL.get(), ZombieGirlRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HUSK_GIRL.get(), HuskGirlRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DROWNED_GIRL.get(), DrownedGirlRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SKELETON_GIRL.get(), SkeletonGirlRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WITHER_SKELETON_GIRL.get(), WitherSkeletonGirlRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.STRAY_GIRL.get(), StrayGirlRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CREEPER_GIRL.get(), CreeperGirlRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GHOST.get(), GhostRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WITHER_GHOST.get(), WitherGhostRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ENDER_EXECUTOR.get(), EnderExecutorRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.KOBOLD.get(), KoboldRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LICH.get(), LichRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.OGRE.get(), OgreRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SPIDER_NEST.get(), SpiderNestRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MELTY_MONSTER.get(), MeltyMonsterRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CURSED_DOLL.get(), CursedDollRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.JACK_FROST.get(), JackFrostRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HORNET.get(), HornetRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DULLAHAN.get(), DullahanRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BANSHEE.get(), BansheeRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ARURAUNE.get(), ArurauneRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CATOBLEPAS.get(), CatoblepasRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SCORPION.get(), ScorpionRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.KASHA.get(), KashaRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DOGU.get(), DoguRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GHSATLY_SHEEKER.get(), GhastlySeekerRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.REDCAP.get(), RedcapRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SLIME_GIRL.get(), SlimeGirlRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MAGICAL_SLIME.get(), MagicalSlimeRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MONOLITH.get(), MonolithRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CRIMSON_SLAUGHTERER.get(), CrimsonSlaughtererRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DYSSOMNIA.get(), DyssomniaRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SNOW_CANINE.get(), SnowCanineRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.STONEULAR.get(), StoneularRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HARPY.get(), HarpyRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SAVAGEFANG.get(), SavagefangRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FORTRESS_KEEPER.get(), FortressKeeperRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.NECROTIC_REAPER.get(), NecroticReaperRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DODOMEKI.get(), DodomekiRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.IMP.get(), ImpRenderer::new);

		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MAGIC_BULLET.get(), MagicBulletRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HARD_SNOWBALL.get(), ModSpriteRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.POISON_SEED.get(), ModSpriteRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.THROWABLE_BOTTLE.get(), ModSpriteRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MAGMA_BULLET.get(), MagmaBulletRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.NEMESIS_BULLET.get(), NemesisBulletRenderer::new);

		RenderTypeLookup.setRenderLayer(ModBlocks.REINFORCED_GLASS.get(), RenderType.cutout());

		event.enqueueWork(() -> {
			ItemModelsProperties.register(ModItems.INSOMNIA_SWORD.get(), new ResourceLocation("level"), (stack, world, livingentity) -> {
				final int i = ModUtils.getLevel(stack);
				return i >= 5 ? 2.0F : (i > 0 ? 1.0F : 0.0F);
			});
			ItemModelsProperties.register(ModItems.NEMESIS_BLADE.get(), new ResourceLocation("level"), (stack, world, livingentity) -> {
				final int i = ModUtils.getLevel(stack);
				return i >= 6 ? 4.0F : (i >= 5 ? 3.0F : (i >= 3 ? 2.0F : (i > 0 ? 1.0F : 0.0F)));
			});
			ItemModelsProperties.register(ModItems.CRIMSON_BOW.get(), new ResourceLocation("pull"), ModClientUtils.PROPERTY_BOW_PULL);
			ItemModelsProperties.register(ModItems.CRIMSON_BOW.get(), new ResourceLocation("pulling"), ModClientUtils.PROPERTY_BOW_PULLING);
			ItemModelsProperties.register(ModItems.ANCIENT_SHIELD.get(), new ResourceLocation("blocking"), ModClientUtils.PROPERTY_SHIELD_BLOCKING);
			ItemModelsProperties.register(ModItems.FORTRESS_SHIELD.get(), new ResourceLocation("blocking"), ModClientUtils.PROPERTY_SHIELD_BLOCKING);
		});

		MinecraftForge.EVENT_BUS.register(new ModClientEvents());
	}

	@SuppressWarnings("resource")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticleFactories(final ParticleFactoryRegisterEvent event)
	{
		Minecraft.getInstance().particleEngine.register(ModParticleTypes.ENCHANTMENT_RUNE.get(), EnchantmentRuneParticle.Factory::new);
		Minecraft.getInstance().particleEngine.register(ModParticleTypes.NEMESIS_FLAME.get(), FlameParticle.Factory::new);
	}
}