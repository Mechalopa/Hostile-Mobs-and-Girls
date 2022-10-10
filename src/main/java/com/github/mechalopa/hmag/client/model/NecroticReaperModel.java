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
public class NecroticReaperModel<T extends Mob> extends AbstractGirlModel<T>
{
	private ModelPart bodyPart1;
	private ModelPart bodyPart2;
	private ModelPart bodyPart3;
	private ModelPart bodyPart4;
	private ModelPart bodyPart5;
	private ModelPart bodyPart6RightA;
	private ModelPart bodyPart6LeftA;
	private ModelPart bodyPart6RightB;
	private ModelPart bodyPart6LeftB;
	private ModelPart bodyPart6RightC;
	private ModelPart bodyPart6LeftC;
	private ModelPart bust;
//	private ModelPart clothPart;
	private ModelPart rightHair1;
	private ModelPart rightHair2;
	private ModelPart rightHair3;
	private ModelPart rightHair4;
	private ModelPart leftHair1;
	private ModelPart leftHair2;
	private ModelPart leftHair3;
	private ModelPart leftHair4;
	private ModelPart tailPart1;
	private ModelPart tailPart2;
	private ModelPart tailPart3;
	private ModelPart tailPart4;
	private ModelPart tailPart1Fin;
	private ModelPart tailPart2Fin;
	private ModelPart hairBand;
	private ModelPart hairPart;
//	private ModelPart headwearPart;
	private ModelPart skirt1;
	private ModelPart skirt2;
	private ModelPart[] rightSkirtPart = new ModelPart[7];
	private ModelPart[] leftSkirtPart = new ModelPart[7];
	private ModelPart rightArmPart1;
	private ModelPart leftArmPart1;
	private ModelPart rightArmPart2;
	private ModelPart leftArmPart2;
	private ModelPart rightArmPart3;
	private ModelPart leftArmPart3;
	private ModelPart rightArmPart4A;
	private ModelPart leftArmPart4A;
	private ModelPart rightArmPart4B;
	private ModelPart leftArmPart4B;
	private ModelPart rightArmPart5;
	private ModelPart leftArmPart5;
	private ModelPart rightArmPart6;
	private ModelPart leftArmPart6;
	private ModelPart rightLegPart1;
	private ModelPart leftLegPart1;
	private ModelPart rightLegPart2;
	private ModelPart leftLegPart2;
	private ModelPart rightLegPart3;
	private ModelPart leftLegPart3;

	public NecroticReaperModel(ModelPart modelPart)
	{
		super(modelPart);
		this.bodyPart1 = this.body.getChild("body_part_1");
		this.bodyPart2 = this.bodyPart1.getChild("body_part_2");
		this.bodyPart3 = this.body.getChild("body_part_3");
		this.bodyPart4 = this.bodyPart3.getChild("body_part_4");
		this.bodyPart5 = this.bodyPart3.getChild("body_part_5");
		this.bodyPart6RightA = this.bodyPart1.getChild("body_part_6_right_a");
		this.bodyPart6LeftA = this.bodyPart1.getChild("body_part_6_left_a");
		this.bodyPart6RightB = this.bodyPart1.getChild("body_part_6_right_b");
		this.bodyPart6LeftB = this.bodyPart1.getChild("body_part_6_left_b");
		this.bodyPart6RightC = this.bodyPart2.getChild("body_part_6_right_c");
		this.bodyPart6LeftC = this.bodyPart2.getChild("body_part_6_left_c");
		this.bust = this.body.getChild("bust");
//		this.clothPart = this.body.getChild("cloth_part");
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.body.getChild("skirt_2");
		Arrays.setAll(this.rightSkirtPart, (p) -> {
			return this.body.getChild("right_skirt_part_" + p);
		});
		Arrays.setAll(this.leftSkirtPart, (p) -> {
			return this.body.getChild("left_skirt_part_" + p);
		});
		this.rightHair1 = this.head.getChild("right_hair_1");
		this.rightHair2 = this.rightHair1.getChild("right_hair_2");
		this.rightHair3 = this.rightHair2.getChild("right_hair_3");
		this.rightHair4 = this.rightHair3.getChild("right_hair_4");
		this.leftHair1 = this.head.getChild("left_hair_1");
		this.leftHair2 = this.leftHair1.getChild("left_hair_2");
		this.leftHair3 = this.leftHair2.getChild("left_hair_3");
		this.leftHair4 = this.leftHair3.getChild("left_hair_4");
		this.tailPart1 = this.head.getChild("tail_part_1");
		this.tailPart2 = this.tailPart1.getChild("tail_part_2");
		this.tailPart3 = this.tailPart2.getChild("tail_part_3");
		this.tailPart4 = this.tailPart3.getChild("tail_part_4");
		this.tailPart1Fin = this.tailPart1.getChild("tail_part_1_fin");
		this.tailPart2Fin = this.tailPart2.getChild("tail_part_2_fin");
		this.hairBand = this.head.getChild("hair_band");
		this.hairPart = this.head.getChild("hair_part");
		this.rightArmPart1 = this.rightArm.getChild("right_arm_part_1");
		this.leftArmPart1 = this.leftArm.getChild("left_arm_part_1");
		this.rightArmPart2 = this.rightArmPart1.getChild("right_arm_part_2");
		this.leftArmPart2 = this.leftArmPart1.getChild("left_arm_part_2");
		this.rightArmPart3 = this.rightArmPart2.getChild("right_arm_part_3");
		this.leftArmPart3 = this.leftArmPart2.getChild("left_arm_part_3");
		this.rightArmPart4A = this.rightArmPart2.getChild("right_arm_part_4a");
		this.leftArmPart4A = this.leftArmPart2.getChild("left_arm_part_4a");
		this.rightArmPart4B = this.rightArmPart2.getChild("right_arm_part_4b");
		this.leftArmPart4B = this.leftArmPart2.getChild("left_arm_part_4b");
		this.rightArmPart5 = this.rightArmPart2.getChild("right_arm_part_5");
		this.leftArmPart5 = this.leftArmPart2.getChild("left_arm_part_5");
		this.rightArmPart6 = this.rightArmPart5.getChild("right_arm_part_6");
		this.leftArmPart6 = this.leftArmPart5.getChild("left_arm_part_6");
		this.rightLegPart1 = this.rightLeg.getChild("right_leg_part_1");
		this.leftLegPart1 = this.leftLeg.getChild("left_leg_part_1");
		this.rightLegPart2 = this.rightLeg.getChild("right_leg_part_2");
		this.leftLegPart2 = this.leftLeg.getChild("left_leg_part_2");
		this.rightLegPart3 = this.rightLegPart2.getChild("right_leg_part_3");
		this.leftLegPart3 = this.leftLegPart2.getChild("left_leg_part_3");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition rapd = ModClientUtils.addC(pd, cd, "right_arm", 40, 16, 0.0F, -2.0F, -1.5F, 2.0F, 1.0F, 3.0F, -5.0F, 2.0F, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.5F, 2.0F, 1.0F, 3.0F, 5.0F, 2.0F, 0.0F, true);
		PartDefinition rlpd = ModClientUtils.addC(pd, cd, "right_leg", 0, 16, -1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, -1.75F, 12.0F, 0.0F);
		PartDefinition llpd = ModClientUtils.addC(pd, cd, "left_leg", 0, 16, -1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 1.75F, 12.0F, 0.0F, true);
		PartDefinition headpd = pd.getChild("head");
		PartDefinition bodypd = ModClientUtils.addC(pd, cd, "body", 16, 16, -3.0F, 0.0F, -1.5F, 6.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		PartDefinition bp1pd = ModClientUtils.addC(bodypd, cd, "body_part_1", 32, 32, -1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 3.75F, 0.5F);
		PartDefinition bp2pd = ModClientUtils.addC(bp1pd, cd, "body_part_2", 40, 32, -1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 2.5F, 0.0F, 0.005F);
		PartDefinition bp3pd = ModClientUtils.addC(bodypd, cd, "body_part_3", 48, 32, -2.5F, 0.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, 9.0F, 0.0F);
		ModClientUtils.addC(bp3pd, cd, "body_part_4", 48, 36, -2.5F, -1.0F, -1.0F, 5.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		ModClientUtils.addC(bp3pd, cd, "body_part_5", 32, 40, -3.0F, 0.0F, -1.5F, 6.0F, 2.0F, 3.0F, 0.0F, 1.0F, 0.0F);
		ModClientUtils.addC(bp1pd, cd, "body_part_6_right_a", 50, 40, 0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, 0.5F, 0.5F, 0.5F);
		ModClientUtils.addC(bp1pd, cd, "body_part_6_left_a", 50, 40, -1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, -0.5F, 0.5F, 0.5F, true);
		ModClientUtils.addC(bp1pd, cd, "body_part_6_right_b", 50, 40, 0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, 0.5F, 2.5F, 0.5F);
		ModClientUtils.addC(bp1pd, cd, "body_part_6_left_b", 50, 40, -1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, -0.5F, 2.5F, 0.5F, true);
		ModClientUtils.addC(bp2pd, cd, "body_part_6_right_c", 50, 40, 0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, 0.5F, 2.0F, 0.5F);
		ModClientUtils.addC(bp2pd, cd, "body_part_6_left_c", 50, 40, -1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, -0.5F, 2.0F, 0.5F, true);
		ModClientUtils.addC(bodypd, cd, "bust", 0, 32, -3.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, 0.0F, 3.5F, -1.1F, -0.001F);
		ModClientUtils.addC(bodypd, cd, "cloth_part", 16, 24, -3.0F, 0.0F, -1.5F, 6.0F, 4.0F, 3.0F, 0.0F, 4.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 74, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_2", 0, 80, -4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, 0.0F, 11.5F, 0.0F);
		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 88).addBox(-1.5F, 0.5F, -0.25F, 3.0F, 6.0F, 2.0F);
		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(0, 88).mirror().addBox(-1.5F, 0.5F, -0.25F, 3.0F, 6.0F, 2.0F);

		for (int k = 0; k < 7; ++k)
		{
			float f = ((float)k - 3) * ((float)Math.PI / 7.0F);
			float f1 = Mth.sin(f - ((float)Math.PI / 2.0F)) * 5.5F;
			float f2 = Mth.cos(f - ((float)Math.PI / 2.0F)) * 3.5F;
			PartPose pp = PartPose.offset(f1, 11.0F, f2);
			bodypd.addOrReplaceChild("right_skirt_part_" + k, cubelistbuilder, pp);
			f = (3 - (float)k) * ((float)Math.PI / 7.0F);
			f1 = Mth.sin(f + ((float)Math.PI / 2.0F)) * 5.5F;
			f2 = Mth.cos(f + ((float)Math.PI / 2.0F)) * 3.5F;
			PartPose pp1 = PartPose.offset(f1, 11.0F, f2);
			bodypd.addOrReplaceChild("left_skirt_part_" + k, cubelistbuilder1, pp1);
		}

		PartDefinition rhp1pd = ModClientUtils.addC(headpd, cd, "right_hair_1", 32, 48, -2.0F, -1.5F, -1.5F, 3.0F, 6.0F, 3.0F, -4.0F, -8.0F, 2.75F);
		PartDefinition rhp2pd = ModClientUtils.addC(rhp1pd, cd, "right_hair_2", 48, 48, -1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, -0.5F, 5.0F, 0.0F, 0.125F);
		PartDefinition rhp3pd = ModClientUtils.addC(rhp2pd, cd, "right_hair_3", 56, 48, -1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 4.5F, 0.0F);
		ModClientUtils.addC(rhp3pd, cd, "right_hair_4", 48, 56, -0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 2.5F, 0.0F, 0.25F);
		PartDefinition lhp1pd = ModClientUtils.addC(headpd, cd, "left_hair_1", 32, 48, -1.0F, -1.5F, -1.5F, 3.0F, 6.0F, 3.0F, 4.0F, -8.0F, 2.75F, true);
		PartDefinition lhp2pd = ModClientUtils.addC(lhp1pd, cd, "left_hair_2", 48, 48, -1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, 0.5F, 5.0F, 0.0F, true, 0.125F);
		PartDefinition lhp3pd = ModClientUtils.addC(lhp2pd, cd, "left_hair_3", 56, 48, -1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 4.5F, 0.0F, true);
		ModClientUtils.addC(lhp3pd, cd, "left_hair_4", 48, 56, -0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 2.5F, 0.0F, true, 0.25F);
		PartDefinition t1pd = ModClientUtils.addC(headpd, cd, "tail_part_1", 32, 80, -1.0F, 0.0F, -0.5F, 2.0F, 8.0F, 1.0F, 0.0F, -3.0F, 4.0F);
		PartDefinition t2pd = ModClientUtils.addC(t1pd, cd, "tail_part_2", 38, 80, -0.5F, 0.0F, -0.5F, 1.0F, 10.0F, 1.0F, 0.0F, 7.75F, 0.0F);
		PartDefinition t3pd = ModClientUtils.addC(t2pd, cd, "tail_part_3", 42, 80, -0.5F, 0.0F, -2.0F, 1.0F, 6.0F, 2.0F, 0.0F, 10.0F, 0.5F);
		ModClientUtils.addC(t3pd, cd, "tail_part_4", 48, 80, -0.5F, 0.0F, -1.0F, 1.0F, 6.0F, 1.0F, 0.0F, 6.0F, 0.0F);
		ModClientUtils.addC(t1pd, cd, "tail_part_1_fin", 32, 92, 0.0F, 0.0F, 0.0F, 0.0F, 7.0F, 2.0F, 0.0F, 1.0F, 0.5F);
		ModClientUtils.addC(t2pd, cd, "tail_part_2_fin", 40, 92, 0.0F, 0.0F, 0.0F, 0.0F, 7.0F, 2.0F, 0.0F, 1.0F, 0.5F);
		ModClientUtils.addC(headpd, cd, "hair_band", 32, 58, -2.5F, -2.0F, 0.0F, 5.0F, 2.0F, 1.0F, 0.0F, -8.0F, 1.0F);
		ModClientUtils.addC(headpd, cd, "hair_part", 32, 74, -4.0F, 0.0F, -1.0F, 8.0F, 3.0F, 1.0F, 0.0F, 0.0F, 4.0F);

		PartDefinition hatpd = pd.getChild("hat");
		ModClientUtils.addC(hatpd, cd, "hat_part", 32, 64, -4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, 1.375F, 0.0F, 0.5F);

		PartDefinition rap1pd = ModClientUtils.addC(rapd, cd, "right_arm_part_1", 40, 20, 0.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.5F);
		PartDefinition lap1pd = ModClientUtils.addC(lapd, cd, "left_arm_part_1", 40, 20, -2.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.5F, true);
		PartDefinition rap2pd = ModClientUtils.addC(rap1pd, cd, "right_arm_part_2", 16, 40, 0.0F, -0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 2.5F, 0.0F, -0.125F);
		PartDefinition lap2pd = ModClientUtils.addC(lap1pd, cd, "left_arm_part_2", 16, 40, -2.0F, -0.5F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, 2.5F, 0.0F, true, -0.125F);
		ModClientUtils.addC(rap2pd, cd, "right_arm_part_3", 24, 42, -0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, 0.25F, 4.5F, 1.0F);
		ModClientUtils.addC(lap2pd, cd, "left_arm_part_3", 24, 42, -0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, -0.25F, 4.5F, 1.0F, true);
		ModClientUtils.addC(rap2pd, cd, "right_arm_part_4a", 16, 48, 0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, 0.5F, 0.5F, 0.5F);
		ModClientUtils.addC(lap2pd, cd, "left_arm_part_4a", 16, 48, -1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, -0.5F, 0.5F, 0.5F, true);
		ModClientUtils.addC(rap2pd, cd, "right_arm_part_4b", 16, 48, 0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, 0.5F, 2.0F, 0.25F);
		ModClientUtils.addC(lap2pd, cd, "left_arm_part_4b", 16, 48, -1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, -0.5F, 2.0F, 0.25F, true);
		PartDefinition rap5pd = ModClientUtils.addC(rap2pd, cd, "right_arm_part_5", 24, 32, 0.0F, -0.5F, -1.5F, 1.0F, 8.0F, 2.0F, 0.5F, 5.0F, 0.0F);
		PartDefinition lap5pd = ModClientUtils.addC(lap2pd, cd, "left_arm_part_5", 24, 32, -1.0F, -0.5F, -1.5F, 1.0F, 8.0F, 2.0F, -0.5F, 5.0F, 0.0F, true);
		ModClientUtils.addC(rap5pd, cd, "right_arm_part_6", 0, 56, 0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 11.0F, 0.5F, 2.0F, -0.5F, -0.001F);
		ModClientUtils.addC(lap5pd, cd, "left_arm_part_6", 0, 56, -1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 11.0F, -0.5F, 2.0F, -0.5F, true, -0.001F);

		ModClientUtils.addC(rlpd, cd, "right_leg_part_1", 0, 38, -0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, -1.0F, 5.5F, -0.25F);
		ModClientUtils.addC(llpd, cd, "left_leg_part_1", 0, 38, -0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, 1.0F, 5.5F, -0.25F, true);
		PartDefinition rlp2pd = ModClientUtils.addC(rlpd, cd, "right_leg_part_2", 8, 38, -0.5F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F, -0.24F, 4.25F, 2.0F);
		PartDefinition llp2pd = ModClientUtils.addC(llpd, cd, "left_leg_part_2", 8, 38, -0.5F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F, 0.24F, 4.25F, 2.0F, true);
		ModClientUtils.addC(rlp2pd, cd, "right_leg_part_3", 0, 42, -0.5F, -3.0F, -1.5F, 1.0F, 11.0F, 2.0F, 0.0F, 0.0F, 1.0F);
		ModClientUtils.addC(llp2pd, cd, "left_leg_part_3", 0, 42, -0.5F, -3.0F, -1.5F, 1.0F, 11.0F, 2.0F, 0.0F, 0.0F, 1.0F, true);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 128);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.zRot = 0.0F;

		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.head.z = Mth.cos(limbSwing * 0.3F + (float)Math.PI / 4.0F) * 2.0F * limbSwingAmount;
		this.head.zRot += Mth.cos(ageInTicks * 0.12F) * 0.06F;
		this.hat.copyFrom(this.head);

		if (this.crouching)
		{
			this.body.xRot = 0.5F;
			this.rightLeg.z = 4.0F;
			this.leftLeg.z = 4.0F;
			this.bodyPart5.xRot = 0.0F;
		}
		else
		{
			this.body.xRot = (float)Math.PI * 0.03F;
			this.rightLeg.z = 0.6F;
			this.leftLeg.z = 0.6F;
			this.bodyPart5.xRot = -((float)Math.PI * 0.03F);
		}

		this.bodyPart1.xRot = (float)Math.PI * 0.06F;
		this.bodyPart2.xRot = -((float)Math.PI * 0.12F);

		this.bodyPart4.xRot = 0.0F;

		this.rightLeg.zRot = -((float)Math.PI * 0.01F);
		this.leftLeg.zRot = (float)Math.PI * 0.01F;

		this.rightArm.xRot *= 0.25F;
		this.leftArm.xRot *= 0.25F;
		this.rightArm.yRot *= 0.75F;
		this.leftArm.yRot *= 0.75F;
		this.rightArm.zRot = (float)Math.PI / 12.0F;
		this.leftArm.zRot = -((float)Math.PI / 12.0F);
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.06F) * 0.021F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.06F) * 0.021F;

		this.rightArmPart1.xRot = (float)Math.PI / 11.0F;
		this.leftArmPart1.xRot = (float)Math.PI / 11.0F;
		this.rightArmPart2.xRot = -((float)Math.PI * 2.0F / 7.0F);
		this.leftArmPart2.xRot = -((float)Math.PI * 2.0F / 7.0F);
		this.rightArmPart3.xRot = -((float)Math.PI / 8.0F);
		this.leftArmPart3.xRot = -((float)Math.PI / 8.0F);
		this.rightArmPart2.xRot -= Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.024F;
		this.leftArmPart2.xRot -= Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.024F;
		this.rightArmPart5.xRot = (float)Math.PI / 5.0F;
		this.leftArmPart5.xRot = (float)Math.PI / 5.0F;
		this.rightArmPart5.xRot += Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.033F;
		this.leftArmPart5.xRot += Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.033F;

		this.rightArmPart2.xRot -= ((float)Math.PI / 7.0F) * limbSwingAmount;
		this.leftArmPart2.xRot -= ((float)Math.PI / 7.0F) * limbSwingAmount;
		this.rightArmPart5.xRot -= ((float)Math.PI * 5.0F / 7.0F) * limbSwingAmount;
		this.leftArmPart5.xRot -= ((float)Math.PI * 5.0F / 7.0F) * limbSwingAmount;

		float f = Mth.sin(this.attackTime * (float)Math.PI);
		float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);

		this.rightArmPart1.xRot -= f * 0.6F - f1 * 0.2F;
		this.leftArmPart1.xRot -= f * 0.6F - f1 * 0.2F;
		this.rightArmPart2.xRot -= f * 0.6F - f1 * 0.3F;
		this.leftArmPart2.xRot -= f * 0.6F - f1 * 0.3F;
		this.rightArmPart5.xRot += f * 1.2F - f1 * 0.4F;
		this.leftArmPart5.xRot += f * 1.2F - f1 * 0.4F;

		this.rightArmPart4A.yRot = -((float)Math.PI / 18.0F);
		this.leftArmPart4A.yRot = (float)Math.PI / 18.0F;
		this.rightArmPart4B.yRot = -((float)Math.PI / 18.0F);
		this.leftArmPart4B.yRot = (float)Math.PI / 18.0F;
		this.rightArmPart4A.yRot -= Mth.cos(ageInTicks * 0.09F) * 0.15F;
		this.leftArmPart4A.yRot += Mth.cos(ageInTicks * 0.09F) * 0.15F;
		this.rightArmPart4B.yRot -= Mth.cos(ageInTicks * 0.09F + (float)Math.PI / 3.0F) * 0.15F;
		this.leftArmPart4B.yRot += Mth.cos(ageInTicks * 0.09F + (float)Math.PI / 3.0F) * 0.15F;
		this.rightArmPart4A.xRot = (float)Math.PI / 20.0F;
		this.leftArmPart4A.xRot = (float)Math.PI / 20.0F;

		this.rightArmPart6.yRot = -((float)Math.PI * 0.12F);
		this.leftArmPart6.yRot = (float)Math.PI * 0.12F;

		this.rightLegPart1.xRot = (float)Math.PI / 3.0F;
		this.leftLegPart1.xRot = (float)Math.PI / 3.0F;
		this.rightLegPart2.xRot = -((float)Math.PI / 15.0F);
		this.leftLegPart2.xRot = -((float)Math.PI / 15.0F);
		this.rightLegPart3.xRot = 0.0F;
		this.leftLegPart3.xRot = 0.0F;

		this.rightHair1.xRot = (float)Math.PI / 18.0F;
		this.leftHair1.xRot = (float)Math.PI / 18.0F;
		this.rightHair1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.03F;
		this.leftHair1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.03F;
		this.rightHair1.zRot = (float)Math.PI / 9.0F;
		this.leftHair1.zRot = -((float)Math.PI / 9.0F);
		this.rightHair1.zRot -= Mth.sin(ageInTicks * 0.09F) * 0.033F;
		this.leftHair1.zRot += Mth.sin(ageInTicks * 0.09F) * 0.033F;
		this.rightHair2.zRot = (float)Math.PI / 16.0F;
		this.leftHair2.zRot = -((float)Math.PI / 16.0F);
		this.rightHair2.zRot -= Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.018F;
		this.leftHair2.zRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.018F;
		this.rightHair3.zRot = -((float)Math.PI / 7.0F);
		this.leftHair3.zRot = (float)Math.PI / 7.0F;
		this.rightHair3.zRot -= Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.012F;
		this.leftHair3.zRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.012F;
		this.rightHair4.zRot = -((float)Math.PI * 5.0F / 21.0F);
		this.leftHair4.zRot = (float)Math.PI * 5.0F / 21.0F;
		this.rightHair4.zRot -= Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.012F;
		this.leftHair4.zRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.012F;

		this.tailPart1.xRot = (float)Math.PI / 12.0F;
		this.tailPart1.xRot += Mth.cos(ageInTicks * 0.03F) * 0.012F;
		this.tailPart1.xRot += (Mth.cos(limbSwing * 0.45F) * 0.09F + ((float)Math.PI / 12.0F)) * limbSwingAmount;
		this.tailPart2.xRot = (float)Math.PI / 15.0F;
		this.tailPart2.xRot += Mth.cos(ageInTicks * 0.03F + (float)Math.PI / 5.0F) * 0.045F;
		this.tailPart2.xRot += Mth.cos(limbSwing * 0.45F) * 0.09F * limbSwingAmount;
		this.tailPart3.xRot = (float)Math.PI / 8.0F;
		this.tailPart3.xRot += Mth.cos(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.06F;
		this.tailPart4.xRot = (float)Math.PI / 5.0F;
		this.tailPart4.xRot += Mth.cos(ageInTicks * 0.045F + (float)Math.PI / 2.0F) * 0.06F;
		this.tailPart1.zRot = Mth.sin(ageInTicks * 0.021F) * 0.045F;
		this.tailPart2.zRot = Mth.sin(ageInTicks * 0.021F + (float)Math.PI / 6.0F) * 0.03F;
		this.tailPart1.zRot += Mth.sin(limbSwing * 0.3F) * 0.25F * limbSwingAmount;
		this.tailPart2.zRot += Mth.sin(limbSwing * 0.3F + (float)Math.PI / 6.0F) * 0.25F * limbSwingAmount;

		this.tailPart1Fin.xRot = 0.0F;
		this.tailPart2Fin.xRot = 0.0F;

		this.bust.xRot = ((float)Math.PI / 4.0F) + ((float)Math.PI / 18.0F);

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
			this.rightLeg.xRot *= 0.5F;
			this.leftLeg.xRot *= 0.5F;
		}

		this.skirt1.xRot = 0.0F;

		this.bodyPart6RightA.yRot = (float)Math.PI / 12.0F;
		this.bodyPart6LeftA.yRot = -((float)Math.PI / 12.0F);
		this.bodyPart6RightB.yRot = (float)Math.PI / 9.0F;
		this.bodyPart6LeftB.yRot = -((float)Math.PI / 9.0F);
		this.bodyPart6RightC.yRot = (float)Math.PI / 12.0F;
		this.bodyPart6LeftC.yRot = -((float)Math.PI / 12.0F);
		this.bodyPart6RightA.yRot -= Mth.cos(ageInTicks * 0.09F) * 0.135F;
		this.bodyPart6LeftA.yRot += Mth.cos(ageInTicks * 0.09F) * 0.135F;
		this.bodyPart6RightB.yRot -= Mth.cos(ageInTicks * 0.09F + (float)Math.PI / 3.0F) * 0.135F;
		this.bodyPart6LeftB.yRot += Mth.cos(ageInTicks * 0.09F + (float)Math.PI / 3.0F) * 0.135F;
		this.bodyPart6RightC.yRot -= Mth.cos(ageInTicks * 0.09F + (float)Math.PI / 6.0F) * 0.135F;
		this.bodyPart6LeftC.yRot += Mth.cos(ageInTicks * 0.09F + (float)Math.PI / 6.0F) * 0.135F;
		this.bodyPart6RightB.xRot = -((float)Math.PI * 0.06F);
		this.bodyPart6LeftB.xRot = -((float)Math.PI * 0.06F);

		this.hairBand.xRot = -((float)Math.PI / 12.0F);
		this.hairBand.xRot += Mth.cos(ageInTicks * 0.03F + (float)Math.PI) * 0.09F;
		this.hairPart.xRot = (float)Math.PI / 12.0F;
		this.hairPart.xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.06F;

		float f2 = (float)Math.PI / 2.0F;

		for (int i = 0; i < this.rightSkirtPart.length; ++i)
		{
			this.rightSkirtPart[i].yRot = (float)Math.PI * ((float)i - 3) / 7.0F + f2;
			this.rightSkirtPart[i].xRot = (float)Math.PI * 2.0F / 5.0F - f2;
			this.rightSkirtPart[i].xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI * (float)i / 7.0F) * 0.15F - 0.03F;
			this.leftSkirtPart[i].yRot = -((float)Math.PI * ((float)i - 3) / 7.0F + f2);
			this.leftSkirtPart[i].xRot = (float)Math.PI * 2.0F / 5.0F - f2;
			this.leftSkirtPart[i].xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI * (float)i / 7.0F) * 0.15F - 0.03F;
		}
	}

	@Override
	protected float getLegRotZ()
	{
		return -((float)Math.PI * 0.004F);
	}

	public boolean isAggressive(T entityIn)
	{
		return entityIn.isAggressive();
	}
}