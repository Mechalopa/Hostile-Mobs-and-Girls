package hmag.entity.projectile;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class ThrowableBottleEntity extends ProjectileItemEntity
{
	public ThrowableBottleEntity(EntityType<? extends ThrowableBottleEntity> type, World world)
	{
		super(type, world);
	}

	public ThrowableBottleEntity(EntityType<? extends ThrowableBottleEntity> type, World worldIn, LivingEntity throwerIn)
	{
		super(type, throwerIn, worldIn);
	}

	public ThrowableBottleEntity(EntityType<? extends ThrowableBottleEntity> type, World worldIn, double x, double y, double z)
	{
		super(type, x, y, z, worldIn);
	}

	@Override
	protected float getGravity()
	{
		return 0.05F;
	}

	@Override
	public float getBrightness()
	{
		return 1.0F;
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}