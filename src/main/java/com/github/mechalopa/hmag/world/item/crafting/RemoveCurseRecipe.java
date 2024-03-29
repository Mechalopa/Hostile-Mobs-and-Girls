package com.github.mechalopa.hmag.world.item.crafting;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.github.mechalopa.hmag.registry.ModRecipes;
import com.github.mechalopa.hmag.util.ModTags;

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

public class RemoveCurseRecipe extends AbstractUpgradeRecipe
{
	public RemoveCurseRecipe(ResourceLocation recipeId)
	{
		super(recipeId);
	}

	@Override
	public boolean matches(Container inv, Level level)
	{
		if (inv.getItem(0).is(ModTags.ItemTags.CURSE_REMOVE_TEMPLATES))
		{
			ItemStack stack = inv.getItem(1);
			ItemStack stack1 = inv.getItem(2);

			if (!stack.isEmpty() && !stack1.isEmpty() && stack1.getItem() != null && stack1.is(ModTags.ItemTags.CURSE_REMOVE_ITEMS) && !stack.is(ModTags.ItemTags.CURSE_UNREMOVABLES))
			{
				Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack);

				for (Entry<Enchantment, Integer> entry : map.entrySet())
				{
					Enchantment enchantment = entry.getKey();

					if (isRemovableCurse(enchantment))
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean isRemovableCurse(Enchantment enchantment)
	{
		return enchantment.isCurse() && !ModTags.checkTagContains(enchantment, ModTags.EnchantmentTags.UNREMOVABLE_CURSES);
	}

	@Override
	public ItemStack assemble(Container inv, RegistryAccess registryAccess)
	{
		ItemStack stack = inv.getItem(1);
		ItemStack stack1 = stack.copy();
		stack1.removeTagKey("Enchantments");
		stack1.removeTagKey("StoredEnchantments");

		Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack).entrySet().stream().filter((p) -> {
			return !isRemovableCurse(p.getKey());
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
	public boolean isTemplateIngredient(ItemStack stack)
	{
		return stack.is(ModTags.ItemTags.CURSE_REMOVE_TEMPLATES);
	}

	@Override
	public boolean isBaseIngredient(ItemStack stack)
	{
		return !stack.is(ModTags.ItemTags.CURSE_UNREMOVABLES);
	}

	@Override
	public boolean isAdditionIngredient(ItemStack stack)
	{
		return stack.is(ModTags.ItemTags.CURSE_REMOVE_ITEMS);
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipes.REMOVE_CURSE.get();
	}
}