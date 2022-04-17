package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.DullahanModel;
import com.github.mechalopa.hmag.entity.DullahanEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DullahanRenderer extends AbstractGirlRenderer<DullahanEntity, DullahanModel<DullahanEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/dullahan.png");

	public DullahanRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new DullahanModel<>(), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(DullahanEntity entityIn)
	{
		return TEX;
	}
}