package com.github.mechalopa.hmag;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.github.mechalopa.hmag.registry.ModEffects;
import com.github.mechalopa.hmag.registry.ModEnchantments;
import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.registry.ModItems;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.util.ModUtils;
import com.github.mechalopa.hmag.world.entity.IModMob;
import com.github.mechalopa.hmag.world.item.AncientShieldItem;
import com.github.mechalopa.hmag.world.item.ILevelItem;
import com.github.mechalopa.hmag.world.item.InsomniaSwordItem;
import com.github.mechalopa.hmag.world.item.ModArmorMaterial;
import com.github.mechalopa.hmag.world.item.NemesisBladeItem;
import com.github.mechalopa.hmag.world.item.enchantment.HealthBoostEnchantment;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Player.BedSleepingProblem;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents
{
	private static final UUID[] NECROTIC_CHAINMAIL_ARMOR_ATTACK_SPEED_UUIDS = new UUID[]{UUID.fromString("81D1E394-8B33-AC3D-325A-C8A03E757B51"), UUID.fromString("B4EFB0CA-2180-0043-0FF9-BE033CC510D5"), UUID.fromString("7FD9FBE7-0438-BF0F-F901-F961384F9591"), UUID.fromString("28E5C0B5-109B-339B-BE83-610B5B399256")};
	private static final Component INSOMNIA_ITEM_MESSAGE = new TranslatableComponent("message.hmag.insomnia_item");

	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if (event.isCanceled())
		{
			return;
		}

		if (event.getEntityLiving() != null && event.getSource() != null)
		{
			LivingEntity livingentity = event.getEntityLiving();
			DamageSource source = event.getSource();

			if (!event.getSource().isProjectile())
			{
				if (source.getEntity() != null && source.getEntity() instanceof LivingEntity)
				{
					LivingEntity attacker = (LivingEntity)source.getEntity();
					ItemStack stack = attacker.getMainHandItem();

					if (!stack.isEmpty() && stack.hasTag())
					{
						final int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.WATER_ASPECT.get(), stack);

						if (level > 0)
						{
							if (livingentity.isOnFire())
							{
								livingentity.clearFire();
							}

							if (livingentity.isSensitiveToWater())
							{
								event.setAmount(event.getAmount() + (level * 2.5F));
							}
						}
					}
				}
			}
			else
			{
				if (source.getDirectEntity() != null && source.getDirectEntity() instanceof AbstractArrow && source.getEntity() != null && source.getEntity() instanceof LivingEntity)
				{
					if (((AbstractArrow)source.getDirectEntity()).shotFromCrossbow())
					{
						LivingEntity attacker = (LivingEntity)source.getEntity();
						int level = 0;

						for (InteractionHand hand : InteractionHand.values())
						{
							ItemStack stack = attacker.getItemInHand(hand);

							if (!stack.isEmpty() && stack.hasTag())
							{
								level = Math.max(level, EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.ANTI_AIR.get(), stack));
							}
						}

						if (level > 0)
						{
							if (!livingentity.isOnGround() && !livingentity.isInWaterOrBubble() && !livingentity.isInLava())
							{
								event.setAmount(event.getAmount() * (1.0F + level * 0.3F));
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onLivingDamage(LivingDamageEvent event)
	{
		if (event.isCanceled())
		{
			return;
		}

		if (event.getEntityLiving() != null && event.getSource() != null)
		{
			if (event.getSource().isFire())
			{
				if (!event.getEntityLiving().getUseItem().isEmpty() && event.getEntityLiving().getUseItem().getItem() == ModItems.FORTRESS_SHIELD.get())
				{
					event.setAmount(event.getAmount() * 0.25F);
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingAttack(LivingAttackEvent event)
	{
		if (event.isCanceled())
		{
			return;
		}

		if (event.getEntityLiving() != null && event.getSource() != null)
		{
			LivingEntity livingentity = event.getEntityLiving();
			DamageSource source = event.getSource();

			if (!livingentity.getUseItem().isEmpty() && source.getEntity() != null && source.getEntity() instanceof LivingEntity && source.getDirectEntity() != null && source.getEntity().equals(source.getDirectEntity()))
			{
				if (livingentity.getUseItem().getItem() == ModItems.ANCIENT_SHIELD.get())
				{
					if (livingentity.getRandom().nextInt(3) == 0)
					{
						final int i = livingentity.getRandom().nextInt(3);
						((LivingEntity)source.getEntity()).addEffect(new MobEffectInstance(i == 2 ? MobEffects.WEAKNESS : (i == 1 ? MobEffects.MOVEMENT_SLOWDOWN : MobEffects.DIG_SLOWDOWN), 5 * 20, 0));
					}
				}
				else if (livingentity.getUseItem().getItem() == ModItems.FORTRESS_SHIELD.get())
				{
					if (livingentity.getRandom().nextInt(3) == 0)
					{
						source.getEntity().setSecondsOnFire(8);
					}
				}
			}
		}
	}

	@SubscribeEvent(receiveCanceled = true)
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() == null)
		{
			return;
		}

		if (event.getEntityLiving().tickCount == 1 && !event.getEntityLiving().getPersistentData().getBoolean(ModUtils.LIVING_UPDATE_CHECKED_KEY))
		{
			event.getEntityLiving().getPersistentData().putBoolean(ModUtils.LIVING_UPDATE_CHECKED_KEY, true);

			if (event.getEntityLiving() instanceof Mob)
			{
				Mob mob = (Mob)event.getEntityLiving();

				if (mob.getPersistentData().getBoolean(ModUtils.WITH_SPAWN_PARTICLE_KEY))
				{
					mob.spawnAnim();
					mob.getPersistentData().remove(ModUtils.WITH_SPAWN_PARTICLE_KEY);
				}

				if (mob.isEffectiveAi() && mob.getPersistentData().getBoolean(ModUtils.LIVING_UPDATE_CHECKING_KEY) && !mob.getPersistentData().getBoolean(ModUtils.LIVING_NOT_REPLACED_KEY) && !mob.isPersistenceRequired() && !mob.requiresCustomPersistence() && !mob.isVehicle() && !mob.isPassenger())
				{
					ServerLevel serverlevel = (ServerLevel)event.getEntityLiving().getCommandSenderWorld();
					final double d = event.getEntityLiving().getRandom().nextDouble();

					if (ModConfigs.cachedServer.ZOMBIE_GIRL_REPLACE_CHANCE > d && mob.getType().is(ModTags.ZOMBIE_GIRL_REPLACEABLES))
					{
						replace(serverlevel, mob, ModEntityTypes.ZOMBIE_GIRL.get());
					}
					else if (ModConfigs.cachedServer.HUSK_GIRL_REPLACE_CHANCE > d && mob.getType().is(ModTags.HUSK_GIRL_REPLACEABLES))
					{
						replace(serverlevel, mob, ModEntityTypes.HUSK_GIRL.get());
					}
					else if (ModConfigs.cachedServer.DROWNED_GIRL_REPLACE_CHANCE > d && mob.getType().is(ModTags.DROWNED_GIRL_REPLACEABLES))
					{
						replace(serverlevel, mob, ModEntityTypes.DROWNED_GIRL.get());
					}
					else if (ModConfigs.cachedServer.SKELETON_GIRL_REPLACE_CHANCE > d && mob.getType().is(ModTags.SKELETON_GIRL_REPLACEABLES))
					{
						replace(serverlevel, mob, ModEntityTypes.SKELETON_GIRL.get());
					}
					else if (ModConfigs.cachedServer.WITHER_SKELETON_GIRL_REPLACE_CHANCE > d && mob.getType().is(ModTags.WITHER_SKELETON_GIRL_REPLACEABLES))
					{
						replace(serverlevel, mob, ModEntityTypes.WITHER_SKELETON_GIRL.get());
					}
					else if (ModConfigs.cachedServer.STRAY_GIRL_REPLACE_CHANCE > d && mob.getType().is(ModTags.STRAY_GIRL_REPLACEABLES))
					{
						replace(serverlevel, mob, ModEntityTypes.STRAY_GIRL.get());
					}
					else if (ModConfigs.cachedServer.CREEPER_GIRL_REPLACE_CHANCE > d && mob.getType().is(ModTags.CREEPER_GIRL_REPLACEABLES))
					{
						replace(serverlevel, mob, ModEntityTypes.CREEPER_GIRL.get());
					}
					else if (ModConfigs.cachedServer.ENDER_EXECUTOR_REPLACE_CHANCE > d && mob.getType().is(ModTags.ENDER_EXECUTOR_REPLACEABLES))
					{
						replace(serverlevel, mob, ModEntityTypes.ENDER_EXECUTOR.get());
					}
				}
			}
		}
	}

	private static boolean replace(ServerLevel level, Mob replacementMob, EntityType<?> type)
	{
		if (replacementMob.getType().equals(type))
		{
			return false;
		}

		Mob mob = createMob(level, type);

		if (mob == null)
		{
			return false;
		}

		SpawnGroupData spawndata = null;
		mob.copyPosition(replacementMob);
		spawndata = mob.finalizeSpawn(level, level.getCurrentDifficultyAt(new BlockPos(replacementMob.position())), MobSpawnType.NATURAL, spawndata, (CompoundTag)null);
		mob.setNoAi(replacementMob.isNoAi());

		if (replacementMob.hasCustomName())
		{
			mob.setCustomName(replacementMob.getCustomName());
			mob.setCustomNameVisible(replacementMob.isCustomNameVisible());
		}

		mob.getPersistentData().putBoolean(ModUtils.LIVING_NOT_REPLACED_KEY, true);
		level.addFreshEntityWithPassengers(mob);
		replacementMob.discard();
		return true;
	}

	@Nullable
	private static Mob createMob(ServerLevel level, EntityType<?> type)
	{
		try
		{
			Entity entity = type.create(level);

			if (!(entity instanceof Mob))
			{
				throw new IllegalStateException("Trying to spawn a non-mob: " + ForgeRegistries.ENTITIES.getKey(type));
			}
			else
			{
				return (Mob)entity;
			}
		}
		catch (Exception e)
		{
			HMaG.LOGGER.warn("Failed to create hmag mob", e);
			return null;
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onCheckSpawn(LivingSpawnEvent.CheckSpawn event)
	{
		if (event.getResult() == Result.DENY || event.getEntityLiving() == null)
		{
			return;
		}

		if (event.getEntityLiving() instanceof IModMob)
		{
			if (ModUtils.checkDimensionList(event.getEntityLiving().getCommandSenderWorld(), ModConfigs.cachedServer.SPAWN_DIMENSION_BLACKLIST))
			{
				event.setResult(Result.DENY);
			}
		}

		if (event.getSpawnReason() == MobSpawnType.NATURAL && !ModUtils.checkDimensionList(event.getEntityLiving().getCommandSenderWorld(), ModConfigs.cachedServer.MOB_REPLACE_DIMENSION_BLACKLIST) && !ModUtils.checkBiomeList(event.getEntityLiving().getCommandSenderWorld(), event.getEntityLiving().blockPosition(), ModConfigs.cachedServer.MOB_REPLACE_BIOME_BLACKLIST))
		{
			LivingEntity livingentity = event.getEntityLiving();

			if (ModConfigs.cachedServer.ZOMBIE_GIRL_REPLACE_CHANCE > 0.0D && livingentity.getType().is(ModTags.ZOMBIE_GIRL_REPLACEABLES))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.HUSK_GIRL_REPLACE_CHANCE > 0.0D && livingentity.getType().is(ModTags.HUSK_GIRL_REPLACEABLES))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.DROWNED_GIRL_REPLACE_CHANCE > 0.0D && livingentity.getType().is(ModTags.DROWNED_GIRL_REPLACEABLES))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.SKELETON_GIRL_REPLACE_CHANCE > 0.0D && livingentity.getType().is(ModTags.SKELETON_GIRL_REPLACEABLES))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.WITHER_SKELETON_GIRL_REPLACE_CHANCE > 0.0D && livingentity.getType().is(ModTags.WITHER_SKELETON_GIRL_REPLACEABLES))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.STRAY_GIRL_REPLACE_CHANCE > 0.0D && livingentity.getType().is(ModTags.STRAY_GIRL_REPLACEABLES))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.CREEPER_GIRL_REPLACE_CHANCE > 0.0D && livingentity.getType().is(ModTags.CREEPER_GIRL_REPLACEABLES))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.ENDER_EXECUTOR_REPLACE_CHANCE > 0.0D && livingentity.getType().is(ModTags.ENDER_EXECUTOR_REPLACEABLES))
			{
				putCheckingTag(livingentity);
			}
		}
	}

	private static void putCheckingTag(LivingEntity living)
	{
		living.getPersistentData().putBoolean(ModUtils.LIVING_UPDATE_CHECKING_KEY, true);
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		try
		{
			if (event.getEntity() != null)
			{
				if (event.getEntity() instanceof EnderMan)
				{
					EnderMan endermanentity = (EnderMan)event.getEntity();
					endermanentity.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(endermanentity, LivingEntity.class, 10, false, false, (p) -> {
						if (p.hasEffect(ModEffects.ENDER_RAGE.get()))
						{
							final double d0 = 8.0D + p.getEffect(ModEffects.ENDER_RAGE.get()).getAmplifier() * 12.0D;
							return p.distanceToSqr(endermanentity) <= d0 * d0;
						}
						else
						{
							return false;
						}
					}).setUnseenMemoryTicks(30));
				}
			}
		}
		catch (Exception e)
		{
			HMaG.LOGGER.warn("Failed to add goals to mobs", e);
		}
	}

	@SubscribeEvent
	public void onPotionApplicable(PotionApplicableEvent event)
	{
		if (event.getEntityLiving() != null && event.getPotionEffect() != null)
		{
			LivingEntity livingentity = event.getEntityLiving();
			MobEffect effect = event.getPotionEffect().getEffect();

			if (effect == MobEffects.MOVEMENT_SLOWDOWN || effect == MobEffects.DIG_SLOWDOWN || effect == MobEffects.WEAKNESS)
			{
				int i = 0;

				for (ItemStack stack : livingentity.getArmorSlots())
				{
					if (!stack.isEmpty())
					{
						Item item = stack.getItem();

						if (item != null && item instanceof ArmorItem && ((ArmorItem)item).getMaterial() == ModArmorMaterial.ANCIENT)
						{
							++i;
						}
					}
				}

				if (!ModUtils.getHeldItem(livingentity, ModItems.ANCIENT_SHIELD.get()).isEmpty())
				{
					++i;
				}

				if (i >= 4 || i > event.getPotionEffect().getAmplifier() + 1)
				{
					event.setResult(Result.DENY);
				}
			}
			else if (effect == MobEffects.BLINDNESS)
			{
				if (livingentity instanceof Player)
				{
					ItemStack stack1 = livingentity.getMainHandItem();

					if (!stack1.isEmpty() && stack1.getItem() == ModItems.INSOMNIA_SWORD.get() && ILevelItem.getItemLevel(stack1) > 0)
					{
						event.setResult(Result.DENY);
					}
				}
			}
			else if (effect == ModEffects.COMBUSTION.get())
			{
				if (livingentity.fireImmune() || !ModUtils.getHeldItem(livingentity, ModItems.FORTRESS_SHIELD.get()).isEmpty())
				{
					event.setResult(Result.DENY);
				}
			}
			else if (effect == ModEffects.ENDER_RAGE.get())
			{
				if (!livingentity.getType().is(ModTags.ENDER_RAGE_IMMUNE_BLACKLIST) && (livingentity instanceof EnderMan || livingentity.getType().is(ModTags.ENDER_RAGE_IMMUNE_WHITELIST)))
				{
					event.setResult(Result.DENY);
				}
			}
		}
	}

	@SubscribeEvent
	public void onItemAttributeModifier(ItemAttributeModifierEvent event)
	{
		if (!event.getItemStack().isEmpty() && event.getItemStack().getItem() != null && event.getSlotType() != null && event.getSlotType().getType() != null)
		{
			ItemStack stack = event.getItemStack();

			if (event.getSlotType() == Mob.getEquipmentSlotForItem(stack))
			{
				final int i = event.getSlotType().getIndex();

				if (event.getSlotType().getType() == EquipmentSlot.Type.ARMOR && i >= 0 && i < HealthBoostEnchantment.HEALTH_BOOST_ENCHANTMENT_MAX_HEALTH_UUIDS.length)
				{
					final int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.HEALTH_BOOST.get(), stack);

					if (level > 0)
					{
						event.addModifier(Attributes.MAX_HEALTH, new AttributeModifier(HealthBoostEnchantment.HEALTH_BOOST_ENCHANTMENT_MAX_HEALTH_UUIDS[i], "Health boost enchantment " + event.getSlotType().getName() + " max health bonus", level * 1.0F, AttributeModifier.Operation.ADDITION));
					}
				}

				if (stack.getItem() instanceof ArmorItem && ((ArmorItem)stack.getItem()).getMaterial() == ModArmorMaterial.NECROTIC_CHAIN)
				{
					event.addModifier(Attributes.ATTACK_SPEED, new AttributeModifier(NECROTIC_CHAINMAIL_ARMOR_ATTACK_SPEED_UUIDS[i], "Necrotic chainmail armor " + event.getSlotType().getName() + " attack speed bonus", 0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL));
				}
				else if (stack.getItem() == ModItems.INSOMNIA_SWORD.get())
				{
					final int level1 = ILevelItem.getItemLevel(stack);

					if (level1 > 0)
					{
						event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(InsomniaSwordItem.INSOMNIA_SWORD_ATTACK_DAMAGE_UUID, "Insomnia sword attack damage bonus", level1 * 1.0F + 1.0F, AttributeModifier.Operation.ADDITION));
					}
				}
				else if (stack.getItem() == ModItems.NEMESIS_BLADE.get())
				{
					final int level2 = ILevelItem.getItemLevel(stack);

					if (level2 > 0)
					{
						event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(NemesisBladeItem.NEMESIS_BLADE_ATTACK_DAMAGE_UUID, "Nemesis blade attack damage bonus", level2 * 1.0F, AttributeModifier.Operation.ADDITION));
						event.addModifier(Attributes.ATTACK_SPEED, new AttributeModifier(NemesisBladeItem.NEMESIS_BLADE_ATTACK_SPEED_UUID, "Nemesis blade attack speed bonus", level2 * 0.25F, AttributeModifier.Operation.ADDITION));
					}
					else
					{
						event.addModifier(Attributes.MOVEMENT_SPEED, NemesisBladeItem.NEMESIS_BLADE_MOVEMENT_SPEED_MODIFIER);
					}
				}
			}

			if (event.getSlotType().getType() == EquipmentSlot.Type.HAND)
			{
				if (stack.getItem() == ModItems.ANCIENT_SHIELD.get())
				{
					event.addModifier(Attributes.KNOCKBACK_RESISTANCE, AncientShieldItem.ANCIENT_SHIELD_KNOCKBACK_RESISTANCE_MODIFIER);
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerSleep(PlayerSleepInBedEvent event)
	{
		if (event.getResultStatus() == null)
		{
			if (event.getPlayer().getInventory().contains(ModTags.INSOMNIA_ITEMS))
			{
				event.setResult(BedSleepingProblem.OTHER_PROBLEM);
				event.getPlayer().displayClientMessage(INSOMNIA_ITEM_MESSAGE, true);
			}
		}
	}

	@SubscribeEvent
	public void onItemToss(ItemTossEvent event)
	{
		if (event.isCanceled())
		{
			return;
		}

		if (event.getEntityItem() != null && event.getEntityItem().getItem() != null && !event.getEntityItem().getItem().isEmpty() && event.getEntityItem().getItem().getItem() != null && event.getEntityItem().getItem().getItem() instanceof ILevelItem)
		{
			ILevelItem.removeItemLevelTag(event.getEntityItem().getItem());
		}
	}

	@SubscribeEvent
	public void onLivingEquipmentChange(LivingEquipmentChangeEvent event)
	{
		if (event.getEntityLiving() != null && !(event.getEntityLiving() instanceof Player) && event.getTo() != null && !event.getTo().isEmpty() && event.getTo().getItem() != null && event.getTo().getItem() instanceof ILevelItem)
		{
			ILevelItem.removeItemLevelTag(event.getTo());
		}
	}

	@SubscribeEvent
	public void onVillagerTrades(VillagerTradesEvent event)
	{
		if (ModConfigs.cachedServer.ADDITIONAL_VILLAGER_TRADES && event.getType() != null)
		{
			Int2ObjectMap<List<ItemListing>> trades = event.getTrades();

			if (event.getType() == VillagerProfession.CLERIC)
			{
				trades.get(2).add(new BasicItemListing(new ItemStack(ModItems.SOUL_POWDER.get(), 24), new ItemStack(Items.EMERALD, 1), 16, 2, 0.05F));
				trades.get(4).add(new BasicItemListing(new ItemStack(ModItems.CUREBERRY.get(), 3), new ItemStack(Items.EMERALD, 1), 16, 15, 0.05F));
				trades.get(4).add(new BasicItemListing(new ItemStack(ModItems.RANDOMBERRY.get(), 3), new ItemStack(Items.EMERALD, 1), 16, 15, 0.05F));
				trades.get(4).add(new BasicItemListing(new ItemStack(ModItems.EXP_BERRY.get(), 3), new ItemStack(Items.EMERALD, 1), 16, 15, 0.05F));
				trades.get(4).add(new BasicItemListing(new ItemStack(ModItems.SPECTRAL_SOUP.get(), 1), new ItemStack(Items.EMERALD, 1), 16, 15, 0.05F));
			}

			if (event.getType() == VillagerProfession.LEATHERWORKER)
			{
				trades.get(3).add(new BasicItemListing(new ItemStack(ModItems.BAT_WING.get(), 4), new ItemStack(Items.EMERALD, 1), 16, 15, 0.05F));

				if (ModConfigs.cachedServer.KOBOLD_SPAWN_WEIGHT > 0)
				{
					trades.get(4).add(new BasicItemListing(new ItemStack(ModItems.KOBOLD_LEATHER.get(), 4), new ItemStack(Items.EMERALD, 1), 12, 20, 0.05F));
				}
			}

			if (event.getType() == VillagerProfession.FARMER)
			{
				trades.get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.LEMON.get(), 4), 12, 4, 0.05F));
			}

			if (event.getType() == VillagerProfession.FISHERMAN)
			{
				if (ModConfigs.cachedServer.SAVAGEFANG_SPAWN_WEIGHT_IN_SWAMP > 0 || ModConfigs.cachedServer.SAVAGEFANG_SPAWN_WEIGHT_IN_JUNGLE > 0)
				{
					trades.get(4).add(new BasicItemListing(new ItemStack(ModItems.SAVAGEFANG_MEAT.get(), 3), new ItemStack(Items.EMERALD, 1), 12, 20, 0.05F));
				}
			}
		}
	}

	@SubscribeEvent
	public void onWandererTrades(WandererTradesEvent event)
	{
		if (ModConfigs.cachedServer.ADDITIONAL_WANDERER_TRADES)
		{
			List<ItemListing> genericTrades = event.getGenericTrades();
			List<ItemListing> rareTrades = event.getRareTrades();

			if (ModConfigs.cachedServer.ENDER_EXECUTOR_REPLACE_CHANCE > 0.0D)
			{
				genericTrades.add(new BasicItemListing(new ItemStack(ModItems.ENDER_PLASM.get(), 1), new ItemStack(Items.EMERALD, 2), 32, 1, 0.05F));
			}

			genericTrades.add(new BasicItemListing(new ItemStack(ModItems.ANCIENT_STONE.get(), 2), new ItemStack(Items.EMERALD, 1), 32, 1, 0.05F));
			genericTrades.add(new BasicItemListing(new ItemStack(ModItems.OGRE_HORN.get(), 1), new ItemStack(Items.EMERALD, 3), 32, 1, 0.05F));
			genericTrades.add(new BasicItemListing(2, new ItemStack(ModItems.LEMON.get()), 5, 1, 1.0F));
			genericTrades.add(new BasicItemListing(5, new ItemStack(ModItems.FIRE_BOTTLE.get()), 8, 1, 1.0F));
			rareTrades.add(new BasicItemListing(32, new ItemStack(ModItems.LIGHTNING_PARTICLE.get()), 5, 1, 0.05F));
			rareTrades.add(new BasicItemListing(1, new ItemStack(ModItems.SOUL_POWDER.get()), 8, 1, 0.05F));
			rareTrades.add(new BasicItemListing(64, new ItemStack(ModItems.LIGHTNING_PARTICLE.get()), 5, 1, 0.05F));
			rareTrades.add(new BasicItemListing(32, new ItemStack(ModItems.PURIFICATION_CLOTH.get()), 3, 1, 0.05F));
		}
	}
}