package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.world.entity.AlrauneEntity;
import com.github.mechalopa.hmag.world.entity.BansheeEntity;
import com.github.mechalopa.hmag.world.entity.CatoblepasEntity;
import com.github.mechalopa.hmag.world.entity.CreeperGirlEntity;
import com.github.mechalopa.hmag.world.entity.CrimsonSlaughtererEntity;
import com.github.mechalopa.hmag.world.entity.CursedDollEntity;
import com.github.mechalopa.hmag.world.entity.DodomekiEntity;
import com.github.mechalopa.hmag.world.entity.DoguEntity;
import com.github.mechalopa.hmag.world.entity.DrownedGirlEntity;
import com.github.mechalopa.hmag.world.entity.DullahanEntity;
import com.github.mechalopa.hmag.world.entity.DyssomniaEntity;
import com.github.mechalopa.hmag.world.entity.EnderExecutorEntity;
import com.github.mechalopa.hmag.world.entity.FortressKeeperEntity;
import com.github.mechalopa.hmag.world.entity.GhastlySeekerEntity;
import com.github.mechalopa.hmag.world.entity.GhostEntity;
import com.github.mechalopa.hmag.world.entity.HarpyEntity;
import com.github.mechalopa.hmag.world.entity.HornetEntity;
import com.github.mechalopa.hmag.world.entity.HuskGirlEntity;
import com.github.mechalopa.hmag.world.entity.ImpEntity;
import com.github.mechalopa.hmag.world.entity.JackFrostEntity;
import com.github.mechalopa.hmag.world.entity.KashaEntity;
import com.github.mechalopa.hmag.world.entity.KoboldEntity;
import com.github.mechalopa.hmag.world.entity.LichEntity;
import com.github.mechalopa.hmag.world.entity.MagicalSlimeEntity;
import com.github.mechalopa.hmag.world.entity.MeltyMonsterEntity;
import com.github.mechalopa.hmag.world.entity.MonolithEntity;
import com.github.mechalopa.hmag.world.entity.NecroticReaperEntity;
import com.github.mechalopa.hmag.world.entity.OgreEntity;
import com.github.mechalopa.hmag.world.entity.RedcapEntity;
import com.github.mechalopa.hmag.world.entity.SavagefangEntity;
import com.github.mechalopa.hmag.world.entity.ScorpionEntity;
import com.github.mechalopa.hmag.world.entity.SkeletonGirlEntity;
import com.github.mechalopa.hmag.world.entity.SlimeGirlEntity;
import com.github.mechalopa.hmag.world.entity.SnowCanineEntity;
import com.github.mechalopa.hmag.world.entity.SpiderNestEntity;
import com.github.mechalopa.hmag.world.entity.StrayGirlEntity;
import com.github.mechalopa.hmag.world.entity.WitherGhostEntity;
import com.github.mechalopa.hmag.world.entity.WitherSkeletonGirlEntity;
import com.github.mechalopa.hmag.world.entity.ZombieGirlEntity;
import com.github.mechalopa.hmag.world.entity.projectile.HardSnowballEntity;
import com.github.mechalopa.hmag.world.entity.projectile.MagicBulletEntity;
import com.github.mechalopa.hmag.world.entity.projectile.MagmaBulletEntity;
import com.github.mechalopa.hmag.world.entity.projectile.PoisonSeedEntity;
import com.github.mechalopa.hmag.world.entity.projectile.ThrowableBottleEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes
{
	private static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, HMaG.MODID);

	public static final RegistryObject<EntityType<ZombieGirlEntity>> ZOMBIE_GIRL = REGISTRY.register("zombie_girl", () -> EntityType.Builder
			.of(ZombieGirlEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "zombie_girl").toString()));
	public static final RegistryObject<EntityType<HuskGirlEntity>> HUSK_GIRL = REGISTRY.register("husk_girl", () -> EntityType.Builder
			.of(HuskGirlEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "husk_girl").toString()));
	public static final RegistryObject<EntityType<DrownedGirlEntity>> DROWNED_GIRL = REGISTRY.register("drowned_girl", () -> EntityType.Builder
			.of(DrownedGirlEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "drowned_girl").toString()));
	public static final RegistryObject<EntityType<SkeletonGirlEntity>> SKELETON_GIRL = REGISTRY.register("skeleton_girl", () -> EntityType.Builder
			.of(SkeletonGirlEntity::new, MobCategory.MONSTER).sized(0.6F, 1.99F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "skeleton_girl").toString()));
	public static final RegistryObject<EntityType<WitherSkeletonGirlEntity>> WITHER_SKELETON_GIRL = REGISTRY.register("wither_skeleton_girl", () -> EntityType.Builder
			.of(WitherSkeletonGirlEntity::new, MobCategory.MONSTER).fireImmune().immuneTo(Blocks.WITHER_ROSE).sized(0.7F, 2.4F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "wither_skeleton_girl").toString()));
	public static final RegistryObject<EntityType<StrayGirlEntity>> STRAY_GIRL = REGISTRY.register("stray_girl", () -> EntityType.Builder
			.of(StrayGirlEntity::new, MobCategory.MONSTER).sized(0.6F, 1.99F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "stray_girl").toString()));
	public static final RegistryObject<EntityType<CreeperGirlEntity>> CREEPER_GIRL = REGISTRY.register("creeper_girl", () -> EntityType.Builder
			.of(CreeperGirlEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "creeper_girl").toString()));
	public static final RegistryObject<EntityType<GhostEntity>> GHOST = REGISTRY.register("ghost", () -> EntityType.Builder
			.of(GhostEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "ghost").toString()));
	public static final RegistryObject<EntityType<WitherGhostEntity>> WITHER_GHOST = REGISTRY.register("wither_ghost", () -> EntityType.Builder
			.of(WitherGhostEntity::new, MobCategory.MONSTER).fireImmune().immuneTo(Blocks.WITHER_ROSE).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "wither_ghost").toString()));
	public static final RegistryObject<EntityType<EnderExecutorEntity>> ENDER_EXECUTOR = REGISTRY.register("ender_executor", () -> EntityType.Builder
			.of(EnderExecutorEntity::new, MobCategory.MONSTER).sized(0.6F, 2.9F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "ender_executor").toString()));
	public static final RegistryObject<EntityType<KoboldEntity>> KOBOLD = REGISTRY.register("kobold", () -> EntityType.Builder
			.of(KoboldEntity::new, MobCategory.MONSTER).sized(0.6F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "kobold").toString()));
	public static final RegistryObject<EntityType<LichEntity>> LICH = REGISTRY.register("lich", () -> EntityType.Builder
			.of(LichEntity::new, MobCategory.MONSTER).sized(0.6F, 2.3F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "lich").toString()));
	public static final RegistryObject<EntityType<OgreEntity>> OGRE = REGISTRY.register("ogre", () -> EntityType.Builder
			.of(OgreEntity::new, MobCategory.MONSTER).sized(0.9F, 2.8F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "ogre").toString()));
	public static final RegistryObject<EntityType<SpiderNestEntity>> SPIDER_NEST = REGISTRY.register("spider_nest", () -> EntityType.Builder
			.of(SpiderNestEntity::new, MobCategory.MONSTER).sized(1.2F, 1.8F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "spider_nest").toString()));
	public static final RegistryObject<EntityType<MeltyMonsterEntity>> MELTY_MONSTER = REGISTRY.register("melty_monster", () -> EntityType.Builder
			.of(MeltyMonsterEntity::new, MobCategory.MONSTER).fireImmune().sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "melty_monster").toString()));
	public static final RegistryObject<EntityType<CursedDollEntity>> CURSED_DOLL = REGISTRY.register("cursed_doll", () -> EntityType.Builder
			.of(CursedDollEntity::new, MobCategory.MONSTER).sized(0.6F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "cursed_doll").toString()));
	public static final RegistryObject<EntityType<JackFrostEntity>> JACK_FROST = REGISTRY.register("jack_frost", () -> EntityType.Builder
			.of(JackFrostEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "jack_frost").toString()));
	public static final RegistryObject<EntityType<HornetEntity>> HORNET = REGISTRY.register("hornet", () -> EntityType.Builder
			.of(HornetEntity::new, MobCategory.MONSTER).sized(0.6F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "hornet").toString()));
	public static final RegistryObject<EntityType<DullahanEntity>> DULLAHAN = REGISTRY.register("dullahan", () -> EntityType.Builder
			.of(DullahanEntity::new, MobCategory.MONSTER).sized(0.6F, 1.75F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "dullahan").toString()));
	public static final RegistryObject<EntityType<BansheeEntity>> BANSHEE = REGISTRY.register("banshee", () -> EntityType.Builder
			.of(BansheeEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "banshee").toString()));
	public static final RegistryObject<EntityType<AlrauneEntity>> ALRAUNE = REGISTRY.register("alraune", () -> EntityType.Builder
			.of(AlrauneEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "alraune").toString()));
	public static final RegistryObject<EntityType<CatoblepasEntity>> CATOBLEPAS = REGISTRY.register("catoblepas", () -> EntityType.Builder
			.of(CatoblepasEntity::new, MobCategory.MONSTER).sized(0.9F, 1.4F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "catoblepas").toString()));
	public static final RegistryObject<EntityType<ScorpionEntity>> SCORPION = REGISTRY.register("scorpion", () -> EntityType.Builder
			.of(ScorpionEntity::new, MobCategory.MONSTER).sized(1.2F, 0.8F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "scorpion").toString()));
	public static final RegistryObject<EntityType<KashaEntity>> KASHA = REGISTRY.register("kasha", () -> EntityType.Builder
			.of(KashaEntity::new, MobCategory.MONSTER).fireImmune().sized(0.6F, 0.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "kasha").toString()));
	public static final RegistryObject<EntityType<DoguEntity>> DOGU = REGISTRY.register("dogu", () -> EntityType.Builder
			.of(DoguEntity::new, MobCategory.MONSTER).sized(0.6F, 1.99F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "dogu").toString()));
	public static final RegistryObject<EntityType<GhastlySeekerEntity>> GHASTLY_SEEKER = REGISTRY.register("ghastly_seeker", () -> EntityType.Builder
			.of(GhastlySeekerEntity::new, MobCategory.MONSTER).fireImmune().sized(0.9F, 2.9F).setTrackingRange(8).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "ghastly_seeker").toString()));
	public static final RegistryObject<EntityType<RedcapEntity>> REDCAP = REGISTRY.register("redcap", () -> EntityType.Builder
			.of(RedcapEntity::new, MobCategory.MONSTER).sized(0.6F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "redcap").toString()));
	public static final RegistryObject<EntityType<SlimeGirlEntity>> SLIME_GIRL = REGISTRY.register("slime_girl", () -> EntityType.Builder
			.of(SlimeGirlEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "slime_girl").toString()));
	public static final RegistryObject<EntityType<MagicalSlimeEntity>> MAGICAL_SLIME = REGISTRY.register("magical_slime", () -> EntityType.Builder
			.of(MagicalSlimeEntity::new, MobCategory.MONSTER).sized(2.04F, 2.04F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "magical_slime").toString()));
	public static final RegistryObject<EntityType<MonolithEntity>> MONOLITH = REGISTRY.register("monolith", () -> EntityType.Builder
			.of(MonolithEntity::new, MobCategory.MONSTER).sized(0.6F, 1.75F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "monolith").toString()));
	public static final RegistryObject<EntityType<CrimsonSlaughtererEntity>> CRIMSON_SLAUGHTERER = REGISTRY.register("crimson_slaughterer", () -> EntityType.Builder
			.of(CrimsonSlaughtererEntity::new, MobCategory.MONSTER).fireImmune().sized(0.75F, 2.45F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "crimson_slaughterer").toString()));
	public static final RegistryObject<EntityType<DyssomniaEntity>> DYSSOMNIA = REGISTRY.register("dyssomnia", () -> EntityType.Builder
			.of(DyssomniaEntity::new, MobCategory.MONSTER).sized(2.3F, 1.1F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "dyssomnia").toString()));
	public static final RegistryObject<EntityType<SnowCanineEntity>> SNOW_CANINE = REGISTRY.register("snow_canine", () -> EntityType.Builder
			.of(SnowCanineEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "snow_canine").toString()));
	public static final RegistryObject<EntityType<HarpyEntity>> HARPY = REGISTRY.register("harpy", () -> EntityType.Builder
			.of(HarpyEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "harpy").toString()));
	public static final RegistryObject<EntityType<SavagefangEntity>> SAVAGEFANG = REGISTRY.register("savagefang", () -> EntityType.Builder
			.of(SavagefangEntity::new, MobCategory.MONSTER).sized(0.75F, 0.55F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "savagefang").toString()));
	public static final RegistryObject<EntityType<FortressKeeperEntity>> FORTRESS_KEEPER = REGISTRY.register("fortress_keeper", () -> EntityType.Builder
			.of(FortressKeeperEntity::new, MobCategory.MONSTER).fireImmune().sized(1.2F, 2.15F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "fortress_keeper").toString()));
	public static final RegistryObject<EntityType<NecroticReaperEntity>> NECROTIC_REAPER = REGISTRY.register("necrotic_reaper", () -> EntityType.Builder
			.of(NecroticReaperEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "necrotic_reaper").toString()));
	public static final RegistryObject<EntityType<DodomekiEntity>> DODOMEKI = REGISTRY.register("dodomeki", () -> EntityType.Builder
			.of(DodomekiEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "dodomeki").toString()));
	public static final RegistryObject<EntityType<ImpEntity>> IMP = REGISTRY.register("imp", () -> EntityType.Builder
			.of(ImpEntity::new, MobCategory.MONSTER).fireImmune().sized(0.6F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "imp").toString()));

	public static final RegistryObject<EntityType<MagicBulletEntity>> MAGIC_BULLET = REGISTRY.register("magic_bullet", () -> EntityType.Builder
			.<MagicBulletEntity>of(MagicBulletEntity::new, MobCategory.MISC).sized(0.3215F, 0.3215F).setTrackingRange(4).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "magic_bullet").toString()));
	public static final RegistryObject<EntityType<HardSnowballEntity>> HARD_SNOWBALL = REGISTRY.register("hard_snowball", () -> EntityType.Builder
			.<HardSnowballEntity>of(HardSnowballEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(10).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "hard_snowball").toString()));
	public static final RegistryObject<EntityType<PoisonSeedEntity>> POISON_SEED = REGISTRY.register("poison_seed", () -> EntityType.Builder
			.<PoisonSeedEntity>of(PoisonSeedEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(10).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "poison_seed").toString()));
	public static final RegistryObject<EntityType<ThrowableBottleEntity>> THROWABLE_BOTTLE = REGISTRY.register("throwable_bottle", () -> EntityType.Builder
			.<ThrowableBottleEntity>of(ThrowableBottleEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(10).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "throwable_bottle").toString()));
	public static final RegistryObject<EntityType<MagmaBulletEntity>> MAGMA_BULLET = REGISTRY.register("magma_bullet", () -> EntityType.Builder
			.<MagmaBulletEntity>of(MagmaBulletEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).setTrackingRange(4).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "magma_bullet").toString()));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}