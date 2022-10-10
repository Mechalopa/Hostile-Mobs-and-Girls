package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.JiangshiModel;
import com.github.mechalopa.hmag.world.entity.JiangshiEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JiangshiRenderer extends AbstractGirlRenderer<JiangshiEntity, JiangshiModel<JiangshiEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/jiangshi.png");

	public JiangshiRenderer(EntityRendererProvider.Context context)
	{
		super(context, new JiangshiModel<>(context.bakeLayer(ModModelLayers.JIANGSHI)), 0.5F);
	}

	@Override
	protected void setupRotations(JiangshiEntity entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);

		if (this.getModel() != null && !this.getModel().riding && !((double)entity.animationSpeed < 0.01D))
		{
			float f = 15.0F;
			float f1 = entity.animationPosition - entity.animationSpeed * (1.0F - partialTicks) + 7.0F;
			float f2 = (Math.abs(f1 % f - 7.5F) - 3.75F) / 3.75F;
			poseStack.mulPose(Vector3f.ZP.rotationDegrees(2.5F * f2));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(JiangshiEntity entity)
	{
		return TEX;
	}
}