package com.github.mechalopa.hmag.item;

import com.github.mechalopa.hmag.registry.ModItems;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CrimsonBowItem extends ModBowItem
{
	public CrimsonBowItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public float getBowDamage(ItemStack stack, LivingEntity livingEntity)
	{
		if (livingEntity != null && livingEntity instanceof Player)
		{
			FoodData foodstats = ((Player)livingEntity).getFoodData();

			if (foodstats != null)
			{
				return 0.5F + ((float)(20 - Mth.clamp(foodstats.getFoodLevel(), 0, 20)) / 20.0F * 2.5F);
			}
		}

		return 0.5F;
	}

	@Override
	public float getArrowVelocity(ItemStack stack, LivingEntity livingEntity)
	{
		return 3.25F;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public float getBowFOV(ItemStack stack, Player player)
	{
		return 0.05F;
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack stack1)
	{
		return stack1.getItem() == ModItems.CRIMSON_CUTICULA.get() || super.isValidRepairItem(stack, stack1);
	}
}