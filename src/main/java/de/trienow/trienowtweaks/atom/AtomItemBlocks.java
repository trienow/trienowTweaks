package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author trienow 2016 - 2023
 */
public class AtomItemBlocks
{
	private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TrienowTweaks.MODID);
	public static final DeferredItem<BlockItem> GENERIC_LIGHT = register(AtomBlocks.GENERIC_LIGHT);
	public static final DeferredItem<BlockItem> INVISIBLE_WALL = register(AtomBlocks.INVISIBLE_WALL);

	public static final DeferredItem<BlockItem> RAILROAD_TRUSS_WOODEN = register(AtomBlocks.RAILROAD_TRUSS_WOODEN);
	public static final DeferredItem<BlockItem> RAILROAD_TRUSS_BLACK = register(AtomBlocks.RAILROAD_TRUSS_BLACK);
	public static final DeferredItem<BlockItem> RAILROAD_TRUSS_BRIGHT = register(AtomBlocks.RAILROAD_TRUSS_BRIGHT);
	public static final DeferredItem<BlockItem> RAILROAD_TRUSS_PURPLE = register(AtomBlocks.RAILROAD_TRUSS_PURPLE);

	public static final DeferredItem<BlockItem> STREETLAMP_FLESH = register(AtomBlocks.STREETLAMP_FLESH);
	public static final DeferredItem<BlockItem> STREETLAMP_FIRE = register(AtomBlocks.STREETLAMP_FIRE);
	public static final DeferredItem<BlockItem> STREETLAMP_GLOWSTONE = register(AtomBlocks.STREETLAMP_GLOWSTONE);

	public static final DeferredItem<BlockItem> FAKE_FIRE = register(AtomBlocks.FAKE_FIRE);
	public static final DeferredItem<BlockItem> MINECART_KILLER = register(AtomBlocks.MINECART_KILLER);
	public static final DeferredItem<BlockItem> COMPACT_CRAFTER = register(AtomBlocks.COMPACT_CRAFTER);
	public static final DeferredItem<BlockItem> ITEM_DETECTOR = register(AtomBlocks.ITEM_DETECTOR);
	public static final DeferredItem<BlockItem> ENTITY_PROHIBITATOR = register(AtomBlocks.ENTITY_PROHIBITATOR);
	public static final DeferredItem<BlockItem> TORCH_SQUARED = register(AtomBlocks.TORCH_SQUARED);

	public static void init(IEventBus modEventBus)
	{
		ITEMS.register(modEventBus);
	}

	private static DeferredItem<BlockItem> register(DeferredBlock<? extends Block> b)
	{
		return ITEMS.registerBlockItem(b.getId().getPath(), b);
	}
}
