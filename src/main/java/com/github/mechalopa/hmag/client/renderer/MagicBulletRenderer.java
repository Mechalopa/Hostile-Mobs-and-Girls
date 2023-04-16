package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.world.entity.projectile.MagicBulletEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagicBulletRenderer extends EntityRenderer<MagicBulletEntity>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/projectile/magic_bullet_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/projectile/magic_bullet_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/projectile/magic_bullet_2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/projectile/magic_bullet_3.png");

	public MagicBulletRenderer(EntityRendererProvider.Context context)
	{
		super(context);
	}

	@Override
	protected int getBlockLightLevel(MagicBulletEntity entity, BlockPos pos)
	{
		switch (entity.getVariant())
		{
		case 1:
			return Mth.clamp(super.getBlockLightLevel(entity, pos) + 7, 0, 15);
		default:
			return 15;
		}
	}

	@Override
	public void render(MagicBulletEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight)
	{
		poseStack.pushPose();
		poseStack.scale(2.0F, 2.0F, 2.0F);
		poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
		poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
		float f = (float)entity.tickCount + partialTicks;
		float f1 = 0.5F + (entity.getVariant() == 2 ? Mth.sin(f * 0.8F) * 0.03F : Mth.sin(f * 0.4F) * 0.09F);
		poseStack.scale(f1, f1, f1);
		PoseStack.Pose posestack$pose = poseStack.last();
		Matrix4f matrix4f = posestack$pose.pose();
		Matrix3f matrix3f = posestack$pose.normal();
		VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entity)));
		this.drawVertex(vertexconsumer, matrix4f, matrix3f, packedLight, 0.0F, 0, 0, 1);
		this.drawVertex(vertexconsumer, matrix4f, matrix3f, packedLight, 1.0F, 0, 1, 1);
		this.drawVertex(vertexconsumer, matrix4f, matrix3f, packedLight, 1.0F, 1, 1, 0);
		this.drawVertex(vertexconsumer, matrix4f, matrix3f, packedLight, 0.0F, 1, 0, 0);
		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	private void drawVertex(VertexConsumer vertexConsumer, Matrix4f matrix, Matrix3f normals, int packedLightIn, float f, int i, int j, int k)
	{
		vertexConsumer.vertex(matrix, f - 0.5F, (float)i - 0.25F, 0.0F).color(255, 255, 255, 255).uv((float)j, (float)k).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(normals, 0.0F, 1.0F, 0.0F).endVertex();
	}

	@Override
	public ResourceLocation getTextureLocation(MagicBulletEntity entity)
	{
		switch (entity.getVariant())
		{
		case 1:
			return TEX1;
		case 2:
			return TEX2;
		case 3:
			return TEX3;
		default:
			return TEX0;
		}
	}
}