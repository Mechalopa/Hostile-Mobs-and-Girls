package com.github.mechalopa.hmag.client.model;

import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CursedDollModel<T extends MobEntity> extends AbstractAdvancedGirlModel<T>
{
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer skirt3;
	private ModelRenderer skirt4;
	private ModelRenderer skirt5;
	private ModelRenderer hairPart1;
	private ModelRenderer hairPart2;
	private ModelRenderer ribbon;
	private ModelRenderer ribbonRightPart;
	private ModelRenderer ribbonLeftPart;
	private ModelRenderer ribbonPartB;

	public CursedDollModel()
	{
		this(0.0F);
	}

	public CursedDollModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false);

		this.skirt1 = new ModelRenderer(this, 0, 40);
		this.skirt1.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 1.0F, 5.0F, modelSize);
		this.skirt1.setPos(0.0F, 11.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 48);
		this.skirt2.addBox(-5.0F, 0.0F, -3.5F, 10.0F, 1.0F, 7.0F, modelSize);
		this.skirt2.setPos(0.0F, 1.0F, 0.0F);
		this.skirt1.addChild(this.skirt2);
		this.skirt3 = new ModelRenderer(this, 0, 56);
		this.skirt3.addBox(-6.0F, 0.0F, -4.5F, 12.0F, 1.0F, 9.0F, modelSize);
		this.skirt3.setPos(0.0F, 1.0F, 0.0F);
		this.skirt2.addChild(this.skirt3);
		this.skirt4 = new ModelRenderer(this, 0, 68);
		this.skirt4.addBox(-7.0F, 0.0F, -5.5F, 14.0F, 1.0F, 11.0F, modelSize);
		this.skirt4.setPos(0.0F, 1.0F, 0.0F);
		this.skirt3.addChild(this.skirt4);
		this.skirt5 = new ModelRenderer(this, 0, 80);
		this.skirt5.addBox(-8.0F, 0.0F, -6.5F, 16.0F, 2.0F, 13.0F, modelSize);
		this.skirt5.setPos(0.0F, 1.0F, 0.0F);
		this.skirt4.addChild(this.skirt5);

		this.hairPart1 = new ModelRenderer(this, 40, 48);
		this.hairPart1.addBox(-4.0F, 0.0F, -1.0F, 8.0F, 4.0F, 1.0F, modelSize);
		this.hairPart1.setPos(0.0F, 0.0F, 4.0F);
		this.head.addChild(this.hairPart1);
		this.hairPart2 = new ModelRenderer(this, 42, 56);
		this.hairPart2.addBox(-4.0F, 0.0F, -1.0F, 8.0F, 8.0F, 1.0F, modelSize);
		this.hairPart2.setPos(0.0F, 4.0F, 0.0F);
		this.hairPart1.addChild(this.hairPart2);

		this.ribbon = new ModelRenderer(this, 16, 32);
		this.ribbon.addBox(-1.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, modelSize);
		this.ribbon.setPos(0.0F, -7.0F, 4.25F);
		this.head.addChild(this.ribbon);

		this.ribbonRightPart = new ModelRenderer(this, 0, 96);
		this.ribbonRightPart.addBox(0.0F, -3.5F, -1.0F, 6.0F, 7.0F, 1.0F, modelSize);
		this.ribbonRightPart.setPos(1.0F, 0.0F, 0.25F);
		this.ribbon.addChild(this.ribbonRightPart);
		this.ribbonLeftPart = new ModelRenderer(this, 16, 96);
		this.ribbonLeftPart.addBox(-6.0F, -3.5F, -1.0F, 6.0F, 7.0F, 1.0F, modelSize);
		this.ribbonLeftPart.setPos(-1.0F, 0.0F, 0.25F);
		this.ribbon.addChild(this.ribbonLeftPart);

		this.ribbonPartB = new ModelRenderer(this, 0, 104);
		this.ribbonPartB.addBox(-4.5F, 0.0F, -1.0F, 9.0F, 5.0F, 1.0F, modelSize);
		this.ribbonPartB.setPos(0.0F, 1.5F, 0.25F);
		this.ribbon.addChild(this.ribbonPartB);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		ModelHelper.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entityIn), this.attackTime, ageInTicks);

		float f = MathHelper.sin(this.attackTime * (float)Math.PI);
		float f1 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);

		this.rightLeg.xRot = (float)Math.PI / 10.0F;
		this.leftLeg.xRot = (float)Math.PI / 10.0F;
		this.rightLeg.xRot -= f * 1.2F - f1 * 0.4F;
		this.leftLeg.xRot -= f * 1.2F - f1 * 0.4F;
		this.rightLeg.xRot += MathHelper.sin(ageInTicks * 0.075F) * 0.06F;
		this.leftLeg.xRot -= MathHelper.sin(ageInTicks * 0.075F) * 0.06F;
		this.rightLeg.zRot = (float)Math.PI / 18.0F;
		this.leftLeg.zRot = -((float)Math.PI / 18.0F);

		this.hairPart1.xRot = (float)Math.PI / 24.0F;
		this.hairPart1.xRot += MathHelper.sin(ageInTicks * 0.075F) * 0.06F;
		this.hairPart2.xRot = (float)Math.PI / 24.0F;
		this.hairPart2.xRot += MathHelper.sin(ageInTicks * 0.075F + (float)Math.PI / 4.0F) * 0.06F;

		float f2 = MathHelper.sin(ageInTicks * 0.075F + (float)Math.PI / 2.0F);
		this.ribbon.xRot = (float)Math.PI / 18.0F;
		this.ribbonRightPart.yRot = -((float)Math.PI / 21.0F);
		this.ribbonLeftPart.yRot = (float)Math.PI / 21.0F;
		this.ribbonRightPart.yRot -= f2 * 0.05F;
		this.ribbonLeftPart.yRot += f2 * 0.05F;
		this.ribbonPartB.xRot = (float)Math.PI / 28.0F;
		this.ribbonPartB.xRot += f2 * 0.05F;
	}

	public boolean isAggressive(T entityIn)
	{
		return entityIn.isAggressive();
	}
}