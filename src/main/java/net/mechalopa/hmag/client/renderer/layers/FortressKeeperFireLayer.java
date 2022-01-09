package net.mechalopa.hmag.client.renderer.layers;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.mechalopa.hmag.client.model.FortressKeeperModel;
import net.mechalopa.hmag.entity.FortressKeeperEntity;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

@OnlyIn(Dist.CLIENT)
public class FortressKeeperFireLayer extends LayerRenderer<FortressKeeperEntity, FortressKeeperModel<FortressKeeperEntity>>
{
	public FortressKeeperFireLayer(IEntityRenderer<FortressKeeperEntity, FortressKeeperModel<FortressKeeperEntity>> rendererIn)
	{
		super(rendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, FortressKeeperEntity livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (!livingEntityIn.isInvisible() && !livingEntityIn.isInWaterOrBubble())
		{
			this.renderFire(matrixStackIn, bufferIn, packedLightIn, livingEntityIn, HandSide.RIGHT);
			this.renderFire(matrixStackIn, bufferIn, packedLightIn, livingEntityIn, HandSide.LEFT);
		}
	}

	private void renderFire(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, FortressKeeperEntity livingEntityIn, HandSide hand)
	{
		matrixStackIn.pushPose();
		this.getParentModel().translateToHand(hand, matrixStackIn);
		float f = 0.4375F;
		matrixStackIn.translate((hand == HandSide.LEFT ? 0.25D : -0.25D) + 0.21875D, -0.5D, -0.21875D);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
		matrixStackIn.scale(f, -f, -f);
		Minecraft.getInstance().getBlockRenderer().renderBlock(Blocks.FIRE.defaultBlockState(), matrixStackIn, bufferIn, packedLightIn, LivingRenderer.getOverlayCoords(livingEntityIn, 0.0F), EmptyModelData.INSTANCE);
		matrixStackIn.popPose();
	}
}