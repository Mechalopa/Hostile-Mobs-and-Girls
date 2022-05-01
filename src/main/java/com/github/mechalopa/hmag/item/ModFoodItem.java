package com.github.mechalopa.hmag.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ModFoodItem extends ModItem
{
	private final int eatDuration;
	private final float healAmount;
	private final boolean removePoison;

	public ModFoodItem(Item.Properties builder, ModFoodItem.Properties builder2)
	{
		this(builder, new ModItem.Properties(), builder2);
	}

	public ModFoodItem(Item.Properties builder, ModItem.Properties builder1, ModFoodItem.Properties builder2)
	{
		super(builder, builder1);
		this.eatDuration = builder2.eatDuration;
		this.healAmount = builder2.healAmount;
		this.removePoison = builder2.removePoison;
	}

	@Override
	public int getUseDuration(ItemStack stack)
	{
		return this.eatDuration > 0 ? this.eatDuration : super.getUseDuration(stack);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving)
	{
		ItemStack stack1 = super.finishUsingItem(stack, worldIn, entityLiving);

		if (!worldIn.isClientSide)
		{
			if (this.healAmount > 0.0F)
			{
				entityLiving.heal(this.healAmount);
			}

			if (this.removePoison)
			{
				entityLiving.removeEffect(Effects.POISON);
			}
		}

		return stack1;
	}

	public static class Properties
	{
		private int eatDuration = 0;
		private float healAmount = 0.0F;
		private boolean removePoison = false;

		public ModFoodItem.Properties eatDuration(int eatDurationIn)
		{
			this.eatDuration = eatDurationIn;
			return this;
		}

		public ModFoodItem.Properties healAmount(float healAmountIn)
		{
			this.healAmount = healAmountIn;
			return this;
		}

		public ModFoodItem.Properties removePoison()
		{
			this.removePoison = true;
			return this;
		}
	}
}