package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.CreeperGirlModel;
import com.github.mechalopa.hmag.client.renderer.layers.CreeperGirlChargeLayer;
import com.github.mechalopa.hmag.entity.CreeperGirlEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlRenderer extends AbstractGirlRenderer<CreeperGirlEntity, CreeperGirlModel<CreeperGirlEntity>>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/creeper_girl_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/creeper_girl_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/creeper_girl_2.png");

	public CreeperGirlRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new CreeperGirlModel<>(), 0.5F);
		this.addLayer(new BipedArmorLayer<>(this, new CreeperGirlModel<>(0.5F, true), new CreeperGirlModel<>(1.0F, true)));
		this.addLayer(new CreeperGirlChargeLayer(this));
	}

	@Override
	protected void scale(CreeperGirlEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		float f = entityIn.getSwelling(partialTickTime);
		float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		f = f * f;
		f = f * f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		matrixStackIn.scale(f2, f3, f2);
	}

	@Override
	protected float getWhiteOverlayProgress(CreeperGirlEntity livingEntityIn, float partialTicks)
	{
		float f = livingEntityIn.getSwelling(partialTicks);
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(f, 0.5F, 1.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(CreeperGirlEntity entityIn)
	{
		switch (entityIn.getVariant())
		{
		case 1:
			return TEX1;
		case 2:
			return TEX2;
		default:
			return TEX0;
		}
	}
}