package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.tiles.TEItemDetector;
import de.trienow.trienowtweaks.tiles.compact_crafter.TECompactCrafter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

import static de.trienow.trienowtweaks.main.TrienowTweaks.MODID;

/**
 * @author (c) trienow 2018 - 2023
 */
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtomTiles
{
	private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TrienowTweaks.MODID);

	public static final RegistryObject<BlockEntityType<TECompactCrafter>> COMPACT_CRAFTER = register("compact_crafter", TECompactCrafter::new, AtomBlocks.COMPACT_CRAFTER);
	public static final RegistryObject<BlockEntityType<TEItemDetector>> ITEM_DETECTOR = register("item_detector", TEItemDetector::new, AtomBlocks.ITEM_DETECTOR);

	public static void init(IEventBus modEventBus)
	{
		TILES.register(modEventBus);
	}

	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> tileSupplier, RegistryObject<Block> block)
	{
		DeferredRegister.
		//The null in build is suggested by the forge docs https://docs.minecraftforge.net/en/1.19.x/blockentities/#registering
		return TILES.register("te_" + name, () -> BlockEntityType.Builder.of(tileSupplier, block.get()).build(null));
	}
}
