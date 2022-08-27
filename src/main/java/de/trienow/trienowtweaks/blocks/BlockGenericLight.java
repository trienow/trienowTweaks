package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.blocks.states.StateGenericLight;
import de.trienow.trienowtweaks.config.Globals;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * @author (c) trienow 2016 - 2022
 */
public class BlockGenericLight extends BaseBlock
{
	private static final Properties PROPS = defaultProperties(Material.AIR)
			.sound(SoundType.LADDER).lightLevel((blockState) -> 15)
			.air()
			.noCollission()
			.noOcclusion(); //Not a full block, whose faces can cull the world
	public static final EnumProperty<StateGenericLight> ANCHOR = EnumProperty.create("anchor", StateGenericLight.class);
	private static final VoxelShape SHAPE_BB = Shapes.empty();

	public BlockGenericLight()
	{
		super(PROPS);
		registerDefaultState(super.defaultBlockState().setValue(ANCHOR, StateGenericLight.NONE));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
	{
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(ANCHOR);
	}

	@Override
	public boolean isRandomlyTicking(BlockState pState)
	{
		return pState.getValue(ANCHOR) != StateGenericLight.NONE;
	}

	@Override
	public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom)
	{
		BlockPos anchorBlock = pPos.subtract(pState.getValue(ANCHOR).getAnchorOffset());
		if (!(pLevel.getBlockState(anchorBlock).getBlock() instanceof BlockTorchSolamnia))
		{
			pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_NONE);
		}
	}

	@Override
	public RenderShape getRenderShape(BlockState pState)
	{
		return Globals.showInvisibleBlocks() ? RenderShape.MODEL : RenderShape.INVISIBLE;
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
	{
		return SHAPE_BB;
	}
}
