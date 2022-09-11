package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.CreeperGirlEntity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlModel<T extends CreeperGirlEntity> extends AbstractAdvancedGirlModel<T>
{
//	private ModelPart skirt1;
//	private ModelPart skirt2;
//	private ModelPart hatPart;

	public CreeperGirlModel(ModelPart modelPart)
	{
		super(modelPart);
//		this.skirt1 = this.body.getChild("skirt_1");
//		this.skirt2 = this.body.getChild("skirt_2");
//		this.hatPart = this.hat.getChild("hat_part");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition bodypd = pd.getChild("body");
		ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_2", 0, 44, -4.0F, 0.0F, -2.5F, 8.0F, 1.0F, 5.0F, 0.0F, 12.0F, 0.0F);
		PartDefinition hatpd = pd.getChild("hat");
		ModClientUtils.addC(hatpd, cd, "hat_part", 32, 48, -4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, 3.0F, 0.0F, 0.5F);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		doAnim(entityIn, ageInTicks, this.attackTime, this);

//		this.skirt1.xRot = 0.0F;
//		this.skirt2.xRot = 0.0F;
//		this.hatPart.xRot = 0.0F;
	}

	public static void doAnim(CreeperGirlEntity entityIn, float ageInTicks, float attackTime, AbstractGirlModel<?> model)
	{
		float f = Mth.sin(attackTime * (float)Math.PI);
		float f1 = Mth.sin((1.0F - (1.0F - attackTime) * (1.0F - attackTime)) * (float)Math.PI);
		model.rightArm.zRot = 0.0F;
		model.leftArm.zRot = 0.0F;
		model.rightArm.yRot = -(0.1F - f * 0.6F);
		model.leftArm.yRot = 0.1F - f * 0.6F;
		model.rightArm.xRot = 0.0F;
		model.leftArm.xRot = 0.0F;
		model.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
		model.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
		model.rightArm.zRot += Mth.cos(ageInTicks * 0.12F) * 0.096F + 0.12F;
		model.leftArm.zRot -= Mth.cos(ageInTicks * 0.12F) * 0.096F + 0.12F;
		model.rightArm.xRot += Mth.sin(ageInTicks * 0.105F + (float)Math.PI * 0.5F) * 0.072F;
		model.leftArm.xRot -= Mth.sin(ageInTicks * 0.105F) * 0.072F;
	}
}