package hmag.entity.projectile;

import javax.annotation.Nonnull;

import hmag.registry.ModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;

public class MagmaBulletEntity extends DamagingProjectileEntity
{
	private static final DataParameter<Float> DAMAGE = EntityDataManager.defineId(MagmaBulletEntity.class, DataSerializers.FLOAT);
	private static final DataParameter<Integer> LIFE_TIME = EntityDataManager.defineId(MagmaBulletEntity.class, DataSerializers.INT);
	private int life = 25;

	public MagmaBulletEntity(EntityType<? extends MagmaBulletEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	public MagmaBulletEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ)
	{
		super(ModEntityTypes.MAGMA_BULLET.get(), shooter, accelX, accelY, accelZ, worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	public MagmaBulletEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
	{
		super(ModEntityTypes.MAGMA_BULLET.get(), x, y, z, accelX, accelY, accelZ, worldIn);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DAMAGE, Float.valueOf(3.0F));
		this.entityData.define(LIFE_TIME, 25);
	}

	@Override
	protected float getInertia()
	{
		return 0.85F;
	}

	@Override
	public boolean isOnFire()
	{
		return false;
	}

	@Override
	public float getBlockExplosionResistance(Explosion explosionIn, IBlockReader worldIn, BlockPos pos, BlockState blockStateIn, FluidState fluidState, float explosionPower)
	{
		return explosionPower;
	}

	@Override
	public void tick()
	{
		if (!this.level.isClientSide && (this.tickCount >= 200 || this.getOwner() == null))
		{
			this.remove();
		}

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

		super.tick();
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult result)
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
				else if (entity1 instanceof LivingEntity)
				{
					this.doEnchantDamageEffects((LivingEntity)entity1, entity);
				}
			}
		}
	}

	@Override
	protected void onHit(RayTraceResult result)
	{
		super.onHit(result);

		if (!this.level.isClientSide)
		{
			boolean flag = ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
			this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), 0.5F, flag, flag ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
			this.level.broadcastEntityEvent(this, (byte)3);
			this.remove();
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 3)
		{
			IParticleData iparticledata = new BlockParticleData(ParticleTypes.BLOCK, Blocks.MAGMA_BLOCK.defaultBlockState()).setPos(this.blockPosition());

			for (int i = 0; i < 16; ++i)
			{
				this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
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
	public void onSyncedDataUpdated(DataParameter<?> data)
	{
		super.onSyncedDataUpdated(data);

		if (LIFE_TIME.equals(data))
		{
			this.life = this.getLifeTime();
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putFloat("Damage", this.getDamage());
		compound.putShort("Life", (short)this.getLife());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setDamage(compound.getFloat("Damage"));
		this.setLifeTime(compound.getShort("Life"));
	}

	@Override
	protected IParticleData getTrailParticle()
	{
		return ParticleTypes.SMOKE;
	}

	@Override
	protected boolean shouldBurn()
	{
		return false;
	}

	@Override
	protected float getEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return 0.15F;
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}