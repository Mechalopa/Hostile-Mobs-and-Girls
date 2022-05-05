package com.github.mechalopa.hmag.client.renderer;

import java.util.Random;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.SpiderNestModel;
import com.github.mechalopa.hmag.client.renderer.layers.SpiderNestEyesLayer;
import com.github.mechalopa.hmag.entity.SpiderNestEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpiderNestRenderer extends MobRenderer<SpiderNestEntity, SpiderNestModel<SpiderNestEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/spider_nest.png");
	private final Random rand = new Random();

	public SpiderNestRenderer(EntityRendererProvider.Context context)
	{
		super(context, new SpiderNestModel<>(context.bakeLayer(ModModelLayers.SPIDER_NEST)), 0.8F);
		this.addLayer(new SpiderNestEyesLayer<>(this));
	}

	@Override
	protected void scale(SpiderNestEntity entity, PoseStack poseStack, float partialTickTime)
	{
		float f = (float)entity.tickCount + partialTickTime;
		float f1 = Mth.sin(f * 0.09F) * 0.015F;
		poseStack.scale(1.2F, 1.2F, 1.2F);
		poseStack.scale(1.0F + f1, 1.0F - f1, 1.0F + f1);
		super.scale(entity, poseStack, partialTickTime);
	}

	@Override
	public Vec3 getRenderOffset(SpiderNestEntity entity, float partialTicks)
	{
		if (entity.isCharging())
		{
			return new Vec3(this.rand.nextGaussian() * 0.02D, 0.0D, this.rand.nextGaussian() * 0.02D);
		}
		else
		{
			return super.getRenderOffset(entity, partialTicks);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(SpiderNestEntity entity)
	{
		return TEX;
	}
}