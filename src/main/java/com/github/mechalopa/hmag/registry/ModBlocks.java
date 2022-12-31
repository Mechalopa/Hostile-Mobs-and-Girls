package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.world.level.block.HealingSandBlock;
import com.github.mechalopa.hmag.world.level.block.ModBlock;
import com.github.mechalopa.hmag.world.level.block.PoisonSandBlock;
import com.github.mechalopa.hmag.world.level.block.ReinforcedBlock;
import com.github.mechalopa.hmag.world.level.block.ReinforcedGlassBlock;
import com.github.mechalopa.hmag.world.level.block.ThornSandBlock;
import com.github.mechalopa.hmag.world.level.block.TintedReinforcedGlassBlock;
import com.github.mechalopa.hmag.world.level.block.WitherSandBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks
{
	private static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, HMaG.MODID);

	public static final RegistryObject<Block> EVIL_CRYSTAL_BLOCK = REGISTRY.register("evil_crystal_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(1.25F, 1.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> SOUL_POWDER_BLOCK = REGISTRY.register("soul_powder_block", () -> new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_CYAN).strength(0.5F).lightLevel((state) -> 5).sound(SoundType.SAND)));
	public static final RegistryObject<Block> ANCIENT_STONE_BLOCK = REGISTRY.register("ancient_stone_block", () -> new ModBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.25F, 4.2F).lightLevel((state) -> 5).sound(SoundType.STONE), new ModBlock.Properties().enchantPowerBonus(1.0F)));
	public static final RegistryObject<Block> BURNING_CORE_BLOCK = REGISTRY.register("burning_core_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(1.0F, 5.0F).lightLevel((state) -> 10).sound(SoundType.STONE)));
	public static final RegistryObject<Block> ENDER_EYE_BLOCK = REGISTRY.register("ender_eye_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_CYAN).strength(1.0F, 5.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> PRISMARINE_CRYSTAL_BLOCK = REGISTRY.register("prismarine_crystal_block", () -> new Block(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.QUARTZ).strength(0.5F).lightLevel((state) -> 15).sound(SoundType.GLASS)));
	public static final RegistryObject<Block> SCUTE_BLOCK = REGISTRY.register("scute_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> NETHERITE_SCRAP_BLOCK = REGISTRY.register("netherite_scrap_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(30.0F, 1200.0F).sound(SoundType.ANCIENT_DEBRIS)));
	public static final RegistryObject<Block> NETHER_STAR_BLOCK = REGISTRY.register("nether_star_block", () -> new ModBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.QUARTZ).requiresCorrectToolForDrops().strength(2.0F, 2000.0F).lightLevel((state) -> 10).sound(SoundType.STONE), new ModBlock.Properties().enchantPowerBonus(5.0F)));
	public static final RegistryObject<Block> SHULKER_SHELL_BLOCK = REGISTRY.register("shulker_shell_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_MAGENTA).strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> NAUTILUS_SHELL_BLOCK = REGISTRY.register("nautilus_shell_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).strength(1.0F, 5.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> HEART_OF_THE_SEA_BLOCK = REGISTRY.register("heart_of_the_sea_block", () -> new ModBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_CYAN).requiresCorrectToolForDrops().strength(2.0F, 6.0F).lightLevel((state) -> 10).sound(SoundType.STONE), new ModBlock.Properties().setConduitFrame()));
	public static final RegistryObject<Block> ECHO_SHARD_BLOCK = REGISTRY.register("echo_shard_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(1.5F).lightLevel((state) -> 2).sound(SoundType.STONE)));
	public static final RegistryObject<Block> THORN_SAND = REGISTRY.register("thorn_sand", () -> new ThornSandBlock(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_GREEN).strength(0.5F).speedFactor(0.4F).sound(SoundType.SOUL_SAND).isValidSpawn(ModBlocks::always).isRedstoneConductor(ModBlocks::always).isViewBlocking(ModBlocks::always).isSuffocating(ModBlocks::always)));
	public static final RegistryObject<Block> POISON_SAND = REGISTRY.register("poison_sand", () -> new PoisonSandBlock(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_MAGENTA).strength(0.5F).speedFactor(0.4F).sound(SoundType.SOUL_SAND).isValidSpawn(ModBlocks::always).isRedstoneConductor(ModBlocks::always).isViewBlocking(ModBlocks::always).isSuffocating(ModBlocks::always)));
	public static final RegistryObject<Block> WITHER_SAND = REGISTRY.register("wither_sand", () -> new WitherSandBlock(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_GRAY).strength(0.5F).speedFactor(0.4F).sound(SoundType.SOUL_SAND).isValidSpawn(ModBlocks::always).isRedstoneConductor(ModBlocks::always).isViewBlocking(ModBlocks::always).isSuffocating(ModBlocks::always)));
	public static final RegistryObject<Block> HEALING_SAND = REGISTRY.register("healing_sand", () -> new HealingSandBlock(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_PINK).strength(0.5F).speedFactor(0.4F).sound(SoundType.SOUL_SAND).isValidSpawn(ModBlocks::always).isRedstoneConductor(ModBlocks::always).isViewBlocking(ModBlocks::always).isSuffocating(ModBlocks::always)));
	public static final RegistryObject<Block> REINFORCED_BLOCK = REGISTRY.register("reinforced_block", () -> new ReinforcedBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(5.0F, 3600000.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> REINFORCED_GLASS = REGISTRY.register("reinforced_glass", () -> new ReinforcedGlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(5.0F, 3600000.0F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(ModBlocks::never).isRedstoneConductor(ModBlocks::never).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never)));
	public static final RegistryObject<Block> TINTED_REINFORCED_GLASS = REGISTRY.register("tinted_reinforced_glass", () -> new TintedReinforcedGlassBlock(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.COLOR_GRAY).strength(5.0F, 3600000.0F).sound(SoundType.GLASS).noOcclusion().isValidSpawn(ModBlocks::never).isRedstoneConductor(ModBlocks::never).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never)));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}

	private static boolean never(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entityType)
	{
		return false;
	}

	private static boolean always(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entityType)
	{
		return true;
	}

	private static boolean always(BlockState state, BlockGetter getter, BlockPos pos)
	{
		return true;
	}

	private static boolean never(BlockState state, BlockGetter getter, BlockPos pos)
	{
		return false;
	}
}