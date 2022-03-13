package de.trienow.trienowtweaks.commands;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.selector.EntitySelector;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.Collection;

/**
 * @author (c) trienow 2019 - 2022
 */
public record CommandArg<T, U>(String name, ArgumentType<U> argument, IArg<T> getter)
{
	/**
	 * @return The argument to the then function
	 */
	public RequiredArgumentBuilder<CommandSourceStack, U> arg()
	{
		return Commands.argument(name, argument);
	}

	/**
	 * Gets the parsed command argument
	 *
	 * @param ctx The command context, in which it was executed
	 * @return The value of the parsed command argument
	 * @throws CommandSyntaxException Syntax is wrong
	 */
	public T get(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException
	{
		return getter.get(ctx, name);
	}

	public static final CommandArg<Boolean, Boolean> BOOLEAN = new CommandArg<>("boolean", BoolArgumentType.bool(), BoolArgumentType::getBool);
	public static final CommandArg<Collection<? extends Entity>, EntitySelector> ENTITIES = new CommandArg<>("entities", EntityArgument.entities(), EntityArgument::getEntities);
	public static final CommandArg<Float, Float> HEARTS = new CommandArg<>("half hearts", FloatArgumentType.floatArg(0), FloatArgumentType::getFloat);
	public static final CommandArg<Collection<ServerPlayer>, EntitySelector> PLAYERS = new CommandArg<>("players", EntityArgument.players(), EntityArgument::getPlayers);
	public static final CommandArg<ServerPlayer, EntitySelector> PLAYER = new CommandArg<>("player", EntityArgument.player(), EntityArgument::getPlayer);
}
