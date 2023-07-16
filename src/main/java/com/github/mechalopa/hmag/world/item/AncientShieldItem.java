package com.github.mechalopa.hmag.world.item;

import java.util.UUID;

import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AncientShieldItem extends ModShieldItem
{
	public static final UUID ANCIENT_SHIELD_KNOCKBACK_RESISTANCE_UUID = UUID.fromString("0915B1C7-492D-2776-EFF2-436BF1072692");
	public static final AttributeModifier ANCIENT_SHIELD_KNOCKBACK_RESISTANCE_MODIFIER = new AttributeModifier(ANCIENT_SHIELD_KNOCKBACK_RESISTANCE_UUID, "Ancient shield knockback resistance bonus", 0.05F, AttributeModifier.Operation.ADDITION);

	public AncientShieldItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack stack1)
	{
		return stack1.is(ModTags.ItemTags.ANCIENT_SHIELD_REPAIR_ITEMS);
	}
}