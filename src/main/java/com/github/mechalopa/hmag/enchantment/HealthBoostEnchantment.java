package com.github.mechalopa.hmag.enchantment;

import com.github.mechalopa.hmag.ModConfigs;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;


public class HealthBoostEnchantment extends Enchantment
{
	public HealthBoostEnchantment(Enchantment.Rarity rarity, EquipmentSlot... slotType)
	{
		super(rarity, EnchantmentCategory.ARMOR, slotType);
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