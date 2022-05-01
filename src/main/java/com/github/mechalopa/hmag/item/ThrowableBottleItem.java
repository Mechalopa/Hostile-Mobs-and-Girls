package com.github.mechalopa.hmag.item;

import com.github.mechalopa.hmag.entity.projectile.ThrowableBottleEntity;

import net.minecraft.Util;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class ThrowableBottleItem extends ModItem
{
	public ThrowableBottleItem(Item.Properties builder)
	{
		this(builder, new ModItem.Properties());
	}

	public ThrowableBottleItem(Item.Properties builder, ModItem.Properties builder1)
	{
		super(builder, builder1);
		DispenserBlock.registerBehavior(this, new AbstractProjectileDispenseBehavior()
		{
			@Override
			protected Projectile getProjectile(Level worldIn, Position position, ItemStack stackIn)
			{
				return Util.make(new ThrowableBottleEntity(worldIn, position.x(), position.y(), position.z()), (e) -> {
					e.setItem(stackIn);
				});
			}

			@Override
			protected float getUncertainty()
			{
				return super.getUncertainty() * 0.5F;
			}

			@Override
			protected float getPower()
			{
				return super.getPower() * 1.25F;
			}
		});
	}

	@Override
	public ActionResult<ItemStack> use(Level worldIn, Player playerIn, Hand handIn)
	{
		ItemStack stack = playerIn.getItemInHand(handIn);
		worldIn.playSound((Player)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.EXPERIENCE_BOTTLE_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		playerIn.getCooldowns().addCooldown(this, 20);

		if (!worldIn.isClientSide)
		{
			ThrowableBottleEntity bottleentity = new ThrowableBottleEntity(worldIn, playerIn);
			bottleentity.setItem(stack);
			bottleentity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), -20.0F, 0.7F, 1.0F);
			worldIn.addFreshEntity(bottleentity);
		}

		playerIn.awardStat(Stats.ITEM_USED.get(this));

		if (!playerIn.getAbilities().instabuild)
		{
			stack.shrink(1);
		}

		return ActionResult.sidedSuccess(stack, worldIn.isClientSide);
	}
}