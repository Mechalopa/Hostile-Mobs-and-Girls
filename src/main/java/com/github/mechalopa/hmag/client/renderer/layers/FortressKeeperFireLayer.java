package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.client.model.FortressKeeperModel;
import com.github.mechalopa.hmag.entity.FortressKeeperEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

@OnlyIn(Dist.CLIENT)
public class FortressKeeperFireLayer extends RenderLayer<FortressKeeperEntity, FortressKeeperModel<FortressKeeperEntity>>
{
	public FortressKeeperFireLayer(RenderLayerParent<FortressKeeperEntity, FortressKeeperModel<FortressKeeperEntity>> renderLayerParent)
	{
		super(renderLayerParent);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, FortressKeeperEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		Minecraft minecraft = Minecraft.getInstance();
		boolean flag = minecraft.shouldEntityAppearGlowing(entity) && entity.isInvisible();

		if (!entity.isInvisible() || flag)
		{
			if (!entity.isInWaterOrBubble())
			{
				this.renderFire(poseStack, buffer, packedLight, flag, entity, Minecraft.getInstance().getBlockRenderer(), HumanoidArm.RIGHT);
				this.renderFire(poseStack, buffer, packedLight, flag, entity, Minecraft.getInstance().getBlockRenderer(), HumanoidArm.LEFT);
			}
		}
	}

	private void renderFire(PoseStack poseStack, MultiBufferSource buffer, int packedLight, boolean flag, FortressKeeperEntity entity, BlockRenderDispatcher renderDispatcher, HumanoidArm hand)
	{
		poseStack.pushPose();
		this.getParentModel().translateToHand(hand, poseStack);
		float f = 0.4375F;
		poseStack.translate((hand == HumanoidArm.LEFT ? 0.25D : -0.25D) + 0.21875D, -0.5D, -0.21875D);
		poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
		poseStack.scale(f, -f, -f);
		BlockState state = Blocks.FIRE.defaultBlockState();

		if (flag)
		{
			renderDispatcher.getModelRenderer().renderModel(poseStack.last(), buffer.getBuffer(RenderType.outline(InventoryMenu.BLOCK_ATLAS)), state, renderDispatcher.getBlockModel(state), 0.0F, 0.0F, 0.0F, packedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), EmptyModelData.INSTANCE);
		}
		else
		{
			renderDispatcher.renderSingleBlock(state, poseStack, buffer, packedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), EmptyModelData.INSTANCE);
		}

		poseStack.popPose();
	}
}