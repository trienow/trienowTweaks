package de.trienow.trienowtweaks.atom;

import com.mojang.brigadier.CommandDispatcher;
import de.trienow.trienowtweaks.commands.commandTT.CommandTT;
import de.trienow.trienowtweaks.commands.commandTreq.CommandTreq;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

/**
 * @author (c) trienow 2023
 */
@EventBusSubscriber(modid = TrienowTweaks.MODID)
public class AtomCommands
{
	@SubscribeEvent
	public static void commandRegistry(final RegisterCommandsEvent evt)
	{
		final CommandDispatcher<CommandSourceStack> dispatcher = evt.getDispatcher();
		dispatcher.register(CommandTT.register());
		dispatcher.register(CommandTreq.register());
	}
}
