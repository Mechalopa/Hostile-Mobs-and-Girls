package hmag.client.renderer.layers;

import hmag.HMaG;
import hmag.client.model.GhostModel;
import hmag.entity.GhostEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherGhostClothingLayer extends GhostClothingLayer
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin3.png");
	private static final ResourceLocation TEX4 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin4.png");

	public WitherGhostClothingLayer(IEntityRenderer<GhostEntity, GhostModel<GhostEntity>> entityRendererIn)
	{
		super(entityRendererIn);
	}

	@Override
	protected float getAlpha(GhostEntity livingEntityIn)
	{
		return 0.875F;
	}

	@Override
	public ResourceLocation getLayerTexture(GhostEntity livingEntityIn)
	{
		switch (livingEntityIn.getVariant())
		{
		case 1:
			return TEX1;
		case 2:
			return TEX2;
		case 3:
			return TEX3;
		case 4:
			return TEX4;
		default:
			return TEX0;
		}
	}
}