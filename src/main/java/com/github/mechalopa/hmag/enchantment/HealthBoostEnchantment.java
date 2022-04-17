package com.github.mechalopa.hmag.enchantment;

import com.github.mechalopa.hmag.ModConfigs;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class HealthBoostEnchantment extends Enchantment
{
	public HealthBoostEnchantment(Enchantment.Rarity rarity, EquipmentSlotType... slotType)
	{
		super(rarity, EnchantmentType.ARMOR, slotType);
	}

	@Override
	public int getMinCost(int level)
	{
		return 3 + ((level + 1) * 5);
	}

	@Override
	public int getMaxCost(int level)
	{
		return this.getMinCost(level) + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return ModConfigs.cachedServer.HEALTH_BOOST_MAX_LEVEL;
	}

	@Override
	public boolean isTreasureOnly()
	{
		return ModConfigs.cachedServer.HEALTH_BOOST_IS_TREASURE;
	}

	@Override
	public boolean isTradeable()
	{
		return ModConfigs.cachedServer.HEALTH_BOOST_IS_TRADEABLE;
	}

	@Override
	public boolean isDiscoverable()
	{
		return ModConfigs.cachedServer.HEALTH_BOOST_IS_DISCOVERABLE;
	}
}