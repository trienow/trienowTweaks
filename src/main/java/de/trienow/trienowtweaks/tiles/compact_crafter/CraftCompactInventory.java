package de.trienow.trienowtweaks.tiles.compact_crafter;

import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;

/**
 * @author (c) trienow 2019 - 2022
 */
class CraftCompactInventory extends CraftingContainer
{
	private ItemStack stack;
	private final int size;

	public CraftCompactInventory(int size)
	{
		super(null, size, size);
		this.size = size;
		this.stack = ItemStack.EMPTY;
	}

	@Override
	public ItemStack getItem(int pIndex)
	{
		if (pIndex >= (size * size))
			return ItemStack.EMPTY;
		else
			return stack;
	}

	@Override
	public void setItem(int pIndex, ItemStack pStack)
	{
		this.stack = pStack;
	}

	public void fillGrid(ItemStack stack)
	{
		this.stack = stack;
	}

	/**
	 * @return The size / amount of slots
	 */
	public int getSlotCount()
	{
		return size * size;
	}
}