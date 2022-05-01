package com.github.mechalopa.hmag.item;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class InvincibleBlockItem extends BlockItem
{
	public InvincibleBlockItem(Block blockIn, Item.Properties builder)
	{
		super(blockIn, builder);
	}

	@Override
	public boolean canBeHurtBy(DamageSource damageSource)
	{
		return damageSource != DamageSource.OUT_OF_WORLD ? false : super.canBeHurtBy(damageSource);
	}
}