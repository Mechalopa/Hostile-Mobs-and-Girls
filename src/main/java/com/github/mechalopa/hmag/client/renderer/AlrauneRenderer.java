package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.AlrauneModel;
import com.github.mechalopa.hmag.world.entity.AlrauneEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlrauneRenderer extends AbstractGirlRenderer<AlrauneEntity, AlrauneModel<AlrauneEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/alraune.png");

	public AlrauneRenderer(EntityRendererProvider.Context context)
	{
		super(context, new AlrauneModel<>(context.bakeLayer(ModModelLayers.ALRAUNE)), 0.5F, -1);
	}

	@Override
	public ResourceLocation getTextureLocation(AlrauneEntity entity)
	{
		return TEXTURE;
	}
}