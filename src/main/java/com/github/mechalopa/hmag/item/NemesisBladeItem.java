package com.github.mechalopa.hmag.item;

import java.util.List;

import javax.annotation.Nullable;

import com.github.mechalopa.hmag.entity.projectile.MagicBulletEntity;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NemesisBladeItem extends ModSwordItem
{
	public NemesisBladeItem(IItemTier tier, Properties builderIn)
	{
		super(tier, 4.0F, -3.5F, 2537, 17, builderIn);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected)
	{
		if (!world.isClientSide)
		{
			if (entity.tickCount % 5 == 0 && entity instanceof Player)
			{
				CompoundTag compoundnbt = stack.getOrCreateTag();
				int level = !compoundnbt.contains("hmag.level") ? 0 : (int)compoundnbt.getByte(ModUtils.LEVEL_KEY);
				final int i = Math.max(((Player)entity).experienceLevel, 0);
				int j = 0;

				if (i < 30)
				{
					j = 6 - (i / 5);
				}

				if (level != j)
				{
					compoundnbt.putByte(ModUtils.LEVEL_KEY, (byte)j);
				}
			}
		}
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attaker)
	{
		if (super.hurtEnemy(stack, target, attaker))
		{
			final int i = ModUtils.getLevel(stack);

			if (i > 0)
			{
				final int j = random.nextInt(3);
				target.addEffect(new MobEffectInstance(j == 2 ? MobEffects.WEAKNESS : (j == 1 ? MobEffects.MOVEMENT_SLOWDOWN : MobEffects.DIG_SLOWDOWN), (i + 4) * 20, 0));
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void releaseUsing(ItemStack stack, Level world, LivingEntity livingEntity, int count)
	{
		if (livingEntity instanceof Player)
		{
			final int i = ModUtils.getLevel(stack);
			Player player = (Player)livingEntity;

			if (this.getUseDuration(stack) - count < 12 || !((player.experienceLevel > 0 && i > 0) || player.isCreative()))
			{
				return;
			}

			if (!world.isClientSide)
			{
				MagicBulletEntity bulletentity = new MagicBulletEntity(world, player, 0.0D, 0.0D, 0.0D);
				bulletentity.setPos(bulletentity.getX(), player.getY(0.5F), bulletentity.getZ());
				bulletentity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.2F, 0.1F);
				bulletentity.setDamage(i * 3.0F + 6.0F);
				bulletentity.setPierceLevel((byte)MathHelper.clamp(i - 1, 0, 255));
				bulletentity.setEffectLevel((byte)MathHelper.clamp(i, 0, 255));
				bulletentity.setVariant(2);

				stack.hurtAndBreak(1, player, (p) -> {
					p.broadcastBreakEvent(player.getUsedItemHand());
				});

				world.addFreshEntity(bulletentity);

				if (!player.isCreative())
				{
					player.giveExperienceLevels(-1);
				}
			}

			player.swing(player.getUsedItemHand());
			world.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.WITHER_SHOOT, SoundCategory.PLAYERS, 0.75F, random.nextFloat() * 0.2F + 0.9F);
			player.awardStat(Stats.ITEM_USED.get(this));
		}
	}

	@Override
	public void onUseTick(Level world, LivingEntity livingEntity, ItemStack stack, int count)
	{
		if (livingEntity instanceof Player)
		{
			Player player = (Player)livingEntity;

			if (this.getUseDuration(stack) - count != 12 || !((player.experienceLevel > 0 && ModUtils.getLevel(stack) > 0) || player.isCreative()))
			{
				return;
			}

			world.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.5F + 1.0F) + 0.2F);
		}
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack)
	{
		return UseAction.BOW;
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, Player playerIn, Hand hand)
	{
		ItemStack stack = playerIn.getItemInHand(hand);
		boolean flag = (playerIn.experienceLevel > 0 && ModUtils.getLevel(stack) > 0) || playerIn.isCreative();

		if (flag)
		{
			playerIn.startUsingItem(hand);
			return ActionResult.consume(stack);
		}
		else
		{
			return ActionResult.fail(stack);
		}
	}

	@Override
	public int getUseDuration(ItemStack stack)
	{
		return 72000;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag iTooltipFlag)
	{
		final int i = (stack != null && !stack.isEmpty()) ? ModUtils.getLevel(stack) : 0;
		TranslationTextComponent textcomponent = new TranslationTextComponent("text.hmag.level", i + 1);
		textcomponent.withStyle(i >= 6 ? ChatFormatting.LIGHT_PURPLE : (i >= 5 ? ChatFormatting.AQUA : (i >= 3 ? ChatFormatting.YELLOW : (i <= 0 ? ChatFormatting.RED : ChatFormatting.GRAY))));
		list.add(textcomponent);
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> list)
	{
		if (this.allowdedIn(group))
		{
			ItemStack stack = new ItemStack(this);
			CompoundTag compoundnbt = stack.getOrCreateTag();
			compoundnbt.putByte(ModUtils.LEVEL_KEY, (byte)6);
			list.add(stack);
		}
	}

	@Override
	public boolean canBeHurtBy(DamageSource damageSource)
	{
		return damageSource != DamageSource.OUT_OF_WORLD ? false : super.canBeHurtBy(damageSource);
	}
}