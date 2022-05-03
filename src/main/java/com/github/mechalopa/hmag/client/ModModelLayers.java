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

	public static final ModelLayerLocation KOBOLD = create("kobold");

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