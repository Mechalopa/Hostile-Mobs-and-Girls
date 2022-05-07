package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.entity.DyssomniaEntity;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DyssomniaModel<T extends DyssomniaEntity> extends HierarchicalModel<T>
{
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart headPart1;
	private final ModelPart headPart2Left1;
	private final ModelPart headPart2Right1;
	private final ModelPart headPart2Left2;
	private final ModelPart headPart2Right2;
	private final ModelPart leftFang1;
	private final ModelPart rightFang1;
	private final ModelPart leftFang2;
	private final ModelPart rightFang2;
	private final ModelPart leftHeadFin;
	private final ModelPart rightHeadFin;
	private final ModelPart body;
	private final ModelPart bodyPart1;
	private final ModelPart bodyPart2;
	private final ModelPart bodyPart3;
	private final ModelPart leftRib;
	private final ModelPart rightRib;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart leftTailFin;
	private final ModelPart rightTailFin;
	private final ModelPart leftWingBase;
	private final ModelPart leftWing1;
	private final ModelPart leftWing2;
	private final ModelPart leftWing3;
	private final ModelPart rightWingBase;
	private final ModelPart rightWing1;
	private final ModelPart rightWing2;
	private final ModelPart rightWing3;
	private final ModelPart smallLeftWingBase;
	private final ModelPart smallLeftWing1;
	private final ModelPart smallLeftWing2;
	private final ModelPart smallRightWingBase;
	private final ModelPart smallRightWing1;
	private final ModelPart smallRightWing2;
	private final ModelPart leftBodyFin;
	private final ModelPart rightBodyFin;
	private final ModelPart smallLeftBodyFin;
	private final ModelPart smallRightBodyFin;

	public DyssomniaModel(ModelPart modelPart)
	{
		this.root = modelPart;
		this.head = modelPart.getChild("head");
		this.headPart1 = this.head.getChild("head_part_1");
		this.headPart2Left1 = this.headPart1.getChild("head_part_2_left_1");
		this.headPart2Right1 = this.headPart1.getChild("head_part_2_right_1");
		this.headPart2Left2 = this.headPart2Left1.getChild("head_part_2_left_2");
		this.headPart2Right2 = this.headPart2Right1.getChild("head_part_2_right_2");
		this.leftFang1 = this.headPart2Left1.getChild("left_fang_1");
		this.rightFang1 = this.headPart2Right1.getChild("right_fang_1");
		this.leftFang2 = this.headPart2Left2.getChild("left_fang_2");
		this.rightFang2 = this.headPart2Right2.getChild("right_fang_2");
		this.leftHeadFin = this.headPart1.getChild("left_head_fin");
		this.rightHeadFin = this.headPart1.getChild("right_head_fin");
		this.body = modelPart.getChild("body");
		this.bodyPart1 = this.body.getChild("body_part_1");
		this.bodyPart2 = this.bodyPart1.getChild("body_part_2");
		this.bodyPart3 = this.body.getChild("body_part_3");
		this.leftRib = this.body.getChild("left_rib");
		this.rightRib = this.body.getChild("right_rib");
		this.tail1 = this.bodyPart2.getChild("tail_1");
		this.tail2 = this.tail1.getChild("tail_2");
		this.tail3 = this.tail2.getChild("tail_3");
		this.leftTailFin = this.tail2.getChild("left_tail_fin");
		this.rightTailFin = this.tail2.getChild("right_tail_fin");
		this.leftWingBase = this.body.getChild("left_wing_base");
		this.leftWing1 = this.leftWingBase.getChild("left_wing_1");
		this.leftWing2 = this.leftWing1.getChild("left_wing_2");
		this.leftWing3 = this.leftWing2.getChild("left_wing_3");
		this.rightWingBase = this.body.getChild("right_wing_base");
		this.rightWing1 = this.rightWingBase.getChild("right_wing_1");
		this.rightWing2 = this.rightWing1.getChild("right_wing_2");
		this.rightWing3 = this.rightWing2.getChild("right_wing_3");
		this.smallLeftWingBase = this.bodyPart2.getChild("small_left_wing_base");
		this.smallLeftWing1 = this.smallLeftWingBase.getChild("small_left_wing_1");
		this.smallLeftWing2 = this.smallLeftWing1.getChild("small_left_wing_2");
		this.smallRightWingBase = this.bodyPart2.getChild("small_right_wing_base");
		this.smallRightWing1 = this.smallRightWingBase.getChild("small_right_wing_1");
		this.smallRightWing2 = this.smallRightWing1.getChild("small_right_wing_2");
		this.leftBodyFin = this.body.getChild("left_body_fin");
		this.rightBodyFin = this.body.getChild("right_body_fin");
		this.smallLeftBodyFin = this.bodyPart2.getChild("small_left_body_fin");
		this.smallRightBodyFin = this.bodyPart2.getChild("small_right_body_fin");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition md = new MeshDefinition();
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 0, -3.5F, -2.0F, -2.0F, 7.0F, 2.0F, 2.0F, 0.0F, -1.0F, -9.0F);
		PartDefinition hp1pd = ModClientUtils.addC(headpd, "head_part_1", 0, 8, -3.5F, -2.0F, -4.0F, 7.0F, 3.0F, 4.0F, 0.0F, 0.0F, -2.0F);
		PartDefinition hp2l1pd = ModClientUtils.addC(hp1pd, "head_part_2_left_1", 24, 0, -2.0F, -1.0F, -1.0F, 4.0F, 3.0F, 1.0F, 2.5F, -0.5F, -2.5F, 0.0F, -((float)Math.PI / 3.0F), 0.0F);
		PartDefinition hp2r1pd = ModClientUtils.addC(hp1pd, "head_part_2_right_1", 24, 0, -2.0F, -1.0F, -1.0F, 4.0F, 3.0F, 1.0F, -2.5F, -0.5F, -2.5F, 0.0F, (float)Math.PI / 3.0F, 0.0F, true);
		PartDefinition hp2l2pd = ModClientUtils.addC(hp2l1pd, "head_part_2_left_2", 40, 0, -4.0F, -1.0F, -1.0F, 7.0F, 3.0F, 1.0F, 0.0F, 0.0F, -1.0F);
		PartDefinition hp2r2pd = ModClientUtils.addC(hp2r1pd, "head_part_2_right_2", 40, 0, -3.0F, -1.0F, -1.0F, 7.0F, 3.0F, 1.0F, 0.0F, 0.0F, -1.0F, true);
		ModClientUtils.addC(hp2l1pd, "left_fang_1", 24, 8, -2.0F, -1.0F, 0.0F, 2.0F, 3.0F, 1.0F, -1.5F, 0.0F, -0.5F, -0.5F);
		ModClientUtils.addC(hp2r1pd, "right_fang_1", 24, 8, 0.0F, -1.0F, 0.0F, 2.0F, 3.0F, 1.0F, 1.5F, 0.0F, -0.5F, true, -0.5F);
		ModClientUtils.addC(hp2l2pd, "left_fang_2", 24, 12, -2.0F, -1.0F, 0.0F, 2.0F, 3.0F, 1.0F, -4.0F, 0.0F, -1.0F);
		ModClientUtils.addC(hp2r2pd, "right_fang_2", 24, 12, 0.0F, -1.0F, 0.0F, 2.0F, 3.0F, 1.0F, 4.0F, 0.0F, -1.0F, true);
		ModClientUtils.addC(hp1pd, "left_head_fin", 32, 8, 0.0F, -2.0F, -4.0F, 1.0F, 2.0F, 4.0F, 1.5F, -2.0F, 0.5F);
		ModClientUtils.addC(hp1pd, "right_head_fin", 32, 8, -1.0F, -2.0F, -4.0F, 1.0F, 2.0F, 4.0F, -1.5F, -2.0F, 0.5F, true);

		PartDefinition bodypd = ModClientUtils.addC(pd, "body", 0, 16, -5.5F, -3.0F, -9.0F, 11.0F, 4.0F, 11.0F, 0.0F, 0.0F, 0.0F);
		PartDefinition bp1pd = ModClientUtils.addC(bodypd, "body_part_1", 0, 32, -4.5F, -3.0F, 0.0F, 9.0F, 4.0F, 1.0F, 0.0F, 0.0F, 2.0F);
		PartDefinition bp2pd = ModClientUtils.addC(bp1pd, "body_part_2", 0, 40, -3.5F, -1.5F, 0.0F, 7.0F, 3.0F, 6.0F, 0.0F, -1.0F, 1.0F);
		ModClientUtils.addC(bodypd, "body_part_3", 32, 32, -4.5F, -2.0F, -1.0F, 9.0F, 3.0F, 1.0F, 0.0F, -0.5F, -9.0F);
		ModClientUtils.addC(bodypd, "left_rib", 64, 0, -1.0F, -2.0F, -3.0F, 3.0F, 3.0F, 7.0F, 2.0F, 2.25F, -2.0F);
		ModClientUtils.addC(bodypd, "right_rib", 64, 0, -2.0F, -2.0F, -3.0F, 3.0F, 3.0F, 7.0F, -2.0F, 2.25F, -2.0F, true);
		PartDefinition t1pd = ModClientUtils.addC(bp2pd, "tail_1", 0, 52, -2.5F, 0.0F, 0.0F, 5.0F, 2.0F, 6.0F, 0.0F, -1.0F, 6.0F);
		PartDefinition t2pd = ModClientUtils.addC(t1pd, "tail_2", 0, 60, -1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 4.0F, 0.0F, 0.5F, 6.0F);
		ModClientUtils.addC(t2pd, "tail_3", 0, 68, -0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 8.0F, 0.0F, 0.0F, 4.0F);
		ModClientUtils.addC(t2pd, "left_tail_fin", 0, 80, 0.0F, 0.0F, 0.0F, 7.0F, 1.0F, 15.0F, 1.5F, 0.0F, 1.0F);
		ModClientUtils.addC(t2pd, "right_tail_fin", 0, 80, -7.0F, 0.0F, 0.0F, 7.0F, 1.0F, 15.0F, -1.5F, 0.0F, 1.0F, true);
		PartDefinition lwbpd = ModClientUtils.addC(bodypd, "left_wing_base", 64, 16, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 10.0F, 4.0F, -2.75F, -8.0F);
		PartDefinition lw1pd = ModClientUtils.addC(lwbpd, "left_wing_1", 64, 32, 0.0F, 0.0F, 0.0F, 4.0F, 3.0F, 10.0F, 2.0F, 0.0F, -0.5F, 0.0F, 0.0F, 0.1F);
		PartDefinition lw2pd = ModClientUtils.addC(lw1pd, "left_wing_2", 64, 48, 0.0F, 0.0F, 0.0F, 6.0F, 2.0F, 9.0F, 4.0F, 0.0F, -0.5F, 0.0F, 0.0F, 0.1F);
		ModClientUtils.addC(lw2pd, "left_wing_3", 64, 64, 0.0F, 0.0F, 0.0F, 13.0F, 1.0F, 9.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
		PartDefinition rwbpd = ModClientUtils.addC(bodypd, "right_wing_base", 64, 16, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 10.0F, -4.0F, -2.75F, -8.0F, true);
		PartDefinition rw1pd = ModClientUtils.addC(rwbpd, "right_wing_1", 64, 32, -4.0F, 0.0F, 0.0F, 4.0F, 3.0F, 10.0F, -2.0F, 0.0F, -0.5F, 0.0F, 0.0F, -0.1F, true);
		PartDefinition rw2pd = ModClientUtils.addC(rw1pd, "right_wing_2", 64, 48, -6.0F, 0.0F, 0.0F, 6.0F, 2.0F, 9.0F, -4.0F, 0.0F, -0.5F, 0.0F, 0.0F, -0.1F, true);
		ModClientUtils.addC(rw2pd, "right_wing_3", 64, 64, -13.0F, 0.0F, 0.0F, 13.0F, 1.0F, 9.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1F, true);
		PartDefinition slwbpd = ModClientUtils.addC(bp2pd, "small_left_wing_base", 32, 40, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 7.0F, 4.0F, -0.5F, 0.0F);
		PartDefinition slw1pd = ModClientUtils.addC(slwbpd, "small_left_wing_1", 32, 52, 0.0F, 0.0F, 0.0F, 4.0F, 1.0F, 8.0F, 2.0F, 0.0F, -0.5F, 0.0F, 0.0F, 0.1F);
		ModClientUtils.addC(slw1pd, "small_left_wing_2", 32, 64, 0.0F, 0.0F, 0.0F, 9.0F, 1.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F);
		PartDefinition srwbpd = ModClientUtils.addC(bp2pd, "small_right_wing_base", 32, 40, -2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 7.0F, -4.0F, -0.5F, 0.0F, true);
		PartDefinition srw1pd = ModClientUtils.addC(srwbpd, "small_right_wing_1", 32, 52, -4.0F, 0.0F, 0.0F, 4.0F, 1.0F, 8.0F, -2.0F, 0.0F, -0.5F, 0.0F, 0.0F, -0.1F, true);
		ModClientUtils.addC(srw1pd, "small_right_wing_2", 32, 64, -9.0F, 0.0F, 0.0F, 9.0F, 1.0F, 7.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1F, true);
		ModClientUtils.addC(bodypd, "left_body_fin", 96, 0, 0.0F, -5.0F, -10.0F, 1.0F, 5.0F, 12.0F, 1.5F, -2.25F, 2.0F);
		ModClientUtils.addC(bodypd, "right_body_fin", 96, 0, -1.0F, -5.0F, -10.0F, 1.0F, 5.0F, 12.0F, -1.5F, -2.25F, 2.0F, true);
		ModClientUtils.addC(bp2pd, "small_left_body_fin", 96, 24, 0.0F, -3.0F, -6.0F, 1.0F, 3.0F, 6.0F, 1.5F, -0.5F, 7.0F);
		ModClientUtils.addC(bp2pd, "small_right_body_fin", 96, 24, -1.0F, -3.0F, -6.0F, 1.0F, 3.0F, 6.0F, -1.5F, -0.5F, 7.0F, true);
		return LayerDefinition.create(md, 128, 128);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);

		this.headPart2Left1.yRot = -((float)Math.PI / 3.0F);
		this.headPart2Right1.yRot = (float)Math.PI / 3.0F;
		float f = entityIn.getAttackAnimationScale(partialTick);

		if (f > 0.0F)
		{
			this.headPart2Left1.yRot -= f * ((float)Math.PI / 180.0F) * 57.0F;
			this.headPart2Right1.yRot += f * ((float)Math.PI / 180.0F) * 57.0F;
		}
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.xRot = 0.2F;
		this.head.yRot = 0.0F;
		this.head.zRot = 0.0F;
		this.body.xRot = -0.1F;
		this.bodyPart2.xRot = 0.15F;
		this.bodyPart3.xRot = 0.0F;

		this.leftRib.zRot = (float)Math.PI / 5.0F;
		this.rightRib.zRot = -((float)Math.PI / 5.0F);
		this.leftRib.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.033F;
		this.rightRib.zRot += Mth.cos(ageInTicks * 0.09F) * 0.033F;
		this.leftRib.yRot = (float)Math.PI / 21.0F;
		this.rightRib.yRot = -((float)Math.PI / 21.0F);

		float f = ((float)(entityIn.getId() * 3) + ageInTicks) * 0.13F;
		float f1 = 9.0F;
		float f2 = (float)Math.PI / 180.0F;

		this.leftFang1.yRot = (8.0F + Mth.cos(f * 1.5F + ((float)Math.PI / 8.0F)) * 8.0F) * f2;
		this.rightFang1.yRot = -this.leftFang1.yRot;
		this.leftFang2.yRot = (9.0F + Mth.cos(f * 1.5F + ((float)Math.PI / 4.0F)) * 9.0F) * f2;
		this.rightFang2.yRot = -this.leftFang2.yRot;

		this.leftWingBase.yRot = (float)Math.PI / 20.0F;
		this.rightWingBase.yRot = -((float)Math.PI / 20.0F);
		this.leftWing1.zRot = Mth.cos(f) * f1 * f2;
		this.leftWing2.zRot = Mth.cos(f) * f1 * f2;
		this.leftWing3.zRot = Mth.cos(f) * f1 * f2;
		this.rightWing1.zRot = -this.leftWing1.zRot;
		this.rightWing2.zRot = -this.leftWing2.zRot;
		this.rightWing3.zRot = -this.leftWing3.zRot;

		this.smallLeftWingBase.yRot = -((float)Math.PI / 15.0F);
		this.smallRightWingBase.yRot = (float)Math.PI / 15.0F;
		this.smallLeftWing1.zRot = Mth.cos(f + ((float)Math.PI / 5.0F)) * f1 * f2;
		this.smallLeftWing2.zRot = Mth.cos(f + ((float)Math.PI / 5.0F)) * f1 * f2;
		this.smallRightWing1.zRot = -this.smallLeftWing1.zRot;
		this.smallRightWing2.zRot = -this.smallLeftWing2.zRot;

		this.leftHeadFin.zRot = (float)Math.PI / 4.0F;
		this.leftHeadFin.zRot += Mth.cos(f * 2.0F + ((float)Math.PI * 11.0F / 24.0F)) * f1 * 0.5F * f2;
		this.rightHeadFin.zRot = -this.leftBodyFin.zRot;

		this.leftBodyFin.yRot = (float)Math.PI / 30.0F;
		this.rightBodyFin.yRot = -((float)Math.PI / 30.0F);
		this.leftBodyFin.zRot = (float)Math.PI / 4.0F;
		this.leftBodyFin.zRot += Mth.cos(f * 2.0F + ((float)Math.PI / 2.0F)) * f1 * 0.5F * f2;
		this.rightBodyFin.zRot = -this.leftBodyFin.zRot;

		this.smallLeftBodyFin.zRot = (float)Math.PI / 4.0F;
		this.smallLeftBodyFin.zRot += Mth.cos(f * 2.0F + ((float)Math.PI * 13.0F / 24.0F)) * f1 * 0.5F * f2;
		this.smallRightBodyFin.zRot = -this.smallLeftBodyFin.zRot;

		this.tail1.xRot = -(2.5F + Mth.cos(f * 2.0F) * 5.0F) * f2 * 1.5F;
		this.tail2.xRot = -(2.5F + Mth.cos(f * 2.0F) * 5.0F) * f2 * 1.75F;
		this.tail3.xRot = -(2.5F + Mth.cos(f * 2.0F) * 5.0F) * f2 * 1.5F;
		this.leftTailFin.zRot = Mth.cos(f * 2.0F + ((float)Math.PI / 2.0F)) * f1 * 1.2F * f2;
		this.rightTailFin.zRot = -this.leftTailFin.zRot;
	}
}