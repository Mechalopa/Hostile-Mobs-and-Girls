package com.github.mechalopa.hmag.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnowCanineModel<T extends MobEntity> extends AbstractAdvancedGirlModel<T>
{
	private ModelRenderer bodyPart3;
	private ModelRenderer bodyPart4;
	private ModelRenderer rightArmPart1;
	private ModelRenderer leftArmPart1;
	private ModelRenderer rightArmPart2;
	private ModelRenderer leftArmPart2;
	private ModelRenderer rightArmPart3;
	private ModelRenderer leftArmPart3;
	private ModelRenderer rightLegPart1;
	private ModelRenderer leftLegPart1;
	private ModelRenderer rightLegPart2;
	private ModelRenderer leftLegPart2;
	private ModelRenderer rightLegPart3;
	private ModelRenderer leftLegPart3;
	private ModelRenderer rightLegPart4;
	private ModelRenderer leftLegPart4;
	private ModelRenderer rightLegPart5;
	private ModelRenderer leftLegPart5;
	private ModelRenderer rightEar;
	private ModelRenderer leftEar;
	private ModelRenderer rightEarPart;
	private ModelRenderer leftEarPart;
	private ModelRenderer tail1;
	private ModelRenderer tail2;
	private ModelRenderer tail3;
	private ModelRenderer tail4;
	private ModelRenderer hairPart;

	public SnowCanineModel()
	{
		this(0.0F);
	}

	public SnowCanineModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false);

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(0.0F, -2.0F, -1.5F, 2.0F, 1.0F, 3.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-2.0F, -2.0F, -1.5F, 2.0F, 1.0F, 3.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F, 0.0F);

		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.rightLeg.setPos(-2.1F, 12.0F, 0.5F);
		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.leftLeg.setPos(2.1F, 12.0F, 0.5F);

		this.bodyPart2 = new ModelRenderer(this, 32, 40);
		this.bodyPart2.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 1.0F, 3.0F, modelSize);
		this.bodyPart2.setPos(0.0F, 2.5F, 0.0F);
		this.bodyPart1.addChild(this.bodyPart2);
		this.bodyPart3 = new ModelRenderer(this, 32, 48);
		this.bodyPart3.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 2.0F, 4.0F, modelSize);
		this.bodyPart3.setPos(0.0F, 0.5F, 0.0F);
		this.bodyPart2.addChild(this.bodyPart3);
		this.bodyPart4 = new ModelRenderer(this, 32, 56);
		this.bodyPart4.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 1.0F, 5.0F, modelSize);
		this.bodyPart4.setPos(0.0F, 1.0F, 0.0F);
		this.bodyPart3.addChild(this.bodyPart4);

		this.rightArmPart1 = new ModelRenderer(this, 40, 20);
		this.rightArmPart1.addBox(0.0F, -2.0F, -1.5F, 2.0F, 6.0F, 3.0F, modelSize);
		this.rightArmPart1.setPos(0.0F, 0.0F, 0.0F);
		this.rightArm.addChild(this.rightArmPart1);
		this.leftArmPart1 = new ModelRenderer(this, 40, 20);
		this.leftArmPart1.mirror = true;
		this.leftArmPart1.addBox(-2.0F, -2.0F, -1.5F, 2.0F, 6.0F, 3.0F, modelSize);
		this.leftArmPart1.setPos(0.0F, 0.0F, 0.0F);
		this.leftArm.addChild(this.leftArmPart1);

		this.rightArmPart2 = new ModelRenderer(this, 16, 32);
		this.rightArmPart2.addBox(0.0F, -0.5F, -1.5F, 2.0F, 4.0F, 3.0F, modelSize - 0.125F);
		this.rightArmPart2.setPos(0.0F, 3.5F, 0.0F);
		this.rightArmPart1.addChild(this.rightArmPart2);
		this.leftArmPart2 = new ModelRenderer(this, 16, 32);
		this.leftArmPart2.mirror = true;
		this.leftArmPart2.addBox(-2.0F, -0.5F, -1.5F, 2.0F, 4.0F, 3.0F, modelSize - 0.125F);
		this.leftArmPart2.setPos(0.0F, 3.5F, 0.0F);
		this.leftArmPart1.addChild(this.leftArmPart2);

		this.rightArmPart3 = new ModelRenderer(this, 16, 40);
		this.rightArmPart3.addBox(0.0F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, modelSize);
		this.rightArmPart3.setPos(0.0F, 2.5F, 0.0F);
		this.rightArmPart2.addChild(this.rightArmPart3);
		this.leftArmPart3 = new ModelRenderer(this, 16, 40);
		this.leftArmPart3.mirror = true;
		this.leftArmPart3.addBox(-2.0F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, modelSize);
		this.leftArmPart3.setPos(0.0F, 2.5F, 0.0F);
		this.leftArmPart2.addChild(this.leftArmPart3);

		this.rightLegPart1 = new ModelRenderer(this, 0, 20);
		this.rightLegPart1.addBox(-2.25F, 0.0F, -1.75F, 4.0F, 5.0F, 4.0F, modelSize);
		this.rightLegPart1.setPos(0.0F, -1.5F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart1);
		this.leftLegPart1 = new ModelRenderer(this, 0, 20);
		this.leftLegPart1.mirror = true;
		this.leftLegPart1.addBox(-1.75F, 0.0F, -1.75F, 4.0F, 5.0F, 4.0F, modelSize);
		this.leftLegPart1.setPos(0.0F, -1.5F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart1);

		this.rightLegPart2 = new ModelRenderer(this, 0, 40);
		this.rightLegPart2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.rightLegPart2.setPos(0.0F, 5.0F, 0.0F);
		this.rightLegPart1.addChild(this.rightLegPart2);
		this.leftLegPart2 = new ModelRenderer(this, 0, 40);
		this.leftLegPart2.mirror = true;
		this.leftLegPart2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.leftLegPart2.setPos(0.0F, 5.0F, 0.0F);
		this.leftLegPart1.addChild(this.leftLegPart2);

		this.rightLegPart3 = new ModelRenderer(this, 0, 46);
		this.rightLegPart3.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, modelSize - 0.001F);
		this.rightLegPart3.setPos(0.0F, 5.25F, 0.0F);
		this.rightLegPart1.addChild(this.rightLegPart3);
		this.leftLegPart3 = new ModelRenderer(this, 0, 46);
		this.leftLegPart3.mirror = true;
		this.leftLegPart3.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, modelSize - 0.001F);
		this.leftLegPart3.setPos(0.0F, 5.25F, 0.0F);
		this.leftLegPart1.addChild(this.leftLegPart3);

		this.rightLegPart4 = new ModelRenderer(this, 0, 56);
		this.rightLegPart4.addBox(-1.5F, -1.5F, -1.0F, 3.0F, 4.0F, 2.0F, modelSize + 0.125F);
		this.rightLegPart4.setPos(0.0F, 5.75F, 0.0F);
		this.rightLegPart3.addChild(this.rightLegPart4);
		this.leftLegPart4 = new ModelRenderer(this, 0, 56);
		this.leftLegPart4.mirror = true;
		this.leftLegPart4.addBox(-1.5F, -1.5F, -1.0F, 3.0F, 4.0F, 2.0F, modelSize + 0.125F);
		this.leftLegPart4.setPos(0.0F, 5.75F, 0.0F);
		this.leftLegPart3.addChild(this.leftLegPart4);
		this.rightLegPart5 = new ModelRenderer(this, 16, 56);
		this.rightLegPart5.addBox(-1.5F, -1.0F, -1.0F, 3.0F, 3.0F, 2.0F, modelSize);
		this.rightLegPart5.setPos(0.0F, 2.5F, 0.0F);
		this.rightLegPart4.addChild(this.rightLegPart5);
		this.leftLegPart5 = new ModelRenderer(this, 16, 56);
		this.leftLegPart5.mirror = true;
		this.leftLegPart5.addBox(-1.5F, -1.0F, -1.0F, 3.0F, 3.0F, 2.0F, modelSize);
		this.leftLegPart5.setPos(0.0F, 2.5F, 0.0F);
		this.leftLegPart4.addChild(this.leftLegPart5);

		this.rightEar = new ModelRenderer(this, 32, 64);
		this.rightEar.addBox(-2.0F, -1.75F, -1.0F, 3.0F, 2.0F, 2.0F, modelSize + 0.25F);
		this.rightEar.setPos(-1.5F, -8.0F, -0.75F);
		this.head.addChild(this.rightEar);
		this.leftEar = new ModelRenderer(this, 32, 64);
		this.leftEar.mirror = true;
		this.leftEar.addBox(-1.0F, -1.75F, -1.0F, 3.0F, 2.0F, 2.0F, modelSize + 0.25F);
		this.leftEar.setPos(1.5F, -8.0F, -0.75F);
		this.head.addChild(this.leftEar);

		this.rightEarPart = new ModelRenderer(this, 32, 68);
		this.rightEarPart.addBox(-2.0F, -1.75F, -1.0F, 3.0F, 3.0F, 2.0F, modelSize);
		this.rightEarPart.setPos(0.0F, -2.0F, 0.0F);
		this.rightEar.addChild(this.rightEarPart);
		this.leftEarPart = new ModelRenderer(this, 32, 68);
		this.leftEarPart.mirror = true;
		this.leftEarPart.addBox(-1.0F, -1.75F, -1.0F, 3.0F, 3.0F, 2.0F, modelSize);
		this.leftEarPart.setPos(0.0F, -2.0F, -0.0F);
		this.leftEar.addChild(this.leftEarPart);

		this.tail1 = new ModelRenderer(this, 48, 64);
		this.tail1.addBox(-1.5F, -0.5F, -2.5F, 3.0F, 3.0F, 3.0F, modelSize);
		this.tail1.setPos(0.0F, 9.0F, 2.0F);
		this.body.addChild(this.tail1);
		this.tail2 = new ModelRenderer(this, 48, 72);
		this.tail2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, modelSize + 0.001F);
		this.tail2.setPos(0.0F, 2.25F, -1.0F);
		this.tail1.addChild(this.tail2);
		this.tail3 = new ModelRenderer(this, 48, 80);
		this.tail3.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, modelSize);
		this.tail3.setPos(0.0F, 2.5F, 0.0F);
		this.tail2.addChild(this.tail3);
		this.tail4 = new ModelRenderer(this, 48, 88);
		this.tail4.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, modelSize);
		this.tail4.setPos(0.0F, 2.0F, 0.0F);
		this.tail3.addChild(this.tail4);

		this.hairPart = new ModelRenderer(this, 0, 64);
		this.hairPart.addBox(-4.0F, 0.0F, -1.0F, 8.0F, 3.0F, 1.0F, modelSize);
		this.hairPart.setPos(0.0F, 0.0F, 4.0F);
		this.head.addChild(this.hairPart);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (this.crouching)
		{
			this.body.xRot = 0.5F;
			this.rightLeg.z = 4.0F;
			this.leftLeg.z = 4.0F;
			this.bodyPart3.xRot = 0.0F;
		}
		else
		{
			this.body.xRot = (float)Math.PI * 0.03F;
			this.rightLeg.z = 0.6F;
			this.leftLeg.z = 0.6F;
			this.bodyPart3.xRot = -((float)Math.PI * 0.03F);
		}

		this.rightArm.zRot = 0.0F;
		this.leftArm.zRot = 0.0F;
		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.024F + 0.21F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.024F + 0.21F;

		this.rightLeg.zRot = -((float)Math.PI * 0.01F);
		this.leftLeg.zRot = (float)Math.PI * 0.01F;
		this.rightArmPart1.xRot = (float)Math.PI / 12.0F;
		this.leftArmPart1.xRot = (float)Math.PI / 12.0F;
		this.rightArmPart2.xRot = -((float)Math.PI / 9.0F);
		this.leftArmPart2.xRot = -((float)Math.PI / 9.0F);
		this.rightArmPart3.xRot = -((float)Math.PI / 24.0F);
		this.leftArmPart3.xRot = -((float)Math.PI / 24.0F);

		this.rightLegPart1.xRot = -((float)Math.PI / 14.0F);
		this.leftLegPart1.xRot = -((float)Math.PI / 14.0F);

		this.rightLegPart3.xRot = (float)Math.PI / 5.0F;
		this.leftLegPart3.xRot = (float)Math.PI / 5.0F;
		this.rightLegPart4.xRot = -((float)Math.PI * 5.0F / 16.0F);
		this.leftLegPart4.xRot = -((float)Math.PI * 5.0F / 16.0F);
		this.rightLegPart5.xRot = -((float)Math.PI / 3.0F);
		this.leftLegPart5.xRot = -((float)Math.PI / 3.0F);

		this.rightEar.zRot = -((float)Math.PI / 36.0F);
		this.rightEar.zRot += MathHelper.sin(ageInTicks * 0.04F) * 0.03F;
		this.leftEar.zRot = (float)Math.PI / 36.0F;
		this.leftEar.zRot += MathHelper.sin(ageInTicks * 0.04F) * -0.03F;

		this.tail1.xRot = (float)Math.PI * 2.0F / 5.0F;
		this.tail1.xRot += MathHelper.sin(ageInTicks * 0.09F) * 0.3F;
		this.tail1.yRot = MathHelper.cos(ageInTicks * 0.033F) * 0.36F;
		this.tail2.xRot = (float)Math.PI / 7.0F;
		this.tail2.xRot += MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 8.0F) * 0.06F;
		this.tail3.xRot = (float)Math.PI / 9.0F;
		this.tail3.xRot += MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 4.0F) * 0.06F;

		this.hairPart.xRot = (float)Math.PI / 12.0F;
		this.hairPart.xRot += MathHelper.sin(ageInTicks * 0.075F) * 0.045F;
	}

	@Override
	protected int getBodyHeight()
	{
		return 6;
	}

	@Override
	protected float getLegRotZ()
	{
		return -((float)Math.PI * 0.004F);
	}
}