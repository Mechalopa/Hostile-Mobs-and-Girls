package com.github.mechalopa.hmag;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class ModConfigs
{
	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	public static ForgeConfigSpec SERVER_CONFIG;

	public static ForgeConfigSpec.BooleanValue ENDER_EXECUTOR_REDUCE_DAMAGE;
	public static ForgeConfigSpec.BooleanValue ENDER_EXECUTOR_BEAM_ATTACK;
	public static ForgeConfigSpec.BooleanValue LICH_ATTACK_BABY_TURTLES;
	public static ForgeConfigSpec.BooleanValue LICH_SUMMON_VEX;
	public static ForgeConfigSpec.BooleanValue OGRE_ATTACK_VILLAGERS;
	public static ForgeConfigSpec.BooleanValue OGRE_ATTACK_BABY_TURTLES;
	public static ForgeConfigSpec.BooleanValue OGRE_DESTROY_BLOCKS;
	public static ForgeConfigSpec.BooleanValue SPIDER_NEST_SUMMON_CAVE_SPIDER;
	public static ForgeConfigSpec.BooleanValue MELTY_MONSTER_SET_FIRE;
	public static ForgeConfigSpec.BooleanValue KASHA_ATTACK_CHICKENS;
	public static ForgeConfigSpec.BooleanValue KASHA_ATTACK_BABY_HOGLINS;
	public static ForgeConfigSpec.BooleanValue KASHA_ATTACK_VILLAGERS;
	public static ForgeConfigSpec.BooleanValue KASHA_ATTACK_BABY_TURTLES;
	public static ForgeConfigSpec.BooleanValue JACK_FROST_FREEZES_WATER;
	public static ForgeConfigSpec.BooleanValue JACK_FROST_REGEN;
	public static ForgeConfigSpec.BooleanValue ARURAUNE_ATTACK_VILLAGERS;
	public static ForgeConfigSpec.BooleanValue ARURAUNE_REGEN;
	public static ForgeConfigSpec.BooleanValue REDCAP_ATTACK_VILLAGERS;
	public static ForgeConfigSpec.BooleanValue REDCAP_ATTACK_BABY_TURTLES;
	public static ForgeConfigSpec.BooleanValue SLIME_GIRL_REGEN;
	public static ForgeConfigSpec.BooleanValue MAGICAL_SLIME_SPAWNS_ON_SLIME_GIRL_DEATH;
	public static ForgeConfigSpec.BooleanValue MONOLITH_ATTACK_GOLEMS;
	public static ForgeConfigSpec.BooleanValue MONOLITH_ATTACK_VILLAGERS;
	public static ForgeConfigSpec.BooleanValue MONOLITH_ATTACK_ILLAGERS;
	public static ForgeConfigSpec.BooleanValue CRIMSON_SLAUGHTERER_ATTACK_ANIMALS;
	public static ForgeConfigSpec.BooleanValue CRIMSON_SLAUGHTERER_ATTACK_VILLAGERS;
	public static ForgeConfigSpec.BooleanValue DYSSOMNIA_SUMMON_PHANTOM;
	public static ForgeConfigSpec.BooleanValue SNOW_CANINE_ATTACK_SHEEP;
	public static ForgeConfigSpec.BooleanValue SNOW_CANINE_ATTACK_RABBITS;
	public static ForgeConfigSpec.BooleanValue SNOW_CANINE_ATTACK_BABY_TURTLES;
	public static ForgeConfigSpec.BooleanValue SAVAGEFANG_ATTACK_ANIMALS;
	public static ForgeConfigSpec.BooleanValue SAVAGEFANG_ATTACK_VILLAGERS;
	public static ForgeConfigSpec.BooleanValue SAVAGEFANG_ATTACK_ILLAGERS;
	public static ForgeConfigSpec.BooleanValue SAVAGEFANG_ATTACK_DAMAGED_MOBS;
	public static ForgeConfigSpec.BooleanValue NECROTIC_REAPER_ATTACK_VILLAGERS;
	public static ForgeConfigSpec.BooleanValue NECROTIC_REAPER_ATTACK_BABY_TURTLES;

	public static ForgeConfigSpec.DoubleValue ZOMBIE_GIRL_REPLACE_CHANCE;
	public static ForgeConfigSpec.DoubleValue HUSK_GIRL_REPLACE_CHANCE;
	public static ForgeConfigSpec.DoubleValue DROWNED_GIRL_REPLACE_CHANCE;
	public static ForgeConfigSpec.DoubleValue SKELETON_GIRL_REPLACE_CHANCE;
	public static ForgeConfigSpec.DoubleValue WITHER_SKELETON_GIRL_REPLACE_CHANCE;
	public static ForgeConfigSpec.DoubleValue STRAY_GIRL_REPLACE_CHANCE;
	public static ForgeConfigSpec.DoubleValue CREEPER_GIRL_REPLACE_CHANCE;
	public static ForgeConfigSpec.DoubleValue ENDER_EXECUTOR_REPLACE_CHANCE;

	public static ForgeConfigSpec.IntValue GHOST_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue WITHER_GHOST_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue WITHER_GHOST_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY;
	public static ForgeConfigSpec.IntValue WITHER_GHOST_SPAWN_WEIGHT_IN_BASALT_DELTAS;
	public static ForgeConfigSpec.IntValue KOBOLD_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue LICH_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue OGRE_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue SPIDER_NEST_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue MELTY_MONSTER_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue MELTY_MONSTER_SPAWN_WEIGHT_IN_NETHER;
	public static ForgeConfigSpec.IntValue MELTY_MONSTER_SPAWN_WEIGHT_IN_END;
	public static ForgeConfigSpec.IntValue CURSED_DOLL_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue JACK_FROST_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue HORNET_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue DULLAHAN_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue BANSHEE_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue ARURAUNE_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue CATOBLEPAS_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue SCORPION_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue KASHA_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue KASHA_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY;
	public static ForgeConfigSpec.IntValue KASHA_SPAWN_WEIGHT_IN_BASALT_DELTAS;
	public static ForgeConfigSpec.IntValue DOGU_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue GHASTLY_SEEKER_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue GHASTLY_SEEKER_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY;
	public static ForgeConfigSpec.IntValue GHASTLY_SEEKER_SPAWN_WEIGHT_IN_BASALT_DELTAS;
	public static ForgeConfigSpec.IntValue REDCAP_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue SLIME_GIRL_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue MONOLITH_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue MONOLITH_SPAWN_WEIGHT_IN_END_CITY;
	public static ForgeConfigSpec.IntValue CRIMSON_SLAUGHTERER_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue DYSSOMNIA_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue SNOW_CANINE_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue HARPY_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue SAVAGEFANG_SPAWN_WEIGHT_IN_SWAMP;
	public static ForgeConfigSpec.IntValue SAVAGEFANG_SPAWN_WEIGHT_IN_JUNGLE;
	public static ForgeConfigSpec.IntValue FORTRESS_KEEPER_SPAWN_WEIGHT_IN_NETHER_FORTRESS;
	public static ForgeConfigSpec.IntValue NECROTIC_REAPER_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue DODOMEKI_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue IMP_SPAWN_WEIGHT;
	public static ForgeConfigSpec.IntValue IMP_SPAWN_WEIGHT_IN_CRIMSON_FOREST;
	public static ForgeConfigSpec.IntValue IMP_SPAWN_WEIGHT_IN_BASALT_DELTAS;

	public static ForgeConfigSpec.DoubleValue GHASTLY_SEEKER_SPAWN_CHANCE;
	public static ForgeConfigSpec.DoubleValue CRIMSON_SLAUGHTERER_SPAWN_CHANCE;
	public static ForgeConfigSpec.DoubleValue SAVAGEFANG_SPAWN_CHANCE;

	public static ForgeConfigSpec.ConfigValue<List<? extends String>> SPAWN_BIOME_BLACKLIST;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> SPAWN_DIMENSION_BLACKLIST;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> MOB_REPLACE_BIOME_BLACKLIST;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> MOB_REPLACE_DIMENSION_BLACKLIST;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> SPAWN_OVERWORLD_BIOME_WHITELIST;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> SPAWN_NETHER_BIOME_WHITELIST;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> SPAWN_END_BIOME_WHITELIST;

	public static ForgeConfigSpec.IntValue HEALTH_BOOST_MAX_LEVEL;
	public static ForgeConfigSpec.BooleanValue HEALTH_BOOST_IS_TREASURE;
	public static ForgeConfigSpec.BooleanValue HEALTH_BOOST_IS_TRADEABLE;
	public static ForgeConfigSpec.BooleanValue HEALTH_BOOST_IS_DISCOVERABLE;
	public static ForgeConfigSpec.IntValue WATER_ASPECT_MAX_LEVEL;
	public static ForgeConfigSpec.BooleanValue WATER_ASPECT_IS_TREASURE;
	public static ForgeConfigSpec.BooleanValue WATER_ASPECT_IS_TRADEABLE;
	public static ForgeConfigSpec.BooleanValue WATER_ASPECT_IS_DISCOVERABLE;
	public static ForgeConfigSpec.IntValue ANTI_AIR_MAX_LEVEL;
	public static ForgeConfigSpec.BooleanValue ANTI_AIR_IS_TREASURE;
	public static ForgeConfigSpec.BooleanValue ANTI_AIR_IS_TRADEABLE;
	public static ForgeConfigSpec.BooleanValue ANTI_AIR_IS_DISCOVERABLE;

	public static ForgeConfigSpec.BooleanValue KOBOLD_POTION_BREWING_RECIPES;
	public static ForgeConfigSpec.BooleanValue HEALING_III_POTION_BREWING_RECIPES;
	public static ForgeConfigSpec.BooleanValue HARMING_III_POTION_BREWING_RECIPES;
	public static ForgeConfigSpec.BooleanValue OGRE_POTION_BREWING_RECIPES;
	public static ForgeConfigSpec.BooleanValue VORACITY_POTION_BREWING_RECIPES;
	public static ForgeConfigSpec.BooleanValue ENDER_RAGE_POTION_BREWING_RECIPES;
	public static ForgeConfigSpec.BooleanValue COMBUSTION_POTION_BREWING_RECIPES;
	public static ForgeConfigSpec.BooleanValue SLOWNESS_POTION_BREWING_RECIPES;
	public static ForgeConfigSpec.BooleanValue INVISIBILITY_POTION_BREWING_RECIPES;
	public static ForgeConfigSpec.BooleanValue REGENERATION_POTION_BREWING_RECIPES;
	public static ForgeConfigSpec.BooleanValue BLASTING_BOTTLE_BREWING_RECIPES;
	public static ForgeConfigSpec.BooleanValue LIGHTNING_BOTTLE_BREWING_RECIPES;

	public static ForgeConfigSpec.BooleanValue ADDITIONAL_CHEST_LOOTS;
	public static ForgeConfigSpec.BooleanValue ADDITIONAL_VILLAGER_TRADES;
	public static ForgeConfigSpec.BooleanValue ADDITIONAL_WANDERER_TRADES;

	static
	{
		SERVER_BUILDER.push("entity");

		ENDER_EXECUTOR_REDUCE_DAMAGE = SERVER_BUILDER.define("enableEnderExecutorReduceDamage", true);
		ENDER_EXECUTOR_BEAM_ATTACK = SERVER_BUILDER.define("enableEnderExecutorBeamAttack", true);
		LICH_ATTACK_BABY_TURTLES = SERVER_BUILDER.define("lichAttackBabyTurtles", true);
		LICH_SUMMON_VEX = SERVER_BUILDER.define("lichSummonVex", true);
		OGRE_ATTACK_VILLAGERS = SERVER_BUILDER.define("ogreAttackVillagers", true);
		OGRE_ATTACK_BABY_TURTLES = SERVER_BUILDER.define("ogreAttackBabyTurtles", true);
		OGRE_DESTROY_BLOCKS = SERVER_BUILDER.define("enableOgreDestroyBlocks", true);
		SPIDER_NEST_SUMMON_CAVE_SPIDER = SERVER_BUILDER.define("spiderNestSummonCaveSpider", true);
		MELTY_MONSTER_SET_FIRE = SERVER_BUILDER.define("enableMeltyMonsterSetFire", true);
		KASHA_ATTACK_CHICKENS = SERVER_BUILDER.define("kashaAttackChickens", true);
		KASHA_ATTACK_BABY_HOGLINS = SERVER_BUILDER.define("kashaAttackBabyHoglins", true);
		KASHA_ATTACK_VILLAGERS = SERVER_BUILDER.define("kashaAttackVillagers", true);
		KASHA_ATTACK_BABY_TURTLES = SERVER_BUILDER.define("kashaAttackBabyTurtles", true);
		JACK_FROST_FREEZES_WATER = SERVER_BUILDER.define("enableJackFrostFreezesWater", true);
		JACK_FROST_REGEN = SERVER_BUILDER.define("enableJackFrostRegen", true);
		ARURAUNE_ATTACK_VILLAGERS = SERVER_BUILDER.define("arurauneAttackVillagers", true);
		ARURAUNE_REGEN = SERVER_BUILDER.define("enableArurauneRegen", true);
		REDCAP_ATTACK_VILLAGERS = SERVER_BUILDER.define("redcapAttackVillagers", true);
		REDCAP_ATTACK_BABY_TURTLES = SERVER_BUILDER.define("redcapAttackBabyTurtles", true);
		SLIME_GIRL_REGEN = SERVER_BUILDER.define("enableSlimeGirlRegen", true);
		MAGICAL_SLIME_SPAWNS_ON_SLIME_GIRL_DEATH = SERVER_BUILDER.define("magicalSlimeSpawnsOnSlimeGirlDeath", true);
		MONOLITH_ATTACK_GOLEMS = SERVER_BUILDER.define("monolithAttackGolems", true);
		MONOLITH_ATTACK_VILLAGERS = SERVER_BUILDER.define("monolithAttackVillagers", true);
		MONOLITH_ATTACK_ILLAGERS = SERVER_BUILDER.define("monolithAttackIllagers", true);
		CRIMSON_SLAUGHTERER_ATTACK_ANIMALS = SERVER_BUILDER.define("crimsonSlaughtererAttackAnimals", true);
		CRIMSON_SLAUGHTERER_ATTACK_VILLAGERS = SERVER_BUILDER.define("crimsonSlaughtererAttackVillagers", true);
		DYSSOMNIA_SUMMON_PHANTOM = SERVER_BUILDER.define("dyssomniaSummonPhantom", true);
		SNOW_CANINE_ATTACK_SHEEP = SERVER_BUILDER.define("snowCanineAttackSheep", true);
		SNOW_CANINE_ATTACK_RABBITS = SERVER_BUILDER.define("snowCanineAttackRabbits", true);
		SNOW_CANINE_ATTACK_BABY_TURTLES = SERVER_BUILDER.define("snowCanineAttackBabyTurtles", true);
		SAVAGEFANG_ATTACK_ANIMALS = SERVER_BUILDER.define("savagefangAttackAnimals", true);
		SAVAGEFANG_ATTACK_VILLAGERS = SERVER_BUILDER.define("savagefangAttackVillagers", true);
		SAVAGEFANG_ATTACK_ILLAGERS = SERVER_BUILDER.define("savagefangAttackIllagers", true);
		SAVAGEFANG_ATTACK_DAMAGED_MOBS = SERVER_BUILDER.define("savagefangAttackDamagedMobs", true);
		NECROTIC_REAPER_ATTACK_VILLAGERS = SERVER_BUILDER.define("necroticReaperAttackVillagers", true);
		NECROTIC_REAPER_ATTACK_BABY_TURTLES = SERVER_BUILDER.define("necroticReaperAttackBabyTurtles", true);

		SERVER_BUILDER.push("spawn");

		SERVER_BUILDER.push("replaceChance");

		ZOMBIE_GIRL_REPLACE_CHANCE = SERVER_BUILDER.defineInRange("zombieGirlReplaceChance", 0.15D, 0.0D, 1.0D);
		HUSK_GIRL_REPLACE_CHANCE = SERVER_BUILDER.defineInRange("huskGirlReplaceChance", 0.15D, 0.0D, 1.0D);
		DROWNED_GIRL_REPLACE_CHANCE = SERVER_BUILDER.defineInRange("drownedGirlReplaceChance", 0.15D, 0.0D, 1.0D);
		SKELETON_GIRL_REPLACE_CHANCE = SERVER_BUILDER.defineInRange("skeletonGirlReplaceChance", 0.15D, 0.0D, 1.0D);
		WITHER_SKELETON_GIRL_REPLACE_CHANCE = SERVER_BUILDER.defineInRange("witherSkeletonGirlReplaceChance", 0.15D, 0.0D, 1.0D);
		STRAY_GIRL_REPLACE_CHANCE = SERVER_BUILDER.defineInRange("strayGirlReplaceChance", 0.15D, 0.0D, 1.0D);
		CREEPER_GIRL_REPLACE_CHANCE = SERVER_BUILDER.defineInRange("creeperGirlReplaceChance", 0.15D, 0.0D, 1.0D);
		ENDER_EXECUTOR_REPLACE_CHANCE = SERVER_BUILDER.defineInRange("enderExecutorReplaceChance", 0.03125D, 0.0D, 1.0D);

		SERVER_BUILDER.pop();

		SERVER_BUILDER.push("weight");

		GHOST_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("ghostSpawnWeight", 25, 0, Integer.MAX_VALUE);
		WITHER_GHOST_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("witherGhostSpawnWeight", 12, 0, Integer.MAX_VALUE);
		WITHER_GHOST_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY = SERVER_BUILDER.defineInRange("witherGhostSpawnWeightInSoulSandValley", 8, 0, Integer.MAX_VALUE);
		WITHER_GHOST_SPAWN_WEIGHT_IN_BASALT_DELTAS = SERVER_BUILDER.defineInRange("witherGhostSpawnWeightInBasaltDeltas", 5, 0, Integer.MAX_VALUE);
		KOBOLD_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("koboldSpawnWeight", 20, 0, Integer.MAX_VALUE);
		LICH_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("lichSpawnWeight", 8, 0, Integer.MAX_VALUE);
		OGRE_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("ogreSpawnWeight", 8, 0, Integer.MAX_VALUE);
		SPIDER_NEST_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("spiderNestSpawnWeight", 8, 0, Integer.MAX_VALUE);
		MELTY_MONSTER_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("meltyMonsterSpawnWeight", 15, 0, Integer.MAX_VALUE);
		MELTY_MONSTER_SPAWN_WEIGHT_IN_NETHER = SERVER_BUILDER.defineInRange("meltyMonsterSpawnWeightInNether", 2, 0, Integer.MAX_VALUE);
		MELTY_MONSTER_SPAWN_WEIGHT_IN_END = SERVER_BUILDER.defineInRange("meltyMonsterSpawnWeightInEnd", 0, 0, Integer.MAX_VALUE);
		CURSED_DOLL_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("cursedDollSpawnWeight", 15, 0, Integer.MAX_VALUE);
		JACK_FROST_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("jackFrostSpawnWeight", 10, 0, Integer.MAX_VALUE);
		HORNET_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("hornetSpawnWeight", 10, 0, Integer.MAX_VALUE);
		DULLAHAN_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("dullahanSpawnWeight", 10, 0, Integer.MAX_VALUE);
		BANSHEE_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("bansheeSpawnWeight", 15, 0, Integer.MAX_VALUE);
		ARURAUNE_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("arurauneSpawnWeight", 10, 0, Integer.MAX_VALUE);
		CATOBLEPAS_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("catoblepasSpawnWeight", 15, 0, Integer.MAX_VALUE);
		SCORPION_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("scorpionSpawnWeight", 15, 0, Integer.MAX_VALUE);
		KASHA_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("kashaSpawnWeight", 5, 0, Integer.MAX_VALUE);
		KASHA_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY = SERVER_BUILDER.defineInRange("kashaSpawnWeightInSoulSandValley", 3, 0, Integer.MAX_VALUE);
		KASHA_SPAWN_WEIGHT_IN_BASALT_DELTAS = SERVER_BUILDER.defineInRange("kashaSpawnWeightInBasaltDeltas", 2, 0, Integer.MAX_VALUE);
		DOGU_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("doguSpawnWeight", 10, 0, Integer.MAX_VALUE);
		GHASTLY_SEEKER_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("ghastlySeekerSpawnWeight", 7, 0, Integer.MAX_VALUE);
		GHASTLY_SEEKER_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY = SERVER_BUILDER.defineInRange("ghastlySeekerSpawnWeightInSoulSandValley", 4, 0, Integer.MAX_VALUE);
		GHASTLY_SEEKER_SPAWN_WEIGHT_IN_BASALT_DELTAS = SERVER_BUILDER.defineInRange("ghastlySeekerSpawnWeightInBasaltDeltas", 2, 0, Integer.MAX_VALUE);
		REDCAP_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("redcapSpawnWeight", 15, 0, Integer.MAX_VALUE);
		SLIME_GIRL_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("slimeGirlSpawnWeight", 10, 0, Integer.MAX_VALUE);
		MONOLITH_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("monolithSpawnWeight", 3, 0, Integer.MAX_VALUE);
		MONOLITH_SPAWN_WEIGHT_IN_END_CITY = SERVER_BUILDER.defineInRange("monolithSpawnWeightInEndCity", 40, 0, Integer.MAX_VALUE);
		CRIMSON_SLAUGHTERER_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("crimsonSlaughtererSpawnWeight", 2, 0, Integer.MAX_VALUE);
		DYSSOMNIA_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("dyssomniaSpawnWeight", 5, 0, Integer.MAX_VALUE);
		SNOW_CANINE_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("snowCanineSpawnWeight", 15, 0, Integer.MAX_VALUE);
		HARPY_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("harpySpawnWeight", 15, 0, Integer.MAX_VALUE);
		SAVAGEFANG_SPAWN_WEIGHT_IN_SWAMP = SERVER_BUILDER.defineInRange("savagefangSpawnWeightInSwamp", 10, 0, Integer.MAX_VALUE);
		SAVAGEFANG_SPAWN_WEIGHT_IN_JUNGLE = SERVER_BUILDER.defineInRange("savagefangSpawnWeightInJungle", 20, 0, Integer.MAX_VALUE);
		FORTRESS_KEEPER_SPAWN_WEIGHT_IN_NETHER_FORTRESS = SERVER_BUILDER.defineInRange("fortressKeeperSpawnWeightInNetherFortress", 5, 0, Integer.MAX_VALUE);
		NECROTIC_REAPER_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("necroticReaperSpawnWeight", 8, 0, Integer.MAX_VALUE);
		DODOMEKI_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("dodomekiSpawnWeight", 15, 0, Integer.MAX_VALUE);
		IMP_SPAWN_WEIGHT = SERVER_BUILDER.defineInRange("impSpawnWeight", 5, 0, Integer.MAX_VALUE);
		IMP_SPAWN_WEIGHT_IN_CRIMSON_FOREST = SERVER_BUILDER.defineInRange("impSpawnWeightInCrimsonForest", 2, 0, Integer.MAX_VALUE);
		IMP_SPAWN_WEIGHT_IN_BASALT_DELTAS = SERVER_BUILDER.defineInRange("impSpawnWeightInBasaltDeltas", 2, 0, Integer.MAX_VALUE);

		SERVER_BUILDER.pop();

		SERVER_BUILDER.push("chance");

		GHASTLY_SEEKER_SPAWN_CHANCE = SERVER_BUILDER.defineInRange("ghastlySeekerSpawnChance", 0.05D, 0.0D, 1.0D);
		CRIMSON_SLAUGHTERER_SPAWN_CHANCE = SERVER_BUILDER.defineInRange("crimsonSlaughtererSpawnChance", 0.5D, 0.0D, 1.0D);
		SAVAGEFANG_SPAWN_CHANCE = SERVER_BUILDER.defineInRange("savagefangSpawnChance", 0.8D, 0.0D, 1.0D);

		SERVER_BUILDER.pop();

		SPAWN_BIOME_BLACKLIST = SERVER_BUILDER.defineList("spawnBiomeBlacklist", Lists.newArrayList(
				"minecraft:mushroom_fields",
				"biomesoplenty:rainbow_hills",
				"biomesoplenty:crystalline_chasm",
				"cinderscapes:luminous_grove",
				"infernalexp:glowstone_canyon"), (s) -> true);
		SPAWN_DIMENSION_BLACKLIST = SERVER_BUILDER.defineList("spawnDimentionBlacklist", Collections.singletonList(""), (s) -> true);
		MOB_REPLACE_BIOME_BLACKLIST = SERVER_BUILDER.defineList("mobReplaceBiomeBlacklist", Collections.singletonList(""), (s) -> true);
		MOB_REPLACE_DIMENSION_BLACKLIST = SERVER_BUILDER.defineList("mobReplaceDimentionBlacklist", Collections.singletonList(""), (s) -> true);
		SPAWN_OVERWORLD_BIOME_WHITELIST = SERVER_BUILDER.defineList("spawnOverworldBiomeWhitelist", Collections.singletonList(""), (s) -> true);
		SPAWN_NETHER_BIOME_WHITELIST = SERVER_BUILDER.defineList("spawnNetherBiomeWhitelist", Collections.singletonList(""), (s) -> true);
		SPAWN_END_BIOME_WHITELIST = SERVER_BUILDER.defineList("spawnEndBiomeWhitelist", Collections.singletonList(""), (s) -> true);

		SERVER_BUILDER.pop();

		SERVER_BUILDER.pop();

		SERVER_BUILDER.push("enchantment");

		HEALTH_BOOST_MAX_LEVEL = SERVER_BUILDER.defineInRange("healthBoostMaxLevel", 5, 1, 10);
		HEALTH_BOOST_IS_TREASURE = SERVER_BUILDER.define("healthBoostIsTreasure", true);
		HEALTH_BOOST_IS_TRADEABLE = SERVER_BUILDER.define("healthBoostIsTradeable", true);
		HEALTH_BOOST_IS_DISCOVERABLE = SERVER_BUILDER.define("healthBoostIsDiscoverable", true);
		WATER_ASPECT_MAX_LEVEL = SERVER_BUILDER.defineInRange("waterAspectMaxLevel", 5, 1, 10);
		WATER_ASPECT_IS_TREASURE = SERVER_BUILDER.define("waterAspectIsTreasure", false);
		WATER_ASPECT_IS_TRADEABLE = SERVER_BUILDER.define("waterAspectIsTradeable", true);
		WATER_ASPECT_IS_DISCOVERABLE = SERVER_BUILDER.define("waterAspectIsDiscoverable", true);
		ANTI_AIR_MAX_LEVEL = SERVER_BUILDER.defineInRange("antiAirMaxLevel", 5, 1, 10);
		ANTI_AIR_IS_TREASURE = SERVER_BUILDER.define("antiAirIsTreasure", false);
		ANTI_AIR_IS_TRADEABLE = SERVER_BUILDER.define("antiAirIsTradeable", true);
		ANTI_AIR_IS_DISCOVERABLE = SERVER_BUILDER.define("antiAirIsDiscoverable", true);

		SERVER_BUILDER.pop();

		SERVER_BUILDER.push("potion");

		KOBOLD_POTION_BREWING_RECIPES = SERVER_BUILDER.define("enableKoboldPotionBrewingRecipes", true);
		HEALING_III_POTION_BREWING_RECIPES = SERVER_BUILDER.define("enableHealingIIIPotionBrewingRecipes", true);
		HARMING_III_POTION_BREWING_RECIPES = SERVER_BUILDER.define("enableHarmingIIIPotionBrewingRecipes", true);
		OGRE_POTION_BREWING_RECIPES = SERVER_BUILDER.define("enableOgrePotionBrewingRecipes", true);
		VORACITY_POTION_BREWING_RECIPES = SERVER_BUILDER.define("enableVoracityPotionBrewingRecipes", true);
		ENDER_RAGE_POTION_BREWING_RECIPES = SERVER_BUILDER.define("enableEnderRagePotionBrewingRecipes", true);
		COMBUSTION_POTION_BREWING_RECIPES = SERVER_BUILDER.define("enableCombustionPotionBrewingRecipes", true);
		SLOWNESS_POTION_BREWING_RECIPES = SERVER_BUILDER.define("enableSlownessPotionBrewingRecipes", true);
		INVISIBILITY_POTION_BREWING_RECIPES = SERVER_BUILDER.define("enableInvisibilityPotionBrewingRecipes", true);
		REGENERATION_POTION_BREWING_RECIPES = SERVER_BUILDER.define("enableRegenerationPotionBrewingRecipes", true);
		BLASTING_BOTTLE_BREWING_RECIPES = SERVER_BUILDER.define("enableBlastingBottleBrewingRecipes", true);
		LIGHTNING_BOTTLE_BREWING_RECIPES = SERVER_BUILDER.define("enableLightningBottleBrewingRecipes", true);

		SERVER_BUILDER.pop();

		SERVER_BUILDER.push("world");

		ADDITIONAL_CHEST_LOOTS = SERVER_BUILDER.define("additionalChestLoots", true);
		ADDITIONAL_VILLAGER_TRADES = SERVER_BUILDER.define("additionalVillagerTrades", true);
		ADDITIONAL_WANDERER_TRADES = SERVER_BUILDER.define("additionalWandererTrades", true);

		SERVER_BUILDER.pop();

		SERVER_CONFIG = SERVER_BUILDER.build();
	}

	public static class cachedServer
	{
		public static boolean ENDER_EXECUTOR_REDUCE_DAMAGE;
		public static boolean ENDER_EXECUTOR_BEAM_ATTACK;
		public static boolean LICH_ATTACK_BABY_TURTLES;
		public static boolean LICH_SUMMON_VEX;
		public static boolean OGRE_ATTACK_VILLAGERS;
		public static boolean OGRE_ATTACK_BABY_TURTLES;
		public static boolean OGRE_DESTROY_BLOCKS;
		public static boolean SPIDER_NEST_SUMMON_CAVE_SPIDER;
		public static boolean MELTY_MONSTER_SET_FIRE;
		public static boolean KASHA_ATTACK_CHICKENS;
		public static boolean KASHA_ATTACK_BABY_HOGLINS;
		public static boolean KASHA_ATTACK_VILLAGERS;
		public static boolean KASHA_ATTACK_BABY_TURTLES;
		public static boolean JACK_FROST_FREEZES_WATER;
		public static boolean JACK_FROST_REGEN;
		public static boolean ARURAUNE_ATTACK_VILLAGERS;
		public static boolean ARURAUNE_REGEN;
		public static boolean REDCAP_ATTACK_VILLAGERS;
		public static boolean REDCAP_ATTACK_BABY_TURTLES;
		public static boolean SLIME_GIRL_REGEN;
		public static boolean MAGICAL_SLIME_SPAWNS_ON_SLIME_GIRL_DEATH;
		public static boolean MONOLITH_ATTACK_GOLEMS;
		public static boolean MONOLITH_ATTACK_VILLAGERS;
		public static boolean MONOLITH_ATTACK_ILLAGERS;
		public static boolean CRIMSON_SLAUGHTERER_ATTACK_ANIMALS;
		public static boolean CRIMSON_SLAUGHTERER_ATTACK_VILLAGERS;
		public static boolean DYSSOMNIA_SUMMON_PHANTOM;
		public static boolean SNOW_CANINE_ATTACK_SHEEP;
		public static boolean SNOW_CANINE_ATTACK_RABBITS;
		public static boolean SNOW_CANINE_ATTACK_BABY_TURTLES;
		public static boolean SAVAGEFANG_ATTACK_ANIMALS;
		public static boolean SAVAGEFANG_ATTACK_VILLAGERS;
		public static boolean SAVAGEFANG_ATTACK_ILLAGERS;
		public static boolean SAVAGEFANG_ATTACK_DAMAGED_MOBS;
		public static boolean NECROTIC_REAPER_ATTACK_VILLAGERS;
		public static boolean NECROTIC_REAPER_ATTACK_BABY_TURTLES;

		public static double ZOMBIE_GIRL_REPLACE_CHANCE;
		public static double HUSK_GIRL_REPLACE_CHANCE;
		public static double DROWNED_GIRL_REPLACE_CHANCE;
		public static double SKELETON_GIRL_REPLACE_CHANCE;
		public static double WITHER_SKELETON_GIRL_REPLACE_CHANCE;
		public static double STRAY_GIRL_REPLACE_CHANCE;
		public static double CREEPER_GIRL_REPLACE_CHANCE;
		public static double ENDER_EXECUTOR_REPLACE_CHANCE;

		public static int GHOST_SPAWN_WEIGHT;
		public static int WITHER_GHOST_SPAWN_WEIGHT;
		public static int WITHER_GHOST_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY;
		public static int WITHER_GHOST_SPAWN_WEIGHT_IN_BASALT_DELTAS;
		public static int KOBOLD_SPAWN_WEIGHT;
		public static int LICH_SPAWN_WEIGHT;
		public static int OGRE_SPAWN_WEIGHT;
		public static int SPIDER_NEST_SPAWN_WEIGHT;
		public static int MELTY_MONSTER_SPAWN_WEIGHT;
		public static int MELTY_MONSTER_SPAWN_WEIGHT_IN_NETHER;
		public static int MELTY_MONSTER_SPAWN_WEIGHT_IN_END;
		public static int CURSED_DOLL_SPAWN_WEIGHT;
		public static int JACK_FROST_SPAWN_WEIGHT;
		public static int HORNET_SPAWN_WEIGHT;
		public static int DULLAHAN_SPAWN_WEIGHT;
		public static int BANSHEE_SPAWN_WEIGHT;
		public static int ARURAUNE_SPAWN_WEIGHT;
		public static int CATOBLEPAS_SPAWN_WEIGHT;
		public static int SCORPION_SPAWN_WEIGHT;
		public static int KASHA_SPAWN_WEIGHT;
		public static int KASHA_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY;
		public static int KASHA_SPAWN_WEIGHT_IN_BASALT_DELTAS;
		public static int DOGU_SPAWN_WEIGHT;
		public static int GHASTLY_SEEKER_SPAWN_WEIGHT;
		public static int GHASTLY_SEEKER_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY;
		public static int GHASTLY_SEEKER_SPAWN_WEIGHT_IN_BASALT_DELTAS;
		public static int REDCAP_SPAWN_WEIGHT;
		public static int SLIME_GIRL_SPAWN_WEIGHT;
		public static int MONOLITH_SPAWN_WEIGHT;
		public static int MONOLITH_SPAWN_WEIGHT_IN_END_CITY;
		public static int CRIMSON_SLAUGHTERER_SPAWN_WEIGHT;
		public static int DYSSOMNIA_SPAWN_WEIGHT;
		public static int SNOW_CANINE_SPAWN_WEIGHT;
		public static int HARPY_SPAWN_WEIGHT;
		public static int SAVAGEFANG_SPAWN_WEIGHT_IN_SWAMP;
		public static int SAVAGEFANG_SPAWN_WEIGHT_IN_JUNGLE;
		public static int FORTRESS_KEEPER_SPAWN_WEIGHT_IN_NETHER_FORTRESS;
		public static int NECROTIC_REAPER_SPAWN_WEIGHT;
		public static int DODOMEKI_SPAWN_WEIGHT;
		public static int IMP_SPAWN_WEIGHT;
		public static int IMP_SPAWN_WEIGHT_IN_CRIMSON_FOREST;
		public static int IMP_SPAWN_WEIGHT_IN_BASALT_DELTAS;

		public static double GHASTLY_SEEKER_SPAWN_CHANCE;
		public static double CRIMSON_SLAUGHTERER_SPAWN_CHANCE;
		public static double SAVAGEFANG_SPAWN_CHANCE;

		public static List<? extends String> SPAWN_BIOME_BLACKLIST;
		public static List<? extends String> SPAWN_DIMENSION_BLACKLIST;
		public static List<? extends String> MOB_REPLACE_BIOME_BLACKLIST;
		public static List<? extends String> MOB_REPLACE_DIMENSION_BLACKLIST;
		public static List<? extends String> SPAWN_OVERWORLD_BIOME_WHITELIST;
		public static List<? extends String> SPAWN_NETHER_BIOME_WHITELIST;
		public static List<? extends String> SPAWN_END_BIOME_WHITELIST;

		public static int HEALTH_BOOST_MAX_LEVEL;
		public static boolean HEALTH_BOOST_IS_TREASURE;
		public static boolean HEALTH_BOOST_IS_TRADEABLE;
		public static boolean HEALTH_BOOST_IS_DISCOVERABLE;
		public static int WATER_ASPECT_MAX_LEVEL;
		public static boolean WATER_ASPECT_IS_TREASURE;
		public static boolean WATER_ASPECT_IS_TRADEABLE;
		public static boolean WATER_ASPECT_IS_DISCOVERABLE;
		public static int ANTI_AIR_MAX_LEVEL;
		public static boolean ANTI_AIR_IS_TREASURE;
		public static boolean ANTI_AIR_IS_TRADEABLE;
		public static boolean ANTI_AIR_IS_DISCOVERABLE;

		public static boolean KOBOLD_POTION_BREWING_RECIPES;
		public static boolean HEALING_III_POTION_BREWING_RECIPES;
		public static boolean HARMING_III_POTION_BREWING_RECIPES;
		public static boolean OGRE_POTION_BREWING_RECIPES;
		public static boolean VORACITY_POTION_BREWING_RECIPES;
		public static boolean ENDER_RAGE_POTION_BREWING_RECIPES;
		public static boolean COMBUSTION_POTION_BREWING_RECIPES;
		public static boolean SLOWNESS_POTION_BREWING_RECIPES;
		public static boolean INVISIBILITY_POTION_BREWING_RECIPES;
		public static boolean REGENERATION_POTION_BREWING_RECIPES;
		public static boolean BLASTING_BOTTLE_BREWING_RECIPES;
		public static boolean LIGHTNING_BOTTLE_BREWING_RECIPES;

		public static boolean ADDITIONAL_CHEST_LOOTS;
		public static boolean ADDITIONAL_VILLAGER_TRADES;
		public static boolean ADDITIONAL_WANDERER_TRADES;

		public static void refresh()
		{
			ENDER_EXECUTOR_REDUCE_DAMAGE = ModConfigs.ENDER_EXECUTOR_REDUCE_DAMAGE.get();
			ENDER_EXECUTOR_BEAM_ATTACK = ModConfigs.ENDER_EXECUTOR_BEAM_ATTACK.get();
			LICH_ATTACK_BABY_TURTLES = ModConfigs.LICH_ATTACK_BABY_TURTLES.get();
			LICH_SUMMON_VEX = ModConfigs.LICH_SUMMON_VEX.get();
			OGRE_ATTACK_VILLAGERS = ModConfigs.OGRE_ATTACK_VILLAGERS.get();
			OGRE_ATTACK_BABY_TURTLES = ModConfigs.OGRE_ATTACK_BABY_TURTLES.get();
			OGRE_DESTROY_BLOCKS = ModConfigs.OGRE_DESTROY_BLOCKS.get();
			SPIDER_NEST_SUMMON_CAVE_SPIDER = ModConfigs.SPIDER_NEST_SUMMON_CAVE_SPIDER.get();
			MELTY_MONSTER_SET_FIRE = ModConfigs.MELTY_MONSTER_SET_FIRE.get();
			KASHA_ATTACK_CHICKENS = ModConfigs.KASHA_ATTACK_CHICKENS.get();
			KASHA_ATTACK_BABY_HOGLINS = ModConfigs.KASHA_ATTACK_BABY_HOGLINS.get();
			KASHA_ATTACK_VILLAGERS = ModConfigs.KASHA_ATTACK_VILLAGERS.get();
			KASHA_ATTACK_BABY_TURTLES = ModConfigs.KASHA_ATTACK_BABY_TURTLES.get();
			JACK_FROST_FREEZES_WATER = ModConfigs.JACK_FROST_FREEZES_WATER.get();
			JACK_FROST_REGEN = ModConfigs.JACK_FROST_REGEN.get();
			ARURAUNE_ATTACK_VILLAGERS = ModConfigs.ARURAUNE_ATTACK_VILLAGERS.get();
			ARURAUNE_REGEN = ModConfigs.ARURAUNE_REGEN.get();
			REDCAP_ATTACK_VILLAGERS = ModConfigs.REDCAP_ATTACK_VILLAGERS.get();
			REDCAP_ATTACK_BABY_TURTLES = ModConfigs.REDCAP_ATTACK_BABY_TURTLES.get();
			SLIME_GIRL_REGEN = ModConfigs.SLIME_GIRL_REGEN.get();
			MAGICAL_SLIME_SPAWNS_ON_SLIME_GIRL_DEATH = ModConfigs.MAGICAL_SLIME_SPAWNS_ON_SLIME_GIRL_DEATH.get();
			MONOLITH_ATTACK_GOLEMS = ModConfigs.MONOLITH_ATTACK_GOLEMS.get();
			MONOLITH_ATTACK_VILLAGERS = ModConfigs.MONOLITH_ATTACK_VILLAGERS.get();
			MONOLITH_ATTACK_ILLAGERS = ModConfigs.MONOLITH_ATTACK_ILLAGERS.get();
			CRIMSON_SLAUGHTERER_ATTACK_ANIMALS = ModConfigs.CRIMSON_SLAUGHTERER_ATTACK_ANIMALS.get();
			CRIMSON_SLAUGHTERER_ATTACK_VILLAGERS = ModConfigs.CRIMSON_SLAUGHTERER_ATTACK_VILLAGERS.get();
			DYSSOMNIA_SUMMON_PHANTOM = ModConfigs.DYSSOMNIA_SUMMON_PHANTOM.get();
			SNOW_CANINE_ATTACK_SHEEP = ModConfigs.SNOW_CANINE_ATTACK_SHEEP.get();
			SNOW_CANINE_ATTACK_RABBITS = ModConfigs.SNOW_CANINE_ATTACK_RABBITS.get();
			SNOW_CANINE_ATTACK_BABY_TURTLES = ModConfigs.SNOW_CANINE_ATTACK_BABY_TURTLES.get();
			SAVAGEFANG_ATTACK_ANIMALS = ModConfigs.SAVAGEFANG_ATTACK_ANIMALS.get();
			SAVAGEFANG_ATTACK_VILLAGERS = ModConfigs.SAVAGEFANG_ATTACK_VILLAGERS.get();
			SAVAGEFANG_ATTACK_ILLAGERS = ModConfigs.SAVAGEFANG_ATTACK_ILLAGERS.get();
			SAVAGEFANG_ATTACK_DAMAGED_MOBS = ModConfigs.SAVAGEFANG_ATTACK_DAMAGED_MOBS.get();
			NECROTIC_REAPER_ATTACK_VILLAGERS = ModConfigs.NECROTIC_REAPER_ATTACK_VILLAGERS.get();
			NECROTIC_REAPER_ATTACK_BABY_TURTLES = ModConfigs.NECROTIC_REAPER_ATTACK_BABY_TURTLES.get();

			ZOMBIE_GIRL_REPLACE_CHANCE = ModConfigs.ZOMBIE_GIRL_REPLACE_CHANCE.get();
			HUSK_GIRL_REPLACE_CHANCE = ModConfigs.HUSK_GIRL_REPLACE_CHANCE.get();
			DROWNED_GIRL_REPLACE_CHANCE = ModConfigs.DROWNED_GIRL_REPLACE_CHANCE.get();
			SKELETON_GIRL_REPLACE_CHANCE = ModConfigs.SKELETON_GIRL_REPLACE_CHANCE.get();
			WITHER_SKELETON_GIRL_REPLACE_CHANCE = ModConfigs.WITHER_SKELETON_GIRL_REPLACE_CHANCE.get();
			STRAY_GIRL_REPLACE_CHANCE = ModConfigs.STRAY_GIRL_REPLACE_CHANCE.get();
			CREEPER_GIRL_REPLACE_CHANCE = ModConfigs.CREEPER_GIRL_REPLACE_CHANCE.get();
			ENDER_EXECUTOR_REPLACE_CHANCE = ModConfigs.ENDER_EXECUTOR_REPLACE_CHANCE.get();

			GHOST_SPAWN_WEIGHT = ModConfigs.GHOST_SPAWN_WEIGHT.get();
			WITHER_GHOST_SPAWN_WEIGHT = ModConfigs.WITHER_GHOST_SPAWN_WEIGHT.get();
			WITHER_GHOST_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY = ModConfigs.WITHER_GHOST_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY.get();
			WITHER_GHOST_SPAWN_WEIGHT_IN_BASALT_DELTAS = ModConfigs.WITHER_GHOST_SPAWN_WEIGHT_IN_BASALT_DELTAS.get();
			KOBOLD_SPAWN_WEIGHT = ModConfigs.KOBOLD_SPAWN_WEIGHT.get();
			LICH_SPAWN_WEIGHT = ModConfigs.LICH_SPAWN_WEIGHT.get();
			OGRE_SPAWN_WEIGHT = ModConfigs.OGRE_SPAWN_WEIGHT.get();
			SPIDER_NEST_SPAWN_WEIGHT = ModConfigs.SPIDER_NEST_SPAWN_WEIGHT.get();
			MELTY_MONSTER_SPAWN_WEIGHT = ModConfigs.MELTY_MONSTER_SPAWN_WEIGHT.get();
			MELTY_MONSTER_SPAWN_WEIGHT_IN_NETHER = ModConfigs.MELTY_MONSTER_SPAWN_WEIGHT_IN_NETHER.get();
			MELTY_MONSTER_SPAWN_WEIGHT_IN_END = ModConfigs.MELTY_MONSTER_SPAWN_WEIGHT_IN_END.get();
			CURSED_DOLL_SPAWN_WEIGHT = ModConfigs.CURSED_DOLL_SPAWN_WEIGHT.get();
			JACK_FROST_SPAWN_WEIGHT = ModConfigs.JACK_FROST_SPAWN_WEIGHT.get();
			HORNET_SPAWN_WEIGHT = ModConfigs.HORNET_SPAWN_WEIGHT.get();
			DULLAHAN_SPAWN_WEIGHT = ModConfigs.DULLAHAN_SPAWN_WEIGHT.get();
			BANSHEE_SPAWN_WEIGHT = ModConfigs.BANSHEE_SPAWN_WEIGHT.get();
			ARURAUNE_SPAWN_WEIGHT = ModConfigs.ARURAUNE_SPAWN_WEIGHT.get();
			CATOBLEPAS_SPAWN_WEIGHT = ModConfigs.CATOBLEPAS_SPAWN_WEIGHT.get();
			SCORPION_SPAWN_WEIGHT = ModConfigs.SCORPION_SPAWN_WEIGHT.get();
			KASHA_SPAWN_WEIGHT = ModConfigs.KASHA_SPAWN_WEIGHT.get();
			KASHA_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY = ModConfigs.KASHA_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY.get();
			KASHA_SPAWN_WEIGHT_IN_BASALT_DELTAS = ModConfigs.KASHA_SPAWN_WEIGHT_IN_BASALT_DELTAS.get();
			DOGU_SPAWN_WEIGHT = ModConfigs.DOGU_SPAWN_WEIGHT.get();
			GHASTLY_SEEKER_SPAWN_WEIGHT = ModConfigs.GHASTLY_SEEKER_SPAWN_WEIGHT.get();
			GHASTLY_SEEKER_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY = ModConfigs.GHASTLY_SEEKER_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY.get();
			GHASTLY_SEEKER_SPAWN_WEIGHT_IN_BASALT_DELTAS = ModConfigs.GHASTLY_SEEKER_SPAWN_WEIGHT_IN_BASALT_DELTAS.get();
			REDCAP_SPAWN_WEIGHT = ModConfigs.REDCAP_SPAWN_WEIGHT.get();
			SLIME_GIRL_SPAWN_WEIGHT = ModConfigs.SLIME_GIRL_SPAWN_WEIGHT.get();
			MONOLITH_SPAWN_WEIGHT = ModConfigs.MONOLITH_SPAWN_WEIGHT.get();
			MONOLITH_SPAWN_WEIGHT_IN_END_CITY = ModConfigs.MONOLITH_SPAWN_WEIGHT_IN_END_CITY.get();
			CRIMSON_SLAUGHTERER_SPAWN_WEIGHT = ModConfigs.CRIMSON_SLAUGHTERER_SPAWN_WEIGHT.get();
			DYSSOMNIA_SPAWN_WEIGHT = ModConfigs.DYSSOMNIA_SPAWN_WEIGHT.get();
			SNOW_CANINE_SPAWN_WEIGHT = ModConfigs.SNOW_CANINE_SPAWN_WEIGHT.get();
			HARPY_SPAWN_WEIGHT = ModConfigs.HARPY_SPAWN_WEIGHT.get();
			SAVAGEFANG_SPAWN_WEIGHT_IN_SWAMP = ModConfigs.SAVAGEFANG_SPAWN_WEIGHT_IN_SWAMP.get();
			SAVAGEFANG_SPAWN_WEIGHT_IN_JUNGLE = ModConfigs.SAVAGEFANG_SPAWN_WEIGHT_IN_JUNGLE.get();
			FORTRESS_KEEPER_SPAWN_WEIGHT_IN_NETHER_FORTRESS = ModConfigs.FORTRESS_KEEPER_SPAWN_WEIGHT_IN_NETHER_FORTRESS.get();
			NECROTIC_REAPER_SPAWN_WEIGHT = ModConfigs.NECROTIC_REAPER_SPAWN_WEIGHT.get();
			DODOMEKI_SPAWN_WEIGHT = ModConfigs.DODOMEKI_SPAWN_WEIGHT.get();
			IMP_SPAWN_WEIGHT = ModConfigs.IMP_SPAWN_WEIGHT.get();
			IMP_SPAWN_WEIGHT_IN_CRIMSON_FOREST = ModConfigs.IMP_SPAWN_WEIGHT_IN_CRIMSON_FOREST.get();
			IMP_SPAWN_WEIGHT_IN_BASALT_DELTAS = ModConfigs.IMP_SPAWN_WEIGHT_IN_BASALT_DELTAS.get();

			GHASTLY_SEEKER_SPAWN_CHANCE = ModConfigs.GHASTLY_SEEKER_SPAWN_CHANCE.get();
			CRIMSON_SLAUGHTERER_SPAWN_CHANCE = ModConfigs.CRIMSON_SLAUGHTERER_SPAWN_CHANCE.get();
			SAVAGEFANG_SPAWN_CHANCE = ModConfigs.SAVAGEFANG_SPAWN_CHANCE.get();

			SPAWN_BIOME_BLACKLIST = ModConfigs.SPAWN_BIOME_BLACKLIST.get();
			SPAWN_DIMENSION_BLACKLIST = ModConfigs.SPAWN_DIMENSION_BLACKLIST.get();
			MOB_REPLACE_BIOME_BLACKLIST = ModConfigs.MOB_REPLACE_BIOME_BLACKLIST.get();
			MOB_REPLACE_DIMENSION_BLACKLIST = ModConfigs.MOB_REPLACE_DIMENSION_BLACKLIST.get();
			SPAWN_OVERWORLD_BIOME_WHITELIST = ModConfigs.SPAWN_OVERWORLD_BIOME_WHITELIST.get();
			SPAWN_NETHER_BIOME_WHITELIST = ModConfigs.SPAWN_NETHER_BIOME_WHITELIST.get();
			SPAWN_END_BIOME_WHITELIST = ModConfigs.SPAWN_END_BIOME_WHITELIST.get();

			HEALTH_BOOST_MAX_LEVEL = ModConfigs.HEALTH_BOOST_MAX_LEVEL.get();
			HEALTH_BOOST_IS_TREASURE = ModConfigs.HEALTH_BOOST_IS_TREASURE.get();
			HEALTH_BOOST_IS_TRADEABLE = ModConfigs.HEALTH_BOOST_IS_TRADEABLE.get();
			HEALTH_BOOST_IS_DISCOVERABLE = ModConfigs.HEALTH_BOOST_IS_DISCOVERABLE.get();
			WATER_ASPECT_MAX_LEVEL = ModConfigs.WATER_ASPECT_MAX_LEVEL.get();
			WATER_ASPECT_IS_TREASURE = ModConfigs.WATER_ASPECT_IS_TREASURE.get();
			WATER_ASPECT_IS_TRADEABLE = ModConfigs.WATER_ASPECT_IS_TRADEABLE.get();
			WATER_ASPECT_IS_DISCOVERABLE = ModConfigs.WATER_ASPECT_IS_DISCOVERABLE.get();
			ANTI_AIR_MAX_LEVEL = ModConfigs.ANTI_AIR_MAX_LEVEL.get();
			ANTI_AIR_IS_TREASURE = ModConfigs.ANTI_AIR_IS_TREASURE.get();
			ANTI_AIR_IS_TRADEABLE = ModConfigs.ANTI_AIR_IS_TRADEABLE.get();
			ANTI_AIR_IS_DISCOVERABLE = ModConfigs.ANTI_AIR_IS_DISCOVERABLE.get();

			KOBOLD_POTION_BREWING_RECIPES = ModConfigs.KOBOLD_POTION_BREWING_RECIPES.get();
			HEALING_III_POTION_BREWING_RECIPES = ModConfigs.HEALING_III_POTION_BREWING_RECIPES.get();
			HARMING_III_POTION_BREWING_RECIPES = ModConfigs.HARMING_III_POTION_BREWING_RECIPES.get();
			OGRE_POTION_BREWING_RECIPES = ModConfigs.OGRE_POTION_BREWING_RECIPES.get();
			VORACITY_POTION_BREWING_RECIPES = ModConfigs.VORACITY_POTION_BREWING_RECIPES.get();
			ENDER_RAGE_POTION_BREWING_RECIPES = ModConfigs.ENDER_RAGE_POTION_BREWING_RECIPES.get();
			COMBUSTION_POTION_BREWING_RECIPES = ModConfigs.COMBUSTION_POTION_BREWING_RECIPES.get();
			SLOWNESS_POTION_BREWING_RECIPES = ModConfigs.SLOWNESS_POTION_BREWING_RECIPES.get();
			INVISIBILITY_POTION_BREWING_RECIPES = ModConfigs.INVISIBILITY_POTION_BREWING_RECIPES.get();
			REGENERATION_POTION_BREWING_RECIPES = ModConfigs.REGENERATION_POTION_BREWING_RECIPES.get();
			BLASTING_BOTTLE_BREWING_RECIPES = ModConfigs.BLASTING_BOTTLE_BREWING_RECIPES.get();
			LIGHTNING_BOTTLE_BREWING_RECIPES = ModConfigs.LIGHTNING_BOTTLE_BREWING_RECIPES.get();

			ADDITIONAL_CHEST_LOOTS = ModConfigs.ADDITIONAL_CHEST_LOOTS.get();
			ADDITIONAL_VILLAGER_TRADES = ModConfigs.ADDITIONAL_VILLAGER_TRADES.get();
			ADDITIONAL_WANDERER_TRADES = ModConfigs.ADDITIONAL_WANDERER_TRADES.get();
		}
	}

	@SubscribeEvent
	public static void loadConfig(final ModConfigEvent event)
	{
		if (event.getConfig().getSpec() == SERVER_CONFIG)
		{
			cachedServer.refresh();
		}
	}
}