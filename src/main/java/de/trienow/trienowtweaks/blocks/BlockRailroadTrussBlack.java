package de.trienow.trienowtweaks.blocks;

import java.util.List;

import net.minecraft.util.text.TextFormatting;

public class BlockRailroadTrussBlack extends BaseBlockRailroadTruss
{
	public BlockRailroadTrussBlack()
	{
		super("railroad_truss_black");
	}

	@Override
	protected void addInformation(List<String> tooltip)
	{
		tooltip.add(TextFormatting.RESET + "A" + TextFormatting.DARK_GRAY + " black" + TextFormatting.RESET + " railroad truss.");
		tooltip.add(TextFormatting.RESET + "It's life matters...");
	}
}
