package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.entity.EnderExecutorEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderExecutorModel<T extends EnderExecutorEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer rightArmPart1;
	private ModelRenderer leftArmPart1;
	private ModelRenderer rightArmPart2;
	private ModelRenderer leftArmPart2;
	private ModelRenderer rightLegPart1;
	private ModelRenderer leftLegPart1;
	private ModelRenderer rightLegPart2;
	private ModelRenderer leftLegPart2;
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer skirt3;
	private ModelRenderer skirt4;
	private ModelRenderer skirt5;
	private ModelRenderer rightHair;
	private ModelRenderer leftHair;
	private ModelRenderer rightHairPart1;
	private ModelRenderer leftHairPart1;

	public boolean isAttacking;
	public boolean isCarrying;
	public boolean isBeamAttacking;
	private float animationAmount;

	public EnderExecutorModel()
	{
		this(0.0F);
	}

	public EnderExecutorModel(float modelSize)
	{
		super(modelSize, -14.0F, 64, 128, false, false);

		float f = -14.0F;

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F + f, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F + f, 0.0F);

		this.rightArmPart1 = new ModelRenderer(this, 0, 40);
		this.rightArmPart1.addBox(-0.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.rightArmPart1.setPos(0.0F, 3.0F, 0.0F);
		this.rightArm.addChild(this.rightArmPart1);
		this.leftArmPart1 = new ModelRenderer(this, 0, 40);
		this.leftArmPart1.mirror = true;
		this.leftArmPart1.addBox(-2.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.leftArmPart1.setPos(0.0F, 3.0F, 0.0F);
		this.leftArm.addChild(this.leftArmPart1);
		this.rightArmPart2 = new ModelRenderer(this, 0, 40);
		this.rightArmPart2.addBox(-0.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.rightArmPart2.setPos(0.0F, 7.0F, 0.0F);
		this.rightArm.addChild(this.rightArmPart2);
		this.leftArmPart2 = new ModelRenderer(this, 0, 40);
		this.leftArmPart2.mirror = true;
		this.leftArmPart2.addBox(-2.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.leftArmPart2.setPos(0.0F, 7.0F, 0.0F);
		this.leftArm.addChild(this.leftArmPart2);

		this.rightLeg = new ModelRenderer(this, 16, 32);
		this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 30.0F, 2.0F, modelSize);
		this.rightLeg.setPos(-2.0F, 12.0F + f, 0.0F);
		this.leftLeg = new ModelRenderer(this, 16, 32);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 30.0F, 2.0F, modelSize);
		this.leftLeg.setPos(2.0F, 12.0F + f, 0.0F);

		this.rightLegPart1 = new ModelRenderer(this, 0, 44);
		this.rightLegPart1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.rightLegPart1.setPos(0.0F, 16.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart1);
		this.leftLegPart1 = new ModelRenderer(this, 0, 44);
		this.leftLegPart1.mirror = true;
		this.leftLegPart1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.leftLegPart1.setPos(0.0F, 16.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart1);
		this.rightLegPart2 = new ModelRenderer(this, 0, 44);
		this.rightLegPart2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.rightLegPart2.setPos(0.0F, 18.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart2);
		this.leftLegPart2 = new ModelRenderer(this, 0, 44);
		this.leftLegPart2.mirror = true;
		this.leftLegPart2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.leftLegPart2.setPos(0.0F, 18.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart2);

		this.bust = new ModelRenderer(this, 0, 32);
		this.bust.addBox(-3.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, modelSize - 0.001F);
		this.bust.setPos(0.0F, 3.5F, -1.1F);
		this.body.addChild(this.bust);

		this.skirt1 = new ModelRenderer(this, 0, 64);
		this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 2.0F, 4.0F, modelSize);
		this.skirt1.setPos(0.0F, 11.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 24, 64);
		this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, modelSize);
		this.skirt2.setPos(0.0F, 13.0F, 0.0F);
		this.body.addChild(this.skirt2);
		this.skirt3 = new ModelRenderer(this, 0, 72);
		this.skirt3.addBox(-4.5F, 0.0F, -3.0F, 9.0F, 2.0F, 6.0F, modelSize);
		this.skirt3.setPos(0.0F, 2.0F, 0.0F);
		this.skirt2.addChild(this.skirt3);
		this.skirt4 = new ModelRenderer(this, 0, 80);
		this.skirt4.addBox(-5.0F, 0.0F, -3.5F, 10.0F, 2.0F, 7.0F, modelSize);
		this.skirt4.setPos(0.0F, 2.0F, 0.0F);
		this.skirt3.addChild(this.skirt4);
		this.skirt5 = new ModelRenderer(this, 0, 92);
		this.skirt5.addBox(-5.5F, 0.0F, -4.0F, 11.0F, 3.0F, 8.0F, modelSize);
		this.skirt5.setPos(0.0F, 2.0F, 0.0F);
		this.skirt4.addChild(this.skirt5);

		this.rightHair = new ModelRenderer(this, 24, 32);
		this.rightHair.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 14.0F, 2.0F, modelSize);
		this.rightHair.setPos(-4.25F, -8.0F, 3.5F);
		this.head.addChild(this.rightHair);
		this.leftHair = new ModelRenderer(this, 24, 32);
		this.leftHair.mirror = true;
		this.leftHair.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 14.0F, 2.0F, modelSize);
		this.leftHair.setPos(4.25F, -8.0F, 3.5F);
		this.head.addChild(this.leftHair);

		this.rightHairPart1 = new ModelRenderer(this, 32, 32);
		this.rightHairPart1.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.rightHairPart1.setPos(0.0F, 12.5F, 0.0F);
		this.rightHair.addChild(this.rightHairPart1);
		this.leftHairPart1 = new ModelRenderer(this, 32, 32);
		this.leftHairPart1.mirror = true;
		this.leftHairPart1.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.leftHairPart1.setPos(0.0F, 12.5F, 0.0F);
		this.leftHair.addChild(this.leftHairPart1);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.animationAmount = entityIn.getAttackAnimationScale(partialTick);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.head.visible = true;
		float f = -14.0F;
		this.body.xRot = 0.0F;
		this.body.y = f;
		this.body.z = 0.0F;
		this.rightLeg.xRot = (float)((double)this.rightLeg.xRot * 0.5D);
		this.leftLeg.xRot = (float)((double)this.leftLeg.xRot * 0.5D);

		float f1 = 0.21F;

		if (this.rightLeg.xRot > f1)
		{
			this.rightLeg.xRot = f1;
		}

		if (this.leftLeg.xRot > f1)
		{
			this.leftLeg.xRot = f1;
		}

		if (this.rightLeg.xRot < -f1)
		{
			this.rightLeg.xRot = -f1;
		}

		if (this.leftLeg.xRot < -f1)
		{
			this.leftLeg.xRot = -f1;
		}

		this.rightLeg.z = 0.0F;
		this.leftLeg.z = 0.0F;
		this.rightLeg.y = 9.0F + f;
		this.leftLeg.y = 9.0F + f;
		this.head.z = 0.0F;
		this.head.y = f;
		this.hat.copyFrom(this.head);

		if (this.isAttacking)
		{
			float f2 = MathHelper.sin(this.attackTime * (float)Math.PI);
			float f3 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
			this.rightArm.zRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightArm.yRot = -(5.0F - f2 * 0.6F);
			this.leftArm.yRot = 5.0F - f2 * 0.6F;
			this.rightArm.xRot = -((float)Math.PI / 2.0F);
			this.leftArm.xRot = -((float)Math.PI / 2.0F);
			this.rightArm.xRot -= f2 * 1.2F - f3 * 0.4F;
			this.leftArm.xRot -= f2 * 1.2F - f3 * 0.4F;
			this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.25F - 0.6F;
			this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.25F - 0.6F;
			this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
			this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		}
		else if (this.isCarrying)
		{
			if (entityIn.getMainArm() != HandSide.RIGHT)
			{
				this.rightArm.xRot = MathHelper.sin(ageInTicks * 0.09F) * 0.03F + ((float)Math.PI / 9.0F);
				this.rightArm.zRot = -(MathHelper.cos(ageInTicks * 0.06F) * 0.125F - ((float)Math.PI / 5.0F));
			}
			else
			{
				this.leftArm.xRot = MathHelper.sin(ageInTicks * 0.09F) * 0.03F + ((float)Math.PI / 9.0F);
				this.leftArm.zRot = MathHelper.cos(ageInTicks * 0.06F) * 0.125F - ((float)Math.PI / 5.0F);
			}
		}

		this.bust.xRot = ((float)Math.PI / 4.0F) + ((float)Math.PI / 18.0F);

		this.rightArm.setPos(-5.0F, 2.0F + f, 0.0F);
		this.leftArm.setPos(5.0F, 2.0F + f, 0.0F);

		this.rightHair.xRot = ((float)Math.PI / 18.0F);
		this.leftHair.xRot = ((float)Math.PI / 18.0F);
		this.rightHair.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.03F;
		this.leftHair.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.03F;
		this.rightHairPart1.xRot = ((float)Math.PI / 32.0F);
		this.leftHairPart1.xRot = ((float)Math.PI / 32.0F);

		this.rightHair.zRot = ((float)Math.PI / 9.0F);
		this.leftHair.zRot = -((float)Math.PI / 9.0F);
		this.rightHair.zRot -= MathHelper.sin(ageInTicks * 0.09F) * 0.03F;
		this.leftHair.zRot += MathHelper.sin(ageInTicks * 0.09F) * 0.03F;
		this.rightHairPart1.zRot = ((float)Math.PI / 27.0F);
		this.leftHairPart1.zRot = -((float)Math.PI / 27.0F);
		this.rightHairPart1.zRot -= MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 4.0F) * 0.024F;
		this.leftHairPart1.zRot += MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 4.0F) * 0.024F;

		if (this.animationAmount > 0.0F)
		{
			this.rightHair.zRot += MathHelper.sin(this.animationAmount * this.animationAmount * ((float)Math.PI / 1.2F)) * 0.75F;
			this.leftHair.zRot -= MathHelper.sin(this.animationAmount * this.animationAmount * ((float)Math.PI / 1.2F)) * 0.75F;
		}
	}
}