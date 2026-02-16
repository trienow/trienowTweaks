package de.trienow.trienowtweaks.events;

import de.trienow.trienowtweaks.capabilities.IPlayerCapability;
import de.trienow.trienowtweaks.entity.layer.RenderSetup;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

/**
 * @author (c) trienow 2022 - 2023
 */
@EventBusSubscriber(value = Dist.CLIENT, modid = TrienowTweaks.MODID)
public class ClientEvents
{
	@SubscribeEvent
	public static void onLevelClose(final LevelEvent.Unload evt)
	{
		RenderSetup.clearPcapCache();
	}

	@SubscribeEvent
	public static void onEntityLeave(final EntityLeaveLevelEvent evt)
	{
		if (evt.getEntity() instanceof Player player)
		{
			player.getCapability(IPlayerCapability.PLAYER_CAP).invalidate();
		}
	}
}
