package com.github.mechalopa.hmag.world.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class MagicalSlimeEntity extends Slime implements VariantHolder<SlimeGirlEntity.ColorVariant>
{
	private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(MagicalSlimeEntity.class, EntityDataSerializers.INT);

	public MagicalSlimeEntity(EntityType<? extends MagicalSlimeEntity> type, Level level)
	{
		super(type, level);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_VARIANT_ID, SlimeGirlEntity.ColorVariant.PINK_1.getId());
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.is(ModTags.DamageTypeTags.MAGICAL_SLIME_RESISTANT_TO))
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	@Override
	public void setSize(int size, boolean resetHealth)
	{
		super.setSize(size, resetHealth);
		this.setVariant(SlimeGirlEntity.ColorVariant.getSpawnVariant(this.getRandom()));
		this.getAttribute(Attributes.ARMOR).setBaseValue(size * 2);
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
	public float[] getColors()
	{
		return this.getVariant().getColors();
	}

	@Override
	public SlimeGirlEntity.ColorVariant getVariant()
	{
		return SlimeGirlEntity.ColorVariant.byId(this.entityData.get(DATA_VARIANT_ID));
	}

	@Override
	public void setVariant(SlimeGirlEntity.ColorVariant variant)
	{
		this.entityData.set(DATA_VARIANT_ID, variant.getId());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(SlimeGirlEntity.ColorVariant.byId(compound.getInt("Variant")));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Variant", this.getVariant().getId());
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}