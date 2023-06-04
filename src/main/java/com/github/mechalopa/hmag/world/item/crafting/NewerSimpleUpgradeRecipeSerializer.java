package com.github.mechalopa.hmag.world.item.crafting;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class NewerSimpleUpgradeRecipeSerializer<T extends AbstractNewerUpgradeRecipe> implements RecipeSerializer<T>
{
	private final NewerSimpleUpgradeRecipeSerializer.Factory<T> constructor;

	public NewerSimpleUpgradeRecipeSerializer(NewerSimpleUpgradeRecipeSerializer.Factory<T> constructor)
	{
		this.constructor = constructor;
	}

	@Override
	public T fromJson(ResourceLocation recipeId, JsonObject json)
	{
		return this.constructor.create(recipeId);
	}

	@Override
	@Nonnull
	public T fromNetwork(@Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer)
	{
		return this.constructor.create(recipeId);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, T recipe){}

	@FunctionalInterface
	public interface Factory<T extends AbstractNewerUpgradeRecipe>
	{
		T create(ResourceLocation recipeId);
	}
}