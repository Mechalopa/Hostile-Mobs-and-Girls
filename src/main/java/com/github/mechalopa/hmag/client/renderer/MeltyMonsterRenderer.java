package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.MeltyMonsterModel;
import com.github.mechalopa.hmag.client.renderer.layers.MeltyMonsterClothingLayer;
import com.github.mechalopa.hmag.client.renderer.layers.MeltyMonsterEyesLayer;
import com.github.mechalopa.hmag.client.renderer.layers.MeltyMonsterLayer;
import com.github.mechalopa.hmag.entity.MeltyMonsterEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MeltyMonsterRenderer extends AbstractGirlRenderer<MeltyMonsterEntity, MeltyMonsterModel<MeltyMonsterEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/melty_monster.png");

	public MeltyMonsterRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new MeltyMonsterModel<>(), 0.5F, -1);
		this.addLayer(new MeltyMonsterLayer(this));
		this.addLayer(new MeltyMonsterClothingLayer<>(this));
		this.addLayer(new MeltyMonsterEyesLayer<>(this));
	}

	@Override
	protected int getBlockLightLevel(MeltyMonsterEntity entityIn, BlockPos pos)
	{
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(MeltyMonsterEntity entityIn)
	{
		return TEX;
	}
}