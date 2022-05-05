package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScorpionModel<T extends Entity> extends HierarchicalModel<T>
{
	private final ModelPart root;
	private final ModelPart head;
//	private final ModelPart neck;
	private final ModelPart body;

	private final ModelPart rightFang;
	private final ModelPart leftFang;

	private final ModelPart bodyPart1;
	private final ModelPart bodyPart2;
	private final ModelPart bodyPart3;
	private final ModelPart bodyPart4;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart tail4;
	private final ModelPart tail5;
	private final ModelPart needle1;
	private final ModelPart needle2;
//	private final ModelPart bodyPart5;

	private final ModelPart leg1A;
	private final ModelPart leg2A;
	private final ModelPart leg3A;
	private final ModelPart leg4A;
	private final ModelPart leg5A;
	private final ModelPart leg6A;
	private final ModelPart leg7A;
	private final ModelPart leg8A;

	private final ModelPart leg1B;
	private final ModelPart leg2B;
	private final ModelPart leg3B;
	private final ModelPart leg4B;
	private final ModelPart leg5B;
	private final ModelPart leg6B;
	private final ModelPart leg7B;
	private final ModelPart leg8B;

	private final ModelPart leg1C;
	private final ModelPart leg2C;
	private final ModelPart leg3C;
	private final ModelPart leg4C;
	private final ModelPart leg5C;
	private final ModelPart leg6C;
	private final ModelPart leg7C;
	private final ModelPart leg8C;

	private final ModelPart leg1D;
	private final ModelPart leg2D;
	private final ModelPart leg3D;
	private final ModelPart leg4D;
	private final ModelPart leg5D;
	private final ModelPart leg6D;
	private final ModelPart leg7D;
	private final ModelPart leg8D;

	private final ModelPart rightArm1;
	private final ModelPart rightArm2;
	private final ModelPart rightArm3;
	private final ModelPart rightArm4;
	private final ModelPart leftArm1;
	private final ModelPart leftArm2;
	private final ModelPart leftArm3;
	private final ModelPart leftArm4;
	private final ModelPart rightScissor1;
	private final ModelPart rightScissor2;
	private final ModelPart leftScissor1;
	private final ModelPart leftScissor2;

	public ScorpionModel(ModelPart modelPart)
	{
		this.root = modelPart;
		this.head = modelPart.getChild("head");
//		this.neck = modelPart.getChild("neck");
		this.body = modelPart.getChild("body");

		this.rightFang = this.head.getChild("right_fang");
		this.leftFang = this.head.getChild("left_fang");

		this.bodyPart1 = this.body.getChild("body_part_1");
		this.bodyPart2 = this.bodyPart1.getChild("body_part_2");
		this.bodyPart3 = this.bodyPart2.getChild("body_part_3");
		this.bodyPart4 = this.bodyPart3.getChild("body_part_4");
		this.tail1 = this.bodyPart4.getChild("tail_1");
		this.tail2 = this.tail1.getChild("tail_2");
		this.tail3 = this.tail2.getChild("tail_3");
		this.tail4 = this.tail3.getChild("tail_4");
		this.tail5 = this.tail4.getChild("tail_5");
		this.needle1 = this.tail5.getChild("needle_1");
		this.needle2 = this.needle1.getChild("needle_2");
//		this.bodyPart5 = this.body.getChild("body_part_5");

		this.leg1A = modelPart.getChild("leg_1a");
		this.leg2A = modelPart.getChild("leg_2a");
		this.leg3A = modelPart.getChild("leg_3a");
		this.leg4A = modelPart.getChild("leg_4a");
		this.leg5A = modelPart.getChild("leg_5a");
		this.leg6A = modelPart.getChild("leg_6a");
		this.leg7A = modelPart.getChild("leg_7a");
		this.leg8A = modelPart.getChild("leg_8a");

		this.leg1B = this.leg1A.getChild("leg_1b");
		this.leg2B = this.leg2A.getChild("leg_2b");
		this.leg3B = this.leg3A.getChild("leg_3b");
		this.leg4B = this.leg4A.getChild("leg_4b");
		this.leg5B = this.leg5A.getChild("leg_5b");
		this.leg6B = this.leg6A.getChild("leg_6b");
		this.leg7B = this.leg7A.getChild("leg_7b");
		this.leg8B = this.leg8A.getChild("leg_8b");

		this.leg1C = this.leg1B.getChild("leg_1c");
		this.leg2C = this.leg2B.getChild("leg_2c");
		this.leg3C = this.leg3B.getChild("leg_3c");
		this.leg4C = this.leg4B.getChild("leg_4c");
		this.leg5C = this.leg5B.getChild("leg_5c");
		this.leg6C = this.leg6B.getChild("leg_6c");
		this.leg7C = this.leg7B.getChild("leg_7c");
		this.leg8C = this.leg8B.getChild("leg_8c");

		this.leg1D = this.leg1C.getChild("leg_1d");
		this.leg2D = this.leg2C.getChild("leg_2d");
		this.leg3D = this.leg3C.getChild("leg_3d");
		this.leg4D = this.leg4C.getChild("leg_4d");
		this.leg5D = this.leg5C.getChild("leg_5d");
		this.leg6D = this.leg6C.getChild("leg_6d");
		this.leg7D = this.leg7C.getChild("leg_7d");
		this.leg8D = this.leg8C.getChild("leg_8d");

		this.rightArm1 = modelPart.getChild("right_arm_1");
		this.rightArm2 = this.rightArm1.getChild("right_arm_2");
		this.rightArm3 = this.rightArm2.getChild("right_arm_3");
		this.rightArm4 = this.rightArm3.getChild("right_arm_4");
		this.leftArm1 = modelPart.getChild("left_arm_1");
		this.leftArm2 = this.leftArm1.getChild("left_arm_2");
		this.leftArm3 = this.leftArm2.getChild("left_arm_3");
		this.leftArm4 = this.leftArm3.getChild("left_arm_4");
		this.rightScissor1 = this.rightArm4.getChild("right_scissor_1");
		this.rightScissor2 = this.rightArm4.getChild("right_scissor_2");
		this.leftScissor1 = this.leftArm4.getChild("left_scissor_1");
		this.leftScissor2 = this.leftArm4.getChild("left_scissor_2");
	}

	public static LayerDefinition createBodyLayer()
	{
		byte b0 = 18;
		byte b1 = 3;
		byte b2 = 4;
		byte b3 = 4;
		byte b4 = 7;
		MeshDefinition md = new MeshDefinition();
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 0, -3.0F, -1.0F, -4.0F, 6.0F, 3.0F, 5.0F, 0.0F, (float)b0 - 0.75F, -7.0F);
		ModClientUtils.addC(pd, "neck", 0, 8, -4.0F, -1.5F, -1.5F, 8.0F, 3.0F, 3.0F, 0.0F, (float)b0, -6.5F, -0.25F);
		PartDefinition bodypd = ModClientUtils.addC(pd, "body", 0, 24, -4.0F, -2.0F, -8.0F, 8.0F, 4.0F, 11.0F, 0.0F, (float)b0, 2.0F);

		ModClientUtils.addC(headpd, "right_fang", 24, 0, -1.0F, -1.0F, -0.75F, 2.0F, 3.0F, 1.0F, 1.25F, 1.0F, -4.0F);
		ModClientUtils.addC(headpd, "left_fang", 24, 0, -1.0F, -1.0F, -0.75F, 2.0F, 4.0F, 1.0F, -1.25F, 1.0F, -4.0F, true);

		PartDefinition bp1pd = ModClientUtils.addC(bodypd, "body_part_1", 0, 40, -3.5F, 0.0F, 0.0F, 7.0F, 3.0F, 5.0F, 0.0F, -1.5F, 2.0F);
		PartDefinition bp2pd = ModClientUtils.addC(bp1pd, "body_part_2", 24, 40, -3.0F, 0.0F, 0.0F, 6.0F, 3.0F, 4.0F, 0.0F, 0.0F, 4.25F);
		PartDefinition bp3pd = ModClientUtils.addC(bp2pd, "body_part_3", 0, 48, -2.5F, 0.0F, 0.0F, 5.0F, 3.0F, 4.0F, 0.0F, -0.125F, 3.25F);
		PartDefinition bp4pd = ModClientUtils.addC(bp3pd, "body_part_4", 24, 48, -2.0F, 0.0F, 0.0F, 4.0F, 3.0F, 3.0F, 0.0F, -0.125F, 3.25F);
		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F);
		PartPose pp = PartPose.offset(0.0F, 0.0F, 2.25F);
		PartDefinition t1pd = ModClientUtils.addC(bp4pd, "tail_1", 0, 56, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, 0.125F, 2.25F, 0.25F);
		PartDefinition t2pd = t1pd.addOrReplaceChild("tail_2", cubelistbuilder, pp);
		PartDefinition t3pd = t2pd.addOrReplaceChild("tail_3", cubelistbuilder, pp);
		PartDefinition t4pd = t3pd.addOrReplaceChild("tail_4", cubelistbuilder, pp);
		PartDefinition t5pd = t4pd.addOrReplaceChild("tail_5", cubelistbuilder, pp);
		PartDefinition n1pd = ModClientUtils.addC(t5pd, "needle_1", 16, 56, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, 0.0F, 2.25F, 0.125F);
		ModClientUtils.addC(n1pd, "needle_2", 32, 56, -0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, -0.5F, 2.75F);

		ModClientUtils.addC(bodypd, "body_part_5", 32, 0, -3.0F, -1.0F, -7.0F, 6.0F, 1.0F, 9.0F, 0.0F, -1.5F, 0.0F);

		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(0, 16).addBox(-((float)b1) + 0.5F, -1.0F, -1.0F, (float)b1, 2.0F, 2.0F, new CubeDeformation(-0.25F));
		CubeListBuilder cubelistbuilder2 = CubeListBuilder.create().texOffs(0, 16).addBox(-0.5F, -1.0F, -1.0F, (float)b1, 2.0F, 2.0F, new CubeDeformation(-0.25F));
		float f = 3.75F;
		float f1 = (float)b0 + 0.5F;
		PartDefinition l1apd = pd.addOrReplaceChild("leg_1a", cubelistbuilder1, PartPose.offset(-f, f1, -0.25F));
		PartDefinition l2apd = pd.addOrReplaceChild("leg_2a", cubelistbuilder2, PartPose.offset(f, f1, -0.25F));
		PartDefinition l3apd = pd.addOrReplaceChild("leg_3a", cubelistbuilder1, PartPose.offset(-f, f1, -1.5F));
		PartDefinition l4apd = pd.addOrReplaceChild("leg_4a", cubelistbuilder2, PartPose.offset(f, f1, -1.5F));
		PartDefinition l5apd = pd.addOrReplaceChild("leg_5a", cubelistbuilder1, PartPose.offset(-f, f1, -2.75F));
		PartDefinition l6apd = pd.addOrReplaceChild("leg_6a", cubelistbuilder2, PartPose.offset(f, f1, -2.75F));
		PartDefinition l7apd = pd.addOrReplaceChild("leg_7a", cubelistbuilder1, PartPose.offset(-f, f1, -4.0F));
		PartDefinition l8apd = pd.addOrReplaceChild("leg_8a", cubelistbuilder2, PartPose.offset(f, f1, -4.0F));
		CubeListBuilder cubelistbuilder3 = CubeListBuilder.create().texOffs(0, 20).addBox(-((float)b2) + 0.5F, -1.0F, -1.0F, (float)b2, 2.0F, 2.0F, new CubeDeformation(-0.2501F));
		CubeListBuilder cubelistbuilder4 = CubeListBuilder.create().texOffs(0, 20).addBox(-0.5F, -1.0F, -1.0F, (float)b2, 2.0F, 2.0F, new CubeDeformation(-0.2501F));
		PartPose pp1 = PartPose.offset(-((float)b1) + 1.0F, 0.0F, 0.0F);
		PartPose pp2 = PartPose.offset((float)b1 - 1.0F, 0.0F, 0.0F);
		PartDefinition l1bpd = l1apd.addOrReplaceChild("leg_1b", cubelistbuilder3, pp1);
		PartDefinition l2bpd = l2apd.addOrReplaceChild("leg_2b", cubelistbuilder4, pp2);
		PartDefinition l3bpd = l3apd.addOrReplaceChild("leg_3b", cubelistbuilder3, pp1);
		PartDefinition l4bpd = l4apd.addOrReplaceChild("leg_4b", cubelistbuilder4, pp2);
		PartDefinition l5bpd = l5apd.addOrReplaceChild("leg_5b", cubelistbuilder3, pp1);
		PartDefinition l6bpd = l6apd.addOrReplaceChild("leg_6b", cubelistbuilder4, pp2);
		PartDefinition l7bpd = l7apd.addOrReplaceChild("leg_7b", cubelistbuilder3, pp1);
		PartDefinition l8bpd = l8apd.addOrReplaceChild("leg_8b", cubelistbuilder4, pp2);
		CubeListBuilder cubelistbuilder5 = CubeListBuilder.create().texOffs(16, 16).addBox(-((float)b3) + 0.5F, -1.0F, -1.0F, (float)b3, 2.0F, 2.0F, new CubeDeformation(-0.25F));
		CubeListBuilder cubelistbuilder6 = CubeListBuilder.create().texOffs(16, 16).addBox(-0.5F, -1.0F, -1.0F, (float)b3, 2.0F, 2.0F, new CubeDeformation(-0.25F));
		PartPose pp3 = PartPose.offset(-((float)b2) + 1.0F, 0.0F, 0.0F);
		PartPose pp4 = PartPose.offset((float)b2 - 1.0F, 0.0F, 0.0F);
		PartDefinition l1cpd = l1bpd.addOrReplaceChild("leg_1c", cubelistbuilder5, pp3);
		PartDefinition l2cpd = l2bpd.addOrReplaceChild("leg_2c", cubelistbuilder6, pp4);
		PartDefinition l3cpd = l3bpd.addOrReplaceChild("leg_3c", cubelistbuilder5, pp3);
		PartDefinition l4cpd = l4bpd.addOrReplaceChild("leg_4c", cubelistbuilder6, pp4);
		PartDefinition l5cpd = l5bpd.addOrReplaceChild("leg_5c", cubelistbuilder5, pp3);
		PartDefinition l6cpd = l6bpd.addOrReplaceChild("leg_6c", cubelistbuilder6, pp4);
		PartDefinition l7cpd = l7bpd.addOrReplaceChild("leg_7c", cubelistbuilder5, pp3);
		PartDefinition l8cpd = l8bpd.addOrReplaceChild("leg_8c", cubelistbuilder6, pp4);
		CubeListBuilder cubelistbuilder7 = CubeListBuilder.create().texOffs(16, 20).addBox(-((float)b4) + 0.5F, -1.0F, -1.0F, (float)b4, 2.0F, 2.0F, new CubeDeformation(-0.5F));
		CubeListBuilder cubelistbuilder8 = CubeListBuilder.create().texOffs(16, 20).addBox(-0.5F, -1.0F, -1.0F, (float)b4, 2.0F, 2.0F, new CubeDeformation(-0.5F));
		PartPose pp5 = PartPose.offset(-((float)b3) + 1.5F, 0.0F, 0.0F);
		PartPose pp6 = PartPose.offset((float)b3 - 1.5F, 0.0F, 0.0F);
		l1cpd.addOrReplaceChild("leg_1d", cubelistbuilder7, pp5);
		l2cpd.addOrReplaceChild("leg_2d", cubelistbuilder8, pp6);
		l3cpd.addOrReplaceChild("leg_3d", cubelistbuilder7, pp5);
		l4cpd.addOrReplaceChild("leg_4d", cubelistbuilder8, pp6);
		l5cpd.addOrReplaceChild("leg_5d", cubelistbuilder7, pp5);
		l6cpd.addOrReplaceChild("leg_6d", cubelistbuilder8, pp6);
		l7cpd.addOrReplaceChild("leg_7d", cubelistbuilder7, pp5);
		l8cpd.addOrReplaceChild("leg_8d", cubelistbuilder8, pp6);

		PartDefinition ra1pd = ModClientUtils.addC(pd, "right_arm_1", 48, 16, -1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, -4.0F, (float)b0, -7.0F);
		PartDefinition ra2pd = ModClientUtils.addC(ra1pd, "right_arm_2", 48, 24, -1.0F, -1.0F, -4.5F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, -3.5F);
		PartDefinition ra3pd = ModClientUtils.addC(ra2pd, "right_arm_3", 48, 32, -1.0F, -1.0F, -4.5F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, -4.5F, 0.25F);
		PartDefinition ra4pd = ModClientUtils.addC(ra3pd, "right_arm_4", 48, 40, -1.5F, -1.0F, -4.5F, 3.0F, 2.0F, 4.0F, 0.0F, 0.0F, -4.5F, 0.25F);
		PartDefinition la1pd = ModClientUtils.addC(pd, "left_arm_1", 48, 16, -1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, 4.0F, (float)b0, -7.0F, true);
		PartDefinition la2pd = ModClientUtils.addC(la1pd, "left_arm_2", 48, 24, -1.0F, -1.0F, -4.5F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, -3.5F, true);
		PartDefinition la3pd = ModClientUtils.addC(la2pd, "left_arm_3", 48, 32, -1.0F, -1.0F, -4.5F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, -4.5F, true, 0.25F);
		PartDefinition la4pd = ModClientUtils.addC(la3pd, "left_arm_4", 48, 40, -1.5F, -1.0F, -4.5F, 3.0F, 2.0F, 4.0F, 0.0F, 0.0F, -4.5F, true, 0.25F);
		ModClientUtils.addC(ra4pd, "right_scissor_1", 48, 48, -0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F, -0.75F, 0.0F, -4.5F);
		ModClientUtils.addC(ra4pd, "right_scissor_2", 48, 52, -0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F, 0.75F, 0.0F, -4.5F, 0.25F);
		ModClientUtils.addC(la4pd, "left_scissor_1", 48, 48, -0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F, 0.75F, 0.0F, -4.5F, true);
		ModClientUtils.addC(la4pd, "left_scissor_2", 48, 52, -0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F, -0.75F, 0.0F, -4.5F, true, 0.25F);
		return LayerDefinition.create(md, 64, 64);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180.0F);
		this.head.xRot = headPitch * ((float)Math.PI / 180.0F);
		this.head.xRot += ((float)Math.PI / 15.0F);

		this.bodyPart1.xRot = (float)Math.PI / 18.0F;
		this.bodyPart2.xRot = (float)Math.PI / 11.0F;
		this.bodyPart3.xRot = (float)Math.PI / 8.0F;
		this.bodyPart4.xRot = (float)Math.PI / 6.0F;

		this.bodyPart1.xRot += Mth.sin(ageInTicks * 0.03F) * 0.021F;
		this.bodyPart2.xRot += Mth.sin(ageInTicks * 0.03F - (float)Math.PI / 9.0F) * 0.024F;
		this.bodyPart3.xRot += Mth.sin(ageInTicks * 0.03F - (float)Math.PI * 2.0F / 9.0F) * 0.024F;
		this.bodyPart4.xRot += Mth.sin(ageInTicks * 0.03F - (float)Math.PI / 3.0F) * 0.021F;

		float f = (float)Math.PI / 7.0F;
		float f1 = (float)Math.PI * 2.0F / 17.0F;

		this.tail1.xRot = f;
		this.tail2.xRot = f;
		this.tail3.xRot = f1;
		this.tail4.xRot = f1;
		this.tail5.xRot = (float)Math.PI / 11.0F;
		this.needle1.xRot = f1;
		this.needle2.xRot = -((float)Math.PI / 9.0F);

		f = Mth.sin(ageInTicks * 0.03F + (float)Math.PI / 3.0F);
		f1 = Mth.sin(ageInTicks * 0.03F + (float)Math.PI * 5.0F / 9.0F);

		this.tail1.xRot -= f * 0.015F;
		this.tail2.xRot -= f * 0.021F;
		this.tail3.xRot -= f * 0.033F;
		this.tail4.xRot -= f1 * 0.045F;
		this.tail5.xRot -= f1 * 0.045F;
		this.needle1.xRot -= f1 * 0.021F;

		this.rightFang.xRot = ((float)Math.PI / 21.0F);
		this.leftFang.xRot = ((float)Math.PI / 21.0F);
		this.rightFang.y = 1.0F;
		this.leftFang.y = 1.0F;
		this.rightFang.y -= (Mth.sin(ageInTicks * 0.042F + (float)Math.PI) * 0.2F);
		this.leftFang.y -= (Mth.sin(ageInTicks * 0.042F + (float)Math.PI) * 0.2F);

		float f2 = (float)Math.PI / 4.0F;

		this.leg1A.zRot = -f2;
		this.leg2A.zRot = f2;
		this.leg3A.zRot = -f2 * 0.74F;
		this.leg4A.zRot = f2 * 0.74F;
		this.leg5A.zRot = -f2 * 0.74F;
		this.leg6A.zRot = f2 * 0.74F;
		this.leg7A.zRot = -f2;
		this.leg8A.zRot = f2;

		this.leg1A.yRot = f2 * 1.0F;
		this.leg2A.yRot = -f2 * 1.0F;
		this.leg3A.yRot = f2 * 0.5F;
		this.leg4A.yRot = -f2 * 0.5F;
		this.leg5A.yRot = -f2 * 0.1F;
		this.leg6A.yRot = f2 * 0.1F;
		this.leg7A.yRot = -f2 * 0.7F;
		this.leg8A.yRot = f2 * 0.7F;

		f2 = (float)Math.PI * 3.0F / 7.0F;

		this.leg1B.zRot = f2;
		this.leg2B.zRot = -f2;
		this.leg3B.zRot = f2;
		this.leg4B.zRot = -f2;
		this.leg5B.zRot = f2;
		this.leg6B.zRot = -f2;
		this.leg7B.zRot = f2;
		this.leg8B.zRot = -f2;

		f2 = (float)Math.PI * 4.0F / 9.0F;

		this.leg1C.zRot = -f2;
		this.leg2C.zRot = f2;
		this.leg3C.zRot = -f2;
		this.leg4C.zRot = f2;
		this.leg5C.zRot = -f2;
		this.leg6C.zRot = f2;
		this.leg7C.zRot = -f2;
		this.leg8C.zRot = f2;

		f2 = (float)Math.PI / 5.0F;

		this.leg1D.zRot = -f2;
		this.leg2D.zRot = f2;
		this.leg3D.zRot = -f2;
		this.leg4D.zRot = f2;
		this.leg5D.zRot = -f2;
		this.leg6D.zRot = f2;
		this.leg7D.zRot = -f2;
		this.leg8D.zRot = f2;

		f = (float)Math.PI / 5.0F;
		f1 = (float)Math.PI * 3.0F / 5.0F;
		f2 = (float)Math.PI * 3.0F / 4.0F;

		this.leftArm1.yRot = -f;
		this.rightArm1.yRot = f;
		this.rightArm1.xRot = (float)Math.PI / 15.0F;
		this.leftArm1.xRot = (float)Math.PI / 15.0F;
		this.rightArm1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.015F;
		this.leftArm1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.015F;
		this.rightArm1.yRot += Mth.sin(ageInTicks * 0.03F) * 0.03F;
		this.leftArm1.yRot -= Mth.sin(ageInTicks * 0.03F) * 0.03F;
		this.leftArm2.yRot = -f1;
		this.rightArm2.yRot = f1;
		this.rightArm2.yRot += Mth.sin(ageInTicks * 0.042F) * 0.054F;
		this.leftArm2.yRot -= Mth.sin(ageInTicks * 0.042F) * 0.054F;
		this.rightArm2.xRot = Mth.sin(ageInTicks * 0.084F + ((float)Math.PI / 3.0F)) * 0.021F;
		this.leftArm2.xRot = Mth.sin(ageInTicks * 0.084F + ((float)Math.PI / 3.0F)) * 0.021F;
		this.leftArm3.yRot = f2;
		this.rightArm3.yRot = -f2;
		this.rightArm3.yRot -= Mth.sin(ageInTicks * 0.084F + ((float)Math.PI / 5.0F)) * 0.067F;
		this.leftArm3.yRot += Mth.sin(ageInTicks * 0.084F + ((float)Math.PI / 5.0F)) * 0.067F;
		this.leftArm4.yRot = (float)Math.PI / 11.0F;
		this.rightArm4.yRot = -((float)Math.PI / 11.0F);
		this.rightArm4.yRot -= Mth.sin(ageInTicks * 0.03F + ((float)Math.PI / 4.0F)) * 0.045F;
		this.leftArm4.yRot += Mth.sin(ageInTicks * 0.03F + ((float)Math.PI * 3.0F / 4.0F)) * 0.045F;

		this.leftArm4.zRot = (float)Math.PI / 6.0F;
		this.rightArm4.zRot = -((float)Math.PI / 6.0F);
		this.rightArm4.zRot -= Mth.sin(ageInTicks * 0.03F + ((float)Math.PI / 4.0F)) * 0.03F;
		this.leftArm4.zRot += Mth.sin(ageInTicks * 0.03F + ((float)Math.PI * 3.0F / 4.0F)) * 0.03F;

		this.rightScissor1.yRot = Math.abs(Mth.sin(ageInTicks * 0.06F)) * 0.18F;
		this.leftScissor1.yRot = -Math.abs(Mth.sin(ageInTicks * 0.06F)) * 0.18F;
		this.rightScissor2.xRot = 0.0F;
		this.leftScissor2.xRot = 0.0F;

		if (entityIn.isOnGround())
		{
			float f3 = -(Mth.cos(limbSwing * 0.67F * 2.0F) * 0.4F) * limbSwingAmount;
			float f4 = -(Mth.cos(limbSwing * 0.67F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
			float f5 = -(Mth.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI / 2.0F)) * 0.4F) * limbSwingAmount;
			float f6 = -(Mth.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI * 3.0F / 2.0F)) * 0.4F) * limbSwingAmount;
			float f7 = Math.abs(Mth.sin(limbSwing * 0.67F) * 0.4F) * limbSwingAmount;
			float f8 = Math.abs(Mth.sin(limbSwing * 0.67F + (float)Math.PI) * 0.4F) * limbSwingAmount;
			float f9 = Math.abs(Mth.sin(limbSwing * 0.67F + ((float)Math.PI / 2.0F)) * 0.4F) * limbSwingAmount;
			float f10 = Math.abs(Mth.sin(limbSwing * 0.67F + ((float)Math.PI * 3.0F / 2.0F)) * 0.4F) * limbSwingAmount;
			float f11 = Math.abs(Mth.sin(limbSwing * 0.67F - ((float)Math.PI / 8.0F)) * 0.24F) * limbSwingAmount;
			float f12 = Math.abs(Mth.sin(limbSwing * 0.67F + ((float)Math.PI * 7.0F / 8.0F)) * 0.24F) * limbSwingAmount;
			float f13 = Math.abs(Mth.sin(limbSwing * 0.67F + ((float)Math.PI * 3.0F / 8.0F)) * 0.24F) * limbSwingAmount;
			float f14 = Math.abs(Mth.sin(limbSwing * 0.67F + ((float)Math.PI * 11.0F / 8.0F)) * 0.24F) * limbSwingAmount;
			this.leg1A.yRot += f3;
			this.leg2A.yRot += -f3;
			this.leg3A.yRot += f4;
			this.leg4A.yRot += -f4;
			this.leg5A.yRot += f5;
			this.leg6A.yRot += -f5;
			this.leg7A.yRot += f6;
			this.leg8A.yRot += -f6;
			this.leg1A.zRot += f7;
			this.leg2A.zRot += -f7;
			this.leg3A.zRot += f8;
			this.leg4A.zRot += -f8;
			this.leg5A.zRot += f9;
			this.leg6A.zRot += -f9;
			this.leg7A.zRot += f10;
			this.leg8A.zRot += -f10;
			this.leg1B.zRot += -f11;
			this.leg2B.zRot += f11;
			this.leg3B.zRot += -f12;
			this.leg4B.zRot += f12;
			this.leg5B.zRot += -f13;
			this.leg6B.zRot += f13;
			this.leg7B.zRot += -f14;
			this.leg8B.zRot += f14;
			this.leg1C.zRot += f7 * 0.45F;
			this.leg2C.zRot += -f7 * 0.45F;
			this.leg3C.zRot += f8 * 0.45F;
			this.leg4C.zRot += -f8 * 0.45F;
			this.leg5C.zRot += f9 * 0.45F;
			this.leg6C.zRot += -f9 * 0.45F;
			this.leg7C.zRot += f10 * 0.45F;
			this.leg8C.zRot += -f10 * 0.45F;
			this.leg1D.zRot += f7 * 0.21F;
			this.leg2D.zRot += -f7 * 0.21F;
			this.leg3D.zRot += f8 * 0.21F;
			this.leg4D.zRot += -f8 * 0.21F;
			this.leg5D.zRot += f9 * 0.21F;
			this.leg6D.zRot += -f9 * 0.21F;
			this.leg7D.zRot += f10 * 0.21F;
			this.leg8D.zRot += -f10 * 0.21F;
		}

		this.bodyPart2.xRot += (Mth.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI / 5.0F)) * 0.33F) * limbSwingAmount * 0.12F;
		this.bodyPart3.xRot += (Mth.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI * 2.0F / 5.0F)) * 0.33F) * limbSwingAmount * 0.12F;
		this.bodyPart4.xRot += (Mth.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI * 3.0F / 5.0F)) * 0.33F) * limbSwingAmount * 0.09F;
		this.rightArm1.xRot += (Mth.cos(limbSwing * 0.75F) * 0.4F) * limbSwingAmount * 0.09F;
		this.leftArm1.xRot += (Mth.cos(limbSwing * 0.75F) * 0.4F) * limbSwingAmount * 0.09F;
		this.rightArm2.yRot -= (Mth.cos(limbSwing * 0.67F * 2.0F) * 0.4F) * limbSwingAmount * 0.45F;
		this.leftArm2.yRot += (Mth.cos(limbSwing * 0.67F * 2.0F) * 0.4F) * limbSwingAmount * 0.45F;
		this.rightArm3.yRot += (Mth.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI / 4.0F)) * 0.4F) * limbSwingAmount * 0.6F;
		this.leftArm3.yRot -= (Mth.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI / 4.0F)) * 0.4F) * limbSwingAmount * 0.6F;

		if (this.attackTime > 0.0F)
		{
			float f11 = 1.0F - this.attackTime;
			f11 = f11 * f11;
			f11 = f11 * f11;
			f11 = 1.0F - f11;
			float f12 = Mth.sin(f11 * (float)Math.PI);
			float f13 = Mth.sin(this.attackTime * (float)Math.PI) * 0.75F;
			float f14 = f12 * 0.5F + f13 * 0.3F;

			this.bodyPart1.xRot += f12 * 0.2F + f13 * 0.3F;
			this.bodyPart2.xRot += f12 * 0.2F + f13 * 0.3F;
			this.bodyPart3.xRot += f12 * 0.2F + f13 * 0.4F;
			this.bodyPart4.xRot += f12 * 0.1F + f13 * 0.3F;
			this.tail1.xRot += f14 * 0.2F;
			this.tail2.xRot -= f14 * 0.3F;
			this.tail3.xRot -= f14 * 0.6F;
			this.tail4.xRot -= f14 * 0.9F;
			this.tail5.xRot -= f14 * 1.1F;
			this.needle1.xRot -= f14 * 0.9F;
			this.rightArm3.yRot -= f12 * 0.2F + f13 * 0.3F;
			this.leftArm3.yRot += f12 * 0.2F + f13 * 0.3F;
			this.rightArm4.yRot -= f12 * 0.1F + f13 * 0.2F;
			this.leftArm4.yRot += f12 * 0.1F + f13 * 0.2F;
			this.rightScissor1.yRot += f12 * 0.3F + f13 * 0.54F;
			this.leftScissor1.yRot -= f12 * 0.3F + f13 * 0.54F;
		}
	}
}