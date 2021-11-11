package hmag.recipe;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import hmag.registry.ModRecipes;
import hmag.util.ModTags;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.ITag;
import net.minecraft.tags.TagCollectionManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemTagShapedRecipe extends ShapedRecipe
{
	public ItemTagShapedRecipe(ShapedRecipe recipe, ItemStack resultIn)
	{
		super(recipe.getId(), recipe.getGroup(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), resultIn);
	}

	@Override
	public boolean matches(CraftingInventory inv, World worldIn)
	{
		if (this.getResultItem().isEmpty())
		{
			return false;
		}
		else
		{
			return super.matches(inv, worldIn);
		}
	}

	@Override
	public boolean isSpecial()
	{
		return this.getResultItem().isEmpty() ? true : super.isSpecial();
	}

	@Override
	public IRecipeSerializer<?> getSerializer()
	{
		return ModRecipes.CRAFTING_ITEM_TAG_SHAPED.get();
	}

	public static class Serializer extends ShapedRecipe.Serializer
	{
		@Override
		public ItemTagShapedRecipe fromJson(ResourceLocation recipeId, JsonObject json)
		{
			JsonObject json2 = JSONUtils.getAsJsonObject(json, "result");
			json2.addProperty("item", Items.DIRT.getRegistryName().toString());
			ResourceLocation resourcelocation = new ResourceLocation(JSONUtils.getAsString(json2, "tag"));
			ITag<Item> itag = TagCollectionManager.getInstance().getItems().getTag(resourcelocation);

			if (itag == null)
			{
				throw new JsonSyntaxException("Unknown item tag '" + resourcelocation + "'");
			}

			final Item item = ModTags.getItem(itag);

			if (item != null)
			{
				ItemStack stack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));

				if (stack != null && !stack.isEmpty())
				{
					ItemStack stack1 = new ItemStack(item, stack.getCount());
					CompoundNBT compoundnbt = stack.getTag();

					if (compoundnbt != null)
					{
						stack1.setTag(compoundnbt.copy());
					}

					return new ItemTagShapedRecipe(super.fromJson(recipeId, json), stack1);
				}
			}

			return new ItemTagShapedRecipe(super.fromJson(recipeId, json), ItemStack.EMPTY);
		}

		@Override
		@Nonnull
		public ItemTagShapedRecipe fromNetwork(@Nonnull ResourceLocation recipeId, PacketBuffer buffer)
		{
			return new ItemTagShapedRecipe(super.fromNetwork(recipeId, buffer), buffer.readItem());
		}

		@Override
		public void toNetwork(PacketBuffer buffer, ShapedRecipe recipe)
		{
			super.toNetwork(buffer, recipe);
			buffer.writeItem(recipe.getResultItem());
		}
	}
}