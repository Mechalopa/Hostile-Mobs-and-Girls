package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.HierarchicalModel;
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
public class SwamperModel<T extends Mob> extends HierarchicalModel<T>
{
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart headPart;
	private final ModelPart body;
	private final ModelPart rightLeg1;
	private final ModelPart leftLeg1;
	private final ModelPart rightLeg2;
	private final ModelPart leftLeg2;
	private final ModelPart rightLeg3;
	private final ModelPart leftLeg3;
	private final ModelPart rightLeg1Part;
	private final ModelPart leftLeg1Part;
	private final ModelPart rightLeg2Part;
	private final ModelPart leftLeg2Part;
	private final ModelPart rightLeg3Part;
	private final ModelPart leftLeg3Part;

	public SwamperModel(ModelPart modelPart)
	{
		this.root = modelPart;
		this.head = modelPart.getChild("head");
		this.headPart = this.head.getChild("head_part");
		this.body = modelPart.getChild("body");
		this.rightLeg1 = modelPart.getChild("right_leg_1");
		this.leftLeg1 = modelPart.getChild("left_leg_1");
		this.rightLeg2 = modelPart.getChild("right_leg_2");
		this.leftLeg2 = modelPart.getChild("left_leg_2");
		this.rightLeg3 = modelPart.getChild("right_leg_3");
		this.leftLeg3 = modelPart.getChild("left_leg_3");
		this.rightLeg1Part = this.rightLeg1.getChild("right_leg_1_part");
		this.leftLeg1Part = this.leftLeg1.getChild("left_leg_1_part");
		this.rightLeg2Part = this.rightLeg2.getChild("right_leg_2_part");
		this.leftLeg2Part = this.leftLeg2.getChild("left_leg_2_part");
		this.rightLeg3Part = this.rightLeg3.getChild("right_leg_3_part");
		this.leftLeg3Part = this.leftLeg3.getChild("left_leg_3_part");
	}

	public static LayerDefinition createBodyLayer()
	{
		float f = 14.0F;
		MeshDefinition md = new MeshDefinition();
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 32, -6.0F, -4.0F, -6.0F, 12.0F, 4.0F, 12.0F, 0.0F, f, 0.0F);
		ModClientUtils.addC(headpd, "head_part", 0, 0, -8.0F, -12.0F, -7.0F, 16.0F, 12.0F, 16.0F, 0.0F, -3.0F, 0.0F);
		ModClientUtils.addC(pd, "body", 0, 58, -4.0F, 0.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, f - 0.5F, 0.0F);
		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 48).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 4.0F, 4.0F);
		PartDefinition rl1pd = pd.addOrReplaceChild("right_leg_1", cubelistbuilder, PartPose.offset(-3.75F, f, -4.5F));
		PartDefinition ll1pd = pd.addOrReplaceChild("left_leg_1", cubelistbuilder.mirror(), PartPose.offset(3.75F, f, -4.5F));
		PartDefinition rl2pd = pd.addOrReplaceChild("right_leg_2", cubelistbuilder, PartPose.offset(-4.75F, f, -0.5F));
		PartDefinition ll2pd = pd.addOrReplaceChild("left_leg_2", cubelistbuilder.mirror(), PartPose.offset(4.75F, f, -0.5F));
		PartDefinition rl3pd = pd.addOrReplaceChild("right_leg_3", cubelistbuilder, PartPose.offset(-4.5F, f, 4.75F));
		PartDefinition ll3pd = pd.addOrReplaceChild("left_leg_3", cubelistbuilder.mirror(), PartPose.offset(4.5F, f, 4.75F));
		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(24, 48).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.25F));
		rl1pd.addOrReplaceChild("right_leg_1_part", cubelistbuilder1, PartPose.offset(0.0F, 3.75F, 0.0F));
		ll1pd.addOrReplaceChild("left_leg_1_part", cubelistbuilder1.mirror(), PartPose.offset(0.0F, 3.75F, 0.0F));
		rl2pd.addOrReplaceChild("right_leg_2_part", cubelistbuilder1, PartPose.offset(0.0F, 3.75F, 0.0F));
		ll2pd.addOrReplaceChild("left_leg_2_part", cubelistbuilder1.mirror(), PartPose.offset(0.0F, 3.75F, 0.0F));
		rl3pd.addOrReplaceChild("right_leg_3_part", cubelistbuilder1, PartPose.offset(0.0F, 3.75F, 0.0F));
		ll3pd.addOrReplaceChild("left_leg_3_part", cubelistbuilder1.mirror(), PartPose.offset(0.0F, 3.75F, 0.0F));
		return LayerDefinition.create(md, 64, 128);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw / (180.0F / (float)Math.PI);
		this.head.xRot = headPitch / (180.0F / (float)Math.PI);
		this.headPart.xRot = -((float)Math.PI / 24.0F);
		this.headPart.xRot += Mth.sin(ageInTicks * 0.067F) * 0.024F;
		this.body.yRot = 0.0F;
		float f = limbSwing * 0.6662F;
		float f1 = Math.min(0.25F, limbSwingAmount);
		this.rightLeg1.xRot = Mth.cos(f + (float)Math.PI) * 1.4F * f1;
		this.leftLeg1.xRot = Mth.cos(f + (float)Math.PI) * 1.4F * f1;
		this.rightLeg2.xRot = Mth.cos(f + (float)Math.PI * 7.0F / 8.0F) * 1.4F * f1;
		this.leftLeg2.xRot = Mth.cos(f + (float)Math.PI * 7.0F / 8.0F) * 1.4F * f1;
		this.rightLeg3.xRot = Mth.cos(f + (float)Math.PI * 3.0F / 4.0F) * 1.4F * f1;
		this.leftLeg3.xRot = Mth.cos(f + (float)Math.PI * 3.0F / 4.0F) * 1.4F * f1;
		this.rightLeg1.yRot = (float)Math.PI / 20.0F;
		this.leftLeg1.yRot = -((float)Math.PI / 20.0F);
		this.rightLeg2.yRot = (float)Math.PI / 20.0F;
		this.leftLeg2.yRot = -((float)Math.PI / 20.0F);
		this.rightLeg3.yRot = -((float)Math.PI / 24.0F);
		this.leftLeg3.yRot = (float)Math.PI / 24.0F;
		this.rightLeg1Part.xRot = -((float)Math.PI / 18.0F);
		this.leftLeg1Part.xRot = -((float)Math.PI / 18.0F);
		this.rightLeg2Part.xRot = (float)Math.PI / 18.0F;
		this.leftLeg2Part.xRot = (float)Math.PI / 18.0F;
		this.rightLeg3Part.xRot = (float)Math.PI / 15.0F;
		this.leftLeg3Part.xRot = (float)Math.PI / 15.0F;
		this.rightLeg1.xRot += Mth.cos(f + (float)Math.PI) * 0.21F * f1;
		this.leftLeg1.xRot += Mth.cos(f + (float)Math.PI) * 0.21F * f1;
		this.rightLeg2.xRot += Mth.cos(f + (float)Math.PI * 7.0F / 8.0F) * 0.21F * f1;
		this.leftLeg2.xRot += Mth.cos(f + (float)Math.PI * 7.0F / 8.0F) * 0.21F * f1;
		this.rightLeg3.xRot += Mth.cos(f - (float)Math.PI / 4.0F) * 0.21F * f1;
		this.leftLeg3.xRot += Mth.cos(f - (float)Math.PI / 4.0F) * 0.21F * f1;
	}
}