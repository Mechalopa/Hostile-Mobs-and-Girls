package hmag.entity.projectile;

import java.util.List;

import javax.annotation.Nonnull;

import hmag.registry.ModEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class PoisonSeedEntity extends ProjectileItemEntity
{
	private static final DataParameter<Float> DAMAGE = EntityDataManager.defineId(PoisonSeedEntity.class, DataSerializers.FLOAT);

	public PoisonSeedEntity(EntityType<? extends PoisonSeedEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	public PoisonSeedEntity(World worldIn, LivingEntity throwerIn)
	{
		super(ModEntityTypes.POISON_SEED.get(), throwerIn, worldIn);
	}

	public PoisonSeedEntity(World worldIn, double x, double y, double z)
	{
		super(ModEntityTypes.POISON_SEED.get(), x, y, z, worldIn);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DAMAGE, Float.valueOf(3.0F));
	}

	@Override
	protected Item getDefaultItem()
	{
		return Items.WHEAT_SEEDS;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 3)
		{
			for (int i = 0; i < 8; ++i)
			{
				this.level.addParticle(new ItemParticleData(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
			}
		}
	}

	@Override
	protected void onHit(RayTraceResult result)
	{
		super.onHit(result);

		if (!this.level.isClientSide)
		{
			AxisAlignedBB axisalignedbb = this.getBoundingBox().inflate(4.0D, 2.0D, 4.0D);
			List<LivingEntity> list2 = this.level.getEntitiesOfClass(LivingEntity.class, axisalignedbb);

			if (!list2.isEmpty())
			{
				for (LivingEntity livingEntity : list2)
				{
					if (livingEntity.isAffectedByPotions())
					{
						double d0 = this.distanceToSqr(livingEntity);

						if (d0 < 16.0D)
						{
							double d1 = 1.0D - Math.sqrt(d0) / 4.0D;

							if (result.getType() == RayTraceResult.Type.ENTITY && ((EntityRayTraceResult)result).getEntity() == livingEntity)
							{
								d1 = 1.0D;
							}

							int i = 0;

							if (this.level.getDifficulty() == Difficulty.NORMAL)
							{
								i = 7 * 20;
							}
							else if (this.level.getDifficulty() == Difficulty.HARD)
							{
								i = 15 * 20;
							}

							i = (int)(d1 * i + 0.5D);

							if (i > 20)
							{
								livingEntity.addEffect(new EffectInstance(Effects.POISON, i, 0));
								livingEntity.addEffect(new EffectInstance(Effects.WEAKNESS, i, 0));
								livingEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, i, 0));
							}
						}
					}
				}
			}

			this.level.levelEvent(2002, this.blockPosition(), Effects.POISON.getColor());
			this.level.broadcastEntityEvent(this, (byte)3);
			this.remove();
		}
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult result)
	{
		super.onHitEntity(result);
		result.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), this.getDamage());
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