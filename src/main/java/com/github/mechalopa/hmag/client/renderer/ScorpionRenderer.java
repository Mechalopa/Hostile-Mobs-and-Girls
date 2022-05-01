package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.ScorpionModel;
import com.github.mechalopa.hmag.entity.ScorpionEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScorpionRenderer extends MobRenderer<ScorpionEntity, ScorpionModel<ScorpionEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/scorpion.png");

	public ScorpionRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ScorpionModel<>(), 0.8F);
	}

	@Override
	protected void scale(ScorpionEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(1.1F, 1.1F, 1.1F);
		super.scale(entityIn, matrixStackIn, partialTickTime);
	}

	@Override
	protected float getFlipDegrees(ScorpionEntity entityLivingBaseIn)
	{
		return 180.0F;
	}

	@Override
	public ResourceLocation getTextureLocation(ScorpionEntity entityIn)
	{
		return TEX;
	}
}