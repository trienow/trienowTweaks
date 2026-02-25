package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.item.ItemAutoFood;
import de.trienow.trienowtweaks.item.ItemAutoLight;
import de.trienow.trienowtweaks.item.ItemWeWand;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author (c) trienow 2016 - 2023
 */
public class AtomItems
{
	public static final List<DeferredItem<Item>> ITEMSLIST = new ArrayList<>();

	private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TrienowTweaks.MODID);

	public static final DeferredItem<ItemWeWand> WE_WAND = register("we_wand", ItemWeWand::new);

	public static final DeferredItem<ItemAutoLight> AUTO_LIGHT = ITEMS.register("auto_light", ItemAutoLight::new);
	public static final DeferredItem<ItemAutoFood> AUTO_FOOD = ITEMS.register("auto_food", ItemAutoFood::new);

	private static <I extends Item> DeferredItem<I> register(String name, Function<ResourceLocation, I> func)
	{
		final DeferredItem<I> itemHolder = ITEMS.register(name, func);
		ITEMSLIST.add((DeferredItem<Item>) itemHolder);
		return itemHolder;
	}

	public static void init(IEventBus modEventBus)
	{
		ITEMS.register(modEventBus);
	}
}
