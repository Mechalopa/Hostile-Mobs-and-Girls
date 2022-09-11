package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.FortressKeeperEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FortressKeeperModel<T extends FortressKeeperEntity> extends HierarchicalModel<T>
{
	private final ModelPart root;
	private final ModelPart head;
//	private final ModelPart rightHeadPart;
//	private final ModelPart leftHeadPart;
	private final ModelPart body;
//	private final ModelPart bodyPart1;
//	private final ModelPart bodyPart2;
//	private final ModelPart bodyPart3;
//	private final ModelPart bodyPart4;
//	private final ModelPart bodyPart5Right;
//	private final ModelPart bodyPart5Left;
	private final ModelPart bodyPart6Right;
	private final ModelPart bodyPart6Left;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
//	private final ModelPart rightArmPart1;
//	private final ModelPart leftArmPart1;
//	private final ModelPart rightArmPart2;
//	private final ModelPart leftArmPart2;
//	private final ModelPart rightArmPart3A;
//	private final ModelPart rightArmPart3B;
//	private final ModelPart rightArmPart3C;
//	private final ModelPart rightArmPart3D;
//	private final ModelPart leftArmPart3A;
//	private final ModelPart leftArmPart3B;
//	private final ModelPart leftArmPart3C;
//	private final ModelPart leftArmPart3D;
	private final ModelPart rightArmPart4;
	private final ModelPart leftArmPart4;
	private final ModelPart rightArmPart5A;
	private final ModelPart rightArmPart5B;
	private final ModelPart rightArmPart5C;
	private final ModelPart rightArmPart5D;
	private final ModelPart leftArmPart5A;
	private final ModelPart leftArmPart5B;
	private final ModelPart leftArmPart5C;
	private final ModelPart leftArmPart5D;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
//	private final ModelPart leg1Part;
//	private final ModelPart leg2Part;
//	private final ModelPart leg3Part;
//	private final ModelPart leg4Part;
	private float animationAmount;
	private float animationAmount1;

	public FortressKeeperModel(ModelPart modelPart)
	{
		this.root = modelPart;
		this.head = modelPart.getChild("head");
		this.body = modelPart.getChild("body");
		this.bodyPart6Right = this.body.getChild("body_part_6_right");
		this.bodyPart6Left = this.body.getChild("body_part_6_left");
		this.rightArm = modelPart.getChild("right_arm");
		this.leftArm = modelPart.getChild("left_arm");
		this.rightArmPart4 = this.rightArm.getChild("right_arm_part_4");
		this.leftArmPart4 = this.leftArm.getChild("left_arm_part_4");
		this.rightArmPart5A = this.rightArmPart4.getChild("right_arm_part_5a");
		this.rightArmPart5B = this.rightArmPart4.getChild("right_arm_part_5b");
		this.rightArmPart5C = this.rightArmPart4.getChild("right_arm_part_5c");
		this.rightArmPart5D = this.rightArmPart4.getChild("right_arm_part_5d");
		this.leftArmPart5A = this.leftArmPart4.getChild("left_arm_part_5a");
		this.leftArmPart5B = this.leftArmPart4.getChild("left_arm_part_5b");
		this.leftArmPart5C = this.leftArmPart4.getChild("left_arm_part_5c");
		this.leftArmPart5D = this.leftArmPart4.getChild("left_arm_part_5d");
		this.leg1 = modelPart.getChild("leg_1");
		this.leg2 = modelPart.getChild("leg_2");
		this.leg3 = modelPart.getChild("leg_3");
		this.leg4 = modelPart.getChild("leg_4");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition md = new MeshDefinition();
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 0, -4.0F, -3.0F, -4.0F, 8.0F, 7.0F, 6.0F, 0.0F, -4.0F, -4.0F);
		ModClientUtils.addC(headpd, "right_head_part", 32, 0, -1.0F, -2.0F, -1.0F, 3.0F, 2.0F, 3.0F, -3.5F, 4.5F, -3.5F);
		ModClientUtils.addC(headpd, "left_head_part", 32, 0, -2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 3.0F, 3.5F, 4.5F, -3.5F, true);
		PartDefinition bodypd = ModClientUtils.addC(pd, "body", 0, 16, -8.0F, 0.0F, -5.0F, 16.0F, 12.0F, 10.0F, 0.0F, -9.0F, 0.0F);
		PartDefinition bp1pd = ModClientUtils.addC(bodypd, "body_part_1", 0, 40, -5.0F, 0.0F, -4.0F, 10.0F, 3.0F, 8.0F, 0.0F, 12.0F, 0.0F);
		PartDefinition bp2pd = ModClientUtils.addC(bp1pd, "body_part_2", 0, 52, -3.5F, 0.0F, -2.5F, 7.0F, 6.0F, 5.0F, 0.0F, 3.0F, 0.0F);
		PartDefinition bp3pd = ModClientUtils.addC(bp2pd, "body_part_3", 0, 64, -5.0F, 0.0F, -3.5F, 10.0F, 2.0F, 7.0F, 0.0F, 6.0F, 0.0F);
		ModClientUtils.addC(bp3pd, "body_part_4", 0, 74, -6.0F, 0.0F, -4.5F, 12.0F, 2.0F, 9.0F, 0.0F, 2.0F, 0.0F);
		ModClientUtils.addC(bodypd, "body_part_5_right", 40, 40, -1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 6.0F, -6.0F, 0.0F, 0.0F);
		ModClientUtils.addC(bodypd, "body_part_5_left", 40, 40, -1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 6.0F, 6.0F, 0.0F, 0.0F, true);
		ModClientUtils.addC(bodypd, "body_part_6_right", 40, 48, -2.0F, -8.0F, -1.0F, 4.0F, 11.0F, 4.0F, -4.0F, 5.0F, 5.0F);
		ModClientUtils.addC(bodypd, "body_part_6_left", 40, 48, -2.0F, -8.0F, -1.0F, 4.0F, 11.0F, 4.0F, 4.0F, 5.0F, 5.0F, true);
		PartDefinition rapd = ModClientUtils.addC(pd, "right_arm", 64, 0, -9.0F, -2.0F, -5.0F, 10.0F, 12.0F, 10.0F, -10.5F, -4.0F, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, "left_arm", 64, 24, -1.0F, -2.0F, -5.0F, 10.0F, 12.0F, 10.0F, 10.5F, -4.0F, 0.0F);
		PartDefinition rap1pd = ModClientUtils.addC(rapd, "right_arm_part_1", 64, 48, -3.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F, -4.0F, -2.0F, 0.0F);
		PartDefinition lap1pd = ModClientUtils.addC(lapd, "left_arm_part_1", 64, 48, -3.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F, 4.0F, -2.0F, 0.0F, true);
		PartDefinition rap2pd = ModClientUtils.addC(rap1pd, "right_arm_part_2", 64, 56, -4.5F, -4.0F, -4.5F, 9.0F, 4.0F, 9.0F, 0.0F, -2.0F, 0.0F);
		PartDefinition lap2pd = ModClientUtils.addC(lap1pd, "left_arm_part_2", 64, 56, -4.5F, -4.0F, -4.5F, 9.0F, 4.0F, 9.0F, 0.0F, -2.0F, 0.0F, true);
		ModClientUtils.addC(rap2pd, "right_arm_part_3a", 104, 0, -1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 4.0F, -4.5F, -4.0F, 0.0F);
		ModClientUtils.addC(rap2pd, "right_arm_part_3b", 104, 6, -1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 4.0F, 4.5F, -4.0F, 0.0F);
		ModClientUtils.addC(rap2pd, "right_arm_part_3c", 104, 12, -2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, -4.0F, -4.5F);
		ModClientUtils.addC(rap2pd, "right_arm_part_3d", 104, 16, -2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, -4.0F, 4.5F);
		ModClientUtils.addC(lap2pd, "left_arm_part_3a", 104, 0, -1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 4.0F, 4.5F, -4.0F, 0.0F, true);
		ModClientUtils.addC(lap2pd, "left_arm_part_3b", 104, 6, -1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 4.0F, -4.5F, -4.0F, 0.0F, true);
		ModClientUtils.addC(lap2pd, "left_arm_part_3c", 104, 12, -2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, -4.0F, -4.5F, true);
		ModClientUtils.addC(lap2pd, "left_arm_part_3d", 104, 16, -2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, -4.0F, 4.5F, true);
		PartDefinition rap4pd = ModClientUtils.addC(rapd, "right_arm_part_4", 64, 72, -6.0F, 0.0F, -6.0F, 12.0F, 6.0F, 12.0F, -4.0F, 10.0F, 0.0F);
		PartDefinition lap4pd = ModClientUtils.addC(lapd, "left_arm_part_4", 64, 72, -6.0F, 0.0F, -6.0F, 12.0F, 6.0F, 12.0F, 4.0F, 10.0F, 0.0F, true);
		ModClientUtils.addC(rap4pd, "right_arm_part_5a", 96, 48, -1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, -3.0F, 5.0F, -3.0F);
		ModClientUtils.addC(rap4pd, "right_arm_part_5b", 104, 48, -1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 3.0F, 5.0F, -3.0F);
		ModClientUtils.addC(rap4pd, "right_arm_part_5c", 104, 48, -1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, -3.0F, 5.0F, 3.0F);
		ModClientUtils.addC(rap4pd, "right_arm_part_5d", 96, 48, -1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 3.0F, 5.0F, 3.0F);
		ModClientUtils.addC(lap4pd, "left_arm_part_5a", 96, 48, -1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 3.0F, 5.0F, -3.0F, true);
		ModClientUtils.addC(lap4pd, "left_arm_part_5b", 104, 48, -1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, -3.0F, 5.0F, -3.0F, true);
		ModClientUtils.addC(lap4pd, "left_arm_part_5c", 104, 48, -1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 3.0F, 5.0F, 3.0F, true);
		ModClientUtils.addC(lap4pd, "left_arm_part_5d", 96, 48, -1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, -3.0F, 5.0F, 3.0F, true);
		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(40, 64).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 3.0F, 5.0F);
		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(40, 64).mirror().addBox(-2.5F, -1.0F, -2.5F, 5.0F, 3.0F, 5.0F);
		PartDefinition l1pd = pd.addOrReplaceChild("leg_1", cubelistbuilder, PartPose.offset(-3.5F, 16.25F, 3.5F));
		PartDefinition l2pd = pd.addOrReplaceChild("leg_2", cubelistbuilder1, PartPose.offset(3.5F, 16.25F, 3.5F));
		PartDefinition l3pd = pd.addOrReplaceChild("leg_3", cubelistbuilder, PartPose.offset(-3.5F, 16.25F, -3.5F));
		PartDefinition l4pd = pd.addOrReplaceChild("leg_4", cubelistbuilder1, PartPose.offset(3.5F, 16.25F, -3.5F));
		CubeListBuilder cubelistbuilder2 = CubeListBuilder.create().texOffs(42, 72).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F);
		CubeListBuilder cubelistbuilder3 = CubeListBuilder.create().texOffs(42, 72).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F);
		PartPose pp = PartPose.offset(0.0F, 2.0F, 0.0F);
		l1pd.addOrReplaceChild("leg_1_part", cubelistbuilder2, pp);
		l2pd.addOrReplaceChild("leg_2_part", cubelistbuilder3, pp);
		l3pd.addOrReplaceChild("leg_3_part", cubelistbuilder2, pp);
		l4pd.addOrReplaceChild("leg_4_part", cubelistbuilder3, pp);
		return LayerDefinition.create(md, 128, 128);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.animationAmount = Mth.clamp(entityIn.getAttackAnimationScale(partialTick), 0.0F, 1.0F);
		this.animationAmount1 = Mth.clamp(entityIn.getAttackHandChangeAnimationScale(partialTick), 0.0F, 1.0F);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw / (180.0F / (float)Math.PI);
		this.head.xRot = headPitch / (180.0F / (float)Math.PI);

		this.bodyPart6Right.xRot = -((float)Math.PI / 21.0F);
		this.bodyPart6Left.xRot = -((float)Math.PI / 21.0F);

		this.leg1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
		this.leg2.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.2F * limbSwingAmount;
		this.leg3.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.2F * limbSwingAmount;
		this.leg4.xRot = Mth.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;

		float f = 0.09F;

		this.leg1.zRot = f;
		this.leg2.zRot = -f;
		this.leg3.zRot = f;
		this.leg4.zRot = -f;

		float f1 = entityIn.getMainArm() == HumanoidArm.RIGHT ? (1.0F - animationAmount1) : animationAmount1;
		float f2 = entityIn.getMainArm() != HumanoidArm.RIGHT ? (1.0F - animationAmount1) : animationAmount1;
		float f3 = 1.0F - (this.animationAmount * f1);
		float f4 = 1.0F - (this.animationAmount * f2);

		this.rightArm.xRot = Mth.sin(limbSwing * 0.6662F) * 1.0F * limbSwingAmount * f3;
		this.leftArm.xRot = Mth.sin(limbSwing * 0.6662F + (float)Math.PI) * 1.0F * limbSwingAmount * f4;
		this.rightArm.yRot = 0.0F;
		this.leftArm.yRot = 0.0F;
		this.rightArm.zRot = (Mth.cos(ageInTicks * 0.06F) * 0.03F + 0.15F) * f3;
		this.leftArm.zRot = -(Mth.cos(ageInTicks * 0.06F) * 0.03F + 0.15F) * f4;

		this.rightArm.y = -4.0F;
		this.rightArm.y += Mth.sin(ageInTicks * 0.075F + (float)Math.PI / 2.0F) * 1.0F * f3;
		this.leftArm.y = -4.0F;
		this.leftArm.y += Mth.sin(ageInTicks * 0.075F + (float)Math.PI * 3.0F / 2.0F) * 1.0F * f4;
		this.rightArm.x = -10.5F;
		this.leftArm.x = 10.5F;
		this.rightArm.z = 0.0F;
		this.leftArm.z = 0.0F;

		this.rightArmPart4.yRot = (ageInTicks * 0.04F) % (180.0F / (float)Math.PI);
		this.leftArmPart4.yRot = -((ageInTicks * 0.04F) % (180.0F / (float)Math.PI));

		if (this.animationAmount > 0.0F)
		{
			this.rightArm.y += 10.0F * this.animationAmount * f1;
			this.leftArm.y += 10.0F * this.animationAmount * f2;
			this.rightArm.y += 3.0F * this.animationAmount * f2;
			this.leftArm.y += 3.0F * this.animationAmount * f1;
			this.rightArm.y -= Mth.sin(this.animationAmount * (float)Math.PI) * 4.0F * f1;
			this.leftArm.y -= Mth.sin(this.animationAmount * (float)Math.PI) * 4.0F * f2;
			this.rightArm.x += 5.5F * this.animationAmount * f1;
			this.leftArm.x -= 5.5F * this.animationAmount * f2;
			this.rightArm.x += 2.0F * this.animationAmount * f2;
			this.leftArm.x -= 2.0F * this.animationAmount * f1;
			this.rightArm.z += Mth.sin(this.animationAmount * (float)Math.PI) * 1.0F * f1;
			this.leftArm.z += Mth.sin(this.animationAmount * (float)Math.PI) * 1.0F * f2;
			this.rightArm.z += 7.0F * this.animationAmount * f1;
			this.leftArm.z += 7.0F * this.animationAmount * f2;
			this.rightArm.z += 1.0F * this.animationAmount * f2;
			this.leftArm.z += 1.0F * this.animationAmount * f1;
			this.rightArm.xRot -= ((float)Math.PI / 2.0F) * this.animationAmount * f1;
			this.leftArm.xRot -= ((float)Math.PI / 2.0F) * this.animationAmount * f2;
			this.rightArm.xRot += Mth.sin(this.animationAmount * (float)Math.PI) * ((float)Math.PI / 5.0F) * f1;
			this.leftArm.xRot += Mth.sin(this.animationAmount * (float)Math.PI) * ((float)Math.PI / 5.0F) * f2;
			this.rightArm.zRot += (Mth.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) * this.animationAmount * f1;
			this.leftArm.zRot -= (Mth.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) * this.animationAmount * f2;
			this.rightArm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.025F * this.animationAmount * f1;
			this.leftArm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.025F * this.animationAmount * f2;
		}
		else if (this.attackTime > 0.0F)
		{
			float f5 = this.attackTime;
			float f6 = Mth.sin(Mth.sqrt(f5) * ((float)Math.PI * 2.0F)) * 0.2F;
			this.rightArm.x = -(Mth.cos(f6) * 4.5F + 6.0F);
			this.leftArm.x = Mth.cos(f6) * 4.5F + 6.0F;
			this.rightArm.z += Mth.sin(f6) * 5.0F;
			this.leftArm.z += Mth.sin(f6) * 5.0F;
			f5 = 1.0F - this.attackTime;
			f5 = f5 * f5;
			f5 = f5 * f5;
			f5 = 1.0F - f5;
			float f7 = Mth.sin(f5 * (float)Math.PI);
			float f8 = Mth.sin(this.attackTime * (float)Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
			this.rightArm.xRot = (float)((double)this.rightArm.xRot - ((double)f7 * 1.5D + (double)f8));
			this.leftArm.xRot = (float)((double)this.leftArm.xRot - ((double)f7 * 1.5D + (double)f8));
			this.rightArm.yRot += f6 * 2.5F;
			this.leftArm.yRot -= f6 * 2.5F;
			this.rightArm.zRot += Mth.sin(this.attackTime * (float)Math.PI) * -0.3F;
			this.leftArm.zRot += Mth.sin(this.attackTime * (float)Math.PI) * 0.3F;
			this.rightArm.z -= Mth.sin(this.attackTime * (float)Math.PI) * 6.0F;
			this.leftArm.z -= Mth.sin(this.attackTime * (float)Math.PI) * 6.0F;
			this.rightArm.y += Mth.sin(this.attackTime * (float)Math.PI) * 4.0F;
			this.leftArm.y += Mth.sin(this.attackTime * (float)Math.PI) * 4.0F;
		}

		this.rightArmPart5A.y = Mth.sin(ageInTicks * 0.081F) * 0.5F + 5.5F;
		this.rightArmPart5B.y = Mth.sin(ageInTicks * 0.081F + (float)Math.PI / 5.0F) * 0.5F + 5.5F;
		this.rightArmPart5C.y = Mth.sin(ageInTicks * 0.081F + (float)Math.PI * 2.0F / 5.0F) * 0.5F + 5.5F;
		this.rightArmPart5D.y = Mth.sin(ageInTicks * 0.081F + (float)Math.PI * 3.0F / 5.0F) * 0.5F + 5.5F;
		this.leftArmPart5A.y = Mth.sin(ageInTicks * 0.081F) * 0.5F + 5.5F;
		this.leftArmPart5B.y = Mth.sin(ageInTicks * 0.081F + (float)Math.PI / 5.0F) * 0.5F + 5.5F;
		this.leftArmPart5C.y = Mth.sin(ageInTicks * 0.081F + (float)Math.PI * 2.0F / 5.0F) * 0.5F + 5.5F;
		this.leftArmPart5D.y = Mth.sin(ageInTicks * 0.081F + (float)Math.PI * 3.0F / 5.0F) * 0.5F + 5.5F;
	}

	public void translateToHand(HumanoidArm hand, PoseStack poseStack)
	{
		this.getArm(hand).translateAndRotate(poseStack);
	}

	protected ModelPart getArm(HumanoidArm hand)
	{
		return hand == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
	}
}