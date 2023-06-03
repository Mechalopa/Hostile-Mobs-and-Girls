package com.github.mechalopa.hmag.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ItemInHandLayer2<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> extends ItemInHandLayer<T, M>
{
	private final ItemInHandRenderer itemInHandRenderer;
	private final int translateX;

	public ItemInHandLayer2(RenderLayerParent<T, M> renderLayerParent, ItemInHandRenderer renderer)
	{
		this(renderLayerParent, renderer, 0);
	}

	public ItemInHandLayer2(RenderLayerParent<T, M> renderLayerParent, ItemInHandRenderer renderer, int itemTranslateX)
	{
		super(renderLayerParent, renderer);
		this.itemInHandRenderer = renderer;
		this.translateX = itemTranslateX;
	}

	@Override
	protected void renderArmWithItem(LivingEntity livingEntity, ItemStack stack, ItemDisplayContext type, HumanoidArm handSide, PoseStack poseStack, MultiBufferSource buffer, int packedLight)
	{
		if (!stack.isEmpty())
		{
			poseStack.pushPose();
			this.getParentModel().translateToHand(handSide, poseStack);
			poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
			boolean flag = handSide == HumanoidArm.LEFT;
			poseStack.translate((double)((float)(flag ? -this.translateX : this.translateX) / 16.0F), 0.125D, -0.625D);
			this.itemInHandRenderer.renderItem(livingEntity, stack, type, flag, poseStack, buffer, packedLight);
			poseStack.popPose();
		}
	}
}