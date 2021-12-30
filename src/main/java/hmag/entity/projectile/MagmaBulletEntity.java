package hmag.entity.projectile;

import hmag.registry.ModEntityTypes;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

public class MagmaBulletEntity extends ModDamagingProjectileEntity
{
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
				else if (entity1 != null && entity1 instanceof LivingEntity)
				{
					this.doEnchantDamageEffects((LivingEntity)entity1, entity);
				}
			}
		}
	}

	@Override
	protected void onHitServer(RayTraceResult result)
	{
		boolean flag = ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
		this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), 0.75F, flag, flag ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
		this.level.broadcastEntityEvent(this, (byte)3);
		super.onHitServer(result);
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
		compound.putShort("Life", (short)this.getLife());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setLifeTime(compound.getShort("Life"));
	}

	@Override
	protected IParticleData getTrailParticle()
	{
		return ParticleTypes.SMOKE;
	}

	@Override
	protected float getEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return 0.15F;
	}
}