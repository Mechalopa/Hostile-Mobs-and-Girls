package net.mechalopa.hmag.client.renderer;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.model.SkeletonGirlModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkeletonGirlRenderer extends AbstractGirlRenderer<AbstractSkeletonEntity, SkeletonGirlModel<AbstractSkeletonEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/skeleton_girl.png");

	public SkeletonGirlRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new SkeletonGirlModel<>(), 0.5F);
		this.addLayer(new BipedArmorLayer<>(this, new SkeletonGirlModel<>(0.5F, true), new SkeletonGirlModel<>(1.0F, true)));
	}

	@Override
	public ResourceLocation getTextureLocation(AbstractSkeletonEntity entityIn)
	{
		return TEX;
	}
}