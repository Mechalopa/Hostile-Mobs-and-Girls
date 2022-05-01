package com.github.mechalopa.hmag.client.renderer;

import java.util.Random;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.SpiderNestModel;
import com.github.mechalopa.hmag.client.renderer.layers.SpiderNestEyesLayer;
import com.github.mechalopa.hmag.entity.SpiderNestEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpiderNestRenderer extends MobRenderer<SpiderNestEntity, SpiderNestModel<SpiderNestEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/spider_nest.png");
	private final Random rand = new Random();

	public SpiderNestRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new SpiderNestModel<>(), 0.8F);
		this.addLayer(new SpiderNestEyesLayer<>(this));
	}

	@Override
	protected void scale(SpiderNestEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		float f = (float)entityIn.tickCount + partialTickTime;
		float f1 = MathHelper.sin(f * 0.09F) * 0.015F;
		matrixStackIn.scale(1.2F, 1.2F, 1.2F);
		matrixStackIn.scale(1.0F + f1, 1.0F - f1, 1.0F + f1);
		super.scale(entityIn, matrixStackIn, partialTickTime);
	}

	@Override
	public Vector3d getRenderOffset(SpiderNestEntity entityIn, float partialTicks)
	{
		if (entityIn.isCharging())
		{
			return new Vector3d(this.rand.nextGaussian() * 0.02D, 0.0D, this.rand.nextGaussian() * 0.02D);
		}
		else
		{
			return super.getRenderOffset(entityIn, partialTicks);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(SpiderNestEntity entityIn)
	{
		return TEX;
	}
}