package com.github.mechalopa.hmag.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
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
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		Minecraft minecraft = Minecraft.getInstance();
		boolean flag = minecraft.shouldEntityAppearGlowing(livingEntity) && livingEntity.isInvisible();

		if (!livingEntity.isInvisible() || flag)
		{
			if (this.canRender(livingEntity))
			{
				EntityModel<T> entitymodel = this.getLayerModel();
				this.getParentModel().copyPropertiesTo(entitymodel);
				entitymodel.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
				entitymodel.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				ResourceLocation resourcelocation = this.getLayerTexture(livingEntity);
				VertexConsumer vertexconsumer;

				if (flag)
				{
					vertexconsumer = buffer.getBuffer(RenderType.outline(resourcelocation));
				}
				else
				{
					vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(resourcelocation));
				}

				entitymodel.renderToBuffer(poseStack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0F), this.getR(livingEntity), this.getG(livingEntity), this.getB(livingEntity), this.getAlpha(livingEntity));
			}
		}
	}

	protected boolean canRender(T livingEntity)
	{
		return true;
	}

	protected float getR(T livingEntity)
	{
		return 1.0F;
	}

	protected float getG(T livingEntity)
	{
		return 1.0F;
	}

	protected float getB(T livingEntity)
	{
		return 1.0F;
	}

	protected float getAlpha(T livingEntity)
	{
		return 1.0F;
	}

	protected abstract ResourceLocation getLayerTexture(T livingEntity);

	protected abstract EntityModel<T> getLayerModel();
}