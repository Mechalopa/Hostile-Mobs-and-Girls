package net.mechalopa.hmag.client.renderer.layers;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.model.SpiderNestModel;
import net.mechalopa.hmag.entity.SpiderNestEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpiderNestEyesLayer<T extends SpiderNestEntity, M extends SpiderNestModel<T>> extends AbstractEyesLayer<T, M>
{
	private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation(HMaG.MODID, "textures/entity/spider_nest_eyes.png"));

	public SpiderNestEyesLayer(IEntityRenderer<T, M> rendererIn)
	{
		super(rendererIn);
	}

	@Override
	public RenderType renderType()
	{
		return RENDER_TYPE;
	}
}