package com.github.mechalopa.hmag.client.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrimsonSlaughtererModel<T extends MobEntity> extends SegmentedModel<T>
{
	private final ModelRenderer head;
	private final ModelRenderer hat;
	private final ModelRenderer hairPart;
	private final ModelRenderer rightTailHair1;
	private final ModelRenderer rightTailHair2;
	private final ModelRenderer rightTailHair3;
	private final ModelRenderer rightTailHair4;
	private final ModelRenderer rightTailHairTop;
	private final ModelRenderer leftTailHair1;
	private final ModelRenderer leftTailHair2;
	private final ModelRenderer leftTailHair3;
	private final ModelRenderer leftTailHair4;
	private final ModelRenderer leftTailHairTop;
	private final ModelRenderer hairBand;

	private final ModelRenderer body;
	private final ModelRenderer bust;
	private final ModelRenderer bodyPart1;
	private final ModelRenderer bodyPart2;
	private final ModelRenderer body2;
	private final ModelRenderer body2Part1;
	private final ModelRenderer body2Part2;
	private final ModelRenderer body2Part3;
	private final ModelRenderer body2Part4;
	private final ModelRenderer body2Eyes;
	private final ModelRenderer body2Teeth;
	private final ModelRenderer body2Part5;
	private final ModelRenderer body2Part6;
	private final ModelRenderer body2Part7;
	private final ModelRenderer body2Part8;

	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArmPart;
	private final ModelRenderer leftArmPart;

	private final ModelRenderer leg1A;
	private final ModelRenderer leg2A;
	private final ModelRenderer leg3A;
	private final ModelRenderer leg4A;
	private final ModelRenderer leg5A;
	private final ModelRenderer leg6A;

	private final ModelRenderer leg1B;
	private final ModelRenderer leg2B;
	private final ModelRenderer leg3B;
	private final ModelRenderer leg4B;
	private final ModelRenderer leg5B;
	private final ModelRenderer leg6B;

	private final ModelRenderer leg1C;
	private final ModelRenderer leg2C;
	private final ModelRenderer leg3C;
	private final ModelRenderer leg4C;
	private final ModelRenderer leg5C;
	private final ModelRenderer leg6C;

	private final ModelRenderer leg1D;
	private final ModelRenderer leg2D;
	private final ModelRenderer leg3D;
	private final ModelRenderer leg4D;
	private final ModelRenderer leg5D;
	private final ModelRenderer leg6D;

	private final ModelRenderer leg1E;
	private final ModelRenderer leg2E;
	private final ModelRenderer leg3E;
	private final ModelRenderer leg4E;
	private final ModelRenderer leg5E;
	private final ModelRenderer leg6E;

	public CrimsonSlaughtererModel()
	{
		this.texHeight = 128;
		this.texWidth = 128;
		float f = 12.0F;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-3.0F, 1.0F, -3.0F, 6.0F, 6.0F, 6.0F, 1.0F);
		this.head.setPos(0.0F, f, 0.0F);
		this.hat = new ModelRenderer(this, 32, 0);
		this.hat.addBox(-4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F);
		this.hat.setPos(0.0F, 0.0F, 0.0F);
		this.head.addChild(this.hat);

		this.hairPart = new ModelRenderer(this, 64, 0);
		this.hairPart.addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F);
		this.hairPart.setPos(0.0F, 0.0F, 0.0F);
		this.head.addChild(this.hairPart);

		this.rightTailHair1 = new ModelRenderer(this, 64, 16);
		this.rightTailHair1.addBox(-1.0F, -3.0F, 0.0F, 2.0F, 4.0F, 2.0F);
		this.rightTailHair1.setPos(-2.25F, 6.0F, 4.0F);
		this.head.addChild(this.rightTailHair1);
		this.rightTailHair2 = new ModelRenderer(this, 72, 16);
		this.rightTailHair2.addBox(-1.0F, -5.5F, 0.0F, 2.0F, 6.0F, 2.0F, 0.001F);
		this.rightTailHair2.setPos(0.0F, -3.0F, 0.0F);
		this.rightTailHair1.addChild(this.rightTailHair2);
		this.rightTailHair3 = new ModelRenderer(this, 72, 24);
		this.rightTailHair3.addBox(-1.0F, -5.5F, 0.0F, 2.0F, 6.0F, 2.0F, -0.25F);
		this.rightTailHair3.setPos(0.0F, -5.25F, 0.0F);
		this.rightTailHair2.addChild(this.rightTailHair3);
		this.rightTailHair4 = new ModelRenderer(this, 80, 16);
		this.rightTailHair4.addBox(-0.5F, -6.5F, 0.0F, 1.0F, 6.0F, 1.0F);
		this.rightTailHair4.setPos(0.0F, -4.5F, 0.5F);
		this.rightTailHair3.addChild(this.rightTailHair4);
		this.rightTailHairTop = new ModelRenderer(this, 88, 16);
		this.rightTailHairTop.addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F);
		this.rightTailHairTop.setPos(0.0F, -6.0F, 0.5F);
		this.rightTailHair4.addChild(this.rightTailHairTop);

		this.leftTailHair1 = new ModelRenderer(this, 64, 16);
		this.leftTailHair1.mirror = true;
		this.leftTailHair1.addBox(-1.0F, -3.0F, 0.0F, 2.0F, 4.0F, 2.0F);
		this.leftTailHair1.setPos(2.25F, 6.0F, 4.0F);
		this.head.addChild(this.leftTailHair1);
		this.leftTailHair2 = new ModelRenderer(this, 72, 16);
		this.leftTailHair2.mirror = true;
		this.leftTailHair2.addBox(-1.0F, -5.5F, 0.0F, 2.0F, 6.0F, 2.0F, 0.001F);
		this.leftTailHair2.setPos(0.0F, -3.0F, 0.0F);
		this.leftTailHair1.addChild(this.leftTailHair2);
		this.leftTailHair3 = new ModelRenderer(this, 72, 24);
		this.leftTailHair3.mirror = true;
		this.leftTailHair3.addBox(-1.0F, -5.5F, 0.0F, 2.0F, 6.0F, 2.0F, -0.25F);
		this.leftTailHair3.setPos(0.0F, -5.25F, 0.0F);
		this.leftTailHair2.addChild(this.leftTailHair3);
		this.leftTailHair4 = new ModelRenderer(this, 80, 16);
		this.leftTailHair4.mirror = true;
		this.leftTailHair4.addBox(-0.5F, -6.5F, 0.0F, 1.0F, 6.0F, 1.0F);
		this.leftTailHair4.setPos(0.0F, -4.5F, 0.5F);
		this.leftTailHair3.addChild(this.leftTailHair4);
		this.leftTailHairTop = new ModelRenderer(this, 88, 16);
		this.leftTailHairTop.mirror = true;
		this.leftTailHairTop.addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F);
		this.leftTailHairTop.setPos(0.0F, -6.0F, 0.5F);
		this.leftTailHair4.addChild(this.leftTailHairTop);

		this.hairBand = new ModelRenderer(this, 88, 24);
		this.hairBand.addBox(-3.5F, 0.0F, 0.0F, 7.0F, 2.0F, 1.0F);
		this.hairBand.setPos(0.0F, 8.0F, -0.5F);
		this.head.addChild(this.hairBand);

		this.body = new ModelRenderer(this, 0, 16);
		this.body.addBox(-3.0F, -6.0F, -1.5F, 6.0F, 6.0F, 3.0F);
		this.body.setPos(0.0F, f, 0.0F);

		this.bust = new ModelRenderer(this, 0, 26);
		this.bust.addBox(-3.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, -0.001F);
		this.bust.setPos(0.0F, -3.5F, -1.1F);
		this.body.addChild(this.bust);

		this.bodyPart1 = new ModelRenderer(this, 24, 16);
		this.bodyPart1.addBox(-2.5F, -1.0F, -1.0F, 5.0F, 1.0F, 2.0F, 0.125F);
		this.bodyPart1.setPos(0.0F, -6.0F, 0.0F);
		this.body.addChild(this.bodyPart1);
		this.bodyPart2 = new ModelRenderer(this, 24, 24);
		this.bodyPart2.addBox(-2.5F, -4.0F, -1.0F, 5.0F, 4.0F, 2.0F);
		this.bodyPart2.setPos(0.0F, -1.0F, 0.0F);
		this.bodyPart1.addChild(this.bodyPart2);

		this.body2 = new ModelRenderer(this, 0, 32);
		this.body2.addBox(-5.5F, -2.0F, -4.0F, 11.0F, 2.0F, 8.0F);
		this.body2.setPos(0.0F, f - 13.0F, 0.0F);

		this.body2Teeth = new ModelRenderer(this, 40, 32);
		this.body2Teeth.addBox(-5.5F, -2.0F, -4.0F, 11.0F, 2.0F, 8.0F);
		this.body2Teeth.setPos(0.0F, -2.0F, 0.0F);
		this.body2.addChild(this.body2Teeth);

		this.body2Part1 = new ModelRenderer(this, 0, 48);
		this.body2Part1.addBox(-5.5F, -4.0F, -4.0F, 11.0F, 6.0F, 8.0F, -0.0001F);
		this.body2Part1.setPos(0.0F, -2.0F, 0.0F);
		this.body2.addChild(this.body2Part1);

		this.body2Part2 = new ModelRenderer(this, 0, 64);
		this.body2Part2.addBox(-6.5F, -4.0F, -4.5F, 13.0F, 2.0F, 9.0F);
		this.body2Part2.setPos(0.0F, -2.0F, 0.0F);
		this.body2.addChild(this.body2Part2);

		this.body2Part3 = new ModelRenderer(this, 0, 80);
		this.body2Part3.addBox(-7.0F, -2.0F, -5.0F, 14.0F, 2.0F, 10.0F);
		this.body2Part3.setPos(0.0F, -4.0F, 0.0F);
		this.body2Part2.addChild(this.body2Part3);

		this.body2Part4 = new ModelRenderer(this, 0, 96);
		this.body2Part4.addBox(-7.0F, -2.0F, -5.0F, 14.0F, 2.0F, 10.0F);
		this.body2Part4.setPos(0.0F, -2.0F, 0.0F);
		this.body2Part3.addChild(this.body2Part4);

		this.body2Eyes = new ModelRenderer(this, 0, 112);
		this.body2Eyes.addBox(-5.0F, -4.0F, -1.0F, 10.0F, 4.0F, 1.0F);
		this.body2Eyes.setPos(0.0F, 0.0F, -5.0F);
		this.body2Part3.addChild(this.body2Eyes);

		this.body2Part5 = new ModelRenderer(this, 48, 48);
		this.body2Part5.addBox(-4.5F, 0.0F, -3.0F, 9.0F, 2.0F, 6.0F);
		this.body2Part5.setPos(0.0F, 0.0F, 0.0F);
		this.body2.addChild(this.body2Part5);

		this.body2Part6 = new ModelRenderer(this, 48, 56);
		this.body2Part6.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 1.0F, 5.0F);
		this.body2Part6.setPos(0.0F, 2.0F, 0.0F);
		this.body2Part5.addChild(this.body2Part6);

		this.body2Part7 = new ModelRenderer(this, 48, 64);
		this.body2Part7.addBox(-3.0F, 0.0F, -2.0F, 6.0F, 2.0F, 4.0F);
		this.body2Part7.setPos(0.0F, -0.5F, -0.5F);
		this.body2Part6.addChild(this.body2Part7);

		this.body2Part8 = new ModelRenderer(this, 48, 72);
		this.body2Part8.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 3.0F, 5.0F);
		this.body2Part8.setPos(0.0F, 1.0F, 0.0F);
		this.body2Part6.addChild(this.body2Part8);

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(-2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F);
		this.rightArm.setPos(-5.0F, f - 2.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F);
		this.leftArm.setPos(5.0F, f - 2.0F, 0.0F);

		this.rightArmPart = new ModelRenderer(this, 48, 16);
		this.rightArmPart.addBox(-2.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.25F);
		this.rightArmPart.setPos(0.0F, 6.5F, 0.0F);
		this.rightArm.addChild(this.rightArmPart);
		this.leftArmPart = new ModelRenderer(this, 48, 16);
		this.leftArmPart.mirror = true;
		this.leftArmPart.addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.25F);
		this.leftArmPart.setPos(0.0F, 6.5F, 0.0F);
		this.leftArm.addChild(this.leftArmPart);

		byte b0 = 4;
		byte b1 = 7;
		byte b2 = 10;
		byte b3 = 18;
		byte b4 = 15;

		this.leg1A = new ModelRenderer(this, 48, 80);
		this.leg1A.addBox(-1.0F, -(float)b0 + 1.0F, -1.0F, 2.0F, (float)b0, 2.0F);
		this.leg1A.setPos(-3.0F, f - 21.0F, -0.5F);
		this.leg2A = new ModelRenderer(this, 48, 80);
		this.leg2A.mirror = true;
		this.leg2A.addBox(-1.0F, -(float)b0 + 1.0F, -1.0F, 2.0F, (float)b0, 2.0F);
		this.leg2A.setPos(3.0F, f - 21.0F, -0.5F);
		this.leg3A = new ModelRenderer(this, 48, 80);
		this.leg3A.addBox(-1.0F, -(float)b0 + 1.0F, -1.0F, 2.0F, (float)b0, 2.0F);
		this.leg3A.setPos(-3.5F, f - 21.0F, 1.5F);
		this.leg4A = new ModelRenderer(this, 48, 80);
		this.leg4A.mirror = true;
		this.leg4A.addBox(-1.0F, -(float)b0 + 1.0F, -1.0F, 2.0F, (float)b0, 2.0F);
		this.leg4A.setPos(3.5F, f - 21.0F, 1.5F);
		this.leg5A = new ModelRenderer(this, 48, 80);
		this.leg5A.addBox(-1.0F, -(float)b0 + 1.0F, -1.0F, 2.0F, (float)b0, 2.0F);
		this.leg5A.setPos(-3.0F, f - 21.0F, 3.5F);
		this.leg6A = new ModelRenderer(this, 48, 80);
		this.leg6A.mirror = true;
		this.leg6A.addBox(-1.0F, -(float)b0 + 1.0F, -1.0F, 2.0F, (float)b0, 2.0F);
		this.leg6A.setPos(3.0F, f - 21.0F, 3.5F);

		this.leg1B = new ModelRenderer(this, 48, 88);
		this.leg1B.addBox(-1.0F, -(float)b1 + 1.0F, -1.0F, 2.0F, (float)b1, 2.0F, 0.125F);
		this.leg1B.setPos(0.0F, -(float)b0 + 0.5F, 0.0F);
		this.leg1A.addChild(this.leg1B);
		this.leg2B = new ModelRenderer(this, 48, 88);
		this.leg2B.mirror = true;
		this.leg2B.addBox(-1.0F, -(float)b1 + 1.0F, -1.0F, 2.0F, (float)b1, 2.0F, 0.125F);
		this.leg2B.setPos(0.0F, -(float)b0 + 0.5F, 0.0F);
		this.leg2A.addChild(this.leg2B);
		this.leg3B = new ModelRenderer(this, 48, 88);
		this.leg3B.addBox(-1.0F, -(float)b1 + 1.0F, -1.0F, 2.0F, (float)b1, 2.0F, 0.125F);
		this.leg3B.setPos(0.0F, -(float)b0 + 0.5F, 0.0F);
		this.leg3A.addChild(this.leg3B);
		this.leg4B = new ModelRenderer(this, 48, 88);
		this.leg4B.mirror = true;
		this.leg4B.addBox(-1.0F, -(float)b1 + 1.0F, -1.0F, 2.0F, (float)b1, 2.0F, 0.125F);
		this.leg4B.setPos(0.0F, -(float)b0 + 0.5F, 0.0F);
		this.leg4A.addChild(this.leg4B);
		this.leg5B = new ModelRenderer(this, 48, 88);
		this.leg5B.addBox(-1.0F, -(float)b1 + 1.0F, -1.0F, 2.0F, (float)b1, 2.0F, 0.125F);
		this.leg5B.setPos(0.0F, -(float)b0 + 0.5F, 0.0F);
		this.leg5A.addChild(this.leg5B);
		this.leg6B = new ModelRenderer(this, 48, 88);
		this.leg6B.mirror = true;
		this.leg6B.addBox(-1.0F, -(float)b1 + 1.0F, -1.0F, 2.0F, (float)b1, 2.0F, 0.125F);
		this.leg6B.setPos(0.0F, -(float)b0 + 0.5F, 0.0F);
		this.leg6A.addChild(this.leg6B);

		this.leg1C = new ModelRenderer(this, 48, 104);
		this.leg1C.addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b2, 2.0F);
		this.leg1C.setPos(0.0F, -(float)b1 + 1.0F, 0.0F);
		this.leg1B.addChild(this.leg1C);
		this.leg2C = new ModelRenderer(this, 48, 104);
		this.leg2C.mirror = true;
		this.leg2C.addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b2, 2.0F);
		this.leg2C.setPos(0.0F, -(float)b1 + 1.0F, 0.0F);
		this.leg2B.addChild(this.leg2C);
		this.leg3C = new ModelRenderer(this, 48, 104);
		this.leg3C.addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b2, 2.0F);
		this.leg3C.setPos(0.0F, -(float)b1 + 1.0F, 0.0F);
		this.leg3B.addChild(this.leg3C);
		this.leg4C = new ModelRenderer(this, 48, 104);
		this.leg4C.mirror = true;
		this.leg4C.addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b2, 2.0F);
		this.leg4C.setPos(0.0F, -(float)b1 + 1.0F, 0.0F);
		this.leg4B.addChild(this.leg4C);
		this.leg5C = new ModelRenderer(this, 48, 104);
		this.leg5C.addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b2, 2.0F);
		this.leg5C.setPos(0.0F, -(float)b1 + 1.0F, 0.0F);
		this.leg5B.addChild(this.leg5C);
		this.leg6C = new ModelRenderer(this, 48, 104);
		this.leg6C.mirror = true;
		this.leg6C.addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b2, 2.0F);
		this.leg6C.setPos(0.0F, -(float)b1 + 1.0F, 0.0F);
		this.leg6B.addChild(this.leg6C);

		this.leg1D = new ModelRenderer(this, 56, 80);
		this.leg1D.addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b3, 2.0F, -0.125F);
		this.leg1D.setPos(0.0F, (float)b2 - 0.5F, 0.0F);
		this.leg1C.addChild(this.leg1D);
		this.leg2D = new ModelRenderer(this, 56, 80);
		this.leg2D.mirror = true;
		this.leg2D.addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b3, 2.0F, -0.125F);
		this.leg2D.setPos(0.0F, (float)b2 - 0.5F, 0.0F);
		this.leg2C.addChild(this.leg2D);
		this.leg3D = new ModelRenderer(this, 56, 80);
		this.leg3D.addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b3, 2.0F, -0.125F);
		this.leg3D.setPos(0.0F, (float)b2 - 0.5F, 0.0F);
		this.leg3C.addChild(this.leg3D);
		this.leg4D = new ModelRenderer(this, 56, 80);
		this.leg4D.mirror = true;
		this.leg4D.addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b3, 2.0F, -0.125F);
		this.leg4D.setPos(0.0F, (float)b2 - 0.5F, 0.0F);
		this.leg4C.addChild(this.leg4D);
		this.leg5D = new ModelRenderer(this, 56, 80);
		this.leg5D.addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b3, 2.0F, -0.125F);
		this.leg5D.setPos(0.0F, (float)b2 - 0.5F, 0.0F);
		this.leg5C.addChild(this.leg5D);
		this.leg6D = new ModelRenderer(this, 56, 80);
		this.leg6D.mirror = true;
		this.leg6D.addBox(-1.0F, -1.0F, -1.0F, 2.0F, (float)b3, 2.0F, -0.125F);
		this.leg6D.setPos(0.0F, (float)b2 - 0.5F, 0.0F);
		this.leg6C.addChild(this.leg6D);

		this.leg1E = new ModelRenderer(this, 64, 80);
		this.leg1E.addBox(-0.5F, -0.5F, -0.5F, 1.0F, (float)b4, 1.0F);
		this.leg1E.setPos(0.0F, (float)b3 - 1.0F, 0.0F);
		this.leg1D.addChild(this.leg1E);
		this.leg2E = new ModelRenderer(this, 64, 80);
		this.leg2E.mirror = true;
		this.leg2E.addBox(-0.5F, -0.5F, -0.5F, 1.0F, (float)b4, 1.0F);
		this.leg2E.setPos(0.0F, (float)b3 - 1.0F, 0.0F);
		this.leg2D.addChild(this.leg2E);
		this.leg3E = new ModelRenderer(this, 64, 80);
		this.leg3E.addBox(-0.5F, -0.5F, -0.5F, 1.0F, (float)b4, 1.0F);
		this.leg3E.setPos(0.0F, (float)b3 - 1.0F, 0.0F);
		this.leg3D.addChild(this.leg3E);
		this.leg4E = new ModelRenderer(this, 64, 80);
		this.leg4E.mirror = true;
		this.leg4E.addBox(-0.5F, -0.5F, -0.5F, 1.0F, (float)b4, 1.0F);
		this.leg4E.setPos(0.0F, (float)b3 - 1.0F, 0.0F);
		this.leg4D.addChild(this.leg4E);
		this.leg5E = new ModelRenderer(this, 64, 80);
		this.leg5E.addBox(-0.5F, -0.5F, -0.5F, 1.0F, (float)b4, 1.0F);
		this.leg5E.setPos(0.0F, (float)b3 - 1.0F, 0.0F);
		this.leg5D.addChild(this.leg5E);
		this.leg6E = new ModelRenderer(this, 64, 80);
		this.leg6E.mirror = true;
		this.leg6E.addBox(-0.5F, -0.5F, -0.5F, 1.0F, (float)b4, 1.0F);
		this.leg6E.setPos(0.0F, (float)b3 - 1.0F, 0.0F);
		this.leg6D.addChild(this.leg6E);
	}

	@Override
	public Iterable<ModelRenderer> parts()
	{
		return ImmutableList.of(this.head, this.body, this.rightArm, this.leftArm, this.body2, this.leg1A, this.leg2A, this.leg3A, this.leg4A, this.leg5A, this.leg6A);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.xRot = headPitch * ((float)Math.PI / 180.0F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180.0F);

		float f = 12.0F - (MathHelper.sin(ageInTicks * 0.09F) * 0.5F);
		this.head.y = f;
		this.body.y = f;
		this.rightArm.y = f - 2.0F;
		this.leftArm.y = f - 2.0F;
		f = -1.0F - (MathHelper.sin(ageInTicks * 0.09F) * 0.25F);
		this.body2.y = f;
		this.body.xRot = -((float)Math.PI / 18.0F);
		this.bust.xRot = ((float)Math.PI / 4.0F) - ((float)Math.PI / 18.0F);

		boolean flag = entityIn.getFallFlyingTicks() > 4;
		float f1 = 1.0F;

		if (flag)
		{
			f1 = (float)entityIn.getDeltaMovement().lengthSqr();
			f1 = f1 / 0.2F;
			f1 = f1 * f1 * f1;
		}

		if (f1 < 1.0F)
		{
			f1 = 1.0F;
		}

		this.rightArm.xRot = -((float)Math.PI / 18.0F);
		this.leftArm.xRot = -((float)Math.PI / 18.0F);
		this.rightArm.xRot += MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.1F / f1;
		this.leftArm.xRot += MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.1F / f1;

		if (entityIn.isAggressive())
		{
			float f2 = MathHelper.sin(this.attackTime * (float)Math.PI);
			float f3 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
			this.rightArm.yRot = -(0.1F - f2 * 0.4F);
			this.leftArm.yRot = 0.1F - f2 * 0.4F;
			this.rightArm.xRot -= f2 * 1.0F - f3 * 0.3F;
			this.leftArm.xRot -= f2 * 1.0F - f3 * 0.3F;
		}

		this.rightArm.zRot = (float)Math.PI * 5.0F / 6.0F;
		this.leftArm.zRot = -((float)Math.PI * 5.0F / 6.0F);
		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.06F) * 0.06F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.06F) * 0.06F;

		this.bodyPart1.xRot = ((float)Math.PI / 30.0F);
		this.bodyPart2.xRot = ((float)Math.PI / 30.0F);

		this.body2.xRot = ((float)Math.PI / 8.0F);
		this.body2.xRot -= MathHelper.cos(limbSwing * 0.45F) * 0.2F * limbSwingAmount;
		this.body2Part1.xRot = -((float)Math.PI / 4.0F);
		this.body2Part1.xRot += MathHelper.cos(ageInTicks * 0.06F) * 0.09F;
		this.body2Part1.xRot += MathHelper.cos(limbSwing * 0.45F) * 0.6F * limbSwingAmount;

		if (this.body2Part1.xRot > -((float)Math.PI / 5.0F))
		{
			this.body2Part1.xRot = -((float)Math.PI / 5.0F);
		}

		this.body2Part2.xRot = -((float)Math.PI / 5.0F);
		this.body2Part2.xRot += MathHelper.cos(limbSwing * 0.45F + (float)Math.PI / 4.0F) * 0.12F * limbSwingAmount;
		this.body2Part7.xRot = -((float)Math.PI / 15.0F);

		float f4 = (float)Math.PI / 4.0F;

		this.leg1A.yRot = -f4 * 0.57F;
		this.leg2A.yRot = f4 * 0.57F;
		this.leg3A.yRot = f4 * 0.15F;
		this.leg4A.yRot = -f4 * 0.15F;
		this.leg5A.yRot = f4 * 0.72F;
		this.leg6A.yRot = -f4 * 0.72F;

		f4 = (float)Math.PI / 16.0F;

		this.leg1A.zRot = -f4;
		this.leg2A.zRot = f4;
		this.leg3A.zRot = -f4;
		this.leg4A.zRot = f4;
		this.leg5A.zRot = -f4;
		this.leg6A.zRot = f4;

		f4 = (float)Math.PI / 3.0F;

		this.leg1B.zRot = -f4;
		this.leg2B.zRot = f4;
		this.leg3B.zRot = -f4;
		this.leg4B.zRot = f4;
		this.leg5B.zRot = -f4;
		this.leg6B.zRot = f4;

		f4 = (float)Math.PI * 4.0F / 7.0F;

		this.leg1C.zRot = f4;
		this.leg2C.zRot = -f4;
		this.leg3C.zRot = f4;
		this.leg4C.zRot = -f4;
		this.leg5C.zRot = f4;
		this.leg6C.zRot = -f4;

		f4 = -((float)Math.PI / 5.0F);

		this.leg1D.zRot = f4;
		this.leg2D.zRot = -f4;
		this.leg3D.zRot = f4;
		this.leg4D.zRot = -f4;
		this.leg5D.zRot = f4;
		this.leg6D.zRot = -f4;

		f4 = -((float)Math.PI / 27.0F);

		this.leg1E.zRot = f4;
		this.leg2E.zRot = -f4;
		this.leg3E.zRot = f4;
		this.leg4E.zRot = -f4;
		this.leg5E.zRot = f4;
		this.leg6E.zRot = -f4;

		if (entityIn.isOnGround())
		{
			float f5 = 0.45F;
			float f6 = -(MathHelper.cos(limbSwing * f5 * 2.0F) * 0.4F) * limbSwingAmount;
			float f7 = -(MathHelper.cos(limbSwing * f5 * 2.0F + (float)Math.PI * 4.0F / 3.0F) * 0.4F) * limbSwingAmount;
			float f8 = -(MathHelper.cos(limbSwing * f5 * 2.0F + ((float)Math.PI * 2.0F / 3.0F)) * 0.4F) * limbSwingAmount;
			float f9 = Math.abs(MathHelper.sin(limbSwing * f5) * 0.4F) * limbSwingAmount;
			float f10 = Math.abs(MathHelper.sin(limbSwing * f5 + (float)Math.PI * 4.0F / 3.0F) * 0.4F) * limbSwingAmount;
			float f11 = Math.abs(MathHelper.sin(limbSwing * f5 + ((float)Math.PI * 2.0F / 3.0F)) * 0.4F) * limbSwingAmount;
			float f12 = Math.abs(MathHelper.sin(limbSwing * f5 - ((float)Math.PI / 16.0F)) * 0.4F) * limbSwingAmount;
			float f13 = Math.abs(MathHelper.sin(limbSwing * f5 + (float)Math.PI * 61.0F / 48.0F) * 0.4F) * limbSwingAmount;
			float f14 = Math.abs(MathHelper.sin(limbSwing * f5 + ((float)Math.PI * 29.0F / 48.0F)) * 0.4F) * limbSwingAmount;
			this.leg1A.yRot += f6;
			this.leg2A.yRot += -f6;
			this.leg3A.yRot += f7;
			this.leg4A.yRot += -f7;
			this.leg5A.yRot += f8;
			this.leg6A.yRot += -f8;
			this.leg1A.zRot += f9 * 0.45F;
			this.leg2A.zRot += -f9 * 0.45F;
			this.leg3A.zRot += f10 * 0.45F;
			this.leg4A.zRot += -f10 * 0.45F;
			this.leg5A.zRot += f11 * 0.45F;
			this.leg6A.zRot += -f11 * 0.45F;
			this.leg1B.zRot += f9 * 0.81F;
			this.leg2B.zRot += -f9 * 0.81F;
			this.leg3B.zRot += f10 * 0.81F;
			this.leg4B.zRot += -f10 * 0.81F;
			this.leg5B.zRot += f11 * 0.81F;
			this.leg6B.zRot += -f11 * 0.81F;
			this.leg1C.zRot += -f9 * 1.2F;
			this.leg2C.zRot += f9 * 1.2F;
			this.leg3C.zRot += -f10 * 1.2F;
			this.leg4C.zRot += f10 * 1.2F;
			this.leg5C.zRot += -f11 * 1.2F;
			this.leg6C.zRot += f11 * 1.2F;
			this.leg1D.zRot += f12 * 0.3F;
			this.leg2D.zRot += -f12 * 0.3F;
			this.leg3D.zRot += f13 * 0.3F;
			this.leg4D.zRot += -f13 * 0.3F;
			this.leg5D.zRot += f14 * 0.3F;
			this.leg6D.zRot += -f14 * 0.3F;
			this.leg1E.zRot += -f12 * 0.27F;
			this.leg2E.zRot += f12 * 0.27F;
			this.leg3E.zRot += -f13 * 0.27F;
			this.leg4E.zRot += f13 * 0.27F;
			this.leg5E.zRot += -f14 * 0.27F;
			this.leg6E.zRot += f14 * 0.27F;
		}

		this.rightTailHair1.xRot = -((float)Math.PI / 15.0F);
		this.leftTailHair1.xRot = -((float)Math.PI / 15.0F);
		this.rightTailHair1.xRot -= MathHelper.sin(ageInTicks * 0.03F) * 0.06F;
		this.leftTailHair1.xRot -= MathHelper.sin(ageInTicks * 0.03F + (float)Math.PI / 6.0F) * 0.06F;
		this.rightTailHair1.zRot = -((float)Math.PI / 21.0F);
		this.leftTailHair1.zRot = (float)Math.PI / 21.0F;
		this.rightTailHair1.zRot -= MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 2.0F) * 0.06F;
		this.leftTailHair1.zRot += MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 2.0F) * 0.06F;
		this.rightTailHair2.xRot = -((float)Math.PI / 12.0F);
		this.leftTailHair2.xRot = -((float)Math.PI / 12.0F);
		this.rightTailHair2.zRot = -((float)Math.PI / 24.0F);
		this.leftTailHair2.zRot = (float)Math.PI / 24.0F;
		this.rightTailHair2.xRot -= MathHelper.sin(ageInTicks * 0.03F + (float)Math.PI / 12.0F) * 0.045F;
		this.leftTailHair2.xRot -= MathHelper.sin(ageInTicks * 0.03F + (float)Math.PI / 4.0F) * 0.045F;
		this.rightTailHair3.xRot = -((float)Math.PI / 12.0F);
		this.leftTailHair3.xRot = -((float)Math.PI / 12.0F);
		this.rightTailHair3.xRot -= MathHelper.sin(ageInTicks * 0.03F + (float)Math.PI / 6.0F) * 0.024F;
		this.leftTailHair3.xRot -= MathHelper.sin(ageInTicks * 0.03F + (float)Math.PI / 3.0F) * 0.024F;
		this.rightTailHair4.xRot = -((float)Math.PI / 9.0F);
		this.leftTailHair4.xRot = -((float)Math.PI / 9.0F);
		this.rightTailHair4.xRot -= MathHelper.sin(ageInTicks * 0.03F + (float)Math.PI / 4.0F) * 0.015F;
		this.leftTailHair4.xRot -= MathHelper.sin(ageInTicks * 0.03F + (float)Math.PI * 5.0F / 12.0F) * 0.015F;
		this.rightTailHairTop.yRot = -((float)Math.PI / 3.0F);
		this.leftTailHairTop.yRot = (float)Math.PI / 3.0F;

		this.hairBand.xRot = (float)Math.PI / 24.0F;
		this.hairBand.xRot += MathHelper.sin(ageInTicks * 0.03F) * 0.012F;
	}
}