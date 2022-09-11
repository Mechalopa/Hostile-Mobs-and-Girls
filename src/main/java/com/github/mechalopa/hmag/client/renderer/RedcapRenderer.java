package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.AbstractGirlModel;
import com.github.mechalopa.hmag.client.model.GirlArmorModel;
import com.github.mechalopa.hmag.client.model.RedcapModel;
import com.github.mechalopa.hmag.world.entity.RedcapEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RedcapRenderer extends AbstractGirlRenderer<RedcapEntity, RedcapModel<RedcapEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/redcap.png");

	public RedcapRenderer(EntityRendererProvider.Context context)
	{
		this(context, new RedcapModel<>(context.bakeLayer(ModModelLayers.REDCAP)), new GirlArmorModel<>(context.bakeLayer(ModModelLayers.REDCAP_INNER_ARMOR)), new GirlArmorModel<>(context.bakeLayer(ModModelLayers.REDCAP_OUTER_ARMOR)));
	}

	public RedcapRenderer(EntityRendererProvider.Context context, RedcapModel<RedcapEntity> model, AbstractGirlModel<RedcapEntity> model1, AbstractGirlModel<RedcapEntity> model2)
	{
		super(context, model, 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, model1, model2));
	}

	@Override
	protected void scale(RedcapEntity entity, PoseStack poseStack, float partialTickTime)
	{
		poseStack.scale(0.875F, 0.875F, 0.875F);
		super.scale(entity, poseStack, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(RedcapEntity entityIn)
	{
		return TEX;
	}
}