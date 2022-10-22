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
	private ModelPart rightArmPart1;
	private ModelPart leftArmPart1;

	public GiantMummyModel(ModelPart modelPart)
	{
		super(modelPart);
		this.bodyPart1 = this.body.getChild("body_part_1");
		this.rightArmPart1 = this.rightArm.getChild("right_arm_part_1");
		this.leftArmPart1 = this.leftArm.getChild("left_arm_part_1");
	}

	public static LayerDefinition createBodyLayer()
	{
		float f = -8.0F;
		MeshDefinition md = HumanoidModel.createMesh(CubeDeformation.NONE, f);
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 0, -4.0F, -8.5F, -4.0F, 8.0F, 9.0F, 8.0F, 0.0F, f, 0.0F);
		PartDefinition bodypd = ModClientUtils.addC(pd, "body", 0, 56, -9.0F, 0.0F, -5.0F, 18.0F, 13.0F, 10.0F, 0.0F, f, 0.0F);
		ModClientUtils.addC(bodypd, "body_part_1", 0, 80, -4.0F, 0.0F, -3.5F, 8.0F, 6.0F, 7.0F, 0.0F, 13.0F, 0.0F);
		PartDefinition rapd = ModClientUtils.addC(pd, "right_arm", 32, 16, -3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 6.0F, -9.75F, 1.0F + f, -1.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, "left_arm", 32, 16, -1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 6.0F, 9.75F, 1.0F + f, -1.0F, true);
		PartDefinition rap1pd = ModClientUtils.addC(rapd, "right_arm_part_1", 56, 16, -3.0F, -1.0F, -2.0F, 4.0F, 12.0F, 6.0F, 0.0F, 10.0F, 0.0F, 0.005F);
		PartDefinition lap1pd = ModClientUtils.addC(lapd, "left_arm_part_1", 56, 16, -1.0F, -1.0F, -2.0F, 4.0F, 12.0F, 6.0F, 0.0F, 10.0F, 0.0F, true, 0.005F);
		PartDefinition rlpd = ModClientUtils.addC(pd, "right_leg", 0, 24, -3.0F, -3.0F, -3.0F, 5.0F, 14.0F, 5.0F, -4.0F, 21.0F + f, 2.0F);
		PartDefinition llpd = ModClientUtils.addC(pd, "left_leg", 0, 24, -2.0F, -3.0F, -3.0F, 5.0F, 14.0F, 5.0F, 4.0F, 21.0F + f, 2.0F, true);
		return LayerDefinition.create(md, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float f = -8.0F;
		this.head.y = f;
		this.hat.y = f;
		this.body.y = f;
		this.body.xRot = (float)Math.PI / 36.0F;
		this.rightArm.setPos(-9.75F, 1.0F + f, -0.5F);
		this.leftArm.setPos(9.75F, 1.0F + f, -0.5F);
		this.rightLeg.y = 21.0F + f;
		this.leftLeg.y = 21.0F + f;
		this.rightLeg.z = 2.0F;
		this.leftLeg.z = 2.0F;

		this.rightArm.xRot = -((float)Math.PI / 36.0F);
		this.leftArm.xRot = -((float)Math.PI / 36.0F);
		this.rightArm.zRot = (float)Math.PI / 18.0F;
		this.leftArm.zRot = -((float)Math.PI / 18.0F);
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.12F) * 0.03F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.12F) * 0.03F;
		this.rightArmPart1.xRot = -((float)Math.PI / 15.0F);
		this.leftArmPart1.xRot = -((float)Math.PI / 15.0F);
	}
}