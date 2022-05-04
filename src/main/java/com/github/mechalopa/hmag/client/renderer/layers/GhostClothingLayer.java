package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.GhostModel;
import com.github.mechalopa.hmag.entity.GhostEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhostClothingLayer extends AbstractClothingLayer<GhostEntity, GhostModel<GhostEntity>>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_skin_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_skin_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_skin_2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_skin_3.png");
	private static final ResourceLocation TEX4 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_skin_4.png");
	private final GhostModel<GhostEntity> modelOuterLayer;

	public GhostClothingLayer(RenderLayerParent<GhostEntity, GhostModel<GhostEntity>> renderLayerParent, EntityModelSet modelSet)
	{
		super(renderLayerParent);
		modelOuterLayer = new GhostModel<>(modelSet.bakeLayer(ModModelLayers.GHOST));
	}

	@Override
	protected float getAlpha(GhostEntity livingEntityIn)
	{
		return 0.625F;
	}

	@Override
	protected EntityModel<GhostEntity> model()
	{
		return this.modelOuterLayer;
	}

	@Override
	public ResourceLocation getLayerTexture(GhostEntity livingEntityIn)
	{
		switch (livingEntityIn.getVariant())
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