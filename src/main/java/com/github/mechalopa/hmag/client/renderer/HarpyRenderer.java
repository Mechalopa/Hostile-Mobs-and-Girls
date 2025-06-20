package com.github.mechalopa.hmag.client.renderer;

import java.util.Map;

import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.HarpyModel;
import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.HarpyEntity;
import com.google.common.collect.Maps;

import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HarpyRenderer extends AbstractGirlRenderer<HarpyEntity, HarpyModel<HarpyEntity>>
{
	private static final Map<HarpyEntity.Variant, ResourceLocation> TEXTURES = Util.make(Maps.newEnumMap(HarpyEntity.Variant.class), p -> {
		p.put(HarpyEntity.Variant.GOLD, ModClientUtils.getHMaGEntityTexture("harpy/harpy_0"));
		p.put(HarpyEntity.Variant.ORANGE, ModClientUtils.getHMaGEntityTexture("harpy/harpy_1"));
		p.put(HarpyEntity.Variant.BEIGE, ModClientUtils.getHMaGEntityTexture("harpy/harpy_2"));
		p.put(HarpyEntity.Variant.BROWN, ModClientUtils.getHMaGEntityTexture("harpy/harpy_3"));
		p.put(HarpyEntity.Variant.GRAY, ModClientUtils.getHMaGEntityTexture("harpy/harpy_4"));
		p.put(HarpyEntity.Variant.WHITE, ModClientUtils.getHMaGEntityTexture("harpy/harpy_5"));
		p.put(HarpyEntity.Variant.PINK, ModClientUtils.getHMaGEntityTexture("harpy/harpy_6"));
	});

	public HarpyRenderer(EntityRendererProvider.Context context)
	{
		super(context, new HarpyModel<>(context.bakeLayer(ModModelLayers.HARPY)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(HarpyEntity entity)
	{
		return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(HarpyEntity.Variant.GOLD));
	}
}