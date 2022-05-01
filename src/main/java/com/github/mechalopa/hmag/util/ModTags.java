package com.github.mechalopa.hmag.util;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.github.mechalopa.hmag.HMaG;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class ModTags
{
	public static final ITag.INamedTag<Block> MONOLITH_SPAWNABLE_IN_LIGHT = makeBlockTag("monolith_spawnable_in_light");
	public static final ITag.INamedTag<Block> OGRE_IMMUNE = makeBlockTag("ogre_immune");

	public static final ITag.INamedTag<Item> CURSE_REMOVABLE_BLACKLIST = makeItemTag("curse_removable_blacklist");
	public static final ITag.INamedTag<Item> CURSE_REMOVE_ITEMS = makeItemTag("curse_remove_items");
	public static final ITag.INamedTag<Item> ENCHANTMENT_UPGRADE_ITEMS = makeItemTag("enchantment_upgrade_items");
	public static final ITag.INamedTag<Item> ENCHANTMENT_UPGRADEABLE_BLACKLIST = makeItemTag("enchantment_upgradeable_blacklist");

	public static final ITag.INamedTag<EntityType<?>> DIAMOND_TIER = makeEntityTag("tiered/diamond");
	public static final ITag.INamedTag<EntityType<?>> EMERALD_TIER = makeEntityTag("tiered/emerald");
	public static final ITag.INamedTag<EntityType<?>> GOLD_TIER = makeEntityTag("tiered/gold");
	public static final ITag.INamedTag<EntityType<?>> IRON_TIER = makeEntityTag("tiered/iron");
	public static final ITag.INamedTag<EntityType<?>> RUBY_TIER = makeEntityTag("tiered/ruby");
	public static final ITag.INamedTag<EntityType<?>> SAPPHIRE_TIER = makeEntityTag("tiered/sapphire");
	public static final ITag.INamedTag<EntityType<?>> SILVER_TIER = makeEntityTag("tiered/silver");
	public static final ITag.INamedTag<EntityType<?>> CREEPER_GIRL_REPLACEABLES = makeEntityTag("creeper_girl_replaceables");
	public static final ITag.INamedTag<EntityType<?>> CRIMSON_SLAUGHTERER_TARGET_ANIMAL_BLACKLIST = makeEntityTag("crimson_slaughterer_target_animal_blacklist");
	public static final ITag.INamedTag<EntityType<?>> DROWNED_GIRL_REPLACEABLES = makeEntityTag("drowned_girl_replaceables");
	public static final ITag.INamedTag<EntityType<?>> ENDER_EXECUTOR_REPLACEABLES = makeEntityTag("ender_executor_replaceables");
	public static final ITag.INamedTag<EntityType<?>> HARD_SNOWBALL_HURTS_EXTRA_TYPES = makeEntityTag("hard_snowball_hurts_extra_types");
	public static final ITag.INamedTag<EntityType<?>> HUSK_GIRL_REPLACEABLES = makeEntityTag("husk_girl_replaceables");
	public static final ITag.INamedTag<EntityType<?>> MONOLITH_TARGET_BLACKLIST = makeEntityTag("monolith_target_blacklist");
	public static final ITag.INamedTag<EntityType<?>> SAVAGEFANG_TARGET_BLACKLIST = makeEntityTag("savagefang_target_blacklist");
	public static final ITag.INamedTag<EntityType<?>> SKELETON_GIRL_REPLACEABLES = makeEntityTag("skeleton_girl_replaceables");
	public static final ITag.INamedTag<EntityType<?>> STRAY_GIRL_REPLACEABLES = makeEntityTag("stray_girl_replaceables");
	public static final ITag.INamedTag<EntityType<?>> TIERED = makeEntityTag("tiered");
	public static final ITag.INamedTag<EntityType<?>> WITHER_SKELETON_GIRL_REPLACEABLES = makeEntityTag("wither_skeleton_girl_replaceables");
	public static final ITag.INamedTag<EntityType<?>> ZOMBIE_GIRL_REPLACEABLES = makeEntityTag("zombie_girl_replaceables");

	public static final ResourceLocation FORGE_COPPER_INGOTS = new ResourceLocation("forge", "ingots/copper");
	public static final ResourceLocation FORGE_RUBY_GEMS = new ResourceLocation("forge", "gems/ruby");
	public static final ResourceLocation FORGE_SAPPHIRE_GEMS = new ResourceLocation("forge", "gems/sapphire");
	public static final ResourceLocation FORGE_SILVER_INGOTS = new ResourceLocation("forge", "ingots/silver");

	private static ITag.INamedTag<Block> makeBlockTag(String name)
	{
		return BlockTags.bind(HMaG.MODID + ":" + name);
	}

	private static ITag.INamedTag<Item> makeItemTag(String name)
	{
		return ItemTags.bind(HMaG.MODID + ":" + name);
	}

	private static ITag.INamedTag<EntityType<?>> makeEntityTag(String name)
	{
		return EntityTypeTags.bind(HMaG.MODID + ":" + name);
	}

	@Nullable
	public static ITag<Item> getItemTag(ResourceLocation r)
	{
		return ItemTags.getAllTags().getAllTags().get(r);
	}

	@Nullable
	public static Item getItem(ResourceLocation r)
	{
		return getItem(getItemTag(r));
	}

	@Nullable
	public static Item getItem(ITag<Item> tag)
	{
		Item item = null;

		if (tag != null)
		{
			List<Item> list = getTagAllElements(tag);

			if (list != null && !list.isEmpty())
			{
				item = list.get(0);
			}
		}

		return item;
	}

	public static <T> boolean checkTagContains(ITag<T> tag, T value)
	{
		if (tag != null && value != null)
		{
			try
			{
				if (tag.contains(value))
				{
					return true;
				}
			}
			catch (Exception e)
			{
				HMaG.LOGGER.warn("Broken tags -> " + tag.toString(), (Throwable)e);
				return false;
			}
		}

		return false;
	}

	public static <T> List<T> getTagAllElements(ITag<T> tag)
	{
		if (tag != null)
		{
			try
			{
				return tag.getValues();
			}
			catch (Exception e)
			{
				HMaG.LOGGER.warn("Broken tags -> " + tag.toString(), (Throwable)e);
				return Collections.emptyList();
			}
		}

		return Collections.emptyList();
	}
}