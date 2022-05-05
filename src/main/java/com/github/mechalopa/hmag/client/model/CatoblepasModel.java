package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CatoblepasModel<T extends Entity> extends HierarchicalModel<T>
{
	private final ModelPart root;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightHorn1;
	private final ModelPart leftHorn1;
	private final ModelPart rightHorn2;
	private final ModelPart leftHorn2;

	public CatoblepasModel(ModelPart modelPart)
	{
		this.root = modelPart;
		this.neck = modelPart.getChild("neck");
		this.head = this.neck.getChild("head");
		this.body = modelPart.getChild("body");
		this.rightHindLeg = modelPart.getChild("right_hind_leg");
		this.leftHindLeg = modelPart.getChild("left_hind_leg");
		this.rightFrontLeg = modelPart.getChild("right_front_leg");
		this.leftFrontLeg = modelPart.getChild("left_front_leg");
		this.rightHorn1 = this.head.getChild("right_horn_1");
		this.leftHorn1 = this.head.getChild("left_horn_1");
		this.rightHorn2 = this.rightHorn1.getChild("right_horn_2");
		this.leftHorn2 = this.rightHorn1.getChild("left_horn_2");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition md = new MeshDefinition();
		PartDefinition pd = md.getRoot();
		PartDefinition neckpd = ModClientUtils.addC(pd, "neck", 24, 32, -3.0F, -2.5F, -9.0F, 6.0F, 5.0F, 9.0F, 0.0F, 6.0F, -7.0F);
		PartDefinition headpd = ModClientUtils.addC(neckpd, "head", 0, 0, -4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F, 0.0F, 1.0F, -8.0F);
		ModClientUtils.addC(pd, "body", 18, 4, -6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, 0.0F, 5.0F, 2.0F);
		ModClientUtils.addC(pd, "right_hind_leg", 0, 16, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, -4.0F, 12.0F, 7.0F);
		ModClientUtils.addC(pd, "left_hind_leg", 0, 16, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 4.0F, 12.0F, 7.0F, true);
		ModClientUtils.addC(pd, "right_front_leg", 0, 16, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, -4.0F, 12.0F, -6.0F);
		ModClientUtils.addC(pd, "left_front_leg", 0, 16, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 4.0F, 12.0F, -6.0F, true);
		PartDefinition rh1pd = ModClientUtils.addC(headpd, "right_horn_1", 0, 32, -1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, -3.25F, -3.5F, -4.25F);
		PartDefinition lh1pd = ModClientUtils.addC(headpd, "left_horn_1", 0, 32, -1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, 3.25F, -3.5F, -4.25F, true);
		ModClientUtils.addC(rh1pd, "right_horn_2", 16, 32, -1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, -5.5F, 0.0F);
		ModClientUtils.addC(lh1pd, "left_horn_2", 16, 32, -1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, -5.5F, 0.0F, true);
		return LayerDefinition.create(md, 64, 64);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.neck.xRot = (float)Math.PI / 9.0F;
		this.neck.xRot += Mth.cos(limbSwing * 0.6662F + (float)Math.PI / 2.0F) * 0.25F * limbSwingAmount;
		this.head.xRot = -((float)Math.PI / 9.0F);
		this.head.xRot -= Mth.cos(limbSwing * 0.6662F + (float)Math.PI / 2.0F) * 0.2F * limbSwingAmount;
		this.head.xRot += headPitch * ((float)Math.PI / 180.0F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180.0F);

		this.body.xRot = (float)Math.PI / 2.0F;
		this.rightHindLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftHindLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.rightHorn1.xRot = -((float)Math.PI / 15.0F);
		this.leftHorn1.xRot = -((float)Math.PI / 15.0F);
		this.rightHorn1.zRot = -((float)Math.PI * 5.0F / 12.0F);
		this.leftHorn1.zRot = (float)Math.PI * 5.0F / 12.0F;
		this.rightHorn2.zRot = (float)Math.PI / 11.0F;
		this.leftHorn2.zRot = -((float)Math.PI / 11.0F);
	}
}