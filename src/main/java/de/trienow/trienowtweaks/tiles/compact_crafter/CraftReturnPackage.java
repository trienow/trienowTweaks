package de.trienow.trienowtweaks.tiles.compact_crafter;

import net.minecraft.world.item.ItemStack;

/**
 * @author (c) trienow 2019
 */
class CraftReturnPackage
{
	/**
	 * The grid size used to craft the outputStack
	 */
	public int slotCount;

	/**
	 * The resulting output stack after crafting
	 */
	public ItemStack outputStack = ItemStack.EMPTY;

	public CraftReturnPackage()
	{
		slotCount = 4;
	}
}