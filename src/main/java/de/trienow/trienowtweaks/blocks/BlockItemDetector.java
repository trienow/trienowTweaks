package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.tiles.TEItemDetector;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

/**
 * @author trienow 2017 - 2023
 */
public class BlockItemDetector extends BaseBlock implements EntityBlock
{
	private static final Properties PROPS = defaultProperties()
			.mapColor(MapColor.STONE)
			.pushReaction(PushReaction.BLOCK);
	public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;

	public BlockItemDetector()
	{
		super(PROPS);
		this.registerDefaultState(super.defaultBlockState().setValue(ENABLED, false));
		super.tooltipCount = 4;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
	{
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(ENABLED);
	}

	@Override
	public int getSignal(BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pDirection)
	{
		return pState.getValue(ENABLED) ? 15 : 0;
	}

	@Override
	public boolean isSignalSource(BlockState pState)
	{
		return true;
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, @Nullable Direction direction)
	{
		return true;
	}

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
	{
		if (!pLevel.isClientSide() && pLevel.getBlockEntity(pPos) instanceof TEItemDetector te)
		{
			final ChatFormatting numberColor;
			if (pPlayer.getPose() == Pose.CROUCHING)
			{
				numberColor = ChatFormatting.RED;
				te.amt -= 10;
			}
			else
			{
				numberColor = ChatFormatting.GREEN;
				te.amt++;
			}

			if (te.amt > 100)
			{
				te.amt = 100;
			}
			if (te.amt < 1)
			{
				te.amt = 1;
			}

			final CommandSourceStack senderE = pPlayer.createCommandSourceStack();
			senderE.sendSuccess(() -> Component.translatable(this.getDescriptionId() + ".message", numberColor, te.amt), false);
		}

		return InteractionResult.CONSUME;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
	{
		return new TEItemDetector(pPos, pState);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType)
	{
		if (pLevel.isClientSide())
		{
			return null;
		}

		return (pLevel1, pPos, pState1, pBlockEntity) -> {
			if (pBlockEntity instanceof TEItemDetector tile)
			{
				tile.tickServer();
			}
		};
	}
}
