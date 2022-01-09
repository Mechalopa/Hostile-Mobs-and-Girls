package net.mechalopa.hmag.item;

import net.mechalopa.hmag.registry.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AncientShieldItem extends ModShieldItem
{
	public AncientShieldItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack stack1)
	{
		return stack1.getItem() == ModItems.ANCIENT_STONE.get();
	}
}