package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.GiantMummyModel;
import com.github.mechalopa.hmag.client.renderer.layers.GiantMummyEyesLayer;
import com.github.mechalopa.hmag.world.entity.GiantMummyEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GiantMummyRenderer extends HumanoidMobRenderer<GiantMummyEntity, GiantMummyModel<GiantMummyEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/giant_mummy.png");

	public  GiantMummyRenderer(EntityRendererProvider.Context context)
	{
		super(context, new GiantMummyModel<>(context.bakeLayer(ModModelLayers.GIANT_MUMMY)), 0.7F);
		this.addLayer(new GiantMummyEyesLayer<>(this));
	}

	@Override
	protected void scale(GiantMummyEntity entity, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(1.1F, 1.1F, 1.1F);
		super.scale(entity, poseStack, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(GiantMummyEntity entity)
	{
		return TEX;
	}
}