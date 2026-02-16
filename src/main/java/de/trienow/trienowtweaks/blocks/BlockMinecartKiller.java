package de.trienow.trienowtweaks.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author trienow 2017 - 2023
 */
public class BlockMinecartKiller extends BaseBlock
{
	private static final Properties PROPS = defaultProperties().mapColor(MapColor.METAL);
	private static final Predicate<Entity> PREDICATE_TRUE = (ent) -> true;

	public BlockMinecartKiller()
	{
		super(PROPS);
	}

	@Override protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston)
	{
		final AABB checkBox = new AABB(pos.above());
		if (!level.isClientSide() && level.getSignal(pos, Direction.UP) > 0)
		{
			List<Minecart> ents = level.getEntities(EntityTypeTest.forClass(Minecart.class), checkBox, PREDICATE_TRUE);

			for (Entity entity : ents)
			{
				entity.hurt(entity.damageSources().generic(), Float.MAX_VALUE);
			}
		}
	}
}
