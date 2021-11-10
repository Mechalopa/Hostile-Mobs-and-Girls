package hmag.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArurauneModel<T extends MobEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer rightHair;
	private ModelRenderer leftHair;
	private ModelRenderer rightHair2;
	private ModelRenderer leftHair2;
	private ModelRenderer rightHair3;
	private ModelRenderer leftHair3;
	private ModelRenderer rightHairFlowerA;
	private ModelRenderer rightHairFlowerB;
	private ModelRenderer rightHairFlowerC;
	private ModelRenderer rightHairFlowerD;
	private ModelRenderer leftHairFlowerA;
	private ModelRenderer leftHairFlowerB;
	private ModelRenderer leftHairFlowerC;
	private ModelRenderer leftHairFlowerD;
	private ModelRenderer hairPart;
	private ModelRenderer headwearPart;
	private ModelRenderer[] rightSkirtLeafPart1 = new ModelRenderer[3];
	private ModelRenderer[] leftSkirtLeafPart1 = new ModelRenderer[3];
	private ModelRenderer[] rightSkirtLeafPart2 = new ModelRenderer[3];
	private ModelRenderer[] leftSkirtLeafPart2 = new ModelRenderer[3];
	private ModelRenderer[] rightSkirtLeafPart3 = new ModelRenderer[3];
	private ModelRenderer[] leftSkirtLeafPart3 = new ModelRenderer[3];
	private ModelRenderer rightTentaclePartA;
	private ModelRenderer rightTentaclePartB;
	private ModelRenderer rightTentaclePartC;
	private ModelRenderer rightTentaclePartD;
	private ModelRenderer leftTentaclePartA;
	private ModelRenderer leftTentaclePartB;
	private ModelRenderer leftTentaclePartC;
	private ModelRenderer leftTentaclePartD;
	private ModelRenderer rightTentacleLeafA;
	private ModelRenderer rightTentacleLeafB;
	private ModelRenderer rightTentacleLeafC;
	private ModelRenderer rightTentacleLeafD;
	private ModelRenderer leftTentacleLeafA;
	private ModelRenderer leftTentacleLeafB;
	private ModelRenderer leftTentacleLeafC;
	private ModelRenderer leftTentacleLeafD;
	private ModelRenderer rightTentacleFlowerA;
	private ModelRenderer rightTentacleFlowerB;
	private ModelRenderer rightTentacleFlowerC;
	private ModelRenderer rightTentacleFlowerD;
	private ModelRenderer leftTentacleFlowerA;
	private ModelRenderer leftTentacleFlowerB;
	private ModelRenderer leftTentacleFlowerC;
	private ModelRenderer leftTentacleFlowerD;
	private ModelRenderer rightLegPart;
	private ModelRenderer leftLegPart;

	public ArurauneModel()
	{
		this(0.0F);
	}

	public ArurauneModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false);

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F, 0.0F);

		this.skirt1 = new ModelRenderer(this, 0, 38);
		this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSize);
		this.skirt1.setPos(0.0F, 11.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 44);
		this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 9.0F, 5.0F, modelSize);
		this.skirt2.setPos(0.0F, 12.0F, 0.0F);
		this.body.addChild(this.skirt2);

		this.rightHair = new ModelRenderer(this, 48, 64);
		this.rightHair.addBox(-0.5F, -1.0F, 0.0F, 1.0F, 10.0F, 1.0F, modelSize + 0.25F);
		this.rightHair.setPos(-4.0F, -8.0F, 1.5F);
		this.head.addChild(this.rightHair);
		this.leftHair = new ModelRenderer(this, 48, 64);
		this.leftHair.mirror = true;
		this.leftHair.addBox(-0.5F, -1.0F, 0.0F, 1.0F, 10.0F, 1.0F, modelSize + 0.25F);
		this.leftHair.setPos(4.0F, -8.0F, 1.5F);
		this.head.addChild(this.leftHair);

		this.rightHair2 = new ModelRenderer(this, 32, 48);
		this.rightHair2.addBox(-1.0F, -0.5F, -0.5F, 2.0F, 6.0F, 1.0F, modelSize);
		this.rightHair2.setPos(0.0F, 9.5F, 0.0F);
		this.rightHair.addChild(this.rightHair2);
		this.leftHair2 = new ModelRenderer(this, 32, 48);
		this.leftHair2.mirror = true;
		this.leftHair2.addBox(-1.0F, -0.5F, -0.5F, 2.0F, 6.0F, 1.0F, modelSize);
		this.leftHair2.setPos(0.0F, 9.5F, 0.0F);
		this.leftHair.addChild(this.leftHair2);

		this.rightHair3 = new ModelRenderer(this, 32, 56);
		this.rightHair3.addBox(-1.0F, -0.5F, -0.5F, 2.0F, 5.0F, 1.0F, modelSize);
		this.rightHair3.setPos(0.0F, 5.5F, 0.0F);
		this.rightHair2.addChild(this.rightHair3);
		this.leftHair3 = new ModelRenderer(this, 32, 56);
		this.leftHair3.mirror = true;
		this.leftHair3.addBox(-1.0F, -0.5F, -0.5F, 2.0F, 5.0F, 1.0F, modelSize);
		this.leftHair3.setPos(0.0F, 5.5F, 0.0F);
		this.leftHair2.addChild(this.leftHair3);

		this.rightHairFlowerA = new ModelRenderer(this, 40, 80);
		this.rightHairFlowerA.addBox(-5.25F, -5.25F, -0.5F, 5, 5, 1, modelSize);
		this.rightHairFlowerA.setPos(-0.125F, 0.5F, 0.25F);
		this.rightHair.addChild(this.rightHairFlowerA);
		this.rightHairFlowerB = new ModelRenderer(this, 40, 88);
		this.rightHairFlowerB.addBox(-5.25F, 0.25F, -0.5F, 5, 5, 1, modelSize);
		this.rightHairFlowerB.setPos(-0.125F, 0.5F, 0.25F);
		this.rightHair.addChild(this.rightHairFlowerB);
		this.rightHairFlowerC = new ModelRenderer(this, 40, 96);
		this.rightHairFlowerC.addBox(0.25F, -5.25F, -0.5F, 5, 5, 1, modelSize);
		this.rightHairFlowerC.setPos(-0.125F, 0.5F, 0.25F);
		this.rightHair.addChild(this.rightHairFlowerC);
		this.rightHairFlowerD = new ModelRenderer(this, 40, 104);
		this.rightHairFlowerD.addBox(0.25F, 0.25F, -0.5F, 5, 5, 1, modelSize);
		this.rightHairFlowerD.setPos(-0.125F, 0.5F, 0.25F);
		this.rightHair.addChild(this.rightHairFlowerD);

		this.leftHairFlowerA = new ModelRenderer(this, 40, 80);
		this.leftHairFlowerA.addBox(-5.25F, -5.25F, -0.5F, 5, 5, 1, modelSize);
		this.leftHairFlowerA.setPos(0.125F, 0.5F, 0.25F);
		this.leftHair.addChild(this.leftHairFlowerA);
		this.leftHairFlowerB = new ModelRenderer(this, 40, 88);
		this.leftHairFlowerB.addBox(-5.25F, 0.25F, -0.5F, 5, 5, 1, modelSize);
		this.leftHairFlowerB.setPos(0.125F, 0.5F, 0.25F);
		this.leftHair.addChild(this.leftHairFlowerB);
		this.leftHairFlowerC = new ModelRenderer(this, 40, 96);
		this.leftHairFlowerC.addBox(0.25F, -5.25F, -0.5F, 5, 5, 1, modelSize);
		this.leftHairFlowerC.setPos(0.125F, 0.5F, 0.25F);
		this.leftHair.addChild(this.leftHairFlowerC);
		this.leftHairFlowerD = new ModelRenderer(this, 40, 104);
		this.leftHairFlowerD.addBox(0.25F, 0.25F, -0.5F, 5, 5, 1, modelSize);
		this.leftHairFlowerD.setPos(0.125F, 0.5F, 0.25F);
		this.leftHair.addChild(this.leftHairFlowerD);

		this.hairPart = new ModelRenderer(this, 0, 112);
		this.hairPart.addBox(-4.0F, 0.0F, -1.0F, 8.0F, 2.0F, 1.0F, modelSize);
		this.hairPart.setPos(0.0F, 0.0F, 4.0F);
		this.head.addChild(this.hairPart);

		this.headwearPart = new ModelRenderer(this, 32, 112);
		this.headwearPart.addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, modelSize + 0.5F);
		this.headwearPart.setPos(0.0F, 3.0F, 0.0F);
		this.hat.addChild(this.headwearPart);

		for (int i = 0; i < this.rightSkirtLeafPart1.length; ++i)
		{
			this.rightSkirtLeafPart1[i] = new ModelRenderer(this, 0, 64);
			this.rightSkirtLeafPart1[i].addBox(-2.5F, 0.5F, 0.0F, 5.0F, 6.0F, 1.0F, modelSize);
			this.rightSkirtLeafPart1[i].setPos(-4.0F, 12.0F, (float)(i - 1) * 2.0F);
			this.body.addChild(this.rightSkirtLeafPart1[i]);
			this.leftSkirtLeafPart1[i] = new ModelRenderer(this, 0, 64);
			this.leftSkirtLeafPart1[i].mirror = true;
			this.leftSkirtLeafPart1[i].addBox(-2.5F, 0.5F, 0.0F, 5.0F, 6.0F, 1.0F, modelSize);
			this.leftSkirtLeafPart1[i].setPos(4.0F, 12.0F, (float)(i - 1) * 2.0F);
			this.body.addChild(this.leftSkirtLeafPart1[i]);

			this.rightSkirtLeafPart2[i] = new ModelRenderer(this, 40, 48);
			this.rightSkirtLeafPart2[i].addBox(-2.5F, 0.0F, 0.0F, 5.0F, 5.0F, 1.0F, modelSize);
			this.rightSkirtLeafPart2[i].setPos(0.0F, 6.5F, 0.0F);
			this.rightSkirtLeafPart1[i].addChild(this.rightSkirtLeafPart2[i]);
			this.leftSkirtLeafPart2[i] = new ModelRenderer(this, 40, 48);
			this.leftSkirtLeafPart2[i].mirror = true;
			this.leftSkirtLeafPart2[i].addBox(-2.5F, 0.0F, 0.0F, 5.0F, 5.0F, 1.0F, modelSize);
			this.leftSkirtLeafPart2[i].setPos(0.0F, 6.5F, 0.0F);
			this.leftSkirtLeafPart1[i].addChild(this.leftSkirtLeafPart2[i]);

			this.rightSkirtLeafPart3[i] = new ModelRenderer(this, 40, 56);
			this.rightSkirtLeafPart3[i].addBox(-2.5F, 0.0F, 0.0F, 5.0F, 4.0F, 1.0F, modelSize);
			this.rightSkirtLeafPart3[i].setPos(0.0F, 5.0F, 0.0F);
			this.rightSkirtLeafPart2[i].addChild(this.rightSkirtLeafPart3[i]);
			this.leftSkirtLeafPart3[i] = new ModelRenderer(this, 40, 56);
			this.leftSkirtLeafPart3[i].mirror = true;
			this.leftSkirtLeafPart3[i].addBox(-2.5F, 0.0F, 0.0F, 5.0F, 4.0F, 1.0F, modelSize);
			this.leftSkirtLeafPart3[i].setPos(0.0F, 5.0F, 0.0F);
			this.leftSkirtLeafPart2[i].addChild(this.leftSkirtLeafPart3[i]);
		}

		this.rightTentaclePartA = new ModelRenderer(this, 24, 64);
		this.rightTentaclePartA.addBox(-0.5F, -5.75F, -0.5F, 1.0F, 6.0F, 1.0F, modelSize + 0.25F);
		this.rightTentaclePartA.setPos(-2.0F, 10.5F, 1.5F);
		this.body.addChild(this.rightTentaclePartA);
		this.leftTentaclePartA = new ModelRenderer(this, 24, 64);
		this.leftTentaclePartA.mirror = true;
		this.leftTentaclePartA.addBox(-0.5F, -5.75F, -0.5F, 1.0F, 6.0F, 1.0F, modelSize + 0.25F);
		this.leftTentaclePartA.setPos(2.0F, 10.5F, 1.5F);
		this.body.addChild(this.leftTentaclePartA);

		this.rightTentaclePartB = new ModelRenderer(this, 28, 64);
		this.rightTentaclePartB.addBox(-0.5F, -7.75F, -0.5F, 1, 8, 1, modelSize);
		this.rightTentaclePartB.setPos(0.0F, -5.75F, 0.0F);
		this.rightTentaclePartA.addChild(this.rightTentaclePartB);
		this.leftTentaclePartB = new ModelRenderer(this, 28, 64);
		this.leftTentaclePartB.mirror = true;
		this.leftTentaclePartB.addBox(-0.5F, -7.75F, -0.5F, 1, 8, 1, modelSize);
		this.leftTentaclePartB.setPos(0.0F, -5.75F, 0.0F);
		this.leftTentaclePartA.addChild(this.leftTentaclePartB);

		this.rightTentaclePartC = new ModelRenderer(this, 28, 64);
		this.rightTentaclePartC.addBox(-0.5F, -7.75F, -0.5F, 1, 8, 1, modelSize);
		this.rightTentaclePartC.setPos(0.0F, -7.75F, 0.0F);
		this.rightTentaclePartB.addChild(this.rightTentaclePartC);
		this.leftTentaclePartC = new ModelRenderer(this, 28, 64);
		this.leftTentaclePartC.mirror = true;
		this.leftTentaclePartC.addBox(-0.5F, -7.75F, -0.5F, 1, 8, 1, modelSize);
		this.leftTentaclePartC.setPos(0.0F, -7.75F, 0.0F);
		this.leftTentaclePartB.addChild(this.leftTentaclePartC);

		this.rightTentaclePartD = new ModelRenderer(this, 28, 64);
		this.rightTentaclePartD.addBox(-0.5F, -7.75F, -0.5F, 1, 8, 1, modelSize + 0.25F);
		this.rightTentaclePartD.setPos(0.0F, -7.75F, 0.0F);
		this.rightTentaclePartC.addChild(this.rightTentaclePartD);
		this.leftTentaclePartD = new ModelRenderer(this, 28, 64);
		this.leftTentaclePartD.mirror = true;
		this.leftTentaclePartD.addBox(-0.5F, -7.75F, -0.5F, 1, 8, 1, modelSize + 0.25F);
		this.leftTentaclePartD.setPos(0.0F, -7.75F, 0.0F);
		this.leftTentaclePartC.addChild(this.leftTentaclePartD);

		this.rightTentacleFlowerA = new ModelRenderer(this, 24, 80);
		this.rightTentacleFlowerA.addBox(-7.25F, -7.25F, -0.5F, 7, 7, 1, modelSize);
		this.rightTentacleFlowerA.setPos(0.0F, -7.5F, 0.0F);
		this.rightTentaclePartD.addChild(this.rightTentacleFlowerA);
		this.rightTentacleFlowerB = new ModelRenderer(this, 24, 88);
		this.rightTentacleFlowerB.addBox(-7.25F, 0.25F, -0.5F, 7, 7, 1, modelSize);
		this.rightTentacleFlowerB.setPos(0.0F, -7.5F, 0.0F);
		this.rightTentaclePartD.addChild(this.rightTentacleFlowerB);
		this.rightTentacleFlowerC = new ModelRenderer(this, 24, 96);
		this.rightTentacleFlowerC.addBox(0.25F, -7.25F, -0.5F, 7, 7, 1, modelSize);
		this.rightTentacleFlowerC.setPos(0.0F, -7.5F, 0.0F);
		this.rightTentaclePartD.addChild(this.rightTentacleFlowerC);
		this.rightTentacleFlowerD = new ModelRenderer(this, 24, 104);
		this.rightTentacleFlowerD.addBox(0.25F, 0.25F, -0.5F, 7, 7, 1, modelSize);
		this.rightTentacleFlowerD.setPos(0.0F, -7.5F, 0.0F);
		this.rightTentaclePartD.addChild(this.rightTentacleFlowerD);

		this.leftTentacleFlowerA = new ModelRenderer(this, 24, 80);
		this.leftTentacleFlowerA.addBox(-7.25F, -7.25F, -0.5F, 7, 7, 1, modelSize);
		this.leftTentacleFlowerA.setPos(0.0F, -7.5F, 0.0F);
		this.leftTentaclePartD.addChild(this.leftTentacleFlowerA);
		this.leftTentacleFlowerB = new ModelRenderer(this, 24, 88);
		this.leftTentacleFlowerB.addBox(-7.25F, 0.25F, -0.5F, 7, 7, 1, modelSize);
		this.leftTentacleFlowerB.setPos(0.0F, -7.5F, 0.0F);
		this.leftTentaclePartD.addChild(this.leftTentacleFlowerB);
		this.leftTentacleFlowerC = new ModelRenderer(this, 24, 96);
		this.leftTentacleFlowerC.addBox(0.25F, -7.25F, -0.5F, 7, 7, 1, modelSize);
		this.leftTentacleFlowerC.setPos(0.0F, -7.5F, 0.0F);
		this.leftTentaclePartD.addChild(this.leftTentacleFlowerC);
		this.leftTentacleFlowerD = new ModelRenderer(this, 24, 104);
		this.leftTentacleFlowerD.addBox(0.25F, 0.25F, -0.5F, 7, 7, 1, modelSize);
		this.leftTentacleFlowerD.setPos(0.0F, -7.5F, 0.0F);
		this.leftTentaclePartD.addChild(this.leftTentacleFlowerD);

		this.rightTentacleLeafA = new ModelRenderer(this, 0, 72);
		this.rightTentacleLeafA.addBox(-8.25F, -8.25F, -0.5F, 8, 8, 1, modelSize);
		this.rightTentacleLeafA.setPos(0.0F, -7.5F, 0.0F);
		this.rightTentaclePartD.addChild(this.rightTentacleLeafA);
		this.rightTentacleLeafB = new ModelRenderer(this, 0, 82);
		this.rightTentacleLeafB.addBox(-8.25F, 0.25F, -0.5F, 8, 8, 1, modelSize);
		this.rightTentacleLeafB.setPos(0.0F, -7.5F, 0.0F);
		this.rightTentaclePartD.addChild(this.rightTentacleLeafB);
		this.rightTentacleLeafC = new ModelRenderer(this, 0, 92);
		this.rightTentacleLeafC.addBox(0.25F, -8.25F, -0.5F, 8, 8, 1, modelSize);
		this.rightTentacleLeafC.setPos(0.0F, -7.5F, 0.0F);
		this.rightTentaclePartD.addChild(this.rightTentacleLeafC);
		this.rightTentacleLeafD = new ModelRenderer(this, 0, 102);
		this.rightTentacleLeafD.addBox(0.25F, 0.25F, -0.5F, 8, 8, 1, modelSize);
		this.rightTentacleLeafD.setPos(0.0F, -7.5F, 0.0F);
		this.rightTentaclePartD.addChild(this.rightTentacleLeafD);

		this.leftTentacleLeafA = new ModelRenderer(this, 0, 72);
		this.leftTentacleLeafA.addBox(-8.25F, -8.25F, -0.5F, 8, 8, 1, modelSize);
		this.leftTentacleLeafA.setPos(0.0F, -7.5F, 0.0F);
		this.leftTentaclePartD.addChild(this.leftTentacleLeafA);
		this.leftTentacleLeafB = new ModelRenderer(this, 0, 82);
		this.leftTentacleLeafB.addBox(-8.25F, 0.25F, -0.5F, 8, 8, 1, modelSize);
		this.leftTentacleLeafB.setPos(0.0F, -7.5F, 0.0F);
		this.leftTentaclePartD.addChild(this.leftTentacleLeafB);
		this.leftTentacleLeafC = new ModelRenderer(this, 0, 92);
		this.leftTentacleLeafC.addBox(0.25F, -8.25F, -0.5F, 8, 8, 1, modelSize);
		this.leftTentacleLeafC.setPos(0.0F, -7.5F, 0.0F);
		this.leftTentaclePartD.addChild(this.leftTentacleLeafC);
		this.leftTentacleLeafD = new ModelRenderer(this, 0, 102);
		this.leftTentacleLeafD.addBox(0.25F, 0.25F, -0.5F, 8, 8, 1, modelSize);
		this.leftTentacleLeafD.setPos(0.0F, -7.5F, 0.0F);
		this.leftTentaclePartD.addChild(this.leftTentacleLeafD);

		this.rightLegPart = new ModelRenderer(this, 32, 64);
		this.rightLegPart.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, modelSize + 0.5F);
		this.rightLegPart.setPos(0.0F, 6.0F, 0.0F);
		this.rightLeg.addChild(this.rightLegPart);
		this.leftLegPart = new ModelRenderer(this, 32, 64);
		this.leftLegPart.mirror = true;
		this.leftLegPart.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, modelSize + 0.5F);
		this.leftLegPart.setPos(0.0F, 6.0F, 0.0F);
		this.leftLeg.addChild(this.leftLegPart);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.rightArm.zRot = ((float)Math.PI / 8.0F);
		this.leftArm.zRot = -((float)Math.PI / 8.0F);
		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.12F) * 0.06F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.12F) * 0.06F;

		this.hairPart.xRot = (float)Math.PI / 12.0F;
		this.hairPart.xRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.03F;

		this.rightHair.xRot = ((float)Math.PI / 18.0F);
		this.leftHair.xRot = ((float)Math.PI / 18.0F);
		this.rightHair.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.045F;
		this.leftHair.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.045F;
		this.rightHair.zRot = ((float)Math.PI / 9.0F);
		this.leftHair.zRot = -((float)Math.PI / 9.0F);
		this.rightHair.zRot -= MathHelper.sin(ageInTicks * 0.09F) * 0.045F;
		this.leftHair.zRot += MathHelper.sin(ageInTicks * 0.09F) * 0.045F;
		this.rightHair2.yRot = ((float)Math.PI / 3.0F);
		this.leftHair2.yRot = -((float)Math.PI / 3.0F);
		this.rightHair2.xRot = -((float)Math.PI / 18.0F);
		this.leftHair2.xRot = -((float)Math.PI / 18.0F);
		this.rightHair2.xRot -= MathHelper.sin(ageInTicks * 0.06F - (float)Math.PI / 3.0F) * 0.015F;
		this.leftHair2.xRot += MathHelper.sin(ageInTicks * 0.06F - (float)Math.PI / 3.0F) * 0.015F;
		this.rightHair2.zRot = -((float)Math.PI / 12.0F);
		this.leftHair2.zRot = ((float)Math.PI / 12.0F);
		this.rightHair2.zRot -= MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 3.0F) * 0.06F;
		this.leftHair2.zRot += MathHelper.sin(ageInTicks * 0.045F - (float)Math.PI / 3.0F) * 0.06F;
		this.rightHair3.xRot = ((float)Math.PI / 9.0F);
		this.leftHair3.xRot = ((float)Math.PI / 9.0F);
		this.rightHair3.xRot -= MathHelper.sin(ageInTicks * 0.06F - (float)Math.PI / 4.0F) * 0.06F;
		this.leftHair3.xRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 4.0F) * 0.06F;

		float f = ((float)Math.PI / 15.0F) + MathHelper.sin(ageInTicks * 0.075F) * 0.045F;
		float f1 = ((float)Math.PI * 7.0F / 15.0F);

		this.rightHairFlowerA.xRot = f;
		this.rightHairFlowerB.xRot = -f;
		this.rightHairFlowerC.xRot = f;
		this.rightHairFlowerD.xRot = -f;
		this.leftHairFlowerA.xRot = f;
		this.leftHairFlowerB.xRot = -f;
		this.leftHairFlowerC.xRot = f;
		this.leftHairFlowerD.xRot = -f;
		this.rightHairFlowerA.yRot = f1 - f;
		this.rightHairFlowerB.yRot = f1 - f;
		this.rightHairFlowerC.yRot = f1 + f;
		this.rightHairFlowerD.yRot = f1 + f;
		this.leftHairFlowerA.yRot = -f1 - f;
		this.leftHairFlowerB.yRot = -f1 - f;
		this.leftHairFlowerC.yRot = -f1 + f;
		this.leftHairFlowerD.yRot = -f1 + f;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
		}

		f1 = (float)Math.PI / 2.0F;

		for (int i = 0; i < this.rightSkirtLeafPart1.length; ++i)
		{
			this.rightSkirtLeafPart1[i].yRot = (float)Math.PI * ((float)(i - 1)) / 3.0F + f1;
			this.rightSkirtLeafPart1[i].xRot = (float)Math.PI * 2.0F / 5.0F - f1;
			this.rightSkirtLeafPart1[i].xRot += MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI * (float)i / 3.0F) * 0.075F;
			this.rightSkirtLeafPart2[i].xRot = -((float)Math.PI * 2.0F / 7.0F);
			this.rightSkirtLeafPart2[i].xRot -= MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI * (float)(i - 1) / 3.0F) * 0.066F;
			this.rightSkirtLeafPart3[i].xRot = -((float)Math.PI * 3.0F / 5.0F);
			this.rightSkirtLeafPart3[i].xRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI * (float)i / 3.0F) * 0.081F;

			this.leftSkirtLeafPart1[i].yRot = -((float)Math.PI * ((float)(i - 1)) / 3.0F + f1);
			this.leftSkirtLeafPart1[i].xRot = (float)Math.PI * 2.0F / 5.0F - f1;
			this.leftSkirtLeafPart1[i].xRot += MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI * (float)i / 3.0F) * 0.075F;
			this.leftSkirtLeafPart2[i].xRot = -((float)Math.PI * 2.0F / 7.0F);
			this.leftSkirtLeafPart2[i].xRot -= MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI * (float)(i - 1) / 3.0F) * 0.066F;
			this.leftSkirtLeafPart3[i].xRot = -((float)Math.PI * 3.0F / 5.0F);
			this.leftSkirtLeafPart3[i].xRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI * (float)i / 3.0F) * 0.081F;
		}

		this.rightTentaclePartA.zRot = -((float)Math.PI / 6.0F);
		this.leftTentaclePartA.zRot = ((float)Math.PI / 6.0F);
		this.rightTentaclePartA.zRot += MathHelper.cos(ageInTicks * 0.048F - (float)Math.PI / 2.0F) * 0.021F;
		this.leftTentaclePartA.zRot += MathHelper.cos(ageInTicks * 0.048F) * 0.021F;
		this.rightTentaclePartA.xRot = -((float)Math.PI * 2.0F / 5.0F);
		this.leftTentaclePartA.xRot = -((float)Math.PI * 2.0F / 5.0F);
		this.rightTentaclePartA.xRot += MathHelper.sin(ageInTicks * 0.048F + (float)Math.PI / 3.0F) * 0.075F;
		this.leftTentaclePartA.xRot += MathHelper.sin(ageInTicks * 0.048F) * 0.075F;
		this.rightTentaclePartB.zRot = -((float)Math.PI / 18.0F);
		this.leftTentaclePartB.zRot = ((float)Math.PI / 18.0F);
		this.rightTentaclePartB.zRot += MathHelper.cos(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.018F;
		this.leftTentaclePartB.zRot += MathHelper.cos(ageInTicks * 0.06F + (float)Math.PI * 2.0F / 3.0F) * 0.018F;
		this.rightTentaclePartB.xRot = ((float)Math.PI * 2.0F / 7.0F);
		this.leftTentaclePartB.xRot = ((float)Math.PI * 2.0F / 7.0F);
		this.rightTentaclePartB.xRot += MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 2.0F) * 0.069F;
		this.leftTentaclePartB.xRot += MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 2.0F + (float)Math.PI / 3.0F) * 0.069F;
		this.rightTentaclePartC.zRot = -((float)Math.PI / 18.0F);
		this.leftTentaclePartC.zRot = ((float)Math.PI / 18.0F);
		this.rightTentaclePartC.zRot += MathHelper.cos(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.012F;
		this.leftTentaclePartC.zRot += MathHelper.cos(ageInTicks * 0.06F + (float)Math.PI * 2.0F / 3.0F) * 0.012F;
		this.rightTentaclePartC.xRot = ((float)Math.PI / 5.0F);
		this.leftTentaclePartC.xRot = ((float)Math.PI / 5.0F);
		this.rightTentaclePartC.xRot += MathHelper.sin(ageInTicks * 0.039F - (float)Math.PI / 4.0F) * 0.072F;
		this.leftTentaclePartC.xRot += MathHelper.sin(ageInTicks * 0.039F - (float)Math.PI / 4.0F + (float)Math.PI / 3.0F) * 0.072F;
		this.rightTentaclePartD.xRot = ((float)Math.PI / 6.0F);
		this.leftTentaclePartD.xRot = ((float)Math.PI / 6.0F);
		this.rightTentaclePartD.xRot += MathHelper.sin(ageInTicks * 0.042F - (float)Math.PI / 3.0F) * 0.06F;
		this.leftTentaclePartD.xRot += MathHelper.sin(ageInTicks * 0.042F - (float)Math.PI / 3.0F + (float)Math.PI / 5.0F) * 0.06F;
		this.rightTentaclePartD.yRot = MathHelper.cos(ageInTicks * 0.072F + (float)Math.PI / 2.0F) * 0.12F;
		this.leftTentaclePartD.yRot = -(MathHelper.cos(ageInTicks * 0.072F + (float)Math.PI / 2.0F) * 0.12F);

		f = ((float)Math.PI / 15.0F) + MathHelper.sin(ageInTicks * 0.075F + (float)Math.PI / 4.0F) * 0.075F;
		f1 = (float)Math.PI * 8.0F / 15.0F;

		if (this.attackTime > 0.0F)
		{
			float f2 = 1.0F - this.attackTime;
			f2 = f2 * f2;
			f2 = f2 * f2;
			f2 = 1.0F - f2;
			float f3 = MathHelper.sin(f2 * (float)Math.PI);
			float f4 = MathHelper.sin(this.attackTime * (float)Math.PI) * 0.1F;
			this.rightTentaclePartB.xRot += f3 * 0.051F + f4;
			this.leftTentaclePartB.xRot += f3 * 0.051F + f4;
			this.rightTentaclePartC.xRot += f3 * 0.06F + f4;
			this.leftTentaclePartC.xRot += f3 * 0.06F + f4;
			this.rightTentaclePartD.xRot += f3 * 0.06F + f4;
			this.leftTentaclePartD.xRot += f3 * 0.06F + f4;

			f += f3 * 0.9F + f4 * 0.5F;
		}

		this.rightTentacleFlowerA.xRot = -f1 + f;
		this.rightTentacleFlowerB.xRot = -f1 - f;
		this.rightTentacleFlowerC.xRot = -f1 + f;
		this.rightTentacleFlowerD.xRot = -f1 - f;
		this.leftTentacleFlowerA.xRot = -f1 + f;
		this.leftTentacleFlowerB.xRot = -f1 - f;
		this.leftTentacleFlowerC.xRot = -f1 + f;
		this.leftTentacleFlowerD.xRot = -f1 - f;
		this.rightTentacleFlowerA.zRot = f;
		this.rightTentacleFlowerB.zRot = f;
		this.rightTentacleFlowerC.zRot = -f;
		this.rightTentacleFlowerD.zRot = -f;
		this.leftTentacleFlowerA.zRot = f;
		this.leftTentacleFlowerB.zRot = f;
		this.leftTentacleFlowerC.zRot = -f;
		this.leftTentacleFlowerD.zRot = -f;

		this.rightTentacleLeafA.xRot = -f1 - f;
		this.rightTentacleLeafB.xRot = -f1 + f;
		this.rightTentacleLeafC.xRot = -f1 - f;
		this.rightTentacleLeafD.xRot = -f1 + f;
		this.leftTentacleLeafA.xRot = -f1 - f;
		this.leftTentacleLeafB.xRot = -f1 + f;
		this.leftTentacleLeafC.xRot = -f1 - f;
		this.leftTentacleLeafD.xRot = -f1 + f;
		this.rightTentacleLeafA.zRot = -f;
		this.rightTentacleLeafB.zRot = -f;
		this.rightTentacleLeafC.zRot = f;
		this.rightTentacleLeafD.zRot = f;
		this.leftTentacleLeafA.zRot = -f;
		this.leftTentacleLeafB.zRot = -f;
		this.leftTentacleLeafC.zRot = f;
		this.leftTentacleLeafD.zRot = f;

		f1 = (float)Math.PI / 4.0F;

		this.rightTentacleLeafA.yRot = f1;
		this.rightTentacleLeafB.yRot = f1;
		this.rightTentacleLeafC.yRot = f1;
		this.rightTentacleLeafD.yRot = f1;
		this.leftTentacleLeafA.yRot = -f1;
		this.leftTentacleLeafB.yRot = -f1;
		this.leftTentacleLeafC.yRot = -f1;
		this.leftTentacleLeafD.yRot = -f1;
	}
}
