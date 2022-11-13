package com.github.mechalopa.hmag.world.item;

import com.github.mechalopa.hmag.world.entity.projectile.ThrowableBottleEntity;

import net.minecraft.Util;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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
			protected Projectile getProjectile(Level level, Position position, ItemStack stack)
			{
				return Util.make(new ThrowableBottleEntity(level, position.x(), position.y(), position.z()), (e) -> {
					e.setItem(stack);
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
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_BOTTLE_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
		player.getCooldowns().addCooldown(this, 20);

		if (!level.isClientSide)
		{
			ThrowableBottleEntity bottleentity = new ThrowableBottleEntity(level, player);
			bottleentity.setItem(stack);
			bottleentity.shootFromRotation(player, player.getXRot(), player.getYRot(), -20.0F, 0.7F, 1.0F);
			level.addFreshEntity(bottleentity);
		}

		player.awardStat(Stats.ITEM_USED.get(this));

		if (!player.getAbilities().instabuild)
		{
			stack.shrink(1);
		}

		return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
	}
}