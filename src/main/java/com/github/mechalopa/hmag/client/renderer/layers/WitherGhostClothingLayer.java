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
	private static final ResourceLocation TEXTURE_0 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin_0.png");
	private static final ResourceLocation TEXTURE_1 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin_1.png");
	private static final ResourceLocation TEXTURE_2 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin_2.png");
	private static final ResourceLocation TEXTURE_3 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin_3.png");
	private static final ResourceLocation TEXTURE_4 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin_4.png");

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
			return TEXTURE_1;
		case 2:
			return TEXTURE_2;
		case 3:
			return TEXTURE_3;
		case 4:
			return TEXTURE_4;
		default:
			return TEXTURE_0;
		}
	}
}