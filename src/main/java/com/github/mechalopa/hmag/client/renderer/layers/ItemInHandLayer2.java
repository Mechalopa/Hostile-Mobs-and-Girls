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

	public ItemInHandLayer2(RenderLayerParent<T, M> renderLayerParent)
	{
		this(renderLayerParent, 0);
	}

	public ItemInHandLayer2(RenderLayerParent<T, M> renderLayerParent, int itemTranslateX)
	{
		super(renderLayerParent);
		this.translateX = itemTranslateX;
	}

	@Override
	protected void renderArmWithItem(LivingEntity livingEntity, ItemStack stack, ItemTransforms.TransformType type, HumanoidArm handSide, PoseStack poseStack, MultiBufferSource buffer, int packedLight)
	{
		if (!stack.isEmpty())
		{
			poseStack.pushPose();
			this.getParentModel().translateToHand(handSide, poseStack);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
			boolean flag = handSide == HumanoidArm.LEFT;
			poseStack.translate((double)((float)(flag ? -this.translateX : this.translateX) / 16.0F), 0.125D, -0.625D);
			Minecraft.getInstance().getItemInHandRenderer().renderItem(livingEntity, stack, type, flag, poseStack, buffer, packedLight);
			poseStack.popPose();
		}
	}
}