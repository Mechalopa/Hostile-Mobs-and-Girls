package com.github.mechalopa.hmag.world.item;

import java.util.List;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.registry.ModEffects;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class TotemOfRepulseItem extends Item
{
	public TotemOfRepulseItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int count)
	{
		if (livingEntity instanceof Player)
		{
			if (this.getUseDuration(stack) - count < 16)
			{
				return;
			}

			Player player = (Player)livingEntity;
			ItemStack stack1 = ModUtils.getPlayerInventoryItem(player, Ingredient.of(ModTags.TOTEM_OF_REPULSE_COSTS));

			if (!stack1.isEmpty() || player.getAbilities().instabuild)
			{
				if (!level.isClientSide)
				{
					player.addEffect(new MobEffectInstance(ModEffects.DARKNESS_RESISTANCE.get(), 60 * 20, 0));
					List<Warden> list = level.getEntitiesOfClass(Warden.class, new AABB(player.getX() - 0.5D, player.getY() - 0.5D, player.getZ() - 0.5D, player.getX() + 0.5D, player.getY() + 0.5D, player.getZ() + 0.5).inflate(16.0D), EntitySelector.ENTITY_STILL_ALIVE.and((p) -> {
						return p instanceof Warden && !(((Warden)p).isNoAi() || !((Warden)p).isAlive() || p.hasPose(Pose.DIGGING) || p.hasPose(Pose.EMERGING));
					}));

					if (!list.isEmpty())
					{
						for (Warden warden : list)
						{
							if (player.distanceToSqr(warden) <= 16.0D * 16.0D)
							{
								if (warden.getEntityAngryAt().isPresent())
								{
									warden.clearAnger(warden.getEntityAngryAt().get());
								}

								warden.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);

								if (warden.getBrain().hasMemoryValue(MemoryModuleType.DIG_COOLDOWN))
								{
									warden.getBrain().setMemoryWithExpiry(MemoryModuleType.DIG_COOLDOWN, Unit.INSTANCE, 0L);
								}

								if (!warden.isPersistenceRequired())
								{
									warden.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
									warden.getBrain().eraseMemory(MemoryModuleType.PATH);
									warden.getBrain().setDefaultActivity(Activity.DIG);
									warden.getBrain().setActiveActivityIfPossible(Activity.DIG);
									warden.spawnAnim();
								}
							}
						}
					}
				}
				else
				{
					ModClientUtils.MINECRAFT.particleEngine.createTrackingEmitter(player, ParticleTypes.SCULK_SOUL, 15);
					level.playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F, false);

					if (player == ModClientUtils.MINECRAFT.player)
					{
						ModClientUtils.MINECRAFT.gameRenderer.displayItemActivation(stack);
					}
				}

				if (ModConfigs.cachedServer.TOTEM_OF_REPULSE_COOLDOWN > 0)
				{
					player.getCooldowns().addCooldown(this, ModConfigs.cachedServer.TOTEM_OF_REPULSE_COOLDOWN);
				}

				if (player instanceof ServerPlayer)
				{
					CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, stack);
				}

				if (!player.getAbilities().instabuild)
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

	@Override
	public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int count)
	{
		if (livingEntity instanceof Player)
		{
			Player player = (Player)livingEntity;

			if (this.getUseDuration(stack) - count != 16)
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

		if (!player.getAbilities().instabuild && ModUtils.getPlayerInventoryItem(player, Ingredient.of(ModTags.TOTEM_OF_REPULSE_COSTS)).isEmpty())
		{
			return InteractionResultHolder.fail(stack);
		}
		else
		{
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(stack);
		}
	}

	@Override
	public int getUseDuration(ItemStack stack)
	{
		return 72000;
	}
}