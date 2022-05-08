package com.github.mechalopa.hmag.world.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

public abstract class ModBowItem extends BowItem
{
	public ModBowItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int count)
	{
		if (livingEntity instanceof Player)
		{
			Player player = (Player)livingEntity;
			boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
			ItemStack stack1 = player.getProjectile(stack);

			int i = this.getUseDuration(stack) - count;
			i = ForgeEventFactory.onArrowLoose(stack, level, player, i, !stack1.isEmpty() || flag);

			if (i < 0)
			{
				return;
			}

			if (!stack1.isEmpty() || flag)
			{
				if (stack1.isEmpty())
				{
					stack1 = new ItemStack(Items.ARROW);
				}

				float f = getPowerForTime(i);

				if (!(f < 0.1D))
				{
					boolean flag1 = player.getAbilities().instabuild || (stack1.getItem() instanceof ArrowItem && ((ArrowItem)stack1.getItem()).isInfinite(stack1, stack, player));

					if (!level.isClientSide)
					{
						AbstractArrow abstractarrowentity = this.createArrow(level, stack1, stack, player, f, flag1);

						stack.hurtAndBreak(1, player, (p) -> {
							p.broadcastBreakEvent(player.getUsedItemHand());
						});

						level.addFreshEntity(abstractarrowentity);
					}

					level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT,  SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

					if (!flag1 && !player.getAbilities().instabuild)
					{
						stack1.shrink(1);

						if (stack1.isEmpty())
						{
							player.getInventory().removeItem(stack1);
						}
					}

					player.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

	protected AbstractArrow createArrow(Level level, ItemStack arrowStack, ItemStack bowStack, Player player, float power, boolean flag)
	{
		ArrowItem arrowitem = (ArrowItem)(arrowStack.getItem() instanceof ArrowItem ? arrowStack.getItem() : Items.ARROW);
		AbstractArrow arrow = this.customArrow(arrowitem.createArrow(level, arrowStack, player));
		arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, power * this.getArrowVelocity(bowStack, player), 1.0F);
		arrow.setBaseDamage(arrow.getBaseDamage() + this.getBowDamage(bowStack, player));

		if (power == 1.0F)
		{
			arrow.setCritArrow(true);
		}

		int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);

		if (j > 0)
		{
			arrow.setBaseDamage(arrow.getBaseDamage() + j * 0.5D + 0.5D);
		}

		int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bowStack);

		if (k > 0)
		{
			arrow.setKnockback(k);
		}

		if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bowStack) > 0)
		{
			arrow.setSecondsOnFire(100);
		}

		if (flag || player.getAbilities().instabuild && (arrowStack.getItem() == Items.SPECTRAL_ARROW || arrowStack.getItem() == Items.TIPPED_ARROW))
		{
			arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
		}

		return arrow;
	}

	public float getBowDamage(ItemStack stack, LivingEntity livingEntity)
	{
		return 0.0F;
	}

	public float getArrowVelocity(ItemStack stack, LivingEntity livingEntity)
	{
		return 3.0F;
	}

	@OnlyIn(Dist.CLIENT)
	public float getBowFOV(ItemStack stack, Player player)
	{
		return 0.0F;
	}
}