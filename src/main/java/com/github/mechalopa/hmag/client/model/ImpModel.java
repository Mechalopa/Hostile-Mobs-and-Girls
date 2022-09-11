package com.github.mechalopa.hmag.client.model;

import java.util.Arrays;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

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
public class ImpModel<T extends Mob> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart rightFeelerPart1;
	private ModelPart leftFeelerPart1;
	private ModelPart rightFeelerPart2;
	private ModelPart leftFeelerPart2;
	private ModelPart rightFeelerPart3;
	private ModelPart leftFeelerPart3;
	private ModelPart rightFeelerPart4;
	private ModelPart leftFeelerPart4;
	private ModelPart rightEar;
	private ModelPart leftEar;
	private ModelPart rightHair1;
	private ModelPart leftHair1;
	private ModelPart rightHair2;
	private ModelPart leftHair2;
	private ModelPart rightHair3;
	private ModelPart leftHair3;
	private ModelPart hairPart;
//	private ModelPart rightArmPart;
//	private ModelPart leftArmPart;
//	private ModelPart clothPart;
	private ModelPart skirt1;
	private ModelPart skirt2;
	private ModelPart[] rightSkirtPart = new ModelPart[5];
	private ModelPart[] leftSkirtPart = new ModelPart[5];
	private ModelPart tail1;
	private ModelPart tail2;
	private ModelPart tail3;
	private ModelPart tail4;
	private ModelPart rightWing;
	private ModelPart leftWing;

	public ImpModel(ModelPart modelPart)
	{
		super(modelPart);
		this.rightFeelerPart1 = this.head.getChild("right_feeler_part_1");
		this.leftFeelerPart1 = this.head.getChild("left_feeler_part_1");
		this.rightFeelerPart2 = this.rightFeelerPart1.getChild("right_feeler_part_2");
		this.leftFeelerPart2 = this.leftFeelerPart1.getChild("left_feeler_part_2");
		this.rightFeelerPart3 = this.rightFeelerPart2.getChild("right_feeler_part_3");
		this.leftFeelerPart3 = this.leftFeelerPart2.getChild("left_feeler_part_3");
		this.rightFeelerPart4 = this.rightFeelerPart3.getChild("right_feeler_part_4");
		this.leftFeelerPart4 = this.leftFeelerPart3.getChild("left_feeler_part_4");
		this.rightEar = this.head.getChild("right_ear");
		this.leftEar = this.head.getChild("left_ear");
		this.rightHair1 = this.head.getChild("right_hair_1");
		this.leftHair1 = this.head.getChild("left_hair_1");
		this.rightHair2 = this.rightHair1.getChild("right_hair_2");
		this.leftHair2 = this.leftHair1.getChild("left_hair_2");
		this.rightHair3 = this.rightHair2.getChild("right_hair_3");
		this.leftHair3 = this.leftHair2.getChild("left_hair_3");
		this.hairPart = this.head.getChild("hair_part");
//		this.rightArmPart = this.rightArm.getChild("right_arm_part");
//		this.leftArmPart = this.leftArm.getChild("left_arm_part");
//		this.clothPart = this.body.getChild("cloth_part");
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.body.getChild("skirt_2");
		Arrays.setAll(this.rightSkirtPart, (p) -> {
			return this.body.getChild("right_skirt_part_" + p);
		});
		Arrays.setAll(this.leftSkirtPart, (p) -> {
			return this.body.getChild("left_skirt_part_" + p);
		});
		this.tail1 = this.body.getChild("tail_1");
		this.tail2 = this.tail1.getChild("tail_2");
		this.tail3 = this.tail2.getChild("tail_3");
		this.tail4 = this.tail3.getChild("tail_4");
		this.rightWing = this.body.getChild("right_wing");
		this.leftWing = this.body.getChild("left_wing");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = pd.getChild("head");
		PartDefinition rfp1pd = ModClientUtils.addC(headpd, cd, "right_feeler_part_1", 22, 36, -0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, -2.5F, -7.75F, -1.75F);
		PartDefinition lfp1pd = ModClientUtils.addC(headpd, cd, "left_feeler_part_1", 22, 36, -0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 2.5F, -7.75F, -1.75F, true);
		PartDefinition rfp2pd = ModClientUtils.addC(rfp1pd, cd, "right_feeler_part_2", 22, 38, -0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, -1.0F, 0.0F);
		PartDefinition lfp2pd = ModClientUtils.addC(lfp1pd, cd, "left_feeler_part_2", 22, 38, -0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, -1.0F, 0.0F, true);
		PartDefinition rfp3pd = ModClientUtils.addC(rfp2pd, cd, "right_feeler_part_3", 22, 40, -0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, -1.0F, 0.0F);
		PartDefinition lfp3pd = ModClientUtils.addC(lfp2pd, cd, "left_feeler_part_3", 22, 40, -0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, -1.0F, 0.0F, true);
		ModClientUtils.addC(rfp3pd, cd, "right_feeler_part_4", 20, 32, -4.5F, -1.5F, -1.0F, 5.0F, 3.0F, 1.0F, 0.0F, -0.75F, -0.001F);
		ModClientUtils.addC(lfp3pd, cd, "left_feeler_part_4", 20, 32, -0.5F, -1.5F, -1.0F, 5.0F, 3.0F, 1.0F, 0.0F, -0.75F, -0.001F, true);
		ModClientUtils.addC(headpd, cd, "right_ear", 26, 36, -1.25F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, -4.0F, -3.5F, -1.0F, 0.125F);
		ModClientUtils.addC(headpd, cd, "left_ear", 26, 36, -0.75F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 4.0F, -3.5F, -1.0F, true, 0.125F);
		PartDefinition rh1pd = ModClientUtils.addC(headpd, cd, "right_hair_1", 32, 64, -2.0F, -1.5F, -1.5F, 3.0F, 7.0F, 3.0F, -4.5F, -7.5F, 1.25F);
		PartDefinition lh1pd = ModClientUtils.addC(headpd, cd, "left_hair_1", 32, 64, -1.0F, -1.5F, -1.5F, 3.0F, 7.0F, 3.0F, 4.5F, -7.5F, 1.25F, true);
		PartDefinition rh2pd = ModClientUtils.addC(rh1pd, cd, "right_hair_2", 48, 64, -1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, -0.5F, 6.0F, 0.0F, 0.25F);
		PartDefinition lh2pd = ModClientUtils.addC(lh1pd, cd, "left_hair_2", 48, 64, -1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, 0.5F, 6.0F, 0.0F, true, 0.25F);
		ModClientUtils.addC(rh2pd, cd, "right_hair_3", 56, 64, -1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 3.5F, 0.0F);
		ModClientUtils.addC(lh2pd, cd, "left_hair_3", 56, 64, -1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 3.5F, 0.0F, true);
		ModClientUtils.addC(headpd, cd, "hair_part", 32, 56, -4.0F, 0.0F, -1.0F, 8.0F, 4.0F, 1.0F, 0.0F, 0.0F, 4.0F);
		PartDefinition rapd = ModClientUtils.addC(pd, cd, "right_arm", 40, 16, 0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, -5.0F, 2.0F, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 5.0F, 2.0F, 0.0F, true);
		ModClientUtils.addC(rapd, cd, "right_arm_part", 48, 32, -0.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, 2.0F, 0.0F);
		ModClientUtils.addC(lapd, cd, "left_arm_part", 48, 32, -2.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, 2.0F, 0.0F, true);
		PartDefinition bodypd = pd.getChild("body");
		ModClientUtils.addC(bodypd, cd, "cloth_part", 32, 48, -3.0F, 0.0F, -1.5F, 6.0F, 3.0F, 3.0F, 0.0F, 7.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_2", 0, 44, -4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, 0.0F, 11.5F, 0.0F);
		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 52).addBox(-2.5F, 0.5F, -1.0F, 5.0F, 5.0F, 1.0F);
		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(0, 52).mirror().addBox(-2.5F, 0.5F, -1.0F, 5.0F, 5.0F, 1.0F);

		for (int k = 0; k < 5; ++k)
		{
			float f = (float)(k - 2) * ((float)Math.PI / 5.0F);
			float f1 = Mth.sin(f - ((float)Math.PI / 2.0F)) * 3.5F;
			float f2 = Mth.cos(f - ((float)Math.PI / 2.0F)) * 2.0F;
			PartPose pp = PartPose.offset(f1, 11.0F, f2);
			bodypd.addOrReplaceChild("right_skirt_part_" + k, cubelistbuilder, pp);
			f = (float)(2 - k) * ((float)Math.PI / 5.0F);
			f1 = Mth.sin(f + ((float)Math.PI / 2.0F)) * 3.5F;
			f2 = Mth.cos(f + ((float)Math.PI / 2.0F)) * 2.0F;
			PartPose pp1 = PartPose.offset(f1, 11.0F, f2);
			bodypd.addOrReplaceChild("left_skirt_part_" + k, cubelistbuilder1, pp1);
		}

		PartDefinition t1pd = ModClientUtils.addC(bodypd, cd, "tail_1", 12, 52, -0.5F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, 9.5F, 1.5F);
		PartDefinition t2pd = ModClientUtils.addC(t1pd, cd, "tail_2", 16, 52, -0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, 3.0F, 0.0F);
		PartDefinition t3pd = ModClientUtils.addC(t2pd , cd, "tail_3", 20, 52, -0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, 4.0F, 0.0F);
		ModClientUtils.addC(t3pd , cd, "tail_4", 24, 52, -1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 1.0F, 0.0F, 2.75F, -0.001F);
		ModClientUtils.addC(bodypd, cd, "right_wing", 0, 60, -6.0F, -3.0F, 0.0F, 6.0F, 9.0F, 1.0F, -1.5F, 3.5F, 1.5F);
		ModClientUtils.addC(bodypd, cd, "left_wing", 0, 60, 0.0F, -3.0F, 0.0F, 6.0F, 9.0F, 1.0F, 1.5F, 3.5F, 1.5F, true);
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
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.075F) * 0.03F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.075F) * 0.03F;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
		}

		this.skirt1.xRot = 0.0F;

		this.rightFeelerPart1.yRot = (float)Math.PI / 9.0F;
		this.leftFeelerPart1.yRot = -((float)Math.PI / 9.0F);
		this.rightFeelerPart1.zRot = -((float)Math.PI / 20.0F);
		this.leftFeelerPart1.zRot = (float)Math.PI / 20.0F;
		this.rightFeelerPart1.xRot = (float)Math.PI / 8.0F;
		this.leftFeelerPart1.xRot = (float)Math.PI / 8.0F;
		this.rightFeelerPart1.xRot += Mth.sin(ageInTicks * 0.09F) * 0.072F;
		this.leftFeelerPart1.xRot += Mth.sin(ageInTicks * 0.09F) * 0.072F;
		this.rightFeelerPart2.xRot = -((float)Math.PI * 8.0F / 15.0F);
		this.leftFeelerPart2.xRot = -((float)Math.PI * 8.0F / 15.0F);
		this.rightFeelerPart3.xRot -= Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 6.0F) * 0.105F;
		this.leftFeelerPart3.xRot -= Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 6.0F) * 0.105F;
		this.rightFeelerPart3.xRot = (float)Math.PI * 7.0F / 15.0F;
		this.leftFeelerPart3.xRot = (float)Math.PI * 7.0F / 15.0F;
		this.rightFeelerPart3.xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 3.0F) * 0.105F;
		this.leftFeelerPart3.xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 3.0F) * 0.105F;
		this.rightFeelerPart4.zRot = (float)Math.PI / 5.0F;
		this.leftFeelerPart4.zRot = -((float)Math.PI / 5.0F);
		this.rightFeelerPart4.zRot += Mth.cos(ageInTicks * 0.12F + (float)Math.PI / 3.0F) * 0.045F;
		this.leftFeelerPart4.zRot -= Mth.cos(ageInTicks * 0.12F + (float)Math.PI / 3.0F) * 0.045F;

		this.rightEar.zRot = (float)Math.PI / 12.0F;
		this.leftEar.zRot = -((float)Math.PI / 12.0F);

		this.rightHair1.xRot = (float)Math.PI / 18.0F;
		this.leftHair1.xRot = (float)Math.PI / 18.0F;
		this.rightHair1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.036F;
		this.leftHair1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.036F;
		this.rightHair1.zRot = (float)Math.PI / 8.0F;
		this.leftHair1.zRot = -((float)Math.PI / 8.0F);
		this.rightHair1.zRot -= Mth.sin(ageInTicks * 0.075F) * 0.03F;
		this.leftHair1.zRot += Mth.sin(ageInTicks * 0.075F) * 0.03F;
		this.rightHair2.zRot = -((float)Math.PI / 15.0F);
		this.leftHair2.zRot = (float)Math.PI / 15.0F;
		this.rightHair2.yRot = (float)Math.PI / 21.0F;
		this.leftHair2.yRot = -((float)Math.PI / 21.0F);
		this.rightHair2.zRot -= Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.03F;
		this.leftHair2.zRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.03F;
		this.rightHair3.zRot = -((float)Math.PI / 12.0F);
		this.leftHair3.zRot = (float)Math.PI / 12.0F;
		this.rightHair3.zRot -= Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.015F;
		this.leftHair3.zRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.015F;
		this.rightHair3.yRot = (float)Math.PI / 21.0F;
		this.leftHair3.yRot = -((float)Math.PI / 21.0F);

		this.hairPart.xRot = (float)Math.PI / 12.0F;
		this.hairPart.xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.06F;

		float f = (float)Math.PI / 2.0F;

		for (int i = 0; i < this.rightSkirtPart.length; ++i)
		{
			this.rightSkirtPart[i].yRot = (float)Math.PI * ((float)(i - 2)) / 5.0F + f;
			this.rightSkirtPart[i].xRot = (float)Math.PI / 3.0F - f;
			this.rightSkirtPart[i].xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.045F;
			this.leftSkirtPart[i].yRot = -((float)Math.PI * ((float)(i - 2)) / 5.0F + f);
			this.leftSkirtPart[i].xRot = (float)Math.PI / 3.0F - f;
			this.leftSkirtPart[i].xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.045F;
		}

		this.tail1.xRot = (float)Math.PI * 2.0F / 5.0F;
		this.tail1.xRot += Mth.sin(ageInTicks * 0.09F) * 0.15F;
		this.tail1.zRot = Mth.cos(ageInTicks * 0.075F) * 0.21F;
		this.tail2.xRot = (float)Math.PI * 4.0F / 7.0F;
		this.tail2.xRot -= Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 3.0F) * 0.12F;
		this.tail3.xRot = -((float)Math.PI / 5.0F);
		this.tail3.xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI * 2.0F / 3.0F) * 0.12F;
		this.tail4.zRot = (float)Math.PI / 4.0F;

		this.rightWing.yRot = (float)Math.PI / 5.0F;
		this.leftWing.yRot = -((float)Math.PI / 5.0F);
		this.rightWing.yRot += Mth.cos(ageInTicks * 0.12F) * 0.096F;
		this.leftWing.yRot -= Mth.cos(ageInTicks * 0.12F) * 0.096F;
		this.rightWing.zRot = (float)Math.PI / 16.0F;
		this.leftWing.zRot = -((float)Math.PI / 16.0F);
		this.rightWing.zRot += Mth.cos(ageInTicks * 0.12F + (float)Math.PI / 3.0F) * 0.045F;
		this.leftWing.zRot -= Mth.cos(ageInTicks * 0.12F + (float)Math.PI / 3.0F) * 0.045F;
	}
}