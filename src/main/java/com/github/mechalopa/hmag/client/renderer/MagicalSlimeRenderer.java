package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.MagicalSlimeModel;
import com.github.mechalopa.hmag.client.renderer.layers.MagicalSlimeGelLayer;
import com.github.mechalopa.hmag.entity.MagicalSlimeEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagicalSlimeRenderer extends MobRenderer<MagicalSlimeEntity, MagicalSlimeModel<MagicalSlimeEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/magical_slime.png");

	public MagicalSlimeRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new MagicalSlimeModel<>(16), 0.25F);
		this.addLayer(new MagicalSlimeGelLayer<>(this));
	}

	@Override
	public void render(MagicalSlimeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		this.shadowRadius = 0.25F * (float)entityIn.getSize();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	protected void scale(MagicalSlimeEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(0.999F, 0.999F, 0.999F);
		matrixStackIn.translate(0.0D, (double)0.001F, 0.0D);
		float f = (float)entityIn.getSize();
		float f1 = MathHelper.lerp(partialTickTime, entityIn.oSquish, entityIn.squish) / (f * 0.5F + 1.0F);
		float f2 = 1.0F / (f1 + 1.0F);
		matrixStackIn.scale(f2 * f, 1.0F / f2 * f, f2 * f);
	}

	@Override
	public ResourceLocation getTextureLocation(MagicalSlimeEntity entityIn)
	{
		return TEX;
	}
}