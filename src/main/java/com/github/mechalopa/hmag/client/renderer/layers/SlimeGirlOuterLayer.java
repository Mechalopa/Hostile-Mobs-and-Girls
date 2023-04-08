package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.SlimeGirlModel;
import com.github.mechalopa.hmag.world.entity.SlimeGirlEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlimeGirlOuterLayer extends AbstractClothingLayer<SlimeGirlEntity, SlimeGirlModel<SlimeGirlEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/slime_girl/slime_girl_overlay.png");
	private final SlimeGirlModel<SlimeGirlEntity> model;

	public SlimeGirlOuterLayer(RenderLayerParent<SlimeGirlEntity, SlimeGirlModel<SlimeGirlEntity>> renderLayerParent, EntityModelSet modelSet)
	{
		super(renderLayerParent);
		this.model = new SlimeGirlModel<>(modelSet.bakeLayer(ModModelLayers.SLIME_GIRL));
		this.model.setOuterLayer(true);
	}

	@Override
	protected float getR(SlimeGirlEntity entity)
	{
		return (entity.getColor())[0];
	}

	@Override
	protected float getG(SlimeGirlEntity entity)
	{
		return (entity.getColor())[1];
	}

	@Override
	protected float getB(SlimeGirlEntity entity)
	{
		return (entity.getColor())[2];
	}

	@Override
	protected float getAlpha(SlimeGirlEntity entity)
	{
		return 0.5F;
	}

	@Override
	protected EntityModel<SlimeGirlEntity> getLayerModel()
	{
		return this.model;
	}

	@Override
	public ResourceLocation getLayerTexture(SlimeGirlEntity entity)
	{
		return TEX;
	}
}