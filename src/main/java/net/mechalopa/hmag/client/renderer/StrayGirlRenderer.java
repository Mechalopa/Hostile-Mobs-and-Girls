package net.mechalopa.hmag.client.renderer;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.renderer.layers.StrayGirlClothingLayer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrayGirlRenderer extends SkeletonGirlRenderer
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/stray_girl.png");

	public StrayGirlRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.addLayer(new StrayGirlClothingLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(AbstractSkeletonEntity entityIn)
	{
		return TEX;
	}
}