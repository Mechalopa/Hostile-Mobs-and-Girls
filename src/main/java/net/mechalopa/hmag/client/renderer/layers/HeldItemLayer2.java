package net.mechalopa.hmag.client.renderer.layers;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HeldItemLayer2<T extends LivingEntity, M extends EntityModel<T> & IHasArm> extends HeldItemLayer<T, M>
{
	private final int translateX;

	public HeldItemLayer2(IEntityRenderer<T, M> entityRendererIn)
	{
		this(entityRendererIn, 0);
	}

	public HeldItemLayer2(IEntityRenderer<T, M> entityRendererIn, int heldItemTranslateX)
	{
		super(entityRendererIn);
		this.translateX = heldItemTranslateX;
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		boolean flag = livingEntityIn.getMainArm() == HandSide.RIGHT;
		ItemStack stack = flag ? livingEntityIn.getOffhandItem() : livingEntityIn.getMainHandItem();
		ItemStack stack1 = flag ? livingEntityIn.getMainHandItem() : livingEntityIn.getOffhandItem();

		if (!stack.isEmpty() || !stack1.isEmpty())
		{
			matrixStackIn.pushPose();

			if (this.getParentModel().young)
			{
				matrixStackIn.translate(0.0D, 0.75D, 0.0D);
				matrixStackIn.scale(0.5F, 0.5F, 0.5F);
			}

			this.renderArmWithItem(livingEntityIn, stack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, matrixStackIn, bufferIn, packedLightIn);
			this.renderArmWithItem(livingEntityIn, stack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, matrixStackIn, bufferIn, packedLightIn);
			matrixStackIn.popPose();
		}
	}

	private void renderArmWithItem(LivingEntity livingEntityIn, ItemStack stackIn, ItemCameraTransforms.TransformType type, HandSide handSide, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		if (!stackIn.isEmpty())
		{
			matrixStackIn.pushPose();
			this.getParentModel().translateToHand(handSide, matrixStackIn);
			matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
			matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
			boolean flag = handSide == HandSide.LEFT;
			matrixStackIn.translate((double)((float)(flag ? -this.translateX : this.translateX) / 16.0F), 0.125D, -0.625D);
			Minecraft.getInstance().getItemInHandRenderer().renderItem(livingEntityIn, stackIn, type, flag, matrixStackIn, bufferIn, packedLightIn);
			matrixStackIn.popPose();
		}
	}
}