package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.KoboldModel;
import com.github.mechalopa.hmag.entity.KoboldEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KoboldRenderer extends AbstractGirlRenderer<KoboldEntity, KoboldModel<KoboldEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/kobold.png");

	public KoboldRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new KoboldModel<>(), 0.5F);
	}

	@Override
	protected void scale(KoboldEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(0.875F, 0.875F, 0.875F);
		super.scale(entityIn, matrixStackIn, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(KoboldEntity entityIn)
	{
		return TEX;
	}
}