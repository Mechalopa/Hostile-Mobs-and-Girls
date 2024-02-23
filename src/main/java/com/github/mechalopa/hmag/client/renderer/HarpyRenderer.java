package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.HarpyModel;
import com.github.mechalopa.hmag.world.entity.HarpyEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HarpyRenderer extends AbstractGirlRenderer<HarpyEntity, HarpyModel<HarpyEntity>>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_3.png");
	private static final ResourceLocation TEX4 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_4.png");
	private static final ResourceLocation TEX5 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_5.png");
	private static final ResourceLocation TEX6 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy/harpy_6.png");

	public HarpyRenderer(EntityRendererProvider.Context context)
	{
		super(context, new HarpyModel<>(context.bakeLayer(ModModelLayers.HARPY)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(HarpyEntity entity)
	{
		switch (entity.getVariant())
		{
		case 1:
			return TEX1;
		case 2:
			return TEX2;
		case 3:
			return TEX3;
		case 4:
			return TEX4;
		case 5:
			return TEX5;
		case 6:
			return TEX6;
		default:
			return TEX0;
		}
	}
}