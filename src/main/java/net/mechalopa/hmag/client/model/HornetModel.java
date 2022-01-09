package net.mechalopa.hmag.client.model;

import com.google.common.collect.ImmutableList;

import net.mechalopa.hmag.entity.AbstractFlyingMonsterEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HornetModel<T extends AbstractFlyingMonsterEntity> extends BipedModel<T>
{
	private ModelRenderer rightFeelerPart1;
	private ModelRenderer rightFeelerPart2;
	private ModelRenderer leftFeelerPart1;
	private ModelRenderer leftFeelerPart2;
	private ModelRenderer rightArmAPart1;
	private ModelRenderer rightArmAPart2;
	private ModelRenderer leftArmAPart1;
	private ModelRenderer leftArmAPart2;
	private ModelRenderer rightArmB;
	private ModelRenderer rightArmBPart1;
	private ModelRenderer rightArmBPart2;
	private ModelRenderer leftArmB;
	private ModelRenderer leftArmBPart1;
	private ModelRenderer leftArmBPart2;
	private ModelRenderer rightLegPart1;
	private ModelRenderer rightLegPart2;
	private ModelRenderer rightLegPart3;
	private ModelRenderer rightLegToe;
	private ModelRenderer leftLegPart1;
	private ModelRenderer leftLegPart2;
	private ModelRenderer leftLegPart3;
	private ModelRenderer leftLegToe;
	private ModelRenderer bust;
	private ModelRenderer rightWingA;
	private ModelRenderer leftWingA;
	private ModelRenderer rightWingB;
	private ModelRenderer leftWingB;
	private ModelRenderer thorax;
	private ModelRenderer abdomenPart1;
	private ModelRenderer abdomenPart2;
	private ModelRenderer abdomenPart3;
	private ModelRenderer abdomenPart4;
	private ModelRenderer abdomenPart5;
	private ModelRenderer abdomenPart6;
	private ModelRenderer abdomenPart7;
	private ModelRenderer abdomenPart8;
	private ModelRenderer abdomenPart9;
	private ModelRenderer needle;

	public HornetModel()
	{
		this(0.0F);
	}

	public HornetModel(float modelSize)
	{
		this(modelSize, 0.0F);
	}

	public HornetModel(float modelSize, float yOffsetIn)
	{
		super(modelSize, yOffsetIn, 64, 128);

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-3.0F, -6.0F - 1.0F, -3.0F, 6.0F, 6.0F, 6.0F, modelSize + 1.0F);
		this.head.setPos(0.0F, 0.0F + yOffsetIn, 0.0F);

		this.rightFeelerPart1 = new ModelRenderer(this, 40, 32);
		this.rightFeelerPart1.addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, modelSize);
		this.rightFeelerPart1.setPos(-2.0F, -7.5F, -1.5F);
		this.head.addChild(this.rightFeelerPart1);
		this.rightFeelerPart2 = new ModelRenderer(this, 40, 40);
		this.rightFeelerPart2.addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize - 0.25F);
		this.rightFeelerPart2.setPos(0.0F, -4.75F, -0.25F);
		this.rightFeelerPart1.addChild(this.rightFeelerPart2);

		this.leftFeelerPart1 = new ModelRenderer(this, 40, 32);
		this.leftFeelerPart1.mirror = true;
		this.leftFeelerPart1.addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, modelSize);
		this.leftFeelerPart1.setPos(2.0F, -7.5F, -1.5F);
		this.head.addChild(this.leftFeelerPart1);
		this.leftFeelerPart2 = new ModelRenderer(this, 40, 40);
		this.leftFeelerPart2.mirror = true;
		this.leftFeelerPart2.addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize - 0.25F);
		this.leftFeelerPart2.setPos(0.0F, -4.75F, -0.25F);
		this.leftFeelerPart1.addChild(this.leftFeelerPart2);

		this.body = new ModelRenderer(this, 16, 16);
		this.body.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 8.0F, 3.0F, modelSize);
		this.body.setPos(0.0F, 0.0F + yOffsetIn, 0.0F);

		this.bust = new ModelRenderer(this, 0, 32);
		this.bust.addBox(-3.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, modelSize - 0.001F);
		this.bust.setPos(0.0F, 3.5F, -1.1F);
		this.body.addChild(this.bust);

		this.rightWingA = new ModelRenderer(this, 24, 96);
		this.rightWingA.addBox(-16.0F, -8.0F, 0.0F, 16.0F, 8.0F, 1.0F, modelSize);
		this.rightWingA.setPos(-1.5F, 4.0F, 1.5F);
		this.body.addChild(this.rightWingA);
		this.leftWingA = new ModelRenderer(this, 24, 96);
		this.leftWingA.mirror = true;
		this.leftWingA.addBox(0.0F, -8.0F, 0.0F, 16.0F, 8.0F, 1.0F, modelSize);
		this.leftWingA.setPos(1.5F, 4.0F, 1.5F);
		this.body.addChild(this.leftWingA);

		this.rightWingB = new ModelRenderer(this, 24, 108);
		this.rightWingB.addBox(-10.0F, 0.0F, 0.0F, 10.0F, 6.0F, 1.0F, modelSize);
		this.rightWingB.setPos(-1.5F, 5.0F, 1.5F);
		this.body.addChild(this.rightWingB);
		this.leftWingB = new ModelRenderer(this, 24, 108);
		this.leftWingB.mirror = true;
		this.leftWingB.addBox(0.0F, 0.0F, 0.0F, 10.0F, 6.0F, 1.0F, modelSize);
		this.leftWingB.setPos(1.5F, 5.0F, 1.5F);
		this.body.addChild(this.leftWingB);

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(-2.0F, -2.0F, -1.5F, 4.0F, 2.0F, 3.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F + yOffsetIn, 0.0F);
		this.rightArmAPart1 = new ModelRenderer(this, 48, 40);
		this.rightArmAPart1.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.rightArmAPart1.setPos(-0.5F, -0.5F, 0.0F);
		this.rightArm.addChild(this.rightArmAPart1);
		this.rightArmAPart2 = new ModelRenderer(this, 56, 40);
		this.rightArmAPart2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize + 0.25F);
		this.rightArmAPart2.setPos(0.0F, 4.75F, 0.0F);
		this.rightArmAPart1.addChild(this.rightArmAPart2);

		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-2.0F, -2.0F, -1.5F, 4.0F, 2.0F, 3.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F + yOffsetIn, 0.0F);
		this.leftArmAPart1 = new ModelRenderer(this, 48, 40);
		this.leftArmAPart1.mirror = true;
		this.leftArmAPart1.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.leftArmAPart1.setPos(0.5F, -0.5F, 0.0F);
		this.leftArm.addChild(this.leftArmAPart1);
		this.leftArmAPart2 = new ModelRenderer(this, 56, 40);
		this.leftArmAPart2.mirror = true;
		this.leftArmAPart2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize + 0.25F);
		this.leftArmAPart2.setPos(0.0F, 4.75F, 0.0F);
		this.leftArmAPart1.addChild(this.leftArmAPart2);

		this.rightArmB = new ModelRenderer(this, 48, 32);
		this.rightArmB.addBox(-1.0F, -2.0F, -1.5F, 2.0F, 2.0F, 3.0F, modelSize);
		this.rightArmB.setPos(-4.0F, 4.5F + yOffsetIn, 0.0F);
		this.rightArmBPart1 = new ModelRenderer(this, 48, 40);
		this.rightArmBPart1.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.rightArmBPart1.setPos(0.5F, -0.5F, 0.0F);
		this.rightArmB.addChild(this.rightArmBPart1);
		this.rightArmBPart2 = new ModelRenderer(this, 56, 40);
		this.rightArmBPart2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize + 0.25F);
		this.rightArmBPart2.setPos(0.0F, 4.75F, 0.0F);
		this.rightArmBPart1.addChild(this.rightArmBPart2);

		this.leftArmB = new ModelRenderer(this, 48, 32);
		this.leftArmB.mirror = true;
		this.leftArmB.addBox(-1.0F, -2.0F, -1.5F, 2.0F, 2.0F, 3.0F, modelSize);
		this.leftArmB.setPos(4.0F, 4.5F + yOffsetIn, 0.0F);
		this.leftArmBPart1 = new ModelRenderer(this, 48, 40);
		this.leftArmBPart1.mirror = true;
		this.leftArmBPart1.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.leftArmBPart1.setPos(-0.5F, -0.5F, 0.0F);
		this.leftArmB.addChild(this.leftArmBPart1);
		this.leftArmBPart2 = new ModelRenderer(this, 56, 40);
		this.leftArmBPart2.mirror = true;
		this.leftArmBPart2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize + 0.25F);
		this.leftArmBPart2.setPos(0.0F, 4.75F, 0.0F);
		this.leftArmBPart1.addChild(this.leftArmBPart2);

		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, modelSize);
		this.rightLeg.setPos(-1.5F, 6.5F + yOffsetIn, -2.0F);
		this.rightLegPart1 = new ModelRenderer(this, 40, 56);
		this.rightLegPart1.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, modelSize);
		this.rightLegPart1.setPos(0.0F, 2.75F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart1);
		this.rightLegPart2 = new ModelRenderer(this, 40, 64);
		this.rightLegPart2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize + 0.25F);
		this.rightLegPart2.setPos(0.0F, 3.75F, 0.0F);
		this.rightLegPart1.addChild(this.rightLegPart2);
		this.rightLegPart3 = new ModelRenderer(this, 40, 72);
		this.rightLegPart3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, modelSize);
		this.rightLegPart3.setPos(0.0F, 4.5F, 0.0F);
		this.rightLegPart2.addChild(this.rightLegPart3);
		this.rightLegToe = new ModelRenderer(this, 40, 80);
		this.rightLegToe.addBox(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, modelSize);
		this.rightLegToe.setPos(0.0F, 2.0F, 0.0F);
		this.rightLegPart3.addChild(this.rightLegToe);

		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 3.0F, 3.0F, modelSize);
		this.leftLeg.setPos(1.5F, 6.5F + yOffsetIn, -2.0F);
		this.leftLegPart1 = new ModelRenderer(this, 40, 56);
		this.leftLegPart1.mirror = true;
		this.leftLegPart1.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, modelSize);
		this.leftLegPart1.setPos(0.0F, 2.75F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart1);
		this.leftLegPart2 = new ModelRenderer(this, 40, 64);
		this.leftLegPart2.mirror = true;
		this.leftLegPart2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize + 0.25F);
		this.leftLegPart2.setPos(0.0F, 3.75F, 0.0F);
		this.leftLegPart1.addChild(this.leftLegPart2);
		this.leftLegPart3 = new ModelRenderer(this, 40, 72);
		this.leftLegPart3.mirror = true;
		this.leftLegPart3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, modelSize);
		this.leftLegPart3.setPos(0.0F, 4.5F, 0.0F);
		this.leftLegPart2.addChild(this.leftLegPart3);
		this.leftLegToe = new ModelRenderer(this, 40, 80);
		this.leftLegToe.mirror = true;
		this.leftLegToe.addBox(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, modelSize);
		this.leftLegToe.setPos(0.0F, 2.0F, 0.0F);
		this.leftLegPart3.addChild(this.leftLegToe);

		this.thorax = new ModelRenderer(this, 32, 48);
		this.thorax.addBox(-2.0F, -0.25F, -1.0F, 4.0F, 3.0F, 2.0F, modelSize);
		this.thorax.setPos(0.0F, 8.0F, 0.0F);
		this.body.addChild(this.thorax);

		this.abdomenPart1 = new ModelRenderer(this, 16, 32);
		this.abdomenPart1.addBox(-2.5F, 0.0F, -1.5F, 5.0F, 1.0F, 3.0F, modelSize);
		this.abdomenPart1.setPos(0.0F, 2.0F, 0.25F);
		this.thorax.addChild(this.abdomenPart1);
		this.abdomenPart2 = new ModelRenderer(this, 0, 40);
		this.abdomenPart2.addBox(-3.0F, 0.0F, -2.0F, 6, 1, 4, modelSize);
		this.abdomenPart2.setPos(0.0F, 1.0F, 0.0F);
		this.abdomenPart1.addChild(this.abdomenPart2);
		this.abdomenPart3 = new ModelRenderer(this, 0, 48);
		this.abdomenPart3.addBox(-3.5F, 0.0F, -3.0F, 7.0F, 2.0F, 6.0F, modelSize);
		this.abdomenPart3.setPos(0.0F, 1.0F, 0.0F);
		this.abdomenPart2.addChild(this.abdomenPart3);
		this.abdomenPart4 = new ModelRenderer(this, 0, 56);
		this.abdomenPart4.addBox(-4.0F, 0.0F, -3.5F, 8.0F, 2.0F, 7.0F, modelSize);
		this.abdomenPart4.setPos(0.0F, 2.0F, 0.0F);
		this.abdomenPart3.addChild(this.abdomenPart4);
		this.abdomenPart5 = new ModelRenderer(this, 0, 68);
		this.abdomenPart5.addBox(-4.5F, 0.0F, -4.0F, 9.0F, 4.0F, 8.0F, modelSize);
		this.abdomenPart5.setPos(0.0F, 2.0F, 0.0F);
		this.abdomenPart4.addChild(this.abdomenPart5);
		this.abdomenPart6 = new ModelRenderer(this, 0, 80);
		this.abdomenPart6.addBox(-4.0F, 0.0F, -3.5F, 8.0F, 2.0F, 7.0F, modelSize);
		this.abdomenPart6.setPos(0.0F, 4.0F, 0.0F);
		this.abdomenPart5.addChild(this.abdomenPart6);
		this.abdomenPart7 = new ModelRenderer(this, 0, 92);
		this.abdomenPart7.addBox(-3.0F, 0.0F, -2.5F, 6.0F, 1.0F, 5.0F, modelSize);
		this.abdomenPart7.setPos(0.0F, 2.0F, 0.5F);
		this.abdomenPart6.addChild(this.abdomenPart7);
		this.abdomenPart8 = new ModelRenderer(this, 0, 100);
		this.abdomenPart8.addBox(-2.5F, 0.0F, -2.0F, 5.0F, 1.0F, 4.0F, modelSize);
		this.abdomenPart8.setPos(0.0F, 1.0F, 0.5F);
		this.abdomenPart7.addChild(this.abdomenPart8);
		this.abdomenPart9 = new ModelRenderer(this, 0, 108);
		this.abdomenPart9.addBox(-1.5F, 0.0F, -1.0F, 3.0F, 2.0F, 2.0F, modelSize);
		this.abdomenPart9.setPos(0.0F, 1.0F, 0.75F);
		this.abdomenPart8.addChild(this.abdomenPart9);

		this.needle = new ModelRenderer(this, 32, 56);
		this.needle.addBox(-1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F, modelSize);
		this.needle.setPos(0.0F, 2.0F, 0.0F);
		this.abdomenPart9.addChild(this.needle);
	}

	@Override
	protected Iterable<ModelRenderer> bodyParts()
	{
		return ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg, this.hat, this.rightArmB, this.leftArmB);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		ModelHelper.animateZombieArms(this.leftArm, this.rightArm, entityIn.isCharging(), this.attackTime, ageInTicks);

		float f = -(float)Math.PI / (entityIn.isCharging() ? 4.0F : 15.0F);
		float f1 = MathHelper.sin(ageInTicks * 0.12F);
		float f2 = MathHelper.sin(ageInTicks * 0.09F);

		this.rightArm.zRot += f1 * 0.05F + 0.05F;
		this.leftArm.zRot -= f1 * 0.05F + 0.05F;

		this.rightArmAPart1.xRot = ((float)Math.PI / 24.0F);
		this.leftArmAPart1.xRot = ((float)Math.PI / 24.0F);
		this.rightArmAPart2.xRot = -((float)Math.PI / 18.0F);
		this.leftArmAPart2.xRot = -((float)Math.PI / 18.0F);
		this.rightArmAPart2.zRot = ((float)Math.PI / 28.0F);
		this.leftArmAPart2.zRot = -((float)Math.PI / 28.0F);

		this.rightArmB.xRot = f * 0.2F;
		this.leftArmB.xRot = f * 0.2F;
		this.rightArmB.zRot = 0.0F;
		this.leftArmB.zRot = 0.0F;

		if (this.crouching)
		{
			this.rightArmB.xRot += -((float)Math.PI / 5.0F);
			this.leftArmB.xRot += -((float)Math.PI / 5.0F);
		}

		this.rightArmB.zRot += f2 * 0.05F + 0.05F;
		this.leftArmB.zRot -= f2 * 0.05F + 0.05F;
		this.rightArmB.xRot += f1 * 0.06F;
		this.leftArmB.xRot -= f1 * 0.06F;

		this.rightArmBPart1.xRot = -((float)Math.PI / 21.0F);
		this.leftArmBPart1.xRot = -((float)Math.PI / 21.0F);
		this.rightArmBPart1.yRot = -((float)Math.PI / 21.0F);
		this.leftArmBPart1.yRot = ((float)Math.PI / 21.0F);
		this.rightArmBPart2.xRot = -((float)Math.PI * 3.0F / 7.0F);
		this.leftArmBPart2.xRot = -((float)Math.PI * 3.0F / 7.0F);

		this.rightLeg.yRot = ((float)Math.PI / 28.0F);
		this.leftLeg.yRot = -((float)Math.PI / 28.0F);

		if (this.crouching)
		{
			this.rightLeg.zRot = ((float)Math.PI / 15.0F);
			this.leftLeg.zRot = -((float)Math.PI / 15.0F);
			this.rightLeg.xRot = -((float)Math.PI / 8.0F);
			this.leftLeg.xRot = -((float)Math.PI / 8.0F);
			this.rightLeg.xRot = 0.0F;
			this.leftLeg.xRot = 0.0F;
			this.rightLegPart2.xRot = 0.0F;
			this.leftLegPart2.xRot = 0.0F;
			this.rightLegToe.xRot = -((float)Math.PI / 4.0F);
			this.leftLegToe.xRot = -((float)Math.PI / 4.0F);
		}
		else
		{
			this.rightLeg.zRot = ((float)Math.PI / 32.0F);
			this.leftLeg.zRot = -((float)Math.PI / 32.0F);
			this.rightLeg.xRot = -((float)Math.PI / 5.0F);
			this.leftLeg.xRot = -((float)Math.PI / 5.0F);
			this.rightLeg.xRot += f1 * 0.045F;
			this.leftLeg.xRot += f1 * 0.045F;

			f1 = MathHelper.sin(ageInTicks * 0.12F - (float)Math.PI / 4.0F);
			this.rightLegPart2.xRot = ((float)Math.PI / 2.0F);
			this.leftLegPart2.xRot = ((float)Math.PI / 2.0F);
			this.rightLegPart2.xRot -= f1 * 0.075F;
			this.leftLegPart2.xRot -= f1 * 0.075F;
			this.rightLegToe.xRot = -((float)Math.PI / 3.0F);
			this.leftLegToe.xRot = -((float)Math.PI / 3.0F);
			this.rightLegToe.xRot += f1 * 0.072F;
			this.leftLegToe.xRot += f1 * 0.072F;
		}

		f1 = MathHelper.sin(ageInTicks * 0.12F - (float)Math.PI / 4.0F);

		this.rightLegToe.yRot = ((float)Math.PI / 28.0F);
		this.leftLegToe.yRot = -((float)Math.PI / 28.0F);

		this.rightFeelerPart1.yRot = ((float)Math.PI / 21.0F);
		this.leftFeelerPart1.yRot = -((float)Math.PI / 21.0F);
		this.rightFeelerPart1.xRot = ((float)Math.PI / 8.0F);
		this.leftFeelerPart1.xRot = ((float)Math.PI / 8.0F);
		this.rightFeelerPart1.xRot += f1 * 0.067F;
		this.leftFeelerPart1.xRot += f1 * 0.067F;
		this.rightFeelerPart2.yRot = ((float)Math.PI / 24.0F);
		this.leftFeelerPart2.yRot = -((float)Math.PI / 24.0F);
		this.rightFeelerPart2.xRot = ((float)Math.PI / 3.0F);
		this.leftFeelerPart2.xRot = ((float)Math.PI / 3.0F);
		this.rightFeelerPart2.xRot -= f2 * 0.048F;
		this.leftFeelerPart2.xRot -= f2 * 0.048F;

		this.bust.xRot = ((float)Math.PI / 4.0F) + ((float)Math.PI / 18.0F);

		float f4 = MathHelper.cos(ageInTicks * 0.2F + (float)Math.PI / 4.0F);
		float f3 = MathHelper.cos(ageInTicks * 2.1F);
		this.rightWingA.yRot = ((float)Math.PI / 4.0F);
		this.leftWingA.yRot = -((float)Math.PI / 4.0F);
		this.rightWingA.zRot = ((float)Math.PI / 16.0F);
		this.leftWingA.zRot = -((float)Math.PI / 16.0F);
		this.rightWingA.yRot += f3 * 0.42F;
		this.leftWingA.yRot -= f3 * 0.42F;
		this.rightWingA.zRot += f4 * 0.18F;
		this.leftWingA.zRot -= f4 * 0.18F;

		f3 = MathHelper.cos(ageInTicks * 2.1F + (float)Math.PI / 3.0F);
		this.rightWingB.yRot = ((float)Math.PI / 4.0F);
		this.leftWingB.yRot = -((float)Math.PI / 4.0F);
		this.rightWingB.zRot = -((float)Math.PI / 16.0F);
		this.leftWingB.zRot = ((float)Math.PI / 16.0F);
		this.rightWingB.yRot += f3 * 0.42F;
		this.leftWingB.yRot -= f3 * 0.42F;
		this.rightWingB.zRot -= f4 * 0.18F;
		this.leftWingB.zRot += f4 * 0.18F;

		f2 = MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F);
		this.thorax.xRot = ((float)Math.PI / 21.0F);
		this.thorax.xRot += f2 * 0.036F;
		this.abdomenPart1.xRot = ((float)Math.PI / 6.0F);
		this.abdomenPart1.xRot += MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.12F;
		this.abdomenPart1.xRot -= f1 * 0.067F;
		this.abdomenPart8.xRot = f1 * 0.033F;
		this.abdomenPart9.xRot = f1 * 0.04F;

		this.abdomenPart6.y = 4.0F - 0.5F;
		this.abdomenPart6.y -= MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI) * 0.2F;

		this.needle.y = 2.0F - 1.0F;
		this.needle.y -= MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI) * 0.8F;
	}
}