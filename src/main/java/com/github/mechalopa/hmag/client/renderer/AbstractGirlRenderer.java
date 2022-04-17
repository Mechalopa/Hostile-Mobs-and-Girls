package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.client.renderer.layers.HeldItemLayer2;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.MobEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractGirlRenderer<T extends MobEntity, M extends BipedModel<T>> extends BipedRenderer<T, M>
{
	public AbstractGirlRenderer(EntityRendererManager renderManagerIn, M modelBipedIn, float shadowSize)
	{
		this(renderManagerIn, modelBipedIn, shadowSize, 0);
	}

	public AbstractGirlRenderer(EntityRendererManager renderManagerIn, M modelBipedIn, float shadowSize, int heldItemTranslateX)
	{
		super(renderManagerIn, modelBipedIn, shadowSize);
		for (LayerRenderer<T, M> layer : this.layers)
		{
			if (layer != null && layer instanceof HeldItemLayer)
			{
				this.layers.remove(layer);
				break;
			}
		}
		this.addLayer(new HeldItemLayer2<>(this, heldItemTranslateX));
	}
}