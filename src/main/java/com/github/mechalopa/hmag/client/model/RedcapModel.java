package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

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
public class RedcapModel<T extends Mob> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart cap1;
//	private ModelPart cap2;
//	private ModelPart cap3;
//	private ModelPart cap4;
	private ModelPart rightHair1;
//	private ModelPart rightHair2;
//	private ModelPart rightHair3;
//	private ModelPart rightHair4;
	private ModelPart leftHair1;
//	private ModelPart leftHair2;
//	private ModelPart leftHair3;
//	private ModelPart leftHair4;
	private ModelPart skirt1;
	private ModelPart skirt2;

	public RedcapModel(ModelPart modelPart)
	{
		super(modelPart);
		this.cap1 = this.head.getChild("cap_1");
		this.rightHair1 = this.head.getChild("right_hair_1");
		this.leftHair1 = this.head.getChild("left_hair_1");
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.body.getChild("skirt_2");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = pd.getChild("head");
		PartDefinition cap1pd = ModClientUtils.addC(headpd, cd, "cap_1", 0, 64, -5.0F, -1.0F, -5.0F, 10.0F, 2.0F, 10.0F, 0.0F, -7.0F, 0.0F);
		PartDefinition cap2pd = ModClientUtils.addC(cap1pd, cd, "cap_2", 0, 48, -4.0F, 0.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, -2.0F, 0.5F);
		ModClientUtils.addC(cap2pd, cd, "cap_3", 0, 40, -3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, -1.0F, 0.5F);
		ModClientUtils.addC(cap1pd, cd, "cap_4", 0, 80, -4.0F, 0.0F, -3.0F, 8.0F, 1.0F, 3.0F, 0.0F, 0.0F, -5.0F);
		PartDefinition rh1pd = ModClientUtils.addC(headpd, cd, "right_hair_1", 32, 80, -1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, -3.5F, -1.0F, 3.0F);
		PartDefinition lh1pd = ModClientUtils.addC(headpd, cd, "left_hair_1", 32, 80, -1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 3.5F, -1.0F, 3.0F, true);
		PartDefinition rh2pd = ModClientUtils.addC(rh1pd, cd, "right_hair_2", 40, 80, -1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, 2.0F, 0.0F);
		PartDefinition lh2pd = ModClientUtils.addC(lh1pd, cd, "left_hair_2", 40, 80, -1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, 2.0F, 0.0F, true);
		PartDefinition rh3pd = ModClientUtils.addC(rh2pd, cd, "right_hair_3", 32, 88, -2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, 2.0F, 0.0F);
		PartDefinition lh3pd = ModClientUtils.addC(lh2pd, cd, "left_hair_3", 32, 88, -2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, 2.0F, 0.0F, true);
		ModClientUtils.addC(rh3pd, cd, "right_hair_4", 32, 96, -2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, 1.0F, 0.0F);
		ModClientUtils.addC(lh3pd, cd, "left_hair_4", 32, 96, -2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, 1.0F, 0.0F, true);
		PartDefinition bodypd = pd.getChild("body");
		ModClientUtils.addC(bodypd, cd, "skirt_1", 32, 48, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_2", 32, 56, -4.0F, 0.0F, -2.5F, 8.0F, 6.0F, 5.0F, 0.0F, 12.0F, 0.0F);
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

		this.cap1.xRot = -((float)Math.PI * 0.016F);

		this.rightHair1.xRot = (float)Math.PI / 7.0F;
		this.leftHair1.xRot = (float)Math.PI / 7.0F;
		this.rightHair1.zRot = (float)Math.PI / 8.0F;
		this.leftHair1.zRot = -((float)Math.PI / 8.0F);
		this.rightHair1.zRot -= Mth.sin(ageInTicks * 0.09F) * 0.033F;
		this.leftHair1.zRot += Mth.sin(ageInTicks * 0.09F) * 0.033F;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
		}

		this.skirt1.xRot = 0.0F;
	}
}