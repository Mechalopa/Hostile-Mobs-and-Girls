package hmag.client.renderer;

import hmag.HMaG;
import hmag.client.model.NecroticReaperModel;
import hmag.client.renderer.layers.NecroticReaperEyesLayer;
import hmag.entity.NecroticReaperEntity;
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