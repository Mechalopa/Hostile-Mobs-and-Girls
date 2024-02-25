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
	private static final ResourceLocation TEXTURE_0 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_0.png");
	private static final ResourceLocation TEXTURE_1 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_1.png");
	private static final ResourceLocation TEXTURE_2 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_2.png");
	private static final ResourceLocation TEXTURE_3 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_3.png");
	private static final ResourceLocation TEXTURE_4 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_4.png");
	private static final ResourceLocation TEXTURE_5 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_5.png");
	private static final ResourceLocation TEXTURE_6 = new ResourceLocation(HMaG.MODID, "textures/entity/harpy_6.png");

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
			return TEXTURE_1;
		case 2:
			return TEXTURE_2;
		case 3:
			return TEXTURE_3;
		case 4:
			return TEXTURE_4;
		case 5:
			return TEXTURE_5;
		case 6:
			return TEXTURE_6;
		default:
			return TEXTURE_0;
		}
	}
}