package de.trienow.trienowtweaks.blocks.compactCrafter;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

class CraftingInventory extends InventoryCrafting
{
	private ItemStack stack;
	private final int size;

	CraftingInventory(int size)
	{
		//noinspection ConstantConditions
		super(null, size, size);
		this.size = size;
		this.stack = ItemStack.EMPTY;
	}

	@Override
	public ItemStack getStackInRowAndColumn(int row, int column)
	{
		if (row >= size || column >= size)
			return ItemStack.EMPTY;
		else
			return stack;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		if (index >= (size * size))
			return ItemStack.EMPTY;
		else
			return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		this.stack = stack;
	}

	public void fillGrid(ItemStack stack)
	{
		this.stack = stack;
	}
}
