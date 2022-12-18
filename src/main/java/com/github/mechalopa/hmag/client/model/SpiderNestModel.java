package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.SpiderNestEntity;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpiderNestModel<T extends SpiderNestEntity> extends HierarchicalModel<T>
{
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart body;
//	private final ModelPart bodyPart1;
//	private final ModelPart bodyPart2;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart leg5;
	private final ModelPart leg6;
	private final ModelPart leg7;
	private final ModelPart leg8;
//	private final ModelPart leg1Part1;
//	private final ModelPart leg2Part1;
//	private final ModelPart leg3Part1;
//	private final ModelPart leg4Part1;
//	private final ModelPart leg5Part1;
//	private final ModelPart leg6Part1;
//	private final ModelPart leg7Part1;
//	private final ModelPart leg8Part1;
//	private final ModelPart leg1Part2;
//	private final ModelPart leg2Part2;
//	private final ModelPart leg3Part2;
//	private final ModelPart leg4Part2;
//	private final ModelPart leg5Part2;
//	private final ModelPart leg6Part2;
//	private final ModelPart leg7Part2;
//	private final ModelPart leg8Part2;
	private final ModelPart bodyPart3;
	private final ModelPart bodyPart4;
	private final ModelPart bodyPart5;
	private final ModelPart bodyPart6;
	private final ModelPart bodyPart7;
	private final ModelPart bodyPart8;
	private final ModelPart bodyPart9Right;
	private final ModelPart bodyPart9Left;
	private final ModelPart bodyPart10Right;
	private final ModelPart bodyPart10Left;

	public SpiderNestModel(ModelPart modelPart)
	{
		this.root = modelPart;
		this.head = modelPart.getChild("head");
		this.body = modelPart.getChild("body");
//		this.bodyPart1 = this.body.getChild("body_part_1");
//		this.bodyPart2 = this.body.getChild("body_part_2");
		this.leg1 = modelPart.getChild("leg_1");
		this.leg2 = modelPart.getChild("leg_2");
		this.leg3 = modelPart.getChild("leg_3");
		this.leg4 = modelPart.getChild("leg_4");
		this.leg5 = modelPart.getChild("leg_5");
		this.leg6 = modelPart.getChild("leg_6");
		this.leg7 = modelPart.getChild("leg_7");
		this.leg8 = modelPart.getChild("leg_8");
//		this.leg1Part1 = this.leg1.getChild("leg_1_part_1");
//		this.leg2Part1 = this.leg2.getChild("leg_2_part_1");
//		this.leg3Part1 = this.leg3.getChild("leg_3_part_1");
//		this.leg4Part1 = this.leg4.getChild("leg_4_part_1");
//		this.leg5Part1 = this.leg5.getChild("leg_5_part_1");
//		this.leg6Part1 = this.leg6.getChild("leg_6_part_1");
//		this.leg7Part1 = this.leg7.getChild("leg_7_part_1");
//		this.leg8Part1 = this.leg8.getChild("leg_8_part_1");
//		this.leg1Part2 = this.leg1.getChild("leg_1_part_2");
//		this.leg2Part2 = this.leg2.getChild("leg_2_part_2");
//		this.leg3Part2 = this.leg3.getChild("leg_3_part_2");
//		this.leg4Part2 = this.leg4.getChild("leg_4_part_2");
//		this.leg5Part2 = this.leg5.getChild("leg_5_part_2");
//		this.leg6Part2 = this.leg6.getChild("leg_6_part_2");
//		this.leg7Part2 = this.leg7.getChild("leg_7_part_2");
//		this.leg8Part2 = this.leg8.getChild("leg_8_part_2");
		this.bodyPart3 = this.body.getChild("body_part_3");
		this.bodyPart4 = this.body.getChild("body_part_4");
		this.bodyPart5 = this.body.getChild("body_part_5");
		this.bodyPart6 = this.body.getChild("body_part_6");
		this.bodyPart7 = this.body.getChild("body_part_7");
		this.bodyPart8 = this.body.getChild("body_part_8");
		this.bodyPart9Right = this.body.getChild("body_part_9_right");
		this.bodyPart9Left = this.body.getChild("body_part_9_left");
		this.bodyPart10Right = this.body.getChild("body_part_10_right");
		this.bodyPart10Left = this.body.getChild("body_part_10_left");
	}

	public static LayerDefinition createBodyLayer()
	{
		float f = 8.0F;
		float f1 = 7.0F;
		float f2 = 8.5F;
		MeshDefinition md = new MeshDefinition();
		PartDefinition pd = md.getRoot();
		ModClientUtils.addC(pd, "head", 0, 0, -4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 6.0F, 0.0F, 15.0F, -8.0F);
		PartDefinition bodypd = ModClientUtils.addC(pd, "body", 0, 32, -7.0F, -11.0F, -8.0F, 14.0F, 16.0F, 16.0F, 0.0F, 14.0F, 0.0F);
		ModClientUtils.addC(bodypd, "body_part_1", 0, 64, -8.0F, -8.0F, -7.0F, 16.0F, 16.0F, 14.0F, 0.0F, -3.0F, 0.0F);
		ModClientUtils.addC(bodypd, "body_part_2", 0, 96, -7.0F, -9.0F, -7.0F, 14.0F, 18.0F, 14.0F, 0.0F, -3.0F, 0.0F);
		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -0.5F, -1.0F, 2.0F, f, 2.0F);
		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(0, 14).mirror().addBox(-1.0F, -0.5F, -1.0F, 2.0F, f, 2.0F);
		PartDefinition l1pd = pd.addOrReplaceChild("leg_1", cubelistbuilder, PartPose.offset(-f2, f1, -6.0F));
		PartDefinition l2pd = pd.addOrReplaceChild("leg_2", cubelistbuilder1, PartPose.offset(f2, f1, -6.0F));
		PartDefinition l3pd = pd.addOrReplaceChild("leg_3", cubelistbuilder, PartPose.offset(-f2, f1, -2.0F));
		PartDefinition l4pd = pd.addOrReplaceChild("leg_4", cubelistbuilder1, PartPose.offset(f2, f1, -2.0F));
		PartDefinition l5pd = pd.addOrReplaceChild("leg_5", cubelistbuilder, PartPose.offset(-f2, f1, 2.0F));
		PartDefinition l6pd = pd.addOrReplaceChild("leg_6", cubelistbuilder1, PartPose.offset(f2, f1, 2.0F));
		PartDefinition l7pd = pd.addOrReplaceChild("leg_7", cubelistbuilder, PartPose.offset(-f2, f1, 6.0F));
		PartDefinition l8pd = pd.addOrReplaceChild("leg_8", cubelistbuilder1, PartPose.offset(f2, f1, 6.0F));
		CubeListBuilder cubelistbuilder2 = CubeListBuilder.create().texOffs(0, 24).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(-0.25F));
		CubeListBuilder cubelistbuilder3 = CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(-0.25F));
		PartPose pp = PartPose.offset(0.0F, -1.0F, 0.0F);
		l1pd.addOrReplaceChild("leg_1_part_1", cubelistbuilder2, pp);
		l2pd.addOrReplaceChild("leg_2_part_1", cubelistbuilder3, pp);
		l3pd.addOrReplaceChild("leg_3_part_1", cubelistbuilder2, pp);
		l4pd.addOrReplaceChild("leg_4_part_1", cubelistbuilder3, pp);
		l5pd.addOrReplaceChild("leg_5_part_1", cubelistbuilder2, pp);
		l6pd.addOrReplaceChild("leg_6_part_1", cubelistbuilder3, pp);
		l7pd.addOrReplaceChild("leg_7_part_1", cubelistbuilder2, pp);
		l8pd.addOrReplaceChild("leg_8_part_1", cubelistbuilder3, pp);
		CubeListBuilder cubelistbuilder4 = CubeListBuilder.create().texOffs(12, 14).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F);
		CubeListBuilder cubelistbuilder5 = CubeListBuilder.create().texOffs(12, 14).mirror().addBox(-1.5F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F);
		PartPose pp1 = PartPose.offset(0.0F, f, 0.0F);
		l1pd.addOrReplaceChild("leg_1_part_2", cubelistbuilder4, pp1);
		l2pd.addOrReplaceChild("leg_2_part_2", cubelistbuilder5, pp1);
		l3pd.addOrReplaceChild("leg_3_part_2", cubelistbuilder4, pp1);
		l4pd.addOrReplaceChild("leg_4_part_2", cubelistbuilder5, pp1);
		l5pd.addOrReplaceChild("leg_5_part_2", cubelistbuilder4, pp1);
		l6pd.addOrReplaceChild("leg_6_part_2", cubelistbuilder5, pp1);
		l7pd.addOrReplaceChild("leg_7_part_2", cubelistbuilder4, pp1);
		l8pd.addOrReplaceChild("leg_8_part_2", cubelistbuilder5, pp1);
		ModClientUtils.addC(bodypd, "body_part_3", 24, 24, -2.0F, -4.5F, -1.0F, 4.0F, 5.0F, 3.0F, -1.0F, -11.0F, -3.5F, true);
		ModClientUtils.addC(bodypd, "body_part_4", 24, 24, -2.0F, -4.5F, -1.0F, 4.0F, 5.0F, 3.0F, 4.0F, -11.5F, 3.5F);
		ModClientUtils.addC(bodypd, "body_part_5", 24, 24, -2.0F, -4.5F, -1.0F, 4.0F, 5.0F, 3.0F, -4.5F, -11.0F, 7.0F, true);
		ModClientUtils.addC(bodypd, "body_part_6", 0, 36, -2.0F, -7.5F, -1.0F, 4.0F, 8.0F, 3.0F, 1.0F, -6.0F, 8.0F, true);
		ModClientUtils.addC(bodypd, "body_part_7", 0, 36, -2.0F, -7.5F, -1.0F, 4.0F, 8.0F, 3.0F, -4.0F, 0.0F, 8.0F, true);
		ModClientUtils.addC(bodypd, "body_part_8", 0, 36, -2.0F, -7.5F, -1.0F, 4.0F, 8.0F, 3.0F, 4.5F, 2.0F, 8.0F);
		ModClientUtils.addC(bodypd, "body_part_9_right", 24, 0, 0.0F, -7.0F, -8.0F, 0.0F, 7.0F, 16.0F, -8.0F, -8.5F, 3.0F);
		ModClientUtils.addC(bodypd, "body_part_9_left", 24, 0, 0.0F, -7.0F, -8.0F, 0.0F, 7.0F, 16.0F, 8.0F, -8.5F, 3.0F, true);
		ModClientUtils.addC(bodypd, "body_part_10_right", 28, 0, 0.0F, -4.0F, -2.0F, 0.0F, 4.0F, 5.0F, -8.0F, -11.0F, -4.0F);
		ModClientUtils.addC(bodypd, "body_part_10_left", 28, 0, 0.0F, -4.0F, -2.0F, 0.0F, 4.0F, 5.0F, 8.0F, -11.0F, -4.0F, true);
		return LayerDefinition.create(md, 64, 128);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (entity.isCharging())
		{
			this.head.zRot = (ageInTicks * 1.2F) % (180.0F / (float)Math.PI) * (entity.getMainArm() == HumanoidArm.RIGHT ? 1.0F : -1.0F);
			this.head.yRot = 0.0F;
			this.head.xRot = 0.0F;
		}
		else
		{
			this.head.zRot = 0.0F;
			this.head.yRot = netHeadYaw / (180.0F / (float)Math.PI);
			this.head.xRot = headPitch / (180.0F / (float)Math.PI);
		}

//		this.bodyPart1.xRot = 0.0F;
//		this.bodyPart2.xRot = 0.0F;

		float f = (float)Math.PI / 20.0F;
		float f1 = (float)Math.PI / 48.0F;
		float f2 = Mth.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount;
		float f3 = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.7F * limbSwingAmount;
		float f4 = Mth.cos(limbSwing * 0.6662F + (float)Math.PI / 2.0F) * 0.7F * limbSwingAmount;
		float f5 = Mth.cos(limbSwing * 0.6662F + (float)Math.PI * 3.0F / 2.0F) * 0.7F * limbSwingAmount;
		float f6 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.1F) * limbSwingAmount;
		float f7 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.1F) * limbSwingAmount;
		float f8 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float)Math.PI / 2.0F) * 0.1F) * limbSwingAmount;
		float f9 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float)Math.PI * 3.0F / 2.0F) * 0.1F) * limbSwingAmount;
		float f10 = (float)Math.PI / 18.0F;
		float f11 = (float)Math.PI / 10.0F;

		this.leg1.xRot = -f - f2;
		this.leg2.xRot = -f + f2;
		this.leg3.xRot = -f1 - f3;
		this.leg4.xRot = -f1 + f3;
		this.leg5.xRot = f1 - f4;
		this.leg6.xRot = f1 + f4;
		this.leg7.xRot = f - f5;
		this.leg8.xRot = f + f5;
		this.leg1.zRot = f10 + f6;
		this.leg2.zRot = -f10 - f6;
		this.leg3.zRot = f11 + f7;
		this.leg4.zRot = -f11 - f7;
		this.leg5.zRot = f11 + f8;
		this.leg6.zRot = -f11 - f8;
		this.leg7.zRot = f10 + f9;
		this.leg8.zRot = -f10 - f9;

//		this.leg1Part1.xRot = 0.0F;
//		this.leg2Part1.xRot = 0.0F;
//		this.leg3Part1.xRot = 0.0F;
//		this.leg4Part1.xRot = 0.0F;
//		this.leg5Part1.xRot = 0.0F;
//		this.leg6Part1.xRot = 0.0F;
//		this.leg7Part1.xRot = 0.0F;
//		this.leg8Part1.xRot = 0.0F;
//		this.leg1Part2.xRot = 0.0F;
//		this.leg2Part2.xRot = 0.0F;
//		this.leg3Part2.xRot = 0.0F;
//		this.leg4Part2.xRot = 0.0F;
//		this.leg5Part2.xRot = 0.0F;
//		this.leg6Part2.xRot = 0.0F;
//		this.leg7Part2.xRot = 0.0F;
//		this.leg8Part2.xRot = 0.0F;

		this.bodyPart3.xRot = -((float)Math.PI / 9.0F);
		this.bodyPart4.xRot = -((float)Math.PI / 9.0F);
		this.bodyPart5.xRot = -((float)Math.PI / 9.0F);
		this.bodyPart3.xRot += Mth.sin(ageInTicks * 0.067F - (float)Math.PI / 11.0F) * 0.12F;
		this.bodyPart4.xRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 5.0F) * 0.12F;
		this.bodyPart5.xRot += Mth.sin(ageInTicks * 0.067F - (float)Math.PI * 2.0F / 9.0F) * 0.12F;
		this.bodyPart3.zRot = -((float)Math.PI / 36.0F);
		this.bodyPart4.zRot = (float)Math.PI / 30.0F;
		this.bodyPart5.zRot = -((float)Math.PI / 48.0F);
		this.bodyPart3.zRot += Mth.cos(ageInTicks * 0.045F - (float)Math.PI / 11.0F) * 0.033F;
		this.bodyPart4.zRot += Mth.cos(ageInTicks * 0.045F + (float)Math.PI * 2.0F / 5.0F) * 0.033F;
		this.bodyPart5.zRot += Mth.cos(ageInTicks * 0.045F - (float)Math.PI * 2.0F / 9.0F) * 0.033F;
		this.bodyPart6.xRot = -((float)Math.PI / 10.0F);
		this.bodyPart7.xRot = -((float)Math.PI / 10.0F);
		this.bodyPart8.xRot = -((float)Math.PI / 10.0F);
		this.bodyPart6.xRot += Mth.sin(ageInTicks * 0.067F - (float)Math.PI * 3.0F / 16.0F) * 0.12F;
		this.bodyPart7.xRot += Mth.sin(ageInTicks * 0.067F - (float)Math.PI * 4.0F / 9.0F) * 0.12F;
		this.bodyPart8.xRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 2.0F) * 0.12F;
		this.bodyPart6.zRot = -((float)Math.PI / 60.0F);
		this.bodyPart7.zRot = -((float)Math.PI / 36.0F);
		this.bodyPart8.zRot = (float)Math.PI / 48.0F;
		this.bodyPart6.zRot += Mth.cos(ageInTicks * 0.045F - (float)Math.PI * 3.0F / 16.0F) * 0.033F;
		this.bodyPart7.zRot += Mth.cos(ageInTicks * 0.045F - (float)Math.PI * 4.0F / 9.0F) * 0.033F;
		this.bodyPart8.zRot += Mth.cos(ageInTicks * 0.045F + (float)Math.PI / 2.0F) * 0.033F;

		this.bodyPart9Right.zRot = -((float)Math.PI / 7.0F);
		this.bodyPart9Left.zRot = (float)Math.PI / 7.0F;
		this.bodyPart9Right.zRot += Mth.sin(ageInTicks * 0.067F - (float)Math.PI / 6.0F) * 0.036F;
		this.bodyPart9Left.zRot -= Mth.sin(ageInTicks * 0.067F - (float)Math.PI / 6.0F) * 0.036F;
		this.bodyPart10Right.zRot = -((float)Math.PI / 15.0F);
		this.bodyPart10Left.zRot = (float)Math.PI / 15.0F;
		this.bodyPart10Right.zRot += Mth.sin(ageInTicks * 0.067F - (float)Math.PI / 3.0F) * 0.036F;
		this.bodyPart10Left.zRot -= Mth.sin(ageInTicks * 0.067F - (float)Math.PI / 3.0F) * 0.036F;
	}
}