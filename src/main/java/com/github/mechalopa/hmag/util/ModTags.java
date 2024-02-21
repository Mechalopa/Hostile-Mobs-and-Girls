package com.github.mechalopa.hmag.util;

import com.github.mechalopa.hmag.HMaG;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
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
	}

	public static final class ItemTags
	{
		public static final TagKey<Item> ANCIENT_ARMOR_REPAIR_ITEMS = createItemTag("ancient_armor_repair_items");
		public static final TagKey<Item> ANCIENT_SHIELD_REPAIR_ITEMS = createItemTag("ancient_shield_repair_items");
		public static final TagKey<Item> CRIMSON_BOW_REPAIR_ITEMS = createItemTag("crimson_bow_repair_items");
		public static final TagKey<Item> CURSE_REMOVE_ITEMS = createItemTag("curse_remove_items");
		public static final TagKey<Item> CURSE_REMOVE_TEMPLATES = createItemTag("curse_remove_templates");
		public static final TagKey<Item> CURSE_UNREMOVABLES = createItemTag("curse_unremovables");
		public static final TagKey<Item> ENCHANTMENT_NOT_UPGRADABLES = createItemTag("enchantment_not_upgradables");
		public static final TagKey<Item> FORTRESS_SHIELD_REPAIR_ITEMS = createItemTag("fortress_shield_repair_items");
		public static final TagKey<Item> GLARYAD_TEMPT_ITEMS = createItemTag("glaryad_tempt_items");
		public static final TagKey<Item> INSOMNIA_ITEMS = createItemTag("insomnia_items");
		public static final TagKey<Item> INSOMNIA_SWORD_REPAIR_ITEMS = createItemTag("insomnia_sword_repair_items");
		public static final TagKey<Item> NECROTIC_CHAINMAIL_ARMOR_REPAIR_ITEMS = createItemTag("necrotic_chainmail_armor_repair_items");
		public static final TagKey<Item> NEMESIS_BLADE_REPAIR_ITEMS = createItemTag("nemesis_blade_repair_items");
		public static final TagKey<Item> SUSPICIOUS_STEW_UPGRADE_ITEMS = createItemTag("suspicious_stew_upgrade_items");
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

	public static final class DamageTypeTags
	{
		public static final TagKey<DamageType> ALRAUNE_VULNERABLE_TO = createDamageTypeTag("alraune_vulnerable_to");
		public static final TagKey<DamageType> BYPASSES_ENDER_EXECUTOR_DAMAGE_REDUCING = createDamageTypeTag("bypasses_ender_executor_damage_reducing");
		public static final TagKey<DamageType> CRIMSON_SLAUGHTERER_RESISTANT_TO = createDamageTypeTag("crimson_slaughterer_resistant_to");
		public static final TagKey<DamageType> CURSED_DOLL_VULNERABLE_TO = createDamageTypeTag("cursed_doll_vulnerable_to");
		public static final TagKey<DamageType> DOGU_IMMUNE_TO = createDamageTypeTag("dogu_immune_to");
		public static final TagKey<DamageType> DOGU_RESISTANT_TO = createDamageTypeTag("dogu_resistant_to");
		public static final TagKey<DamageType> DULLAHAN_RESISTANT_TO = createDamageTypeTag("dullahan_resistant_to");
		public static final TagKey<DamageType> FORTRESS_KEEPER_RESISTANT_TO = createDamageTypeTag("fortress_keeper_resistant_to");
		public static final TagKey<DamageType> GIANT_MUMMY_RESISTANT_TO = createDamageTypeTag("giant_mummy_resistant_to");
		public static final TagKey<DamageType> GIANT_MUMMY_VULNERABLE_TO = createDamageTypeTag("giant_mummy_vulnerable_to");
		public static final TagKey<DamageType> GLARYAD_VULNERABLE_TO = createDamageTypeTag("glaryad_vulnerable_to");
		public static final TagKey<DamageType> HORNET_VULNERABLE_TO = createDamageTypeTag("hornet_vulnerable_to");
		public static final TagKey<DamageType> IMP_RESISTANT_TO = createDamageTypeTag("imp_resistant_to");
		public static final TagKey<DamageType> JACK_FROST_VULNERABLE_TO = createDamageTypeTag("jack_frost_vulnerable_to");
		public static final TagKey<DamageType> JIANGSHI_RESISTANT_TO = createDamageTypeTag("jiangshi_resistant_to");
		public static final TagKey<DamageType> LICH_RESISTANT_TO = createDamageTypeTag("lich_resistant_to");
		public static final TagKey<DamageType> MAGICAL_SLIME_RESISTANT_TO = createDamageTypeTag("magical_slime_resistant_to");
		public static final TagKey<DamageType> MONOLITH_RESISTANT_TO = createDamageTypeTag("monolith_resistant_to");
		public static final TagKey<DamageType> NIGHTWALKER_RESISTANT_TO = createDamageTypeTag("nightwalker_resistant_to");
		public static final TagKey<DamageType> OGRE_HIGHLY_RESISTANT_TO = createDamageTypeTag("ogre_highly_resistant_to");
		public static final TagKey<DamageType> OGRE_RESISTANT_TO = createDamageTypeTag("ogre_resistant_to");
		public static final TagKey<DamageType> SLIME_GIRL_RESISTANT_TO = createDamageTypeTag("slime_girl_resistant_to");
		public static final TagKey<DamageType> SNOW_CANINE_RESISTANT_TO = createDamageTypeTag("snow_canine_resistant_to");
		public static final TagKey<DamageType> SPIDER_NEST_RESISTANT_TO = createDamageTypeTag("spider_nest_resistant_to");
		public static final TagKey<DamageType> SPIDER_NEST_VULNERABLE_TO = createDamageTypeTag("spider_nest_vulnerable_to");
		public static final TagKey<DamageType> SWAMPER_RESISTANT_TO = createDamageTypeTag("swamper_resistant_to");
		public static final TagKey<DamageType> SWAMPER_VULNERABLE_TO = createDamageTypeTag("swamper_vulnerable_to");
		public static final TagKey<DamageType> TRIGGERS_OGRE_DESTROYING = createDamageTypeTag("triggers_ogre_destroying");
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
		public static final TagKey<Biome> IS_BADLANDS = createBiomeTag("is_badlands");
		public static final TagKey<Biome> IS_COLD = createBiomeTag("is_cold");
		public static final TagKey<Biome> IS_PLAINS = createBiomeTag("is_plains");
		public static final TagKey<Biome> IS_SANDY = createBiomeTag("is_sandy");
		public static final TagKey<Biome> IS_SAVANNA = createBiomeTag("is_savanna");
		public static final TagKey<Biome> JACK_FROST_MELTS = createBiomeTag("jack_frost_melts");
		public static final TagKey<Biome> NO_MOB_REPLACEMENTS = createBiomeTag("no_mob_replacements");
		public static final TagKey<Biome> SWAMPER_SUFFOCATES = createBiomeTag("swamper_suffocates");
	}

	public static final class StructureTags
	{
		public static final TagKey<Structure> GIANT_MUMMIES_SPAWN_IN = createStructureTag("giant_mummies_spawn_in");
		public static final TagKey<Structure> MONOLITHS_SPAWN_IN = createStructureTag("monoliths_spawn_in");
	}

	private static TagKey<Block> createBlockTag(String name)
	{
		return TagKey.create(Registries.BLOCK, new ResourceLocation(HMaG.MODID, name));
	}

	private static TagKey<Item> createItemTag(String name)
	{
		return TagKey.create(Registries.ITEM, new ResourceLocation(HMaG.MODID, name));
	}

	private static TagKey<EntityType<?>> createEntityTypeTag(String name)
	{
		return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(HMaG.MODID, name));
	}

	private static TagKey<DamageType> createDamageTypeTag(String name)
	{
		return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(HMaG.MODID, name));
	}

	private static TagKey<Enchantment> createEnchantmentTag(String name)
	{
		return TagKey.create(Registries.ENCHANTMENT, new ResourceLocation(HMaG.MODID, name));
	}

	private static TagKey<MobEffect> createMobEffectTag(String name)
	{
		return TagKey.create(Registries.MOB_EFFECT, new ResourceLocation(HMaG.MODID, name));
	}

	private static TagKey<Biome> createBiomeTag(String name)
	{
		return TagKey.create(Registries.BIOME, new ResourceLocation(HMaG.MODID, name));
	}

	private static TagKey<Structure> createStructureTag(String name)
	{
		return TagKey.create(Registries.STRUCTURE, new ResourceLocation(HMaG.MODID, name));
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