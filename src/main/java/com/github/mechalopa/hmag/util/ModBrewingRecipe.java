package com.github.mechalopa.hmag.util;

import javax.annotation.Nonnull;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;

public class ModBrewingRecipe extends BrewingRecipe
{
	@Nonnull
	private final Ingredient input;
	@Nonnull
	private final Ingredient ingredient;
	@Nonnull
	private final ItemStack output;

	public ModBrewingRecipe(Ingredient input, Ingredient ingredient, ItemStack output)
	{
		super(input, ingredient, output);
		this.input = input;
		this.ingredient = ingredient;
		this.output = output;
	}

	@Override
	public boolean isInput(@Nonnull ItemStack stack)
	{
		if (stack != null)
		{
			ItemStack[] stacks = this.input.getItems();

			if (stacks.length > 0)
			{
				for (ItemStack stack1 : stacks)
				{
					if (stack1.sameItem(stack) && PotionUtils.getPotion(stack1).equals(PotionUtils.getPotion(stack)))
					{
						return true;
					}
				}
			}
			else
			{
				return stack.isEmpty();
			}
		}

		return false;
	}
}