package net.mechalopa.hmag.registry;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.block.HealingSandBlock;
import net.mechalopa.hmag.block.ModBlock;
import net.mechalopa.hmag.block.PoisonSandBlock;
import net.mechalopa.hmag.block.ReinforcedBlock;
import net.mechalopa.hmag.block.ReinforcedGlassBlock;
import net.mechalopa.hmag.block.ThornSandBlock;
import net.mechalopa.hmag.block.WitherSandBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks
{
	private static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, HMaG.MODID);

	public static final RegistryObject<Block> EVIL_CRYSTAL_BLOCK = REGISTRY.register("evil_crystal_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(1.25F, 1.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> SOUL_POWDER_BLOCK = REGISTRY.register("soul_powder_block", () -> new Block(AbstractBlock.Properties.of(Material.SAND, MaterialColor.COLOR_CYAN).strength(0.5F).lightLevel((state) -> 5).sound(SoundType.SAND)));
	public static final RegistryObject<Block> ANCIENT_STONE_BLOCK = REGISTRY.register("ancient_stone_block", () -> new ModBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(1.25F, 4.2F).lightLevel((state) -> 5).sound(SoundType.STONE), new ModBlock.Properties().enchantPowerBonus(1.0F)));
	public static final RegistryObject<Block> NETHER_STAR_BLOCK = REGISTRY.register("nether_star_block", () -> new ModBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.QUARTZ).requiresCorrectToolForDrops().strength(2.0F, 2000.0F).lightLevel((state) -> 10).sound(SoundType.STONE), new ModBlock.Properties().enchantPowerBonus(5.0F)));
	public static final RegistryObject<Block> HEART_OF_THE_SEA_BLOCK = REGISTRY.register("heart_of_the_sea_block", () -> new ModBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_CYAN).requiresCorrectToolForDrops().strength(2.0F, 6.0F).lightLevel((state) -> 10).sound(SoundType.STONE), new ModBlock.Properties().setConduitFrame()));
	public static final RegistryObject<Block> THORN_SAND = REGISTRY.register("thorn_sand", () -> new ThornSandBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.COLOR_GREEN).strength(0.5F).speedFactor(0.4F).sound(SoundType.SOUL_SAND).harvestTool(ToolType.SHOVEL).isValidSpawn(ModBlocks::always).isRedstoneConductor(ModBlocks::always).isViewBlocking(ModBlocks::always).isSuffocating(ModBlocks::always)));
	public static final RegistryObject<Block> POISON_SAND = REGISTRY.register("poison_sand", () -> new PoisonSandBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.COLOR_MAGENTA).strength(0.5F).speedFactor(0.4F).sound(SoundType.SOUL_SAND).harvestTool(ToolType.SHOVEL).isValidSpawn(ModBlocks::always).isRedstoneConductor(ModBlocks::always).isViewBlocking(ModBlocks::always).isSuffocating(ModBlocks::always)));
	public static final RegistryObject<Block> WITHER_SAND = REGISTRY.register("wither_sand", () -> new WitherSandBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.COLOR_GRAY).strength(0.5F).speedFactor(0.4F).sound(SoundType.SOUL_SAND).harvestTool(ToolType.SHOVEL).isValidSpawn(ModBlocks::always).isRedstoneConductor(ModBlocks::always).isViewBlocking(ModBlocks::always).isSuffocating(ModBlocks::always)));
	public static final RegistryObject<Block> HEALING_SAND = REGISTRY.register("healing_sand", () -> new HealingSandBlock(AbstractBlock.Properties.of(Material.SAND, MaterialColor.COLOR_PINK).strength(0.5F).speedFactor(0.4F).sound(SoundType.SOUL_SAND).harvestTool(ToolType.SHOVEL).isValidSpawn(ModBlocks::always).isRedstoneConductor(ModBlocks::always).isViewBlocking(ModBlocks::always).isSuffocating(ModBlocks::always)));
	public static final RegistryObject<Block> REINFORCED_BLOCK = REGISTRY.register("reinforced_block", () -> new ReinforcedBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(5.0F, 3600000.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> REINFORCED_GLASS = REGISTRY.register("reinforced_glass", () -> new ReinforcedGlassBlock(AbstractBlock.Properties.of(Material.GLASS, MaterialColor.COLOR_BLACK).strength(5.0F, 3600000.0F).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE).noOcclusion().isValidSpawn(ModBlocks::never).isRedstoneConductor(ModBlocks::never).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never)));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}

	private static boolean never(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity)
	{
		return false;
	}

	private static boolean always(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity)
	{
		return true;
	}

	private static boolean always(BlockState state, IBlockReader reader, BlockPos pos)
	{
		return true;
	}

	private static boolean never(BlockState state, IBlockReader reader, BlockPos pos)
	{
		return false;
	}
}