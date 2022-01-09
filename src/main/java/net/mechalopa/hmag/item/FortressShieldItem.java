package net.mechalopa.hmag.item;

import net.mechalopa.hmag.registry.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FortressShieldItem extends ModShieldItem
{
	public FortressShieldItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack stack1)
	{
		return stack1.getItem() == ModItems.BURNING_CORE.get();
	}
}