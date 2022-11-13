package com.github.mechalopa.hmag.world.item;

import com.github.mechalopa.hmag.world.entity.projectile.EvilArrowEntity;

import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class EvilArrowItem extends ArrowItem
{
	public EvilArrowItem(Item.Properties builder)
	{
		super(builder);
		DispenserBlock.registerBehavior(this, new AbstractProjectileDispenseBehavior()
		{
			@Override
			protected Projectile getProjectile(Level level, Position position, ItemStack stack)
			{
				AbstractArrow abstractarrow = new EvilArrowEntity(level, position.x(), position.y(), position.z());
				abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
				return abstractarrow;
			}
		});
	}

	@Override
	public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity thrower)
	{
		return new EvilArrowEntity(level, thrower);
	}
}