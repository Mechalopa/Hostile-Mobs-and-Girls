package net.mechalopa.hmag.client.renderer;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.model.JackFrostModel;
import net.mechalopa.hmag.entity.JackFrostEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JackFrostRenderer extends AbstractGirlRenderer<JackFrostEntity, JackFrostModel<JackFrostEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/jack_frost.png");

	public  JackFrostRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new JackFrostModel<>(), 0.5F, -1);
	}

	@Override
	public ResourceLocation getTextureLocation(JackFrostEntity entityIn)
	{
		return TEX;
	}
}