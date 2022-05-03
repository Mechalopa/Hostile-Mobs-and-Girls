package com.github.mechalopa.hmag.client;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.DrownedGirlModel;
import com.github.mechalopa.hmag.client.model.KoboldModel;
import com.github.mechalopa.hmag.client.model.ZombieGirlModel;
import com.github.mechalopa.hmag.client.particle.EnchantmentRuneParticle;
import com.github.mechalopa.hmag.client.renderer.DrownedGirlRenderer;
import com.github.mechalopa.hmag.client.renderer.HuskGirlRenderer;
import com.github.mechalopa.hmag.client.renderer.KoboldRenderer;
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
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ZOMBIE_GIRL.get(), ZombieGirlRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HUSK_GIRL.get(), HuskGirlRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DROWNED_GIRL.get(), DrownedGirlRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SKELETON_GIRL.get(), SkeletonGirlRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WITHER_SKELETON_GIRL.get(), WitherSkeletonGirlRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.STRAY_GIRL.get(), StrayGirlRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CREEPER_GIRL.get(), CreeperGirlRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GHOST.get(), GhostRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WITHER_GHOST.get(), WitherGhostRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ENDER_EXECUTOR.get(), EnderExecutorRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.KOBOLD.get(), KoboldRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LICH.get(), LichRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.OGRE.get(), OgreRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SPIDER_NEST.get(), SpiderNestRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MELTY_MONSTER.get(), MeltyMonsterRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CURSED_DOLL.get(), CursedDollRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.JACK_FROST.get(), JackFrostRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HORNET.get(), HornetRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DULLAHAN.get(), DullahanRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BANSHEE.get(), BansheeRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ARURAUNE.get(), ArurauneRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CATOBLEPAS.get(), CatoblepasRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SCORPION.get(), ScorpionRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.KASHA.get(), KashaRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DOGU.get(), DoguRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GHSATLY_SHEEKER.get(), GhastlySeekerRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.REDCAP.get(), RedcapRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SLIME_GIRL.get(), SlimeGirlRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MAGICAL_SLIME.get(), MagicalSlimeRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MONOLITH.get(), MonolithRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CRIMSON_SLAUGHTERER.get(), CrimsonSlaughtererRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DYSSOMNIA.get(), DyssomniaRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SNOW_CANINE.get(), SnowCanineRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.STONEULAR.get(), StoneularRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HARPY.get(), HarpyRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SAVAGEFANG.get(), SavagefangRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FORTRESS_KEEPER.get(), FortressKeeperRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.NECROTIC_REAPER.get(), NecroticReaperRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DODOMEKI.get(), DodomekiRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.IMP.get(), ImpRenderer::new);
//
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MAGIC_BULLET.get(), MagicBulletRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HARD_SNOWBALL.get(), ModSpriteRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.POISON_SEED.get(), ModSpriteRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.THROWABLE_BOTTLE.get(), ModSpriteRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MAGMA_BULLET.get(), MagmaBulletRenderer::new);

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

		event.registerEntityRenderer(ModEntityTypes.KOBOLD.get(), KoboldRenderer::new);
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

		event.registerLayerDefinition(ModModelLayers.KOBOLD, KoboldModel::createBodyLayer);
	}

	@SuppressWarnings("resource")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticleFactories(final ParticleFactoryRegisterEvent event)
	{
		Minecraft.getInstance().particleEngine.register(ModParticleTypes.ENCHANTMENT_RUNE.get(), EnchantmentRuneParticle.Provider::new);
		Minecraft.getInstance().particleEngine.register(ModParticleTypes.NEMESIS_FLAME.get(), FlameParticle.Provider::new);
	}
}