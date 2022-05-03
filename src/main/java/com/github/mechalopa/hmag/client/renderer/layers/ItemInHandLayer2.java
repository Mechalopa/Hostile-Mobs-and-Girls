package com.github.mechalopa.hmag.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ItemInHandLayer2<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> extends ItemInHandLayer<T, M>
{
	private final int translateX;

	public ItemInHandLayer2(RenderLayerParent<T, M> entityRendererIn)
	{
		this(entityRendererIn, 0);
	}

	public ItemInHandLayer2(RenderLayerParent<T, M> entityRendererIn, int heldItemTranslateX)
	{
		super(entityRendererIn);
		this.translateX = heldItemTranslateX;
	}

	@Override
	public void render(PoseStack poseStackIn, MultiBufferSource bufferIn, int packedLightIn, T livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		boolean flag = livingEntityIn.getMainArm() == HumanoidArm.RIGHT;
		ItemStack stack = flag ? livingEntityIn.getOffhandItem() : livingEntityIn.getMainHandItem();
		ItemStack stack1 = flag ? livingEntityIn.getMainHandItem() : livingEntityIn.getOffhandItem();

		if (!stack.isEmpty() || !stack1.isEmpty())
		{
			poseStackIn.pushPose();

			if (this.getParentModel().young)
			{
				poseStackIn.translate(0.0D, 0.75D, 0.0D);
				poseStackIn.scale(0.5F, 0.5F, 0.5F);
			}

			this.renderArmWithItem(livingEntityIn, stack1, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, poseStackIn, bufferIn, packedLightIn);
			this.renderArmWithItem(livingEntityIn, stack, ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, poseStackIn, bufferIn, packedLightIn);
			poseStackIn.popPose();
		}
	}

	@Override
	protected void renderArmWithItem(LivingEntity livingEntityIn, ItemStack stackIn, ItemTransforms.TransformType type, HumanoidArm handSide, PoseStack poseStackIn, MultiBufferSource bufferIn, int packedLightIn)
	{
		if (!stackIn.isEmpty())
		{
			poseStackIn.pushPose();
			this.getParentModel().translateToHand(handSide, poseStackIn);
			poseStackIn.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
			poseStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
			boolean flag = handSide == HumanoidArm.LEFT;
			poseStackIn.translate((double)((float)(flag ? -this.translateX : this.translateX) / 16.0F), 0.125D, -0.625D);
			Minecraft.getInstance().getItemInHandRenderer().renderItem(livingEntityIn, stackIn, type, flag, poseStackIn, bufferIn, packedLightIn);
			poseStackIn.popPose();
		}
	}
}