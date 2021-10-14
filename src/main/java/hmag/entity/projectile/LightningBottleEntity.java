package hmag.entity.projectile;

import hmag.registry.ModEntityTypes;
import hmag.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class LightningBottleEntity extends ThrowableBottleEntity
{
	public LightningBottleEntity(EntityType<? extends LightningBottleEntity> type, World world)
	{
		super(type, world);
	}

	public LightningBottleEntity(World worldIn, LivingEntity throwerIn)
	{
		super(ModEntityTypes.LIGHTNING_BOTTLE.get(), worldIn, throwerIn);
	}

	public LightningBottleEntity(World worldIn, double x, double y, double z)
	{
		super(ModEntityTypes.LIGHTNING_BOTTLE.get(), worldIn, x, y, z);
	}

	@Override
	protected Item getDefaultItem()
	{
		return ModItems.LIGHTNING_BOTTLE.get();
	}

	@Override
	protected void onHit(RayTraceResult result)
	{
		super.onHit(result);

		if (!this.level.isClientSide)
		{
			LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(this.level);
			lightningboltentity.moveTo(Vector3d.atBottomCenterOf(this.blockPosition()));
			Entity owner = this.getOwner();
			lightningboltentity.setCause((owner != null && owner instanceof ServerPlayerEntity) ? (ServerPlayerEntity)owner : null);
			this.level.addFreshEntity(lightningboltentity);
			this.level.levelEvent(2002, this.blockPosition(), Effects.NIGHT_VISION.getColor());
			this.remove();
		}
	}
}