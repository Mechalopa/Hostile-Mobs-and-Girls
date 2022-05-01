package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.HarpyModel;
import com.github.mechalopa.hmag.entity.HarpyEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HarpyRenderer extends AbstractGirlRenderer<HarpyEntity, HarpyModel<HarpyEntity>>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_3.png");
	private static final ResourceLocation TEX4 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_4.png");
	private static final ResourceLocation TEX5 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_5.png");
	private static final ResourceLocation TEX6 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_6.png");
	private static final ResourceLocation TEX7 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_7.png");

	public HarpyRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new HarpyModel<>(), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(HarpyEntity entityIn)
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
		case 5:
			return TEX5;
		case 6:
			return TEX6;
		case 7:
			return TEX7;
		default:
			return TEX0;
		}
	}
}