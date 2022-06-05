package de.trienow.trienowtweaks.commands.commandTT;

import com.mojang.brigadier.builder.ArgumentBuilder;
import de.trienow.trienowtweaks.commands.CommandArg;
import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;

import static de.trienow.trienowtweaks.main.TrienowTweaks.LOG;

/**
 * @author (c) trienow 2019 - 2022
 */
class SetAbsorption
{
	private static final String TEXT_RESPONSE = "cmd." + TrienowTweaks.MODID + ".tt.set_absorption";

	public static ArgumentBuilder<CommandSourceStack, ?> register()
	{
		//@fm:off
		return Commands.literal(SubCommands.setAbsorption.toString()).requires((cs) -> cs.hasPermission(2))
				.then(CommandArg.PLAYERS.arg()
				.then(CommandArg.HEARTS.arg().executes(ctx -> setAbsorption(ctx.getSource(), CommandArg.PLAYERS.get(ctx), CommandArg.HEARTS.get(ctx)))));
		//@fm:on
	}

	/**
	 * @param sender     The command executor
	 * @param targets    The players, which are targeted or <code>null</code>
	 * @param halfHearts The amount of health the targets should gain.
	 * @return Something
	 */
	private static int setAbsorption(CommandSourceStack sender, Collection<ServerPlayer> targets, float halfHearts)
	{
		for (ServerPlayer target : targets)
		{
			target.setAbsorptionAmount(halfHearts);
			LOG.info("[TT] /tt setAbsorption: Set absorption-half-hearts of {} to {} issued by {}",
					target.getDisplayName().toString(),
					halfHearts,
					sender.getDisplayName().toString());
			CommandUtils.sendIm(sender, TEXT_RESPONSE, target.getDisplayName(), String.format("%.1f", halfHearts));
		}

		return 1;
	}
}
