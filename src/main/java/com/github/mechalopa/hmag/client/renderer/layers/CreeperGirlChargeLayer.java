package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.client.model.CreeperGirlModel;
import com.github.mechalopa.hmag.entity.CreeperGirlEntity;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlChargeLayer extends EnergyLayer<CreeperGirlEntity, CreeperGirlModel<CreeperGirlEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
	private final CreeperGirlModel<CreeperGirlEntity> model = new CreeperGirlModel<>(2.0F, true, true);

	public CreeperGirlChargeLayer(IEntityRenderer<CreeperGirlEntity, CreeperGirlModel<CreeperGirlEntity>> entityRendererIn)
	{
		super(entityRendererIn);
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