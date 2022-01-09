package net.mechalopa.hmag.enchantment;

import net.mechalopa.hmag.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;

public class WaterAspectEnchantment extends Enchantment
{
	public WaterAspectEnchantment(Enchantment.Rarity rarity, EquipmentSlotType... slotType)
	{
		super(rarity, EnchantmentType.WEAPON, slotType);
	}

	@Override
	public int getMinCost(int level)
	{
		return 5 + ((level + 1) * 8);
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