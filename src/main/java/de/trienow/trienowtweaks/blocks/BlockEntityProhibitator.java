package de.trienow.trienowtweaks.blocks;

import net.minecraft.world.level.material.MapColor;

/**
 * @author trienow 2017 - 2023
 */
public class BlockEntityProhibitator extends BaseBlock
{
	public BlockEntityProhibitator()
	{
		super(defaultProperties().mapColor(MapColor.STONE));
		super.tooltipCount = 4;
	}
}
