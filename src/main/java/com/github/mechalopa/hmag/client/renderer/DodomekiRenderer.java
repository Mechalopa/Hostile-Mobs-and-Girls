package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.DodomekiModel;
import com.github.mechalopa.hmag.client.renderer.layers.DodomekiEyesLayer;
import com.github.mechalopa.hmag.entity.DodomekiEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DodomekiRenderer extends AbstractGirlRenderer<DodomekiEntity, DodomekiModel<DodomekiEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/dodomeki.png");

	public DodomekiRenderer(EntityRendererProvider.Context context)
	{
		super(context, new DodomekiModel<>(context.bakeLayer(ModModelLayers.DODOMEKI)), 0.5F);
		this.addLayer(new DodomekiEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(DodomekiEntity entity)
	{
		return TEX;
	}
}