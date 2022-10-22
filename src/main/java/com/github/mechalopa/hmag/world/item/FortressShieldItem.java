package com.github.mechalopa.hmag.world.item;

import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FortressShieldItem extends ModShieldItem
{
	public FortressShieldItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack stack1)
	{
		return stack1.is(ModTags.FORTRESS_SHIELD_REPAIR_ITEMS);
	}
}