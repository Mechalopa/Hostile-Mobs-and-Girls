package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.DodomekiModel;
import com.github.mechalopa.hmag.world.entity.DodomekiEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DodomekiEyesLayer<T extends DodomekiEntity, M extends DodomekiModel<T>> extends EyesLayer<T, M>
{
	private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation(HMaG.MODID, "textures/entity/dodomeki_eyes.png"));

	public DodomekiEyesLayer(RenderLayerParent<T, M> renderLayerParent)
	{
		super(renderLayerParent);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLightIn, T livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		final float f = Mth.clamp(livingEntityIn.getEyesGlowingAnimationScale(partialTicks), 0.0F, 1.0F);

		if (f > 0.0F)
		{
			VertexConsumer vertexconsumer = buffer.getBuffer(this.renderType());
			this.getParentModel().renderToBuffer(poseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, f, f, f, 1.0F);
		}
	}

	@Override
	public RenderType renderType()
	{
		return RENDER_TYPE;
	}
}