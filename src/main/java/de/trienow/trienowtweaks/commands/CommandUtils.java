package de.trienow.trienowtweaks.commands;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

/**
 * @author trienow 2019 - 2023
 */
public class CommandUtils
{
	public static void sendIm(final CommandSourceStack cs, final String translationKey, final Object... args)
	{
		cs.sendSuccess(() -> Component.translatable(translationKey, args), false);
	}

	public static void sendLoggedIm(final CommandSourceStack cs, final String translationKey, final Object... args)
	{
		cs.sendSuccess(() -> Component.translatable(translationKey, args), true);
	}

	public static void sendIm(final Player toPlayer, final String translationKey, final Object... args)
	{
		sendIm(toPlayer.createCommandSourceStack(), translationKey, args);
	}
}
