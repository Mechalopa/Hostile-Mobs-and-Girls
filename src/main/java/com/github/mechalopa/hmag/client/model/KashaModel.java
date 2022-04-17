package com.github.mechalopa.hmag.client.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KashaModel<T extends Entity> extends SegmentedModel<T>
{
	private ModelRenderer backLeftLeg;
	private ModelRenderer backRightLeg;
	private ModelRenderer frontLeftLeg;
	private ModelRenderer frontRightLeg;
	private ModelRenderer tail;
	private ModelRenderer tail2A;
	private ModelRenderer tail2B;
	private ModelRenderer head;
	private ModelRenderer body;
	public int state = 1;

	public KashaModel()
	{
		this.head = new ModelRenderer(this);
		this.head.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5, 0.0F, 0, 0);
		this.head.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2, 0.0F, 0, 24);
		this.head.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2, 0.0F, 0, 10);
		this.head.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2, 0.0F, 6, 10);
		this.head.setPos(0.0F, 15.0F, -9.0F);
		this.body = new ModelRenderer(this, 20, 0);
		this.body.addBox(-2.0F, 3.0F, -8.0F, 4.0F, 16.0F, 6.0F);
		this.body.setPos(0.0F, 12.0F, -10.0F);
		this.tail = new ModelRenderer(this, 0, 15);
		this.tail.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F);
		this.tail.xRot = 0.9F;
		this.tail.setPos(0.0F, 15.0F, 8.0F);
		this.tail2A = new ModelRenderer(this, 4, 15);
		this.tail2A.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F);
		this.tail2A.setPos(0.0F, 20.0F, 14.0F);
		this.tail2B = new ModelRenderer(this, 4, 15);
		this.tail2B.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F);
		this.tail2B.setPos(0.0F, 20.0F, 14.0F);
		this.backLeftLeg = new ModelRenderer(this, 8, 13);
		this.backLeftLeg.addBox(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F);
		this.backLeftLeg.setPos(1.1F, 18.0F, 5.0F);
		this.backRightLeg = new ModelRenderer(this, 8, 13);
		this.backRightLeg.addBox(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F);
		this.backRightLeg.setPos(-1.1F, 18.0F, 5.0F);
		this.frontLeftLeg = new ModelRenderer(this, 40, 0);
		this.frontLeftLeg.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F);
		this.frontLeftLeg.setPos(1.2F, 14.1F, -5.0F);
		this.frontRightLeg = new ModelRenderer(this, 40, 0);
		this.frontRightLeg.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F);
		this.frontRightLeg.setPos(-1.2F, 14.1F, -5.0F);
	}

	@Override
	public Iterable<ModelRenderer> parts()
	{
		return ImmutableList.of(this.head, this.body, this.backLeftLeg, this.backRightLeg, this.frontLeftLeg, this.frontRightLeg, this.tail, this.tail2A, this.tail2B);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.xRot = headPitch * ((float)Math.PI / 180.0F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180.0F);
		this.body.xRot = ((float)Math.PI / 2.0F);
		float f = 0.6662F;
		float f1 = 1.7278761F;
		float f2 = 0.47123894F;

		if (this.state == 2)
		{
			this.backLeftLeg.xRot = MathHelper.cos(limbSwing * f) * limbSwingAmount;
			this.backRightLeg.xRot = MathHelper.cos(limbSwing * f + 0.3F) * limbSwingAmount;
			this.frontLeftLeg.xRot = MathHelper.cos(limbSwing * f + (float)Math.PI + 0.3F) * limbSwingAmount;
			this.frontRightLeg.xRot = MathHelper.cos(limbSwing * f + (float)Math.PI) * limbSwingAmount;
			this.tail2A.xRot = f1 + ((float)Math.PI / 10.0F) * MathHelper.cos(limbSwing) * limbSwingAmount;
			this.tail2B.xRot = f1 + ((float)Math.PI / 10.0F) * MathHelper.cos(limbSwing + (float)Math.PI) * limbSwingAmount;
		}
		else
		{
			this.backLeftLeg.xRot = MathHelper.cos(limbSwing * f) * limbSwingAmount;
			this.backRightLeg.xRot = MathHelper.cos(limbSwing * f + (float)Math.PI) * limbSwingAmount;
			this.frontLeftLeg.xRot = MathHelper.cos(limbSwing * f + (float)Math.PI) * limbSwingAmount;
			this.frontRightLeg.xRot = MathHelper.cos(limbSwing * f) * limbSwingAmount;

			if (this.state == 1)
			{
				this.tail2A.xRot = f1 + ((float)Math.PI / 4.0F) * MathHelper.cos(limbSwing) * limbSwingAmount;
				this.tail2B.xRot = f1 + ((float)Math.PI / 4.0F) * MathHelper.cos(limbSwing + (float)Math.PI) * limbSwingAmount;
			}
			else
			{
				this.tail2A.xRot = f1 + f2 * MathHelper.cos(limbSwing) * limbSwingAmount;
				this.tail2B.xRot = f1 + f2 * MathHelper.cos(limbSwing + (float)Math.PI) * limbSwingAmount;
			}
		}
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		this.body.y = 12.0F;
		this.body.z = -10.0F;
		this.head.y = 15.0F;
		this.head.z = -9.0F;
		this.tail.y = 15.0F;
		this.tail.z = 8.0F;
		this.tail2A.y = 20.0F;
		this.tail2B.z = 14.0F;
		this.tail2A.y = 20.0F;
		this.tail2B.z = 14.0F;
		this.frontLeftLeg.y = 14.1F;
		this.frontLeftLeg.z = -5.0F;
		this.frontRightLeg.y = 14.1F;
		this.frontRightLeg.z = -5.0F;
		this.backLeftLeg.y = 18.0F;
		this.backLeftLeg.z = 5.0F;
		this.backRightLeg.y = 18.0F;
		this.backRightLeg.z = 5.0F;
		this.tail.xRot = 0.9F;

		if (entityIn.isCrouching())
		{
			++this.body.y;
			this.head.y += 2.0F;
			++this.tail.y;
			this.tail2A.y += -4.0F;
			this.tail2A.z += 2.0F;
			this.tail2B.y += -4.0F;
			this.tail2B.z += 2.0F;
			this.tail.xRot = ((float)Math.PI / 2.0F);
			this.tail2A.xRot = ((float)Math.PI / 2.0F);
			this.tail2B.xRot = ((float)Math.PI / 2.0F);
			this.state = 0;
		}
		else if (entityIn.isSprinting())
		{
			this.tail2A.y = this.tail.y;
			this.tail2A.z += 2.0F;
			this.tail2B.y = this.tail.y;
			this.tail2B.z += 2.0F;
			this.tail.xRot = ((float)Math.PI / 2.0F);
			this.tail2A.xRot = ((float)Math.PI / 2.0F);
			this.tail2B.xRot = ((float)Math.PI / 2.0F);
			this.state = 2;
		}
		else
		{
			this.state = 1;
		}
	}
}