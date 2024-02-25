package com.github.mechalopa.hmag.client.renderer.layers;

import java.util.Map;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.GhostModel;
import com.github.mechalopa.hmag.world.entity.GhostEntity;
import com.google.common.collect.Maps;

import net.minecraft.Util;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherGhostClothingLayer extends GhostClothingLayer
{
	private static final Map<GhostEntity.Variant, ResourceLocation> TEXTURES = Util.make(Maps.newEnumMap(GhostEntity.Variant.class), p -> {
		p.put(GhostEntity.Variant.VARIANT_0, new ResourceLocation(HMaG.MODID, "textures/entity/ghost/wither_ghost_skin_0.png"));
		p.put(GhostEntity.Variant.VARIANT_1, new ResourceLocation(HMaG.MODID, "textures/entity/ghost/wither_ghost_skin_1.png"));
		p.put(GhostEntity.Variant.VARIANT_2, new ResourceLocation(HMaG.MODID, "textures/entity/ghost/wither_ghost_skin_2.png"));
		p.put(GhostEntity.Variant.VARIANT_3, new ResourceLocation(HMaG.MODID, "textures/entity/ghost/wither_ghost_skin_3.png"));
		p.put(GhostEntity.Variant.VARIANT_4, new ResourceLocation(HMaG.MODID, "textures/entity/ghost/wither_ghost_skin_4.png"));
	});

	public WitherGhostClothingLayer(RenderLayerParent<GhostEntity, GhostModel<GhostEntity>> renderLayerParent, EntityModelSet modelSet)
	{
		super(renderLayerParent, modelSet);
	}

	@Override
	protected float getAlpha(GhostEntity entity)
	{
		return 0.875F;
	}

	@Override
	public ResourceLocation getLayerTexture(GhostEntity entity)
	{
		return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(GhostEntity.Variant.VARIANT_0));
	}
}