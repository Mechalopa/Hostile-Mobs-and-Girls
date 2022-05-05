package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.GhastlySeekerModel;
import com.github.mechalopa.hmag.entity.GhastlySeekerEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhastlySeekerLayer extends AbstractClothingLayer<GhastlySeekerEntity, GhastlySeekerModel<GhastlySeekerEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/ghastly_seeker_overlay.png");
	private final GhastlySeekerModel<GhastlySeekerEntity> model = new GhastlySeekerModel<>(0.0F);

	public GhastlySeekerLayer(IEntityRenderer<GhastlySeekerEntity, GhastlySeekerModel<GhastlySeekerEntity>> entityRendererIn)
	{
		super(entityRendererIn);
	}

	@Override
	protected boolean canRender(GhastlySeekerEntity entity)
	{
		return entity.getAttackingTime() > 0;
	}

	@Override
	protected float getAlpha(GhastlySeekerEntity entity)
	{
		return (float)(Math.min(entity.getAttackingTime(), 20)) / 20.0F;
	}

	@Override
	protected EntityModel<GhastlySeekerEntity> getLayerModel()
	{
		return this.model;
	}

	@Override
	public ResourceLocation getLayerTexture(GhastlySeekerEntity entity)
	{
		return TEX;
	}
}