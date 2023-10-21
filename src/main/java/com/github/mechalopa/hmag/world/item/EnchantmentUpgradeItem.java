package com.github.mechalopa.hmag.world.item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.world.item.EnchantmentUpgradeItem.Properties.EnchantmentUpgradeProp;
import com.google.common.collect.Lists;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentUpgradeItem extends ModItem
{
	@Nonnull
	private final List<EnchantmentUpgradeProp> eups;

	public EnchantmentUpgradeItem(Item.Properties builder, EnchantmentUpgradeItem.Properties builder2)
	{
		this(builder, new ModItem.Properties(), builder2);
	}

	public EnchantmentUpgradeItem(Item.Properties builder, ModItem.Properties builder1, EnchantmentUpgradeItem.Properties builder2)
	{
		super(builder, builder1);
		this.eups = builder2.eups;
	}

	@Nonnull
	public List<EnchantmentUpgradeProp> getEnchantmentUpgradeProps()
	{
		return this.eups;
	}

	public static class Properties
	{
		private final List<EnchantmentUpgradeProp> eups = Lists.newArrayList();

		public EnchantmentUpgradeItem.Properties enchantment(Supplier<Enchantment> enchantmentSupplier, int min, int max)
		{
			return this.enchantment(new EnchantmentUpgradeProp(enchantmentSupplier, min, max));
		}

		public EnchantmentUpgradeItem.Properties enchantment(EnchantmentUpgradeProp eupIn)
		{
			this.eups.add(eupIn);
			return this;
		}

		public class EnchantmentUpgradeProp
		{
			@Nullable
			private final Supplier<Enchantment> enchantmentSupplier;
			private final int minLevel;
			private final int maxLevel;

			public EnchantmentUpgradeProp(Supplier<Enchantment> enchantmentSupplier, int min, int max)
			{
				this.enchantmentSupplier = enchantmentSupplier;
				this.minLevel = min;
				this.maxLevel = max;
			}

			@Nullable
			public Enchantment getEnchantment()
			{
				return this.enchantmentSupplier.get();
			}

			public int getMinLevel()
			{
				return this.minLevel;
			}

			public int getMaxLevel()
			{
				return this.maxLevel;
			}
		}
	}
}