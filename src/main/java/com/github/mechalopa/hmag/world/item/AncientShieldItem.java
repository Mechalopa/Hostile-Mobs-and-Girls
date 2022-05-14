package com.github.mechalopa.hmag.world.item;

import com.github.mechalopa.hmag.registry.ModItems;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

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