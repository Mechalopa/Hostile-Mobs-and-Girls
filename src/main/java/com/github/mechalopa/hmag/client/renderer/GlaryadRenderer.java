package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.GlaryadModel;
import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.GlaryadEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlaryadRenderer extends AbstractGirlRenderer<GlaryadEntity, GlaryadModel<GlaryadEntity>>
{
	private static final ResourceLocation TEXTURE = ModClientUtils.getHMaGEntityTexture("glaryad");

	public GlaryadRenderer(EntityRendererProvider.Context context)
	{
		super(context, new GlaryadModel<>(context.bakeLayer(ModModelLayers.GLARYAD)), 0.5F, -1);
	}

	@Override
	protected int getBlockLightLevel(GlaryadEntity entity, BlockPos pos)
	{
		return Mth.clamp(super.getBlockLightLevel(entity, pos) + 6, 0, 15);
	}

	@Override
	public ResourceLocation getTextureLocation(GlaryadEntity entity)
	{
		return TEXTURE;
	}
}