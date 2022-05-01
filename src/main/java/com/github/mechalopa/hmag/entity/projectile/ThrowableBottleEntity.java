package com.github.mechalopa.hmag.entity.projectile;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.registry.ModItems;
import com.mojang.math.Vector3d;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;

public class ThrowableBottleEntity extends ThrowableItemProjectile
{
	public ThrowableBottleEntity(EntityType<? extends ThrowableBottleEntity> type, Level world)
	{
		super(type, world);
	}

	public ThrowableBottleEntity(Level worldIn, LivingEntity throwerIn)
	{
		super(ModEntityTypes.THROWABLE_BOTTLE.get(), throwerIn, worldIn);
	}

	public ThrowableBottleEntity(Level worldIn, double x, double y, double z)
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
				this.level.levelEvent(2002, this.blockPosition(), MobEffects.FIRE_RESISTANCE.getColor());
				boolean flag = ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
				this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), item == ModItems.BLASTING_BOTTLE.get() ? 2.5F : 1.0F, flag, flag ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE);
			}
			else if (item == ModItems.LIGHTNING_BOTTLE.get())
			{
				LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(this.level);
				lightningboltentity.moveTo(Vector3d.atBottomCenterOf(this.blockPosition()));
				Entity owner = this.getOwner();
				lightningboltentity.setCause((owner != null && owner instanceof ServerPlayer) ? (ServerPlayer)owner : null);
				this.level.addFreshEntity(lightningboltentity);
				this.level.levelEvent(2002, this.blockPosition(), MobEffects.NIGHT_VISION.getColor());
			}
			else
			{
				this.level.levelEvent(2002, this.blockPosition(), MobEffects.HARM.getColor());
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