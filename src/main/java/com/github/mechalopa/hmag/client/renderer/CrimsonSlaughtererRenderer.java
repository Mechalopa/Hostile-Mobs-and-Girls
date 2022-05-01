package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.CrimsonSlaughtererModel;
import com.github.mechalopa.hmag.entity.CrimsonSlaughtererEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrimsonSlaughtererRenderer extends MobRenderer<CrimsonSlaughtererEntity, CrimsonSlaughtererModel<CrimsonSlaughtererEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/crimson_slaughterer.png");

	public CrimsonSlaughtererRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new CrimsonSlaughtererModel<>(), 0.8F);
	}

	@Override
	protected int getBlockLightLevel(CrimsonSlaughtererEntity entityIn, BlockPos pos)
	{
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(CrimsonSlaughtererEntity entityIn)
	{
		return TEX;
	}
}