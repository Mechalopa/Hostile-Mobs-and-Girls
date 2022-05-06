package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.DoguModel;
import com.github.mechalopa.hmag.entity.DoguEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DoguRenderer extends BipedRenderer<DoguEntity, DoguModel<DoguEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/dogu.png");

	public DoguRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new DoguModel<>(), 0.5F);
	}

	@Override
	protected void scale(DoguEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		super.scale(entityIn, matrixStackIn, partialTickTime);
		float f = (float)entityIn.tickCount + partialTickTime;
		matrixStackIn.translate(0.0F, -0.06F + MathHelper.sin(f * 0.06F) * 0.04F, 0.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(DoguEntity entityIn)
	{
		return TEX;
	}
}