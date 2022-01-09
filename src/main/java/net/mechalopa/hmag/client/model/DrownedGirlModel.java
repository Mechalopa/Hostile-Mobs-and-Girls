package net.mechalopa.hmag.client.model;

import net.mechalopa.hmag.util.ModUtils;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DrownedGirlModel<T extends ZombieEntity> extends ZombieGirlModel<T>
{
	private ModelRenderer rightArmPart;
	private ModelRenderer leftArmPart;

	public DrownedGirlModel()
	{
		this(0.0F);
	}

	public DrownedGirlModel(float modelSize)
	{
		this(modelSize, false);
	}

	public DrownedGirlModel(float modelSize, boolean isArmor)
	{
		super(modelSize, isArmor);

		if (!isArmor)
		{
			this.rightArm = new ModelRenderer(this, 50, 32);
			this.rightArm.addBox(-1.0F, -2.0F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
			this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
			this.rightLeg = new ModelRenderer(this, 50, 48);
			this.rightLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
			this.rightLeg.setPos(-1.9F, 12.0F, 0.0F);

			this.rightArmPart = new ModelRenderer(this, 24, 56);
			this.rightArmPart.addBox(-1.0F, -2.0F, 0.0F, 1.0F, 4.0F, 3.0F, modelSize);
			this.rightArmPart.setPos(-1.0F, 2.0F, 1.5F);
			this.rightArm.addChild(this.rightArmPart);
			this.leftArmPart = new ModelRenderer(this, 40, 54);
			this.leftArmPart.addBox(0.0F, -2.0F, 0.0F, 1.0F, 8.0F, 2.0F, modelSize);
			this.leftArmPart.setPos(1.0F, 2.0F, 1.5F);
			this.leftArm.addChild(this.leftArmPart);
		}
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		this.rightArmPose = BipedModel.ArmPose.EMPTY;
		this.leftArmPose = BipedModel.ArmPose.EMPTY;
		ItemStack stack = entityIn.getItemInHand(Hand.MAIN_HAND);

		if (ModUtils.isTrident(stack) && entityIn.isAggressive())
		{
			if (entityIn.getMainArm() == HandSide.RIGHT)
			{
				this.rightArmPose = BipedModel.ArmPose.THROW_SPEAR;
			}
			else
			{
				this.leftArmPose = BipedModel.ArmPose.THROW_SPEAR;
			}
		}

		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (this.leftArmPose == BipedModel.ArmPose.THROW_SPEAR)
		{
			this.leftArm.xRot = this.leftArm.xRot * 0.5F - (float)Math.PI;
			this.leftArm.yRot = 0.0F;
		}

		if (this.rightArmPose == BipedModel.ArmPose.THROW_SPEAR)
		{
			this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float)Math.PI;
			this.rightArm.yRot = 0.0F;
		}

		if (this.swimAmount > 0.0F)
		{
			this.rightArm.xRot = this.rotlerpRad(this.swimAmount, this.rightArm.xRot, -2.5132742F) + this.swimAmount * 0.35F * MathHelper.sin(0.1F * ageInTicks);
			this.leftArm.xRot = this.rotlerpRad(this.swimAmount, this.leftArm.xRot, -2.5132742F) - this.swimAmount * 0.35F * MathHelper.sin(0.1F * ageInTicks);
			this.rightArm.zRot = this.rotlerpRad(this.swimAmount, this.rightArm.zRot, -0.15F);
			this.leftArm.zRot = this.rotlerpRad(this.swimAmount, this.leftArm.zRot, 0.15F);
			this.leftLeg.xRot -= this.swimAmount * 0.55F * MathHelper.sin(0.1F * ageInTicks);
			this.rightLeg.xRot += this.swimAmount * 0.55F * MathHelper.sin(0.1F * ageInTicks);
			this.head.xRot = 0.0F;
		}

		this.rightArmPart.yRot = -((float)Math.PI / 18.0F);
		this.rightArmPart.yRot += MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI) * 0.06F;
		this.leftArmPart.yRot = (float)Math.PI / 18.0F;
		this.leftArmPart.yRot -= MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI) * 0.06F;

		this.hat.copyFrom(this.head);
	}
}