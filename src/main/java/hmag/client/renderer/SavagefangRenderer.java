package hmag.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import hmag.HMaG;
import hmag.client.model.SavagefangModel;
import hmag.client.renderer.layers.SavagefangEyesLayer;
import hmag.entity.SavagefangEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SavagefangRenderer extends MobRenderer<SavagefangEntity, SavagefangModel<SavagefangEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/savagefang.png");

	public SavagefangRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new SavagefangModel<>(), 0.4F);
		this.addLayer(new SavagefangEyesLayer<>(this));
	}

	@Override
	protected void scale(SavagefangEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(1.25F, 1.25F, 1.25F);
		super.scale(entityIn, matrixStackIn, partialTickTime);
	}

	@Override
	protected void setupRotations(SavagefangEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);

		float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));

		if (entityLiving.isLaunched())
		{
			matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
			matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		}
		else
		{
			matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(entityLiving.getXRotAnimationScale(partialTicks) * 180.0F));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(SavagefangEntity entityIn)
	{
		return TEX;
	}
}