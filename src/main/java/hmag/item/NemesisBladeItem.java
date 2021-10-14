package hmag.item;

import java.util.List;

import javax.annotation.Nullable;

import hmag.util.ModUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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

			if (this.getUseDuration(stack) - count < 8 || !((player.experienceLevel > 0 && i > 0) || player.isCreative()))
			{
				return;
			}

			if (!world.isClientSide)
			{
				int j = 0;

				for (LivingEntity livingentity : world.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(4.0D, 0.25D, 4.0D), EntityPredicates.LIVING_ENTITY_STILL_ALIVE))
				{
					if (livingentity != player && !player.isAlliedTo(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity)livingentity).isMarker()) && player.distanceToSqr(livingentity) < 25.0D)
					{
						livingentity.knockback(1.0F * ((float)i / 6.0F) + 0.5F, (double)MathHelper.sin(player.yRot * ((float)Math.PI / 180.0F)), (double)(-MathHelper.cos(player.yRot * ((float)Math.PI / 180.0F))));
						livingentity.hurt(DamageSource.indirectMagic(player, player), 0.0F);
						livingentity.addEffect(new EffectInstance(Effects.WITHER, (i * 2 + 3) * 20, 1));
						++j;

						for (int k = 0; k < 12; ++k)
						{
							((ServerWorld)world).sendParticles(ParticleTypes.LARGE_SMOKE, livingentity.getRandomX(0.5D), livingentity.getRandomY() - 0.5D, livingentity.getRandomZ(0.5D), 1, 0.0D, 0.0D, 0.0D, 0.0D);
						}
					}
				}

				double d0 = (double)(-MathHelper.sin(player.yRot * ((float)Math.PI / 180.0F)));
				double d1 = (double)MathHelper.cos(player.yRot * ((float)Math.PI / 180.0F));

				if (world instanceof ServerWorld)
				{
					((ServerWorld)world).sendParticles(ParticleTypes.SWEEP_ATTACK, player.getX() + d0, player.getY(0.5D), player.getZ() + d1, 0, d0, 0.0D, d1, 0.0D);
				}

				if (j > 0 && !player.isCreative())
				{
					player.giveExperienceLevels(-Math.min(j, player.experienceLevel));
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

			if (!((player.experienceLevel > 0 && ModUtils.getLevel(stack) > 0) || player.isCreative()) || count % 10 != 1)
			{
				return;
			}

			if (!world.isClientSide)
			{
				for (LivingEntity livingentity : world.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(4.0D, 0.25D, 4.0D), EntityPredicates.LIVING_ENTITY_STILL_ALIVE))
				{
					if (livingentity != player && !player.isAlliedTo(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity)livingentity).isMarker()) && player.distanceToSqr(livingentity) < 25.0D)
					{
						for (int k = 0; k < 2; ++k)
						{
							((ServerWorld)world).sendParticles(ParticleTypes.LARGE_SMOKE, livingentity.getRandomX(0.5D), livingentity.getRandomY() - 0.5D, livingentity.getRandomZ(0.5D), 1, 0.0D, 0.0D, 0.0D, 0.0D);
						}
					}
				}
			}
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