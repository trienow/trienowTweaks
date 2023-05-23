package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.blocks.states.StateGenericLight;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

/**
 * @author (c) trienow 2016 - 2023
 */
public class BlockTorchSolamnia extends BaseBlock
{
	private static final Properties PROPS = Properties.of(Material.DECORATION)
			.noCollission()
			.noOcclusion()
			.strength(0)
			.lightLevel((blockState) -> 15)
			.sound(SoundType.WOOD);

	private static final DirectionProperty FACING = BlockStateProperties.FACING;

	private static final VoxelShape SHAPE_BB_UP = Shapes.create(0.438D, 0.000D, 0.438D, 0.563D, 0.625D, 0.563D);
	private static final VoxelShape SHAPE_BB_DOWN = Shapes.create(0.438D, 0.375D, 0.438D, 0.563D, 1.000D, 0.563D);
	private static final VoxelShape SHAPE_BB_NORTH = Shapes.create(0.400D, 0.150D, 0.650D, 0.600D, 0.850D, 1.000D);
	private static final VoxelShape SHAPE_BB_EAST = Shapes.create(0.000D, 0.150D, 0.400D, 0.350D, 0.850D, 0.600D);
	private static final VoxelShape SHAPE_BB_SOUTH = Shapes.create(0.400D, 0.150D, 0.000D, 0.600D, 0.850D, 0.350D);
	private static final VoxelShape SHAPE_BB_WEST = Shapes.create(0.650D, 0.150D, 0.400D, 1.000D, 0.850D, 0.600D);
	private static final VoxelShape SHAPE_COLLISION = Shapes.empty();
	private static final Direction[] FIX_PLACEMENT_TRIES = new Direction[] { Direction.UP, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN };

	public BlockTorchSolamnia()
	{
		super(PROPS);
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.UP));
		tooltipCount = 3;
	}

	private static VoxelShape getShape(Direction facing)
	{
		return switch (facing)
				{
					case DOWN -> SHAPE_BB_DOWN;
					case EAST -> SHAPE_BB_EAST;
					case NORTH -> SHAPE_BB_NORTH;
					case SOUTH -> SHAPE_BB_SOUTH;
					case WEST -> SHAPE_BB_WEST;
					default -> SHAPE_BB_UP;
				};
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
	{
		return getShape(pState.getValue(FACING));
	}

	@Override
	public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
	{
		return SHAPE_COLLISION;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
	{
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(FACING);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext)
	{
		return this.defaultBlockState().setValue(FACING, pContext.getClickedFace());
	}

	@Override
	public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack)
	{
		for (StateGenericLight remote : StateGenericLight.values())
		{
			BlockPos offset = pPos.offset(remote.getAnchorOffset());
			if (pLevel.getBlockState(offset).isAir())
			{
				pLevel.setBlock(offset, AtomBlocks.GENERIC_LIGHT.get().defaultBlockState().setValue(BlockGenericLight.ANCHOR, remote), Block.UPDATE_ALL);
			}
		}
	}

	@Override
	public void wasExploded(Level pLevel, BlockPos pPos, Explosion pExplosion)
	{
		removeTickingGenericLights(pLevel, pPos);
	}

	@Override
	public void destroy(LevelAccessor pLevel, BlockPos pPos, BlockState pState)
	{
		removeTickingGenericLights(pLevel, pPos);
	}

	@Override
	public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos)
	{
		BlockState stateOut = pState;
		Direction thisFacing = pState.getValue(FACING);
		Direction anchoredToFace = thisFacing.getOpposite();

		// If the torch is pointing up (i.e. anchored to the bottom block) we only want to react to those events
		if (anchoredToFace == pDirection && !canBeOnFace(pLevel, pCurrentPos, thisFacing))
		{
			// Now that we know, that we can't stay on the currently fixed face, let's find another one!
			stateOut = Blocks.AIR.defaultBlockState(); // <- Otherwise make it to air.

			for (Direction thisNewFacing : FIX_PLACEMENT_TRIES)
			{
				if (thisFacing != thisNewFacing && canBeOnFace(pLevel, pCurrentPos, thisNewFacing))
				{
					stateOut = pState.setValue(FACING, thisNewFacing);
					break;
				}
			}
		}

		return stateOut;
	}

	private static boolean canBeOnFace(LevelAccessor level, BlockPos thisPos, Direction thisDirection)
	{
		return canSupportCenter(level, thisPos, thisDirection.getOpposite());
	}

	private static void removeTickingGenericLights(LevelAccessor world, BlockPos pos)
	{
		for (StateGenericLight remote : StateGenericLight.values())
		{
			BlockPos offset = pos.offset(remote.getAnchorOffset());
			BlockState bState = world.getBlockState(offset);
			if (bState.hasProperty(BlockGenericLight.ANCHOR) && bState.getValue(BlockGenericLight.ANCHOR) != StateGenericLight.NONE)
			{
				world.setBlock(offset, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
			}
		}
	}
}
