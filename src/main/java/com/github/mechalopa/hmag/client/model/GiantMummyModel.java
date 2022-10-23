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
	private ModelPart bodyPart1;
	private ModelPart bodyPart2;
	private ModelPart rightArmPart1;
	private ModelPart leftArmPart1;
	private ModelPart rightLegPart1;
	private ModelPart leftLegPart1;
	private ModelPart rightLegPart2;
	private ModelPart leftLegPart2;

	public GiantMummyModel(ModelPart modelPart)
	{
		super(modelPart);
		this.bodyPart1 = this.body.getChild("body_part_1");
		this.bodyPart2 = this.bodyPart1.getChild("body_part_2");
		this.rightArmPart1 = this.rightArm.getChild("right_arm_part_1");
		this.leftArmPart1 = this.leftArm.getChild("left_arm_part_1");
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
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 0, -4.0F, -8.5F, -4.0F, 8.0F, 9.0F, 8.0F, 0.0F, f, 0.0F);
		PartDefinition bodypd = ModClientUtils.addC(pd, "body", 0, 56, -8.0F, 0.0F, -5.0F, 16.0F, 10.0F, 10.0F, 0.0F, f, 0.0F);
		PartDefinition bp1pd = ModClientUtils.addC(bodypd, "body_part_1", 0, 80, -6.0F, 0.0F, -4.5F, 12.0F, 3.0F, 9.0F, 0.0F, 10.0F, 0.0F);
		ModClientUtils.addC(bp1pd, "body_part_2", 0, 96, -4.5F, 0.0F, -3.5F, 9.0F, 6.0F, 7.0F, 0.0F, 3.0F, 0.0F);
		PartDefinition rapd = ModClientUtils.addC(pd, "right_arm", 32, 16, -3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 6.0F, -8.75F, 1.0F + f, -0.5F);
		PartDefinition lapd = ModClientUtils.addC(pd, "left_arm", 32, 16, -1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 6.0F, 8.75F, 1.0F + f, -0.5F, true);
		PartDefinition rap1pd = ModClientUtils.addC(rapd, "right_arm_part_1", 56, 16, -3.0F, -2.0F, -2.0F, 4.0F, 13.0F, 6.0F, 0.0F, 10.0F, 0.0F, 0.005F);
		PartDefinition lap1pd = ModClientUtils.addC(lapd, "left_arm_part_1", 56, 16, -1.0F, -2.0F, -2.0F, 4.0F, 13.0F, 6.0F, 0.0F, 10.0F, 0.0F, true, 0.005F);
		ModClientUtils.addC(pd, "right_leg", 0, 16, -0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, -2.0F, 12.0F, 0.0F);
		ModClientUtils.addC(pd, "left_leg", 0, 16, -0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 2.0F, 12.0F, 0.0F, true);
		PartDefinition rlpd = ModClientUtils.addC(pd, "right_leg", 0, 24, -3.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, -4.0F, 19.25F + f, 2.0F);
		PartDefinition llpd = ModClientUtils.addC(pd, "left_leg", 0, 24, -2.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 4.0F, 19.25F + f, 2.0F, true);
		PartDefinition rlp1pd = ModClientUtils.addC(rlpd, "right_leg_part_1", 0, 24, -3.0F, -1.0F, -3.0F, 5.0F, 8.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.005F);
		PartDefinition llp1pd = ModClientUtils.addC(llpd, "left_leg_part_1", 0, 24, -2.0F, -1.0F, -3.0F, 5.0F, 8.0F, 5.0F, 0.0F, 0.0F, 0.0F, true, 0.005F);
		ModClientUtils.addC(rlp1pd, "right_leg_part_2", 0, 40, -3.0F, -2.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F, 8.0F, 0.0F);
		ModClientUtils.addC(llp1pd, "left_leg_part_2", 0, 40, -2.0F, -2.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F, 8.0F, 0.0F, true);
		return LayerDefinition.create(md, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
//		this.rightArm.xRot = 0.0F;
//		this.leftArm.xRot = 0.0F;

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
		this.rightArmPart1.xRot = -((float)Math.PI * 2.0F / 15.0F);
		this.leftArmPart1.xRot = -((float)Math.PI * 2.0F / 15.0F);
		this.rightArmPart1.xRot += Mth.sin(ageInTicks * 0.12F + (float)Math.PI / 4.0F) * 0.03F;
		this.leftArmPart1.xRot += Mth.sin(ageInTicks * 0.12F + (float)Math.PI / 4.0F) * 0.03F;

		this.rightLegPart1.xRot = -((float)Math.PI / 24.0F);
		this.leftLegPart1.xRot = -((float)Math.PI / 24.0F);
		this.rightLegPart2.xRot = (float)Math.PI / 15.0F;
		this.leftLegPart2.xRot = (float)Math.PI / 15.0F;
	}
}