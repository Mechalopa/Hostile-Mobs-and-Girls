package hmag.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import hmag.HMaG;
import hmag.entity.projectile.NemesisBulletEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NemesisBulletRenderer extends EntityRenderer<NemesisBulletEntity>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/nemesis_bullet.png");

	public NemesisBulletRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	@Override
	protected int getBlockLightLevel(NemesisBulletEntity entityIn, BlockPos pos)
	{
		return 15;
	}

	@Override
	public void render(NemesisBulletEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		matrixStackIn.pushPose();
		matrixStackIn.scale(2.0F, 2.0F, 2.0F);
		matrixStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
		float f = (float)entityIn.tickCount + partialTicks;
		float f1 = 0.5F + MathHelper.sin(f * 0.8F) * 0.03F;
		matrixStackIn.scale(f1, f1, f1);
		MatrixStack.Entry matrixstack$entry = matrixStackIn.last();
		Matrix4f matrix4f = matrixstack$entry.pose();
		Matrix3f matrix3f = matrixstack$entry.normal();
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entityIn)));
		this.drawVertex(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 0, 0, 1);
		this.drawVertex(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 0, 1, 1);
		this.drawVertex(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 1, 1, 0);
		this.drawVertex(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 1, 0, 0);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	private void drawVertex(IVertexBuilder vertexBuilder, Matrix4f matrix, Matrix3f normals, int packedLightIn, float f, int i, int j, int k)
	{
		vertexBuilder.vertex(matrix, f - 0.5F, (float)i - 0.25F, 0.0F).color(255, 255, 255, 255).uv((float)j, (float)k).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(normals, 0.0F, 1.0F, 0.0F).endVertex();
	}

	@Override
	public ResourceLocation getTextureLocation(NemesisBulletEntity entityIn)
	{
		return TEX;
	}
}