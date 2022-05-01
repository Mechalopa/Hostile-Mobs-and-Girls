package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.entity.MeltyMonsterEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MeltyMonsterModel<T extends MeltyMonsterEntity> extends AbstractGirlModel<T>
{
	protected boolean isOverlay = false;
	private ModelRenderer rightArmPart;
	private ModelRenderer leftArmPart;
	private ModelRenderer rightLegPart;
	private ModelRenderer leftLegPart;
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer skirt3;
	private ModelRenderer rightHairAPart1;
	private ModelRenderer rightHairAPart2;
	private ModelRenderer rightHairAPart3;
	private ModelRenderer rightHairAPart4;
	private ModelRenderer rightHairAPart5;
	private ModelRenderer leftHairAPart1;
	private ModelRenderer leftHairAPart2;
	private ModelRenderer leftHairAPart3;
	private ModelRenderer leftHairAPart4;
	private ModelRenderer leftHairAPart5;
	private ModelRenderer rightHairBPart1;
	private ModelRenderer rightHairBPart2;
	private ModelRenderer rightHairBPart3;
	private ModelRenderer rightHairBPart4;
	private ModelRenderer rightHairBPart5;
	private ModelRenderer leftHairBPart1;
	private ModelRenderer leftHairBPart2;
	private ModelRenderer leftHairBPart3;
	private ModelRenderer leftHairBPart4;
	private ModelRenderer leftHairBPart5;
	private ModelRenderer ahoge;

	public MeltyMonsterModel()
	{
		this(0.0F);
	}

	public MeltyMonsterModel(float modelSize)
	{
		this(modelSize, false);
	}

	public MeltyMonsterModel(float modelSize, boolean isOverlay)
	{
		super(modelSize, 0.0F, 64, 128, false);
		this.isOverlay = isOverlay;

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F, 0.0F);
		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, modelSize);
		this.rightLeg.setPos(-1.75F, 12.0F, 0.0F);
		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, modelSize);
		this.leftLeg.setPos(1.75F, 12.0F, 0.0F);

		this.rightArmPart = new ModelRenderer(this, 16, 56);
		this.rightArmPart.addBox(-0.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, modelSize);
		this.rightArmPart.setPos(0.0F, 5.0F, 0.0F);
		this.rightArm.addChild(this.rightArmPart);
		this.leftArmPart = new ModelRenderer(this, 16, 56);
		this.leftArmPart.mirror = true;
		this.leftArmPart.addBox(-2.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, modelSize);
		this.leftArmPart.setPos(0.0F, 5.0F, 0.0F);
		this.leftArm.addChild(this.leftArmPart);

		this.rightLegPart = new ModelRenderer(this, 0, 56);
		this.rightLegPart.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, modelSize);
		this.rightLegPart.setPos(0.0F, 7.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart);
		this.leftLegPart = new ModelRenderer(this, 0, 56);
		this.leftLegPart.mirror = true;
		this.leftLegPart.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, modelSize);
		this.leftLegPart.setPos(0.0F, 7.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart);

		this.skirt1 = new ModelRenderer(this, 0, 38);
		this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSize);
		this.skirt1.setPos(0.0F, 11.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 44);
		this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 3.0F, 5.0F, modelSize);
		this.skirt2.setPos(0.0F, 12.0F, 0.0F);
		this.body.addChild(this.skirt2);

		this.rightHairAPart1 = new ModelRenderer(this, 32, 48);
		this.rightHairAPart1.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.rightHairAPart1.setPos(-3.75F, -7.0F, 0.5F);
		this.head.addChild(this.rightHairAPart1);
		this.rightHairAPart2 = new ModelRenderer(this, 32, 56);
		this.rightHairAPart2.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.rightHairAPart2.setPos(-1.0F, 0.5F, 1.5F);
		this.rightHairAPart1.addChild(this.rightHairAPart2);
		this.rightHairAPart3 = new ModelRenderer(this, 40, 56);
		this.rightHairAPart3.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, modelSize + 0.25F);
		this.rightHairAPart3.setPos(0.0F, 3.5F, 0.0F);
		this.rightHairAPart2.addChild(this.rightHairAPart3);
		this.rightHairAPart4 = new ModelRenderer(this, 48, 56);
		this.rightHairAPart4.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, modelSize);
		this.rightHairAPart4.setPos(0.0F, 6.0F, 0.0F);
		this.rightHairAPart3.addChild(this.rightHairAPart4);
		this.rightHairAPart5 = new ModelRenderer(this, 32, 64);
		this.rightHairAPart5.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, modelSize);
		this.rightHairAPart5.setPos(0.0F, 2.5F, 0.0F);
		this.rightHairAPart4.addChild(this.rightHairAPart5);

		this.leftHairAPart1 = new ModelRenderer(this, 32, 48);
		this.leftHairAPart1.mirror = true;
		this.leftHairAPart1.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.leftHairAPart1.setPos(3.75F, -7.0F, 0.5F);
		this.head.addChild(this.leftHairAPart1);
		this.leftHairAPart2 = new ModelRenderer(this, 32, 56);
		this.leftHairAPart2.mirror = true;
		this.leftHairAPart2.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.leftHairAPart2.setPos(1.0F, 0.5F, 1.5F);
		this.leftHairAPart1.addChild(this.leftHairAPart2);
		this.leftHairAPart3 = new ModelRenderer(this, 40, 56);
		this.leftHairAPart3.mirror = true;
		this.leftHairAPart3.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, modelSize + 0.25F);
		this.leftHairAPart3.setPos(0.0F, 3.5F, 0.0F);
		this.leftHairAPart2.addChild(this.leftHairAPart3);
		this.leftHairAPart4 = new ModelRenderer(this, 48, 56);
		this.leftHairAPart4.mirror = true;
		this.leftHairAPart4.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, modelSize);
		this.leftHairAPart4.setPos(0.0F, 6.0F, 0.0F);
		this.leftHairAPart3.addChild(this.leftHairAPart4);
		this.leftHairAPart5 = new ModelRenderer(this, 32, 64);
		this.leftHairAPart5.mirror = true;
		this.leftHairAPart5.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, modelSize);
		this.leftHairAPart5.setPos(0.0F, 2.5F, 0.0F);
		this.leftHairAPart4.addChild(this.leftHairAPart5);

		this.rightHairBPart1 = new ModelRenderer(this, 32, 48);
		this.rightHairBPart1.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.rightHairBPart1.setPos(-3.0F, -7.0F, 3.75F);
		this.head.addChild(this.rightHairBPart1);
		this.rightHairBPart2 = new ModelRenderer(this, 32, 56);
		this.rightHairBPart2.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.rightHairBPart2.setPos(0.5F, 0.75F, 1.5F);
		this.rightHairBPart1.addChild(this.rightHairBPart2);
		this.rightHairBPart3 = new ModelRenderer(this, 40, 56);
		this.rightHairBPart3.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, modelSize + 0.25F);
		this.rightHairBPart3.setPos(0.0F, 3.5F, 0.0F);
		this.rightHairBPart2.addChild(this.rightHairBPart3);
		this.rightHairBPart4 = new ModelRenderer(this, 48, 56);
		this.rightHairBPart4.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, modelSize);
		this.rightHairBPart4.setPos(0.0F, 6.0F, 0.0F);
		this.rightHairBPart3.addChild(this.rightHairBPart4);
		this.rightHairBPart5 = new ModelRenderer(this, 32, 64);
		this.rightHairBPart5.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, modelSize);
		this.rightHairBPart5.setPos(0.0F, 2.5F, 0.0F);
		this.rightHairBPart4.addChild(this.rightHairAPart5);

		this.leftHairBPart1 = new ModelRenderer(this, 32, 48);
		this.leftHairBPart1.mirror = true;
		this.leftHairBPart1.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.leftHairBPart1.setPos(3.0F, -7.0F, 3.75F);
		this.head.addChild(this.leftHairBPart1);
		this.leftHairBPart2 = new ModelRenderer(this, 32, 56);
		this.leftHairBPart2.mirror = true;
		this.leftHairBPart2.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.leftHairBPart2.setPos(-0.5F, 0.75F, 1.5F);
		this.leftHairBPart1.addChild(this.leftHairBPart2);
		this.leftHairBPart3 = new ModelRenderer(this, 40, 56);
		this.leftHairBPart3.mirror = true;
		this.leftHairBPart3.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, modelSize + 0.25F);
		this.leftHairBPart3.setPos(0.0F, 3.5F, 0.0F);
		this.leftHairBPart2.addChild(this.leftHairBPart3);
		this.leftHairBPart4 = new ModelRenderer(this, 48, 56);
		this.leftHairBPart4.mirror = true;
		this.leftHairBPart4.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, modelSize);
		this.leftHairBPart4.setPos(0.0F, 6.0F, 0.0F);
		this.leftHairBPart3.addChild(this.leftHairBPart4);
		this.leftHairBPart5 = new ModelRenderer(this, 32, 64);
		this.leftHairBPart5.mirror = true;
		this.leftHairBPart5.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, modelSize);
		this.leftHairBPart5.setPos(0.0F, 2.5F, 0.0F);
		this.leftHairBPart4.addChild(this.leftHairAPart5);

		if (!isOverlay)
		{
			this.skirt3 = new ModelRenderer(this, 0, 64);
			this.skirt3.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, modelSize);
			this.skirt3.setPos(0.0F, 3.0F, 0.0F);
			this.skirt2.addChild(this.skirt3);

			this.ahoge = new ModelRenderer(this, 16, 32);
			this.ahoge.addBox(-2.5F, -3.0F, 0.0F, 5.0F, 3.0F, 1.0F, modelSize - 0.25F);
			this.ahoge.setPos(0.0F, -7.75F, 0.0F);
			this.head.addChild(this.ahoge);
		}
		else
		{
			this.hat.visible = false;
		}
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.rightArm.zRot = (float)Math.PI / 8.0F;
		this.leftArm.zRot = -((float)Math.PI / 8.0F);
		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.12F) * 0.06F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.12F) * 0.06F;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;

			float f = 0.21F;

			if (this.rightLeg.xRot > f)
			{
				this.rightLeg.xRot = f;
			}

			if (this.leftLeg.xRot > f)
			{
				this.leftLeg.xRot = f;
			}

			if (this.rightLeg.xRot < -f)
			{
				this.rightLeg.xRot = -f;
			}

			if (this.leftLeg.xRot < -f)
			{
				this.leftLeg.xRot = -f;
			}
		}

		this.rightHairAPart1.zRot = ((float)Math.PI / 27.0F);
		this.leftHairAPart1.zRot = -((float)Math.PI / 27.0F);
		this.rightHairAPart1.zRot += MathHelper.sin(ageInTicks * 0.048F) * 0.05F;
		this.leftHairAPart1.zRot -= MathHelper.sin(ageInTicks * 0.048F) * 0.05F;
		this.rightHairAPart2.yRot = -((float)Math.PI / 8.0F);
		this.leftHairAPart2.yRot = ((float)Math.PI / 8.0F);
		this.rightHairAPart2.zRot = ((float)Math.PI / 30.0F);
		this.leftHairAPart2.zRot = -((float)Math.PI / 30.0F);
		this.rightHairAPart2.xRot = ((float)Math.PI / 27.0F);
		this.leftHairAPart2.xRot = ((float)Math.PI / 27.0F);
		this.rightHairAPart2.xRot += MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.045F;
		this.leftHairAPart2.xRot += MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.045F;
		this.rightHairAPart2.zRot = ((float)Math.PI / 24.0F);
		this.leftHairAPart2.zRot = -((float)Math.PI / 24.0F);
		this.rightHairAPart3.zRot = ((float)Math.PI / 30.0F);
		this.leftHairAPart3.zRot = -((float)Math.PI / 30.0F);
		this.rightHairAPart3.zRot -= MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.045F;
		this.leftHairAPart3.zRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.045F;
		this.rightHairAPart4.zRot = -((float)Math.PI / 27.0F);
		this.leftHairAPart4.zRot = ((float)Math.PI / 27.0F);
		this.rightHairAPart4.zRot -= MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.021F;
		this.leftHairAPart4.zRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.021F;
		this.rightHairAPart5.zRot = -((float)Math.PI / 30.0F);
		this.leftHairAPart5.zRot = ((float)Math.PI / 30.0F);
		this.rightHairAPart5.zRot -= MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.021F;
		this.leftHairAPart5.zRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.021F;

		this.rightHairBPart1.zRot = ((float)Math.PI / 30.0F);
		this.leftHairBPart1.zRot = -((float)Math.PI / 30.0F);
		this.rightHairBPart1.zRot -= MathHelper.sin(ageInTicks * 0.048F + (float)Math.PI / 3.0F) * 0.05F;
		this.leftHairBPart1.zRot += MathHelper.sin(ageInTicks * 0.048F + (float)Math.PI / 3.0F) * 0.05F;
		this.rightHairBPart2.yRot = ((float)Math.PI / 8.0F);
		this.leftHairBPart2.yRot = -((float)Math.PI / 8.0F);
		this.rightHairBPart2.zRot = ((float)Math.PI / 30.0F);
		this.leftHairBPart2.zRot = -((float)Math.PI / 30.0F);
		this.rightHairBPart2.xRot = ((float)Math.PI / 27.0F);
		this.leftHairBPart2.xRot = ((float)Math.PI / 27.0F);
		this.rightHairBPart2.xRot += MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.045F;
		this.leftHairBPart2.xRot += MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.045F;
		this.rightHairBPart2.zRot = ((float)Math.PI / 24.0F);
		this.leftHairBPart2.zRot = -((float)Math.PI / 24.0F);
		this.rightHairBPart3.zRot = ((float)Math.PI / 30.0F);
		this.leftHairBPart3.zRot = -((float)Math.PI / 30.0F);
		this.rightHairBPart3.zRot -= MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 3.0F) * 0.045F;
		this.leftHairBPart3.zRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 3.0F) * 0.045F;
		this.rightHairBPart4.zRot = -((float)Math.PI / 27.0F);
		this.leftHairBPart4.zRot = ((float)Math.PI / 27.0F);
		this.rightHairBPart4.zRot -= MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 3.0F) * 0.021F;
		this.leftHairBPart4.zRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 3.0F) * 0.021F;
		this.rightHairBPart5.zRot = -((float)Math.PI / 30.0F);
		this.leftHairBPart5.zRot = ((float)Math.PI / 30.0F);
		this.rightHairBPart5.zRot -= MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 3.0F) * 0.021F;
		this.leftHairBPart5.zRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI * 2.0F / 3.0F) * 0.021F;

		if (!this.isOverlay)
		{
			this.ahoge.xRot = -((float)Math.PI / 12.0F);
			this.ahoge.xRot += MathHelper.cos(ageInTicks * 0.03F + (float)Math.PI) * 0.09F;
		}
	}
}
