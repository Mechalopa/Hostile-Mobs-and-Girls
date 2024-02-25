package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.FortressKeeperModel;
import com.github.mechalopa.hmag.client.renderer.layers.FortressKeeperFireLayer;
import com.github.mechalopa.hmag.world.entity.FortressKeeperEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FortressKeeperRenderer extends MobRenderer<FortressKeeperEntity, FortressKeeperModel<FortressKeeperEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(HMaG.MODID, "textures/entity/fortress_keeper.png");

	public FortressKeeperRenderer(EntityRendererProvider.Context context)
	{
		super(context, new FortressKeeperModel<>(context.bakeLayer(ModModelLayers.FORTRESS_KEEPER)), 0.7F);
		this.addLayer(new FortressKeeperFireLayer(this));
	}

	@Override
	protected int getBlockLightLevel(FortressKeeperEntity entity, BlockPos pos)
	{
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(FortressKeeperEntity entity)
	{
		return TEXTURE;
	}
}