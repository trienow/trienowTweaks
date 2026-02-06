package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.armor.ArmorDrToast;
import de.trienow.trienowtweaks.armor.ArmorKnight;
import de.trienow.trienowtweaks.item.ItemAutoFood;
import de.trienow.trienowtweaks.item.ItemAutoLight;
import de.trienow.trienowtweaks.item.ItemWeWand;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

/**
 * @author (c) trienow 2016 - 2023
 */
@SuppressWarnings("SameParameterValue")
public class AtomItems
{
	private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TrienowTweaks.MODID);

	public static final DeferredItem<ItemWeWand> WE_WAND = ITEMS.register("we_wand", ItemWeWand::new);

	public static final DeferredItem<ItemAutoLight> AUTO_LIGHT = ITEMS.register("auto_light", ItemAutoLight::new);
	public static final DeferredItem<ItemAutoFood> AUTO_FOOD = ITEMS.register("auto_food", ItemAutoFood::new);

	public static final DeferredItem<ArmorItem> DRTOAST_HEAD = register("drtoast", ArmorItem.Type.HELMET, ArmorDrToast::new);

	public static final DeferredItem<ArmorItem> KNIGHT_HEAD = register("knight", ArmorItem.Type.HELMET, ArmorKnight::new);
	public static final DeferredItem<ArmorItem> KNIGHT_CHEST = register("knight", ArmorItem.Type.CHESTPLATE, ArmorKnight::new);
	public static final DeferredItem<ArmorItem> KNIGHT_LEGS = register("knight", ArmorItem.Type.LEGGINGS, ArmorKnight::new);
	public static final DeferredItem<ArmorItem> KNIGHT_FEET = register("knight", ArmorItem.Type.BOOTS, ArmorKnight::new);

	public static void init(IEventBus modEventBus)
	{
		ITEMS.register(modEventBus);
	}

	private static DeferredItem<ArmorItem> register(String baseName, ArmorItem.Type slot, Function<ArmorItem.Type, ArmorItem> creator)
	{
		switch (slot)
		{
			case HELMET -> baseName += "_helmet";
			case CHESTPLATE -> baseName += "_chestplate";
			case LEGGINGS -> baseName += "_leggings";
			case BOOTS -> baseName += "_boots";
		}

		return ITEMS.register(baseName, () -> creator.apply(slot));
	}
}
