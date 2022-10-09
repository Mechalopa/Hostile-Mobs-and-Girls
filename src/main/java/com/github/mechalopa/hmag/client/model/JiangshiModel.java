package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.JiangshiEntity;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JiangshiModel<T extends JiangshiEntity> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart skirt1;
	private ModelPart skirt2;
	private float animationAmount;

	public JiangshiModel(ModelPart modelPart)
	{
		super(modelPart);
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.body.getChild("skirt_2");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition bodypd = pd.getChild("body");
		ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_2", 0, 44, -4.0F, 0.0F, -2.5F, 8.0F, 6.0F, 5.0F, 0.0F, 12.0F, 0.0F);
//		PartDefinition headpd = pd.getChild("head");
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 128);
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks)
	{
		super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
		this.animationAmount = Mth.clamp(entity.getAnimationScale(partialTicks), 0.0F, 1.0F);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.xRot = 0.0F;
		this.head.zRot = 0.0F;

		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.head.xRot += Mth.cos(limbSwing * 0.45F) * 0.3F * limbSwingAmount;
		this.head.zRot += Mth.sin(limbSwing * 0.15F) * 0.1F * limbSwingAmount;
		this.hat.copyFrom(this.head);

		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entity), this.attackTime, ageInTicks);

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
			this.rightLeg.xRot = -0.75F * Mth.triangleWave(limbSwing, 7.5F) * limbSwingAmount;
			this.leftLeg.xRot = 0.75F * Mth.triangleWave(limbSwing, 7.5F) * limbSwingAmount;

			if (this.animationAmount > 0.0F)
			{
				this.rightLeg.xRot *= 1.0F - this.animationAmount;
				this.leftLeg.xRot *= 1.0F - this.animationAmount;
				this.rightLeg.yRot *= 1.0F - this.animationAmount;
				this.leftLeg.yRot *= 1.0F - this.animationAmount;
				this.rightLeg.zRot *= 1.0F - this.animationAmount;
				this.leftLeg.zRot *= 1.0F - this.animationAmount;
				this.rightLeg.xRot += ((float)Math.PI / 45.0F) * this.animationAmount;
				this.leftLeg.xRot += ((float)Math.PI / 45.0F) * this.animationAmount;
				this.rightLeg.zRot += -((float)Math.PI / 60.0F) * this.animationAmount;
				this.leftLeg.zRot += ((float)Math.PI / 60.0F) * this.animationAmount;
			}
		}

		this.skirt1.xRot = 0.0F;
	}

	public boolean isAggressive(T entityIn)
	{
		return entityIn.isAggressive();
	}
}