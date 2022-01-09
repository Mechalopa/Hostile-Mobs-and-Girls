package net.mechalopa.hmag.client.model;

import net.mechalopa.hmag.entity.SlimeGirlEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlimeGirlModel<T extends SlimeGirlEntity> extends AbstractGirlModel<T>
{
	protected boolean isOverlay = false;
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer rightHair;
	private ModelRenderer leftHair;
	private ModelRenderer rightHair2;
	private ModelRenderer leftHair2;
	private ModelRenderer rightHair3;
	private ModelRenderer leftHair3;
	private ModelRenderer rightHairCore;
	private ModelRenderer leftHairCore;
	private ModelRenderer hairPart;
	private ModelRenderer core;

	public SlimeGirlModel()
	{
		this(0.0F);
	}

	public SlimeGirlModel(float modelSize)
	{
		this(modelSize, false);
	}

	public SlimeGirlModel(float modelSize, boolean isOverlay)
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

		this.skirt1 = new ModelRenderer(this, 0, 38);
		this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSize);
		this.skirt1.setPos(0.0F, 11.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 44);
		this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 5.0F, 5.0F, modelSize);
		this.skirt2.setPos(0.0F, 12.0F, 0.0F);
		this.body.addChild(this.skirt2);

		this.rightHair = new ModelRenderer(this, 32, 48);
		this.rightHair.addBox(-2.0F, -1.5F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
		this.rightHair.setPos(-4.0F, -8.0F, 2.75F);
		this.head.addChild(this.rightHair);
		this.leftHair = new ModelRenderer(this, 32, 48);
		this.leftHair.mirror = true;
		this.leftHair.addBox(-1.0F, -1.5F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
		this.leftHair.setPos(4.0F, -8.0F, 2.75F);
		this.head.addChild(this.leftHair);

		this.rightHair2 = new ModelRenderer(this, 48, 48);
		this.rightHair2.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 8.0F, 2.0F, modelSize + 0.25F);
		this.rightHair2.setPos(-0.5F, 11.0F, 0.0F);
		this.rightHair.addChild(this.rightHair2);
		this.leftHair2 = new ModelRenderer(this, 48, 48);
		this.leftHair2.mirror = true;
		this.leftHair2.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 8.0F, 2.0F, modelSize + 0.25F);
		this.leftHair2.setPos(0.5F, 11.0F, 0.0F);
		this.leftHair.addChild(this.leftHair2);

		this.rightHair3 = new ModelRenderer(this, 56, 48);
		this.rightHair3.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.rightHair3.setPos(0.0F, 7.5F, 0.0F);
		this.rightHair2.addChild(this.rightHair3);
		this.leftHair3 = new ModelRenderer(this, 56, 48);
		this.leftHair3.mirror = true;
		this.leftHair3.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.leftHair3.setPos(0.0F, 7.5F, 0.0F);
		this.leftHair2.addChild(this.leftHair3);

		this.hairPart = new ModelRenderer(this, 0, 56);
		this.hairPart.addBox(-4.0F, 0.0F, -1.0F, 8.0F, 1.0F, 1.0F, modelSize);
		this.hairPart.setPos(0.0F, 0.0F, 4.0F);
		this.head.addChild(this.hairPart);

		if (!isOverlay)
		{
			this.rightHairCore = new ModelRenderer(this, 32, 64);
			this.rightHairCore.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, modelSize - 1.0F);
			this.rightHairCore.setPos(-0.5F, 1.0F, 0.0F);
			this.rightHair.addChild(this.rightHairCore);
			this.leftHairCore = new ModelRenderer(this, 32, 64);
			this.leftHairCore.mirror = true;
			this.leftHairCore.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, modelSize - 1.0F);
			this.leftHairCore.setPos(0.5F, 1.0F, 0.0F);
			this.leftHair.addChild(this.leftHairCore);

			this.core = new ModelRenderer(this, 0, 64);
			this.core.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, modelSize - 1.75F);
			this.core.setPos(0.0F, 4.0F, -0.5F);
			this.body.addChild(this.core);
		}
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.rightArm.zRot = (float)Math.PI / 10.0F;
		this.leftArm.zRot = -((float)Math.PI / 10.0F);
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

		this.rightHair.xRot = (float)Math.PI / 18.0F;
		this.leftHair.xRot = (float)Math.PI / 18.0F;
		this.rightHair.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.036F;
		this.leftHair.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.036F;
		this.rightHair.zRot = (float)Math.PI / 9.0F;
		this.leftHair.zRot = -((float)Math.PI / 9.0F);
		this.rightHair.zRot -= MathHelper.sin(ageInTicks * 0.09F) * 0.03F;
		this.leftHair.zRot += MathHelper.sin(ageInTicks * 0.09F) * 0.03F;
		this.rightHair2.zRot = (float)Math.PI / 16.0F;
		this.leftHair2.zRot = -((float)Math.PI / 16.0F);
		this.rightHair2.zRot -= MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.03F;
		this.leftHair2.zRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.03F;
		this.rightHair3.zRot = -((float)Math.PI / 5.0F);
		this.leftHair3.zRot = (float)Math.PI / 5.0F;
		this.rightHair3.zRot -= MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.015F;
		this.leftHair3.zRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.015F;

		this.hairPart.xRot = ((float)Math.PI / 12.0F);
		this.hairPart.xRot += MathHelper.sin(ageInTicks * 0.03F) * 0.03F;

		if (!this.isOverlay)
		{
			this.rightHairCore.xRot = MathHelper.sin(ageInTicks * 0.042F + (float)Math.PI / 4.0F) * 0.09F;
			this.leftHairCore.xRot = MathHelper.sin(ageInTicks * 0.042F) * 0.09F;
			this.rightHairCore.zRot = MathHelper.cos(ageInTicks * 0.042F + (float)Math.PI / 4.0F) * 0.09F;
			this.leftHairCore.zRot = MathHelper.cos(ageInTicks * 0.042F) * 0.09F;
			this.rightHairCore.y = 1.0F + (MathHelper.cos(ageInTicks * 0.075F + (float)Math.PI * 2.0F / 3.0F) * 0.5F);
			this.leftHairCore.y = 1.0F + (MathHelper.cos(ageInTicks * 0.075F - (float)Math.PI / 3.0F) * 0.5F);
			this.core.xRot = (float)Math.PI / 4.0F;
			this.core.xRot += MathHelper.sin(ageInTicks * 0.042F + (float)Math.PI * 2.0F / 3.0F) * 0.12F;
			this.core.zRot = MathHelper.cos(ageInTicks * 0.042F + (float)Math.PI * 2.0F / 3.0F) * 0.09F;
			this.core.y = 4.0F + (MathHelper.cos(ageInTicks * 0.075F + (float)Math.PI / 2.0F) * 0.24F);
		}
	}
}