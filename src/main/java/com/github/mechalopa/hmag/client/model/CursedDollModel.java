package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.AnimationUtils;
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
public class CursedDollModel<T extends Mob> extends AbstractAdvancedGirlModel<T>
{
//	private ModelPart skirt1;
//	private ModelPart skirt2;
//	private ModelPart skirt3;
//	private ModelPart skirt4;
//	private ModelPart skirt5;
	private ModelPart hairPart1;
	private ModelPart hairPart2;
	private ModelPart ribbon1;
	private ModelPart ribbonRightPart;
	private ModelPart ribbonLeftPart;
	private ModelPart ribbon2;

	public CursedDollModel(ModelPart modelPart)
	{
		super(modelPart);
//		this.skirt1 = this.body.getChild("skirt_1");
//		this.skirt2 = this.skirt1.getChild("skirt_2");
//		this.skirt3 = this.skirt2.getChild("skirt_3");
//		this.skirt4 = this.skirt3.getChild("skirt_4");
//		this.skirt5 = this.skirt4.getChild("skirt_5");
		this.hairPart1 = this.head.getChild("hair_part_1");
		this.hairPart2 = this.hairPart1.getChild("hair_part_2");
		this.ribbon1 = this.head.getChild("ribbon_1");
		this.ribbonRightPart = this.ribbon1.getChild("ribbon_right_part");
		this.ribbonLeftPart = this.ribbon1.getChild("ribbon_left_part");
		this.ribbon2 = this.ribbon1.getChild("ribbon_2");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F, 6);
		PartDefinition pd = md.getRoot();
		PartDefinition bodypd = pd.getChild("body");
		PartDefinition s1pd = ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 40, -4.0F, 0.0F, -2.5F, 8.0F, 1.0F, 5.0F, 0.0F, 11.0F, 0.0F);
		PartDefinition s2pd = ModClientUtils.addC(s1pd, cd, "skirt_2", 0, 48, -5.0F, 0.0F, -3.5F, 10.0F, 1.0F, 7.0F, 0.0F, 1.0F, 0.0F);
		PartDefinition s3pd = ModClientUtils.addC(s2pd, cd, "skirt_3", 0, 56, -6.0F, 0.0F, -4.5F, 12.0F, 1.0F, 9.0F, 0.0F, 1.0F, 0.0F);
		PartDefinition s4pd = ModClientUtils.addC(s3pd, cd, "skirt_4", 0, 68, -7.0F, 0.0F, -5.5F, 14.0F, 1.0F, 11.0F, 0.0F, 1.0F, 0.0F);
		ModClientUtils.addC(s4pd, cd, "skirt_5", 0, 80, -8.0F, 0.0F, -6.5F, 16.0F, 2.0F, 13.0F, 0.0F, 1.0F, 0.0F);
		PartDefinition headpd = pd.getChild("head");
		PartDefinition h1pd = ModClientUtils.addC(headpd, cd, "hair_part_1", 40, 48, -4.0F, 0.0F, -1.0F, 8.0F, 4.0F, 1.0F, 0.0F, 0.0F, 4.0F);
		ModClientUtils.addC(h1pd, cd, "hair_part_2", 42, 56, -4.0F, 0.0F, -1.0F, 8.0F, 8.0F, 1.0F, 0.0F, 4.0F, 0.0F);
		PartDefinition ribbonpd = ModClientUtils.addC(headpd, cd, "ribbon_1", 16, 32, -1.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, -7.0F, 4.25F);
		ModClientUtils.addC(ribbonpd, cd, "ribbon_right_part", 0, 96, 0.0F, -3.5F, -1.0F, 6.0F, 7.0F, 1.0F, 1.0F, 0.0F, 0.25F);
		ModClientUtils.addC(ribbonpd, cd, "ribbon_left_part", 16, 96, -6.0F, -3.5F, -1.0F, 6.0F, 7.0F, 1.0F, -1.0F, 0.0F, 0.25F);
		ModClientUtils.addC(ribbonpd, cd, "ribbon_2", 0, 104, -4.5F, 0.0F, -1.0F, 9.0F, 5.0F, 1.0F, 0.0F, 1.5F, 0.25F);
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

		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entityIn), this.attackTime, ageInTicks);

		float f = Mth.sin(this.attackTime * (float)Math.PI);
		float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);

//		this.skirt5.xRot = 0.0F;

		this.rightLeg.xRot = (float)Math.PI / 10.0F;
		this.leftLeg.xRot = (float)Math.PI / 10.0F;
		this.rightLeg.xRot -= f * 1.2F - f1 * 0.4F;
		this.leftLeg.xRot -= f * 1.2F - f1 * 0.4F;
		this.rightLeg.xRot += Mth.sin(ageInTicks * 0.075F) * 0.06F;
		this.leftLeg.xRot -= Mth.sin(ageInTicks * 0.075F) * 0.06F;
		this.rightLeg.zRot = (float)Math.PI / 18.0F;
		this.leftLeg.zRot = -((float)Math.PI / 18.0F);

		this.hairPart1.xRot = (float)Math.PI / 24.0F;
		this.hairPart1.xRot += Mth.sin(ageInTicks * 0.075F) * 0.06F;
		this.hairPart2.xRot = (float)Math.PI / 24.0F;
		this.hairPart2.xRot += Mth.sin(ageInTicks * 0.075F + (float)Math.PI / 4.0F) * 0.06F;

		float f2 = Mth.sin(ageInTicks * 0.075F + (float)Math.PI / 2.0F);
		this.ribbon1.xRot = (float)Math.PI / 18.0F;
		this.ribbonRightPart.yRot = -((float)Math.PI / 21.0F);
		this.ribbonLeftPart.yRot = (float)Math.PI / 21.0F;
		this.ribbonRightPart.yRot -= f2 * 0.05F;
		this.ribbonLeftPart.yRot += f2 * 0.05F;
		this.ribbon2.xRot = (float)Math.PI / 28.0F;
		this.ribbon2.xRot += f2 * 0.05F;
	}

	public boolean isAggressive(T entityIn)
	{
		return entityIn.isAggressive();
	}
}