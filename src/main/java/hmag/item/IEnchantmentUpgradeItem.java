package hmag.item;

import net.minecraft.enchantment.Enchantment;

public interface IEnchantmentUpgradeItem
{
	public Enchantment[] getEnchantments();

	public int getMinLevel();

	public int getMaxLevel();
}