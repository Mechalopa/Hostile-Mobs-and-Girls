package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.entity.EnderExecutorEntity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderExecutorModel<T extends EnderExecutorEntity> extends AbstractGirlModel<T>
{
//	private ModelPart rightArmPart1;
//	private ModelPart leftArmPart1;
//	private ModelPart rightArmPart2;
//	private ModelPart leftArmPart2;
//	private ModelPart rightLegPart1;
//	private ModelPart leftLegPart1;
//	private ModelPart rightLegPart2;
//	private ModelPart leftLegPart2;
	private ModelPart bust;
//	private ModelPart skirt1;
//	private ModelPart skirt2;
//	private ModelPart skirt3;
//	private ModelPart skirt4;
//	private ModelPart skirt5;
	private ModelPart rightHair1;
	private ModelPart leftHair1;
	private ModelPart rightHair2;
	private ModelPart leftHair2;
	public boolean creepy;
	public boolean carrying;
	public boolean beamAttacking;
	private float animationAmount;

	public EnderExecutorModel(ModelPart modelPart)
	{
		super(modelPart);
//		this.rightArmPart1 = this.rightArm.getChild("right_arm_part_1");
//		this.leftArmPart1 = this.leftArm.getChild("left_arm_part_1");
//		this.rightArmPart2 = this.rightArm.getChild("right_arm_part_2");
//		this.leftArmPart2 = this.leftArm.getChild("left_arm_part_2");
//		this.rightLegPart1 = this.rightLeg.getChild("right_leg_part_1");
//		this.leftLegPart1 = this.leftLeg.getChild("left_leg_part_1");
//		this.rightLegPart2 = this.rightLeg.getChild("right_leg_part_2");
//		this.leftLegPart2 = this.leftLeg.getChild("left_leg_part_2");
		this.bust = this.body.getChild("bust");
//		this.skirt1 = this.body.getChild("skirt_1");
//		this.skirt2 = this.body.getChild("skirt_2");
//		this.skirt3 = this.skirt2.getChild("skirt_3");
//		this.skirt4 = this.skirt3.getChild("skirt_4");
//		this.skirt5 = this.skirt4.getChild("skirt_5");
		this.rightHair1 = this.head.getChild("right_hair_1");
		this.leftHair1 = this.head.getChild("left_hair_1");
		this.rightHair2 = this.rightHair1.getChild("right_hair_2");
		this.leftHair2 = this.leftHair1.getChild("left_hair_2");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		float f = -14.0F;
		MeshDefinition md = AbstractGirlModel.createMesh(cd, f);
		PartDefinition pd = md.getRoot();
		PartDefinition rapd = ModClientUtils.addC(pd, cd, "right_arm", 40, 16, 0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, -5.0F, 2.0F + f, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 5.0F, 2.0F + f, 0.0F, true);
		ModClientUtils.addC(rapd, cd, "right_arm_part_1", 0, 40, -0.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 3.0F, 0.0F);
		ModClientUtils.addC(lapd, cd, "left_arm_part_1", 0, 40, -2.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 3.0F, 0.0F, true);
		ModClientUtils.addC(rapd, cd, "right_arm_part_2", 0, 40, -0.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 7.0F, 0.0F);
		ModClientUtils.addC(lapd, cd, "left_arm_part_2", 0, 40, -2.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 7.0F, 0.0F, true);
		PartDefinition rlpd = ModClientUtils.addC(pd, cd, "right_leg", 16, 32, -1.0F, 0.0F, -1.0F, 2.0F, 30.0F, 2.0F, -2.0F, 12.0F + f, 0.0F);
		PartDefinition llpd = ModClientUtils.addC(pd, cd, "left_leg", 16, 32, -1.0F, 0.0F, -1.0F, 2.0F, 30.0F, 2.0F, 2.0F, 12.0F + f, 0.0F, true);
		ModClientUtils.addC(rlpd, cd, "right_leg_part_1", 0, 44, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 16.0F, 0.0F);
		ModClientUtils.addC(llpd, cd, "left_leg_part_1", 0, 44, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 16.0F, 0.0F, true);
		ModClientUtils.addC(rlpd, cd, "right_leg_part_2", 0, 44, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 18.0F, 0.0F);
		ModClientUtils.addC(llpd, cd, "left_leg_part_2", 0, 44, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 18.0F, 0.0F, true);
		PartDefinition bodypd = pd.getChild("body");
		ModClientUtils.addC(bodypd, cd, "bust", 0, 32, -3.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, 0.0F, 3.5F, -1.1F, -0.001F);
		ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 64, -3.5F, 0.0F, -2.0F, 7.0F, 2.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		PartDefinition s2pd = ModClientUtils.addC(bodypd, cd, "skirt_2", 24, 64, -4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, 0.0F, 13.0F, 0.0F);
		PartDefinition s3pd = ModClientUtils.addC(s2pd, cd, "skirt_3", 0, 72, -4.5F, 0.0F, -3.0F, 9.0F, 2.0F, 6.0F, 0.0F, 2.0F, 0.0F);
		PartDefinition s4pd = ModClientUtils.addC(s3pd, cd, "skirt_4", 0, 80, -5.0F, 0.0F, -3.5F, 10.0F, 2.0F, 7.0F, 0.0F, 2.0F, 0.0F);
		ModClientUtils.addC(s4pd, cd, "skirt_5", 0, 92, -5.5F, 0.0F, -4.0F, 11.0F, 3.0F, 8.0F, 0.0F, 2.0F, 0.0F);
		PartDefinition headpd = pd.getChild("head");
		PartDefinition rhpd = ModClientUtils.addC(headpd, cd, "right_hair_1", 24, 32, -1.0F, -1.5F, -1.0F, 2.0F, 14.0F, 2.0F, -4.25F, -8.0F, 3.5F);
		PartDefinition lhpd = ModClientUtils.addC(headpd, cd, "left_hair_1", 24, 32, -1.0F, -1.5F, -1.0F, 2.0F, 14.0F, 2.0F, 4.25F, -8.0F, 3.5F, true);
		ModClientUtils.addC(rhpd, cd, "right_hair_2", 32, 32, -1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, 12.5F, 0.0F);
		ModClientUtils.addC(lhpd, cd, "left_hair_2", 32, 32, -1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, 12.5F, 0.0F, true);
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
		this.animationAmount = entityIn.getAttackAnimationScale(partialTick);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.head.visible = true;
		float f = -14.0F;
		this.body.xRot = 0.0F;
		this.body.y = f;
		this.body.z = 0.0F;
		this.rightLeg.xRot = (float)((double)this.rightLeg.xRot * 0.5D);
		this.leftLeg.xRot = (float)((double)this.leftLeg.xRot * 0.5D);
//		this.skirt1.xRot = 0.0F;
//		this.skirt5.xRot = 0.0F;

		float f1 = 0.21F;

		if (this.rightLeg.xRot > f1)
		{
			this.rightLeg.xRot = f1;
		}

		if (this.leftLeg.xRot > f1)
		{
			this.leftLeg.xRot = f1;
		}

		if (this.rightLeg.xRot < -f1)
		{
			this.rightLeg.xRot = -f1;
		}

		if (this.leftLeg.xRot < -f1)
		{
			this.leftLeg.xRot = -f1;
		}

		this.rightLeg.z = 0.0F;
		this.leftLeg.z = 0.0F;
		this.rightLeg.y = 9.0F + f;
		this.leftLeg.y = 9.0F + f;
		this.head.z = 0.0F;
		this.head.y = f;
		this.hat.copyFrom(this.head);

		if (this.creepy)
		{
			float f2 = Mth.sin(this.attackTime * (float)Math.PI);
			float f3 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
			this.rightArm.zRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightArm.yRot = -(5.0F - f2 * 0.6F);
			this.leftArm.yRot = 5.0F - f2 * 0.6F;
			this.rightArm.xRot = -((float)Math.PI / 2.0F);
			this.leftArm.xRot = -((float)Math.PI / 2.0F);
			this.rightArm.xRot -= f2 * 1.2F - f3 * 0.4F;
			this.leftArm.xRot -= f2 * 1.2F - f3 * 0.4F;
			this.rightArm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.25F - 0.6F;
			this.leftArm.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.25F - 0.6F;
			this.rightArm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
			this.leftArm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
		}
		else if (this.carrying)
		{
			if (entityIn.getMainArm() != HumanoidArm.RIGHT)
			{
				this.rightArm.xRot = Mth.sin(ageInTicks * 0.09F) * 0.03F + ((float)Math.PI / 9.0F);
				this.rightArm.zRot = -(Mth.cos(ageInTicks * 0.06F) * 0.125F - ((float)Math.PI / 5.0F));
			}
			else
			{
				this.leftArm.xRot = Mth.sin(ageInTicks * 0.09F) * 0.03F + ((float)Math.PI / 9.0F);
				this.leftArm.zRot = Mth.cos(ageInTicks * 0.06F) * 0.125F - ((float)Math.PI / 5.0F);
			}
		}

		this.bust.xRot = ((float)Math.PI / 4.0F) + ((float)Math.PI / 18.0F);

		this.rightArm.setPos(-5.0F, 2.0F + f, 0.0F);
		this.leftArm.setPos(5.0F, 2.0F + f, 0.0F);

		this.rightHair1.xRot = ((float)Math.PI / 18.0F);
		this.leftHair1.xRot = ((float)Math.PI / 18.0F);
		this.rightHair1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.03F;
		this.leftHair1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.03F;
		this.rightHair2.xRot = ((float)Math.PI / 32.0F);
		this.leftHair2.xRot = ((float)Math.PI / 32.0F);

		this.rightHair1.zRot = ((float)Math.PI / 9.0F);
		this.leftHair1.zRot = -((float)Math.PI / 9.0F);
		this.rightHair1.zRot -= Mth.sin(ageInTicks * 0.09F) * 0.03F;
		this.leftHair1.zRot += Mth.sin(ageInTicks * 0.09F) * 0.03F;
		this.rightHair2.zRot = ((float)Math.PI / 27.0F);
		this.leftHair2.zRot = -((float)Math.PI / 27.0F);
		this.rightHair2.zRot -= Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 4.0F) * 0.024F;
		this.leftHair2.zRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 4.0F) * 0.024F;

		if (this.animationAmount > 0.0F)
		{
			this.rightHair1.zRot += Mth.sin(this.animationAmount * this.animationAmount * ((float)Math.PI / 1.2F)) * 0.75F;
			this.leftHair1.zRot -= Mth.sin(this.animationAmount * this.animationAmount * ((float)Math.PI / 1.2F)) * 0.75F;
		}

//		this.rightArmPart1.xRot = 0.0F;
//		this.leftArmPart1.xRot = 0.0F;
//		this.rightArmPart2.xRot = 0.0F;
//		this.leftArmPart2.xRot = 0.0F;
//		this.rightLegPart1.xRot = 0.0F;
//		this.leftLegPart1.xRot = 0.0F;
//		this.rightLegPart2.xRot = 0.0F;
//		this.leftLegPart2.xRot = 0.0F;
	}
}