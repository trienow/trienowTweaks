package de.trienow.trienowtweaks.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

/**
 * @author (c) trienow 2017 - 2022
 */
public class BlockFakeFire extends BaseBlock
{
	private static final Properties PROPS = Properties.of(Material.DECORATION, MaterialColor.TERRACOTTA_ORANGE)
			.strength(0.1f)
			.lightLevel((blockState) -> 15)
			.sound(SoundType.WOOD)
			.noOcclusion()
			.noCollission();

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
	public void animateTick(BlockState p_49888_, Level level, BlockPos pos, Random rand)
	{
		if (rand.nextInt(80) == 0)
		{
			level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 0.5f, rand.nextFloat(), true);
		}

		if (rand.nextInt(10) == 0)
		{
			double rnd1 = rand.nextDouble();
			double rnd4 = rand.nextDouble();
			double rnd5 = rand.nextDouble();
			rnd1 = rnd1 < 0.2D ? rnd1 : 0.1D;
			level.addParticle(ParticleTypes.LARGE_SMOKE, false, (pos.getX() + rnd4), pos.getY(), (pos.getZ() + rnd5), 0, rnd1, 0);
		}
	}
}
