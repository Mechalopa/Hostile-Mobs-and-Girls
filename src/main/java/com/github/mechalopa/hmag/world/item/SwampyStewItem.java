package com.github.mechalopa.hmag.world.item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SwampyStewItem extends BowlFoodItem
{
	public SwampyStewItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity)
	{
		ItemStack stack1 = super.finishUsingItem(stack, level, livingEntity);

		if (!level.isClientSide)
		{
			livingEntity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
		}

		return stack1;
	}
}