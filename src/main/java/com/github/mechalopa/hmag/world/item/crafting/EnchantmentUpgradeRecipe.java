package com.github.mechalopa.hmag.world.item.crafting;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.github.mechalopa.hmag.registry.ModRecipes;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.world.item.EnchantmentUpgradeItem;
import com.github.mechalopa.hmag.world.item.EnchantmentUpgradeItem.Properties.EnchantmentUpgradeProp;
import com.google.common.collect.ImmutableMap;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.LegacyUpgradeRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

@Deprecated(forRemoval = true)
public class EnchantmentUpgradeRecipe extends LegacyUpgradeRecipe
{
	private final ResourceLocation recipeId;

	public EnchantmentUpgradeRecipe(ResourceLocation recipeId)
	{
		super(recipeId, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY);
		this.recipeId = recipeId;
	}

	@Override
	public boolean matches(Container inv, Level level)
	{
		ItemStack stack = inv.getItem(0);
		ItemStack stack1 = inv.getItem(1);

		if (!stack.isEmpty() && !(stack.getItem() == null || stack.getItem() == Items.ENCHANTED_BOOK) && !stack1.isEmpty() && stack1.getItem() != null && stack1.getItem() instanceof EnchantmentUpgradeItem && stack1.is(ModTags.ItemTags.ENCHANTMENT_UPGRADE_ITEMS) && !stack.is(ModTags.ItemTags.ENCHANTMENT_NOT_UPGRADABLES))
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
			int i = EnchantmentHelper.getTagEnchantmentLevel(enchantmentIn, stackIn);

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
	public ItemStack assemble(Container inv, RegistryAccess registryAccess)
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
									int j = EnchantmentHelper.getTagEnchantmentLevel(enchantment, stack1);

									Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack1).entrySet().stream().filter((p) -> {
										return p.getKey() != enchantment;
									}).collect(Collectors.toMap(Entry::getKey, Entry::getValue));

									map.put(enchantment, j + 1);
									EnchantmentHelper.setEnchantments(map, stack2);
									stack2.setRepairCost(0);

									for (int k = 0; k < map.size(); ++k)
									{
										stack2.setRepairCost(AnvilMenu.calculateIncreasedRepairCost(stack1.getBaseRepairCost()));
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
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipes.ENCHANTMENT_UPGRADE.get();
	}
}