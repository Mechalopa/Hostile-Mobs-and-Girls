package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.HornetModel;
import com.github.mechalopa.hmag.world.entity.HornetEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HornetRenderer extends AbstractGirlRenderer<HornetEntity, HornetModel<HornetEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/hornet.png");

	public HornetRenderer(EntityRendererProvider.Context context)
	{
		super(context, new HornetModel<>(context.bakeLayer(ModModelLayers.HORNET)), 0.375F);
	}

	@Override
	protected void scale(HornetEntity entity, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(0.875F, 0.875F, 0.875F);
		super.scale(entity, poseStack, partialTickTime);
	}

	@Override
	protected void setupRotations(HornetEntity entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
		poseStack.mulPose(Vector3f.XP.rotationDegrees(-6.0F));
	}

	@Override
	public ResourceLocation getTextureLocation(HornetEntity entity)
	{
		return TEXTURE;
	}
}