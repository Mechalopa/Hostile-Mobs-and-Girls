package net.mechalopa.hmag.client.renderer;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.model.DodomekiModel;
import net.mechalopa.hmag.client.renderer.layers.DodomekiEyesLayer;
import net.mechalopa.hmag.entity.DodomekiEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DodomekiRenderer extends AbstractGirlRenderer<DodomekiEntity, DodomekiModel<DodomekiEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/dodomeki.png");

	public DodomekiRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new DodomekiModel<>(), 0.5F);
		this.addLayer(new DodomekiEyesLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(DodomekiEntity entityIn)
	{
		return TEX;
	}
}