package hmag.entity.projectile;

import hmag.registry.ModEntityTypes;
import hmag.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class FireBottleEntity extends ThrowableBottleEntity
{
	public FireBottleEntity(EntityType<? extends FireBottleEntity> type, World world)
	{
		super(type, world);
	}

	public FireBottleEntity(World worldIn, LivingEntity throwerIn)
	{
		super(ModEntityTypes.FIRE_BOTTLE.get(), worldIn, throwerIn);
	}

	public FireBottleEntity(World worldIn, double x, double y, double z)
	{
		super(ModEntityTypes.FIRE_BOTTLE.get(), worldIn, x, y, z);
	}

	@Override
	protected Item getDefaultItem()
	{
		return ModItems.FIRE_BOTTLE.get();
	}

	@Override
	protected void onHit(RayTraceResult result)
	{
		super.onHit(result);

		if (!this.level.isClientSide)
		{
			this.level.levelEvent(2002, this.blockPosition(), Effects.FIRE_RESISTANCE.getColor());
			boolean flag = ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
			this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), 1.0F, flag, flag ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
			this.remove();
		}
	}
}