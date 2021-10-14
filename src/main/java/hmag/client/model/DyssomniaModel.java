package hmag.client.model;

import com.google.common.collect.ImmutableList;

import hmag.entity.DyssomniaEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DyssomniaModel<T extends DyssomniaEntity> extends SegmentedModel<T>
{
	private final ModelRenderer head;
	private final ModelRenderer headPart1;
	private final ModelRenderer headPart2Left1;
	private final ModelRenderer headPart2Right1;
	private final ModelRenderer headPart2Left2;
	private final ModelRenderer headPart2Right2;
	private final ModelRenderer leftFang1;
	private final ModelRenderer rightFang1;
	private final ModelRenderer leftFang2;
	private final ModelRenderer rightFang2;
	private final ModelRenderer leftHeadFin;
	private final ModelRenderer rightHeadFin;
	private final ModelRenderer body;
	private final ModelRenderer bodyPart1;
	private final ModelRenderer bodyPart2;
	private final ModelRenderer bodyPart3;
	private final ModelRenderer leftRib;
	private final ModelRenderer rightRib;
	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer leftTailFin;
	private final ModelRenderer rightTailFin;
	private final ModelRenderer leftWingBase;
	private final ModelRenderer leftWing1;
	private final ModelRenderer leftWing2;
	private final ModelRenderer leftWing3;
	private final ModelRenderer rightWingBase;
	private final ModelRenderer rightWing1;
	private final ModelRenderer rightWing2;
	private final ModelRenderer rightWing3;
	private final ModelRenderer smallLeftWingBase;
	private final ModelRenderer smallLeftWing1;
	private final ModelRenderer smallLeftWing2;
	private final ModelRenderer smallRightWingBase;
	private final ModelRenderer smallRightWing1;
	private final ModelRenderer smallRightWing2;
	private final ModelRenderer leftBodyFin;
	private final ModelRenderer rightBodyFin;
	private final ModelRenderer smallLeftBodyFin;
	private final ModelRenderer smallRightBodyFin;

	public DyssomniaModel()
	{
		this.texHeight = 128;
		this.texWidth = 128;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-3.5F, -2.0F, -2.0F, 7.0F, 2.0F, 2.0F);
		this.head.setPos(0.0F, -1.0F, -9.0F);
		this.head.xRot = 0.2F;

		this.headPart1 = new ModelRenderer(this, 0, 8);
		this.headPart1.addBox(-3.5F, -2.0F, -4.0F, 7.0F, 3.0F, 4.0F);
		this.headPart1.setPos(0.0F, 0.0F, -2.0F);
		this.head.addChild(this.headPart1);

		this.headPart2Left1 = new ModelRenderer(this, 24, 0);
		this.headPart2Left1.addBox(-2.0F, -1.0F, -1.0F, 4.0F, 3.0F, 1.0F);
		this.headPart2Left1.setPos(2.5F, -0.5F, -2.5F);
		this.headPart1.addChild(this.headPart2Left1);
		this.headPart2Right1 = new ModelRenderer(this, 24, 0);
		this.headPart2Right1.mirror = true;
		this.headPart2Right1.addBox(-2.0F, -1.0F, -1.0F, 4.0F, 3.0F, 1.0F);
		this.headPart2Right1.setPos(-2.5F, -0.5F, -2.5F);
		this.headPart1.addChild(this.headPart2Right1);

		this.headPart2Left2 = new ModelRenderer(this, 40, 0);
		this.headPart2Left2.addBox(-4.0F, -1.0F, -1.0F, 7.0F, 3.0F, 1.0F);
		this.headPart2Left2.setPos(0.0F, 0.0F, -1.0F);
		this.headPart2Left1.addChild(this.headPart2Left2);
		this.headPart2Right2 = new ModelRenderer(this, 40, 0);
		this.headPart2Right2.mirror = true;
		this.headPart2Right2.addBox(-3.0F, -1.0F, -1.0F, 7.0F, 3.0F, 1.0F);
		this.headPart2Right2.setPos(0.0F, 0.0F, -1.0F);
		this.headPart2Right1.addChild(this.headPart2Right2);

		this.leftFang1 = new ModelRenderer(this, 24, 8);
		this.leftFang1.addBox(-2.0F, -1.0F, 0.0F, 2.0F, 3.0F, 1.0F, -0.5F);
		this.leftFang1.setPos(-1.5F, 0.0F, -0.5F);
		this.headPart2Left1.addChild(this.leftFang1);
		this.rightFang1 = new ModelRenderer(this, 24, 8);
		this.rightFang1.mirror = true;
		this.rightFang1.addBox(0.0F, -1.0F, 0.0F, 2.0F, 3.0F, 1.0F, -0.5F);
		this.rightFang1.setPos(1.5F, 0.0F, -0.5F);
		this.headPart2Right1.addChild(this.rightFang1);

		this.leftFang2 = new ModelRenderer(this, 24, 12);
		this.leftFang2.addBox(-2.0F, -1.0F, 0.0F, 2.0F, 3.0F, 1.0F);
		this.leftFang2.setPos(-4.0F, 0.0F, -1.0F);
		this.headPart2Left2.addChild(this.leftFang2);
		this.rightFang2 = new ModelRenderer(this, 24, 12);
		this.rightFang2.mirror = true;
		this.rightFang2.addBox(0.0F, -1.0F, 0.0F, 2.0F, 3.0F, 1.0F);
		this.rightFang2.setPos(4.0F, 0.0F, -1.0F);
		this.headPart2Right2.addChild(this.rightFang2);

		this.leftHeadFin = new ModelRenderer(this, 32, 8);
		this.leftHeadFin.addBox(0.0F, -2.0F, -4.0F, 1.0F, 2.0F, 4.0F);
		this.leftHeadFin.setPos(1.5F, -2.0F, 0.5F);
		this.headPart1.addChild(this.leftHeadFin);
		this.rightHeadFin = new ModelRenderer(this, 32, 8);
		this.rightHeadFin.mirror = true;
		this.rightHeadFin.addBox(-1.0F, -2.0F, -4.0F, 1.0F, 2.0F, 4.0F);
		this.rightHeadFin.setPos(-1.5F, -2.0F, 0.5F);
		this.headPart1.addChild(this.rightHeadFin);

		this.body = new ModelRenderer(this, 0, 16);
		this.body.addBox(-5.5F, -3.0F, -9.0F, 11.0F, 4.0F, 11.0F);
		this.body.setPos(0.0F, 0.0F, 0.0F);

		this.bodyPart1 = new ModelRenderer(this, 0, 32);
		this.bodyPart1.addBox(-4.5F, -3.0F, 0.0F, 9.0F, 4.0F, 1.0F);
		this.bodyPart1.setPos(0.0F, 0.0F, 2.0F);
		this.body.addChild(this.bodyPart1);

		this.bodyPart2 = new ModelRenderer(this, 0, 40);
		this.bodyPart2.addBox(-3.5F, -1.5F, 0.0F, 7.0F, 3.0F, 6.0F);
		this.bodyPart2.setPos(0.0F, -1.0F, 1.0F);
		this.bodyPart1.addChild(this.bodyPart2);

		this.bodyPart3 = new ModelRenderer(this, 32, 32);
		this.bodyPart3.addBox(-4.5F, -2.0F, -1.0F, 9.0F, 3.0F, 1.0F);
		this.bodyPart3.setPos(0.0F, -0.5F, -9.0F);
		this.body.addChild(this.bodyPart3);

		this.leftRib = new ModelRenderer(this, 64, 0);
		this.leftRib.addBox(-1.0F, -2.0F, -3.0F, 3.0F, 3.0F, 7.0F);
		this.leftRib.setPos(2.0F, 2.25F, -2.0F);
		this.body.addChild(this.leftRib);
		this.rightRib = new ModelRenderer(this, 64, 0);
		this.rightRib.mirror = true;
		this.rightRib.addBox(-2.0F, -2.0F, -3.0F, 3.0F, 3.0F, 7.0F);
		this.rightRib.setPos(-2.0F, 2.25F, -2.0F);
		this.body.addChild(this.rightRib);

		this.tail1 = new ModelRenderer(this, 0, 52);
		this.tail1.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 2.0F, 6.0F);
		this.tail1.setPos(0.0F, -1.0F, 6.0F);
		this.bodyPart2.addChild(this.tail1);
		this.tail2 = new ModelRenderer(this, 0, 60);
		this.tail2.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 4.0F);
		this.tail2.setPos(0.0F, 0.5F, 6.0F);
		this.tail1.addChild(this.tail2);
		this.tail3 = new ModelRenderer(this, 0, 68);
		this.tail3.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 8.0F);
		this.tail3.setPos(0.0F, 0.0F, 4.0F);
		this.tail2.addChild(this.tail3);

		this.leftTailFin = new ModelRenderer(this, 0, 80);
		this.leftTailFin.addBox(0.0F, 0.0F, 0.0F, 7.0F, 1.0F, 15.0F);
		this.leftTailFin.setPos(1.5F, 0.0F, 1.0F);
		this.tail2.addChild(this.leftTailFin);
		this.rightTailFin = new ModelRenderer(this, 0, 80);
		this.rightTailFin.mirror = true;
		this.rightTailFin.addBox(-7.0F, 0.0F, 0.0F, 7.0F, 1.0F, 15.0F);
		this.rightTailFin.setPos(-1.5F, 0.0F, 1.0F);
		this.tail2.addChild(this.rightTailFin);

		this.leftWingBase = new ModelRenderer(this, 64, 16);
		this.leftWingBase.addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 10.0F);
		this.leftWingBase.setPos(4.0F, -2.75F, -8.0F);
		this.body.addChild(this.leftWingBase);
		this.leftWing1 = new ModelRenderer(this, 64, 32);
		this.leftWing1.addBox(0.0F, 0.0F, 0.0F, 4.0F, 3.0F, 10.0F);
		this.leftWing1.setPos(2.0F, 0.0F, -0.5F);
		this.leftWingBase.addChild(this.leftWing1);
		this.leftWing2 = new ModelRenderer(this, 64, 48);
		this.leftWing2.addBox(0.0F, 0.0F, 0.0F, 6.0F, 2.0F, 9.0F);
		this.leftWing2.setPos(4.0F, 0.0F, -0.5F);
		this.leftWing1.addChild(this.leftWing2);
		this.leftWing3 = new ModelRenderer(this, 64, 64);
		this.leftWing3.addBox(0.0F, 0.0F, 0.0F, 13.0F, 1.0F, 9.0F);
		this.leftWing3.setPos(6.0F, 0.0F, 0.0F);
		this.leftWing2.addChild(this.leftWing3);

		this.rightWingBase = new ModelRenderer(this, 64, 16);
		this.rightWingBase.mirror = true;
		this.rightWingBase.addBox(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 10.0F);
		this.rightWingBase.setPos(-4.0F, -2.75F, -8.0F);
		this.body.addChild(this.rightWingBase);
		this.rightWing1 = new ModelRenderer(this, 64, 32);
		this.rightWing1.mirror = true;
		this.rightWing1.addBox(-4.0F, 0.0F, 0.0F, 4.0F, 3.0F, 10.0F);
		this.rightWing1.setPos(-2.0F, 0.0F, -0.5F);
		this.rightWingBase.addChild(this.rightWing1);
		this.rightWing2 = new ModelRenderer(this, 64, 48);
		this.rightWing2.mirror = true;
		this.rightWing2.addBox(-6.0F, 0.0F, 0.0F, 6.0F, 2.0F, 9.0F);
		this.rightWing2.setPos(-4.0F, 0.0F, -0.5F);
		this.rightWing1.addChild(this.rightWing2);
		this.rightWing3 = new ModelRenderer(this, 64, 64);
		this.rightWing3.mirror = true;
		this.rightWing3.addBox(-13.0F, 0.0F, 0.0F, 13.0F, 1.0F, 9.0F);
		this.rightWing3.setPos(-6.0F, 0.0F, 0.0F);
		this.rightWing2.addChild(this.rightWing3);

		this.smallLeftWingBase = new ModelRenderer(this, 32, 40);
		this.smallLeftWingBase.addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 7.0F);
		this.smallLeftWingBase.setPos(4.0F, -0.5F, 0.0F);
		this.bodyPart2.addChild(this.smallLeftWingBase);
		this.smallLeftWing1 = new ModelRenderer(this, 32, 52);
		this.smallLeftWing1.addBox(0.0F, 0.0F, 0.0F, 4.0F, 1.0F, 8.0F);
		this.smallLeftWing1.setPos(2.0F, 0.0F, -0.5F);
		this.smallLeftWingBase.addChild(this.smallLeftWing1);
		this.smallLeftWing2 = new ModelRenderer(this, 32, 64);
		this.smallLeftWing2.addBox(0.0F, 0.0F, 0.0F, 9.0F, 1.0F, 7.0F);
		this.smallLeftWing2.setPos(4.0F, 0.0F, 0.0F);
		this.smallLeftWing1.addChild(this.smallLeftWing2);

		this.smallRightWingBase = new ModelRenderer(this, 32, 40);
		this.smallRightWingBase.mirror = true;
		this.smallRightWingBase.addBox(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 7.0F);
		this.smallRightWingBase.setPos(-4.0F, -0.5F, 0.0F);
		this.bodyPart2.addChild(this.smallRightWingBase);
		this.smallRightWing1 = new ModelRenderer(this, 32, 52);
		this.smallRightWing1.mirror = true;
		this.smallRightWing1.addBox(-4.0F, 0.0F, 0.0F, 4.0F, 1.0F, 8.0F);
		this.smallRightWing1.setPos(-2.0F, 0.0F, -0.5F);
		this.smallRightWingBase.addChild(this.smallRightWing1);
		this.smallRightWing2 = new ModelRenderer(this, 32, 64);
		this.smallRightWing2.mirror = true;
		this.smallRightWing2.addBox(-9.0F, 0.0F, 0.0F, 9.0F, 1.0F, 7.0F);
		this.smallRightWing2.setPos(-4.0F, 0.0F, 0.0F);
		this.smallRightWing1.addChild(this.smallRightWing2);

		this.leftBodyFin = new ModelRenderer(this, 96, 0);
		this.leftBodyFin.addBox(0.0F, -5.0F, -10.0F, 1.0F, 5.0F, 12.0F);
		this.leftBodyFin.setPos(1.5F, -2.25F, 2.0F);
		this.body.addChild(this.leftBodyFin);
		this.rightBodyFin = new ModelRenderer(this, 96, 0);
		this.rightBodyFin.mirror = true;
		this.rightBodyFin.addBox(-1.0F, -5.0F, -10.0F, 1.0F, 5.0F, 12.0F);
		this.rightBodyFin.setPos(-1.5F, -2.25F, 2.0F);
		this.body.addChild(this.rightBodyFin);

		this.smallLeftBodyFin = new ModelRenderer(this, 96, 24);
		this.smallLeftBodyFin.addBox(0.0F, -3.0F, -6.0F, 1.0F, 3.0F, 6.0F);
		this.smallLeftBodyFin.setPos(1.5F, -0.5F, 7.0F);
		this.bodyPart2.addChild(this.smallLeftBodyFin);
		this.smallRightBodyFin = new ModelRenderer(this, 96, 24);
		this.smallRightBodyFin.mirror = true;
		this.smallRightBodyFin.addBox(-1.0F, -3.0F, -6.0F, 1.0F, 3.0F, 6.0F);
		this.smallRightBodyFin.setPos(-1.5F, -0.5F, 7.0F);
		this.bodyPart2.addChild(this.smallRightBodyFin);

		this.headPart2Left1.yRot = -((float)Math.PI / 3.0F);
		this.headPart2Right1.yRot = (float)Math.PI / 3.0F;
		this.leftWing1.zRot = 0.1F;
		this.leftWing2.zRot = 0.1F;
		this.leftWing3.zRot = 0.1F;
		this.rightWing1.zRot = -0.1F;
		this.rightWing2.zRot = -0.1F;
		this.rightWing3.zRot = -0.1F;
		this.smallLeftWing1.zRot = 0.1F;
		this.smallLeftWing2.zRot = 0.1F;
		this.smallRightWing1.zRot = -0.1F;
		this.smallRightWing2.zRot = -0.1F;
	}

	@Override
	public Iterable<ModelRenderer> parts()
	{
		return ImmutableList.of(this.head, this.body);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);

		this.headPart2Left1.yRot = -((float)Math.PI / 3.0F);
		this.headPart2Right1.yRot = (float)Math.PI / 3.0F;
		float f = entityIn.getAttackAnimationScale(partialTick);

		if (f > 0.0F)
		{
			this.headPart2Left1.yRot -= f * ((float)Math.PI / 180.0F) * 57.0F;
			this.headPart2Right1.yRot += f * ((float)Math.PI / 180.0F) * 57.0F;
		}
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.xRot = 0.2F;
		this.head.yRot = 0.0F;
		this.head.zRot = 0.0F;
		this.body.xRot = -0.1F;
		this.bodyPart2.xRot = 0.15F;

		this.leftRib.zRot = (float)Math.PI / 5.0F;
		this.rightRib.zRot = -((float)Math.PI / 5.0F);
		this.leftRib.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.033F;
		this.rightRib.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.033F;
		this.leftRib.yRot = (float)Math.PI / 21.0F;
		this.rightRib.yRot = -((float)Math.PI / 21.0F);

		float f = ((float)(entityIn.getId() * 3) + ageInTicks) * 0.13F;
		float f1 = 9.0F;
		float f2 = (float)Math.PI / 180.0F;

		this.leftFang1.yRot = (8.0F + MathHelper.cos(f * 1.5F + ((float)Math.PI / 8.0F)) * 8.0F) * f2;
		this.rightFang1.yRot = -this.leftFang1.yRot;
		this.leftFang2.yRot = (9.0F + MathHelper.cos(f * 1.5F + ((float)Math.PI / 4.0F)) * 9.0F) * f2;
		this.rightFang2.yRot = -this.leftFang2.yRot;

		this.leftWingBase.yRot = (float)Math.PI / 20.0F;
		this.rightWingBase.yRot = -((float)Math.PI / 20.0F);
		this.leftWing1.zRot = MathHelper.cos(f) * f1 * f2;
		this.leftWing2.zRot = MathHelper.cos(f) * f1 * f2;
		this.leftWing3.zRot = MathHelper.cos(f) * f1 * f2;
		this.rightWing1.zRot = -this.leftWing1.zRot;
		this.rightWing2.zRot = -this.leftWing2.zRot;
		this.rightWing3.zRot = -this.leftWing3.zRot;

		this.smallLeftWingBase.yRot = -((float)Math.PI / 15.0F);
		this.smallRightWingBase.yRot = (float)Math.PI / 15.0F;
		this.smallLeftWing1.zRot = MathHelper.cos(f + ((float)Math.PI / 5.0F)) * f1 * f2;
		this.smallLeftWing2.zRot = MathHelper.cos(f + ((float)Math.PI / 5.0F)) * f1 * f2;
		this.smallRightWing1.zRot = -this.smallLeftWing1.zRot;
		this.smallRightWing2.zRot = -this.smallLeftWing2.zRot;

		this.leftHeadFin.zRot = (float)Math.PI / 4.0F;
		this.leftHeadFin.zRot += MathHelper.cos(f * 2.0F + ((float)Math.PI * 11.0F / 24.0F)) * f1 * 0.5F * f2;
		this.rightHeadFin.zRot = -this.leftBodyFin.zRot;

		this.leftBodyFin.yRot = (float)Math.PI / 30.0F;
		this.rightBodyFin.yRot = -((float)Math.PI / 30.0F);
		this.leftBodyFin.zRot = (float)Math.PI / 4.0F;
		this.leftBodyFin.zRot += MathHelper.cos(f * 2.0F + ((float)Math.PI / 2.0F)) * f1 * 0.5F * f2;
		this.rightBodyFin.zRot = -this.leftBodyFin.zRot;

		this.smallLeftBodyFin.zRot = (float)Math.PI / 4.0F;
		this.smallLeftBodyFin.zRot += MathHelper.cos(f * 2.0F + ((float)Math.PI * 13.0F / 24.0F)) * f1 * 0.5F * f2;
		this.smallRightBodyFin.zRot = -this.smallLeftBodyFin.zRot;

		this.tail1.xRot = -(2.5F + MathHelper.cos(f * 2.0F) * 5.0F) * f2 * 1.5F;
		this.tail2.xRot = -(2.5F + MathHelper.cos(f * 2.0F) * 5.0F) * f2 * 1.75F;
		this.tail3.xRot = -(2.5F + MathHelper.cos(f * 2.0F) * 5.0F) * f2 * 1.5F;
		this.leftTailFin.zRot = MathHelper.cos(f * 2.0F + ((float)Math.PI / 2.0F)) * f1 * 1.2F * f2;
		this.rightTailFin.zRot = -this.leftTailFin.zRot;
	}
}