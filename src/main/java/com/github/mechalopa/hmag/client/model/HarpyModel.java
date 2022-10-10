package com.github.mechalopa.hmag.client.model;

import java.util.Arrays;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.HarpyEntity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HarpyModel<T extends HarpyEntity> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart bodyPart3;
	private ModelPart bodyPart4;
//	private ModelPart clothPart;
	private ModelPart rightArmPart1;
	private ModelPart leftArmPart1;
	private ModelPart rightArmPart2;
	private ModelPart leftArmPart2;
	private ModelPart rightArmPart3;
	private ModelPart leftArmPart3;
	private ModelPart rightArmPart2Wing;
	private ModelPart leftArmPart2Wing;
	private ModelPart rightArmPart3Wing;
	private ModelPart leftArmPart3Wing;
	private ModelPart rightLegPart1;
	private ModelPart leftLegPart1;
	private ModelPart rightLegPart2;
	private ModelPart leftLegPart2;
	private ModelPart rightLegPart3;
	private ModelPart leftLegPart3;
	private ModelPart rightLegPart4;
	private ModelPart leftLegPart4;
	private ModelPart rightLegPart5[] = new ModelPart[2];
	private ModelPart leftLegPart5[] = new ModelPart[2];
	private ModelPart rightLegPart6;
	private ModelPart leftLegPart6;
	private ModelPart rightLegPart7;
	private ModelPart leftLegPart7;
	private ModelPart tail1;
	private ModelPart tail2[] = new ModelPart[4];
	private ModelPart tail3[] = new ModelPart[4];
	private ModelPart rightHeadWing;
	private ModelPart leftHeadWing;
	private ModelPart hairPart;
	private ModelPart ahoge;
	private float animationAmount;

	public HarpyModel(ModelPart modelPart)
	{
		super(modelPart);
		this.bodyPart3 = this.bodyPart2.getChild("body_part_3");
		this.bodyPart4 = this.bodyPart3.getChild("body_part_4");
//		this.clothPart = this.body.getChild("cloth_part");
		this.rightArmPart1 = this.rightArm.getChild("right_arm_part_1");
		this.leftArmPart1 = this.leftArm.getChild("left_arm_part_1");
		this.rightArmPart2 = this.rightArmPart1.getChild("right_arm_part_2");
		this.leftArmPart2 = this.leftArmPart1.getChild("left_arm_part_2");
		this.rightArmPart3 = this.rightArmPart2.getChild("right_arm_part_3");
		this.leftArmPart3 = this.leftArmPart2.getChild("left_arm_part_3");
		this.rightArmPart2Wing = this.rightArmPart2.getChild("right_arm_part_2_wing");
		this.leftArmPart2Wing = this.leftArmPart2.getChild("left_arm_part_2_wing");
		this.rightArmPart3Wing = this.rightArmPart3.getChild("right_arm_part_3_wing");
		this.leftArmPart3Wing = this.leftArmPart3.getChild("left_arm_part_3_wing");
		this.rightLegPart1 = this.rightLeg.getChild("right_leg_part_1");
		this.leftLegPart1 = this.leftLeg.getChild("left_leg_part_1");
		this.rightLegPart2 = this.rightLegPart1.getChild("right_leg_part_2");
		this.leftLegPart2 = this.leftLegPart1.getChild("left_leg_part_2");
		this.rightLegPart3 = this.rightLegPart1.getChild("right_leg_part_3");
		this.leftLegPart3 = this.leftLegPart1.getChild("left_leg_part_3");
		this.rightLegPart4 = this.rightLegPart3.getChild("right_leg_part_4");
		this.leftLegPart4 = this.leftLegPart3.getChild("left_leg_part_4");
		Arrays.setAll(this.rightLegPart5, (p) -> {
			return this.rightLegPart4.getChild("right_leg_part_5_" + p);
		});
		Arrays.setAll(this.leftLegPart5, (p) -> {
			return this.leftLegPart4.getChild("left_leg_part_5_" + p);
		});
		this.rightLegPart6 = this.rightLegPart4.getChild("right_leg_part_6");
		this.leftLegPart6 = this.leftLegPart4.getChild("left_leg_part_6");
		this.rightLegPart7 = this.rightLegPart3.getChild("right_leg_part_7");
		this.leftLegPart7 = this.leftLegPart3.getChild("left_leg_part_7");
		this.tail1 = this.body.getChild("tail_1");
		Arrays.setAll(this.tail2, (p) -> {
			return this.tail1.getChild("tail_2_" + p);
		});
		Arrays.setAll(this.tail3, (p) -> {
			return this.tail2[p].getChild("tail_3_" + p);
		});
		this.rightHeadWing = this.head.getChild("right_head_wing");
		this.leftHeadWing = this.head.getChild("left_head_wing");
		this.hairPart = this.head.getChild("hair_part");
		this.ahoge = this.head.getChild("ahoge");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F, 6);
		PartDefinition pd = md.getRoot();
		PartDefinition rapd = ModClientUtils.addC(pd, cd, "right_arm", 40, 16, 0.0F, -2.0F, -1.5F, 2.0F, 1.0F, 3.0F, -5.0F, 2.0F, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.5F, 2.0F, 1.0F, 3.0F, 5.0F, 2.0F, 0.0F, true);
		PartDefinition rlpd = ModClientUtils.addC(pd, cd, "right_leg", 0, 16, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, -2.1F, 12.0F, 0.5F);
		PartDefinition llpd = ModClientUtils.addC(pd, cd, "left_leg", 0, 16, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 2.1F, 12.0F, 0.5F, true);
		PartDefinition headpd = pd.getChild("head");
		PartDefinition bodypd = pd.getChild("body");
		PartDefinition bodypart1pd = bodypd.getChild("body_part_1");
		PartDefinition bodypart2pd = ModClientUtils.addC(bodypart1pd, cd, "body_part_2", 32, 40, -3.0F, 0.0F, -1.5F, 6.0F, 1.0F, 3.0F, 0.0F, 2.5F, 0.0F);
		PartDefinition bodypart3pd = ModClientUtils.addC(bodypart2pd, cd, "body_part_3", 32, 48, -3.5F, 0.0F, -2.0F, 7.0F, 2.0F, 4.0F, 0.0F, 0.5F, 0.0F);
		ModClientUtils.addC(bodypart3pd, cd, "body_part_4", 32, 56, -4.0F, 0.0F, -2.5F, 8.0F, 1.0F, 5.0F, 0.0F, 1.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "cloth_part", 0, 76, -3.0F, 0.0F, -1.5F, 6.0F, 1.0F, 3.0F, 0.0F, 6.0F, 0.0F);

		PartDefinition rap1pd = ModClientUtils.addC(rapd, cd, "right_arm_part_1", 40, 20, 0.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.5F);
		PartDefinition lap1pd = ModClientUtils.addC(lapd, cd, "left_arm_part_1", 40, 20, -2.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.5F, true);
		PartDefinition rap2pd = ModClientUtils.addC(rap1pd, cd, "right_arm_part_2", 16, 35, 0.0F, -0.5F, -1.5F, 2.0F, 6.0F, 3.0F, 0.0F, 1.5F, 0.0F, -0.125F);
		PartDefinition lap2pd = ModClientUtils.addC(lap1pd, cd, "left_arm_part_2", 16, 35, -2.0F, -0.5F, -1.5F, 2.0F, 6.0F, 3.0F, 0.0F, 1.5F, 0.0F, true, -0.125F);
		PartDefinition rap3pd = ModClientUtils.addC(rap2pd, cd, "right_arm_part_3", 26, 32, 0.0F, -0.5F, -1.5F, 1.0F, 7.0F, 2.0F, 0.5F, 5.0F, 0.0F);
		PartDefinition lap3pd = ModClientUtils.addC(lap2pd, cd, "left_arm_part_3", 26, 32, -1.0F, -0.5F, -1.5F, 1.0F, 7.0F, 2.0F, -0.5F, 5.0F, 0.0F, true);
		ModClientUtils.addC(rap2pd, cd, "right_arm_part_2_wing", 16, 44, 0.0F, 0.0F, 0.0F, 1.0F, 7.0F, 5.0F, 0.5F, -2.5F, 1.0F);
		ModClientUtils.addC(lap2pd, cd, "left_arm_part_2_wing", 16, 44, -1.0F, 0.0F, 0.0F, 1.0F, 7.0F, 5.0F, -0.5F, -2.5F, 1.0F, true);
		ModClientUtils.addC(rap3pd, cd, "right_arm_part_3_wing", 16, 56, 0.0F, 0.0F, 0.0F, 1.0F, 8.0F, 7.0F, 0.0F, 0.0F, -1.0F, -0.005F);
		ModClientUtils.addC(lap3pd, cd, "left_arm_part_3_wing", 16, 56, -1.0F, 0.0F, 0.0F, 1.0F, 8.0F, 7.0F, 0.0F, 0.0F, -1.0F, true, -0.005F);

		PartDefinition rlp1pd = ModClientUtils.addC(rlpd, cd, "right_leg_part_1", 0, 20, -2.25F, 0.0F, -1.75F, 4.0F, 5.0F, 4.0F, 0.0F, -1.5F, 0.0F);
		PartDefinition llp1pd = ModClientUtils.addC(llpd, cd, "left_leg_part_1", 0, 20, -1.75F, 0.0F, -1.75F, 4.0F, 5.0F, 4.0F, 0.0F, -1.5F, 0.0F, true);
		ModClientUtils.addC(rlp1pd, cd, "right_leg_part_2", 0, 40, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 5.0F, 0.0F);
		ModClientUtils.addC(llp1pd, cd, "left_leg_part_2", 0, 40, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 5.0F, 0.0F, true);
		PartDefinition rlp3pd = ModClientUtils.addC(rlp1pd, cd, "right_leg_part_3", 0, 46, -1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 5.25F, 0.0F, -0.125F);
		PartDefinition llp3pd = ModClientUtils.addC(llp1pd, cd, "left_leg_part_3", 0, 46, -1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 5.25F, 0.0F, true, -0.125F);
		PartDefinition rlp4pd = ModClientUtils.addC(rlp3pd, cd, "right_leg_part_4", 0, 56, -1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 6.25F, 0.0F, 0.0625F);
		PartDefinition llp4pd = ModClientUtils.addC(llp3pd, cd, "left_leg_part_4", 0, 56, -1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 6.25F, 0.0F, true, 0.0625F);
		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F);
		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(0, 60).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F);

		for (int k = 0; k < 2; ++k)
		{
			PartPose pp = PartPose.offset(-1.0F * (k - 0.5F), 0.25F, -0.75F);
			rlp4pd.addOrReplaceChild("right_leg_part_5_" + k, cubelistbuilder, pp);
			PartPose pp1 = PartPose.offset(-1.0F * (0.5F - k), 0.25F, -0.75F);
			llp4pd.addOrReplaceChild("left_leg_part_5_" + k, cubelistbuilder1, pp1);
		}

		ModClientUtils.addC(rlp4pd, cd, "right_leg_part_6", 4, 60, -0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.375F, 0.875F);
		ModClientUtils.addC(llp4pd, cd, "left_leg_part_6", 4, 60, -0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.375F, 0.875F, true);
		ModClientUtils.addC(rlp3pd, cd, "right_leg_part_7", 0, 64, -1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 4.5F, 0.0F, 0.125F);
		ModClientUtils.addC(llp3pd, cd, "left_leg_part_7", 0, 64, -1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 4.5F, 0.0F, true, 0.125F);

		PartDefinition t1pd = ModClientUtils.addC(bodypd, cd, "tail_1", 48, 64, -1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 2.0F, 0.0F, 9.5F, 2.0F);
		CubeListBuilder cubelistbuilder6 = CubeListBuilder.create().texOffs(48, 68).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F);
		CubeListBuilder cubelistbuilder7 = CubeListBuilder.create().texOffs(48, 72).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 1.0F);
		PartPose pp2 = PartPose.offset(0.0F, 2.0F, 0.0F);

		for (int k = 0; k < 4; ++k)
		{
			PartDefinition t2pd = t1pd.addOrReplaceChild("tail_2_" + k, cubelistbuilder6, PartPose.offset(-1.0F * (k - 1.5F), 0.0F, 0.0F));
			t2pd.addOrReplaceChild("tail_3_" + k, cubelistbuilder7, pp2);
		}

		ModClientUtils.addC(headpd, cd, "right_head_wing", 32, 72, 0.0F, -2.0F, -0.25F, 1.0F, 5.0F, 6.0F, -4.5F, -6.0F, -1.5F);
		ModClientUtils.addC(headpd, cd, "left_head_wing", 32, 72, -1.0F, -2.0F, -0.25F, 1.0F, 5.0F, 6.0F, 4.5F, -6.0F, -1.5F, true);
		ModClientUtils.addC(headpd, cd, "hair_part", 0, 72, -4.0F, 0.0F, -1.0F, 8.0F, 3.0F, 1.0F, 0.0F, 0.0F, 4.0F);
		ModClientUtils.addC(headpd, cd, "ahoge", 32, 64, -2.5F, -4.0F, 0.0F, 5.0F, 4.0F, 1.0F, 0.0F, -7.75F, 0.0F, -0.25F);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 128);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.animationAmount = Mth.clamp(entityIn.getAnimationScale(partialTick), 0.0F, 1.0F);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (this.crouching)
		{
			this.body.xRot = 0.5F;
			this.rightLeg.z = 4.0F;
			this.leftLeg.z = 4.0F;
			this.bodyPart3.xRot = 0.0F;
		}
		else
		{
			this.body.xRot = (float)Math.PI * 0.03F;
			this.rightLeg.z = 0.6F;
			this.leftLeg.z = 0.6F;
			this.bodyPart3.xRot = -((float)Math.PI * 0.03F);
		}

		this.bodyPart4.xRot = 0.0F;

		this.rightArm.xRot *= 0.5F;
		this.leftArm.xRot *= 0.5F;
		this.rightArm.zRot = (float)Math.PI * 3.0F / 16.0F;
		this.leftArm.zRot = -((float)Math.PI * 3.0F / 16.0F);

		float f = (Mth.cos(ageInTicks * 0.81F) * ((float)Math.PI / 7.0F) + ((float)Math.PI / 4.0F)) * this.animationAmount;

		this.rightArm.zRot += f * 0.6F;
		this.leftArm.zRot -= f * 0.6F;
		this.rightArm.xRot += Mth.sin(ageInTicks * 0.105F + (float)Math.PI * 0.6F) * 0.072F;
		this.leftArm.xRot -= Mth.sin(ageInTicks * 0.105F) * 0.072F;

		this.rightArmPart2Wing.yRot = -((float)Math.PI * 0.021F);
		this.leftArmPart2Wing.yRot = (float)Math.PI * 0.021F;
		this.rightArmPart3Wing.yRot = 0.0F;
		this.leftArmPart3Wing.yRot = 0.0F;

		this.rightLeg.xRot *= (1.0F - this.animationAmount) * 0.9F;
		this.leftLeg.xRot *= (1.0F - this.animationAmount) * 0.9F;
		this.rightLeg.xRot -= ((float)Math.PI / 15.0F) * this.animationAmount;
		this.leftLeg.xRot -= ((float)Math.PI / 15.0F) * this.animationAmount;

		this.rightLeg.zRot = -((float)Math.PI * 0.01F);
		this.leftLeg.zRot = (float)Math.PI * 0.01F;
		this.rightLeg.zRot += ((float)Math.PI / 15.0F) * this.animationAmount;
		this.leftLeg.zRot -= ((float)Math.PI / 15.0F) * this.animationAmount;

		this.rightArmPart1.xRot = (float)Math.PI / 11.0F;
		this.leftArmPart1.xRot = (float)Math.PI / 11.0F;
		this.rightArmPart1.xRot -= ((float)Math.PI / 12.0F) * this.animationAmount;
		this.leftArmPart1.xRot -= ((float)Math.PI / 12.0F) * this.animationAmount;
		this.rightArmPart1.yRot = (float)Math.PI / 12.0F;
		this.leftArmPart1.yRot = -((float)Math.PI / 12.0F);
		this.rightArmPart1.yRot += ((float)Math.PI / 16.0F) * this.animationAmount;
		this.leftArmPart1.yRot -= ((float)Math.PI / 16.0F) * this.animationAmount;
		this.rightArmPart2.xRot = -((float)Math.PI / 5.0F);
		this.leftArmPart2.xRot = -((float)Math.PI / 5.0F);
		this.rightArmPart2.xRot -= Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.024F;
		this.leftArmPart2.xRot -= Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.024F;
		this.rightArmPart3.xRot += ((float)Math.PI / 6.0F) * this.animationAmount;
		this.leftArmPart3.xRot += ((float)Math.PI / 6.0F) * this.animationAmount;
		this.rightArmPart2.yRot = (float)Math.PI / 7.0F;
		this.leftArmPart2.yRot = -((float)Math.PI / 7.0F);
		this.rightArmPart2.zRot = -((float)Math.PI / 9.0F);
		this.leftArmPart2.zRot = (float)Math.PI / 9.0F;
		this.rightArmPart2.zRot += f * 0.4F;
		this.leftArmPart2.zRot -= f * 0.4F;
		this.rightArmPart3.xRot = (float)Math.PI / 6.0F;
		this.leftArmPart3.xRot = (float)Math.PI / 6.0F;
		this.rightArmPart3.xRot += Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.033F;
		this.leftArmPart3.xRot += Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.033F;
		this.rightArmPart3.xRot -= ((float)Math.PI / 8.0F) * this.animationAmount;
		this.leftArmPart3.xRot -= ((float)Math.PI / 8.0F) * this.animationAmount;

		this.rightLegPart1.xRot = -((float)Math.PI / 14.0F);
		this.leftLegPart1.xRot = -((float)Math.PI / 14.0F);

		this.rightLegPart3.xRot = (float)Math.PI / 8.0F;
		this.leftLegPart3.xRot = (float)Math.PI / 8.0F;
		this.rightLegPart4.xRot = -((float)Math.PI / 25.0F);
		this.leftLegPart4.xRot = -((float)Math.PI / 25.0F);

		float f1 = ((float)Math.PI / 9.0F) * this.animationAmount;

		for (int i = 0; i < this.rightLegPart5.length; ++i)
		{
			this.rightLegPart5[i].xRot = -((float)Math.PI / 3.0F);
			this.rightLegPart5[i].xRot += f1;
			this.rightLegPart5[i].yRot = (float)Math.PI * 0.15F * (i - 0.5F);
			this.rightLegPart5[i].zRot = (float)Math.PI * 0.07F * (i - 0.5F);
		}

		for (int i = 0; i < this.leftLegPart5.length; ++i)
		{
			this.leftLegPart5[i].xRot = -((float)Math.PI / 3.0F);
			this.leftLegPart5[i].xRot += f1;
			this.leftLegPart5[i].yRot = (float)Math.PI * 0.15F * (0.5F - i);
			this.leftLegPart5[i].zRot = (float)Math.PI * 0.07F * (0.5F - i);
		}

		this.rightLegPart6.xRot = (float)Math.PI * 1.0F / 5.0F;
		this.leftLegPart6.xRot = (float)Math.PI * 1.0F / 5.0F;
		this.rightLegPart6.xRot -= f1;
		this.leftLegPart6.xRot -= f1;

		this.rightLegPart2.xRot = 0.0F;
		this.leftLegPart2.xRot = 0.0F;
		this.rightLegPart7.xRot = 0.0F;
		this.leftLegPart7.xRot = 0.0F;

		this.tail1.xRot = (float)Math.PI * 2.0F / 5.0F;
		this.tail1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.021F;
		this.tail1.xRot += (Mth.sin(ageInTicks * 0.405F) * 0.08F + 0.08F) * this.animationAmount;

		for (int i = 0; i < this.tail2.length; ++i)
		{
			this.tail2[i].yRot = (float)Math.PI / 12.0F * (i - 1.5F);
			this.tail2[i].zRot = (float)Math.PI / 6.0F * (i - 1.5F);
			this.tail2[i].xRot = (float)Math.PI / 15.0F;
			this.tail2[i].xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.06F;
			this.tail2[i].xRot += 0.25F * Mth.sin(limbSwing * 1.25F * 0.5F) * limbSwingAmount;
			this.tail3[i].xRot = (float)Math.PI / 6.0F;
			this.tail3[i].xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.09F;
		}

		this.rightHeadWing.xRot = (float)Math.PI / 15.0F;
		this.leftHeadWing.xRot = (float)Math.PI / 15.0F;
		this.rightHeadWing.xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.03F;
		this.leftHeadWing.xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.03F;
		this.rightHeadWing.xRot += 0.15F * Mth.sin(limbSwing * 1.25F * 0.5F) * limbSwingAmount;
		this.leftHeadWing.xRot += 0.15F * Mth.sin(limbSwing * 1.25F * 0.5F) * limbSwingAmount;
		this.rightHeadWing.yRot = -((float)Math.PI / 10.0F);
		this.leftHeadWing.yRot = (float)Math.PI / 10.0F;
		this.rightHeadWing.xRot += Mth.cos(ageInTicks * 0.03F) * 0.06F;
		this.leftHeadWing.xRot -= Mth.cos(ageInTicks * 0.03F) * 0.06F;
		this.rightHeadWing.yRot -= (Mth.cos(ageInTicks * 0.81F + (float)Math.PI / 3.0F) * 0.15F + 0.12F) * this.animationAmount;
		this.leftHeadWing.yRot += (Mth.cos(ageInTicks * 0.81F + (float)Math.PI / 3.0F) * 0.15F + 0.12F) * this.animationAmount;

		this.hairPart.xRot = (float)Math.PI / 12.0F;
		this.hairPart.xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.06F;

		this.ahoge.xRot = -((float)Math.PI / 12.0F);
		this.ahoge.xRot += Mth.cos(ageInTicks * 0.03F + (float)Math.PI) * 0.09F;
	}

	@Override
	protected float getLegRotZ()
	{
		return -((float)Math.PI * 0.004F);
	}
}