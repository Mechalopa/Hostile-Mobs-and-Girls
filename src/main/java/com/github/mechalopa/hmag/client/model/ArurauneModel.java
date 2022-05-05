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
public class ArurauneModel<T extends Mob> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart rightHair1;
	private ModelPart leftHair1;
	private ModelPart rightHair2;
	private ModelPart leftHair2;
	private ModelPart rightHair3;
	private ModelPart leftHair3;
	private ModelPart rightHairFlowerA;
	private ModelPart rightHairFlowerB;
	private ModelPart rightHairFlowerC;
	private ModelPart rightHairFlowerD;
	private ModelPart leftHairFlowerA;
	private ModelPart leftHairFlowerB;
	private ModelPart leftHairFlowerC;
	private ModelPart leftHairFlowerD;
	private ModelPart hairPart;
//	private ModelPart headwearPart;
	private ModelPart skirt1;
	private ModelPart skirt2;
	private ModelPart[] rightSkirtLeafPart1 = new ModelPart[3];
	private ModelPart[] leftSkirtLeafPart1 = new ModelPart[3];
	private ModelPart[] rightSkirtLeafPart2 = new ModelPart[3];
	private ModelPart[] leftSkirtLeafPart2 = new ModelPart[3];
	private ModelPart[] rightSkirtLeafPart3 = new ModelPart[3];
	private ModelPart[] leftSkirtLeafPart3 = new ModelPart[3];
	private ModelPart rightTentaclePart1;
	private ModelPart leftTentaclePart1;
	private ModelPart rightTentaclePart2;
	private ModelPart leftTentaclePart2;
	private ModelPart rightTentaclePart3;
	private ModelPart leftTentaclePart3;
	private ModelPart rightTentaclePart4;
	private ModelPart leftTentaclePart4;
	private ModelPart rightTentacleFlowerA;
	private ModelPart rightTentacleFlowerB;
	private ModelPart rightTentacleFlowerC;
	private ModelPart rightTentacleFlowerD;
	private ModelPart leftTentacleFlowerA;
	private ModelPart leftTentacleFlowerB;
	private ModelPart leftTentacleFlowerC;
	private ModelPart leftTentacleFlowerD;
	private ModelPart rightTentacleLeafA;
	private ModelPart rightTentacleLeafB;
	private ModelPart rightTentacleLeafC;
	private ModelPart rightTentacleLeafD;
	private ModelPart leftTentacleLeafA;
	private ModelPart leftTentacleLeafB;
	private ModelPart leftTentacleLeafC;
	private ModelPart leftTentacleLeafD;
//	private ModelPart rightLegPart;
//	private ModelPart leftLegPart;

	public ArurauneModel(ModelPart modelPart)
	{
		super(modelPart);
		this.rightHair1 = this.head.getChild("right_hair_1");
		this.leftHair1 = this.head.getChild("left_hair_1");
		this.rightHair2 = this.rightHair1.getChild("right_hair_2");
		this.leftHair2 = this.leftHair1.getChild("left_hair_2");
		this.rightHair3 = this.rightHair2.getChild("right_hair_3");
		this.leftHair3 = this.leftHair2.getChild("left_hair_3");
		this.rightHairFlowerA = this.rightHair1.getChild("right_hair_flower_a");
		this.rightHairFlowerB = this.rightHair1.getChild("right_hair_flower_b");
		this.rightHairFlowerC = this.rightHair1.getChild("right_hair_flower_c");
		this.rightHairFlowerD = this.rightHair1.getChild("right_hair_flower_d");
		this.leftHairFlowerA = this.leftHair1.getChild("left_hair_flower_a");
		this.leftHairFlowerB = this.leftHair1.getChild("left_hair_flower_b");
		this.leftHairFlowerC = this.leftHair1.getChild("left_hair_flower_c");
		this.leftHairFlowerD = this.leftHair1.getChild("left_hair_flower_d");
		this.hairPart = this.head.getChild("hair_part");
//		this.headwearPart = this.hat.getChild("hat_part");
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.body.getChild("skirt_2");
		Arrays.setAll(this.rightSkirtLeafPart1, (p) -> {
			return this.body.getChild("right_skirt_leaf_1_" + p);
		});
		Arrays.setAll(this.leftSkirtLeafPart1, (p) -> {
			return this.body.getChild("left_skirt_leaf_1_" + p);
		});
		Arrays.setAll(this.rightSkirtLeafPart2, (p) -> {
			return this.rightSkirtLeafPart1[p].getChild("right_skirt_leaf_2_" + p);
		});
		Arrays.setAll(this.leftSkirtLeafPart2, (p) -> {
			return this.leftSkirtLeafPart1[p].getChild("left_skirt_leaf_2_" + p);
		});
		Arrays.setAll(this.rightSkirtLeafPart3, (p) -> {
			return this.rightSkirtLeafPart2[p].getChild("right_skirt_leaf_3_" + p);
		});
		Arrays.setAll(this.leftSkirtLeafPart3, (p) -> {
			return this.leftSkirtLeafPart2[p].getChild("left_skirt_leaf_3_" + p);
		});
		this.rightTentaclePart1 = this.body.getChild("right_tentacle_part_1");
		this.leftTentaclePart1 = this.body.getChild("left_tentacle_part_1");
		this.rightTentaclePart2 = this.rightTentaclePart1.getChild("right_tentacle_part_2");
		this.leftTentaclePart2 = this.leftTentaclePart1.getChild("left_tentacle_part_2");
		this.rightTentaclePart3 = this.rightTentaclePart2.getChild("right_tentacle_part_3");
		this.leftTentaclePart3 = this.leftTentaclePart2.getChild("left_tentacle_part_4");
		this.rightTentaclePart4 = this.rightTentaclePart3.getChild("right_tentacle_part_4");
		this.leftTentaclePart4 = this.leftTentaclePart3.getChild("left_tentacle_part_4");
		this.rightTentacleFlowerA = this.rightTentaclePart4.getChild("right_tentacle_flower_a");
		this.rightTentacleFlowerB = this.rightTentaclePart4.getChild("right_tentacle_flower_b");
		this.rightTentacleFlowerC = this.rightTentaclePart4.getChild("right_tentacle_flower_c");
		this.rightTentacleFlowerD = this.rightTentaclePart4.getChild("right_tentacle_flower_d");
		this.leftTentacleFlowerA = this.leftTentaclePart4.getChild("left_tentacle_flower_a");
		this.leftTentacleFlowerB = this.leftTentaclePart4.getChild("left_tentacle_flower_b");
		this.leftTentacleFlowerC = this.leftTentaclePart4.getChild("left_tentacle_flower_c");
		this.leftTentacleFlowerD = this.leftTentaclePart4.getChild("left_tentacle_flower_d");
		this.rightTentacleLeafA = this.rightTentaclePart4.getChild("right_tentacle_leaf_a");
		this.rightTentacleLeafB = this.rightTentaclePart4.getChild("right_tentacle_leaf_a");
		this.rightTentacleLeafC = this.rightTentaclePart4.getChild("right_tentacle_leaf_a");
		this.rightTentacleLeafD = this.rightTentaclePart4.getChild("right_tentacle_leaf_a");
		this.leftTentacleLeafA = this.leftTentaclePart4.getChild("left_tentacle_leaf_a");
		this.leftTentacleLeafB = this.leftTentaclePart4.getChild("left_tentacle_leaf_b");
		this.leftTentacleLeafC = this.leftTentaclePart4.getChild("left_tentacle_leaf_c");
		this.leftTentacleLeafD = this.leftTentaclePart4.getChild("left_tentacle_leaf_d");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F, 6);
		PartDefinition pd = md.getRoot();
		ModClientUtils.addC(pd, cd, "right_arm", 40, 16, 0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, -5.0F, 2.0F, 0.0F);
		ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 5.0F, 2.0F, 0.0F, true);

		PartDefinition headpd = pd.getChild("head");
		PartDefinition rh1pd = ModClientUtils.addC(headpd, cd, "right_hair_1", 48, 64, -0.5F, -1.0F, 0.0F, 1.0F, 10.0F, 1.0F, -4.0F, -8.0F, 1.5F, 0.25F);
		PartDefinition lh1pd = ModClientUtils.addC(headpd, cd, "left_hair_1", 48, 64, -0.5F, -1.0F, 0.0F, 1.0F, 10.0F, 1.0F, 4.0F, -8.0F, 1.5F, true, 0.25F);
		PartDefinition rh2pd = ModClientUtils.addC(rh1pd, cd, "right_hair_2", 32, 48, -1.0F, -0.5F, -0.5F, 2.0F, 6.0F, 1.0F, 0.0F, 9.5F, 0.0F);
		PartDefinition lh2pd = ModClientUtils.addC(lh1pd, cd, "left_hair_2", 32, 48, -1.0F, -0.5F, -0.5F, 2.0F, 6.0F, 1.0F, 0.0F, 9.5F, 0.0F, true);
		ModClientUtils.addC(rh2pd, cd, "right_hair_3", 32, 56, -1.0F, -0.5F, -0.5F, 2.0F, 5.0F, 1.0F, 0.0F, 5.5F, 0.0F);
		ModClientUtils.addC(lh2pd, cd, "left_hair_3", 32, 56, -1.0F, -0.5F, -0.5F, 2.0F, 5.0F, 1.0F, 0.0F, 5.5F, 0.0F, true);

		ModClientUtils.addC(rh1pd, cd, "right_hair_flower_a", 40, 80, -5.25F, -5.25F, -0.5F, 5.0F, 5.0F, 1.0F, -0.125F, 0.5F, 0.25F);
		ModClientUtils.addC(rh1pd, cd, "right_hair_flower_b", 40, 88, -5.25F, 0.25F, -0.5F, 5.0F, 5.0F, 1.0F, -0.125F, 0.5F, 0.25F);
		ModClientUtils.addC(rh1pd, cd, "right_hair_flower_c", 40, 96, 0.25F, -5.25F, -0.5F, 5.0F, 5.0F, 1.0F, -0.125F, 0.5F, 0.25F);
		ModClientUtils.addC(rh1pd, cd, "right_hair_flower_d", 40, 104, 0.25F, 0.25F, -0.5F, 5.0F, 5.0F, 1.0F, -0.125F, 0.5F, 0.25F);
		ModClientUtils.addC(lh1pd, cd, "left_hair_flower_a", 40, 80, -5.25F, -5.25F, -0.5F, 5.0F, 5.0F, 1.0F, 0.125F, 0.5F, 0.25F);
		ModClientUtils.addC(lh1pd, cd, "left_hair_flower_b", 40, 88, -5.25F, 0.25F, -0.5F, 5.0F, 5.0F, 1.0F, 0.125F, 0.5F, 0.25F);
		ModClientUtils.addC(lh1pd, cd, "left_hair_flower_c", 40, 96, 0.25F, -5.25F, -0.5F, 5.0F, 5.0F, 1.0F, 0.125F, 0.5F, 0.25F);
		ModClientUtils.addC(lh1pd, cd, "left_hair_flower_d", 40, 104, 0.25F, 0.25F, -0.5F, 5.0F, 5.0F, 1.0F, 0.125F, 0.5F, 0.25F);

		ModClientUtils.addC(headpd, cd, "hair_part", 0, 112, -4.0F, 0.0F, -1.0F, 8.0F, 2.0F, 1.0F, 0.0F, 0.0F, 4.0F);
		PartDefinition hatpd = pd.getChild("hat");
		ModClientUtils.addC(hatpd, cd, "hat_part", 32, 112, -4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, 3.0F, 0.0F, 0.5F);

		PartDefinition bodypd = pd.getChild("body");
		ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_2", 0, 44, -4.0F, 0.0F, -2.5F, 8.0F, 9.0F, 5.0F, 0.0F, 12.0F, 0.0F);

		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 64).addBox(-2.5F, 0.5F, 0.0F, 5.0F, 6.0F, 1.0F);
		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(40, 48).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 5.0F, 1.0F);
		CubeListBuilder cubelistbuilder2 = CubeListBuilder.create().texOffs(40, 56).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 4.0F, 1.0F);
		CubeListBuilder cubelistbuilder3 = CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-2.5F, 0.5F, 0.0F, 5.0F, 6.0F, 1.0F);
		CubeListBuilder cubelistbuilder4 = CubeListBuilder.create().texOffs(40, 48).mirror().addBox(-2.5F, 0.0F, 0.0F, 5.0F, 5.0F, 1.0F);
		CubeListBuilder cubelistbuilder5 = CubeListBuilder.create().texOffs(40, 56).mirror().addBox(-2.5F, 0.0F, 0.0F, 5.0F, 4.0F, 1.0F);
		PartPose pp = PartPose.offset(0.0F, 6.5F, 0.0F);
		PartPose pp1 = PartPose.offset(0.0F, 5.0F, 0.0F);

		for (int k = 0; k < 3; ++k)
		{
			PartPose pp2 = PartPose.offset(-4.0F, 12.0F, (float)(k - 1) * 2.0F);
			PartDefinition rsl1pd = bodypd.addOrReplaceChild("right_skirt_leaf_1_" + k, cubelistbuilder, pp2);
			PartDefinition rsl2pd = rsl1pd.addOrReplaceChild("right_skirt_leaf_2_" + k, cubelistbuilder1, pp);
			rsl2pd.addOrReplaceChild("right_skirt_leaf_3_" + k, cubelistbuilder2, pp1);
			PartPose pp3 = PartPose.offset(4.0F, 12.0F, (float)(k - 1) * 2.0F);
			PartDefinition lsl1pd = bodypd.addOrReplaceChild("left_skirt_leaf_1_" + k, cubelistbuilder3, pp3);
			PartDefinition lsl2pd = lsl1pd.addOrReplaceChild("left_skirt_leaf_2_" + k, cubelistbuilder4, pp);
			lsl2pd.addOrReplaceChild("left_skirt_leaf_3_" + k, cubelistbuilder5, pp1);
		}

		PartDefinition rtp1pd = ModClientUtils.addC(bodypd, cd, "right_tentacle_part_1", 24, 64, -0.5F, -5.75F, -0.5F, 1.0F, 6.0F, 1.0F, -2.0F, 10.5F, 1.5F, 0.25F);
		PartDefinition ltp1pd = ModClientUtils.addC(bodypd, cd, "left_tentacle_part_1", 24, 64, -0.5F, -5.75F, -0.5F, 1.0F, 6.0F, 1.0F, 2.0F, 10.5F, 1.5F, true, 0.25F);
		PartDefinition rtp2pd = ModClientUtils.addC(rtp1pd, cd, "right_tentacle_part_2", 28, 64, -0.5F, -7.75F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, -5.75F, 0.0F);
		PartDefinition ltp2pd = ModClientUtils.addC(ltp1pd, cd, "left_tentacle_part_2", 28, 64, -0.5F, -7.75F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, -5.75F, 0.0F, true);
		PartDefinition rtp3pd = ModClientUtils.addC(rtp2pd, cd, "right_tentacle_part_3", 28, 64, -0.5F, -7.75F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, -7.75F, 0.0F);
		PartDefinition ltp3pd = ModClientUtils.addC(ltp2pd, cd, "left_tentacle_part_3", 28, 64, -0.5F, -7.75F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, -7.75F, 0.0F, true);
		PartDefinition rtp4pd = ModClientUtils.addC(rtp3pd, cd, "right_tentacle_part_4", 28, 64, -0.5F, -7.75F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, -7.75F, 0.0F, 0.25F);
		PartDefinition ltp4pd = ModClientUtils.addC(ltp3pd, cd, "left_tentacle_part_4", 28, 64, -0.5F, -7.75F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, -7.75F, 0.0F, true, 0.25F);

		ModClientUtils.addC(rtp4pd, cd, "right_tentacle_flower_a", 24, 80, -7.25F, -7.25F, -0.5F, 7.0F, 7.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(rtp4pd, cd, "right_tentacle_flower_b", 24, 88, -7.25F, 0.25F, -0.5F, 7.0F, 7.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(rtp4pd, cd, "right_tentacle_flower_c", 24, 96, 0.25F, -7.25F, -0.5F, 7.0F, 7.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(rtp4pd, cd, "right_tentacle_flower_d", 24, 104, 0.25F, 0.25F, -0.5F, 7.0F, 7.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(ltp4pd, cd, "left_tentacle_flower_a", 24, 80, -7.25F, -7.25F, -0.5F, 7.0F, 7.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(ltp4pd, cd, "left_tentacle_flower_b", 24, 88, -7.25F, 0.25F, -0.5F, 7.0F, 7.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(ltp4pd, cd, "left_tentacle_flower_c", 24, 96, 0.25F, -7.25F, -0.5F, 7.0F, 7.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(ltp4pd, cd, "left_tentacle_flower_d", 24, 104, 0.25F, 0.25F, -0.5F, 7.0F, 7.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(rtp4pd, cd, "right_tentacle_leaf_a", 0, 72, -8.25F, -8.25F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(rtp4pd, cd, "right_tentacle_leaf_b", 0, 82, -8.25F, 0.25F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(rtp4pd, cd, "right_tentacle_leaf_c", 0, 92, 0.25F, -8.25F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(rtp4pd, cd, "right_tentacle_leaf_d", 0, 102, 0.25F, 0.25F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(ltp4pd, cd, "left_tentacle_leaf_a", 0, 72, -8.25F, -8.25F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(ltp4pd, cd, "left_tentacle_leaf_b", 0, 82, -8.25F, 0.25F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(ltp4pd, cd, "left_tentacle_leaf_c", 0, 92, 0.25F, -8.25F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, -7.5F, 0.0F);
		ModClientUtils.addC(ltp4pd, cd, "left_tentacle_leaf_d", 0, 102, 0.25F, 0.25F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, -7.5F, 0.0F);

		PartDefinition rlpd = pd.getChild("right_leg");
		PartDefinition llpd = pd.getChild("left_leg");
		ModClientUtils.addC(rlpd, cd, "right_leg_part", 32, 64, -1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, 6.0F, 0.0F, 0.5F);
		ModClientUtils.addC(llpd, cd, "left_leg_part", 32, 64, -1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, 6.0F, 0.0F, true, 0.5F);
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

		this.rightArm.zRot = ((float)Math.PI / 8.0F);
		this.leftArm.zRot = -((float)Math.PI / 8.0F);
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.12F) * 0.06F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.12F) * 0.06F;

		this.hairPart.xRot = (float)Math.PI / 12.0F;
		this.hairPart.xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.03F;

		this.rightHair1.xRot = ((float)Math.PI / 18.0F);
		this.leftHair1.xRot = ((float)Math.PI / 18.0F);
		this.rightHair1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.045F;
		this.leftHair1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.045F;
		this.rightHair1.zRot = ((float)Math.PI / 9.0F);
		this.leftHair1.zRot = -((float)Math.PI / 9.0F);
		this.rightHair1.zRot -= Mth.sin(ageInTicks * 0.09F) * 0.045F;
		this.leftHair1.zRot += Mth.sin(ageInTicks * 0.09F) * 0.045F;
		this.rightHair2.yRot = ((float)Math.PI / 3.0F);
		this.leftHair2.yRot = -((float)Math.PI / 3.0F);
		this.rightHair2.xRot = -((float)Math.PI / 18.0F);
		this.leftHair2.xRot = -((float)Math.PI / 18.0F);
		this.rightHair2.xRot -= Mth.sin(ageInTicks * 0.06F - (float)Math.PI / 3.0F) * 0.015F;
		this.leftHair2.xRot += Mth.sin(ageInTicks * 0.06F - (float)Math.PI / 3.0F) * 0.015F;
		this.rightHair2.zRot = -((float)Math.PI / 12.0F);
		this.leftHair2.zRot = ((float)Math.PI / 12.0F);
		this.rightHair2.zRot -= Mth.sin(ageInTicks * 0.045F + (float)Math.PI / 3.0F) * 0.06F;
		this.leftHair2.zRot += Mth.sin(ageInTicks * 0.045F - (float)Math.PI / 3.0F) * 0.06F;
		this.rightHair3.xRot = ((float)Math.PI / 9.0F);
		this.leftHair3.xRot = ((float)Math.PI / 9.0F);
		this.rightHair3.xRot -= Mth.sin(ageInTicks * 0.06F - (float)Math.PI / 4.0F) * 0.06F;
		this.leftHair3.xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 4.0F) * 0.06F;

		float f = ((float)Math.PI / 15.0F) + Mth.sin(ageInTicks * 0.075F) * 0.045F;
		float f1 = ((float)Math.PI * 7.0F / 15.0F);

		this.rightHairFlowerA.xRot = f;
		this.rightHairFlowerB.xRot = -f;
		this.rightHairFlowerC.xRot = f;
		this.rightHairFlowerD.xRot = -f;
		this.leftHairFlowerA.xRot = f;
		this.leftHairFlowerB.xRot = -f;
		this.leftHairFlowerC.xRot = f;
		this.leftHairFlowerD.xRot = -f;
		this.rightHairFlowerA.yRot = f1 - f;
		this.rightHairFlowerB.yRot = f1 - f;
		this.rightHairFlowerC.yRot = f1 + f;
		this.rightHairFlowerD.yRot = f1 + f;
		this.leftHairFlowerA.yRot = -f1 - f;
		this.leftHairFlowerB.yRot = -f1 - f;
		this.leftHairFlowerC.yRot = -f1 + f;
		this.leftHairFlowerD.yRot = -f1 + f;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
		}

		this.skirt1.xRot = 0.0F;

		f1 = (float)Math.PI / 2.0F;

		for (int i = 0; i < this.rightSkirtLeafPart1.length; ++i)
		{
			this.rightSkirtLeafPart1[i].yRot = (float)Math.PI * ((float)(i - 1)) / 3.0F + f1;
			this.rightSkirtLeafPart1[i].xRot = (float)Math.PI * 2.0F / 5.0F - f1;
			this.rightSkirtLeafPart1[i].xRot += Mth.sin(ageInTicks * 0.045F + (float)Math.PI * (float)i / 3.0F) * 0.075F;
			this.rightSkirtLeafPart2[i].xRot = -((float)Math.PI * 2.0F / 7.0F);
			this.rightSkirtLeafPart2[i].xRot -= Mth.sin(ageInTicks * 0.06F + (float)Math.PI * (float)(i - 1) / 3.0F) * 0.066F;
			this.rightSkirtLeafPart3[i].xRot = -((float)Math.PI * 3.0F / 5.0F);
			this.rightSkirtLeafPart3[i].xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI * (float)i / 3.0F) * 0.081F;

			this.leftSkirtLeafPart1[i].yRot = -((float)Math.PI * ((float)(i - 1)) / 3.0F + f1);
			this.leftSkirtLeafPart1[i].xRot = (float)Math.PI * 2.0F / 5.0F - f1;
			this.leftSkirtLeafPart1[i].xRot += Mth.sin(ageInTicks * 0.045F + (float)Math.PI * (float)i / 3.0F) * 0.075F;
			this.leftSkirtLeafPart2[i].xRot = -((float)Math.PI * 2.0F / 7.0F);
			this.leftSkirtLeafPart2[i].xRot -= Mth.sin(ageInTicks * 0.06F + (float)Math.PI * (float)(i - 1) / 3.0F) * 0.066F;
			this.leftSkirtLeafPart3[i].xRot = -((float)Math.PI * 3.0F / 5.0F);
			this.leftSkirtLeafPart3[i].xRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI * (float)i / 3.0F) * 0.081F;
		}

		this.rightTentaclePart1.zRot = -((float)Math.PI / 6.0F);
		this.leftTentaclePart1.zRot = ((float)Math.PI / 6.0F);
		this.rightTentaclePart1.zRot += Mth.cos(ageInTicks * 0.096F + (float)Math.PI / 2.0F) * 0.021F;
		this.leftTentaclePart1.zRot += Mth.cos(ageInTicks * 0.096F) * 0.021F;
		this.rightTentaclePart1.xRot = -((float)Math.PI * 2.0F / 5.0F);
		this.leftTentaclePart1.xRot = -((float)Math.PI * 2.0F / 5.0F);
		this.rightTentaclePart1.xRot += Mth.sin(ageInTicks * 0.075F) * 0.075F;
		this.leftTentaclePart1.xRot += Mth.sin(ageInTicks * 0.075F + (float)Math.PI / 3.0F) * 0.075F;
		this.rightTentaclePart2.zRot = -((float)Math.PI / 18.0F);
		this.leftTentaclePart2.zRot = ((float)Math.PI / 18.0F);
		this.rightTentaclePart2.zRot += Mth.cos(ageInTicks * 0.105F + (float)Math.PI / 3.0F) * 0.018F;
		this.leftTentaclePart2.zRot += Mth.cos(ageInTicks * 0.105F + (float)Math.PI * 2.0F / 3.0F) * 0.018F;
		this.rightTentaclePart2.xRot = ((float)Math.PI * 2.0F / 7.0F);
		this.leftTentaclePart2.xRot = ((float)Math.PI * 2.0F / 7.0F);
		this.rightTentaclePart2.xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.072F;
		this.leftTentaclePart2.xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI) * 0.072F;
		this.rightTentaclePart3.zRot = -((float)Math.PI / 18.0F);
		this.leftTentaclePart3.zRot = ((float)Math.PI / 18.0F);
		this.rightTentaclePart3.zRot += Mth.cos(ageInTicks * 0.105F + (float)Math.PI / 3.0F) * 0.012F;
		this.leftTentaclePart3.zRot += Mth.cos(ageInTicks * 0.105F + (float)Math.PI * 2.0F / 3.0F) * 0.012F;
		this.rightTentaclePart3.xRot = ((float)Math.PI / 5.0F);
		this.leftTentaclePart3.xRot = ((float)Math.PI / 5.0F);
		this.rightTentaclePart3.xRot += Mth.sin(ageInTicks * 0.096F + (float)Math.PI / 4.0F) * 0.09F;
		this.leftTentaclePart3.xRot += Mth.sin(ageInTicks * 0.096F + (float)Math.PI * 3.0F / 4.0F) * 0.09F;
		this.rightTentaclePart4.xRot = ((float)Math.PI / 6.0F);
		this.leftTentaclePart4.xRot = ((float)Math.PI / 6.0F);
		this.rightTentaclePart4.xRot += Mth.sin(ageInTicks * 0.042F - (float)Math.PI / 3.0F) * 0.06F;
		this.leftTentaclePart4.xRot += Mth.sin(ageInTicks * 0.042F - (float)Math.PI * 8.0F / 15.0F) * 0.06F;
		this.rightTentaclePart4.yRot = Mth.cos(ageInTicks * 0.072F + (float)Math.PI / 2.0F) * 0.06F;
		this.leftTentaclePart4.yRot = -(Mth.cos(ageInTicks * 0.072F + (float)Math.PI / 2.0F) * 0.06F);

		f = ((float)Math.PI / 15.0F) + Mth.sin(ageInTicks * 0.075F + (float)Math.PI / 4.0F) * 0.075F;
		f1 = (float)Math.PI * 8.0F / 15.0F;

		if (this.attackTime > 0.0F)
		{
			float f2 = 1.0F - this.attackTime;
			f2 = f2 * f2;
			f2 = f2 * f2;
			f2 = 1.0F - f2;
			float f3 = Mth.sin(f2 * (float)Math.PI);
			float f4 = Mth.sin(this.attackTime * (float)Math.PI) * 0.1F;
			this.rightTentaclePart2.xRot += f3 * 0.051F + f4;
			this.leftTentaclePart2.xRot += f3 * 0.051F + f4;
			this.rightTentaclePart3.xRot += f3 * 0.06F + f4;
			this.leftTentaclePart3.xRot += f3 * 0.06F + f4;
			this.rightTentaclePart4.xRot += f3 * 0.06F + f4;
			this.leftTentaclePart4.xRot += f3 * 0.06F + f4;

			f += f3 * 0.9F + f4 * 0.5F;
		}

		this.rightTentacleFlowerA.xRot = -f1 + f;
		this.rightTentacleFlowerB.xRot = -f1 - f;
		this.rightTentacleFlowerC.xRot = -f1 + f;
		this.rightTentacleFlowerD.xRot = -f1 - f;
		this.leftTentacleFlowerA.xRot = -f1 + f;
		this.leftTentacleFlowerB.xRot = -f1 - f;
		this.leftTentacleFlowerC.xRot = -f1 + f;
		this.leftTentacleFlowerD.xRot = -f1 - f;
		this.rightTentacleFlowerA.zRot = f;
		this.rightTentacleFlowerB.zRot = f;
		this.rightTentacleFlowerC.zRot = -f;
		this.rightTentacleFlowerD.zRot = -f;
		this.leftTentacleFlowerA.zRot = f;
		this.leftTentacleFlowerB.zRot = f;
		this.leftTentacleFlowerC.zRot = -f;
		this.leftTentacleFlowerD.zRot = -f;

		this.rightTentacleLeafA.xRot = -f1 - f;
		this.rightTentacleLeafB.xRot = -f1 + f;
		this.rightTentacleLeafC.xRot = -f1 - f;
		this.rightTentacleLeafD.xRot = -f1 + f;
		this.leftTentacleLeafA.xRot = -f1 - f;
		this.leftTentacleLeafB.xRot = -f1 + f;
		this.leftTentacleLeafC.xRot = -f1 - f;
		this.leftTentacleLeafD.xRot = -f1 + f;
		this.rightTentacleLeafA.zRot = -f;
		this.rightTentacleLeafB.zRot = -f;
		this.rightTentacleLeafC.zRot = f;
		this.rightTentacleLeafD.zRot = f;
		this.leftTentacleLeafA.zRot = -f;
		this.leftTentacleLeafB.zRot = -f;
		this.leftTentacleLeafC.zRot = f;
		this.leftTentacleLeafD.zRot = f;

		f1 = (float)Math.PI / 4.0F;

		this.rightTentacleLeafA.yRot = f1;
		this.rightTentacleLeafB.yRot = f1;
		this.rightTentacleLeafC.yRot = f1;
		this.rightTentacleLeafD.yRot = f1;
		this.leftTentacleLeafA.yRot = -f1;
		this.leftTentacleLeafB.yRot = -f1;
		this.leftTentacleLeafC.yRot = -f1;
		this.leftTentacleLeafD.yRot = -f1;
	}
}