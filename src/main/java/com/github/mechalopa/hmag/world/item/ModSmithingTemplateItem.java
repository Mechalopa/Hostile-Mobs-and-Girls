package com.github.mechalopa.hmag.world.item;

import java.util.List;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

public class ModSmithingTemplateItem extends SmithingTemplateItem
{
	private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
	private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
	private static final Component EVIL_CRYSTAL_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", ModUtils.createHMaGRL("evil_crystal_upgrade"))).withStyle(TITLE_FORMAT);
	private static final String EVIL_CRYSTAL_UPGRADE_KEY = "smithing_template." + HMaG.MODID + ".evil_crystal_upgrade";
	private static final Component EVIL_CRYSTAL_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ModUtils.createRL(EVIL_CRYSTAL_UPGRADE_KEY + ".applies_to"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component EVIL_CRYSTAL_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ModUtils.createRL(EVIL_CRYSTAL_UPGRADE_KEY + ".ingredients"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component EVIL_CRYSTAL_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ModUtils.createRL(EVIL_CRYSTAL_UPGRADE_KEY + ".base_slot_description")));
	private static final Component EVIL_CRYSTAL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ModUtils.createRL(EVIL_CRYSTAL_UPGRADE_KEY + ".additions_slot_description")));
	private static final ResourceLocation EMPTY_SLOT_HELMET = ModUtils.createRL("item/empty_armor_slot_helmet");
	private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ModUtils.createRL("item/empty_armor_slot_chestplate");
	private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ModUtils.createRL("item/empty_armor_slot_leggings");
	private static final ResourceLocation EMPTY_SLOT_BOOTS = ModUtils.createRL("item/empty_armor_slot_boots");
	private static final ResourceLocation EMPTY_SLOT_HOE = ModUtils.createRL("item/empty_slot_hoe");
	private static final ResourceLocation EMPTY_SLOT_AXE = ModUtils.createRL("item/empty_slot_axe");
	private static final ResourceLocation EMPTY_SLOT_SWORD = ModUtils.createRL("item/empty_slot_sword");
	private static final ResourceLocation EMPTY_SLOT_SHOVEL = ModUtils.createRL("item/empty_slot_shovel");
	private static final ResourceLocation EMPTY_SLOT_PICKAXE = ModUtils.createRL("item/empty_slot_pickaxe");
	private static final ResourceLocation EMPTY_SLOT_INGOT = ModUtils.createRL("item/empty_slot_ingot");
	private static final ResourceLocation EMPTY_SLOT_REDSTONE_DUST = ModUtils.createRL("item/empty_slot_redstone_dust");
	private static final ResourceLocation EMPTY_SLOT_QUARTZ = ModUtils.createRL("item/empty_slot_quartz");
	private static final ResourceLocation EMPTY_SLOT_EMERALD = ModUtils.createRL("item/empty_slot_emerald");
	private static final ResourceLocation EMPTY_SLOT_DIAMOND = ModUtils.createRL("item/empty_slot_diamond");
	private static final ResourceLocation EMPTY_SLOT_LAPIS_LAZULI = ModUtils.createRL("item/empty_slot_lapis_lazuli");
	private static final ResourceLocation EMPTY_SLOT_AMETHYST_SHARD = ModUtils.createRL("item/empty_slot_amethyst_shard");

	public ModSmithingTemplateItem(Component appliesToDescription, Component ingredientsDescription, Component upgradeDescription, Component baseSlotDescription, Component addtionsSlotDescription, List<ResourceLocation> iconList, List<ResourceLocation> materialIconList)
	{
		super(appliesToDescription, ingredientsDescription, upgradeDescription, baseSlotDescription, addtionsSlotDescription, iconList, materialIconList);
	}

	public static ModSmithingTemplateItem createEvilCrystalUpgradeTemplate()
	{
		return new ModSmithingTemplateItem(EVIL_CRYSTAL_UPGRADE_APPLIES_TO, EVIL_CRYSTAL_UPGRADE_INGREDIENTS, EVIL_CRYSTAL_UPGRADE, EVIL_CRYSTAL_UPGRADE_BASE_SLOT_DESCRIPTION, EVIL_CRYSTAL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createIconList(), createMaterialIconList());
	}

	private static List<ResourceLocation> createIconList()
	{
		return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
	}

	private static List<ResourceLocation> createMaterialIconList()
	{
		return List.of(EMPTY_SLOT_INGOT, EMPTY_SLOT_REDSTONE_DUST, EMPTY_SLOT_LAPIS_LAZULI, EMPTY_SLOT_QUARTZ, EMPTY_SLOT_DIAMOND, EMPTY_SLOT_EMERALD, EMPTY_SLOT_AMETHYST_SHARD);
	}
}