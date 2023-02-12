package com.github.mechalopa.hmag.world.entity.projectile;

import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.registry.ModItems;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class EvilArrowEntity extends AbstractArrow
{
	public EvilArrowEntity(EntityType<? extends EvilArrowEntity> type, Level level)
	{
		super(type, level);
	}

	public EvilArrowEntity(Level level, LivingEntity thrower)
	{
		super(ModEntityTypes.EVIL_ARROW.get(), thrower, level);
	}

	public EvilArrowEntity(Level level, double x, double y, double z)
	{
		super(ModEntityTypes.EVIL_ARROW.get(), x, y, z, level);
	}

	public EvilArrowEntity(PlayMessages.SpawnEntity spawnEntity, Level level)
	{
		this(ModEntityTypes.EVIL_ARROW.get(), level);
	}

	@Override
	protected ItemStack getPickupItem()
	{
		return new ItemStack(ModItems.EVIL_ARROW.get());
	}
}