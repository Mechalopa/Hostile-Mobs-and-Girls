package hmag.client.renderer;

import hmag.HMaG;
import hmag.client.model.KashaModel;
import hmag.entity.KashaEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KashaRenderer extends MobRenderer<KashaEntity, KashaModel<KashaEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/kasha.png");

	public KashaRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new KashaModel<>(), 0.4F);
	}

	@Override
	public ResourceLocation getTextureLocation(KashaEntity entityIn)
	{
		return TEX;
	}
}