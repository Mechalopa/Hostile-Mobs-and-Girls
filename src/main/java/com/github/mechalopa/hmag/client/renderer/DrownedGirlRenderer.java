package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.DrownedGirlModel;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DrownedGirlRenderer extends AbstractGirlRenderer<DrownedEntity, DrownedGirlModel<DrownedEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/drowned_girl.png");

	public DrownedGirlRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new DrownedGirlModel<>(), 0.5F);
		this.addLayer(new BipedArmorLayer<>(this, new DrownedGirlModel<>(0.5F, true), new DrownedGirlModel<>(1.0F, true)));
	}

	@Override
	public ResourceLocation getTextureLocation(DrownedEntity entityIn)
	{
		return TEX;
	}

	@Override
	protected void setupRotations(DrownedEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		float f = entityLiving.getSwimAmount(partialTicks);

		if (f > 0.0F)
		{
			matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(MathHelper.lerp(f, entityLiving.xRot, -10.0F - entityLiving.xRot)));
		}
	}

	@Override
	protected boolean isShaking(DrownedEntity entityIn)
	{
		return entityIn.isUnderWaterConverting();
	}
}