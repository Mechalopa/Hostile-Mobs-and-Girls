package com.github.mechalopa.hmag.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DullahanModel<T extends MobEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer neck1;
	private ModelRenderer neck2;
	private ModelRenderer cloak;

	public DullahanModel()
	{
		this(0.0F);
	}

	public DullahanModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false);

		this.skirt1 = new ModelRenderer(this, 0, 38);
		this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSize);
		this.skirt1.setPos(0.0F, 11.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 44);
		this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 5.0F, 5.0F, modelSize);
		this.skirt2.setPos(0.0F, 12.0F, 0.0F);
		this.body.addChild(this.skirt2);
		this.neck1 = new ModelRenderer(this, 0, 56);
		this.neck1.addBox(-3.0F, 0.0F, -2.0F, 6.0F, 1.0F, 4.0F, modelSize);
		this.neck1.setPos(0.0F, -1.0F, 0.0F);
		this.body.addChild(this.neck1);
		this.neck2 = new ModelRenderer(this, 0, 64);
		this.neck2.addBox(-3.5F, 0.0F, -3.0F, 7.0F, 3.0F, 6.0F, modelSize);
		this.neck2.setPos(0.0F, -3.0F, 0.0F);
		this.neck1.addChild(this.neck2);
		this.cloak = new ModelRenderer(this, 32, 56);
		this.cloak.addBox(-4.5F, 0.0F, -1.0F, 9.0F, 15.0F, 1.0F, modelSize);
		this.cloak.setPos(0.0F, -0.5F, 2.0F);
		this.body.addChild(this.cloak);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float f = MathHelper.sin(this.attackTime * (float)Math.PI);
		float f1 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
		boolean flag = entityIn.getMainArm() == HandSide.LEFT;

		if (flag)
		{
			this.rightArm.zRot = 0.0F;
			this.rightArm.yRot = -(0.1F - f * 0.6F);
			this.rightArm.xRot = (float)Math.PI / 12.0F;
			this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.rightArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		}
		else
		{
			this.leftArm.zRot = 0.0F;
			this.leftArm.yRot = 0.1F - f * 0.6F;
			this.leftArm.xRot = (float)Math.PI / 12.0F;
			this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		}

		float f2 = 1.0F;
		this.head.x = f2 * 7.0F * (flag ? -1.0F : 1.0F);
		this.head.y = f2 * 17.5F;
		this.head.z = f2 * 1.5F;
		this.hat.copyFrom(this.head);

		this.cloak.xRot = (float)Math.PI / 6.0F;
		this.cloak.xRot -= MathHelper.cos(limbSwing * 0.45F) * 2.0F * limbSwingAmount * 0.21F;
		this.cloak.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.036F;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
		}
	}
}