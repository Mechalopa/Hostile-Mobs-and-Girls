package com.github.mechalopa.hmag.entity.projectile;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.registry.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;

public class ThrowableBottleEntity extends ProjectileItemEntity
{
	public ThrowableBottleEntity(EntityType<? extends ThrowableBottleEntity> type, World world)
	{
		super(type, world);
	}

	public ThrowableBottleEntity(World worldIn, LivingEntity throwerIn)
	{
		super(ModEntityTypes.THROWABLE_BOTTLE.get(), throwerIn, worldIn);
	}

	public ThrowableBottleEntity(World worldIn, double x, double y, double z)
	{
		super(ModEntityTypes.THROWABLE_BOTTLE.get(), x, y, z, worldIn);
	}

	@Override
	protected Item getDefaultItem()
	{
		return Items.GLASS_BOTTLE;
	}

	@Override
	protected ITextComponent getTypeName()
	{
		return this.getItem().isEmpty() ? super.getTypeName() : new TranslationTextComponent(this.getItem().getDescriptionId());
	}

	@Override
	protected void onHit(RayTraceResult result)
	{
		super.onHit(result);

		if (!this.level.isClientSide)
		{
			final Item item = this.getItem().getItem();

			if (item == ModItems.FIRE_BOTTLE.get() || item == ModItems.BLASTING_BOTTLE.get())
			{
				this.level.levelEvent(2002, this.blockPosition(), Effects.FIRE_RESISTANCE.getColor());
				boolean flag = ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
				this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), item == ModItems.BLASTING_BOTTLE.get() ? 2.5F : 1.0F, flag, flag ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
			}
			else if (item == ModItems.LIGHTNING_BOTTLE.get())
			{
				LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(this.level);
				lightningboltentity.moveTo(Vector3d.atBottomCenterOf(this.blockPosition()));
				Entity owner = this.getOwner();
				lightningboltentity.setCause((owner != null && owner instanceof ServerPlayerEntity) ? (ServerPlayerEntity)owner : null);
				this.level.addFreshEntity(lightningboltentity);
				this.level.levelEvent(2002, this.blockPosition(), Effects.NIGHT_VISION.getColor());
			}
			else
			{
				this.level.levelEvent(2002, this.blockPosition(), Effects.HARM.getColor());
			}

			this.remove();
		}
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