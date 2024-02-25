package com.github.mechalopa.hmag.client.renderer;

import java.util.Random;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.DyssomniaModel;
import com.github.mechalopa.hmag.client.renderer.layers.DyssomniaEyesLayer;
import com.github.mechalopa.hmag.world.entity.DyssomniaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DyssomniaRenderer extends MobRenderer<DyssomniaEntity, DyssomniaModel<DyssomniaEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/dyssomnia.png");
	private final Random rand = new Random();

	public DyssomniaRenderer(EntityRendererProvider.Context context)
	{
		super(context, new DyssomniaModel<>(context.bakeLayer(ModModelLayers.DYSSOMNIA)), 1.2F);
		this.addLayer(new DyssomniaEyesLayer<>(this));
	}

	@Override
	protected void scale(DyssomniaEntity entity, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(1.75F, 1.75F, 1.75F);
		poseStack.translate(0.0D, 1.25D, 0.0D);
	}

	@Override
	protected void setupRotations(DyssomniaEntity entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
		poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRotAnimationScale(partialTicks) * 180.0F));
	}

	@Override
	public Vec3 getRenderOffset(DyssomniaEntity entity, float partialTicks)
	{
		if (entity.getAttackingTime() >= 0 && entity.getAttackPhase() == DyssomniaEntity.AttackPhase.SUMMON && entity.isAlive())
		{
			return new Vec3(this.rand.nextGaussian() * 0.03D, this.rand.nextGaussian() * 0.03D, this.rand.nextGaussian() * 0.03D);
		}
		else
		{
			return super.getRenderOffset(entity, partialTicks);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(DyssomniaEntity entity)
	{
		return TEXTURE;
	}
}