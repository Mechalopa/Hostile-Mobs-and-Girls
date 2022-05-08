package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.AbstractGirlModel;
import com.github.mechalopa.hmag.client.model.CreeperGirlArmorModel;
import com.github.mechalopa.hmag.entity.CreeperGirlEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlPowerLayer extends EnergySwirlLayer<CreeperGirlEntity, AbstractGirlModel<CreeperGirlEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
	private final CreeperGirlArmorModel<CreeperGirlEntity> model;

	public CreeperGirlPowerLayer(RenderLayerParent<CreeperGirlEntity, AbstractGirlModel<CreeperGirlEntity>> renderLayerParent, EntityModelSet modelSet)
	{
		super(renderLayerParent);
		this.model = new CreeperGirlArmorModel<>(modelSet.bakeLayer(ModModelLayers.CREEPER_GIRL_POWER_ARMOR));
	}

	@Override
	protected float xOffset(float f)
	{
		return f * 0.01F;
	}

	@Override
	protected ResourceLocation getTextureLocation()
	{
		return TEX;
	}

	@Override
	protected EntityModel<CreeperGirlEntity> model()
	{
		return this.model;
	}
}