package com.github.mechalopa.hmag.world.item.crafting;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;

public abstract class AbstractNewerUpgradeRecipe implements SmithingRecipe
{
	private final ResourceLocation recipeId;

	public AbstractNewerUpgradeRecipe(ResourceLocation recipeId)
	{
		this.recipeId = recipeId;
	}

	@Override
	public boolean matches(Container inv, Level level)
	{
		return level.enabledFeatures().contains(FeatureFlags.UPDATE_1_20) && inv.getItem(0).isEmpty();
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess)
	{
		return ItemStack.EMPTY;
	}

	@Override
	public boolean isTemplateIngredient(ItemStack stack)
	{
		return stack.isEmpty();
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