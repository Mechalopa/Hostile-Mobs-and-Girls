package com.github.mechalopa.hmag.entity.projectile;

import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public abstract class ModDamagingProjectileEntity extends AbstractHurtingProjectile
{
	private static final DataParameter<Float> DAMAGE = EntityDataManager.defineId(ModDamagingProjectileEntity.class, DataSerializers.FLOAT);

	public ModDamagingProjectileEntity(EntityType<? extends ModDamagingProjectileEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}

	public ModDamagingProjectileEntity(EntityType<? extends ModDamagingProjectileEntity> type, LivingEntity shooter, double accelX, double accelY, double accelZ, Level worldIn)
	{
		super(type, shooter, accelX, accelY, accelZ, worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	public ModDamagingProjectileEntity(EntityType<? extends ModDamagingProjectileEntity> type, double x, double y, double z, double accelX, double accelY, double accelZ, Level worldIn)
	{
		super(type, x, y, z, accelX, accelY, accelZ, worldIn);
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
		if (!this.level.isClientSide && (this.tickCount >= 200 || this.getOwner() == null))
		{
			this.remove();
		}
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

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}