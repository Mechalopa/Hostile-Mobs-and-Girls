package com.github.mechalopa.hmag.entity.projectile;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class ModProjectileItemEntity extends ProjectileItemEntity
{
	private static final DataParameter<Float> DAMAGE = EntityDataManager.defineId(ModProjectileItemEntity.class, DataSerializers.FLOAT);

	public ModProjectileItemEntity(EntityType<? extends ModProjectileItemEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	public ModProjectileItemEntity(EntityType<? extends ModProjectileItemEntity> type, LivingEntity throwerIn, World worldIn)
	{
		super(type, throwerIn, worldIn);
	}

	public ModProjectileItemEntity(EntityType<? extends ModProjectileItemEntity> type, double x, double y, double z, World worldIn)
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
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putFloat("Damage", this.getDamage());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
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