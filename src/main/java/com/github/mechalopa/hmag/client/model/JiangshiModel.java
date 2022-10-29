package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.JiangshiEntity;

import net.minecraft.client.model.AnimationUtils;
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
public class JiangshiModel<T extends JiangshiEntity> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart headPart1;
	private ModelPart headPart2Front;
//	private ModelPart headPart2Right;
//	private ModelPart headPart2Left;
//	private ModelPart headPart2Back;
	private ModelPart ofuda1;
	private ModelPart ofuda2;
	private ModelPart ofuda3;
	private ModelPart hairPart1;
	private ModelPart hairPart2;
	private ModelPart hairPart3;
	private ModelPart hairPart4;
	private ModelPart hairPart5;
	private ModelPart hairPart6;
	private ModelPart skirt1;
	private ModelPart skirt2;
	private ModelPart rightSkirtOfuda;
	private ModelPart leftSkirtOfuda;
	private ModelPart frontClothPart1;
	private ModelPart frontClothPart2;
	private ModelPart backClothPart1;
	private ModelPart backClothPart2;
//	private ModelPart rightArmPart1;
//	private ModelPart leftArmPart1;
	private float animationAmount;

	public JiangshiModel(ModelPart modelPart)
	{
		super(modelPart);
		this.headPart1 = this.head.getChild("head_part_1");
		this.headPart2Front = this.headPart1.getChild("head_part_2_front");
//		this.headPart2Right = this.headPart1.getChild("head_part_2_right");
//		this.headPart2Left = this.headPart1.getChild("head_part_2_left");
//		this.headPart2Back = this.headPart1.getChild("head_part_2_back");
		this.ofuda1 = this.headPart2Front.getChild("ofuda_1");
		this.ofuda2 = this.ofuda1.getChild("ofuda_2");
		this.ofuda3 = this.ofuda2.getChild("ofuda_3");
		this.hairPart1 = this.head.getChild("hair_part_1");
		this.hairPart2 = this.hairPart1.getChild("hair_part_2");
		this.hairPart3 = this.hairPart2.getChild("hair_part_3");
		this.hairPart4 = this.hairPart3.getChild("hair_part_4");
		this.hairPart5 = this.hairPart4.getChild("hair_part_5");
		this.hairPart6 = this.hairPart4.getChild("hair_part_6");
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.skirt1.getChild("skirt_2");
		this.rightSkirtOfuda = this.skirt2.getChild("right_skirt_ofuda");
		this.leftSkirtOfuda = this.skirt2.getChild("left_skirt_ofuda");
		this.frontClothPart1 = this.body.getChild("front_cloth_part_1");
		this.frontClothPart2 = this.frontClothPart1.getChild("front_cloth_part_2");
		this.backClothPart1 = this.body.getChild("back_cloth_part_1");
		this.backClothPart2 = this.backClothPart1.getChild("back_cloth_part_2");
//		this.rightArmPart1 = this.rightArm.getChild("right_arm_part_1");
//		this.leftArmPart1 = this.leftArm.getChild("left_arm_part_1");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = pd.getChild("head");
		PartDefinition hp1pd = ModClientUtils.addC(headpd, cd, "head_part_1", 0, 64, -5.0F, -1.0F, -5.0F, 10.0F, 2.0F, 10.0F, 0.0F, -6.5F, 0.0F);
		float f = (float)Math.PI / 12.0F;
		PartDefinition hp2fpd = ModClientUtils.addC(hp1pd, cd, "head_part_2_front", 0, 80, -5.0F, -3.0F, 0.0F, 10.0F, 3.0F, 1.0F, 0.0F, 1.0F, -5.0F, f, 0.0F, 0.0F);
		ModClientUtils.addC(hp1pd, cd, "head_part_2_right", 24, 80, 0.0F, -3.0F, -5.0F, 1.0F, 3.0F, 10.0F, -5.0F, 1.0F, 0.0F, 0.0F, 0.0F, -f);
		ModClientUtils.addC(hp1pd, cd, "head_part_2_left", 24, 80, -1.0F, -3.0F, -5.0F, 1.0F, 3.0F, 10.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F, f, true);
		ModClientUtils.addC(hp1pd, cd, "head_part_2_back", 0, 84, -5.0F, -3.0F, -1.0F, 10.0F, 3.0F, 1.0F, 0.0F, 1.0F, 5.0F, -f, 0.0F, 0.0F);
		PartDefinition hp3pd = ModClientUtils.addC(hp1pd, cd, "head_part_3", 0, 96, -4.5F, 0.0F, -4.5F, 9.0F, 1.0F, 9.0F, 0.0F, -2.0F, 0.0F);
		PartDefinition hp4pd = ModClientUtils.addC(hp3pd, cd, "head_part_4", 0, 112, -3.5F, 0.0F, -3.5F, 7.0F, 1.0F, 7.0F, 0.0F, -0.75F, 0.0F);
		ModClientUtils.addC(hp4pd, cd, "head_part_5", 32, 112, -1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, -1.0F, 0.0F);
		PartDefinition o1pd = ModClientUtils.addC(hp2fpd, cd, "ofuda_1", 20, 32, -1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F, -1.5F, 0.0F);
		PartDefinition o2pd = ModClientUtils.addC(o1pd, cd, "ofuda_2", 20, 36, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, 2.0F, 0.0F);
		ModClientUtils.addC(o2pd, cd, "ofuda_3", 24, 40, -1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F, 3.0F, 0.0F);
		PartDefinition hairp1pd = ModClientUtils.addC(headpd, cd, "hair_part_1", 48, 32, -1.0F, 0.0F, -1.5F, 2.0F, 5.0F, 2.0F, 0.0F, -1.0F, 4.0F, -0.25F);
		PartDefinition hairp2pd = ModClientUtils.addC(hairp1pd, cd, "hair_part_2", 50, 40, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 4.25F, -0.5F, -0.245F);
		PartDefinition hairp3pd = ModClientUtils.addC(hairp2pd, cd, "hair_part_3", 48, 48, -1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 4.25F, 0.0F, -0.25F);
		PartDefinition hairp4pd = ModClientUtils.addC(hairp3pd, cd, "hair_part_4", 56, 52, -0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 4.5F, 0.0F);
		ModClientUtils.addC(hairp4pd, cd, "hair_part_5", 56, 48, -1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, 1.5F, 0.0F);
		ModClientUtils.addC(hairp4pd, cd, "hair_part_6", 60, 52, -0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, 3.25F, 0.0F, 0.0625F);
		PartDefinition bodypd = pd.getChild("body");
		PartDefinition bodypart1pd = ModClientUtils.addC(bodypd, cd, "body_part_1", 32, 32, -2.5F, 0.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 7.0F, 0.0F);
		ModClientUtils.addC(bodypart1pd, cd, "body_part_2", 32, 40, -3.0F, 0.0F, -1.5F, 6.0F, 1.0F, 3.0F, 0.0F, 1.5F, 0.0F);
		PartDefinition s1pd = ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -3.5F, 0.0F, -2.0F, 7.0F, 2.0F, 4.0F, 0.0F, 9.5F, 0.0F);
		PartDefinition s2pd = ModClientUtils.addC(s1pd, cd, "skirt_2", 0, 44, -4.0F, 0.0F, -2.5F, 8.0F, 5.0F, 5.0F, 0.0F, 2.0F, 0.0F);
		ModClientUtils.addC(s2pd, cd, "right_skirt_ofuda", 16, 54, 0.0F, 0.0F, -2.5F, 1.0F, 5.0F, 5.0F, -4.5F, 2.0F, 0.0F, -0.5F);
		ModClientUtils.addC(s2pd, cd, "left_skirt_ofuda", 16, 54, -1.0F, 0.0F, -2.5F, 1.0F, 5.0F, 5.0F, 4.5F, 2.0F, 0.0F, true, -0.5F);
		PartDefinition fcp1pd = ModClientUtils.addC(bodypd, cd, "front_cloth_part_1", 48, 56, -2.5F, 0.0F, 0.0F, 5.0F, 3.0F, 1.0F, 0.0F, 9.5F, -2.0F);
		ModClientUtils.addC(fcp1pd, cd, "front_cloth_part_2", 48, 60, -2.5F, 0.0F, 0.0F, 5.0F, 6.0F, 1.0F, 0.0F, 3.0F, 0.0F);
		PartDefinition bcp1pd = ModClientUtils.addC(bodypd, cd, "back_cloth_part_1", 48, 68, -2.5F, 0.0F, -1.0F, 5.0F, 3.0F, 1.0F, 0.0F, 9.5F, 2.0F);
		ModClientUtils.addC(bcp1pd, cd, "back_cloth_part_2", 48, 72, -2.5F, 0.0F, -1.0F, 5.0F, 6.0F, 1.0F, 0.0F, 3.0F, 0.0F);
		PartDefinition rapd = pd.getChild("right_arm");
		PartDefinition lapd = pd.getChild("left_arm");
		ModClientUtils.addC(rapd, cd, "right_arm_part_1", 0, 54, -1.5F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, 3.0F, 0.0F);
		ModClientUtils.addC(lapd, cd, "left_arm_part_1", 0, 54, -2.5F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, 3.0F, 0.0F, true);
		ModClientUtils.addC(pd, cd, "left_leg", 32, 48, -1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 1.9F, 12.0F, 0.0F);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 128);
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks)
	{
		super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
		this.animationAmount = Mth.clamp(entity.getAnimationScale(partialTicks), 0.0F, 1.0F);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.xRot = 0.0F;
		this.head.zRot = 0.0F;

		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.head.xRot += Mth.cos(limbSwing * 0.3F) * 0.15F * limbSwingAmount;
		this.head.zRot += Mth.sin(limbSwing * 0.1F) * 0.1F * limbSwingAmount * (entity.getMainArm() == HumanoidArm.RIGHT ? 1.0F : -1.0F);
		this.hat.copyFrom(this.head);

		this.headPart1.xRot = -((float)Math.PI * 0.014F);

		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entity), this.attackTime, ageInTicks);

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
			this.frontClothPart1.xRot = -((float)Math.PI * 7.0F / 20.0F);
			this.frontClothPart2.xRot = -((float)Math.PI / 18.0F);
			this.backClothPart1.xRot = (float)Math.PI / 7.0F;
			this.backClothPart2.xRot = (float)Math.PI * 7.0F / 20.0F;
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
			this.rightLeg.xRot = -0.75F * Mth.triangleWave(limbSwing, 7.5F) * limbSwingAmount;
			this.leftLeg.xRot = 0.75F * Mth.triangleWave(limbSwing, 7.5F) * limbSwingAmount;

			if (this.animationAmount > 0.0F)
			{
				this.rightLeg.xRot *= 1.0F - this.animationAmount;
				this.leftLeg.xRot *= 1.0F - this.animationAmount;
				this.rightLeg.yRot *= 1.0F - this.animationAmount;
				this.leftLeg.yRot *= 1.0F - this.animationAmount;
				this.rightLeg.zRot *= 1.0F - this.animationAmount;
				this.leftLeg.zRot *= 1.0F - this.animationAmount;
				this.rightLeg.xRot += ((float)Math.PI / 45.0F) * this.animationAmount;
				this.leftLeg.xRot += ((float)Math.PI / 45.0F) * this.animationAmount;
				this.rightLeg.zRot += -((float)Math.PI / 60.0F) * this.animationAmount;
				this.leftLeg.zRot += ((float)Math.PI / 60.0F) * this.animationAmount;
			}

			this.frontClothPart1.xRot = -((float)Math.PI / 9.0F);
			this.frontClothPart1.xRot += Mth.sin(ageInTicks * 0.067F) * 0.021F;
			this.frontClothPart2.xRot = (float)Math.PI / 11.0F;
			this.frontClothPart2.xRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 6.0F) * 0.021F;
			this.frontClothPart2.xRot += Mth.cos(limbSwing * 0.45F + (float)Math.PI / 3.0F) * 0.3F * limbSwingAmount;

			this.backClothPart1.xRot = (float)Math.PI / 9.0F;
			this.backClothPart1.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.021F;
			this.backClothPart2.xRot = -((float)Math.PI / 11.0F);
			this.backClothPart2.xRot -= Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 6.0F) * 0.021F;
			this.backClothPart2.xRot -= Mth.cos(limbSwing * 0.45F + (float)Math.PI / 3.0F) * 0.3F * limbSwingAmount;
		}

		this.skirt1.xRot = 0.0F;

		this.ofuda1.zRot = (((float)Math.PI / 8.0F) * Mth.triangleWave(limbSwing + ((float)Math.PI / 4.0F), 15.0F) * limbSwingAmount);
		this.ofuda1.xRot = -((float)Math.PI / 8.0F);
		this.ofuda1.xRot += Mth.sin(ageInTicks * 0.075F) * 0.045F;
		this.ofuda2.xRot = (float)Math.PI / 15.0F;
		this.ofuda2.xRot += Mth.sin(ageInTicks * 0.075F + (float)Math.PI / 8.0F) * 0.054F;
		this.ofuda3.xRot = -((float)Math.PI / 7.0F);
		this.ofuda3.xRot += Mth.sin(ageInTicks * 0.075F + (float)Math.PI / 4.0F) * 0.06F;

		this.rightSkirtOfuda.zRot = (float)Math.PI / 18.0F;
		this.leftSkirtOfuda.zRot = -((float)Math.PI / 18.0F);
		this.rightSkirtOfuda.zRot += Mth.cos(ageInTicks * 0.067F + (float)Math.PI / 4.0F) * 0.03F;
		this.leftSkirtOfuda.zRot -= Mth.cos(ageInTicks * 0.067F + (float)Math.PI / 4.0F) * 0.03F;
		this.rightSkirtOfuda.xRot = (float)Math.PI / 24.0F;
		this.leftSkirtOfuda.xRot = (float)Math.PI / 24.0F;
		this.rightSkirtOfuda.xRot += Mth.sin(ageInTicks * 0.045F) * 0.015F;
		this.leftSkirtOfuda.xRot += Mth.sin(ageInTicks * 0.045F) * 0.015F;

		this.hairPart1.xRot = (float)Math.PI / 15.0F;
		this.hairPart1.xRot += Mth.cos(ageInTicks * 0.067F) * 0.03F;
		this.hairPart1.xRot += (Mth.cos(limbSwing * 0.45F) * 0.09F + ((float)Math.PI / 9.0F)) * limbSwingAmount;
		this.hairPart2.xRot = (float)Math.PI / 24.0F;
		this.hairPart2.xRot += Mth.cos(ageInTicks * 0.067F + (float)Math.PI / 8.0F) * 0.03F;
		this.hairPart2.xRot += (Mth.cos(limbSwing * 0.45F) * 0.015F + ((float)Math.PI / 18.0F)) * limbSwingAmount;
		this.hairPart3.xRot = (float)Math.PI / 21.0F;
		this.hairPart3.xRot += Mth.cos(ageInTicks * 0.067F + (float)Math.PI / 4.0F) * 0.03F;
		this.hairPart3.xRot += (Mth.cos(limbSwing * 0.45F) * 0.015F + ((float)Math.PI / 18.0F)) * limbSwingAmount;
		this.hairPart4.xRot = (float)Math.PI / 21.0F;
		this.hairPart4.xRot += Mth.cos(ageInTicks * 0.067F + (float)Math.PI * 3.0F / 8.0F) * 0.012F;
		this.hairPart5.yRot = (float)Math.PI / 4.0F;
		this.hairPart6.yRot = (float)Math.PI / 4.0F;
		this.hairPart1.zRot = Mth.sin(ageInTicks * 0.045F) * 0.036F;
		this.hairPart1.zRot += (((float)Math.PI / 12.0F) * Mth.triangleWave(limbSwing + ((float)Math.PI / 8.0F), 15.0F) * limbSwingAmount);
		this.hairPart2.zRot = Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 8.0F) * 0.03F;
		this.hairPart2.zRot += (((float)Math.PI / 9.0F) * Mth.triangleWave(limbSwing + ((float)Math.PI / 4.0F), 15.0F) * limbSwingAmount);
		this.hairPart3.zRot = Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.015F;
		this.hairPart2.zRot += (((float)Math.PI / 9.0F) * Mth.triangleWave(limbSwing + ((float)Math.PI * 3.0F / 8.0F), 15.0F) * limbSwingAmount);
	}

	public boolean isAggressive(T entity)
	{
		return entity.isAggressive();
	}
}