package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.tiles.TEItemDetector;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static de.trienow.trienowtweaks.main.TrienowTweaks.MODID;

/**
 * @author (c) trienow 2018 - 2026
 */
@EventBusSubscriber(modid = MODID)
public class AtomTiles
{
	private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TrienowTweaks.MODID);

	public static final Supplier<BlockEntityType<TEItemDetector>> ITEM_DETECTOR = register("item_detector", TEItemDetector::new, AtomBlocks.ITEM_DETECTOR);

	public static void init(IEventBus modEventBus)
	{
		TILES.register(modEventBus);
	}

	private static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> tileSupplier, Supplier<Block> block)
	{
		return TILES.register("te_" + name, () -> new BlockEntityType<>(tileSupplier, block.get()));
	}
}
