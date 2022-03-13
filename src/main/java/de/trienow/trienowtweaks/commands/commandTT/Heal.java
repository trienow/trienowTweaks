package de.trienow.trienowtweaks.commands.commandTT;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.trienow.trienowtweaks.commands.CommandArg;
import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Collections;

/**
 * @author (c) trienow 2019 - 2022
 */
class Heal
{
	private static final CommandArg<Float, Float> HEARTS = new CommandArg<>("half hearts", FloatArgumentType.floatArg(0), FloatArgumentType::getFloat);

	public static ArgumentBuilder<CommandSourceStack, ?> register()
	{
		//@fm:off
		return Commands.literal(SubCommands.heal.toString()).requires((cs) -> cs.hasPermission(4))
				.then(CommandArg.PLAYERS.arg()
						.then(HEARTS.arg()
								.executes(ctx -> heal(ctx.getSource(), CommandArg.PLAYERS.get(ctx), HEARTS.get(ctx))))
						.executes(ctx -> heal(ctx.getSource(), CommandArg.PLAYERS.get(ctx), Float.MAX_VALUE)))
				.executes(ctx -> heal(ctx.getSource(), null, Float.MAX_VALUE));
		//@fm:on
	}

	/**
	 * @param sender  The command executor.
	 * @param targets The targeted entity, may be null. Then the sender is used
	 * @param health  The new health to give the entity may be max value
	 * @return 1, because success?
	 * @throws CommandSyntaxException When no player was given and the sender is not a player too
	 */
	private static int heal(CommandSourceStack sender, Collection<ServerPlayer> targets, float health) throws CommandSyntaxException
	{
		targets = targets == null ? Collections.singleton(sender.getPlayerOrException()) : targets;

		for (ServerPlayer target : targets)
		{
			float newHealth = Math.min(health, target.getMaxHealth());

			target.setHealth(newHealth);
			final int MAX_HEALTH = 20;
			target.getFoodData().setFoodLevel(MAX_HEALTH);

			CommandUtils.sendIm(sender, "cmd." + TrienowTweaks.MODID + ".tt.heal", target.getDisplayName(), String.format("%.1f", newHealth), MAX_HEALTH);
		}

		return 1;
	}
}
