package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.entity.AbstractFlyingMonsterEntity;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhostModel<T extends AbstractFlyingMonsterEntity> extends HumanoidModel<T>
{
	public GhostModel(ModelPart modelPart)
	{
		super(modelPart);
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition md = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition pd = md.getRoot();
		ModClientUtils.addC(pd, "body", 16, 16, -4.0F, 0.0F, -2.0F, 8.0F, 20.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		ModClientUtils.addC(pd, "right_leg", 0, 16, -0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, -2.0F, 12.0F, 0.0F);
		ModClientUtils.addC(pd, "left_leg", 0, 16, -0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 2.0F, 12.0F, 0.0F, true);
		return LayerDefinition.create(md, 64, 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, entityIn.isCharging(), this.attackTime, ageInTicks);

		float f = Mth.sin(this.attackTime * (float)Math.PI);
		float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
		this.rightLeg.xRot = (float)Math.PI / 24.0F;
		this.leftLeg.xRot = (float)Math.PI / 24.0F;
		this.rightLeg.zRot = (float)Math.PI / 27.0F;
		this.leftLeg.zRot = -(float)Math.PI / 27.0F;
		this.rightLeg.xRot -= f * 1.2F - f1 * 0.4F;
		this.leftLeg.xRot -= f * 1.2F - f1 * 0.4F;
		this.rightLeg.zRot += Mth.cos(ageInTicks * 0.067F) * 0.05F;
		this.leftLeg.zRot -= Mth.cos(ageInTicks * 0.067F) * 0.05F;
		this.rightLeg.xRot += Mth.sin(ageInTicks * 0.033F) * 0.025F;
		this.leftLeg.xRot += Mth.sin(ageInTicks * 0.033F) * 0.025F;
	}
}