package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.SlimeGirlModel;
import com.github.mechalopa.hmag.client.renderer.layers.SlimeGirlLayer;
import com.github.mechalopa.hmag.entity.SlimeGirlEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlimeGirlRenderer extends AbstractGirlRenderer<SlimeGirlEntity, SlimeGirlModel<SlimeGirlEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/slime_girl.png");

	public SlimeGirlRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new SlimeGirlModel<>(), 0.5F, -1);
		this.addLayer(new SlimeGirlLayer(this));
	}

	@Override
	protected void scale(SlimeGirlEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(0.999F, 0.999F, 0.999F);
		matrixStackIn.translate(0.0D, (double)0.001F, 0.0D);
		float f1 = 1.0F;
		float f2 = MathHelper.lerp(partialTickTime, entitylivingbaseIn.oSquish, entitylivingbaseIn.squish) / (f1 * 0.5F + 3.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		matrixStackIn.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	@Override
	public ResourceLocation getTextureLocation(SlimeGirlEntity entityIn)
	{
		return TEX;
	}
}