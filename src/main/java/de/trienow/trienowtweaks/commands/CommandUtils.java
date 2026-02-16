package de.trienow.trienowtweaks.commands;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * @author trienow 2019 - 2023
 */
public class CommandUtils
{
	public static void sendIm(final ServerPlayer player, final String translationKey, final Object... args)
	{
		player.sendSystemMessage(Component.translatable(translationKey, args));
	}

	public static void sendLoggedIm(final CommandSourceStack cs, final String translationKey, final Object... args)
	{
		cs.sendSuccess(() -> Component.translatable(translationKey, args), true);
	}
}
