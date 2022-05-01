package com.github.mechalopa.hmag.entity.projectile;

import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraftforge.network.NetworkHooks;

public abstract class ModProjectileItemEntity extends ThrowableItemProjectile
{
	private static final DataParameter<Float> DAMAGE = EntityDataManager.defineId(ModProjectileItemEntity.class, DataSerializers.FLOAT);

	public ModProjectileItemEntity(EntityType<? extends ModProjectileItemEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}

	public ModProjectileItemEntity(EntityType<? extends ModProjectileItemEntity> type, LivingEntity throwerIn, Level worldIn)
	{
		super(type, throwerIn, worldIn);
	}

	public ModProjectileItemEntity(EntityType<? extends ModProjectileItemEntity> type, double x, double y, double z, Level worldIn)
	{
		super(type, x, y, z, worldIn);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DAMAGE, Float.valueOf(3.0F));
	}

	@Override
	protected void onHit(RayTraceResult result)
	{
		super.onHit(result);

		if (!this.level.isClientSide)
		{
			this.onHitServer(result);
		}
	}

	protected void onHitServer(RayTraceResult result)
	{
		this.remove();
	}

	public void setDamage(float amount)
	{
		this.entityData.set(DAMAGE, amount);
	}

	public float getDamage()
	{
		return this.entityData.get(DAMAGE).floatValue();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putFloat("Damage", this.getDamage());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setDamage(compound.getFloat("Damage"));
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}