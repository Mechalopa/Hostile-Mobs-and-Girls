package com.github.mechalopa.hmag.client.renderer;

import java.util.Map;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.GhostModel;
import com.github.mechalopa.hmag.client.renderer.layers.GhostClothingLayer;
import com.github.mechalopa.hmag.world.entity.GhostEntity;
import com.google.common.collect.Maps;

import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhostRenderer extends HumanoidMobRenderer<GhostEntity, GhostModel<GhostEntity>>
{
	private static final Map<GhostEntity.Variant, ResourceLocation> TEXTURES = Util.make(Maps.newEnumMap(GhostEntity.Variant.class), p -> {
		p.put(GhostEntity.Variant.VARIANT_0, new ResourceLocation(HMaG.MODID, "textures/entity/ghost/ghost_0.png"));
		p.put(GhostEntity.Variant.VARIANT_1, new ResourceLocation(HMaG.MODID, "textures/entity/ghost/ghost_1.png"));
		p.put(GhostEntity.Variant.VARIANT_2, new ResourceLocation(HMaG.MODID, "textures/entity/ghost/ghost_2.png"));
		p.put(GhostEntity.Variant.VARIANT_3, new ResourceLocation(HMaG.MODID, "textures/entity/ghost/ghost_3.png"));
		p.put(GhostEntity.Variant.VARIANT_4, new ResourceLocation(HMaG.MODID, "textures/entity/ghost/ghost_4.png"));
	});

	public GhostRenderer(EntityRendererProvider.Context context)
	{
		this(context, ModModelLayers.GHOST, ModModelLayers.GHOST_INNER_ARMOR, ModModelLayers.GHOST_OUTER_ARMOR);
	}

	public GhostRenderer(EntityRendererProvider.Context context, ModelLayerLocation location, ModelLayerLocation location1, ModelLayerLocation location2)
	{
		this(context, new GhostModel<>(context.bakeLayer(location)), new GhostModel<>(context.bakeLayer(location1)), new GhostModel<>(context.bakeLayer(location2)));
	}

	public GhostRenderer(EntityRendererProvider.Context context, GhostModel<GhostEntity> model, GhostModel<GhostEntity> model1, GhostModel<GhostEntity> model2)
	{
		super(context, model, 0.375F);
		this.addLayer(new HumanoidArmorLayer<>(this, model1, model2, context.getModelManager()));
		this.addLayer(this.getLayer(context));
	}

	@Override
	protected int getBlockLightLevel(GhostEntity entity, BlockPos pos)
	{
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(GhostEntity entity)
	{
		return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(GhostEntity.Variant.VARIANT_0));
	}

	public GhostClothingLayer getLayer(EntityRendererProvider.Context context)
	{
		return new GhostClothingLayer(this, context.getModelSet());
	}
}