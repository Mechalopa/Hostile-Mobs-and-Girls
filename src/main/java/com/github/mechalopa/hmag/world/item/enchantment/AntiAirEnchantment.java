package com.github.mechalopa.hmag.world.item.enchantment;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AntiAirEnchantment extends Enchantment
{
	public AntiAirEnchantment(Enchantment.Rarity rarity, EquipmentSlot... slotType)
	{
		super(rarity, EnchantmentCategory.CROSSBOW, slotType);
	}

	@Override
	public int getMinCost(int level)
	{
		return 7 + ((level - 1) * 5);
	}

	@Override
	public int getMaxCost(int level)
	{
		return this.getMinCost(level) + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return ModConfigs.cachedServer.ANTI_AIR_MAX_LEVEL;
	}

	@Override
	public boolean checkCompatibility(Enchantment enchantment)
	{
		return super.checkCompatibility(enchantment) && !ModTags.checkTagContains(enchantment, ModTags.EnchantmentTags.INCOMPATIBLE_WITH_ANTI_AIR);
	}

	@Override
	public boolean isTreasureOnly()
	{
		return ModConfigs.cachedServer.ANTI_AIR_IS_TREASURE;
	}

	@Override
	public boolean isTradeable()
	{
		return ModConfigs.cachedServer.ANTI_AIR_IS_TRADEABLE;
	}

	@Override
	public boolean isDiscoverable()
	{
		return ModConfigs.cachedServer.ANTI_AIR_IS_DISCOVERABLE;
	}
}