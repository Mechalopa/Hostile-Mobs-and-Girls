package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.projectile.EvilArrowEntity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EvilArrowRenderer extends ArrowRenderer<EvilArrowEntity>
{
	private static final ResourceLocation TEXTURE = ModClientUtils.getHMaGEntityTexture("projectile/evil_arrow");

	public EvilArrowRenderer(EntityRendererProvider.Context context)
	{
		super(context);
	}

	@Override
	public ResourceLocation getTextureLocation(EvilArrowEntity entity)
	{
		return TEXTURE;
	}
}