package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.KoboldModel;
import com.github.mechalopa.hmag.entity.KoboldEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KoboldRenderer extends AbstractGirlRenderer<KoboldEntity, KoboldModel<KoboldEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/kobold.png");

	public KoboldRenderer(EntityRendererProvider.Context context)
	{
		super(context, new KoboldModel<>(context.bakeLayer(ModModelLayers.KOBOLD)), 0.5F);
	}

	@Override
	protected void scale(KoboldEntity entityIn, PoseStack poseStackIn, float partialTickTime)
	{
		poseStackIn.scale(0.875F, 0.875F, 0.875F);
		super.scale(entityIn, poseStackIn, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(KoboldEntity entityIn)
	{
		return TEX;
	}
}