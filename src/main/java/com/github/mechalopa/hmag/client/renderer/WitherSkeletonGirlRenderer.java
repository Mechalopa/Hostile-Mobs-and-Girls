package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherSkeletonGirlRenderer extends SkeletonGirlRenderer
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/wither_skeleton_girl.png");

	public WitherSkeletonGirlRenderer(EntityRendererProvider.Context context)
	{
		super(context, ModModelLayers.WITHER_SKELETON_GIRL, ModModelLayers.WITHER_SKELETON_GIRL_INNER_ARMOR, ModModelLayers.WITHER_SKELETON_GIRL_OUTER_ARMOR);
	}

	@Override
	protected void scale(AbstractSkeleton entityIn, PoseStack poseStackIn, float partialTickTime)
	{
		poseStackIn.scale(1.2F, 1.2F, 1.2F);
		super.scale(entityIn, poseStackIn, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(AbstractSkeleton entityIn)
	{
		return TEX;
	}
}