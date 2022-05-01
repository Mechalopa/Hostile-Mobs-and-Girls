package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.LichModel;
import com.github.mechalopa.hmag.entity.LichEntity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LichRenderer extends BipedRenderer<LichEntity, LichModel<LichEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/lich.png");

	public LichRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new LichModel<>(), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(LichEntity entityIn)
	{
		return TEX;
	}
}