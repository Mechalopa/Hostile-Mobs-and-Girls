package com.github.mechalopa.hmag.util;

import javax.annotation.Nullable;

import com.github.mechalopa.hmag.HMaG;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class ModDamageTypes
{
	public static final ResourceKey<DamageType> MAGMA_BULLET = register("magma_bullet");
	public static final ResourceKey<DamageType> CRITICAL_EVIL_ARROW = register("critical_evil_arrow");

	private static ResourceKey<DamageType> register(String name)
	{
		return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(HMaG.MODID, name));
	}

	public static DamageSource source(Level level, ResourceKey<DamageType> key)
	{
		return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
	}

	public static DamageSource source(Level level, ResourceKey<DamageType> key, @Nullable Entity entity)
	{
		return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key), entity);
	}

	public static DamageSource source(Level level, ResourceKey<DamageType> key, @Nullable Entity entity, @Nullable Entity sourceEntity)
	{
		return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key), entity, sourceEntity);
	}
}