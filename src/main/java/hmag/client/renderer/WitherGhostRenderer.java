package hmag.client.renderer;

import hmag.HMaG;
import hmag.client.renderer.layers.GhostClothingLayer;
import hmag.client.renderer.layers.WitherGhostClothingLayer;
import hmag.entity.GhostEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherGhostRenderer extends GhostRenderer
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost3.png");
	private static final ResourceLocation TEX4 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost4.png");

	public WitherGhostRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	@Override
	public ResourceLocation getTextureLocation(GhostEntity entityIn)
	{
		switch (entityIn.getVariant())
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

	@Override
	public GhostClothingLayer getLayer()
	{
		return new WitherGhostClothingLayer(this);
	}
}