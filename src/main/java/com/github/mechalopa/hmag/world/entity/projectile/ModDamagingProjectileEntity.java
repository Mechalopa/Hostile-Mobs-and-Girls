package com.github.mechalopa.hmag.world.entity.projectile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class ModDamagingProjectileEntity extends AbstractHurtingProjectile
{
	private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(ModDamagingProjectileEntity.class, EntityDataSerializers.FLOAT);

	public ModDamagingProjectileEntity(EntityType<? extends ModDamagingProjectileEntity> type, Level level)
	{
		super(type, level);
	}

	public ModDamagingProjectileEntity(EntityType<? extends ModDamagingProjectileEntity> type, LivingEntity shooter, double accelX, double accelY, double accelZ, Level level)
	{
		super(type, shooter, accelX, accelY, accelZ, level);
	}

	@OnlyIn(Dist.CLIENT)
	public ModDamagingProjectileEntity(EntityType<? extends ModDamagingProjectileEntity> type, double x, double y, double z, double accelX, double accelY, double accelZ, Level level)
	{
		super(type, x, y, z, accelX, accelY, accelZ, level);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DAMAGE, Float.valueOf(3.0F));
	}

	@Override
	public boolean isOnFire()
	{
		return false;
	}

	@Override
	public void tick()
	{
		this.tick2();
		super.tick();
	}

	protected void tick2()
	{
		if (!this.level().isClientSide() && (this.tickCount >= 200 || this.getOwner() == null))
		{
			this.discard();
		}
	}

	@Override
	protected void onHit(HitResult result)
	{
		super.onHit(result);

		if (!this.level().isClientSide())
		{
			this.onHitServer(result);
		}
	}

	protected void onHitServer(HitResult result)
	{
		this.discard();
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return false;
	}

	@Override
	public boolean isPickable()
	{
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		return false;
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

	@Override
	protected boolean shouldBurn()
	{
		return false;
	}
}