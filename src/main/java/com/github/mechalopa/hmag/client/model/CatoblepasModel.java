package com.github.mechalopa.hmag.client.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CatoblepasModel<T extends Entity> extends SegmentedModel<T>
{
	private final ModelRenderer head;
	private final ModelRenderer neck;
	private final ModelRenderer body;
	private final ModelRenderer legBackRight;
	private final ModelRenderer legBackLeft;
	private final ModelRenderer legFrontRight;
	private final ModelRenderer legFrontLeft;

	private final ModelRenderer rightHorn1;
	private final ModelRenderer leftHorn1;
	private final ModelRenderer rightHorn2;
	private final ModelRenderer leftHorn2;

	public CatoblepasModel()
	{
		this.texHeight = 64;
		this.texWidth = 64;

		this.neck = new ModelRenderer(this, 24, 32);
		this.neck.addBox(-3.0F, -2.5F, -9.0F, 6.0F, 5.0F, 9.0F);
		this.neck.setPos(0.0F, 6.0F, -7.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F);
		this.head.setPos(0.0F, 1.0F, -8.0F);
		this.neck.addChild(this.head);
		this.body = new ModelRenderer(this, 18, 4);
		this.body.addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F);
		this.body.setPos(0.0F, 5.0F, 2.0F);
		this.legBackRight = new ModelRenderer(this, 0, 16);
		this.legBackRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
		this.legBackRight.setPos(-4.0F, 12.0F, 7.0F);
		this.legBackLeft = new ModelRenderer(this, 0, 16);
		this.legBackLeft.mirror = true;
		this.legBackLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
		this.legBackLeft.setPos(4.0F, 12.0F, 7.0F);
		this.legFrontRight = new ModelRenderer(this, 0, 16);
		this.legFrontRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
		this.legFrontRight.setPos(-4.0F, 12.0F, -6.0F);
		this.legFrontLeft = new ModelRenderer(this, 0, 16);
		this.legFrontLeft.mirror = true;
		this.legFrontLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
		this.legFrontLeft.setPos(4.0F, 12.0F, -6.0F);

		this.rightHorn1 = new ModelRenderer(this, 0, 32);
		this.rightHorn1.addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F);
		this.rightHorn1.setPos(-3.25F, -3.5F, -4.25F);
		this.head.addChild(this.rightHorn1);
		this.leftHorn1 = new ModelRenderer(this, 0, 32);
		this.leftHorn1.mirror = true;
		this.leftHorn1.addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F);
		this.leftHorn1.setPos(3.25F, -3.5F, -4.25F);
		this.head.addChild(this.leftHorn1);

		this.rightHorn2 = new ModelRenderer(this, 16, 32);
		this.rightHorn2.addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F);
		this.rightHorn2.setPos(0.0F, -5.5F, 0.0F);
		this.rightHorn1.addChild(this.rightHorn2);
		this.leftHorn2 = new ModelRenderer(this, 16, 32);
		this.leftHorn2.mirror = true;
		this.leftHorn2.addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F);
		this.leftHorn2.setPos(0.0F, -5.5F, 0.0F);
		this.leftHorn1.addChild(this.leftHorn2);
	}

	@Override
	public Iterable<ModelRenderer> parts()
	{
		return ImmutableList.of(this.neck, this.body, this.legBackRight, this.legBackLeft, this.legFrontRight, this.legFrontLeft);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.neck.xRot = (float)Math.PI / 9.0F;
		this.neck.xRot += MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI / 2.0F) * 0.25F * limbSwingAmount;
		this.head.xRot = -((float)Math.PI / 9.0F);
		this.head.xRot -= MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI / 2.0F) * 0.2F * limbSwingAmount;
		this.head.xRot += headPitch * ((float)Math.PI / 180.0F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180.0F);

		this.body.xRot = (float)Math.PI / 2.0F;
		this.legBackRight.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.legBackLeft.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.legFrontRight.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.legFrontLeft.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.rightHorn1.xRot = -((float)Math.PI / 15.0F);
		this.leftHorn1.xRot = -((float)Math.PI / 15.0F);
		this.rightHorn1.zRot = -((float)Math.PI * 5.0F / 12.0F);
		this.leftHorn1.zRot = (float)Math.PI * 5.0F / 12.0F;
		this.rightHorn2.zRot = (float)Math.PI / 11.0F;
		this.leftHorn2.zRot = -((float)Math.PI / 11.0F);
	}
}