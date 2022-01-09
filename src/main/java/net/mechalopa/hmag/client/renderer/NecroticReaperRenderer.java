package net.mechalopa.hmag.client.renderer;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.model.NecroticReaperModel;
import net.mechalopa.hmag.client.renderer.layers.NecroticReaperEyesLayer;
import net.mechalopa.hmag.entity.NecroticReaperEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NecroticReaperRenderer extends AbstractGirlRenderer<NecroticReaperEntity, NecroticReaperModel<NecroticReaperEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/necrotic_reaper.png");

	public  NecroticReaperRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new NecroticReaperModel<>(), 0.5F);
		this.addLayer(new NecroticReaperEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(NecroticReaperEntity entityIn)
	{
		return TEX;
	}
}