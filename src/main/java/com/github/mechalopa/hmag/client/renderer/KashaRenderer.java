package com.github.mechalopa.hmag.client.renderer;

import java.util.Map;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.KashaModel;
import com.github.mechalopa.hmag.world.entity.KashaEntity;
import com.google.common.collect.Maps;

import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KashaRenderer extends MobRenderer<KashaEntity, KashaModel<KashaEntity>>
{
	private static final Map<KashaEntity.Variant, ResourceLocation> TEXTURES = Util.make(Maps.newEnumMap(KashaEntity.Variant.class), p -> {
		p.put(KashaEntity.Variant.NORMAL, new ResourceLocation(HMaG.MODID, "textures/entity/kasha/kasha.png"));
		p.put(KashaEntity.Variant.SOUL, new ResourceLocation(HMaG.MODID, "textures/entity/kasha/soul_kasha.png"));
	});

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
		return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(KashaEntity.Variant.NORMAL));
	}
}