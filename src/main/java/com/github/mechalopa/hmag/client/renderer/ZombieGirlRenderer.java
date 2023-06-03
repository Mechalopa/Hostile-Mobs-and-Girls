package com.github.mechalopa.hmag.client.renderer;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.AbstractGirlModel;
import com.github.mechalopa.hmag.client.model.ZombieGirlArmorModel;
import com.github.mechalopa.hmag.client.model.ZombieGirlModel;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlRenderer extends AbstractGirlRenderer<Zombie, AbstractGirlModel<Zombie>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/zombie_girl.png");

	public ZombieGirlRenderer(EntityRendererProvider.Context context)
	{
		this(context, ModModelLayers.ZOMBIE_GIRL, ModModelLayers.ZOMBIE_GIRL_INNER_ARMOR, ModModelLayers.ZOMBIE_GIRL_OUTER_ARMOR);
	}

	public ZombieGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation location, ModelLayerLocation location1, ModelLayerLocation location2)
	{
		this(context, new ZombieGirlModel<>(context.bakeLayer(location)), new ZombieGirlArmorModel<>(context.bakeLayer(location1)), new ZombieGirlArmorModel<>(context.bakeLayer(location2)));
	}

	public ZombieGirlRenderer(EntityRendererProvider.Context context, ZombieGirlModel<Zombie> model, AbstractGirlModel<Zombie> model1,AbstractGirlModel<Zombie> model2)
	{
		super(context, model, 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, model1, model2, context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(Zombie entity)
	{
		return TEX;
	}

	@Override
	protected boolean isShaking(Zombie entity)
	{
		return super.isShaking(entity) || entity.isUnderWaterConverting();
	}
}