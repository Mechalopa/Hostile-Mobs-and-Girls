package com.github.mechalopa.hmag.client.renderer.layers;

import java.util.Map;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.BansheeModel;
import com.github.mechalopa.hmag.world.entity.BansheeEntity;
import com.github.mechalopa.hmag.world.entity.CommonOrUncommonVariant;
import com.google.common.collect.Maps;

import net.minecraft.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BansheeLayer extends AbstractClothingLayer<BansheeEntity, BansheeModel<BansheeEntity>>
{
	private static final Map<CommonOrUncommonVariant, ResourceLocation> TEXTURES = Util.make(Maps.newEnumMap(CommonOrUncommonVariant.class), p -> {
		p.put(CommonOrUncommonVariant.COMMON, new ResourceLocation(HMaG.MODID, "textures/entity/banshee/banshee_overlay_0.png"));
		p.put(CommonOrUncommonVariant.UNCOMMON, new ResourceLocation(HMaG.MODID, "textures/entity/banshee/banshee_overlay_1.png"));
	});
	private final BansheeModel<BansheeEntity> model;

	public BansheeLayer(RenderLayerParent<BansheeEntity, BansheeModel<BansheeEntity>> renderLayerParent, EntityModelSet modelSet)
	{
		super(renderLayerParent);
		this.model = new BansheeModel<>(modelSet.bakeLayer(ModModelLayers.BANSHEE));
	}

	@Override
	protected float getAlpha(BansheeEntity entity)
	{
		return 0.8F;
	}

	@Override
	protected EntityModel<BansheeEntity> getLayerModel()
	{
		return this.model;
	}

	@Override
	public ResourceLocation getLayerTexture(BansheeEntity entity)
	{
		return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(CommonOrUncommonVariant.COMMON));
	}
}