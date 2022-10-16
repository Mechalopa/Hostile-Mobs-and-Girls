package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.OgreEntity;

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
public class OgreModel<T extends OgreEntity> extends HumanoidModel<T>
{
	private ModelPart horn1;
	private ModelPart horn2;
	private ModelPart bodyPart1;
	private ModelPart bodyPart2;
	private ModelPart bodyPart3;
	private ModelPart rightArmPart1;
	private ModelPart leftArmPart1;
	private ModelPart rightArmPart2;
	private ModelPart leftArmPart2;
	private ModelPart rightArmPart3;
	private ModelPart leftArmPart3;
	private ModelPart rightArmPart3A;
	private ModelPart leftArmPart3A;
	private ModelPart rightArmPart3B;
	private ModelPart leftArmPart3B;
	private ModelPart rightLegPart;
	private ModelPart leftLegPart;
	private float swingAmount;

	public OgreModel(ModelPart modelPart)
	{
		super(modelPart);
		this.hat.visible = false;
		this.horn1 = this.head.getChild("horn_1");
		this.horn2 = this.horn1.getChild("horn_2");
		this.bodyPart1 = this.body.getChild("body_part_1");
		this.bodyPart2 = this.body.getChild("body_part_2");
		this.bodyPart3 = this.bodyPart2.getChild("body_part_3");
		this.rightArmPart1 = this.rightArm.getChild("right_arm_part_1");
		this.leftArmPart1 = this.leftArm.getChild("left_arm_part_1");
		this.rightArmPart2 = this.rightArmPart1.getChild("right_arm_part_2");
		this.leftArmPart2 = this.leftArmPart1.getChild("left_arm_part_2");
		this.rightArmPart3 = this.rightArm.getChild("right_arm_part_3");
		this.leftArmPart3 = this.leftArm.getChild("left_arm_part_3");
		this.rightArmPart3A = this.rightArmPart3.getChild("right_arm_part_3a");
		this.leftArmPart3A = this.leftArmPart3.getChild("left_arm_part_3a");
		this.rightArmPart3B = this.rightArmPart3.getChild("right_arm_part_3b");
		this.leftArmPart3B = this.leftArmPart3.getChild("left_arm_part_3b");
		this.rightLegPart = this.rightLeg.getChild("right_leg_part");
		this.leftLegPart = this.leftLeg.getChild("left_leg_part");
	}

	public static LayerDefinition createBodyLayer()
	{
		float f = -5.0F;
		MeshDefinition md = HumanoidModel.createMesh(CubeDeformation.NONE, f);
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 0, -5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 8.0F, 0.0F, f, 0.0F);
		ModClientUtils.addC(pd, "hat", 0, 0, -0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F + f, 0.0F);
		PartDefinition horn1pd = ModClientUtils.addC(headpd, "horn_1", 40, 16, -1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, -7.75F, 0.5F);
		ModClientUtils.addC(horn1pd, "horn_2", 48, 16, -0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, -3.0F, 0.0F);
		PartDefinition bodypd = ModClientUtils.addC(pd, "body", 0, 16, -6.0F, 0.0F, -3.0F, 12.0F, 6.0F, 6.0F, 0.0F, f, 0.0F);
		ModClientUtils.addC(bodypd, "body_part_1", 0, 56, -6.5F, 0.0F, -3.5F, 13.0F, 6.0F, 7.0F, 0.0F, 6.0F, 0.0F);
		PartDefinition bodypart2pd = ModClientUtils.addC(bodypd, "body_part_2", 0, 72, -7.0F, 0.0F, -4.0F, 14.0F, 5.0F, 8.0F, 0.0F, 12.0F, 0.0F);
		ModClientUtils.addC(bodypart2pd, "body_part_3", 0, 88, -7.0F, 0.0F, -4.0F, 14.0F, 3.0F, 8.0F, 0.0F, 5.0F, 0.0F);
		PartDefinition rapd = ModClientUtils.addC(pd, "right_arm", 24, 32, -6.0F, -2.0F, -2.0F, 5.0F, 8.0F, 4.0F, -5.0F, 2.0F + f, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, "left_arm", 24, 32, 1.0F, -2.0F, -2.0F, 5.0F, 8.0F, 4.0F, 5.0F, 2.0F + f, 0.0F, true);
		PartDefinition rap1pd = ModClientUtils.addC(rapd, "right_arm_part_1", 42, 32, -6.5F, 0.0F, -2.5F, 6.0F, 6.0F, 5.0F, 0.0F, 6.0F, 0.0F);
		PartDefinition lap1pd = ModClientUtils.addC(lapd, "left_arm_part_1", 42, 32, 0.5F, 0.0F, -2.5F, 6.0F, 6.0F, 5.0F, 0.0F, 6.0F, 0.0F, true);
		ModClientUtils.addC(rap1pd, "right_arm_part_2", 40, 56, -2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, -6.25F, 2.9F, 0.0F);
		ModClientUtils.addC(lap1pd, "left_arm_part_2", 40, 56, 0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 6.25F, 2.9F, 0.0F, true);
		PartDefinition rap3pd = ModClientUtils.addC(rapd, "right_arm_part_3", 40, 48, -6.5F, 0.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.5F, -2.5F, 0.0F, 0.5F);
		PartDefinition lap3pd = ModClientUtils.addC(lapd, "left_arm_part_3", 40, 48, 0.5F, 0.0F, -3.0F, 6.0F, 2.0F, 6.0F, -0.5F, -2.5F, 0.0F, true, 0.5F);
		ModClientUtils.addC(rap3pd, "right_arm_part_3a", 24, 48, -1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, -3.5F, -0.25F, 0.0F);
		ModClientUtils.addC(lap3pd, "left_arm_part_3a", 24, 48, -1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, 3.5F, -0.25F, 0.0F, true);
		ModClientUtils.addC(rap3pd, "right_arm_part_3b", 24, 48, -1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, -6.25F, 0.75F, 0.0F);
		ModClientUtils.addC(lap3pd, "left_arm_part_3b", 24, 48, -1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, 6.25F, 0.75F, 0.0F, true);
		PartDefinition rlpd = ModClientUtils.addC(pd, "right_leg", 0, 32, -2.5F, 0.0F, -2.0F, 5.0F, 6.0F, 4.0F, -3.5F, 17.0F + f, 0.0F);
		PartDefinition llpd = ModClientUtils.addC(pd, "left_leg", 0, 32, -2.5F, 0.0F, -2.0F, 5.0F, 6.0F, 4.0F, 3.5F, 17.0F + f, 0.0F, true);
		ModClientUtils.addC(rlpd, "right_leg_part", 0, 42, -3.0F, 0.0F, -2.5F, 6.0F, 6.0F, 5.0F, 0.0F, 6.0F, 0.0F);
		ModClientUtils.addC(llpd, "left_leg_part", 0, 42, -3.0F, 0.0F, -2.5F, 6.0F, 6.0F, 5.0F, 0.0F, 6.0F, 0.0F, true);
		return LayerDefinition.create(md, 64, 128);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.swingAmount = entityIn.getAnimationScale(partialTick);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float f = -5.0F;
		this.body.y = f;
		this.head.y = f;
		this.hat.y = f;
		this.rightArm.setPos(-5.0F, 2.0F + f, 0.0F);
		this.leftArm.setPos(5.0F, 2.0F + f, 0.0F);
		this.rightLeg.y = 17.0F + f;
		this.leftLeg.y = 17.0F + f;

		this.rightArm.zRot = (float)Math.PI / 30.0F;
		this.leftArm.zRot = -((float)Math.PI / 30.0F);
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.03F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.03F;
		this.rightArm.yRot = 0.0F;
		this.leftArm.yRot = 0.0F;

		if (this.swingAmount > 0.0F)
		{
			this.rightArm.xRot = this.rightArm.xRot * (1.0F - this.swingAmount);
			this.leftArm.xRot = this.leftArm.xRot * (1.0F - this.swingAmount);
			this.rightArm.xRot += -(this.swingAmount * ((float)Math.PI * 4.0F / 7.0F));
			this.leftArm.xRot += -(this.swingAmount * ((float)Math.PI * 4.0F / 7.0F));
		}

		this.rightArmPart3A.zRot = -((float)Math.PI / 18.0F);
		this.leftArmPart3A.zRot = (float)Math.PI / 18.0F;
		this.rightArmPart3B.zRot = -((float)Math.PI / 3.0F);
		this.leftArmPart3B.zRot = (float)Math.PI / 3.0F;

		this.horn2.xRot = 0.0F;
		this.bodyPart1.xRot = 0.0F;
		this.bodyPart3.xRot = 0.0F;
		this.rightArmPart2.xRot = 0.0F;
		this.leftArmPart2.xRot = 0.0F;
		this.rightLegPart.xRot = 0.0F;
		this.leftLegPart.xRot = 0.0F;
	}
}