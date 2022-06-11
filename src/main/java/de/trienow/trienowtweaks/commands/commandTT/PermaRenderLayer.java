package de.trienow.trienowtweaks.commands.commandTT;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.trienow.trienowtweaks.capabilities.IPlayerCapability;
import de.trienow.trienowtweaks.commands.CommandArg;
import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.entity.layer.LayerTtRenderMode;
import de.trienow.trienowtweaks.network.PacketPlayerCaps;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

/**
 * @author (c) trienow 2022
 */
class PermaRenderLayer
{
	public static ArgumentBuilder<CommandSourceStack, ?> register()
	{
		return Commands.literal(SubCommands.permaRenderLayer.toString())
				.then(Commands.literal("show")
						.then(CommandArg.PLAYERS.arg().executes(ctx -> permaRenderLayer(ctx.getSource(), CommandArg.PLAYERS.get(ctx), LayerTtRenderMode.SHOW)))
						.executes(ctx -> permaRenderLayer(ctx.getSource(), null, LayerTtRenderMode.SHOW)))
				.then(Commands.literal("hide")
						.then(CommandArg.PLAYERS.arg().executes(ctx -> permaRenderLayer(ctx.getSource(), CommandArg.PLAYERS.get(ctx), LayerTtRenderMode.HIDE)))
						.executes(ctx -> permaRenderLayer(ctx.getSource(), null, LayerTtRenderMode.HIDE)));
	}

	private static int permaRenderLayer(@Nonnull CommandSourceStack source, @Nullable Collection<ServerPlayer> serverPlayers, LayerTtRenderMode renderMode) throws CommandSyntaxException
	{
		if (serverPlayers == null)
		{
			serverPlayers = Collections.singleton(source.getPlayerOrException());
		}

		for (ServerPlayer serverPlayer : serverPlayers)
		{
			serverPlayer.getCapability(IPlayerCapability.PLAYER_CAP).ifPresent(pcap -> pcap.setLayerTtRenderMode(renderMode));
			new PacketPlayerCaps(renderMode, serverPlayer.getUUID()).sendToAll();
		}

		CommandUtils.sendIm(source, "cmd.trienowtweaks.tt.permarenderlayer.response", renderMode);

		return 1;
	}
}
