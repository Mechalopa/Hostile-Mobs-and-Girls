package hmag.client.renderer.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import hmag.client.model.MagicalSlimeModel;
import hmag.entity.MagicalSlimeEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagicalSlimeGelLayer<T extends LivingEntity> extends LayerRenderer<T, MagicalSlimeModel<T>>
{
	private final EntityModel<T> slimeModel = new MagicalSlimeModel<>(0);

	public MagicalSlimeGelLayer(IEntityRenderer<T, MagicalSlimeModel<T>> entityRendererIn)
	{
		super(entityRendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (!livingEntityIn.isInvisible())
		{
			this.getParentModel().copyPropertiesTo(this.slimeModel);
			this.slimeModel.prepareMobModel(livingEntityIn, limbSwing, limbSwingAmount, partialTicks);
			this.slimeModel.setupAnim(livingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(livingEntityIn)));

			if (livingEntityIn instanceof MagicalSlimeEntity)
			{
				float[] afloat = ((MagicalSlimeEntity)livingEntityIn).getColor();
				this.slimeModel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getOverlayCoords(livingEntityIn, 0.0F), afloat[0], afloat[1], afloat[2], 1.0F);
			}
			else
			{
				this.slimeModel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getOverlayCoords(livingEntityIn, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
			}
		}
	}
}