package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.ScorpionModel;
import com.github.mechalopa.hmag.world.entity.ScorpionEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScorpionRenderer extends MobRenderer<ScorpionEntity, ScorpionModel<ScorpionEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/scorpion.png");

	public ScorpionRenderer(EntityRendererProvider.Context context)
	{
		super(context, new ScorpionModel<>(context.bakeLayer(ModModelLayers.SCORPION)), 0.8F);
	}

	@Override
	protected void scale(ScorpionEntity entity, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(1.1F, 1.1F, 1.1F);
		super.scale(entity, poseStack, partialTickTime);
	}

	@Override
	protected float getFlipDegrees(ScorpionEntity entity)
	{
		return 180.0F;
	}

	@Override
	public ResourceLocation getTextureLocation(ScorpionEntity entity)
	{
		return TEXTURE;
	}
}