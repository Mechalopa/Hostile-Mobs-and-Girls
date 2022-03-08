package net.mechalopa.hmag.item;

import java.util.List;

import javax.annotation.Nullable;

import net.mechalopa.hmag.entity.projectile.NemesisBulletEntity;
import net.mechalopa.hmag.util.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
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

public class NemesisBladeItem extends ModSwordItem
{
	public NemesisBladeItem(IItemTier tier, Properties builderIn)
	{
		super(tier, 4.0F, -3.5F, 2537, 17, builderIn);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected)
	{
		if (!world.isClientSide)
		{
			if (entity.tickCount % 5 == 0 && entity instanceof PlayerEntity)
			{
				CompoundNBT compoundnbt = stack.getOrCreateTag();
				int level = !compoundnbt.contains("hmag.level") ? 0 : (int)compoundnbt.getByte(ModUtils.LEVEL_KEY);
				final int i = Math.max(((PlayerEntity)entity).experienceLevel, 0);
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
				target.addEffect(new EffectInstance(j == 2 ? Effects.WEAKNESS : (j == 1 ? Effects.MOVEMENT_SLOWDOWN : Effects.DIG_SLOWDOWN), (i + 4) * 20, 0));
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void releaseUsing(ItemStack stack, World world, LivingEntity livingEntity, int count)
	{
		if (livingEntity instanceof PlayerEntity)
		{
			final int i = ModUtils.getLevel(stack);
			PlayerEntity player = (PlayerEntity)livingEntity;

			if (this.getUseDuration(stack) - count < 12 || !((player.experienceLevel > 0 && i > 0) || player.isCreative()))
			{
				return;
			}

			if (!world.isClientSide)
			{
				NemesisBulletEntity bulletentity = new NemesisBulletEntity(world, player, 0.0D, 0.0D, 0.0D);
				bulletentity.setPos(bulletentity.getX(), player.getY(0.5F), bulletentity.getZ());
				bulletentity.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.2F, 0.1F);
				bulletentity.setDamage((float)i * 3.0F + 6.0F);
				bulletentity.setPierceLevel((byte)MathHelper.clamp(i - 1, 0, 255));
				bulletentity.setEffectLevel((byte)MathHelper.clamp(i, 0, 255));

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
			world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.WITHER_SHOOT, SoundCategory.PLAYERS, 0.75F, random.nextFloat() * 0.2F + 0.9F);
			player.awardStat(Stats.ITEM_USED.get(this));
		}
	}

	@Override
	public void onUseTick(World world, LivingEntity livingEntity, ItemStack stack, int count)
	{
		if (livingEntity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)livingEntity;

			if (this.getUseDuration(stack) - count != 12 || !((player.experienceLevel > 0 && ModUtils.getLevel(stack) > 0) || player.isCreative()))
			{
				return;
			}

			world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.5F + 1.0F) + 0.2F);
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
		textcomponent.withStyle(i >= 6 ? TextFormatting.LIGHT_PURPLE : (i >= 5 ? TextFormatting.AQUA : (i >= 3 ? TextFormatting.YELLOW : (i <= 0 ? TextFormatting.RED : TextFormatting.GRAY))));
		list.add(textcomponent);
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> list)
	{
		if (this.allowdedIn(group))
		{
			ItemStack stack = new ItemStack(this);
			CompoundNBT compoundnbt = stack.getOrCreateTag();
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