package hmag.client.renderer.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import hmag.HMaG;
import hmag.client.model.DodomekiModel;
import hmag.entity.DodomekiEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DodomekiEyesLayer<T extends DodomekiEntity, M extends DodomekiModel<T>> extends AbstractEyesLayer<T, M>
{
	private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation(HMaG.MODID, "textures/entity/dodomeki_eyes.png"));

	public DodomekiEyesLayer(IEntityRenderer<T, M> rendererIn)
	{
		super(rendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.renderType());
		final float f = MathHelper.clamp(livingEntityIn.getEyesGlowingAnimationScale(partialTicks), 0.0F, 1.0F);
		this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, f, f, f, 1.0F);
	}

	public RenderType renderType()
	{
		return RENDER_TYPE;
	}
}