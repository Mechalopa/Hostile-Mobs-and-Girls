package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.world.entity.projectile.MagmaBulletEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

@OnlyIn(Dist.CLIENT)
public class MagmaBulletRenderer extends EntityRenderer<MagmaBulletEntity>
{
	public MagmaBulletRenderer(EntityRendererProvider.Context context)
	{
		super(context);
		this.shadowRadius = 0.25F;
	}

	@Override
	protected int getBlockLightLevel(MagmaBulletEntity entity, BlockPos pos)
	{
		return 15;
	}

	@Override
	public void render(MagmaBulletEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight)
	{
		poseStack.pushPose();
		poseStack.translate(0.0D, 0.25D, 0.0D);
		float f = ((float)entity.tickCount + partialTicks) * 30.0F % 360.0F;
		float f1 = ((float)entity.tickCount + partialTicks) * 45.0F % 360.0F;
		poseStack.mulPose(Vector3f.YP.rotationDegrees(f));
		poseStack.mulPose(Vector3f.XP.rotationDegrees(f1));
		poseStack.scale(0.5F, 0.5F, 0.5F);
		poseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
		poseStack.translate(-0.5D, -0.5D, 0.5D);
		poseStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.MAGMA_BLOCK.defaultBlockState(), poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(MagmaBulletEntity entity)
	{
		return InventoryMenu.BLOCK_ATLAS;
	}
}