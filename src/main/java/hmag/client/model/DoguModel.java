package hmag.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DoguModel<T extends MobEntity> extends BipedModel<T>
{
	private ModelRenderer headPart;
	private ModelRenderer hat1;
	private ModelRenderer hatParts[] = new ModelRenderer[3];
	private ModelRenderer rightArmPart1;
	private ModelRenderer rightArmPart2;
	private ModelRenderer rightArmPart3;
	private ModelRenderer rightArmPart4;
	private ModelRenderer leftArmPart1;
	private ModelRenderer leftArmPart2;
	private ModelRenderer leftArmPart3;
	private ModelRenderer leftArmPart4;
	private ModelRenderer rightLegPart1;
	private ModelRenderer rightLegPart2;
	private ModelRenderer rightLegPart3;
	private ModelRenderer rightLegPart4;
	private ModelRenderer leftLegPart1;
	private ModelRenderer leftLegPart2;
	private ModelRenderer leftLegPart3;
	private ModelRenderer leftLegPart4;
	private ModelRenderer bust;
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;

	public DoguModel()
	{
		this(0.0F);
	}

	public DoguModel(float modelSize)
	{
		this(modelSize, 0.0F, 64, 128);
	}

	public DoguModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn)
	{
		super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-4.0F, -6.0F, -4.0F, 8.0F, 6.0F, 8.0F, modelSize);
		this.head.setPos(0.0F, 0.0F, 0.0F);
		this.headPart = new ModelRenderer(this, 0, 84);
		this.headPart.addBox(-4.5F, -4.5F, -4.5F, 9.0F, 3.0F, 9.0F, modelSize);
		this.headPart.setPos(0.0F, 0.0F, 0.0F);
		this.head.addChild(this.headPart);
		this.hat1 = new ModelRenderer(this, 0, 96);
		this.hat1.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 2.0F, 4.0F, modelSize);
		this.hat1.setPos(0.0F, -6.0F, 0.0F);
		this.head.addChild(this.hat1);

		for (int i = 0; i < this.hatParts.length; ++i)
		{
			this.hatParts[i] = new ModelRenderer(this, 16, 96);
			this.hatParts[i].addBox(-1.0F, -5.0F, -0.5F, 2.0F, 4.0F, 1.0F, modelSize);
			this.hatParts[i].setPos(0.0F, 0.0F, 0.0F);
			this.hat1.addChild(this.hatParts[i]);
		}

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 3.0F, 4.0F, modelSize);
		this.rightArm.setPos(-6.0F, 2.0F, 0.0F);
		this.rightArmPart1 = new ModelRenderer(this, 32, 32);
		this.rightArmPart1.addBox(-3.0F, 0.0F, -2.0F, 5.0F, 1.0F, 4.0F, modelSize);
		this.rightArmPart1.setPos(-1.0F, 0.0F, 0.0F);
		this.rightArm.addChild(this.rightArmPart1);
		this.rightArmPart2 = new ModelRenderer(this, 32, 40);
		this.rightArmPart2.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, modelSize);
		this.rightArmPart2.setPos(-2.0F, 1.0F, 0.0F);
		this.rightArm.addChild(this.rightArmPart2);
		this.rightArmPart3 = new ModelRenderer(this, 32, 52);
		this.rightArmPart3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, modelSize);
		this.rightArmPart3.setPos(-2.0F, 5.0F, 0.0F);
		this.rightArm.addChild(this.rightArmPart3);
		this.rightArmPart4 = new ModelRenderer(this, 32, 60);
		this.rightArmPart4.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.rightArmPart4.setPos(-2.0F, 9.0F, 0.0F);
		this.rightArm.addChild(this.rightArmPart4);

		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 3.0F, 4.0F, modelSize);
		this.leftArm.setPos(6.0F, 2.0F, 0.0F);
		this.leftArmPart1 = new ModelRenderer(this, 32, 32);
		this.leftArmPart1.mirror = true;
		this.leftArmPart1.addBox(-2.0F, 0.0F, -2.0F, 5.0F, 1.0F, 4.0F, modelSize);
		this.leftArmPart1.setPos(1.0F, 0.0F, 0.0F);
		this.leftArm.addChild(this.leftArmPart1);
		this.leftArmPart2 = new ModelRenderer(this, 32, 40);
		this.leftArmPart2.mirror = true;
		this.leftArmPart2.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, modelSize);
		this.leftArmPart2.setPos(2.0F, 1.0F, 0.0F);
		this.leftArm.addChild(this.leftArmPart2);
		this.leftArmPart3 = new ModelRenderer(this, 32, 52);
		this.leftArmPart3.mirror = true;
		this.leftArmPart3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, modelSize);
		this.leftArmPart3.setPos(2.0F, 5.0F, 0.0F);
		this.leftArm.addChild(this.leftArmPart3);
		this.leftArmPart4 = new ModelRenderer(this, 32, 60);
		this.leftArmPart4.mirror = true;
		this.leftArmPart4.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.leftArmPart4.setPos(2.0F, 9.0F, 0.0F);
		this.leftArm.addChild(this.leftArmPart4);

		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, modelSize);
		this.rightLeg.setPos(-2.75F, 12.0F, 0.0F);
		this.rightLegPart1 = new ModelRenderer(this, 0, 32);
		this.rightLegPart1.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, modelSize);
		this.rightLegPart1.setPos(0.0F, 3.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart1);
		this.rightLegPart2 = new ModelRenderer(this, 0, 42);
		this.rightLegPart2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, modelSize);
		this.rightLegPart2.setPos(0.0F, 7.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart2);
		this.rightLegPart3 = new ModelRenderer(this, 0, 48);
		this.rightLegPart3.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, modelSize);
		this.rightLegPart3.setPos(0.0F, 9.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart3);
		this.rightLegPart4 = new ModelRenderer(this, 16, 48);
		this.rightLegPart4.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, modelSize);
		this.rightLegPart4.setPos(0.0F, 11.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart4);

		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, modelSize);
		this.leftLeg.setPos(2.75F, 12.0F, 0.0F);
		this.leftLegPart1 = new ModelRenderer(this, 0, 32);
		this.leftLegPart1.mirror = true;
		this.leftLegPart1.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, modelSize);
		this.leftLegPart1.setPos(0.0F, 3.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart1);
		this.leftLegPart2 = new ModelRenderer(this, 0, 42);
		this.leftLegPart2.mirror = true;
		this.leftLegPart2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, modelSize);
		this.leftLegPart2.setPos(0.0F, 7.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart2);
		this.leftLegPart3 = new ModelRenderer(this, 0, 48);
		this.leftLegPart3.mirror = true;
		this.leftLegPart3.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, modelSize);
		this.leftLegPart3.setPos(0.0F, 9.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart3);
		this.leftLegPart4 = new ModelRenderer(this, 16, 48);
		this.leftLegPart4.mirror = true;
		this.leftLegPart4.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, modelSize);
		this.leftLegPart4.setPos(0.0F, 11.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart4);

		this.bust = new ModelRenderer(this, 0, 56);
		this.bust.addBox(-4.5F, 0.0F, -3.0F, 9.0F, 3.0F, 5.0F, modelSize);
		this.bust.setPos(0.0F, 2.0F, 0.0F);
		this.body.addChild(this.bust);
		this.skirt1 = new ModelRenderer(this, 0, 64);
		this.skirt1.addBox(-5.0F, 0.0F, -3.0F, 10.0F, 2.0F, 6.0F, modelSize);
		this.skirt1.setPos(0.0F, 8.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 72);
		this.skirt2.addBox(-5.5F, 0.0F, -3.5F, 11.0F, 2.0F, 7.0F, modelSize);
		this.skirt2.setPos(0.0F, 10.0F, 0.0F);
		this.body.addChild(this.skirt2);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float f6 = MathHelper.sin(this.attackTime * (float)Math.PI);
		float f7 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
		float f8 = ((float)Math.PI / 15.0F) - (f6 * 1.2F - f7 * 0.4F);
		float f9 = (float)Math.PI / 36.0F;
		this.rightArm.xRot = f8;
		this.leftArm.xRot = f8;
		this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.06F;
		this.leftArm.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.06F;
		this.rightArm.zRot = f9;
		this.leftArm.zRot = -f9;
		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.075F) * 0.04F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.075F) * 0.04F;
		this.rightLeg.xRot = f8;
		this.leftLeg.xRot = f8;
		this.rightLeg.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.06F;
		this.leftLeg.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.06F;
		this.rightLeg.zRot = f9;
		this.leftLeg.zRot = -f9;
		this.rightLeg.zRot += MathHelper.cos(ageInTicks * 0.075F) * 0.04F;
		this.leftLeg.zRot -= MathHelper.cos(ageInTicks * 0.075F) * 0.04F;

		this.hatParts[0].zRot = (float)Math.PI / 4.0F;
		this.hatParts[1].zRot = 0.0F;
		this.hatParts[2].zRot = -((float)Math.PI / 4.0F);
	}
}