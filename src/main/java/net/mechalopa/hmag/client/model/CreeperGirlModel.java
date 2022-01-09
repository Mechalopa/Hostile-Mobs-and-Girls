package net.mechalopa.hmag.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlModel<T extends CreeperEntity> extends AbstractGirlModel<T>
{
	protected boolean isChargeLayer = false;
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer headwearPart;

	public CreeperGirlModel()
	{
		this(0.0F);
	}

	public CreeperGirlModel(float modelSize)
	{
		this(modelSize, false, false);
	}

	public CreeperGirlModel(float modelSize, boolean isArmor)
	{
		this(modelSize, isArmor, false);
	}

	public CreeperGirlModel(float modelSize, boolean isArmor, boolean isLayer)
	{
		super(modelSize, 0.0F, isArmor);
		this.isChargeLayer = isLayer;

		if (isLayer)
		{
			this.body = new ModelRenderer(this, 16, 16);
			this.body.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 12.0F, 3.0F, modelSize);
			this.body.setPos(0.0F, 0.0F, 0.0F);
			this.rightArm = new ModelRenderer(this, 40, 16);
			this.rightArm.addBox(-1.0F, -2.0F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
			this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
			this.leftArm = new ModelRenderer(this, 40, 16);
			this.leftArm.mirror = true;
			this.leftArm.addBox(-2.0F, -2.0F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
			this.leftArm.setPos(5.0F, 2.0F, 0.0F);
			this.rightLeg = new ModelRenderer(this, 0, 16);
			this.rightLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
			this.rightLeg.setPos(-1.9F, 12.0F, 0.0F);
			this.leftLeg = new ModelRenderer(this, 0, 16);
			this.leftLeg.mirror = true;
			this.leftLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
			this.leftLeg.setPos(1.9F, 12.0F, 0.0F);
		}

		if (!isArmor && !isLayer)
		{
			this.skirt1 = new ModelRenderer(this, 0, 38);
			this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSize);
			this.skirt1.setPos(0.0F, 11.0F, 0.0F);
			this.body.addChild(this.skirt1);
			this.skirt2 = new ModelRenderer(this, 0, 44);
			this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 1.0F, 5.0F, modelSize);
			this.skirt2.setPos(0.0F, 12.0F, 0.0F);
			this.body.addChild(this.skirt2);

			this.headwearPart = new ModelRenderer(this, 32, 48);
			this.headwearPart.addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, modelSize + 0.5F);
			this.headwearPart.setPos(0.0F, 3.0F, 0.0F);
			this.hat.addChild(this.headwearPart);
		}
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float f = MathHelper.sin(this.attackTime * (float)Math.PI);
		float f1 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
		this.rightArm.zRot = 0.0F;
		this.leftArm.zRot = 0.0F;
		this.rightArm.yRot = -(0.1F - f * 0.6F);
		this.leftArm.yRot = 0.1F - f * 0.6F;
		this.rightArm.xRot = 0.0F;
		this.leftArm.xRot = 0.0F;
		this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
		this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.12F) * 0.096F + 0.12F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.12F) * 0.096F + 0.12F;
		this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.105F + (float)Math.PI * 0.5F) * 0.072F;
		this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.105F) * 0.072F;
	}
}