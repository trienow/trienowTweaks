package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.capabilities.IPlayerCapability;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.capabilities.RegisterCapabilitiesEvent;

/**
 * @author (c) trienow 2023
 */
@EventBusSubscriber(modid = TrienowTweaks.MODID)
public class AtomCapabilities
{
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent evt)
	{
		evt.register(IPlayerCapability.class);
	}

}
