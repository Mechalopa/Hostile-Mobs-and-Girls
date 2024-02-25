package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.NecroticReaperModel;
import com.github.mechalopa.hmag.client.renderer.layers.NecroticReaperEyesLayer;
import com.github.mechalopa.hmag.world.entity.NecroticReaperEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NecroticReaperRenderer extends AbstractGirlRenderer<NecroticReaperEntity, NecroticReaperModel<NecroticReaperEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/necrotic_reaper.png");

	public  NecroticReaperRenderer(EntityRendererProvider.Context context)
	{
		super(context, new NecroticReaperModel<>(context.bakeLayer(ModModelLayers.NECROTIC_REAPER)), 0.5F);
		this.addLayer(new NecroticReaperEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(NecroticReaperEntity entity)
	{
		return TEXTURE;
	}
}