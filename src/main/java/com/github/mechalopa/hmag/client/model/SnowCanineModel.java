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
public class SnowCanineModel<T extends Mob> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart bodyPart3;
	private ModelPart bodyPart4;
	private ModelPart rightArmPart1;
	private ModelPart leftArmPart1;
	private ModelPart rightArmPart2;
	private ModelPart leftArmPart2;
	private ModelPart rightArmPart3;
	private ModelPart leftArmPart3;
	private ModelPart rightLegPart1;
	private ModelPart leftLegPart1;
	private ModelPart rightLegPart2;
	private ModelPart leftLegPart2;
	private ModelPart rightLegPart3;
	private ModelPart leftLegPart3;
	private ModelPart rightLegPart4;
	private ModelPart leftLegPart4;
	private ModelPart rightLegPart5;
	private ModelPart leftLegPart5;
	private ModelPart rightEar;
	private ModelPart leftEar;
	private ModelPart rightEarPart;
	private ModelPart leftEarPart;
	private ModelPart hairPart;
	private ModelPart tail1;
	private ModelPart tail2;
	private ModelPart tail3;
	private ModelPart tail4;

	public SnowCanineModel(ModelPart modelPart)
	{
		super(modelPart);
		this.bodyPart3 = this.bodyPart2.getChild("body_part_3");
		this.bodyPart4 = this.bodyPart3.getChild("body_part_4");
		this.rightArmPart1 = this.rightArm.getChild("right_arm_part_1");
		this.leftArmPart1 = this.leftArm.getChild("left_arm_part_1");
		this.rightArmPart2 = this.rightArmPart1.getChild("right_arm_part_2");
		this.leftArmPart2 = this.leftArmPart1.getChild("left_arm_part_2");
		this.rightArmPart3 = this.rightArmPart2.getChild("right_arm_part_3");
		this.leftArmPart3 = this.leftArmPart2.getChild("left_arm_part_3");
		this.rightLegPart1 = this.rightLeg.getChild("right_leg_part_1");
		this.leftLegPart1 = this.leftLeg.getChild("left_leg_part_1");
		this.rightLegPart2 = this.rightLegPart1.getChild("right_leg_part_2");
		this.leftLegPart2 = this.leftLegPart1.getChild("left_leg_part_2");
		this.rightLegPart3 = this.rightLegPart1.getChild("right_leg_part_3");
		this.leftLegPart3 = this.leftLegPart1.getChild("left_leg_part_3");
		this.rightLegPart4 = this.rightLegPart3.getChild("right_leg_part_4");
		this.leftLegPart4 = this.leftLegPart3.getChild("left_leg_part_4");
		this.rightLegPart5 = this.rightLegPart4.getChild("right_leg_part_5");
		this.leftLegPart5 = this.leftLegPart4.getChild("left_leg_part_5");
		this.rightEar = this.head.getChild("right_ear");
		this.leftEar = this.head.getChild("left_ear");
		this.rightEarPart = this.rightEar.getChild("right_ear_part");
		this.leftEarPart = this.leftEar.getChild("left_ear_part");
		this.hairPart = this.head.getChild("hair_part");
		this.tail1 = this.body.getChild("tail_1");
		this.tail2 = this.tail1.getChild("tail_2");
		this.tail3 = this.tail2.getChild("tail_3");
		this.tail4 = this.tail3.getChild("tail_4");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F, 6);
		PartDefinition pd = md.getRoot();
		PartDefinition rapd = ModClientUtils.addC(pd, cd, "right_arm", 40, 16, 0.0F, -2.0F, -1.5F, 2.0F, 1.0F, 3.0F, -5.0F, 2.0F, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.5F, 2.0F, 1.0F, 3.0F, 5.0F, 2.0F, 0.0F, true);
		PartDefinition rlpd = ModClientUtils.addC(pd, cd, "right_leg", 0, 16, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, -2.1F, 12.0F, 0.5F);
		PartDefinition llpd = ModClientUtils.addC(pd, cd, "left_leg", 0, 16, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 2.1F, 12.0F, 0.5F, true);
		PartDefinition headpd = pd.getChild("head");
		PartDefinition bodypd = pd.getChild("body");
		PartDefinition bodypart1pd = bodypd.getChild("body_part_1");
		PartDefinition bodypart2pd = ModClientUtils.addC(bodypart1pd, cd, "body_part_2", 32, 40, -3.0F, 0.0F, -1.5F, 6.0F, 1.0F, 3.0F, 0.0F, 2.5F, 0.0F);
		PartDefinition bodypart3pd = ModClientUtils.addC(bodypart2pd, cd, "body_part_3", 32, 48, -4.0F, 0.0F, -2.0F, 8.0F, 2.0F, 4.0F, 0.0F, 0.5F, 0.0F);
		ModClientUtils.addC(bodypart3pd, cd, "body_part_4", 32, 56, -4.0F, 0.0F, -2.5F, 8.0F, 1.0F, 5.0F, 0.0F, 1.0F, 0.0F);
		PartDefinition rap1pd = ModClientUtils.addC(rapd, cd, "right_arm_part_1", 40, 20, 0.0F, -2.0F, -1.5F, 2.0F, 6.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		PartDefinition lap1pd = ModClientUtils.addC(lapd, cd, "left_arm_part_1", 40, 20, -2.0F, -2.0F, -1.5F, 2.0F, 6.0F, 3.0F, 0.0F, 0.0F, 0.0F, true);
		PartDefinition rap2pd = ModClientUtils.addC(rap1pd, cd, "right_arm_part_2", 16, 32, 0.0F, -0.5F, -1.5F, 2.0F, 5.0F, 3.0F, 0.0F, 3.5F, 0.75F, -0.125F);
		PartDefinition lap2pd = ModClientUtils.addC(lap1pd, cd, "left_arm_part_2", 16, 32, -2.0F, -0.5F, -1.5F, 2.0F, 5.0F, 3.0F, 0.0F, 3.5F, 0.75F, true, -0.125F);
		ModClientUtils.addC(rap2pd, cd, "right_arm_part_3", 16, 40, 0.0F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, 3.5F, 0.0F);
		ModClientUtils.addC(lap2pd, cd, "left_arm_part_3", 16, 40, -2.0F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, 3.5F, 0.0F, true);
		PartDefinition rlp1pd = ModClientUtils.addC(rlpd, cd, "right_leg_part_1", 0, 20, -2.25F, 0.0F, -1.75F, 4.0F, 5.0F, 4.0F, 0.0F, -1.5F, 0.0F);
		PartDefinition llp1pd = ModClientUtils.addC(llpd, cd, "left_leg_part_1", 0, 20, -1.75F, 0.0F, -1.75F, 4.0F, 5.0F, 4.0F, 0.0F, -1.5F, 0.0F, true);
		ModClientUtils.addC(rlp1pd, cd, "right_leg_part_2", 0, 40, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 5.0F, 0.0F);
		ModClientUtils.addC(llp1pd, cd, "left_leg_part_2", 0, 40, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 5.0F, 0.0F, true);
		PartDefinition rlp3pd = ModClientUtils.addC(rlp1pd, cd, "right_leg_part_3", 0, 46, -1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, 5.25F, 0.25F, -0.005F);
		PartDefinition llp3pd = ModClientUtils.addC(llp1pd, cd, "left_leg_part_3", 0, 46, -1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, 5.25F, 0.25F, true, -0.005F);
		PartDefinition rlp4pd = ModClientUtils.addC(rlp3pd, cd, "right_leg_part_4", 0, 56, -1.5F, -1.5F, -1.0F, 3.0F, 4.0F, 2.0F, 0.0F, 5.75F, -0.125F, 0.125F);
		PartDefinition llp4pd = ModClientUtils.addC(llp3pd, cd, "left_leg_part_4", 0, 56, -1.5F, -1.5F, -1.0F, 3.0F, 4.0F, 2.0F, 0.0F, 5.75F, -0.125F, true, 0.125F);
		ModClientUtils.addC(rlp4pd, cd, "right_leg_part_5", 16, 56, -1.5F, -1.0F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, 2.5F, 0.0F);
		ModClientUtils.addC(llp4pd, cd, "left_leg_part_5", 16, 56, -1.5F, -1.0F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, 2.5F, 0.0F, true);
		PartDefinition repd = ModClientUtils.addC(headpd, cd, "right_ear", 32, 64, -2.0F, -1.75F, -1.0F, 3.0F, 2.0F, 2.0F, -1.5F, -8.0F, -0.75F, 0.25F);
		PartDefinition lepd = ModClientUtils.addC(headpd, cd, "left_ear", 32, 64, -1.0F, -1.75F, -1.0F, 3.0F, 2.0F, 2.0F, 1.5F, -8.0F, -0.75F, true, 0.25F);
		ModClientUtils.addC(repd, cd, "right_ear_part", 32, 68, -2.0F, -1.75F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, -2.0F, 0.0F);
		ModClientUtils.addC(lepd, cd, "left_ear_part", 32, 68, -1.0F, -1.75F, -1.0F, 3.0F, 3.0F, 2.0F, 0.0F, -2.0F, 0.0F, true);
		ModClientUtils.addC(headpd, cd, "hair_part", 0, 64, -4.0F, 0.0F, -1.0F, 8.0F, 3.0F, 1.0F, 0.0F, 0.0F, 4.0F);
		PartDefinition t1pd = ModClientUtils.addC(bodypd, cd, "tail_1", 48, 64, -1.5F, -0.5F, -2.5F, 3.0F, 3.0F, 3.0F, 0.0F, 9.0F, 2.0F);
		PartDefinition t2pd = ModClientUtils.addC(t1pd, cd, "tail_2", 48, 72, -1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, 2.25F, -1.0F, 0.005F);
		PartDefinition t3pd = ModClientUtils.addC(t2pd , cd, "tail_3", 48, 80, -1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, 2.5F, 0.0F);
		ModClientUtils.addC(t3pd , cd, "tail_4", 48, 88, -1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, 2.0F, 0.0F);
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

		if (this.crouching)
		{
			this.body.xRot = 0.5F;
			this.rightLeg.z = 4.0F;
			this.leftLeg.z = 4.0F;
			this.bodyPart3.xRot = 0.0F;
		}
		else
		{
			this.body.xRot = (float)Math.PI * 0.03F;
			this.rightLeg.z = 0.6F;
			this.leftLeg.z = 0.6F;
			this.bodyPart3.xRot = -((float)Math.PI * 0.03F);
		}

		this.bodyPart4.xRot = 0.0F;

		this.rightArm.zRot = (float)Math.PI / 12.0F;
		this.leftArm.zRot = -((float)Math.PI / 12.0F);
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.024F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.024F;

		this.rightLeg.zRot = -((float)Math.PI * 0.01F);
		this.leftLeg.zRot = (float)Math.PI * 0.01F;
		this.rightArmPart1.xRot = (float)Math.PI / 14.0F;
		this.leftArmPart1.xRot = (float)Math.PI / 14.0F;
		this.rightArmPart2.xRot = -((float)Math.PI / 6.0F);
		this.leftArmPart2.xRot = -((float)Math.PI / 6.0F);
		this.rightArmPart3.xRot = -((float)Math.PI / 24.0F);
		this.leftArmPart3.xRot = -((float)Math.PI / 24.0F);

		this.rightLegPart1.xRot = -((float)Math.PI / 14.0F);
		this.leftLegPart1.xRot = -((float)Math.PI / 14.0F);

		this.rightLegPart3.xRot = (float)Math.PI / 5.0F;
		this.leftLegPart3.xRot = (float)Math.PI / 5.0F;
		this.rightLegPart4.xRot = -((float)Math.PI * 5.0F / 16.0F);
		this.leftLegPart4.xRot = -((float)Math.PI * 5.0F / 16.0F);
		this.rightLegPart4.yRot = (float)Math.PI / 48.0F;
		this.leftLegPart4.yRot = -((float)Math.PI / 48.0F);
		this.rightLegPart5.xRot = -((float)Math.PI / 3.0F);
		this.leftLegPart5.xRot = -((float)Math.PI / 3.0F);
		this.rightLegPart2.xRot = 0.0F;
		this.leftLegPart2.xRot = 0.0F;

		this.rightEar.xRot = -((float)Math.PI / 32.0F);
		this.leftEar.xRot = -((float)Math.PI / 32.0F);
		this.rightEar.zRot += Mth.cos(ageInTicks * 0.033F + (float)Math.PI / 4.0F) * 0.045F;
		this.leftEar.zRot += Mth.cos(ageInTicks * 0.033F + (float)Math.PI / 4.0F) * 0.045F;
		this.rightEar.zRot = -((float)Math.PI / 36.0F);
		this.rightEar.zRot += Mth.sin(ageInTicks * 0.04F) * 0.03F;
		this.leftEar.zRot = (float)Math.PI / 36.0F;
		this.leftEar.zRot += Mth.sin(ageInTicks * 0.04F) * -0.03F;

		this.rightEarPart.zRot = 0.0F;
		this.leftEarPart.zRot = 0.0F;

		this.tail1.xRot = (float)Math.PI * 8.0F / 15.0F;
		this.tail1.xRot += Mth.sin(ageInTicks * 0.09F) * 0.3F;
		this.tail1.yRot = Mth.cos(ageInTicks * 0.033F) * 0.36F;
		this.tail2.xRot = (float)Math.PI / 7.0F;
		this.tail2.xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 8.0F) * 0.024F;
		this.tail3.xRot = (float)Math.PI / 9.0F;
		this.tail3.xRot += Mth.sin(ageInTicks * 0.09F + (float)Math.PI / 4.0F) * 0.03F;
		this.tail4.xRot = 0.0F;

		this.hairPart.xRot = (float)Math.PI / 12.0F;
		this.hairPart.xRot += Mth.sin(ageInTicks * 0.075F) * 0.045F;
	}

	@Override
	protected float getLegRotZ()
	{
		return -((float)Math.PI * 0.004F);
	}
}