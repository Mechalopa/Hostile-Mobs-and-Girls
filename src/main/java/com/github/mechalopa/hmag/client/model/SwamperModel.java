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
public class SwamperModel<T extends Mob> extends HierarchicalModel<T>
{
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart headPart;
	private final ModelPart rightFin;
	private final ModelPart leftFin;
	private final ModelPart body;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightArmPart1;
	private final ModelPart leftArmPart1;
	private final ModelPart rightArmPart2;
	private final ModelPart leftArmPart2;
	private final ModelPart rightLeg1;
	private final ModelPart leftLeg1;
	private final ModelPart rightLeg2;
	private final ModelPart leftLeg2;
	private final ModelPart rightLeg3;
	private final ModelPart leftLeg3;
	private final ModelPart rightLeg1Part;
	private final ModelPart leftLeg1Part;
	private final ModelPart rightLeg2Part;
	private final ModelPart leftLeg2Part;
	private final ModelPart rightLeg3Part;
	private final ModelPart leftLeg3Part;

	public SwamperModel(ModelPart modelPart)
	{
		this.root = modelPart;
		this.head = modelPart.getChild("head");
		this.headPart = this.head.getChild("head_part");
		this.rightFin = this.headPart.getChild("right_fin");
		this.leftFin = this.headPart.getChild("left_fin");
		this.body = modelPart.getChild("body");
		this.rightArm = modelPart.getChild("right_arm");
		this.leftArm = modelPart.getChild("left_arm");
		this.rightArmPart1 = this.rightArm.getChild("right_arm_part_1");
		this.leftArmPart1 = this.leftArm.getChild("left_arm_part_1");
		this.rightArmPart2 = this.rightArmPart1.getChild("right_arm_part_2");
		this.leftArmPart2 = this.leftArmPart1.getChild("left_arm_part_2");
		this.rightLeg1 = modelPart.getChild("right_leg_1");
		this.leftLeg1 = modelPart.getChild("left_leg_1");
		this.rightLeg2 = modelPart.getChild("right_leg_2");
		this.leftLeg2 = modelPart.getChild("left_leg_2");
		this.rightLeg3 = modelPart.getChild("right_leg_3");
		this.leftLeg3 = modelPart.getChild("left_leg_3");
		this.rightLeg1Part = this.rightLeg1.getChild("right_leg_1_part");
		this.leftLeg1Part = this.leftLeg1.getChild("left_leg_1_part");
		this.rightLeg2Part = this.rightLeg2.getChild("right_leg_2_part");
		this.leftLeg2Part = this.leftLeg2.getChild("left_leg_2_part");
		this.rightLeg3Part = this.rightLeg3.getChild("right_leg_3_part");
		this.leftLeg3Part = this.leftLeg3.getChild("left_leg_3_part");
	}

	public static LayerDefinition createBodyLayer()
	{
		float f = 14.0F;
		MeshDefinition md = new MeshDefinition();
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 28, -6.0F, -4.0F, -6.0F, 12.0F, 4.0F, 12.0F, 0.0F, f, 0.0F);
		PartDefinition hppd = ModClientUtils.addC(headpd, "head_part", 0, 0, -8.0F, -12.0F, -7.0F, 16.0F, 12.0F, 16.0F, 0.0F, -3.0F, 0.0F);
		ModClientUtils.addC(hppd, "right_fin", 36, 28, -7.0F, 0.0F, -3.0F, 7.0F, 0.0F, 6.0F, -7.75F, -10.0F, 5.0F);
		ModClientUtils.addC(hppd, "left_fin", 36, 28, 0.0F, 0.0F, -3.0F, 7.0F, 0.0F, 6.0F, 7.75F, -10.0F, 5.0F, true);
		ModClientUtils.addC(pd, "body", 0, 44, -4.0F, 0.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, f - 0.5F, 0.0F);

		PartDefinition rapd = ModClientUtils.addC(pd, "right_arm", 32, 44, -1.0F, -0.5F, -1.0F, 2.0F, 7.0F, 2.0F, -6.0F, f - 2.0F, -2.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, "left_arm", 32, 44, -1.0F, -0.5F, -1.0F, 2.0F, 7.0F, 2.0F, 6.0F, f - 2.0F, -2.0F, true);
		PartDefinition rap1pd = ModClientUtils.addC(rapd, "right_arm_part_1", 40, 44, -1.5F, -0.5F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, 6.5F, 0.0F);
		PartDefinition lap1pd = ModClientUtils.addC(lapd, "left_arm_part_1", 40, 44, -1.5F, -0.5F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, 6.5F, 0.0F, true);
		ModClientUtils.addC(rap1pd, "right_arm_part_2", 52, 44, -1.5F, -0.5F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F, 5.5F, 0.0F, 0.25F);
		ModClientUtils.addC(lap1pd, "left_arm_part_2", 52, 44, -1.5F, -0.5F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F, 5.5F, 0.0F, true, 0.25F);

		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 54).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 4.0F, 4.0F);
		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(0, 54).mirror().addBox(-3.0F, 0.0F, -2.0F, 6.0F, 4.0F, 4.0F);
		PartDefinition rl1pd = pd.addOrReplaceChild("right_leg_1", cubelistbuilder, PartPose.offset(-3.75F, f, -4.5F));
		PartDefinition ll1pd = pd.addOrReplaceChild("left_leg_1", cubelistbuilder1, PartPose.offset(3.75F, f, -4.5F));
		PartDefinition rl2pd = pd.addOrReplaceChild("right_leg_2", cubelistbuilder, PartPose.offset(-4.75F, f, -0.5F));
		PartDefinition ll2pd = pd.addOrReplaceChild("left_leg_2", cubelistbuilder1, PartPose.offset(4.75F, f, -0.5F));
		PartDefinition rl3pd = pd.addOrReplaceChild("right_leg_3", cubelistbuilder, PartPose.offset(-4.5F, f, 4.75F));
		PartDefinition ll3pd = pd.addOrReplaceChild("left_leg_3", cubelistbuilder1, PartPose.offset(4.5F, f, 4.75F));
		CubeListBuilder cubelistbuilder2 = CubeListBuilder.create().texOffs(20, 54).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.25F));
		CubeListBuilder cubelistbuilder3 = CubeListBuilder.create().texOffs(20, 54).mirror().addBox(-3.0F, 0.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.25F));
		rl1pd.addOrReplaceChild("right_leg_1_part", cubelistbuilder2, PartPose.offset(0.0F, 3.75F, 0.0F));
		ll1pd.addOrReplaceChild("left_leg_1_part", cubelistbuilder3, PartPose.offset(0.0F, 3.75F, 0.0F));
		rl2pd.addOrReplaceChild("right_leg_2_part", cubelistbuilder2, PartPose.offset(0.0F, 3.75F, 0.0F));
		ll2pd.addOrReplaceChild("left_leg_2_part", cubelistbuilder3, PartPose.offset(0.0F, 3.75F, 0.0F));
		rl3pd.addOrReplaceChild("right_leg_3_part", cubelistbuilder2, PartPose.offset(0.0F, 3.75F, 0.0F));
		ll3pd.addOrReplaceChild("left_leg_3_part", cubelistbuilder3, PartPose.offset(0.0F, 3.75F, 0.0F));
		return LayerDefinition.create(md, 64, 64);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw / (180.0F / (float)Math.PI);
		this.head.xRot = headPitch / (180.0F / (float)Math.PI);
		this.headPart.xRot = -((float)Math.PI / 24.0F);
		this.headPart.xRot += Mth.sin(ageInTicks * 0.067F) * 0.024F;
		ModClientUtils.scaleModelPart(this.headPart, Mth.sin(ageInTicks * 0.105F + (float)Math.PI / 10.0F) * 0.018F);

		this.rightFin.xRot = -((float)Math.PI / 18.0F);
		this.leftFin.xRot = -((float)Math.PI / 18.0F);
		this.rightFin.zRot = (float)Math.PI / 5.0F;
		this.leftFin.zRot = -((float)Math.PI / 5.0F);
		this.rightFin.zRot += Mth.cos(ageInTicks * 0.105F) * 0.045F;
		this.leftFin.zRot -= Mth.cos(ageInTicks * 0.105F) * 0.045F;

		this.body.yRot = 0.0F;

		float f = limbSwing * 0.6662F;
		float f1 = Math.min(0.25F, limbSwingAmount);
		float f2 = 1.0F - limbSwingAmount;
		float f3 = Math.abs(Mth.clamp(this.attackTime, 0.0F, 1.0F) * 2.0F - 1.0F);
		f3 = f3 * f3 * f3;
		float f4 = 1.0F - f3;

		this.rightArm.xRot = ((float)Math.PI / 12.0F) * f3;
		this.leftArm.xRot = ((float)Math.PI / 12.0F) * f3;
		this.rightArm.xRot += Mth.cos(f + (float)Math.PI / 2.0F) * 0.21F * f1 * f3;
		this.leftArm.xRot += Mth.cos(f + (float)Math.PI / 4.0F) * 0.21F * f1 * f3;
		this.rightArm.xRot += -((float)Math.PI * 4.0F / 9.0F) * f4;
		this.leftArm.xRot += -((float)Math.PI * 4.0F / 9.0F) * f4;
		this.rightArm.yRot = ((float)Math.PI / 8.0F) * f4;
		this.leftArm.yRot = -((float)Math.PI / 8.0F) * f4;
		this.rightArm.zRot = ((float)Math.PI / 8.0F) * f3;
		this.leftArm.zRot = -((float)Math.PI / 8.0F) * f3;
		this.rightArm.zRot += ((float)Math.PI / 4.0F) * f4;
		this.leftArm.zRot += -((float)Math.PI / 4.0F) * f4;
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.067F) * 0.021F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.067F + (float)Math.PI / 8.0F) * 0.021F;
		this.rightArmPart1.xRot = Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.018F * f2;
		this.leftArmPart1.xRot = Mth.sin(ageInTicks * 0.045F) * 0.018F * f2;
		this.rightArmPart1.xRot += Mth.cos(f + (float)Math.PI * 5.0F / 8.0F) * 0.14F * f1 * f3;
		this.leftArmPart1.xRot += Mth.cos(f + (float)Math.PI * 3.0F / 8.0F) * 0.14F * f1 * f3;
		this.rightArmPart1.zRot = -((float)Math.PI * 4.0F / 3.0F) * f3;
		this.leftArmPart1.zRot = ((float)Math.PI * 4.0F / 3.0F) * f3;
		this.rightArmPart1.zRot -= Mth.cos(f + (float)Math.PI / 2.0F) * 0.42F * f1 * f3;
		this.leftArmPart1.zRot += Mth.cos(f + (float)Math.PI / 4.0F) * 0.42F * f1 * f3;
		this.rightArmPart1.zRot += -((float)Math.PI / 6.0F) * f4;
		this.leftArmPart1.zRot += ((float)Math.PI / 6.0F) * f4;
		this.rightArmPart1.zRot += Mth.cos(ageInTicks * 0.067F + (float)Math.PI / 6.0F) * 0.024F * f2;
		this.leftArmPart1.zRot -= Mth.cos(ageInTicks * 0.067F + (float)Math.PI * 7.0F / 24.0F) * 0.024F * f2;
		this.rightArmPart2.xRot = Mth.sin(ageInTicks * 0.045F) * 0.015F;
		this.leftArmPart2.xRot = Mth.sin(ageInTicks * 0.045F - (float)Math.PI / 4.0F) * 0.015F;
		this.rightArmPart2.zRot = ((float)Math.PI / 6.0F) * f3;
		this.leftArmPart2.zRot = -((float)Math.PI / 6.0F) * f3;
		this.rightArmPart2.zRot -= Mth.cos(f + (float)Math.PI * 5.0F / 8.0F) * 0.21F * f1 * f3;
		this.leftArmPart2.zRot += Mth.cos(f + (float)Math.PI * 3.0F / 8.0F) * 0.21F * f1 * f3;
		this.rightArmPart2.zRot += -((float)Math.PI / 7.0F) * f4;
		this.leftArmPart2.zRot += ((float)Math.PI / 7.0F) * f4;
		this.rightArmPart2.zRot += Mth.cos(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.024F * f2;
		this.leftArmPart2.zRot -= Mth.cos(ageInTicks * 0.067F + (float)Math.PI * 11.0F / 24.0F) * 0.024F * f2;
		ModClientUtils.scaleModelPart(this.rightArmPart1, Mth.sin(ageInTicks * 0.105F + (float)Math.PI / 2.0F) * 0.015F);
		ModClientUtils.scaleModelPart(this.leftArmPart1, Mth.sin(ageInTicks * 0.105F + (float)Math.PI / 2.0F) * 0.015F);

		this.rightLeg1.xRot = Mth.cos(f + (float)Math.PI) * 1.4F * f1;
		this.leftLeg1.xRot = Mth.cos(f + (float)Math.PI) * 1.4F * f1;
		this.rightLeg2.xRot = Mth.cos(f + (float)Math.PI * 7.0F / 8.0F) * 1.4F * f1;
		this.leftLeg2.xRot = Mth.cos(f + (float)Math.PI * 7.0F / 8.0F) * 1.4F * f1;
		this.rightLeg3.xRot = Mth.cos(f + (float)Math.PI * 3.0F / 4.0F) * 1.4F * f1;
		this.leftLeg3.xRot = Mth.cos(f + (float)Math.PI * 3.0F / 4.0F) * 1.4F * f1;
		this.rightLeg1.yRot = (float)Math.PI / 20.0F;
		this.leftLeg1.yRot = -((float)Math.PI / 20.0F);
		this.rightLeg2.yRot = (float)Math.PI / 20.0F;
		this.leftLeg2.yRot = -((float)Math.PI / 20.0F);
		this.rightLeg3.yRot = -((float)Math.PI / 24.0F);
		this.leftLeg3.yRot = (float)Math.PI / 24.0F;
		this.rightLeg1Part.xRot = -((float)Math.PI / 18.0F);
		this.leftLeg1Part.xRot = -((float)Math.PI / 18.0F);
		this.rightLeg2Part.xRot = (float)Math.PI / 18.0F;
		this.leftLeg2Part.xRot = (float)Math.PI / 18.0F;
		this.rightLeg3Part.xRot = (float)Math.PI / 15.0F;
		this.leftLeg3Part.xRot = (float)Math.PI / 15.0F;
		this.rightLeg1.xRot += Mth.cos(f + (float)Math.PI) * 0.21F * f1;
		this.leftLeg1.xRot += Mth.cos(f + (float)Math.PI) * 0.21F * f1;
		this.rightLeg2.xRot += Mth.cos(f + (float)Math.PI * 7.0F / 8.0F) * 0.21F * f1;
		this.leftLeg2.xRot += Mth.cos(f + (float)Math.PI * 7.0F / 8.0F) * 0.21F * f1;
		this.rightLeg3.xRot += Mth.cos(f - (float)Math.PI * 3.0F / 4.0F) * 0.21F * f1;
		this.leftLeg3.xRot += Mth.cos(f - (float)Math.PI * 3.0F / 4.0F) * 0.21F * f1;
	}
}