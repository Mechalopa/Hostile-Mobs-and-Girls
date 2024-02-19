package com.github.mechalopa.hmag.client.renderer;

import java.util.Map;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.HarpyModel;
import com.github.mechalopa.hmag.world.entity.HarpyEntity;
import com.google.common.collect.Maps;

import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HarpyRenderer extends AbstractGirlRenderer<HarpyEntity, HarpyModel<HarpyEntity>>
{
	private static final Map<HarpyEntity.Variant, ResourceLocation> TEXTURES = Util.make(Maps.newEnumMap(HarpyEntity.Variant.class), p -> {
		p.put(HarpyEntity.Variant.GOLD, new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_0.png"));
		p.put(HarpyEntity.Variant.ORANGE, new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_1.png"));
		p.put(HarpyEntity.Variant.BEIGE, new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_2.png"));
		p.put(HarpyEntity.Variant.BROWN, new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_3.png"));
		p.put(HarpyEntity.Variant.GRAY, new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_4.png"));
		p.put(HarpyEntity.Variant.WHITE, new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_5.png"));
		p.put(HarpyEntity.Variant.PINK, new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_6.png"));
	});

	public HarpyRenderer(EntityRendererProvider.Context context)
	{
		super(context, new HarpyModel<>(context.bakeLayer(ModModelLayers.HARPY)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(HarpyEntity entity)
	{
		return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(HarpyEntity.Variant.GOLD));
	}
}