package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractGirlModel<T extends LivingEntity> extends HumanoidModel<T>
{
	public AbstractGirlModel(ModelPart modelPart)
	{
		super(modelPart);
	}

	public static MeshDefinition createMesh(CubeDeformation cd, float yOffset)
	{
		MeshDefinition md = HumanoidModel.createMesh(cd, yOffset);
		PartDefinition pd = md.getRoot();
		ModClientUtils.addC(pd, cd, "head", 0, 0, -3.0F, -6.0F - 1.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, 0.0F + yOffset, 0.0F, 1.0F);
		ModClientUtils.addC(pd, cd, "body", 16, 16, -3.0F, 0.0F, -1.5F, 6.0F, 12.0F, 3.0F, 0.0F, 0.0F + yOffset, 0.0F);
		ModClientUtils.addC(pd, cd, "right_arm", 40, 16, -1.0F, -2.0F, -1.5F, 3.0F, 12.0F, 3.0F, -5.0F, 2.0F + yOffset, 0.0F);
		ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.5F, 3.0F, 12.0F, 3.0F, 5.0F, 2.0F + yOffset, 0.0F, true);
		ModClientUtils.addC(pd, cd, "right_leg", 0, 16, -1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, -1.9F, 12.0F + yOffset, 0.0F);
		ModClientUtils.addC(pd, cd, "left_leg", 0, 16, -1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 1.9F, 12.0F + yOffset, 0.0F, true);
		return md;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (!this.riding)
		{
			this.rightLeg.zRot -= this.getLegRotZ();
			this.leftLeg.zRot += this.getLegRotZ();
		}
	}

	@Override
	public void translateToHand(HumanoidArm side, PoseStack poseStack)
	{
		if (this.isSkeletonHandTranslate())
		{
			float f = side == HumanoidArm.RIGHT ? 1.0F : -1.0F;
			ModelPart modelpart = this.getArm(side);
			modelpart.x += f;
			modelpart.translateAndRotate(poseStack);
			modelpart.x -= f;
		}
		else
		{
			super.translateToHand(side, poseStack);
		}
	}

	protected float getLegRotZ()
	{
		return (float)Math.PI * 0.008F;
	}

	protected boolean isSkeletonHandTranslate()
	{
		return false;
	}
}