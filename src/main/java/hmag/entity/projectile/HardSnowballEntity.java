package hmag.entity.projectile;

import javax.annotation.Nonnull;

import hmag.entity.MeltyMonsterEntity;
import hmag.registry.ModEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class HardSnowballEntity extends ProjectileItemEntity
{
	private static final DataParameter<Float> DAMAGE = EntityDataManager.defineId(HardSnowballEntity.class, DataSerializers.FLOAT);

	public HardSnowballEntity(EntityType<? extends HardSnowballEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	public HardSnowballEntity(World worldIn, LivingEntity throwerIn)
	{
		super(ModEntityTypes.HARD_SNOWBALL.get(), throwerIn, worldIn);
	}

	public HardSnowballEntity(World worldIn, double x, double y, double z)
	{
		super(ModEntityTypes.HARD_SNOWBALL.get(), x, y, z, worldIn);
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
		return Items.SNOWBALL;
	}

	@OnlyIn(Dist.CLIENT)
	private IParticleData makeParticle()
	{
		ItemStack itemstack = this.getItemRaw();
		return (IParticleData)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack));
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 3)
		{
			IParticleData iparticledata = this.makeParticle();

			for (int i = 0; i < 8; ++i)
			{
				this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void onHit(RayTraceResult result)
	{
		super.onHit(result);

		if (!this.level.isClientSide)
		{
			this.level.broadcastEntityEvent(this, (byte)3);
			this.remove();
		}
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult result)
	{
		super.onHitEntity(result);
		float f = this.getDamage();
		Entity entity = result.getEntity();

		if (entity instanceof BlazeEntity || entity instanceof MeltyMonsterEntity)
		{
			f *= 2.0;
		}

		entity.hurt(DamageSource.thrown(this, this.getOwner()), f);
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