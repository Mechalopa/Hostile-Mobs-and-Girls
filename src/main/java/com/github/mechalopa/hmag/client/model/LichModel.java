package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.AnimationUtils;
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
public class LichModel<T extends Mob> extends HumanoidModel<T>
{
	private ModelPart rightArmPart;
	private ModelPart leftArmPart;
	private ModelPart rightLegPart1;
	private ModelPart leftLegPart1;
	private ModelPart rightLegPart2;
	private ModelPart leftLegPart2;
	private ModelPart wand;
	private ModelPart wandTop;
	private ModelPart cloak;
	private ModelPart cloakPart1;
	private ModelPart cloakPart2;

	public LichModel(ModelPart modelPart)
	{
		super(modelPart);
		this.rightArmPart = this.rightArm.getChild("right_arm_part");
		this.leftArmPart = this.leftArm.getChild("left_arm_part");
		this.rightLegPart1 = this.rightLeg.getChild("right_leg_part_1");
		this.leftLegPart1 = this.leftLeg.getChild("left_leg_part_1");
		this.rightLegPart2 = this.rightLeg.getChild("right_leg_part_2");
		this.leftLegPart2 = this.leftLeg.getChild("left_leg_part_2");
		this.wand = this.rightArm.getChild("wand");
		this.wandTop = this.wand.getChild("wand_top");
		this.cloak = this.body.getChild("cloak");
		this.cloakPart1 = this.cloak.getChild("cloak_part_1");
		this.cloakPart2 = this.cloakPart1.getChild("cloak_part_2");
	}

	public static LayerDefinition createBodyLayer()
	{
		float f = -4.0F;
		MeshDefinition md = HumanoidModel.createMesh(CubeDeformation.NONE, f);
		PartDefinition pd = md.getRoot();
		PartDefinition rapd = ModClientUtils.addC(pd, "right_arm", 40, 16, -2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, -5.0F, 2.0F + f, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, "left_arm", 40, 16, 0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 5.0F, 2.0F + f, 0.0F, true);
		ModClientUtils.addC(rapd, "right_arm_part", 40, 32, -3.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, -2.0F, 0.0F);
		ModClientUtils.addC(lapd, "left_arm_part", 40, 32, -1.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, -2.0F, 0.0F, true);
		PartDefinition rlpd = ModClientUtils.addC(pd, "right_leg", 0, 16, -1.0F, 0.0F, -1.0F, 2.0F, 16.0F, 2.0F, -2.0F, 12.0F + f, 0.0F);
		PartDefinition llpd = ModClientUtils.addC(pd, "left_leg", 0, 16, -1.0F, 0.0F, -1.0F, 2.0F, 16.0F, 2.0F, 2.0F, 12.0F + f, 0.0F, true);
		ModClientUtils.addC(rlpd, "right_leg_part_1", 0, 34, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		ModClientUtils.addC(llpd, "left_leg_part_1", 0, 34, -2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F, true);
		ModClientUtils.addC(rlpd, "right_leg_part_2", 0, 44, -2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, 8.0F, 0.0F);
		ModClientUtils.addC(llpd, "left_leg_part_2", 0, 44, -2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, 8.0F, 0.0F, true);
		PartDefinition wpd = ModClientUtils.addC(rapd, "wand", 16, 40, -1.0F, -14.0F, -1.0F, 2.0F, 22.0F, 2.0F, -0.5F, 8.5F, 0.0F);
		ModClientUtils.addC(wpd, "wand_top", 16, 32, -2.0F, -16.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 22.0F, 0.0F);
		PartDefinition bodypd = pd.getChild("body");
		PartDefinition cpd = ModClientUtils.addC(bodypd, "cloak", 24, 44, -4.5F, 0.0F, -1.0F, 9.0F, 7.0F, 1.0F, 0.0F, 0.0F, 2.0F);
		PartDefinition cp1pd = ModClientUtils.addC(cpd, "cloak_part_1", 24, 52, -4.5F, 0.0F, -1.0F, 9.0F, 7.0F, 1.0F, 0.0F, 7.0F, 0.0F);
		ModClientUtils.addC(cp1pd, "cloak_part_2", 44, 44, -4.5F, 0.0F, -1.0F, 9.0F, 8.0F, 1.0F, 0.0F, 7.0F, 0.0F);
		return LayerDefinition.create(md, 64, 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entityIn), this.attackTime, ageInTicks);

		float f = -4.0F;
		this.body.y = f;
		this.head.y = f;
		this.hat.y = f;
		this.rightArm.setPos(-5.0F, 2.0F + f, 0.0F);
		this.leftArm.setPos(5.0F, 2.0F + f, 0.0F);
		this.rightLeg.y = 12.0F + f;
		this.leftLeg.y = 12.0F + f;

		this.wand.xRot = -((float)Math.PI * 17.0F / 36.0F);
		this.wandTop.xRot = 0.0F;

		this.cloak.xRot = (float)Math.PI / 15.0F;
		this.cloak.xRot -= Mth.cos(limbSwing * 0.45F) * 2.0F * limbSwingAmount * 0.15F;
		this.cloak.xRot += Mth.sin(ageInTicks * 0.067F) * 0.036F;
		this.cloakPart1.xRot = (float)Math.PI / 12.0F;
		this.cloakPart1.xRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 8.0F) * 0.015F;
		this.cloakPart2.xRot = (float)Math.PI / 12.0F;
		this.cloakPart2.xRot += Mth.sin(ageInTicks * 0.067F + (float)Math.PI / 4.0F) * 0.015F;

		this.rightArmPart.xRot = 0.0F;
		this.leftArmPart.xRot = 0.0F;
		this.rightLegPart1.xRot = 0.0F;
		this.leftLegPart1.xRot = 0.0F;
		this.rightLegPart2.xRot = 0.0F;
		this.leftLegPart2.xRot = 0.0F;
	}

	public boolean isAggressive(T entityIn)
	{
		return entityIn.isAggressive();
	}
}