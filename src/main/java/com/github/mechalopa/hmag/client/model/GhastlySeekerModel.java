package com.github.mechalopa.hmag.client.model;

import java.util.Arrays;
import java.util.Random;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.GhastlySeekerEntity;

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
public class GhastlySeekerModel<T extends GhastlySeekerEntity> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart rightArmPart1;
	private ModelPart rightArmPart2;
	private ModelPart leftArmPart1;
	private ModelPart leftArmPart2;
	private ModelPart skirt1;
	private ModelPart skirt2;
	private ModelPart skirt3;
	private ModelPart skirt4;
	private ModelPart[] tentacles = new ModelPart[7];
	private ModelPart rightHairPart1;
	private ModelPart rightHairPart2;
	private ModelPart rightHairPart3;
	private ModelPart rightHairPart4;
	private ModelPart rightHairPart5;
//	private ModelPart rightHairPart6;
	private ModelPart leftHairPart1;
	private ModelPart leftHairPart2;
	private ModelPart leftHairPart3;
	private ModelPart leftHairPart4;
	private ModelPart leftHairPart5;
//	private ModelPart leftHairPart6;
	private ModelPart hairPart;
//	private ModelPart headwearPart;

	public GhastlySeekerModel(ModelPart modelPart)
	{
		super(modelPart);
		this.rightArmPart1 = this.rightArm.getChild("right_arm_part_1");
		this.leftArmPart1 = this.leftArm.getChild("left_arm_part_1");
		this.rightArmPart2 = this.rightArmPart1.getChild("right_arm_part_2");
		this.leftArmPart2 = this.leftArmPart1.getChild("left_arm_part_2");
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.body.getChild("skirt_2");
		this.skirt3 = this.skirt2.getChild("skirt_3");
		this.skirt4 = this.skirt3.getChild("skirt_4");
		Arrays.setAll(this.tentacles, (p) -> {
			return this.skirt4.getChild("tentacle_" + p);
		});
		this.hairPart = this.head.getChild("hair_part");
		this.rightHairPart1 = this.head.getChild("right_hair_part_1");
		this.leftHairPart1 = this.head.getChild("left_hair_part_1");
		this.rightHairPart2 = this.rightHairPart1.getChild("right_hair_part_2");
		this.leftHairPart2 = this.leftHairPart1.getChild("left_hair_part_2");
		this.rightHairPart3 = this.rightHairPart1.getChild("right_hair_part_3");
		this.rightHairPart4 = this.rightHairPart3.getChild("right_hair_part_4");
		this.rightHairPart5 = this.rightHairPart4.getChild("right_hair_part_5");
		this.leftHairPart3 = this.leftHairPart1.getChild("left_hair_part_3");
		this.leftHairPart4 = this.leftHairPart3.getChild("left_hair_part_4");
		this.leftHairPart5 = this.leftHairPart4.getChild("left_hair_part_5");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition rapd = ModClientUtils.addC(pd, cd, "right_arm", 40, 16, -1.0F, -2.0F, -1.5F, 3.0F, 5.0F, 3.0F, -5.0F, 2.0F, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.5F, 3.0F, 5.0F, 3.0F, 5.0F, 2.0F, 0.0F, true);
		PartDefinition rap1pd = ModClientUtils.addC(rapd, cd, "right_arm_part_1", 40, 48, -1.5F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 2.5F, 0.0F);
		ModClientUtils.addC(rap1pd, cd, "right_arm_part_2", 40, 56, -2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, 3.5F, 0.0F);
		PartDefinition lap1pd = ModClientUtils.addC(lapd, cd, "left_arm_part_1", 40, 48, -2.5F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 2.5F, 0.0F, true);
		ModClientUtils.addC(lap1pd, cd, "left_arm_part_2", 40, 56, -2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, 3.5F, 0.0F, true);
		ModClientUtils.addC(pd, cd, "right_leg", 0, 16, -0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, -2.0F, 12.0F, 0.0F);
		ModClientUtils.addC(pd, cd, "left_leg", 0, 16, -0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 2.0F, 12.0F, 0.0F, true);
		PartDefinition bodypd = pd.getChild("body");
		PartDefinition bodypart1pd = bodypd.getChild("body_part_1");
		ModClientUtils.addC(bodypart1pd, cd, "body_part_2", 32, 40, -3.0F, 0.0F, -1.5F, 6.0F, 2.0F, 3.0F, 0.0F, 2.5F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, 0.0F, 10.0F, 0.0F);
		PartDefinition s2pd = ModClientUtils.addC(bodypd, cd, "skirt_2", 0, 46, -4.5F, 0.0F, -3.0F, 9.0F, 2.0F, 7.0F, 0.0F, 11.0F, 0.0F);
		PartDefinition s3pd = ModClientUtils.addC(s2pd, cd, "skirt_3", 0, 56, -5.0F, 0.0F, -3.5F, 10.0F, 2.0F, 9.0F, 0.0F, 1.0F, 0.0F);
		PartDefinition s4pd = ModClientUtils.addC(s3pd, cd, "skirt_4", 0, 72, -5.5F, 0.0F, -4.0F, 11.0F, 3.0F, 11.0F, 0.0F, 1.0F, 0.0F);

		Random r = new Random(1660L);

		for (int i = 0; i < 7; ++i)
		{
			float f = (((float)(i % 3) - (float)(i / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 3.0F;
			float f1 = ((float)(i / 3) / 2.0F * 2.0F - 1.0F) * 3.0F;
			int j = r.nextInt(3) + 8;
			s4pd.addOrReplaceChild("tentacle_" + i, CubeListBuilder.create().texOffs(0, 88).addBox(-1.5F, 0.0F, -1.5F, 3.0F, (float)j, 3.0F), PartPose.offset(f, 1.75F, f1 + 1.0F));
		}

		PartDefinition headpd = pd.getChild("head");
		ModClientUtils.addC(headpd, cd, "hair_part", 0, 104, -4.0F, 0.0F, -1.0F, 8.0F, 2.0F, 1.0F, 0.0F, 0.0F, 4.0F);
		PartDefinition hatpd = pd.getChild("hat");
		ModClientUtils.addC(hatpd, cd, "hat_part", 32, 104, -4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, 3.0F, 0.0F, 0.5F);
		PartDefinition rhp1pd = ModClientUtils.addC(headpd, cd, "right_hair_part_1", 16, 88, -1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, -3.75F, -7.0F, -0.5F);
		PartDefinition lhp1pd = ModClientUtils.addC(headpd, cd, "left_hair_part_1", 16, 88, -1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, 3.75F, -7.0F, -0.5F, true);
		ModClientUtils.addC(rhp1pd, cd, "right_hair_part_2", 24, 88, -0.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, -1.5F, 0.0F, -0.25F);
		ModClientUtils.addC(lhp1pd, cd, "left_hair_part_2", 24, 88, -0.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, 1.5F, 0.0F, -0.25F);
		PartDefinition rhp3pd = ModClientUtils.addC(rhp1pd, cd, "right_hair_part_3", 48, 72, -1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, -1.5F, 1.0F, 1.5F);
		PartDefinition rhp4pd = ModClientUtils.addC(rhp3pd, cd, "right_hair_part_4", 40, 88, -1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, 3.5F, 0.0F, 0.25F);
		PartDefinition rhp5pd = ModClientUtils.addC(rhp4pd, cd, "right_hair_part_5", 48, 88, -1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, 8.0F, 0.0F);
		ModClientUtils.addC(rhp5pd, cd, "right_hair_part_6", 48, 80, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 6.0F, 0.0F);
		PartDefinition lhp3pd = ModClientUtils.addC(lhp1pd, cd, "left_hair_part_3", 48, 72, -1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, 1.5F, 1.0F, 1.5F, true);
		PartDefinition lhp4pd = ModClientUtils.addC(lhp3pd, cd, "left_hair_part_4", 40, 88, -1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, 3.5F, 0.0F, true, 0.25F);
		PartDefinition lhp5pd = ModClientUtils.addC(lhp4pd, cd, "left_hair_part_5", 48, 88, -1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, 8.0F, 0.0F, true);
		ModClientUtils.addC(lhp5pd, cd, "left_hair_part_6", 48, 80, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 6.0F, 0.0F, true);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 128);
	}

	public static LayerDefinition createOuterLayer()
	{
		return LayerDefinition.create(createMesh(new CubeDeformation(0.001F)), 64, 128);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float f = Mth.sin(this.attackTime * (float)Math.PI);
		float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);

		if (entityIn.isAttacking())
		{
			this.rightArm.zRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightArm.yRot = -(0.3F - f * 0.6F);
			this.leftArm.yRot = 0.3F - f * 0.6F;
			this.rightArm.xRot = -((float)Math.PI / 2F);
			this.leftArm.xRot = -((float)Math.PI / 2F);
			this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.rightArm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.leftArm.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.rightArm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
			this.leftArm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
		}
		else
		{
			this.rightArm.zRot = ((float)Math.PI / 12.0F);
			this.leftArm.zRot = -((float)Math.PI / 12.0F);
			this.rightArm.yRot = -(0.1F - f * 0.6F);
			this.leftArm.yRot = 0.1F - f * 0.6F;
			this.rightArm.xRot = ((float)Math.PI / 15F);
			this.leftArm.xRot = ((float)Math.PI / 15F);
			this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.rightArm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.04F;
			this.leftArm.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.04F;
			this.rightArm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.04F;
			this.leftArm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.04F;
		}

		float f2 = Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F);

		this.rightArmPart1.xRot = ((float)Math.PI / 24.0F);
		this.leftArmPart1.xRot = ((float)Math.PI / 24.0F);
		this.rightArmPart1.xRot += f2 * 0.067F;
		this.leftArmPart1.xRot += f2 * 0.067F;

		this.rightArmPart2.zRot = ((float)Math.PI / 30.0F);
		this.leftArmPart2.zRot = -((float)Math.PI / 30.0F);

		this.skirt2.xRot = ((float)Math.PI / 24.0F);
		this.skirt2.xRot -= f * 1.2F - f1 * 0.4F;
		this.skirt2.xRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 2.0F) * 0.04F;
		this.skirt3.xRot = ((float)Math.PI / 30.0F);
		this.skirt3.xRot += f2 * 0.03F;
		this.skirt4.xRot = ((float)Math.PI / 30.0F);
		this.skirt4.xRot += f2 * 0.03F;

		this.skirt1.xRot = 0.0F;

		for (int i = 0; i < this.tentacles.length; ++i)
		{
			this.tentacles[i].xRot = 0.2F * Mth.sin(ageInTicks * 0.15F + (float)i) + 0.4F;
		}

		this.rightHairPart1.zRot = ((float)Math.PI / 27.0F);
		this.leftHairPart1.zRot = -((float)Math.PI / 27.0F);
		this.rightHairPart1.zRot += Mth.sin(ageInTicks * 0.067F) * 0.067F;
		this.leftHairPart1.zRot -= Mth.sin(ageInTicks * 0.067F) * 0.067F;
		this.rightHairPart2.yRot = -((float)Math.PI / 5.0F);
		this.leftHairPart2.yRot = ((float)Math.PI / 5.0F);
		this.rightHairPart2.zRot = ((float)Math.PI / 24.0F);
		this.leftHairPart2.zRot = -((float)Math.PI / 24.0F);
		this.rightHairPart3.xRot = ((float)Math.PI / 12.0F);
		this.leftHairPart3.xRot = ((float)Math.PI / 12.0F);
		this.rightHairPart3.xRot += f2 * 0.045F;
		this.leftHairPart3.xRot += f2 * 0.045F;
		this.rightHairPart3.zRot = ((float)Math.PI / 15.0F);
		this.leftHairPart3.zRot = -((float)Math.PI / 15.0F);
		this.rightHairPart4.zRot = ((float)Math.PI / 30.0F);
		this.leftHairPart4.zRot = -((float)Math.PI / 30.0F);
		this.rightHairPart4.zRot -= Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.045F;
		this.leftHairPart4.zRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.045F;
		this.rightHairPart5.zRot = -((float)Math.PI / 27.0F);
		this.leftHairPart5.zRot = ((float)Math.PI / 27.0F);

		this.hairPart.xRot = ((float)Math.PI / 12.0F);
		this.hairPart.xRot += Mth.sin(ageInTicks * 0.075F) * 0.045F;
	}
}