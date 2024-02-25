package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.MagicalSlimeModel;
import com.github.mechalopa.hmag.client.renderer.layers.MagicalSlimeOuterLayer;
import com.github.mechalopa.hmag.world.entity.MagicalSlimeEntity;
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
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/magical_slime.png");

	public MagicalSlimeRenderer(EntityRendererProvider.Context context)
	{
		super(context, new MagicalSlimeModel<>(context.bakeLayer(ModModelLayers.MAGICAL_SLIME)), 0.25F);
		this.addLayer(new MagicalSlimeOuterLayer<>(this, context.getModelSet()));
	}

	@Override
	public void render(MagicalSlimeEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight)
	{
		this.shadowRadius = 0.25F * (float)entity.getSize();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	@Override
	protected void scale(MagicalSlimeEntity entity, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(0.999F, 0.999F, 0.999F);
		poseStack.translate(0.0D, (double)0.001F, 0.0D);
		float f = (float)entity.getSize();
		float f1 = Mth.lerp(partialTickTime, entity.oSquish, entity.squish) / (f * 0.5F + 1.0F);
		float f2 = 1.0F / (f1 + 1.0F);
		poseStack.scale(f2 * f, 1.0F / f2 * f, f2 * f);
	}

	@Override
	public ResourceLocation getTextureLocation(MagicalSlimeEntity entity)
	{
		return TEXTURE;
	}
}