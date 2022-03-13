package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.blocks.states.StateStreetlamp;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import org.jetbrains.annotations.Nullable;

/**
 * @author trienow 2017 - 2022
 */
public class BlockStreetlamp extends BaseBlock
{
	private static final Properties PROPS = Properties.of(Material.HEAVY_METAL)
			.strength(5)
			.lightLevel((blockState) -> 15)
			.noOcclusion();

	private static final EnumProperty<StateStreetlamp> SOLAMNIA = EnumProperty.create("solamnia", StateStreetlamp.class);

	private static final VoxelShape SHAPE_COLLISION = Shapes.create(0.375, 0.0, 0.375, 0.625, 1.0, 0.625);
	private static final VoxelShape SHAPE_BB = Shapes.create(0.1875, 0.0, 0.1875, 0.8125, 1.0, 0.8125);

	/**
	 * Constructs a new streetlamp block
	 */
	public BlockStreetlamp()
	{
		super(PROPS);
		this.registerDefaultState(this.defaultBlockState().setValue(SOLAMNIA, StateStreetlamp.TOP));
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
	{
		return SHAPE_BB;
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
		pBuilder.add(SOLAMNIA);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext)
	{
		Level level = pContext.getLevel();
		BlockPos pos = pContext.getClickedPos();
		BlockState state = defaultBlockState(); // <- Is StateStreetlamp.TOP

		if (pContext.getPlayer().getPose() != Pose.CROUCHING && level.getBlockState(pos.above()).isAir() && level.getBlockState(pos.above(2)).isAir())
		{
			BlockState middle = state.setValue(SOLAMNIA, StateStreetlamp.MIDDLE);
			BlockState bottom = state.setValue(SOLAMNIA, StateStreetlamp.BOTTOM);

			level.setBlock(pos.above(), middle, Block.UPDATE_ALL);
			level.setBlock(pos.above(2), state, Block.UPDATE_ALL);

			state = bottom;
		}

		return state;
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable)
	{
		return true;
	}

	/**
	 * Checks if a {@link BlockStreetlamp} exists at the given {@link BlockPos}
	 *
	 * @param pLevel The world object to use to check
	 * @param pPos   The position at which a check should be performed
	 * @return Returns <code>true</code> when a {@link BlockStreetlamp} is present
	 */
	@SuppressWarnings("BooleanMethodIsAlwaysInverted")
	private static boolean isStreetlamp(LevelAccessor pLevel, BlockPos pPos)
	{
		return pLevel.getBlockState(pPos).getBlock() instanceof BlockStreetlamp;
	}

	/**
	 * Sets AIR at the given {@link BlockPos}
	 *
	 * @param pLevel The world object to edit
	 * @param pPos   The position at which air should be set
	 */
	private static void setAir(LevelAccessor pLevel, BlockPos pPos)
	{
		pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
	}

	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
	{
		if (level.isClientSide() || !(blockIn instanceof BlockStreetlamp))
		{
			return;
		}

		boolean up = fromPos.equals(pos.above()) && !isStreetlamp(level, pos.above());
		boolean down = fromPos.equals(pos.below()) && !isStreetlamp(level, pos.below());

		switch (state.getValue(SOLAMNIA))
		{
			case BOTTOM:
				if (up)
				{
					setAir(level, pos);
				}
				break;

			case MIDDLE:
				if (down || up)
				{
					setAir(level, pos);
				}
				break;

			case TOP:
				if (down)
				{
					setAir(level, pos);
				}
				break;
		}
	}
}
