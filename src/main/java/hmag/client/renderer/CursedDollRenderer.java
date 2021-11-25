package hmag.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import hmag.HMaG;
import hmag.client.model.CursedDollModel;
import hmag.entity.CursedDollEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CursedDollRenderer extends AbstractGirlRenderer<CursedDollEntity, CursedDollModel<CursedDollEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/cursed_doll.png");

	public CursedDollRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new CursedDollModel<>(), 0.375F);
	}

	@Override
	protected void scale(CursedDollEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(0.875F, 0.875F, 0.875F);
		super.scale(entityIn, matrixStackIn, partialTickTime);
		float f = (float)entityIn.tickCount + partialTickTime;
		matrixStackIn.translate(0.0F, -0.12F + MathHelper.sin(f * 0.06F) * 0.08F, 0.0F);
	}

	@Override
	protected void setupRotations(CursedDollEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-10.0F));
	}

	@Override
	public ResourceLocation getTextureLocation(CursedDollEntity entityIn)
	{
		return TEX;
	}
}