package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.SwamperModel;
import com.github.mechalopa.hmag.world.entity.SwamperEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SwamperRenderer extends MobRenderer<SwamperEntity, SwamperModel<SwamperEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/swamper.png");

	public SwamperRenderer(EntityRendererProvider.Context context)
	{
		super(context, new SwamperModel<>(context.bakeLayer(ModModelLayers.SWAMPER)), 0.7F);
	}

	@Override
	protected void scale(SwamperEntity entity, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(1.2F, 1.2F, 1.2F);
		super.scale(entity, poseStack, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(SwamperEntity entity)
	{
		return TEXTURE;
	}

	@Override
	protected boolean isShaking(SwamperEntity entity)
	{
		return super.isShaking(entity) || entity.isSuffocating();
	}
}