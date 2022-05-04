package com.github.mechalopa.hmag.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractClothingLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M>
{
	public AbstractClothingLayer(RenderLayerParent<T, M> renderLayerParent)
	{
		super(renderLayerParent);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLightIn, T livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (!livingEntityIn.isInvisible() && this.canRender(livingEntityIn))
		{
			EntityModel<T> entitymodel = this.model();
			this.getParentModel().copyPropertiesTo(entitymodel);
			entitymodel.prepareMobModel(livingEntityIn, limbSwing, limbSwingAmount, partialTicks);
			entitymodel.setupAnim(livingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			ResourceLocation resourcelocation = this.getLayerTexture(livingEntityIn);
			VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(resourcelocation));
			entitymodel.renderToBuffer(poseStack, vertexconsumer, packedLightIn, LivingEntityRenderer.getOverlayCoords(livingEntityIn, 0.0F), this.getR(livingEntityIn), this.getG(livingEntityIn), this.getB(livingEntityIn), this.getAlpha(livingEntityIn));
		}
	}

	protected boolean canRender(T livingEntityIn)
	{
		return true;
	}

	protected float getR(T livingEntityIn)
	{
		return 1.0F;
	}

	protected float getG(T livingEntityIn)
	{
		return 1.0F;
	}

	protected float getB(T livingEntityIn)
	{
		return 1.0F;
	}

	protected float getAlpha(T livingEntityIn)
	{
		return 1.0F;
	}

	protected abstract ResourceLocation getLayerTexture(T livingEntityIn);

	protected abstract EntityModel<T> model();
}