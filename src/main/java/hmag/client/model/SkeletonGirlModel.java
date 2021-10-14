package hmag.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;

import hmag.util.ModUtils;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkeletonGirlModel<T extends MobEntity & IRangedAttackMob> extends AbstractGirlModel<T>
{
	protected boolean isClothing = false;
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer rightHair;
	private ModelRenderer leftHair;
	private ModelRenderer rightHair2;
	private ModelRenderer leftHair2;

	public SkeletonGirlModel()
	{
		this(0.0F);
	}

	public SkeletonGirlModel(float modelSize)
	{
		this(modelSize, false, false);
	}

	public SkeletonGirlModel(float modelSize, boolean isArmor)
	{
		this(modelSize, isArmor, false);
	}

	public SkeletonGirlModel(float modelSize, boolean isArmor, boolean isClothing)
	{
		super(modelSize, 0.0F, isArmor, false);
		this.isClothing = isClothing;

		if (isClothing)
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

		if (!isArmor && !isClothing)
		{
			this.rightArm = new ModelRenderer(this, 40, 16);
			this.rightArm.addBox(0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
			this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
			this.leftArm = new ModelRenderer(this, 40, 16);
			this.leftArm.mirror = true;
			this.leftArm.addBox(-2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
			this.leftArm.setPos(5.0F, 2.0F, 0.0F);
			this.rightLeg = new ModelRenderer(this, 0, 16);
			this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
			this.rightLeg.setPos(-1.75F, 12.0F, 0.0F);
			this.leftLeg = new ModelRenderer(this, 0, 16);
			this.leftLeg.mirror = true;
			this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
			this.leftLeg.setPos(1.75F, 12.0F, 0.0F);

			this.skirt1 = new ModelRenderer(this, 0, 38);
			this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSize);
			this.skirt1.setPos(0.0F, 11.0F, 0.0F);
			this.body.addChild(this.skirt1);
			this.skirt2 = new ModelRenderer(this, 0, 44);
			this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 6.0F, 5.0F, modelSize);
			this.skirt2.setPos(0.0F, 12.0F, 0.0F);
			this.body.addChild(this.skirt2);

			this.rightHair = new ModelRenderer(this, 32, 32);
			this.rightHair.addBox(-0.5F, -1.0F, 0.0F, 1.0F, 12.0F, 1.0F, modelSize + 0.25F);
			this.rightHair.setPos(-4.0F, -8.0F, 2.75F);
			this.head.addChild(this.rightHair);
			this.leftHair = new ModelRenderer(this, 32, 32);
			this.leftHair.mirror = true;
			this.leftHair.addBox(-0.5F, -1.0F, 0.0F, 1.0F, 12.0F, 1.0F, modelSize + 0.25F);
			this.leftHair.setPos(4.0F, -8.0F, 2.75F);
			this.head.addChild(this.leftHair);

			this.rightHair2 = new ModelRenderer(this, 36, 32);
			this.rightHair2.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 10.0F, 1.0F, modelSize);
			this.rightHair2.setPos(0.0F, 11.0F, 0.0F);
			this.rightHair.addChild(this.rightHair2);
			this.leftHair2 = new ModelRenderer(this, 36, 32);
			this.leftHair2.mirror = true;
			this.leftHair2.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 10.0F, 1.0F, modelSize);
			this.leftHair2.setPos(0.0F, 11.0F, 0.0F);
			this.leftHair.addChild(this.leftHair2);
		}
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		this.rightArmPose = BipedModel.ArmPose.EMPTY;
		this.leftArmPose = BipedModel.ArmPose.EMPTY;

		ItemStack stack = entityIn.getItemInHand(Hand.MAIN_HAND);

		if (ModUtils.isBow(stack) && entityIn.isAggressive())
		{
			if (entityIn.getMainArm() == HandSide.RIGHT)
			{
				this.rightArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
			}
			else
			{
				this.leftArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
			}
		}

		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		ItemStack stack = entityIn.getMainHandItem();

		if (entityIn.isAggressive() && (stack.isEmpty() || !ModUtils.isBow(stack)))
		{
			float f = MathHelper.sin(this.attackTime * (float)Math.PI);
			float f1 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
			this.rightArm.zRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightArm.yRot = -(0.1F - f * 0.6F);
			this.leftArm.yRot = 0.1F - f * 0.6F;
			this.rightArm.xRot = (-(float)Math.PI / 2F);
			this.leftArm.xRot = (-(float)Math.PI / 2F);
			this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
			ModelHelper.bobArms(this.rightArm, this.leftArm, ageInTicks);
		}

		if (!this.isArmor && !this.isClothing)
		{
			if (this.riding)
			{
				this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
			}
			else
			{
				this.skirt2.xRot = this.body.xRot;
			}

			this.rightHair.xRot = ((float)Math.PI / 18.0F);
			this.leftHair.xRot = ((float)Math.PI / 18.0F);
			this.rightHair.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.036F;
			this.leftHair.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.036F;
			this.rightHair.zRot = ((float)Math.PI / 9.0F);
			this.leftHair.zRot = -((float)Math.PI / 9.0F);
			this.rightHair.zRot -= MathHelper.sin(ageInTicks * 0.09F) * 0.03F;
			this.leftHair.zRot += MathHelper.sin(ageInTicks * 0.09F) * 0.03F;
			this.rightHair2.zRot = -((float)Math.PI / 15.0F);
			this.leftHair2.zRot = ((float)Math.PI / 15.0F);
			this.rightHair2.zRot -= MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.03F;
			this.leftHair2.zRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.03F;
		}
	}

	@Override
	public void translateToHand(HandSide sideIn, MatrixStack matrixStackIn)
	{
		float f = sideIn == HandSide.RIGHT ? 1.0F : -1.0F;
		ModelRenderer modelrenderer = this.getArm(sideIn);
		modelrenderer.x += f;
		modelrenderer.translateAndRotate(matrixStackIn);
		modelrenderer.x -= f;
	}
}