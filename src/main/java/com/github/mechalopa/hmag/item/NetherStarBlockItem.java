package com.github.mechalopa.hmag.item;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

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