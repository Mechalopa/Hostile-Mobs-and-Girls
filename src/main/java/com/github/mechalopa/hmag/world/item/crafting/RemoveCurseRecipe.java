package com.github.mechalopa.hmag.world.item.crafting;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.registry.ModRecipes;
import com.github.mechalopa.hmag.util.ModTags;
import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class RemoveCurseRecipe extends UpgradeRecipe
{
	private final ResourceLocation recipeId;

	public RemoveCurseRecipe(ResourceLocation recipeId)
	{
		super(recipeId, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
		this.recipeId = recipeId;
	}

	@Override
	public boolean matches(Container inv, Level level)
	{
		ItemStack stack = inv.getItem(0);
		ItemStack stack1 = inv.getItem(1);

		if (!stack.isEmpty() && !stack1.isEmpty() && stack1.getItem() != null && stack1.is(ModTags.CURSE_REMOVE_ITEMS) && !stack.is(ModTags.CURSE_REMOVABLE_BLACKLIST))
		{
			Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack);

			for (Entry<Enchantment, Integer> entry : map.entrySet())
			{
				Enchantment enchantment = entry.getKey();

				if (enchantment.isCurse())
				{
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public ItemStack assemble(Container inv)
	{
		ItemStack stack = inv.getItem(0);
		ItemStack stack1 = stack.copy();
		stack1.removeTagKey("Enchantments");
		stack1.removeTagKey("StoredEnchantments");

		Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack).entrySet().stream().filter((p) -> {
			return !(p.getKey().isCurse());
		}).collect(Collectors.toMap(Entry::getKey, Entry::getValue));

		EnchantmentHelper.setEnchantments(map, stack1);
		stack1.setRepairCost(0);

		if (stack1.getItem() == Items.ENCHANTED_BOOK && map.size() == 0)
		{
			stack1 = new ItemStack(Items.BOOK);

			if (stack.hasCustomHoverName())
			{
				stack1.setHoverName(stack.getHoverName());
			}
		}

		for (int i = 0; i < map.size(); ++i)
		{
			stack1.setRepairCost(AnvilMenu.calculateIncreasedRepairCost(stack.getBaseRepairCost()));
		}

		return stack1;
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

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipes.REMOVE_CURSE.get();
	}

	public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<RemoveCurseRecipe>
	{
		@Override
		public RemoveCurseRecipe fromJson(ResourceLocation recipeId, JsonObject json)
		{
			return new RemoveCurseRecipe(this.getRegistryName());
		}

		@Override
		@Nonnull
		public RemoveCurseRecipe fromNetwork(@Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer)
		{
			return new RemoveCurseRecipe(this.getRegistryName());
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, RemoveCurseRecipe recipe){}
	}
}