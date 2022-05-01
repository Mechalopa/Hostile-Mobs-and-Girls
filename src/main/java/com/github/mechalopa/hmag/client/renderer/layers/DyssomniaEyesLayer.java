package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.DyssomniaModel;
import com.github.mechalopa.hmag.entity.DyssomniaEntity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DyssomniaEyesLayer<T extends DyssomniaEntity, M extends DyssomniaModel<T>> extends AbstractEyesLayer<T, M>
{
	private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation(HMaG.MODID, "textures/entity/dyssomnia_eyes.png"));

	public DyssomniaEyesLayer(IEntityRenderer<T, M> rendererIn)
	{
		super(rendererIn);
	}

	@Override
	public RenderType renderType()
	{
		return RENDER_TYPE;
	}
}