package com.github.mechalopa.hmag.client.util;

import com.github.mechalopa.hmag.entity.IBeamAttackMob;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3d;

import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModClientUtils
{
	public static final IItemPropertyGetter PROPERTY_BOW_PULL = (stack, world, livingentity) -> {
		if (livingentity == null)
		{
			return 0.0F;
		}
		else
		{
			return livingentity.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration() - livingentity.getUseItemRemainingTicks()) / 20.0F;
		}
	};
	public static final IItemPropertyGetter PROPERTY_BOW_PULLING = (stack, world, livingentity) -> {
		return livingentity != null && livingentity.isUsingItem() && livingentity.getUseItem() == stack ? 1.0F : 0.0F;
	};
	public static final IItemPropertyGetter PROPERTY_SHIELD_BLOCKING = (stack, world, livingentity) -> {
		return livingentity != null && livingentity.isUsingItem() && livingentity.getUseItem() == stack ? 1.0F : 0.0F;
	};

	public static Vector3d getPosition(LivingEntity entityLivingBaseIn, double d0, float f)
	{
		double d1 = MathHelper.lerp((double)f, entityLivingBaseIn.xOld, entityLivingBaseIn.getX());
		double d2 = MathHelper.lerp((double)f, entityLivingBaseIn.yOld, entityLivingBaseIn.getY()) + d0;
		double d3 = MathHelper.lerp((double)f, entityLivingBaseIn.zOld, entityLivingBaseIn.getZ());
		return new Vector3d(d1, d2, d3);
	}

	public static boolean shouldRenderBeamAttackMob(LivingEntity livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ, IBeamAttackMob beamAttackMobIn)
	{
		if (beamAttackMobIn.hasActiveAttackTarget())
		{
			LivingEntity target = beamAttackMobIn.getActiveAttackTarget();

			if (target != null)
			{
				Vector3d vector3d = ModClientUtils.getPosition(target, target.getBbHeight() * 0.5D, 1.0F);
				Vector3d vector3d1 = ModClientUtils.getPosition(livingEntityIn, livingEntityIn.getEyeHeight(), 1.0F);
				return camera.isVisible(new AABB(vector3d1.x, vector3d1.y, vector3d1.z, vector3d.x, vector3d.y, vector3d.z));
			}
		}

		return false;
	}

	public static void drawVertex(IVertexBuilder vertexBuilder, Matrix4f matrix, Matrix3f normals, float f, float f1, float f2, int i, int j, int k, float f3, float f4)
	{
		vertexBuilder.vertex(matrix, f, f1, f2).color(i, j, k, 255).uv(f3, f4).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normals, 0.0F, 1.0F, 0.0F).endVertex();
	}

	public static int getSkyDarken(Level worldIn)
	{
		double d0 = 1.0D - worldIn.getRainLevel(1.0F) * 5.0F / 16.0D;
		double d1 = 1.0D - worldIn.getThunderLevel(1.0F) * 5.0F / 16.0D;
		double d2 = 0.5D + 2.0D * Mth.clamp((double)Mth.cos(worldIn.getTimeOfDay(1.0F) * ((float)Math.PI * 2.0F)), -0.25D, 0.25D);
		return (int)((1.0D - d2 * d0 * d1) * 11.0D);
	}
}