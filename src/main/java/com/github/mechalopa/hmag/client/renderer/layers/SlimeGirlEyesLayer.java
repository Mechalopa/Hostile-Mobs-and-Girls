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
public class SlimeGirlEyesLayer extends AbstractClothingLayer<SlimeGirlEntity, SlimeGirlModel<SlimeGirlEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/slime_girl_eyes.png");
	private final SlimeGirlModel<SlimeGirlEntity> modelOuterLayer = new SlimeGirlModel<>(0.0F);

	public SlimeGirlEyesLayer(IEntityRenderer<SlimeGirlEntity, SlimeGirlModel<SlimeGirlEntity>> entityRendererIn)
	{
		super(entityRendererIn);
	}

	@Override
	protected float getAlpha(SlimeGirlEntity entitylivingbaseIn)
	{
		return 0.875F;
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