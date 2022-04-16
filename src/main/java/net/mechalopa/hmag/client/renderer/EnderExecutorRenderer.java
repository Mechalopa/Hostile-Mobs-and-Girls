package net.mechalopa.hmag.client.renderer;

import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.model.EnderExecutorModel;
import net.mechalopa.hmag.client.renderer.layers.EnderExecutorEyesLayer;
import net.mechalopa.hmag.client.renderer.layers.EnderExecutorHeldBlockLayer;
import net.mechalopa.hmag.client.renderer.layers.HeldItemLayer2;
import net.mechalopa.hmag.client.util.ModClientUtils;
import net.mechalopa.hmag.entity.EnderExecutorEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderExecutorRenderer extends MobRenderer<EnderExecutorEntity, EnderExecutorModel<EnderExecutorEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/ender_executor.png");
	private static final ResourceLocation BEAM_TEX = new ResourceLocation(HMaG.MODID, "textures/entity/ender_executor_beam.png");
	private static final RenderType BEAM_RENDER_TYPE = RenderType.entityCutoutNoCull(BEAM_TEX);
	private final Random rand = new Random();

	public EnderExecutorRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new EnderExecutorModel<>(), 0.5F);
		this.addLayer(new EnderExecutorEyesLayer<>(this));
		this.addLayer(new HeldItemLayer2<>(this, -1));
		this.addLayer(new EnderExecutorHeldBlockLayer(this));
	}

	@Override
	public boolean shouldRender(EnderExecutorEntity livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ)
	{
		if (super.shouldRender(livingEntityIn, camera, camX, camY, camZ))
		{
			return true;
		}
		else
		{
			return ModClientUtils.shouldRenderBeamAttackMob(livingEntityIn, camera, camX, camY, camZ, livingEntityIn);
		}
	}

	@Override
	public void render(EnderExecutorEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		BlockState blockstate = entityIn.getCarriedBlock();
		EnderExecutorModel<EnderExecutorEntity> endermanmodel = this.getModel();
		endermanmodel.isCarrying = blockstate != null;
		endermanmodel.isAttacking = entityIn.isCreepy();
		LivingEntity target = entityIn.getActiveAttackTarget();
		endermanmodel.isBeamAttacking = entityIn.hasActiveAttackTarget() && target != null;

		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

		if (entityIn.isAlive() && target != null)
		{
			float f = entityIn.getAttackAnimationScale(partialTicks);
			float f1 = (float)(entityIn.level.getGameTime() % 24000L) + partialTicks;
			float f2 = f1 * 0.5F % 1.0F;
			float f3 = entityIn.getEyeHeight();
			matrixStackIn.pushPose();
			matrixStackIn.translate(0.0D, (double)f3 + (double)entityIn.getBbHeight() * 0.05D, 0.0D);
			Vector3d vector3d = ModClientUtils.getPosition(target, (double)target.getBbHeight() * 0.5D, partialTicks);
			Vector3d vector3d1 = ModClientUtils.getPosition(entityIn, (double)f3, partialTicks);
			Vector3d vector3d2 = vector3d.subtract(vector3d1);
			float f4 = (float)(vector3d2.length() + 1.0D);
			vector3d2 = vector3d2.normalize();
			float f5 = (float)Math.acos(vector3d2.y);
			float f6 = (float)Math.atan2(vector3d2.z, vector3d2.x);
			matrixStackIn.mulPose(Vector3f.YP.rotationDegrees((((float)Math.PI / 2.0F) - f6) * (180.0F / (float)Math.PI)));
			matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(f5 * (180.0F / (float)Math.PI)));
			float f7 = 0.0F;
			float f8 = f * f;
			int i = 200 + (int)(f8 * 4.0F);
			int j = 235 - (int)(f8 * 141.0F);
			int k = 235 + (int)(f8 * 17.0F);
			float f11 = MathHelper.cos(f7 + 2.3561945F) * 0.282F;
			float f12 = MathHelper.sin(f7 + 2.3561945F) * 0.282F;
			float f13 = MathHelper.cos(f7 + ((float)Math.PI / 4.0F)) * 0.282F;
			float f14 = MathHelper.sin(f7 + ((float)Math.PI / 4.0F)) * 0.282F;
			float f15 = MathHelper.cos(f7 + 3.926991F) * 0.282F;
			float f16 = MathHelper.sin(f7 + 3.926991F) * 0.282F;
			float f17 = MathHelper.cos(f7 + 5.4977875F) * 0.282F;
			float f18 = MathHelper.sin(f7 + 5.4977875F) * 0.282F;
			float f19 = MathHelper.cos(f7 + (float)Math.PI) * 0.2F;
			float f20 = MathHelper.sin(f7 + (float)Math.PI) * 0.2F;
			float f21 = MathHelper.cos(f7 + 0.0F) * 0.2F;
			float f22 = MathHelper.sin(f7 + 0.0F) * 0.2F;
			float f29 = -1.0F + f2;
			float f30 = f4 * 2.5F + f29;
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(BEAM_RENDER_TYPE);
			MatrixStack.Entry matrixstack$entry = matrixStackIn.last();
			Matrix4f matrix4f = matrixstack$entry.pose();
			Matrix3f matrix3f = matrixstack$entry.normal();
			ModClientUtils.drawVertex(ivertexbuilder, matrix4f, matrix3f, f19, f4, f20, i, j, k, 0.5F, f30);
			ModClientUtils.drawVertex(ivertexbuilder, matrix4f, matrix3f, f19, 0.0F, f20, i, j, k, 0.5F, f29);
			ModClientUtils.drawVertex(ivertexbuilder, matrix4f, matrix3f, f21, 0.0F, f22, i, j, k, 0.0F, f29);
			ModClientUtils.drawVertex(ivertexbuilder, matrix4f, matrix3f, f21, f4, f22, i, j, k, 0.0F, f30);
			float f31 = entityIn.tickCount % 2 == 0 ? 0.5F : 0.0F;
			ModClientUtils.drawVertex(ivertexbuilder, matrix4f, matrix3f, f11, f4, f12, i, j, k, 0.5F, f31 + 0.5F);
			ModClientUtils.drawVertex(ivertexbuilder, matrix4f, matrix3f, f13, f4, f14, i, j, k, 1.0F, f31 + 0.5F);
			ModClientUtils.drawVertex(ivertexbuilder, matrix4f, matrix3f, f17, f4, f18, i, j, k, 1.0F, f31);
			ModClientUtils.drawVertex(ivertexbuilder, matrix4f, matrix3f, f15, f4, f16, i, j, k, 0.5F, f31);
			matrixStackIn.popPose();
		}
	}

	@Override
	public Vector3d getRenderOffset(EnderExecutorEntity entityIn, float partialTicks)
	{
		if (entityIn.isCreepy())
		{
			return new Vector3d(this.rand.nextGaussian() * 0.02D, 0.0D, this.rand.nextGaussian() * 0.02D);
		}
		else
		{
			return super.getRenderOffset(entityIn, partialTicks);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(EnderExecutorEntity entityIn)
	{
		return TEX;
	}
}