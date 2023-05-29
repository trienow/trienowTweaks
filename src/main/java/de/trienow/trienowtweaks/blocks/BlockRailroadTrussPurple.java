package de.trienow.trienowtweaks.blocks;

import java.util.List;

import net.minecraft.util.text.TextFormatting;

public class BlockRailroadTrussPurple extends BaseBlockRailroadTruss
{
	public BlockRailroadTrussPurple()
	{
		super("railroad_truss_purple");
	}

	@Override
	protected void addInformation(List<String> tooltip)
	{
		tooltip.add(TextFormatting.RESET + "A" + TextFormatting.DARK_PURPLE + " purple" + TextFormatting.RESET + " railroad truss for @Lavinaia");
	}
}
