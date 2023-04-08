package com.github.mechalopa.hmag.client.renderer;

import java.util.Random;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.MonolithModel;
import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.MonolithEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonolithRenderer extends MobRenderer<MonolithEntity, MonolithModel<MonolithEntity>>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/monolith/monolith_0.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/monolith/monolith_1.png");
	private static final ResourceLocation TEX2 = new ResourceLocation(HMaG.MODID, "textures/entity/monolith/monolith_2.png");
	private static final ResourceLocation TEX3 = new ResourceLocation(HMaG.MODID, "textures/entity/monolith/monolith_3.png");
	private static final ResourceLocation TEX4 = new ResourceLocation(HMaG.MODID, "textures/entity/monolith/monolith_4.png");
	private static final ResourceLocation BEAM_TEX = new ResourceLocation("textures/entity/guardian_beam.png");
	private static final RenderType BEAM_RENDER_TYPE = RenderType.entityCutoutNoCull(BEAM_TEX);
	private final Random rand = new Random();

	public MonolithRenderer(EntityRendererProvider.Context context)
	{
		super(context, new MonolithModel<>(context.bakeLayer(ModModelLayers.MONOLITH)), 0.5F);
	}

	@Override
	public boolean shouldRender(MonolithEntity entity, Frustum camera, double camX, double camY, double camZ)
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
	public void render(MonolithEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight)
	{
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
		LivingEntity target = entity.getActiveAttackTarget();

		if (target != null)
		{
			float f = entity.getAttackAnimationScale(partialTicks);
			float f1 = (float)(entity.level.getGameTime() % 24000L) + partialTicks;
			float f2 = f1 * 0.5F % 1.0F;
			float f3 = entity.getEyeHeight();
			poseStack.pushPose();
			poseStack.translate(0.0D, (double)f3, 0.0D);
			Vec3 vec3 = ModClientUtils.getPosition(target, (double)target.getBbHeight() * 0.5D, partialTicks);
			Vec3 vec31 = ModClientUtils.getPosition(entity, (double)f3, partialTicks);
			Vec3 vec32 = vec3.subtract(vec31);
			float f4 = (float)(vec32.length() + 1.0D);
			vec32 = vec32.normalize();
			float f5 = (float)Math.acos(vec32.y);
			float f6 = (float)Math.atan2(vec32.z, vec32.x);
			poseStack.mulPose(Vector3f.YP.rotationDegrees((((float)Math.PI / 2.0F) - f6) * (180.0F / (float)Math.PI)));
			poseStack.mulPose(Vector3f.XP.rotationDegrees(f5 * (180.0F / (float)Math.PI)));
			float f7 = f1 * 0.05F * -1.5F;
			float f8 = f * f;
			int i = 192 + (int)(f8 * 55.0F);
			int j = 32 + (int)(f8 * 159.0F);
			int k = 160 + (int)(f8 * 63.0F);
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
			float f23 = Mth.cos(f7 + ((float)Math.PI / 2.0F)) * 0.2F;
			float f24 = Mth.sin(f7 + ((float)Math.PI / 2.0F)) * 0.2F;
			float f25 = Mth.cos(f7 + ((float)Math.PI * 1.5F)) * 0.2F;
			float f26 = Mth.sin(f7 + ((float)Math.PI * 1.5F)) * 0.2F;
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
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f23, f4, f24, i, j, k, 0.5F, f30);
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f23, 0.0F, f24, i, j, k, 0.5F, f29);
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f25, 0.0F, f26, i, j, k, 0.0F, f29);
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f25, f4, f26, i, j, k, 0.0F, f30);
			float f31 = entity.tickCount % 2 == 0 ? 0.5F : 0.0F;
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f11, f4, f12, i, j, k, 0.5F, f31 + 0.5F);
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f13, f4, f14, i, j, k, 1.0F, f31 + 0.5F);
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f17, f4, f18, i, j, k, 1.0F, f31);
			ModClientUtils.drawVertex(vertexconsumer, matrix4f, matrix3f, f15, f4, f16, i, j, k, 0.5F, f31);
			poseStack.popPose();
		}
	}

	@Override
	public Vec3 getRenderOffset(MonolithEntity entity, float partialTicks)
	{
		if (entity.getAttackPhase() == MonolithEntity.AttackPhase.ROAR_ATTACK)
		{
			return new Vec3(this.rand.nextGaussian() * 0.01D, 0.0D, this.rand.nextGaussian() * 0.01D);
		}
		else
		{
			return super.getRenderOffset(entity, partialTicks);
		}
	}

	@Override
	protected int getBlockLightLevel(MonolithEntity entity, BlockPos pos)
	{
		return Mth.clamp(super.getBlockLightLevel(entity, pos) + 12, 0, 15);
	}

	@Override
	public ResourceLocation getTextureLocation(MonolithEntity entity)
	{
		switch (entity.getAttackPhase())
		{
		case BEAM_CHARGE2:
		case BEAM_END:
			return TEX1;
		case BEAM_ATTACK:
			return TEX2;
		case ROAR_CHARGE:
		case ROAR_END:
			return TEX3;
		case ROAR_ATTACK:
			return TEX4;
		default:
			return TEX0;
		}
	}
}