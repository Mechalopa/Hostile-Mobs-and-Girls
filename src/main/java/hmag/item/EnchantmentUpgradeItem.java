package hmag.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;

public class EnchantmentUpgradeItem extends ModItem
{
	private final Enchantment[] enchantments;
	private final int minLevel;
	private final int maxLevel;

	public EnchantmentUpgradeItem(Item.Properties builder, Enchantment enchantmentIn, int min)
	{
		this(builder, new ModItem.Properties(), enchantmentIn, min);
	}

	public EnchantmentUpgradeItem(Item.Properties builder, Enchantment enchantmentIn, int min, int max)
	{
		this(builder, new ModItem.Properties(), enchantmentIn, min, max);
	}

	public EnchantmentUpgradeItem(Item.Properties builder, Enchantment[] enchantmentsIn, int min, int max)
	{
		this(builder, new ModItem.Properties(), enchantmentsIn, min, max);
	}

	public EnchantmentUpgradeItem(Item.Properties builder, ModItem.Properties builder1, Enchantment enchantmentIn, int min)
	{
		this(builder, builder1, enchantmentIn, min, min);
	}

	public EnchantmentUpgradeItem(Item.Properties builder, ModItem.Properties builder1, Enchantment enchantmentIn, int min, int max)
	{
		this(builder, builder1, new Enchantment[]{enchantmentIn}, min, max);
	}

	public EnchantmentUpgradeItem(Item.Properties builder, ModItem.Properties builder1, Enchantment[] enchantmentsIn, int min, int max)
	{
		super(builder, builder1);
		this.enchantments = enchantmentsIn;
		this.minLevel = min;
		this.maxLevel = max;
	}

	public Enchantment[] getEnchantments()
	{
		return this.enchantments;
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