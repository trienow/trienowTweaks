package de.trienow.trienowtweaks.commands.commandTT;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.server.command.EnumArgument;

import static de.trienow.trienowtweaks.commands.CommandUtils.sendIm;

/**
 * @author (c) trienow 2019 - 2022
 */
class Help
{
	public static ArgumentBuilder<CommandSourceStack, ?> register()
	{
		//@fm:off
		return Commands.literal(SubCommands.help.toString()).requires((cs) -> cs.hasPermission(4))
				.then(Commands.argument("subCommand", EnumArgument.enumArgument(SubCommands.class))
				.executes(Help::helpSubcommand));
		//@fm:on
	}

	/**
	 * @param ctx The context, in which the command was executed
	 * @return Something
	 */
	private static int helpSubcommand(CommandContext<CommandSourceStack> ctx)
	{
		final SubCommands subCommand = ctx.getArgument("subCommand", SubCommands.class);
		sendIm(ctx.getSource(), "cmd." + TrienowTweaks.MODID + ".tt.help." + subCommand.toString());
		return 1;
	}
}
