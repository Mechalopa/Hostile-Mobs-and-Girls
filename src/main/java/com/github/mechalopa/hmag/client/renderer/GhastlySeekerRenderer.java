package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.GhastlySeekerModel;
import com.github.mechalopa.hmag.client.renderer.layers.GhastlySeekerLayer;
import com.github.mechalopa.hmag.world.entity.GhastlySeekerEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhastlySeekerRenderer extends MobRenderer<GhastlySeekerEntity, GhastlySeekerModel<GhastlySeekerEntity>>
{
	private static final ResourceLocation TEXTURE_0 = new ResourceLocation(HMaG.MODID, "textures/entity/ghastly_seeker.png");
	private static final ResourceLocation TEXTURE_1 = new ResourceLocation(HMaG.MODID, "textures/entity/ghastly_seeker_shooting.png");

	public GhastlySeekerRenderer(EntityRendererProvider.Context context)
	{
		super(context, new GhastlySeekerModel<>(context.bakeLayer(ModModelLayers.GHASTLY_SEEKER)), 0.8F);
		this.addLayer(new GhastlySeekerLayer(this, context.getModelSet()));
	}

	@Override
	protected void scale(GhastlySeekerEntity entity, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(1.5F, 1.5F, 1.5F);
	}

	@Override
	protected void setupRotations(GhastlySeekerEntity entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
		poseStack.mulPose(Vector3f.XP.rotationDegrees(-15.0F));
	}

	@Override
	public ResourceLocation getTextureLocation(GhastlySeekerEntity entity)
	{
		return entity.isAttacking() ? TEXTURE_1 : TEXTURE_0;
	}
}