package de.trienow.trienowtweaks.commands.commandTT;

import com.mojang.brigadier.builder.ArgumentBuilder;
import de.trienow.trienowtweaks.commands.CommandArg;
import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.utils.LevelUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Collections;

import static de.trienow.trienowtweaks.main.TrienowTweaks.LOG;

/**
 * @author trienow 2019 - 2023
 */
class Spawn
{
	private static final String TEXT_RESPONSE = "cmd." + TrienowTweaks.MODID + ".tt.spawn";

	public static ArgumentBuilder<CommandSourceStack, ?> register()
	{
		//@fm:off
		return Commands.literal(SubCommands.spawn.toString())
				.then(CommandArg.PLAYERS.arg().executes(ctx -> tpSpawn(ctx.getSource(), CommandArg.PLAYERS.get(ctx))))
				.executes(ctx -> tpSpawn(ctx.getSource(), Collections.singleton(ctx.getSource().getPlayerOrException())));
		//@fm:on
	}

	/**
	 * @param sender  The command executor
	 * @param targets The players, which are targeted
	 * @return Something
	 */
	private static int tpSpawn(CommandSourceStack sender, Collection<ServerPlayer> targets)
	{
		for (ServerPlayer target : targets)
		{
			LOG.info("[TT] /tt spawn: Teleporting {} to spawn issued by {}",
					target.getDisplayName().toString(),
					sender.getTextName());

			BlockPos spawn = LevelUtils.getSpawn(target.level());
			target.teleportTo(spawn.getX() + 0.5, spawn.getY() + 0.5, spawn.getZ() + 0.5);
			CommandUtils.sendIm(sender, TEXT_RESPONSE, target.getDisplayName());
		}
		return 1;
	}
}
