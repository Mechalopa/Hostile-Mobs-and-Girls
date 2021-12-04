package hmag.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImpModel<T extends MobEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer rightFeelerPart1;
	private ModelRenderer leftFeelerPart1;
	private ModelRenderer rightFeelerPart2;
	private ModelRenderer leftFeelerPart2;
	private ModelRenderer rightFeelerPart3;
	private ModelRenderer leftFeelerPart3;
	private ModelRenderer rightFeelerPart4;
	private ModelRenderer leftFeelerPart4;
	private ModelRenderer rightEar;
	private ModelRenderer leftEar;
	private ModelRenderer rightHair;
	private ModelRenderer leftHair;
	private ModelRenderer rightHair2;
	private ModelRenderer leftHair2;
	private ModelRenderer rightHair3;
	private ModelRenderer leftHair3;
	private ModelRenderer hairPart;
	private ModelRenderer rightArmSleeve;
	private ModelRenderer leftArmSleeve;
	private ModelRenderer clothPart;
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer[] rightSkirtPart = new ModelRenderer[5];
	private ModelRenderer[] leftSkirtPart = new ModelRenderer[5];
	private ModelRenderer tailPart1;
	private ModelRenderer tailPart2;
	private ModelRenderer tailPart3;
	private ModelRenderer tailPart4;
	private ModelRenderer rightWing;
	private ModelRenderer leftWing;

	public ImpModel()
	{
		this(0.0F);
	}

	public ImpModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false);

		this.rightFeelerPart1 = new ModelRenderer(this, 22, 36);
		this.rightFeelerPart1.addBox(-0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, modelSize);
		this.rightFeelerPart1.setPos(-1.75F, -7.75F, -1.0F);
		this.head.addChild(this.rightFeelerPart1);
		this.leftFeelerPart1 = new ModelRenderer(this, 22, 36);
		this.leftFeelerPart1.mirror = true;
		this.leftFeelerPart1.addBox(-0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, modelSize);
		this.leftFeelerPart1.setPos(1.75F, -7.75F, -1.0F);
		this.head.addChild(this.leftFeelerPart1);
		this.rightFeelerPart2 = new ModelRenderer(this, 22, 38);
		this.rightFeelerPart2.addBox(-0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, modelSize);
		this.rightFeelerPart2.setPos(0.0F, -1.0F, 0.0F);
		this.rightFeelerPart1.addChild(this.rightFeelerPart2);
		this.leftFeelerPart2 = new ModelRenderer(this, 22, 38);
		this.leftFeelerPart2.mirror = true;
		this.leftFeelerPart2.addBox(-0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, modelSize);
		this.leftFeelerPart2.setPos(0.0F, -1.0F, 0.0F);
		this.leftFeelerPart1.addChild(this.leftFeelerPart2);
		this.rightFeelerPart3 = new ModelRenderer(this, 22, 40);
		this.rightFeelerPart3.addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, modelSize);
		this.rightFeelerPart3.setPos(0.0F, -1.0F, 0.0F);
		this.rightFeelerPart2.addChild(this.rightFeelerPart3);
		this.leftFeelerPart3 = new ModelRenderer(this, 22, 40);
		this.leftFeelerPart3.mirror = true;
		this.leftFeelerPart3.addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, modelSize);
		this.leftFeelerPart3.setPos(0.0F, -1.0F, 0.0F);
		this.leftFeelerPart2.addChild(this.leftFeelerPart3);
		this.rightFeelerPart4 = new ModelRenderer(this, 20, 32);
		this.rightFeelerPart4.addBox(-3.0F, -1.5F, -1.0F, 5.0F, 2.0F, 1.0F, modelSize);
		this.rightFeelerPart4.setPos(0.0F, -0.75F, -0.001F);
		this.rightFeelerPart3.addChild(this.rightFeelerPart4);
		this.leftFeelerPart4 = new ModelRenderer(this, 20, 32);
		this.leftFeelerPart4.mirror = true;
		this.leftFeelerPart4.addBox(-2.0F, -1.5F, -1.0F, 5.0F, 2.0F, 1.0F, modelSize);
		this.leftFeelerPart4.setPos(0.0F, -0.75F, -0.001F);
		this.leftFeelerPart3.addChild(this.leftFeelerPart4);

		this.rightEar = new ModelRenderer(this, 26, 36);
		this.rightEar.addBox(-1.25F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, modelSize + 0.125F);
		this.rightEar.setPos(-4.0F, -3.5F, -1.0F);
		this.head.addChild(this.rightEar);
		this.leftEar = new ModelRenderer(this, 26, 36);
		this.leftEar.mirror = true;
		this.leftEar.addBox(-0.75F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, modelSize + 0.125F);
		this.leftEar.setPos(4.0F, -3.5F, -1.0F);
		this.head.addChild(this.leftEar);

		this.rightHair = new ModelRenderer(this, 32, 64);
		this.rightHair.addBox(-2.0F, -1.5F, -1.5F, 3.0F, 7.0F, 3.0F, modelSize);
		this.rightHair.setPos(-4.5F, -7.5F, 1.25F);
		this.head.addChild(this.rightHair);
		this.leftHair = new ModelRenderer(this, 32, 64);
		this.leftHair.mirror = true;
		this.leftHair.addBox(-1.0F, -1.5F, -1.5F, 3.0F, 7.0F, 3.0F, modelSize);
		this.leftHair.setPos(4.5F, -7.5F, 1.25F);
		this.head.addChild(this.leftHair);

		this.rightHair2 = new ModelRenderer(this, 48, 64);
		this.rightHair2.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, modelSize + 0.25F);
		this.rightHair2.setPos(-0.5F, 6.0F, 0.0F);
		this.rightHair.addChild(this.rightHair2);
		this.leftHair2 = new ModelRenderer(this, 48, 64);
		this.leftHair2.mirror = true;
		this.leftHair2.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, modelSize + 0.25F);
		this.leftHair2.setPos(0.5F, 6.0F, 0.0F);
		this.leftHair.addChild(this.leftHair2);

		this.rightHair3 = new ModelRenderer(this, 56, 64);
		this.rightHair3.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.rightHair3.setPos(0.0F, 3.5F, 0.0F);
		this.rightHair2.addChild(this.rightHair3);
		this.leftHair3 = new ModelRenderer(this, 56, 64);
		this.leftHair3.mirror = true;
		this.leftHair3.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.leftHair3.setPos(0.0F, 3.5F, 0.0F);
		this.leftHair2.addChild(this.leftHair3);

		this.hairPart = new ModelRenderer(this, 32, 56);
		this.hairPart.addBox(-4.0F, 0.0F, -1.0F, 8.0F, 4.0F, 1.0F, modelSize);
		this.hairPart.setPos(0.0F, 0.0F, 4.0F);
		this.head.addChild(this.hairPart);

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F, 0.0F);

		this.rightArmSleeve = new ModelRenderer(this, 48, 32);
		this.rightArmSleeve.addBox(-0.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, modelSize);
		this.rightArmSleeve.setPos(0.0F, 2.0F, 0.0F);
		this.rightArm.addChild(this.rightArmSleeve);
		this.leftArmSleeve = new ModelRenderer(this, 48, 32);
		this.leftArmSleeve.mirror = true;
		this.leftArmSleeve.addBox(-2.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, modelSize);
		this.leftArmSleeve.setPos(0.0F, 2.0F, 0.0F);
		this.leftArm.addChild(this.leftArmSleeve);

		this.clothPart = new ModelRenderer(this, 32, 48);
		this.clothPart.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 3.0F, 3.0F, modelSize);
		this.clothPart.setPos(0.0F, 7.0F, 0.0F);
		this.body.addChild(this.clothPart);

		this.skirt1 = new ModelRenderer(this, 0, 38);
		this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSize);
		this.skirt1.setPos(0.0F, 11.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 44);
		this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, modelSize);
		this.skirt2.setPos(0.0F, 11.5F, 0.0F);
		this.body.addChild(this.skirt2);

		for (int i = 0; i < this.rightSkirtPart.length; ++i)
		{
			float f = (float)(i - 2) * ((float)Math.PI / 5.0F);
			float f1 = MathHelper.sin(f - ((float)Math.PI / 2.0F)) * 3.5F;
			float f2 = MathHelper.cos(f - ((float)Math.PI / 2.0F)) * 2.0F;
			this.rightSkirtPart[i] = new ModelRenderer(this, 0, 52);
			this.rightSkirtPart[i].addBox(-2.5F, 0.5F, -1.0F, 5.0F, 5.0F, 1.0F, modelSize);
			this.rightSkirtPart[i].setPos(f1, 11.0F, f2);
			this.body.addChild(this.rightSkirtPart[i]);
			f = (float)(2 - i) * ((float)Math.PI / 5.0F);
			f1 = MathHelper.sin(f + ((float)Math.PI / 2.0F)) * 3.5F;
			f2 = MathHelper.cos(f + ((float)Math.PI / 2.0F)) * 2.0F;
			this.leftSkirtPart[i] = new ModelRenderer(this, 0, 52);
			this.leftSkirtPart[i].mirror = true;
			this.leftSkirtPart[i].addBox(-2.5F, 0.5F, -1.0F, 5.0F, 5.0F, 1.0F, modelSize);
			this.leftSkirtPart[i].setPos(f1, 11.0F, f2);
			this.body.addChild(this.leftSkirtPart[i]);
		}

		this.tailPart1 = new ModelRenderer(this, 12, 52);
		this.tailPart1.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, modelSize);
		this.tailPart1.setPos(0.0F, 9.5F, 1.5F);
		this.body.addChild(this.tailPart1);
		this.tailPart2 = new ModelRenderer(this, 16, 52);
		this.tailPart2.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 1.0F, modelSize);
		this.tailPart2.setPos(0.0F, 3.0F, 0.0F);
		this.tailPart1.addChild(this.tailPart2);
		this.tailPart3 = new ModelRenderer(this, 20, 52);
		this.tailPart3.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 1.0F, modelSize);
		this.tailPart3.setPos(0.0F, 4.0F, 0.0F);
		this.tailPart2.addChild(this.tailPart3);
		this.tailPart4 = new ModelRenderer(this, 24, 52);
		this.tailPart4.addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 1.0F, modelSize);
		this.tailPart4.setPos(0.0F, 2.75F, -0.001F);
		this.tailPart3.addChild(this.tailPart4);

		this.rightWing = new ModelRenderer(this, 0, 60);
		this.rightWing.addBox(-6.0F, -3.0F, 0.0F, 6.0F, 9.0F, 1.0F, modelSize);
		this.rightWing.setPos(-1.5F, 3.5F, 1.5F);
		this.body.addChild(this.rightWing);
		this.leftWing = new ModelRenderer(this, 0, 60);
		this.leftWing.mirror = true;
		this.leftWing.addBox(0.0F, -3.0F, 0.0F, 6.0F, 9.0F, 1.0F, modelSize);
		this.leftWing.setPos(1.5F, 3.5F, 1.5F);
		this.body.addChild(this.leftWing);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.rightArm.zRot = (float)Math.PI / 12.0F;
		this.leftArm.zRot = -((float)Math.PI / 12.0F);
		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.075F) * 0.03F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.075F) * 0.03F;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
		}

		this.rightFeelerPart1.yRot = (float)Math.PI / 9.0F;
		this.leftFeelerPart1.yRot = -((float)Math.PI / 9.0F);
		this.rightFeelerPart1.zRot = -((float)Math.PI / 15.0F);
		this.leftFeelerPart1.zRot = (float)Math.PI / 15.0F;
		this.rightFeelerPart1.xRot = (float)Math.PI / 8.0F;
		this.leftFeelerPart1.xRot = (float)Math.PI / 8.0F;
		this.rightFeelerPart1.xRot += MathHelper.sin(ageInTicks * 0.12F) * 0.09F;
		this.leftFeelerPart1.xRot += MathHelper.sin(ageInTicks * 0.12F) * 0.09F;
		this.rightFeelerPart2.xRot = -((float)Math.PI / 3.0F);
		this.leftFeelerPart2.xRot = -((float)Math.PI / 3.0F);
		this.rightFeelerPart3.xRot -= MathHelper.sin(ageInTicks * 0.12F + (float)Math.PI / 6.0F) * 0.081F;
		this.leftFeelerPart3.xRot -= MathHelper.sin(ageInTicks * 0.12F + (float)Math.PI / 6.0F) * 0.081F;
		this.rightFeelerPart3.xRot = (float)Math.PI * 3.0F / 7.0F;
		this.leftFeelerPart3.xRot = (float)Math.PI * 3.0F / 7.0F;
		this.rightFeelerPart3.xRot += MathHelper.sin(ageInTicks * 0.12F + (float)Math.PI / 3.0F) * 0.12F;
		this.leftFeelerPart3.xRot += MathHelper.sin(ageInTicks * 0.12F + (float)Math.PI / 3.0F) * 0.12F;
		this.rightFeelerPart4.zRot = -((float)Math.PI / 9.0F);
		this.leftFeelerPart4.zRot = (float)Math.PI / 9.0F;

		this.rightEar.zRot = (float)Math.PI / 12.0F;
		this.leftEar.zRot = -((float)Math.PI / 12.0F);

		this.rightHair.xRot = (float)Math.PI / 18.0F;
		this.leftHair.xRot = (float)Math.PI / 18.0F;
		this.rightHair.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.036F;
		this.leftHair.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.036F;
		this.rightHair.zRot = (float)Math.PI / 8.0F;
		this.leftHair.zRot = -((float)Math.PI / 8.0F);
		this.rightHair.zRot -= MathHelper.sin(ageInTicks * 0.075F) * 0.03F;
		this.leftHair.zRot += MathHelper.sin(ageInTicks * 0.075F) * 0.03F;
		this.rightHair2.zRot = -((float)Math.PI / 15.0F);
		this.leftHair2.zRot = (float)Math.PI / 15.0F;
		this.rightHair2.zRot -= MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.03F;
		this.leftHair2.zRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.03F;
		this.rightHair3.zRot = -((float)Math.PI / 12.0F);
		this.leftHair3.zRot = (float)Math.PI / 12.0F;
		this.rightHair3.zRot -= MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.015F;
		this.leftHair3.zRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.015F;

		this.hairPart.xRot = (float)Math.PI / 12.0F;
		this.hairPart.xRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.06F;

		float f = (float)Math.PI / 2.0F;

		for (int i = 0; i < this.rightSkirtPart.length; ++i)
		{
			this.rightSkirtPart[i].yRot = (float)Math.PI * ((float)(i - 2)) / 5.0F + f;
			this.rightSkirtPart[i].xRot = (float)Math.PI / 3.0F - f;
			this.rightSkirtPart[i].xRot += MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.045F;
			this.leftSkirtPart[i].yRot = -((float)Math.PI * ((float)(i - 2)) / 5.0F + f);
			this.leftSkirtPart[i].xRot = (float)Math.PI / 3.0F - f;
			this.leftSkirtPart[i].xRot += MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F) * 0.045F;
		}

		this.tailPart1.xRot = (float)Math.PI * 2.0F / 5.0F;
		this.tailPart1.xRot += MathHelper.sin(ageInTicks * 0.09F) * 0.15F;
		this.tailPart1.zRot = MathHelper.cos(ageInTicks * 0.075F) * 0.21F;
		this.tailPart2.xRot = (float)Math.PI * 4.0F / 7.0F;
		this.tailPart2.xRot -= MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 3.0F) * 0.12F;
		this.tailPart3.xRot = -((float)Math.PI / 5.0F);
		this.tailPart3.xRot += MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI * 2.0F / 3.0F) * 0.12F;
		this.tailPart4.zRot = (float)Math.PI / 4.0F;

		this.rightWing.yRot = (float)Math.PI / 5.0F;
		this.leftWing.yRot = -((float)Math.PI / 5.0F);
		this.rightWing.yRot += MathHelper.cos(ageInTicks * 0.12F) * 0.096F;
		this.leftWing.yRot -= MathHelper.cos(ageInTicks * 0.12F) * 0.096F;
		this.rightWing.zRot = (float)Math.PI / 16.0F;
		this.leftWing.zRot = -((float)Math.PI / 16.0F);
		this.rightWing.zRot += MathHelper.cos(ageInTicks * 0.12F + (float)Math.PI / 3.0F) * 0.045F;
		this.leftWing.zRot -= MathHelper.cos(ageInTicks * 0.12F + (float)Math.PI / 3.0F) * 0.045F;
	}
}