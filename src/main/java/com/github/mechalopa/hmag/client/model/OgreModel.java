package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.entity.OgreEntity;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OgreModel<T extends OgreEntity> extends BipedModel<T>
{
	private ModelRenderer horn1;
	private ModelRenderer horn2;
	private ModelRenderer body2;
	private ModelRenderer hip;
	private ModelRenderer hip2;
	private ModelRenderer rightArmTop;
	private ModelRenderer leftArmTop;
	private ModelRenderer rightShoulder;
	private ModelRenderer leftShoulder;
	private ModelRenderer rightLegTop;
	private ModelRenderer leftLegTop;

	private ModelRenderer rightShoulderPartA;
	private ModelRenderer leftShoulderPartA;
	private ModelRenderer rightShoulderPartB;
	private ModelRenderer leftShoulderPartB;
	private ModelRenderer rightArmPart;
	private ModelRenderer leftArmPart;
	private float swingAmount;

	public OgreModel()
	{
		this(0.0F);
	}

	public OgreModel(float modelSize)
	{
		this(modelSize, -5.0F, 64, 128);
	}

	public OgreModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn)
	{
		super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);

		float f = yOffsetIn;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 8.0F, modelSize);
		this.head.setPos(0.0F, 0.0F + f, 0.0F);
		this.hat = new ModelRenderer(this, 0, 0);
		this.hat.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, modelSize);
		this.hat.setPos(0.0F, 0.0F + f, 0.0F);

		this.horn1 = new ModelRenderer(this, 40, 16);
		this.horn1.addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.horn1.setPos(0.0F, -7.75F, 0.5F);
		this.head.addChild(this.horn1);
		this.horn2 = new ModelRenderer(this, 48, 16);
		this.horn2.addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, modelSize);
		this.horn2.setPos(0.0F, -3.0F, 0.0F);
		this.horn1.addChild(this.horn2);

		this.body = new ModelRenderer(this, 0, 16);
		this.body.addBox(-6.0F, 0.0F, -3.0F, 12.0F, 6.0F, 6.0F, modelSize);
		this.body.setPos(0.0F, 0.0F + f, 0.0F);

		this.body2 = new ModelRenderer(this, 0, 56);
		this.body2.addBox(-6.5F, 0.0F, -3.5F, 13.0F, 6.0F, 7.0F, modelSize);
		this.body2.setPos(0.0F, 6.0F, 0.0F);
		this.body.addChild(this.body2);
		this.hip = new ModelRenderer(this, 0, 72);
		this.hip.addBox(-7.0F, 0.0F, -4.0F, 14.0F, 5.0F, 8.0F, modelSize);
		this.hip.setPos(0.0F, 12.0F, 0.0F);
		this.body.addChild(this.hip);
		this.hip2 = new ModelRenderer(this, 0, 88);
		this.hip2.addBox(-7.0F, 0.0F, -4.0F, 14.0F, 3.0F, 8.0F, modelSize);
		this.hip2.setPos(0.0F, 5.0F, 0.0F);
		this.hip.addChild(this.hip2);

		this.rightArm = new ModelRenderer(this, 24, 32);
		this.rightArm.addBox(-6.0F, -2.0F, -2.0F, 5.0F, 8.0F, 4.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F + f, 0.0F);
		this.leftArm = new ModelRenderer(this, 24, 32);
		this.leftArm.mirror = true;
		this.leftArm.addBox(1.0F, -2.0F, -2.0F, 5.0F, 8.0F, 4.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F + f, 0.0F);

		this.rightArmTop = new ModelRenderer(this, 42, 32);
		this.rightArmTop.addBox(-6.5F, 0.0F, -2.5F, 6.0F, 6.0F, 5.0F, modelSize);
		this.rightArmTop.setPos(0.0F, 6.0F, 0.0F);
		this.rightArm.addChild(this.rightArmTop);
		this.leftArmTop = new ModelRenderer(this, 42, 32);
		this.leftArmTop.mirror = true;
		this.leftArmTop.addBox(0.5F, 0.0F, -2.5F, 6.0F, 6.0F, 5.0F, modelSize);
		this.leftArmTop.setPos(0.0F, 6.0F, 0.0F);
		this.leftArm.addChild(this.leftArmTop);

		this.rightArmPart = new ModelRenderer(this, 40, 56);
		this.rightArmPart.addBox(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, modelSize);
		this.rightArmPart.setPos(-6.25F, 2.9F, 0.0F);
		this.rightArmTop.addChild(this.rightArmPart);
		this.leftArmPart = new ModelRenderer(this, 40, 56);
		this.leftArmPart.mirror = true;
		this.leftArmPart.addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, modelSize);
		this.leftArmPart.setPos(6.25F, 2.9F, 0.0F);
		this.leftArmTop.addChild(this.leftArmPart);

		this.rightShoulder = new ModelRenderer(this, 40, 48);
		this.rightShoulder.addBox(-6.5F, 0.0F, -3.0F, 6.0F, 2.0F, 6.0F, modelSize + 0.5F);
		this.rightShoulder.setPos(0.5F, -2.5F, 0.0F);
		this.rightArm.addChild(this.rightShoulder);
		this.leftShoulder = new ModelRenderer(this, 40, 48);
		this.leftShoulder.mirror = true;
		this.leftShoulder.addBox(0.5F, 0.0F, -3.0F, 6.0F, 2.0F, 6.0F, modelSize + 0.5F);
		this.leftShoulder.setPos(-0.5F, -2.5F, 0.0F);
		this.leftArm.addChild(this.leftShoulder);

		this.rightShoulderPartA = new ModelRenderer(this, 24, 48);
		this.rightShoulderPartA.addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.rightShoulderPartA.setPos(-3.5F, -0.25F, 0.0F);
		this.rightShoulder.addChild(this.rightShoulderPartA);
		this.leftShoulderPartA = new ModelRenderer(this, 24, 48);
		this.leftShoulderPartA.mirror = true;
		this.leftShoulderPartA.addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.leftShoulderPartA.setPos(3.5F, -0.25F, 0.0F);
		this.leftShoulder.addChild(this.leftShoulderPartA);
		this.rightShoulderPartB = new ModelRenderer(this, 24, 48);
		this.rightShoulderPartB.addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.rightShoulderPartB.setPos(-6.25F, 0.75F, 0.0F);
		this.rightShoulder.addChild(this.rightShoulderPartB);
		this.leftShoulderPartB = new ModelRenderer(this, 24, 48);
		this.leftShoulderPartB.mirror = true;
		this.leftShoulderPartB.addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.leftShoulderPartB.setPos(6.25F, 0.75F, 0.0F);
		this.leftShoulder.addChild(this.leftShoulderPartB);

		this.rightLeg = new ModelRenderer(this, 0, 32);
		this.rightLeg.addBox(-2.5F, 0.0F, -2.0F, 5.0F, 6.0F, 4.0F, modelSize);
		this.rightLeg.setPos(-3.5F, 17.0F + f, 0.0F);
		this.leftLeg = new ModelRenderer(this, 0, 32);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-2.5F, 0.0F, -2.0F, 5.0F, 6.0F, 4.0F, modelSize);
		this.leftLeg.setPos(3.5F, 17.0F + f, 0.0F);

		this.rightLegTop = new ModelRenderer(this, 0, 42);
		this.rightLegTop.addBox(-3.0F, 0.0F, -2.5F, 6.0F, 6.0F, 5.0F, modelSize);
		this.rightLegTop.setPos(0.0F, 6.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegTop);
		this.leftLegTop = new ModelRenderer(this, 0, 42);
		this.leftLegTop.mirror = true;
		this.leftLegTop.addBox(-3.0F, 0.0F, -2.5F, 6.0F, 6.0F, 5.0F, modelSize);
		this.leftLegTop.setPos(0.0F, 6.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegTop);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.swingAmount = entityIn.getAnimationScale(partialTick);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float f = -5.0F;
		this.body.y = f;
		this.head.y = f;
		this.hat.y = f;
		this.rightArm.setPos(-5.0F, 2.0F + f, 0.0F);
		this.leftArm.setPos(5.0F, 2.0F + f, 0.0F);
		this.rightLeg.y = 17.0F + f;
		this.leftLeg.y = 17.0F + f;

		this.rightArm.zRot = (float)Math.PI / 30.0F;
		this.leftArm.zRot = -((float)Math.PI / 30.0F);
		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.03F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.03F;
		this.rightArm.yRot = 0.0F;
		this.leftArm.yRot = 0.0F;

		if (this.swingAmount > 0.0F)
		{
			this.rightArm.xRot = this.rightArm.xRot * (1.0F - this.swingAmount);
			this.leftArm.xRot = this.leftArm.xRot * (1.0F - this.swingAmount);
			this.rightArm.xRot += -(this.swingAmount * ((float)Math.PI * 4.0F / 7.0F));
			this.leftArm.xRot += -(this.swingAmount * ((float)Math.PI * 4.0F / 7.0F));
		}

		this.rightShoulderPartA.zRot = -((float)Math.PI / 18.0F);
		this.leftShoulderPartA.zRot = (float)Math.PI / 18.0F;
		this.rightShoulderPartB.zRot = -((float)Math.PI / 3.0F);
		this.leftShoulderPartB.zRot = (float)Math.PI / 3.0F;
	}
}