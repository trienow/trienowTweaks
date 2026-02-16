package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.blocks.*;
import de.trienow.trienowtweaks.blocks.flavors.FlavorRailroadTruss;
import de.trienow.trienowtweaks.blocks.flavors.FlavorStreetlamp;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author trienow 2019 - 2026
 */
public class AtomBlocks
{
	private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TrienowTweaks.MODID);

	public static final DeferredBlock<BlockGenericLight> GENERIC_LIGHT = BLOCKS.register("generic_light", BlockGenericLight::new);
	public static final DeferredBlock<BlockInvisibleWall> INVISIBLE_WALL = BLOCKS.register("invisible_wall", BlockInvisibleWall::new);

	public static final DeferredBlock<BlockRailroadTruss> RAILROAD_TRUSS_WOODEN = register(FlavorRailroadTruss.WOODEN);
	public static final DeferredBlock<BlockRailroadTruss> RAILROAD_TRUSS_BLACK = register(FlavorRailroadTruss.BLACK);
	public static final DeferredBlock<BlockRailroadTruss> RAILROAD_TRUSS_BRIGHT = register(FlavorRailroadTruss.BRIGHT);
	public static final DeferredBlock<BlockRailroadTruss> RAILROAD_TRUSS_PURPLE = register(FlavorRailroadTruss.PURPLE);

	public static final DeferredBlock<Block> STREETLAMP_FLESH = BLOCKS.register("streetlamp_" + FlavorStreetlamp.FLESH, BlockStreetlamp::new);
	public static final DeferredBlock<Block> STREETLAMP_FIRE = BLOCKS.register("streetlamp_" + FlavorStreetlamp.FIRE, BlockStreetlamp::new);
	public static final DeferredBlock<Block> STREETLAMP_GLOWSTONE = BLOCKS.register("streetlamp_" + FlavorStreetlamp.GLOWSTONE, BlockStreetlamp::new);

	public static final DeferredBlock<Block> FAKE_FIRE = BLOCKS.register("fake_fire", BlockFakeFire::new);
	public static final DeferredBlock<Block> MINECART_KILLER = BLOCKS.register("minecart_killer", BlockMinecartKiller::new);
	public static final DeferredBlock<Block> COMPACT_CRAFTER = BLOCKS.register("compact_crafter", BlockCompactCrafter::new);
	public static final DeferredBlock<Block> ITEM_DETECTOR = BLOCKS.register("item_detector", BlockItemDetector::new);
	public static final DeferredBlock<Block> ENTITY_PROHIBITATOR = BLOCKS.register("entity_prohibitator", BlockEntityProhibitator::new);
	public static final DeferredBlock<Block> TORCH_SQUARED = BLOCKS.register("torch_squared", BlockTorchSquared::new);

	public static void init(IEventBus modEventBus)
	{
		BLOCKS.register(modEventBus);
	}

	private static DeferredBlock<BlockRailroadTruss> register(FlavorRailroadTruss flavor)
	{
		return BLOCKS.register("railroad_truss_" + flavor, () -> new BlockRailroadTruss(flavor));
	}
}
