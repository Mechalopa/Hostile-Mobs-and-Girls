package com.github.mechalopa.hmag.recipe;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.registry.ModRecipes;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

public class ItemTagShapedRecipe extends ShapedRecipe
{
	public ItemTagShapedRecipe(ShapedRecipe recipe, ItemStack resultIn)
	{
		super(recipe.getId(), recipe.getGroup(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), resultIn);
	}

	@Override
	public boolean matches(CraftingContainer inv, Level level)
	{
		if (this.getResultItem().isEmpty())
		{
			return false;
		}
		else
		{
			return super.matches(inv, level);
		}
	}

	@Override
	public boolean isSpecial()
	{
		return this.getResultItem().isEmpty() ? true : super.isSpecial();
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipes.CRAFTING_ITEM_TAG_SHAPED.get();
	}

	public static class Serializer extends net.minecraft.world.item.crafting.ShapedRecipe.Serializer
	{
		@Override
		public ItemTagShapedRecipe fromJson(ResourceLocation recipeId, JsonObject json)
		{
			JsonObject json2 = GsonHelper.getAsJsonObject(json, "result");
			json2.addProperty("item", Items.DIRT.getRegistryName().toString());
			ResourceLocation resourcelocation = new ResourceLocation(GsonHelper.getAsString(json2, "tag"));
			TagKey<Item> tagKey = TagKey.create(Registry.ITEM_REGISTRY, resourcelocation);

			if (tagKey == null)
			{
				throw new JsonSyntaxException("Unknown item tag '" + resourcelocation + "'");
			}

			final Ingredient.Value tagValue = new Ingredient.TagValue(tagKey);
			ItemStack stack = tagValue.getItems().stream().findFirst().orElse(ItemStack.EMPTY);

			if (!stack.isEmpty())
			{
				ItemStack stack1 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

				if (stack1 != null && !stack1.isEmpty())
				{
					ItemStack stack2 = new ItemStack(stack.getItem(), stack1.getCount());
					CompoundTag compound = stack1.getTag();

					if (compound != null)
					{
						stack2.setTag(compound.copy());
					}

					return new ItemTagShapedRecipe(super.fromJson(recipeId, json), stack2);
				}
			}

			return new ItemTagShapedRecipe(super.fromJson(recipeId, json), ItemStack.EMPTY);
		}

		@Override
		@Nonnull
		public ItemTagShapedRecipe fromNetwork(@Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer)
		{
			return new ItemTagShapedRecipe(super.fromNetwork(recipeId, buffer), buffer.readItem());
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, ShapedRecipe recipe)
		{
			super.toNetwork(buffer, recipe);
			buffer.writeItem(recipe.getResultItem());
		}
	}
}