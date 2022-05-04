package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.GhostModel;
import com.github.mechalopa.hmag.client.renderer.layers.GhostClothingLayer;
import com.github.mechalopa.hmag.entity.GhostEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhostRenderer extends HumanoidMobRenderer<GhostEntity, GhostModel<GhostEntity>>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_3.png");
	private static final ResourceLocation TEX4 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_4.png");

	public GhostRenderer(EntityRendererProvider.Context context)
	{
		this(context, ModModelLayers.GHOST, ModModelLayers.GHOST_INNER_ARMOR, ModModelLayers.GHOST_OUTER_ARMOR);
	}

	public GhostRenderer(EntityRendererProvider.Context context, ModelLayerLocation location, ModelLayerLocation location1, ModelLayerLocation location2)
	{
		this(context, new GhostModel<>(context.bakeLayer(location)), new GhostModel<>(context.bakeLayer(location1)), new GhostModel<>(context.bakeLayer(location2)));
	}

	public GhostRenderer(EntityRendererProvider.Context context, GhostModel<GhostEntity> model, GhostModel<GhostEntity> model1, GhostModel<GhostEntity> model2)
	{
		super(context, model, 0.375F);
		this.addLayer(new HumanoidArmorLayer<>(this, model1, model2));
		this.addLayer(this.getLayer(context));
	}

	@Override
	protected int getBlockLightLevel(GhostEntity entityIn, BlockPos pos)
	{
		return 15;
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

	public GhostClothingLayer getLayer(EntityRendererProvider.Context context)
	{
		return new GhostClothingLayer(this, context.getModelSet());
	}
}