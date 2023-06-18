package de.trienow.trienowtweaks.tiles.compact_crafter;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;

/**
 * @author trienow 2019 - 2023
 */
class CraftCompactInventory implements CraftingContainer
{
	private ItemStack stack;
	private final int size;

	public CraftCompactInventory(int size)
	{
		this.size = size;
		this.stack = ItemStack.EMPTY;
	}

	@Override
	public int getContainerSize()
	{
		return this.size;
	}

	@Override
	public boolean isEmpty()
	{
		return false;
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

	@Override
	public void setChanged()
	{
		//Don't know what to do here
	}

	@Override
	public boolean stillValid(Player p_18946_)
	{
		return false;
	}

	/**
	 * Prevents accidental calling of the "removeItem" function, which accesses the menu field, which I initialize with null.
	 *
	 * @param pIndex ignored.
	 * @param pCount ignored.
	 */
	@Override
	public ItemStack removeItem(int pIndex, int pCount)
	{
		return stack.copy();
	}

	@Override
	public ItemStack removeItemNoUpdate(int p_18951_)
	{
		return null;
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

	@Override
	public int getWidth()
	{
		return this.size;
	}

	@Override
	public int getHeight()
	{
		return this.size;
	}

	@Override
	public List<ItemStack> getItems()
	{
		return Collections.emptyList();
	}

	@Override
	public void clearContent()
	{
		this.stack = ItemStack.EMPTY;
	}

	@Override
	public void fillStackedContents(StackedContents pStackedContents)
	{

	}
}