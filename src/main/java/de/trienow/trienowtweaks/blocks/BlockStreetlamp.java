package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.blocks.states.StateStreetlamp;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author (c) trienow
 */
public class BlockStreetlamp extends BaseBlock
{
	private static final Properties PROPS = BlockBehaviour.Properties.of()
			.mapColor(MapColor.METAL)
			.pushReaction(PushReaction.BLOCK)
			.strength(5f)
			.lightLevel((blockState) -> 15)
			.noOcclusion()
			.requiresCorrectToolForDrops();

	private static final EnumProperty<StateStreetlamp> PLACEMENT = EnumProperty.create("placement", StateStreetlamp.class);

	private static final VoxelShape SHAPE_COLLISION = Shapes.create(0.375, 0.0, 0.375, 0.625, 1.0, 0.625);
	private static final VoxelShape SHAPE_BB = Shapes.create(0.1875, 0.0, 0.1875, 0.8125, 1.0, 0.8125);

	/**
	 * Constructs a new streetlamp block
	 */
	public BlockStreetlamp(ResourceLocation registryName)
	{
		super(PROPS, registryName);
		this.registerDefaultState(this.defaultBlockState().setValue(PLACEMENT, StateStreetlamp.TOP));
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
		pBuilder.add(PLACEMENT);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext)
	{
		Level level = pContext.getLevel();
		BlockPos pos = pContext.getClickedPos();
		BlockState state = defaultBlockState(); // <- Is StateStreetlamp.TOP
		Player player = pContext.getPlayer();

		if (player != null && player.getPose() != Pose.CROUCHING && level.getBlockState(pos.above()).isAir() && level.getBlockState(pos.above(2)).isAir())
		{
			BlockState middle = state.setValue(PLACEMENT, StateStreetlamp.MIDDLE);
			BlockState bottom = state.setValue(PLACEMENT, StateStreetlamp.BOTTOM);

			level.setBlock(pos.above(), middle, Block.UPDATE_ALL);
			level.setBlock(pos.above(2), state, Block.UPDATE_ALL);

			state = bottom;
		}

		return state;
	}

	@Override protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random)
	{
		return canSurvive(state, level, pos) ? super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random) : Blocks.AIR.defaultBlockState();
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
	{
		switch (state.getValue(PLACEMENT))
		{
			case StateStreetlamp.TOP:
				return level.getBlockState(pos.below()).getBlock() instanceof BlockStreetlamp;

			case StateStreetlamp.MIDDLE:
				return (level.getBlockState(pos.below()).getBlock() instanceof BlockStreetlamp) &&
						(level.getBlockState(pos.above()).getBlock() instanceof BlockStreetlamp);

			case StateStreetlamp.BOTTOM:
				return level.getBlockState(pos.above()).getBlock() instanceof BlockStreetlamp;

		}
		return false;
	}

	@Override protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params)
	{
		if (state.getValue(PLACEMENT) == StateStreetlamp.TOP)
		{
			return super.getDrops(state, params);
		}
		else
		{
			return new ArrayList<>();
		}
	}
}
