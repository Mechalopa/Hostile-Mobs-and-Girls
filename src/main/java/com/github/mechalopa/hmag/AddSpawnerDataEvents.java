package com.github.mechalopa.hmag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.util.ModUtils;
import com.google.common.collect.ImmutableMap;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.NetherFortressFeature;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AddSpawnerDataEvents
{
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onBiomeLoad(BiomeLoadingEvent event)
	{
		if (event.getName() != null && !ModUtils.checkList(event.getName(), ModConfigs.cachedServer.SPAWN_BIOME_BLACKLIST))
		{
			ResourceKey<Biome> biomeKey = ResourceKey.create(ForgeRegistries.Keys.BIOMES, event.getName());

			if (biomeKey != null)
			{
				boolean isVoid = BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.VOID);

				if ((BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.OVERWORLD) && !isVoid) || ModUtils.checkList(event.getName(), ModConfigs.cachedServer.SPAWN_OVERWORLD_BIOME_WHITELIST))
				{
					if (ModConfigs.cachedServer.GHOST_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.GHOST.get(), ModConfigs.cachedServer.GHOST_SPAWN_WEIGHT, 4, 4));
					if (ModConfigs.cachedServer.KOBOLD_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.KOBOLD.get(), ModConfigs.cachedServer.KOBOLD_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.LICH_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.LICH.get(), ModConfigs.cachedServer.LICH_SPAWN_WEIGHT, 1, 1));
					if (ModConfigs.cachedServer.OGRE_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.OGRE.get(), ModConfigs.cachedServer.OGRE_SPAWN_WEIGHT, 1, 1));
					if (ModConfigs.cachedServer.SPIDER_NEST_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.SPIDER_NEST.get(), ModConfigs.cachedServer.SPIDER_NEST_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.CURSED_DOLL_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.CONIFEROUS))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.CURSED_DOLL.get(), ModConfigs.cachedServer.CURSED_DOLL_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.JACK_FROST_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SNOWY)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.WATER))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.JACK_FROST.get(), ModConfigs.cachedServer.JACK_FROST_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.HORNET_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.CONIFEROUS)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SPOOKY))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.HORNET.get(), ModConfigs.cachedServer.HORNET_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.DULLAHAN_SPAWN_WEIGHT > 0
							&& ((BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MOUNTAIN)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
							|| (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST)
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SPOOKY))))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.DULLAHAN.get(), ModConfigs.cachedServer.DULLAHAN_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.BANSHEE_SPAWN_WEIGHT > 0
							&& ((BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MOUNTAIN)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
							|| BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.CONIFEROUS)))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.BANSHEE.get(), ModConfigs.cachedServer.BANSHEE_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.ARURAUNE_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.JUNGLE)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.ARURAUNE.get(), ModConfigs.cachedServer.ARURAUNE_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.CATOBLEPAS_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.PLAINS))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.CATOBLEPAS.get(), ModConfigs.cachedServer.CATOBLEPAS_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.SCORPION_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SANDY)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.SCORPION.get(), ModConfigs.cachedServer.SCORPION_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.DOGU_SPAWN_WEIGHT > 0
							&& (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MESA)
							|| BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.JUNGLE)
							|| BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MAGICAL)))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.DOGU.get(), ModConfigs.cachedServer.DOGU_SPAWN_WEIGHT, 1, 1));
					if (ModConfigs.cachedServer.REDCAP_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST)
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SPOOKY))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.REDCAP.get(), ModConfigs.cachedServer.REDCAP_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.SLIME_GIRL_SPAWN_WEIGHT > 0
							&& (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SWAMP)
							|| (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MAGICAL)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.DRY)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD))))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.SLIME_GIRL.get(), ModConfigs.cachedServer.SLIME_GIRL_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.DYSSOMNIA_SPAWN_WEIGHT > 0
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.DENSE))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.DYSSOMNIA.get(), ModConfigs.cachedServer.DYSSOMNIA_SPAWN_WEIGHT, 1, 1));
					if (ModConfigs.cachedServer.SNOW_CANINE_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SNOWY)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.WATER))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.SNOW_CANINE.get(), ModConfigs.cachedServer.SNOW_CANINE_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.HARPY_SPAWN_WEIGHT > 0
							&& (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.PLAINS)
							|| BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SANDY))
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MESA))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.HARPY.get(), ModConfigs.cachedServer.HARPY_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.SAVAGEFANG_SPAWN_WEIGHT_IN_SWAMP > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SWAMP))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.SAVAGEFANG.get(), ModConfigs.cachedServer.SAVAGEFANG_SPAWN_WEIGHT_IN_SWAMP, 6, 8));
					else if (ModConfigs.cachedServer.SAVAGEFANG_SPAWN_WEIGHT_IN_JUNGLE > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.JUNGLE))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.SAVAGEFANG.get(), ModConfigs.cachedServer.SAVAGEFANG_SPAWN_WEIGHT_IN_JUNGLE, 6, 8));
					if (ModConfigs.cachedServer.NECROTIC_REAPER_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.NECROTIC_REAPER.get(), ModConfigs.cachedServer.NECROTIC_REAPER_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.DODOMEKI_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.CONIFEROUS))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.DODOMEKI.get(), ModConfigs.cachedServer.DODOMEKI_SPAWN_WEIGHT, 1, 2));

					if (ModConfigs.cachedServer.MELTY_MONSTER_SPAWN_WEIGHT > 0)
					{
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.MELTY_MONSTER.get(), ModConfigs.cachedServer.MELTY_MONSTER_SPAWN_WEIGHT, 1, 2));
						event.getSpawns().addMobCharge(ModEntityTypes.MELTY_MONSTER.get(), 0.25D, 0.25D);
					}
				}

				if ((BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.NETHER) && !isVoid) || ModUtils.checkList(event.getName(), ModConfigs.cachedServer.SPAWN_NETHER_BIOME_WHITELIST))
				{
					if (biomeKey == Biomes.SOUL_SAND_VALLEY)
					{
						if (ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY > 0)
						{
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.WITHER_GHOST.get(), ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY, 2, 2));
							event.getSpawns().addMobCharge(ModEntityTypes.WITHER_GHOST.get(), 0.7D, 0.15D);
						}

						if (ModConfigs.cachedServer.KASHA_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY > 0)
						{
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.KASHA.get(), ModConfigs.cachedServer.KASHA_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY, 1, 2));
							event.getSpawns().addMobCharge(ModEntityTypes.KASHA.get(), 0.7D, 0.15D);
						}

						if (ModConfigs.cachedServer.GHASTLY_SEEKER_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY > 0)
						{
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.GHASTLY_SEEKER.get(), ModConfigs.cachedServer.GHASTLY_SEEKER_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY, 1, 1));
							event.getSpawns().addMobCharge(ModEntityTypes.GHASTLY_SEEKER.get(), 0.7D, 0.15D);
						}
					}
					else if (biomeKey == Biomes.BASALT_DELTAS)
					{
						if (ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT_IN_BASALT_DELTAS > 0)
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.WITHER_GHOST.get(), ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT_IN_BASALT_DELTAS, 2, 2));
						if (ModConfigs.cachedServer.KASHA_SPAWN_WEIGHT_IN_BASALT_DELTAS > 0)
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.KASHA.get(), ModConfigs.cachedServer.KASHA_SPAWN_WEIGHT_IN_BASALT_DELTAS, 1, 2));
						if (ModConfigs.cachedServer.GHASTLY_SEEKER_SPAWN_WEIGHT_IN_BASALT_DELTAS > 0)
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.GHASTLY_SEEKER.get(), ModConfigs.cachedServer.GHASTLY_SEEKER_SPAWN_WEIGHT_IN_BASALT_DELTAS, 1, 1));
						if (ModConfigs.cachedServer.IMP_SPAWN_WEIGHT_IN_BASALT_DELTAS > 0)
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.IMP.get(), ModConfigs.cachedServer.IMP_SPAWN_WEIGHT_IN_BASALT_DELTAS, 1, 2));
					}
					else if (biomeKey.location().toString().equals("biomesoplenty:withered_abyss"))
					{
						if (ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT > 0)
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.WITHER_GHOST.get(), 1, 1, 2));
					}
					else if (biomeKey != Biomes.CRIMSON_FOREST && biomeKey != Biomes.WARPED_FOREST)
					{
						if (ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT > 0)
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.WITHER_GHOST.get(), ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT, 2, 4));
						if (ModConfigs.cachedServer.KASHA_SPAWN_WEIGHT > 0)
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.KASHA.get(), ModConfigs.cachedServer.KASHA_SPAWN_WEIGHT, 1, 2));
						if (ModConfigs.cachedServer.GHASTLY_SEEKER_SPAWN_WEIGHT > 0)
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.GHASTLY_SEEKER.get(), ModConfigs.cachedServer.GHASTLY_SEEKER_SPAWN_WEIGHT, 1, 1));
						if (ModConfigs.cachedServer.IMP_SPAWN_WEIGHT > 0)
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.IMP.get(), ModConfigs.cachedServer.IMP_SPAWN_WEIGHT, 1, 2));
					}

					if (biomeKey == Biomes.CRIMSON_FOREST || (biomeKey != Biomes.WARPED_FOREST && BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST)))
					{
						if (ModConfigs.cachedServer.CRIMSON_SLAUGHTERER_SPAWN_WEIGHT > 0)
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.CRIMSON_SLAUGHTERER.get(), ModConfigs.cachedServer.CRIMSON_SLAUGHTERER_SPAWN_WEIGHT, 1, 1));
						if (ModConfigs.cachedServer.IMP_SPAWN_WEIGHT_IN_CRIMSON_FOREST > 0)
							event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.IMP.get(), ModConfigs.cachedServer.IMP_SPAWN_WEIGHT_IN_CRIMSON_FOREST, 1, 2));
					}

					if (ModConfigs.cachedServer.MELTY_MONSTER_SPAWN_WEIGHT_IN_NETHER > 0)
					{
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.MELTY_MONSTER.get(), ModConfigs.cachedServer.MELTY_MONSTER_SPAWN_WEIGHT_IN_NETHER, 1, 2));

						if (biomeKey == Biomes.SOUL_SAND_VALLEY)
						{
							event.getSpawns().addMobCharge(ModEntityTypes.MELTY_MONSTER.get(), 0.7D, 0.15D);
						}
						else if (biomeKey == Biomes.WARPED_FOREST)
						{
							event.getSpawns().addMobCharge(ModEntityTypes.MELTY_MONSTER.get(), 1.0D, 0.12D);
						}
						else
						{
							event.getSpawns().addMobCharge(ModEntityTypes.MELTY_MONSTER.get(), 0.5D, 0.15D);
						}
					}
				}

				if ((BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.END) && !isVoid) || ModUtils.checkList(event.getName(), ModConfigs.cachedServer.SPAWN_END_BIOME_WHITELIST))
				{
					if (ModConfigs.cachedServer.MONOLITH_SPAWN_WEIGHT > 0 && (biomeKey == Biomes.END_BARRENS || biomeKey == Biomes.SMALL_END_ISLANDS))
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.MONOLITH.get(), ModConfigs.cachedServer.MONOLITH_SPAWN_WEIGHT, 1, 3));

					if (ModConfigs.cachedServer.MELTY_MONSTER_SPAWN_WEIGHT_IN_END > 0)
					{
						event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntityTypes.MELTY_MONSTER.get(), ModConfigs.cachedServer.MELTY_MONSTER_SPAWN_WEIGHT_IN_END, 1, 2));
						event.getSpawns().addMobCharge(ModEntityTypes.MELTY_MONSTER.get(), 0.25D, 0.25D);
					}
				}
			}
		}
	}

	/*
	 * The following code is taken and modified from https://github.com/Alex-the-666/AlexsMobs/blob/edd533010648d3bfd0b2929fa5593ccea3db4f37/src/main/java/com/github/alexthe666/alexsmobs/world/AMWorldRegistry.java
	 */

	private static WeightedRandomList<MobSpawnSettings.SpawnerData> DEFAULT_FORTRESS_ENEMIES;

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onDatapackSync(OnDatapackSyncEvent event)
	{
		MinecraftServer server = event.getPlayerList().getServer();
		Registry<ConfiguredStructureFeature<?, ?>> registry = server.registryAccess().registryOrThrow(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY);
		ConfiguredStructureFeature<?, ?> endCity = registry.get(BuiltinStructures.END_CITY);
		ConfiguredStructureFeature<?, ?> fortress = registry.get(BuiltinStructures.FORTRESS);
		boolean flag = false;
		boolean flag1 = false;
		List<MobSpawnSettings.SpawnerData> fortressEnemyList = new ArrayList<>();

		if (DEFAULT_FORTRESS_ENEMIES == null)
		{
			DEFAULT_FORTRESS_ENEMIES = WeightedRandomList.create(NetherFortressFeature.FORTRESS_ENEMIES.unwrap());
			flag = true;
		}

		if (endCity != null && ModConfigs.cachedServer.MONOLITH_SPAWN_WEIGHT_IN_END_CITY > 0)
		{
			addSpawnToStructure(endCity, StructureSpawnOverride.BoundingBoxType.PIECE, MobCategory.MONSTER, new SpawnerData(ModEntityTypes.MONOLITH.get(), ModConfigs.cachedServer.MONOLITH_SPAWN_WEIGHT_IN_END_CITY, 1, 1));
		}

		if (fortress != null && ModConfigs.cachedServer.FORTRESS_KEEPER_SPAWN_WEIGHT_IN_NETHER_FORTRESS > 0)
		{
			MobSpawnSettings.SpawnerData data = new SpawnerData(ModEntityTypes.FORTRESS_KEEPER.get(), ModConfigs.cachedServer.FORTRESS_KEEPER_SPAWN_WEIGHT_IN_NETHER_FORTRESS, 1, 1);
			addSpawnToStructure(fortress, StructureSpawnOverride.BoundingBoxType.PIECE, MobCategory.MONSTER, data);
			fortressEnemyList.add(data);
		}

		if (!fortressEnemyList.isEmpty())
		{
			addFortressEnemies(fortressEnemyList);
			flag1 = true;
		}

		if (!flag && !flag1)
		{
			NetherFortressFeature.FORTRESS_ENEMIES = WeightedRandomList.create(DEFAULT_FORTRESS_ENEMIES.unwrap());
		}
	}

	private static void addSpawnToStructure(ConfiguredStructureFeature<?, ?> feature, StructureSpawnOverride.BoundingBoxType bbType, MobCategory category, MobSpawnSettings.SpawnerData spawnerData)
	{
		if (feature.spawnOverrides.isEmpty() || feature.spawnOverrides.get(category) == null)
		{
			WeightedRandomList<MobSpawnSettings.SpawnerData> spawns = WeightedRandomList.create(spawnerData);
			StructureSpawnOverride override = new StructureSpawnOverride(bbType, spawns);
			HashMap<MobCategory, StructureSpawnOverride> newMap = new HashMap<>(feature.spawnOverrides);
			newMap.put(category, override);
			feature.spawnOverrides = ImmutableMap.copyOf(newMap);
		}
		else
		{
			StructureSpawnOverride override1 = (StructureSpawnOverride)feature.spawnOverrides.get(category);
			List<MobSpawnSettings.SpawnerData> list = new ArrayList<>(override1.spawns().unwrap());
			boolean flag = false;

			for (MobSpawnSettings.SpawnerData data : list)
			{
				if (data.type.equals(spawnerData.type))
				{
					flag = true;
				}
			}

			WeightedRandomList<MobSpawnSettings.SpawnerData> spawns = WeightedRandomList.create(list);
			StructureSpawnOverride override2 = new StructureSpawnOverride(override1.boundingBox(), spawns);
			HashMap<MobCategory, StructureSpawnOverride> newMap = new HashMap<>(feature.spawnOverrides);

			if (!flag)
			{
				list.add(spawnerData);
			}

			newMap.put(category, override2);
			feature.spawnOverrides = ImmutableMap.copyOf(newMap);
		}
	}

	private static void addFortressEnemies(List<MobSpawnSettings.SpawnerData> additionalSpawnerDataList)
	{
		List<MobSpawnSettings.SpawnerData> list = new ArrayList<>(NetherFortressFeature.FORTRESS_ENEMIES.unwrap());

		for (MobSpawnSettings.SpawnerData additionalSpawnData : additionalSpawnerDataList)
		{
			for (MobSpawnSettings.SpawnerData data : list)
			{
				if (data.type.equals(additionalSpawnData.type))
				{
					break;
				}
			}

			list.add(additionalSpawnData);
		}

		WeightedRandomList<MobSpawnSettings.SpawnerData> fortressEnemies = WeightedRandomList.create(list);
		NetherFortressFeature.FORTRESS_ENEMIES = fortressEnemies;
	}
}