package de.trienow.trienowtweaks.tiles.compact_crafter;

import de.trienow.trienowtweaks.atom.AtomTiles;
import de.trienow.trienowtweaks.blocks.BlockCompactCrafter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

/**
 * @author (c) trienow 2019 - 2023
 */
public class TECompactCrafter extends BlockEntity
{
	private static final Logger LOG = LogManager.getLogger();

	private static final CraftCompactInventory craft2 = new CraftCompactInventory(2);
	private static final CraftCompactInventory craft3 = new CraftCompactInventory(3);

	private byte tickThreshold = 0;

	public TECompactCrafter(BlockPos pWorldPosition, BlockState pBlockState)
	{
		super(AtomTiles.COMPACT_CRAFTER.get(), pWorldPosition, pBlockState);
	}

	public void tickServer()
	{
		if (tickThreshold % 200 == 0)
		{
			tickThreshold = 0;
			checkInventories();
		}

		tickThreshold++;
	}

	private void checkInventories()
	{
		Direction blockFacing = getBlockState().getValue(BlockCompactCrafter.FACING);
		IItemHandler itemHandlerI = getNeighborItemHandler(level, worldPosition, blockFacing.getClockWise(Direction.Axis.Y));
		IItemHandler itemHandlerO = getNeighborItemHandler(level, worldPosition, blockFacing.getCounterClockWise(Direction.Axis.Y));

		if (itemHandlerI == null || itemHandlerO == null)
		{
			return;
		}

		int itemHandlerISlots = itemHandlerI.getSlots();
		for (int i = 0; i < itemHandlerISlots; i++)
		{
			ItemStack inputStackSim = itemHandlerI.extractItem(i, Integer.MAX_VALUE, true);

			if (!inputStackSim.isEmpty())
			{
				CraftReturnPackage crp = getRecipe(level, inputStackSim);

				if (!crp.outputStack.isEmpty())
				{
					// Try to find all other instances of the same Item. Why: When I leave nine diamonds in nine different slots, they won't be turned into a block.
					int[] slotsWithInputItem = new int[itemHandlerISlots - i + 1];
					int maxExtractableItems = inputStackSim.getCount();
					slotsWithInputItem[0] = maxExtractableItems;

					for (int j = 1; i + j < itemHandlerISlots; j++)
					{
						ItemStack groupableStackSim = itemHandlerI.extractItem(i + j, Integer.MAX_VALUE, true);

						if (ItemStack.isSame(inputStackSim, groupableStackSim))
						{
							slotsWithInputItem[j] = groupableStackSim.getCount();
							maxExtractableItems += slotsWithInputItem[j];
						}
					}

					// Calculate the amt to craft and output.
					int countToExtract = maxExtractableItems - (maxExtractableItems % crp.slotCount);
					int craftingOperations = countToExtract / crp.slotCount;

					ItemStack outputStack = crp.outputStack.copy();
					outputStack.setCount(crp.outputStack.getCount() * craftingOperations);
					outputStack.shrink(ItemHandlerHelper.insertItem(itemHandlerO, outputStack, true).getCount());

					// Extract the Items and craft the output
					if (!outputStack.isEmpty() && outputStack.getCount() > 0)
					{
						countToExtract = (outputStack.getCount() * crp.slotCount) / crp.outputStack.getCount();

						for (int j = 0; j < slotsWithInputItem.length; j++)
						{
							if (slotsWithInputItem[j] > 0)
								countToExtract -= itemHandlerI.extractItem(i + j, countToExtract, false).getCount();
						}
						ItemHandlerHelper.insertItem(itemHandlerO, outputStack, false);
					}
				}
			}
		}
	}

	/**
	 * Gets the resulting recipe for the {@link ItemStack}, laid out in a 2x2 or 3x3 pattern in the crafting table.
	 *
	 * @param world      The Minecraft {@link Level} Object. Is used to find the matching crafting recipe
	 * @param inputStack The {@link ItemStack} to fill the grid with
	 * @return See: {@link CraftReturnPackage}
	 */
	@Nonnull
	private static CraftReturnPackage getRecipe(Level world, ItemStack inputStack)
	{
		CraftReturnPackage crp = new CraftReturnPackage();
		try
		{
			RecipeManager rm = world.getServer().getRecipeManager();
			craft2.fillGrid(inputStack);
			Optional<CraftingRecipe> recipe = rm.getRecipeFor(RecipeType.CRAFTING, craft2, world);
			if (recipe.isPresent())
			{
				crp.slotCount = craft2.getSlotCount();
				crp.outputStack = recipe.get().getResultItem();
			}
			else
			{
				craft3.fillGrid(inputStack);
				recipe = rm.getRecipeFor(RecipeType.CRAFTING, craft3, world);
				if (recipe.isPresent())
				{
					crp.slotCount = craft3.getSlotCount();
					crp.outputStack = recipe.get().getResultItem();
				}
			}
		}
		catch (Exception ex)
		{
			LOG.error("Failed while getting recipe", ex);
		}
		return crp;
	}

	/**
	 * Gets the {@link IItemHandler} from the neighbor Block, if there is one.
	 *
	 * @param world The Minecraft {@link Level} Object. Is used to get the {@link BlockEntity} from the neighbor {@link Block}
	 * @param pos   The position of the current {@link Block}
	 * @param face  The direction of the {@link Block} to get the {@link IItemHandler} from.
	 * @return The {@link IItemHandler} or <code>null</code> if nothing is found.
	 */
	@Nullable
	private static IItemHandler getNeighborItemHandler(@Nullable Level world, BlockPos pos, Direction face)
	{
		IItemHandler iih = null;

		if (world != null)
		{
			pos = pos.offset(face.getNormal());
			BlockEntity te = world.getBlockEntity(pos);

			if (te != null)
			{
				iih = te.getCapability(ForgeCapabilities.ITEM_HANDLER, face).orElse(null);
			}
		}

		return iih;
	}
}
