package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.SlimeGirlModel;
import com.github.mechalopa.hmag.entity.SlimeGirlEntity;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlimeGirlLayer extends AbstractClothingLayer<SlimeGirlEntity, SlimeGirlModel<SlimeGirlEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/slime_girl_overlay.png");
	private final SlimeGirlModel<SlimeGirlEntity> modelOuterLayer = new SlimeGirlModel<>(0.0F);

	public SlimeGirlLayer(IEntityRenderer<SlimeGirlEntity, SlimeGirlModel<SlimeGirlEntity>> entityRendererIn)
	{
		super(entityRendererIn);
	}

	@Override
	protected float getR(SlimeGirlEntity livingEntityIn)
	{
		return (livingEntityIn.getColor())[0];
	}

	@Override
	protected float getG(SlimeGirlEntity livingEntityIn)
	{
		return (livingEntityIn.getColor())[1];
	}

	@Override
	protected float getB(SlimeGirlEntity livingEntityIn)
	{
		return (livingEntityIn.getColor())[2];
	}

	@Override
	protected float getAlpha(SlimeGirlEntity entitylivingbaseIn)
	{
		return 0.5F;
	}

	@Override
	protected EntityModel<SlimeGirlEntity> model()
	{
		return this.modelOuterLayer;
	}

	@Override
	public ResourceLocation getLayerTexture(SlimeGirlEntity entity)
	{
		return TEX;
	}
}