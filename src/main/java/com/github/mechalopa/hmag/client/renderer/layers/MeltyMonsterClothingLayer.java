package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.MeltyMonsterModel;
import com.github.mechalopa.hmag.entity.MeltyMonsterEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MeltyMonsterClothingLayer<T extends MeltyMonsterEntity, M extends MeltyMonsterModel<T>> extends EyesLayer<T, M>
{
	private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation(HMaG.MODID, "textures/entity/melty_monster_clothing.png"));

	public MeltyMonsterClothingLayer(RenderLayerParent<T, M> renderLayerParent)
	{
		super(renderLayerParent);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (!livingEntity.isInvisible())
		{
			super.render(poseStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
		}
	}

	@Override
	public RenderType renderType()
	{
		return RENDER_TYPE;
	}
}