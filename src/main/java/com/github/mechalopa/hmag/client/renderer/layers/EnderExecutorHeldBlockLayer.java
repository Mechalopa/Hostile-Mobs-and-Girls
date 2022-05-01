package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.client.model.EnderExecutorModel;
import com.github.mechalopa.hmag.entity.EnderExecutorEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

@OnlyIn(Dist.CLIENT)
public class EnderExecutorHeldBlockLayer extends LayerRenderer<EnderExecutorEntity, EnderExecutorModel<EnderExecutorEntity>>
{
	public EnderExecutorHeldBlockLayer(IEntityRenderer<EnderExecutorEntity, EnderExecutorModel<EnderExecutorEntity>> rendererIn)
	{
		super(rendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EnderExecutorEntity livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		BlockState blockstate = livingEntityIn.getCarriedBlock();

		if (blockstate != null)
		{
			matrixStackIn.pushPose();
			this.getParentModel().translateToHand(livingEntityIn.getMainArm() == HandSide.RIGHT ? HandSide.LEFT : HandSide.RIGHT, matrixStackIn);
			matrixStackIn.translate(0.0D, 0.875D, -0.05D);
			float f = (float)livingEntityIn.tickCount + partialTicks;
			matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(MathHelper.cos(f * 0.33F + 0.2F) * 6.0F));
			matrixStackIn.mulPose(Vector3f.YP.rotationDegrees((f * 6.0F + 15.0F) % 360.0F));
			matrixStackIn.translate(0.1875D, 0.1875D, 0.1875D);
			float f1 = 0.375F;
			matrixStackIn.scale(-f1, -f1, f1);
			matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));
			Minecraft.getInstance().getBlockRenderer().renderBlock(blockstate, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
			matrixStackIn.popPose();
		}
	}
}