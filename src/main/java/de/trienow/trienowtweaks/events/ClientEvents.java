package de.trienow.trienowtweaks.events;

import de.trienow.trienowtweaks.capabilities.IPlayerCapability;
import de.trienow.trienowtweaks.entity.layer.RenderSetup;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author (c) trienow 2022
 */
@Mod.EventBusSubscriber(modid = TrienowTweaks.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents
{
	@SubscribeEvent
	public static void onWorldClose(final WorldEvent.Unload evt)
	{
		RenderSetup.clearPcapCache();
	}

	@SubscribeEvent
	public static void onEntityLeave(final EntityLeaveWorldEvent evt)
	{
		if (evt.getEntity() instanceof Player player)
		{
			player.getCapability(IPlayerCapability.PLAYER_CAP).invalidate();
		}
	}
}
