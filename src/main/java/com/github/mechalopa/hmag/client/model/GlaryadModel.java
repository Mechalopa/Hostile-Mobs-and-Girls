package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlaryadModel<T extends Mob> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart rightArmPart1;
	private ModelPart leftArmPart1;
	private ModelPart rightArmPart2;
	private ModelPart leftArmPart2;
	private ModelPart skirt1;
	private ModelPart skirt2;
	private ModelPart skirt3;
	private ModelPart rightSkirtPart;
	private ModelPart leftSkirtPart;
	private ModelPart hairPart1;
	private ModelPart hairPart2;
	private ModelPart hairPart3;
	private ModelPart rightHairPart2;
	private ModelPart leftHairPart2;
	private ModelPart ahoge;

	public GlaryadModel(ModelPart modelPart)
	{
		super(modelPart);

		this.rightArmPart1 = this.rightArm.getChild("right_arm_part_1");
		this.leftArmPart1 = this.leftArm.getChild("left_arm_part_1");
		this.rightArmPart2 = this.rightArm.getChild("right_arm_part_2");
		this.leftArmPart2 = this.leftArm.getChild("left_arm_part_2");
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.skirt1.getChild("skirt_2");
		this.skirt3 = this.skirt2.getChild("skirt_3");
		this.rightSkirtPart = this.skirt3.getChild("right_skirt_part");
		this.leftSkirtPart = this.skirt3.getChild("left_skirt_part");
		this.hairPart1 = this.head.getChild("hair_part_1");
		this.hairPart2 = this.hairPart1.getChild("hair_part_2");
		this.hairPart3 = this.hairPart2.getChild("hair_part_3");
		this.rightHairPart2 = this.head.getChild("right_hair_part_2");
		this.leftHairPart2 = this.head.getChild("left_hair_part_2");
		this.ahoge = this.head.getChild("ahoge");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition rapd = ModClientUtils.addC(pd, cd, "right_arm", 40, 16, 0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, -5.0F, 2.0F, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 5.0F, 2.0F, 0.0F, true);
		ModClientUtils.addC(rapd, cd, "right_arm_part_1", 16, 32, -1.5F, 0.0F, 0.0F, 3.0F, 4.0F, 1.0F, 0.25F, -0.25F, -1.0F);
		ModClientUtils.addC(lapd, cd, "left_arm_part_1", 16, 32, -1.5F, 0.0F, 0.0F, 3.0F, 4.0F, 1.0F, -0.25F, -0.25F, -1.0F);
		ModClientUtils.addC(rapd, cd, "right_arm_part_2", 24, 32, 0.0F, 0.0F, -1.5F, 1.0F, 6.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		ModClientUtils.addC(lapd, cd, "left_arm_part_2", 24, 32, -1.0F, 0.0F, -1.5F, 1.0F, 6.0F, 3.0F, 0.0F, 0.0F, 0.0F, true);
		PartDefinition bodypd = pd.getChild("body");
		PartDefinition s1pd = ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 48, -3.5F, 0.0F, -2.0F, 7.0F, 2.0F, 4.0F, 0.0F, 6.0F, 0.0F);
		PartDefinition s2pd = ModClientUtils.addC(s1pd, cd, "skirt_2", 0, 56, -4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, 0.0F, 2.0F, 0.0F);
		PartDefinition s3pd = ModClientUtils.addC(s2pd, cd, "skirt_3", 0, 64, -4.5F, 0.0F, -3.0F, 9.0F, 6.0F, 6.0F, 0.0F, 2.0F, 0.0F);
		ModClientUtils.addC(s3pd, cd, "right_skirt_part", 24, 48, 0.0F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F, -4.5F, 4.5F, 1.0F);
		ModClientUtils.addC(s3pd, cd, "left_skirt_part", 24, 48, -1.0F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F, 4.5F, 4.5F, 1.0F, true);
		PartDefinition headpd = pd.getChild("head");
		PartDefinition h1pd = ModClientUtils.addC(headpd, cd, "hair_part_1", 32, 48, -5.0F, 0.0F, -1.0F, 10.0F, 2.0F, 1.0F, 0.0F, -1.0F, 4.0F);
		PartDefinition h2pd = ModClientUtils.addC(h1pd, cd, "hair_part_2", 32, 52, -6.0F, 0.0F, -1.0F, 12.0F, 2.0F, 1.0F, 0.0F, 2.0F, 0.0F);
		ModClientUtils.addC(h2pd, cd, "hair_part_3", 32, 56, -5.0F, 0.0F, -1.0F, 10.0F, 3.0F, 1.0F, 0.0F, 2.0F, 0.0F);
		float f = (float)Math.PI / 15.0F;
		ModClientUtils.addC(headpd, cd, "right_hair_part_1", 48, 32, 0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, -4.5F, -2.5F, 0.5F, 0.0F, f, 0.0F);
		ModClientUtils.addC(headpd, cd, "left_hair_part_1", 48, 32, -1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, 4.5F, -2.5F, 0.5F, 0.0F, -f, 0.0F, true);
		ModClientUtils.addC(headpd, cd, "right_hair_part_2", 56, 32, 0.0F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F, -4.0F, -2.625F, 1.5F);
		ModClientUtils.addC(headpd, cd, "left_hair_part_2", 56, 32, -1.0F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F, 4.0F, -2.625F, 1.5F, true);
		ModClientUtils.addC(headpd, cd, "ahoge", 0, 40, -4.5F, -5.0F, 0.0F, 9.0F, 5.0F, 1.0F, 0.0F, -7.75F, 0.0F, -0.5F);
		PartDefinition hatpd = pd.getChild("hat");
		ModClientUtils.addC(hatpd, cd, "hat_part", 0, 80, -4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, 3.0F, 0.0F, 0.5F);
		PartDefinition rlpd = pd.getChild("right_leg");
		PartDefinition llpd = pd.getChild("left_leg");
		ModClientUtils.addC(rlpd, cd, "right_leg_part", 32, 64, -1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, 6.0F, 0.0F, 0.25F);
		ModClientUtils.addC(llpd, cd, "left_leg_part", 32, 64, -1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, 6.0F, 0.0F, true, 0.25F);
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

		this.rightArm.zRot = (float)Math.PI / 12.0F;
		this.leftArm.zRot = -((float)Math.PI / 12.0F);
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.12F) * 0.06F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.12F) * 0.06F;

		if (this.riding)
		{
			this.skirt3.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt3.xRot = this.body.xRot;
		}

		this.skirt1.xRot = 0.0F;
		this.skirt2.xRot = 0.0F;

		this.rightSkirtPart.zRot = (float)Math.PI / 18.0F;
		this.leftSkirtPart.zRot = -((float)Math.PI / 18.0F);
		this.rightSkirtPart.zRot += Mth.cos(ageInTicks * 0.075F + (float)Math.PI / 4.0F) * 0.03F;
		this.leftSkirtPart.zRot -= Mth.cos(ageInTicks * 0.075F + (float)Math.PI / 4.0F) * 0.03F;
		this.rightSkirtPart.xRot = (float)Math.PI / 24.0F;
		this.leftSkirtPart.xRot = (float)Math.PI / 24.0F;
		this.rightSkirtPart.xRot += Mth.sin(ageInTicks * 0.06F) * 0.015F;
		this.leftSkirtPart.xRot += Mth.sin(ageInTicks * 0.06F) * 0.015F;

		this.rightArmPart1.xRot = -((float)Math.PI / 18.0F);
		this.leftArmPart1.xRot = -((float)Math.PI / 18.0F);
		this.rightArmPart1.xRot += Mth.cos(ageInTicks * 0.075F) * 0.015F;
		this.leftArmPart1.xRot += Mth.cos(ageInTicks * 0.075F) * 0.015F;
		this.rightArmPart1.yRot = (float)Math.PI / 9.0F;
		this.leftArmPart1.yRot = -((float)Math.PI / 9.0F);
		this.rightArmPart1.zRot = (float)Math.PI / 24.0F;
		this.leftArmPart1.zRot = -((float)Math.PI / 24.0F);
		this.rightArmPart2.zRot = (float)Math.PI / 18.0F;
		this.leftArmPart2.zRot = -((float)Math.PI / 18.0F);
		this.rightArmPart2.zRot += Mth.cos(ageInTicks * 0.075F) * 0.03F;
		this.leftArmPart2.zRot -= Mth.cos(ageInTicks * 0.075F) * 0.03F;
		this.rightArmPart2.xRot = (float)Math.PI / 24.0F;
		this.leftArmPart2.xRot = (float)Math.PI / 24.0F;
		this.rightArmPart2.xRot += Mth.sin(ageInTicks * 0.06F) * 0.015F;
		this.leftArmPart2.xRot += Mth.sin(ageInTicks * 0.06F) * 0.015F;

		this.hairPart1.xRot = (float)Math.PI / 27.0F;
		this.hairPart1.xRot += Mth.sin(ageInTicks * 0.075F) * 0.06F;
		this.hairPart2.xRot = (float)Math.PI / 27.0F;
		this.hairPart2.xRot += Mth.sin(ageInTicks * 0.075F + (float)Math.PI / 8.0F) * 0.06F;
		this.hairPart3.xRot = (float)Math.PI / 27.0F;
		this.hairPart3.xRot += Mth.sin(ageInTicks * 0.075F + (float)Math.PI / 4.0F) * 0.06F;

		this.rightHairPart2.zRot = (float)Math.PI / 9.0F;
		this.leftHairPart2.zRot = -((float)Math.PI / 9.0F);
		this.rightHairPart2.zRot += Mth.cos(ageInTicks * 0.075F + (float)Math.PI / 2.0F) * 0.045F;
		this.leftHairPart2.zRot -= Mth.cos(ageInTicks * 0.075F + (float)Math.PI / 2.0F) * 0.045F;
		this.rightHairPart2.xRot = (float)Math.PI / 15.0F;
		this.leftHairPart2.xRot = (float)Math.PI / 15.0F;
		this.rightHairPart2.xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 4.0F) * 0.015F;
		this.leftHairPart2.xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 4.0F) * 0.015F;

		this.ahoge.xRot = -((float)Math.PI / 12.0F);
		this.ahoge.xRot += Mth.cos(ageInTicks * 0.03F + (float)Math.PI) * 0.09F;
	}
}