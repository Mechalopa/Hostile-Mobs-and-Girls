package com.github.mechalopa.hmag.entity.projectile;

import com.github.mechalopa.hmag.registry.ModEntityTypes;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

public class MagmaBulletEntity extends ModDamagingProjectileEntity
{
	private static final EntityDataAccessor<Integer> LIFE_TIME = SynchedEntityData.defineId(MagmaBulletEntity.class, EntityDataSerializers.INT);
	private int life = 25;

	public MagmaBulletEntity(EntityType<? extends MagmaBulletEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}

	public MagmaBulletEntity(Level worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ)
	{
		super(ModEntityTypes.MAGMA_BULLET.get(), shooter, accelX, accelY, accelZ, worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	public MagmaBulletEntity(Level worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
	{
		super(ModEntityTypes.MAGMA_BULLET.get(), x, y, z, accelX, accelY, accelZ, worldIn);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(LIFE_TIME, 25);
	}

	@Override
	protected float getInertia()
	{
		return 0.85F;
	}

	@Override
	protected void tick2()
	{
		super.tick2();

		if (this.life > 0)
		{
			if (this.isInWaterRainOrBubble())
			{
				this.life = Math.max(this.life - 3, 0);
			}
			else
			{
				--this.life;
			}
		}

		if (this.life <= 0)
		{
			if (!this.isNoGravity())
			{
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.05D, 0.0D));
			}

			this.xPower *= 0.97D;
			this.yPower *= 0.97D;
			this.zPower *= 0.97D;
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result)
	{
		super.onHitEntity(result);

		if (!this.level.isClientSide)
		{
			Entity entity = result.getEntity();

			if (!entity.fireImmune())
			{
				Entity entity1 = this.getOwner();
				int i = entity.getRemainingFireTicks();
				entity.setSecondsOnFire(5);
				boolean flag = entity.hurt(DamageSource.thrown(this, entity1).setIsFire(), this.getDamage());

				if (!flag)
				{
					entity.setRemainingFireTicks(i);
				}
				else if (entity1 != null && entity1 instanceof LivingEntity)
				{
					this.doEnchantDamageEffects((LivingEntity)entity1, entity);
				}
			}
		}
	}

	@Override
	protected void onHitServer(HitResult result)
	{
		boolean flag = ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
		this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), 0.75F, flag, flag ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE);
		this.level.broadcastEntityEvent(this, (byte)3);
		super.onHitServer(result);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 3)
		{
			ParticleOptions particleoptions = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MAGMA_BLOCK.defaultBlockState()).setPos(this.blockPosition());

			for (int i = 0; i < 16; ++i)
			{
				this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}

			if (!this.isInWaterOrBubble())
			{
				for (int j = 0; j < 8; ++j)
				{
					this.level.addParticle(ParticleTypes.LAVA, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}

	public void setLifeTime(int amount)
	{
		this.entityData.set(LIFE_TIME, amount);
		this.life = amount;
	}

	public int getLifeTime()
	{
		return this.entityData.get(LIFE_TIME);
	}

	public int getLife()
	{
		return this.life;
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> data)
	{
		super.onSyncedDataUpdated(data);

		if (LIFE_TIME.equals(data))
		{
			this.life = this.getLifeTime();
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putShort("Life", (short)this.getLife());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setLifeTime(compound.getShort("Life"));
	}

	@Override
	protected ParticleOptions getTrailParticle()
	{
		return ParticleTypes.SMOKE;
	}

	@Override
	protected float getEyeHeight(Pose poseIn, EntityDimensions sizeIn)
	{
		return 0.15F;
	}
}