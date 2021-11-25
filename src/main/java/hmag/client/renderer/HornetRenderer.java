package hmag.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import hmag.HMaG;
import hmag.client.model.HornetModel;
import hmag.entity.HornetEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HornetRenderer extends AbstractGirlRenderer<HornetEntity, HornetModel<HornetEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/hornet.png");

	public HornetRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new HornetModel<>(), 0.375F);
	}

	@Override
	protected void scale(HornetEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(0.875F, 0.875F, 0.875F);
		super.scale(entityIn, matrixStackIn, partialTickTime);
	}

	@Override
	protected void setupRotations(HornetEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-6.0F));
	}

	@Override
	public ResourceLocation getTextureLocation(HornetEntity entityIn)
	{
		return TEX;
	}
}