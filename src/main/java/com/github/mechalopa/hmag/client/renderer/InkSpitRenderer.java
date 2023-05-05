package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.InkSpitModel;
import com.github.mechalopa.hmag.world.entity.projectile.InkSpitEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InkSpitRenderer extends EntityRenderer<InkSpitEntity>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/projectile/ink_spit.png");
	private final InkSpitModel<InkSpitEntity> model;

	public InkSpitRenderer(EntityRendererProvider.Context context)
	{
		super(context);
		this.model = new InkSpitModel<>(context.bakeLayer(ModModelLayers.INK_SPIT));
	}

	@Override
	public void render(InkSpitEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight)
	{
		poseStack.pushPose();
		poseStack.translate(0.0D, (double)0.15F, 0.0D);
		poseStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
		poseStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
		this.model.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer vertexconsumer = buffer.getBuffer(this.model.renderType(TEX));
		this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(InkSpitEntity entity)
	{
		return TEX;
	}
}