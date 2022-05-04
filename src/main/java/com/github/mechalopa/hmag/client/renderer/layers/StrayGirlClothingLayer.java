package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.StrayGirlClothingModel;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrayGirlClothingLayer<T extends Mob & RangedAttackMob, M extends EntityModel<T>> extends RenderLayer<T, M>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/stray_girl_clothing.png");
	private final StrayGirlClothingModel<T> layerModel;

	public StrayGirlClothingLayer(RenderLayerParent<T, M> renderLayerParent, EntityModelSet modelSet)
	{
		super(renderLayerParent);
		this.layerModel = new StrayGirlClothingModel<>(modelSet.bakeLayer(ModelLayers.STRAY_OUTER_LAYER));
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLightIn, T livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		coloredCutoutModelCopyLayerRender(this.getParentModel(), this.layerModel, TEX, poseStack, buffer, packedLightIn, livingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
	}
}