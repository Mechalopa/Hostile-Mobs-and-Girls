package com.github.mechalopa.hmag.world.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.registry.ModSoundEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

public class RedcapEntity extends Monster
{
	public RedcapEntity(EntityType<? extends RedcapEntity> type, Level level)
	{
		super(type, level);
		this.xpReward = 12;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true).setUnseenMemoryTicks(120));
		if (ModConfigs.cachedServer.REDCAP_ATTACK_VILLAGERS)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		if (ModConfigs.cachedServer.REDCAP_ATTACK_BABY_TURTLES)
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.31D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D)
				.add(Attributes.FOLLOW_RANGE, 24.0D);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance difficulty)
	{
		float f = difficulty.getSpecialMultiplier();

		if (rand.nextFloat() < 0.25F * f + 0.25F)
		{
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
		}
		else
		{
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_AXE));
		}

		if (rand.nextFloat() < 0.05F)
		{
			this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.BONE));
		}

		int i = rand.nextInt(8);

		if (i == 0)
		{
			this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
		}
		else
		{
			this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
		}
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType spawnType, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag)
	{
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, spawnType, spawnDataIn, dataTag);
		RandomSource randomsource = worldIn.getRandom();
		this.populateDefaultEquipmentSlots(randomsource, difficultyIn);
		this.populateDefaultEquipmentEnchantments(randomsource, difficultyIn);
		return spawnDataIn;
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 3;
	}

	@Override
	public double getMyRidingOffset()
	{
		return -0.32D;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size)
	{
		return 1.52F;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.GIRL_MOB_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource)
	{
		return ModSoundEvents.GIRL_MOB_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.GIRL_MOB_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block)
	{
		this.playSound(SoundEvents.PIGLIN_STEP, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}