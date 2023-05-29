package de.trienow.trienowtweaks.blocks.compactCrafter;

import de.trienow.trienowtweaks.blocks.BaseBlock;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Random;

public class BlockCompactCrafter extends BaseBlock
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	private static final CraftingInventory craft2 = new CraftingInventory(2);
	private static final CraftingInventory craft3 = new CraftingInventory(3);

	public BlockCompactCrafter()
	{
		super("compact_crafter", Material.ROCK, true);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	@Override
	public int tickRate(World world)
	{
		return 200;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced)
	{
		tooltip.add(TextFormatting.DARK_GREEN + "Put a Inventory on the left and right of this block.");
		tooltip.add(TextFormatting.YELLOW + "Items in the left Inventory will be crafted in a 2x2");
		tooltip.add(TextFormatting.YELLOW + "or 3x3 pattern." + TextFormatting.RED + " The right Inventory is the output.");
	}

	// BLOCKSTATES :')
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState iBlockState = getDefaultState();

		switch (meta)
		{
			case 1:
				iBlockState = iBlockState.withProperty(FACING, EnumFacing.EAST);
				break;

			case 2:
				iBlockState = iBlockState.withProperty(FACING, EnumFacing.WEST);
				break;

			case 3:
				iBlockState = iBlockState.withProperty(FACING, EnumFacing.NORTH);
				break;

			default:
				iBlockState = iBlockState.withProperty(FACING, EnumFacing.SOUTH);
				break;
		}

		return iBlockState;
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		switch (state.getValue(FACING))
		{
			case EAST:
				i = i | 1;
				break;

			case WEST:
				i = i | 2;
				break;

			case NORTH:
				i = i | 3;
				break;

			default:
				break;
		}
		return i;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING);
	}

	// MECHANICS
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		world.scheduleUpdate(pos, this, tickRate(world));
		super.onBlockAdded(world, pos, state);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (world.isRemote)
			return;

		EnumFacing blockFacing = state.getValue(FACING);
		IItemHandler itemHandlerI = getNeighborItemHandler(world, pos, blockFacing.rotateY());
		IItemHandler itemHandlerO = getNeighborItemHandler(world, pos, blockFacing.rotateYCCW());

		if (itemHandlerI != null && itemHandlerO != null)
		{
			int itemHandlerISlots = itemHandlerI.getSlots();
			for (int i = 0; i < itemHandlerISlots; i++)
			{
				ItemStack inputStackSim = itemHandlerI.extractItem(i, Integer.MAX_VALUE, true);

				if (!inputStackSim.isEmpty())
				{
					CraftReturnPackage crp = getRecipe(world, inputStackSim);

					if (!crp.outputStack.isEmpty())
					{
						//Try to find all other instances of the same Item. Why: When I leave nine diamonds in nine different slots, they won't be turned into a block.
						int[] slotsWithInputItem = new int[itemHandlerISlots - i + 1];
						int maxExtractableItems = inputStackSim.getCount();
						slotsWithInputItem[0] = maxExtractableItems;

						for (int j = 1; i + j < itemHandlerISlots; j++)
						{
							ItemStack groupableStackSim = itemHandlerI.extractItem(i + j, Integer.MAX_VALUE, true);

							if (ItemStack.areItemStacksEqual(inputStackSim, groupableStackSim))
							{
								slotsWithInputItem[j] = groupableStackSim.getCount();
								maxExtractableItems += slotsWithInputItem[j];
							}
						}

						//Calculate the amt to craft and output.
						int countToExtract = maxExtractableItems - (maxExtractableItems % crp.gridSize);
						int craftingOperations = countToExtract / crp.gridSize;

						ItemStack outputStack = crp.outputStack.copy();
						outputStack.setCount(crp.outputStack.getCount() * craftingOperations);
						outputStack.shrink(ItemHandlerHelper.insertItem(itemHandlerO, outputStack, true).getCount());

						//Extract the Items and craft the output
						if (!outputStack.isEmpty() && outputStack.getCount() > 0)
						{
							countToExtract = (outputStack.getCount() * crp.gridSize) / crp.outputStack.getCount();

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

		world.scheduleUpdate(pos, this, tickRate(world));
	}

	/**
	 * Gets the resulting recipe for the {@link ItemStack}, laid out in a 2x2 or 3x3 pattern in the crafting table.
	 *
	 * @param world      The Minecraft {@link World} Object. Is used to find the matching crafting recipe
	 * @param inputStack The {@link ItemStack} to fill the grid with
	 * @return See: {@link CraftReturnPackage}
	 */
	private CraftReturnPackage getRecipe(World world, ItemStack inputStack)
	{
		CraftReturnPackage crp = new CraftReturnPackage();
		try
		{
			craft2.fillGrid(inputStack);
			crp.outputStack = CraftingManager.findMatchingResult(craft2, world);

			if (crp.outputStack.isEmpty())
			{
				craft3.fillGrid(inputStack);
				crp.outputStack = CraftingManager.findMatchingResult(craft3, world);

				if (!crp.outputStack.isEmpty())
					crp.gridSize = 9;
			}
		}
		catch (Exception ex)
		{
			TrienowTweaks.logger.log(Level.ERROR, ex);
		}
		return crp;
	}

	/**
	 * Gets the {@link IItemHandler} from the neighbor Block, if there is one.
	 *
	 * @param world     The Minecraft {@link World} Object. Is used to get the {@link TileEntity} from the neighbor {@link Block}
	 * @param pos       The position of the current {@link Block}
	 * @param direction The direction of the {@link Block} to get the {@link IItemHandler} from.
	 * @return The {@link IItemHandler} or <code>null</code> if nothing is found.
	 */
	private IItemHandler getNeighborItemHandler(World world, BlockPos pos, EnumFacing direction)
	{
		pos = pos.offset(direction);

		TileEntity te = world.getTileEntity(pos);
		if (te != null && te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction))
		{
			return te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, direction);
		}

		return null;
	}
}
