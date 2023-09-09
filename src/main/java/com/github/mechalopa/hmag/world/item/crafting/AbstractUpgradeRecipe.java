package com.github.mechalopa.hmag.world.item.crafting;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingRecipe;

public abstract class AbstractUpgradeRecipe implements SmithingRecipe
{
	private final ResourceLocation recipeId;

	public AbstractUpgradeRecipe(ResourceLocation recipeId)
	{
		this.recipeId = recipeId;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess)
	{
		return ItemStack.EMPTY;
	}

	@Override
	public boolean isSpecial()
	{
		return true;
	}

	@Override
	public ResourceLocation getId()
	{
		return this.recipeId;
	}
}