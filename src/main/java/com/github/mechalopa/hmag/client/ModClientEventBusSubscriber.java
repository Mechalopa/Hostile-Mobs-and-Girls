package com.github.mechalopa.hmag.client;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.ArurauneModel;
import com.github.mechalopa.hmag.client.model.BansheeModel;
import com.github.mechalopa.hmag.client.model.CatoblepasModel;
import com.github.mechalopa.hmag.client.model.CreeperGirlModel;
import com.github.mechalopa.hmag.client.model.CreeperGirlPowerArmorModel;
import com.github.mechalopa.hmag.client.model.CrimsonSlaughtererModel;
import com.github.mechalopa.hmag.client.model.CursedDollModel;
import com.github.mechalopa.hmag.client.model.DodomekiModel;
import com.github.mechalopa.hmag.client.model.DoguModel;
import com.github.mechalopa.hmag.client.model.DrownedGirlModel;
import com.github.mechalopa.hmag.client.model.DullahanModel;
import com.github.mechalopa.hmag.client.model.DyssomniaModel;
import com.github.mechalopa.hmag.client.model.EnderExecutorModel;
import com.github.mechalopa.hmag.client.model.FortressKeeperModel;
import com.github.mechalopa.hmag.client.model.GhastlySeekerModel;
import com.github.mechalopa.hmag.client.model.GhostModel;
import com.github.mechalopa.hmag.client.model.HarpyModel;
import com.github.mechalopa.hmag.client.model.HornetModel;
import com.github.mechalopa.hmag.client.model.ImpModel;
import com.github.mechalopa.hmag.client.model.JackFrostModel;
import com.github.mechalopa.hmag.client.model.KashaModel;
import com.github.mechalopa.hmag.client.model.KoboldModel;
import com.github.mechalopa.hmag.client.model.LichModel;
import com.github.mechalopa.hmag.client.model.MagicalSlimeModel;
import com.github.mechalopa.hmag.client.model.MeltyMonsterModel;
import com.github.mechalopa.hmag.client.model.MonolithModel;
import com.github.mechalopa.hmag.client.model.NecroticReaperModel;
import com.github.mechalopa.hmag.client.model.OgreModel;
import com.github.mechalopa.hmag.client.model.RedcapModel;
import com.github.mechalopa.hmag.client.model.SavagefangModel;
import com.github.mechalopa.hmag.client.model.ScorpionModel;
import com.github.mechalopa.hmag.client.model.SkeletonGirlModel;
import com.github.mechalopa.hmag.client.model.SlimeGirlModel;
import com.github.mechalopa.hmag.client.model.SnowCanineModel;
import com.github.mechalopa.hmag.client.model.SpiderNestModel;
import com.github.mechalopa.hmag.client.model.StrayGirlClothingModel;
import com.github.mechalopa.hmag.client.model.ZombieGirlModel;
import com.github.mechalopa.hmag.client.particle.EnchantmentRuneParticle;
import com.github.mechalopa.hmag.client.renderer.ArurauneRenderer;
import com.github.mechalopa.hmag.client.renderer.BansheeRenderer;
import com.github.mechalopa.hmag.client.renderer.CatoblepasRenderer;
import com.github.mechalopa.hmag.client.renderer.CreeperGirlRenderer;
import com.github.mechalopa.hmag.client.renderer.CrimsonSlaughtererRenderer;
import com.github.mechalopa.hmag.client.renderer.CursedDollRenderer;
import com.github.mechalopa.hmag.client.renderer.DodomekiRenderer;
import com.github.mechalopa.hmag.client.renderer.DoguRenderer;
import com.github.mechalopa.hmag.client.renderer.DrownedGirlRenderer;
import com.github.mechalopa.hmag.client.renderer.DullahanRenderer;
import com.github.mechalopa.hmag.client.renderer.DyssomniaRenderer;
import com.github.mechalopa.hmag.client.renderer.EnderExecutorRenderer;
import com.github.mechalopa.hmag.client.renderer.FortressKeeperRenderer;
import com.github.mechalopa.hmag.client.renderer.GhastlySeekerRenderer;
import com.github.mechalopa.hmag.client.renderer.GhostRenderer;
import com.github.mechalopa.hmag.client.renderer.HarpyRenderer;
import com.github.mechalopa.hmag.client.renderer.HornetRenderer;
import com.github.mechalopa.hmag.client.renderer.HuskGirlRenderer;
import com.github.mechalopa.hmag.client.renderer.ImpRenderer;
import com.github.mechalopa.hmag.client.renderer.JackFrostRenderer;
import com.github.mechalopa.hmag.client.renderer.KashaRenderer;
import com.github.mechalopa.hmag.client.renderer.KoboldRenderer;
import com.github.mechalopa.hmag.client.renderer.LichRenderer;
import com.github.mechalopa.hmag.client.renderer.MagicalSlimeRenderer;
import com.github.mechalopa.hmag.client.renderer.MeltyMonsterRenderer;
import com.github.mechalopa.hmag.client.renderer.MonolithRenderer;
import com.github.mechalopa.hmag.client.renderer.NecroticReaperRenderer;
import com.github.mechalopa.hmag.client.renderer.OgreRenderer;
import com.github.mechalopa.hmag.client.renderer.RedcapRenderer;
import com.github.mechalopa.hmag.client.renderer.SavagefangRenderer;
import com.github.mechalopa.hmag.client.renderer.ScorpionRenderer;
import com.github.mechalopa.hmag.client.renderer.SkeletonGirlRenderer;
import com.github.mechalopa.hmag.client.renderer.SlimeGirlRenderer;
import com.github.mechalopa.hmag.client.renderer.SnowCanineRenderer;
import com.github.mechalopa.hmag.client.renderer.SpiderNestRenderer;
import com.github.mechalopa.hmag.client.renderer.StrayGirlRenderer;
import com.github.mechalopa.hmag.client.renderer.WitherGhostRenderer;
import com.github.mechalopa.hmag.client.renderer.WitherSkeletonGirlRenderer;
import com.github.mechalopa.hmag.client.renderer.ZombieGirlRenderer;
import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.registry.ModBlocks;
import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.registry.ModItems;
import com.github.mechalopa.hmag.registry.ModParticleTypes;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ModClientEventBusSubscriber
{
	@SubscribeEvent
	public static void setupClient(final FMLClientSetupEvent event)
	{
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.REINFORCED_GLASS.get(), RenderType.cutout());

		event.enqueueWork(() -> {
			ItemProperties.register(ModItems.INSOMNIA_SWORD.get(), new ResourceLocation("level"), (stack, world, livingentity, seed) -> {
				final int i = ModUtils.getItemLevel(stack);
				return i >= 5 ? 2.0F : (i > 0 ? 1.0F : 0.0F);
			});
			ItemProperties.register(ModItems.NEMESIS_BLADE.get(), new ResourceLocation("level"), (stack, world, livingentity, seed) -> {
				final int i = ModUtils.getItemLevel(stack);
				return i >= 6 ? 4.0F : (i >= 5 ? 3.0F : (i >= 3 ? 2.0F : (i > 0 ? 1.0F : 0.0F)));
			});
			ItemProperties.register(ModItems.CRIMSON_BOW.get(), new ResourceLocation("pull"), ModClientUtils.PROPERTY_BOW_PULL);
			ItemProperties.register(ModItems.CRIMSON_BOW.get(), new ResourceLocation("pulling"), ModClientUtils.PROPERTY_BOW_PULLING);
			ItemProperties.register(ModItems.ANCIENT_SHIELD.get(), new ResourceLocation("blocking"), ModClientUtils.PROPERTY_SHIELD_BLOCKING);
			ItemProperties.register(ModItems.FORTRESS_SHIELD.get(), new ResourceLocation("blocking"), ModClientUtils.PROPERTY_SHIELD_BLOCKING);
		});

		MinecraftForge.EVENT_BUS.register(new ModClientEvents());
	}

	@SubscribeEvent
	public static void registerRenderers(RegisterRenderers event)
	{
		event.registerEntityRenderer(ModEntityTypes.ZOMBIE_GIRL.get(), ZombieGirlRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.HUSK_GIRL.get(), HuskGirlRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.DROWNED_GIRL.get(), DrownedGirlRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.SKELETON_GIRL.get(), SkeletonGirlRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.WITHER_SKELETON_GIRL.get(), WitherSkeletonGirlRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.STRAY_GIRL.get(), StrayGirlRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.CREEPER_GIRL.get(), CreeperGirlRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.GHOST.get(), GhostRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.WITHER_GHOST.get(), WitherGhostRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.ENDER_EXECUTOR.get(), EnderExecutorRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.KOBOLD.get(), KoboldRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.LICH.get(), LichRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.OGRE.get(), OgreRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.SPIDER_NEST.get(), SpiderNestRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.MELTY_MONSTER.get(), MeltyMonsterRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.CURSED_DOLL.get(), CursedDollRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.JACK_FROST.get(), JackFrostRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.HORNET.get(), HornetRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.DULLAHAN.get(), DullahanRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.BANSHEE.get(), BansheeRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.ARURAUNE.get(), ArurauneRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.CATOBLEPAS.get(), CatoblepasRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.SCORPION.get(), ScorpionRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.KASHA.get(), KashaRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.DOGU.get(), DoguRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.GHSATLY_SHEEKER.get(), GhastlySeekerRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.REDCAP.get(), RedcapRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.SLIME_GIRL.get(), SlimeGirlRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.MAGICAL_SLIME.get(), MagicalSlimeRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.MONOLITH.get(), MonolithRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.CRIMSON_SLAUGHTERER.get(), CrimsonSlaughtererRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.DYSSOMNIA.get(), DyssomniaRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.SNOW_CANINE.get(), SnowCanineRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.HARPY.get(), HarpyRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.SAVAGEFANG.get(), SavagefangRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.FORTRESS_KEEPER.get(), FortressKeeperRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.NECROTIC_REAPER.get(), NecroticReaperRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.DODOMEKI.get(), DodomekiRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.IMP.get(), ImpRenderer::new);
	}

	@SubscribeEvent
	public static void registerRenderers(RegisterLayerDefinitions event)
	{
		LayerDefinition layerdefinition = LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0.0F), 64, 32);
		LayerDefinition layerdefinition1 = LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.INNER_ARMOR_DEFORMATION, 0.0F), 64, 32);
		event.registerLayerDefinition(ModModelLayers.ZOMBIE_GIRL, ZombieGirlModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.ZOMBIE_GIRL_INNER_ARMOR, () -> layerdefinition1);
		event.registerLayerDefinition(ModModelLayers.ZOMBIE_GIRL_OUTER_ARMOR, () -> layerdefinition);
		event.registerLayerDefinition(ModModelLayers.HUSK_GIRL, ZombieGirlModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.HUSK_GIRL_INNER_ARMOR, () -> layerdefinition1);
		event.registerLayerDefinition(ModModelLayers.HUSK_GIRL_OUTER_ARMOR, () -> layerdefinition);
		event.registerLayerDefinition(ModModelLayers.DROWNED_GIRL, DrownedGirlModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.DROWNED_GIRL_INNER_ARMOR, () -> layerdefinition1);
		event.registerLayerDefinition(ModModelLayers.DROWNED_GIRL_OUTER_ARMOR, () -> layerdefinition);
		event.registerLayerDefinition(ModModelLayers.SKELETON_GIRL, SkeletonGirlModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.SKELETON_GIRL_INNER_ARMOR, () -> layerdefinition1);
		event.registerLayerDefinition(ModModelLayers.SKELETON_GIRL_OUTER_ARMOR, () -> layerdefinition);
		event.registerLayerDefinition(ModModelLayers.WITHER_SKELETON_GIRL, SkeletonGirlModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.WITHER_SKELETON_GIRL_INNER_ARMOR, () -> layerdefinition1);
		event.registerLayerDefinition(ModModelLayers.WITHER_SKELETON_GIRL_OUTER_ARMOR, () -> layerdefinition);
		event.registerLayerDefinition(ModModelLayers.STRAY_GIRL, SkeletonGirlModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.STRAY_GIRL_INNER_ARMOR, () -> layerdefinition1);
		event.registerLayerDefinition(ModModelLayers.STRAY_GIRL_OUTER_ARMOR, () -> layerdefinition);
		event.registerLayerDefinition(ModModelLayers.STRAY_GIRL_OUTER_LAYER, () -> LayerDefinition.create(StrayGirlClothingModel.createMesh(new CubeDeformation(0.25F)), 64, 32));
		event.registerLayerDefinition(ModModelLayers.CREEPER_GIRL, CreeperGirlModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.CREEPER_GIRL_INNER_ARMOR, () -> layerdefinition1);
		event.registerLayerDefinition(ModModelLayers.CREEPER_GIRL_OUTER_ARMOR, () -> layerdefinition);
		event.registerLayerDefinition(ModModelLayers.CREEPER_GIRL_POWER_ARMOR, () -> LayerDefinition.create(CreeperGirlPowerArmorModel.createMesh(new CubeDeformation(2.0F)), 64, 32));
		event.registerLayerDefinition(ModModelLayers.GHOST, GhostModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.GHOST_INNER_ARMOR, () -> layerdefinition1);
		event.registerLayerDefinition(ModModelLayers.GHOST_OUTER_ARMOR, () -> layerdefinition);
		event.registerLayerDefinition(ModModelLayers.WITHER_GHOST, GhostModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.WITHER_GHOST_INNER_ARMOR, () -> layerdefinition1);
		event.registerLayerDefinition(ModModelLayers.WITHER_GHOST_OUTER_ARMOR, () -> layerdefinition);
		event.registerLayerDefinition(ModModelLayers.ENDER_EXECUTOR, EnderExecutorModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.KOBOLD, KoboldModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.LICH, LichModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.OGRE, OgreModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.SPIDER_NEST, SpiderNestModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.MELTY_MONSTER, MeltyMonsterModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.MELTY_MONSTER_OUTER, MeltyMonsterModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.CURSED_DOLL, CursedDollModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.JACK_FROST, JackFrostModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.HORNET, HornetModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.DULLAHAN, DullahanModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.BANSHEE, BansheeModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.ARURAUNE, ArurauneModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.CATOBLEPAS, CatoblepasModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.SCORPION, ScorpionModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.KASHA, KashaModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.DOGU, DoguModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.GHSATLY_SHEEKER, GhastlySeekerModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.REDCAP, RedcapModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.REDCAP_INNER_ARMOR, () -> layerdefinition1);
		event.registerLayerDefinition(ModModelLayers.REDCAP_OUTER_ARMOR, () -> layerdefinition);
		event.registerLayerDefinition(ModModelLayers.SLIME_GIRL, SlimeGirlModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.SLIME_GIRL_OUTER, SlimeGirlModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.MAGICAL_SLIME, MagicalSlimeModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.MAGICAL_SLIME_OUTER, MagicalSlimeModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.MONOLITH, MonolithModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.CRIMSON_SLAUGHTERER, CrimsonSlaughtererModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.DYSSOMNIA, DyssomniaModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.SNOW_CANINE, SnowCanineModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.HARPY, HarpyModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.SAVAGEFANG, SavagefangModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.FORTRESS_KEEPER, FortressKeeperModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.NECROTIC_REAPER, NecroticReaperModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.DODOMEKI, DodomekiModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.IMP, ImpModel::createBodyLayer);
	}

	@SuppressWarnings("resource")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticleFactories(final ParticleFactoryRegisterEvent event)
	{
		Minecraft.getInstance().particleEngine.register(ModParticleTypes.ENCHANTMENT_RUNE.get(), EnchantmentRuneParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ModParticleTypes.NEMESIS_FLAME.get(), FlameParticle.Provider::new);
	}
}