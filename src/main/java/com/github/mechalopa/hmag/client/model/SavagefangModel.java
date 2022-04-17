package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.entity.SavagefangEntity;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SavagefangModel<T extends SavagefangEntity> extends SegmentedModel<T>
{
	private final ModelRenderer body;
	private final ModelRenderer topFin;
	private final ModelRenderer bottomFin;
	private final ModelRenderer head;
	private final ModelRenderer upperJaw;
	private final ModelRenderer upperJawPart;
	private final ModelRenderer upperFang;
	private final ModelRenderer lowerJaw;
	private final ModelRenderer lowerFang;
	private final ModelRenderer barbel0;
	private final ModelRenderer barbel1;
	private final ModelRenderer nose;
	private final ModelRenderer nose2;
	private final ModelRenderer sideFin0;
	private final ModelRenderer sideFin1;
	private final ModelRenderer tailFinBase;
	private final ModelRenderer tailFin;
	private float animationAmount;

	public SavagefangModel()
	{
		this.texWidth = 64;
		this.texHeight = 64;
		float f = 21.0F;

		this.body = new ModelRenderer(this, 0, 10);
		this.body.addBox(-2.5F, -4.0F, 0.0F, 5.0F, 8.0F, 4.0F);
		this.body.setPos(0.0F, f, -1.0F);

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-2.0F, -3.5F, -2.0F, 4.0F, 7.0F, 2.0F, 0.001F);
		this.head.setPos(0.0F, f, -1.0F);

		this.upperJaw = new ModelRenderer(this, 12, 0);
		this.upperJaw.addBox(-2.0F, -3.0F, -3.0F, 4.0F, 3.0F, 3.0F);
		this.upperJaw.setPos(0.0F, 0.0F, -1.5F);
		this.head.addChild(this.upperJaw);
		this.upperJawPart = new ModelRenderer(this, 12, 6);
		this.upperJawPart.addBox(-2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 1.0F);
		this.upperJawPart.setPos(0.0F, 0.0F, -3.0F);
		this.upperJaw.addChild(this.upperJawPart);
		this.upperFang = new ModelRenderer(this, 26, 0);
		this.upperFang.addBox(-2.0F, 0.0F, -4.0F, 4.0F, 1.0F, 4.0F, -0.001F);
		this.upperFang.setPos(0.0F, -0.5F, 0.0F);
		this.upperJaw.addChild(this.upperFang);

		this.lowerJaw = new ModelRenderer(this, 24, 8);
		this.lowerJaw.addBox(-2.5F, 0.0F, -4.75F, 5.0F, 2.0F, 5.0F);
		this.lowerJaw.setPos(0.0F, 0.0F, -1.75F);
		this.head.addChild(this.lowerJaw);
		this.lowerFang = new ModelRenderer(this, 24, 16);
		this.lowerFang.addBox(-2.5F, -2.0F, -4.75F, 5.0F, 2.0F, 5.0F, -0.001F);
		this.lowerFang.setPos(0.0F, 0.5F, 0.0F);
		this.lowerJaw.addChild(this.lowerFang);

		this.barbel0 = new ModelRenderer(this, 48, 0);
		this.barbel0.addBox(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 1.0F);
		this.barbel0.setPos(-1.0F, 2.0F, -2.75F);
		this.lowerJaw.addChild(this.barbel0);
		this.barbel1 = new ModelRenderer(this, 48, 2);
		this.barbel1.addBox(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 1.0F);
		this.barbel1.setPos(1.0F, 2.0F, -2.75F);
		this.lowerJaw.addChild(this.barbel1);

		this.nose = new ModelRenderer(this, 0, 22);
		this.nose.addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 1.0F);
		this.nose.setPos(0.0F, f, 3.0F);

		this.nose2 = new ModelRenderer(this, 10, 22);
		this.nose2.addBox(-2.0F, -3.0F, 0.0F, 4.0F, 5.0F, 1.0F);
		this.nose2.setPos(0.0F, 0.0F, 1.0F);
		this.nose.addChild(this.nose2);

		this.sideFin0 = new ModelRenderer(this, 40, 32);
		this.sideFin0.addBox(-4.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F);
		this.sideFin0.setPos(-2.5F, f, 0.0F);
		this.sideFin1 = new ModelRenderer(this, 40, 40);
		this.sideFin1.addBox(0.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F);
		this.sideFin1.setPos(2.5F, f, 0.0F);

		this.tailFinBase = new ModelRenderer(this, 48, 8);
		this.tailFinBase.addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 2.0F);
		this.tailFinBase.setPos(0.0F, f - 1.0F, 5.0F);
		this.tailFin = new ModelRenderer(this, 48, 12);
		this.tailFin.addBox(0.0F, -3.5F, 0.0F, 0.0F, 8.0F, 5.0F);
		this.tailFin.setPos(0.0F, 0.0F, 1.0F);
		this.tailFinBase.addChild(this.tailFin);

		this.topFin = new ModelRenderer(this, 0, 30);
		this.topFin.addBox(0.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F);
		this.topFin.setPos(0.0F, f - 4.0F, 2.0F);

		this.bottomFin = new ModelRenderer(this, 24, 24);
		this.bottomFin.addBox(0.0F, -1.0F, -2.0F, 0.0F, 3.0F, 4.0F);
		this.bottomFin.setPos(0.0F, f + 4.0F, 3.0F);
	}

	@Override
	public Iterable<ModelRenderer> parts()
	{
		return ImmutableList.of(this.body, this.head, this.nose, this.sideFin0, this.sideFin1, this.tailFinBase, this.topFin, this.bottomFin);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.animationAmount = MathHelper.clamp(entityIn.getAttackAnimationScale(partialTick), 0.0F, 1.0F);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		float f = 1.0F;

		if (!entityIn.isInWater())
		{
			f = 1.5F;
		}

		this.tailFinBase.yRot = -f * 0.45F * MathHelper.sin(0.6F * ageInTicks);
		this.sideFin0.zRot = -(((float)Math.PI / 5.0F) + (0.15F * MathHelper.cos(0.6F * ageInTicks)));
		this.sideFin1.zRot = -this.sideFin0.zRot;

		this.upperJaw.xRot = -0.12F * Math.abs(MathHelper.cos(0.45F * ageInTicks)) * this.animationAmount;
		this.lowerJaw.xRot = -((float)Math.PI / 30.0F);
		this.lowerJaw.xRot += 0.9F * Math.abs(MathHelper.cos(0.45F * ageInTicks)) * this.animationAmount;

		this.upperFang.y = -0.5F + (0.375F * this.animationAmount);
		this.lowerFang.y = 0.5F - (0.375F * this.animationAmount);

		this.barbel0.zRot = ((float)Math.PI / 15.0F) + (0.12F * MathHelper.cos(0.12F * ageInTicks));
		this.barbel1.zRot = -this.barbel0.zRot;
	}
}