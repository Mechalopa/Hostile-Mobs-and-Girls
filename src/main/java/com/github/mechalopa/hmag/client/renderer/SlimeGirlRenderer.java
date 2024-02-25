package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.SlimeGirlModel;
import com.github.mechalopa.hmag.client.renderer.layers.SlimeGirlEyesLayer;
import com.github.mechalopa.hmag.client.renderer.layers.SlimeGirlOuterLayer;
import com.github.mechalopa.hmag.world.entity.SlimeGirlEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlimeGirlRenderer extends AbstractGirlRenderer<SlimeGirlEntity, SlimeGirlModel<SlimeGirlEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/slime_girl.png");

	public SlimeGirlRenderer(EntityRendererProvider.Context context)
	{
		super(context, new SlimeGirlModel<>(context.bakeLayer(ModModelLayers.SLIME_GIRL)), 0.5F, -1);
		this.addLayer(new SlimeGirlEyesLayer(this, context.getModelSet()));
		this.addLayer(new SlimeGirlOuterLayer(this, context.getModelSet()));
	}

	@Override
	protected void scale(SlimeGirlEntity entity, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(0.999F, 0.999F, 0.999F);
		poseStack.translate(0.0D, (double)0.001F, 0.0D);
		float f1 = 1.0F;
		float f2 = Mth.lerp(partialTickTime, entity.oSquish, entity.squish) / (f1 * 0.5F + 3.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		poseStack.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	@Override
	public ResourceLocation getTextureLocation(SlimeGirlEntity entity)
	{
		return TEXTURE;
	}
}