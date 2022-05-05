package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.client.renderer.layers.ItemInHandLayer2;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractGirlRenderer<T extends Mob, M extends HumanoidModel<T>> extends HumanoidMobRenderer<T, M>
{
	public AbstractGirlRenderer(EntityRendererProvider.Context context, M modelIn, float shadowSize)
	{
		this(context, modelIn, shadowSize, 0);
	}

	public AbstractGirlRenderer(EntityRendererProvider.Context context, M modelIn, float shadowSize, int itemTranslateX)
	{
		super(context, modelIn, shadowSize);
		for (RenderLayer<T, M> layer : this.layers)
		{
			if (layer != null && layer instanceof ItemInHandLayer)
			{
				this.layers.remove(layer);
				break;
			}
		}
		this.addLayer(new ItemInHandLayer2<>(this, itemTranslateX));
	}
}