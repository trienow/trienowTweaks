package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.capabilities.IPlayerCapability;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.capabilities.RegisterCapabilitiesEvent;

/**
 * @author (c) trienow 2023
 */
@Mod.EventBusSubscriber(modid = TrienowTweaks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtomCapabilities
{
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent evt)
	{
		evt.register(IPlayerCapability.class);
	}
}
