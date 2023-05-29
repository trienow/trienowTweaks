package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.tiles.TELightEntity;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockTorchSolamnia extends BaseBlock
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public BlockTorchSolamnia()
	{
		super("torch_solamnia", Material.CIRCUITS, true);
		setLightLevel(1);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
		setHardness(0);
		setSoundType(SoundType.WOOD);
		setTickRandomly(true);
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced)
	{
		tooltip.add(TextFormatting.YELLOW + "Lights? Check!");
		tooltip.add(TextFormatting.GOLD + "Camera? Check!");
		tooltip.add(TextFormatting.RED + "Action!");
	}

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
				iBlockState = iBlockState.withProperty(FACING, EnumFacing.SOUTH);
				break;

			case 4:
				iBlockState = iBlockState.withProperty(FACING, EnumFacing.NORTH);
				break;

			case 5:
				iBlockState = iBlockState.withProperty(FACING, EnumFacing.DOWN);
				break;

			default:
				iBlockState = iBlockState.withProperty(FACING, EnumFacing.UP);
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
				i = i | 4;
				break;

			case SOUTH:
				i = i | 3;
				break;

			case DOWN:
				i = i | 5;
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
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		switch (state.getValue(FACING))
		{
			case DOWN:
				//                       X1      Y1      Z1      X2      Y2      Z2
				return new AxisAlignedBB(0.438D, 0.375D, 0.438D, 0.563D, 1.000D, 0.563D);

			case EAST:
				//                       X1      Y1      Z1      X2      Y2      Z2
				return new AxisAlignedBB(0.000D, 0.150D, 0.400D, 0.350D, 0.850D, 0.600D);

			case SOUTH:
				//                       X1      Y1      Z1      X2      Y2      Z2
				return new AxisAlignedBB(0.400D, 0.150D, 0.000D, 0.600D, 0.850D, 0.350D);

			case NORTH:
				//                       X1      Y1      Z1      X2      Y2      Z2
				return new AxisAlignedBB(0.400D, 0.150D, 0.650D, 0.600D, 0.850D, 1.000D);

			case WEST:
				//                       X1      Y1      Z1      X2      Y2      Z2
				return new AxisAlignedBB(0.650D, 0.150D, 0.400D, 1.000D, 0.850D, 0.600D);

			default: // OR UP
				//                       X1      Y1      Z1      X2      Y2      Z2
				return new AxisAlignedBB(0.438D, 0.000D, 0.438D, 0.563D, 0.625D, 0.563D);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
	{
		BlockPos otherBlock = pos.offset(side.getOpposite());

		return worldIn.isSideSolid(otherBlock, side, false);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		if (!world.isRemote)
		{
			BlockPos[] bpAr = new BlockPos[8];
			bpAr[0] = new BlockPos(pos.add(4, 2, 4));
			bpAr[1] = new BlockPos(pos.add(-4, 2, 4));
			bpAr[2] = new BlockPos(pos.add(4, 2, -4));
			bpAr[3] = new BlockPos(pos.add(-4, 2, -4));
			bpAr[4] = new BlockPos(pos.add(8, 2, 0));
			bpAr[5] = new BlockPos(pos.add(-8, 2, 0));
			bpAr[6] = new BlockPos(pos.add(0, 2, 8));
			bpAr[7] = new BlockPos(pos.add(0, 2, -8));

			for (BlockPos bp : bpAr)
			{
				if (world.getBlockState(bp) == Blocks.AIR.getDefaultState())
				{
					world.setBlockState(bp, AtomBlocks.blockTEGenericLight.getDefaultState());
					TileEntity te = world.getTileEntity(bp);
					if (te instanceof TELightEntity)
					{
						TELightEntity bte = (TELightEntity) te;
						bte.posArray = new int[] { pos.getX(), pos.getY(), pos.getZ() };
					}
				}
			}
		}
		// System.out.println(facing);
		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		EnumFacing torchDirection = state.getValue(FACING);

		if (torchDirection != EnumFacing.DOWN && torchDirection != EnumFacing.UP && !canPlaceBlockOnSide(worldIn, pos, torchDirection))
		{
			dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
		}
	}
}
