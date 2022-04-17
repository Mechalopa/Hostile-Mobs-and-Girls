package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.entity.StoneularEntity;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StoneularModel<T extends StoneularEntity> extends SegmentedModel<T>
{
	private final ModelRenderer head;
	private final ModelRenderer headPart1;
	private final ModelRenderer headPart2;
	private final ModelRenderer headPart3;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLegPart1;
	private final ModelRenderer leftLegPart1;
	private final ModelRenderer rightLegPart2;
	private final ModelRenderer leftLegPart2;
	private final ModelRenderer hornBase;
	private final ModelRenderer hornPart1;
	private final ModelRenderer hornPart2;
	private final ModelRenderer bristle0;
	private final ModelRenderer bristle1;
	private final ModelRenderer bristle2;
	private final ModelRenderer bristle3;
	private final ModelRenderer faceBristle;
	private float animationAmount;

	public StoneularModel()
	{
		this.texWidth = 128;
		this.texHeight = 128;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-8.0F, -6.0F, -8.0F, 16.0F, 14.0F, 5.0F);
		this.head.setPos(0.0F, 1.0F, 0.0F);

		this.headPart1 = new ModelRenderer(this, 0, 24);
		this.headPart1.addBox(-7.0F, -5.0F, -8.0F, 14.0F, 12.0F, 6.0F);
		this.headPart1.setPos(0.0F, 0.0F, 5.0F);
		this.head.addChild(this.headPart1);

		this.headPart2 = new ModelRenderer(this, 48, 0);
		this.headPart2.addBox(-8.0F, -6.0F, -8.0F, 16.0F, 14.0F, 5.0F);
		this.headPart2.setPos(0.0F, 0.0F, 6.0F);
		this.headPart1.addChild(this.headPart2);

		this.headPart3 = new ModelRenderer(this, 48, 24);
		this.headPart3.addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 1.0F);
		this.headPart3.setPos(0.0F, -2.0F, -8.0F);
		this.head.addChild(this.headPart3);

		this.rightLeg = new ModelRenderer(this, 0, 48);
		this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F);
		this.rightLeg.setPos(-4.0F, 8.0F, 0.0F);
		this.leftLeg = new ModelRenderer(this, 0, 48);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F);
		this.leftLeg.setPos(4.0F, 8.0F, 0.0F);

		this.rightLegPart1 = new ModelRenderer(this, 16, 48);
		this.rightLegPart1.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 3.0F, 6.0F);
		this.rightLegPart1.setPos(0.0F, 4.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart1);
		this.leftLegPart1 = new ModelRenderer(this, 40, 48);
		this.leftLegPart1.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 3.0F, 6.0F);
		this.leftLegPart1.setPos(0.0F, 4.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart1);

		this.rightLegPart2 = new ModelRenderer(this, 16, 60);
		this.rightLegPart2.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F);
		this.rightLegPart2.setPos(0.0F, 9.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart2);
		this.leftLegPart2 = new ModelRenderer(this, 40, 60);
		this.leftLegPart2.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 7.0F, 6.0F);
		this.leftLegPart2.setPos(0.0F, 9.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart2);

		this.hornBase = new ModelRenderer(this, 48, 32);
		this.hornBase.addBox(-2.0F, -2.0F, -5.0F, 4.0F, 4.0F, 5.0F);
		this.hornBase.setPos(0.0F, -1.0F, -6.0F);
		this.head.addChild(this.hornBase);

		this.hornPart1 = new ModelRenderer(this, 48, 42);
		this.hornPart1.addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 2.0F);
		this.hornPart1.setPos(0.0F, 0.0F, -5.0F);
		this.hornBase.addChild(this.hornPart1);

		this.hornPart2 = new ModelRenderer(this, 64, 42);
		this.hornPart2.addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 3.0F);
		this.hornPart2.setPos(0.0F, 0.0F, -2.0F);
		this.hornPart1.addChild(this.hornPart2);

		this.bristle0 = new ModelRenderer(this, 0, 80);
		this.bristle0.addBox(0.0F, 0.0F, -8.0F, 1.0F, 4.0F, 16.0F, 0.0F, true);
		this.bristle0.setPos(-6.0F, 8.0F, 0.0F);
		this.head.addChild(this.bristle0);
		this.bristle1 = new ModelRenderer(this, 0, 80);
		this.bristle1.addBox(-1.0F, 0.0F, -8.0F, 1.0F, 4.0F, 16.0F, 0.0F);
		this.bristle1.setPos(6.0F, 8.0F, 0.0F);
		this.head.addChild(this.bristle1);

		this.bristle2 = new ModelRenderer(this, 48, 80);
		this.bristle2.addBox(0.0F, 0.0F, -4.0F, 1.0F, 6.0F, 8.0F, 0.0F, true);
		this.bristle2.setPos(-7.0F, 6.0F, 0.0F);
		this.head.addChild(this.bristle2);
		this.bristle3 = new ModelRenderer(this, 48, 80);
		this.bristle3.addBox(-1.0F, 0.0F, -4.0F, 1.0F, 6.0F, 8.0F, 0.0F);
		this.bristle3.setPos(7.0F, 6.0F, 0.0F);
		this.head.addChild(this.bristle3);

		this.faceBristle = new ModelRenderer(this, 0, 104);
		this.faceBristle.addBox(-8.0F, 0.0F, 0.0F, 16.0F, 3.0F, 1.0F, 0.0F);
		this.faceBristle.setPos(0.0F, 8.0F, -8.0F);
		this.head.addChild(this.faceBristle);
	}

	@Override
	public Iterable<ModelRenderer> parts()
	{
		return ImmutableList.of(this.head, this.leftLeg, this.rightLeg);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.animationAmount = MathHelper.clamp(entityIn.getAnimationScale(partialTick), 0.0F, 1.0F);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (entityIn.isWalking())
		{
			this.head.xRot = headPitch * ((float)Math.PI / 180.0F);
			this.head.yRot = netHeadYaw * ((float)Math.PI / 180.0F);
		}
		else
		{
			this.head.xRot = 0.0F;
			this.head.yRot = 0.0F;
		}

		float f = 1.75F;
		float f1 = 1.0F - this.animationAmount;
		limbSwingAmount = Math.min(0.25F, limbSwingAmount) * f1;

		this.head.zRot = 0.1F * MathHelper.sin(limbSwing * f) * 4.0F * limbSwingAmount;
		this.head.y = this.animationAmount * 13.0F + 2.0F;
		this.head.y -= 2.0F * MathHelper.cos(limbSwing * f) * 2.0F * limbSwingAmount;
		this.leftLeg.xRot = MathHelper.sin(limbSwing * f * 0.5F) * 2.0F * limbSwingAmount;
		this.rightLeg.xRot = MathHelper.sin(limbSwing * f * 0.5F + (float)Math.PI) * 2.0F * limbSwingAmount;
		this.leftLeg.zRot = 0.17453292F * MathHelper.cos(limbSwing * f * 0.5F) * limbSwingAmount;
		this.rightLeg.zRot = 0.17453292F * MathHelper.cos(limbSwing * f * 0.5F + (float)Math.PI) * limbSwingAmount;
		float f2 = this.animationAmount * 3.0F + 8.0F;
		this.leftLeg.y = f2 + 2.0F * MathHelper.sin(limbSwing * f * 0.5F + (float)Math.PI) * 2.0F * limbSwingAmount;
		this.rightLeg.y = f2 + 2.0F * MathHelper.sin(limbSwing * f * 0.5F) * 2.0F * limbSwingAmount;
		float f3 = this.animationAmount * (float)Math.PI;
		this.leftLeg.zRot -= f3;
		this.rightLeg.zRot += f3;
		float f4 = (1.0F - Math.abs(this.animationAmount * 2.0F - 1.0F)) * 2.0F;
		this.leftLeg.x = 4.0F + f4;
		this.rightLeg.x = -4.0F - f4;

		this.hornBase.xRot = -((float)Math.PI / 12.0F) * f1;
		this.hornBase.yRot = 0.25F * MathHelper.cos(limbSwing * f * 0.5F) * limbSwingAmount;
		this.hornBase.z = -6.0F + this.animationAmount * 6.0F;
		this.hornPart1.z = -5.0F + this.animationAmount * 2.5F;
		this.hornPart2.z = -2.0F + this.animationAmount * 1.5F;

		this.bristle0.zRot = (float)Math.PI / 24.0F;
		this.bristle1.zRot = -((float)Math.PI / 24.0F);
		this.bristle2.zRot = (float)Math.PI / 27.0F;
		this.bristle3.zRot = -((float)Math.PI / 27.0F);
		float f5 = MathHelper.cos(limbSwing * 1.5F + (float)Math.PI) * limbSwingAmount;
		this.bristle0.zRot += f5 * 1.3F;
		this.bristle1.zRot += f5 * 1.3F;
		this.bristle2.zRot += f5 * 1.2F;
		this.bristle3.zRot += f5 * 1.2F;
		this.bristle0.zRot += 0.05F * MathHelper.sin(ageInTicks * 1.0F * 0.2F);
		this.bristle1.zRot += 0.05F * MathHelper.sin(ageInTicks * 1.0F * 0.2F);
		this.bristle2.zRot += 0.05F * MathHelper.sin(ageInTicks * 1.0F * -0.3F);
		this.bristle3.zRot += 0.05F * MathHelper.sin(ageInTicks * 1.0F * -0.3F);
		this.faceBristle.xRot = (float)Math.PI / 24.0F;
		this.faceBristle.xRot += f5 * 1.2F;
		this.faceBristle.xRot += 0.05F * MathHelper.sin(ageInTicks * 1.0F * 0.3F);
	}
}