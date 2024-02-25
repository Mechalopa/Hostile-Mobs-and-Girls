package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.OgreModel;
import com.github.mechalopa.hmag.world.entity.OgreEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OgreRenderer extends HumanoidMobRenderer<OgreEntity, OgreModel<OgreEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/ogre.png");

	public OgreRenderer(EntityRendererProvider.Context context)
	{
		super(context, new OgreModel<>(context.bakeLayer(ModModelLayers.OGRE)), 0.7F);
	}

	@Override
	protected void scale(OgreEntity entity, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(1.2F, 1.2F, 1.2F);
		super.scale(entity, poseStack, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(OgreEntity entity)
	{
		return TEXTURE;
	}
}