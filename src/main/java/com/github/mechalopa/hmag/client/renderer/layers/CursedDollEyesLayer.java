package com.github.mechalopa.hmag.client.renderer.layers;

import java.util.Map;

import com.github.mechalopa.hmag.client.model.CursedDollModel;
import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.CommonOrUncommonVariant;
import com.github.mechalopa.hmag.world.entity.CursedDollEntity;
import com.google.common.collect.Maps;

import net.minecraft.Util;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CursedDollEyesLayer<T extends CursedDollEntity, M extends CursedDollModel<T>> extends EyesLayer2<T, M>
{
	private static final Map<CommonOrUncommonVariant, RenderType> RENDER_TYPES = Util.make(Maps.newEnumMap(CommonOrUncommonVariant.class), p -> {
		p.put(CommonOrUncommonVariant.COMMON, RenderType.eyes(ModClientUtils.getHMaGEntityTexture("cursed_doll/cursed_doll_eyes_0")));
		p.put(CommonOrUncommonVariant.UNCOMMON, RenderType.eyes(ModClientUtils.getHMaGEntityTexture("cursed_doll/cursed_doll_eyes_1")));
	});

	public CursedDollEyesLayer(RenderLayerParent<T, M> renderLayerParent)
	{
		super(renderLayerParent);
	}

	@Override
	public RenderType renderType(T entity)
	{
		return RENDER_TYPES.getOrDefault(entity.getVariant(), RENDER_TYPES.get(CommonOrUncommonVariant.COMMON));
	}
}