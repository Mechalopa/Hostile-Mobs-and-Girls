package hmag.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import hmag.item.EnchantmentUpgradeItem.Properties.EnchantmentUpgradeProp;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;

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

		public EnchantmentUpgradeItem.Properties enchantment(Enchantment enchantmentIn, int min, int max)
		{
			return this.enchantment(new EnchantmentUpgradeProp(enchantmentIn, min, max));
		}

		public EnchantmentUpgradeItem.Properties enchantment(EnchantmentUpgradeProp eupIn)
		{
			this.eups.add(eupIn);
			return this;
		}

		public class EnchantmentUpgradeProp
		{
			@Nullable
			private final Enchantment enchantment;
			private final int minLevel;
			private final int maxLevel;

			public EnchantmentUpgradeProp(@Nullable Enchantment enchantmentIn, int min, int max)
			{
				this.enchantment = enchantmentIn;
				this.minLevel = min;
				this.maxLevel = max;
			}

			@Nullable
			public Enchantment getEnchantment()
			{
				return this.enchantment;
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