package hmag.compat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import hmag.HMaG;
import hmag.item.EnchantmentUpgradeItem;
import hmag.recipe.EnchantmentUpgradeRecipe;
import hmag.recipe.RemoveCurseRecipe;
import hmag.util.ModTags;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SmithingRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

@JeiPlugin
public class JEIPlugin implements IModPlugin
{
	@Override
	public ResourceLocation getPluginUid()
	{
		return new ResourceLocation(HMaG.MODID, HMaG.MODID);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration)
	{
		final List<Item> items = ImmutableList.of(
				Items.DIAMOND_SWORD,
				Items.DIAMOND_PICKAXE,
				Items.DIAMOND_AXE,
				Items.DIAMOND_SHOVEL,
				Items.DIAMOND_HOE,
				Items.DIAMOND_CHESTPLATE,
				Items.DIAMOND_HELMET,
				Items.DIAMOND_LEGGINGS,
				Items.DIAMOND_BOOTS,
				Items.ELYTRA,
				Items.SHIELD,
				Items.BOW,
				Items.CROSSBOW,
				Items.TRIDENT,
				Items.FISHING_ROD,
				Items.SHEARS);

		@SuppressWarnings("resource")
		Iterable<IRecipe<?>> recipes = Minecraft.getInstance().level.getRecipeManager().getRecipes();
		List<SmithingRecipe> smithingRecipes = new ArrayList<SmithingRecipe>();
		boolean flag = false;
		boolean flag1 = false;

		for (IRecipe<?> recipe : recipes)
		{
			if (recipe != null)
			{
				if (recipe instanceof RemoveCurseRecipe && !flag)
				{
					flag = true;
					Ingredient ingredient = Ingredient.of(ModTags.CURSE_REMOVE_ITEMS);

					if (!ingredient.isEmpty())
					{
						for (Enchantment enchantment : ForgeRegistries.ENCHANTMENTS)
						{
							if (enchantment != null && enchantment.isCurse())
							{
								ItemStack stack1 = getEnchantableItemStack(registration, items, enchantment);

								if (!stack1.isEmpty())
								{
									List<ItemStack> stacks = Lists.newArrayList();

									for (int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); ++i)
									{
										if (i > 0)
										{
											ItemStack stack2 = stack1.copy();
											EnchantmentHelper.setEnchantments(ImmutableMap.of(enchantment, i), stack2);
											stacks.add(stack2);
										}
									}

									if (!stacks.isEmpty())
									{
										ResourceLocation resourcelocation = new ResourceLocation(HMaG.MODID, "jei." + recipe.getId().getPath() + "." + enchantment.getRegistryName().getNamespace() + "." + enchantment.getRegistryName().getPath());
										SmithingRecipe smithingRecipe = new SmithingRecipe(resourcelocation, Ingredient.of(stacks.stream()), ingredient, stack1);
										smithingRecipes.add(smithingRecipe);
									}
								}
							}
						}
					}
				}
				else if (recipe instanceof EnchantmentUpgradeRecipe && !flag1)
				{
					flag1 = true;
					final List<Item> items1 = ModTags.getTagAllElements(ModTags.ENCHANTMENT_UPGRADE_ITEMS);

					if (!items1.isEmpty())
					{
						int i = 0;

						for (Item item : items1)
						{
							if (item != null && item instanceof EnchantmentUpgradeItem)
							{
								EnchantmentUpgradeItem eui = (EnchantmentUpgradeItem)item;
								Enchantment[] enchantments = eui.getEnchantments();
								final int minLevel = eui.getMinLevel();
								final int maxLevel = eui.getMaxLevel();

								for (int j = 0; j < enchantments.length; ++j)
								{
									final Enchantment enchantment = enchantments[j];

									if (enchantment != null)
									{
										ItemStack stack1 = getEnchantableItemStack(registration, items, enchantment);

										if (!stack1.isEmpty())
										{
											for (int k = minLevel; k <= maxLevel; ++k)
											{
												ResourceLocation resourcelocation = new ResourceLocation(HMaG.MODID, "jei." + recipe.getId().getPath() + "." + i + "." + j + "." + k);
												ItemStack stack2 = stack1.copy();
												ItemStack stack3 = stack1.copy();

												if (k > 0)
												{
													EnchantmentHelper.setEnchantments(ImmutableMap.of(enchantment, k), stack2);
												}

												EnchantmentHelper.setEnchantments(ImmutableMap.of(enchantment, k + 1), stack3);
												SmithingRecipe smithingRecipe = new SmithingRecipe(resourcelocation, Ingredient.of(stack2), Ingredient.of(item), stack3);
												smithingRecipes.add(smithingRecipe);
											}
										}
									}
								}

								++i;
							}
						}
					}
				}

/*				else if (recipe instanceof EnchantmentUpgradeRecipe)
				{
					EnchantmentUpgradeRecipe enchantmentUpgradeRecipe = (EnchantmentUpgradeRecipe)recipe;
					Enchantment enchantment = enchantmentUpgradeRecipe.getEnchantment();

					if (enchantment != null)
					{
						ItemStack stack1 = getEnchantableItemStack(registration, items, enchantment);

						if (!stack1.isEmpty())
						{
							for (int i = enchantmentUpgradeRecipe.getMinLevel(); i <= enchantmentUpgradeRecipe.getMaxLevel(); ++i)
							{
								ResourceLocation resourcelocation = new ResourceLocation(HMaG.MODID, "jei." + enchantmentUpgradeRecipe.getId().getPath() + "." + i);
								ItemStack stack2 = stack1.copy();
								ItemStack stack3 = stack1.copy();

								if (i > 0)
								{
									EnchantmentHelper.setEnchantments(ImmutableMap.of(enchantment, i), stack2);
								}

								EnchantmentHelper.setEnchantments(ImmutableMap.of(enchantment, i + 1), stack3);
								SmithingRecipe smithingRecipe = new SmithingRecipe(resourcelocation, Ingredient.of(stack2), enchantmentUpgradeRecipe.getAddition(), stack3);
								smithingRecipes.add(smithingRecipe);
							}
						}
					}
				}*/

				if (flag && flag1)
				{
					break;
				}
			}
		}

		registration.addRecipes(smithingRecipes, VanillaRecipeCategoryUid.SMITHING);
	}

	private static ItemStack getEnchantableItemStack(IRecipeRegistration registration, List<Item> list, Enchantment enchantment)
	{
		for (Item item : list)
		{
			ItemStack stack = new ItemStack(item);

			if (enchantment.canEnchant(stack))
			{
				return stack;
			}
		}

		Collection<ItemStack> stacks = registration.getIngredientManager().getAllIngredients(VanillaTypes.ITEM);

		for (ItemStack stack2 : stacks)
		{
			if (!stack2.isEmpty() && stack2.getItem() != Items.ENCHANTED_BOOK && enchantment.canEnchant(stack2))
			{
				return stack2;
			}
		}

		return ItemStack.EMPTY;
	}
}