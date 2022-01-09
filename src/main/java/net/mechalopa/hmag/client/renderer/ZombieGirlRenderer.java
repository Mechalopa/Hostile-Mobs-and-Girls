package net.mechalopa.hmag.client.renderer;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.model.ZombieGirlModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlRenderer extends AbstractGirlRenderer<ZombieEntity, ZombieGirlModel<ZombieEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/zombie_girl.png");

	public ZombieGirlRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ZombieGirlModel<>(), 0.5F);
		this.addLayer(new BipedArmorLayer<>(this, new ZombieGirlModel<>(0.5F, true), new ZombieGirlModel<>(1.0F, true)));
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieEntity entityIn)
	{
		return TEX;
	}

	@Override
	protected boolean isShaking(ZombieEntity entityIn)
	{
		return entityIn.isUnderWaterConverting();
	}
}