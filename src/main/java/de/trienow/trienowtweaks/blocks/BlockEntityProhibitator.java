package de.trienow.trienowtweaks.blocks;

import net.minecraft.world.level.material.Material;

/**
 * @author (c) trienow 2017 - 2022
 */
public class BlockEntityProhibitator extends BaseBlock
{
	public BlockEntityProhibitator()
	{
		super(defaultProperties(Material.WOOD));
		super.tooltipCount = 4;
	}
}
