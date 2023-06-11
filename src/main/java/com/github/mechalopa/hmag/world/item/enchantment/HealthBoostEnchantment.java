package com.github.mechalopa.hmag.world.item.enchantment;

import java.util.UUID;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.ForgeRegistries;

public class HealthBoostEnchantment extends Enchantment
{
	public static final UUID[] HEALTH_BOOST_ENCHANTMENT_MAX_HEALTH_UUIDS = new UUID[]{UUID.fromString("EB931A99-0CF2-6E81-DC0C-9DC22573CCDA"), UUID.fromString("16515E9A-B529-151F-EF1E-FA0B55CB3044"), UUID.fromString("F31C89C1-E8F7-EFED-89E4-8C14E796C09A"), UUID.fromString("036BEDC4-2459-9224-5E89-909F95F799EB")};

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
	public boolean checkCompatibility(Enchantment enchantment)
	{
		Holder<Enchantment> holder = ForgeRegistries.ENCHANTMENTS.getHolder(enchantment).orElseThrow();
		return super.checkCompatibility(enchantment) && !(holder != null && holder.is(ModTags.EnchantmentTags.INCOMPATIBLE_WITH_HEALTH_BOOST));
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