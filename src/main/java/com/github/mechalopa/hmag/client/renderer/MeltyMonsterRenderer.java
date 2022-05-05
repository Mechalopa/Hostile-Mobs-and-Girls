package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.MeltyMonsterModel;
import com.github.mechalopa.hmag.client.renderer.layers.MeltyMonsterClothingLayer;
import com.github.mechalopa.hmag.client.renderer.layers.MeltyMonsterEyesLayer;
import com.github.mechalopa.hmag.client.renderer.layers.MeltyMonsterMagmaLayer;
import com.github.mechalopa.hmag.entity.MeltyMonsterEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MeltyMonsterRenderer extends AbstractGirlRenderer<MeltyMonsterEntity, MeltyMonsterModel<MeltyMonsterEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/melty_monster.png");

	public MeltyMonsterRenderer(EntityRendererProvider.Context context)
	{
		super(context, new MeltyMonsterModel<>(context.bakeLayer(ModModelLayers.MELTY_MONSTER)), 0.5F, -1);
		this.addLayer(new MeltyMonsterMagmaLayer(this, context.getModelSet()));
		this.addLayer(new MeltyMonsterClothingLayer<>(this));
		this.addLayer(new MeltyMonsterEyesLayer<>(this));
	}

	@Override
	protected int getBlockLightLevel(MeltyMonsterEntity entity, BlockPos pos)
	{
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(MeltyMonsterEntity entity)
	{
		return TEX;
	}
}