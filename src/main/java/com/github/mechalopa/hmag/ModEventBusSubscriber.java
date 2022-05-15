package com.github.mechalopa.hmag;

import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.registry.ModItems;
import com.github.mechalopa.hmag.registry.ModPotions;
import com.github.mechalopa.hmag.util.ModBrewingRecipe;
import com.github.mechalopa.hmag.util.ModSpawnRules;
import com.github.mechalopa.hmag.util.ModUtils;
import com.github.mechalopa.hmag.world.entity.ArurauneEntity;
import com.github.mechalopa.hmag.world.entity.BansheeEntity;
import com.github.mechalopa.hmag.world.entity.CatoblepasEntity;
import com.github.mechalopa.hmag.world.entity.CreeperGirlEntity;
import com.github.mechalopa.hmag.world.entity.CrimsonSlaughtererEntity;
import com.github.mechalopa.hmag.world.entity.CursedDollEntity;
import com.github.mechalopa.hmag.world.entity.DodomekiEntity;
import com.github.mechalopa.hmag.world.entity.DoguEntity;
import com.github.mechalopa.hmag.world.entity.DrownedGirlEntity;
import com.github.mechalopa.hmag.world.entity.DullahanEntity;
import com.github.mechalopa.hmag.world.entity.DyssomniaEntity;
import com.github.mechalopa.hmag.world.entity.EnderExecutorEntity;
import com.github.mechalopa.hmag.world.entity.FortressKeeperEntity;
import com.github.mechalopa.hmag.world.entity.GhastlySeekerEntity;
import com.github.mechalopa.hmag.world.entity.GhostEntity;
import com.github.mechalopa.hmag.world.entity.HarpyEntity;
import com.github.mechalopa.hmag.world.entity.HornetEntity;
import com.github.mechalopa.hmag.world.entity.HuskGirlEntity;
import com.github.mechalopa.hmag.world.entity.ImpEntity;
import com.github.mechalopa.hmag.world.entity.JackFrostEntity;
import com.github.mechalopa.hmag.world.entity.KashaEntity;
import com.github.mechalopa.hmag.world.entity.KoboldEntity;
import com.github.mechalopa.hmag.world.entity.LichEntity;
import com.github.mechalopa.hmag.world.entity.MagicalSlimeEntity;
import com.github.mechalopa.hmag.world.entity.MeltyMonsterEntity;
import com.github.mechalopa.hmag.world.entity.MonolithEntity;
import com.github.mechalopa.hmag.world.entity.NecroticReaperEntity;
import com.github.mechalopa.hmag.world.entity.OgreEntity;
import com.github.mechalopa.hmag.world.entity.RedcapEntity;
import com.github.mechalopa.hmag.world.entity.SavagefangEntity;
import com.github.mechalopa.hmag.world.entity.ScorpionEntity;
import com.github.mechalopa.hmag.world.entity.SkeletonGirlEntity;
import com.github.mechalopa.hmag.world.entity.SlimeGirlEntity;
import com.github.mechalopa.hmag.world.entity.SnowCanineEntity;
import com.github.mechalopa.hmag.world.entity.SpiderNestEntity;
import com.github.mechalopa.hmag.world.entity.StrayGirlEntity;
import com.github.mechalopa.hmag.world.entity.WitherGhostEntity;
import com.github.mechalopa.hmag.world.entity.WitherSkeletonGirlEntity;
import com.github.mechalopa.hmag.world.entity.ZombieGirlEntity;
import com.github.mechalopa.hmag.world.level.storage.loot.conditions.ModLoadedCondition;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusSubscriber
{
	@SubscribeEvent
	public static void setup(final FMLCommonSetupEvent event)
	{
		event.enqueueWork(() -> {
			if (ModConfigs.cachedServer.KOBOLD_POTION_BREWING_RECIPES)
			{
				registerBrewingRecipe(Potions.AWKWARD, ModItems.KOBOLD_LEATHER.get(), ModPotions.KOBOLD.get());
				registerBrewingRecipe(ModPotions.KOBOLD.get(), Items.GLOWSTONE_DUST, ModPotions.KOBOLD_STRONG.get());
			}

			if (ModConfigs.cachedServer.HEALING_III_POTION_BREWING_RECIPES)
				registerBrewingRecipe(Potions.STRONG_HEALING, ModItems.LICH_CLOTH.get(), ModPotions.HEALING_III.get());

			if (ModConfigs.cachedServer.HARMING_III_POTION_BREWING_RECIPES)
			{
				registerBrewingRecipe(Potions.STRONG_HARMING, ModItems.LICH_CLOTH.get(), ModPotions.HARMING_III.get());
				registerBrewingRecipe(ModPotions.HEALING_III.get(), Items.FERMENTED_SPIDER_EYE, ModPotions.HARMING_III.get());
			}

			if (ModConfigs.cachedServer.OGRE_POTION_BREWING_RECIPES)
				registerBrewingRecipe(Potions.STRENGTH, ModItems.OGRE_HORN.get(), ModPotions.OGRE.get());
			if (ModConfigs.cachedServer.VORACITY_POTION_BREWING_RECIPES)
				registerBrewingRecipe(Potions.STRONG_STRENGTH, ModItems.CRIMSON_CUTICULA.get(), ModPotions.VORACITY.get());

			if (ModConfigs.cachedServer.ENDER_RAGE_POTION_BREWING_RECIPES)
			{
				registerBrewingRecipe(Potions.AWKWARD, ModItems.ENDER_PLASM.get(), ModPotions.ENDER_RAGE.get());
				registerBrewingRecipe(ModPotions.ENDER_RAGE.get(), Items.REDSTONE, ModPotions.ENDER_RAGE_LONG.get());
				registerBrewingRecipe(ModPotions.ENDER_RAGE.get(), Items.GLOWSTONE_DUST, ModPotions.ENDER_RAGE_STRONG.get());
			}

			if (ModConfigs.cachedServer.COMBUSTION_POTION_BREWING_RECIPES)
			{
				registerBrewingRecipe(Potions.AWKWARD, ModItems.BURNING_CORE.get(), ModPotions.COMBUSTION.get());
				registerBrewingRecipe(ModPotions.COMBUSTION.get(), Items.REDSTONE, ModPotions.COMBUSTION_LONG.get());
				registerBrewingRecipe(ModPotions.COMBUSTION.get(), Items.FERMENTED_SPIDER_EYE, Potions.FIRE_RESISTANCE);
				registerBrewingRecipe(ModPotions.COMBUSTION_LONG.get(), Items.FERMENTED_SPIDER_EYE, Potions.LONG_FIRE_RESISTANCE);
			}

			if (ModConfigs.cachedServer.SLOWNESS_POTION_BREWING_RECIPES)
				registerBrewingRecipe(Potions.AWKWARD, ModItems.NECROFIBER.get(), Potions.SLOWNESS);
			if (ModConfigs.cachedServer.INVISIBILITY_POTION_BREWING_RECIPES)
				registerBrewingRecipe(Potions.AWKWARD, ModItems.SOUL_APPLE.get(), Potions.INVISIBILITY);
			if (ModConfigs.cachedServer.REGENERATION_POTION_BREWING_RECIPES)
				registerBrewingRecipe(Potions.AWKWARD, ModItems.CUBIC_NUCLEUS.get(), Potions.REGENERATION);
			if (ModConfigs.cachedServer.BLASTING_BOTTLE_BREWING_RECIPES)
				BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Ingredient.of(ModItems.FIRE_BOTTLE.get()), Ingredient.of(ModItems.BURNING_CORE.get()), new ItemStack(ModItems.BLASTING_BOTTLE.get(), 1)));
			if (ModConfigs.cachedServer.LIGHTNING_BOTTLE_BREWING_RECIPES)
				BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Ingredient.of(ModItems.FIRE_BOTTLE.get()), Ingredient.of(ModItems.LIGHTNING_PARTICLE.get()), new ItemStack(ModItems.LIGHTNING_BOTTLE.get(), 1)));
		});

		Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(HMaG.MODID, "mod_loaded"), ModLoadedCondition.TYPE);
	}

//	@SubscribeEvent
//	public static void enqueueIMC(final InterModEnqueueEvent event){}
//
//	@SubscribeEvent
//	public static void processIMC(final InterModProcessEvent event){}
//
//	@SubscribeEvent
//	public void onServerStarting(FMLServerStartingEvent event){}

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		ComposterBlock.COMPOSTABLES.put(ModItems.LEMON.get(), 0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.STRAWBERRY.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(ModItems.BLUEBERRY.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(ModItems.EXP_BERRY.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(ModItems.CURE_BERRY.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(ModItems.HONEYED_APPLE.get(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(ModItems.HONEYED_LEMON.get(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(ModItems.LEMON_PIE.get(), 1.0F);
		ComposterBlock.COMPOSTABLES.put(ModItems.BLUEBERRY_PIE.get(), 1.0F);
		ComposterBlock.COMPOSTABLES.put(ModItems.CUBIC_NUCLEUS.get(), 0.65F);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event)
	{
		SpawnPlacements.register(ModEntityTypes.ZOMBIE_GIRL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(ModEntityTypes.HUSK_GIRL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnOnGroundRules);
		SpawnPlacements.register(ModEntityTypes.DROWNED_GIRL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DrownedGirlEntity::checkDrownedGirlSpawnRules);
		SpawnPlacements.register(ModEntityTypes.SKELETON_GIRL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(ModEntityTypes.WITHER_SKELETON_GIRL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(ModEntityTypes.STRAY_GIRL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnOnGroundRules);
		SpawnPlacements.register(ModEntityTypes.CREEPER_GIRL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(ModEntityTypes.GHOST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(ModEntityTypes.WITHER_GHOST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(ModEntityTypes.ENDER_EXECUTOR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(ModEntityTypes.KOBOLD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightUnderY32Rules);
		SpawnPlacements.register(ModEntityTypes.LICH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightUnderY0Rules);
		SpawnPlacements.register(ModEntityTypes.OGRE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightUnderY0Rules);
		SpawnPlacements.register(ModEntityTypes.SPIDER_NEST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightUnderY0Rules);
		SpawnPlacements.register(ModEntityTypes.MELTY_MONSTER.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MeltyMonsterEntity::checkMeltyMonsterSpawnRules);
		SpawnPlacements.register(ModEntityTypes.CURSED_DOLL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.JACK_FROST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.HORNET.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.DULLAHAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.BANSHEE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.ARURAUNE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.CATOBLEPAS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.SCORPION.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.KASHA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
		SpawnPlacements.register(ModEntityTypes.DOGU.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnOnUndergroundRules);
		SpawnPlacements.register(ModEntityTypes.GHASTLY_SEEKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GhastlySeekerEntity::checkGhastlySeekerSpawnRules);
		SpawnPlacements.register(ModEntityTypes.REDCAP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.SLIME_GIRL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.MAGICAL_SLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMobSpawnInLightRules);
		SpawnPlacements.register(ModEntityTypes.MONOLITH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MonolithEntity::checkMonolithSpawnRules);
		SpawnPlacements.register(ModEntityTypes.CRIMSON_SLAUGHTERER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrimsonSlaughtererEntity::checkCrimsonSlaughtererSpawnRules);
		SpawnPlacements.register(ModEntityTypes.DYSSOMNIA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DyssomniaEntity::checkDyssomniaSpawnRules);
		SpawnPlacements.register(ModEntityTypes.SNOW_CANINE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.HARPY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.SAVAGEFANG.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SavagefangEntity::checkSavagefangSpawnRules);
		SpawnPlacements.register(ModEntityTypes.FORTRESS_KEEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
		SpawnPlacements.register(ModEntityTypes.NECROTIC_REAPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightUnderY0Rules);
		SpawnPlacements.register(ModEntityTypes.DODOMEKI.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		SpawnPlacements.register(ModEntityTypes.IMP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
	}

	@SubscribeEvent
	public static void createEntityAttributes(final EntityAttributeCreationEvent event)
	{
		event.put(ModEntityTypes.ZOMBIE_GIRL.get(), ZombieGirlEntity.createAttributes().build());
		event.put(ModEntityTypes.HUSK_GIRL.get(), HuskGirlEntity.createAttributes().build());
		event.put(ModEntityTypes.DROWNED_GIRL.get(), DrownedGirlEntity.createAttributes().build());
		event.put(ModEntityTypes.SKELETON_GIRL.get(), SkeletonGirlEntity.createAttributes().build());
		event.put(ModEntityTypes.WITHER_SKELETON_GIRL.get(), WitherSkeletonGirlEntity.createAttributes().build());
		event.put(ModEntityTypes.STRAY_GIRL.get(), StrayGirlEntity.createAttributes().build());
		event.put(ModEntityTypes.CREEPER_GIRL.get(), CreeperGirlEntity.createAttributes().build());
		event.put(ModEntityTypes.GHOST.get(), GhostEntity.createAttributes().build());
		event.put(ModEntityTypes.WITHER_GHOST.get(), WitherGhostEntity.createAttributes().build());
		event.put(ModEntityTypes.ENDER_EXECUTOR.get(), EnderExecutorEntity.createAttributes().build());
		event.put(ModEntityTypes.KOBOLD.get(), KoboldEntity.createAttributes().build());
		event.put(ModEntityTypes.LICH.get(), LichEntity.createAttributes().build());
		event.put(ModEntityTypes.OGRE.get(), OgreEntity.createAttributes().build());
		event.put(ModEntityTypes.SPIDER_NEST.get(), SpiderNestEntity.createAttributes().build());
		event.put(ModEntityTypes.MELTY_MONSTER.get(), MeltyMonsterEntity.createAttributes().build());
		event.put(ModEntityTypes.CURSED_DOLL.get(), CursedDollEntity.createAttributes().build());
		event.put(ModEntityTypes.JACK_FROST.get(), JackFrostEntity.createAttributes().build());
		event.put(ModEntityTypes.HORNET.get(), HornetEntity.createAttributes().build());
		event.put(ModEntityTypes.DULLAHAN.get(), DullahanEntity.createAttributes().build());
		event.put(ModEntityTypes.BANSHEE.get(), BansheeEntity.createAttributes().build());
		event.put(ModEntityTypes.ARURAUNE.get(), ArurauneEntity.createAttributes().build());
		event.put(ModEntityTypes.CATOBLEPAS.get(), CatoblepasEntity.createAttributes().build());
		event.put(ModEntityTypes.SCORPION.get(), ScorpionEntity.createAttributes().build());
		event.put(ModEntityTypes.KASHA.get(), KashaEntity.createAttributes().build());
		event.put(ModEntityTypes.DOGU.get(), DoguEntity.createAttributes().build());
		event.put(ModEntityTypes.GHASTLY_SEEKER.get(), GhastlySeekerEntity.createAttributes().build());
		event.put(ModEntityTypes.REDCAP.get(), RedcapEntity.createAttributes().build());
		event.put(ModEntityTypes.SLIME_GIRL.get(), SlimeGirlEntity.createAttributes().build());
		event.put(ModEntityTypes.MAGICAL_SLIME.get(), MagicalSlimeEntity.createAttributes().build());
		event.put(ModEntityTypes.MONOLITH.get(), MonolithEntity.createAttributes().build());
		event.put(ModEntityTypes.CRIMSON_SLAUGHTERER.get(), CrimsonSlaughtererEntity.createAttributes().build());
		event.put(ModEntityTypes.DYSSOMNIA.get(), DyssomniaEntity.createAttributes().build());
		event.put(ModEntityTypes.SNOW_CANINE.get(), SnowCanineEntity.createAttributes().build());
		event.put(ModEntityTypes.HARPY.get(), HarpyEntity.createAttributes().build());
		event.put(ModEntityTypes.SAVAGEFANG.get(), SavagefangEntity.createAttributes().build());
		event.put(ModEntityTypes.FORTRESS_KEEPER.get(), FortressKeeperEntity.createAttributes().build());
		event.put(ModEntityTypes.NECROTIC_REAPER.get(), NecroticReaperEntity.createAttributes().build());
		event.put(ModEntityTypes.DODOMEKI.get(), DodomekiEntity.createAttributes().build());
		event.put(ModEntityTypes.IMP.get(), ImpEntity.createAttributes().build());
	}

	private static void registerBrewingRecipe(Potion inputPotion, Item item, Potion outputPotion)
	{
		BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Ingredient.of(ModUtils.getPotionStack(inputPotion)), Ingredient.of(item), ModUtils.getPotionStack(outputPotion)));
		BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Ingredient.of(ModUtils.getPotionStack(inputPotion, Items.SPLASH_POTION)), Ingredient.of(item), ModUtils.getPotionStack(outputPotion, Items.SPLASH_POTION)));
		BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Ingredient.of(ModUtils.getPotionStack(inputPotion, Items.LINGERING_POTION)), Ingredient.of(item), ModUtils.getPotionStack(outputPotion, Items.LINGERING_POTION)));
	}
}