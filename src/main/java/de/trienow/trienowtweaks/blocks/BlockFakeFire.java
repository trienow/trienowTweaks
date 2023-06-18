package de.trienow.trienowtweaks.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * @author trienow 2017 - 2023
 */
public class BlockFakeFire extends BaseBlock
{
	private static final Properties PROPS = BlockBehaviour.Properties.of()
			.strength(0.1f)
			.lightLevel((blockState) -> 15)
			.sound(SoundType.SNOW)
			.noOcclusion()
			.noCollission()
			.mapColor(MapColor.FIRE);

	private static final VoxelShape AABB = Shapes.create(0.02D, 0D, 0.02D, 0.98D, 0.2D, 0.98D);

	public BlockFakeFire()
	{
		super(PROPS);
	}

	@Override
	public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos)
	{
		return 0.0f; //Makes the block below dark. Like soot.
	}

	@Override
	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos)
	{
		return canSupportCenter(pLevel, pPos.below(), Direction.UP);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState)
	{
		return RenderShape.MODEL;
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
	{
		return AABB;
	}

	@Override
	public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom)
	{
		if (pRandom.nextInt(80) == 0)
		{
			pLevel.playLocalSound(pPos.getX(), pPos.getY(), pPos.getZ(), SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 0.5f, pRandom.nextFloat(), true);
		}

		if (pRandom.nextInt(10) == 0)
		{
			double rnd1 = pRandom.nextDouble();
			double rnd4 = pRandom.nextDouble();
			double rnd5 = pRandom.nextDouble();
			rnd1 = rnd1 < 0.2D ? rnd1 : 0.1D;
			pLevel.addParticle(ParticleTypes.LARGE_SMOKE, false, (pPos.getX() + rnd4), pPos.getY(), (pPos.getZ() + rnd5), 0, rnd1, 0);
		}
	}
}
