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