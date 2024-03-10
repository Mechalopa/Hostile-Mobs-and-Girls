package com.github.mechalopa.hmag.util;

import com.github.mechalopa.hmag.HMaG;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTags
{
	public static final class BlockTags
	{
		public static final TagKey<Block> GIANT_MUMMY_DESTROYABLES = createBlockTag("giant_mummy_destroyables");
		public static final TagKey<Block> GLARYADS_SPAWNABLE_ON = createBlockTag("glaryads_spawnable_on");
		public static final TagKey<Block> OGRE_IMMUNE = createBlockTag("ogre_immune");
		public static final TagKey<Block> SOUL_KASHA_SPAWNABLE_ON = createBlockTag("soul_kasha_spawnable_on");
	}

	public static final class ItemTags
	{
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
		public static final TagKey<Item> TOTEM_OF_REPULSE_COSTS = createItemTag("totem_of_repulse_costs");
	}

	public static final class EntityTypeTags
	{
		public static final TagKey<EntityType<?>> CREEPER_GIRL_REPLACEABLES = createEntityTypeTag("creeper_girl_replaceables");
		public static final TagKey<EntityType<?>> CRIMSON_SLAUGHTERER_TARGET_ANIMAL_BLACKLIST = createEntityTypeTag("crimson_slaughterer_target_animal_blacklist");
		public static final TagKey<EntityType<?>> DROWNED_GIRL_REPLACEABLES = createEntityTypeTag("drowned_girl_replaceables");
		public static final TagKey<EntityType<?>> ENDER_EXECUTOR_REPLACEABLES = createEntityTypeTag("ender_executor_replaceables");
		public static final TagKey<EntityType<?>> ENDER_RAGE_IMMUNE_BLACKLIST = createEntityTypeTag("ender_rage_immune_blacklist");
		public static final TagKey<EntityType<?>> ENDER_RAGE_IMMUNE_WHITELIST = createEntityTypeTag("ender_rage_immune_whitelist");
		public static final TagKey<EntityType<?>> HARD_SNOWBALL_HURTS_EXTRA_TYPES = createEntityTypeTag("hard_snowball_hurts_extra_types");
		public static final TagKey<EntityType<?>> HUSK_GIRL_REPLACEABLES = createEntityTypeTag("husk_girl_replaceables");
		public static final TagKey<EntityType<?>> INK_SPIT_IMMUNE = createEntityTypeTag("ink_spit_immune");
		public static final TagKey<EntityType<?>> KASHA_TARGETS = createEntityTypeTag("kasha_targets");
		public static final TagKey<EntityType<?>> KASHA_TARGETS_BABY_ONLY = createEntityTypeTag("kasha_targets_baby_only");
		public static final TagKey<EntityType<?>> MELTY_MONSTER_AVOIDS = createEntityTypeTag("melty_monster_avoids");
		public static final TagKey<EntityType<?>> MONOLITH_ROAR_IMMUNE = createEntityTypeTag("monolith_roar_immune");
		public static final TagKey<EntityType<?>> MONOLITH_TARGET_BLACKLIST = createEntityTypeTag("monolith_target_blacklist");
		public static final TagKey<EntityType<?>> SAVAGEFANG_TARGET_BLACKLIST = createEntityTypeTag("savagefang_target_blacklist");
		public static final TagKey<EntityType<?>> SKELETON_GIRL_REPLACEABLES = createEntityTypeTag("skeleton_girl_replaceables");
		public static final TagKey<EntityType<?>> SNOW_CANINE_TARGETS = createEntityTypeTag("snow_canine_targets");
		public static final TagKey<EntityType<?>> STRAY_GIRL_REPLACEABLES = createEntityTypeTag("stray_girl_replaceables");
		public static final TagKey<EntityType<?>> WITHER_SKELETON_GIRL_REPLACEABLES = createEntityTypeTag("wither_skeleton_girl_replaceables");
		public static final TagKey<EntityType<?>> ZOMBIE_GIRL_REPLACEABLES = createEntityTypeTag("zombie_girl_replaceables");
	}

	public static final class EnchantmentTags
	{
		public static final TagKey<Enchantment> INCOMPATIBLE_WITH_ANTI_AIR = createEnchantmentTag("incompatible_with_anti_air");
		public static final TagKey<Enchantment> INCOMPATIBLE_WITH_HEALTH_BOOST = createEnchantmentTag("incompatible_with_health_boost");
		public static final TagKey<Enchantment> INCOMPATIBLE_WITH_WATER_ASPECT = createEnchantmentTag("incompatible_with_water_aspect");
		public static final TagKey<Enchantment> UNREMOVABLE_CURSES = createEnchantmentTag("unremovable_curses");
	}

	public static final class MobEffectTags
	{
		public static final TagKey<MobEffect> ALRAUNE_IMMUNE_TO = createMobEffectTag("alraune_immune_to");
		public static final TagKey<MobEffect> CATOBLEPAS_IMMUNE_TO = createMobEffectTag("catoblepas_immune_to");
		public static final TagKey<MobEffect> CRIMSON_SLAUGHTERER_IMMUNE_TO = createMobEffectTag("crimson_slaughterer_immune_to");
		public static final TagKey<MobEffect> DYSSOMNIA_IMMUNE_TO = createMobEffectTag("dyssomnia_immune_to");
		public static final TagKey<MobEffect> MONOLITH_IMMUNE_TO = createMobEffectTag("monolith_immune_to");
		public static final TagKey<MobEffect> RANDOMBERRY_GIVES = createMobEffectTag("randomberry_gives");
		public static final TagKey<MobEffect> SPIDER_NEST_IMMUNE_TO = createMobEffectTag("spider_nest_immune_to");
		public static final TagKey<MobEffect> SCORPION_IMMUNE_TO = createMobEffectTag("scorpion_immune_to");
		public static final TagKey<MobEffect> UNREMOVABLE_EFFECTS = createMobEffectTag("unremovable_effects");
		public static final TagKey<MobEffect> WITHER_GHOST_IMMUNE_TO = createMobEffectTag("wither_ghost_immune_to");
	}

	public static final class BiomeTags
	{
		public static final TagKey<Biome> MELTS_JACK_FROSTS_BLACKLIST = createBiomeTag("melts_jack_frosts_blacklist");
		public static final TagKey<Biome> IS_BADLANDS = createBiomeTag("is_badlands");
		public static final TagKey<Biome> IS_COLD = createBiomeTag("is_cold");
		public static final TagKey<Biome> IS_PLAINS = createBiomeTag("is_plains");
		public static final TagKey<Biome> IS_SANDY = createBiomeTag("is_sandy");
		public static final TagKey<Biome> IS_SAVANNA = createBiomeTag("is_savanna");
		public static final TagKey<Biome> NO_MOB_REPLACEMENTS = createBiomeTag("no_mob_replacements");
		public static final TagKey<Biome> SUFFOCATES_SWAMPERS_BLACKLIST = createBiomeTag("suffocates_swampers_blacklist");
	}

	public static final class StructureTags
	{
		public static final TagKey<Structure> GIANT_MUMMIES_SPAWN_IN = createStructureTag("giant_mummies_spawn_in");
		public static final TagKey<Structure> MONOLITHS_SPAWN_IN = createStructureTag("monoliths_spawn_in");
	}

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

	private static TagKey<Enchantment> createEnchantmentTag(String name)
	{
		return TagKey.create(Registry.ENCHANTMENT_REGISTRY, new ResourceLocation(HMaG.MODID, name));
	}

	private static TagKey<MobEffect> createMobEffectTag(String name)
	{
		return TagKey.create(Registry.MOB_EFFECT_REGISTRY, new ResourceLocation(HMaG.MODID, name));
	}

	private static TagKey<Biome> createBiomeTag(String name)
	{
		return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(HMaG.MODID, name));
	}

	private static TagKey<Structure> createStructureTag(String name)
	{
		return TagKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(HMaG.MODID, name));
	}

	public static boolean checkTagContains(Enchantment enchantment, TagKey<Enchantment> tag)
	{
		return enchantment != null && tag != null && ForgeRegistries.ENCHANTMENTS.getHolder(enchantment).orElseThrow().is(tag);
	}

	public static boolean checkTagContains(MobEffect effect, TagKey<MobEffect> tag)
	{
		return effect != null && tag != null && ForgeRegistries.MOB_EFFECTS.getHolder(effect).orElseThrow().is(tag);
	}
}