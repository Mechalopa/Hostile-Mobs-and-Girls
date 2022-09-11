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
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrimsonSlaughtererModel<T extends Mob> extends HierarchicalModel<T>
{
	private final ModelPart root;

	private final ModelPart head;
//	private final ModelPart hat;
//	private final ModelPart hairPart;
	private final ModelPart rightTailHair1;
	private final ModelPart rightTailHair2;
	private final ModelPart rightTailHair3;
	private final ModelPart rightTailHair4;
	private final ModelPart rightTailHair5;
	private final ModelPart leftTailHair1;
	private final ModelPart leftTailHair2;
	private final ModelPart leftTailHair3;
	private final ModelPart leftTailHair4;
	private final ModelPart leftTailHair5;
	private final ModelPart hairBand;

	private final ModelPart body;
	private final ModelPart bust;
	private final ModelPart bodyPart1;
	private final ModelPart bodyPart2;
	private final ModelPart body2;
	private final ModelPart body2Part1;
	private final ModelPart body2Part2;
	private final ModelPart body2Teeth;
	private final ModelPart body2Part3;
	private final ModelPart body2Part4;
	private final ModelPart body2Eyes;
	private final ModelPart body2Part5;
	private final ModelPart body2Part6;
	private final ModelPart body2Part7;
	private final ModelPart body2Part8;

	private final ModelPart rightArm;
	private final ModelPart leftArm;
//	private final ModelPart rightArmPart;
//	private final ModelPart leftArmPart;

	private final ModelPart leg1A;
	private final ModelPart leg2A;
	private final ModelPart leg3A;
	private final ModelPart leg4A;
	private final ModelPart leg5A;
	private final ModelPart leg6A;

	private final ModelPart leg1B;
	private final ModelPart leg2B;
	private final ModelPart leg3B;
	private final ModelPart leg4B;
	private final ModelPart leg5B;
	private final ModelPart leg6B;

	private final ModelPart leg1C;
	private final ModelPart leg2C;
	private final ModelPart leg3C;
	private final ModelPart leg4C;
	private final ModelPart leg5C;
	private final ModelPart leg6C;

	private final ModelPart leg1D;
	private final ModelPart leg2D;
	private final ModelPart leg3D;
	private final ModelPart leg4D;
	private final ModelPart leg5D;
	private final ModelPart leg6D;

	private final ModelPart leg1E;
	private final ModelPart leg2E;
	private final ModelPart leg3E;
	private final ModelPart leg4E;
	private final ModelPart leg5E;
	private final ModelPart leg6E;

	public CrimsonSlaughtererModel(ModelPart modelPart)
	{
		this.root = modelPart;

		this.head = modelPart.getChild("head");
//		this.hat = this.head.getChild("hat");
//		this.hairPart = this.head.getChild("hair_part");
		this.rightTailHair1 = this.head.getChild("right_tail_hair_1");
		this.rightTailHair2 = this.rightTailHair1.getChild("right_tail_hair_2");
		this.rightTailHair3 = this.rightTailHair2.getChild("right_tail_hair_3");
		this.rightTailHair4 = this.rightTailHair3.getChild("right_tail_hair_4");
		this.rightTailHair5 = this.rightTailHair4.getChild("right_tail_hair_5");
		this.leftTailHair1 = this.head.getChild("left_tail_hair_1");
		this.leftTailHair2 = this.leftTailHair1.getChild("left_tail_hair_2");
		this.leftTailHair3 = this.leftTailHair2.getChild("left_tail_hair_3");
		this.leftTailHair4 = this.leftTailHair3.getChild("left_tail_hair_4");
		this.leftTailHair5 = this.leftTailHair4.getChild("left_tail_hair_5");
		this.hairBand = this.head.getChild("hair_band");

		this.body = modelPart.getChild("body");
		this.bust = this.body.getChild("bust");
		this.bodyPart1 = this.body.getChild("body_part_1");
		this.bodyPart2 = this.bodyPart1.getChild("body_part_2");

		this.body2 = modelPart.getChild("body_2");
		this.body2Part1 = this.body2.getChild("body_2_part_1");
		this.body2Part2 = this.body2.getChild("body_2_part_2");
		this.body2Teeth = this.body2.getChild("body_2_teeth");
		this.body2Part3 = this.body2Part2.getChild("body_2_part_3");
		this.body2Part4 = this.body2Part3.getChild("body_2_part_4");
		this.body2Eyes = this.body2Part3.getChild("body_2_eyes");
		this.body2Part5 = this.body2.getChild("body_2_part_5");
		this.body2Part6 = this.body2Part5.getChild("body_2_part_6");
		this.body2Part7 = this.body2Part6.getChild("body_2_part_7");
		this.body2Part8 = this.body2Part6.getChild("body_2_part_8");

		this.rightArm = modelPart.getChild("right_arm");
		this.leftArm = modelPart.getChild("left_arm");
//		this.rightArmPart = this.rightArm.getChild("right_arm_part");
//		this.leftArmPart = this.leftArm.getChild("left_arm_part");

		this.leg1A = modelPart.getChild("leg_1a");
		this.leg2A = modelPart.getChild("leg_2a");
		this.leg3A = modelPart.getChild("leg_3a");
		this.leg4A = modelPart.getChild("leg_4a");
		this.leg5A = modelPart.getChild("leg_5a");
		this.leg6A = modelPart.getChild("leg_6a");

		this.leg1B = this.leg1A.getChild("leg_1b");
		this.leg2B = this.leg2A.getChild("leg_2b");
		this.leg3B = this.leg3A.getChild("leg_3b");
		this.leg4B = this.leg4A.getChild("leg_4b");
		this.leg5B = this.leg5A.getChild("leg_5b");
		this.leg6B = this.leg6A.getChild("leg_6b");

		this.leg1C = this.leg1B.getChild("leg_1c");
		this.leg2C = this.leg2B.getChild("leg_2c");
		this.leg3C = this.leg3B.getChild("leg_3c");
		this.leg4C = this.leg4B.getChild("leg_4c");
		this.leg5C = this.leg5B.getChild("leg_5c");
		this.leg6C = this.leg6B.getChild("leg_6c");

		this.leg1D = this.leg1C.getChild("leg_1d");
		this.leg2D = this.leg2C.getChild("leg_2d");
		this.leg3D = this.leg3C.getChild("leg_3d");
		this.leg4D = this.leg4C.getChild("leg_4d");
		this.leg5D = this.leg5C.getChild("leg_5d");
		this.leg6D = this.leg6C.getChild("leg_6d");

		this.leg1E = this.leg1D.getChild("leg_1e");
		this.leg2E = this.leg2D.getChild("leg_2e");
		this.leg3E = this.leg3D.getChild("leg_3e");
		this.leg4E = this.leg4D.getChild("leg_4e");
		this.leg5E = this.leg5D.getChild("leg_5e");
		this.leg6E = this.leg6D.getChild("leg_6e");
	}

	public static LayerDefinition createBodyLayer()
	{
		float f = 12.0F;
		byte b0 = 4;
		byte b1 = 7;
		byte b2 = 10;
		byte b3 = 18;
		byte b4 = 15;
		MeshDefinition md = new MeshDefinition();
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 0, -3.0F, 1.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, f, 0.0F, 1.0F);
		ModClientUtils.addC(headpd, "hat", 32, 0, -4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.5F);
		ModClientUtils.addC(headpd, "hair_part", 64, 0, -4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		PartDefinition rth1pd = ModClientUtils.addC(headpd, "right_tail_hair_1", 64, 16, -1.0F, -3.0F, 0.0F, 2.0F, 4.0F, 2.0F, -2.25F, 6.0F, 4.0F);
		PartDefinition rth2pd = ModClientUtils.addC(rth1pd, "right_tail_hair_2", 72, 16, -1.0F, -5.5F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, -3.0F, 0.0F, 0.001F);
		PartDefinition rth3pd = ModClientUtils.addC(rth2pd, "right_tail_hair_3", 72, 24, -1.0F, -5.5F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, -5.25F, 0.0F, -0.25F);
		PartDefinition rth4pd = ModClientUtils.addC(rth3pd, "right_tail_hair_4", 80, 16, -0.5F, -6.5F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, -4.5F, 0.5F);
		ModClientUtils.addC(rth4pd, "right_tail_hair_5", 88, 16, -1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, -6.0F, 0.5F);
		PartDefinition lth1pd = ModClientUtils.addC(headpd, "left_tail_hair_1", 64, 16, -1.0F, -3.0F, 0.0F, 2.0F, 4.0F, 2.0F, 2.25F, 6.0F, 4.0F, true);
		PartDefinition lth2pd = ModClientUtils.addC(lth1pd, "left_tail_hair_2", 72, 16, -1.0F, -5.5F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, -3.0F, 0.0F, true, 0.001F);
		PartDefinition lth3pd = ModClientUtils.addC(lth2pd, "left_tail_hair_3", 72, 24, -1.0F, -5.5F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, -5.25F, 0.0F, true, -0.25F);
		PartDefinition lth4pd = ModClientUtils.addC(lth3pd, "left_tail_hair_4", 80, 16, -0.5F, -6.5F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, -4.5F, 0.5F, true);
		ModClientUtils.addC(lth4pd, "left_tail_hair_5", 88, 16, -1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, -6.0F, 0.5F, true);
		ModClientUtils.addC(headpd, "hair_band", 88, 24, -3.5F, 0.0F, 0.0F, 7.0F, 2.0F, 1.0F, 0.0F, 8.0F, -0.5F);

		PartDefinition bodypd = ModClientUtils.addC(pd, "body", 0, 16, -3.0F, -6.0F, -1.5F, 6.0F, 6.0F, 3.0F, 0.0F, f, 0.0F);
		ModClientUtils.addC(bodypd, "bust", 0, 26, -3.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, 0.0F, -3.5F, -1.1F, -0.001F);
		PartDefinition bp1pd = ModClientUtils.addC(bodypd, "body_part_1", 24, 16, -2.5F, -1.0F, -1.0F, 5.0F, 1.0F, 2.0F, 0.0F, -6.0F, 0.0F, 0.125F);
		ModClientUtils.addC(bp1pd, "body_part_2", 24, 24, -2.5F, -4.0F, -1.0F, 5.0F, 4.0F, 2.0F, 0.0F, -1.0F, 0.0F);

		PartDefinition body2pd = ModClientUtils.addC(pd, "body_2", 0, 32, -5.5F, -2.0F, -4.0F, 11.0F, 2.0F, 8.0F, 0.0F, f - 13.0F, 0.0F);
		ModClientUtils.addC(body2pd, "body_2_part_1", 0, 48, -5.5F, -4.0F, -4.0F, 11.0F, 6.0F, 8.0F, 0.0F, -2.0F, 0.0F, -0.0001F);
		PartDefinition b2p2pd = ModClientUtils.addC(body2pd, "body_2_part_2", 0, 64, -6.5F, -4.0F, -4.5F, 13.0F, 2.0F, 9.0F, 0.0F, -2.0F, 0.0F);
		ModClientUtils.addC(body2pd, "body_2_teeth", 40, 32, -5.5F, -2.0F, -4.0F, 11.0F, 2.0F, 8.0F, 0.0F, -2.0F, 0.0F);
		PartDefinition b2p3pd = ModClientUtils.addC(b2p2pd, "body_2_part_3", 0, 80, -7.0F, -2.0F, -5.0F, 14.0F, 2.0F, 10.0F, 0.0F, -4.0F, 0.0F);
		ModClientUtils.addC(b2p3pd, "body_2_part_4", 0, 96, -7.0F, -2.0F, -5.0F, 14.0F, 2.0F, 10.0F, 0.0F, -2.0F, 0.0F);
		ModClientUtils.addC(b2p3pd, "body_2_eyes", 0, 112, -5.0F, -4.0F, -1.0F, 10.0F, 4.0F, 1.0F, 0.0F, 0.0F, -5.0F);
		PartDefinition b2p5pd = ModClientUtils.addC(body2pd, "body_2_part_5", 48, 48, -4.5F, 0.0F, -3.0F, 9.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		PartDefinition b2p6pd = ModClientUtils.addC(b2p5pd, "body_2_part_6", 48, 56, -4.0F, 0.0F, -2.5F, 8.0F, 1.0F, 5.0F, 0.0F, 2.0F, 0.0F);
		ModClientUtils.addC(b2p6pd, "body_2_part_7", 48, 64, -3.0F, 0.0F, -2.0F, 6.0F, 2.0F, 4.0F, 0.0F, -0.5F, -0.5F);
		ModClientUtils.addC(b2p6pd, "body_2_part_8", 48, 72, -4.0F, 0.0F, -2.5F, 8.0F, 3.0F, 5.0F, 0.0F, 1.0F, 0.0F);

		PartDefinition rapd = ModClientUtils.addC(pd, "right_arm", 40, 16, -2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, -5.0F, f - 2.0F, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, "left_arm", 40, 16, 0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 5.0F, f - 2.0F, 0.0F, true);
		ModClientUtils.addC(rapd, "right_arm_part", 48, 16, -2.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 6.5F, 0.0F, 0.25F);
		ModClientUtils.addC(lapd, "left_arm_part", 48, 16, 0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 6.5F, 0.0F, true, 0.25F);

		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(48, 80).addBox(-1.0F, -(float)b0 + 1.0F, -1.0F, 2.0F, (float)b0, 2.0F);
		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(48, 80).mirror().addBox(-1.0F, -(float)b0 + 1.0F, -1.0F, 2.0F, (float)b0, 2.0F);
		PartDefinition l1apd = pd.addOrReplaceChild("leg_1a", cubelistbuilder, PartPose.offset(-3.0F, f - 21.0F, -0.5F));
		PartDefinition l2apd = pd.addOrReplaceChild("leg_2a", cubelistbuilder1, PartPose.offset(3.0F, f - 21.0F, -0.5F));
		PartDefinition l3apd = pd.addOrReplaceChild("leg_3a", cubelistbuilder, PartPose.offset(-3.5F, f - 21.0F, 1.5F));
		PartDefinition l4apd = pd.addOrReplaceChild("leg_4a", cubelistbuilder1, PartPose.offset(3.5F, f - 21.0F, 1.5F));
		PartDefinition l5apd = pd.addOrReplaceChild("leg_5a", cubelistbuilder, PartPose.offset(-3.0F, f - 21.0F, 3.5F));
		PartDefinition l6apd = pd.addOrReplaceChild("leg_6a", cubelistbuilder1, PartPose.offset(3.0F, f - 21.0F, 3.5F));
		CubeListBuilder cubelistbuilder2 = CubeListBuilder.create().texOffs(48, 88).addBox(-1.0F, -(float)b1 + 1.0F, -1.0F, 2.0F, (float)b1, 2.0F, new CubeDeformation(0.125F));
		CubeListBuilder cubelistbuilder3 = CubeListBuilder.create().texOffs(48, 88).mirror().addBox(-1.0F, -(float)b1 + 1.0F, -1.0F, 2.0F, (float)b1, 2.0F, new CubeDeformation(0.125F));
		PartPose pp = PartPose.offset(0.0F, -(float)b0 + 0.5F, 0.0F);
		PartDefinition l1bpd = l1apd.addOrReplaceChild("leg_1b", cubelistbuilder2, pp);
		PartDefinition l2bpd = l2apd.addOrReplaceChild("leg_2b", cubelistbuilder3, pp);
		PartDefinition l3bpd = l3apd.addOrReplaceChild("leg_3b", cubelistbuilder2, pp);
		PartDefinition l4bpd = l4apd.addOrReplaceChild("leg_4b", cubelistbuilder3, pp);
		PartDefinition l5bpd = l5apd.addOrReplaceChild("leg_5b", cubelistbuilder2, pp);
		PartDefinition l6bpd = l6apd.addOrReplaceChild("leg_6b", cubelistbuilder3, pp);
		CubeListBuilder cubelistbuilder4 = CubeListBuilder.create().texOffs(48, 104).addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b2, 2.0F);
		CubeListBuilder cubelistbuilder5 = CubeListBuilder.create().texOffs(48, 104).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b2, 2.0F);
		PartPose pp1 = PartPose.offset(0.0F, -(float)b1 + 1.0F, 0.0F);
		PartDefinition l1cpd = l1bpd.addOrReplaceChild("leg_1c", cubelistbuilder4, pp1);
		PartDefinition l2cpd = l2bpd.addOrReplaceChild("leg_2c", cubelistbuilder5, pp1);
		PartDefinition l3cpd = l3bpd.addOrReplaceChild("leg_3c", cubelistbuilder4, pp1);
		PartDefinition l4cpd = l4bpd.addOrReplaceChild("leg_4c", cubelistbuilder5, pp1);
		PartDefinition l5cpd = l5bpd.addOrReplaceChild("leg_5c", cubelistbuilder4, pp1);
		PartDefinition l6cpd = l6bpd.addOrReplaceChild("leg_6c", cubelistbuilder5, pp1);
		CubeListBuilder cubelistbuilder6 = CubeListBuilder.create().texOffs(56, 80).addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b3, 2.0F, new CubeDeformation(-0.125F));
		CubeListBuilder cubelistbuilder7 = CubeListBuilder.create().texOffs(56, 80).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b3, 2.0F, new CubeDeformation(-0.125F));
		PartPose pp2 = PartPose.offset(0.0F, (float)b2 - 0.5F, 0.0F);
		PartDefinition l1dpd = l1cpd.addOrReplaceChild("leg_1d", cubelistbuilder6, pp2);
		PartDefinition l2dpd = l2cpd.addOrReplaceChild("leg_2d", cubelistbuilder7, pp2);
		PartDefinition l3dpd = l3cpd.addOrReplaceChild("leg_3d", cubelistbuilder6, pp2);
		PartDefinition l4dpd = l4cpd.addOrReplaceChild("leg_4d", cubelistbuilder7, pp2);
		PartDefinition l5dpd = l5cpd.addOrReplaceChild("leg_5d", cubelistbuilder6, pp2);
		PartDefinition l6dpd = l6cpd.addOrReplaceChild("leg_6d", cubelistbuilder7, pp2);
		CubeListBuilder cubelistbuilder8 = CubeListBuilder.create().texOffs(64, 80).addBox(-0.5F, -0.5F, -0.5F, 1.0F, (float)b4, 1.0F);
		CubeListBuilder cubelistbuilder9 = CubeListBuilder.create().texOffs(64, 80).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, (float)b4, 1.0F);
		PartPose pp3 = PartPose.offset(0.0F, (float)b3 - 1.0F, 0.0F);
		l1dpd.addOrReplaceChild("leg_1e", cubelistbuilder8, pp3);
		l2dpd.addOrReplaceChild("leg_2e", cubelistbuilder9, pp3);
		l3dpd.addOrReplaceChild("leg_3e", cubelistbuilder8, pp3);
		l4dpd.addOrReplaceChild("leg_4e", cubelistbuilder9, pp3);
		l5dpd.addOrReplaceChild("leg_5e", cubelistbuilder8, pp3);
		l6dpd.addOrReplaceChild("leg_6e", cubelistbuilder9, pp3);
		return LayerDefinition.create(md, 128, 128);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.xRot = headPitch * ((float)Math.PI / 180.0F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180.0F);

		float f = 12.0F - (Mth.sin(ageInTicks * 0.09F) * 0.5F);
		this.head.y = f;
		this.body.y = f;
		this.rightArm.y = f - 2.0F;
		this.leftArm.y = f - 2.0F;
		f = -1.0F - (Mth.sin(ageInTicks * 0.09F) * 0.25F);
		this.body2.y = f;
		this.body.xRot = -((float)Math.PI / 18.0F);
		this.bust.xRot = ((float)Math.PI / 4.0F) - ((float)Math.PI / 18.0F);

		boolean flag = entityIn.getFallFlyingTicks() > 4;
		float f1 = 1.0F;

		if (flag)
		{
			f1 = (float)entityIn.getDeltaMovement().lengthSqr();
			f1 = f1 / 0.2F;
			f1 = f1 * f1 * f1;
		}

		if (f1 < 1.0F)
		{
			f1 = 1.0F;
		}

		this.rightArm.xRot = -((float)Math.PI / 18.0F);
		this.leftArm.xRot = -((float)Math.PI / 18.0F);
		this.rightArm.xRot += Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.1F / f1;
		this.leftArm.xRot += Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.1F / f1;

		if (entityIn.isAggressive())
		{
			float f2 = Mth.sin(this.attackTime * (float)Math.PI);
			float f3 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
			this.rightArm.yRot = -(0.1F - f2 * 0.4F);
			this.leftArm.yRot = 0.1F - f2 * 0.4F;
			this.rightArm.xRot -= f2 * 1.0F - f3 * 0.3F;
			this.leftArm.xRot -= f2 * 1.0F - f3 * 0.3F;
		}

		this.rightArm.zRot = (float)Math.PI * 5.0F / 6.0F;
		this.leftArm.zRot = -((float)Math.PI * 5.0F / 6.0F);
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.06F) * 0.06F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.06F) * 0.06F;

		this.bodyPart1.xRot = ((float)Math.PI / 30.0F);
		this.bodyPart2.xRot = ((float)Math.PI / 30.0F);

		this.body2.xRot = ((float)Math.PI / 8.0F);
		this.body2.xRot -= Mth.cos(limbSwing * 0.45F) * 0.2F * limbSwingAmount;
		this.body2Part1.xRot = -((float)Math.PI / 4.0F);
		this.body2Part1.xRot += Mth.cos(ageInTicks * 0.06F) * 0.09F;
		this.body2Part1.xRot += Mth.cos(limbSwing * 0.45F) * 0.6F * limbSwingAmount;

		if (this.body2Part1.xRot > -((float)Math.PI / 5.0F))
		{
			this.body2Part1.xRot = -((float)Math.PI / 5.0F);
		}

		this.body2Part2.xRot = -((float)Math.PI / 5.0F);
		this.body2Part2.xRot += Mth.cos(limbSwing * 0.45F + (float)Math.PI / 4.0F) * 0.12F * limbSwingAmount;
		this.body2Part7.xRot = -((float)Math.PI / 15.0F);

		this.body2Teeth.xRot = 0.0F;
		this.body2Part4.xRot = 0.0F;
		this.body2Eyes.xRot = 0.0F;
		this.body2Part8.xRot = 0.0F;

		float f4 = (float)Math.PI / 4.0F;

		this.leg1A.yRot = -f4 * 0.57F;
		this.leg2A.yRot = f4 * 0.57F;
		this.leg3A.yRot = f4 * 0.15F;
		this.leg4A.yRot = -f4 * 0.15F;
		this.leg5A.yRot = f4 * 0.72F;
		this.leg6A.yRot = -f4 * 0.72F;

		f4 = (float)Math.PI / 16.0F;

		this.leg1A.zRot = -f4;
		this.leg2A.zRot = f4;
		this.leg3A.zRot = -f4;
		this.leg4A.zRot = f4;
		this.leg5A.zRot = -f4;
		this.leg6A.zRot = f4;

		f4 = (float)Math.PI / 3.0F;

		this.leg1B.zRot = -f4;
		this.leg2B.zRot = f4;
		this.leg3B.zRot = -f4;
		this.leg4B.zRot = f4;
		this.leg5B.zRot = -f4;
		this.leg6B.zRot = f4;

		f4 = (float)Math.PI * 4.0F / 7.0F;

		this.leg1C.zRot = f4;
		this.leg2C.zRot = -f4;
		this.leg3C.zRot = f4;
		this.leg4C.zRot = -f4;
		this.leg5C.zRot = f4;
		this.leg6C.zRot = -f4;

		f4 = -((float)Math.PI / 5.0F);

		this.leg1D.zRot = f4;
		this.leg2D.zRot = -f4;
		this.leg3D.zRot = f4;
		this.leg4D.zRot = -f4;
		this.leg5D.zRot = f4;
		this.leg6D.zRot = -f4;

		f4 = -((float)Math.PI / 27.0F);

		this.leg1E.zRot = f4;
		this.leg2E.zRot = -f4;
		this.leg3E.zRot = f4;
		this.leg4E.zRot = -f4;
		this.leg5E.zRot = f4;
		this.leg6E.zRot = -f4;

		if (entityIn.isOnGround())
		{
			float f5 = 0.45F;
			float f6 = -(Mth.cos(limbSwing * f5 * 2.0F) * 0.4F) * limbSwingAmount;
			float f7 = -(Mth.cos(limbSwing * f5 * 2.0F + (float)Math.PI * 4.0F / 3.0F) * 0.4F) * limbSwingAmount;
			float f8 = -(Mth.cos(limbSwing * f5 * 2.0F + ((float)Math.PI * 2.0F / 3.0F)) * 0.4F) * limbSwingAmount;
			float f9 = Math.abs(Mth.sin(limbSwing * f5) * 0.4F) * limbSwingAmount;
			float f10 = Math.abs(Mth.sin(limbSwing * f5 + (float)Math.PI * 4.0F / 3.0F) * 0.4F) * limbSwingAmount;
			float f11 = Math.abs(Mth.sin(limbSwing * f5 + ((float)Math.PI * 2.0F / 3.0F)) * 0.4F) * limbSwingAmount;
			float f12 = Math.abs(Mth.sin(limbSwing * f5 - ((float)Math.PI / 16.0F)) * 0.4F) * limbSwingAmount;
			float f13 = Math.abs(Mth.sin(limbSwing * f5 + (float)Math.PI * 61.0F / 48.0F) * 0.4F) * limbSwingAmount;
			float f14 = Math.abs(Mth.sin(limbSwing * f5 + ((float)Math.PI * 29.0F / 48.0F)) * 0.4F) * limbSwingAmount;
			this.leg1A.yRot += f6;
			this.leg2A.yRot += -f6;
			this.leg3A.yRot += f7;
			this.leg4A.yRot += -f7;
			this.leg5A.yRot += f8;
			this.leg6A.yRot += -f8;
			this.leg1A.zRot += f9 * 0.45F;
			this.leg2A.zRot += -f9 * 0.45F;
			this.leg3A.zRot += f10 * 0.45F;
			this.leg4A.zRot += -f10 * 0.45F;
			this.leg5A.zRot += f11 * 0.45F;
			this.leg6A.zRot += -f11 * 0.45F;
			this.leg1B.zRot += f9 * 0.81F;
			this.leg2B.zRot += -f9 * 0.81F;
			this.leg3B.zRot += f10 * 0.81F;
			this.leg4B.zRot += -f10 * 0.81F;
			this.leg5B.zRot += f11 * 0.81F;
			this.leg6B.zRot += -f11 * 0.81F;
			this.leg1C.zRot += -f9 * 1.2F;
			this.leg2C.zRot += f9 * 1.2F;
			this.leg3C.zRot += -f10 * 1.2F;
			this.leg4C.zRot += f10 * 1.2F;
			this.leg5C.zRot += -f11 * 1.2F;
			this.leg6C.zRot += f11 * 1.2F;
			this.leg1D.zRot += f12 * 0.3F;
			this.leg2D.zRot += -f12 * 0.3F;
			this.leg3D.zRot += f13 * 0.3F;
			this.leg4D.zRot += -f13 * 0.3F;
			this.leg5D.zRot += f14 * 0.3F;
			this.leg6D.zRot += -f14 * 0.3F;
			this.leg1E.zRot += -f12 * 0.27F;
			this.leg2E.zRot += f12 * 0.27F;
			this.leg3E.zRot += -f13 * 0.27F;
			this.leg4E.zRot += f13 * 0.27F;
			this.leg5E.zRot += -f14 * 0.27F;
			this.leg6E.zRot += f14 * 0.27F;
		}

		this.rightTailHair1.xRot = -((float)Math.PI / 15.0F);
		this.leftTailHair1.xRot = -((float)Math.PI / 15.0F);
		this.rightTailHair1.xRot -= Mth.sin(ageInTicks * 0.03F) * 0.06F;
		this.leftTailHair1.xRot -= Mth.sin(ageInTicks * 0.03F + (float)Math.PI / 6.0F) * 0.06F;
		this.rightTailHair1.zRot = -((float)Math.PI / 21.0F);
		this.leftTailHair1.zRot = (float)Math.PI / 21.0F;
		this.rightTailHair1.zRot -= Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 2.0F) * 0.06F;
		this.leftTailHair1.zRot += Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 2.0F) * 0.06F;
		this.rightTailHair2.xRot = -((float)Math.PI / 12.0F);
		this.leftTailHair2.xRot = -((float)Math.PI / 12.0F);
		this.rightTailHair2.zRot = -((float)Math.PI / 24.0F);
		this.leftTailHair2.zRot = (float)Math.PI / 24.0F;
		this.rightTailHair2.xRot -= Mth.sin(ageInTicks * 0.03F + (float)Math.PI / 12.0F) * 0.045F;
		this.leftTailHair2.xRot -= Mth.sin(ageInTicks * 0.03F + (float)Math.PI / 4.0F) * 0.045F;
		this.rightTailHair3.xRot = -((float)Math.PI / 12.0F);
		this.leftTailHair3.xRot = -((float)Math.PI / 12.0F);
		this.rightTailHair3.xRot -= Mth.sin(ageInTicks * 0.03F + (float)Math.PI / 6.0F) * 0.024F;
		this.leftTailHair3.xRot -= Mth.sin(ageInTicks * 0.03F + (float)Math.PI / 3.0F) * 0.024F;
		this.rightTailHair4.xRot = -((float)Math.PI / 9.0F);
		this.leftTailHair4.xRot = -((float)Math.PI / 9.0F);
		this.rightTailHair4.xRot -= Mth.sin(ageInTicks * 0.03F + (float)Math.PI / 4.0F) * 0.015F;
		this.leftTailHair4.xRot -= Mth.sin(ageInTicks * 0.03F + (float)Math.PI * 5.0F / 12.0F) * 0.015F;
		this.rightTailHair5.yRot = -((float)Math.PI / 3.0F);
		this.leftTailHair5.yRot = (float)Math.PI / 3.0F;

		this.hairBand.xRot = (float)Math.PI / 24.0F;
		this.hairBand.xRot += Mth.sin(ageInTicks * 0.03F) * 0.012F;
	}
}