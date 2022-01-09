package net.mechalopa.hmag.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DodomekiModel<T extends MobEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer rightArmPart;
	private ModelRenderer leftArmPart;
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer skirt3;
	private ModelRenderer hairPart1;
	private ModelRenderer hairPart2;
	private ModelRenderer headwearPart;

	public DodomekiModel()
	{
		this(0.0F);
	}

	public DodomekiModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false);

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(-1.0F, -2.0F, -1.5F, 3.0F, 4.0F, 3.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 24);
		this.leftArm.addBox(-2.0F, -2.0F, -1.5F, 3.0F, 4.0F, 3.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F, 0.0F);

		this.rightArmPart = new ModelRenderer(this, 32, 48);
		this.rightArmPart.addBox(-2.0F, 0.0F, -1.5F, 4.0F, 12.0F, 3.0F, modelSize);
		this.rightArmPart.setPos(0.5F, 1.5F, 0.125F);
		this.rightArm.addChild(this.rightArmPart);
		this.leftArmPart = new ModelRenderer(this, 48, 48);
		this.leftArmPart.addBox(-2.0F, 0.0F, -1.5F, 4.0F, 12.0F, 3.0F, modelSize);
		this.leftArmPart.setPos(-0.5F, 1.5F, 0.125F);
		this.leftArm.addChild(this.leftArmPart);

		this.leftLeg = new ModelRenderer(this, 0, 56);
		this.leftLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
		this.leftLeg.setPos(1.9F, 12.0F, 0.0F);

		this.skirt1 = new ModelRenderer(this, 0, 38);
		this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSize);
		this.skirt1.setPos(0.0F, 11.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 44);
		this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, modelSize);
		this.skirt2.setPos(0.0F, 12.0F, 0.0F);
		this.body.addChild(this.skirt2);
		this.skirt3 = new ModelRenderer(this, 32, 64);
		this.skirt3.addBox(-4.5F, 0.0F, -3.0F, 9.0F, 7.0F, 6.0F, modelSize);
		this.skirt3.setPos(0.0F, 2.0F, 0.0F);
		this.skirt2.addChild(this.skirt3);

		this.hairPart1 = new ModelRenderer(this, 0, 72);
		this.hairPart1.addBox(-4.0F, 0.0F, -1.0F, 8.0F, 4.0F, 1.0F, modelSize);
		this.hairPart1.setPos(0.0F, 0.0F, 4.0F);
		this.head.addChild(this.hairPart1);
		this.hairPart2 = new ModelRenderer(this, 0, 80);
		this.hairPart2.addBox(-4.0F, 0.0F, -1.0F, 8.0F, 8.0F, 1.0F, modelSize);
		this.hairPart2.setPos(0.0F, 4.0F, 0.0F);
		this.hairPart1.addChild(this.hairPart2);

		this.headwearPart = new ModelRenderer(this, 32, 80);
		this.headwearPart.addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, modelSize + 0.5F);
		this.headwearPart.setPos(0.0F, 3.0F, 0.0F);
		this.hat.addChild(this.headwearPart);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.zRot = 0.0F;

		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.head.zRot += MathHelper.cos(ageInTicks * 0.054F) * 0.06F;
		this.hat.copyFrom(this.head);

		this.rightArm.zRot = (float)Math.PI / 12.0F;
		this.leftArm.zRot = -((float)Math.PI / 12.0F);
		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.054F + (float)Math.PI * 4.0F / 3.0F) * 0.15F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.054F + (float)Math.PI / 3.0F) * 0.15F;
		this.rightArmPart.zRot = (float)Math.PI / 36.0F;
		this.leftArmPart.zRot = -((float)Math.PI / 36.0F);
		this.rightArmPart.zRot += MathHelper.cos(ageInTicks * 0.12F) * 0.021F;
		this.leftArmPart.zRot -= MathHelper.cos(ageInTicks * 0.12F) * 0.021F;
		this.rightArmPart.xRot = (float)Math.PI / 30.0F;
		this.leftArmPart.xRot = (float)Math.PI / 30.0F;
		this.rightArmPart.zRot += MathHelper.sin(ageInTicks * 0.12F) * 0.012F;
		this.leftArmPart.zRot -= MathHelper.sin(ageInTicks * 0.12F) * 0.012F;

		this.hairPart1.xRot = (float)Math.PI / 21.0F;
		this.hairPart1.xRot += MathHelper.sin(ageInTicks * 0.075F) * 0.06F;
		this.hairPart2.xRot = (float)Math.PI / 21.0F;
		this.hairPart2.xRot += MathHelper.sin(ageInTicks * 0.075F + (float)Math.PI / 4.0F) * 0.06F;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
			this.rightLeg.xRot *= 0.625F;
			this.leftLeg.xRot *= 0.625F;
		}
	}
}