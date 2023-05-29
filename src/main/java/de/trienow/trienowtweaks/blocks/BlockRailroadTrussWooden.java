package de.trienow.trienowtweaks.blocks;

import java.util.List;

import net.minecraft.util.text.TextFormatting;

public class BlockRailroadTrussWooden extends BaseBlockRailroadTruss
{
	public BlockRailroadTrussWooden()
	{
		super("railroad_truss_wooden");
	}

	@Override
	protected void addInformation(List<String> tooltip)
	{
		tooltip.add(TextFormatting.RESET + "A" + TextFormatting.GOLD + " wooden" + TextFormatting.RESET + " railroad truss for simple minecart projects");
	}
}
