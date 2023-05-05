package com.github.mechalopa.hmag.world.entity.projectile;

import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.PlayMessages;

public class InkSpitEntity extends Projectile
{
	private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(InkSpitEntity.class, EntityDataSerializers.FLOAT);

	public InkSpitEntity(EntityType<? extends InkSpitEntity> entity, Level level)
	{
		super(entity, level);
	}

	public InkSpitEntity(EntityType<? extends InkSpitEntity> entity, double x, double y, double z, Level level)
	{
		this(entity, level);
		this.setPos(x, y, z);
	}

	public InkSpitEntity(EntityType<? extends InkSpitEntity> entity, LivingEntity thrower, Level level)
	{
		this(entity, thrower.getX(), thrower.getEyeY() - (double)0.1F, thrower.getZ(), level);
		this.setOwner(thrower);
	}

	public InkSpitEntity(Level level, LivingEntity thrower)
	{
		this(ModEntityTypes.INK_SPIT.get(), thrower, level);
	}

	public InkSpitEntity(Level level, double x, double y, double z)
	{
		this(ModEntityTypes.INK_SPIT.get(), x, y, z, level);
	}

	public InkSpitEntity(PlayMessages.SpawnEntity spawnEntity, Level level)
	{
		this(ModEntityTypes.INK_SPIT.get(), level);
	}

	@Override
	protected void defineSynchedData()
	{
		this.entityData.define(DAMAGE, Float.valueOf(3.0F));
	}

	@Override
	public void tick()
	{
		super.tick();

		HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);

		if (hitresult.getType() != HitResult.Type.MISS && !ForgeEventFactory.onProjectileImpact(this, hitresult))
		{
			this.onHit(hitresult);
		}

		this.checkInsideBlocks();
		Vec3 vec3 = this.getDeltaMovement();
		double d0 = this.getX() + vec3.x;
		double d1 = this.getY() + vec3.y;
		double d2 = this.getZ() + vec3.z;
		this.updateRotation();
		float f;

		if (this.isInWater())
		{
			f = 0.95F;
		}
		else
		{
			f = 0.99F;
		}

		this.setDeltaMovement(vec3.scale((double)f));

		if (!this.isNoGravity())
		{
			Vec3 vec31 = this.getDeltaMovement();
			this.setDeltaMovement(vec31.x, vec31.y - (double)this.getGravity(), vec31.z);
		}

		this.setPos(d0, d1, d2);
	}

	@Override
	protected void onHitEntity(EntityHitResult result)
	{
		super.onHitEntity(result);

		if (!this.level.isClientSide)
		{
			Entity entity = this.getOwner();
			LivingEntity livingEntity = (entity != null && entity instanceof LivingEntity) ? (LivingEntity)entity : null;
			Entity entity1 = result.getEntity();

			if (!entity1.getType().is(ModTags.INK_SPIT_IMMUNE))
			{
				boolean flag = entity1.hurt(DamageSource.indirectMobAttack(this, livingEntity).setProjectile(), this.getDamage());

				if (flag && entity1 instanceof LivingEntity)
				{
					int i = 3;

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
						((LivingEntity)entity1).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20 * i, 0));
					}
				}
			}
		}
	}

	@Override
	protected void onHit(HitResult result)
	{
		super.onHit(result);

		if (!this.level.isClientSide)
		{
			this.level.broadcastEntityEvent(this, (byte)3);
			this.discard();
		}
	}

	protected float getGravity()
	{
		return 0.03F;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 3)
		{
			for (int i = 0; i < 30; ++i)
			{
				this.level.addParticle(ParticleTypes.SQUID_INK, this.getX(), this.getY(), this.getZ(), (this.random.nextFloat() - 0.5D) * 1.2D, (this.random.nextFloat() - 0.5D) * 1.2D, (this.random.nextFloat() - 1.2D) * 0.08D);
			}
		}
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
	public void recreateFromPacket(ClientboundAddEntityPacket packet)
	{
		super.recreateFromPacket(packet);

		double d0 = packet.getXa();
		double d1 = packet.getYa();
		double d2 = packet.getZa();

		for (int i = 0; i < 30; ++i)
		{
			double d3 = 0.4D + 0.1D * ((double)i / 5.0D);
			Vec3 vec3 = new Vec3(d0 * d3 * ((double)(0.8F + this.random.nextFloat() * 0.4F)), d1 * ((double)(0.8F + this.random.nextFloat() * 0.4F)), d2 * d3 * ((double)(0.8F + this.random.nextFloat() * 0.4F)));
			this.level.addParticle(ParticleTypes.SQUID_INK, this.getX(), this.getY(), this.getZ(), vec3.x, vec3.y, vec3.z);
		}
	}
}