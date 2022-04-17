package com.github.mechalopa.hmag.recipe;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.registry.ModRecipes;
import com.github.mechalopa.hmag.util.ModTags;
import com.google.gson.JsonObject;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class RemoveCurseRecipe extends SmithingRecipe
{
	private final ResourceLocation recipeId;

	public RemoveCurseRecipe(ResourceLocation recipeId)
	{
		super(recipeId, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
		this.recipeId = recipeId;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn)
	{
		ItemStack stack = inv.getItem(0);
		ItemStack stack1 = inv.getItem(1);

		if (!stack.isEmpty() && !stack1.isEmpty() && stack1.getItem() != null && ModTags.checkTagContains(ModTags.CURSE_REMOVE_ITEMS, stack1.getItem()))
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
	public ItemStack assemble(IInventory inv)
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
			stack1.setRepairCost(RepairContainer.calculateIncreasedRepairCost(stack.getBaseRepairCost()));
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
	public IRecipeSerializer<?> getSerializer()
	{
		return ModRecipes.REMOVE_CURSE.get();
	}

	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<RemoveCurseRecipe>
	{
		@Override
		public RemoveCurseRecipe fromJson(ResourceLocation recipeId, JsonObject json)
		{
			return new RemoveCurseRecipe(this.getRegistryName());
		}

		@Override
		@Nonnull
		public RemoveCurseRecipe fromNetwork(@Nonnull ResourceLocation recipeId, PacketBuffer buffer)
		{
			return new RemoveCurseRecipe(this.getRegistryName());
		}

		@Override
		public void toNetwork(PacketBuffer buffer, RemoveCurseRecipe recipe){}
	}
}