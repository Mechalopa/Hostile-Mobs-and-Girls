package net.mechalopa.hmag.client.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScorpionModel<T extends Entity> extends SegmentedModel<T>
{
	private final ModelRenderer head;
	private final ModelRenderer neck;
	private final ModelRenderer body;

	private final ModelRenderer bodyPart1;
	private final ModelRenderer bodyPart2;
	private final ModelRenderer bodyPart3;
	private final ModelRenderer bodyPart4;
	private final ModelRenderer bodyPart5;

	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer tail4;
	private final ModelRenderer tail5;
	private final ModelRenderer needle1;
	private final ModelRenderer needle2;

	private final ModelRenderer rightFang;
	private final ModelRenderer leftFang;

	private final ModelRenderer leg1A;
	private final ModelRenderer leg2A;
	private final ModelRenderer leg3A;
	private final ModelRenderer leg4A;
	private final ModelRenderer leg5A;
	private final ModelRenderer leg6A;
	private final ModelRenderer leg7A;
	private final ModelRenderer leg8A;

	private final ModelRenderer leg1B;
	private final ModelRenderer leg2B;
	private final ModelRenderer leg3B;
	private final ModelRenderer leg4B;
	private final ModelRenderer leg5B;
	private final ModelRenderer leg6B;
	private final ModelRenderer leg7B;
	private final ModelRenderer leg8B;

	private final ModelRenderer leg1C;
	private final ModelRenderer leg2C;
	private final ModelRenderer leg3C;
	private final ModelRenderer leg4C;
	private final ModelRenderer leg5C;
	private final ModelRenderer leg6C;
	private final ModelRenderer leg7C;
	private final ModelRenderer leg8C;

	private final ModelRenderer leg1D;
	private final ModelRenderer leg2D;
	private final ModelRenderer leg3D;
	private final ModelRenderer leg4D;
	private final ModelRenderer leg5D;
	private final ModelRenderer leg6D;
	private final ModelRenderer leg7D;
	private final ModelRenderer leg8D;

	private final ModelRenderer rightScissorArm1;
	private final ModelRenderer rightScissorArm2;
	private final ModelRenderer rightScissorArm3;
	private final ModelRenderer rightScissorArm4;
	private final ModelRenderer rightScissor1;
	private final ModelRenderer rightScissor2;

	private final ModelRenderer leftScissorArm1;
	private final ModelRenderer leftScissorArm2;
	private final ModelRenderer leftScissorArm3;
	private final ModelRenderer leftScissorArm4;
	private final ModelRenderer leftScissor1;
	private final ModelRenderer leftScissor2;

	public ScorpionModel()
	{
		this.texHeight = 64;
		this.texWidth = 64;
		byte b0 = 18;

		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-3.0F, -1.0F, -4.0F, 6.0F, 3.0F, 5.0F);
		this.head.setPos(0.0F, (float)b0 - 0.75F, -7.0F);

		this.neck = new ModelRenderer(this, 0, 8);
		this.neck.addBox(-4.0F, -1.5F, -1.5F, 8.0F, 3.0F, 3.0F, -0.25F);
		this.neck.setPos(0.0F, (float)b0, -6.5F);

		this.body = new ModelRenderer(this, 0, 24);
		this.body.addBox(-4.0F, -2.0F, -8.0F, 8.0F, 4.0F, 11.0F);
		this.body.setPos(0.0F, (float)b0, 2.0F);

		this.bodyPart1 = new ModelRenderer(this, 0, 40);
		this.bodyPart1.addBox(-3.5F, 0.0F, 0.0F, 7.0F, 3.0F, 5.0F);
		this.bodyPart1.setPos(0.0F, -1.5F, 2.0F);
		this.body.addChild(this.bodyPart1);
		this.bodyPart2 = new ModelRenderer(this, 24, 40);
		this.bodyPart2.addBox(-3.0F, 0.0F, 0.0F, 6.0F, 3.0F, 4.0F);
		this.bodyPart2.setPos(0.0F, 0.0F, 4.25F);
		this.bodyPart1.addChild(this.bodyPart2);
		this.bodyPart3 = new ModelRenderer(this, 0, 48);
		this.bodyPart3.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 3.0F, 4.0F);
		this.bodyPart3.setPos(0.0F, -0.125F, 3.25F);
		this.bodyPart2.addChild(this.bodyPart3);
		this.bodyPart4 = new ModelRenderer(this, 24, 48);
		this.bodyPart4.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 3.0F, 3.0F);
		this.bodyPart4.setPos(0.0F, -0.125F, 3.25F);
		this.bodyPart3.addChild(this.bodyPart4);

		this.bodyPart5 = new ModelRenderer(this, 32, 0);
		this.bodyPart5.addBox(-3.0F, -1.0F, -7.0F, 6.0F, 1.0F, 9.0F);
		this.bodyPart5.setPos(0.0F, -1.5F, 0.0F);
		this.body.addChild(this.bodyPart5);

		this.tail1 = new ModelRenderer(this, 0, 56);
		this.tail1.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.25F);
		this.tail1.setPos(0.0F, 0.125F, 2.25F);
		this.bodyPart4.addChild(this.tail1);
		this.tail2 = new ModelRenderer(this, 0, 56);
		this.tail2.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F);
		this.tail2.setPos(0.0F, 0.0F, 2.25F);
		this.tail1.addChild(this.tail2);
		this.tail3 = new ModelRenderer(this, 0, 56);
		this.tail3.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F);
		this.tail3.setPos(0.0F, 0.0F, 2.25F);
		this.tail2.addChild(this.tail3);
		this.tail4 = new ModelRenderer(this, 0, 56);
		this.tail4.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F);
		this.tail4.setPos(0.0F, 0.0F, 2.25F);
		this.tail3.addChild(this.tail4);
		this.tail5 = new ModelRenderer(this, 0, 56);
		this.tail5.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F);
		this.tail5.setPos(0.0F, 0.0F, 2.25F);
		this.tail4.addChild(this.tail5);

		this.needle1 = new ModelRenderer(this, 16, 56);
		this.needle1.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.125F);
		this.needle1.setPos(0.0F, 0.0F, 2.25F);
		this.tail5.addChild(this.needle1);
		this.needle2 = new ModelRenderer(this, 32, 56);
		this.needle2.addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 3.0F);
		this.needle2.setPos(0.0F, -0.5F, 2.75F);
		this.needle1.addChild(this.needle2);

		this.rightFang = new ModelRenderer(this, 24, 0);
		this.rightFang.addBox(-1.0F, -1.0F, -0.75F, 2.0F, 3.0F, 1.0F);
		this.rightFang.setPos(1.25F, 1.0F, -4.0F);
		this.head.addChild(this.rightFang);
		this.leftFang = new ModelRenderer(this, 24, 0);
		this.leftFang.mirror = true;
		this.leftFang.addBox(-1.0F, -1.0F, -0.75F, 2.0F, 4.0F, 1.0F);
		this.leftFang.setPos(-1.25F, 1.0F, -4.0F);
		this.head.addChild(this.leftFang);

		byte b1 = 3;
		byte b2 = 4;
		byte b3 = 4;
		byte b4 = 7;

		this.leg1A = new ModelRenderer(this, 0, 16);
		this.leg1A.addBox(-((float)b1) + 0.5F, -1.0F, -1.0F, (float)b1, 2.0F, 2.0F, -0.25F);
		this.leg1A.setPos(-3.75F, (float)b0 + 0.5F, -0.25F);
		this.leg2A = new ModelRenderer(this, 0, 16);
		this.leg2A.addBox(-0.5F, -1.0F, -1.0F, (float)b1, 2.0F, 2.0F, -0.25F);
		this.leg2A.setPos(3.75F, (float)b0 + 0.5F, -0.25F);
		this.leg3A = new ModelRenderer(this, 0, 16);
		this.leg3A.addBox(-((float)b1) + 0.5F, -1.0F, -1.0F, (float)b1, 2.0F, 2.0F, -0.25F);
		this.leg3A.setPos(-3.75F, (float)b0 + 0.5F, -1.5F);
		this.leg4A = new ModelRenderer(this, 0, 16);
		this.leg4A.addBox(-0.5F, -1.0F, -1.0F, (float)b1, 2.0F, 2.0F, -0.25F);
		this.leg4A.setPos(3.75F, (float)b0 + 0.5F, -1.5F);
		this.leg5A = new ModelRenderer(this, 0, 16);
		this.leg5A.addBox(-((float)b1) + 0.5F, -1.0F, -1.0F, (float)b1, 2.0F, 2.0F, -0.25F);
		this.leg5A.setPos(-3.75F, (float)b0 + 0.5F, -2.75F);
		this.leg6A = new ModelRenderer(this, 0, 16);
		this.leg6A.addBox(-0.5F, -1.0F, -1.0F, (float)b1, 2.0F, 2.0F, -0.25F);
		this.leg6A.setPos(3.75F, (float)b0 + 0.5F, -2.75F);
		this.leg7A = new ModelRenderer(this, 0, 16);
		this.leg7A.addBox(-((float)b1) + 0.5F, -1.0F, -1.0F, (float)b1, 2.0F, 2.0F, -0.25F);
		this.leg7A.setPos(-3.75F, (float)b0 + 0.5F, -4.0F);
		this.leg8A = new ModelRenderer(this, 0, 16);
		this.leg8A.addBox(-0.5F, -1.0F, -1.0F, (float)b1, 2.0F, 2.0F, -0.25F);
		this.leg8A.setPos(3.75F, (float)b0 + 0.5F, -4.0F);

		this.leg1B = new ModelRenderer(this, 0, 20);
		this.leg1B.addBox(-((float)b2) + 0.5F, -1.0F, -1.0F, (float)b2, 2.0F, 2.0F, -0.2501F);
		this.leg1B.setPos(-((float)b1) + 1.0F, 0.0F, 0.0F);
		this.leg1A.addChild(this.leg1B);
		this.leg2B = new ModelRenderer(this, 0, 20);
		this.leg2B.addBox(-0.5F, -1.0F, -1.0F, (float)b2, 2.0F, 2.0F, -0.251F);
		this.leg2B.setPos((float)b1 - 1.0F, 0.0F, 0.0F);
		this.leg2A.addChild(this.leg2B);
		this.leg3B = new ModelRenderer(this, 0, 20);
		this.leg3B.addBox(-((float)b2) + 0.5F, -1.0F, -1.0F, (float)b2, 2.0F, 2.0F, -0.2501F);
		this.leg3B.setPos(-((float)b1) + 1.0F, 0.0F, 0.0F);
		this.leg3A.addChild(this.leg3B);
		this.leg4B = new ModelRenderer(this, 0, 20);
		this.leg4B.addBox(-0.5F, -1.0F, -1.0F, (float)b2, 2.0F, 2.0F, -0.251F);
		this.leg4B.setPos((float)b1 - 1.0F, 0.0F, 0.0F);
		this.leg4A.addChild(this.leg4B);
		this.leg5B = new ModelRenderer(this, 0, 20);
		this.leg5B.addBox(-((float)b2) + 0.5F, -1.0F, -1.0F, (float)b2, 2.0F, 2.0F, -0.2501F);
		this.leg5B.setPos(-((float)b1) + 1.0F, 0.0F, 0.0F);
		this.leg5A.addChild(this.leg5B);
		this.leg6B = new ModelRenderer(this, 0, 20);
		this.leg6B.addBox(-0.5F, -1.0F, -1.0F, (float)b2, 2.0F, 2.0F, -0.251F);
		this.leg6B.setPos((float)b1 - 1.0F, 0.0F, 0.0F);
		this.leg6A.addChild(this.leg6B);
		this.leg7B = new ModelRenderer(this, 0, 20);
		this.leg7B.addBox(-((float)b2) + 0.5F, -1.0F, -1.0F, (float)b2, 2.0F, 2.0F, -0.2501F);
		this.leg7B.setPos(-((float)b1) + 1.0F, 0.0F, 0.0F);
		this.leg7A.addChild(this.leg7B);
		this.leg8B = new ModelRenderer(this, 0, 20);
		this.leg8B.addBox(-0.5F, -1.0F, -1.0F, (float)b2, 2.0F, 2.0F, -0.251F);
		this.leg8B.setPos((float)b1 - 1.0F, 0.0F, 0.0F);
		this.leg8A.addChild(this.leg8B);

		this.leg1C = new ModelRenderer(this, 16, 16);
		this.leg1C.addBox(-((float)b3) + 0.5F, -1.0F, -1.0F, (float)b3, 2.0F, 2.0F, -0.25F);
		this.leg1C.setPos(-((float)b2) + 1.0F, 0.0F, 0.0F);
		this.leg1B.addChild(this.leg1C);
		this.leg2C = new ModelRenderer(this, 16, 16);
		this.leg2C.addBox(-0.5F, -1.0F, -1.0F, (float)b3, 2.0F, 2.0F, -0.25F);
		this.leg2C.setPos((float)b2 - 1.0F, 0.0F, 0.0F);
		this.leg2B.addChild(this.leg2C);
		this.leg3C = new ModelRenderer(this, 16, 16);
		this.leg3C.addBox(-((float)b3) + 0.5F, -1.0F, -1.0F, (float)b3, 2.0F, 2.0F, -0.25F);
		this.leg3C.setPos(-((float)b2) + 1.0F, 0.0F, 0.0F);
		this.leg3B.addChild(this.leg3C);
		this.leg4C = new ModelRenderer(this, 16, 16);
		this.leg4C.addBox(-0.5F, -1.0F, -1.0F, (float)b3, 2.0F, 2.0F, -0.25F);
		this.leg4C.setPos((float)b2 - 1.0F, 0.0F, 0.0F);
		this.leg4B.addChild(this.leg4C);
		this.leg5C = new ModelRenderer(this, 16, 16);
		this.leg5C.addBox(-((float)b3) + 0.5F, -1.0F, -1.0F, (float)b3, 2.0F, 2.0F, -0.25F);
		this.leg5C.setPos(-((float)b2) + 1.0F, 0.0F, 0.0F);
		this.leg5B.addChild(this.leg5C);
		this.leg6C = new ModelRenderer(this, 16, 16);
		this.leg6C.addBox(-0.5F, -1.0F, -1.0F, (float)b3, 2.0F, 2.0F, -0.25F);
		this.leg6C.setPos((float)b2 - 1.0F, 0.0F, 0.0F);
		this.leg6B.addChild(this.leg6C);
		this.leg7C = new ModelRenderer(this, 16, 16);
		this.leg7C.addBox(-((float)b3) + 0.5F, -1.0F, -1.0F, (float)b3, 2.0F, 2.0F, -0.25F);
		this.leg7C.setPos(-((float)b2) + 1.0F, 0.0F, 0.0F);
		this.leg7B.addChild(this.leg7C);
		this.leg8C = new ModelRenderer(this, 16, 16);
		this.leg8C.addBox(-0.5F, -1.0F, -1.0F, (float)b3, 2.0F, 2.0F, -0.25F);
		this.leg8C.setPos((float)b2 - 1.0F, 0.0F, 0.0F);
		this.leg8B.addChild(this.leg8C);

		this.leg1D = new ModelRenderer(this, 16, 20);
		this.leg1D.addBox(-((float)b4) + 0.5F, -1.0F, -1.0F, (float)b4, 2.0F, 2.0F, -0.5F);
		this.leg1D.setPos(-((float)b3) + 1.5F, 0.0F, 0.0F);
		this.leg1C.addChild(this.leg1D);
		this.leg2D = new ModelRenderer(this, 16, 20);
		this.leg2D.addBox(-0.5F, -1.0F, -1.0F, (float)b4, 2.0F, 2.0F, -0.5F);
		this.leg2D.setPos((float)b3 - 1.5F, 0.0F, 0.0F);
		this.leg2C.addChild(this.leg2D);
		this.leg3D = new ModelRenderer(this, 16, 20);
		this.leg3D.addBox(-((float)b4) + 0.5F, -1.0F, -1.0F, (float)b4, 2.0F, 2.0F, -0.5F);
		this.leg3D.setPos(-((float)b3) + 1.5F, 0.0F, 0.0F);
		this.leg3C.addChild(this.leg3D);
		this.leg4D = new ModelRenderer(this, 16, 20);
		this.leg4D.addBox(-0.5F, -1.0F, -1.0F, (float)b4, 2.0F, 2.0F, -0.5F);
		this.leg4D.setPos((float)b3 - 1.5F, 0.0F, 0.0F);
		this.leg4C.addChild(this.leg4D);
		this.leg5D = new ModelRenderer(this, 16, 20);
		this.leg5D.addBox(-((float)b4) + 0.5F, -1.0F, -1.0F, (float)b4, 2.0F, 2.0F, -0.5F);
		this.leg5D.setPos(-((float)b3) + 1.5F, 0.0F, 0.0F);
		this.leg5C.addChild(this.leg5D);
		this.leg6D = new ModelRenderer(this, 16, 20);
		this.leg6D.addBox(-0.5F, -1.0F, -1.0F, (float)b4, 2.0F, 2.0F, -0.5F);
		this.leg6D.setPos((float)b3 - 1.5F, 0.0F, 0.0F);
		this.leg6C.addChild(this.leg6D);
		this.leg7D = new ModelRenderer(this, 16, 20);
		this.leg7D.addBox(-((float)b4) + 0.5F, -1.0F, -1.0F, (float)b4, 2.0F, 2.0F, -0.5F);
		this.leg7D.setPos(-((float)b3) + 1.5F, 0.0F, 0.0F);
		this.leg7C.addChild(this.leg7D);
		this.leg8D = new ModelRenderer(this, 16, 20);
		this.leg8D.addBox(-0.5F, -1.0F, -1.0F, (float)b4, 2.0F, 2.0F, -0.5F);
		this.leg8D.setPos((float)b3 - 1.5F, 0.0F, 0.0F);
		this.leg8C.addChild(this.leg8D);

		this.rightScissorArm1 = new ModelRenderer(this, 48, 16);
		this.rightScissorArm1.addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F);
		this.rightScissorArm1.setPos(-4.0F, (float)b0, -7.0F);
		this.rightScissorArm2 = new ModelRenderer(this, 48, 24);
		this.rightScissorArm2.addBox(-1.0F, -1.0F, -4.5F, 2.0F, 2.0F, 5.0F);
		this.rightScissorArm2.setPos(0.0F, 0.0F, -3.5F);
		this.rightScissorArm1.addChild(rightScissorArm2);
		this.rightScissorArm3 = new ModelRenderer(this, 48, 32);
		this.rightScissorArm3.addBox(-1.0F, -1.0F, -4.5F, 2.0F, 2.0F, 5.0F, 0.25F);
		this.rightScissorArm3.setPos(0.0F, 0.0F, -4.5F);
		this.rightScissorArm2.addChild(rightScissorArm3);
		this.rightScissorArm4 = new ModelRenderer(this, 48, 40);
		this.rightScissorArm4.addBox(-1.5F, -1.0F, -4.5F, 3.0F, 2.0F, 4.0F, 0.25F);
		this.rightScissorArm4.setPos(0.0F, 0.0F, -4.5F);
		this.rightScissorArm3.addChild(rightScissorArm4);

		this.leftScissorArm1 = new ModelRenderer(this, 48, 16);
		this.leftScissorArm1.mirror = true;
		this.leftScissorArm1.addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F);
		this.leftScissorArm1.setPos(4.0F, (float)b0, -7.0F);
		this.leftScissorArm2 = new ModelRenderer(this, 48, 24);
		this.leftScissorArm2.mirror = true;
		this.leftScissorArm2.addBox(-1.0F, -1.0F, -4.5F, 2.0F, 2.0F, 5.0F);
		this.leftScissorArm2.setPos(0.0F, 0.0F, -3.5F);
		this.leftScissorArm1.addChild(leftScissorArm2);
		this.leftScissorArm3 = new ModelRenderer(this, 48, 32);
		this.leftScissorArm3.mirror = true;
		this.leftScissorArm3.addBox(-1.0F, -1.0F, -4.5F, 2.0F, 2.0F, 5.0F, 0.25F);
		this.leftScissorArm3.setPos(0.0F, 0.0F, -4.5F);
		this.leftScissorArm2.addChild(leftScissorArm3);
		this.leftScissorArm4 = new ModelRenderer(this, 48, 40);
		this.leftScissorArm4.mirror = true;
		this.leftScissorArm4.addBox(-1.5F, -1.0F, -4.5F, 3.0F, 2.0F, 4.0F, 0.25F);
		this.leftScissorArm4.setPos(0.0F, 0.0F, -4.5F);
		this.leftScissorArm3.addChild(leftScissorArm4);

		this.rightScissor1 = new ModelRenderer(this, 48, 48);
		this.rightScissor1.addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F);
		this.rightScissor1.setPos(-0.75F, 0.0F, -4.5F);
		this.rightScissorArm4.addChild(this.rightScissor1);
		this.rightScissor2 = new ModelRenderer(this, 48, 52);
		this.rightScissor2.addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F, 0.25F);
		this.rightScissor2.setPos(0.75F, 0.0F, -4.5F);
		this.rightScissorArm4.addChild(this.rightScissor2);
		this.leftScissor1 = new ModelRenderer(this, 48, 48);
		this.leftScissor1.mirror = true;
		this.leftScissor1.addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F);
		this.leftScissor1.setPos(0.75F, 0.0F, -4.5F);
		this.leftScissorArm4.addChild(this.leftScissor1);
		this.leftScissor2 = new ModelRenderer(this, 48, 52);
		this.leftScissor2.mirror = true;
		this.leftScissor2.addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F, 0.25F);
		this.leftScissor2.setPos(-0.75F, 0.0F, -4.5F);
		this.leftScissorArm4.addChild(this.leftScissor2);
	}

	@Override
	public Iterable<ModelRenderer> parts()
	{
		return ImmutableList.of(this.head, this.neck, this.body, this.leg1A, this.leg2A, this.leg3A, this.leg4A, this.leg5A, this.leg6A, this.leg7A, this.leg8A, this.rightScissorArm1, this.leftScissorArm1);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180.0F);
		this.head.xRot = headPitch * ((float)Math.PI / 180.0F);
		this.head.xRot += ((float)Math.PI / 15.0F);

		this.bodyPart1.xRot = (float)Math.PI / 18.0F;
		this.bodyPart2.xRot = (float)Math.PI / 11.0F;
		this.bodyPart3.xRot = (float)Math.PI / 8.0F;
		this.bodyPart4.xRot = (float)Math.PI / 6.0F;

		this.bodyPart1.xRot += MathHelper.sin(ageInTicks * 0.03F) * 0.021F;
		this.bodyPart2.xRot += MathHelper.sin(ageInTicks * 0.03F - (float)Math.PI / 9.0F) * 0.024F;
		this.bodyPart3.xRot += MathHelper.sin(ageInTicks * 0.03F - (float)Math.PI * 2.0F / 9.0F) * 0.024F;
		this.bodyPart4.xRot += MathHelper.sin(ageInTicks * 0.03F - (float)Math.PI / 3.0F) * 0.021F;

		float f = (float)Math.PI / 7.0F;
		float f1 = (float)Math.PI * 2.0F / 17.0F;

		this.tail1.xRot = f;
		this.tail2.xRot = f;
		this.tail3.xRot = f1;
		this.tail4.xRot = f1;
		this.tail5.xRot = (float)Math.PI / 11.0F;
		this.needle1.xRot = f1;
		this.needle2.xRot = -((float)Math.PI / 9.0F);

		f = MathHelper.sin(ageInTicks * 0.03F + (float)Math.PI / 3.0F);
		f1 = MathHelper.sin(ageInTicks * 0.03F + (float)Math.PI * 5.0F / 9.0F);

		this.tail1.xRot -= f * 0.015F;
		this.tail2.xRot -= f * 0.021F;
		this.tail3.xRot -= f * 0.033F;
		this.tail4.xRot -= f1 * 0.045F;
		this.tail5.xRot -= f1 * 0.045F;
		this.needle1.xRot -= f1 * 0.021F;

		this.rightFang.xRot = ((float)Math.PI / 21.0F);
		this.leftFang.xRot = ((float)Math.PI / 21.0F);
		this.rightFang.y = 1.0F;
		this.leftFang.y = 1.0F;
		this.rightFang.y -= (MathHelper.sin(ageInTicks * 0.042F + (float)Math.PI) * 0.2F);
		this.leftFang.y -= (MathHelper.sin(ageInTicks * 0.042F + (float)Math.PI) * 0.2F);

		float f2 = (float)Math.PI / 4.0F;

		this.leg1A.zRot = -f2;
		this.leg2A.zRot = f2;
		this.leg3A.zRot = -f2 * 0.74F;
		this.leg4A.zRot = f2 * 0.74F;
		this.leg5A.zRot = -f2 * 0.74F;
		this.leg6A.zRot = f2 * 0.74F;
		this.leg7A.zRot = -f2;
		this.leg8A.zRot = f2;

		this.leg1A.yRot = f2 * 1.0F;
		this.leg2A.yRot = -f2 * 1.0F;
		this.leg3A.yRot = f2 * 0.5F;
		this.leg4A.yRot = -f2 * 0.5F;
		this.leg5A.yRot = -f2 * 0.1F;
		this.leg6A.yRot = f2 * 0.1F;
		this.leg7A.yRot = -f2 * 0.7F;
		this.leg8A.yRot = f2 * 0.7F;

		f2 = (float)Math.PI * 3.0F / 7.0F;

		this.leg1B.zRot = f2;
		this.leg2B.zRot = -f2;
		this.leg3B.zRot = f2;
		this.leg4B.zRot = -f2;
		this.leg5B.zRot = f2;
		this.leg6B.zRot = -f2;
		this.leg7B.zRot = f2;
		this.leg8B.zRot = -f2;

		f2 = (float)Math.PI * 4.0F / 9.0F;

		this.leg1C.zRot = -f2;
		this.leg2C.zRot = f2;
		this.leg3C.zRot = -f2;
		this.leg4C.zRot = f2;
		this.leg5C.zRot = -f2;
		this.leg6C.zRot = f2;
		this.leg7C.zRot = -f2;
		this.leg8C.zRot = f2;

		f2 = (float)Math.PI / 5.0F;

		this.leg1D.zRot = -f2;
		this.leg2D.zRot = f2;
		this.leg3D.zRot = -f2;
		this.leg4D.zRot = f2;
		this.leg5D.zRot = -f2;
		this.leg6D.zRot = f2;
		this.leg7D.zRot = -f2;
		this.leg8D.zRot = f2;

		f = (float)Math.PI / 5.0F;
		f1 = (float)Math.PI * 3.0F / 5.0F;
		f2 = (float)Math.PI * 3.0F / 4.0F;

		this.leftScissorArm1.yRot = -f;
		this.rightScissorArm1.yRot = f;
		this.rightScissorArm1.xRot = (float)Math.PI / 15.0F;
		this.leftScissorArm1.xRot = (float)Math.PI / 15.0F;
		this.rightScissorArm1.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.015F;
		this.leftScissorArm1.xRot += MathHelper.sin(ageInTicks * 0.06F) * 0.015F;
		this.rightScissorArm1.yRot += MathHelper.sin(ageInTicks * 0.03F) * 0.03F;
		this.leftScissorArm1.yRot -= MathHelper.sin(ageInTicks * 0.03F) * 0.03F;
		this.leftScissorArm2.yRot = -f1;
		this.rightScissorArm2.yRot = f1;
		this.rightScissorArm2.yRot += MathHelper.sin(ageInTicks * 0.042F) * 0.054F;
		this.leftScissorArm2.yRot -= MathHelper.sin(ageInTicks * 0.042F) * 0.054F;
		this.rightScissorArm2.xRot = MathHelper.sin(ageInTicks * 0.084F + ((float)Math.PI / 3.0F)) * 0.021F;
		this.leftScissorArm2.xRot = MathHelper.sin(ageInTicks * 0.084F + ((float)Math.PI / 3.0F)) * 0.021F;
		this.leftScissorArm3.yRot = f2;
		this.rightScissorArm3.yRot = -f2;
		this.rightScissorArm3.yRot -= MathHelper.sin(ageInTicks * 0.084F + ((float)Math.PI / 5.0F)) * 0.067F;
		this.leftScissorArm3.yRot += MathHelper.sin(ageInTicks * 0.084F + ((float)Math.PI / 5.0F)) * 0.067F;
		this.leftScissorArm4.yRot = (float)Math.PI / 11.0F;
		this.rightScissorArm4.yRot = -((float)Math.PI / 11.0F);
		this.rightScissorArm4.yRot -= MathHelper.sin(ageInTicks * 0.03F + ((float)Math.PI / 4.0F)) * 0.045F;
		this.leftScissorArm4.yRot += MathHelper.sin(ageInTicks * 0.03F + ((float)Math.PI * 3.0F / 4.0F)) * 0.045F;

		this.leftScissorArm4.zRot = (float)Math.PI / 6.0F;
		this.rightScissorArm4.zRot = -((float)Math.PI / 6.0F);
		this.rightScissorArm4.zRot -= MathHelper.sin(ageInTicks * 0.03F + ((float)Math.PI / 4.0F)) * 0.03F;
		this.leftScissorArm4.zRot += MathHelper.sin(ageInTicks * 0.03F + ((float)Math.PI * 3.0F / 4.0F)) * 0.03F;

		this.rightScissor1.yRot = Math.abs(MathHelper.sin(ageInTicks * 0.06F)) * 0.18F;
		this.leftScissor1.yRot = -Math.abs(MathHelper.sin(ageInTicks * 0.06F)) * 0.18F;

		if (entityIn.isOnGround())
		{
			float f3 = -(MathHelper.cos(limbSwing * 0.67F * 2.0F) * 0.4F) * limbSwingAmount;
			float f4 = -(MathHelper.cos(limbSwing * 0.67F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
			float f5 = -(MathHelper.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI / 2.0F)) * 0.4F) * limbSwingAmount;
			float f6 = -(MathHelper.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI * 3.0F / 2.0F)) * 0.4F) * limbSwingAmount;
			float f7 = Math.abs(MathHelper.sin(limbSwing * 0.67F) * 0.4F) * limbSwingAmount;
			float f8 = Math.abs(MathHelper.sin(limbSwing * 0.67F + (float)Math.PI) * 0.4F) * limbSwingAmount;
			float f9 = Math.abs(MathHelper.sin(limbSwing * 0.67F + ((float)Math.PI / 2.0F)) * 0.4F) * limbSwingAmount;
			float f10 = Math.abs(MathHelper.sin(limbSwing * 0.67F + ((float)Math.PI * 3.0F / 2.0F)) * 0.4F) * limbSwingAmount;
			float f11 = Math.abs(MathHelper.sin(limbSwing * 0.67F - ((float)Math.PI / 8.0F)) * 0.24F) * limbSwingAmount;
			float f12 = Math.abs(MathHelper.sin(limbSwing * 0.67F + ((float)Math.PI * 7.0F / 8.0F)) * 0.24F) * limbSwingAmount;
			float f13 = Math.abs(MathHelper.sin(limbSwing * 0.67F + ((float)Math.PI * 3.0F / 8.0F)) * 0.24F) * limbSwingAmount;
			float f14 = Math.abs(MathHelper.sin(limbSwing * 0.67F + ((float)Math.PI * 11.0F / 8.0F)) * 0.24F) * limbSwingAmount;
			this.leg1A.yRot += f3;
			this.leg2A.yRot += -f3;
			this.leg3A.yRot += f4;
			this.leg4A.yRot += -f4;
			this.leg5A.yRot += f5;
			this.leg6A.yRot += -f5;
			this.leg7A.yRot += f6;
			this.leg8A.yRot += -f6;
			this.leg1A.zRot += f7;
			this.leg2A.zRot += -f7;
			this.leg3A.zRot += f8;
			this.leg4A.zRot += -f8;
			this.leg5A.zRot += f9;
			this.leg6A.zRot += -f9;
			this.leg7A.zRot += f10;
			this.leg8A.zRot += -f10;
			this.leg1B.zRot += -f11;
			this.leg2B.zRot += f11;
			this.leg3B.zRot += -f12;
			this.leg4B.zRot += f12;
			this.leg5B.zRot += -f13;
			this.leg6B.zRot += f13;
			this.leg7B.zRot += -f14;
			this.leg8B.zRot += f14;
			this.leg1C.zRot += f7 * 0.45F;
			this.leg2C.zRot += -f7 * 0.45F;
			this.leg3C.zRot += f8 * 0.45F;
			this.leg4C.zRot += -f8 * 0.45F;
			this.leg5C.zRot += f9 * 0.45F;
			this.leg6C.zRot += -f9 * 0.45F;
			this.leg7C.zRot += f10 * 0.45F;
			this.leg8C.zRot += -f10 * 0.45F;
			this.leg1D.zRot += f7 * 0.21F;
			this.leg2D.zRot += -f7 * 0.21F;
			this.leg3D.zRot += f8 * 0.21F;
			this.leg4D.zRot += -f8 * 0.21F;
			this.leg5D.zRot += f9 * 0.21F;
			this.leg6D.zRot += -f9 * 0.21F;
			this.leg7D.zRot += f10 * 0.21F;
			this.leg8D.zRot += -f10 * 0.21F;
		}

		this.bodyPart2.xRot += (MathHelper.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI / 5.0F)) * 0.33F) * limbSwingAmount * 0.12F;
		this.bodyPart3.xRot += (MathHelper.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI * 2.0F / 5.0F)) * 0.33F) * limbSwingAmount * 0.12F;
		this.bodyPart4.xRot += (MathHelper.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI * 3.0F / 5.0F)) * 0.33F) * limbSwingAmount * 0.09F;
		this.rightScissorArm1.xRot += (MathHelper.cos(limbSwing * 0.75F) * 0.4F) * limbSwingAmount * 0.09F;
		this.leftScissorArm1.xRot += (MathHelper.cos(limbSwing * 0.75F) * 0.4F) * limbSwingAmount * 0.09F;
		this.rightScissorArm2.yRot -= (MathHelper.cos(limbSwing * 0.67F * 2.0F) * 0.4F) * limbSwingAmount * 0.45F;
		this.leftScissorArm2.yRot += (MathHelper.cos(limbSwing * 0.67F * 2.0F) * 0.4F) * limbSwingAmount * 0.45F;
		this.rightScissorArm3.yRot += (MathHelper.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI / 4.0F)) * 0.4F) * limbSwingAmount * 0.6F;
		this.leftScissorArm3.yRot -= (MathHelper.cos(limbSwing * 0.67F * 2.0F + ((float)Math.PI / 4.0F)) * 0.4F) * limbSwingAmount * 0.6F;

		if (this.attackTime > 0.0F)
		{
			float f11 = 1.0F - this.attackTime;
			f11 = f11 * f11;
			f11 = f11 * f11;
			f11 = 1.0F - f11;
			float f12 = MathHelper.sin(f11 * (float)Math.PI);
			float f13 = MathHelper.sin(this.attackTime * (float)Math.PI) * 0.75F;
			float f14 = f12 * 0.5F + f13 * 0.3F;

			this.bodyPart1.xRot += f12 * 0.2F + f13 * 0.3F;
			this.bodyPart2.xRot += f12 * 0.2F + f13 * 0.3F;
			this.bodyPart3.xRot += f12 * 0.2F + f13 * 0.4F;
			this.bodyPart4.xRot += f12 * 0.1F + f13 * 0.3F;
			this.tail1.xRot += f14 * 0.2F;
			this.tail2.xRot -= f14 * 0.3F;
			this.tail3.xRot -= f14 * 0.6F;
			this.tail4.xRot -= f14 * 0.9F;
			this.tail5.xRot -= f14 * 1.1F;
			this.needle1.xRot -= f14 * 0.9F;
			this.rightScissorArm3.yRot -= f12 * 0.2F + f13 * 0.3F;
			this.leftScissorArm3.yRot += f12 * 0.2F + f13 * 0.3F;
			this.rightScissorArm4.yRot -= f12 * 0.1F + f13 * 0.2F;
			this.leftScissorArm4.yRot += f12 * 0.1F + f13 * 0.2F;
			this.rightScissor1.yRot += f12 * 0.3F + f13 * 0.54F;
			this.leftScissor1.yRot -= f12 * 0.3F + f13 * 0.54F;
		}
	}
}