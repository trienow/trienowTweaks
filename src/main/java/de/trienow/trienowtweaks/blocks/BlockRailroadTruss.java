package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.blocks.flavors.FlavorRailroadTruss;
import de.trienow.trienowtweaks.blocks.states.StateRailroadTruss;
import de.trienow.trienowtweaks.blocks.states.StateRailroadTrussType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.IPlantable;
import org.jetbrains.annotations.Nullable;

/**
 * @author trienow 2017 - 2023
 */
public class BlockRailroadTruss extends BaseBlock
{
	private static final Properties PROPS = BlockBehaviour.Properties.of()
			.mapColor(MapColor.WOOD)
			.ignitedByLava()
			.strength(1, 10)
			.noOcclusion();
	private static final EnumProperty<StateRailroadTruss> DISPLAY = EnumProperty.create("display", StateRailroadTruss.class);

	public BlockRailroadTruss(FlavorRailroadTruss flavor)
	{
		super(PROPS);
		tooltipCount = flavor.getTooltipCount();

		this.registerDefaultState(this.defaultBlockState().setValue(DISPLAY, StateRailroadTruss.TOP_NS));
	}

	@Override
	public RenderShape getRenderShape(BlockState pState)
	{
		return RenderShape.MODEL;
	}

	@Override
	public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving)
	{
		neighborChanged(pState, pLevel, pPos, this, pPos, pIsMoving);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
	{
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(DISPLAY);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext)
	{
		StateRailroadTruss display = pContext.getHorizontalDirection().getAxis() == Direction.Axis.Z ? StateRailroadTruss.TOP_NS : StateRailroadTruss.TOP_EW;
		return this.defaultBlockState().setValue(DISPLAY, display);
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable)
	{
		return true;
	}

	@Override
	public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos)
	{
		//Otherwise, the sides get weird pitch black shadows when a truss is paced on top
		return 1.0f;
	}

	private static StateRailroadTruss getSlant(Direction face)
	{
		return switch (face)
		{
			case WEST -> StateRailroadTruss.SLANTED_W;
			case EAST -> StateRailroadTruss.SLANTED_E;
			case SOUTH -> StateRailroadTruss.SLANTED_S;
			default -> StateRailroadTruss.SLANTED_N;
		};
	}

	private static boolean isTruss(Level worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos).getBlock() instanceof BlockRailroadTruss;
	}

	private static boolean isTruss(Level worldIn, BlockPos pos, StateRailroadTrussType type)
	{
		BlockState bState = worldIn.getBlockState(pos);
		return bState.hasProperty(DISPLAY) && bState.getValue(DISPLAY).getType() == type;
	}

	@Override
	public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving)
	{
		if (pLevel.isClientSide())
		{
			return;
		}

		Direction face = pState.getValue(DISPLAY).getFacing();
		Direction faceOpposite = face.getOpposite();
		boolean facingNS = face.getAxis() == Direction.Axis.Z;
		BlockState ibs = defaultBlockState();

		if (isTruss(pLevel, pPos.above()))
		{
			ibs = ibs.setValue(DISPLAY, facingNS ? StateRailroadTruss.MIDDLE_NS : StateRailroadTruss.MIDDLE_EW);
		}
		else if (!isTruss(pLevel, pPos.relative(face)) &&
				(isTruss(pLevel, pPos.relative(face).below(), StateRailroadTrussType.SLANT) || isTruss(pLevel, pPos.relative(face).below(), StateRailroadTrussType.TOP)))
		{
			ibs = ibs.setValue(DISPLAY, getSlant(face));
		}
		else if (!isTruss(pLevel, pPos.relative(faceOpposite)) &&
				(isTruss(pLevel, pPos.relative(faceOpposite).below(), StateRailroadTrussType.SLANT) || isTruss(pLevel, pPos.relative(faceOpposite).below(), StateRailroadTrussType.TOP)))
		{
			ibs = ibs.setValue(DISPLAY, getSlant(faceOpposite));
		}
		else if (!facingNS)
		{
			ibs = ibs.setValue(DISPLAY, StateRailroadTruss.TOP_EW);
		}

		if (ibs != pState)
		{
			pLevel.setBlock(pPos, ibs, Block.UPDATE_ALL);
		}
	}

	@Override
	public void updateIndirectNeighbourShapes(BlockState pState, LevelAccessor pLevel, BlockPos pPos, int pFlags, int pRecursionLeft)
	{
		pLevel.blockUpdated(pPos.above(), this);
	}

	@Override
	public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pDirection)
	{
		boolean result = false;
		if (!pAdjacentBlockState.is(this) && pAdjacentBlockState.isSolid())
		{
			result = true;
		}
		else if (pDirection != Direction.UP && pAdjacentBlockState.hasProperty(DISPLAY))
		{
			StateRailroadTruss thisState = pState.getValue(DISPLAY);
			Direction thisFacing = thisState.getFacing();
			StateRailroadTruss otherState = pAdjacentBlockState.getValue(DISPLAY);
			Direction otherFacing = otherState.getFacing();

			if (otherFacing.getAxis() == thisFacing.getAxis())
			{
				boolean bothAreSlants = thisState.getType() == StateRailroadTrussType.SLANT && otherState.getType() == StateRailroadTrussType.SLANT;
				boolean bothSameFacing = otherFacing == thisFacing;
				result = !bothAreSlants && bothSameFacing;
			}
		}

		return result;
	}
}
