package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.KashaModel;
import com.github.mechalopa.hmag.world.entity.KashaEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KashaRenderer extends MobRenderer<KashaEntity, KashaModel<KashaEntity>>
{
	private static final ResourceLocation NORMAL_TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/kasha_0.png");
	private static final ResourceLocation SOUL_TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/kasha_1.png");

	public KashaRenderer(EntityRendererProvider.Context context)
	{
		super(context, new KashaModel<>(context.bakeLayer(ModModelLayers.KASHA)), 0.4F);
	}

	@Override
	protected int getBlockLightLevel(KashaEntity entity, BlockPos pos)
	{
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(KashaEntity entity)
	{
		switch (entity.getVariant())
		{
		case SOUL:
			return SOUL_TEXTURE;
		default:
			return NORMAL_TEXTURE;
		}
	}
}