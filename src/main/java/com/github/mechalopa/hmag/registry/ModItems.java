package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.world.item.AncientShieldItem;
import com.github.mechalopa.hmag.world.item.CrimsonBowItem;
import com.github.mechalopa.hmag.world.item.EnchantmentUpgradeItem;
import com.github.mechalopa.hmag.world.item.EndlessPearlItem;
import com.github.mechalopa.hmag.world.item.EvilFlameItem;
import com.github.mechalopa.hmag.world.item.EvilThornItem;
import com.github.mechalopa.hmag.world.item.ExperienceBerryItem;
import com.github.mechalopa.hmag.world.item.FortressShieldItem;
import com.github.mechalopa.hmag.world.item.InsomniaFruitItem;
import com.github.mechalopa.hmag.world.item.InsomniaSwordItem;
import com.github.mechalopa.hmag.world.item.InvincibleBlockItem;
import com.github.mechalopa.hmag.world.item.LightningSoupItem;
import com.github.mechalopa.hmag.world.item.ModArmorMaterial;
import com.github.mechalopa.hmag.world.item.ModFoodItem;
import com.github.mechalopa.hmag.world.item.ModItem;
import com.github.mechalopa.hmag.world.item.ModSwordItem;
import com.github.mechalopa.hmag.world.item.NemesisBladeItem;
import com.github.mechalopa.hmag.world.item.NetherStarBlockItem;
import com.github.mechalopa.hmag.world.item.PurificationClothItem;
import com.github.mechalopa.hmag.world.item.RandomberryItem;
import com.github.mechalopa.hmag.world.item.ThrowableBottleItem;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
	private static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, HMaG.MODID);

	public static final RegistryObject<Item> EVIL_CRYSTAL_BLOCK = REGISTRY.register("evil_crystal_block", () -> new BlockItem(ModBlocks.EVIL_CRYSTAL_BLOCK.get(), new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> SOUL_POWDER_BLOCK = REGISTRY.register("soul_powder_block", () -> new BlockItem(ModBlocks.SOUL_POWDER_BLOCK.get(), new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> ANCIENT_STONE_BLOCK = REGISTRY.register("ancient_stone_block", () -> new BlockItem(ModBlocks.ANCIENT_STONE_BLOCK.get(), new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> NETHER_STAR_BLOCK = REGISTRY.register("nether_star_block", () -> new NetherStarBlockItem(ModBlocks.NETHER_STAR_BLOCK.get(), new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> HEART_OF_THE_SEA_BLOCK = REGISTRY.register("heart_of_the_sea_block", () -> new BlockItem(ModBlocks.HEART_OF_THE_SEA_BLOCK.get(), new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> THORN_SAND = REGISTRY.register("thorn_sand", () -> new BlockItem(ModBlocks.THORN_SAND.get(), new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> POISON_SAND = REGISTRY.register("poison_sand", () -> new BlockItem(ModBlocks.POISON_SAND.get(), new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> WITHER_SAND = REGISTRY.register("wither_sand", () -> new BlockItem(ModBlocks.WITHER_SAND.get(), new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> HEALING_SAND = REGISTRY.register("healing_sand", () -> new BlockItem(ModBlocks.HEALING_SAND.get(), new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> REINFORCED_BLOCK = REGISTRY.register("reinforced_block", () -> new InvincibleBlockItem(ModBlocks.REINFORCED_BLOCK.get(), new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON).fireResistant()));
	public static final RegistryObject<Item> REINFORCED_GLASS = REGISTRY.register("reinforced_glass", () -> new InvincibleBlockItem(ModBlocks.REINFORCED_GLASS.get(), new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON).fireResistant()));
	public static final RegistryObject<Item> TINTED_REINFORCED_GLASS = REGISTRY.register("tinted_reinforced_glass", () -> new InvincibleBlockItem(ModBlocks.TINTED_REINFORCED_GLASS.get(), new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON).fireResistant()));

	public static final RegistryObject<Item> EVIL_CRYSTAL_FRAGMENT = REGISTRY.register("evil_crystal_fragment", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> EVIL_CRYSTAL = REGISTRY.register("evil_crystal", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> SOUL_POWDER = REGISTRY.register("soul_powder", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> KOBOLD_LEATHER = REGISTRY.register("kobold_leather", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> LICH_CLOTH = REGISTRY.register("lich_cloth", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> OGRE_HORN = REGISTRY.register("ogre_horn", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> ENDER_PLASM = REGISTRY.register("ender_plasm", () -> new Item(new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> ANCIENT_STONE = REGISTRY.register("ancient_stone", () -> new Item(new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> CRIMSON_CUTICULA = REGISTRY.register("crimson_cuticula", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> DYSSOMNIA_SKIN = REGISTRY.register("dyssomnia_skin", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> MYSTERIOUS_PETAL = REGISTRY.register("mysterious_petal", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> SHARP_FANG = REGISTRY.register("sharp_fang", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> BURNING_CORE = REGISTRY.register("burning_core", () -> new ModItem(new Item.Properties().tab(HMaG.MODTAB), new ModItem.Properties().burnTime(4000)));
	public static final RegistryObject<Item> NECROFIBER = REGISTRY.register("necrofiber", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> BAT_WING = REGISTRY.register("bat_wing", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> LIGHTNING_PARTICLE = REGISTRY.register("lightning_particle", () -> new Item(new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> COPPER_NUGGET = REGISTRY.register("copper_nugget", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> DIAMOND_FRAGMENT = REGISTRY.register("diamond_fragment", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> EMERALD_FRAGMENT = REGISTRY.register("emerald_fragment", () -> new Item(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> TINY_NETHERITE_SCRAP = REGISTRY.register("tiny_netherite_scrap", () -> new Item(new Item.Properties().tab(HMaG.MODTAB).fireResistant()));
	public static final RegistryObject<Item> NETHERITE_NUGGET = REGISTRY.register("netherite_nugget", () -> new Item(new Item.Properties().tab(HMaG.MODTAB).fireResistant()));
	public static final RegistryObject<Item> NETHER_STAR_FRAGMENT = REGISTRY.register("nether_star_fragment", () -> new ModItem(new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON), new ModItem.Properties().foil().setResistanceType(ModItem.ResistanceType.NETHER_STAR)));
	public static final RegistryObject<Item> LEMON = REGISTRY.register("lemon", () -> new Item(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).build())));
	public static final RegistryObject<Item> CUREBERRY = REGISTRY.register("cureberry", () -> new ModFoodItem(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.4F).alwaysEat().build()), new ModFoodItem.Properties().healAmount(4.0F)));
	public static final RegistryObject<Item> RANDOMBERRY = REGISTRY.register("randomberry", () -> new RandomberryItem(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.2F).alwaysEat().build())));
	public static final RegistryObject<Item> EXP_BERRY = REGISTRY.register("exp_berry", () -> new ExperienceBerryItem(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.2F).alwaysEat().build()).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> SOUL_APPLE = REGISTRY.register("soul_apple", () -> new ModFoodItem(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 3 * 20, 0), 1.0F).alwaysEat().build()), new ModFoodItem.Properties().eatDuration(16).healAmount(1.0F)));
	public static final RegistryObject<Item> HONEYED_APPLE = REGISTRY.register("honeyed_apple", () -> new ModFoodItem(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.4F).alwaysEat().build()), new ModFoodItem.Properties().eatDuration(24).removePoison()));
	public static final RegistryObject<Item> HONEYED_LEMON = REGISTRY.register("honeyed_lemon", () -> new ModFoodItem(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.3F).alwaysEat().build()), new ModFoodItem.Properties().removePoison()));
	public static final RegistryObject<Item> LEMON_PIE = REGISTRY.register("lemon_pie", () -> new Item(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(7).saturationMod(0.3F).build())));
	public static final RegistryObject<Item> GOLDEN_TROPICAL_FISH = REGISTRY.register("golden_tropical_fish", () -> new Item(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(5).saturationMod(2.5F).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 60 * 20, 1), 1.0F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 15 * 20, 0), 1.0F).effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 15 * 20, 0), 1.0F).alwaysEat().build()).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> CUBIC_NUCLEUS = REGISTRY.register("cubic_nucleus", () -> new ModFoodItem(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(4).saturationMod(0.8F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 15 * 20, 0), 1.0F).alwaysEat().build()), new ModFoodItem.Properties().eatDuration(48)));
	public static final RegistryObject<Item> SAVAGEFANG_MEAT = REGISTRY.register("savagefang_meat", () -> new Item(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 30 * 20, 0), 0.2F).build())));
	public static final RegistryObject<Item> COOKED_SAVAGEFANG_MEAT = REGISTRY.register("cooked_savagefang_meat", () -> new Item(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.7F).build())));
	public static final RegistryObject<Item> BAT_STEW = REGISTRY.register("bat_stew", () -> new BowlFoodItem(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build()).stacksTo(1)));
	public static final RegistryObject<Item> SPECTRAL_SOUP = REGISTRY.register("spectral_soup", () -> new BowlFoodItem(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(1.5F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 30 * 20, 0), 1.0F).alwaysEat().build()).stacksTo(1)));
	public static final RegistryObject<Item> LIGHTNING_SOUP = REGISTRY.register("lightning_soup", () -> new LightningSoupItem(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(9).saturationMod(1.8F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 60 * 20, 0), 1.0F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 60 * 20, 0), 1.0F).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60 * 20, 0), 1.0F).effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 60 * 20, 0), 1.0F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60 * 20, 1), 1.0F).effect(() -> new MobEffectInstance(MobEffects.JUMP, 60 * 20, 1), 1.0F).alwaysEat().build()).stacksTo(1).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> INSOMNIA_FRUIT = REGISTRY.register("insomnia_fruit", () -> new InsomniaFruitItem(new Item.Properties().tab(HMaG.MODTAB).food((new FoodProperties.Builder()).nutrition(5).saturationMod(0.3F).alwaysEat().build()).stacksTo(1).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> INSOMNIA_SWORD = REGISTRY.register("insomnia_sword", () -> new InsomniaSwordItem(Tiers.DIAMOND, new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> NEMESIS_BLADE = REGISTRY.register("nemesis_blade", () -> new NemesisBladeItem(Tiers.NETHERITE, new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.EPIC).fireResistant()));
	public static final RegistryObject<Item> IRON_SPEAR = REGISTRY.register("iron_spear", () -> new ModSwordItem(Tiers.IRON, 2.0F, -2.4F, new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> GOLDEN_FORK = REGISTRY.register("golden_fork", () -> new ModSwordItem(Tiers.GOLD, 2.0F, -2.4F, new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> CRIMSON_BOW = REGISTRY.register("crimson_bow", () -> new CrimsonBowItem(new Item.Properties().tab(HMaG.MODTAB).durability(575).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> ANCIENT_HELMET = REGISTRY.register("ancient_helmet", () -> new ArmorItem(ModArmorMaterial.ANCIENT, EquipmentSlot.HEAD, (new Item.Properties()).tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> ANCIENT_CHESTPLATE = REGISTRY.register("ancient_chestplate", () -> new ArmorItem(ModArmorMaterial.ANCIENT, EquipmentSlot.CHEST, (new Item.Properties()).tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> ANCIENT_LEGGINGS = REGISTRY.register("ancient_leggings", () -> new ArmorItem(ModArmorMaterial.ANCIENT, EquipmentSlot.LEGS, (new Item.Properties()).tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> ANCIENT_BOOTS = REGISTRY.register("ancient_boots", () -> new ArmorItem(ModArmorMaterial.ANCIENT, EquipmentSlot.FEET, (new Item.Properties()).tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> ANCIENT_SHIELD = REGISTRY.register("ancient_shield", () -> new AncientShieldItem(new Item.Properties().tab(HMaG.MODTAB).durability(671).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> FORTRESS_SHIELD = REGISTRY.register("fortress_shield", () -> new FortressShieldItem(new Item.Properties().tab(HMaG.MODTAB).durability(671).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> NECROTIC_CHAINMAIL_HELMET = REGISTRY.register("necrotic_chainmail_helmet", () -> new ArmorItem(ModArmorMaterial.NECROTIC_CHAIN, EquipmentSlot.HEAD, (new Item.Properties()).tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> NECROTIC_CHAINMAIL_CHESTPLATE = REGISTRY.register("necrotic_chainmail_chestplate", () -> new ArmorItem(ModArmorMaterial.NECROTIC_CHAIN, EquipmentSlot.CHEST, (new Item.Properties()).tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> NECROTIC_CHAINMAIL_LEGGINGS = REGISTRY.register("necrotic_chainmail_leggings", () -> new ArmorItem(ModArmorMaterial.NECROTIC_CHAIN, EquipmentSlot.LEGS, (new Item.Properties()).tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> NECROTIC_CHAINMAIL_BOOTS = REGISTRY.register("necrotic_chainmail_boots", () -> new ArmorItem(ModArmorMaterial.NECROTIC_CHAIN, EquipmentSlot.FEET, (new Item.Properties()).tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> FIRE_BOTTLE = REGISTRY.register("fire_bottle", () -> new ThrowableBottleItem(new Item.Properties().tab(HMaG.MODTAB).stacksTo(16), new ModItem.Properties().burnTime(800)));
	public static final RegistryObject<Item> BLASTING_BOTTLE = REGISTRY.register("blasting_bottle", () -> new ThrowableBottleItem(new Item.Properties().tab(HMaG.MODTAB).stacksTo(16).rarity(Rarity.UNCOMMON), new ModItem.Properties().burnTime(3200)));
	public static final RegistryObject<Item> LIGHTNING_BOTTLE = REGISTRY.register("lightning_bottle", () -> new ThrowableBottleItem(new Item.Properties().tab(HMaG.MODTAB).stacksTo(16).rarity(Rarity.RARE), new ModItem.Properties().foil()));
	public static final RegistryObject<Item> REINFORCING_CHAIN = REGISTRY.register("reinforcing_chain", () -> new EnchantmentUpgradeItem(new Item.Properties().tab(HMaG.MODTAB), new EnchantmentUpgradeItem.Properties().enchantment(Enchantments.UNBREAKING, 0, 2)));
	public static final RegistryObject<Item> MULTIPLEX_REINFORCING_CHAIN = REGISTRY.register("multiplex_reinforcing_chain", () -> new EnchantmentUpgradeItem(new Item.Properties().tab(HMaG.MODTAB).rarity(Rarity.UNCOMMON), new ModItem.Properties().foil(), new EnchantmentUpgradeItem.Properties().enchantment(Enchantments.UNBREAKING, 3, 9)));
	public static final RegistryObject<Item> REPULSION_GADGET = REGISTRY.register("repulsion_gadget", () -> new EnchantmentUpgradeItem(new Item.Properties().tab(HMaG.MODTAB), new EnchantmentUpgradeItem.Properties().enchantment(Enchantments.KNOCKBACK, 0, 9).enchantment(Enchantments.PUNCH_ARROWS, 0, 9)));
	public static final RegistryObject<Item> EVIL_FLAME = REGISTRY.register("evil_flame", () -> new EvilFlameItem(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> EVIL_THORN = REGISTRY.register("evil_thorn", () -> new EvilThornItem(new Item.Properties().tab(HMaG.MODTAB)));
	public static final RegistryObject<Item> PURIFICATION_CLOTH = REGISTRY.register("purification_cloth", () -> new PurificationClothItem(new Item.Properties().tab(HMaG.MODTAB).stacksTo(16).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> ENDLESS_PEARL = REGISTRY.register("endless_pearl", () -> new EndlessPearlItem(new Item.Properties().tab(HMaG.MODTAB).stacksTo(1).rarity(Rarity.UNCOMMON)));

	public static final RegistryObject<Item> ZOMBIE_GIRL_SPAWN_EGG = createSpawnEggRegistryObject("zombie_girl", ModEntityTypes.ZOMBIE_GIRL, 0xB4AD53, 0x577431);
	public static final RegistryObject<Item> HUSK_GIRL_SPAWN_EGG = createSpawnEggRegistryObject("husk_girl", ModEntityTypes.HUSK_GIRL, 0xD3B87D, 0x62453D);
	public static final RegistryObject<Item> DROWNED_GIRL_SPAWN_EGG = createSpawnEggRegistryObject("drowned_girl", ModEntityTypes.DROWNED_GIRL, 0x7DBAAC, 0x0E6866);
	public static final RegistryObject<Item> SKELETON_GIRL_SPAWN_EGG = createSpawnEggRegistryObject("skeleton_girl", ModEntityTypes.SKELETON_GIRL, 0xB6A28D, 0x615042);
	public static final RegistryObject<Item> WITHER_SKELETON_GIRL_SPAWN_EGG = createSpawnEggRegistryObject("wither_skeleton_girl", ModEntityTypes.WITHER_SKELETON_GIRL, 0x2F2E37, 0x5B5B63);
	public static final RegistryObject<Item> STRAY_GIRL_SPAWN_EGG = createSpawnEggRegistryObject("stray_girl", ModEntityTypes.STRAY_GIRL, 0xA6B6B7, 0x576C6D);
	public static final RegistryObject<Item> CREEPER_GIRL_SPAWN_EGG = createSpawnEggRegistryObject("creeper_girl", ModEntityTypes.CREEPER_GIRL, 0x00A500, 0xFF7E00);
	public static final RegistryObject<Item> GHOST_SPAWN_EGG = createSpawnEggRegistryObject("ghost", ModEntityTypes.GHOST, 0xC3EEEE, 0xC4C495);
	public static final RegistryObject<Item> WITHER_GHOST_SPAWN_EGG = createSpawnEggRegistryObject("wither_ghost", ModEntityTypes.WITHER_GHOST, 0x7B4B3E, 0x2C2C2C);
	public static final RegistryObject<Item> ENDER_EXECUTOR_SPAWN_EGG = createSpawnEggRegistryObject("ender_executor", ModEntityTypes.ENDER_EXECUTOR, 0x100A1A, 0xD3D3A0);
	public static final RegistryObject<Item> KOBOLD_SPAWN_EGG = createSpawnEggRegistryObject("kobold", ModEntityTypes.KOBOLD, 0x0D1B27, 0xA9AF8D);
	public static final RegistryObject<Item> LICH_SPAWN_EGG = createSpawnEggRegistryObject("lich", ModEntityTypes.LICH, 0x391010, 0x55666A);
	public static final RegistryObject<Item> OGRE_SPAWN_EGG = createSpawnEggRegistryObject("ogre", ModEntityTypes.OGRE, 0x4A4839, 0x6E1C02);
	public static final RegistryObject<Item> SPIDER_NEST_SPAWN_EGG = createSpawnEggRegistryObject("spider_nest", ModEntityTypes.SPIDER_NEST, 0xE8E8DC, 0x828D85);
	public static final RegistryObject<Item> MELTY_MONSTER_SPAWN_EGG = createSpawnEggRegistryObject("melty_monster", ModEntityTypes.MELTY_MONSTER, 0xA80000, 0xFF9100);
	public static final RegistryObject<Item> CURSED_DOLL_SPAWN_EGG = createSpawnEggRegistryObject("cursed_doll", ModEntityTypes.CURSED_DOLL, 0x050000, 0xB53A95);
	public static final RegistryObject<Item> JACK_FROST_SPAWN_EGG = createSpawnEggRegistryObject("jack_frost", ModEntityTypes.JACK_FROST, 0xEEFFFF, 0x53629B);
	public static final RegistryObject<Item> HORNET_SPAWN_EGG = createSpawnEggRegistryObject("hornet", ModEntityTypes.HORNET, 0xFF770F, 0x851A05);
	public static final RegistryObject<Item> DULLAHAN_SPAWN_EGG = createSpawnEggRegistryObject("dullahan", ModEntityTypes.DULLAHAN, 0x6E2424, 0xA97D48);
	public static final RegistryObject<Item> BANSHEE_SPAWN_EGG = createSpawnEggRegistryObject("banshee", ModEntityTypes.BANSHEE, 0xDC8BC7, 0xC36CAA);
	public static final RegistryObject<Item> ALRAUNE_SPAWN_EGG = createSpawnEggRegistryObject("alraune", ModEntityTypes.ALRAUNE, 0x23AA23, 0xD52B39);
	public static final RegistryObject<Item> CATOBLEPAS_SPAWN_EGG = createSpawnEggRegistryObject("catoblepas", ModEntityTypes.CATOBLEPAS, 0x74620B, 0x4E2310);
	public static final RegistryObject<Item> SCORPION_SPAWN_EGG = createSpawnEggRegistryObject("scorpion", ModEntityTypes.SCORPION, 0xDAB68F, 0x625358);
	public static final RegistryObject<Item> KASHA_SPAWN_EGG = createSpawnEggRegistryObject("kasha", ModEntityTypes.KASHA, 0x200000, 0xFFAE4A);
	public static final RegistryObject<Item> DOGU_SPAWN_EGG = createSpawnEggRegistryObject("dogu", ModEntityTypes.DOGU, 0xB45927, 0x692707);
	public static final RegistryObject<Item> GHASTLY_SEEKER_SPAWN_EGG = createSpawnEggRegistryObject("ghastly_seeker", ModEntityTypes.GHASTLY_SEEKER, 0xF4E6D7, 0xC3B5A6);
	public static final RegistryObject<Item> REDCAP_SPAWN_EGG = createSpawnEggRegistryObject("redcap", ModEntityTypes.REDCAP, 0x73530D, 0x8E2323);
	public static final RegistryObject<Item> SLIME_GIRL_SPAWN_EGG = createSpawnEggRegistryObject("slime_girl", ModEntityTypes.SLIME_GIRL, 0xFD73AC, 0xFA476B);
	public static final RegistryObject<Item> MAGICAL_SLIME_SPAWN_EGG = createSpawnEggRegistryObject("magical_slime", ModEntityTypes.MAGICAL_SLIME, 0xFD73AC, 0xFF7D98);
	public static final RegistryObject<Item> MONOLITH_SPAWN_EGG = createSpawnEggRegistryObject("monolith", ModEntityTypes.MONOLITH, 0x4A3B71, 0x99A6C7);
	public static final RegistryObject<Item> CRIMSON_SLAUGHTERER_SPAWN_EGG = createSpawnEggRegistryObject("crimson_slaughterer", ModEntityTypes.CRIMSON_SLAUGHTERER, 0x942020, 0x562C3E);
	public static final RegistryObject<Item> DYSSOMNIA_SPAWN_EGG = createSpawnEggRegistryObject("dyssomnia", ModEntityTypes.DYSSOMNIA, 0x374377, 0xC3B9A1);
	public static final RegistryObject<Item> SNOW_CANINE_SPAWN_EGG = createSpawnEggRegistryObject("snow_canine", ModEntityTypes.SNOW_CANINE, 0xE9E3DF, 0xB19B8A);
	public static final RegistryObject<Item> HARPY_SPAWN_EGG = createSpawnEggRegistryObject("harpy", ModEntityTypes.HARPY, 0xD29741, 0x6B5244);
	public static final RegistryObject<Item> SAVAGEFANG_SPAWN_EGG = createSpawnEggRegistryObject("savagefang", ModEntityTypes.SAVAGEFANG, 0x415A4E, 0x690C1B);
	public static final RegistryObject<Item> FORTRESS_KEEPER_SPAWN_EGG = createSpawnEggRegistryObject("fortress_keeper", ModEntityTypes.FORTRESS_KEEPER, 0x291519, 0xF48522);
	public static final RegistryObject<Item> NECROTIC_REAPER_SPAWN_EGG = createSpawnEggRegistryObject("necrotic_reaper", ModEntityTypes.NECROTIC_REAPER, 0x6A4B54, 0xADB5A7);
	public static final RegistryObject<Item> DODOMEKI_SPAWN_EGG = createSpawnEggRegistryObject("dodomeki", ModEntityTypes.DODOMEKI, 0x242121, 0xB3ACAB);
	public static final RegistryObject<Item> IMP_SPAWN_EGG = createSpawnEggRegistryObject("imp", ModEntityTypes.IMP, 0x5E2E78, 0x4E181B);
	public static final RegistryObject<Item> GLARYAD_SPAWN_EGG = createSpawnEggRegistryObject("glaryad", ModEntityTypes.GLARYAD, 0x70922D, 0xBA62CE);

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}

	private static RegistryObject<Item> createSpawnEggRegistryObject(String name, RegistryObject<? extends EntityType<? extends Mob>> entityTypeSupplier, int primaryColorIn, int secondaryColorIn)
	{
		return REGISTRY.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entityTypeSupplier, primaryColorIn, secondaryColorIn, new Item.Properties().tab(HMaG.MODTAB)));
	}
}