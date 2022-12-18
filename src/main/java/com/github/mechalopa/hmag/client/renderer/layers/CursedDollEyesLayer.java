package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.CursedDollModel;
import com.github.mechalopa.hmag.world.entity.CursedDollEntity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CursedDollEyesLayer<T extends CursedDollEntity, M extends CursedDollModel<T>> extends EyesLayer2<T, M>
{
	private static final RenderType RENDER_TYPE0 = RenderType.eyes(new ResourceLocation(HMaG.MODID, "textures/entity/cursed_doll_eyes_0.png"));
	private static final RenderType RENDER_TYPE1 = RenderType.eyes(new ResourceLocation(HMaG.MODID, "textures/entity/cursed_doll_eyes_1.png"));

	public CursedDollEyesLayer(RenderLayerParent<T, M> renderLayerParent)
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