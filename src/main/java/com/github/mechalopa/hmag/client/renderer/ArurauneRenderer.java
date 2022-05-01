package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.ArurauneModel;
import com.github.mechalopa.hmag.entity.ArurauneEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArurauneRenderer extends AbstractGirlRenderer<ArurauneEntity, ArurauneModel<ArurauneEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/aruraune.png");

	public ArurauneRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ArurauneModel<>(), 0.5F, -1);
	}

	@Override
	public ResourceLocation getTextureLocation(ArurauneEntity entityIn)
	{
		return TEX;
	}
}