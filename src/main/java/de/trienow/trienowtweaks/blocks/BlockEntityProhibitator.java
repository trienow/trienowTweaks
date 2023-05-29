package de.trienow.trienowtweaks.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockEntityProhibitator extends BaseBlock
{
	public BlockEntityProhibitator()
	{
		super("entity_prohibitator", Material.WOOD, true);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.GREEN + "Will prohibit any Monsters from Spawning in a Chunk");
		tooltip.add(TextFormatting.DARK_GREEN + "Must be placed at the Chunk's origen at y = 255");
		tooltip.add(TextFormatting.BLUE + "How to calculate X and Z:");
		tooltip.add(TextFormatting.AQUA + "Coordinate = 'Chunk Coordinate' * 16");
	}
}
