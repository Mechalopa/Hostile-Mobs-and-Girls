package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.BansheeModel;
import com.github.mechalopa.hmag.entity.BansheeEntity;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BansheeLayer extends AbstractClothingLayer<BansheeEntity, BansheeModel<BansheeEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/banshee_overlay.png");
	private final BansheeModel<BansheeEntity> modelOuterLayer = new BansheeModel<>(0.0F);

	public BansheeLayer(IEntityRenderer<BansheeEntity, BansheeModel<BansheeEntity>> entityRendererIn)
	{
		super(entityRendererIn);
	}

	@Override
	protected float getAlpha(BansheeEntity livingEntityIn)
	{
		return 0.8F;
	}

	@Override
	protected EntityModel<BansheeEntity> model()
	{
		return this.modelOuterLayer;
	}

	@Override
	public ResourceLocation getLayerTexture(BansheeEntity livingEntityIn)
	{
		return TEX;
	}
}