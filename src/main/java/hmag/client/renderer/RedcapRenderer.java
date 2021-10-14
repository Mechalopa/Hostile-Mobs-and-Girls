package hmag.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import hmag.HMaG;
import hmag.client.model.RedcapModel;
import hmag.entity.RedcapEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RedcapRenderer extends AbstractGirlRenderer<RedcapEntity, RedcapModel<RedcapEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/redcap.png");

	public RedcapRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new RedcapModel<>(), 0.5F);
		this.addLayer(new BipedArmorLayer<>(this, new RedcapModel<>(0.5F, true), new RedcapModel<>(1.0F, true)));
	}

	@Override
	protected void scale(RedcapEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(0.875F, 0.875F, 0.875F);
		super.scale(entityIn, matrixStackIn, partialTickTime);
	}

	@Override
	public ResourceLocation getTextureLocation(RedcapEntity entityIn)
	{
		return TEX;
	}
}