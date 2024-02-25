package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.renderer.layers.StrayGirlClothingLayer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrayGirlRenderer extends SkeletonGirlRenderer
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/stray_girl.png");

	public StrayGirlRenderer(EntityRendererProvider.Context context)
	{
		super(context, ModModelLayers.STRAY_GIRL, ModModelLayers.STRAY_GIRL_INNER_ARMOR, ModModelLayers.STRAY_GIRL_OUTER_ARMOR);
		this.addLayer(new StrayGirlClothingLayer<>(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(AbstractSkeleton entity)
	{
		return TEXTURE;
	}
}