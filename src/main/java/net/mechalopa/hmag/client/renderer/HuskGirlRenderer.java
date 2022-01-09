package net.mechalopa.hmag.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.mechalopa.hmag.HMaG;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HuskGirlRenderer extends ZombieGirlRenderer
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/husk_girl.png");

	public HuskGirlRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	@Override
	protected void scale(ZombieEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(1.0625F, 1.0625F, 1.0625F);
		super.scale(entityIn, matrixStackIn, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieEntity entityIn)
	{
		return TEX;
	}
}