package com.github.mechalopa.hmag.world.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.ModConfigs;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.network.NetworkHooks;

public class EnderExecutorEntity extends EnderMan implements IModMob, IBeamAttackMob
{
	private static final EntityDataAccessor<Integer> ATTACKING_TIME = SynchedEntityData.defineId(EnderExecutorEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> ATTACK_TARGET = SynchedEntityData.defineId(EnderExecutorEntity.class, EntityDataSerializers.INT);
	private LivingEntity targetedEntity;
	private int clientAttackTime;

	public EnderExecutorEntity(EntityType<? extends EnderExecutorEntity> type, Level worldIn)
	{
		super(type, worldIn);
		this.xpReward = 25;
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ATTACK_TARGET, 0);
		this.entityData.define(ATTACKING_TIME, -20);
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return EnderMan.createAttributes()
				.add(Attributes.MAX_HEALTH, 120.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.ATTACK_DAMAGE, 8.0D)
				.add(Attributes.ARMOR, 4.0D);
	}

	@Override
	public void aiStep()
	{
		if (!this.level.isClientSide)
		{
			if (this.isAlive() && !this.isNoAi() && this.level.getDifficulty().getId() > 1 && ModConfigs.cachedServer.ENDER_EXECUTOR_BEAM_ATTACK)
			{
				LivingEntity target = this.getTarget();

				if (target != null && target.isAlive())
				{
					double d0 = this.distanceToSqr(target);

					if (this.hasLineOfSight(target) && d0 > 1.0D * 1.0D && d0 <= 24.0D * 24.0D)
					{
						int i = this.getAttackingTime();
						++i;

						if (i == 0)
						{
							this.setAttackingTime(i);
							this.setActiveAttackTarget(target.getId());
						}
						else if (i >= this.getAttackDuration())
						{
							if (this.getActiveAttackTarget() != null)
							{
								if (this.attackEntityWithBeamAttack(this.getActiveAttackTarget(), 6.0F) && this.random.nextInt(10) == 0)
								{
									this.teleport();
								}
							}

							this.setAttackingTime(-(10 + this.random.nextInt(6)));
							this.setActiveAttackTarget(0);
						}
						else
						{
							this.setAttackingTime(i);
						}
					}
					else
					{
						this.setAttackingTime(-20);
						this.setActiveAttackTarget(0);
					}
				}
				else
				{
					this.setAttackingTime(-20);
				}
			}
		}
		else
		{
			if (this.isAlive() && this.hasActiveAttackTarget())
			{
				if (this.clientAttackTime < this.getAttackDuration())
				{
					++this.clientAttackTime;
				}

				LivingEntity target = this.getActiveAttackTarget();

				if (target != null)
				{
					this.getLookControl().setLookAt(target, 90.0F, 90.0F);
					this.getLookControl().tick();
				}
			}
			else
			{
				this.clientAttackTime = 0;
			}
		}

		super.aiStep();
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (this.isInvulnerableTo(source))
		{
			return false;
		}
		else
		{
			float f = amount;

			if (ModConfigs.cachedServer.ENDER_EXECUTOR_REDUCE_DAMAGE)
			{
				if (!(source.getEntity() != null && source.isCreativePlayer()) && source != DamageSource.OUT_OF_WORLD && f > 10.0F)
				{
					if (this.level.getDifficulty() == Difficulty.NORMAL)
					{
						f = 10.0F + (f - 10.0F) * 0.25F;
					}
					else if (this.level.getDifficulty() == Difficulty.HARD)
					{
						f = 10.0F + (f - 10.0F) * 0.1F;
					}
				}
			}

			if (source instanceof IndirectEntityDamageSource)
			{
				for (int i = 0; i < 64; ++i)
				{
					if (this.teleport())
					{
						return true;
					}
				}

				return false;
			}
			else
			{
				return super.hurt(source, f);
			}
		}
	}

	@Override
	public void setTarget(@Nullable LivingEntity entitylivingbaseIn)
	{
		if (entitylivingbaseIn == null)
		{
			this.setActiveAttackTarget(0);
		}

		super.setTarget(entitylivingbaseIn);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType spawnType, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag)
	{
		SpawnGroupData spawngroupdata = super.finalizeSpawn(worldIn, difficultyIn, spawnType, spawnDataIn, dataTag);

		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));

		return spawngroupdata;
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
	{
		super.populateDefaultEquipmentSlots(difficulty);

		if (this.getRandom().nextFloat() < 0.05F)
		{
		}
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 1;
	}

	public int getAttackingTime()
	{
		return this.entityData.get(ATTACKING_TIME);
	}

	private void setAttackingTime(int value)
	{
		this.entityData.set(ATTACKING_TIME, value);
	}

	@Override
	public boolean randomTeleport(double x, double y, double z, boolean flag)
	{
		if (super.randomTeleport(x, y, z, flag))
		{
			if (this.hasActiveAttackTarget())
			{
				this.setActiveAttackTarget(0);
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key)
	{
		super.onSyncedDataUpdated(key);

		if (ATTACK_TARGET.equals(key))
		{
			this.clientAttackTime = 0;
			this.targetedEntity = null;
		}
	}

	@Override
	public int getAttackDuration()
	{
		return 40;
	}

	@Override
	public boolean attackEntityWithBeamAttack(LivingEntity target, float damage)
	{
		if (!this.isSilent())
		{
			this.level.playSound((Player)null, target.getX(), target.getY(), target.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.2F + 0.9F);
		}

		float f = damage;

		if (this.level.getDifficulty() == Difficulty.HARD)
		{
			f += 2.0F;
		}

		return target.hurt(DamageSource.indirectMagic(this, this), f);
	}

	@Override
	public void setActiveAttackTarget(int entityId)
	{
		this.entityData.set(ATTACK_TARGET, entityId);
	}

	@Override
	public boolean hasActiveAttackTarget()
	{
		return this.entityData.get(ATTACK_TARGET) != 0;
	}

	@Nullable
	@Override
	public LivingEntity getActiveAttackTarget()
	{
		if (!this.hasActiveAttackTarget())
		{
			return null;
		}
		else if (this.level.isClientSide)
		{
			if (this.targetedEntity != null)
			{
				return this.targetedEntity;
			}
			else
			{
				Entity entity = this.level.getEntity(this.entityData.get(ATTACK_TARGET));

				if (entity instanceof LivingEntity)
				{
					this.targetedEntity = (LivingEntity)entity;
					return this.targetedEntity;
				}
				else
				{
					return null;
				}
			}
		}
		else
		{
			return this.getTarget();
		}
	}

	@Override
	public float getAttackAnimationScale(float f)
	{
		return (this.clientAttackTime + f) / this.getAttackDuration();
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}