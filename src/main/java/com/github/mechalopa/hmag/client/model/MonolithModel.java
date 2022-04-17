package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.entity.MonolithEntity;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonolithModel<T extends MonolithEntity> extends SegmentedModel<T>
{
	private final ModelRenderer head;
	private final ModelRenderer headPart1;
	private final ModelRenderer headPart2;
	private final ModelRenderer topRightPart;
	private final ModelRenderer topLeftPart;
	private final ModelRenderer bottomRightPart;
	private final ModelRenderer bottomLeftPart;

	public MonolithModel()
	{
		this.texHeight = 32;
		this.texWidth = 64;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-5.0F, -4.0F, -2.0F, 10.0F, 18.0F, 4.0F);
		this.head.setPos(0.0F, 4.0F, 0.0F);

		this.headPart1 = new ModelRenderer(this, 32, 0);
		this.headPart1.addBox(-2.5F, -2.5F, -2.0F, 5.0F, 5.0F, 4.0F, -0.1F);
		this.headPart1.setPos(0.0F, -4.0F, 0.0F);
		this.head.addChild(this.headPart1);
		this.headPart2 = new ModelRenderer(this, 32, 16);
		this.headPart2.addBox(-2.5F, -2.5F, -2.0F, 5.0F, 5.0F, 4.0F, -0.1F);
		this.headPart2.setPos(0.0F, 14.0F, 0.0F);
		this.head.addChild(this.headPart2);

		this.topRightPart = new ModelRenderer(this, 0, 24);
		this.topRightPart.addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F);
		this.topRightPart.setPos(-2.0F, -4.0F, 0.0F);
		this.head.addChild(this.topRightPart);
		this.topLeftPart = new ModelRenderer(this, 0, 24);
		this.topLeftPart.mirror = true;
		this.topLeftPart.addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F);
		this.topLeftPart.setPos(2.0F, -4.0F, 0.0F);
		this.head.addChild(this.topLeftPart);

		this.bottomRightPart = new ModelRenderer(this, 8, 24);
		this.bottomRightPart.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F);
		this.bottomRightPart.setPos(-2.0F, 14.0F, 0.0F);
		this.head.addChild(this.bottomRightPart);
		this.bottomLeftPart = new ModelRenderer(this, 8, 24);
		this.bottomLeftPart.mirror = true;
		this.bottomLeftPart.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F);
		this.bottomLeftPart.setPos(2.0F, 14.0F, 0.0F);
		this.head.addChild(this.bottomLeftPart);
	}

	@Override
	public Iterable<ModelRenderer> parts()
	{
		return ImmutableList.of(this.head);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.headPart1.zRot = (float)Math.PI / 4.0F;
		this.headPart2.zRot = (float)Math.PI / 4.0F;
		this.topRightPart.zRot = -((float)Math.PI / 32.0F);
		this.topLeftPart.zRot = (float)Math.PI / 32.0F;
		this.bottomRightPart.zRot = (float)Math.PI / 32.0F;
		this.bottomLeftPart.zRot = -((float)Math.PI / 32.0F);
	}
}