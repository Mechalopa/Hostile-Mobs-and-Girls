package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.ImpModel;
import com.github.mechalopa.hmag.entity.ImpEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImpRenderer extends AbstractGirlRenderer<ImpEntity, ImpModel<ImpEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/imp.png");

	public ImpRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ImpModel<>(), 0.5F, -1);
	}

	@Override
	protected void scale(ImpEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(0.875F, 0.875F, 0.875F);
		super.scale(entityIn, matrixStackIn, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(ImpEntity entityIn)
	{
		return TEX;
	}
}