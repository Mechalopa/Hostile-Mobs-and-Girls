package hmag.client.model;

import com.google.common.collect.ImmutableList;

import hmag.entity.SpiderNestEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpiderNestModel<T extends SpiderNestEntity> extends SegmentedModel<T>
{
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer bodyPart1;
	private final ModelRenderer bodyPart2;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg1Part;
	private final ModelRenderer leg2Part;
	private final ModelRenderer leg3Part;
	private final ModelRenderer leg4Part;
	private final ModelRenderer bodyPartsRightA[] = new ModelRenderer[4];
	private final ModelRenderer bodyPartsLeftA[] = new ModelRenderer[4];
	private final ModelRenderer bodyPartsRightB[] = new ModelRenderer[4];
	private final ModelRenderer bodyPartsLeftB[] = new ModelRenderer[4];
	private final ModelRenderer tailRight;
	private final ModelRenderer tailLeft;

	public SpiderNestModel()
	{
		this.texHeight = 128;
		this.texWidth = 64;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 6.0F);
		this.head.setPos(0.0F, 13.0F, -8.0F);
		this.body = new ModelRenderer(this, 0, 16);
		this.body.addBox(-7.0F, -11.0F, -8.0F, 14.0F, 16.0F, 16.0F);
		this.body.setPos(0.0F, 12.0F, 0.0F);
		this.bodyPart1 = new ModelRenderer(this, 0, 64);
		this.bodyPart1.addBox(-8.0F, -8.0F, -7.0F, 16.0F, 16.0F, 14.0F);
		this.bodyPart1.setPos(0.0F, -3.0F, 0.0F);
		this.body.addChild(this.bodyPart1);
		this.bodyPart2 = new ModelRenderer(this, 0, 96);
		this.bodyPart2.addBox(-7.0F, -9.0F, -7.0F, 14.0F, 18.0F, 14.0F);
		this.bodyPart2.setPos(0.0F, -3.0F, 0.0F);
		this.body.addChild(this.bodyPart2);

		this.leg1 = new ModelRenderer(this, 0, 48);
		this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F);
		this.leg1.setPos(-5.5F, 17.0F, 5.5F);
		this.leg2 = new ModelRenderer(this, 0, 48);
		this.leg2.mirror = true;
		this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F);
		this.leg2.setPos(5.5F, 17.0F, 5.5F);
		this.leg3 = new ModelRenderer(this, 0, 48);
		this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F);
		this.leg3.setPos(-5.5F, 17.0F, -5.5F);
		this.leg4 = new ModelRenderer(this, 0, 48);
		this.leg4.mirror = true;
		this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F);
		this.leg4.setPos(5.5F, 17.0F, -5.5F);

		this.leg1Part = new ModelRenderer(this, 16, 48);
		this.leg1Part.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F);
		this.leg1Part.setPos(0.0F, 1.5F, 0.0F);
		this.leg1.addChild(this.leg1Part);
		this.leg2Part = new ModelRenderer(this, 16, 48);
		this.leg2Part.mirror = true;
		this.leg2Part.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F);
		this.leg2Part.setPos(0.0F, 1.5F, 0.0F);
		this.leg2.addChild(this.leg2Part);
		this.leg3Part = new ModelRenderer(this, 16, 48);
		this.leg3Part.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F);
		this.leg3Part.setPos(0.0F, 1.5F, 0.0F);
		this.leg3.addChild(this.leg3Part);
		this.leg4Part = new ModelRenderer(this, 16, 48);
		this.leg4Part.mirror = true;
		this.leg4Part.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F);
		this.leg4Part.setPos(0.0F, 1.5F, 0.0F);
		this.leg4.addChild(this.leg4Part);

		for (int i = 0; i < this.bodyPartsRightA.length; ++i)
		{
			this.bodyPartsRightA[i] = new ModelRenderer(this, 32, 0);
			this.bodyPartsRightA[i].addBox(-1.0F, -5.5F, -1.0F, 2.0F, 6.0F, 2.0F);
			this.bodyPartsRightA[i].setPos(7.5F, -9.0F, -4.5F + (float)i * 3.0F);
			this.body.addChild(this.bodyPartsRightA[i]);

			this.bodyPartsRightB[i] = new ModelRenderer(this, 40, 0);
			this.bodyPartsRightB[i].addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, -0.25F);
			this.bodyPartsRightB[i].setPos(0.0F, 0.0F, 0.0F);
			this.bodyPartsRightA[i].addChild(this.bodyPartsRightB[i]);
		}

		for (int i = 0; i < this.bodyPartsLeftA.length; ++i)
		{
			this.bodyPartsLeftA[i] = new ModelRenderer(this, 32, 0);
			this.bodyPartsLeftA[i].mirror = true;
			this.bodyPartsLeftA[i].addBox(-1.0F, -5.5F, -1.0F, 2.0F, 6.0F, 2.0F);
			this.bodyPartsLeftA[i].setPos(-7.5F, -9.0F, -4.5F + (float)i * 3.0F);
			this.body.addChild(this.bodyPartsLeftA[i]);

			this.bodyPartsLeftB[i] = new ModelRenderer(this, 40, 0);
			this.bodyPartsLeftB[i].mirror = true;
			this.bodyPartsLeftB[i].addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, -0.25F);
			this.bodyPartsLeftB[i].setPos(0.0F, 0.0F, 0.0F);
			this.bodyPartsLeftA[i].addChild(this.bodyPartsLeftB[i]);
		}

		this.tailRight = new ModelRenderer(this, 36, 48);
		this.tailRight.addBox(-2.0F, -7.5F, -1.0F, 4.0F, 8.0F, 3.0F);
		this.tailRight.setPos(4.0F, 1.0F, 8.0F);
		this.body.addChild(this.tailRight);
		this.tailLeft = new ModelRenderer(this, 36, 48);
		this.tailLeft.mirror = true;
		this.tailLeft.addBox(-2.0F, -7.5F, -1.0F, 4.0F, 8.0F, 3.0F);
		this.tailLeft.setPos(-4.0F, 1.0F, 8.0F);
		this.body.addChild(this.tailLeft);
	}

	@Override
	public Iterable<ModelRenderer> parts()
	{
		return ImmutableList.of(this.head, this.body, this.leg1, this.leg2, this.leg3, this.leg4);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (entityIn.isCharging())
		{
			this.head.zRot = (ageInTicks * 1.2F) % (180.0F / (float)Math.PI) * (entityIn.getMainArm() == HandSide.RIGHT ? 1.0F : -1.0F);
			this.head.yRot = 0.0F;
			this.head.xRot = 0.0F;
		}
		else
		{
			this.head.zRot = 0.0F;
			this.head.yRot = netHeadYaw / (180.0F / (float)Math.PI);
			this.head.xRot = headPitch / (180.0F / (float)Math.PI);
		}

		this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg4.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		for (int i = 0; i < this.bodyPartsRightA.length; ++i)
		{
			this.bodyPartsRightA[i].xRot = -((float)Math.PI * (float)(i - 2) / 15.0F);
			this.bodyPartsRightA[i].xRot += MathHelper.cos(ageInTicks * 0.081F + (float)Math.PI * (float)i / 3.0F) * 0.15F;
			this.bodyPartsRightA[i].zRot = ((float)Math.PI * 2.0F / 9.0F);
			this.bodyPartsRightA[i].zRot += MathHelper.sin(ageInTicks * 0.081F + (float)Math.PI * (float)(i + 4) / 3.0F) * 0.3F;
		}

		for (int i = 0; i < this.bodyPartsLeftA.length; ++i)
		{
			this.bodyPartsLeftA[i].xRot = -((float)Math.PI * (float)(i - 2) / 15.0F);
			this.bodyPartsLeftA[i].xRot += MathHelper.cos(ageInTicks * 0.081F + (float)Math.PI * (float)i / 3.0F) * 0.15F;
			this.bodyPartsLeftA[i].zRot = -((float)Math.PI * 2.0F / 9.0F);
			this.bodyPartsLeftA[i].zRot -= MathHelper.sin(ageInTicks * 0.081F + (float)Math.PI * (float)(i + 4) / 3.0F) * 0.3F;
		}

		this.tailRight.xRot = -((float)Math.PI / 12.0F);
		this.tailLeft.xRot = -((float)Math.PI  / 12.0F);
		this.tailRight.xRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 2.0F) * 0.12F;
		this.tailLeft.xRot -= MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 2.0F) * 0.12F;
	}
}