package com.github.mechalopa.hmag.world.item.crafting;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.registry.ModRecipes;
import com.github.mechalopa.hmag.util.ModTags;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
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
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentUpgradeRecipe extends UpgradeRecipe
{
	private final Ingredient addition;
	private final String enchantmentId;
	private final Enchantment enchantment;
	private final int minLevel;
	private final int maxLevel;

	public EnchantmentUpgradeRecipe(ResourceLocation recipeId, Ingredient addition, String enchantmentId, int min, int max)
	{
		super(recipeId, Ingredient.EMPTY, addition, ItemStack.EMPTY);
		this.addition = addition;
		this.enchantmentId = enchantmentId;
		this.minLevel = min;
		this.maxLevel = max;

		ResourceLocation resourcelocation = new ResourceLocation(enchantmentId);
		Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(resourcelocation);

		if (enchantment == null)
		{
			throw new JsonSyntaxException("Unknown enchantment '" + resourcelocation + "'");
		}

		this.enchantment = enchantment;
	}

	@Override
	public boolean matches(Container inv, Level level)
	{
		if (this.getEnchantment() != null)
		{
			final Enchantment enchantment = this.getEnchantment();
			ItemStack stack = inv.getItem(0);
			ItemStack stack1 = inv.getItem(1);

			if (!stack.isEmpty() && !(stack.getItem() == null || stack.getItem() == Items.ENCHANTED_BOOK) && !stack1.isEmpty() && this.addition.test(stack1) && !stack.is(ModTags.ENCHANTMENT_NOT_UPGRADEABLES))
			{
				if (enchantment != null && checkEnchantableItem(stack, enchantment, this.minLevel, this.maxLevel))
				{
					return true;
				}
			}
		}

		return false;
	}

	private static boolean checkEnchantableItem(ItemStack stack, Enchantment enchantment, int min, int max)
	{
		if (!stack.isEmpty() && enchantment.canEnchant(stack))
		{
			int i = EnchantmentHelper.getTagEnchantmentLevel(enchantment, stack);

			if (i == 0 && min == 0 && max >= 0)
			{
				if (EnchantmentHelper.isEnchantmentCompatible(EnchantmentHelper.getEnchantments(stack).keySet(), enchantment))
				{
					return true;
				}
			}
			else if (i >= min && i <= max)
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public ItemStack assemble(Container inv)
	{
		if (this.getEnchantment() != null)
		{
			ItemStack stack = inv.getItem(0);
			ItemStack stack1 = stack.copy();
			final Enchantment enchantment = this.getEnchantment();

			if (checkEnchantableItem(stack1, enchantment, this.minLevel, this.maxLevel))
			{
				if (stack1.isEnchanted())
				{
					stack1.removeTagKey("Enchantments");
					stack1.removeTagKey("StoredEnchantments");
					int i = EnchantmentHelper.getTagEnchantmentLevel(enchantment, stack);

					Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack).entrySet().stream().filter((p) -> {
						return p.getKey() != enchantment;
					}).collect(Collectors.toMap(Entry::getKey, Entry::getValue));

					map.put(enchantment, i + 1);
					EnchantmentHelper.setEnchantments(map, stack1);
					stack1.setRepairCost(0);

					for (int j = 0; j < map.size(); ++j)
					{
						stack1.setRepairCost(AnvilMenu.calculateIncreasedRepairCost(stack1.getBaseRepairCost()));
					}
				}
				else
				{
					EnchantmentHelper.setEnchantments(ImmutableMap.of(enchantment, 1), stack1);
				}

				return stack1;
			}
		}

		return ItemStack.EMPTY;
	}

	public Ingredient getAddition()
	{
		return this.addition;
	}

	public String getEnchantmentId()
	{
		return this.enchantmentId;
	}

	@Nullable
	public Enchantment getEnchantment()
	{
		return this.enchantment;
	}

	public int getMinLevel()
	{
		return this.minLevel;
	}

	public int getMaxLevel()
	{
		return this.maxLevel;
	}

	@Override
	public boolean isSpecial()
	{
		return true;
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipes.ENCHANTMENT_UPGRADE.get();
	}

	public static class Serializer implements RecipeSerializer<EnchantmentUpgradeRecipe>
	{
		@Override
		public EnchantmentUpgradeRecipe fromJson(ResourceLocation recipeId, JsonObject json)
		{
			Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));
			String enchantmentId = GsonHelper.getAsString(json, "enchantment", "");
//			ResourceLocation resourcelocation = new ResourceLocation(enchantmentId);
//			Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(resourcelocation);

//			if (enchantment == null)
//			{
//				throw new JsonSyntaxException("Unknown enchantment '" + resourcelocation + "'");
//			}

			int max = Mth.clamp(GsonHelper.getAsInt(json, "maxLevel", 0), 0, 254);
			int min = Mth.clamp(GsonHelper.getAsInt(json, "minLevel", 0), 0, 254);
			return new EnchantmentUpgradeRecipe(recipeId, ingredient, enchantmentId, min, max);
		}

		@Override
		@Nonnull
		public EnchantmentUpgradeRecipe fromNetwork(@Nonnull ResourceLocation recipeId, FriendlyByteBuf buffer)
		{
			Ingredient ingredient = Ingredient.fromNetwork(buffer);
			String enchantmentId = buffer.readUtf(Short.MAX_VALUE);
//			ResourceLocation resourcelocation = new ResourceLocation(enchantmentId);
//			Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(resourcelocation);

//			if (enchantment == null)
//			{
//				throw new JsonSyntaxException("Unknown enchantment '" + resourcelocation + "'");
//			}

			int max = buffer.readInt();
			int min = buffer.readInt();
			return new EnchantmentUpgradeRecipe(recipeId, ingredient, enchantmentId, min, max);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, EnchantmentUpgradeRecipe recipe)
		{
			recipe.addition.toNetwork(buffer);
			buffer.writeUtf(recipe.enchantmentId);
			buffer.writeVarInt(recipe.maxLevel);
			buffer.writeVarInt(recipe.minLevel);
		}
	}
}