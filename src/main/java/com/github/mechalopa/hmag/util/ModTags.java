package com.github.mechalopa.hmag.util;

import com.github.mechalopa.hmag.HMaG;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags
{
	public static final TagKey<Block> MONOLITH_SPAWNABLE_IN_LIGHT = createBlockTag("monolith_spawnable_in_light");
	public static final TagKey<Block> OGRE_IMMUNE = createBlockTag("ogre_immune");

	public static final TagKey<Item> CURSE_REMOVABLE_BLACKLIST = createItemTag("curse_removable_blacklist");
	public static final TagKey<Item> CURSE_REMOVE_ITEMS = createItemTag("curse_remove_items");
	public static final TagKey<Item> ENCHANTMENT_UPGRADE_ITEMS = createItemTag("enchantment_upgrade_items");
	public static final TagKey<Item> ENCHANTMENT_UPGRADEABLE_BLACKLIST = createItemTag("enchantment_upgradeable_blacklist");

	public static final TagKey<EntityType<?>> DIAMOND_TIER = createEntityTypeTag("tiered/diamond");
	public static final TagKey<EntityType<?>> EMERALD_TIER = createEntityTypeTag("tiered/emerald");
	public static final TagKey<EntityType<?>> GOLD_TIER = createEntityTypeTag("tiered/gold");
	public static final TagKey<EntityType<?>> IRON_TIER = createEntityTypeTag("tiered/iron");
	public static final TagKey<EntityType<?>> RUBY_TIER = createEntityTypeTag("tiered/ruby");
	public static final TagKey<EntityType<?>> SAPPHIRE_TIER = createEntityTypeTag("tiered/sapphire");
	public static final TagKey<EntityType<?>> SILVER_TIER = createEntityTypeTag("tiered/silver");
	public static final TagKey<EntityType<?>> CREEPER_GIRL_REPLACEABLES = createEntityTypeTag("creeper_girl_replaceables");
	public static final TagKey<EntityType<?>> CRIMSON_SLAUGHTERER_TARGET_ANIMAL_BLACKLIST = createEntityTypeTag("crimson_slaughterer_target_animal_blacklist");
	public static final TagKey<EntityType<?>> DROWNED_GIRL_REPLACEABLES = createEntityTypeTag("drowned_girl_replaceables");
	public static final TagKey<EntityType<?>> ENDER_EXECUTOR_REPLACEABLES = createEntityTypeTag("ender_executor_replaceables");
	public static final TagKey<EntityType<?>> HARD_SNOWBALL_HURTS_EXTRA_TYPES = createEntityTypeTag("hard_snowball_hurts_extra_types");
	public static final TagKey<EntityType<?>> HUSK_GIRL_REPLACEABLES = createEntityTypeTag("husk_girl_replaceables");
	public static final TagKey<EntityType<?>> MONOLITH_TARGET_BLACKLIST = createEntityTypeTag("monolith_target_blacklist");
	public static final TagKey<EntityType<?>> SAVAGEFANG_TARGET_BLACKLIST = createEntityTypeTag("savagefang_target_blacklist");
	public static final TagKey<EntityType<?>> SKELETON_GIRL_REPLACEABLES = createEntityTypeTag("skeleton_girl_replaceables");
	public static final TagKey<EntityType<?>> STRAY_GIRL_REPLACEABLES = createEntityTypeTag("stray_girl_replaceables");
	public static final TagKey<EntityType<?>> TIERED = createEntityTypeTag("tiered");
	public static final TagKey<EntityType<?>> WITHER_SKELETON_GIRL_REPLACEABLES = createEntityTypeTag("wither_skeleton_girl_replaceables");
	public static final TagKey<EntityType<?>> ZOMBIE_GIRL_REPLACEABLES = createEntityTypeTag("zombie_girl_replaceables");

	public static final ResourceLocation FORGE_COPPER_INGOTS = new ResourceLocation("forge", "ingots/copper");
	public static final ResourceLocation FORGE_RUBY_GEMS = new ResourceLocation("forge", "gems/ruby");
	public static final ResourceLocation FORGE_SAPPHIRE_GEMS = new ResourceLocation("forge", "gems/sapphire");
	public static final ResourceLocation FORGE_SILVER_INGOTS = new ResourceLocation("forge", "ingots/silver");

	private static TagKey<Block> createBlockTag(String name)
	{
		return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(HMaG.MODID, name));
	}

	private static TagKey<Item> createItemTag(String name)
	{
		return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(HMaG.MODID, name));
	}

	private static TagKey<EntityType<?>> createEntityTypeTag(String name)
	{
		return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(HMaG.MODID, name));
	}

//	@Nullable
//	public static ITag<Item> getItemTag(ResourceLocation r)
//	{
//		return ItemTags.getAllTags().getAllTags().get(r);
//	}
//
//	@Nullable
//	public static Item getItem(ResourceLocation r)
//	{
//		return getItem(getItemTag(r));
//	}
//
//	@Nullable
//	public static Item getItem(ITag<Item> tag)
//	{
//		Item item = null;
//
//		if (tag != null)
//		{
//			List<Item> list = getTagAllElements(tag);
//
//			if (list != null && !list.isEmpty())
//			{
//				item = list.get(0);
//			}
//		}
//
//		return item;
//	}
//
//	public static <T> boolean checkTagContains(ITag<T> tag, T value)
//	{
//		if (tag != null && value != null)
//		{
//			try
//			{
//				if (tag.contains(value))
//				{
//					return true;
//				}
//			}
//			catch (Exception e)
//			{
//				HMaG.LOGGER.warn("Broken tags -> " + tag.toString(), e);
//				return false;
//			}
//		}
//
//		return false;
//	}
//
//	public static <T> List<T> getTagAllElements(ITag<T> tag)
//	{
//		if (tag != null)
//		{
//			try
//			{
//				return tag.getValues();
//			}
//			catch (Exception e)
//			{
//				HMaG.LOGGER.warn("Broken tags -> " + tag.toString(), e);
//				return Collections.emptyList();
//			}
//		}
//
//		return Collections.emptyList();
//	}
}