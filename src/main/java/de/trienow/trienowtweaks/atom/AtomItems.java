package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.item.ItemAutoFood;
import de.trienow.trienowtweaks.item.ItemAutoLight;
import de.trienow.trienowtweaks.item.ItemWeWand;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author (c) trienow 2016 - 2023
 */
public class AtomItems
{
	private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TrienowTweaks.MODID);

	public static final DeferredItem<ItemWeWand> WE_WAND = ITEMS.register("we_wand", ItemWeWand::new);

	public static final DeferredItem<ItemAutoLight> AUTO_LIGHT = ITEMS.register("auto_light", ItemAutoLight::new);
	public static final DeferredItem<ItemAutoFood> AUTO_FOOD = ITEMS.register("auto_food", ItemAutoFood::new);

	public static void init(IEventBus modEventBus)
	{
		ITEMS.register(modEventBus);
	}
}
