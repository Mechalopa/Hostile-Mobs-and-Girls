package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.GhostModel;
import com.github.mechalopa.hmag.client.renderer.layers.GhostClothingLayer;
import com.github.mechalopa.hmag.entity.GhostEntity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhostRenderer extends BipedRenderer<GhostEntity, GhostModel<GhostEntity>>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_3.png");
	private static final ResourceLocation TEX4 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost_4.png");

	public GhostRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new GhostModel<>(), 0.375F);
		this.addLayer(new BipedArmorLayer<>(this, new GhostModel<>(0.5F, true), new GhostModel<>(1.0F, true)));
		this.addLayer(this.getLayer());
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

	public GhostClothingLayer getLayer()
	{
		return new GhostClothingLayer(this);
	}
}