package com.github.mechalopa.hmag.client.util;

import javax.annotation.Nonnull;

import org.joml.Matrix3f;
import org.joml.Matrix4f;

import com.github.mechalopa.hmag.util.ModUtils;
import com.github.mechalopa.hmag.world.entity.IBeamAttackMob;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModClientUtils
{
	public static final Minecraft MINECRAFT = Minecraft.getInstance();
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

	public static ResourceLocation getHMaGEntityTexture(String name)
	{
		return ModUtils.createHMaGRL("textures/entity/" + name + ".png");
	}

	public static Vec3 getPosition(@Nonnull LivingEntity entityLiving, double d0, float f)
	{
		double d1 = Mth.lerp((double)f, entityLiving.xOld, entityLiving.getX());
		double d2 = Mth.lerp((double)f, entityLiving.yOld, entityLiving.getY()) + d0;
		double d3 = Mth.lerp((double)f, entityLiving.zOld, entityLiving.getZ());
		return new Vec3(d1, d2, d3);
	}

	public static boolean shouldRenderBeamAttackMob(@Nonnull LivingEntity livingEntity, @Nonnull Frustum frustum, double camX, double camY, double camZ, @Nonnull IBeamAttackMob beamAttackMob)
	{
		if (beamAttackMob.hasActiveAttackTarget())
		{
			LivingEntity target = beamAttackMob.getActiveAttackTarget();

			if (target != null)
			{
				Vec3 vec3 = ModClientUtils.getPosition(target, target.getBbHeight() * 0.5D, 1.0F);
				Vec3 vec31 = ModClientUtils.getPosition(livingEntity, livingEntity.getEyeHeight(), 1.0F);
				return frustum.isVisible(new AABB(vec31.x, vec31.y, vec31.z, vec3.x, vec3.y, vec3.z));
			}
		}

		return false;
	}

	public static void drawVertex(VertexConsumer vertexConsumer, Matrix4f matrix, Matrix3f normals, float f, float f1, float f2, int i, int j, int k, float f3, float f4)
	{
		vertexConsumer.vertex(matrix, f, f1, f2).color(i, j, k, 255).uv(f3, f4).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normals, 0.0F, 1.0F, 0.0F).endVertex();
	}

	public static int getSkyDarken(Level level)
	{
		double d0 = 1.0D - level.getRainLevel(1.0F) * 5.0F / 16.0D;
		double d1 = 1.0D - level.getThunderLevel(1.0F) * 5.0F / 16.0D;
		double d2 = 0.5D + 2.0D * Mth.clamp((double)Mth.cos(level.getTimeOfDay(1.0F) * ((float)Math.PI * 2.0F)), -0.25D, 0.25D);
		return (int)((1.0D - d2 * d0 * d1) * 11.0D);
	}

	public static PartDefinition addC(PartDefinition part, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float extend)
	{
		return addC(part, new CubeDeformation(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, false);
	}

	public static PartDefinition addC(PartDefinition part, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, boolean isMirror, float extend)
	{
		return addC(part, new CubeDeformation(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, isMirror);
	}

	public static PartDefinition addC(PartDefinition part, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs)
	{
		return addC(part, CubeDeformation.NONE, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, false);
	}

	public static PartDefinition addC(PartDefinition part, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, boolean isMirror)
	{
		return addC(part, CubeDeformation.NONE, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, isMirror);
	}

	public static PartDefinition addC(PartDefinition part, CubeDeformation cube, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float extend)
	{
		return addC(part, cube.extend(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, false);
	}

	public static PartDefinition addC(PartDefinition part, CubeDeformation cube, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, boolean isMirror, float extend)
	{
		return addC(part, cube.extend(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, isMirror);
	}

	public static PartDefinition addC(PartDefinition part, CubeDeformation cube, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs)
	{
		return addC(part, cube, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, false);
	}

	public static PartDefinition addC(PartDefinition part, CubeDeformation cube, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, boolean isMirror)
	{
		return addC(part, cube, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, 0.0F, 0.0F, 0.0F, isMirror);
	}

	public static PartDefinition addC(PartDefinition part, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot, float extend)
	{
		return addC(part, new CubeDeformation(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, false);
	}

	public static PartDefinition addC(PartDefinition part, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot, boolean isMirror, float extend)
	{
		return addC(part, new CubeDeformation(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, isMirror);
	}

	public static PartDefinition addC(PartDefinition part, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot)
	{
		return addC(part, CubeDeformation.NONE, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, false);
	}

	public static PartDefinition addC(PartDefinition part, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot, boolean isMirror)
	{
		return addC(part, CubeDeformation.NONE, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, isMirror);
	}

	public static PartDefinition addC(PartDefinition part, CubeDeformation cube, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot, float extend)
	{
		return addC(part, cube.extend(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, false);
	}

	public static PartDefinition addC(PartDefinition part, CubeDeformation cube, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot, boolean isMirror, float extend)
	{
		return addC(part, cube.extend(extend), name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, isMirror);
	}

	public static PartDefinition addC(PartDefinition part, CubeDeformation cube, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot)
	{
		return addC(part, cube, name, xTexOffs, yTexOffs, xp, yp, zp, xSize, ySize, zSize, xOffs, yOffs, zOffs, xRot, yRot, zRot, false);
	}

	public static PartDefinition addC(PartDefinition part, CubeDeformation cube, String name, int xTexOffs, int yTexOffs, float xp, float yp, float zp, float xSize, float ySize, float zSize, float xOffs, float yOffs, float zOffs, float xRot, float yRot, float zRot, boolean isMirror)
	{
		return part.addOrReplaceChild(name, CubeListBuilder.create().texOffs(xTexOffs, yTexOffs).mirror(isMirror).addBox(xp, yp, zp, xSize, ySize, zSize, cube), PartPose.offsetAndRotation(xOffs, yOffs, zOffs, xRot, yRot, zRot));
	}

	public static void scaleModelPart(ModelPart part, float scale)
	{
		part.xScale = 1.0F + scale;
		part.yScale = 1.0F - scale;
		part.zScale = 1.0F + scale;
	}

	public static float[] getRainbowColors(Entity entity, float partialTicks)
	{
		final int i = entity.tickCount / 25 + entity.getId();
		final int j = DyeColor.values().length;
		final int k = i % j;
		final int l = (i + 1) % j;
		final float f = ((float)(entity.tickCount % 25) + partialTicks) / 25.0F;
		final float[] afloat1 = Sheep.getColorArray(DyeColor.byId(k));
		final float[] afloat2 = Sheep.getColorArray(DyeColor.byId(l));
		final float[] afloat3 = {afloat1[0] * (1.0F - f) + afloat2[0] * f, afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f};
		return afloat3;
	}
}