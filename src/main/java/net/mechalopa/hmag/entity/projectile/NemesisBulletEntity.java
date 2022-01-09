package net.mechalopa.hmag.entity.projectile;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.mechalopa.hmag.registry.ModEntityTypes;
import net.mechalopa.hmag.registry.ModParticleTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NemesisBulletEntity extends ModDamagingProjectileEntity
{
	private static final DataParameter<Byte> EFFECT_LEVEL = EntityDataManager.defineId(NemesisBulletEntity.class, DataSerializers.BYTE);
	private static final DataParameter<Byte> PIERCE_LEVEL = EntityDataManager.defineId(NemesisBulletEntity.class, DataSerializers.BYTE);
	private IntOpenHashSet piercingIgnoreEntityIds;

	public NemesisBulletEntity(EntityType<? extends NemesisBulletEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	public NemesisBulletEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ)
	{
		super(ModEntityTypes.NEMESIS_BULLET.get(), shooter, accelX, accelY, accelZ, worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	public NemesisBulletEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
	{
		super(ModEntityTypes.NEMESIS_BULLET.get(), x, y, z, accelX, accelY, accelZ, worldIn);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(EFFECT_LEVEL, (byte)1);
		this.entityData.define(PIERCE_LEVEL, (byte)0);
	}

	@Override
	protected float getInertia()
	{
		return 0.98F;
	}

	@Override
	protected void tick2()
	{
		if (!this.level.isClientSide && (this.tickCount >= 60 || this.getOwner() == null))
		{
			this.remove();
		}

		if (this.level.isClientSide)
		{
			this.level.addParticle(ModParticleTypes.NEMESIS_FLAME.get(), this.getRandomX(0.5D), this.getY(this.random.nextDouble() * 0.5D), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult result)
	{
		super.onHitEntity(result);

		Entity entity = result.getEntity();
		Vector3d vector3d = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize();

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
				final int i = (this.getEffectLevel() * 2 + 4) * 20;
				((LivingEntity)entity).addEffect(new EffectInstance(Effects.WEAKNESS, i, 1));
				((LivingEntity)entity).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, i, 1));
				((LivingEntity)entity).addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, i, 1));
				((LivingEntity)entity).addEffect(new EffectInstance(Effects.WITHER, i, 1));

				if (vector3d.lengthSqr() > 0.0D)
				{
					((LivingEntity)entity).knockback(1.0F * ((float)this.getEffectLevel() / 12.0F) + 0.1F, -vector3d.x, -vector3d.z);
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
	protected void onHitServer(RayTraceResult result)
	{
		if (result.getType() != RayTraceResult.Type.ENTITY || this.getPierceLevel() <= 0 || this.piercingIgnoreEntityIds.size() >= this.getPierceLevel() + 1)
		{
			this.remove();
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 3)
		{
			for (int i = 0; i < 12; ++i)
			{
				this.level.addParticle(ModParticleTypes.NEMESIS_FLAME.get(), this.getRandomX(1.75D), this.getY(this.random.nextDouble() * 1.75D), this.getRandomZ(1.75D), 0.0D, 0.0D, 0.0D);
			}
		}
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
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putByte("PierceLevel", this.getPierceLevel());
		compound.putByte("EffectLevel", this.getEffectLevel());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setPierceLevel(compound.getByte("PierceLevel"));
		this.setEffectLevel(compound.getByte("EffectLevel"));
	}

	@Override
	protected IParticleData getTrailParticle()
	{
		return ParticleTypes.SMOKE;
	}
}