package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.JiangshiModel;
import com.github.mechalopa.hmag.world.entity.JiangshiEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
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

		if (this.getModel() != null && !this.getModel().riding)
		{
			float f = entity.tickCount + partialTicks;
			poseStack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(f * 0.03F) * 1.0F));
			poseStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.cos(f * 0.05F) * 1.5F * (entity.getMainArm() == HumanoidArm.LEFT ? -1.0F : 1.0F)));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(JiangshiEntity entity)
	{
		return TEX;
	}
}