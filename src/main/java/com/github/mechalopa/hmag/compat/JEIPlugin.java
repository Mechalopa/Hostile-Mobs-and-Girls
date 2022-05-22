package com.github.mechalopa.hmag.compat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.world.item.EnchantmentUpgradeItem;
import com.github.mechalopa.hmag.world.item.EnchantmentUpgradeItem.Properties.EnchantmentUpgradeProp;
import com.github.mechalopa.hmag.world.item.crafting.EnchantmentUpgradeRecipe;
import com.github.mechalopa.hmag.world.item.crafting.RemoveCurseRecipe;
import com.github.mechalopa.hmag.world.item.crafting.SuspiciousStewUpgradeRecipe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
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
				Items.SHEARS,
				Items.FLINT_AND_STEEL,
				Items.SHULKER_BOX,
				Items.DIAMOND);

		Minecraft minecraft = Minecraft.getInstance();
		Iterable<Recipe<?>> recipes = minecraft.level.getRecipeManager().getRecipes();
		List<UpgradeRecipe> smithingRecipes = new ArrayList<UpgradeRecipe>();
		List<CraftingRecipe> shapelessRecipes = new ArrayList<CraftingRecipe>();
		boolean flag = false;
		boolean flag1 = false;
		boolean flag2 = false;

		for (Recipe<?> recipe : recipes)
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
								ItemStack stack1 = getEnchantableItemStack(registration, items, enchantment, ModTags.CURSE_REMOVABLE_BLACKLIST);

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
										ResourceLocation id = new ResourceLocation(HMaG.MODID, "jei." + recipe.getId().getPath() + "." + enchantment.getRegistryName().getNamespace() + "." + enchantment.getRegistryName().getPath());
										UpgradeRecipe recipe1 = new UpgradeRecipe(id, Ingredient.of(stacks.stream()), ingredient, stack1);
										smithingRecipes.add(recipe1);
									}
								}
							}
						}
					}
				}
				else if (recipe instanceof EnchantmentUpgradeRecipe && !flag1)
				{
					flag1 = true;
					Ingredient ingredient = Ingredient.of(ModTags.ENCHANTMENT_UPGRADE_ITEMS);

					if (!ingredient.isEmpty())
					{
						int i = 0;

						for (ItemStack stack : ingredient.getItems())
						{
							if (stack != null && !stack.isEmpty() && stack.getItem() != null && stack.getItem() instanceof EnchantmentUpgradeItem)
							{
								Item item = stack.getItem();
								final List<EnchantmentUpgradeProp> eups = ((EnchantmentUpgradeItem)item).getEnchantmentUpgradeProps();

								if (!eups.isEmpty())
								{
									int j = 0;

									for (EnchantmentUpgradeProp eup : eups)
									{
										if (eup != null)
										{
											Enchantment enchantment = eup.getEnchantment();

											if (enchantment != null)
											{
												final int minLevel = eup.getMinLevel();
												final int maxLevel = eup.getMaxLevel();
												ItemStack stack1 = getEnchantableItemStack(registration, items, enchantment, ModTags.ENCHANTMENT_UPGRADEABLE_BLACKLIST);

												if (!stack1.isEmpty())
												{
													for (int k = minLevel; k <= maxLevel; ++k)
													{
														ResourceLocation id = new ResourceLocation(HMaG.MODID, "jei." + recipe.getId().getPath() + "." + i + "." + j + "." + k);
														ItemStack stack2 = stack1.copy();
														ItemStack stack3 = stack1.copy();

														if (k > 0)
														{
															EnchantmentHelper.setEnchantments(ImmutableMap.of(enchantment, k), stack2);
														}

														EnchantmentHelper.setEnchantments(ImmutableMap.of(enchantment, k + 1), stack3);
														UpgradeRecipe recipe2 = new UpgradeRecipe(id, Ingredient.of(stack2), Ingredient.of(item), stack3);
														smithingRecipes.add(recipe2);
													}

													++j;
												}
											}
										}
									}

									++i;
								}
							}
						}
					}
				}
				else if (recipe instanceof SuspiciousStewUpgradeRecipe && !flag2)
				{
					flag2 = true;
					Ingredient ingredient = Ingredient.of(ModTags.SUSPICIOUS_STEW_UPGRADE_ITEMS);

					if (!ingredient.isEmpty())
					{
						String group = "jei." + HMaG.MODID + ".suspicious.stew.upgrade";
						NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY, Ingredient.of(Items.SUSPICIOUS_STEW), ingredient);
						ItemStack output = new ItemStack(Items.SUSPICIOUS_STEW, 1);
						CompoundTag compoundtag = output.getOrCreateTag();
						compoundtag.putBoolean(SuspiciousStewUpgradeRecipe.UPGRADED_KEY, true);
						ResourceLocation id = new ResourceLocation(HMaG.MODID, "jei." + recipe.getId().getPath());
						ShapelessRecipe recipe3 = new ShapelessRecipe(id, group, output, inputs);
						shapelessRecipes.add(recipe3);
					}
				}

				if (flag && flag1 && flag2)
				{
					break;
				}
			}
		}

		registration.addRecipes(RecipeTypes.SMITHING, smithingRecipes);
		registration.addRecipes(RecipeTypes.CRAFTING, shapelessRecipes);
	}

	private static ItemStack getEnchantableItemStack(IRecipeRegistration registration, List<Item> list, Enchantment enchantment, TagKey<Item> blacklist)
	{
		for (Item item : list)
		{
			ItemStack stack = new ItemStack(item);

			if (enchantment.canEnchant(stack) && !stack.is(blacklist))
			{
				return stack;
			}
		}

		Collection<ItemStack> stacks = registration.getIngredientManager().getAllIngredients(VanillaTypes.ITEM_STACK);

		for (ItemStack stack2 : stacks)
		{
			if (!stack2.isEmpty() && stack2.getItem() != Items.ENCHANTED_BOOK && enchantment.canEnchant(stack2) && !stack2.is(blacklist))
			{
				return stack2;
			}
		}

		return ItemStack.EMPTY;
	}
}