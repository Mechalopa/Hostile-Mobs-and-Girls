package net.mechalopa.hmag.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LichModel<T extends MobEntity> extends BipedModel<T>
{
	private ModelRenderer rightArmSleeve;
	private ModelRenderer leftArmSleeve;
	private ModelRenderer rightLegPants1;
	private ModelRenderer leftLegPants1;
	private ModelRenderer rightLegPants2;
	private ModelRenderer leftLegPants2;
	private ModelRenderer wand;
	private ModelRenderer wandTop;
	private ModelRenderer cloak;
	private ModelRenderer cloakPart1;
	private ModelRenderer cloakPart2;

	public LichModel()
	{
		this(0.0F);
	}

	public LichModel(float modelSize)
	{
		this(modelSize, -4.0F, 64, 64);
	}

	public LichModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn)
	{
		super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);

		float f = yOffsetIn;

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(-2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F + f, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F + f, 0.0F);

		this.rightArmSleeve = new ModelRenderer(this, 40, 32);
		this.rightArmSleeve.addBox(-3.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, modelSize);
		this.rightArmSleeve.setPos(0.0F, -2.0F, 0.0F);
		this.rightArm.addChild(this.rightArmSleeve);
		this.leftArmSleeve = new ModelRenderer(this, 40, 32);
		this.leftArmSleeve.mirror = true;
		this.leftArmSleeve.addBox(-1.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, modelSize);
		this.leftArmSleeve.setPos(0.0F, -2.0F, 0.0F);
		this.leftArm.addChild(this.leftArmSleeve);

		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 16.0F, 2.0F, modelSize);
		this.rightLeg.setPos(-2.0F, 12.0F + f, 0.0F);
		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 16.0F, 2.0F, modelSize);
		this.leftLeg.setPos(2.0F, 12.0F + f, 0.0F);

		this.rightLegPants1 = new ModelRenderer(this, 0, 34);
		this.rightLegPants1.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, modelSize);
		this.rightLegPants1.setPos(0.0F, 0.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegPants1);
		this.leftLegPants1 = new ModelRenderer(this, 0, 34);
		this.leftLegPants1.mirror = true;
		this.leftLegPants1.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, modelSize);
		this.leftLegPants1.setPos(0.0F, 0.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegPants1);
		this.rightLegPants2 = new ModelRenderer(this, 0, 44);
		this.rightLegPants2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, modelSize);
		this.rightLegPants2.setPos(0.0F, 8.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegPants2);
		this.leftLegPants2 = new ModelRenderer(this, 0, 44);
		this.leftLegPants2.mirror = true;
		this.leftLegPants2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, modelSize);
		this.leftLegPants2.setPos(0.0F, 8.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegPants2);

		this.wand = new ModelRenderer(this, 16, 40);
		this.wand.addBox(-1.0F, -14.0F, -1.0F, 2.0F, 22.0F, 2.0F, modelSize);
		this.wand.setPos(-0.5F, 8.5F, 0.0F);
		this.rightArm.addChild(this.wand);
		this.wandTop = new ModelRenderer(this, 16, 32);
		this.wandTop.addBox(-2.0F, -16.0F, -2.0F, 4.0F, 4.0F, 4.0F, modelSize);
		this.wandTop.setPos(0.0F, 22.0F, 0.0F);
		this.wand.addChild(this.wandTop);

		this.cloak = new ModelRenderer(this, 24, 44);
		this.cloak.addBox(-4.5F, 0.0F, -1.0F, 9.0F, 7.0F, 1.0F, modelSize);
		this.cloak.setPos(0.0F, 0.0F, 2.0F);
		this.body.addChild(this.cloak);
		this.cloakPart1 = new ModelRenderer(this, 24, 52);
		this.cloakPart1.addBox(-4.5F, 0.0F, -1.0F, 9.0F, 7.0F, 1.0F, modelSize);
		this.cloakPart1.setPos(0.0F, 7.0F, 0.0F);
		this.cloak.addChild(this.cloakPart1);
		this.cloakPart2 = new ModelRenderer(this, 44, 44);
		this.cloakPart2.addBox(-4.5F, 0.0F, -1.0F, 9.0F, 8.0F, 1.0F, modelSize);
		this.cloakPart2.setPos(0.0F, 7.0F, 0.0F);
		this.cloakPart1.addChild(this.cloakPart2);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		ModelHelper.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entityIn), this.attackTime, ageInTicks);

		float f = -4.0F;
		this.body.y = f;
		this.head.y = f;
		this.hat.y = f;
		this.rightArm.setPos(-5.0F, 2.0F + f, 0.0F);
		this.leftArm.setPos(5.0F, 2.0F + f, 0.0F);
		this.rightLeg.y = 12.0F + f;
		this.leftLeg.y = 12.0F + f;

		this.wand.xRot = -((float)Math.PI * 17.0F / 36.0F);

		this.cloak.xRot = (float)Math.PI / 15.0F;
		this.cloak.xRot -= MathHelper.cos(limbSwing * 0.45F) * 2.0F * limbSwingAmount * 0.15F;
		this.cloak.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.036F;
		this.cloakPart1.xRot = (float)Math.PI / 12.0F;
		this.cloakPart1.xRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 8.0F) * 0.015F;
		this.cloakPart2.xRot = (float)Math.PI / 12.0F;
		this.cloakPart2.xRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 4.0F) * 0.015F;
	}

	public boolean isAggressive(T entityIn)
	{
		return entityIn.isAggressive();
	}
}