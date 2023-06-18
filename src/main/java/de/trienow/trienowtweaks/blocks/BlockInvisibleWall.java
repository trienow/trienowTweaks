package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.config.Globals;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * @author trienow 2016 - 2023
 */
public class BlockInvisibleWall extends BaseBlock
{
	private static final Properties PROPS = BlockBehaviour.Properties.of()
			.mapColor(DyeColor.YELLOW)
			.strength(0.5f, 5f)
			.noOcclusion();
	private static final VoxelShape SHAPE_COLLISION = Shapes.create(0, 0, 0, 1, 1.5, 1);
	private static final VoxelShape SHAPE_BB = Shapes.block();

	public BlockInvisibleWall()
	{
		super(PROPS);
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

	@Override
	public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
	{
		return SHAPE_COLLISION;
	}

	@Override
	public boolean collisionExtendsVertically(BlockState state, BlockGetter world, BlockPos pos, Entity collidingEntity)
	{
		return true; //Determines if this block's collision box should be treated as though it can extend above its block space. Use this to replicate fence and wall behavior.
	}

	@Override
	public boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos)
	{
		return true;
	}
}