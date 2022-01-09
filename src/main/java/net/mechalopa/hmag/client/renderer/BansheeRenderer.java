package net.mechalopa.hmag.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.model.BansheeModel;
import net.mechalopa.hmag.client.renderer.layers.BansheeLayer;
import net.mechalopa.hmag.entity.BansheeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BansheeRenderer extends AbstractGirlRenderer<BansheeEntity, BansheeModel<BansheeEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/banshee.png");

	public BansheeRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new BansheeModel<>(), 0.375F);
		this.addLayer(new BansheeLayer(this));
	}

	@Override
	protected void scale(BansheeEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		super.scale(entityIn, matrixStackIn, partialTickTime);
		float f = (float)entityIn.tickCount + partialTickTime;
		matrixStackIn.translate(0.0F, -0.12F + MathHelper.sin(f * 0.03F) * 0.06F, 0.0F);
	}

	@Override
	protected void setupRotations(BansheeEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-10.0F));
	}

	@Override
	protected int getBlockLightLevel(BansheeEntity entityIn, BlockPos pos)
	{
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(BansheeEntity entityIn)
	{
		return TEX;
	}
}