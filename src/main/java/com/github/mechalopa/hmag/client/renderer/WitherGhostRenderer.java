package com.github.mechalopa.hmag.client.renderer;

import java.util.Map;

import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.renderer.layers.GhostClothingLayer;
import com.github.mechalopa.hmag.client.renderer.layers.WitherGhostClothingLayer;
import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.GhostEntity;
import com.google.common.collect.Maps;

import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherGhostRenderer extends GhostRenderer
{
	private static final Map<GhostEntity.Variant, ResourceLocation> TEXTURES = Util.make(Maps.newEnumMap(GhostEntity.Variant.class), p -> {
		p.put(GhostEntity.Variant.VARIANT_0, ModClientUtils.getHMaGEntityTexture("ghost/wither_ghost_0"));
		p.put(GhostEntity.Variant.VARIANT_1, ModClientUtils.getHMaGEntityTexture("ghost/wither_ghost_1"));
		p.put(GhostEntity.Variant.VARIANT_2, ModClientUtils.getHMaGEntityTexture("ghost/wither_ghost_2"));
		p.put(GhostEntity.Variant.VARIANT_3, ModClientUtils.getHMaGEntityTexture("ghost/wither_ghost_3"));
		p.put(GhostEntity.Variant.VARIANT_4, ModClientUtils.getHMaGEntityTexture("ghost/wither_ghost_4"));
	});

	public WitherGhostRenderer(EntityRendererProvider.Context context)
	{
		super(context, ModModelLayers.WITHER_GHOST, ModModelLayers.WITHER_GHOST_INNER_ARMOR, ModModelLayers.WITHER_GHOST_OUTER_ARMOR);
	}

	@Override
	public ResourceLocation getTextureLocation(GhostEntity entity)
	{
		return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(GhostEntity.Variant.VARIANT_0));
	}

	@Override
	public GhostClothingLayer getLayer(EntityRendererProvider.Context context)
	{
		return new WitherGhostClothingLayer(this, context.getModelSet());
	}
}