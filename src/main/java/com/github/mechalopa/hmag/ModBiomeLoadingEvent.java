package com.github.mechalopa.hmag;

import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModBiomeLoadingEvent
{
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onBiomeLoad(BiomeLoadingEvent event)
	{
		if (event.getName() != null && !ModUtils.checkList(event.getName(), ModConfigs.cachedServer.SPAWN_BIOME_BLACKLIST))
		{
			RegistryKey<Biome> biomeKey = RegistryKey.create(ForgeRegistries.Keys.BIOMES, event.getName());

			if (biomeKey != null)
			{
				boolean isVoid = BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.VOID);

				if ((BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.OVERWORLD) && !isVoid) || ModUtils.checkList(event.getName(), ModConfigs.cachedServer.SPAWN_OVERWORLD_BIOME_WHITELIST))
				{
					if (ModConfigs.cachedServer.GHOST_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.GHOST.get(), ModConfigs.cachedServer.GHOST_SPAWN_WEIGHT, 4, 4));
					if (ModConfigs.cachedServer.KOBOLD_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.KOBOLD.get(), ModConfigs.cachedServer.KOBOLD_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.LICH_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.LICH.get(), ModConfigs.cachedServer.LICH_SPAWN_WEIGHT, 1, 1));
					if (ModConfigs.cachedServer.OGRE_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.OGRE.get(), ModConfigs.cachedServer.OGRE_SPAWN_WEIGHT, 1, 1));
					if (ModConfigs.cachedServer.SPIDER_NEST_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.SPIDER_NEST.get(), ModConfigs.cachedServer.SPIDER_NEST_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.CURSED_DOLL_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.CONIFEROUS))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.CURSED_DOLL.get(), ModConfigs.cachedServer.CURSED_DOLL_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.JACK_FROST_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SNOWY)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.WATER))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.JACK_FROST.get(), ModConfigs.cachedServer.JACK_FROST_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.HORNET_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.CONIFEROUS)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SPOOKY))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.HORNET.get(), ModConfigs.cachedServer.HORNET_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.DULLAHAN_SPAWN_WEIGHT > 0
							&& ((BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MOUNTAIN)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
							|| (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST)
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SPOOKY))))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.DULLAHAN.get(), ModConfigs.cachedServer.DULLAHAN_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.BANSHEE_SPAWN_WEIGHT > 0
							&& ((BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MOUNTAIN)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT))
							|| BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.CONIFEROUS)))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.BANSHEE.get(), ModConfigs.cachedServer.BANSHEE_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.ARURAUNE_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.JUNGLE)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.ARURAUNE.get(), ModConfigs.cachedServer.ARURAUNE_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.CATOBLEPAS_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.PLAINS))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.CATOBLEPAS.get(), ModConfigs.cachedServer.CATOBLEPAS_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.SCORPION_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SANDY)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.SCORPION.get(), ModConfigs.cachedServer.SCORPION_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.DOGU_SPAWN_WEIGHT > 0
							&& (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MESA)
							|| BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.JUNGLE)
							|| BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MAGICAL)))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.DOGU.get(), ModConfigs.cachedServer.DOGU_SPAWN_WEIGHT, 1, 1));
					if (ModConfigs.cachedServer.REDCAP_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST)
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SPOOKY))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.REDCAP.get(), ModConfigs.cachedServer.REDCAP_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.SLIME_GIRL_SPAWN_WEIGHT > 0
							&& (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SWAMP)
							|| (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MAGICAL)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.DRY)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD))))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.SLIME_GIRL.get(), ModConfigs.cachedServer.SLIME_GIRL_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.DYSSOMNIA_SPAWN_WEIGHT > 0
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.DENSE))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.DYSSOMNIA.get(), ModConfigs.cachedServer.DYSSOMNIA_SPAWN_WEIGHT, 1, 1));
					if (ModConfigs.cachedServer.SNOW_CANINE_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SNOWY)
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.WATER))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.SNOW_CANINE.get(), ModConfigs.cachedServer.SNOW_CANINE_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.STONEULAR_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.STONEULAR.get(), ModConfigs.cachedServer.STONEULAR_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.HARPY_SPAWN_WEIGHT > 0
							&& (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.PLAINS)
							|| BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SANDY))
							&& !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.MESA))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.HARPY.get(), ModConfigs.cachedServer.HARPY_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.SAVAGEFANG_SPAWN_WEIGHT_IN_SWAMP > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SWAMP))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.SAVAGEFANG.get(), ModConfigs.cachedServer.SAVAGEFANG_SPAWN_WEIGHT_IN_SWAMP, 6, 8));
					else if (ModConfigs.cachedServer.SAVAGEFANG_SPAWN_WEIGHT_IN_JUNGLE > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.JUNGLE))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.SAVAGEFANG.get(), ModConfigs.cachedServer.SAVAGEFANG_SPAWN_WEIGHT_IN_JUNGLE, 6, 8));
					if (ModConfigs.cachedServer.NECROTIC_REAPER_SPAWN_WEIGHT > 0)
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.NECROTIC_REAPER.get(), ModConfigs.cachedServer.NECROTIC_REAPER_SPAWN_WEIGHT, 1, 2));
					if (ModConfigs.cachedServer.DODOMEKI_SPAWN_WEIGHT > 0
							&& BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.CONIFEROUS))
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.DODOMEKI.get(), ModConfigs.cachedServer.DODOMEKI_SPAWN_WEIGHT, 1, 2));

					if (ModConfigs.cachedServer.MELTY_MONSTER_SPAWN_WEIGHT > 0)
					{
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.MELTY_MONSTER.get(), ModConfigs.cachedServer.MELTY_MONSTER_SPAWN_WEIGHT, 1, 2));
						event.getSpawns().addMobCharge(ModEntityTypes.MELTY_MONSTER.get(), 0.25D, 0.25D);
					}
				}

				if ((BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.NETHER) && !isVoid) || ModUtils.checkList(event.getName(), ModConfigs.cachedServer.SPAWN_NETHER_BIOME_WHITELIST))
				{
					if (biomeKey == Biomes.SOUL_SAND_VALLEY)
					{
						if (ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY > 0)
						{
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.WITHER_GHOST.get(), ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY, 2, 2));
							event.getSpawns().addMobCharge(ModEntityTypes.WITHER_GHOST.get(), 0.7D, 0.15D);
						}

						if (ModConfigs.cachedServer.KASHA_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY > 0)
						{
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.KASHA.get(), ModConfigs.cachedServer.KASHA_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY, 1, 2));
							event.getSpawns().addMobCharge(ModEntityTypes.KASHA.get(), 0.7D, 0.15D);
						}

						if (ModConfigs.cachedServer.GHSATLY_SHEEKER_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY > 0)
						{
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.GHSATLY_SHEEKER.get(), ModConfigs.cachedServer.GHSATLY_SHEEKER_SPAWN_WEIGHT_IN_SOUL_SAND_VALLEY, 1, 1));
							event.getSpawns().addMobCharge(ModEntityTypes.GHSATLY_SHEEKER.get(), 0.7D, 0.15D);
						}
					}
					else if (biomeKey == Biomes.BASALT_DELTAS)
					{
						if (ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT_IN_BASALT_DELTAS > 0)
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.WITHER_GHOST.get(), ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT_IN_BASALT_DELTAS, 2, 2));
						if (ModConfigs.cachedServer.KASHA_SPAWN_WEIGHT_IN_BASALT_DELTAS > 0)
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.KASHA.get(), ModConfigs.cachedServer.KASHA_SPAWN_WEIGHT_IN_BASALT_DELTAS, 1, 2));
						if (ModConfigs.cachedServer.GHSATLY_SHEEKER_SPAWN_WEIGHT_IN_BASALT_DELTAS > 0)
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.GHSATLY_SHEEKER.get(), ModConfigs.cachedServer.GHSATLY_SHEEKER_SPAWN_WEIGHT_IN_BASALT_DELTAS, 1, 1));
						if (ModConfigs.cachedServer.IMP_SPAWN_WEIGHT_IN_BASALT_DELTAS > 0)
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.IMP.get(), ModConfigs.cachedServer.IMP_SPAWN_WEIGHT_IN_BASALT_DELTAS, 1, 2));
					}
					else if (biomeKey.location().toString().equals("biomesoplenty:withered_abyss"))
					{
						if (ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT > 0)
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.WITHER_GHOST.get(), 1, 1, 2));
					}
					else if (biomeKey != Biomes.CRIMSON_FOREST && biomeKey != Biomes.WARPED_FOREST)
					{
						if (ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT > 0)
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.WITHER_GHOST.get(), ModConfigs.cachedServer.WITHER_GHOST_SPAWN_WEIGHT, 2, 4));
						if (ModConfigs.cachedServer.KASHA_SPAWN_WEIGHT > 0)
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.KASHA.get(), ModConfigs.cachedServer.KASHA_SPAWN_WEIGHT, 1, 2));
						if (ModConfigs.cachedServer.GHSATLY_SHEEKER_SPAWN_WEIGHT > 0)
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.GHSATLY_SHEEKER.get(), ModConfigs.cachedServer.GHSATLY_SHEEKER_SPAWN_WEIGHT, 1, 1));
						if (ModConfigs.cachedServer.IMP_SPAWN_WEIGHT > 0)
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.IMP.get(), ModConfigs.cachedServer.IMP_SPAWN_WEIGHT, 1, 2));
					}

					if (biomeKey == Biomes.CRIMSON_FOREST || (biomeKey != Biomes.WARPED_FOREST && BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST)))
					{
						if (ModConfigs.cachedServer.CRIMSON_SLAUGHTERER_SPAWN_WEIGHT > 0)
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.CRIMSON_SLAUGHTERER.get(), ModConfigs.cachedServer.CRIMSON_SLAUGHTERER_SPAWN_WEIGHT, 1, 1));
						if (ModConfigs.cachedServer.IMP_SPAWN_WEIGHT_IN_CRIMSON_FOREST > 0)
							event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.IMP.get(), ModConfigs.cachedServer.IMP_SPAWN_WEIGHT_IN_CRIMSON_FOREST, 1, 2));
					}

					if (ModConfigs.cachedServer.MELTY_MONSTER_SPAWN_WEIGHT_IN_NETHER > 0)
					{
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.MELTY_MONSTER.get(), ModConfigs.cachedServer.MELTY_MONSTER_SPAWN_WEIGHT_IN_NETHER, 1, 2));

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
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.MONOLITH.get(), ModConfigs.cachedServer.MONOLITH_SPAWN_WEIGHT, 1, 3));

					if (ModConfigs.cachedServer.MELTY_MONSTER_SPAWN_WEIGHT_IN_END > 0)
					{
						event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModEntityTypes.MELTY_MONSTER.get(), ModConfigs.cachedServer.MELTY_MONSTER_SPAWN_WEIGHT_IN_END, 1, 2));
						event.getSpawns().addMobCharge(ModEntityTypes.MELTY_MONSTER.get(), 0.25D, 0.25D);
					}
				}
			}
		}
	}
}