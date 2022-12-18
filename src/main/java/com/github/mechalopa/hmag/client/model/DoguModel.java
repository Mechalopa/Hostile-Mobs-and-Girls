package com.github.mechalopa.hmag.client.model;

import java.util.Arrays;

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
public class DoguModel<T extends Mob> extends HumanoidModel<T>
{
//	private ModelPart headPart1;
	private ModelPart headPart2;
	private ModelPart headPart2Parts[] = new ModelPart[3];
//	private ModelPart rightArmPart1;
//	private ModelPart rightArmPart2;
//	private ModelPart rightArmPart3;
//	private ModelPart rightArmPart4;
//	private ModelPart leftArmPart1;
//	private ModelPart leftArmPart2;
//	private ModelPart leftArmPart3;
//	private ModelPart leftArmPart4;
//	private ModelPart rightLegPart1;
//	private ModelPart rightLegPart2;
//	private ModelPart rightLegPart3;
//	private ModelPart rightLegPart4;
//	private ModelPart leftLegPart1;
//	private ModelPart leftLegPart2;
//	private ModelPart leftLegPart3;
//	private ModelPart leftLegPart4;
//	private ModelPart bust;
//	private ModelPart skirt1;
//	private ModelPart skirt2;

	public DoguModel(ModelPart modelPart)
	{
		super(modelPart);
		this.hat.visible = false;
//		this.headPart1 = this.head.getChild("head_part_1");
		this.headPart2 = this.head.getChild("head_part_2");
		Arrays.setAll(this.headPart2Parts, (p) -> {
			return this.headPart2.getChild("head_part_2_parts_" + p);
		});
	}

	public static LayerDefinition createBodyLayer()
	{
		float f = -4.0F;
		MeshDefinition md = HumanoidModel.createMesh(CubeDeformation.NONE, f);
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 0, -4.0F, -6.0F, -4.0F, 8.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		ModClientUtils.addC(headpd, "head_part_1", 0, 84, -4.5F, -4.5F, -4.5F, 9.0F, 3.0F, 9.0F, 0.0F, 0.0F, 0.0F);
		PartDefinition headpart2pd = ModClientUtils.addC(headpd, "head_part_2", 0, 96, -2.0F, -2.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, -6.0F, 0.0F);

		for (int k = 0; k < 3; ++k)
		{
			ModClientUtils.addC(headpart2pd, "head_part_2_parts_" + k, 16, 96, -1.0F, -5.0F, -0.5F, 2.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		}

		PartDefinition bodypd = pd.getChild("body");
		ModClientUtils.addC(bodypd, "bust", 0, 56, -4.5F, 0.0F, -3.0F, 9.0F, 3.0F, 5.0F, 0.0F, 2.0F, 0.0F);
		ModClientUtils.addC(bodypd, "skirt_1", 0, 64, -5.0F, 0.0F, -3.0F, 10.0F, 2.0F, 6.0F, 0.0F, 8.0F, 0.0F);
		ModClientUtils.addC(bodypd, "skirt_2", 0, 72, -5.5F, 0.0F, -3.5F, 11.0F, 2.0F, 7.0F, 0.0F, 10.0F, 0.0F);
		PartDefinition rapd = ModClientUtils.addC(pd, "right_arm", 40, 16, -3.0F, -2.0F, -2.0F, 4.0F, 3.0F, 4.0F, -6.0F, 2.0F, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, "left_arm", 40, 16, -1.0F, -2.0F, -2.0F, 4.0F, 3.0F, 4.0F, 6.0F, 2.0F, 0.0F, true);
		ModClientUtils.addC(rapd, "right_arm_part_1", 32, 32, -3.0F, 0.0F, -2.0F, 5.0F, 1.0F, 4.0F, -1.0F, 0.0F, 0.0F);
		ModClientUtils.addC(lapd, "left_arm_part_1", 32, 32, -2.0F, 0.0F, -2.0F, 5.0F, 1.0F, 4.0F, 1.0F, 0.0F, 0.0F, true);
		ModClientUtils.addC(rapd, "right_arm_part_2", 32, 40, -2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, -2.0F, 1.0F, 0.0F);
		ModClientUtils.addC(lapd, "left_arm_part_2", 32, 40, -2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, 2.0F, 1.0F, 0.0F, true);
		ModClientUtils.addC(rapd, "right_arm_part_3", 32, 52, -2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, -2.0F, 5.0F, 0.0F);
		ModClientUtils.addC(lapd, "left_arm_part_3", 32, 52, -2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 2.0F, 5.0F, 0.0F, true);
		ModClientUtils.addC(rapd, "right_arm_part_4", 32, 60, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, -2.0F, 9.0F, 0.0F);
		ModClientUtils.addC(lapd, "left_arm_part_4", 32, 60, -1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 2.0F, 9.0F, 0.0F, true);
		PartDefinition rlpd = ModClientUtils.addC(pd, "right_leg", 0, 16, -2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, -2.75F, 12.0F, 0.0F);
		PartDefinition llpd = ModClientUtils.addC(pd, "left_leg", 0, 16, -2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 2.75F, 12.0F, 0.0F, true);
		ModClientUtils.addC(rlpd, "right_leg_part_1", 0, 32, -2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, 3.0F, 0.0F);
		ModClientUtils.addC(llpd, "left_leg_part_1", 0, 32, -2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, 3.0F, 0.0F, true);
		ModClientUtils.addC(rlpd, "right_leg_part_2", 0, 42, -2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, 7.0F, 0.0F);
		ModClientUtils.addC(llpd, "left_leg_part_2", 0, 42, -2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, 7.0F, 0.0F, true);
		ModClientUtils.addC(rlpd, "right_leg_part_3", 0, 48, -1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, 9.0F, 0.0F);
		ModClientUtils.addC(llpd, "left_leg_part_3", 0, 48, -1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, 9.0F, 0.0F, true);
		ModClientUtils.addC(rlpd, "right_leg_part_4", 16, 48, -1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(llpd, "left_leg_part_4", 16, 48, -1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 11.0F, 0.0F, true);
		return LayerDefinition.create(md, 64, 128);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float f = Mth.sin(this.attackTime * (float)Math.PI);
		float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
		float f2 = ((float)Math.PI / 15.0F) - (f * 1.2F - f1 * 0.4F);
		float f3 = (float)Math.PI / 36.0F;
		this.rightArm.xRot = f2;
		this.leftArm.xRot = f2;
		this.rightArm.xRot += Mth.sin(ageInTicks * 0.06F) * 0.06F;
		this.leftArm.xRot += Mth.sin(ageInTicks * 0.06F) * 0.06F;
		this.rightArm.zRot = f3;
		this.leftArm.zRot = -f3;
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.075F) * 0.04F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.075F) * 0.04F;
		this.rightLeg.xRot = f2;
		this.leftLeg.xRot = f2;
		this.rightLeg.xRot += Mth.sin(ageInTicks * 0.06F) * 0.06F;
		this.leftLeg.xRot += Mth.sin(ageInTicks * 0.06F) * 0.06F;
		this.rightLeg.zRot = f3;
		this.leftLeg.zRot = -f3;
		this.rightLeg.zRot += Mth.cos(ageInTicks * 0.075F) * 0.04F;
		this.leftLeg.zRot -= Mth.cos(ageInTicks * 0.075F) * 0.04F;

		this.headPart2Parts[0].zRot = (float)Math.PI / 4.0F;
		this.headPart2Parts[1].zRot = 0.0F;
		this.headPart2Parts[2].zRot = -((float)Math.PI / 4.0F);
	}
}