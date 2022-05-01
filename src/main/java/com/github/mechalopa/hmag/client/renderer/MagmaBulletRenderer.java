package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.entity.projectile.MagmaBulletEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

@OnlyIn(Dist.CLIENT)
public class MagmaBulletRenderer extends EntityRenderer<MagmaBulletEntity>
{
	public MagmaBulletRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.shadowRadius = 0.25F;
	}

	@Override
	protected int getBlockLightLevel(MagmaBulletEntity entityIn, BlockPos pos)
	{
		return 15;
	}

	@Override
	public void render(MagmaBulletEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		matrixStackIn.pushPose();
		matrixStackIn.translate(0.0D, 0.25D, 0.0D);
		float f = ((float)entityIn.tickCount + partialTicks) * 30.0F % 360.0F;
		float f1 = ((float)entityIn.tickCount + partialTicks) * 45.0F % 360.0F;
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
		matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(f1));
		matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
		matrixStackIn.translate(-0.5D, -0.5D, 0.5D);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));
		Minecraft.getInstance().getBlockRenderer().renderBlock(Blocks.MAGMA_BLOCK.defaultBlockState(), matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(MagmaBulletEntity entityIn)
	{
		return PlayerContainer.BLOCK_ATLAS;
	}
}