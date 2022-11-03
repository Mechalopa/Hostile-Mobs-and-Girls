package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.HumanoidModel;
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
public class GiantMummyModel<T extends Mob> extends HumanoidModel<T>
{
//	private ModelPart bodyPart1;
//	private ModelPart bodyPart2;
	private ModelPart clothPart1;
	private ModelPart clothPart2;
	private ModelPart rightArmPart;
	private ModelPart leftArmPart;
	private ModelPart rightLegPart1;
	private ModelPart leftLegPart1;
	private ModelPart rightLegPart2;
	private ModelPart leftLegPart2;

	public GiantMummyModel(ModelPart modelPart)
	{
		super(modelPart);
		this.hat.visible = false;
//		this.bodyPart1 = this.body.getChild("body_part_1");
//		this.bodyPart2 = this.bodyPart1.getChild("body_part_2");
		this.clothPart1 = this.body.getChild("cloth_part_1");
		this.clothPart2 = this.clothPart1.getChild("cloth_part_2");
		this.rightArmPart = this.rightArm.getChild("right_arm_part");
		this.leftArmPart = this.leftArm.getChild("left_arm_part");
		this.rightLegPart1 = this.rightLeg.getChild("right_leg_part_1");
		this.leftLegPart1 = this.leftLeg.getChild("left_leg_part_1");
		this.rightLegPart2 = this.rightLegPart1.getChild("right_leg_part_2");
		this.leftLegPart2 = this.leftLegPart1.getChild("left_leg_part_2");
	}

	public static LayerDefinition createBodyLayer()
	{
		float f = -10.0F;
		MeshDefinition md = HumanoidModel.createMesh(CubeDeformation.NONE, f);
		PartDefinition pd = md.getRoot();
		ModClientUtils.addC(pd, "head", 0, 0, -4.0F, -8.5F, -4.0F, 8.0F, 9.0F, 8.0F, 0.0F, f, 0.0F);
		ModClientUtils.addC(pd, "hat", 0, 0, -0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, f, 0.0F);
		PartDefinition bodypd = ModClientUtils.addC(pd, "body", 0, 76, -8.0F, 0.0F, -5.0F, 16.0F, 10.0F, 10.0F, 0.0F, f, 0.0F);
		PartDefinition bp1pd = ModClientUtils.addC(bodypd, "body_part_1", 0, 96, -6.0F, 0.0F, -4.5F, 12.0F, 3.0F, 9.0F, 0.0F, 10.0F, 0.0F);
		ModClientUtils.addC(bp1pd, "body_part_2", 0, 46, -4.5F, 0.0F, -3.5F, 9.0F, 6.0F, 7.0F, 0.0F, 3.0F, 0.0F);
		PartDefinition cp1pd = ModClientUtils.addC(bodypd, "cloth_part_1", 0, 60, -3.0F, 0.0F, 0.0F, 6.0F, 4.0F, 1.0F, -3.0F, 10.0F, -5.0F);
		ModClientUtils.addC(cp1pd, "cloth_part_2", 0, 66, -3.0F, 0.0F, 0.0F, 6.0F, 4.0F, 1.0F, 0.0F, 4.0F, 0.0F);
		PartDefinition rapd = ModClientUtils.addC(pd, "right_arm", 40, 0, -4.0F, -2.0F, -2.0F, 5.0F, 12.0F, 6.0F, -8.75F, 1.0F + f, -0.5F, 0.005F);
		PartDefinition lapd = ModClientUtils.addC(pd, "left_arm", 40, 38, -1.0F, -2.0F, -2.0F, 5.0F, 12.0F, 6.0F, 8.75F, 1.0F + f, -0.5F, 0.005F);
		ModClientUtils.addC(rapd, "right_arm_part", 40, 18, -4.0F, -2.0F, -2.0F, 5.0F, 13.0F, 6.0F, 0.0F, 10.0F, 0.0F);
		ModClientUtils.addC(lapd, "left_arm_part", 40, 56, -1.0F, -2.0F, -2.0F, 5.0F, 13.0F, 6.0F, 0.0F, 10.0F, 0.0F);
		PartDefinition rlpd = ModClientUtils.addC(pd, "right_leg", 0, 18, -3.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, -4.0F, 19.25F + f, 2.0F);
		PartDefinition llpd = ModClientUtils.addC(pd, "left_leg", 20, 18, -2.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 4.0F, 19.25F + f, 2.0F);
		PartDefinition rlp1pd = ModClientUtils.addC(rlpd, "right_leg_part_1", 0, 18, -3.0F, -1.0F, -3.0F, 5.0F, 8.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.005F);
		PartDefinition llp1pd = ModClientUtils.addC(llpd, "left_leg_part_1", 20, 18, -2.0F, -1.0F, -3.0F, 5.0F, 8.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.005F);
		ModClientUtils.addC(rlp1pd, "right_leg_part_2", 0, 32, -3.0F, -2.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F, 8.0F, 0.0F);
		ModClientUtils.addC(llp1pd, "left_leg_part_2", 20, 32, -2.0F, -2.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F, 8.0F, 0.0F);
		return LayerDefinition.create(md, 64, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float f = -10.0F;
		this.head.y = f;
		this.hat.y = f;
		this.body.y = f;
		this.body.xRot = (float)Math.PI / 36.0F;
		this.rightArm.setPos(-8.75F, 1.0F + f, -0.5F);
		this.leftArm.setPos(8.75F, 1.0F + f, -0.5F);
		this.rightLeg.y = 19.25F + f;
		this.leftLeg.y = 19.25F + f;
		this.rightLeg.z = 2.0F;
		this.leftLeg.z = 2.0F;

		this.rightArm.xRot += (float)Math.PI / 18.0F;
		this.leftArm.xRot += (float)Math.PI / 18.0F;
		this.rightArm.zRot = (float)Math.PI / 18.0F;
		this.leftArm.zRot = -((float)Math.PI / 18.0F);
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.12F) * 0.03F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.12F) * 0.03F;
		this.rightArmPart.xRot = -((float)Math.PI * 2.0F / 15.0F);
		this.leftArmPart.xRot = -((float)Math.PI * 2.0F / 15.0F);
		this.rightArmPart.xRot += Mth.sin(ageInTicks * 0.12F + (float)Math.PI / 4.0F) * 0.03F;
		this.leftArmPart.xRot += Mth.sin(ageInTicks * 0.12F + (float)Math.PI / 4.0F) * 0.03F;

		this.rightLegPart1.xRot = -((float)Math.PI / 24.0F);
		this.leftLegPart1.xRot = -((float)Math.PI / 24.0F);
		this.rightLegPart2.xRot = (float)Math.PI / 15.0F;
		this.leftLegPart2.xRot = (float)Math.PI / 15.0F;

		this.clothPart1.xRot = -((float)Math.PI / 36.0F);
		this.clothPart1.xRot += Mth.sin(ageInTicks * 0.066F) * 0.024F;
		this.clothPart2.xRot = -((float)Math.PI / 27.0F);
		this.clothPart2.xRot += Mth.sin(ageInTicks * 0.066F + (float)Math.PI / 3.0F) * 0.036F;
	}
}