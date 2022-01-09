package net.mechalopa.hmag.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
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
	public void releaseUsing(ItemStack stack, World world, LivingEntity livingEntity, int count)
	{
		if (livingEntity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)livingEntity;
			boolean flag = player.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
			ItemStack stack1 = player.getProjectile(stack);

			int i = this.getUseDuration(stack) - count;
			i = ForgeEventFactory.onArrowLoose(stack, world, player, i, !stack1.isEmpty() || flag);

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

				if (!((double)f < 0.1D))
				{
					boolean flag1 = player.abilities.instabuild || (stack1.getItem() instanceof ArrowItem && ((ArrowItem)stack1.getItem()).isInfinite(stack1, stack, player));

					if (!world.isClientSide)
					{
						AbstractArrowEntity abstractarrowentity = this.createArrow(world, stack1, stack, player, f, flag1);

						stack.hurtAndBreak(1, player, (p) -> {
							p.broadcastBreakEvent(player.getUsedItemHand());
						});

						world.addFreshEntity(abstractarrowentity);
					}

					world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

					if (!flag1 && !player.abilities.instabuild)
					{
						stack1.shrink(1);

						if (stack1.isEmpty())
						{
							player.inventory.removeItem(stack1);
						}
					}

					player.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

	protected AbstractArrowEntity createArrow(World world, ItemStack arrowStack, ItemStack bowStack, PlayerEntity player, float power, boolean flag)
	{
		ArrowItem arrowitem = (ArrowItem)(arrowStack.getItem() instanceof ArrowItem ? arrowStack.getItem() : Items.ARROW);
		AbstractArrowEntity abstractarrowentity = this.customArrow(arrowitem.createArrow(world, arrowStack, player));
		abstractarrowentity.shootFromRotation(player, player.xRot, player.yRot, 0.0F, power * this.getArrowVelocity(bowStack, player), 1.0F);
		abstractarrowentity.setBaseDamage(abstractarrowentity.getBaseDamage() + (double)this.getBowDamage(bowStack, player));

		if (power == 1.0F)
		{
			abstractarrowentity.setCritArrow(true);
		}

		int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);

		if (j > 0)
		{
			abstractarrowentity.setBaseDamage(abstractarrowentity.getBaseDamage() + (double)j * 0.5D + 0.5D);
		}

		int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bowStack);

		if (k > 0)
		{
			abstractarrowentity.setKnockback(k);
		}

		if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bowStack) > 0)
		{
			abstractarrowentity.setSecondsOnFire(100);
		}

		if (flag || player.abilities.instabuild && (arrowStack.getItem() == Items.SPECTRAL_ARROW || arrowStack.getItem() == Items.TIPPED_ARROW))
		{
			abstractarrowentity.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
		}

		return abstractarrowentity;
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
	public float getBowFOV(ItemStack stack, PlayerEntity player)
	{
		return 0.0F;
	}
}