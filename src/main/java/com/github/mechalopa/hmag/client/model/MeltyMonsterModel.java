package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.entity.MeltyMonsterEntity;

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
public class MeltyMonsterModel<T extends MeltyMonsterEntity> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart rightArmPart;
	private ModelPart leftArmPart;
	private ModelPart rightLegPart;
	private ModelPart leftLegPart;
	private ModelPart skirt1;
	private ModelPart skirt2;
	private ModelPart skirt3;
	private ModelPart rightHairA1;
	private ModelPart rightHairA2;
	private ModelPart rightHairA3;
	private ModelPart rightHairA4;
	private ModelPart rightHairA5;
	private ModelPart leftHairA1;
	private ModelPart leftHairA2;
	private ModelPart leftHairA3;
	private ModelPart leftHairA4;
	private ModelPart leftHairA5;
	private ModelPart rightHairB1;
	private ModelPart rightHairB2;
	private ModelPart rightHairB3;
	private ModelPart rightHairB4;
	private ModelPart rightHairB5;
	private ModelPart leftHairB1;
	private ModelPart leftHairB2;
	private ModelPart leftHairB3;
	private ModelPart leftHairB4;
	private ModelPart leftHairB5;
	private ModelPart ahoge;

	public MeltyMonsterModel(ModelPart modelPart)
	{
		super(modelPart);
		this.rightArmPart = this.rightArm.getChild("right_arm_part");
		this.leftArmPart = this.leftArm.getChild("left_arm_part");
		this.rightLegPart = this.rightLeg.getChild("right_leg_part");
		this.leftLegPart = this.leftLeg.getChild("left_leg_part");
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.skirt1.getChild("skirt_2");
		this.skirt3 = this.skirt2.getChild("skirt_3");
		this.rightHairA1 = this.head.getChild("right_hair_a_1");
		this.rightHairA2 = this.rightHairA1.getChild("right_hair_a_2");
		this.rightHairA3 = this.rightHairA2.getChild("right_hair_a_3");
		this.rightHairA4 = this.rightHairA3.getChild("right_hair_a_4");
		this.rightHairA5 = this.rightHairA4.getChild("right_hair_a_5");
		this.leftHairA1 = this.head.getChild("left_hair_a_1");
		this.leftHairA2 = this.leftHairA1.getChild("left_hair_a_2");
		this.leftHairA3 = this.leftHairA2.getChild("left_hair_a_3");
		this.leftHairA4 = this.leftHairA3.getChild("left_hair_a_4");
		this.leftHairA5 = this.leftHairA4.getChild("left_hair_a_5");
		this.rightHairB1 = this.head.getChild("right_hair_b_1");
		this.rightHairB2 = this.rightHairB1.getChild("right_hair_b_2");
		this.rightHairB3 = this.rightHairB2.getChild("right_hair_b_3");
		this.rightHairB4 = this.rightHairB3.getChild("right_hair_b_4");
		this.rightHairB5 = this.rightHairB4.getChild("right_hair_b_5");
		this.leftHairB1 = this.head.getChild("left_hair_b_1");
		this.leftHairB2 = this.leftHairB1.getChild("left_hair_b_2");
		this.leftHairB3 = this.leftHairB2.getChild("left_hair_b_3");
		this.leftHairB4 = this.leftHairB3.getChild("left_hair_b_4");
		this.leftHairB5 = this.leftHairB4.getChild("left_hair_b_5");
		this.ahoge = this.head.getChild("ahoge");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition rapd = ModClientUtils.addC(pd, cd, "right_arm", 40, 16, 0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, -5.0F, 2.0F, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 5.0F, 2.0F, 0.0F, true);
		ModClientUtils.addC(rapd, cd, "right_arm_part", 16, 56, -0.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, 5.0F, 0.0F);
		ModClientUtils.addC(lapd, cd, "left_arm_part", 16, 56, -2.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, 5.0F, 0.0F, true);
		PartDefinition rlpd = ModClientUtils.addC(pd, cd, "right_leg", 0, 16, -1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, -1.75F, 12.0F, 0.0F);
		PartDefinition llpd = ModClientUtils.addC(pd, cd, "left_leg", 0, 16, -1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 1.75F, 12.0F, 0.0F, true);
		ModClientUtils.addC(rlpd, cd, "right_leg_part", 0, 56, -1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, 7.0F, 0.0F);
		ModClientUtils.addC(llpd, cd, "left_leg_part", 0, 56, -1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, 7.0F, 0.0F, true);
		PartDefinition bodypd = pd.getChild("body");
		PartDefinition skirt1pd = ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		PartDefinition skirt2pd = ModClientUtils.addC(skirt1pd, cd, "skirt_2", 0, 44, -4.0F, 0.0F, -2.5F, 8.0F, 3.0F, 5.0F, 0.0F, 12.0F, 0.0F);
		ModClientUtils.addC(skirt2pd, cd, "skirt_3", 0, 64, -4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, 0.0F, 3.0F, 0.0F);
		PartDefinition headpd = pd.getChild("head");
		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, cd);
		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(32, 56).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, cd);
		CubeListBuilder cubelistbuilder2 = CubeListBuilder.create().texOffs(40, 56).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, cd.extend(0.25F));
		CubeListBuilder cubelistbuilder3 = CubeListBuilder.create().texOffs(48, 56).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, cd);
		CubeListBuilder cubelistbuilder4 = CubeListBuilder.create().texOffs(32, 64).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, cd);
		CubeListBuilder cubelistbuilder5 = CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, cd);
		CubeListBuilder cubelistbuilder6 = CubeListBuilder.create().texOffs(32, 56).mirror().addBox(-1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, cd);
		CubeListBuilder cubelistbuilder7 = CubeListBuilder.create().texOffs(40, 56).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, cd.extend(0.25F));
		CubeListBuilder cubelistbuilder8 = CubeListBuilder.create().texOffs(48, 56).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, cd);
		CubeListBuilder cubelistbuilder9 = CubeListBuilder.create().texOffs(32, 64).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, cd);
		PartDefinition rha1pd = headpd.addOrReplaceChild("right_hair_a_1", cubelistbuilder, PartPose.offset(-3.75F, -7.0F, 0.5F));
		PartDefinition rha2pd = rha1pd.addOrReplaceChild("right_hair_a_2", cubelistbuilder1, PartPose.offset(-1.0F, 0.5F, 1.5F));
		PartDefinition rha3pd = rha2pd.addOrReplaceChild("right_hair_a_3", cubelistbuilder2, PartPose.offset(0.0F, 3.5F, 0.0F));
		PartDefinition rha4pd = rha3pd.addOrReplaceChild("right_hair_a_4", cubelistbuilder3, PartPose.offset(0.0F, 6.0F, 0.0F));
		rha4pd.addOrReplaceChild("right_hair_a_5", cubelistbuilder4, PartPose.offset(0.0F, 2.5F, 0.0F));
		PartDefinition lha1pd = headpd.addOrReplaceChild("left_hair_a_1", cubelistbuilder5, PartPose.offset(3.75F, -7.0F, 0.5F));
		PartDefinition lha2pd = lha1pd.addOrReplaceChild("left_hair_a_2", cubelistbuilder6, PartPose.offset(1.0F, 0.5F, 1.5F));
		PartDefinition lha3pd = lha2pd.addOrReplaceChild("left_hair_a_3", cubelistbuilder7, PartPose.offset(0.0F, 3.5F, 0.0F));
		PartDefinition lha4pd = lha3pd.addOrReplaceChild("left_hair_a_4", cubelistbuilder8, PartPose.offset(0.0F, 6.0F, 0.0F));
		lha4pd.addOrReplaceChild("left_hair_a_5", cubelistbuilder9, PartPose.offset(0.0F, 2.5F, 0.0F));
		PartDefinition rhb1pd = headpd.addOrReplaceChild("right_hair_b_1", cubelistbuilder, PartPose.offset(-3.0F, -7.0F, 3.75F));
		PartDefinition rhb2pd = rhb1pd.addOrReplaceChild("right_hair_b_2", cubelistbuilder1, PartPose.offset(0.5F, 0.75F, 1.5F));
		PartDefinition rhb3pd = rhb2pd.addOrReplaceChild("right_hair_b_3", cubelistbuilder2, PartPose.offset(0.0F, 3.5F, 0.0F));
		PartDefinition rhb4pd = rhb3pd.addOrReplaceChild("right_hair_b_4", cubelistbuilder3, PartPose.offset(0.0F, 6.0F, 0.0F));
		rhb4pd.addOrReplaceChild("right_hair_b_5", cubelistbuilder4, PartPose.offset(0.0F, 2.5F, 0.0F));
		PartDefinition lhb1pd = headpd.addOrReplaceChild("left_hair_b_1", cubelistbuilder5, PartPose.offset(3.0F, -7.0F, 3.75F));
		PartDefinition lhb2pd = lhb1pd.addOrReplaceChild("left_hair_b_2", cubelistbuilder6, PartPose.offset(-0.5F, 0.75F, 1.5F));
		PartDefinition lhb3pd = lhb2pd.addOrReplaceChild("left_hair_b_3", cubelistbuilder7, PartPose.offset(0.0F, 3.5F, 0.0F));
		PartDefinition lhb4pd = lhb3pd.addOrReplaceChild("left_hair_b_4", cubelistbuilder8, PartPose.offset(0.0F, 6.0F, 0.0F));
		lhb4pd.addOrReplaceChild("left_hair_b_5", cubelistbuilder9, PartPose.offset(0.0F, 2.5F, 0.0F));
		ModClientUtils.addC(headpd, cd, "ahoge", 16, 32, -2.5F, -3.0F, 0.0F, 5.0F, 3.0F, 1.0F, 0.0F, -7.75F, 0.0F, -0.25F);
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

		this.rightArm.zRot = (float)Math.PI / 8.0F;
		this.leftArm.zRot = -((float)Math.PI / 8.0F);
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.12F) * 0.06F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.12F) * 0.06F;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;

			float f = 0.21F;

			if (this.rightLeg.xRot > f)
			{
				this.rightLeg.xRot = f;
			}

			if (this.leftLeg.xRot > f)
			{
				this.leftLeg.xRot = f;
			}

			if (this.rightLeg.xRot < -f)
			{
				this.rightLeg.xRot = -f;
			}

			if (this.leftLeg.xRot < -f)
			{
				this.leftLeg.xRot = -f;
			}
		}

		this.rightHairA1.zRot = ((float)Math.PI / 27.0F);
		this.leftHairA1.zRot = -((float)Math.PI / 27.0F);
		this.rightHairA1.zRot += Mth.sin(ageInTicks * 0.048F) * 0.05F;
		this.leftHairA1.zRot -= Mth.sin(ageInTicks * 0.048F) * 0.05F;
		this.rightHairA2.yRot = -((float)Math.PI / 8.0F);
		this.leftHairA2.yRot = ((float)Math.PI / 8.0F);
		this.rightHairA2.zRot = ((float)Math.PI / 30.0F);
		this.leftHairA2.zRot = -((float)Math.PI / 30.0F);
		this.rightHairA2.xRot = ((float)Math.PI / 27.0F);
		this.leftHairA2.xRot = ((float)Math.PI / 27.0F);
		this.rightHairA2.xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.045F;
		this.leftHairA2.xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.045F;
		this.rightHairA2.zRot = ((float)Math.PI / 24.0F);
		this.leftHairA2.zRot = -((float)Math.PI / 24.0F);
		this.rightHairA3.zRot = ((float)Math.PI / 30.0F);
		this.leftHairA3.zRot = -((float)Math.PI / 30.0F);
		this.rightHairA3.zRot -= Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.045F;
		this.leftHairA3.zRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.045F;
		this.rightHairA4.zRot = -((float)Math.PI / 27.0F);
		this.leftHairA4.zRot = ((float)Math.PI / 27.0F);
		this.rightHairA4.zRot -= Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.021F;
		this.leftHairA4.zRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.021F;
		this.rightHairA5.zRot = -((float)Math.PI / 30.0F);
		this.leftHairA5.zRot = ((float)Math.PI / 30.0F);
		this.rightHairA5.zRot -= Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.021F;
		this.leftHairA5.zRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.021F;

		this.rightHairB1.zRot = ((float)Math.PI / 30.0F);
		this.leftHairB1.zRot = -((float)Math.PI / 30.0F);
		this.rightHairB1.zRot -= Mth.sin(ageInTicks * 0.048F + (float)Math.PI / 3.0F) * 0.05F;
		this.leftHairB1.zRot += Mth.sin(ageInTicks * 0.048F + (float)Math.PI / 3.0F) * 0.05F;
		this.rightHairB2.yRot = ((float)Math.PI / 8.0F);
		this.leftHairB2.yRot = -((float)Math.PI / 8.0F);
		this.rightHairB2.zRot = ((float)Math.PI / 30.0F);
		this.leftHairB2.zRot = -((float)Math.PI / 30.0F);
		this.rightHairB2.xRot = ((float)Math.PI / 27.0F);
		this.leftHairB2.xRot = ((float)Math.PI / 27.0F);
		this.rightHairB2.xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.045F;
		this.leftHairB2.xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.045F;
		this.rightHairB2.zRot = ((float)Math.PI / 24.0F);
		this.leftHairB2.zRot = -((float)Math.PI / 24.0F);
		this.rightHairB3.zRot = ((float)Math.PI / 30.0F);
		this.leftHairB3.zRot = -((float)Math.PI / 30.0F);
		this.rightHairB3.zRot -= Mth.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 3.0F) * 0.045F;
		this.leftHairB3.zRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 3.0F) * 0.045F;
		this.rightHairB4.zRot = -((float)Math.PI / 27.0F);
		this.leftHairB4.zRot = ((float)Math.PI / 27.0F);
		this.rightHairB4.zRot -= Mth.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 3.0F) * 0.021F;
		this.leftHairB4.zRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 3.0F) * 0.021F;
		this.rightHairB5.zRot = -((float)Math.PI / 30.0F);
		this.leftHairB5.zRot = ((float)Math.PI / 30.0F);
		this.rightHairB5.zRot -= Mth.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 3.0F) * 0.021F;
		this.leftHairB5.zRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 3.0F) * 0.021F;

		this.ahoge.xRot = -((float)Math.PI / 12.0F);
		this.ahoge.xRot += Mth.cos(ageInTicks * 0.03F + (float)Math.PI) * 0.09F;

		this.rightArmPart.xRot = 0.0F;
		this.leftArmPart.xRot = 0.0F;
		this.rightLegPart.xRot = 0.0F;
		this.leftLegPart.xRot = 0.0F;
		this.skirt3.xRot = 0.0F;
	}

	public void setLayer(boolean flag)
	{
		this.hat.visible = !flag;
		this.skirt3.visible = !flag;
		this.ahoge.visible = !flag;
	}
}