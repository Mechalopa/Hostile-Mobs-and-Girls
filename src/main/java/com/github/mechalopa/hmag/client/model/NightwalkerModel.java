package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NightwalkerModel<T extends Mob> extends HierarchicalModel<T>
{
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart body;
//	private final ModelPart skirt1;
//	private final ModelPart skirt2;
	private final ModelPart rightWing;
	private final ModelPart leftWing;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart hairPart;
	private final ModelPart rightHairLeg1;
	private final ModelPart leftHairLeg1;
	private final ModelPart rightHairLeg2;
	private final ModelPart leftHairLeg2;
	private final ModelPart rightHairLeg3;
	private final ModelPart leftHairLeg3;
	private final ModelPart rightHairLeg4;
	private final ModelPart leftHairLeg4;
	private final ModelPart rightHairLeg5A;
	private final ModelPart rightHairLeg5B;
	private final ModelPart rightHairLeg5C;
	private final ModelPart rightHairLeg5D;
	private final ModelPart leftHairLeg5A;
	private final ModelPart leftHairLeg5B;
	private final ModelPart leftHairLeg5C;
	private final ModelPart leftHairLeg5D;
	private final ModelPart rightHairLeg6;
	private final ModelPart leftHairLeg6;

	public NightwalkerModel(ModelPart modelPart)
	{
		this.root = modelPart;
		this.head = modelPart.getChild("head");
		this.body = modelPart.getChild("body");
//		this.skirt1 = this.body.getChild("skirt_1");
//		this.skirt2 = this.body.getChild("skirt_2");
		this.rightWing = this.body.getChild("right_wing");
		this.leftWing = this.body.getChild("left_wing");
		this.rightLeg = this.body.getChild("right_leg");
		this.leftLeg = this.body.getChild("left_leg");
		this.hairPart = this.head.getChild("hair_part");
		this.rightHairLeg1 = modelPart.getChild("right_hair_leg_1");
		this.leftHairLeg1 = modelPart.getChild("left_hair_leg_1");
		this.rightHairLeg2 = this.rightHairLeg1.getChild("right_hair_leg_2");
		this.leftHairLeg2 = this.leftHairLeg1.getChild("left_hair_leg_2");
		this.rightHairLeg3 = this.rightHairLeg2.getChild("right_hair_leg_3");
		this.leftHairLeg3 = this.leftHairLeg2.getChild("left_hair_leg_3");
		this.rightHairLeg4 = this.rightHairLeg3.getChild("right_hair_leg_4");
		this.leftHairLeg4 = this.leftHairLeg3.getChild("left_hair_leg_4");
		this.rightHairLeg5A = this.rightHairLeg4.getChild("right_hair_leg_5_a");
		this.rightHairLeg5B = this.rightHairLeg4.getChild("right_hair_leg_5_b");
		this.rightHairLeg5C = this.rightHairLeg4.getChild("right_hair_leg_5_c");
		this.rightHairLeg5D = this.rightHairLeg4.getChild("right_hair_leg_5_d");
		this.leftHairLeg5A = this.leftHairLeg4.getChild("left_hair_leg_5_a");
		this.leftHairLeg5B = this.leftHairLeg4.getChild("left_hair_leg_5_b");
		this.leftHairLeg5C = this.leftHairLeg4.getChild("left_hair_leg_5_c");
		this.leftHairLeg5D = this.leftHairLeg4.getChild("left_hair_leg_5_d");
		this.rightHairLeg6 = this.rightHairLeg2.getChild("right_hair_leg_6");
		this.leftHairLeg6 = this.leftHairLeg2.getChild("left_hair_leg_6");
	}

	public static LayerDefinition createBodyLayer()
	{
		float f = -5.0F;
		MeshDefinition md = new MeshDefinition();
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 0, -3.0F, -6.0F - 1.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, f, 0.0F, 1.0F);
		ModClientUtils.addC(headpd, "hat", 32, 0, -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.5F);
		PartDefinition bodypd = ModClientUtils.addC(pd, "body", 16, 16, -2.0F, 0.0F, -1.5F, 4.0F, 5.0F, 3.0F, 0.0F, f, 0.0F);
		ModClientUtils.addC(bodypd, "skirt_1", 42, 26, -2.5F, 0.0F, -2.0F, 5.0F, 2.0F, 4.0F, 0.0F, 2.0F, 0.0F);
		ModClientUtils.addC(bodypd, "skirt_2", 42, 32, -3.0F, 0.0F, -2.5F, 6.0F, 4.0F, 5.0F, 0.0F, 4.0F, 0.0F);
		ModClientUtils.addC(bodypd, "right_wing", 40, 92, -6.0F, -1.0F, 0.0F, 6.0F, 7.0F, 1.0F, -1.5F, 2.75F, 2.0F);
		ModClientUtils.addC(bodypd, "left_wing", 40, 92, 0.0F, -1.0F, 0.0F, 6.0F, 7.0F, 1.0F, 1.5F, 2.75F, 2.0F, true);

		ModClientUtils.addC(bodypd, "right_leg", 0, 16, -2.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, -0.5F, 4.0F, 0.0F);
		ModClientUtils.addC(bodypd, "left_leg", 0, 16, 0.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.5F, 4.0F, 0.0F, true);

		ModClientUtils.addC(headpd, "hair_part", 32, 88, -4.0F, 0.0F, -1.0F, 8.0F, 2.0F, 1.0F, 0.0F, 0.0F, 4.0F);

		PartDefinition rhl1pd = ModClientUtils.addC(pd, "right_hair_leg_1", 32, 16, -3.0F, -2.5F, -2.5F, 3.0F, 5.0F, 5.0F, -3.0F, f - 7.0F, 0.0F);
		PartDefinition lhl1pd = ModClientUtils.addC(pd, "left_hair_leg_1", 32, 16, 0.0F, -2.5F, -2.5F, 3.0F, 5.0F, 5.0F, 3.0F, f - 7.0F, 0.0F, true);
		PartDefinition rhl2pd = ModClientUtils.addC(rhl1pd, "right_hair_leg_2", 0, 24, -10.0F, -5.0F, -5.5F, 10.0F, 12.0F, 11.0F, -3.0F, 0.0F, 0.0F);
		PartDefinition lhl2pd = ModClientUtils.addC(lhl1pd, "left_hair_leg_2", 0, 24, 0.0F, -5.0F, -5.5F, 10.0F, 12.0F, 11.0F, 3.0F, 0.0F, 0.0F, true);
		PartDefinition rhl3pd = ModClientUtils.addC(rhl2pd, "right_hair_leg_3", 0, 48, -3.0F, 0.0F, -4.0F, 6.0F, 4.0F, 8.0F, -4.0F, 7.0F, 0.0F);
		PartDefinition lhl3pd = ModClientUtils.addC(lhl2pd, "left_hair_leg_3", 0, 48, -3.0F, 0.0F, -4.0F, 6.0F, 4.0F, 8.0F, 4.0F, 7.0F, 0.0F, true);
		PartDefinition rhl4pd = ModClientUtils.addC(rhl3pd, "right_hair_leg_4", 0, 60, -3.5F, 0.0F, -4.5F, 7.0F, 25.0F, 9.0F, 0.0F, 4.0F, 0.0F);
		PartDefinition lhl4pd = ModClientUtils.addC(lhl3pd, "left_hair_leg_4", 0, 60, -3.5F, 0.0F, -4.5F, 7.0F, 25.0F, 9.0F, 0.0F, 4.0F, 0.0F, true);
		ModClientUtils.addC(rhl4pd, "right_hair_leg_5_a", 32, 48, -3.5F, 0.0F, 0.0F, 7.0F, 15.0F, 1.0F, 0.0F, 8.0F, -4.5F);
		ModClientUtils.addC(lhl4pd, "left_hair_leg_5_a", 32, 48, -3.5F, 0.0F, 0.0F, 7.0F, 15.0F, 1.0F, 0.0F, 8.0F, -4.5F, true);
		ModClientUtils.addC(rhl4pd, "right_hair_leg_5_b", 48, 48, -3.5F, 0.0F, -1.0F, 7.0F, 15.0F, 1.0F, 0.0F, 8.0F, 4.5F);
		ModClientUtils.addC(lhl4pd, "left_hair_leg_5_b", 48, 48, -3.5F, 0.0F, -1.0F, 7.0F, 15.0F, 1.0F, 0.0F, 8.0F, 4.5F, true);
		ModClientUtils.addC(rhl4pd, "right_hair_leg_5_c", 32, 64, 0.0F, 0.0F, -4.5F, 1.0F, 15.0F, 9.0F, -3.5F, 8.0F, 0.0F);
		ModClientUtils.addC(lhl4pd, "left_hair_leg_5_c", 0, 96, 0.0F, 0.0F, -4.5F, 1.0F, 15.0F, 9.0F, -3.5F, 8.0F, 0.0F, true);
		ModClientUtils.addC(rhl4pd, "right_hair_leg_5_d", 0, 96, -1.0F, 0.0F, -4.5F, 1.0F, 15.0F, 9.0F, 3.5F, 8.0F, 0.0F);
		ModClientUtils.addC(lhl4pd, "left_hair_leg_5_d", 32, 64, -1.0F, 0.0F, -4.5F, 1.0F, 15.0F, 9.0F, 3.5F, 8.0F, 0.0F, true);
		ModClientUtils.addC(rhl2pd, "right_hair_leg_6", 24, 96, 0.0F, 0.0F, -5.5F, 1.0F, 6.0F, 11.0F, -10.0F, 4.0F, 0.0F);
		ModClientUtils.addC(lhl2pd, "left_hair_leg_6", 24, 96, -1.0F, 0.0F, -5.5F, 1.0F, 6.0F, 11.0F, 10.0F, 4.0F, 0.0F, true);

		return LayerDefinition.create(md, 64, 128);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw / (180.0F / (float)Math.PI);
		this.head.xRot = headPitch / (180.0F / (float)Math.PI);

		this.body.xRot = (float)Math.PI / 30.0F;
		this.body.xRot += Mth.sin(ageInTicks * 0.09F) * 0.036F;

		float f = Math.min(0.125F, limbSwingAmount);
		float f1 = limbSwing * 0.75F;
		this.leftHairLeg1.xRot = Mth.sin(f1) * 2.0F * f;
		this.rightHairLeg1.xRot = Mth.sin(f1 + (float)Math.PI) * 2.0F * f;
		float f2 = 12.0F / (180.0F / (float)Math.PI);
		this.leftHairLeg1.zRot = f2 * Mth.cos(f1) * f;
		this.rightHairLeg1.zRot = f2 * Mth.cos(f1 + (float)Math.PI) * f;
		this.leftHairLeg1.y = -12.0F + 2.0F * Mth.sin(f1 + (float)Math.PI) * 2.0F * f;
		this.rightHairLeg1.y = -12.0F + 2.0F * Mth.sin(f1) * 2.0F * f;

		this.body.zRot = Mth.sin(f1 * 0.5F - (float)Math.PI / 6.0F) * 0.5F * f;

		this.rightLeg.xRot = -((float)Math.PI / 27.0F);
		this.leftLeg.xRot = -((float)Math.PI / 27.0F);
		this.rightLeg.xRot += Mth.sin(ageInTicks * 0.09F) * 0.036F;
		this.leftLeg.xRot += Mth.sin(ageInTicks * 0.09F) * 0.036F;
		this.rightLeg.zRot = (float)Math.PI / 30.0F;
		this.leftLeg.zRot = -((float)Math.PI / 30.0F);
		this.rightLeg.zRot += Mth.cos(ageInTicks * 0.067F) * 0.015F;
		this.leftLeg.zRot -= Mth.cos(ageInTicks * 0.067F) * 0.015F;

		this.hairPart.xRot = (float)Math.PI / 30.0F;
		this.hairPart.xRot += Mth.sin(ageInTicks * 0.18F) * 0.033F;

		float f3 = (float)Math.PI / 30.0F;
		f3 += Mth.sin(ageInTicks * 0.18F + (float)Math.PI / 2.0F) * 0.012F;

		this.rightHairLeg5A.xRot = -f3;
		this.leftHairLeg5A.xRot = -f3;
		this.rightHairLeg5B.xRot = f3;
		this.leftHairLeg5B.xRot = f3;
		this.rightHairLeg5C.zRot = f3;
		this.leftHairLeg5C.zRot = f3;
		this.rightHairLeg5D.zRot = -f3;
		this.leftHairLeg5D.zRot = -f3;

		this.rightHairLeg6.zRot = (float)Math.PI / 30.0F;
		this.leftHairLeg6.zRot = -((float)Math.PI / 30.0F);
		this.rightHairLeg6.zRot += Mth.sin(ageInTicks * 0.18F + (float)Math.PI / 4.0F) * 0.015F;
		this.leftHairLeg6.zRot -= Mth.sin(ageInTicks * 0.18F + (float)Math.PI / 4.0F) * 0.015F;

		this.rightWing.yRot = (float)Math.PI * 5.0F / 12.0F;
		this.leftWing.yRot = -((float)Math.PI * 5.0F / 12.0F);
		this.rightWing.yRot += Mth.cos(ageInTicks * 0.18F) * 0.06F;
		this.leftWing.yRot -= Mth.cos(ageInTicks * 0.18F) * 0.06F;
		this.rightWing.zRot = (float)Math.PI / 12.0F;
		this.leftWing.zRot = -((float)Math.PI / 12.0F);
		this.rightWing.zRot += Mth.cos(ageInTicks * 0.18F + (float)Math.PI / 3.0F) * 0.033F;
		this.leftWing.zRot -= Mth.cos(ageInTicks * 0.18F + (float)Math.PI / 3.0F) * 0.033F;
	}
}