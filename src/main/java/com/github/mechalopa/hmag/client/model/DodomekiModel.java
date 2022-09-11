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
public class DodomekiModel<T extends Mob> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart rightArmPart;
	private ModelPart leftArmPart;
	private ModelPart skirt1;
	private ModelPart skirt2;
	private ModelPart skirt3;
	private ModelPart hairPart1;
	private ModelPart hairPart2;
//	private ModelPart headwearPart;

	public DodomekiModel(ModelPart modelPart)
	{
		super(modelPart);
		this.rightArmPart = this.rightArm.getChild("right_arm_part");
		this.leftArmPart = this.leftArm.getChild("left_arm_part");
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.body.getChild("skirt_2");
		this.skirt3 = this.skirt2.getChild("skirt_3");
		this.hairPart1 = this.head.getChild("hair_part_1");
		this.hairPart2 = this.hairPart1.getChild("hair_part_2");
//		this.headwearPart = this.hat.getChild("hat_part");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition rapd = ModClientUtils.addC(pd, "right_arm", 40, 16, -1.0F, -2.0F, -1.5F, 3.0F, 4.0F, 3.0F, -5.0F, 2.0F, 0.0F);
		PartDefinition lapd = ModClientUtils.addC(pd, "left_arm", 40, 24, -2.0F, -2.0F, -1.5F, 3.0F, 4.0F, 3.0F, 5.0F, 2.0F, 0.0F);
		ModClientUtils.addC(rapd, "right_arm_part", 32, 48, -2.0F, 0.0F, -1.5F, 4.0F, 12.0F, 3.0F, 0.5F, 1.5F, 0.125F);
		ModClientUtils.addC(lapd, "left_arm_part", 48, 48, -2.0F, 0.0F, -1.5F, 4.0F, 12.0F, 3.0F, -0.5F, 1.5F, 0.125F);
		ModClientUtils.addC(pd, "left_leg", 0, 56, -1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 1.9F, 12.0F, 0.0F);
		PartDefinition bodypd = pd.getChild("body");
		ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		PartDefinition s2pd = ModClientUtils.addC(bodypd, cd, "skirt_2", 0, 44, -4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, 0.0F, 12.0F, 0.0F);
		ModClientUtils.addC(s2pd, cd, "skirt_3", 32, 64, -4.5F, 0.0F, -3.0F, 9.0F, 7.0F, 6.0F, 0.0F, 2.0F, 0.0F);
		PartDefinition headpd = pd.getChild("head");
		PartDefinition h1pd = ModClientUtils.addC(headpd, cd, "hair_part_1", 0, 72, -4.0F, 0.0F, -1.0F, 8.0F, 4.0F, 1.0F, 0.0F, 0.0F, 4.0F);
		ModClientUtils.addC(h1pd, cd, "hair_part_2", 0, 80, -4.0F, 0.0F, -1.0F, 8.0F, 8.0F, 1.0F, 0.0F, 4.0F, 0.0F);
		PartDefinition hatpd = pd.getChild("hat");
		ModClientUtils.addC(hatpd, cd, "hat_part", 32, 80, -4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, 3.0F, 0.0F, 0.5F);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 128);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.zRot = 0.0F;

		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.head.zRot += Mth.cos(ageInTicks * 0.054F) * 0.06F;
		this.hat.copyFrom(this.head);

		this.rightArm.zRot = (float)Math.PI / 12.0F;
		this.leftArm.zRot = -((float)Math.PI / 12.0F);
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.054F + (float)Math.PI * 4.0F / 3.0F) * 0.15F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.054F + (float)Math.PI / 3.0F) * 0.15F;
		this.rightArmPart.zRot = (float)Math.PI / 36.0F;
		this.leftArmPart.zRot = -((float)Math.PI / 36.0F);
		this.rightArmPart.zRot += Mth.cos(ageInTicks * 0.12F) * 0.021F;
		this.leftArmPart.zRot -= Mth.cos(ageInTicks * 0.12F) * 0.021F;
		this.rightArmPart.xRot = (float)Math.PI / 30.0F;
		this.leftArmPart.xRot = (float)Math.PI / 30.0F;
		this.rightArmPart.zRot += Mth.sin(ageInTicks * 0.12F) * 0.012F;
		this.leftArmPart.zRot -= Mth.sin(ageInTicks * 0.12F) * 0.012F;

		this.hairPart1.xRot = (float)Math.PI / 21.0F;
		this.hairPart1.xRot += Mth.sin(ageInTicks * 0.075F) * 0.06F;
		this.hairPart2.xRot = (float)Math.PI / 21.0F;
		this.hairPart2.xRot += Mth.sin(ageInTicks * 0.075F + (float)Math.PI / 4.0F) * 0.06F;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
			this.rightLeg.xRot *= 0.625F;
			this.leftLeg.xRot *= 0.625F;
		}

		this.skirt1.xRot = 0.0F;
		this.skirt3.xRot = 0.0F;
	}
}