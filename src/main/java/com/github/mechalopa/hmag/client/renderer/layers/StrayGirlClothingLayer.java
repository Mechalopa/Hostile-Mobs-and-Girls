package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.SkeletonGirlModel;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrayGirlClothingLayer<T extends MobEntity & IRangedAttackMob, M extends EntityModel<T>> extends LayerRenderer<T, M>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/stray_girl_clothing.png");
	private final SkeletonGirlModel<T> layerModel = new SkeletonGirlModel<>(0.25F, true, true);

	public StrayGirlClothingLayer(IEntityRenderer<T, M> entityRendererIn)
	{
		super(entityRendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		coloredCutoutModelCopyLayerRender(this.getParentModel(), this.layerModel, TEX, matrixStackIn, bufferIn, packedLightIn, livingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
	}
}