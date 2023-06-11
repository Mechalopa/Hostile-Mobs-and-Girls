package com.github.mechalopa.hmag.world.item;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.world.entity.projectile.MagicBulletEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NemesisBladeItem extends ModSwordItem implements ILevelItem
{
	public static final UUID NEMESIS_BLADE_ATTACK_DAMAGE_UUID = UUID.fromString("FD835C41-1211-AC72-0CD4-66F3061FB156");
	public static final UUID NEMESIS_BLADE_ATTACK_SPEED_UUID = UUID.fromString("1911699B-779B-820E-064A-112D1EB232F7");
	public static final UUID NEMESIS_BLADE_MOVEMENT_SPEED_UUID = UUID.fromString("C80A68DD-F985-4245-4EC4-64884C8EBA4C");
	public static final AttributeModifier NEMESIS_BLADE_MOVEMENT_SPEED_MODIFIER = new AttributeModifier(NEMESIS_BLADE_MOVEMENT_SPEED_UUID, "Nemesis blade move speed penalty", -0.5F, AttributeModifier.Operation.MULTIPLY_TOTAL);

	public NemesisBladeItem(Tier tier, Properties builder)
	{
		super(tier, 4.0F, -3.5F, 2537, 17, builder);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected)
	{
		if (!level.isClientSide)
		{
			if (entity instanceof Player)
			{
				CompoundTag compoundnbt = stack.getOrCreateTag();
				int itemLevel = !compoundnbt.contains("hmag.level") ? 0 : (int)compoundnbt.getByte(ILevelItem.LEVEL_KEY);
				final int i = Math.max(((Player)entity).experienceLevel, 0);
				int j = 0;

				if (i < 30)
				{
					j = 6 - (i / 5);
				}

				if (itemLevel != j)
				{
					compoundnbt.putByte(ILevelItem.LEVEL_KEY, (byte)j);
				}
			}
		}
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attaker)
	{
		if (super.hurtEnemy(stack, target, attaker))
		{
			final int i = ILevelItem.getItemLevel(stack);

			if (i > 0)
			{
				final int j = attaker.getRandom().nextInt(3);
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
	public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int count)
	{
		if (livingEntity instanceof Player)
		{
			final int i = ILevelItem.getItemLevel(stack);
			Player player = (Player)livingEntity;

			if (this.getUseDuration(stack) - count < 8 || !((player.experienceLevel > 0 && i > 0) || player.isCreative()))
			{
				return;
			}

			if (!level.isClientSide)
			{
				MagicBulletEntity bullet = new MagicBulletEntity(level, player, 0.0D, 0.0D, 0.0D);
				bullet.setPos(bullet.getX(), player.getY(0.5F), bullet.getZ());
				bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.2F, 0.1F);
				bullet.setDamage(i * 3.0F + 6.0F);
				bullet.setPierceLevel((byte)Mth.clamp(i - 1, 0, 255));
				bullet.setEffectLevel((byte)Mth.clamp(i, 0, 255));
				bullet.setVariant(2);

				stack.hurtAndBreak(1, player, (p) -> {
					p.broadcastBreakEvent(player.getUsedItemHand());
				});

				level.addFreshEntity(bullet);

				if (!player.isCreative())
				{
					player.giveExperienceLevels(-1);
				}
			}

			player.swing(player.getUsedItemHand());
			level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.WITHER_SHOOT, SoundSource.PLAYERS, 0.75F, level.getRandom().nextFloat() * 0.2F + 0.9F);
			player.awardStat(Stats.ITEM_USED.get(this));
		}
	}

	@Override
	public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int count)
	{
		if (livingEntity instanceof Player)
		{
			Player player = (Player)livingEntity;

			if (this.getUseDuration(stack) - count != 8 || !((player.experienceLevel > 0 && ILevelItem.getItemLevel(stack) > 0) || player.isCreative()))
			{
				return;
			}

			level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
		}
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack)
	{
		return UseAnim.BOW;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		boolean flag = (player.experienceLevel > 0 && ILevelItem.getItemLevel(stack) > 0) || player.isCreative();

		if (flag && player.isCrouching())
		{
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(stack);
		}
		else
		{
			return InteractionResultHolder.fail(stack);
		}
	}

	@Override
	public int getUseDuration(ItemStack stack)
	{
		return 72000;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag)
	{
		final int i = ILevelItem.getItemLevel(stack);
		list.add(Component.translatable("text.hmag.level", i + 1).withStyle(i >= 6 ? ChatFormatting.LIGHT_PURPLE : (i >= 5 ? ChatFormatting.AQUA : (i >= 3 ? ChatFormatting.YELLOW : (i <= 0 ? ChatFormatting.RED : ChatFormatting.GRAY)))));
	}

	@Override
	public boolean canBeHurtBy(DamageSource damageSource)
	{
		return !damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY) ? false : super.canBeHurtBy(damageSource);
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack stack1)
	{
		return stack1.is(ModTags.ItemTags.NEMESIS_BLADE_REPAIR_ITEMS);
	}

	@Override
	public int getMaxLevel()
	{
		return 6;
	}
}