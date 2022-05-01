package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.entity.SpiderNestEntity;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpiderNestModel<T extends SpiderNestEntity> extends SegmentedModel<T>
{
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer bodyPart1;
	private final ModelRenderer bodyPart2;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg5;
	private final ModelRenderer leg6;
	private final ModelRenderer leg7;
	private final ModelRenderer leg8;
	private final ModelRenderer leg1Part1;
	private final ModelRenderer leg2Part1;
	private final ModelRenderer leg3Part1;
	private final ModelRenderer leg4Part1;
	private final ModelRenderer leg5Part1;
	private final ModelRenderer leg6Part1;
	private final ModelRenderer leg7Part1;
	private final ModelRenderer leg8Part1;
	private final ModelRenderer leg1Part2;
	private final ModelRenderer leg2Part2;
	private final ModelRenderer leg3Part2;
	private final ModelRenderer leg4Part2;
	private final ModelRenderer leg5Part2;
	private final ModelRenderer leg6Part2;
	private final ModelRenderer leg7Part2;
	private final ModelRenderer leg8Part2;
	private final ModelRenderer bodyPart3;
	private final ModelRenderer bodyPart4;
	private final ModelRenderer bodyPart5;
	private final ModelRenderer bodyPart6;
	private final ModelRenderer bodyPart7;
	private final ModelRenderer bodyPart8;
	private final ModelRenderer bodyPartsRightA[] = new ModelRenderer[4];
	private final ModelRenderer bodyPartsLeftA[] = new ModelRenderer[4];
	private final ModelRenderer bodyPartsRightB[] = new ModelRenderer[4];
	private final ModelRenderer bodyPartsLeftB[] = new ModelRenderer[4];

	public SpiderNestModel()
	{
		this.texHeight = 128;
		this.texWidth = 64;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 6.0F);
		this.head.setPos(0.0F, 15.0F, -8.0F);
		this.body = new ModelRenderer(this, 0, 16);
		this.body.addBox(-7.0F, -11.0F, -8.0F, 14.0F, 16.0F, 16.0F);
		this.body.setPos(0.0F, 14.0F, 0.0F);
		this.bodyPart1 = new ModelRenderer(this, 0, 64);
		this.bodyPart1.addBox(-8.0F, -8.0F, -7.0F, 16.0F, 16.0F, 14.0F);
		this.bodyPart1.setPos(0.0F, -3.0F, 0.0F);
		this.body.addChild(this.bodyPart1);
		this.bodyPart2 = new ModelRenderer(this, 0, 96);
		this.bodyPart2.addBox(-7.0F, -9.0F, -7.0F, 14.0F, 18.0F, 14.0F);
		this.bodyPart2.setPos(0.0F, -3.0F, 0.0F);
		this.body.addChild(this.bodyPart2);

		float f = 8.0F;
		float f1 = 7.0F;

		this.leg1 = new ModelRenderer(this, 0, 48);
		this.leg1.addBox(-1.0F, -0.5F, -1.0F, 2.0F, f, 2.0F);
		this.leg1.setPos(-8.5F, f1, -6.0F);
		this.leg2 = new ModelRenderer(this, 0, 48);
		this.leg2.mirror = true;
		this.leg2.addBox(-1.0F, -0.5F, -1.0F, 2.0F, f, 2.0F);
		this.leg2.setPos(8.5F, f1, -6.0F);
		this.leg3 = new ModelRenderer(this, 0, 48);
		this.leg3.addBox(-1.0F, -0.5F, -1.0F, 2.0F, f, 2.0F);
		this.leg3.setPos(-8.5F, f1, -2.0F);
		this.leg4 = new ModelRenderer(this, 0, 48);
		this.leg4.mirror = true;
		this.leg4.addBox(-1.0F, -0.5F, -1.0F, 2.0F, f, 2.0F);
		this.leg4.setPos(8.5F, f1, -2.0F);
		this.leg5 = new ModelRenderer(this, 0, 48);
		this.leg5.addBox(-1.0F, -0.5F, -1.0F, 2.0F, f, 2.0F);
		this.leg5.setPos(-8.5F, f1, 2.0F);
		this.leg6 = new ModelRenderer(this, 0, 48);
		this.leg6.mirror = true;
		this.leg6.addBox(-1.0F, -0.5F, -1.0F, 2.0F, f, 2.0F);
		this.leg6.setPos(8.5F, f1, 2.0F);
		this.leg7 = new ModelRenderer(this, 0, 48);
		this.leg7.addBox(-1.0F, -0.5F, -1.0F, 2.0F, f, 2.0F);
		this.leg7.setPos(-8.5F, f1, 6.0F);
		this.leg8 = new ModelRenderer(this, 0, 48);
		this.leg8.mirror = true;
		this.leg8.addBox(-1.0F, -0.5F, -1.0F, 2.0F, f, 2.0F);
		this.leg8.setPos(8.5F, f1, 6.0F);

		this.leg1Part1 = new ModelRenderer(this, 8, 48);
		this.leg1Part1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, -0.25F);
		this.leg1Part1.setPos(0.0F, -1.0F, 0.0F);
		this.leg1.addChild(this.leg1Part1);
		this.leg2Part1 = new ModelRenderer(this, 8, 48);
		this.leg2Part1.mirror = true;
		this.leg2Part1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, -0.25F);
		this.leg2Part1.setPos(0.0F, -1.0F, 0.0F);
		this.leg2.addChild(this.leg2Part1);
		this.leg3Part1 = new ModelRenderer(this, 8, 48);
		this.leg3Part1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, -0.25F);
		this.leg3Part1.setPos(0.0F, -1.0F, 0.0F);
		this.leg3.addChild(this.leg3Part1);
		this.leg4Part1 = new ModelRenderer(this, 8, 48);
		this.leg4Part1.mirror = true;
		this.leg4Part1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, -0.25F);
		this.leg4Part1.setPos(0.0F, -1.0F, 0.0F);
		this.leg4.addChild(this.leg4Part1);
		this.leg5Part1 = new ModelRenderer(this, 8, 48);
		this.leg5Part1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, -0.25F);
		this.leg5Part1.setPos(0.0F, -1.0F, 0.0F);
		this.leg5.addChild(this.leg5Part1);
		this.leg6Part1 = new ModelRenderer(this, 8, 48);
		this.leg6Part1.mirror = true;
		this.leg6Part1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, -0.25F);
		this.leg6Part1.setPos(0.0F, -1.0F, 0.0F);
		this.leg6.addChild(this.leg6Part1);
		this.leg7Part1 = new ModelRenderer(this, 8, 48);
		this.leg7Part1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, -0.25F);
		this.leg7Part1.setPos(0.0F, -1.0F, 0.0F);
		this.leg7.addChild(this.leg7Part1);
		this.leg8Part1 = new ModelRenderer(this, 8, 48);
		this.leg8Part1.mirror = true;
		this.leg8Part1.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, -0.25F);
		this.leg8Part1.setPos(0.0F, -1.0F, 0.0F);
		this.leg8.addChild(this.leg8Part1);

		this.leg1Part2 = new ModelRenderer(this, 20, 48);
		this.leg1Part2.addBox(-1.5F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F);
		this.leg1Part2.setPos(0.0F, f, 0.0F);
		this.leg1.addChild(this.leg1Part2);
		this.leg2Part2 = new ModelRenderer(this, 20, 48);
		this.leg2Part2.mirror = true;
		this.leg2Part2.addBox(-1.5F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F);
		this.leg2Part2.setPos(0.0F, f, 0.0F);
		this.leg2.addChild(this.leg2Part2);
		this.leg3Part2 = new ModelRenderer(this, 20, 48);
		this.leg3Part2.addBox(-1.5F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F);
		this.leg3Part2.setPos(0.0F, f, 0.0F);
		this.leg3.addChild(this.leg3Part2);
		this.leg4Part2 = new ModelRenderer(this, 20, 48);
		this.leg4Part2.mirror = true;
		this.leg4Part2.addBox(-1.5F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F);
		this.leg4Part2.setPos(0.0F, f, 0.0F);
		this.leg4.addChild(this.leg4Part2);
		this.leg5Part2 = new ModelRenderer(this, 20, 48);
		this.leg5Part2.addBox(-1.5F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F);
		this.leg5Part2.setPos(0.0F, f, 0.0F);
		this.leg5.addChild(this.leg5Part2);
		this.leg6Part2 = new ModelRenderer(this, 20, 48);
		this.leg6Part2.mirror = true;
		this.leg6Part2.addBox(-1.5F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F);
		this.leg6Part2.setPos(0.0F, f, 0.0F);
		this.leg6.addChild(this.leg6Part2);
		this.leg7Part2 = new ModelRenderer(this, 20, 48);
		this.leg7Part2.addBox(-1.5F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F);
		this.leg7Part2.setPos(0.0F, f, 0.0F);
		this.leg7.addChild(this.leg7Part2);
		this.leg8Part2 = new ModelRenderer(this, 20, 48);
		this.leg8Part2.mirror = true;
		this.leg8Part2.addBox(-1.5F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F);
		this.leg8Part2.setPos(0.0F, f, 0.0F);
		this.leg8.addChild(this.leg8Part2);

		this.bodyPart3 = new ModelRenderer(this, 32, 48);
		this.bodyPart3.mirror = true;
		this.bodyPart3.addBox(-2.0F, -4.5F, -1.0F, 4.0F, 5.0F, 3.0F);
		this.bodyPart3.setPos(-1.0F, -11.0F, -3.5F);
		this.body.addChild(this.bodyPart3);
		this.bodyPart4 = new ModelRenderer(this, 32, 48);
		this.bodyPart4.addBox(-2.0F, -4.5F, -1.0F, 4.0F, 5.0F, 3.0F);
		this.bodyPart4.setPos(3.0F, -11.5F, 3.5F);
		this.body.addChild(this.bodyPart4);
		this.bodyPart5 = new ModelRenderer(this, 32, 48);
		this.bodyPart5.mirror = true;
		this.bodyPart5.addBox(-2.0F, -4.5F, -1.0F, 4.0F, 5.0F, 3.0F);
		this.bodyPart5.setPos(-4.5F, -11.0F, 7.0F);
		this.body.addChild(this.bodyPart5);

		this.bodyPart6 = new ModelRenderer(this, 48, 48);
		this.bodyPart6.mirror = true;
		this.bodyPart6.addBox(-2.0F, -7.5F, -1.0F, 4.0F, 8.0F, 3.0F);
		this.bodyPart6.setPos(1.0F, -6.0F, 8.0F);
		this.body.addChild(this.bodyPart6);
		this.bodyPart7 = new ModelRenderer(this, 48, 48);
		this.bodyPart7.mirror = true;
		this.bodyPart7.addBox(-2.0F, -7.5F, -1.0F, 4.0F, 8.0F, 3.0F);
		this.bodyPart7.setPos(-4.0F, 0.0F, 8.0F);
		this.body.addChild(this.bodyPart7);
		this.bodyPart8 = new ModelRenderer(this, 48, 48);
		this.bodyPart8.addBox(-2.0F, -7.5F, -1.0F, 4.0F, 8.0F, 3.0F);
		this.bodyPart8.setPos(4.5F, 2.0F, 8.0F);
		this.body.addChild(this.bodyPart8);

		for (int i = 0; i < this.bodyPartsRightA.length; ++i)
		{
			this.bodyPartsRightA[i] = new ModelRenderer(this, 32, 0);
			this.bodyPartsRightA[i].addBox(-1.0F, -2.5F, -1.0F, 2.0F, 3.0F, 2.0F);
			this.bodyPartsRightA[i].setPos(7.5F, -9.0F - (float)i * 0.25F, -4.5F + (float)i * 3.0F);
			this.body.addChild(this.bodyPartsRightA[i]);

			this.bodyPartsRightB[i] = new ModelRenderer(this, 40, 0);
			this.bodyPartsRightB[i].addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F, -0.25F);
			this.bodyPartsRightB[i].setPos(0.0F, 0.0F, 0.0F);
			this.bodyPartsRightA[i].addChild(this.bodyPartsRightB[i]);
		}

		for (int i = 0; i < this.bodyPartsLeftA.length; ++i)
		{
			this.bodyPartsLeftA[i] = new ModelRenderer(this, 32, 0);
			this.bodyPartsLeftA[i].mirror = true;
			this.bodyPartsLeftA[i].addBox(-1.0F, -2.5F, -1.0F, 2.0F, 3.0F, 2.0F);
			this.bodyPartsLeftA[i].setPos(-7.5F, -9.0F - (float)i * 0.25F, -4.5F + (float)i * 3.0F);
			this.body.addChild(this.bodyPartsLeftA[i]);

			this.bodyPartsLeftB[i] = new ModelRenderer(this, 40, 0);
			this.bodyPartsLeftB[i].mirror = true;
			this.bodyPartsLeftB[i].addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F, -0.25F);
			this.bodyPartsLeftB[i].setPos(0.0F, 0.0F, 0.0F);
			this.bodyPartsLeftA[i].addChild(this.bodyPartsLeftB[i]);
		}
	}

	@Override
	public Iterable<ModelRenderer> parts()
	{
		return ImmutableList.of(this.head, this.body, this.leg1, this.leg2, this.leg3, this.leg4, this.leg5, this.leg6, this.leg7, this.leg8);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (entityIn.isCharging())
		{
			this.head.zRot = (ageInTicks * 1.2F) % (180.0F / (float)Math.PI) * (entityIn.getMainArm() == HandSide.RIGHT ? 1.0F : -1.0F);
			this.head.yRot = 0.0F;
			this.head.xRot = 0.0F;
		}
		else
		{
			this.head.zRot = 0.0F;
			this.head.yRot = netHeadYaw / (180.0F / (float)Math.PI);
			this.head.xRot = headPitch / (180.0F / (float)Math.PI);
		}

		float f = (float)Math.PI / 20.0F;
		float f1 = (float)Math.PI / 48.0F;
		float f2 = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount;
		float f3 = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.7F * limbSwingAmount;
		float f4 = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI / 2.0F) * 0.7F * limbSwingAmount;
		float f5 = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI * 3.0F / 2.0F) * 0.7F * limbSwingAmount;
		float f6 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.1F) * limbSwingAmount;
		float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.1F) * limbSwingAmount;
		float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI / 2.0F) * 0.1F) * limbSwingAmount;
		float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI * 3.0F / 2.0F) * 0.1F) * limbSwingAmount;
		float f10 = (float)Math.PI / 18.0F;
		float f11 = (float)Math.PI / 10.0F;

		this.leg1.xRot = -f - f2;
		this.leg2.xRot = -f + f2;
		this.leg3.xRot = -f1 - f3;
		this.leg4.xRot = -f1 + f3;
		this.leg5.xRot = f1 - f4;
		this.leg6.xRot = f1 + f4;
		this.leg7.xRot = f - f5;
		this.leg8.xRot = f + f5;
		this.leg1.zRot = f10 + f6;
		this.leg2.zRot = -f10 - f6;
		this.leg3.zRot = f11 + f7;
		this.leg4.zRot = -f11 - f7;
		this.leg5.zRot = f11 + f8;
		this.leg6.zRot = -f11 - f8;
		this.leg7.zRot = f10 + f9;
		this.leg8.zRot = -f10 - f9;

		this.bodyPart3.xRot = -((float)Math.PI / 9.0F);
		this.bodyPart4.xRot = -((float)Math.PI / 9.0F);
		this.bodyPart5.xRot = -((float)Math.PI / 9.0F);
		this.bodyPart3.xRot += MathHelper.sin(ageInTicks * 0.067F - (float)Math.PI / 11.0F) * 0.12F;
		this.bodyPart4.xRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 5.0F) * 0.12F;
		this.bodyPart5.xRot += MathHelper.sin(ageInTicks * 0.067F - (float)Math.PI * 2.0F / 9.0F) * 0.12F;
		this.bodyPart3.zRot = -((float)Math.PI / 36.0F);
		this.bodyPart4.zRot = (float)Math.PI / 30.0F;
		this.bodyPart5.zRot = -((float)Math.PI / 48.0F);
		this.bodyPart3.zRot += MathHelper.cos(ageInTicks * 0.045F - (float)Math.PI / 11.0F) * 0.033F;
		this.bodyPart4.zRot += MathHelper.cos(ageInTicks * 0.045F + (float)Math.PI * 2.0F / 5.0F) * 0.033F;
		this.bodyPart5.zRot += MathHelper.cos(ageInTicks * 0.045F - (float)Math.PI * 2.0F / 9.0F) * 0.033F;
		this.bodyPart6.xRot = -((float)Math.PI / 10.0F);
		this.bodyPart7.xRot = -((float)Math.PI / 10.0F);
		this.bodyPart8.xRot = -((float)Math.PI / 10.0F);
		this.bodyPart6.xRot += MathHelper.sin(ageInTicks * 0.067F - (float)Math.PI * 3.0F / 16.0F) * 0.12F;
		this.bodyPart7.xRot += MathHelper.sin(ageInTicks * 0.067F - (float)Math.PI * 4.0F / 9.0F) * 0.12F;
		this.bodyPart8.xRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 2.0F) * 0.12F;
		this.bodyPart6.zRot = -((float)Math.PI / 60.0F);
		this.bodyPart7.zRot = -((float)Math.PI / 36.0F);
		this.bodyPart8.zRot = (float)Math.PI / 48.0F;
		this.bodyPart6.zRot += MathHelper.cos(ageInTicks * 0.045F - (float)Math.PI * 3.0F / 16.0F) * 0.033F;
		this.bodyPart7.zRot += MathHelper.cos(ageInTicks * 0.045F - (float)Math.PI * 4.0F / 9.0F) * 0.033F;
		this.bodyPart8.zRot += MathHelper.cos(ageInTicks * 0.045F + (float)Math.PI / 2.0F) * 0.033F;

		for (int i = 0; i < this.bodyPartsRightA.length; ++i)
		{
			this.bodyPartsRightA[i].xRot = -((float)Math.PI * (float)(i - 2) / 15.0F);
			this.bodyPartsRightA[i].xRot += MathHelper.cos(ageInTicks * 0.06F + (float)Math.PI * (float)i / 3.0F) * 0.15F;
			this.bodyPartsRightA[i].zRot = ((float)Math.PI * 2.0F / 9.0F);
			this.bodyPartsRightA[i].zRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI * (float)(i + 4) / 3.0F) * 0.3F;
		}

		for (int i = 0; i < this.bodyPartsLeftA.length; ++i)
		{
			this.bodyPartsLeftA[i].xRot = -((float)Math.PI * (float)(i - 2) / 15.0F);
			this.bodyPartsLeftA[i].xRot += MathHelper.cos(ageInTicks * 0.06F + (float)Math.PI * (float)i / 3.0F) * 0.15F;
			this.bodyPartsLeftA[i].zRot = -((float)Math.PI * 2.0F / 9.0F);
			this.bodyPartsLeftA[i].zRot -= MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI * (float)(i + 4) / 3.0F) * 0.3F;
		}
	}
}