package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.BansheeModel;
import com.github.mechalopa.hmag.client.renderer.layers.BansheeLayer;
import com.github.mechalopa.hmag.world.entity.BansheeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BansheeRenderer extends AbstractGirlRenderer<BansheeEntity, BansheeModel<BansheeEntity>>
{
	private static final ResourceLocation TEXTURE_0 = new ResourceLocation(HMaG.MODID, "textures/entity/banshee_0.png");
	private static final ResourceLocation TEXTURE_1 = new ResourceLocation(HMaG.MODID, "textures/entity/banshee_1.png");

	public BansheeRenderer(EntityRendererProvider.Context context)
	{
		super(context, new BansheeModel<>(context.bakeLayer(ModModelLayers.BANSHEE)), 0.375F);
		this.addLayer(new BansheeLayer(this, context.getModelSet()));
	}

	@Override
	protected void scale(BansheeEntity entity, PoseStack poseStack, float partialTickTime)
	{
		super.scale(entity, poseStack, partialTickTime);
		float f = (float)entity.tickCount + partialTickTime;
		poseStack.translate(0.0F, -0.06F + Mth.sin(f * 0.06F) * 0.04F, 0.0F);
	}

	@Override
	protected void setupRotations(BansheeEntity entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
		poseStack.mulPose(Vector3f.XP.rotationDegrees(-6.0F));
	}

	@Override
	protected int getBlockLightLevel(BansheeEntity entity, BlockPos pos)
	{
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(BansheeEntity entity)
	{
		switch (entity.getVariant())
		{
		case 1:
			return TEXTURE_1;
		default:
			return TEXTURE_0;
		}
	}
}