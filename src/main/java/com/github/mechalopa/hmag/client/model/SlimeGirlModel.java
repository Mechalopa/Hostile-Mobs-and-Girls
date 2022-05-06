package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.entity.SlimeGirlEntity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlimeGirlModel<T extends SlimeGirlEntity> extends AbstractAdvancedGirlModel<T>
{
	protected boolean isOuterLayer = false;
	private ModelPart rightHair1;
	private ModelPart leftHair1;
	private ModelPart rightHair2;
	private ModelPart leftHair2;
	private ModelPart rightHair3;
	private ModelPart leftHair3;
	private ModelPart hairPart;
	private ModelPart skirt1;
	private ModelPart skirt2;
	private ModelPart rightHairCore;
	private ModelPart leftHairCore;
	private ModelPart bodyCore;

	public SlimeGirlModel(ModelPart modelPart)
	{
		super(modelPart);
		this.rightHair1 = this.head.getChild("right_hair_1");
		this.leftHair1 = this.head.getChild("left_hair_1");
		this.rightHair2 = this.rightHair1.getChild("right_hair_2");
		this.leftHair2 = this.leftHair1.getChild("left_hair_2");
		this.rightHair3 = this.rightHair2.getChild("right_hair_3");
		this.leftHair3 = this.leftHair2.getChild("left_hair_3");
		this.hairPart = this.head.getChild("hair_part");
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.body.getChild("skirt_2");
		this.rightHairCore = this.rightHair1.getChild("right_hair_core");
		this.leftHairCore = this.leftHair1.getChild("left_hair_core");
		this.bodyCore = this.body.getChild("body_core");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F, 6);
		PartDefinition pd = md.getRoot();
		ModClientUtils.addC(pd, cd, "right_arm", 40, 16, 0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, -5.0F, 2.0F, 0.0F);
		ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 5.0F, 2.0F, 0.0F, true);
		PartDefinition headpd = pd.getChild("head");
		PartDefinition rh1pd = ModClientUtils.addC(headpd, cd, "right_hair_1", 32, 48, -2.0F, -1.5F, -1.5F, 3.0F, 12.0F, 3.0F, -4.0F, -8.0F, 2.75F);
		PartDefinition lh1pd = ModClientUtils.addC(headpd, cd, "left_hair_1", 32, 48, -1.0F, -1.5F, -1.5F, 3.0F, 12.0F, 3.0F, 4.0F, -8.0F, 2.75F, true);
		PartDefinition rh2pd = ModClientUtils.addC(rh1pd, cd, "right_hair_2", 48, 48, -1.0F, -0.5F, -1.0F, 2.0F, 8.0F, 2.0F, -0.5F, 11.0F, 0.0F, 0.25F);
		PartDefinition lh2pd = ModClientUtils.addC(lh1pd, cd, "left_hair_2", 48, 48, -1.0F, -0.5F, -1.0F, 2.0F, 8.0F, 2.0F, 0.5F, 11.0F, 0.0F, true, 0.25F);
		ModClientUtils.addC(rh2pd, cd, "right_hair_3", 56, 48, -1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 7.5F, 0.0F);
		ModClientUtils.addC(lh2pd, cd, "left_hair_3", 56, 48, -1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 7.5F, 0.0F, true);
		ModClientUtils.addC(headpd, cd, "hair_part", 0, 56, -4.0F, 0.0F, -1.0F, 8.0F, 1.0F, 1.0F, 0.0F, 0.0F, 4.0F);
		PartDefinition bodypd = pd.getChild("body");
		ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_2", 0, 44, -4.0F, 0.0F, -2.5F, 8.0F, 5.0F, 5.0F, 0.0F, 12.0F, 0.0F);
		ModClientUtils.addC(rh1pd, cd, "right_hair_core", 32, 64, -2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, -0.5F, 1.0F, 0.0F, -1.0F);
		ModClientUtils.addC(lh1pd, cd, "left_hair_core", 32, 64, -2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.5F, 1.0F, 0.0F, true, -1.0F);
		ModClientUtils.addC(bodypd, cd, "body_core", 0, 64, -3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, 4.0F, -0.5F, -1.75F);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 128);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.rightArm.zRot = (float)Math.PI / 10.0F;
		this.leftArm.zRot = -((float)Math.PI / 10.0F);
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.12F) * 0.06F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.12F) * 0.06F;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;

			float f = 0.21F;

			if (this.rightLeg.xRot > f)
			{
				this.rightLeg.xRot = f;
			}

			if (this.leftLeg.xRot > f)
			{
				this.leftLeg.xRot = f;
			}

			if (this.rightLeg.xRot < -f)
			{
				this.rightLeg.xRot = -f;
			}

			if (this.leftLeg.xRot < -f)
			{
				this.leftLeg.xRot = -f;
			}
		}

		this.skirt1.xRot = 0.0F;

		this.rightHair1.xRot = (float)Math.PI / 18.0F;
		this.leftHair1.xRot = (float)Math.PI / 18.0F;
		this.rightHair1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.036F;
		this.leftHair1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.036F;
		this.rightHair1.zRot = (float)Math.PI / 9.0F;
		this.leftHair1.zRot = -((float)Math.PI / 9.0F);
		this.rightHair1.zRot -= Mth.sin(ageInTicks * 0.09F) * 0.03F;
		this.leftHair1.zRot += Mth.sin(ageInTicks * 0.09F) * 0.03F;
		this.rightHair2.zRot = (float)Math.PI / 16.0F;
		this.leftHair2.zRot = -((float)Math.PI / 16.0F);
		this.rightHair2.zRot -= Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.03F;
		this.leftHair2.zRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.03F;
		this.rightHair3.zRot = -((float)Math.PI / 5.0F);
		this.leftHair3.zRot = (float)Math.PI / 5.0F;
		this.rightHair3.zRot -= Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.015F;
		this.leftHair3.zRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.015F;

		this.hairPart.xRot = ((float)Math.PI / 12.0F);
		this.hairPart.xRot += Mth.sin(ageInTicks * 0.03F) * 0.03F;

		if (!this.isOuterLayer)
		{
			this.rightHairCore.xRot = Mth.sin(ageInTicks * 0.042F + (float)Math.PI / 4.0F) * 0.09F;
			this.leftHairCore.xRot = Mth.sin(ageInTicks * 0.042F) * 0.09F;
			this.rightHairCore.zRot = Mth.cos(ageInTicks * 0.042F + (float)Math.PI / 4.0F) * 0.09F;
			this.leftHairCore.zRot = Mth.cos(ageInTicks * 0.042F) * 0.09F;
			this.rightHairCore.y = 1.0F + (Mth.cos(ageInTicks * 0.075F + (float)Math.PI * 2.0F / 3.0F) * 0.5F);
			this.leftHairCore.y = 1.0F + (Mth.cos(ageInTicks * 0.075F - (float)Math.PI / 3.0F) * 0.5F);
			this.bodyCore.xRot = (float)Math.PI / 4.0F;
			this.bodyCore.xRot += Mth.sin(ageInTicks * 0.042F + (float)Math.PI * 2.0F / 3.0F) * 0.12F;
			this.bodyCore.zRot = Mth.cos(ageInTicks * 0.042F + (float)Math.PI * 2.0F / 3.0F) * 0.09F;
			this.bodyCore.y = 4.0F + (Mth.cos(ageInTicks * 0.075F + (float)Math.PI / 2.0F) * 0.24F);
		}
	}

	public void setOuterLayer(boolean flag)
	{
		this.isOuterLayer = flag;
		this.rightHairCore.visible = !flag;
		this.leftHairCore.visible = !flag;
		this.bodyCore.visible = !flag;
	}
}