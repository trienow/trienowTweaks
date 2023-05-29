package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.item.BaseItem;
import de.trienow.trienowtweaks.item.ItemAutoFood;
import de.trienow.trienowtweaks.item.ItemAutoLight;
import de.trienow.trienowtweaks.item.ItemWeWand;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AtomItems
{
	@GameRegistry.ObjectHolder("trienowtweaks:auto_light")
	public static final ItemAutoLight itemAutoLight = new ItemAutoLight();
	@GameRegistry.ObjectHolder("trienowtweaks:auto_food")
	public static final ItemAutoFood itemAutoFood = new ItemAutoFood();

	@GameRegistry.ObjectHolder("trienowtweaks:we_wand")
	public static final ItemWeWand itemWeWand = new ItemWeWand();

	public static final BaseItem[] itemList = { itemAutoFood, itemWeWand, itemAutoLight };

	/**
	 * Initializes the Item Models
	 */
	public static void initModels()
	{
		for (BaseItem baseItem : itemList)
		{
			baseItem.initModel();
		}
	}
}
