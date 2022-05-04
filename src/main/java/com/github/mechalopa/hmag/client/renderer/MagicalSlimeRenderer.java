package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.MagicalSlimeModel;
import com.github.mechalopa.hmag.client.renderer.layers.MagicalSlimeOuterLayer;
import com.github.mechalopa.hmag.entity.MagicalSlimeEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagicalSlimeRenderer extends MobRenderer<MagicalSlimeEntity, MagicalSlimeModel<MagicalSlimeEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/magical_slime.png");

	public MagicalSlimeRenderer(EntityRendererProvider.Context context)
	{
		super(context, new MagicalSlimeModel<>(context.bakeLayer(ModModelLayers.MAGICAL_SLIME)), 0.25F);
		this.addLayer(new MagicalSlimeOuterLayer<>(this, context.getModelSet()));
	}

	@Override
	public void render(MagicalSlimeEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLightIn)
	{
		this.shadowRadius = 0.25F * (float)entityIn.getSize();
		super.render(entityIn, entityYaw, partialTicks, poseStack, buffer, packedLightIn);
	}

	@Override
	protected void scale(MagicalSlimeEntity entityIn, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(0.999F, 0.999F, 0.999F);
		poseStack.translate(0.0D, (double)0.001F, 0.0D);
		float f = (float)entityIn.getSize();
		float f1 = Mth.lerp(partialTickTime, entityIn.oSquish, entityIn.squish) / (f * 0.5F + 1.0F);
		float f2 = 1.0F / (f1 + 1.0F);
		poseStack.scale(f2 * f, 1.0F / f2 * f, f2 * f);
	}

	@Override
	public ResourceLocation getTextureLocation(MagicalSlimeEntity entityIn)
	{
		return TEX;
	}
}