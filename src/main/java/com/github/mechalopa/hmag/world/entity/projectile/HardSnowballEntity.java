package com.github.mechalopa.hmag.world.entity.projectile;

import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PlayMessages;

public class HardSnowballEntity extends ModProjectileItemEntity
{
	public HardSnowballEntity(EntityType<? extends HardSnowballEntity> type, Level level)
	{
		super(type, level);
	}

	public HardSnowballEntity(Level level, LivingEntity thrower)
	{
		super(ModEntityTypes.HARD_SNOWBALL.get(), thrower, level);
	}

	public HardSnowballEntity(Level level, double x, double y, double z)
	{
		super(ModEntityTypes.HARD_SNOWBALL.get(), x, y, z, level);
	}

	public HardSnowballEntity(PlayMessages.SpawnEntity spawnEntity, Level level)
	{
		this(ModEntityTypes.HARD_SNOWBALL.get(), level);
	}

	@Override
	protected Item getDefaultItem()
	{
		return Items.SNOWBALL;
	}

	@OnlyIn(Dist.CLIENT)
	private ParticleOptions getParticle()
	{
		ItemStack stack = this.getItemRaw();
		return stack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, stack);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 3)
		{
			ParticleOptions particleoptions = this.getParticle();

			for (int i = 0; i < 8; ++i)
			{
				this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void onHitServer(HitResult result)
	{
		this.level.broadcastEntityEvent(this, (byte)3);
		super.onHitServer(result);
	}

	@Override
	protected void onHitEntity(EntityHitResult result)
	{
		super.onHitEntity(result);
		float f = this.getDamage();
		Entity entity = result.getEntity();

		if (entity.getType().is(ModTags.HARD_SNOWBALL_HURTS_EXTRA_TYPES))
		{
			f *= 2.0;
		}

		entity.hurt(this.damageSources().thrown(this, this.getOwner()), f);
	}
}