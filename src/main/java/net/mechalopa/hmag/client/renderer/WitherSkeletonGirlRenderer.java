package net.mechalopa.hmag.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.mechalopa.hmag.HMaG;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherSkeletonGirlRenderer extends SkeletonGirlRenderer
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/wither_skeleton_girl.png");

	public WitherSkeletonGirlRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	@Override
	protected void scale(AbstractSkeletonEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(1.2F, 1.2F, 1.2F);
		super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(AbstractSkeletonEntity entityIn)
	{
		return TEX;
	}
}