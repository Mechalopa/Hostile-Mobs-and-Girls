package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.SavagefangModel;
import com.github.mechalopa.hmag.client.renderer.layers.SavagefangEyesLayer;
import com.github.mechalopa.hmag.world.entity.SavagefangEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SavagefangRenderer extends MobRenderer<SavagefangEntity, SavagefangModel<SavagefangEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/savagefang.png");

	public SavagefangRenderer(EntityRendererProvider.Context context)
	{
		super(context, new SavagefangModel<>(context.bakeLayer(ModModelLayers.SAVAGEFANG)), 0.4F);
		this.addLayer(new SavagefangEyesLayer<>(this));
	}

	@Override
	protected void scale(SavagefangEntity entity, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(1.25F, 1.25F, 1.25F);
		super.scale(entity, poseStack, partialTickTime);
	}

	@Override
	protected void setupRotations(SavagefangEntity entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);

		float f = 4.3F * Mth.sin(0.6F * ageInTicks);
		poseStack.mulPose(Vector3f.YP.rotationDegrees(f));

		if (entity.isLaunched())
		{
			poseStack.translate((double)0.1F, (double)0.1F, (double)-0.1F);
			poseStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		}
		else
		{
			poseStack.mulPose(Vector3f.XP.rotationDegrees(entity.getXRotAnimationScale(partialTicks) * 180.0F));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(SavagefangEntity entity)
	{
		return TEXTURE;
	}
}