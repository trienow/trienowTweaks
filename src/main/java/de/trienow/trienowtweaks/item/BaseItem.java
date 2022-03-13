package de.trienow.trienowtweaks.item;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.world.item.Item;

/**
 * @author (c) trienow 2016 - 2022
 */
class BaseItem extends Item
{
	BaseItem(Item.Properties props)
	{
		super(props.tab(TrienowTweaks.ITEM_GROUP));
	}
}
