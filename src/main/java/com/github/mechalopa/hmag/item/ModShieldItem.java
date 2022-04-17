package com.github.mechalopa.hmag.item;

import javax.annotation.Nullable;

import com.github.mechalopa.hmag.client.renderer.ModItemStackTileEntityRenderer;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public abstract class ModShieldItem extends ShieldItem
{
	public ModShieldItem(Item.Properties builder)
	{
		super(builder.setISTER(() -> ModItemStackTileEntityRenderer::new));
	}

	@Override
	public String getDescriptionId(ItemStack stack)
	{
		return this.getDescriptionId();
	}

	@Override
	public boolean isShield(ItemStack stack, @Nullable LivingEntity entity)
	{
		return true;
	}

	@Override
	public int getEnchantmentValue()
	{
		return 1;
	}
}