package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.CatoblepasModel;
import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.CatoblepasEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CatoblepasRenderer extends MobRenderer<CatoblepasEntity, CatoblepasModel<CatoblepasEntity>>
{
	private static final ResourceLocation TEXTURE = ModClientUtils.getHMaGEntityTexture("catoblepas");

	public CatoblepasRenderer(EntityRendererProvider.Context context)
	{
		super(context, new CatoblepasModel<>(context.bakeLayer(ModModelLayers.CATOBLEPAS)), 0.7F);
	}

	@Override
	public ResourceLocation getTextureLocation(CatoblepasEntity entity)
	{
		return TEXTURE;
	}
}