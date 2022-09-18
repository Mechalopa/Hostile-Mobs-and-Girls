package com.github.mechalopa.hmag.world.level.modifiers;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;

public record ModAddSpawnsBiomeModifier(List<List<ModAddSpawnsBiomeModifier.BiomeProp>> biomePropLists, List<SpawnerData> spawners, double energyBudget, double charge) implements BiomeModifier
{
	public static final Supplier<Codec<ModAddSpawnsBiomeModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(builder -> builder.group(
			BiomeProp.CODEC.listOf().listOf().fieldOf("biomes").forGetter(ModAddSpawnsBiomeModifier::biomePropLists),
			new ExtraCodecs.EitherCodec<>(SpawnerData.CODEC.listOf(), SpawnerData.CODEC).xmap(either -> either.map(Function.identity(), List::of), list -> list.size() == 1 ? Either.right(list.get(0)) : Either.left(list)).fieldOf("spawners").forGetter(ModAddSpawnsBiomeModifier::spawners),
			Codec.DOUBLE.optionalFieldOf("energy_budget", -1.0D).forGetter(ModAddSpawnsBiomeModifier::energyBudget),
			Codec.DOUBLE.optionalFieldOf("charge", -1.0D).forGetter(ModAddSpawnsBiomeModifier::charge))
			.apply(builder, ModAddSpawnsBiomeModifier::new)));

	public static ModAddSpawnsBiomeModifier singleSpawn(List<List<BiomeProp>> biomePropLists, SpawnerData spawner, double energyBudget, double charge)
	{
		return new ModAddSpawnsBiomeModifier(biomePropLists, List.of(spawner), energyBudget, charge);
	}

	@Override
	public void modify(Holder<Biome> biome, Phase phase, Builder builder)
	{
		if (phase == Phase.ADD)
		{
			if (matches(biome, this.biomePropLists))
			{
				MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();

				for (SpawnerData spawner : this.spawners)
				{
					EntityType<?> type = spawner.type;
					spawns.addSpawn(type.getCategory(), spawner);

					if (this.energyBudget > 0.0D && this.charge > 0.0D)
					{
						spawns.addMobCharge(type, this.charge, this.energyBudget);
					}
				}
			}
		}
	}

	public static boolean matches(Holder<Biome> biome, List<List<BiomeProp>> biomePropLists)
	{
		if (biomePropLists != null && !biomePropLists.isEmpty())
		{
			for (List<BiomeProp> biomeProps : biomePropLists)
			{
				boolean flag = true;

				for (BiomeProp biomeProp : biomeProps)
				{
					if (!biomeProp.biomes().contains(biome) ^ biomeProp.negate())
					{
						flag = false;
						break;
					}
				}

				if (flag)
				{
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public Codec<? extends BiomeModifier> codec()
	{
		return CODEC.get();
	}

	public static record BiomeProp(HolderSet<Biome> biomes, boolean negate)
	{
		public static final Codec<BiomeProp> CODEC = RecordCodecBuilder.create((p -> {
			return p.group(Biome.LIST_CODEC.fieldOf("name").forGetter(BiomeProp::biomes), Codec.BOOL.optionalFieldOf("negate", false).forGetter(BiomeProp::negate)).apply(p, BiomeProp::new);
		}));
	}
}