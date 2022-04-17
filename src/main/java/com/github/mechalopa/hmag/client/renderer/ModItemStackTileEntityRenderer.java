package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.ModShieldModel;
import com.github.mechalopa.hmag.registry.ModItems;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModItemStackTileEntityRenderer extends ItemStackTileEntityRenderer
{
	private static final ResourceLocation ANCIENT_SHIELD_TEX = new ResourceLocation(HMaG.MODID, "textures/entity/shield/ancient_shield.png");
	private static final ResourceLocation FORTRESS_SHIELD_TEX = new ResourceLocation(HMaG.MODID, "textures/entity/shield/fortress_shield.png");
	private static final ModShieldModel ANCIENT_SHIELD_MODEL = new ModShieldModel();
	private static final ModShieldModel FORTRESS_SHIELD_MODEL = new ModShieldModel();

	@Override
	public void renderByItem(ItemStack stackIn, ItemCameraTransforms.TransformType transformTypeIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, int packedOverlayIn)
	{
		Item item = stackIn.getItem();

		if (item == ModItems.ANCIENT_SHIELD.get())
		{
			drawShieldModel(stackIn, transformTypeIn, matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, ANCIENT_SHIELD_TEX, ANCIENT_SHIELD_MODEL);
		}
		else if (item == ModItems.FORTRESS_SHIELD.get())
		{
			drawShieldModel(stackIn, transformTypeIn, matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, FORTRESS_SHIELD_TEX, FORTRESS_SHIELD_MODEL);
		}
	}

	private static void drawShieldModel(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, int packedOverlay, ResourceLocation resource, Model model)
	{
		matrixStack.pushPose();
		matrixStack.scale(1.0F, -1.0F, -1.0F);
		IVertexBuilder ivertexbuilder = ItemRenderer.getFoilBufferDirect(buffer, model.renderType(resource), false, stack.hasFoil());
		model.renderToBuffer(matrixStack, ivertexbuilder, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStack.popPose();
	}
}