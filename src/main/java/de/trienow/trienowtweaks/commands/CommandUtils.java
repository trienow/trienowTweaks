package de.trienow.trienowtweaks.commands;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;

/**
 * @author (c) trienow 2019 - 2022
 */
public class CommandUtils
{
	public static void sendIm(CommandSourceStack cs, String translationKey, Object... args)
	{
		cs.sendSuccess(new TranslatableComponent(translationKey, args), false);
	}

	public static void sendLoggedIm(CommandSourceStack cs, String translationKey, Object... args)
	{
		cs.sendSuccess(new TranslatableComponent(translationKey, args), true);
	}

	public static void sendIm(Player toPlayer, String translationKey, Object... args)
	{
		sendIm(toPlayer.createCommandSourceStack(), translationKey, args);
	}
}
