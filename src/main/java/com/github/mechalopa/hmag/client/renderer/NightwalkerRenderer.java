package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.NightwalkerModel;
import com.github.mechalopa.hmag.client.renderer.layers.NightwalkerEyesLayer;
import com.github.mechalopa.hmag.world.entity.NightwalkerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NightwalkerRenderer extends MobRenderer<NightwalkerEntity, NightwalkerModel<NightwalkerEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/nightwalker.png");

	public NightwalkerRenderer(EntityRendererProvider.Context context)
	{
		super(context, new NightwalkerModel<>(context.bakeLayer(ModModelLayers.NIGHTWALKER)), 0.8F);
		this.addLayer(new NightwalkerEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(NightwalkerEntity entity)
	{
		return TEX;
	}
}