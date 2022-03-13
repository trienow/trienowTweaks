package de.trienow.trienowtweaks.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author (c) trienow 2017 - 2022
 */
public class BlockMinecartKiller extends BaseBlock
{
	private static final Properties PROPS = defaultProperties(Material.METAL);
	private static final Predicate<Entity> PREDICATE_TRUE = (ent) -> true;

	public BlockMinecartKiller()
	{
		super(PROPS);
	}

	@Override
	public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving)
	{
		final AABB checkBox = new AABB(pPos.above());
		if (!pLevel.isClientSide() && pLevel.getSignal(pPos, Direction.UP) > 0)
		{
			List<Minecart> ents = pLevel.getEntities(EntityTypeTest.forClass(Minecart.class), checkBox, PREDICATE_TRUE);

			for (Entity entity : ents)
			{
				entity.hurt(DamageSource.GENERIC, Float.MAX_VALUE);
			}
		}
	}
}
