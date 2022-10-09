package com.github.mechalopa.hmag.client;

import com.github.mechalopa.hmag.HMaG;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModModelLayers
{
	public static final ModelLayerLocation ZOMBIE_GIRL = create("zombie_girl");
	public static final ModelLayerLocation ZOMBIE_GIRL_INNER_ARMOR = createInnerArmor("zombie_girl");
	public static final ModelLayerLocation ZOMBIE_GIRL_OUTER_ARMOR = createOuterArmor("zombie_girl");
	public static final ModelLayerLocation HUSK_GIRL = create("husk_girl");
	public static final ModelLayerLocation HUSK_GIRL_INNER_ARMOR = createInnerArmor("husk_girl");
	public static final ModelLayerLocation HUSK_GIRL_OUTER_ARMOR = createOuterArmor("husk_girl");
	public static final ModelLayerLocation DROWNED_GIRL = create("drowned_girl");
	public static final ModelLayerLocation DROWNED_GIRL_INNER_ARMOR = createInnerArmor("drowned_girl");
	public static final ModelLayerLocation DROWNED_GIRL_OUTER_ARMOR = createOuterArmor("drowned_girl");
	public static final ModelLayerLocation SKELETON_GIRL = create("skeleton_girl");
	public static final ModelLayerLocation SKELETON_GIRL_INNER_ARMOR = createInnerArmor("skeleton_girl");
	public static final ModelLayerLocation SKELETON_GIRL_OUTER_ARMOR = createOuterArmor("skeleton_girl");
	public static final ModelLayerLocation WITHER_SKELETON_GIRL = create("wither_skeleton_girl");
	public static final ModelLayerLocation WITHER_SKELETON_GIRL_INNER_ARMOR = createInnerArmor("wither_skeleton_girl");
	public static final ModelLayerLocation WITHER_SKELETON_GIRL_OUTER_ARMOR = createOuterArmor("wither_skeleton_girl");
	public static final ModelLayerLocation STRAY_GIRL = create("stray_girl");
	public static final ModelLayerLocation STRAY_GIRL_INNER_ARMOR = createInnerArmor("stray_girl");
	public static final ModelLayerLocation STRAY_GIRL_OUTER_ARMOR = createOuterArmor("stray_girl");
	public static final ModelLayerLocation STRAY_GIRL_OUTER_LAYER = create("stray_girl", "outer");
	public static final ModelLayerLocation CREEPER_GIRL = create("creeper_girl");
	public static final ModelLayerLocation CREEPER_GIRL_INNER_ARMOR = createInnerArmor("creeper_girl");
	public static final ModelLayerLocation CREEPER_GIRL_OUTER_ARMOR = createOuterArmor("creeper_girl");
	public static final ModelLayerLocation CREEPER_GIRL_POWER_ARMOR = create("creeper", "armor");
	public static final ModelLayerLocation GHOST = create("ghost");
	public static final ModelLayerLocation GHOST_INNER_ARMOR = createInnerArmor("ghost");
	public static final ModelLayerLocation GHOST_OUTER_ARMOR = createOuterArmor("ghost");
	public static final ModelLayerLocation WITHER_GHOST = create("wither_ghost");
	public static final ModelLayerLocation WITHER_GHOST_INNER_ARMOR = createInnerArmor("wither_ghost");
	public static final ModelLayerLocation WITHER_GHOST_OUTER_ARMOR = createOuterArmor("wither_ghost");
	public static final ModelLayerLocation ENDER_EXECUTOR = create("ender_executor");
	public static final ModelLayerLocation KOBOLD = create("kobold");
	public static final ModelLayerLocation LICH = create("lich");
	public static final ModelLayerLocation OGRE = create("ogre");
	public static final ModelLayerLocation SPIDER_NEST = create("spider_nest");
	public static final ModelLayerLocation MELTY_MONSTER = create("melty_monster");
	public static final ModelLayerLocation MELTY_MONSTER_OUTER_LAYER = create("melty_monster", "outer");
	public static final ModelLayerLocation CURSED_DOLL = create("cursed_doll");
	public static final ModelLayerLocation JACK_FROST = create("jack_frost");
	public static final ModelLayerLocation HORNET = create("hornet");
	public static final ModelLayerLocation DULLAHAN = create("dullahan");
	public static final ModelLayerLocation BANSHEE = create("banshee");
	public static final ModelLayerLocation ALRAUNE = create("alraune");
	public static final ModelLayerLocation CATOBLEPAS = create("catoblepas");
	public static final ModelLayerLocation SCORPION = create("scorpion");
	public static final ModelLayerLocation KASHA = create("kasha");
	public static final ModelLayerLocation DOGU = create("dogu");
	public static final ModelLayerLocation GHASTLY_SEEKER = create("ghastly_seeker");
	public static final ModelLayerLocation GHASTLY_SEEKER_OUTER_LAYER = create("ghastly_seeker", "outer");
	public static final ModelLayerLocation REDCAP = create("redcap");
	public static final ModelLayerLocation REDCAP_INNER_ARMOR = createInnerArmor("redcap");
	public static final ModelLayerLocation REDCAP_OUTER_ARMOR = createOuterArmor("redcap");
	public static final ModelLayerLocation SLIME_GIRL = create("slime_girl");
	public static final ModelLayerLocation SLIME_GIRL_OUTER = create("slime_girl", "outer");
	public static final ModelLayerLocation MAGICAL_SLIME = create("magical_slime");
	public static final ModelLayerLocation MAGICAL_SLIME_OUTER = create("magical_slime", "outer");
	public static final ModelLayerLocation MONOLITH = create("monolith");
	public static final ModelLayerLocation CRIMSON_SLAUGHTERER = create("crimson_slaughterer");
	public static final ModelLayerLocation DYSSOMNIA = create("dyssomnia");
	public static final ModelLayerLocation SNOW_CANINE = create("snow_canine");
	public static final ModelLayerLocation HARPY = create("harpy");
	public static final ModelLayerLocation SAVAGEFANG = create("savagefang");
	public static final ModelLayerLocation FORTRESS_KEEPER = create("fortress_keeper");
	public static final ModelLayerLocation NECROTIC_REAPER = create("necrotic_reaper");
	public static final ModelLayerLocation DODOMEKI = create("dodomeki");
	public static final ModelLayerLocation IMP = create("imp");
	public static final ModelLayerLocation GLARYAD = create("glaryad");
	public static final ModelLayerLocation JIANGSHI = create("jiangshi");

	public static final ModelLayerLocation ANCIENT_SHIELD = create("ancient_shield");
	public static final ModelLayerLocation FORTRESS_SHIELD = create("fortress_shield");

	private static ModelLayerLocation create(String name, String layername)
	{
		return new ModelLayerLocation(new ResourceLocation(HMaG.MODID, name), layername);
	}

	private static ModelLayerLocation create(String name)
	{
		return create(name, "main");
	}

	private static ModelLayerLocation createInnerArmor(String name)
	{
		return create(name, "inner_armor");
	}

	private static ModelLayerLocation createOuterArmor(String name)
	{
		return create(name, "outer_armor");
	}
}