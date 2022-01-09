package net.mechalopa.hmag.item;

import net.mechalopa.hmag.registry.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.MathHelper;
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
		if (livingEntity != null && livingEntity instanceof PlayerEntity)
		{
			FoodStats foodstats = ((PlayerEntity)livingEntity).getFoodData();

			if (foodstats != null)
			{
				return 0.5F + ((float)(20 - MathHelper.clamp(foodstats.getFoodLevel(), 0, 20)) / 20.0F * 2.5F);
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
	public float getBowFOV(ItemStack stack, PlayerEntity player)
	{
		return 0.05F;
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack stack1)
	{
		return stack1.getItem() == ModItems.CRIMSON_CUTICULA.get() || super.isValidRepairItem(stack, stack1);
	}
}