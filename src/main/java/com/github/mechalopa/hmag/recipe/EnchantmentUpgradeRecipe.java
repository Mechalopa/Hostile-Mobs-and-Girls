package com.github.mechalopa.hmag.recipe;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.item.EnchantmentUpgradeItem;
import com.github.mechalopa.hmag.item.EnchantmentUpgradeItem.Properties.EnchantmentUpgradeProp;
import com.github.mechalopa.hmag.registry.ModRecipes;
import com.github.mechalopa.hmag.util.ModTags;
import com.google.common.collect.ImmutableMap;
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

public class EnchantmentUpgradeRecipe extends SmithingRecipe
{
	private final ResourceLocation recipeId;

	public EnchantmentUpgradeRecipe(ResourceLocation recipeId)
	{
		super(recipeId, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
		this.recipeId = recipeId;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn)
	{
		ItemStack stack = inv.getItem(0);
		ItemStack stack1 = inv.getItem(1);

		if (!stack.isEmpty() && !(stack.getItem() == null || stack.getItem() == Items.ENCHANTED_BOOK) && !stack1.isEmpty() && stack1.getItem() != null && stack1.getItem() instanceof EnchantmentUpgradeItem && ModTags.checkTagContains(ModTags.ENCHANTMENT_UPGRADE_ITEMS, stack1.getItem()) && !ModTags.checkTagContains(ModTags.ENCHANTMENT_UPGRADEABLE_BLACKLIST, stack.getItem()))
		{
			final List<EnchantmentUpgradeProp> eups = ((EnchantmentUpgradeItem)stack1.getItem()).getEnchantmentUpgradeProps();

			if (!eups.isEmpty())
			{
				for (EnchantmentUpgradeProp eup : eups)
				{
					if (eup != null)
					{
						final Enchantment enchantment = eup.getEnchantment();

						if (enchantment != null && checkEnchantableItem(stack, enchantment, eup.getMinLevel(), eup.getMaxLevel()))
						{
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	private static boolean checkEnchantableItem(ItemStack stackIn, Enchantment enchantmentIn, int min, int max)
	{
		if (!stackIn.isEmpty() && enchantmentIn.canEnchant(stackIn))
		{
			int i = EnchantmentHelper.getItemEnchantmentLevel(enchantmentIn, stackIn);

			if (i == 0 && min == 0 && max >= 0)
			{
				if (EnchantmentHelper.isEnchantmentCompatible(EnchantmentHelper.getEnchantments(stackIn).keySet(), enchantmentIn))
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
	public ItemStack assemble(IInventory inv)
	{
		ItemStack stack = inv.getItem(1);

		if (stack.getItem() instanceof EnchantmentUpgradeItem)
		{
			final List<EnchantmentUpgradeProp> eups = ((EnchantmentUpgradeItem)stack.getItem()).getEnchantmentUpgradeProps();

			if (!eups.isEmpty())
			{
				for (EnchantmentUpgradeProp eup : eups)
				{
					if (eup != null)
					{
						final Enchantment enchantment = eup.getEnchantment();

						if (enchantment != null)
						{
							ItemStack stack1 = inv.getItem(0);

							if (checkEnchantableItem(stack1, enchantment, eup.getMinLevel(), eup.getMaxLevel()))
							{
								ItemStack stack2 = stack1.copy();

								if (stack1.isEnchanted())
								{
									stack2.removeTagKey("Enchantments");
									stack2.removeTagKey("StoredEnchantments");
									int j = EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack1);

									Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack1).entrySet().stream().filter((p) -> {
										return p.getKey() != enchantment;
									}).collect(Collectors.toMap(Entry::getKey, Entry::getValue));

									map.put(enchantment, j + 1);
									EnchantmentHelper.setEnchantments(map, stack2);
									stack2.setRepairCost(0);

									for (int k = 0; k < map.size(); ++k)
									{
										stack2.setRepairCost(RepairContainer.calculateIncreasedRepairCost(stack1.getBaseRepairCost()));
									}
								}
								else
								{
									EnchantmentHelper.setEnchantments(ImmutableMap.of(enchantment, 1), stack2);
								}

								return stack2;
							}
						}
					}
				}
			}
		}

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

	@Override
	public IRecipeSerializer<?> getSerializer()
	{
		return ModRecipes.ENCHANTMENT_UPGRADE.get();
	}

	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<EnchantmentUpgradeRecipe>
	{
		@Override
		public EnchantmentUpgradeRecipe fromJson(ResourceLocation recipeId, JsonObject json)
		{
			return new EnchantmentUpgradeRecipe(this.getRegistryName());
		}

		@Override
		@Nonnull
		public EnchantmentUpgradeRecipe fromNetwork(@Nonnull ResourceLocation recipeId, PacketBuffer buffer)
		{
			return new EnchantmentUpgradeRecipe(this.getRegistryName());
		}

		@Override
		public void toNetwork(PacketBuffer buffer, EnchantmentUpgradeRecipe recipe){}
	}
}

/*public class EnchantmentUpgradeRecipe extends SmithingRecipe
{
	private final Ingredient addition;
	private final Enchantment enchantment;
	private final int minLevel;
	private final int maxLevel;

	public EnchantmentUpgradeRecipe(ResourceLocation recipeId, Ingredient addition, Enchantment enchantmentIn, int min, int max)
	{
		super(recipeId, Ingredient.EMPTY, addition, ItemStack.EMPTY);
		this.addition = addition;
		this.enchantment = enchantmentIn;
		this.minLevel = min;
		this.maxLevel = max;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn)
	{
		if (this.getEnchantment() != null)
		{
			ItemStack stack = inv.getItem(0);
			final Enchantment ench = this.getEnchantment();

			if (!stack.isEmpty() && stack.getItem() != Items.ENCHANTED_BOOK && ench.canEnchant(stack) && this.addition.test(inv.getItem(1)))
			{
				int i = EnchantmentHelper.getItemEnchantmentLevel(ench, stack);

				if (i == 0 && this.minLevel == 0 && this.maxLevel >= 0)
				{
					if (EnchantmentHelper.isEnchantmentCompatible(EnchantmentHelper.getEnchantments(stack).keySet(), ench))
					{
						return true;
					}
				}
				else if (i >= this.minLevel && i <= this.maxLevel)
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
		if (this.getEnchantment() != null)
		{
			ItemStack stack = inv.getItem(0);
			ItemStack stack1 = stack.copy();
			final Enchantment ench = this.getEnchantment();

			if (stack.isEnchanted())
			{
				stack1.removeTagKey("Enchantments");
				stack1.removeTagKey("StoredEnchantments");
				int i = EnchantmentHelper.getItemEnchantmentLevel(ench, stack);

				Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack).entrySet().stream().filter((p) -> {
					return p.getKey() != ench;
				}).collect(Collectors.toMap(Entry::getKey, Entry::getValue));

				map.put(ench, i + 1);
				EnchantmentHelper.setEnchantments(map, stack1);
				stack1.setRepairCost(0);

				for (int j = 0; j < map.size(); ++j)
				{
					stack1.setRepairCost(RepairContainer.calculateIncreasedRepairCost(stack.getBaseRepairCost()));
				}
			}
			else
			{
				EnchantmentHelper.setEnchantments(ImmutableMap.of(ench, 1), stack1);
			}

			return stack1;
		}
		else
		{
			return ItemStack.EMPTY;
		}
	}

	public Ingredient getAddition()
	{
		return this.addition;
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
	public IRecipeSerializer<?> getSerializer()
	{
		return ModRecipes.ENCHANTMENT_UPGRADE.get();
	}

	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<EnchantmentUpgradeRecipe>
	{
		@Override
		public EnchantmentUpgradeRecipe fromJson(ResourceLocation recipeId, JsonObject json)
		{
			Ingredient ingredient = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "addition"));
			String s = JSONUtils.getAsString(json, "enchantment", "");
			ResourceLocation resourcelocation = new ResourceLocation(s);
			Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(resourcelocation);

			if (enchantment == null)
			{
				throw new JsonSyntaxException("Unknown enchantment '" + resourcelocation + "'");
			}

			int min = MathHelper.clamp(JSONUtils.getAsInt(json, "minLevel", 0), 0, 254);
			int max = MathHelper.clamp(JSONUtils.getAsInt(json, "maxLevel", min), 0, 254);
			return new EnchantmentUpgradeRecipe(recipeId, ingredient, enchantment, min, max);
		}

		@Override
		@Nonnull
		public EnchantmentUpgradeRecipe fromNetwork(@Nonnull ResourceLocation recipeId, PacketBuffer buffer)
		{
			Ingredient ingredient = Ingredient.fromNetwork(buffer);
			String s = buffer.readUtf(Short.MAX_VALUE);
			ResourceLocation resourcelocation = new ResourceLocation(s);
			Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(resourcelocation);

			if (enchantment == null)
			{
				throw new JsonSyntaxException("Unknown enchantment '" + resourcelocation + "'");
			}

			int min = buffer.readInt();
			int max = buffer.readInt();
			return new EnchantmentUpgradeRecipe(recipeId, ingredient, enchantment, min, max);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, EnchantmentUpgradeRecipe recipe)
		{
			recipe.addition.toNetwork(buffer);
			buffer.writeUtf(ForgeRegistries.ENCHANTMENTS.getKey(recipe.enchantment).toString());
			buffer.writeVarInt(recipe.minLevel);
			buffer.writeVarInt(recipe.maxLevel);
		}
	}
}*/