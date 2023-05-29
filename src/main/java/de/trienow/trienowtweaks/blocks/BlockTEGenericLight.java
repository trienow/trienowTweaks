package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.tiles.TELightEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockTEGenericLight extends BaseBlock implements ITileEntityProvider
{
	public BlockTEGenericLight()
	{
		super("generic_light_te", new MaterialTransparent(MapColor.AIR), false);
		setLightLevel(1);
		setLightOpacity(0);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}

	@Override
	public boolean isAir(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return false;
	}

	@Override
	public boolean isCollidable()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return Block.NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
	{
		return false;
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!worldIn.isRemote)
		{
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TELightEntity)
			{
				TELightEntity bte = (TELightEntity) te;

				if (bte.posArray != null && bte.posArray.length == 3)
				{
					IBlockState bsInWorld = worldIn.getBlockState(new BlockPos(bte.posArray[0], bte.posArray[1], bte.posArray[2])).getBlock().getDefaultState();
					IBlockState bsTs = AtomBlocks.blockTorchSolamnia.getDefaultState();

					if (bsInWorld != bsTs)
					{
						worldIn.setBlockToAir(pos);
					}
					else
						worldIn.scheduleUpdate(pos, this, 500);
				}
				else
					worldIn.setBlockToAir(pos);
			}
			else
				worldIn.setBlockToAir(pos);
		}
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleUpdate(pos, this, 500);
		super.onBlockAdded(worldIn, pos, state);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TELightEntity();
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}

	@Override
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
}
