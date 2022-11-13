package com.github.mechalopa.hmag.world.entity.projectile;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.registry.ModItems;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class ThrowableBottleEntity extends ThrowableItemProjectile
{
	public ThrowableBottleEntity(EntityType<? extends ThrowableBottleEntity> type, Level level)
	{
		super(type, level);
	}

	public ThrowableBottleEntity(Level level, LivingEntity thrower)
	{
		super(ModEntityTypes.THROWABLE_BOTTLE.get(), thrower, level);
	}

	public ThrowableBottleEntity(Level level, double x, double y, double z)
	{
		super(ModEntityTypes.THROWABLE_BOTTLE.get(), x, y, z, level);
	}

	public ThrowableBottleEntity(PlayMessages.SpawnEntity spawnEntity, Level level)
	{
		this(ModEntityTypes.THROWABLE_BOTTLE.get(), level);
	}

	@Override
	protected Item getDefaultItem()
	{
		return Items.GLASS_BOTTLE;
	}

	@Override
	protected Component getTypeName()
	{
		return this.getItem().isEmpty() ? super.getTypeName() : Component.translatable(this.getItem().getDescriptionId());
	}

	@Override
	protected void onHit(HitResult result)
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
				LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level);
				lightningbolt.moveTo(Vec3.atBottomCenterOf(this.blockPosition()));
				Entity owner = this.getOwner();
				lightningbolt.setCause((owner != null && owner instanceof ServerPlayer) ? (ServerPlayer)owner : null);
				this.level.addFreshEntity(lightningbolt);
				this.level.levelEvent(2002, this.blockPosition(), MobEffects.NIGHT_VISION.getColor());
			}
			else
			{
				this.level.levelEvent(2002, this.blockPosition(), MobEffects.HARM.getColor());
			}

			this.discard();
		}
	}

	@Override
	protected float getGravity()
	{
		return 0.05F;
	}

	@Override
	public float getLightLevelDependentMagicValue()
	{
		return 1.0F;
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}