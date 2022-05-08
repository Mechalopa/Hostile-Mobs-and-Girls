package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.ArurauneModel;
import com.github.mechalopa.hmag.world.entity.ArurauneEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArurauneRenderer extends AbstractGirlRenderer<ArurauneEntity, ArurauneModel<ArurauneEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/aruraune.png");

	public ArurauneRenderer(EntityRendererProvider.Context context)
	{
		super(context, new ArurauneModel<>(context.bakeLayer(ModModelLayers.ARURAUNE)), 0.5F, -1);
	}

	@Override
	public ResourceLocation getTextureLocation(ArurauneEntity entity)
	{
		return TEX;
	}
}