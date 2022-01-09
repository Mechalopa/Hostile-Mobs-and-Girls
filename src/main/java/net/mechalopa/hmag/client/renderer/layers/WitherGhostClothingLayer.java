package net.mechalopa.hmag.client.renderer.layers;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.model.GhostModel;
import net.mechalopa.hmag.entity.GhostEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherGhostClothingLayer extends GhostClothingLayer
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin_2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin_3.png");
	private static final ResourceLocation TEX4 = new ResourceLocation(HMaG.MODID, "textures/entity/wither_ghost_skin_4.png");

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