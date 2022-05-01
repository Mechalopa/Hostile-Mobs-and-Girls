package com.github.mechalopa.hmag.client.renderer;

import java.util.Random;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.DyssomniaModel;
import com.github.mechalopa.hmag.client.renderer.layers.DyssomniaEyesLayer;
import com.github.mechalopa.hmag.entity.DyssomniaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DyssomniaRenderer extends MobRenderer<DyssomniaEntity, DyssomniaModel<DyssomniaEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/dyssomnia.png");
	private final Random rand = new Random();

	public DyssomniaRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new DyssomniaModel<>(), 1.2F);
		this.addLayer(new DyssomniaEyesLayer<>(this));
	}

	@Override
	protected void scale(DyssomniaEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(1.75F, 1.75F, 1.75F);
		matrixStackIn.translate(0.0D, 1.25D, 0.0D);
	}

	@Override
	protected void setupRotations(DyssomniaEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(entityLiving.getXRotAnimationScale(partialTicks) * 180.0F));
	}

	@Override
	public Vector3d getRenderOffset(DyssomniaEntity entityIn, float partialTicks)
	{
		if (entityIn.getAttackingTime() >= 0 && entityIn.getAttackPhase() == DyssomniaEntity.AttackPhase.SUMMON)
		{
			return new Vector3d(this.rand.nextGaussian() * 0.03D, this.rand.nextGaussian() * 0.03D, this.rand.nextGaussian() * 0.03D);
		}
		else
		{
			return super.getRenderOffset(entityIn, partialTicks);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(DyssomniaEntity entityIn)
	{
		return TEX;
	}
}