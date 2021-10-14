package hmag.client.model;

import hmag.entity.AbstractFlyingMonsterEntity;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BansheeModel<T extends AbstractFlyingMonsterEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer skirt3;
	private ModelRenderer skirt4;
	private ModelRenderer bodyRibbon1;
	private ModelRenderer bodyRibbon2;
	private ModelRenderer hairPart1;
	private ModelRenderer hairPart2;
	private ModelRenderer rightHairPart1;
	private ModelRenderer rightHairPart2;
	private ModelRenderer rightHairPart3;
	private ModelRenderer rightHairPart4;
	private ModelRenderer rightHairPart5;
	private ModelRenderer leftHairPart1;
	private ModelRenderer leftHairPart2;
	private ModelRenderer leftHairPart3;
	private ModelRenderer leftHairPart4;
	private ModelRenderer leftHairPart5;
	private ModelRenderer ribbonRightPart1;
	private ModelRenderer ribbonRightPart2;
	private ModelRenderer ribbonLeftPart1;
	private ModelRenderer ribbonLeftPart2;
	private ModelRenderer ahoge;

	public BansheeModel()
	{
		this(0.0F);
	}

	public BansheeModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false);

		this.bodyPart1 = new ModelRenderer(this, 32, 32);
		this.bodyPart1.addBox(-2.5F, 0.0F, -1.0F, 5, 2, 2, modelSize);
		this.bodyPart1.setPos(0.0F, 7.0F, 0.0F);
		this.body.addChild(this.bodyPart1);
		this.bodyPart2 = new ModelRenderer(this, 32, 40);
		this.bodyPart2.addBox(-3.0F, 0.0F, -1.5F, 6, 1, 3, modelSize);
		this.bodyPart2.setPos(0.0F, 1.5F, 0.0F);
		this.bodyPart1.addChild(this.bodyPart2);

		this.skirt1 = new ModelRenderer(this, 0, 40);
		this.skirt1.addBox(-4.0F, 0.0F, -2.5F, 8, 2, 5, modelSize);
		this.skirt1.setPos(0.0F, 9.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 48);
		this.skirt2.addBox(-4.5F, 0.0F, -3.5F, 9, 2, 7, modelSize);
		this.skirt2.setPos(0.0F, 2.0F, 0.0F);
		this.skirt1.addChild(this.skirt2);
		this.skirt3 = new ModelRenderer(this, 0, 64);
		this.skirt3.addBox(-5.0F, 0.0F, -4.5F, 10, 2, 9, modelSize);
		this.skirt3.setPos(0.0F, 2.0F, 0.0F);
		this.skirt2.addChild(this.skirt3);
		this.skirt4 = new ModelRenderer(this, 0, 80);
		this.skirt4.addBox(-5.5F, 0.0F, -5.5F, 11, 4, 11, modelSize);
		this.skirt4.setPos(0.0F, 2.0F, 0.0F);
		this.skirt3.addChild(this.skirt4);

		this.bodyRibbon1 = new ModelRenderer(this, 16, 32);
		this.bodyRibbon1.addBox(-2.5F, -2.5F, -1.0F, 3, 5, 1, modelSize - 0.25F);
		this.bodyRibbon1.setPos(0.0F, 0.0F, -2.0F);
		this.skirt1.addChild(this.bodyRibbon1);
		this.bodyRibbon2 = new ModelRenderer(this, 24, 32);
		this.bodyRibbon2.addBox(-0.5F, -2.5F, -1.0F, 3, 5, 1, modelSize - 0.25F);
		this.bodyRibbon2.setPos(0.0F, 0.0F, -1.75F);
		this.skirt1.addChild(this.bodyRibbon2);

		this.hairPart1 = new ModelRenderer(this, 32, 96);
		this.hairPart1.addBox(-4.0F, 0.0F, -1.0F, 8, 5, 1, modelSize);
		this.hairPart1.setPos(0.0F, 0.0F, 4.0F);
		this.head.addChild(this.hairPart1);
		this.hairPart2 = new ModelRenderer(this, 32, 104);
		this.hairPart2.addBox(-6.0F, 0.0F, -1.0F, 12, 10, 1, modelSize);
		this.hairPart2.setPos(0.0F, 5.0F, 0.0F);
		this.hairPart1.addChild(this.hairPart2);

		this.rightHairPart1 = new ModelRenderer(this, 40, 56);
		this.rightHairPart1.addBox(-1.0F, -1.0F, -1.0F, 2, 3, 2, modelSize);
		this.rightHairPart1.setPos(-3.75F, -7.0F, -1.0F);
		this.head.addChild(this.rightHairPart1);
		this.leftHairPart1 = new ModelRenderer(this, 40, 56);
		this.leftHairPart1.addBox(-1.0F, -1.0F, -1.0F, 2, 3, 2, modelSize);
		this.leftHairPart1.setPos(3.75F, -7.0F, -1.0F);
		this.head.addChild(this.leftHairPart1);

		this.rightHairPart2 = new ModelRenderer(this, 48, 64);
		this.rightHairPart2.addBox(-1.0F, -1.5F, -1.0F, 2, 5, 2, modelSize);
		this.rightHairPart2.setPos(-1.5F, 1.0F, 1.5F);
		this.rightHairPart1.addChild(this.rightHairPart2);
		this.rightHairPart3 = new ModelRenderer(this, 40, 64);
		this.rightHairPart3.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, modelSize + 0.25F);
		this.rightHairPart3.setPos(0.0F, 3.5F, 0.0F);
		this.rightHairPart2.addChild(this.rightHairPart3);
		this.rightHairPart4 = new ModelRenderer(this, 48, 80);
		this.rightHairPart4.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, modelSize);
		this.rightHairPart4.setPos(0.0F, 6.0F, 0.0F);
		this.rightHairPart3.addChild(this.rightHairPart4);
		this.rightHairPart5 = new ModelRenderer(this, 48, 72);
		this.rightHairPart5.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, modelSize);
		this.rightHairPart5.setPos(0.0F, 4.0F, 0.0F);
		this.rightHairPart4.addChild(this.rightHairPart5);

		this.leftHairPart2 = new ModelRenderer(this, 48, 64);
		this.leftHairPart2.mirror = true;
		this.leftHairPart2.addBox(-1.0F, -1.5F, -1.0F, 2, 5, 2, modelSize);
		this.leftHairPart2.setPos(1.5F, 1.0F, 1.5F);
		this.leftHairPart1.addChild(this.leftHairPart2);
		this.leftHairPart3 = new ModelRenderer(this, 40, 64);
		this.leftHairPart3.mirror = true;
		this.leftHairPart3.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, modelSize + 0.25F);
		this.leftHairPart3.setPos(0.0F, 3.5F, 0.0F);
		this.leftHairPart2.addChild(this.leftHairPart3);
		this.leftHairPart4 = new ModelRenderer(this, 48, 80);
		this.leftHairPart4.mirror = true;
		this.leftHairPart4.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, modelSize);
		this.leftHairPart4.setPos(0.0F, 6.0F, 0.0F);
		this.leftHairPart3.addChild(this.leftHairPart4);
		this.leftHairPart5 = new ModelRenderer(this, 48, 72);
		this.leftHairPart5.mirror = true;
		this.leftHairPart5.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, modelSize);
		this.leftHairPart5.setPos(0.0F, 4.0F, 0.0F);
		this.leftHairPart4.addChild(this.leftHairPart5);

		this.ribbonRightPart1 = new ModelRenderer(this, 16, 32);
		this.ribbonRightPart1.addBox(-2.5F, -2.5F, -1.0F, 3, 5, 1, modelSize - 0.25F);
		this.ribbonRightPart1.setPos(-0.5F, -0.5F, 0.0F);
		this.rightHairPart2.addChild(this.ribbonRightPart1);
		this.ribbonRightPart2 = new ModelRenderer(this, 24, 32);
		this.ribbonRightPart2.addBox(-0.5F, -2.5F, -1.0F, 3, 5, 1, modelSize - 0.25F);
		this.ribbonRightPart2.setPos(-0.5F, -0.5F, 0.0F);
		this.rightHairPart2.addChild(this.ribbonRightPart2);

		this.ribbonLeftPart1 = new ModelRenderer(this, 16, 32);
		this.ribbonLeftPart1.addBox(-2.5F, -2.5F, -1.0F, 3, 5, 1, modelSize - 0.25F);
		this.ribbonLeftPart1.setPos(0.5F, -0.5F, 0.0F);
		this.leftHairPart2.addChild(this.ribbonLeftPart1);
		this.ribbonLeftPart2 = new ModelRenderer(this, 24, 32);
		this.ribbonLeftPart2.addBox(-0.5F, -2.5F, -1.0F, 3, 5, 1, modelSize - 0.25F);
		this.ribbonLeftPart2.setPos(0.5F, -0.5F, 0.0F);
		this.leftHairPart2.addChild(this.ribbonLeftPart2);

		this.ahoge = new ModelRenderer(this, 0, 96);
		this.ahoge.addBox(-2.5F, -4.0F, 0.0F, 5, 4, 1, modelSize - 0.25F);
		this.ahoge.setPos(0.0F, -7.75F, 0.0F);
		this.head.addChild(this.ahoge);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (entityIn.getAttackPhase() == 0)
		{
			this.head.xRot = (float)Math.PI / 10.0F;
			this.hat.xRot = (float)Math.PI / 10.0F;
		}
		else
		{
			this.head.xRot = 0.0F;
			this.hat.xRot = 0.0F;
		}

		this.rightLeg.zRot = (float)Math.PI / 24.0F;
		this.leftLeg.zRot = -((float)Math.PI / 24.0F);

		float f = MathHelper.sin(this.attackTime * (float)Math.PI);
		float f1 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);

		if (entityIn.getAttackPhase() != 0)
		{
			ModelHelper.animateZombieArms(this.leftArm, this.rightArm, entityIn.isCharging(), this.attackTime, ageInTicks);
		}
		else
		{
			this.rightArm.zRot = (float)Math.PI / 10.0F;
			this.leftArm.zRot = -((float)Math.PI / 10.0F);
			this.rightArm.yRot = -(0.1F - f * 0.6F);
			this.leftArm.yRot = 0.1F - f * 0.6F;
			this.rightArm.xRot = -((float)Math.PI * 7.0F / 11.0F);
			this.leftArm.xRot = -((float)Math.PI * 7.0F / 11.0F);
			this.rightArm.xRot -= f * 1.2F - f1 * 0.2F;
			this.leftArm.xRot -= f * 1.2F - f1 * 0.2F;
			this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.01F + 0.05F;
			this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.01F + 0.05F;
			this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.01F;
			this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.01F;
		}

		this.rightLeg.xRot = ((float)Math.PI / 18.0F);
		this.leftLeg.xRot = ((float)Math.PI / 18.0F);
		this.rightLeg.xRot -= f * 1.2F - f1 * 0.4F;
		this.leftLeg.xRot -= f * 1.2F - f1 * 0.4F;
		this.rightLeg.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.03F;
		this.leftLeg.xRot -= MathHelper.sin(ageInTicks * 0.06F) * 0.03F;

		this.hairPart1.xRot = ((float)Math.PI / 18.0F);
		this.hairPart1.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.045F;
		this.hairPart2.xRot = ((float)Math.PI / 18.0F);
		this.hairPart2.xRot += MathHelper.sin(ageInTicks * 0.06F - (float)Math.PI / 6.0F) * 0.045F;

		float f2 = ((float)Math.PI / 9.0F) + MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.06F;
		float f3 = (float)Math.PI * 2.0F / 5.0F;
		float f4 = (float)Math.PI / 15.0F;

		this.bodyRibbon1.yRot = -f2;
		this.bodyRibbon2.yRot = f2;
		this.bodyRibbon1.xRot = -f4;
		this.bodyRibbon2.xRot = -f4;

		this.rightHairPart1.zRot = (float)Math.PI / 27.0F;
		this.leftHairPart1.zRot = -((float)Math.PI / 27.0F);
		this.rightHairPart1.zRot += MathHelper.sin(ageInTicks * 0.067F) * 0.045F;
		this.leftHairPart1.zRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.045F;

		this.ribbonRightPart1.xRot = f2;
		this.ribbonRightPart2.xRot = f2;
		this.ribbonLeftPart1.xRot = f2;
		this.ribbonLeftPart2.xRot = f2;
		this.ribbonRightPart1.yRot = f3 - f2;
		this.ribbonRightPart2.yRot = f3 + f2;
		this.ribbonLeftPart1.yRot = -f3 - f2;
		this.ribbonLeftPart2.yRot = -f3 + f2;

		f2 = MathHelper.sin(ageInTicks * 0.06F) * 0.04F;
		f3 = MathHelper.sin(ageInTicks * 0.045F - (float)Math.PI / 6.0F) * 0.03F;
		f4 = MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.045F;

		this.rightHairPart2.xRot = (float)Math.PI / 27.0F;
		this.leftHairPart2.xRot = ((float)Math.PI / 27.0F);
		this.rightHairPart2.xRot += f2;
		this.leftHairPart2.xRot += f2;
		this.rightHairPart2.zRot = (float)Math.PI / 24.0F;
		this.leftHairPart2.zRot = -((float)Math.PI / 24.0F);
		this.rightHairPart2.zRot -= f3;
		this.leftHairPart2.zRot += f3;
		this.rightHairPart3.zRot = (float)Math.PI / 30.0F;
		this.leftHairPart3.zRot = -((float)Math.PI / 30.0F);
		this.rightHairPart3.zRot -= f4;
		this.leftHairPart3.zRot += f4;
		this.rightHairPart4.zRot = -((float)Math.PI / 27.0F);
		this.leftHairPart4.zRot = (float)Math.PI / 27.0F;

		this.ahoge.xRot = -((float)Math.PI / 12.0F);
		this.ahoge.xRot += MathHelper.cos(ageInTicks * 0.03F + (float)Math.PI) * 0.09F;
	}
}
