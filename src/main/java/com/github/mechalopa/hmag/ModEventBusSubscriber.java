package com.github.mechalopa.hmag;

import com.github.mechalopa.hmag.entity.ArurauneEntity;
import com.github.mechalopa.hmag.entity.BansheeEntity;
import com.github.mechalopa.hmag.entity.CatoblepasEntity;
import com.github.mechalopa.hmag.entity.CreeperGirlEntity;
import com.github.mechalopa.hmag.entity.CrimsonSlaughtererEntity;
import com.github.mechalopa.hmag.entity.CursedDollEntity;
import com.github.mechalopa.hmag.entity.DodomekiEntity;
import com.github.mechalopa.hmag.entity.DoguEntity;
import com.github.mechalopa.hmag.entity.DrownedGirlEntity;
import com.github.mechalopa.hmag.entity.DullahanEntity;
import com.github.mechalopa.hmag.entity.DyssomniaEntity;
import com.github.mechalopa.hmag.entity.EnderExecutorEntity;
import com.github.mechalopa.hmag.entity.FortressKeeperEntity;
import com.github.mechalopa.hmag.entity.GhastlySeekerEntity;
import com.github.mechalopa.hmag.entity.GhostEntity;
import com.github.mechalopa.hmag.entity.HarpyEntity;
import com.github.mechalopa.hmag.entity.HornetEntity;
import com.github.mechalopa.hmag.entity.HuskGirlEntity;
import com.github.mechalopa.hmag.entity.ImpEntity;
import com.github.mechalopa.hmag.entity.JackFrostEntity;
import com.github.mechalopa.hmag.entity.KashaEntity;
import com.github.mechalopa.hmag.entity.KoboldEntity;
import com.github.mechalopa.hmag.entity.LichEntity;
import com.github.mechalopa.hmag.entity.MagicalSlimeEntity;
import com.github.mechalopa.hmag.entity.MeltyMonsterEntity;
import com.github.mechalopa.hmag.entity.MonolithEntity;
import com.github.mechalopa.hmag.entity.NecroticReaperEntity;
import com.github.mechalopa.hmag.entity.OgreEntity;
import com.github.mechalopa.hmag.entity.RedcapEntity;
import com.github.mechalopa.hmag.entity.SavagefangEntity;
import com.github.mechalopa.hmag.entity.ScorpionEntity;
import com.github.mechalopa.hmag.entity.SkeletonGirlEntity;
import com.github.mechalopa.hmag.entity.SlimeGirlEntity;
import com.github.mechalopa.hmag.entity.SnowCanineEntity;
import com.github.mechalopa.hmag.entity.SpiderNestEntity;
import com.github.mechalopa.hmag.entity.StoneularEntity;
import com.github.mechalopa.hmag.entity.StrayGirlEntity;
import com.github.mechalopa.hmag.entity.WitherGhostEntity;
import com.github.mechalopa.hmag.entity.WitherSkeletonGirlEntity;
import com.github.mechalopa.hmag.entity.ZombieGirlEntity;
import com.github.mechalopa.hmag.loot.conditions.ModLoadedCondition;
import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.registry.ModItems;
import com.github.mechalopa.hmag.registry.ModPotions;
import com.github.mechalopa.hmag.util.ModBrewingRecipe;
import com.github.mechalopa.hmag.util.ModSpawnRules;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
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
		ComposterBlock.COMPOSTABLES.put(ModItems.HONEYED_APPLE.get(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(ModItems.HONEYED_LEMON.get(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(ModItems.LEMON_PIE.get(), 1.0F);
		ComposterBlock.COMPOSTABLES.put(ModItems.BLUEBERRY_PIE.get(), 1.0F);
		ComposterBlock.COMPOSTABLES.put(ModItems.CUBIC_NUCLEUS.get(), 0.65F);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event)
	{
		EntitySpawnPlacementRegistry.register(ModEntityTypes.ZOMBIE_GIRL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.HUSK_GIRL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnOnGroundRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.DROWNED_GIRL.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DrownedGirlEntity::checkDrownedGirlSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.SKELETON_GIRL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.WITHER_SKELETON_GIRL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.STRAY_GIRL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnOnGroundRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.CREEPER_GIRL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.GHOST.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.WITHER_GHOST.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.ENDER_EXECUTOR.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.KOBOLD.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightUnderY56Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.LICH.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightUnderY24Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.OGRE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightUnderY24Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.SPIDER_NEST.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightUnderY24Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.MELTY_MONSTER.get(), EntitySpawnPlacementRegistry.PlacementType.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MeltyMonsterEntity::checkMeltyMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.CURSED_DOLL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.JACK_FROST.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.HORNET.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.DULLAHAN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.BANSHEE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.ARURAUNE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.CATOBLEPAS.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.SCORPION.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.KASHA.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkAnyLightMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.DOGU.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnOnUndergroundRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.GHSATLY_SHEEKER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GhastlySeekerEntity::checkGhastlySeekerSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.REDCAP.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.SLIME_GIRL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.MAGICAL_SLIME.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMobSpawnInLightRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.MONOLITH.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonolithEntity::checkMonolithSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.CRIMSON_SLAUGHTERER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CrimsonSlaughtererEntity::checkCrimsonSlaughtererSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.DYSSOMNIA.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DyssomniaEntity::checkDyssomniaSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.SNOW_CANINE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.STONEULAR.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightUnderY32Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.HARPY.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.SAVAGEFANG.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SavagefangEntity::checkSavagefangSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.FORTRESS_KEEPER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkAnyLightMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.NECROTIC_REAPER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightUnderY32Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.DODOMEKI.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ModSpawnRules::checkMonsterSpawnInLightOverY64Rules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.IMP.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkAnyLightMonsterSpawnRules);
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
		event.put(ModEntityTypes.GHSATLY_SHEEKER.get(), GhastlySeekerEntity.createAttributes().build());
		event.put(ModEntityTypes.REDCAP.get(), RedcapEntity.createAttributes().build());
		event.put(ModEntityTypes.SLIME_GIRL.get(), SlimeGirlEntity.createAttributes().build());
		event.put(ModEntityTypes.MAGICAL_SLIME.get(), MagicalSlimeEntity.createAttributes().build());
		event.put(ModEntityTypes.MONOLITH.get(), MonolithEntity.createAttributes().build());
		event.put(ModEntityTypes.CRIMSON_SLAUGHTERER.get(), CrimsonSlaughtererEntity.createAttributes().build());
		event.put(ModEntityTypes.DYSSOMNIA.get(), DyssomniaEntity.createAttributes().build());
		event.put(ModEntityTypes.SNOW_CANINE.get(), SnowCanineEntity.createAttributes().build());
		event.put(ModEntityTypes.STONEULAR.get(), StoneularEntity.createAttributes().build());
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