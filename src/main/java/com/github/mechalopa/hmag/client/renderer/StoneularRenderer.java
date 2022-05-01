package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.StoneularModel;
import com.github.mechalopa.hmag.entity.StoneularEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StoneularRenderer extends MobRenderer<StoneularEntity, StoneularModel<StoneularEntity>>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/stoneular.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/stoneular_awaiting.png");

	public StoneularRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new StoneularModel<>(), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(StoneularEntity entityIn)
	{
		return entityIn.isWalking() ? TEX0 : TEX1;
	}
}