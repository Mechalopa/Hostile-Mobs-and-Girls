package net.mechalopa.hmag.registry;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.entity.ArurauneEntity;
import net.mechalopa.hmag.entity.BansheeEntity;
import net.mechalopa.hmag.entity.CatoblepasEntity;
import net.mechalopa.hmag.entity.CreeperGirlEntity;
import net.mechalopa.hmag.entity.CrimsonSlaughtererEntity;
import net.mechalopa.hmag.entity.CursedDollEntity;
import net.mechalopa.hmag.entity.DodomekiEntity;
import net.mechalopa.hmag.entity.DoguEntity;
import net.mechalopa.hmag.entity.DrownedGirlEntity;
import net.mechalopa.hmag.entity.DullahanEntity;
import net.mechalopa.hmag.entity.DyssomniaEntity;
import net.mechalopa.hmag.entity.EnderExecutorEntity;
import net.mechalopa.hmag.entity.FortressKeeperEntity;
import net.mechalopa.hmag.entity.GhastlySeekerEntity;
import net.mechalopa.hmag.entity.GhostEntity;
import net.mechalopa.hmag.entity.HarpyEntity;
import net.mechalopa.hmag.entity.HornetEntity;
import net.mechalopa.hmag.entity.HuskGirlEntity;
import net.mechalopa.hmag.entity.ImpEntity;
import net.mechalopa.hmag.entity.JackFrostEntity;
import net.mechalopa.hmag.entity.KashaEntity;
import net.mechalopa.hmag.entity.KoboldEntity;
import net.mechalopa.hmag.entity.LichEntity;
import net.mechalopa.hmag.entity.MagicalSlimeEntity;
import net.mechalopa.hmag.entity.MeltyMonsterEntity;
import net.mechalopa.hmag.entity.MonolithEntity;
import net.mechalopa.hmag.entity.NecroticReaperEntity;
import net.mechalopa.hmag.entity.OgreEntity;
import net.mechalopa.hmag.entity.RedcapEntity;
import net.mechalopa.hmag.entity.SavagefangEntity;
import net.mechalopa.hmag.entity.ScorpionEntity;
import net.mechalopa.hmag.entity.SkeletonGirlEntity;
import net.mechalopa.hmag.entity.SlimeGirlEntity;
import net.mechalopa.hmag.entity.SnowCanineEntity;
import net.mechalopa.hmag.entity.SpiderNestEntity;
import net.mechalopa.hmag.entity.StoneularEntity;
import net.mechalopa.hmag.entity.StrayGirlEntity;
import net.mechalopa.hmag.entity.WitherGhostEntity;
import net.mechalopa.hmag.entity.WitherSkeletonGirlEntity;
import net.mechalopa.hmag.entity.ZombieGirlEntity;
import net.mechalopa.hmag.entity.projectile.HardSnowballEntity;
import net.mechalopa.hmag.entity.projectile.MagicBulletEntity;
import net.mechalopa.hmag.entity.projectile.MagmaBulletEntity;
import net.mechalopa.hmag.entity.projectile.NemesisBulletEntity;
import net.mechalopa.hmag.entity.projectile.PoisonSeedEntity;
import net.mechalopa.hmag.entity.projectile.ThrowableBottleEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes
{
	private static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, HMaG.MODID);

	public static final RegistryObject<EntityType<ZombieGirlEntity>> ZOMBIE_GIRL = REGISTRY.register("zombie_girl", () -> EntityType.Builder
			.of(ZombieGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "zombie_girl").toString()));
	public static final RegistryObject<EntityType<HuskGirlEntity>> HUSK_GIRL = REGISTRY.register("husk_girl", () -> EntityType.Builder
			.of(HuskGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "husk_girl").toString()));
	public static final RegistryObject<EntityType<DrownedGirlEntity>> DROWNED_GIRL = REGISTRY.register("drowned_girl", () -> EntityType.Builder
			.of(DrownedGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "drowned_girl").toString()));
	public static final RegistryObject<EntityType<SkeletonGirlEntity>> SKELETON_GIRL = REGISTRY.register("skeleton_girl", () -> EntityType.Builder
			.of(SkeletonGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.99F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "skeleton_girl").toString()));
	public static final RegistryObject<EntityType<WitherSkeletonGirlEntity>> WITHER_SKELETON_GIRL = REGISTRY.register("wither_skeleton_girl", () -> EntityType.Builder
			.of(WitherSkeletonGirlEntity::new, EntityClassification.MONSTER).fireImmune().immuneTo(Blocks.WITHER_ROSE).sized(0.7F, 2.4F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "wither_skeleton_girl").toString()));
	public static final RegistryObject<EntityType<StrayGirlEntity>> STRAY_GIRL = REGISTRY.register("stray_girl", () -> EntityType.Builder
			.of(StrayGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.99F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "stray_girl").toString()));
	public static final RegistryObject<EntityType<CreeperGirlEntity>> CREEPER_GIRL = REGISTRY.register("creeper_girl", () -> EntityType.Builder
			.of(CreeperGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "creeper_girl").toString()));
	public static final RegistryObject<EntityType<GhostEntity>> GHOST = REGISTRY.register("ghost", () -> EntityType.Builder
			.of(GhostEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "ghost").toString()));
	public static final RegistryObject<EntityType<WitherGhostEntity>> WITHER_GHOST = REGISTRY.register("wither_ghost", () -> EntityType.Builder
			.of(WitherGhostEntity::new, EntityClassification.MONSTER).fireImmune().immuneTo(Blocks.WITHER_ROSE).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "wither_ghost").toString()));
	public static final RegistryObject<EntityType<EnderExecutorEntity>> ENDER_EXECUTOR = REGISTRY.register("ender_executor", () -> EntityType.Builder
			.of(EnderExecutorEntity::new, EntityClassification.MONSTER).sized(0.6F, 2.9F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "ender_executor").toString()));
	public static final RegistryObject<EntityType<KoboldEntity>> KOBOLD = REGISTRY.register("kobold", () -> EntityType.Builder
			.of(KoboldEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "kobold").toString()));
	public static final RegistryObject<EntityType<LichEntity>> LICH = REGISTRY.register("lich", () -> EntityType.Builder
			.of(LichEntity::new, EntityClassification.MONSTER).sized(0.6F, 2.3F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "lich").toString()));
	public static final RegistryObject<EntityType<OgreEntity>> OGRE = REGISTRY.register("ogre", () -> EntityType.Builder
			.of(OgreEntity::new, EntityClassification.MONSTER).sized(0.9F, 2.8F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "ogre").toString()));
	public static final RegistryObject<EntityType<SpiderNestEntity>> SPIDER_NEST = REGISTRY.register("spider_nest", () -> EntityType.Builder
			.of(SpiderNestEntity::new, EntityClassification.MONSTER).sized(0.9F, 1.8F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "spider_nest").toString()));
	public static final RegistryObject<EntityType<MeltyMonsterEntity>> MELTY_MONSTER = REGISTRY.register("melty_monster", () -> EntityType.Builder
			.of(MeltyMonsterEntity::new, EntityClassification.MONSTER).fireImmune().sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "melty_monster").toString()));
	public static final RegistryObject<EntityType<CursedDollEntity>> CURSED_DOLL = REGISTRY.register("cursed_doll", () -> EntityType.Builder
			.of(CursedDollEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "cursed_doll").toString()));
	public static final RegistryObject<EntityType<JackFrostEntity>> JACK_FROST = REGISTRY.register("jack_frost", () -> EntityType.Builder
			.of(JackFrostEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "jack_frost").toString()));
	public static final RegistryObject<EntityType<HornetEntity>> HORNET = REGISTRY.register("hornet", () -> EntityType.Builder
			.of(HornetEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "hornet").toString()));
	public static final RegistryObject<EntityType<DullahanEntity>> DULLAHAN = REGISTRY.register("dullahan", () -> EntityType.Builder
			.of(DullahanEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.75F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "dullahan").toString()));
	public static final RegistryObject<EntityType<BansheeEntity>> BANSHEE = REGISTRY.register("banshee", () -> EntityType.Builder
			.of(BansheeEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "banshee").toString()));
	public static final RegistryObject<EntityType<ArurauneEntity>> ARURAUNE = REGISTRY.register("aruraune", () -> EntityType.Builder
			.of(ArurauneEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "aruraune").toString()));
	public static final RegistryObject<EntityType<CatoblepasEntity>> CATOBLEPAS = REGISTRY.register("catoblepas", () -> EntityType.Builder
			.of(CatoblepasEntity::new, EntityClassification.MONSTER).sized(0.9F, 1.4F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "catoblepas").toString()));
	public static final RegistryObject<EntityType<ScorpionEntity>> SCORPION = REGISTRY.register("scorpion", () -> EntityType.Builder
			.of(ScorpionEntity::new, EntityClassification.MONSTER).sized(1.2F, 0.8F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "scorpion").toString()));
	public static final RegistryObject<EntityType<KashaEntity>> KASHA = REGISTRY.register("kasha", () -> EntityType.Builder
			.of(KashaEntity::new, EntityClassification.MONSTER).fireImmune().sized(0.6F, 0.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "kasha").toString()));
	public static final RegistryObject<EntityType<DoguEntity>> DOGU = REGISTRY.register("dogu", () -> EntityType.Builder
			.of(DoguEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.99F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "dogu").toString()));
	public static final RegistryObject<EntityType<GhastlySeekerEntity>> GHSATLY_SHEEKER = REGISTRY.register("ghastly_seeker", () -> EntityType.Builder
			.of(GhastlySeekerEntity::new, EntityClassification.MONSTER).fireImmune().sized(0.9F, 2.9F).setTrackingRange(8).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "ghastly_seeker").toString()));
	public static final RegistryObject<EntityType<RedcapEntity>> REDCAP = REGISTRY.register("redcap", () -> EntityType.Builder
			.of(RedcapEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "redcap").toString()));
	public static final RegistryObject<EntityType<SlimeGirlEntity>> SLIME_GIRL = REGISTRY.register("slime_girl", () -> EntityType.Builder
			.of(SlimeGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "slime_girl").toString()));
	public static final RegistryObject<EntityType<MagicalSlimeEntity>> MAGICAL_SLIME = REGISTRY.register("magical_slime", () -> EntityType.Builder
			.of(MagicalSlimeEntity::new, EntityClassification.MONSTER).sized(2.04F, 2.04F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "magical_slime").toString()));
	public static final RegistryObject<EntityType<MonolithEntity>> MONOLITH = REGISTRY.register("monolith", () -> EntityType.Builder
			.of(MonolithEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.75F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "monolith").toString()));
	public static final RegistryObject<EntityType<CrimsonSlaughtererEntity>> CRIMSON_SLAUGHTERER = REGISTRY.register("crimson_slaughterer", () -> EntityType.Builder
			.of(CrimsonSlaughtererEntity::new, EntityClassification.MONSTER).fireImmune().sized(0.75F, 2.45F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "crimson_slaughterer").toString()));
	public static final RegistryObject<EntityType<DyssomniaEntity>> DYSSOMNIA = REGISTRY.register("dyssomnia", () -> EntityType.Builder
			.of(DyssomniaEntity::new, EntityClassification.MONSTER).sized(2.3F, 1.1F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "dyssomnia").toString()));
	public static final RegistryObject<EntityType<SnowCanineEntity>> SNOW_CANINE = REGISTRY.register("snow_canine", () -> EntityType.Builder
			.of(SnowCanineEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "snow_canine").toString()));
	public static final RegistryObject<EntityType<StoneularEntity>> STONEULAR = REGISTRY.register("stoneular", () -> EntityType.Builder
			.of(StoneularEntity::new, EntityClassification.MONSTER).sized(0.9F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "stoneular").toString()));
	public static final RegistryObject<EntityType<HarpyEntity>> HARPY = REGISTRY.register("harpy", () -> EntityType.Builder
			.of(HarpyEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "harpy").toString()));
	public static final RegistryObject<EntityType<SavagefangEntity>> SAVAGEFANG = REGISTRY.register("savagefang", () -> EntityType.Builder
			.of(SavagefangEntity::new, EntityClassification.MONSTER).sized(0.75F, 0.55F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "savagefang").toString()));
	public static final RegistryObject<EntityType<FortressKeeperEntity>> FORTRESS_KEEPER = REGISTRY.register("fortress_keeper", () -> EntityType.Builder
			.of(FortressKeeperEntity::new, EntityClassification.MONSTER).fireImmune().sized(1.2F, 2.15F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "fortress_keeper").toString()));
	public static final RegistryObject<EntityType<NecroticReaperEntity>> NECROTIC_REAPER = REGISTRY.register("necrotic_reaper", () -> EntityType.Builder
			.of(NecroticReaperEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "necrotic_reaper").toString()));
	public static final RegistryObject<EntityType<DodomekiEntity>> DODOMEKI = REGISTRY.register("dodomeki", () -> EntityType.Builder
			.of(DodomekiEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "dodomeki").toString()));
	public static final RegistryObject<EntityType<ImpEntity>> IMP = REGISTRY.register("imp", () -> EntityType.Builder
			.of(ImpEntity::new, EntityClassification.MONSTER).fireImmune().sized(0.6F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "imp").toString()));

	public static final RegistryObject<EntityType<MagicBulletEntity>> MAGIC_BULLET = REGISTRY.register("magic_bullet", () -> EntityType.Builder
			.<MagicBulletEntity>of(MagicBulletEntity::new, EntityClassification.MISC).sized(0.3215F, 0.3215F).setTrackingRange(4).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "magic_bullet").toString()));
	public static final RegistryObject<EntityType<HardSnowballEntity>> HARD_SNOWBALL = REGISTRY.register("hard_snowball", () -> EntityType.Builder
			.<HardSnowballEntity>of(HardSnowballEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(10).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "hard_snowball").toString()));
	public static final RegistryObject<EntityType<PoisonSeedEntity>> POISON_SEED = REGISTRY.register("poison_seed", () -> EntityType.Builder
			.<PoisonSeedEntity>of(PoisonSeedEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(10).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "poison_seed").toString()));
	public static final RegistryObject<EntityType<ThrowableBottleEntity>> THROWABLE_BOTTLE = REGISTRY.register("throwable_bottle", () -> EntityType.Builder
			.<ThrowableBottleEntity>of(ThrowableBottleEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(10).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "throwable_bottle").toString()));
	public static final RegistryObject<EntityType<MagmaBulletEntity>> MAGMA_BULLET = REGISTRY.register("magma_bullet", () -> EntityType.Builder
			.<MagmaBulletEntity>of(MagmaBulletEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).setTrackingRange(4).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "magma_bullet").toString()));
	public static final RegistryObject<EntityType<NemesisBulletEntity>> NEMESIS_BULLET = REGISTRY.register("nemesis_bullet", () -> EntityType.Builder
			.<NemesisBulletEntity>of(NemesisBulletEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).setTrackingRange(4).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "nemesis_bullet").toString()));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}