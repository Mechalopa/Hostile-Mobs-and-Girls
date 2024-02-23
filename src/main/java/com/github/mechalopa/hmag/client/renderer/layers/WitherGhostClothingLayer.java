package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.GhostModel;
import com.github.mechalopa.hmag.world.entity.GhostEntity;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherGhostClothingLayer extends GhostClothingLayer
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost/wither_ghost_skin_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost/wither_ghost_skin_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost/wither_ghost_skin_2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost/wither_ghost_skin_3.png");
	private static final ResourceLocation TEX4 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost/wither_ghost_skin_4.png");

	public WitherGhostClothingLayer(RenderLayerParent<GhostEntity, GhostModel<GhostEntity>> renderLayerParent, EntityModelSet modelSet)
	{
		super(renderLayerParent, modelSet);
	}

	@Override
	protected float getAlpha(GhostEntity entity)
	{
		return 0.875F;
	}

	@Override
	public ResourceLocation getLayerTexture(GhostEntity entity)
	{
		switch (entity.getVariant())
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
}