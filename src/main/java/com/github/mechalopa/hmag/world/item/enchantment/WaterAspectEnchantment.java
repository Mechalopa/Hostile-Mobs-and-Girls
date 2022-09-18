package com.github.mechalopa.hmag.world.item.enchantment;

import com.github.mechalopa.hmag.ModConfigs;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class WaterAspectEnchantment extends Enchantment
{
	public WaterAspectEnchantment(Enchantment.Rarity rarity, EquipmentSlot... slotType)
	{
		super(rarity, EnchantmentCategory.WEAPON, slotType);
	}

	@Override
	public int getMinCost(int level)
	{
		return 5 + ((level - 1) * 5);
	}

	@Override
	public int getMaxCost(int level)
	{
		return this.getMinCost(level) + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return ModConfigs.cachedServer.WATER_ASPECT_MAX_LEVEL;
	}

	@Override
	public boolean checkCompatibility(Enchantment enchantment)
	{
		return enchantment != Enchantments.FIRE_ASPECT;
	}

	@Override
	public boolean isTreasureOnly()
	{
		return ModConfigs.cachedServer.WATER_ASPECT_IS_TREASURE;
	}

	@Override
	public boolean isTradeable()
	{
		return ModConfigs.cachedServer.WATER_ASPECT_IS_TRADEABLE;
	}

	@Override
	public boolean isDiscoverable()
	{
		return ModConfigs.cachedServer.WATER_ASPECT_IS_DISCOVERABLE;
	}
}