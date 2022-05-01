package com.github.mechalopa.hmag.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NecroticReaperModel<T extends MobEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer bodyPart3;
	private ModelRenderer bodyPart4;
	private ModelRenderer bodyPart5;
	private ModelRenderer bodyPart6RightA;
	private ModelRenderer bodyPart6LeftA;
	private ModelRenderer bodyPart6RightB;
	private ModelRenderer bodyPart6LeftB;
	private ModelRenderer bodyPart6RightC;
	private ModelRenderer bodyPart6LeftC;
	private ModelRenderer clothPart;
	private ModelRenderer rightHair;
	private ModelRenderer leftHair;
	private ModelRenderer rightHair2;
	private ModelRenderer leftHair2;
	private ModelRenderer rightHair3;
	private ModelRenderer leftHair3;
	private ModelRenderer rightHair4;
	private ModelRenderer leftHair4;
	private ModelRenderer tailPart1;
	private ModelRenderer tailPart2;
	private ModelRenderer tailPart3;
	private ModelRenderer tailPart4;
	private ModelRenderer tailPart1Fin;
	private ModelRenderer tailPart2Fin;
	private ModelRenderer hairBand;
	private ModelRenderer hairPart;
	private ModelRenderer headwearPart;
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer[] rightSkirtPart = new ModelRenderer[7];
	private ModelRenderer[] leftSkirtPart = new ModelRenderer[7];
	private ModelRenderer rightArmPart1;
	private ModelRenderer leftArmPart1;
	private ModelRenderer rightArmPart2;
	private ModelRenderer leftArmPart2;
	private ModelRenderer rightArmPart3;
	private ModelRenderer leftArmPart3;
	private ModelRenderer rightArmPart4A;
	private ModelRenderer leftArmPart4A;
	private ModelRenderer rightArmPart4B;
	private ModelRenderer leftArmPart4B;
	private ModelRenderer rightArmPart5;
	private ModelRenderer leftArmPart5;
	private ModelRenderer rightArmPart6;
	private ModelRenderer leftArmPart6;
	private ModelRenderer rightLegPart1;
	private ModelRenderer leftLegPart1;
	private ModelRenderer rightLegPart2;
	private ModelRenderer leftLegPart2;
	private ModelRenderer rightLegPart3;
	private ModelRenderer leftLegPart3;

	public NecroticReaperModel()
	{
		this(0.0F);
	}

	public NecroticReaperModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false, false);

		this.bodyPart1 = new ModelRenderer(this, 32, 32);
		this.bodyPart1.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.bodyPart1.setPos(0.0F, 3.75F, 0.5F);
		this.body.addChild(this.bodyPart1);
		this.bodyPart2 = new ModelRenderer(this, 40, 32);
		this.bodyPart2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, modelSize + 0.001F);
		this.bodyPart2.setPos(0.0F, 2.5F, 0.0F);
		this.bodyPart1.addChild(this.bodyPart2);
		this.bodyPart3 = new ModelRenderer(this, 48, 32);
		this.bodyPart3.addBox(-2.5F, 0.0F, -1.0F, 5.0F, 2.0F, 2.0F, modelSize);
		this.bodyPart3.setPos(0.0F, 9.0F, 0.0F);
		this.body.addChild(this.bodyPart3);
		this.bodyPart4 = new ModelRenderer(this, 48, 36);
		this.bodyPart4.addBox(-2.5F, -1.0F, -1.0F, 5.0F, 1.0F, 2.0F, modelSize);
		this.bodyPart4.setPos(0.0F, 0.0F, 0.0F);
		this.bodyPart3.addChild(this.bodyPart4);
		this.bodyPart5 = new ModelRenderer(this, 32, 40);
		this.bodyPart5.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 2.0F, 3.0F, modelSize);
		this.bodyPart5.setPos(0.0F, 1.0F, 0.0F);
		this.bodyPart3.addChild(this.bodyPart5);

		this.bodyPart6RightA = new ModelRenderer(this, 50, 40);
		this.bodyPart6RightA.addBox(0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, modelSize);
		this.bodyPart6RightA.setPos(0.5F, 0.5F, 0.5F);
		this.bodyPart1.addChild(this.bodyPart6RightA);
		this.bodyPart6LeftA = new ModelRenderer(this, 50, 40);
		this.bodyPart6LeftA.mirror = true;
		this.bodyPart6LeftA.addBox(-1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, modelSize);
		this.bodyPart6LeftA.setPos(-0.5F, 0.5F, 0.5F);
		this.bodyPart1.addChild(this.bodyPart6LeftA);

		this.bodyPart6RightB = new ModelRenderer(this, 50, 40);
		this.bodyPart6RightB.addBox(0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, modelSize);
		this.bodyPart6RightB.setPos(0.5F, 2.5F, 0.5F);
		this.bodyPart1.addChild(this.bodyPart6RightB);
		this.bodyPart6LeftB = new ModelRenderer(this, 50, 40);
		this.bodyPart6LeftB.mirror = true;
		this.bodyPart6LeftB.addBox(-1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, modelSize);
		this.bodyPart6LeftB.setPos(-0.5F, 2.5F, 0.5F);
		this.bodyPart1.addChild(this.bodyPart6LeftB);

		this.bodyPart6RightC = new ModelRenderer(this, 50, 40);
		this.bodyPart6RightC.addBox(0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, modelSize);
		this.bodyPart6RightC.setPos(0.5F, 2.0F, 0.5F);
		this.bodyPart2.addChild(this.bodyPart6RightC);
		this.bodyPart6LeftC = new ModelRenderer(this, 50, 40);
		this.bodyPart6LeftC.mirror = true;
		this.bodyPart6LeftC.addBox(-1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, modelSize);
		this.bodyPart6LeftC.setPos(-0.5F, 2.0F, 0.5F);
		this.bodyPart2.addChild(this.bodyPart6LeftC);

		this.bust = new ModelRenderer(this, 0, 32);
		this.bust.addBox(-3.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, modelSize - 0.001F);
		this.bust.setPos(0.0F, 3.5F, -1.1F);
		this.body.addChild(this.bust);

		this.clothPart = new ModelRenderer(this, 16, 24);
		this.clothPart.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 4.0F, 3.0F, modelSize);
		this.clothPart.setPos(0.0F, 4.0F, 0.0F);
		this.body.addChild(this.clothPart);

		this.skirt1 = new ModelRenderer(this, 0, 74);
		this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSize);
		this.skirt1.setPos(0.0F, 11.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 80);
		this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, modelSize);
		this.skirt2.setPos(0.0F, 11.5F, 0.0F);
		this.body.addChild(this.skirt2);

		for (int i = 0; i < this.rightSkirtPart.length; ++i)
		{
			float f = (float)(i - 3) * ((float)Math.PI / 7.0F);
			float f1 = MathHelper.sin(f - ((float)Math.PI / 2.0F)) * 5.5F;
			float f2 = MathHelper.cos(f - ((float)Math.PI / 2.0F)) * 3.5F;
			this.rightSkirtPart[i] = new ModelRenderer(this, 0, 88);
			this.rightSkirtPart[i].addBox(-1.5F, 0.5F, -0.25F, 3.0F, 6.0F, 2.0F, modelSize);
			this.rightSkirtPart[i].setPos(f1, 11.0F, f2);
			this.body.addChild(this.rightSkirtPart[i]);
			f = (float)(3 - i) * ((float)Math.PI / 7.0F);
			f1 = MathHelper.sin(f + ((float)Math.PI / 2.0F)) * 5.5F;
			f2 = MathHelper.cos(f + ((float)Math.PI / 2.0F)) * 3.5F;
			this.leftSkirtPart[i] = new ModelRenderer(this, 0, 88);
			this.leftSkirtPart[i].mirror = true;
			this.leftSkirtPart[i].addBox(-1.5F, 0.5F, -0.25F, 3.0F, 6.0F, 2.0F, modelSize);
			this.leftSkirtPart[i].setPos(f1, 11.0F, f2);
			this.body.addChild(this.leftSkirtPart[i]);
		}

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(0.0F, -2.0F, -1.5F, 2.0F, 1.0F, 3.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-2.0F, -2.0F, -1.5F, 2.0F, 1.0F, 3.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F, 0.0F);

		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, modelSize);
		this.rightLeg.setPos(-1.75F, 12.0F, 0.0F);
		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, modelSize);
		this.leftLeg.setPos(1.75F, 12.0F, 0.0F);

		this.rightHair = new ModelRenderer(this, 32, 48);
		this.rightHair.addBox(-2.0F, -1.5F, -1.5F, 3.0F, 6.0F, 3.0F, modelSize);
		this.rightHair.setPos(-4.0F, -8.0F, 2.75F);
		this.head.addChild(this.rightHair);
		this.leftHair = new ModelRenderer(this, 32, 48);
		this.leftHair.mirror = true;
		this.leftHair.addBox(-1.0F, -1.5F, -1.5F, 3.0F, 6.0F, 3.0F, modelSize);
		this.leftHair.setPos(4.0F, -8.0F, 2.75F);
		this.head.addChild(this.leftHair);

		this.rightHair2 = new ModelRenderer(this, 48, 48);
		this.rightHair2.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize + 0.125F);
		this.rightHair2.setPos(-0.5F, 5.0F, 0.0F);
		this.rightHair.addChild(this.rightHair2);
		this.leftHair2 = new ModelRenderer(this, 48, 48);
		this.leftHair2.mirror = true;
		this.leftHair2.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize + 0.125F);
		this.leftHair2.setPos(0.5F, 5.0F, 0.0F);
		this.leftHair.addChild(this.leftHair2);

		this.rightHair3 = new ModelRenderer(this, 56, 48);
		this.rightHair3.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.rightHair3.setPos(0.0F, 4.5F, 0.0F);
		this.rightHair2.addChild(this.rightHair3);
		this.leftHair3 = new ModelRenderer(this, 56, 48);
		this.leftHair3.mirror = true;
		this.leftHair3.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.leftHair3.setPos(0.0F, 4.5F, 0.0F);
		this.leftHair2.addChild(this.leftHair3);

		this.rightHair4 = new ModelRenderer(this, 48, 56);
		this.rightHair4.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, modelSize + 0.25F);
		this.rightHair4.setPos(0.0F, 2.5F, 0.0F);
		this.rightHair3.addChild(this.rightHair4);
		this.leftHair4 = new ModelRenderer(this, 48, 56);
		this.leftHair4.mirror = true;
		this.leftHair4.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, modelSize + 0.25F);
		this.leftHair4.setPos(0.0F, 2.5F, 0.0F);
		this.leftHair3.addChild(this.leftHair4);

		this.tailPart1 = new ModelRenderer(this, 32, 80);
		this.tailPart1.addBox(-1.0F, 0.0F, -0.5F, 2.0F, 8.0F, 1.0F, modelSize);
		this.tailPart1.setPos(0.0F, -3.0F, 4.0F);
		this.head.addChild(this.tailPart1);
		this.tailPart2 = new ModelRenderer(this, 38, 80);
		this.tailPart2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 10.0F, 1.0F, modelSize);
		this.tailPart2.setPos(0.0F, 7.75F, 0.0F);
		this.tailPart1.addChild(this.tailPart2);
		this.tailPart3 = new ModelRenderer(this, 42, 80);
		this.tailPart3.addBox(-0.5F, 0.0F, -2.0F, 1.0F, 6.0F, 2.0F, modelSize);
		this.tailPart3.setPos(0.0F, 10.0F, 0.5F);
		this.tailPart2.addChild(this.tailPart3);
		this.tailPart4 = new ModelRenderer(this, 48, 80);
		this.tailPart4.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 6.0F, 1.0F, modelSize);
		this.tailPart4.setPos(0.0F, 6.0F, 0.0F);
		this.tailPart3.addChild(this.tailPart4);

		this.tailPart1Fin = new ModelRenderer(this, 32, 92);
		this.tailPart1Fin.addBox(0.0F, 0.0F, 0.0F, 0.0F, 7.0F, 2.0F, modelSize);
		this.tailPart1Fin.setPos(0.0F, 1.0F, 0.5F);
		this.tailPart1.addChild(this.tailPart1Fin);
		this.tailPart2Fin = new ModelRenderer(this, 40, 92);
		this.tailPart2Fin.addBox(0.0F, 0.0F, 0.0F, 0.0F, 7.0F, 2.0F, modelSize);
		this.tailPart2Fin.setPos(0.0F, 1.0F, 0.5F);
		this.tailPart2.addChild(this.tailPart2Fin);

		this.hairBand = new ModelRenderer(this, 32, 58);
		this.hairBand.addBox(-2.5F, -2.0F, 0.0F, 5.0F, 2.0F, 1.0F, modelSize);
		this.hairBand.setPos(0.0F, -8.0F, 1.0F);
		this.head.addChild(this.hairBand);

		this.hairPart = new ModelRenderer(this, 32, 74);
		this.hairPart.addBox(-4.0F, 0.0F, -1.0F, 8.0F, 3.0F, 1.0F, modelSize);
		this.hairPart.setPos(0.0F, 0.0F, 4.0F);
		this.head.addChild(this.hairPart);

		this.headwearPart = new ModelRenderer(this, 32, 64);
		this.headwearPart.addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, modelSize + 0.5F);
		this.headwearPart.setPos(0.0F, 1.375F, 0.0F);
		this.hat.addChild(this.headwearPart);

		this.rightArmPart1 = new ModelRenderer(this, 40, 20);
		this.rightArmPart1.addBox(0.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.rightArmPart1.setPos(0.0F, 0.0F, 0.5F);
		this.rightArm.addChild(this.rightArmPart1);
		this.leftArmPart1 = new ModelRenderer(this, 40, 20);
		this.leftArmPart1.mirror = true;
		this.leftArmPart1.addBox(-2.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.leftArmPart1.setPos(0.0F, 0.0F, 0.5F);
		this.leftArm.addChild(this.leftArmPart1);

		this.rightArmPart2 = new ModelRenderer(this, 16, 40);
		this.rightArmPart2.addBox(0.0F, -0.5F, -1.0F, 2.0F, 6.0F, 2.0F, modelSize - 0.125F);
		this.rightArmPart2.setPos(0.0F, 2.5F, 0.0F);
		this.rightArmPart1.addChild(this.rightArmPart2);
		this.leftArmPart2 = new ModelRenderer(this, 16, 40);
		this.leftArmPart2.mirror = true;
		this.leftArmPart2.addBox(-2.0F, -0.5F, -1.0F, 2.0F, 6.0F, 2.0F, modelSize - 0.125F);
		this.leftArmPart2.setPos(0.0F, 2.5F, 0.0F);
		this.leftArmPart1.addChild(this.leftArmPart2);

		this.rightArmPart3 = new ModelRenderer(this, 24, 42);
		this.rightArmPart3.addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, modelSize);
		this.rightArmPart3.setPos(0.25F, 4.5F, 1.0F);
		this.rightArmPart2.addChild(this.rightArmPart3);
		this.leftArmPart3 = new ModelRenderer(this, 24, 42);
		this.leftArmPart3.mirror = true;
		this.leftArmPart3.addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, modelSize);
		this.leftArmPart3.setPos(-0.25F, 4.5F, 1.0F);
		this.leftArmPart2.addChild(this.leftArmPart3);

		this.rightArmPart4A = new ModelRenderer(this, 16, 48);
		this.rightArmPart4A.addBox(0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, modelSize);
		this.rightArmPart4A.setPos(0.5F, 0.5F, 0.5F);
		this.rightArmPart2.addChild(this.rightArmPart4A);
		this.leftArmPart4A = new ModelRenderer(this, 16, 48);
		this.leftArmPart4A.mirror = true;
		this.leftArmPart4A.addBox(-1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, modelSize);
		this.leftArmPart4A.setPos(-0.5F, 0.5F, 0.5F);
		this.leftArmPart2.addChild(this.leftArmPart4A);

		this.rightArmPart4B = new ModelRenderer(this, 16, 48);
		this.rightArmPart4B.addBox(0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, modelSize);
		this.rightArmPart4B.setPos(0.5F, 2.0F, 0.25F);
		this.rightArmPart2.addChild(this.rightArmPart4B);
		this.leftArmPart4B = new ModelRenderer(this, 16, 48);
		this.leftArmPart4B.mirror = true;
		this.leftArmPart4B.addBox(-1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, modelSize);
		this.leftArmPart4B.setPos(-0.5F, 2.0F, 0.25F);
		this.leftArmPart2.addChild(this.leftArmPart4B);

		this.rightArmPart5 = new ModelRenderer(this, 24, 32);
		this.rightArmPart5.addBox(0.0F, -0.5F, -1.5F, 1.0F, 8.0F, 2.0F, modelSize);
		this.rightArmPart5.setPos(0.5F, 5.0F, 0.0F);
		this.rightArmPart2.addChild(this.rightArmPart5);
		this.leftArmPart5 = new ModelRenderer(this, 24, 32);
		this.leftArmPart5.mirror = true;
		this.leftArmPart5.addBox(-1.0F, -0.5F, -1.5F, 1.0F, 8.0F, 2.0F, modelSize);
		this.leftArmPart5.setPos(-0.5F, 5.0F, 0.0F);
		this.leftArmPart2.addChild(this.leftArmPart5);

		this.rightArmPart6 = new ModelRenderer(this, 0, 56);
		this.rightArmPart6.addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 11.0F, modelSize - 0.001F);
		this.rightArmPart6.setPos(0.5F, 2.0F, -0.5F);
		this.rightArmPart5.addChild(this.rightArmPart6);
		this.leftArmPart6 = new ModelRenderer(this, 0, 56);
		this.leftArmPart6.mirror = true;
		this.leftArmPart6.addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 11.0F, modelSize - 0.001F);
		this.leftArmPart6.setPos(-0.5F, 2.0F, -0.5F);
		this.leftArmPart5.addChild(this.leftArmPart6);

		this.rightLegPart1 = new ModelRenderer(this, 0, 38);
		this.rightLegPart1.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, modelSize);
		this.rightLegPart1.setPos(-1.0F, 5.5F, -0.25F);
		this.rightLeg.addChild(this.rightLegPart1);
		this.leftLegPart1 = new ModelRenderer(this, 0, 38);
		this.leftLegPart1.mirror = true;
		this.leftLegPart1.addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, modelSize);
		this.leftLegPart1.setPos(1.0F, 5.5F, -0.25F);
		this.leftLeg.addChild(this.leftLegPart1);

		this.rightLegPart2 = new ModelRenderer(this, 8, 38);
		this.rightLegPart2.addBox(-0.5F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F, modelSize);
		this.rightLegPart2.setPos(-0.24F, 4.25F, 2.0F);
		this.rightLeg.addChild(this.rightLegPart2);
		this.leftLegPart2 = new ModelRenderer(this, 8, 38);
		this.leftLegPart2.mirror = true;
		this.leftLegPart2.addBox(-0.5F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F, modelSize);
		this.leftLegPart2.setPos(0.24F, 4.25F, 2.0F);
		this.leftLeg.addChild(this.leftLegPart2);

		this.rightLegPart3 = new ModelRenderer(this, 0, 42);
		this.rightLegPart3.addBox(-0.5F, -3.0F, -1.5F, 1.0F, 11.0F, 2.0F, modelSize);
		this.rightLegPart3.setPos(0.0F, 0.0F, 1.0F);
		this.rightLegPart2.addChild(this.rightLegPart3);
		this.leftLegPart3 = new ModelRenderer(this, 0, 42);
		this.leftLegPart3.mirror = true;
		this.leftLegPart3.addBox(-0.5F, -3.0F, -1.5F, 1.0F, 11.0F, 2.0F, modelSize);
		this.leftLegPart3.setPos(0.0F, 0.0F, 1.0F);
		this.leftLegPart2.addChild(this.leftLegPart3);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.zRot = 0.0F;

		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.head.z = MathHelper.cos(limbSwing * 0.3F + (float)Math.PI / 4.0F) * 2.0F * limbSwingAmount;
		this.head.zRot += MathHelper.cos(ageInTicks * 0.12F) * 0.06F;
		this.hat.copyFrom(this.head);

		if (this.crouching)
		{
			this.body.xRot = 0.5F;
			this.rightLeg.z = 4.0F;
			this.leftLeg.z = 4.0F;
			this.bodyPart5.xRot = 0.0F;
		}
		else
		{
			this.body.xRot = (float)Math.PI * 0.03F;
			this.rightLeg.z = 0.6F;
			this.leftLeg.z = 0.6F;
			this.bodyPart5.xRot = -((float)Math.PI * 0.03F);
		}

		this.bodyPart1.xRot = (float)Math.PI * 0.06F;
		this.bodyPart2.xRot = -((float)Math.PI * 0.12F);

		this.rightLeg.zRot = -((float)Math.PI * 0.01F);
		this.leftLeg.zRot = (float)Math.PI * 0.01F;

		this.rightArm.xRot *= 0.25F;
		this.leftArm.xRot *= 0.25F;
		this.rightArm.yRot *= 0.75F;
		this.leftArm.yRot *= 0.75F;
		this.rightArm.zRot = (float)Math.PI / 12.0F;
		this.leftArm.zRot = -((float)Math.PI / 12.0F);
		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.06F) * 0.021F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.06F) * 0.021F;

		this.rightArmPart1.xRot = (float)Math.PI / 11.0F;
		this.leftArmPart1.xRot = (float)Math.PI / 11.0F;
		this.rightArmPart2.xRot = -((float)Math.PI * 2.0F / 7.0F);
		this.leftArmPart2.xRot = -((float)Math.PI * 2.0F / 7.0F);
		this.rightArmPart3.xRot = -((float)Math.PI / 8.0F);
		this.leftArmPart3.xRot = -((float)Math.PI / 8.0F);
		this.rightArmPart2.xRot -= MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.024F;
		this.leftArmPart2.xRot -= MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.024F;
		this.rightArmPart5.xRot = (float)Math.PI / 5.0F;
		this.leftArmPart5.xRot = (float)Math.PI / 5.0F;
		this.rightArmPart5.xRot += MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.033F;
		this.leftArmPart5.xRot += MathHelper.sin(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.033F;

		this.rightArmPart2.xRot -= ((float)Math.PI / 7.0F) * limbSwingAmount;
		this.leftArmPart2.xRot -= ((float)Math.PI / 7.0F) * limbSwingAmount;
		this.rightArmPart5.xRot -= ((float)Math.PI * 5.0F / 7.0F) * limbSwingAmount;
		this.leftArmPart5.xRot -= ((float)Math.PI * 5.0F / 7.0F) * limbSwingAmount;

		float f = MathHelper.sin(this.attackTime * (float)Math.PI);
		float f1 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);

		this.rightArmPart1.xRot -= f * 0.6F - f1 * 0.2F;
		this.leftArmPart1.xRot -= f * 0.6F - f1 * 0.2F;
		this.rightArmPart2.xRot -= f * 0.6F - f1 * 0.3F;
		this.leftArmPart2.xRot -= f * 0.6F - f1 * 0.3F;
		this.rightArmPart5.xRot += f * 1.2F - f1 * 0.4F;
		this.leftArmPart5.xRot += f * 1.2F - f1 * 0.4F;

		this.rightArmPart4A.yRot = -((float)Math.PI / 18.0F);
		this.leftArmPart4A.yRot = (float)Math.PI / 18.0F;
		this.rightArmPart4B.yRot = -((float)Math.PI / 18.0F);
		this.leftArmPart4B.yRot = (float)Math.PI / 18.0F;
		this.rightArmPart4A.yRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.15F;
		this.leftArmPart4A.yRot += MathHelper.cos(ageInTicks * 0.09F) * 0.15F;
		this.rightArmPart4B.yRot -= MathHelper.cos(ageInTicks * 0.09F + (float)Math.PI / 3.0F) * 0.15F;
		this.leftArmPart4B.yRot += MathHelper.cos(ageInTicks * 0.09F + (float)Math.PI / 3.0F) * 0.15F;
		this.rightArmPart4A.xRot = (float)Math.PI / 20.0F;
		this.leftArmPart4A.xRot = (float)Math.PI / 20.0F;

		this.rightArmPart6.yRot = -((float)Math.PI * 0.12F);
		this.leftArmPart6.yRot = (float)Math.PI * 0.12F;

		this.rightLegPart1.xRot = (float)Math.PI / 3.0F;
		this.leftLegPart1.xRot = (float)Math.PI / 3.0F;
		this.rightLegPart2.xRot = -((float)Math.PI / 15.0F);
		this.leftLegPart2.xRot = -((float)Math.PI / 15.0F);

		this.rightHair.xRot = (float)Math.PI / 18.0F;
		this.leftHair.xRot = (float)Math.PI / 18.0F;
		this.rightHair.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.03F;
		this.leftHair.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.03F;
		this.rightHair.zRot = (float)Math.PI / 9.0F;
		this.leftHair.zRot = -((float)Math.PI / 9.0F);
		this.rightHair.zRot -= MathHelper.sin(ageInTicks * 0.09F) * 0.033F;
		this.leftHair.zRot += MathHelper.sin(ageInTicks * 0.09F) * 0.033F;
		this.rightHair2.zRot = (float)Math.PI / 16.0F;
		this.leftHair2.zRot = -((float)Math.PI / 16.0F);
		this.rightHair2.zRot -= MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.018F;
		this.leftHair2.zRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.018F;
		this.rightHair3.zRot = -((float)Math.PI / 7.0F);
		this.leftHair3.zRot = (float)Math.PI / 7.0F;
		this.rightHair3.zRot -= MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.012F;
		this.leftHair3.zRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.012F;
		this.rightHair4.zRot = -((float)Math.PI * 5.0F / 21.0F);
		this.leftHair4.zRot = (float)Math.PI * 5.0F / 21.0F;
		this.rightHair4.zRot -= MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.012F;
		this.leftHair4.zRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 6.0F) * 0.012F;

		this.tailPart1.xRot = (float)Math.PI / 12.0F;
		this.tailPart1.xRot += MathHelper.cos(ageInTicks * 0.03F) * 0.012F;
		this.tailPart1.xRot += (MathHelper.cos(limbSwing * 0.45F) * 0.09F + ((float)Math.PI / 12.0F)) * limbSwingAmount;
		this.tailPart2.xRot = (float)Math.PI / 15.0F;
		this.tailPart2.xRot += MathHelper.cos(ageInTicks * 0.03F + (float)Math.PI / 5.0F) * 0.045F;
		this.tailPart2.xRot += MathHelper.cos(limbSwing * 0.45F) * 0.09F * limbSwingAmount;
		this.tailPart3.xRot = (float)Math.PI / 8.0F;
		this.tailPart3.xRot += MathHelper.cos(ageInTicks * 0.045F + (float)Math.PI / 4.0F) * 0.06F;
		this.tailPart4.xRot = (float)Math.PI / 5.0F;
		this.tailPart4.xRot += MathHelper.cos(ageInTicks * 0.045F + (float)Math.PI / 2.0F) * 0.06F;
		this.tailPart1.zRot = MathHelper.sin(ageInTicks * 0.021F) * 0.045F;
		this.tailPart2.zRot = MathHelper.sin(ageInTicks * 0.021F + (float)Math.PI / 6.0F) * 0.03F;
		this.tailPart1.zRot += MathHelper.sin(limbSwing * 0.3F) * 0.25F * limbSwingAmount;
		this.tailPart2.zRot += MathHelper.sin(limbSwing * 0.3F + (float)Math.PI / 6.0F) * 0.25F * limbSwingAmount;

		this.bust.xRot = ((float)Math.PI / 4.0F) + ((float)Math.PI / 18.0F);

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
			this.rightLeg.xRot *= 0.5F;
			this.leftLeg.xRot *= 0.5F;
		}

		this.bodyPart6RightA.yRot = (float)Math.PI / 12.0F;
		this.bodyPart6LeftA.yRot = -((float)Math.PI / 12.0F);
		this.bodyPart6RightB.yRot = (float)Math.PI / 9.0F;
		this.bodyPart6LeftB.yRot = -((float)Math.PI / 9.0F);
		this.bodyPart6RightC.yRot = (float)Math.PI / 12.0F;
		this.bodyPart6LeftC.yRot = -((float)Math.PI / 12.0F);
		this.bodyPart6RightA.yRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.135F;
		this.bodyPart6LeftA.yRot += MathHelper.cos(ageInTicks * 0.09F) * 0.135F;
		this.bodyPart6RightB.yRot -= MathHelper.cos(ageInTicks * 0.09F + (float)Math.PI / 3.0F) * 0.135F;
		this.bodyPart6LeftB.yRot += MathHelper.cos(ageInTicks * 0.09F + (float)Math.PI / 3.0F) * 0.135F;
		this.bodyPart6RightC.yRot -= MathHelper.cos(ageInTicks * 0.09F + (float)Math.PI / 6.0F) * 0.135F;
		this.bodyPart6LeftC.yRot += MathHelper.cos(ageInTicks * 0.09F + (float)Math.PI / 6.0F) * 0.135F;
		this.bodyPart6RightB.xRot = -((float)Math.PI * 0.06F);
		this.bodyPart6LeftB.xRot = -((float)Math.PI * 0.06F);

		this.hairBand.xRot = -((float)Math.PI / 12.0F);
		this.hairBand.xRot += MathHelper.cos(ageInTicks * 0.03F + (float)Math.PI) * 0.09F;
		this.hairPart.xRot = (float)Math.PI / 12.0F;
		this.hairPart.xRot += MathHelper.sin(ageInTicks * 0.06F + (float)Math.PI / 2.0F) * 0.06F;

		float f2 = (float)Math.PI / 2.0F;

		for (int i = 0; i < this.rightSkirtPart.length; ++i)
		{
			this.rightSkirtPart[i].yRot = (float)Math.PI * ((float)(i - 3)) / 7.0F + f2;
			this.rightSkirtPart[i].xRot = (float)Math.PI * 2.0F / 5.0F - f2;
			this.rightSkirtPart[i].xRot += MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI * (float)i / 7.0F) * 0.15F - 0.03F;
			this.leftSkirtPart[i].yRot = -((float)Math.PI * ((float)(i - 3)) / 7.0F + f2);
			this.leftSkirtPart[i].xRot = (float)Math.PI * 2.0F / 5.0F - f2;
			this.leftSkirtPart[i].xRot += MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI * (float)i / 7.0F) * 0.15F - 0.03F;
		}
	}

	@Override
	protected int getBodyHeight()
	{
		return -1;
	}

	@Override
	protected float getLegRotZ()
	{
		return -((float)Math.PI * 0.004F);
	}

	public boolean isAggressive(T entityIn)
	{
		return entityIn.isAggressive();
	}
}