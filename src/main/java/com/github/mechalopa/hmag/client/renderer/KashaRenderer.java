package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.KashaModel;
import com.github.mechalopa.hmag.entity.KashaEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KashaRenderer extends MobRenderer<KashaEntity, KashaModel<KashaEntity>>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/kasha_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/kasha_1.png");

	public KashaRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new KashaModel<>(), 0.4F);
	}

	@Override
	protected int getBlockLightLevel(KashaEntity entityIn, BlockPos pos)
	{
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(KashaEntity entityIn)
	{
		switch (entityIn.getVariant())
		{
		case SOUL:
			return TEX1;
		default:
			return TEX0;
		}
	}
}