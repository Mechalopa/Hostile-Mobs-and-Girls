package hmag.entity.projectile;

import javax.annotation.Nonnull;

import hmag.entity.LichEntity;
import hmag.registry.ModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class MagicBulletEntity extends DamagingProjectileEntity
{
	private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(MagicBulletEntity.class, DataSerializers.INT);
	private static final DataParameter<Float> DAMAGE = EntityDataManager.defineId(MagicBulletEntity.class, DataSerializers.FLOAT);

	public MagicBulletEntity(EntityType<? extends MagicBulletEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	public MagicBulletEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ)
	{
		super(ModEntityTypes.MAGIC_BULLET.get(), shooter, accelX, accelY, accelZ, worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	public MagicBulletEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
	{
		super(ModEntityTypes.MAGIC_BULLET.get(), x, y, z, accelX, accelY, accelZ, worldIn);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_VARIANT_ID, 0);
		this.entityData.define(DAMAGE, Float.valueOf(3.0F));
	}

	@Override
	protected float getInertia()
	{
		switch (this.getVariant())
		{
		case 1:
			return 0.87F;
		case 0:
		default:
			return 0.9F;
		}
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

		super.tick();
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult result)
	{
		super.onHitEntity(result);

		if (!this.level.isClientSide)
		{
			Entity entity = result.getEntity();
			Entity entity1 = this.getOwner();
			float damage = this.getDamage();
			boolean flag;

			if (entity1 instanceof LivingEntity)
			{
				LivingEntity livingentity = (LivingEntity)entity1;

				if (this.getVariant() == 0)
				{
					if (livingentity instanceof LichEntity && entity instanceof VexEntity && livingentity.isAlliedTo((entity)))
					{
						damage *= 5.0F;
					}
				}

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

			if (flag && entity instanceof LivingEntity)
			{
				int i = 0;

				switch (this.getVariant())
				{
				case 0:
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
						((LivingEntity)entity).addEffect(new EffectInstance(Effects.WEAKNESS, 20 * i, 0));
					}

					break;
				case 1:
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
						((LivingEntity)entity).addEffect(new EffectInstance(Effects.BLINDNESS, 20 * i, 0));
					}

					break;
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
			this.remove();
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

	public int getVariant()
	{
		return this.entityData.get(DATA_VARIANT_ID);
	}

	public void setVariant(int typeIn)
	{
		if (typeIn < 0 || typeIn >= 2)
		{
			typeIn = 0;
		}

		this.entityData.set(DATA_VARIANT_ID, typeIn);
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
		compound.putInt("Variant", this.getVariant());
		compound.putFloat("Damage", this.getDamage());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getInt("Variant"));
		this.setDamage(compound.getFloat("Damage"));
	}

	@Override
	protected IParticleData getTrailParticle()
	{
		switch (this.getVariant())
		{
		case 1:
			return ParticleTypes.LARGE_SMOKE;
		case 0:
		default:
			return ParticleTypes.DRAGON_BREATH;
		}
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