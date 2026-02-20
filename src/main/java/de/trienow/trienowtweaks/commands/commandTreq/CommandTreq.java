package de.trienow.trienowtweaks.commands.commandTreq;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.trienow.trienowtweaks.commands.CommandArg;
import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

/**
 * @author (c) trienow
 */
public class CommandTreq
{
	private static int activeTick = -100;
	private static final TeleportRequests REQUESTS = new TeleportRequests();

	public static LiteralArgumentBuilder<CommandSourceStack> register()
	{
		return LiteralArgumentBuilder.<CommandSourceStack>literal("treq")
				.requires(ctx -> ctx.hasPermission(0))
				.then(Commands.literal("to")
						.then(CommandArg.PLAYER.arg()
								.executes(ctx -> REQUESTS.newRequest(ctx.getSource().getPlayerOrException(), CommandArg.PLAYER.get(ctx)))))
				.then(Commands.literal("accept")
						.then(CommandArg.PLAYERS.arg()
								.executes(ctx -> REQUESTS.accept(ctx.getSource().getPlayerOrException(), CommandArg.PLAYERS.get(ctx)))))
				.then(Commands.literal("block")
						.then(CommandArg.PLAYERS.arg()
								.executes(ctx -> REQUESTS.block(ctx.getSource().getPlayerOrException(), CommandArg.PLAYERS.get(ctx)))))
				.then(Commands.literal("unblock")
						.then(CommandArg.PLAYERS.arg()
								.executes(ctx -> REQUESTS.unblock(ctx.getSource().getPlayerOrException(), CommandArg.PLAYERS.get(ctx)))))
				.then(Commands.literal("help")
						.executes(ctx -> help(ctx.getSource().getPlayerOrException())));
	}

	/**
	 * @param sender The command executor
	 * @return Something
	 */
	private static int help(ServerPlayer sender)
	{
		CommandUtils.sendIm(sender, "cmd." + TrienowTweaks.MODID + ".treq.help");
		return 1;
	}

	public static void onLevelTick(LevelTickEvent evt)
	{
		if (activeTick >= 0)
		{
			MinecraftServer server = evt.getLevel().getServer();
			if (server != null)
			{
				REQUESTS.cleanUpRequests(server.getPlayerList());
			}
			activeTick = -100;
		}
		else
		{
			activeTick++;
		}
	}
}
