package de.trienow.trienowtweaks.tiles;

import de.trienow.trienowtweaks.atom.AtomTiles;
import de.trienow.trienowtweaks.blocks.BlockItemDetector;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author (c) trienow 2018 - 2023
 */
public class TEItemDetector extends BlockEntity
{
	private static final Predicate<Entity> PREDICATE_TRUE = (ent) -> true;
	private static final int MAX_ITEMS = 10000;

	public int amt = 1;
	private byte tick = 0;
	private AABB checkBox;

	public TEItemDetector(BlockPos pWorldPosition, BlockState pBlockState)
	{
		super(AtomTiles.ITEM_DETECTOR.get(), pWorldPosition, pBlockState);

		checkBox = new AABB(worldPosition.above());
	}

	@Override protected void loadAdditional(ValueInput input)
	{
		super.loadAdditional(input);

		amt = input.getIntOr("amt", 0);
		amt = Math.max(amt, 1);

		if (checkBox == null)
		{
			checkBox = new AABB(worldPosition.above());
		}
	}

	@Override protected void saveAdditional(ValueOutput output)
	{
		super.saveAdditional(output);
		output.putInt("amt", amt);
	}

	public void tickServer()
	{
		if (level != null && !level.isClientSide())
		{
			if (tick % 20 == 0)
			{
				tick = 0;
				checkForItems();
			}

			tick++;
		}
	}

	private void checkForItems()
	{
		List<ItemEntity> ents = level.getEntities(EntityTypeTest.forClass(ItemEntity.class), checkBox, PREDICATE_TRUE);
		final int entityItemCount = ents.size();

		if (entityItemCount > MAX_ITEMS)
		{
			for (int i = 0; i < entityItemCount - MAX_ITEMS; i++)
			{
				ents.get(i).remove(Entity.RemovalReason.DISCARDED);
			}
		}

		BlockState currentState = getBlockState();
		final boolean initial = currentState.getValue(BlockItemDetector.ENABLED);
		final boolean newState = entityItemCount >= amt;

		if (initial != newState)
		{
			level.setBlock(worldPosition, currentState.setValue(BlockItemDetector.ENABLED, newState), Block.UPDATE_ALL);
		}
	}
}