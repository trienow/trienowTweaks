package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public abstract class BaseBlockRailroadTruss extends BaseBlock
{
	public static final PropertyEnum<EnumDisplay> DISPLAY = PropertyEnum.create("display", BlockRailroadTrussWooden.EnumDisplay.class);

	public BaseBlockRailroadTruss(String name)
	{
		super(name, Material.WOOD, true);
		setDefaultState(blockState.getBaseState().withProperty(DISPLAY, EnumDisplay.TOP_NS));
	}

	// METHODS
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		neighborChanged(state, worldIn, pos, this, pos);
		worldIn.notifyNeighborsOfStateChange(pos.up(), this, true);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		EnumFacing enf = placer.getHorizontalFacing().getOpposite();

		if (enf == EnumFacing.EAST || enf == EnumFacing.WEST)
			return getDefaultState().withProperty(DISPLAY, EnumDisplay.TOP_EW);

		return getDefaultState();
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		if (worldIn.isRemote)
			return;

		EnumFacing face = state.getValue(DISPLAY).getFacing();
		IBlockState ibs = getDefaultState();

		//@fm:off
		if (AtomBlocks.TRUSS.contains(worldIn.getBlockState(pos.up()).getBlock().getDefaultState()) || worldIn.getBlockState(pos.up()).isFullCube())
		{
			if (face == EnumFacing.EAST || face == EnumFacing.WEST)
				ibs = ibs.withProperty(DISPLAY, EnumDisplay.MIDDLE_EW);
			else
				ibs = ibs.withProperty(DISPLAY, EnumDisplay.MIDDLE_NS);
		}
		else if (!AtomBlocks.TRUSS.contains(worldIn.getBlockState(pos.offset(face)).getBlock().getDefaultState()) &&
				(AtomBlocks.TRUSS.containsStateProperty(worldIn.getBlockState(pos.offset(face).down()), DISPLAY, getDisplay(face, EnumDisplayType.SLANT)) || AtomBlocks.TRUSS.containsStateProperty(worldIn.getBlockState(pos.offset(face).down()), DISPLAY, getDisplay(face, EnumDisplayType.TOP))))
		{
			ibs = ibs.withProperty(DISPLAY, getDisplay(face, EnumDisplayType.SLANT));
		}
		else if (!AtomBlocks.TRUSS.contains(worldIn.getBlockState(pos.offset(face.getOpposite())).getBlock().getDefaultState()) &&
				(AtomBlocks.TRUSS.containsStateProperty(worldIn.getBlockState(pos.offset(face.getOpposite()).down()), DISPLAY, getDisplay(face.getOpposite(), EnumDisplayType.SLANT)) || AtomBlocks.TRUSS.containsStateProperty(worldIn.getBlockState(pos.offset(face.getOpposite()).down()), DISPLAY, getDisplay(face, EnumDisplayType.TOP))))
		{
			ibs = ibs.withProperty(DISPLAY, getDisplay(face.getOpposite(), EnumDisplayType.SLANT));
		}
		else if (face == EnumFacing.EAST || face == EnumFacing.WEST)
			ibs = ibs.withProperty(DISPLAY, EnumDisplay.TOP_EW);
		//@fm:on

		if (ibs != state)
		{
			worldIn.setBlockState(pos, ibs, 2);
			worldIn.notifyNeighborsOfStateChange(pos.up(), this, true);
		}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.notifyNeighborsOfStateChange(pos.up(), this, true);
	}

	/**
	 * Gets the correct {@link EnumDisplay} value, according to the {@link EnumFacing} and {@link EnumDisplayType}.
	 *
	 * @param face The compass point, the block is facing
	 * @param type The style of the Block
	 * @return Returns the correct {@link EnumDisplay} value, according to the {@link EnumFacing} and {@link EnumDisplayType}.
	 */
	public EnumDisplay getDisplay(EnumFacing face, EnumDisplayType type)
	{
		switch (type)
		{
			case MIDDLE:
				switch (face)
				{
					case WEST:
					case EAST:
						return EnumDisplay.MIDDLE_EW;
					case SOUTH:
					default:
						return EnumDisplay.MIDDLE_NS;
				}

			case SLANT:
				switch (face)
				{
					case WEST:
						return EnumDisplay.SLANTED_W;
					case EAST:
						return EnumDisplay.SLANTED_E;
					case SOUTH:
						return EnumDisplay.SLANTED_S;
					default:
						return EnumDisplay.SLANTED_N;
				}

			case TOP:
				switch (face)
				{
					case WEST:
					case EAST:
						return EnumDisplay.TOP_EW;
					case SOUTH:
					default:
						return EnumDisplay.TOP_NS;
				}

			default:
				TrienowTweaks.logger.warn("[TT] INVALID S**** MATE!!!!");
				return EnumDisplay.TOP_NS;
		}
	}

	// FUCKING BLOCKSTATES
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(DISPLAY, EnumDisplay.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(DISPLAY).getMetadata();
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		switch (state.getValue(DISPLAY))
		{
			case MIDDLE_EW:
				return getDefaultState().withProperty(DISPLAY, EnumDisplay.MIDDLE_NS);
			case MIDDLE_NS:
				return getDefaultState().withProperty(DISPLAY, EnumDisplay.MIDDLE_EW);
			case SLANTED_E:
				return getDefaultState().withProperty(DISPLAY, EnumDisplay.SLANTED_S);
			case SLANTED_N:
				return getDefaultState().withProperty(DISPLAY, EnumDisplay.SLANTED_E);
			case SLANTED_S:
				return getDefaultState().withProperty(DISPLAY, EnumDisplay.SLANTED_W);
			case SLANTED_W:
				return getDefaultState().withProperty(DISPLAY, EnumDisplay.SLANTED_N);
			case TOP_EW:
				return getDefaultState().withProperty(DISPLAY, EnumDisplay.TOP_NS);
			default:
				return getDefaultState().withProperty(DISPLAY, EnumDisplay.TOP_EW);

		}
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, DISPLAY);
	}

	// RENDERING
	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return side == EnumFacing.UP;
	}

	@Override
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
	{
		return 1;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced)
	{
		Block blockType = Block.getBlockFromItem(stack.getItem());

		if (blockType == this)
			addInformation(tooltip);
	}

	protected abstract void addInformation(List<String> tooltip);

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return FULL_BLOCK_AABB;
	}

	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}

	// TRASH LOL
	public enum EnumDisplay implements IStringSerializable
	{
		TOP_NS(0, "top_ns", EnumFacing.NORTH),
		TOP_EW(1, "top_ew", EnumFacing.EAST),
		MIDDLE_NS(2, "middle_ns", EnumFacing.NORTH),
		MIDDLE_EW(3, "middle_ew", EnumFacing.EAST),
		SLANTED_N(4, "slanted_n", EnumFacing.NORTH),
		SLANTED_E(5, "slanted_e", EnumFacing.EAST),
		SLANTED_S(6, "slanted_s", EnumFacing.SOUTH),
		SLANTED_W(7, "slanted_w", EnumFacing.WEST);

		private static final BlockRailroadTrussWooden.EnumDisplay[] META_LOOKUP = new BlockRailroadTrussWooden.EnumDisplay[values().length];
		private final int meta;
		private final String name;
		private final EnumFacing face;

		EnumDisplay(int meta, String name, EnumFacing face)
		{
			this.meta = meta;
			this.name = name;
			this.face = face;
		}

		@Override
		public String getName()
		{
			return name;
		}

		public int getMetadata()
		{
			return meta;
		}

		public EnumFacing getFacing()
		{
			return face;
		}

		public static BlockRailroadTrussWooden.EnumDisplay byMetadata(int meta)
		{
			if (meta < 0 || meta >= META_LOOKUP.length)
			{
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		@Override
		public String toString()
		{
			return name;
		}

		static
		{
			for (BlockRailroadTrussWooden.EnumDisplay brted : values())
			{
				META_LOOKUP[brted.getMetadata()] = brted;
			}
		}
	}

	public enum EnumDisplayType
	{
		TOP,
		MIDDLE,
		SLANT
	}
}
