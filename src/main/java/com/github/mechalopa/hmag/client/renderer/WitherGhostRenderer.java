package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.renderer.layers.GhostClothingLayer;
import com.github.mechalopa.hmag.client.renderer.layers.WitherGhostClothingLayer;
import com.github.mechalopa.hmag.entity.GhostEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherGhostRenderer extends GhostRenderer
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_3.png");
	private static final ResourceLocation TEX4 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_4.png");

	public WitherGhostRenderer(EntityRendererProvider.Context context)
	{
		super(context, ModModelLayers.WITHER_GHOST, ModModelLayers.WITHER_GHOST_INNER_ARMOR, ModModelLayers.WITHER_GHOST_OUTER_ARMOR);
	}

	@Override
	public ResourceLocation getTextureLocation(GhostEntity entityIn)
	{
		switch (entityIn.getVariant())
		{
		case 1:
			return TEX1;
		case 2:
			return TEX2;
		case 3:
			return TEX3;
		case 4:
			return TEX4;
		default:
			return TEX0;
		}
	}

	@Override
	public GhostClothingLayer getLayer(EntityRendererProvider.Context context)
	{
		return new WitherGhostClothingLayer(this, context.getModelSet());
	}
}