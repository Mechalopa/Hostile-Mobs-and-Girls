package com.github.mechalopa.hmag.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;

public abstract class ModShieldItem extends ShieldItem
{
	public ModShieldItem(Item.Properties builder)
	{
		super(builder);
//		super(builder.setISTER(() -> ModItemStackTileEntityRenderer::new));
	}

	@Override
	public String getDescriptionId(ItemStack stack)
	{
		return this.getDescriptionId();
	}

//	@Override
//	public boolean isShield(ItemStack stack, @Nullable LivingEntity entity)
//	{
//		return true;
//	}

	@Override
	public int getEnchantmentValue()
	{
		return 1;
	}
}