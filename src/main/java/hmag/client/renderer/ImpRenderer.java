package hmag.client.renderer;

import hmag.HMaG;
import hmag.client.model.ImpModel;
import hmag.entity.ImpEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImpRenderer extends AbstractGirlRenderer<ImpEntity, ImpModel<ImpEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/imp.png");

	public ImpRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ImpModel<>(), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(ImpEntity entityIn)
	{
		return TEX;
	}
}