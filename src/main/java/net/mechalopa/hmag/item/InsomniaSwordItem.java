package net.mechalopa.hmag.item;

import java.util.List;

import javax.annotation.Nullable;

import net.mechalopa.hmag.util.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class InsomniaSwordItem extends ModSwordItem
{
	public InsomniaSwordItem(IItemTier tier, Properties builderIn)
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
				CompoundNBT compoundnbt = stack.getOrCreateTag();
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
		if (livingEntity instanceof PlayerEntity)
		{
			if (this.getUseDuration(stack) - count < 8)
			{
				return;
			}

			PlayerEntity player = (PlayerEntity)livingEntity;

			if (!world.isClientSide)
			{
				player.addEffect(new EffectInstance(Effects.SLOW_FALLING, 10 * 20, 0));
			}

			world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 1.0F, random.nextFloat() * 0.2F + 0.9F);
			player.awardStat(Stats.ITEM_USED.get(this));
		}
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack)
	{
		return UseAction.BOW;
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand)
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
		textcomponent.withStyle(i >= 5 ? TextFormatting.LIGHT_PURPLE : (i >= 4 ? TextFormatting.AQUA : (i >= 2 ? TextFormatting.YELLOW : (i <= 0 ? TextFormatting.RED : TextFormatting.GRAY))));
		list.add(textcomponent);
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> list)
	{
		if (this.allowdedIn(group))
		{
			ItemStack stack = new ItemStack(this);
			CompoundNBT compoundnbt = stack.getOrCreateTag();
			compoundnbt.putByte(ModUtils.LEVEL_KEY, (byte)5);
			list.add(stack);
		}
	}
}