package com.github.mechalopa.hmag.world.entity.projectile;

import java.util.function.IntFunction;

import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.registry.ModParticleTypes;
import com.github.mechalopa.hmag.world.entity.LichEntity;
import com.mojang.serialization.Codec;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PlayMessages;

public class MagicBulletEntity extends ModDamagingProjectileEntity implements VariantHolder<MagicBulletEntity.Variant>
{
	private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(MagicBulletEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Byte> EFFECT_LEVEL = SynchedEntityData.defineId(MagicBulletEntity.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> PIERCE_LEVEL = SynchedEntityData.defineId(MagicBulletEntity.class, EntityDataSerializers.BYTE);
	private IntOpenHashSet piercingIgnoreEntityIds;

	public MagicBulletEntity(EntityType<? extends MagicBulletEntity> type, Level level)
	{
		super(type, level);
	}

	public MagicBulletEntity(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ)
	{
		super(ModEntityTypes.MAGIC_BULLET.get(), shooter, accelX, accelY, accelZ, level);
	}

	@OnlyIn(Dist.CLIENT)
	public MagicBulletEntity(Level level, double x, double y, double z, double accelX, double accelY, double accelZ)
	{
		super(ModEntityTypes.MAGIC_BULLET.get(), x, y, z, accelX, accelY, accelZ, level);
	}

	public MagicBulletEntity(PlayMessages.SpawnEntity spawnEntity, Level level)
	{
		this(ModEntityTypes.MAGIC_BULLET.get(), level);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_VARIANT_ID, MagicBulletEntity.Variant.LICH.getId());
		this.entityData.define(EFFECT_LEVEL, (byte)1);
		this.entityData.define(PIERCE_LEVEL, (byte)0);
	}

	@Override
	protected float getInertia()
	{
		switch (this.getVariant())
		{
		case DYSSOMNIA:
			return 0.87F;
		case NEMESIS:
			return 0.98F;
		default:
			return 0.9F;
		}
	}

	@Override
	protected void tick2()
	{
		if (this.getVariant() == MagicBulletEntity.Variant.NEMESIS)
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

				if (this.getVariant() == MagicBulletEntity.Variant.LICH)
				{
					if (livingentity instanceof LichEntity && entity instanceof Vex && livingentity.isAlliedTo((entity)))
					{
						damage *= 5.0F;
					}
				}

				flag = entity.hurt(this.damageSources().indirectMagic(this, livingentity), damage);

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
				flag = entity.hurt(this.damageSources().magic(), damage);
			}

			if (flag && entity instanceof LivingEntity && this.getEffectLevel() > 0)
			{
				int i = 0;

				switch (this.getVariant())
				{
				case LICH:
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
				case DYSSOMNIA:
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
				case NEMESIS:
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
				case NIGHTWALKER:
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
						((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20 * i, 0));
					}

					break;
				}
			}

			this.level.broadcastEntityEvent(this, (byte)3);
		}
	}

	@Override
	protected boolean canHitEntity(Entity entity)
	{
		return super.canHitEntity(entity) && (this.getOwner() != null && !this.getOwner().equals(entity)) && (this.piercingIgnoreEntityIds == null || !this.piercingIgnoreEntityIds.contains(entity.getId()));
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
		if (this.getVariant() == MagicBulletEntity.Variant.NEMESIS)
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
			if (this.getVariant() == MagicBulletEntity.Variant.NEMESIS)
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

	@Override
	public MagicBulletEntity.Variant getVariant()
	{
		return MagicBulletEntity.Variant.byId(this.entityData.get(DATA_VARIANT_ID));
	}

	@Override
	public void setVariant(MagicBulletEntity.Variant variant)
	{
		this.entityData.set(DATA_VARIANT_ID, variant.getId());
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
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(MagicBulletEntity.Variant.byId(compound.getInt("Variant")));
		this.setPierceLevel(compound.getByte("PierceLevel"));
		this.setEffectLevel(compound.getByte("EffectLevel"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Variant", this.getVariant().getId());
		compound.putByte("PierceLevel", this.getPierceLevel());
		compound.putByte("EffectLevel", this.getEffectLevel());
	}

	@Override
	protected ParticleOptions getTrailParticle()
	{
		switch (this.getVariant())
		{
		case DYSSOMNIA:
			return ParticleTypes.LARGE_SMOKE;
		case NEMESIS:
			return ParticleTypes.SMOKE;
		case NIGHTWALKER:
			return ModParticleTypes.NIGHTWALKER_BULLET.get();
		default:
			return ParticleTypes.DRAGON_BREATH;
		}
	}

	public static enum Variant implements StringRepresentable
	{
		LICH(0, "lich"),
		DYSSOMNIA(1, "dyssomnia"),
		NEMESIS(2, "nemesis"),
		NIGHTWALKER(3, "nightwalker");

		private static final IntFunction<MagicBulletEntity.Variant> BY_ID = ByIdMap.continuous(MagicBulletEntity.Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
		public static final Codec<MagicBulletEntity.Variant> CODEC = StringRepresentable.fromEnum(MagicBulletEntity.Variant::values);
		private final int id;
		private final String name;

		private Variant(int id, String name)
		{
			this.id = id;
			this.name = name;
		}

		@Override
		public String getSerializedName()
		{
			return this.name;
		}

		public int getId()
		{
			return this.id;
		}

		public static MagicBulletEntity.Variant byId(int id)
		{
			return BY_ID.apply(id);
		}
	}
}