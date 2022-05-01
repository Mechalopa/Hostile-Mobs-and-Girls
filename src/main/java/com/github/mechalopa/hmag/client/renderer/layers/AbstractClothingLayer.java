package com.github.mechalopa.hmag.client.renderer.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractClothingLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M>
{
	public AbstractClothingLayer(IEntityRenderer<T, M> entityRendererIn)
	{
		super(entityRendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (!livingEntityIn.isInvisible() && this.canRender(livingEntityIn))
		{
			EntityModel<T> entitymodel = this.model();
			this.getParentModel().copyPropertiesTo(entitymodel);
			entitymodel.prepareMobModel(livingEntityIn, limbSwing, limbSwingAmount, partialTicks);
			entitymodel.setupAnim(livingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			ResourceLocation resourcelocation = this.getLayerTexture(livingEntityIn);
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucent(resourcelocation));
			entitymodel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getOverlayCoords(livingEntityIn, 0.0F), this.getR(livingEntityIn), this.getG(livingEntityIn), this.getB(livingEntityIn), this.getAlpha(livingEntityIn));
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