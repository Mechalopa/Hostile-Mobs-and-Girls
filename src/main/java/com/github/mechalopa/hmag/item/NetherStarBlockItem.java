package com.github.mechalopa.hmag.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;

public class NetherStarBlockItem extends BlockItem
{
	public NetherStarBlockItem(Block blockIn, Item.Properties builder)
	{
		super(blockIn, builder);
	}

	@Override
	public boolean canBeHurtBy(DamageSource damageSource)
	{
		return damageSource.isExplosion() ? false : super.canBeHurtBy(damageSource);
	}
}