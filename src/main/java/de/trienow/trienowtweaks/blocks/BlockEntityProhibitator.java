package de.trienow.trienowtweaks.blocks;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.MapColor;

/**
 * @author trienow 2017 - 2023
 */
public class BlockEntityProhibitator extends BaseBlock
{
	public BlockEntityProhibitator(ResourceLocation registryName)
	{
		super(defaultProperties().mapColor(MapColor.STONE), registryName);
		super.tooltipCount = 4;
	}
}
