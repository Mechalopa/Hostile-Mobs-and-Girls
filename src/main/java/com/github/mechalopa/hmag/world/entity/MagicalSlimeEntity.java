package com.github.mechalopa.hmag.world.entity;

import javax.annotation.Nonnull;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class MagicalSlimeEntity extends Slime implements IModMob
{
	private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(MagicalSlimeEntity.class, EntityDataSerializers.INT);

	public MagicalSlimeEntity(EntityType<? extends MagicalSlimeEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_VARIANT_ID, 0);
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.isMagic() || source == DamageSource.FALL)
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	@Override
	protected void setSize(int size, boolean resetHealth)
	{
		super.setSize(size, resetHealth);
		this.setVariant(this.getRandom().nextInt(SlimeGirlEntity.ColorVariant.values().length));
		this.getAttribute(Attributes.ARMOR).setBaseValue(size * 2);
	}

	@Override
	protected ResourceLocation getDefaultLootTable()
	{
		return this.isTiny() ? BuiltInLootTables.EMPTY : this.getType().getDefaultLootTable();
	}

	@Override
	protected int getJumpDelay()
	{
		return (int)(super.getJumpDelay() * 0.75F);
	}

	@Override
	protected boolean isDealsDamage()
	{
		return this.isEffectiveAi();
	}

	@Override
	protected float getAttackDamage()
	{
		return super.getAttackDamage() + 2.0F;
	}

	@Override
	protected ParticleOptions getParticleType()
	{
		return ParticleTypes.WITCH;
	}

	@OnlyIn(Dist.CLIENT)
	public float[] getColor()
	{
		return SlimeGirlEntity.ColorVariant.byId(this.getVariant()).getColors();
	}

	public int getVariant()
	{
		return this.entityData.get(DATA_VARIANT_ID);
	}

	public void setVariant(int typeIn)
	{
		if (typeIn < 0 || typeIn >= SlimeGirlEntity.ColorVariant.values().length)
		{
			typeIn = this.getRandom().nextInt(SlimeGirlEntity.ColorVariant.values().length);
		}

		this.entityData.set(DATA_VARIANT_ID, typeIn);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Variant", this.getVariant());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getInt("Variant"));
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}