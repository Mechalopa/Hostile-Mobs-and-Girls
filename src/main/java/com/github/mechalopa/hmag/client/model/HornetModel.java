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
public class HornetModel<T extends AbstractFlyingMonsterEntity> extends HumanoidModel<T>
{
	private ModelPart rightFeelerPart1;
	private ModelPart rightFeelerPart2;
	private ModelPart leftFeelerPart1;
	private ModelPart leftFeelerPart2;
	private ModelPart bust;
	private ModelPart rightWingA;
	private ModelPart leftWingA;
	private ModelPart rightWingB;
	private ModelPart leftWingB;
	private ModelPart rightArmAPart1;
	private ModelPart rightArmAPart2;
	private ModelPart leftArmAPart1;
	private ModelPart leftArmAPart2;
	private ModelPart rightArmB;
	private ModelPart rightArmBPart1;
	private ModelPart rightArmBPart2;
	private ModelPart leftArmB;
	private ModelPart leftArmBPart1;
	private ModelPart leftArmBPart2;
	private ModelPart rightLegPart1;
	private ModelPart rightLegPart2;
	private ModelPart rightLegPart3;
	private ModelPart rightLegPart4;
	private ModelPart leftLegPart1;
	private ModelPart leftLegPart2;
	private ModelPart leftLegPart3;
	private ModelPart leftLegPart4;
	private ModelPart thorax;
	private ModelPart abdomenPart1;
	private ModelPart abdomenPart2;
	private ModelPart abdomenPart3;
	private ModelPart abdomenPart4;
	private ModelPart abdomenPart5;
	private ModelPart abdomenPart6;
	private ModelPart abdomenPart7;
	private ModelPart abdomenPart8;
	private ModelPart abdomenPart9;
	private ModelPart needle;

	public HornetModel(ModelPart modelPart)
	{
		super(modelPart);
		this.rightFeelerPart1 = this.head.getChild("right_feeler_part_1");
		this.rightFeelerPart2 = this.rightFeelerPart1.getChild("right_feeler_part_2");
		this.leftFeelerPart1 = this.head.getChild("left_feeler_part_1");
		this.leftFeelerPart2 = this.leftFeelerPart1.getChild("left_feeler_part_2");
		this.bust = this.body.getChild("bust");
		this.rightWingA = this.body.getChild("right_wing_a");
		this.leftWingA = this.body.getChild("left_wing_a");
		this.rightWingB = this.body.getChild("right_wing_b");
		this.leftWingB = this.body.getChild("left_wing_b");
		this.rightArmAPart1 = this.rightArm.getChild("right_arm_a_part_1");
		this.rightArmAPart2 = this.rightArmAPart1.getChild("right_arm_a_part_2");
		this.leftArmAPart1 = this.leftArm.getChild("left_arm_a_part_1");
		this.leftArmAPart2 = this.leftArmAPart1.getChild("left_arm_a_part_2");
		this.rightArmB = modelPart.getChild("right_arm_b");
		this.rightArmBPart1 = this.rightArmB.getChild("right_arm_b_part_1");
		this.rightArmBPart2 = this.rightArmBPart1.getChild("right_arm_b_part_2");
		this.leftArmB = modelPart.getChild("left_arm_b");
		this.leftArmBPart1 = this.leftArmB.getChild("left_arm_b_part_1");
		this.leftArmBPart2 = this.leftArmBPart1.getChild("left_arm_b_part_2");
		this.rightLegPart1 = this.rightLeg.getChild("right_leg_part_1");
		this.rightLegPart2 = this.rightLegPart1.getChild("right_leg_part_2");
		this.rightLegPart3 = this.rightLegPart2.getChild("right_leg_part_3");
		this.rightLegPart4 = this.rightLegPart3.getChild("right_leg_part_4");
		this.leftLegPart1 = this.leftLeg.getChild("left_leg_part_1");
		this.leftLegPart2 = this.leftLegPart1.getChild("left_leg_part_2");
		this.leftLegPart3 = this.leftLegPart2.getChild("left_leg_part_3");
		this.leftLegPart4 = this.leftLegPart3.getChild("left_leg_part_4");
		this.thorax = this.body.getChild("thorax");
		this.abdomenPart1 = this.thorax.getChild("abdomen_part_1");
		this.abdomenPart2 = this.abdomenPart1.getChild("abdomen_part_2");
		this.abdomenPart3 = this.abdomenPart2.getChild("abdomen_part_3");
		this.abdomenPart4 = this.abdomenPart3.getChild("abdomen_part_4");
		this.abdomenPart5 = this.abdomenPart4.getChild("abdomen_part_5");
		this.abdomenPart6 = this.abdomenPart5.getChild("abdomen_part_6");
		this.abdomenPart7 = this.abdomenPart6.getChild("abdomen_part_7");
		this.abdomenPart8 = this.abdomenPart7.getChild("abdomen_part_8");
		this.abdomenPart9 = this.abdomenPart8.getChild("abdomen_part_9");
		this.needle = this.abdomenPart9.getChild("needle");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = HumanoidModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = ModClientUtils.addC(pd, cd, "head", 0, 0, -3.0F, -6.0F - 1.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F, 1.0F);
		PartDefinition rfp1pd = ModClientUtils.addC(headpd, cd, "right_feeler_part_1", 40, 32, -0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, -2.0F, -7.5F, -1.5F);
		ModClientUtils.addC(rfp1pd, cd, "right_feeler_part_2", 40, 40, -1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, -4.75F, -0.25F, -0.25F);
		PartDefinition lfp1pd = ModClientUtils.addC(headpd, cd, "left_feeler_part_1", 40, 32, -0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 2.0F, -7.5F, -1.5F, true);
		ModClientUtils.addC(lfp1pd, cd, "left_feeler_part_2", 40, 40, -1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, -4.75F, -0.25F, true, -0.25F);
		PartDefinition bodypd = ModClientUtils.addC(pd, cd, "body", 16, 16, -3.0F, 0.0F, -1.5F, 6.0F, 8.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "bust", 0, 32, -3.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, 0.0F, 3.5F, -1.1F, -0.001F);
		ModClientUtils.addC(bodypd, cd, "right_wing_a", 24, 96, -16.0F, -8.0F, 0.0F, 16.0F, 8.0F, 1.0F, -1.5F, 4.0F, 1.5F);
		ModClientUtils.addC(bodypd, cd, "left_wing_a", 24, 96, 0.0F, -8.0F, 0.0F, 16.0F, 8.0F, 1.0F, 1.5F, 4.0F, 1.5F, true);
		ModClientUtils.addC(bodypd, cd, "right_wing_b", 24, 108, -10.0F, 0.0F, 0.0F, 10.0F, 6.0F, 1.0F, -1.5F, 5.0F, 1.5F);
		ModClientUtils.addC(bodypd, cd, "left_wing_b", 24, 108, 0.0F, 0.0F, 0.0F, 10.0F, 6.0F, 1.0F, 1.5F, 5.0F, 1.5F, true);

		PartDefinition rapd = ModClientUtils.addC(pd, cd, "right_arm", 40, 16, -2.0F, -2.0F, -1.5F, 4.0F, 2.0F, 3.0F, -5.0F, 2.0F, 0.0F);
		PartDefinition raap1pd = ModClientUtils.addC(rapd, cd, "right_arm_a_part_1", 48, 40, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, -0.5F, -0.5F, 0.0F);
		ModClientUtils.addC(raap1pd, cd, "right_arm_a_part_2", 56, 40, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 4.75F, 0.0F, 0.25F);
		PartDefinition lapd = ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.5F, 4.0F, 2.0F, 3.0F, 5.0F, 2.0F, 0.0F, true);
		PartDefinition laap1pd = ModClientUtils.addC(lapd, cd, "left_arm_a_part_1", 48, 40, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.5F, -0.5F, 0.0F, true);
		ModClientUtils.addC(laap1pd, cd, "left_arm_a_part_2", 56, 40, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 4.75F, 0.0F, true, 0.25F);

		PartDefinition rabpd = ModClientUtils.addC(pd, cd, "right_arm_b", 48, 32, -1.0F, -2.0F, -1.5F, 2.0F, 2.0F, 3.0F, -4.0F, 4.5F, 0.0F);
		PartDefinition rabp1pd = ModClientUtils.addC(rabpd, cd, "right_arm_b_part_1", 48, 40, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.5F, -0.5F, 0.0F);
		ModClientUtils.addC(rabp1pd, cd, "right_arm_b_part_2", 56, 40, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 4.75F, 0.0F, 0.25F);
		PartDefinition labpd = ModClientUtils.addC(pd, cd, "left_arm_b", 48, 32, -1.0F, -2.0F, -1.5F, 2.0F, 2.0F, 3.0F, 4.0F, 4.5F, 0.0F, true);
		PartDefinition labp1pd = ModClientUtils.addC(labpd, cd, "left_arm_b_part_1", 48, 40, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, -0.5F, -0.5F, 0.0F, true);
		ModClientUtils.addC(labp1pd, cd, "left_arm_b_part_2", 56, 40, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 4.75F, 0.0F, true, 0.25F);

		PartDefinition rlpd = ModClientUtils.addC(pd, cd, "right_leg", 0, 16, -1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, -1.5F, 6.5F, -2.0F);
		PartDefinition rlp1pd = ModClientUtils.addC(rlpd, cd, "right_leg_part_1", 40, 56, -1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 2.75F, 0.0F);
		PartDefinition rlp2pd = ModClientUtils.addC(rlp1pd, cd, "right_leg_part_2", 40, 64, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 3.75F, 0.0F, 0.25F);
		PartDefinition rlp3pd = ModClientUtils.addC(rlp2pd, cd, "right_leg_part_3", 40, 72, -2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, 4.5F, 0.0F);
		ModClientUtils.addC(rlp3pd, cd, "right_leg_part_4", 40, 80, -1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, 0.0F, 2.0F, 0.0F);
		PartDefinition llpd = ModClientUtils.addC(pd, cd, "left_leg", 0, 16, -1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, 1.5F, 6.5F, -2.0F, true);
		PartDefinition llp1pd = ModClientUtils.addC(llpd, cd, "left_leg_part_1", 40, 56, -1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 2.75F, 0.0F, true);
		PartDefinition llp2pd = ModClientUtils.addC(llp1pd, cd, "left_leg_part_2", 40, 64, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 3.75F, 0.0F, true, 0.25F);
		PartDefinition llp3pd = ModClientUtils.addC(llp2pd, cd, "left_leg_part_3", 40, 72, -2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, 4.5F, 0.0F, true);
		ModClientUtils.addC(llp3pd, cd, "left_leg_part_4", 40, 80, -1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, 0.0F, 2.0F, 0.0F, true);

		PartDefinition thoraxpd = ModClientUtils.addC(bodypd, cd, "thorax", 32, 48, -2.0F, -0.25F, -1.0F, 4.0F, 3.0F, 2.0F, 0.0F, 8.0F, 0.0F);
		PartDefinition abp1pd = ModClientUtils.addC(thoraxpd, cd, "abdomen_part_1", 16, 32, -2.5F, 0.0F, -1.5F, 5.0F, 1.0F, 3.0F, 0.0F, 2.0F, 0.25F);
		PartDefinition abp2pd = ModClientUtils.addC(abp1pd, cd, "abdomen_part_2", 0, 40, -3.0F, 0.0F, -2.0F, 6.0F, 1.0F, 4.0F, 0.0F, 1.0F, 0.0F);
		PartDefinition abp3pd = ModClientUtils.addC(abp2pd, cd, "abdomen_part_3", 0, 48, -3.5F, 0.0F, -3.0F, 7.0F, 2.0F, 6.0F, 0.0F, 1.0F, 0.0F);
		PartDefinition abp4pd = ModClientUtils.addC(abp3pd, cd, "abdomen_part_4", 0, 56, -4.0F, 0.0F, -3.5F, 8.0F, 2.0F, 7.0F, 0.0F, 2.0F, 0.0F);
		PartDefinition abp5pd = ModClientUtils.addC(abp4pd, cd, "abdomen_part_5", 0, 68, -4.5F, 0.0F, -4.0F, 9.0F, 4.0F, 8.0F, 0.0F, 2.0F, 0.0F);
		PartDefinition abp6pd = ModClientUtils.addC(abp5pd, cd, "abdomen_part_6", 0, 80, -4.0F, 0.0F, -3.5F, 8.0F, 2.0F, 7.0F, 0.0F, 4.0F, 0.0F);
		PartDefinition abp7pd = ModClientUtils.addC(abp6pd, cd, "abdomen_part_7", 0, 92, -3.0F, 0.0F, -2.5F, 6.0F, 1.0F, 5.0F, 0.0F, 2.0F, 0.5F);
		PartDefinition abp8pd = ModClientUtils.addC(abp7pd, cd, "abdomen_part_8", 0, 100, -2.5F, 0.0F, -2.0F, 5.0F, 1.0F, 4.0F, 0.0F, 1.0F, 0.5F);
		PartDefinition abp9pd = ModClientUtils.addC(abp8pd, cd, "abdomen_part_9", 0, 108, -1.5F, 0.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, 1.0F, 0.75F);
		ModClientUtils.addC(abp9pd, cd, "needle", 32, 56, -1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, 2.0F, 0.0F);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 128);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, entityIn.isCharging(), this.attackTime, ageInTicks);

		float f = -(float)Math.PI / (entityIn.isCharging() ? 4.0F : 15.0F);
		float f1 = Mth.sin(ageInTicks * 0.12F);
		float f2 = Mth.sin(ageInTicks * 0.09F);

		this.rightArm.zRot += f1 * 0.05F + 0.05F;
		this.leftArm.zRot -= f1 * 0.05F + 0.05F;

		this.rightArmAPart1.xRot = ((float)Math.PI / 24.0F);
		this.leftArmAPart1.xRot = ((float)Math.PI / 24.0F);
		this.rightArmAPart2.xRot = -((float)Math.PI / 18.0F);
		this.leftArmAPart2.xRot = -((float)Math.PI / 18.0F);
		this.rightArmAPart2.zRot = ((float)Math.PI / 28.0F);
		this.leftArmAPart2.zRot = -((float)Math.PI / 28.0F);

		this.rightArmB.xRot = f * 0.2F;
		this.leftArmB.xRot = f * 0.2F;
		this.rightArmB.zRot = 0.0F;
		this.leftArmB.zRot = 0.0F;

		if (this.crouching)
		{
			this.rightArmB.xRot += -((float)Math.PI / 5.0F);
			this.leftArmB.xRot += -((float)Math.PI / 5.0F);
		}

		this.rightArmB.zRot += f2 * 0.05F + 0.05F;
		this.leftArmB.zRot -= f2 * 0.05F + 0.05F;
		this.rightArmB.xRot += f1 * 0.06F;
		this.leftArmB.xRot -= f1 * 0.06F;

		this.rightArmBPart1.xRot = -((float)Math.PI / 21.0F);
		this.leftArmBPart1.xRot = -((float)Math.PI / 21.0F);
		this.rightArmBPart1.yRot = -((float)Math.PI / 21.0F);
		this.leftArmBPart1.yRot = ((float)Math.PI / 21.0F);
		this.rightArmBPart2.xRot = -((float)Math.PI * 3.0F / 7.0F);
		this.leftArmBPart2.xRot = -((float)Math.PI * 3.0F / 7.0F);

		this.rightLeg.yRot = ((float)Math.PI / 28.0F);
		this.leftLeg.yRot = -((float)Math.PI / 28.0F);

		if (this.crouching)
		{
			this.rightLeg.zRot = ((float)Math.PI / 15.0F);
			this.leftLeg.zRot = -((float)Math.PI / 15.0F);
			this.rightLeg.xRot = -((float)Math.PI / 8.0F);
			this.leftLeg.xRot = -((float)Math.PI / 8.0F);
			this.rightLeg.xRot = 0.0F;
			this.leftLeg.xRot = 0.0F;
			this.rightLegPart2.xRot = 0.0F;
			this.leftLegPart2.xRot = 0.0F;
			this.rightLegPart4.xRot = -((float)Math.PI / 4.0F);
			this.leftLegPart4.xRot = -((float)Math.PI / 4.0F);
		}
		else
		{
			this.rightLeg.zRot = ((float)Math.PI / 32.0F);
			this.leftLeg.zRot = -((float)Math.PI / 32.0F);
			this.rightLeg.xRot = -((float)Math.PI / 5.0F);
			this.leftLeg.xRot = -((float)Math.PI / 5.0F);
			this.rightLeg.xRot += f1 * 0.045F;
			this.leftLeg.xRot += f1 * 0.045F;

			f1 = Mth.sin(ageInTicks * 0.12F - (float)Math.PI / 4.0F);
			this.rightLegPart2.xRot = ((float)Math.PI / 2.0F);
			this.leftLegPart2.xRot = ((float)Math.PI / 2.0F);
			this.rightLegPart2.xRot -= f1 * 0.075F;
			this.leftLegPart2.xRot -= f1 * 0.075F;
			this.rightLegPart4.xRot = -((float)Math.PI / 3.0F);
			this.leftLegPart4.xRot = -((float)Math.PI / 3.0F);
			this.rightLegPart4.xRot += f1 * 0.072F;
			this.leftLegPart4.xRot += f1 * 0.072F;
		}

		f1 = Mth.sin(ageInTicks * 0.12F - (float)Math.PI / 4.0F);

		this.rightLegPart4.yRot = ((float)Math.PI / 28.0F);
		this.leftLegPart4.yRot = -((float)Math.PI / 28.0F);

		this.rightFeelerPart1.yRot = ((float)Math.PI / 21.0F);
		this.leftFeelerPart1.yRot = -((float)Math.PI / 21.0F);
		this.rightFeelerPart1.xRot = ((float)Math.PI / 8.0F);
		this.leftFeelerPart1.xRot = ((float)Math.PI / 8.0F);
		this.rightFeelerPart1.xRot += f1 * 0.067F;
		this.leftFeelerPart1.xRot += f1 * 0.067F;
		this.rightFeelerPart2.yRot = ((float)Math.PI / 24.0F);
		this.leftFeelerPart2.yRot = -((float)Math.PI / 24.0F);
		this.rightFeelerPart2.xRot = ((float)Math.PI / 3.0F);
		this.leftFeelerPart2.xRot = ((float)Math.PI / 3.0F);
		this.rightFeelerPart2.xRot -= f2 * 0.048F;
		this.leftFeelerPart2.xRot -= f2 * 0.048F;

		this.bust.xRot = ((float)Math.PI / 4.0F) + ((float)Math.PI / 18.0F);

		float f4 = Mth.cos(ageInTicks * 0.2F + (float)Math.PI / 4.0F);
		float f3 = Mth.cos(ageInTicks * 2.1F);
		this.rightWingA.yRot = ((float)Math.PI / 4.0F);
		this.leftWingA.yRot = -((float)Math.PI / 4.0F);
		this.rightWingA.zRot = ((float)Math.PI / 16.0F);
		this.leftWingA.zRot = -((float)Math.PI / 16.0F);
		this.rightWingA.yRot += f3 * 0.42F;
		this.leftWingA.yRot -= f3 * 0.42F;
		this.rightWingA.zRot += f4 * 0.18F;
		this.leftWingA.zRot -= f4 * 0.18F;

		f3 = Mth.cos(ageInTicks * 2.1F + (float)Math.PI / 3.0F);
		this.rightWingB.yRot = ((float)Math.PI / 4.0F);
		this.leftWingB.yRot = -((float)Math.PI / 4.0F);
		this.rightWingB.zRot = -((float)Math.PI / 16.0F);
		this.leftWingB.zRot = ((float)Math.PI / 16.0F);
		this.rightWingB.yRot += f3 * 0.42F;
		this.leftWingB.yRot -= f3 * 0.42F;
		this.rightWingB.zRot -= f4 * 0.18F;
		this.leftWingB.zRot += f4 * 0.18F;

		f2 = Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F);
		this.thorax.xRot = ((float)Math.PI / 21.0F);
		this.thorax.xRot += f2 * 0.036F;
		this.abdomenPart1.xRot = ((float)Math.PI / 6.0F);
		this.abdomenPart1.xRot += Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.12F;
		this.abdomenPart1.xRot -= f1 * 0.067F;
		this.abdomenPart8.xRot = f1 * 0.033F;
		this.abdomenPart9.xRot = f1 * 0.04F;

		this.abdomenPart6.y = 4.0F - 0.5F;
		this.abdomenPart6.y -= Mth.sin(ageInTicks * 0.09F + (float)Math.PI) * 0.2F;

		this.needle.y = 2.0F - 1.0F;
		this.needle.y -= Mth.sin(ageInTicks * 0.09F + (float)Math.PI) * 0.8F;
	}
}