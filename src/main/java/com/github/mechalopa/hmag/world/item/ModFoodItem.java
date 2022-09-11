package com.github.mechalopa.hmag.world.item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving)
	{
		ItemStack stack1 = super.finishUsingItem(stack, level, entityLiving);

		if (!level.isClientSide)
		{
			if (this.healAmount > 0.0F)
			{
				entityLiving.heal(this.healAmount);
			}

			if (this.removePoison)
			{
				entityLiving.removeEffect(MobEffects.POISON);
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