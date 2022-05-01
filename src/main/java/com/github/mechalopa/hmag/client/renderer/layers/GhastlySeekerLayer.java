package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.GhastlySeekerModel;
import com.github.mechalopa.hmag.entity.GhastlySeekerEntity;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhastlySeekerLayer extends AbstractClothingLayer<GhastlySeekerEntity, GhastlySeekerModel<GhastlySeekerEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/ghastly_seeker_overlay.png");
	private final GhastlySeekerModel<GhastlySeekerEntity> modelOuterLayer = new GhastlySeekerModel<>(0.0F);

	public GhastlySeekerLayer(IEntityRenderer<GhastlySeekerEntity, GhastlySeekerModel<GhastlySeekerEntity>> entityRendererIn)
	{
		super(entityRendererIn);
	}

	@Override
	protected boolean canRender(GhastlySeekerEntity livingEntityIn)
	{
		return livingEntityIn.getAttackingTime() > 0;
	}

	@Override
	protected float getAlpha(GhastlySeekerEntity livingEntityIn)
	{
		return (float)(Math.min(livingEntityIn.getAttackingTime(), 20)) / 20.0F;
	}

	@Override
	protected EntityModel<GhastlySeekerEntity> model()
	{
		return this.modelOuterLayer;
	}

	@Override
	public ResourceLocation getLayerTexture(GhastlySeekerEntity livingEntityIn)
	{
		return TEX;
	}
}