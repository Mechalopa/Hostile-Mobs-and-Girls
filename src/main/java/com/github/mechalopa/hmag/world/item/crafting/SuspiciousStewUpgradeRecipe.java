package com.github.mechalopa.hmag.world.item.crafting;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.registry.ModRecipes;
import com.github.mechalopa.hmag.util.ModTags;
import com.google.common.base.Strings;

import net.minecraft.ResourceLocationException;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class SuspiciousStewUpgradeRecipe extends CustomRecipe
{
	public static final String UPGRADED_KEY = HMaG.MODID + ".upgraded";

	public SuspiciousStewUpgradeRecipe(ResourceLocation recipeId, CraftingBookCategory category)
	{
		super(recipeId, category);
	}

	@Override
	public boolean matches(CraftingContainer inv, Level level)
	{
		boolean flag = false;
		boolean flag1 = false;

		for (int i = 0; i < inv.getContainerSize(); ++i)
		{
			ItemStack stack = inv.getItem(i);

			if (!stack.isEmpty())
			{
				if (stack.is(Items.SUSPICIOUS_STEW) && (stack.hasTag() && !stack.getTag().getBoolean(UPGRADED_KEY)) && !flag)
				{
					flag = true;
				}
				else
				{
					if (!stack.is(ModTags.ItemTags.SUSPICIOUS_STEW_UPGRADE_ITEMS) || flag1)
					{
						return false;
					}

					flag1 = true;
				}
			}
		}

		return flag && flag1;
	}

	@Override
	public ItemStack assemble(CraftingContainer inv, RegistryAccess registryAccess)
	{
		ItemStack stack = ItemStack.EMPTY;

		for (int i = 0; i < inv.getContainerSize(); ++i)
		{
			ItemStack stack1 = inv.getItem(i);

			if (!stack1.isEmpty() && stack1.is(Items.SUSPICIOUS_STEW))
			{
				stack = stack1.copy();
				break;
			}
		}

		if (!stack.isEmpty())
		{
			CompoundTag compoundtag = stack.getOrCreateTag();
			compoundtag.putBoolean(UPGRADED_KEY, true);

			if (compoundtag.contains("Effects"))
			{
				ListTag listtag = compoundtag.getList("Effects", 10);
				ItemStack stack2 = stack.copy();
				CompoundTag compoundtag1 = stack2.getOrCreateTag();
				compoundtag1.remove("Effects");
				ListTag listtag1 = compoundtag1.getList("Effects", 9);

				for (int i = 0; i < listtag.size(); ++i)
				{
					int j = 0;
					CompoundTag compoundtag2 = listtag.getCompound(i);

					if (compoundtag2.contains("EffectDuration"))
					{
						j = compoundtag2.getInt("EffectDuration");
					}

					byte b0 = compoundtag2.getByte("EffectId");
					MobEffect mobeffect = MobEffect.byId(b0);
					String name = compoundtag2.getString("forge:effect_id");

					if (!Strings.isNullOrEmpty(name))
					{
						try
						{
							mobeffect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(name));
						}
						catch (ResourceLocationException e){}
					}

					if (mobeffect != null && j > 0)
					{
						CompoundTag compoundtag3 = new CompoundTag();
						compoundtag3.putByte("EffectId", b0);
						ResourceLocation mobeffectid = ForgeRegistries.MOB_EFFECTS.getKey(mobeffect);

						if (mobeffectid != null)
						{
							name = mobeffectid.toString();
						}

						compoundtag3.putString("forge:effect_id", name);
						compoundtag3.putInt("EffectDuration", mobeffect.isInstantenous() ? j + 5 : j * 5);
						listtag1.add(compoundtag3);
					}
				}

				if (!listtag1.isEmpty())
				{
					compoundtag1.put("Effects", listtag1);
					return stack2;
				}
			}
		}

		return stack;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height)
	{
		return width * height >= 2;
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipes.SUSPICIOUS_STEW_UPGRADE.get();
	}
}