package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.DrownedGirlArmorModel;
import com.github.mechalopa.hmag.client.model.DrownedGirlModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DrownedGirlRenderer extends ZombieGirlRenderer
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/drowned_girl.png");

	public DrownedGirlRenderer(EntityRendererProvider.Context context)
	{
		super(context, new DrownedGirlModel<>(context.bakeLayer(ModModelLayers.DROWNED_GIRL)), new DrownedGirlArmorModel<>(context.bakeLayer(ModModelLayers.DROWNED_GIRL_INNER_ARMOR)), new DrownedGirlArmorModel<>(context.bakeLayer(ModModelLayers.DROWNED_GIRL_OUTER_ARMOR)));
	}

	@Override
	public ResourceLocation getTextureLocation(Zombie entity)
	{
		return TEX;
	}

	@Override
	protected void setupRotations(Zombie entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
		float f = entity.getSwimAmount(partialTicks);

		if (f > 0.0F)
		{
			poseStack.rotateAround(Axis.XP.rotationDegrees(Mth.lerp(f, 0.0F, -10.0F - entity.getXRot())), 0.0F, entity.getBbHeight() / 2.0F, 0.0F);
		}
	}
}