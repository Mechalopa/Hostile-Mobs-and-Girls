package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.BansheeModel;
import com.github.mechalopa.hmag.entity.BansheeEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BansheeLayer extends AbstractClothingLayer<BansheeEntity, BansheeModel<BansheeEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/banshee_overlay.png");
	private final BansheeModel<BansheeEntity> model = new BansheeModel<>(0.0F);

	public BansheeLayer(IEntityRenderer<BansheeEntity, BansheeModel<BansheeEntity>> entityRendererIn)
	{
		super(entityRendererIn);
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
		return TEX;
	}
}