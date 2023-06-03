package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.AbstractGirlModel;
import com.github.mechalopa.hmag.client.model.CreeperGirlArmorModel;
import com.github.mechalopa.hmag.client.model.CreeperGirlModel;
import com.github.mechalopa.hmag.client.renderer.layers.CreeperGirlPowerLayer;
import com.github.mechalopa.hmag.world.entity.CreeperGirlEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlRenderer extends AbstractGirlRenderer<CreeperGirlEntity, AbstractGirlModel<CreeperGirlEntity>>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/creeper_girl/creeper_girl_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/creeper_girl/creeper_girl_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/creeper_girl/creeper_girl_2.png");

	public CreeperGirlRenderer(EntityRendererProvider.Context context)
	{
		this(context, new CreeperGirlModel<>(context.bakeLayer(ModModelLayers.CREEPER_GIRL)), new CreeperGirlArmorModel<>(context.bakeLayer(ModModelLayers.CREEPER_GIRL_INNER_ARMOR)), new CreeperGirlArmorModel<>(context.bakeLayer(ModModelLayers.CREEPER_GIRL_OUTER_ARMOR)));
	}

	public CreeperGirlRenderer(EntityRendererProvider.Context context, CreeperGirlModel<CreeperGirlEntity> model, AbstractGirlModel<CreeperGirlEntity> model1, AbstractGirlModel<CreeperGirlEntity> model2)
	{
		super(context, model, 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, model1, model2, context.getModelManager()));
		this.addLayer(new CreeperGirlPowerLayer(this, context.getModelSet()));
	}

	@Override
	protected void scale(CreeperGirlEntity entity, PoseStack poseStack, float partialTickTime)
	{
		float f = entity.getSwelling(partialTickTime);
		float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f = f * f;
		f = f * f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		poseStack.scale(f2, f3, f2);
	}

	@Override
	protected float getWhiteOverlayProgress(CreeperGirlEntity entity, float partialTicks)
	{
		float f = entity.getSwelling(partialTicks);
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(CreeperGirlEntity entity)
	{
		switch (entity.getVariant())
		{
		case 1:
			return TEX1;
		case 2:
			return TEX2;
		default:
			return TEX0;
		}
	}
}