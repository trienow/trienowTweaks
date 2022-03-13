package de.trienow.trienowtweaks.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;

/**
 * @author (c) trienow 2019
 */
@FunctionalInterface
public interface IArg<T>
{
	/**
	 * @param ctx The command context, in which it was executed
	 * @return The typed parsed argument
	 * @throws CommandSyntaxException When the argument could not be parsed correctly
	 */
	T get(CommandContext<CommandSourceStack> ctx, String name) throws CommandSyntaxException;
}
