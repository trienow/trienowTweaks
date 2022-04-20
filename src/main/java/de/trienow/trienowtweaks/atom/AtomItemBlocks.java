package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AtomItemBlocks
{
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TrienowTweaks.MODID);
	private static final Item.Properties ITEM_PROPERTIES_TAB = new Item.Properties().tab(TrienowTweaks.ITEM_GROUP);

	public static final RegistryObject<Item> GENERIC_LIGHT = register(AtomBlocks.GENERIC_LIGHT);
	public static final RegistryObject<Item> INVISIBLE_WALL = register(AtomBlocks.INVISIBLE_WALL);

	public static final RegistryObject<Item> RAILROAD_TRUSS_WOODEN = register(AtomBlocks.RAILROAD_TRUSS_WOODEN);
	public static final RegistryObject<Item> RAILROAD_TRUSS_BLACK = register(AtomBlocks.RAILROAD_TRUSS_BLACK);
	public static final RegistryObject<Item> RAILROAD_TRUSS_BRIGHT = register(AtomBlocks.RAILROAD_TRUSS_BRIGHT);
	public static final RegistryObject<Item> RAILROAD_TRUSS_PURPLE = register(AtomBlocks.RAILROAD_TRUSS_PURPLE);

	public static final RegistryObject<Item> STREETLAMP_FLESH = register(AtomBlocks.STREETLAMP_FLESH);
	public static final RegistryObject<Item> STREETLAMP_FIRE = register(AtomBlocks.STREETLAMP_FIRE);
	public static final RegistryObject<Item> STREETLAMP_GLOWSTONE = register(AtomBlocks.STREETLAMP_GLOWSTONE);

	public static final RegistryObject<Item> FAKE_FIRE = register(AtomBlocks.FAKE_FIRE);
	public static final RegistryObject<Item> MINECART_KILLER = register(AtomBlocks.MINECART_KILLER);
	public static final RegistryObject<Item> COMPACT_CRAFTER = register(AtomBlocks.COMPACT_CRAFTER);
	public static final RegistryObject<Item> ITEM_DETECTOR = register(AtomBlocks.ITEM_DETECTOR);
	public static final RegistryObject<Item> ENTITY_PROHIBITATOR = register(AtomBlocks.ENTITY_PROHIBITATOR);
	public static final RegistryObject<Item> TORCH_SOLAMNIA = register(AtomBlocks.TORCH_SOLAMNIA);

	public static void init(IEventBus modEventBus)
	{
		ITEMS.register(modEventBus);
	}

	private static RegistryObject<Item> register(RegistryObject<Block> b)
	{
		return ITEMS.register(b.getId().getPath(), () -> new BlockItem(b.get(), ITEM_PROPERTIES_TAB));
	}
}
