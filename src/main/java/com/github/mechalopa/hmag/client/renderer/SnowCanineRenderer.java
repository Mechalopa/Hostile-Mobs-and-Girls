package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.SnowCanineModel;
import com.github.mechalopa.hmag.client.renderer.layers.SnowCanineEyesLayer;
import com.github.mechalopa.hmag.world.entity.SnowCanineEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnowCanineRenderer extends AbstractGirlRenderer<SnowCanineEntity, SnowCanineModel<SnowCanineEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/snow_canine.png");

	public SnowCanineRenderer(EntityRendererProvider.Context context)
	{
		super(context, new SnowCanineModel<>(context.bakeLayer(ModModelLayers.SNOW_CANINE)), 0.5F);
		this.addLayer(new SnowCanineEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(SnowCanineEntity entity)
	{
		return TEX;
	}
}