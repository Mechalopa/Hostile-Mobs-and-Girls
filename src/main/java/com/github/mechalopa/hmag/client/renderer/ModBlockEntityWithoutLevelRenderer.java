package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.ModShieldModel;
import com.github.mechalopa.hmag.registry.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBlockEntityWithoutLevelRenderer extends BlockEntityWithoutLevelRenderer
{
	private static final ResourceLocation ANCIENT_SHIELD_TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/shield/ancient_shield.png");
	private static final ResourceLocation FORTRESS_SHIELD_TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/shield/fortress_shield.png");
	private ModShieldModel ancientShieldModel;
	private ModShieldModel fortressShieldModel;

	public ModBlockEntityWithoutLevelRenderer(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet modelSet)
	{
		super(renderDispatcher, modelSet);
		this.ancientShieldModel = new ModShieldModel(modelSet.bakeLayer(ModModelLayers.ANCIENT_SHIELD));
		this.fortressShieldModel = new ModShieldModel(modelSet.bakeLayer(ModModelLayers.FORTRESS_SHIELD));
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext type, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay)
	{
		Item item = stack.getItem();

		if (item == ModItems.ANCIENT_SHIELD.get())
		{
			drawShieldModel(stack, type, poseStack, buffer, packedLight, packedOverlay, ANCIENT_SHIELD_TEXTURE, ancientShieldModel);
		}
		else if (item == ModItems.FORTRESS_SHIELD.get())
		{
			drawShieldModel(stack, type, poseStack, buffer, packedLight, packedOverlay, FORTRESS_SHIELD_TEXTURE, fortressShieldModel);
		}
	}

	private static void drawShieldModel(ItemStack stack, ItemDisplayContext type, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, ResourceLocation resource, ModShieldModel model)
	{
		poseStack.pushPose();
		poseStack.scale(1.0F, -1.0F, -1.0F);
		VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(buffer, model.renderType(resource), false, stack.hasFoil());
		model.renderToBuffer(poseStack, vertexconsumer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
		poseStack.popPose();
	}
}