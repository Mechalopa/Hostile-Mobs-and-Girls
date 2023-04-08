package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.JiangshiModel;
import com.github.mechalopa.hmag.world.entity.JiangshiEntity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JiangshiEyesLayer<T extends JiangshiEntity, M extends JiangshiModel<T>> extends EyesLayer2<T, M>
{
	private static final RenderType RENDER_TYPE0 = RenderType.eyes(new ResourceLocation(HMaG.MODID, "textures/entity/jiangshi/jiangshi_eyes_0.png"));
	private static final RenderType RENDER_TYPE1 = RenderType.eyes(new ResourceLocation(HMaG.MODID, "textures/entity/jiangshi/jiangshi_eyes_1.png"));

	public JiangshiEyesLayer(RenderLayerParent<T, M> renderLayerParent)
	{
		super(renderLayerParent);
	}

	@Override
	public RenderType renderType(T entity)
	{
		switch (entity.getVariant())
		{
		case 1:
			return RENDER_TYPE1;
		default:
			return RENDER_TYPE0;
		}
	}
}