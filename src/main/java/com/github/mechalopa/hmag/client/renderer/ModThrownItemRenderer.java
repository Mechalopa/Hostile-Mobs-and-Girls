package com.github.mechalopa.hmag.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModThrownItemRenderer<T extends Entity & ItemSupplier> extends ThrownItemRenderer<T>
{
	public ModThrownItemRenderer(EntityRendererProvider.Context context, float scale, boolean isFullBright)
	{
		super(context, scale, isFullBright);
	}

	public ModThrownItemRenderer(EntityRendererProvider.Context context)
	{
		super(context);
	}
}