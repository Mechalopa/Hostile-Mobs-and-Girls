package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.entity.FortressKeeperEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FortressKeeperModel<T extends FortressKeeperEntity> extends SegmentedModel<T>
{
	private final ModelRenderer head;
	private final ModelRenderer headPartRight;
	private final ModelRenderer headPartLeft;
	private final ModelRenderer body;
	private final ModelRenderer bodyPart1;
	private final ModelRenderer bodyPart2;
	private final ModelRenderer bodyPart3;
	private final ModelRenderer bodyPart4;
	private final ModelRenderer bodyPart5Right;
	private final ModelRenderer bodyPart5Left;
	private final ModelRenderer bodyPart6Right;
	private final ModelRenderer bodyPart6Left;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArmPart1;
	private final ModelRenderer leftArmPart1;
	private final ModelRenderer rightArmPart2;
	private final ModelRenderer leftArmPart2;
	private final ModelRenderer rightArmPart3A;
	private final ModelRenderer rightArmPart3B;
	private final ModelRenderer rightArmPart3C;
	private final ModelRenderer rightArmPart3D;
	private final ModelRenderer leftArmPart3A;
	private final ModelRenderer leftArmPart3B;
	private final ModelRenderer leftArmPart3C;
	private final ModelRenderer leftArmPart3D;
	private final ModelRenderer rightArmPart4;
	private final ModelRenderer leftArmPart4;
	private final ModelRenderer rightArmPart5A;
	private final ModelRenderer rightArmPart5B;
	private final ModelRenderer rightArmPart5C;
	private final ModelRenderer rightArmPart5D;
	private final ModelRenderer leftArmPart5A;
	private final ModelRenderer leftArmPart5B;
	private final ModelRenderer leftArmPart5C;
	private final ModelRenderer leftArmPart5D;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg1Part;
	private final ModelRenderer leg2Part;
	private final ModelRenderer leg3Part;
	private final ModelRenderer leg4Part;
	private float animationAmount;
	private float animationAmount1;

	public FortressKeeperModel()
	{
		this.texHeight = 128;
		this.texWidth = 128;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-4.0F, -3.0F, -4.0F, 8.0F, 7.0F, 6.0F);
		this.head.setPos(0.0F, -4.0F, -4.0F);

		this.headPartRight = new ModelRenderer(this, 32, 0);
		this.headPartRight.addBox(-1.0F, -2.0F, -1.0F, 3.0F, 2.0F, 3.0F);
		this.headPartRight.setPos(-3.5F, 4.5F, -3.5F);
		this.head.addChild(this.headPartRight);
		this.headPartLeft = new ModelRenderer(this, 32, 0);
		this.headPartLeft.mirror = true;
		this.headPartLeft.addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 3.0F);
		this.headPartLeft.setPos(3.5F, 4.5F, -3.5F);
		this.head.addChild(this.headPartLeft);

		this.body = new ModelRenderer(this, 0, 16);
		this.body.addBox(-8.0F, 0.0F, -5.0F, 16.0F, 12.0F, 10.0F);
		this.body.setPos(0.0F, -9.0F, 0.0F);

		this.bodyPart1 = new ModelRenderer(this, 0, 40);
		this.bodyPart1.addBox(-5.0F, 0.0F, -4.0F, 10.0F, 3.0F, 8.0F);
		this.bodyPart1.setPos(0.0F, 12.0F, 0.0F);
		this.body.addChild(this.bodyPart1);

		this.bodyPart2 = new ModelRenderer(this, 0, 52);
		this.bodyPart2.addBox(-3.5F, 0.0F, -2.5F, 7.0F, 6.0F, 5.0F);
		this.bodyPart2.setPos(0.0F, 3.0F, 0.0F);
		this.bodyPart1.addChild(this.bodyPart2);

		this.bodyPart3 = new ModelRenderer(this, 0, 64);
		this.bodyPart3.addBox(-5.0F, 0.0F, -3.5F, 10.0F, 2.0F, 7.0F);
		this.bodyPart3.setPos(0.0F, 6.0F, 0.0F);
		this.bodyPart2.addChild(this.bodyPart3);

		this.bodyPart4 = new ModelRenderer(this, 0, 74);
		this.bodyPart4.addBox(-6.0F, 0.0F, -4.5F, 12.0F, 2.0F, 9.0F);
		this.bodyPart4.setPos(0.0F, 2.0F, 0.0F);
		this.bodyPart3.addChild(this.bodyPart4);

		this.bodyPart5Right = new ModelRenderer(this, 40, 40);
		this.bodyPart5Right.addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 6.0F);
		this.bodyPart5Right.setPos(-6.0F, 0.0F, 0.0F);
		this.body.addChild(this.bodyPart5Right);
		this.bodyPart5Left = new ModelRenderer(this, 40, 40);
		this.bodyPart5Left.mirror = true;
		this.bodyPart5Left.addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 6.0F);
		this.bodyPart5Left.setPos(6.0F, 0.0F, 0.0F);
		this.body.addChild(this.bodyPart5Left);

		this.bodyPart6Right = new ModelRenderer(this, 40, 48);
		this.bodyPart6Right.addBox(-2.0F, -8.0F, -1.0F, 4.0F, 11.0F, 4.0F);
		this.bodyPart6Right.setPos(-4.0F, 5.0F, 5.0F);
		this.body.addChild(this.bodyPart6Right);
		this.bodyPart6Left = new ModelRenderer(this, 40, 48);
		this.bodyPart6Left.mirror = true;
		this.bodyPart6Left.addBox(-2.0F, -8.0F, -1.0F, 4.0F, 11.0F, 4.0F);
		this.bodyPart6Left.setPos(4.0F, 5.0F, 5.0F);
		this.body.addChild(this.bodyPart6Left);

		this.rightArm = new ModelRenderer(this, 64, 0);
		this.rightArm.addBox(-9.0F, -2.0F, -5.0F, 10.0F, 12.0F, 10.0F);
		this.rightArm.setPos(-10.5F, -4.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 64, 24);
		this.leftArm.addBox(-1.0F, -2.0F, -5.0F, 10.0F, 12.0F, 10.0F);
		this.leftArm.setPos(10.5F, -4.0F, 0.0F);

		this.rightArmPart1 = new ModelRenderer(this, 64, 48);
		this.rightArmPart1.addBox(-3.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F);
		this.rightArmPart1.setPos(-4.0F, -2.0F, 0.0F);
		this.rightArm.addChild(this.rightArmPart1);
		this.leftArmPart1 = new ModelRenderer(this, 64, 48);
		this.leftArmPart1.mirror = true;
		this.leftArmPart1.addBox(-3.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F);
		this.leftArmPart1.setPos(4.0F, -2.0F, 0.0F);
		this.leftArm.addChild(this.leftArmPart1);

		this.rightArmPart2 = new ModelRenderer(this, 64, 56);
		this.rightArmPart2.addBox(-4.5F, -4.0F, -4.5F, 9.0F, 4.0F, 9.0F);
		this.rightArmPart2.setPos(0.0F, -2.0F, 0.0F);
		this.rightArmPart1.addChild(this.rightArmPart2);
		this.leftArmPart2 = new ModelRenderer(this, 64, 56);
		this.leftArmPart2.mirror = true;
		this.leftArmPart2.addBox(-4.5F, -4.0F, -4.5F, 9.0F, 4.0F, 9.0F);
		this.leftArmPart2.setPos(0.0F, -2.0F, 0.0F);
		this.leftArmPart1.addChild(this.leftArmPart2);

		this.rightArmPart3A = new ModelRenderer(this, 104, 0);
		this.rightArmPart3A.addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 4.0F);
		this.rightArmPart3A.setPos(-4.5F, -4.0F, 0.0F);
		this.rightArmPart2.addChild(this.rightArmPart3A);
		this.rightArmPart3B = new ModelRenderer(this, 104, 6);
		this.rightArmPart3B.addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 4.0F);
		this.rightArmPart3B.setPos(4.5F, -4.0F, 0.0F);
		this.rightArmPart2.addChild(this.rightArmPart3B);
		this.rightArmPart3C = new ModelRenderer(this, 104, 12);
		this.rightArmPart3C.addBox(-2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F);
		this.rightArmPart3C.setPos(0.0F, -4.0F, -4.5F);
		this.rightArmPart2.addChild(this.rightArmPart3C);
		this.rightArmPart3D = new ModelRenderer(this, 104, 16);
		this.rightArmPart3D.addBox(-2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F);
		this.rightArmPart3D.setPos(0.0F, -4.0F, 4.5F);
		this.rightArmPart2.addChild(this.rightArmPart3D);
		this.leftArmPart3A = new ModelRenderer(this, 104, 0);
		this.leftArmPart3A.mirror = true;
		this.leftArmPart3A.addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 4.0F);
		this.leftArmPart3A.setPos(4.5F, -4.0F, 0.0F);
		this.leftArmPart2.addChild(this.leftArmPart3A);
		this.leftArmPart3B = new ModelRenderer(this, 104, 6);
		this.leftArmPart3B.mirror = true;
		this.leftArmPart3B.addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 4.0F);
		this.leftArmPart3B.setPos(-4.5F, -4.0F, 0.0F);
		this.leftArmPart2.addChild(this.leftArmPart3B);
		this.leftArmPart3C = new ModelRenderer(this, 104, 12);
		this.leftArmPart3C.mirror = true;
		this.leftArmPart3C.addBox(-2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F);
		this.leftArmPart3C.setPos(0.0F, -4.0F, -4.5F);
		this.leftArmPart2.addChild(this.leftArmPart3C);
		this.leftArmPart3D = new ModelRenderer(this, 104, 16);
		this.leftArmPart3D.mirror = true;
		this.leftArmPart3D.addBox(-2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F);
		this.leftArmPart3D.setPos(0.0F, -4.0F, 4.5F);
		this.leftArmPart2.addChild(this.leftArmPart3D);

		this.rightArmPart4 = new ModelRenderer(this, 64, 72);
		this.rightArmPart4.addBox(-6.0F, 0.0F, -6.0F, 12.0F, 6.0F, 12.0F);
		this.rightArmPart4.setPos(-4.0F, 10.0F, 0.0F);
		this.rightArm.addChild(this.rightArmPart4);
		this.leftArmPart4 = new ModelRenderer(this, 64, 72);
		this.leftArmPart4.mirror = true;
		this.leftArmPart4.addBox(-6.0F, 0.0F, -6.0F, 12.0F, 6.0F, 12.0F);
		this.leftArmPart4.setPos(4.0F, 10.0F, 0.0F);
		this.leftArm.addChild(this.leftArmPart4);

		this.rightArmPart5A = new ModelRenderer(this, 96, 48);
		this.rightArmPart5A.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F);
		this.rightArmPart5A.setPos(-3.0F, 5.0F, -3.0F);
		this.rightArmPart4.addChild(this.rightArmPart5A);
		this.rightArmPart5B = new ModelRenderer(this, 104, 48);
		this.rightArmPart5B.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F);
		this.rightArmPart5B.setPos(3.0F, 5.0F, -3.0F);
		this.rightArmPart4.addChild(this.rightArmPart5B);
		this.rightArmPart5C = new ModelRenderer(this, 104, 48);
		this.rightArmPart5C.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F);
		this.rightArmPart5C.setPos(-3.0F, 5.0F, 3.0F);
		this.rightArmPart4.addChild(this.rightArmPart5C);
		this.rightArmPart5D = new ModelRenderer(this, 96, 48);
		this.rightArmPart5D.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F);
		this.rightArmPart5D.setPos(3.0F, 5.0F, 3.0F);
		this.rightArmPart4.addChild(this.rightArmPart5D);
		this.leftArmPart5A = new ModelRenderer(this, 96, 48);
		this.leftArmPart5A.mirror = true;
		this.leftArmPart5A.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F);
		this.leftArmPart5A.setPos(3.0F, 5.0F, -3.0F);
		this.leftArmPart4.addChild(this.leftArmPart5A);
		this.leftArmPart5B = new ModelRenderer(this, 104, 48);
		this.leftArmPart5B.mirror = true;
		this.leftArmPart5B.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F);
		this.leftArmPart5B.setPos(-3.0F, 5.0F, -3.0F);
		this.leftArmPart4.addChild(this.leftArmPart5B);
		this.leftArmPart5C = new ModelRenderer(this, 104, 48);
		this.leftArmPart5C.mirror = true;
		this.leftArmPart5C.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F);
		this.leftArmPart5C.setPos(3.0F, 5.0F, 3.0F);
		this.leftArmPart4.addChild(this.leftArmPart5C);
		this.leftArmPart5D = new ModelRenderer(this, 96, 48);
		this.leftArmPart5D.mirror = true;
		this.leftArmPart5D.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F);
		this.leftArmPart5D.setPos(-3.0F, 5.0F, 3.0F);
		this.leftArmPart4.addChild(this.leftArmPart5D);

		this.leg1 = new ModelRenderer(this, 40, 64);
		this.leg1.addBox(-2.5F, -1.0F, -2.5F, 5.0F, 3.0F, 5.0F);
		this.leg1.setPos(-3.5F, 16.25F, 3.5F);
		this.leg2 = new ModelRenderer(this, 40, 64);
		this.leg2.mirror = true;
		this.leg2.addBox(-2.5F, -1.0F, -2.5F, 5.0F, 3.0F, 5.0F);
		this.leg2.setPos(3.5F, 16.25F, 3.5F);
		this.leg3 = new ModelRenderer(this, 40, 64);
		this.leg3.addBox(-2.5F, -1.0F, -2.5F, 5.0F, 3.0F, 5.0F);
		this.leg3.setPos(-3.5F, 16.25F, -3.5F);
		this.leg4 = new ModelRenderer(this, 40, 64);
		this.leg4.mirror = true;
		this.leg4.addBox(-2.5F, -1.0F, -2.5F, 5.0F, 3.0F, 5.0F);
		this.leg4.setPos(3.5F, 16.25F, -3.5F);

		this.leg1Part = new ModelRenderer(this, 42, 72);
		this.leg1Part.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F);
		this.leg1Part.setPos(0.0F, 2.0F, 0.0F);
		this.leg1.addChild(this.leg1Part);
		this.leg2Part = new ModelRenderer(this, 42, 72);
		this.leg2Part.mirror = true;
		this.leg2Part.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F);
		this.leg2Part.setPos(0.0F, 2.0F, 0.0F);
		this.leg2.addChild(this.leg2Part);
		this.leg3Part = new ModelRenderer(this, 42, 72);
		this.leg3Part.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F);
		this.leg3Part.setPos(0.0F, 2.0F, 0.0F);
		this.leg3.addChild(this.leg3Part);
		this.leg4Part = new ModelRenderer(this, 42, 72);
		this.leg4Part.mirror = true;
		this.leg4Part.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F);
		this.leg4Part.setPos(0.0F, 2.0F, 0.0F);
		this.leg4.addChild(this.leg4Part);
	}

	@Override
	public Iterable<ModelRenderer> parts()
	{
		return ImmutableList.of(this.head, this.body, this.rightArm, this.leftArm, this.leg1, this.leg2, this.leg3, this.leg4);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.animationAmount = MathHelper.clamp(entityIn.getAttackAnimationScale(partialTick), 0.0F, 1.0F);
		this.animationAmount1 = MathHelper.clamp(entityIn.getAttackHandChangeAnimationScale(partialTick), 0.0F, 1.0F);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw / (180.0F / (float)Math.PI);
		this.head.xRot = headPitch / (180.0F / (float)Math.PI);

		this.bodyPart6Right.xRot = -((float)Math.PI / 21.0F);
		this.bodyPart6Left.xRot = -((float)Math.PI / 21.0F);

		this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
		this.leg2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.2F * limbSwingAmount;
		this.leg3.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.2F * limbSwingAmount;
		this.leg4.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;

		float f = 0.09F;

		this.leg1.zRot = f;
		this.leg2.zRot = -f;
		this.leg3.zRot = f;
		this.leg4.zRot = -f;

		float f1 = entityIn.getMainArm() == HandSide.RIGHT ? (1.0F - animationAmount1) : animationAmount1;
		float f2 = entityIn.getMainArm() != HandSide.RIGHT ? (1.0F - animationAmount1) : animationAmount1;
		float f3 = 1.0F - (this.animationAmount * f1);
		float f4 = 1.0F - (this.animationAmount * f2);

		this.rightArm.xRot = MathHelper.sin(limbSwing * 0.6662F) * 1.0F * limbSwingAmount * f3;
		this.leftArm.xRot = MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 1.0F * limbSwingAmount * f4;
		this.rightArm.yRot = 0.0F;
		this.leftArm.yRot = 0.0F;
		this.rightArm.zRot = (MathHelper.cos(ageInTicks * 0.06F) * 0.03F + 0.15F) * f3;
		this.leftArm.zRot = -(MathHelper.cos(ageInTicks * 0.06F) * 0.03F + 0.15F) * f4;

		this.rightArm.y = -4.0F;
		this.rightArm.y += MathHelper.sin(ageInTicks * 0.075F + (float)Math.PI / 2.0F) * 1.0F * f3;
		this.leftArm.y = -4.0F;
		this.leftArm.y += MathHelper.sin(ageInTicks * 0.075F + (float)Math.PI * 3.0F / 2.0F) * 1.0F * f4;
		this.rightArm.x = -10.5F;
		this.leftArm.x = 10.5F;
		this.rightArm.z = 0.0F;
		this.leftArm.z = 0.0F;

		this.rightArmPart4.yRot = (ageInTicks * 0.04F) % (180.0F / (float)Math.PI);
		this.leftArmPart4.yRot = -((ageInTicks * 0.04F) % (180.0F / (float)Math.PI));

		if (this.animationAmount > 0.0F)
		{
			this.rightArm.y += 10.0F * this.animationAmount * f1;
			this.leftArm.y += 10.0F * this.animationAmount * f2;
			this.rightArm.y += 3.0F * this.animationAmount * f2;
			this.leftArm.y += 3.0F * this.animationAmount * f1;
			this.rightArm.y -= MathHelper.sin(this.animationAmount * (float)Math.PI) * 4.0F * f1;
			this.leftArm.y -= MathHelper.sin(this.animationAmount * (float)Math.PI) * 4.0F * f2;
			this.rightArm.x += 5.5F * this.animationAmount * f1;
			this.leftArm.x -= 5.5F * this.animationAmount * f2;
			this.rightArm.x += 2.0F * this.animationAmount * f2;
			this.leftArm.x -= 2.0F * this.animationAmount * f1;
			this.rightArm.z += MathHelper.sin(this.animationAmount * (float)Math.PI) * 1.0F * f1;
			this.leftArm.z += MathHelper.sin(this.animationAmount * (float)Math.PI) * 1.0F * f2;
			this.rightArm.z += 7.0F * this.animationAmount * f1;
			this.leftArm.z += 7.0F * this.animationAmount * f2;
			this.rightArm.z += 1.0F * this.animationAmount * f2;
			this.leftArm.z += 1.0F * this.animationAmount * f1;
			this.rightArm.xRot -= ((float)Math.PI / 2.0F) * this.animationAmount * f1;
			this.leftArm.xRot -= ((float)Math.PI / 2.0F) * this.animationAmount * f2;
			this.rightArm.xRot += MathHelper.sin(this.animationAmount * (float)Math.PI) * ((float)Math.PI / 5.0F) * f1;
			this.leftArm.xRot += MathHelper.sin(this.animationAmount * (float)Math.PI) * ((float)Math.PI / 5.0F) * f2;
			this.rightArm.zRot += (MathHelper.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) * this.animationAmount * f1;
			this.leftArm.zRot -= (MathHelper.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) * this.animationAmount * f2;
			this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.025F * this.animationAmount * f1;
			this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.025F * this.animationAmount * f2;
		}
		else if (this.attackTime > 0.0F)
		{
			float f5 = this.attackTime;
			float f6 = MathHelper.sin(MathHelper.sqrt(f5) * ((float)Math.PI * 2.0F)) * 0.2F;
			this.rightArm.x = -(MathHelper.cos(f6) * 4.5F + 6.0F);
			this.leftArm.x = MathHelper.cos(f6) * 4.5F + 6.0F;
			this.rightArm.z += MathHelper.sin(f6) * 5.0F;
			this.leftArm.z += MathHelper.sin(f6) * 5.0F;
			f5 = 1.0F - this.attackTime;
			f5 = f5 * f5;
			f5 = f5 * f5;
			f5 = 1.0F - f5;
			float f7 = MathHelper.sin(f5 * (float)Math.PI);
			float f8 = MathHelper.sin(this.attackTime * (float)Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
			this.rightArm.xRot = (float)((double)this.rightArm.xRot - ((double)f7 * 1.5D + (double)f8));
			this.leftArm.xRot = (float)((double)this.leftArm.xRot - ((double)f7 * 1.5D + (double)f8));
			this.rightArm.yRot += f6 * 2.5F;
			this.leftArm.yRot -= f6 * 2.5F;
			this.rightArm.zRot += MathHelper.sin(this.attackTime * (float)Math.PI) * -0.3F;
			this.leftArm.zRot += MathHelper.sin(this.attackTime * (float)Math.PI) * 0.3F;
			this.rightArm.z -= MathHelper.sin(this.attackTime * (float)Math.PI) * 6.0F;
			this.leftArm.z -= MathHelper.sin(this.attackTime * (float)Math.PI) * 6.0F;
			this.rightArm.y += MathHelper.sin(this.attackTime * (float)Math.PI) * 4.0F;
			this.leftArm.y += MathHelper.sin(this.attackTime * (float)Math.PI) * 4.0F;
		}

		this.rightArmPart5A.y = MathHelper.sin(ageInTicks * 0.081F) * 0.5F + 5.5F;
		this.rightArmPart5B.y = MathHelper.sin(ageInTicks * 0.081F + (float)Math.PI / 5.0F) * 0.5F + 5.5F;
		this.rightArmPart5C.y = MathHelper.sin(ageInTicks * 0.081F + (float)Math.PI * 2.0F / 5.0F) * 0.5F + 5.5F;
		this.rightArmPart5D.y = MathHelper.sin(ageInTicks * 0.081F + (float)Math.PI * 3.0F / 5.0F) * 0.5F + 5.5F;
		this.leftArmPart5A.y = MathHelper.sin(ageInTicks * 0.081F) * 0.5F + 5.5F;
		this.leftArmPart5B.y = MathHelper.sin(ageInTicks * 0.081F + (float)Math.PI / 5.0F) * 0.5F + 5.5F;
		this.leftArmPart5C.y = MathHelper.sin(ageInTicks * 0.081F + (float)Math.PI * 2.0F / 5.0F) * 0.5F + 5.5F;
		this.leftArmPart5D.y = MathHelper.sin(ageInTicks * 0.081F + (float)Math.PI * 3.0F / 5.0F) * 0.5F + 5.5F;
	}

	public void translateToHand(HandSide hand, MatrixStack matrixStack)
	{
		this.getArm(hand).translateAndRotate(matrixStack);
	}

	protected ModelRenderer getArm(HandSide hand)
	{
		return hand == HandSide.LEFT ? this.leftArm : this.rightArm;
	}
}