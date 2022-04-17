package com.github.mechalopa.hmag.client.model;

import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlModel<T extends ZombieEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer clothPart;
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer tailHair1;
	private ModelRenderer tailHair2;
	private ModelRenderer tailHair3;
	private ModelRenderer tailHair4;
	private ModelRenderer ahoge;

	public ZombieGirlModel()
	{
		this(0.0F);
	}

	public ZombieGirlModel(float modelSize)
	{
		this(modelSize, false);
	}

	public ZombieGirlModel(float modelSize, boolean isArmor)
	{
		super(modelSize, 0.0F, isArmor);

		if (!isArmor)
		{
			this.clothPart = new ModelRenderer(this, 32, 48);
			this.clothPart.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 3.0F, 3.0F, modelSize);
			this.clothPart.setPos(0.0F, 7.0F, 0.0F);
			this.body.addChild(this.clothPart);
			this.skirt1 = new ModelRenderer(this, 0, 38);
			this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSize);
			this.skirt1.setPos(0.0F, 11.0F, 0.0F);
			this.body.addChild(this.skirt1);
			this.skirt2 = new ModelRenderer(this, 0, 44);
			this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 6.0F, 5.0F, modelSize);
			this.skirt2.setPos(0.0F, 12.0F, 0.0F);
			this.body.addChild(this.skirt2);
			this.tailHair1 = new ModelRenderer(this, 0, 56);
			this.tailHair1.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 4.0F, 2.0F, modelSize);
			this.tailHair1.setPos(0.0F, -7.0F, 4.0F);
			this.head.addChild(this.tailHair1);
			this.tailHair2 = new ModelRenderer(this, 8, 56);
			this.tailHair2.addBox(-1.0F, -0.5F, 0.0F, 2.0F, 5.0F, 2.0F, modelSize + 0.001F);
			this.tailHair2.setPos(0.0F, 3.0F, 0.0F);
			this.tailHair1.addChild(this.tailHair2);
			this.tailHair3 = new ModelRenderer(this, 8, 56);
			this.tailHair3.addBox(-1.0F, -0.5F, 0.0F, 2.0F, 5.0F, 2.0F, modelSize - 0.25F);
			this.tailHair3.setPos(0.0F, 4.25F, 0.0F);
			this.tailHair2.addChild(this.tailHair3);
			this.tailHair4 = new ModelRenderer(this, 16, 56);
			this.tailHair4.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 4.0F, 1.0F, modelSize);
			this.tailHair4.setPos(0.0F, 4.5F, 0.5F);
			this.tailHair3.addChild(this.tailHair4);
			this.ahoge = new ModelRenderer(this, 16, 32);
			this.ahoge.addBox(-2.5F, -4.0F, 0.0F, 5.0F, 4.0F, 1.0F, modelSize - 0.25F);
			this.ahoge.setPos(0.0F, -7.75F, 0.0F);
			this.head.addChild(this.ahoge);
		}
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		ModelHelper.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entityIn), this.attackTime, ageInTicks);

		if (!this.isArmor)
		{
			if (this.riding)
			{
				this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
			}
			else
			{
				this.skirt2.xRot = this.body.xRot;
			}

			this.tailHair1.xRot = (float)Math.PI / 8.0F;
			this.tailHair1.xRot += MathHelper.sin(ageInTicks * 0.03F) * 0.09F;
			this.tailHair2.xRot = -((float)Math.PI / 18.0F);
			this.tailHair2.xRot += MathHelper.sin(ageInTicks * 0.03F + (float)Math.PI / 12.0F) * 0.045F;
			this.tailHair3.xRot = -((float)Math.PI / 18.0F);
			this.tailHair3.xRot += MathHelper.sin(ageInTicks * 0.03F + (float)Math.PI / 6.0F) * 0.03F;
			this.tailHair4.xRot = -((float)Math.PI / 24.0F);
			this.tailHair4.xRot += MathHelper.sin(ageInTicks * 0.03F + (float)Math.PI / 4.0F) * 0.015F;

			this.ahoge.xRot = -((float)Math.PI / 12.0F);
			this.ahoge.xRot += MathHelper.cos(ageInTicks * 0.03F + (float)Math.PI) * 0.09F;
		}
	}

	public boolean isAggressive(T entityIn)
	{
		return entityIn.isAggressive();
	}
}