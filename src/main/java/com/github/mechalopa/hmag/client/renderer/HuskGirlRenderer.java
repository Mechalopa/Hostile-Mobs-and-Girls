package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HuskGirlRenderer extends ZombieGirlRenderer
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/husk_girl.png");

	public HuskGirlRenderer(EntityRendererProvider.Context context)
	{
		super(context, ModModelLayers.HUSK_GIRL, ModModelLayers.HUSK_GIRL_INNER_ARMOR, ModModelLayers.HUSK_GIRL_OUTER_ARMOR);
	}

	@Override
	protected void scale(Zombie entityIn, PoseStack poseStackIn, float partialTickTime)
	{
		poseStackIn.scale(1.0625F, 1.0625F, 1.0625F);
		super.scale(entityIn, poseStackIn, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(Zombie entityIn)
	{
		return TEX;
	}
}