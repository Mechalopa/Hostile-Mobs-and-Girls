package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.DullahanModel;
import com.github.mechalopa.hmag.entity.DullahanEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DullahanRenderer extends AbstractGirlRenderer<DullahanEntity, DullahanModel<DullahanEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/dullahan.png");

	public DullahanRenderer(EntityRendererProvider.Context context)
	{
		super(context, new DullahanModel<>(context.bakeLayer(ModModelLayers.DULLAHAN)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(DullahanEntity entity)
	{
		return TEX;
	}
}