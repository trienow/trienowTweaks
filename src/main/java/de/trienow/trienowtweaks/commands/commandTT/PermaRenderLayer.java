package de.trienow.trienowtweaks.commands.commandTT;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.trienow.trienowtweaks.atom.AtomAttachments;
import de.trienow.trienowtweaks.commands.CommandArg;
import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.entity.layer.LayerTtType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author (c) trienow 2022 - 2023
 */
class PermaRenderLayer
{
	public static ArgumentBuilder<CommandSourceStack, ?> register()
	{
		LiteralArgumentBuilder<CommandSourceStack> literal = Commands.literal(SubCommands.permaRenderLayer.toString());

		for (LayerTtType type : LayerTtType.values())
		{
			literal = literal.then(Commands.literal(type.toString())
					.then(CommandArg.PLAYER.arg().executes(
							(ctx) -> permaRenderLayer(ctx.getSource(), CommandArg.PLAYER.get(ctx), type)
					))
					.executes((ctx) -> permaRenderLayer(ctx.getSource(), null, type)));
		}

		return literal;
	}

	private static int permaRenderLayer(@Nonnull CommandSourceStack source, @Nullable ServerPlayer serverPlayer, LayerTtType renderMode) throws CommandSyntaxException
	{
		if (serverPlayer == null)
		{
			serverPlayer = source.getPlayerOrException();
		}

		serverPlayer.setData(AtomAttachments.LAYER_TT, renderMode.getId());

		CommandUtils.sendIm(source, "cmd.trienowtweaks.tt.permarenderlayer.response", renderMode.toString());

		return 1;
	}
}
