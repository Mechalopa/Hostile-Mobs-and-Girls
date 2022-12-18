package com.github.mechalopa.hmag.client.util;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.world.entity.IBeamAttackMob;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModClientUtils
{
	public static final ClampedItemPropertyFunction PROPERTY_BOW_PULL = (stack, level, livingentity, seed) -> {
		if (livingentity == null)
		{
			return 0.0F;
		}
		else
		{
			return livingentity.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration() - livingentity.getUseItemRemainingTicks()) / 20.0F;
		}
	};
	public static final ClampedItemPropertyFunction PROPERTY_BOW_PULLING = (stack, world, livingentity, seed) -> {
		return livingentity != null && livingentity.isUsingItem() && livingentity.getUseItem() == stack ? 1.0F : 0.0F;
	};
	public static final ClampedItemPropertyFunction PROPERTY_SHIELD_BLOCKING = (stack, world, livingentity, seed) -> {
		return livingentity != null && livingentity.isUsingItem() && livingentity.getUseItem() == stack ? 1.0F : 0.0F;
	};

	public static Vec3 getPosition(@Nonnull LivingEntity entityLiving, double d0, float f)
	{
		double d1 = Mth.lerp((double)f, entityLiving.xOld, entityLiving.getX());
		double d2 = Mth.lerp((double)f, entityLiving.yOld, entityLiving.getY()) + d0;
		double d3 = Mth.lerp((double)f, entityLiving.zOld, entityLiving.getZ());
		return new Vec3(d1, d2, d3);
	}

	public static boolean shouldRenderBeamAttackMob(@Nonnull LivingEntity livingEntityIn, Frustum camera, double camX, double camY, double camZ, @Nonnull IBeamAttackMob beamAttackMobIn)
	{
		if (beamAttackMobIn.hasActiveAttackTarget())
		{
			LivingEntity target = beamAttackMobIn.getActiveAttackTarget();

			if (target != null)
			{
				Vec3 vec3 = ModClientUtils.getPosition(target, target.getBbHeight() * 0.5D, 1.0F);
				Vec3 vec31 = ModClientUtils.getPosition(livingEntityIn, livingEntityIn.getEyeHeight(), 1.0F);
				return camera.isVisible(new AABB(vec31.x, vec31.y, vec31.z, vec3.x, vec3.y, vec3.z));
			}
		}

		return false;
	}

	public static void drawVertex(VertexConsumer vertexConsumer, Matrix4f matrix, Matrix3f normals, float f, float f1, float f2, int i, int j, int k, float f3, float f4)
	{
		vertexConsumer.vertex(matrix, f, f1, f2).color(i, j, k, 255).uv(f3, f4).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normals, 0.0F, 1.0F, 0.0F).endVertex();
	}

	public static int getSkyDarken(Level worldIn)
	{
		double d0 = 1.0D - worldIn.getRainLevel(1.0F) * 5.0F / 16.0D;
		double d1 = 1.0D - worldIn.getThunderLevel(1.0F) * 5.0F / 16.0D;
		double d2 = 0.5D + 2.0D * Mth.clamp((double)Mth.cos(worldIn.getTimeOfDay(1.0F) * ((float)Math.PI * 2.0F)), -0.25D, 0.25D);
		return (int)((1.0D - d2 * d0 * d1) * 11.0D);
	}

	public static PartDefinition addC(PartDefinition partdefinition, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float extend)
	{
		return addC(partdefinition, new CubeDeformation(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, false);
	}

	public static PartDefinition addC(PartDefinition partdefinition, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, boolean isMirror, float extend)
	{
		return addC(partdefinition, new CubeDeformation(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, isMirror);
	}

	public static PartDefinition addC(PartDefinition partdefinition, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs)
	{
		return addC(partdefinition, CubeDeformation.NONE, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, false);
	}

	public static PartDefinition addC(PartDefinition partdefinition, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, boolean isMirror)
	{
		return addC(partdefinition, CubeDeformation.NONE, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, isMirror);
	}

	public static PartDefinition addC(PartDefinition partdefinition, CubeDeformation cubeDeformation, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float extend)
	{
		return addC(partdefinition, cubeDeformation.extend(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, false);
	}

	public static PartDefinition addC(PartDefinition partdefinition, CubeDeformation cubeDeformation, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, boolean isMirror, float extend)
	{
		return addC(partdefinition, cubeDeformation.extend(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, isMirror);
	}

	public static PartDefinition addC(PartDefinition partdefinition, CubeDeformation cubeDeformation, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs)
	{
		return addC(partdefinition, cubeDeformation, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, false);
	}

	public static PartDefinition addC(PartDefinition partdefinition, CubeDeformation cubeDeformation, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, boolean isMirror)
	{
		return addC(partdefinition, cubeDeformation, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, isMirror);
	}

	public static PartDefinition addC(PartDefinition partdefinition, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot, float extend)
	{
		return addC(partdefinition, new CubeDeformation(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, false);
	}

	public static PartDefinition addC(PartDefinition partdefinition, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot, boolean isMirror, float extend)
	{
		return addC(partdefinition, new CubeDeformation(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, isMirror);
	}

	public static PartDefinition addC(PartDefinition partdefinition, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot)
	{
		return addC(partdefinition, CubeDeformation.NONE, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, false);
	}

	public static PartDefinition addC(PartDefinition partdefinition, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot, boolean isMirror)
	{
		return addC(partdefinition, CubeDeformation.NONE, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, isMirror);
	}

	public static PartDefinition addC(PartDefinition partdefinition, CubeDeformation cubeDeformation, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot, float extend)
	{
		return addC(partdefinition, cubeDeformation.extend(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, false);
	}

	public static PartDefinition addC(PartDefinition partdefinition, CubeDeformation cubeDeformation, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot, boolean isMirror, float extend)
	{
		return addC(partdefinition, cubeDeformation.extend(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, isMirror);
	}

	public static PartDefinition addC(PartDefinition partdefinition, CubeDeformation cubeDeformation, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot)
	{
		return addC(partdefinition, cubeDeformation, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, false);
	}

	public static PartDefinition addC(PartDefinition partdefinition, CubeDeformation cubeDeformation, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot, boolean isMirror)
	{
		return partdefinition.addOrReplaceChild(name, CubeListBuilder.create().texOffs(xTexOffs, yTexOffs).mirror(isMirror).addBox(xp, yp, zp, xSize, ySize, zSize, cubeDeformation), PartPose.offsetAndRotation(xOffs, yOffs, zOffs, xRot, yRot, zRot));
	}
}