package com.github.mechalopa.hmag.item;

import java.util.List;

import javax.annotation.Nullable;

import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class InsomniaSwordItem extends ModSwordItem
{
	public InsomniaSwordItem(Tier tier, Properties builderIn)
	{
		super(tier, 1.0F, -2.4F, 1750, 15, builderIn);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected)
	{
		if (!world.isClientSide)
		{
			if (entity.tickCount % 5 == 0 && entity instanceof ServerPlayerEntity)
			{
				CompoundTag compoundnbt = stack.getOrCreateTag();
				int level = !compoundnbt.contains(ModUtils.LEVEL_KEY) ? 0 : (int)compoundnbt.getByte(ModUtils.LEVEL_KEY);
				ServerStatisticsManager serverstatisticsmanager = ((ServerPlayerEntity)entity).getStats();
				final int i = 24000;
				final int j = Math.max(serverstatisticsmanager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)), 1);
				int k = j / i;
				k = MathHelper.clamp(k - 2, 0, 5);

				if (level != k)
				{
					compoundnbt.putByte(ModUtils.LEVEL_KEY, (byte)k);
				}
			}
		}
	}

	@Override
	public void releaseUsing(ItemStack stack, World world, LivingEntity livingEntity, int count)
	{
		if (livingEntity instanceof Player)
		{
			if (this.getUseDuration(stack) - count < 8)
			{
				return;
			}

			Player player = (Player)livingEntity;

			if (!world.isClientSide)
			{
				player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10 * 20, 0));
			}

			world.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 1.0F, random.nextFloat() * 0.2F + 0.9F);
			player.awardStat(Stats.ITEM_USED.get(this));
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
		boolean flag = ModUtils.getLevel(stack) > 0 || playerIn.isCreative();

		if (flag && playerIn.isCrouching())
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
		textcomponent.withStyle(i >= 5 ? ChatFormatting.LIGHT_PURPLE : (i >= 4 ? ChatFormatting.AQUA : (i >= 2 ? ChatFormatting.YELLOW : (i <= 0 ? ChatFormatting.RED : ChatFormatting.GRAY))));
		list.add(textcomponent);
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> list)
	{
		if (this.allowdedIn(group))
		{
			ItemStack stack = new ItemStack(this);
			CompoundTag compoundnbt = stack.getOrCreateTag();
			compoundnbt.putByte(ModUtils.LEVEL_KEY, (byte)5);
			list.add(stack);
		}
	}
}