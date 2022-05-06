package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.CrimsonSlaughtererModel;
import com.github.mechalopa.hmag.entity.CrimsonSlaughtererEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrimsonSlaughtererRenderer extends MobRenderer<CrimsonSlaughtererEntity, CrimsonSlaughtererModel<CrimsonSlaughtererEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/crimson_slaughterer.png");

	public CrimsonSlaughtererRenderer(EntityRendererProvider.Context context)
	{
		super(context, new CrimsonSlaughtererModel<>(context.bakeLayer(ModModelLayers.CRIMSON_SLAUGHTERER)), 0.8F);
	}

	@Override
	protected int getBlockLightLevel(CrimsonSlaughtererEntity entity, BlockPos pos)
	{
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(CrimsonSlaughtererEntity entity)
	{
		return TEX;
	}
}