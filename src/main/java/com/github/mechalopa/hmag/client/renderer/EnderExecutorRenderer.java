package com.github.mechalopa.hmag.client.renderer;

import java.util.Random;

import org.joml.Matrix3f;
import org.joml.Matrix4f;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.EnderExecutorModel;
import com.github.mechalopa.hmag.client.renderer.layers.EnderExecutorCarriedBlockLayer;
import com.github.mechalopa.hmag.client.renderer.layers.EnderExecutorEyesLayer;
import com.github.mechalopa.hmag.client.renderer.layers.ItemInHandLayer2;
import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.EnderExecutorEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderExecutorRenderer extends MobRenderer<EnderExecutorEntity, EnderExecutorModel<EnderExecutorEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/ender_executor/ender_executor.png");
	private static final ResourceLocation BEAM_TEX = new ResourceLocation(HMaG.MODID, "textures/entity/ender_executor/ender_executor_beam.png");
	private static final RenderType BEAM_RENDER_TYPE = RenderType.entityCutoutNoCull(BEAM_TEX);
	private final Random rand = new Random();

	public EnderExecutorRenderer(EntityRendererProvider.Context context)
	{
		super(context, new EnderExecutorModel<>(context.bakeLayer(ModModelLayers.ENDER_EXECUTOR)), 0.5F);
		this.addLayer(new EnderExecutorEyesLayer<>(this));
		this.addLayer(new ItemInHandLayer2<>(this, context.getItemInHandRenderer(), -1));
		this.addLayer(new EnderExecutorCarriedBlockLayer(this));
	}

	@Override
	public boolean shouldRender(EnderExecutorEntity entity, Frustum camera, double camX, double camY, double camZ)
	{
		if (super.shouldRender(entity, camera, camX, camY, camZ))
		{
			return true;
		}
		else
		{
			return ModClientUtils.shouldRenderBeamAttackMob(entity, camera, camX, camY, camZ, entity);
		}
	}

	@Override
	public void render(EnderExecutorEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight)
	{
		BlockState blockstate = entity.getCarriedBlock();
		EnderExecutorModel<EnderExecutorEntity> endermanmodel = this.getModel();
		endermanmodel.carrying = blockstate != null;
		endermanmodel.creepy = entity.isCreepy();
		LivingEntity target = entity.getActiveAttackTarget();
		endermanmodel.beamAttacking = entity.hasActiveAttackTarget() && target != null;

		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);

		if (entity.isAlive() && target != null)
		{
			float f = entity.getAttackAnimationScale(partialTicks);
			float f1 = (float)(entity.level.getGameTime() % 24000L) + partialTicks;
			float f2 = f1 * 0.5F % 1.0F;
			float f3 = entity.getEyeHeight();
			poseStack.pushPose();
			poseStack.translate(0.0D, (double)f3 + (double)entity.getBbHeight() * 0.05D, 0.0D);
			Vec3 vec3 = ModClientUtils.getPosition(target, (double)target.getBbHeight() * 0.5D, partialTicks);
			Vec3 vec31 = ModClientUtils.getPosition(entity, (double)f3, partialTicks);
			Vec3 vec32 = vec3.subtract(vec31);
			float f4 = (float)(vec32.length() + 1.0D);
			vec32 = vec32.normalize();
			float f5 = (float)Math.acos(vec32.y);
			float f6 = (float)Math.atan2(vec32.z, vec32.x);
			poseStack.mulPose(Axis.YP.rotationDegrees((((float)Math.PI / 2.0F) - f6) * (180.0F / (float)Math.PI)));
			poseStack.mulPose(Axis.XP.rotationDegrees(f5 * (180.0F / (float)Math.PI)));
			float f7 = 0.0F;
			float f8 = f * f;
			int i = 200 + (int)(f8 * 4.0F);
			int j = 235 - (int)(f8 * 141.0F);
			int k = 235 + (int)(f8 * 17.0F);
			float f11 = Mth.cos(f7 + 2.3561945F) * 0.282F;
			float f12 = Mth.sin(f7 + 2.3561945F) * 0.282F;
			float f13 = Mth.cos(f7 + ((float)Math.PI / 4.0F)) * 0.282F;
			float f14 = Mth.sin(f7 + ((float)Math.PI / 4.0F)) * 0.282F;
			float f15 = Mth.cos(f7 + 3.926991F) * 0.282F;
			float f16 = Mth.sin(f7 + 3.926991F) * 0.282F;
			float f17 = Mth.cos(f7 + 5.4977875F) * 0.282F;
			float f18 = Mth.sin(f7 + 5.4977875F) * 0.282F;
			float f19 = Mth.cos(f7 + (float)Math.PI) * 0.2F;
			float f20 = Mth.sin(f7 + (float)Math.PI) * 0.2F;
			float f21 = Mth.cos(f7 + 0.0F) * 0.2F;
			float f22 = Mth.sin(f7 + 0.0F) * 0.2F;
			float f29 = -1.0F + f2;
			float f30 = f4 * 2.5F + f29;
			VertexConsumer vertexconsumer = buffer.getBuffer(BEAM_RENDER_TYPE);
			PoseStack.Pose posestack$pose = poseStack.last();
			Matrix4f matrix4f = posestack$pose.pose();
			Matrix3f matrix3f = posestack$pose.normal();
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f19, f4, f20, i, j, k, 0.5F, f30);
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f19, 0.0F, f20, i, j, k, 0.5F, f29);
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f21, 0.0F, f22, i, j, k, 0.0F, f29);
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f21, f4, f22, i, j, k, 0.0F, f30);
			float f31 = entity.tickCount % 2 == 0 ? 0.5F : 0.0F;
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f11, f4, f12, i, j, k, 0.5F, f31 + 0.5F);
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f13, f4, f14, i, j, k, 1.0F, f31 + 0.5F);
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f17, f4, f18, i, j, k, 1.0F, f31);
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f15, f4, f16, i, j, k, 0.5F, f31);
			poseStack.popPose();
		}
	}

	@Override
	public Vec3 getRenderOffset(EnderExecutorEntity entity, float partialTicks)
	{
		if (entity.isCreepy())
		{
			return new Vec3(this.rand.nextGaussian() * 0.02D, 0.0D, this.rand.nextGaussian() * 0.02D);
		}
		else
		{
			return super.getRenderOffset(entity, partialTicks);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(EnderExecutorEntity entity)
	{
		return TEX;
	}
}