package de.trienow.trienowtweaks.blocks;

import java.util.List;

import net.minecraft.util.text.TextFormatting;

public class BlockRailroadTrussBright extends BaseBlockRailroadTruss
{
	public BlockRailroadTrussBright()
	{
		super("railroad_truss_bright");
	}

	@Override
	protected void addInformation(List<String> tooltip)
	{
		tooltip.add(TextFormatting.RESET + "A" + TextFormatting.YELLOW + " fir" + TextFormatting.RESET + " railroad truss for " + TextFormatting.ITALIC + "furry" + TextFormatting.RESET + " minecart projects");
	}
}
