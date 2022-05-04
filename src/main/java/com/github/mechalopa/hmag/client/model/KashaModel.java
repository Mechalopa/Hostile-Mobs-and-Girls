package com.github.mechalopa.hmag.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KashaModel<T extends Entity> extends HierarchicalModel<T>
{
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart tail1;
	private final ModelPart tail2A;
	private final ModelPart tail2B;
	private final ModelPart leftHindLeg;
	private final ModelPart rightHindLeg;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightFrontLeg;
	public int state = 1;

	public KashaModel(ModelPart modelPart)
	{
		this.root = modelPart;
		this.head = modelPart.getChild("head");
		this.body = modelPart.getChild("body");
		this.tail1 = modelPart.getChild("tail_1");
		this.tail2A = modelPart.getChild("tail_2a");
		this.tail2B = modelPart.getChild("tail_2b");
		this.leftHindLeg = modelPart.getChild("left_hind_leg");
		this.rightHindLeg = modelPart.getChild("right_hind_leg");
		this.leftFrontLeg = modelPart.getChild("left_front_leg");
		this.rightFrontLeg = modelPart.getChild("right_front_leg");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition md = new MeshDefinition();
		PartDefinition pd = md.getRoot();
		pd.addOrReplaceChild("head", CubeListBuilder.create().addBox("main", -2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F).addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2, 0, 24).addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2, 0, 10).addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2, 6, 10), PartPose.offset(0.0F, 15.0F, -9.0F));
		pd.addOrReplaceChild("body", CubeListBuilder.create().texOffs(20, 0).addBox(-2.0F, 3.0F, -8.0F, 4.0F, 16.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 12.0F, -10.0F, ((float)Math.PI / 2.0F), 0.0F, 0.0F));
		pd.addOrReplaceChild("tail_1", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 15.0F, 8.0F, 0.9F, 0.0F, 0.0F));
		pd.addOrReplaceChild("tail_2a", CubeListBuilder.create().texOffs(4, 15).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F), PartPose.offset(0.0F, 20.0F, 14.0F));
		pd.addOrReplaceChild("tail_2b", CubeListBuilder.create().texOffs(4, 15).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F), PartPose.offset(0.0F, 20.0F, 14.0F));
		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(8, 13).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F);
		pd.addOrReplaceChild("left_hind_leg", cubelistbuilder, PartPose.offset(1.1F, 18.0F, 5.0F));
		pd.addOrReplaceChild("right_hind_leg", cubelistbuilder, PartPose.offset(-1.1F, 18.0F, 5.0F));
		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(40, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F);
		pd.addOrReplaceChild("left_front_leg", cubelistbuilder1, PartPose.offset(1.2F, 14.1F, -5.0F));
		pd.addOrReplaceChild("right_front_leg", cubelistbuilder1, PartPose.offset(-1.2F, 14.1F, -5.0F));
		return LayerDefinition.create(md, 64, 32);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.xRot = headPitch * ((float)Math.PI / 180.0F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180.0F);
		this.body.xRot = ((float)Math.PI / 2.0F);
		float f = 0.6662F;
		float f1 = 1.7278761F;
		float f2 = 0.47123894F;

		if (this.state == 2)
		{
			this.leftHindLeg.xRot = Mth.cos(limbSwing * f) * limbSwingAmount;
			this.rightHindLeg.xRot = Mth.cos(limbSwing * f + 0.3F) * limbSwingAmount;
			this.leftFrontLeg.xRot = Mth.cos(limbSwing * f + (float)Math.PI + 0.3F) * limbSwingAmount;
			this.rightFrontLeg.xRot = Mth.cos(limbSwing * f + (float)Math.PI) * limbSwingAmount;
			this.tail2A.xRot = f1 + ((float)Math.PI / 10.0F) * Mth.cos(limbSwing) * limbSwingAmount;
			this.tail2B.xRot = f1 + ((float)Math.PI / 10.0F) * Mth.cos(limbSwing + (float)Math.PI) * limbSwingAmount;
		}
		else
		{
			this.leftHindLeg.xRot = Mth.cos(limbSwing * f) * limbSwingAmount;
			this.rightHindLeg.xRot = Mth.cos(limbSwing * f + (float)Math.PI) * limbSwingAmount;
			this.leftFrontLeg.xRot = Mth.cos(limbSwing * f + (float)Math.PI) * limbSwingAmount;
			this.rightFrontLeg.xRot = Mth.cos(limbSwing * f) * limbSwingAmount;

			if (this.state == 1)
			{
				this.tail2A.xRot = f1 + ((float)Math.PI / 4.0F) * Mth.cos(limbSwing) * limbSwingAmount;
				this.tail2B.xRot = f1 + ((float)Math.PI / 4.0F) * Mth.cos(limbSwing + (float)Math.PI) * limbSwingAmount;
			}
			else
			{
				this.tail2A.xRot = f1 + f2 * Mth.cos(limbSwing) * limbSwingAmount;
				this.tail2B.xRot = f1 + f2 * Mth.cos(limbSwing + (float)Math.PI) * limbSwingAmount;
			}
		}
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		this.body.y = 12.0F;
		this.body.z = -10.0F;
		this.head.y = 15.0F;
		this.head.z = -9.0F;
		this.tail1.y = 15.0F;
		this.tail1.z = 8.0F;
		this.tail2A.y = 20.0F;
		this.tail2B.z = 14.0F;
		this.tail2A.y = 20.0F;
		this.tail2B.z = 14.0F;
		this.leftFrontLeg.y = 14.1F;
		this.leftFrontLeg.z = -5.0F;
		this.rightFrontLeg.y = 14.1F;
		this.rightFrontLeg.z = -5.0F;
		this.leftHindLeg.y = 18.0F;
		this.leftHindLeg.z = 5.0F;
		this.rightHindLeg.y = 18.0F;
		this.rightHindLeg.z = 5.0F;
		this.tail1.xRot = 0.9F;

		if (entityIn.isCrouching())
		{
			++this.body.y;
			this.head.y += 2.0F;
			++this.tail1.y;
			this.tail2A.y += -4.0F;
			this.tail2A.z += 2.0F;
			this.tail2B.y += -4.0F;
			this.tail2B.z += 2.0F;
			this.tail1.xRot = ((float)Math.PI / 2.0F);
			this.tail2A.xRot = ((float)Math.PI / 2.0F);
			this.tail2B.xRot = ((float)Math.PI / 2.0F);
			this.state = 0;
		}
		else if (entityIn.isSprinting())
		{
			this.tail2A.y = this.tail1.y;
			this.tail2A.z += 2.0F;
			this.tail2B.y = this.tail1.y;
			this.tail2B.z += 2.0F;
			this.tail1.xRot = ((float)Math.PI / 2.0F);
			this.tail2A.xRot = ((float)Math.PI / 2.0F);
			this.tail2B.xRot = ((float)Math.PI / 2.0F);
			this.state = 2;
		}
		else
		{
			this.state = 1;
		}
	}
}