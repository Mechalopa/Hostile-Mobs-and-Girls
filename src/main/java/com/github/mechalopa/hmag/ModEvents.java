package com.github.mechalopa.hmag;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.github.mechalopa.hmag.entity.IModMob;
import com.github.mechalopa.hmag.entity.KashaEntity;
import com.github.mechalopa.hmag.item.ModArmorMaterial;
import com.github.mechalopa.hmag.registry.ModEffects;
import com.github.mechalopa.hmag.registry.ModEnchantments;
import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.registry.ModItems;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.util.ModUtils;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerEntity.SleepResult;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.BasicTrade;
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
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents
{
	private static final UUID[] HEALTH_BOOST_ENCHANTMENT_MAX_HEALTH_UUIDS = new UUID[]{UUID.fromString("EB931A99-0CF2-6E81-DC0C-9DC22573CCDA"), UUID.fromString("16515E9A-B529-151F-EF1E-FA0B55CB3044"), UUID.fromString("F31C89C1-E8F7-EFED-89E4-8C14E796C09A"), UUID.fromString("036BEDC4-2459-9224-5E89-909F95F799EB")};
	private static final UUID[] NECROTIC_CHAINMAIL_ARMOR_ATTACK_SPEED_UUIDS = new UUID[]{UUID.fromString("81D1E394-8B33-AC3D-325A-C8A03E757B51"), UUID.fromString("B4EFB0CA-2180-0043-0FF9-BE033CC510D5"), UUID.fromString("7FD9FBE7-0438-BF0F-F901-F961384F9591"), UUID.fromString("28E5C0B5-109B-339B-BE83-610B5B399256")};
	private static final UUID INSOMNIA_SWORD_ATTACK_DAMAGE_UUID = UUID.fromString("04C38766-C1A5-31D4-410B-C6B4BE3B7DD2");
	private static final UUID NEMESIS_BLADE_ATTACK_DAMAGE_UUID = UUID.fromString("FD835C41-1211-AC72-0CD4-66F3061FB156");
	private static final UUID NEMESIS_BLADE_ATTACK_SPEED_UUID = UUID.fromString("1911699B-779B-820E-064A-112D1EB232F7");
	private static final UUID NEMESIS_BLADE_MOVEMENT_SPEED_UUID = UUID.fromString("C80A68DD-F985-4245-4EC4-64884C8EBA4C");
	private static final AttributeModifier NEMESIS_BLADE_MOVEMENT_SPEED_MODIFIER = new AttributeModifier(NEMESIS_BLADE_MOVEMENT_SPEED_UUID, "Nemesis blade move speed penalty", (double)-0.5F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	private static final UUID ANCIENT_SHIELD_KNOCKBACK_RESISTANCE_UUID = UUID.fromString("0915B1C7-492D-2776-EFF2-436BF1072692");
	private static final AttributeModifier ANCIENT_SHIELD_KNOCKBACK_RESISTANCE_MODIFIER = new AttributeModifier(ANCIENT_SHIELD_KNOCKBACK_RESISTANCE_UUID, "Ancient shield knockback resistance bonus", (double)0.05F, AttributeModifier.Operation.ADDITION);
	private static final ITextComponent INSOMNIA_SWORD_MESSAGE = new TranslationTextComponent("message.hmag.insomnia_sword");

	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if (event.isCanceled())
		{
			return;
		}

		if (event.getEntityLiving() != null && event.getSource() != null && !event.getSource().isProjectile())
		{
			LivingEntity livingentity = event.getEntityLiving();
			DamageSource source = event.getSource();

			if (source.getEntity() != null && source.getEntity() instanceof LivingEntity)
			{
				LivingEntity attacker = (LivingEntity)source.getEntity();
				ItemStack stack = attacker.getMainHandItem();

				if (!stack.isEmpty())
				{
					if (stack.hasTag())
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
								event.setAmount(event.getAmount() + ((float)level * 2.5F));
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
					if (livingentity.getRandom().nextInt(5) == 0)
					{
						final int i = livingentity.getRandom().nextInt(3);
						((LivingEntity)source.getEntity()).addEffect(new EffectInstance(i == 2 ? Effects.WEAKNESS : (i == 1 ? Effects.MOVEMENT_SLOWDOWN : Effects.DIG_SLOWDOWN), 5 * 20, 0));
					}
				}
				else if (livingentity.getUseItem().getItem() == ModItems.FORTRESS_SHIELD.get())
				{
					if (livingentity.getRandom().nextInt(5) == 0)
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

			if (event.getEntityLiving() instanceof MobEntity)
			{
				MobEntity mobentity = (MobEntity)event.getEntityLiving();

				if (mobentity.getPersistentData().getBoolean(ModUtils.WITH_SPAWN_PARTICLE_KEY))
				{
					mobentity.spawnAnim();
					mobentity.getPersistentData().remove(ModUtils.WITH_SPAWN_PARTICLE_KEY);
				}

				if (mobentity.isEffectiveAi() && mobentity.getPersistentData().getBoolean(ModUtils.LIVING_UPDATE_CHECKING_KEY) && !mobentity.getPersistentData().getBoolean(ModUtils.LIVING_NOT_REPLACED_KEY) && !mobentity.isPersistenceRequired() && !mobentity.requiresCustomPersistence() && !mobentity.isVehicle() && !mobentity.isPassenger())
				{
					ServerWorld serverworld = (ServerWorld)event.getEntityLiving().getCommandSenderWorld();
					final double d = event.getEntityLiving().getRandom().nextDouble();

					if (ModConfigs.cachedServer.ZOMBIE_GIRL_REPLACE_CHANCE > d && ModTags.checkTagContains(ModTags.ZOMBIE_GIRL_REPLACEABLES, mobentity.getType()))
					{
						replace(serverworld, mobentity, ModEntityTypes.ZOMBIE_GIRL.get());
					}
					else if (ModConfigs.cachedServer.HUSK_GIRL_REPLACE_CHANCE > d && ModTags.checkTagContains(ModTags.HUSK_GIRL_REPLACEABLES, mobentity.getType()))
					{
						replace(serverworld, mobentity, ModEntityTypes.HUSK_GIRL.get());
					}
					else if (ModConfigs.cachedServer.DROWNED_GIRL_REPLACE_CHANCE > d && ModTags.checkTagContains(ModTags.DROWNED_GIRL_REPLACEABLES, mobentity.getType()))
					{
						replace(serverworld, mobentity, ModEntityTypes.DROWNED_GIRL.get());
					}
					else if (ModConfigs.cachedServer.SKELETON_GIRL_REPLACE_CHANCE > d && ModTags.checkTagContains(ModTags.SKELETON_GIRL_REPLACEABLES, mobentity.getType()))
					{
						replace(serverworld, mobentity, ModEntityTypes.SKELETON_GIRL.get());
					}
					else if (ModConfigs.cachedServer.WITHER_SKELETON_GIRL_REPLACE_CHANCE > d && ModTags.checkTagContains(ModTags.WITHER_SKELETON_GIRL_REPLACEABLES, mobentity.getType()))
					{
						replace(serverworld, mobentity, ModEntityTypes.WITHER_SKELETON_GIRL.get());
					}
					else if (ModConfigs.cachedServer.STRAY_GIRL_REPLACE_CHANCE > d && ModTags.checkTagContains(ModTags.STRAY_GIRL_REPLACEABLES, mobentity.getType()))
					{
						replace(serverworld, mobentity, ModEntityTypes.STRAY_GIRL.get());
					}
					else if (ModConfigs.cachedServer.CREEPER_GIRL_REPLACE_CHANCE > d && ModTags.checkTagContains(ModTags.CREEPER_GIRL_REPLACEABLES, mobentity.getType()))
					{
						replace(serverworld, mobentity, ModEntityTypes.CREEPER_GIRL.get());
					}
					else if (ModConfigs.cachedServer.ENDER_EXECUTOR_REPLACE_CHANCE > d && ModTags.checkTagContains(ModTags.ENDER_EXECUTOR_REPLACEABLES, mobentity.getType()))
					{
						replace(serverworld, mobentity, ModEntityTypes.ENDER_EXECUTOR.get());
					}
				}
			}
		}
	}

	private static boolean replace(ServerWorld worldIn, MobEntity mobEntityIn, EntityType<?> type)
	{
		if (mobEntityIn.getType().equals(type))
		{
			return false;
		}

		MobEntity mobentity = createMob(worldIn, type);

		if (mobentity == null)
		{
			return false;
		}

		ILivingEntityData ilivingentitydata = null;
		mobentity.copyPosition(mobEntityIn);
		ilivingentitydata = mobentity.finalizeSpawn(worldIn, worldIn.getCurrentDifficultyAt(new BlockPos(mobEntityIn.position())), SpawnReason.NATURAL, ilivingentitydata, (CompoundNBT)null);
		mobentity.setNoAi(mobEntityIn.isNoAi());

		if (mobEntityIn.hasCustomName())
		{
			mobentity.setCustomName(mobEntityIn.getCustomName());
			mobentity.setCustomNameVisible(mobEntityIn.isCustomNameVisible());
		}

		mobentity.getPersistentData().putBoolean(ModUtils.LIVING_NOT_REPLACED_KEY, true);
		worldIn.addFreshEntityWithPassengers(mobentity);
		mobEntityIn.remove();
		return true;
	}

	@Nullable
	private static MobEntity createMob(ServerWorld worldIn, EntityType<?> type)
	{
		try
		{
			Entity entity = type.create(worldIn);

			if (!(entity instanceof MobEntity))
			{
				throw new IllegalStateException("Trying to spawn a non-mob: " + ForgeRegistries.ENTITIES.getKey(type));
			}
			else
			{
				return (MobEntity)entity;
			}
		}
		catch (Exception e)
		{
			HMaG.LOGGER.warn("Failed to create hmag mob", (Throwable)e);
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

		if (event.getSpawnReason() == SpawnReason.NATURAL && !ModUtils.checkDimensionList(event.getEntityLiving().getCommandSenderWorld(), ModConfigs.cachedServer.MOB_REPLACE_DIMENSION_BLACKLIST) && !ModUtils.checkBiomeList(event.getEntityLiving().getCommandSenderWorld(), event.getEntityLiving().blockPosition(), ModConfigs.cachedServer.MOB_REPLACE_BIOME_BLACKLIST))
		{
			LivingEntity livingentity = event.getEntityLiving();

			if (ModConfigs.cachedServer.ZOMBIE_GIRL_REPLACE_CHANCE > 0.0D && ModTags.checkTagContains(ModTags.ZOMBIE_GIRL_REPLACEABLES, livingentity.getType()))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.HUSK_GIRL_REPLACE_CHANCE > 0.0D && ModTags.checkTagContains(ModTags.HUSK_GIRL_REPLACEABLES, livingentity.getType()))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.DROWNED_GIRL_REPLACE_CHANCE > 0.0D && ModTags.checkTagContains(ModTags.DROWNED_GIRL_REPLACEABLES, livingentity.getType()))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.SKELETON_GIRL_REPLACE_CHANCE > 0.0D && ModTags.checkTagContains(ModTags.SKELETON_GIRL_REPLACEABLES, livingentity.getType()))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.WITHER_SKELETON_GIRL_REPLACE_CHANCE > 0.0D && ModTags.checkTagContains(ModTags.WITHER_SKELETON_GIRL_REPLACEABLES, livingentity.getType()))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.STRAY_GIRL_REPLACE_CHANCE > 0.0D && ModTags.checkTagContains(ModTags.STRAY_GIRL_REPLACEABLES, livingentity.getType()))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.CREEPER_GIRL_REPLACE_CHANCE > 0.0D && ModTags.checkTagContains(ModTags.CREEPER_GIRL_REPLACEABLES, livingentity.getType()))
			{
				putCheckingTag(livingentity);
			}
			else if (ModConfigs.cachedServer.ENDER_EXECUTOR_REPLACE_CHANCE > 0.0D && ModTags.checkTagContains(ModTags.ENDER_EXECUTOR_REPLACEABLES, livingentity.getType()))
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
				if (event.getEntity() instanceof EndermanEntity)
				{
					EndermanEntity endermanentity = (EndermanEntity)event.getEntity();
					endermanentity.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(endermanentity, LivingEntity.class, 10, false, false, (p) -> {
						if (!(p instanceof EndermanEntity) && p.hasEffect(ModEffects.ENDER_RAGE.get()))
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
				else if (event.getEntity() instanceof CreeperEntity)
				{
					CreeperEntity creeerentity = (CreeperEntity)event.getEntity();
					creeerentity.goalSelector.addGoal(3, new AvoidEntityGoal<>(creeerentity, KashaEntity.class, 6.0F, 1.0D, 1.2D));
				}
			}
		}
		catch (Exception e)
		{
			HMaG.LOGGER.warn("Failed to add goals to mobs", (Throwable)e);
		}
	}

	@SubscribeEvent
	public void onPotionApplicable(PotionApplicableEvent event)
	{
		if (event.getEntityLiving() != null && event.getPotionEffect() != null)
		{
			LivingEntity livingentity = event.getEntityLiving();
			Effect effect = event.getPotionEffect().getEffect();

			if (effect == Effects.MOVEMENT_SLOWDOWN || effect == Effects.DIG_SLOWDOWN || effect == Effects.WEAKNESS)
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

				if (ModUtils.matchItemBothHands(livingentity, ModItems.ANCIENT_SHIELD.get()))
				{
					++i;
				}

				if (i >= 4 || i > event.getPotionEffect().getAmplifier() + 1)
				{
					event.setResult(Result.DENY);
				}
			}
			else if (effect == Effects.BLINDNESS)
			{
				if (livingentity instanceof PlayerEntity)
				{
					ItemStack stack1 = livingentity.getMainHandItem();

					if (!stack1.isEmpty() && stack1.getItem() == ModItems.INSOMNIA_SWORD.get() && ModUtils.getLevel(stack1) > 0)
					{
						event.setResult(Result.DENY);
					}
				}
			}
			else if (effect == ModEffects.COMBUSTION.get())
			{
				if (livingentity.fireImmune() || ModUtils.matchItemBothHands(livingentity, ModItems.FORTRESS_SHIELD.get()))
				{
					event.setResult(Result.DENY);
				}
			}
			else if (effect == ModEffects.ENDER_RAGE.get())
			{
				if (livingentity instanceof EndermanEntity)
				{
					event.setResult(Result.DENY);
				}
			}
		}
	}

	@SubscribeEvent
	public void onItemAttributeModifier(ItemAttributeModifierEvent event)
	{
		if (!event.getItemStack().isEmpty() && event.getItemStack().getItem() != null)
		{
			ItemStack stack = event.getItemStack();

			if (event.getSlotType() == MobEntity.getEquipmentSlotForItem(stack))
			{
				final int i = event.getSlotType().getIndex();

				if (event.getSlotType().getType() == EquipmentSlotType.Group.ARMOR && i >= 0 && i < HEALTH_BOOST_ENCHANTMENT_MAX_HEALTH_UUIDS.length)
				{
					int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.HEALTH_BOOST.get(), stack);

					if (level > 0)
					{
						event.addModifier(Attributes.MAX_HEALTH, new AttributeModifier(HEALTH_BOOST_ENCHANTMENT_MAX_HEALTH_UUIDS[i], "Health boost enchantment " + event.getSlotType().getName() + " max health bonus", (double)((float)level * 1.0F), AttributeModifier.Operation.ADDITION));
					}
				}

				if (stack.getItem() instanceof ArmorItem && ((ArmorItem)stack.getItem()).getMaterial() == ModArmorMaterial.NECROTIC_CHAIN)
				{
					event.addModifier(Attributes.ATTACK_SPEED, new AttributeModifier(NECROTIC_CHAINMAIL_ARMOR_ATTACK_SPEED_UUIDS[i], "Necrotic chainmail armor " + event.getSlotType().getName() + " attack speed bonus", (double)0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL));
				}
				else if (stack.getItem() == ModItems.INSOMNIA_SWORD.get())
				{
					final int level1 = ModUtils.getLevel(stack);

					if (level1 > 0)
					{
						event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(INSOMNIA_SWORD_ATTACK_DAMAGE_UUID, "Insomnia sword attack damage bonus", (double)((float)level1 * 1.0F + 1.0F), AttributeModifier.Operation.ADDITION));
					}
				}
				else if (stack.getItem() == ModItems.NEMESIS_BLADE.get())
				{
					final int level2 = ModUtils.getLevel(stack);

					if (level2 > 0)
					{
						event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(NEMESIS_BLADE_ATTACK_DAMAGE_UUID, "Nemesis blade attack damage bonus", (double)((float)level2 * 1.0F), AttributeModifier.Operation.ADDITION));
						event.addModifier(Attributes.ATTACK_SPEED, new AttributeModifier(NEMESIS_BLADE_ATTACK_SPEED_UUID, "Nemesis blade attack speed bonus", (double)((float)level2 * 0.25F), AttributeModifier.Operation.ADDITION));
					}
					else
					{
						event.addModifier(Attributes.MOVEMENT_SPEED, NEMESIS_BLADE_MOVEMENT_SPEED_MODIFIER);
					}
				}
			}

			if (event.getSlotType().getType() == EquipmentSlotType.Group.HAND)
			{
				if (stack.getItem() == ModItems.ANCIENT_SHIELD.get())
				{
					event.addModifier(Attributes.KNOCKBACK_RESISTANCE, ANCIENT_SHIELD_KNOCKBACK_RESISTANCE_MODIFIER);
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerSleep(PlayerSleepInBedEvent event)
	{
		if (event.getResultStatus() == null)
		{
			if (event.getPlayer().inventory.contains(new ItemStack(ModItems.INSOMNIA_SWORD.get())))
			{
				event.setResult(SleepResult.OTHER_PROBLEM);
				event.getPlayer().displayClientMessage(INSOMNIA_SWORD_MESSAGE, true);
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

		if (event.getEntityItem() != null && event.getEntityItem().getItem() != null && !event.getEntityItem().getItem().isEmpty())
		{
			if (event.getEntityItem().getItem().getItem() == ModItems.INSOMNIA_SWORD.get())
			{
				ModUtils.removeLevelTag(event.getEntityItem().getItem());
			}
			else if (event.getEntityItem().getItem().getItem() == ModItems.NEMESIS_BLADE.get())
			{
				ModUtils.removeLevelTag(event.getEntityItem().getItem());
			}
		}
	}

	@SubscribeEvent
	public void onLivingEquipmentChange(LivingEquipmentChangeEvent event)
	{
		if (event.getEntityLiving() != null && !(event.getEntityLiving() instanceof PlayerEntity) && event.getTo() != null && !event.getTo().isEmpty())
		{
			if (event.getTo().getItem() == ModItems.INSOMNIA_SWORD.get())
			{
				ModUtils.removeLevelTag(event.getTo());
			}
			else if (event.getTo().getItem() == ModItems.NEMESIS_BLADE.get())
			{
				ModUtils.removeLevelTag(event.getTo());
			}
		}
	}

	@SubscribeEvent
	public void onStructureSpawn(StructureSpawnListGatherEvent event)
	{
		if (ModConfigs.cachedServer.MONOLITH_SPAWN_WEIGHT_IN_END_CITY > 0)
		{
			if (event.getStructure() == Structure.END_CITY)
			{
				event.addEntitySpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntityTypes.MONOLITH.get(), ModConfigs.cachedServer.MONOLITH_SPAWN_WEIGHT_IN_END_CITY, 1, 2));
			}
		}

		if (ModConfigs.cachedServer.FORTRESS_KEEPER_SPAWN_WEIGHT_IN_NETHER_FORTRESS > 0)
		{
			if (event.getStructure() == Structure.NETHER_BRIDGE)
			{
				event.addEntitySpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntityTypes.FORTRESS_KEEPER.get(), ModConfigs.cachedServer.FORTRESS_KEEPER_SPAWN_WEIGHT_IN_NETHER_FORTRESS, 1, 1));
			}
		}
	}

	@SubscribeEvent
	public void onVillagerTrades(VillagerTradesEvent event)
	{
		if (ModConfigs.cachedServer.ADDITIONAL_VILLAGER_TRADES && event.getType() != null)
		{
			Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

			if (event.getType() == VillagerProfession.CLERIC)
			{
				trades.get(2).add(new BasicTrade(new ItemStack(ModItems.SOUL_POWDER.get(), 24), new ItemStack(Items.EMERALD, 1), 16, 2, 0.05F));
				trades.get(4).add(new BasicTrade(new ItemStack(ModItems.EXP_BERRY.get(), 3), new ItemStack(Items.EMERALD, 1), 16, 15, 0.05F));
				trades.get(4).add(new BasicTrade(new ItemStack(ModItems.SPECTRAL_SOUP.get(), 1), new ItemStack(Items.EMERALD, 1), 16, 15, 0.05F));
			}

			if (event.getType() == VillagerProfession.LEATHERWORKER)
			{
				trades.get(3).add(new BasicTrade(new ItemStack(ModItems.BAT_WING.get(), 4), new ItemStack(Items.EMERALD, 1), 16, 15, 0.05F));

				if (ModConfigs.cachedServer.KOBOLD_SPAWN_WEIGHT > 0)
				{
					trades.get(4).add(new BasicTrade(new ItemStack(ModItems.KOBOLD_LEATHER.get(), 4), new ItemStack(Items.EMERALD, 1), 12, 20, 0.05F));
				}
			}

			if (event.getType() == VillagerProfession.FARMER)
			{
				trades.get(2).add(new BasicTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.BLUEBERRY.get(), 4), 12, 4, 0.05F));
				trades.get(2).add(new BasicTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.STRAWBERRY.get(), 4), 12, 4, 0.05F));
				trades.get(2).add(new BasicTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.LEMON.get(), 4), 12, 4, 0.05F));
			}

			if (event.getType() == VillagerProfession.FISHERMAN)
			{
				if (ModConfigs.cachedServer.SAVAGEFANG_SPAWN_WEIGHT_IN_SWAMP > 0 || ModConfigs.cachedServer.SAVAGEFANG_SPAWN_WEIGHT_IN_JUNGLE > 0)
				{
					trades.get(4).add(new BasicTrade(new ItemStack(ModItems.SAVAGEFANG_MEAT.get(), 3), new ItemStack(Items.EMERALD, 1), 12, 20, 0.05F));
				}
			}
		}
	}

	@SubscribeEvent
	public void onWandererTrades(WandererTradesEvent event)
	{
		if (ModConfigs.cachedServer.ADDITIONAL_WANDERER_TRADES)
		{
			List<VillagerTrades.ITrade> genericTrades = event.getGenericTrades();
			List<VillagerTrades.ITrade> rareTrades = event.getRareTrades();

			if (ModConfigs.cachedServer.ENDER_EXECUTOR_REPLACE_CHANCE > 0.0D)
			{
				genericTrades.add(new BasicTrade(new ItemStack(ModItems.ENDER_PLASM.get(), 1), new ItemStack(Items.EMERALD, 2), 32, 1, 0.05F));
			}

			genericTrades.add(new BasicTrade(new ItemStack(ModItems.ANCIENT_STONE.get(), 2), new ItemStack(Items.EMERALD, 1), 32, 1, 0.05F));
			genericTrades.add(new BasicTrade(5, new ItemStack(ModItems.FIRE_BOTTLE.get()), 8, 1, 1.0F));
			rareTrades.add(new BasicTrade(32, new ItemStack(ModItems.LIGHTNING_PARTICLE.get()), 5, 1, 0.05F));
			rareTrades.add(new BasicTrade(32, new ItemStack(ModItems.PURIFICATION_CLOTH.get()), 3, 1, 0.05F));
		}
	}
}