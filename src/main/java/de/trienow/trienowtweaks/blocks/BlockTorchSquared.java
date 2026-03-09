package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.blocks.states.StateGenericLight;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

/**
 * @author trienow 2016 - 2023
 */
public class BlockTorchSquared extends BaseBlock
{
	private static final Properties PROPS = BlockBehaviour.Properties.of()
			.mapColor(MapColor.NONE)
			.noCollission()
			.noOcclusion()
			.strength(0)
			.lightLevel((blockState) -> 15)
			.sound(SoundType.WOOD);

	private static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;

	private static final VoxelShape SHAPE_BB_UP = Shapes.create(0.438D, 0.000D, 0.438D, 0.563D, 0.625D, 0.563D);
	private static final VoxelShape SHAPE_BB_DOWN = Shapes.create(0.438D, 0.375D, 0.438D, 0.563D, 1.000D, 0.563D);
	private static final VoxelShape SHAPE_BB_NORTH = Shapes.create(0.400D, 0.150D, 0.650D, 0.600D, 0.850D, 1.000D);
	private static final VoxelShape SHAPE_BB_EAST = Shapes.create(0.000D, 0.150D, 0.400D, 0.350D, 0.850D, 0.600D);
	private static final VoxelShape SHAPE_BB_SOUTH = Shapes.create(0.400D, 0.150D, 0.000D, 0.600D, 0.850D, 0.350D);
	private static final VoxelShape SHAPE_BB_WEST = Shapes.create(0.650D, 0.150D, 0.400D, 1.000D, 0.850D, 0.600D);
	private static final VoxelShape SHAPE_COLLISION = Shapes.empty();
	private static final Direction[] FIX_PLACEMENT_TRIES = new Direction[] { Direction.UP, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN };

	public BlockTorchSquared(ResourceLocation registryName)
	{
		super(PROPS, registryName);
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
	public void destroy(LevelAccessor pLevel, BlockPos pPos, BlockState pState)
	{
		removeTickingGenericLights(pLevel, pPos);
	}

	@Override protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston)
	{
		Direction thisFacing = state.getValue(FACING); //UP = anchored to the bottom block

		if (!canSupportCenter(level, pos.relative(thisFacing.getOpposite()), thisFacing))
		{
			// Now that we know, that we can't stay on the currently fixed face, let's find another one!
			BlockState stateOut = Blocks.AIR.defaultBlockState(); // <- Otherwise make it to air.

			for (Direction thisNewFacing : FIX_PLACEMENT_TRIES)
			{
				if (thisNewFacing != thisFacing)
				{
					boolean sturdy = canSupportCenter(level, pos.relative(thisNewFacing.getOpposite()), thisNewFacing);
					if (sturdy)
					{
						stateOut = state.setValue(FACING, thisNewFacing);
						break;
					}
				}
			}

			if (stateOut.is(Blocks.AIR))
			{
				dropResources(state, level, pos);
				removeTickingGenericLights(level, pos);
				level.setBlockAndUpdate(pos, stateOut);
			}
			else if (stateOut != state)
			{
				level.setBlockAndUpdate(pos, stateOut);
			}
		}
	}

	@Override public void wasExploded(ServerLevel level, BlockPos pos, Explosion explosion)
	{
		removeTickingGenericLights(level, pos);
	}

	@Override protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
	{
		Direction thisFacing = state.getValue(FACING);
		return canSupportCenter(level, pos.relative(thisFacing.getOpposite()), thisFacing);
	}

	private static void removeTickingGenericLights(LevelAccessor world, BlockPos pos)
	{
		for (StateGenericLight remote : StateGenericLight.values())
		{
			BlockPos offset = pos.offset(remote.getAnchorOffset());
			BlockState bState = world.getBlockState(offset);
			if (bState.hasProperty(BlockGenericLight.ANCHOR) && bState.getValue(BlockGenericLight.ANCHOR) != StateGenericLight.NONE)
			{
				world.setBlock(offset, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
			}
		}
	}
}
