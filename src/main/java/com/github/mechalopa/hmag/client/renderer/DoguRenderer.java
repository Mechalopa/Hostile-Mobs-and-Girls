package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.DoguModel;
import com.github.mechalopa.hmag.client.renderer.layers.DoguEyesLayer;
import com.github.mechalopa.hmag.world.entity.DoguEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DoguRenderer extends HumanoidMobRenderer<DoguEntity, DoguModel<DoguEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/dogu.png");

	public DoguRenderer(EntityRendererProvider.Context context)
	{
		super(context, new DoguModel<>(context.bakeLayer(ModModelLayers.DOGU)), 0.5F);
		this.addLayer(new DoguEyesLayer<>(this));
	}

	@Override
	protected void scale(DoguEntity entity, PoseStack poseStack, float partialTickTime)
	{
		super.scale(entity, poseStack, partialTickTime);
		float f = (float)entity.tickCount + partialTickTime;
		poseStack.translate(0.0F, -0.06F + Mth.sin(f * 0.06F) * 0.04F, 0.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(DoguEntity entity)
	{
		return TEX;
	}
}