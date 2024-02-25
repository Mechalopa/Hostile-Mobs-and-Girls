package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.JackFrostModel;
import com.github.mechalopa.hmag.world.entity.JackFrostEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JackFrostRenderer extends AbstractGirlRenderer<JackFrostEntity, JackFrostModel<JackFrostEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/jack_frost.png");

	public  JackFrostRenderer(EntityRendererProvider.Context context)
	{
		super(context, new JackFrostModel<>(context.bakeLayer(ModModelLayers.JACK_FROST)), 0.5F, -1);
	}

	@Override
	public ResourceLocation getTextureLocation(JackFrostEntity entity)
	{
		return TEXTURE;
	}
}