package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.AbstractFlyingMonsterEntity;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BansheeModel<T extends AbstractFlyingMonsterEntity> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart skirt1;
//	private ModelPart skirt2;
//	private ModelPart skirt3;
//	private ModelPart skirt4;
	private ModelPart bodyRibbon1;
	private ModelPart bodyRibbon2;
	private ModelPart hairPart1;
	private ModelPart hairPart2;
	private ModelPart rightHairPart1;
	private ModelPart rightHairPart2;
	private ModelPart rightHairPart3;
	private ModelPart rightHairPart4;
	private ModelPart rightHairPart5;
	private ModelPart leftHairPart1;
	private ModelPart leftHairPart2;
	private ModelPart leftHairPart3;
	private ModelPart leftHairPart4;
	private ModelPart leftHairPart5;
	private ModelPart rightRibbonPart1;
	private ModelPart rightRibbonPart2;
	private ModelPart leftRibbonPart1;
	private ModelPart leftRibbonPart2;
	private ModelPart ahoge;

	public BansheeModel(ModelPart modelPart)
	{
		super(modelPart);
		this.skirt1 = this.body.getChild("skirt_1");
		this.bodyRibbon1 = this.skirt1.getChild("body_ribbon_1");
		this.bodyRibbon2 = this.skirt1.getChild("body_ribbon_2");
		this.hairPart1 = this.head.getChild("hair_part_1");
		this.hairPart2 = this.hairPart1.getChild("hair_part_2");
		this.rightHairPart1 = this.head.getChild("right_hair_part_1");
		this.rightHairPart2 = this.rightHairPart1.getChild("right_hair_part_2");
		this.rightHairPart3 = this.rightHairPart2.getChild("right_hair_part_3");
		this.rightHairPart4 = this.rightHairPart3.getChild("right_hair_part_4");
		this.rightHairPart5 = this.rightHairPart4.getChild("right_hair_part_5");
		this.leftHairPart1 = this.head.getChild("left_hair_part_1");
		this.leftHairPart2 = this.leftHairPart1.getChild("left_hair_part_2");
		this.leftHairPart3 = this.leftHairPart2.getChild("left_hair_part_3");
		this.leftHairPart4 = this.leftHairPart3.getChild("left_hair_part_4");
		this.leftHairPart5 = this.leftHairPart4.getChild("left_hair_part_5");
		this.rightRibbonPart1 = this.rightHairPart2.getChild("right_ribbon_part_1");
		this.rightRibbonPart2 = this.rightHairPart2.getChild("right_ribbon_part_2");
		this.leftRibbonPart1 = this.leftHairPart2.getChild("left_ribbon_part_1");
		this.leftRibbonPart2 = this.leftHairPart2.getChild("left_ribbon_part_2");
		this.ahoge = this.head.getChild("ahoge");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition bodypd = pd.getChild("body");
		PartDefinition bodypart1pd = ModClientUtils.addC(bodypd, cd, "body_part_1", 32, 32, -2.5F, 0.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 7.0F, 0.0F);
		ModClientUtils.addC(bodypart1pd, cd, "body_part_2", 32, 40, -3.0F, 0.0F, -1.5F, 6.0F, 1.0F, 3.0F, 0.0F, 1.5F, 0.0F);
		PartDefinition s1pd = ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 40, -4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, 0.0F, 9.0F, 0.0F);
		PartDefinition s2pd = ModClientUtils.addC(s1pd, cd, "skirt_2", 0, 48, -4.5F, 0.0F, -3.5F, 9.0F, 2.0F, 7.0F, 0.0F, 2.0F, 0.0F);
		PartDefinition s3pd = ModClientUtils.addC(s2pd, cd, "skirt_3", 0, 64, -5.0F, 0.0F, -4.5F, 10.0F, 2.0F, 9.0F, 0.0F, 2.0F, 0.0F);
		ModClientUtils.addC(s3pd, cd, "skirt_4", 0, 80, -5.5F, 0.0F, -5.5F, 11.0F, 4.0F, 11.0F, 0.0F, 2.0F, 0.0F);
		ModClientUtils.addC(s1pd, cd, "body_ribbon_1", 16, 32, -2.5F, -2.5F, -1.0F, 3.0F, 5.0F, 1.0F, 0.0F, 0.0F, -1.75F, -0.25F);
		ModClientUtils.addC(s1pd, cd, "body_ribbon_2", 24, 32, -0.5F, -2.5F, -1.0F, 3.0F, 5.0F, 1.0F, 0.0F, 0.0F, -1.75F, -0.25F);
		PartDefinition headpd = pd.getChild("head");
		PartDefinition h1pd = ModClientUtils.addC(headpd, cd, "hair_part_1", 32, 96, -4.0F, 0.0F, -1.0F, 8.0F, 5.0F, 1.0F, 0.0F, 0.0F, 4.0F);
		ModClientUtils.addC(h1pd, cd, "hair_part_2", 32, 104, -6.0F, 0.0F, -1.0F, 12.0F, 10.0F, 1.0F, 0.0F, 5.0F, 0.0F);
		PartDefinition rhp1pd = ModClientUtils.addC(headpd, cd, "right_hair_part_1", 40, 56, -1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, -3.75F, -7.0F, -1.0F);
		PartDefinition rhp2pd = ModClientUtils.addC(rhp1pd, cd, "right_hair_part_2", 48, 64, -1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, -1.5F, 1.0F, 1.5F);
		PartDefinition rhp3pd = ModClientUtils.addC(rhp2pd, cd, "right_hair_part_3", 40, 64, -1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 3.5F, 0.0F, 0.25F);
		PartDefinition rhp4pd = ModClientUtils.addC(rhp3pd, cd, "right_hair_part_4", 48, 80, -1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, 6.0F, 0.0F);
		ModClientUtils.addC(rhp4pd, cd, "right_hair_part_5", 48, 72, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 4.0F, 0.0F);
		PartDefinition lhp1pd = ModClientUtils.addC(headpd, cd, "left_hair_part_1", 40, 56, -1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, 3.75F, -7.0F, -1.0F, true);
		PartDefinition lhp2pd = ModClientUtils.addC(lhp1pd, cd, "left_hair_part_2", 48, 64, -1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, 1.5F, 1.0F, 1.5F, true);
		PartDefinition lhp3pd = ModClientUtils.addC(lhp2pd, cd, "left_hair_part_3", 40, 64, -1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 3.5F, 0.0F, true, 0.25F);
		PartDefinition lhp4pd = ModClientUtils.addC(lhp3pd, cd, "left_hair_part_4", 48, 80, -1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, 6.0F, 0.0F, true);
		ModClientUtils.addC(lhp4pd, cd, "left_hair_part_5", 48, 72, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 4.0F, 0.0F, true);
		ModClientUtils.addC(rhp2pd, cd, "right_ribbon_part_1", 16, 32, -2.5F, -2.5F, -1.0F, 3.0F, 5.0F, 1.0F, -0.5F, -0.5F, 0.0F, -0.25F);
		ModClientUtils.addC(rhp2pd, cd, "right_ribbon_part_2", 24, 32, -0.5F, -2.5F, -1.0F, 3.0F, 5.0F, 1.0F, -0.5F, -0.5F, 0.0F, -0.25F);
		ModClientUtils.addC(lhp2pd, cd, "left_ribbon_part_1", 16, 32, -2.5F, -2.5F, -1.0F, 3.0F, 5.0F, 1.0F, 0.5F, -0.5F, 0.0F, -0.25F);
		ModClientUtils.addC(lhp2pd, cd, "left_ribbon_part_2", 24, 32, -0.5F, -2.5F, -1.0F, 3.0F, 5.0F, 1.0F, 0.5F, -0.5F, 0.0F, -0.25F);
		ModClientUtils.addC(headpd, cd, "ahoge", 0, 96, -2.5F, -4.0F, 0.0F, 5.0F, 4.0F, 1.0F, 0.0F, -7.75F, 0.0F, -0.25F);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (entity.getAttackPhase() == 0)
		{
			this.head.xRot = (float)Math.PI / 10.0F;
			this.hat.xRot = (float)Math.PI / 10.0F;
		}
		else
		{
			this.head.xRot = 0.0F;
			this.hat.xRot = 0.0F;
		}

		this.rightLeg.zRot = (float)Math.PI / 24.0F;
		this.leftLeg.zRot = -((float)Math.PI / 24.0F);

		float f = Mth.sin(this.attackTime * (float)Math.PI);
		float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);

		if (entity.getAttackPhase() != 0)
		{
			AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, entity.isCharging(), this.attackTime, ageInTicks);
		}
		else
		{
			this.rightArm.zRot = (float)Math.PI / 10.0F;
			this.leftArm.zRot = -((float)Math.PI / 10.0F);
			this.rightArm.yRot = -(0.1F - f * 0.6F);
			this.leftArm.yRot = 0.1F - f * 0.6F;
			this.rightArm.xRot = -((float)Math.PI * 7.0F / 11.0F);
			this.leftArm.xRot = -((float)Math.PI * 7.0F / 11.0F);
			this.rightArm.xRot -= f * 1.2F - f1 * 0.2F;
			this.leftArm.xRot -= f * 1.2F - f1 * 0.2F;
			this.rightArm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.01F + 0.05F;
			this.leftArm.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.01F + 0.05F;
			this.rightArm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.01F;
			this.leftArm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.01F;
		}

		this.rightLeg.xRot = ((float)Math.PI / 18.0F);
		this.leftLeg.xRot = ((float)Math.PI / 18.0F);
		this.rightLeg.xRot -= f * 1.2F - f1 * 0.4F;
		this.leftLeg.xRot -= f * 1.2F - f1 * 0.4F;
		this.rightLeg.xRot += Mth.sin(ageInTicks * 0.06F) * 0.03F;
		this.leftLeg.xRot -= Mth.sin(ageInTicks * 0.06F) * 0.03F;

		this.hairPart1.xRot = ((float)Math.PI / 18.0F);
		this.hairPart1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.045F;
		this.hairPart2.xRot = ((float)Math.PI / 18.0F);
		this.hairPart2.xRot += Mth.sin(ageInTicks * 0.06F - (float)Math.PI / 6.0F) * 0.045F;

		float f2 = ((float)Math.PI / 9.0F) + Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.06F;
		float f3 = (float)Math.PI * 2.0F / 5.0F;
		float f4 = (float)Math.PI / 15.0F;

		this.bodyRibbon1.yRot = -f2;
		this.bodyRibbon2.yRot = f2;
		this.bodyRibbon1.xRot = -f4;
		this.bodyRibbon2.xRot = -f4;

		this.rightHairPart1.zRot = (float)Math.PI / 27.0F;
		this.leftHairPart1.zRot = -((float)Math.PI / 27.0F);
		this.rightHairPart1.zRot += Mth.sin(ageInTicks * 0.067F) * 0.045F;
		this.leftHairPart1.zRot -= Mth.sin(ageInTicks * 0.067F) * 0.045F;

		this.rightRibbonPart1.xRot = f2;
		this.rightRibbonPart2.xRot = f2;
		this.leftRibbonPart1.xRot = f2;
		this.leftRibbonPart2.xRot = f2;
		this.rightRibbonPart1.yRot = f3 - f2;
		this.rightRibbonPart2.yRot = f3 + f2;
		this.leftRibbonPart1.yRot = -f3 - f2;
		this.leftRibbonPart2.yRot = -f3 + f2;

		f2 = Mth.sin(ageInTicks * 0.06F) * 0.04F;
		f3 = Mth.sin(ageInTicks * 0.045F - (float)Math.PI / 6.0F) * 0.03F;
		f4 = Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.045F;

		this.rightHairPart2.xRot = (float)Math.PI / 27.0F;
		this.leftHairPart2.xRot = ((float)Math.PI / 27.0F);
		this.rightHairPart2.xRot += f2;
		this.leftHairPart2.xRot += f2;
		this.rightHairPart2.zRot = (float)Math.PI / 24.0F;
		this.leftHairPart2.zRot = -((float)Math.PI / 24.0F);
		this.rightHairPart2.zRot -= f3;
		this.leftHairPart2.zRot += f3;
		this.rightHairPart3.zRot = (float)Math.PI / 30.0F;
		this.leftHairPart3.zRot = -((float)Math.PI / 30.0F);
		this.rightHairPart3.zRot -= f4;
		this.leftHairPart3.zRot += f4;
		this.rightHairPart4.zRot = -((float)Math.PI / 27.0F);
		this.leftHairPart4.zRot = (float)Math.PI / 27.0F;
		this.rightHairPart5.xRot = 0.0F;
		this.leftHairPart5.xRot = 0.0F;

		this.ahoge.xRot = -((float)Math.PI / 12.0F);
		this.ahoge.xRot += Mth.cos(ageInTicks * 0.03F + (float)Math.PI) * 0.09F;
	}
}