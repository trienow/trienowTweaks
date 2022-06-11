package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.capabilities.IPlayerCapability;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author (c) trienow 2022
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
