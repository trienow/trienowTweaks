package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.commands.*;
import de.trienow.trienowtweaks.commands.commandTA.CommandTA;
import net.minecraft.command.ServerCommandManager;

public class AtomCommands
{
	public static void registerCommands(ServerCommandManager manager)
	{
		manager.registerCommand(new CommandTT());
		manager.registerCommand(new CommandTA());
	}
}
