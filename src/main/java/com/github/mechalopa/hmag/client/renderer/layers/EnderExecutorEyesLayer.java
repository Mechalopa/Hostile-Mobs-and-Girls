package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.client.model.EnderExecutorModel;
import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.EnderExecutorEntity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderExecutorEyesLayer<T extends EnderExecutorEntity, M extends EnderExecutorModel<T>> extends EyesLayer2<T, M>
{
	private static final RenderType RENDER_TYPE_0 = RenderType.eyes(ModClientUtils.getHMaGEntityTexture("ender_executor/ender_executor_eyes"));
	private static final RenderType RENDER_TYPE_1 = RenderType.eyes(ModClientUtils.getHMaGEntityTexture("ender_executor/ender_executor_eyes_is_beam"));

	public EnderExecutorEyesLayer(RenderLayerParent<T, M> renderLayerParent)
	{
		super(renderLayerParent);
	}

	@Override
	public RenderType renderType(T entity)
	{
		return this.getParentModel().beamAttacking && entity.tickCount % 2 == 0 ? RENDER_TYPE_1 : RENDER_TYPE_0;
	}
}