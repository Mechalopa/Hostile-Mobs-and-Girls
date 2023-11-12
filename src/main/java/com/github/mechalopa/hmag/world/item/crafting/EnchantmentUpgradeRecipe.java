package com.github.mechalopa.hmag.world.item.crafting;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.github.mechalopa.hmag.registry.ModRecipes;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.world.item.crafting.EnchantmentUpgradeItemManager.EnchantmentUpgradeItemProp;
import com.google.common.collect.ImmutableMap;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class EnchantmentUpgradeRecipe extends AbstractUpgradeRecipe
{
	public EnchantmentUpgradeRecipe(ResourceLocation recipeId)
	{
		super(recipeId);
	}

	@Override
	public boolean matches(Container inv, Level level)
	{
		ItemStack stack = inv.getItem(0);
		ItemStack stack1 = inv.getItem(1);
		ItemStack stack2 = inv.getItem(2);

		if (!stack.isEmpty() && stack.getItem() != null && !stack1.isEmpty() && !(stack1.getItem() == null || stack1.getItem() == Items.ENCHANTED_BOOK) && !stack1.is(ModTags.ItemTags.ENCHANTMENT_NOT_UPGRADEABLES) && !stack2.isEmpty() && stack2.getItem() != null && !EnchantmentUpgradeItemManager.getPropMap().isEmpty())
		{
			for (EnchantmentUpgradeItemProp prop : EnchantmentUpgradeItemManager.getPropMap().keySet())
			{
				if (prop.getEnchantment() != null && prop.getTemplate().equals(stack.getItem()) && prop.getAddition().equals(stack2.getItem()))
				{
					final Enchantment enchantment = prop.getEnchantment();

					if (checkEnchantableItem(stack1, enchantment, prop.getMinLevel(), prop.getMaxLevel()))
					{
						return true;
					}
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
	public ItemStack assemble(Container inv, RegistryAccess registryAccess)
	{
		for (EnchantmentUpgradeItemProp prop : EnchantmentUpgradeItemManager.getPropMap().keySet())
		{
			if (prop.getEnchantment() != null && prop.getTemplate().equals(inv.getItem(0).getItem()) && prop.getAddition().equals(inv.getItem(2).getItem()))
			{
				final Enchantment enchantment = prop.getEnchantment();
				ItemStack stack = inv.getItem(1);

				if (checkEnchantableItem(stack, enchantment, prop.getMinLevel(), prop.getMaxLevel()))
				{
					ItemStack stack1 = stack.copy();

					if (stack.isEnchanted())
					{
						stack1.removeTagKey("Enchantments");
						stack1.removeTagKey("StoredEnchantments");
						int j = EnchantmentHelper.getTagEnchantmentLevel(enchantment, stack);

						Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack).entrySet().stream().filter((p) -> {
							return p.getKey() != enchantment;
						}).collect(Collectors.toMap(Entry::getKey, Entry::getValue));

						map.put(enchantment, j + 1);
						EnchantmentHelper.setEnchantments(map, stack1);
						stack1.setRepairCost(0);

						for (int k = 0; k < map.size(); ++k)
						{
							stack1.setRepairCost(AnvilMenu.calculateIncreasedRepairCost(stack.getBaseRepairCost()));
						}
					}
					else
					{
						EnchantmentHelper.setEnchantments(ImmutableMap.of(enchantment, 1), stack1);
					}

					return stack1;
				}
			}
		}

		return ItemStack.EMPTY;
	}

	@Override
	public boolean isTemplateIngredient(ItemStack stack)
	{
		if (stack.getItem() != null && !EnchantmentUpgradeItemManager.getPropMap().isEmpty())
		{
			for (EnchantmentUpgradeItemProp prop : EnchantmentUpgradeItemManager.getPropMap().keySet())
			{
				if (prop.getEnchantment() != null && prop.getTemplate().equals(stack.getItem()))
				{
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean isBaseIngredient(ItemStack stack)
	{
		return stack.getItem() != Items.ENCHANTED_BOOK && !stack.is(ModTags.ItemTags.ENCHANTMENT_NOT_UPGRADEABLES);
	}

	@Override
	public boolean isAdditionIngredient(ItemStack stack)
	{
		if (stack.getItem() != null && !EnchantmentUpgradeItemManager.getPropMap().isEmpty())
		{
			for (EnchantmentUpgradeItemProp prop : EnchantmentUpgradeItemManager.getPropMap().keySet())
			{
				if (prop.getEnchantment() != null && prop.getAddition().equals(stack.getItem()))
				{
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipes.ENCHANTMENT_UPGRADE.get();
	}
}