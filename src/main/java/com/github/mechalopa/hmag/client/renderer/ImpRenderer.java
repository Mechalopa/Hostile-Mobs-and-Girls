package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.ImpModel;
import com.github.mechalopa.hmag.world.entity.ImpEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImpRenderer extends AbstractGirlRenderer<ImpEntity, ImpModel<ImpEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/imp.png");

	public ImpRenderer(EntityRendererProvider.Context context)
	{
		super(context, new ImpModel<>(context.bakeLayer(ModModelLayers.IMP)), 0.5F, -1);
	}

	@Override
	protected void scale(ImpEntity entity, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(0.875F, 0.875F, 0.875F);
		super.scale(entity, poseStack, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(ImpEntity entity)
	{
		return TEXTURE;
	}
}