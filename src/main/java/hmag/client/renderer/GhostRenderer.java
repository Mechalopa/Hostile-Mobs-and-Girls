package hmag.client.renderer;

import hmag.HMaG;
import hmag.client.model.GhostModel;
import hmag.client.renderer.layers.GhostClothingLayer;
import hmag.entity.GhostEntity;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhostRenderer extends BipedRenderer<GhostEntity, GhostModel<GhostEntity>>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost3.png");
	private static final ResourceLocation TEX4 = new ResourceLocation(HMaG.MODID, "textures/entity/ghost4.png");

	public GhostRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new GhostModel<>(), 0.375F);
		this.addLayer(new BipedArmorLayer<>(this, new GhostModel<>(0.5F, true), new GhostModel<>(1.0F, true)));
		this.addLayer(this.getLayer());
	}

	@Override
	protected int getBlockLightLevel(GhostEntity entityIn, BlockPos pos)
	{
		return 15;
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

	public GhostClothingLayer getLayer()
	{
		return new GhostClothingLayer(this);
	}
}