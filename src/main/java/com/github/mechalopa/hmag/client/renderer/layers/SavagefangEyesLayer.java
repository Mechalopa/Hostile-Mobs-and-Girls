package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.model.SavagefangModel;
import com.github.mechalopa.hmag.world.entity.SavagefangEntity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SavagefangEyesLayer<T extends SavagefangEntity, M extends SavagefangModel<T>> extends EyesLayer<T, M>
{
	private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation(HMaG.MODID, "textures/entity/savagefang_eyes.png"));

	public SavagefangEyesLayer(RenderLayerParent<T, M> renderLayerParent)
	{
		super(renderLayerParent);
	}

	@Override
	public RenderType renderType()
	{
		return RENDER_TYPE;
	}
}