package com.github.mechalopa.hmag.world.entity.projectile;

import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.registry.ModParticleTypes;
import com.github.mechalopa.hmag.world.entity.LichEntity;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PlayMessages;

public class MagicBulletEntity extends ModDamagingProjectileEntity
{
	private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(MagicBulletEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Byte> EFFECT_LEVEL = SynchedEntityData.defineId(MagicBulletEntity.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> PIERCE_LEVEL = SynchedEntityData.defineId(MagicBulletEntity.class, EntityDataSerializers.BYTE);
	private IntOpenHashSet piercingIgnoreEntityIds;

	public MagicBulletEntity(EntityType<? extends MagicBulletEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}

	public MagicBulletEntity(Level worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ)
	{
		super(ModEntityTypes.MAGIC_BULLET.get(), shooter, accelX, accelY, accelZ, worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	public MagicBulletEntity(Level worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
	{
		super(ModEntityTypes.MAGIC_BULLET.get(), x, y, z, accelX, accelY, accelZ, worldIn);
	}

	public MagicBulletEntity(PlayMessages.SpawnEntity spawnEntity, Level level)
	{
		this(ModEntityTypes.MAGIC_BULLET.get(), level);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_VARIANT_ID, 0);
		this.entityData.define(EFFECT_LEVEL, (byte)1);
		this.entityData.define(PIERCE_LEVEL, (byte)0);
	}

	@Override
	protected float getInertia()
	{
		switch (this.getVariant())
		{
		case 1:
			return 0.87F;
		case 2:
			return 0.98F;
		case 0:
		default:
			return 0.9F;
		}
	}

	@Override
	protected void tick2()
	{
		if (this.getVariant() == 2)
		{
			if (!this.level.isClientSide && (this.tickCount >= 60 || this.getOwner() == null))
			{
				this.discard();
			}

			if (this.level.isClientSide)
			{
				this.level.addParticle(ModParticleTypes.NEMESIS_FLAME.get(), this.getRandomX(0.5D), this.getY(this.random.nextDouble() * 0.5D), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
			}
		}
		else
		{
			super.tick2();
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result)
	{
		super.onHitEntity(result);

		Entity entity = result.getEntity();
		Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize();

		if (this.getPierceLevel() > 0)
		{
			if (this.piercingIgnoreEntityIds == null)
			{
				this.piercingIgnoreEntityIds = new IntOpenHashSet(5);
			}

			if (this.piercingIgnoreEntityIds.size() < this.getPierceLevel() + 1)
			{
				this.piercingIgnoreEntityIds.add(entity.getId());
			}
		}

		if (!this.level.isClientSide)
		{
			Entity entity1 = this.getOwner();
			float damage = this.getDamage();
			boolean flag;

			if (entity1 != null && entity1 instanceof LivingEntity)
			{
				LivingEntity livingentity = (LivingEntity)entity1;

				if (this.getVariant() == 0)
				{
					if (livingentity instanceof LichEntity && entity instanceof Vex && livingentity.isAlliedTo((entity)))
					{
						damage *= 5.0F;
					}
				}

				flag = entity.hurt(DamageSource.indirectMagic(this, livingentity), damage);

				if (flag)
				{
					if (entity.isAlive())
					{
						this.doEnchantDamageEffects(livingentity, entity);
					}
				}
			}
			else
			{
				flag = entity.hurt(DamageSource.MAGIC, damage);
			}

			if (flag && entity instanceof LivingEntity && this.getEffectLevel() > 0)
			{
				int i = 0;

				switch (this.getVariant())
				{
				case 0:
					if (this.level.getDifficulty() == Difficulty.NORMAL)
					{
						i = 7;
					}
					else if (this.level.getDifficulty() == Difficulty.HARD)
					{
						i = 15;
					}

					if (i > 0)
					{
						((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * i, 0));
					}

					break;
				case 1:
					if (this.level.getDifficulty() == Difficulty.NORMAL)
					{
						i = 5;
					}
					else if (this.level.getDifficulty() == Difficulty.HARD)
					{
						i = 10;
					}

					if (i > 0)
					{
						((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20 * i, 0));
					}

					break;
				case 2:
					i = (this.getEffectLevel() * 2 + 4) * 20;
					((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, i, 1));
					((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, i, 1));
					((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, i, 1));
					((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.WITHER, i, 1));

					if (vec3.lengthSqr() > 0.0D)
					{
						((LivingEntity)entity).knockback(1.0F * (this.getEffectLevel() / 12.0F) + 0.1F, -vec3.x, -vec3.z);
					}

					break;
				}
			}

			this.level.broadcastEntityEvent(this, (byte)3);
		}
	}

	@Override
	protected boolean canHitEntity(Entity entityIn)
	{
		return super.canHitEntity(entityIn) && (this.getOwner() != null && !this.getOwner().equals(entityIn)) && (this.piercingIgnoreEntityIds == null || !this.piercingIgnoreEntityIds.contains(entityIn.getId()));
	}

	@Override
	protected void onHitServer(HitResult result)
	{
		if (result.getType() != HitResult.Type.ENTITY || this.getPierceLevel() <= 0 || this.piercingIgnoreEntityIds.size() >= this.getPierceLevel() + 1)
		{
			this.discard();
		}
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> data)
	{
		if (DATA_VARIANT_ID.equals(data))
		{
			this.refreshDimensions();
		}

		super.onSyncedDataUpdated(data);
	}

	@Override
	public EntityDimensions getDimensions(Pose pose)
	{
		if (this.getVariant() == 2)
		{
			return super.getDimensions(pose).scale(1.6F);
		}
		else
		{
			return super.getDimensions(pose);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 3)
		{
			if (this.getVariant() == 2)
			{
				for (int i = 0; i < 12; ++i)
				{
					this.level.addParticle(ModParticleTypes.NEMESIS_FLAME.get(), this.getRandomX(1.75D), this.getY(this.random.nextDouble() * 1.75D), this.getRandomZ(1.75D), 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}

	@Override
	public boolean isInWater()
	{
		return false;
	}

	public int getVariant()
	{
		return this.entityData.get(DATA_VARIANT_ID);
	}

	public void setVariant(int typeIn)
	{
		if (typeIn < 0 || typeIn >= 3)
		{
			typeIn = 0;
		}

		this.entityData.set(DATA_VARIANT_ID, typeIn);
	}

	public void setPierceLevel(byte amount)
	{
		this.entityData.set(PIERCE_LEVEL, amount);
	}

	public byte getPierceLevel()
	{
		return this.entityData.get(PIERCE_LEVEL);
	}

	public void setEffectLevel(byte amount)
	{
		this.entityData.set(EFFECT_LEVEL, amount);
	}

	public byte getEffectLevel()
	{
		return this.entityData.get(EFFECT_LEVEL);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Variant", this.getVariant());
		compound.putByte("PierceLevel", this.getPierceLevel());
		compound.putByte("EffectLevel", this.getEffectLevel());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getInt("Variant"));
		this.setPierceLevel(compound.getByte("PierceLevel"));
		this.setEffectLevel(compound.getByte("EffectLevel"));
	}

	@Override
	protected ParticleOptions getTrailParticle()
	{
		switch (this.getVariant())
		{
		case 1:
			return ParticleTypes.LARGE_SMOKE;
		case 2:
			return ParticleTypes.SMOKE;
		case 0:
		default:
			return ParticleTypes.DRAGON_BREATH;
		}
	}
}