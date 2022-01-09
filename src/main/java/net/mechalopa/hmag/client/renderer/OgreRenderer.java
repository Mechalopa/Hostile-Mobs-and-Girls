package net.mechalopa.hmag.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.model.OgreModel;
import net.mechalopa.hmag.entity.OgreEntity;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OgreRenderer extends BipedRenderer<OgreEntity, OgreModel<OgreEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/ogre.png");

	public OgreRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new OgreModel<>(), 0.7F);
	}

	@Override
	protected void scale(OgreEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(1.2F, 1.2F, 1.2F);
		super.scale(entityIn, matrixStackIn, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(OgreEntity entityIn)
	{
		return TEX;
	}
}