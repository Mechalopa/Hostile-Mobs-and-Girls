package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.GhastlySeekerModel;
import com.github.mechalopa.hmag.world.entity.GhastlySeekerEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhastlySeekerLayer extends AbstractClothingLayer<GhastlySeekerEntity, GhastlySeekerModel<GhastlySeekerEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/ghastly_seeker_overlay.png");
	private final GhastlySeekerModel<GhastlySeekerEntity> model;

	public GhastlySeekerLayer(RenderLayerParent<GhastlySeekerEntity, GhastlySeekerModel<GhastlySeekerEntity>> renderLayerParent, EntityModelSet modelSet)
	{
		super(renderLayerParent);
		this.model = new GhastlySeekerModel<>(modelSet.bakeLayer(ModModelLayers.GHASTLY_SEEKER_OUTER_LAYER));
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
		return TEXTURE;
	}
}