package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.LichModel;
import com.github.mechalopa.hmag.entity.LichEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LichRenderer extends HumanoidMobRenderer<LichEntity, LichModel<LichEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/lich.png");

	public LichRenderer(EntityRendererProvider.Context context)
	{
		super(context, new LichModel<>(context.bakeLayer(ModModelLayers.LICH)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(LichEntity entityIn)
	{
		return TEX;
	}
}