package hmag.client.renderer;

import hmag.HMaG;
import hmag.client.model.CatoblepasModel;
import hmag.entity.CatoblepasEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CatoblepasRenderer extends MobRenderer<CatoblepasEntity, CatoblepasModel<CatoblepasEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/catoblepas.png");

	public CatoblepasRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new CatoblepasModel<>(), 0.7F);
	}

	@Override
	public ResourceLocation getTextureLocation(CatoblepasEntity entityIn)
	{
		return TEX;
	}
}