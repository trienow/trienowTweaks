package de.trienow.trienowtweaks.integration.hwyla;

import de.trienow.trienowtweaks.blocks.BlockTEApothecaryRefiller;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.IWailaRegistrar;
import mcp.mobius.waila.api.WailaPlugin;

/**
 * @author (c) trienow 2018
 */
@WailaPlugin(value = "trienowtweaks")
public class Hwyla implements IWailaPlugin
{
	@Override
	public void register(IWailaRegistrar registrar)
	{
		registrar.registerBodyProvider(new FluidHolderProvider(), BlockTEApothecaryRefiller.class);
	}
}
