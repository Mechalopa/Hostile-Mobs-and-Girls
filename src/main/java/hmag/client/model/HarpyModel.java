package hmag.client.model;

import hmag.entity.HarpyEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HarpyModel<T extends HarpyEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer bodyPart3;
	private ModelRenderer bodyPart4;
	private ModelRenderer clothPart;
	private ModelRenderer rightArmPart1;
	private ModelRenderer leftArmPart1;
	private ModelRenderer rightArmPart2;
	private ModelRenderer leftArmPart2;
	private ModelRenderer rightArmPart3;
	private ModelRenderer leftArmPart3;
	private ModelRenderer rightArmPart2Wing;
	private ModelRenderer leftArmPart2Wing;
	private ModelRenderer rightArmPart3Wing;
	private ModelRenderer leftArmPart3Wing;
	private ModelRenderer rightLegPart1;
	private ModelRenderer leftLegPart1;
	private ModelRenderer rightLegPart2;
	private ModelRenderer leftLegPart2;
	private ModelRenderer rightLegPart3;
	private ModelRenderer leftLegPart3;
	private ModelRenderer rightLegPart4;
	private ModelRenderer leftLegPart4;
	private ModelRenderer rightLegPart5[] = new ModelRenderer[2];
	private ModelRenderer leftLegPart5[] = new ModelRenderer[2];
	private ModelRenderer rightLegPart6;
	private ModelRenderer leftLegPart6;
	private ModelRenderer rightLegPart7;
	private ModelRenderer leftLegPart7;
	private ModelRenderer tail1;
	private ModelRenderer tail2[] = new ModelRenderer[4];
	private ModelRenderer tail3[] = new ModelRenderer[4];
	private ModelRenderer rightHeadWing;
	private ModelRenderer leftHeadWing;
	private ModelRenderer hairPart;
	private ModelRenderer ahoge;
	private float animationAmount;

	public HarpyModel()
	{
		this(0.0F);
	}

	public HarpyModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false);

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(0.0F, -2.0F, -1.5F, 2.0F, 1.0F, 3.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-2.0F, -2.0F, -1.5F, 2.0F, 1.0F, 3.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F, 0.0F);

		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.rightLeg.setPos(-2.1F, 12.0F, 0.5F);
		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.leftLeg.setPos(2.1F, 12.0F, 0.5F);

		this.bodyPart2 = new ModelRenderer(this, 32, 40);
		this.bodyPart2.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 1.0F, 3.0F, modelSize);
		this.bodyPart2.setPos(0.0F, 2.5F, 0.0F);
		this.bodyPart1.addChild(this.bodyPart2);
		this.bodyPart3 = new ModelRenderer(this, 32, 48);
		this.bodyPart3.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 2.0F, 4.0F, modelSize);
		this.bodyPart3.setPos(0.0F, 0.5F, 0.0F);
		this.bodyPart2.addChild(this.bodyPart3);
		this.bodyPart4 = new ModelRenderer(this, 32, 56);
		this.bodyPart4.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 1.0F, 5.0F, modelSize);
		this.bodyPart4.setPos(0.0F, 1.0F, 0.0F);
		this.bodyPart3.addChild(this.bodyPart4);

		this.clothPart = new ModelRenderer(this, 0, 76);
		this.clothPart.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 1.0F, 3.0F, modelSize);
		this.clothPart.setPos(0.0F, 6.0F, 0.0F);
		this.body.addChild(this.clothPart);

		this.rightArmPart1 = new ModelRenderer(this, 40, 20);
		this.rightArmPart1.addBox(0.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, modelSize);
		this.rightArmPart1.setPos(0.0F, 0.0F, 0.5F);
		this.rightArm.addChild(this.rightArmPart1);
		this.leftArmPart1 = new ModelRenderer(this, 40, 20);
		this.leftArmPart1.mirror = true;
		this.leftArmPart1.addBox(-2.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, modelSize);
		this.leftArmPart1.setPos(0.0F, 0.0F, 0.5F);
		this.leftArm.addChild(this.leftArmPart1);

		this.rightArmPart2 = new ModelRenderer(this, 16, 35);
		this.rightArmPart2.addBox(0.0F, -0.5F, -1.5F, 2.0F, 6.0F, 3.0F, modelSize - 0.125F);
		this.rightArmPart2.setPos(0.0F, 1.5F, 0.0F);
		this.rightArmPart1.addChild(this.rightArmPart2);
		this.leftArmPart2 = new ModelRenderer(this, 16, 35);
		this.leftArmPart2.mirror = true;
		this.leftArmPart2.addBox(-2.0F, -0.5F, -1.5F, 2.0F, 6.0F, 3.0F, modelSize - 0.125F);
		this.leftArmPart2.setPos(0.0F, 1.5F, 0.0F);
		this.leftArmPart1.addChild(this.leftArmPart2);

		this.rightArmPart3 = new ModelRenderer(this, 26, 32);
		this.rightArmPart3.addBox(0.0F, -0.5F, -1.5F, 1.0F, 5.0F, 2.0F, modelSize);
		this.rightArmPart3.setPos(0.5F, 5.0F, 0.0F);
		this.rightArmPart2.addChild(this.rightArmPart3);
		this.leftArmPart3 = new ModelRenderer(this, 26, 32);
		this.leftArmPart3.mirror = true;
		this.leftArmPart3.addBox(-1.0F, -0.5F, -1.5F, 1.0F, 5.0F, 2.0F, modelSize);
		this.leftArmPart3.setPos(-0.5F, 5.0F, 0.0F);
		this.leftArmPart2.addChild(this.leftArmPart3);

		this.rightArmPart2Wing = new ModelRenderer(this, 16, 44);
		this.rightArmPart2Wing.addBox(0.0F, 0.0F, 0.0F, 1.0F, 7.0F, 5.0F, modelSize);
		this.rightArmPart2Wing.setPos(0.5F, -2.5F, 1.0F);
		this.rightArmPart2.addChild(this.rightArmPart2Wing);
		this.leftArmPart2Wing = new ModelRenderer(this, 16, 44);
		this.leftArmPart2Wing.mirror = true;
		this.leftArmPart2Wing.addBox(-1.0F, 0.0F, 0.0F, 1.0F, 7.0F, 5.0F, modelSize);
		this.leftArmPart2Wing.setPos(-0.5F, -2.5F, 1.0F);
		this.leftArmPart2.addChild(this.leftArmPart2Wing);

		this.rightArmPart3Wing = new ModelRenderer(this, 16, 56);
		this.rightArmPart3Wing.addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 7.0F, modelSize - 0.001F);
		this.rightArmPart3Wing.setPos(0.0F, 0.0F, -1.0F);
		this.rightArmPart3.addChild(this.rightArmPart3Wing);
		this.leftArmPart3Wing = new ModelRenderer(this, 16, 56);
		this.leftArmPart3Wing.mirror = true;
		this.leftArmPart3Wing.addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 7.0F, modelSize - 0.001F);
		this.leftArmPart3Wing.setPos(0.0F, 0.0F, -1.0F);
		this.leftArmPart3.addChild(this.leftArmPart3Wing);

		this.rightLegPart1 = new ModelRenderer(this, 0, 20);
		this.rightLegPart1.addBox(-2.25F, 0.0F, -1.75F, 4.0F, 5.0F, 4.0F, modelSize);
		this.rightLegPart1.setPos(0.0F, -1.5F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart1);
		this.leftLegPart1 = new ModelRenderer(this, 0, 20);
		this.leftLegPart1.mirror = true;
		this.leftLegPart1.addBox(-1.75F, 0.0F, -1.75F, 4.0F, 5.0F, 4.0F, modelSize);
		this.leftLegPart1.setPos(0.0F, -1.5F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart1);

		this.rightLegPart2 = new ModelRenderer(this, 0, 40);
		this.rightLegPart2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.rightLegPart2.setPos(0.0F, 5.0F, 0.0F);
		this.rightLegPart1.addChild(this.rightLegPart2);
		this.leftLegPart2 = new ModelRenderer(this, 0, 40);
		this.leftLegPart2.mirror = true;
		this.leftLegPart2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, modelSize);
		this.leftLegPart2.setPos(0.0F, 5.0F, 0.0F);
		this.leftLegPart1.addChild(this.leftLegPart2);

		this.rightLegPart3 = new ModelRenderer(this, 0, 46);
		this.rightLegPart3.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, modelSize - 0.125F);
		this.rightLegPart3.setPos(0.0F, 5.25F, 0.0F);
		this.rightLegPart1.addChild(this.rightLegPart3);
		this.leftLegPart3 = new ModelRenderer(this, 0, 46);
		this.leftLegPart3.mirror = true;
		this.leftLegPart3.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, modelSize - 0.125F);
		this.leftLegPart3.setPos(0.0F, 5.25F, 0.0F);
		this.leftLegPart1.addChild(this.leftLegPart3);

		this.rightLegPart4 = new ModelRenderer(this, 0, 56);
		this.rightLegPart4.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, modelSize + 0.0625F);
		this.rightLegPart4.setPos(0.0F, 6.25F, 0.0F);
		this.rightLegPart3.addChild(this.rightLegPart4);
		this.leftLegPart4 = new ModelRenderer(this, 0, 56);
		this.leftLegPart4.mirror = true;
		this.leftLegPart4.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, modelSize + 0.0625F);
		this.leftLegPart4.setPos(0.0F, 6.25F, 0.0F);
		this.leftLegPart3.addChild(this.leftLegPart4);

		for (int i = 0; i < this.rightLegPart5.length; ++i)
		{
			this.rightLegPart5[i] = new ModelRenderer(this, 0, 60);
			this.rightLegPart5[i].addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, modelSize);
			this.rightLegPart5[i].setPos(-1.0F * ((float)i - 0.5F), 0.25F, -0.75F);
			this.rightLegPart4.addChild(this.rightLegPart5[i]);
		}

		for (int i = 0; i < this.leftLegPart5.length; ++i)
		{
			this.leftLegPart5[i] = new ModelRenderer(this, 0, 60);
			this.leftLegPart5[i].mirror = true;
			this.leftLegPart5[i].addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, modelSize);
			this.leftLegPart5[i].setPos(-1.0F * (0.5F - (float)i), 0.25F, -0.75F);
			this.leftLegPart4.addChild(this.leftLegPart5[i]);
		}

		this.rightLegPart6 = new ModelRenderer(this, 4, 60);
		this.rightLegPart6.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, modelSize);
		this.rightLegPart6.setPos(0.0F, 0.375F, 0.875F);
		this.rightLegPart4.addChild(this.rightLegPart6);
		this.leftLegPart6 = new ModelRenderer(this, 4, 60);
		this.leftLegPart6.mirror = true;
		this.leftLegPart6.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, modelSize);
		this.leftLegPart6.setPos(0.0F, 0.375F, 0.875F);
		this.leftLegPart4.addChild(this.leftLegPart6);

		this.rightLegPart7 = new ModelRenderer(this, 0, 64);
		this.rightLegPart7.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, modelSize + 0.125F);
		this.rightLegPart7.setPos(0.0F, 4.5F, 0.0F);
		this.rightLegPart3.addChild(this.rightLegPart7);
		this.leftLegPart7 = new ModelRenderer(this, 0, 64);
		this.leftLegPart7.mirror = true;
		this.leftLegPart7.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, modelSize + 0.125F);
		this.leftLegPart7.setPos(0.0F, 4.5F, 0.0F);
		this.leftLegPart3.addChild(this.leftLegPart7);

		this.tail1 = new ModelRenderer(this, 48, 64);
		this.tail1.addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 2.0F, modelSize);
		this.tail1.setPos(0.0F, 9.5F, 2.0F);
		this.body.addChild(this.tail1);

		for (int i = 0; i < this.tail2.length; ++i)
		{
			this.tail2[i] = new ModelRenderer(this, 48, 68);
			this.tail2[i].addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 1.0F, modelSize);
			this.tail2[i].setPos(-1.0F * ((float)i - 1.5F), 0.0F, 0.0F);
			this.tail1.addChild(this.tail2[i]);

			this.tail3[i] = new ModelRenderer(this, 48, 72);
			this.tail3[i].addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 1.0F, modelSize);
			this.tail3[i].setPos(0.0F, 2.0F, 0.0F);
			this.tail2[i].addChild(this.tail3[i]);
		}

		this.rightHeadWing = new ModelRenderer(this, 32, 72);
		this.rightHeadWing.addBox(0.0F, -2.0F, -0.25F, 1.0F, 5.0F, 6.0F, modelSize);
		this.rightHeadWing.setPos(-4.5F, -6.0F, -1.5F);
		this.head.addChild(this.rightHeadWing);
		this.leftHeadWing = new ModelRenderer(this, 32, 72);
		this.leftHeadWing.mirror = true;
		this.leftHeadWing.addBox(-1.0F, -2.0F, -0.25F, 1.0F, 5.0F, 6.0F, modelSize);
		this.leftHeadWing.setPos(4.5F, -6.0F, -1.5F);
		this.head.addChild(this.leftHeadWing);

		this.hairPart = new ModelRenderer(this, 0, 72);
		this.hairPart.addBox(-4.0F, 0.0F, -1.0F, 8.0F, 3.0F, 1.0F, modelSize);
		this.hairPart.setPos(0.0F, 0.0F, 4.0F);
		this.head.addChild(this.hairPart);

		this.ahoge = new ModelRenderer(this, 32, 64);
		this.ahoge.addBox(-2.5F, -4.0F, 0.0F, 5.0F, 4.0F, 1.0F, modelSize - 0.25F);
		this.ahoge.setPos(0.0F, -7.75F, 0.0F);
		this.head.addChild(this.ahoge);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.animationAmount = MathHelper.clamp(entityIn.getAnimationScale(partialTick), 0.0F, 1.0F);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (this.crouching)
		{
			this.body.xRot = 0.5F;
			this.rightLeg.z = 4.0F;
			this.leftLeg.z = 4.0F;
			this.bodyPart3.xRot = 0.0F;
		}
		else
		{
			this.body.xRot = (float)Math.PI * 0.03F;
			this.rightLeg.z = 0.6F;
			this.leftLeg.z = 0.6F;
			this.bodyPart3.xRot = -((float)Math.PI * 0.03F);
		}

		this.rightArm.xRot *= 0.5F;
		this.leftArm.xRot *= 0.5F;
		this.rightArm.zRot = (float)Math.PI / 8.0F;
		this.leftArm.zRot = -((float)Math.PI / 8.0F);
		this.rightArm.zRot += (MathHelper.cos(ageInTicks * 0.75F) * 0.45F + 0.6F) * this.animationAmount;
		this.leftArm.zRot -= (MathHelper.cos(ageInTicks * 0.75F) * 0.45F + 0.6F) * this.animationAmount;
		this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.105F + (float)Math.PI * 0.5F) * 0.072F;
		this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.105F) * 0.072F;

		this.rightArmPart2Wing.yRot = -((float)Math.PI * 0.021F);
		this.leftArmPart2Wing.yRot = (float)Math.PI * 0.021F;
		this.rightArmPart3Wing.yRot = (float)Math.PI * 0.015F;
		this.leftArmPart3Wing.yRot = -((float)Math.PI * 0.015F);

		this.rightLeg.xRot *= (1.0F - this.animationAmount) * 0.9F;
		this.leftLeg.xRot *= (1.0F - this.animationAmount) * 0.9F;
		this.rightLeg.xRot -= ((float)Math.PI / 15.0F) * this.animationAmount;
		this.leftLeg.xRot -= ((float)Math.PI / 15.0F) * this.animationAmount;

		this.rightLeg.zRot = -((float)Math.PI * 0.01F);
		this.leftLeg.zRot = (float)Math.PI * 0.01F;
		this.rightLeg.zRot += ((float)Math.PI / 15.0F) * this.animationAmount;
		this.leftLeg.zRot -= ((float)Math.PI / 15.0F) * this.animationAmount;

		this.rightArmPart1.xRot = (float)Math.PI / 11.0F;
		this.leftArmPart1.xRot = (float)Math.PI / 11.0F;
		this.rightArmPart2.xRot = -((float)Math.PI / 3.0F);
		this.leftArmPart2.xRot = -((float)Math.PI / 3.0F);
		this.rightArmPart2.xRot -= MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.024F;
		this.leftArmPart2.xRot -= MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.024F;
		this.rightArmPart3.xRot = (float)Math.PI / 6.0F;
		this.leftArmPart3.xRot = (float)Math.PI / 6.0F;
		this.rightArmPart3.xRot += MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.033F;
		this.leftArmPart3.xRot += MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.033F;
		this.rightArmPart3.xRot += ((float)Math.PI / 12.0F) * this.animationAmount;
		this.leftArmPart3.xRot += ((float)Math.PI / 12.0F) * this.animationAmount;

		this.rightLegPart1.xRot = -((float)Math.PI / 14.0F);
		this.leftLegPart1.xRot = -((float)Math.PI / 14.0F);

		this.rightLegPart3.xRot = (float)Math.PI / 8.0F;
		this.leftLegPart3.xRot = (float)Math.PI / 8.0F;
		this.rightLegPart4.xRot = -((float)Math.PI / 25.0F);
		this.leftLegPart4.xRot = -((float)Math.PI / 25.0F);

		float f = ((float)Math.PI / 9.0F) * this.animationAmount;

		for (int i = 0; i < this.rightLegPart5.length; ++i)
		{
			this.rightLegPart5[i].xRot = -((float)Math.PI / 3.0F);
			this.rightLegPart5[i].xRot += f;
			this.rightLegPart5[i].yRot = (float)Math.PI * 0.15F * ((float)i - 0.5F);
			this.rightLegPart5[i].zRot = (float)Math.PI * 0.07F * ((float)i - 0.5F);
		}

		for (int i = 0; i < this.leftLegPart5.length; ++i)
		{
			this.leftLegPart5[i].xRot = -((float)Math.PI / 3.0F);
			this.leftLegPart5[i].xRot += f;
			this.leftLegPart5[i].yRot = (float)Math.PI * 0.15F * (0.5F - (float)i);
			this.leftLegPart5[i].zRot = (float)Math.PI * 0.07F * (0.5F - (float)i);
		}

		this.rightLegPart6.xRot = (float)Math.PI * 1.0F / 5.0F;
		this.leftLegPart6.xRot = (float)Math.PI * 1.0F / 5.0F;
		this.rightLegPart6.xRot -= f;
		this.leftLegPart6.xRot -= f;

		this.tail1.xRot = (float)Math.PI * 2.0F / 5.0F;
		this.tail1.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.021F;
		this.tail1.xRot += (MathHelper.sin(ageInTicks * 0.375F) * 0.08F + 0.08F) * this.animationAmount;

		for (int i = 0; i < this.tail2.length; ++i)
		{
			this.tail2[i].yRot = (float)Math.PI / 12.0F * ((float)i - 1.5F);
			this.tail2[i].zRot = (float)Math.PI / 6.0F * ((float)i - 1.5F);
			this.tail2[i].xRot = (float)Math.PI / 15.0F;
			this.tail2[i].xRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.06F;
			this.tail2[i].xRot += 0.25F * MathHelper.sin(limbSwing * 1.25F * 0.5F) * limbSwingAmount;
			this.tail3[i].xRot = (float)Math.PI / 6.0F;
			this.tail3[i].xRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.09F;
		}

		this.rightHeadWing.xRot = (float)Math.PI / 15.0F;
		this.leftHeadWing.xRot = (float)Math.PI / 15.0F;
		this.rightHeadWing.xRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.03F;
		this.leftHeadWing.xRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.03F;
		this.rightHeadWing.xRot += 0.15F * MathHelper.sin(limbSwing * 1.25F * 0.5F) * limbSwingAmount;
		this.leftHeadWing.xRot += 0.15F * MathHelper.sin(limbSwing * 1.25F * 0.5F) * limbSwingAmount;
		this.rightHeadWing.yRot = -((float)Math.PI / 15.0F);
		this.leftHeadWing.yRot = (float)Math.PI / 15.0F;
		this.rightHeadWing.xRot += MathHelper.cos(ageInTicks * 0.03F) * 0.06F;
		this.leftHeadWing.xRot -= MathHelper.cos(ageInTicks * 0.03F) * 0.06F;
		this.rightHeadWing.yRot -= (MathHelper.cos(ageInTicks * 0.375F) * 0.15F + 0.12F) * this.animationAmount;
		this.leftHeadWing.yRot += (MathHelper.cos(ageInTicks * 0.375F) * 0.15F + 0.12F) * this.animationAmount;

		this.hairPart.xRot = (float)Math.PI / 12.0F;
		this.hairPart.xRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.06F;

		this.ahoge.xRot = -((float)Math.PI / 12.0F);
		this.ahoge.xRot += MathHelper.cos(ageInTicks * 0.03F + (float)Math.PI) * 0.09F;
	}

	@Override
	protected int getBodyHeight()
	{
		return 6;
	}

	@Override
	protected float getLegRotZ()
	{
		return -((float)Math.PI * 0.004F);
	}
}