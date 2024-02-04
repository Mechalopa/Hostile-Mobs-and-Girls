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
	public static final TagKey<Block> GLARYADS_SPAWNABLE_ON = createBlockTag("glaryads_spawnable_on");
	public static final TagKey<Block> OGRE_IMMUNE = createBlockTag("ogre_immune");

	public static final TagKey<Item> ANCIENT_ARMOR_REPAIR_ITEMS = createItemTag("ancient_armor_repair_items");
	public static final TagKey<Item> ANCIENT_SHIELD_REPAIR_ITEMS = createItemTag("ancient_shield_repair_items");
	public static final TagKey<Item> CRIMSON_BOW_REPAIR_ITEMS = createItemTag("crimson_bow_repair_items");
	public static final TagKey<Item> CURSE_REMOVE_ITEMS = createItemTag("curse_remove_items");
	public static final TagKey<Item> CURSE_UNREMOVABLES = createItemTag("curse_unremovables");
	public static final TagKey<Item> ENCHANTMENT_NOT_UPGRADABLES = createItemTag("enchantment_not_upgradables");
	public static final TagKey<Item> ENCHANTMENT_UPGRADE_ITEMS = createItemTag("enchantment_upgrade_items");
	public static final TagKey<Item> FORTRESS_SHIELD_REPAIR_ITEMS = createItemTag("fortress_shield_repair_items");
	public static final TagKey<Item> GLARYAD_TEMPT_ITEMS = createItemTag("glaryad_tempt_items");
	public static final TagKey<Item> INSOMNIA_ITEMS = createItemTag("insomnia_items");
	public static final TagKey<Item> INSOMNIA_SWORD_REPAIR_ITEMS = createItemTag("insomnia_sword_repair_items");
	public static final TagKey<Item> NECROTIC_CHAINMAIL_ARMOR_REPAIR_ITEMS = createItemTag("necrotic_chainmail_armor_repair_items");
	public static final TagKey<Item> NEMESIS_BLADE_REPAIR_ITEMS = createItemTag("nemesis_blade_repair_items");
	public static final TagKey<Item> SUSPICIOUS_STEW_UPGRADE_ITEMS = createItemTag("suspicious_stew_upgrade_items");

	public static final TagKey<EntityType<?>> CREEPER_GIRL_REPLACEABLES = createEntityTypeTag("creeper_girl_replaceables");
	public static final TagKey<EntityType<?>> CRIMSON_SLAUGHTERER_TARGET_ANIMAL_BLACKLIST = createEntityTypeTag("crimson_slaughterer_target_animal_blacklist");
	public static final TagKey<EntityType<?>> DROWNED_GIRL_REPLACEABLES = createEntityTypeTag("drowned_girl_replaceables");
	public static final TagKey<EntityType<?>> ENDER_EXECUTOR_REPLACEABLES = createEntityTypeTag("ender_executor_replaceables");
	public static final TagKey<EntityType<?>> ENDER_RAGE_IMMUNE_BLACKLIST = createEntityTypeTag("ender_rage_immune_blacklist");
	public static final TagKey<EntityType<?>> ENDER_RAGE_IMMUNE_WHITELIST = createEntityTypeTag("ender_rage_immune_whitelist");
	public static final TagKey<EntityType<?>> HARD_SNOWBALL_HURTS_EXTRA_TYPES = createEntityTypeTag("hard_snowball_hurts_extra_types");
	public static final TagKey<EntityType<?>> HUSK_GIRL_REPLACEABLES = createEntityTypeTag("husk_girl_replaceables");
	public static final TagKey<EntityType<?>> MELTY_MONSTER_AVOID_MOBS = createEntityTypeTag("melty_monster_avoid_mobs");
	public static final TagKey<EntityType<?>> MONOLITH_TARGET_BLACKLIST = createEntityTypeTag("monolith_target_blacklist");
	public static final TagKey<EntityType<?>> SAVAGEFANG_TARGET_BLACKLIST = createEntityTypeTag("savagefang_target_blacklist");
	public static final TagKey<EntityType<?>> SKELETON_GIRL_REPLACEABLES = createEntityTypeTag("skeleton_girl_replaceables");
	public static final TagKey<EntityType<?>> STRAY_GIRL_REPLACEABLES = createEntityTypeTag("stray_girl_replaceables");
	public static final TagKey<EntityType<?>> WITHER_SKELETON_GIRL_REPLACEABLES = createEntityTypeTag("wither_skeleton_girl_replaceables");
	public static final TagKey<EntityType<?>> ZOMBIE_GIRL_REPLACEABLES = createEntityTypeTag("zombie_girl_replaceables");

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
}