package de.trienow.trienowtweaks.commands.commandTT;

import com.mojang.brigadier.builder.ArgumentBuilder;
import de.trienow.trienowtweaks.commands.CommandArg;
import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.Entity;

import java.util.Collection;

/**
 * @author (c) trienow 2019 - 2022
 */
class Invulnerable
{
	private static final String TEXT_RESPONSE = "cmd." + TrienowTweaks.MODID + ".tt.invulnerable.";

	public static ArgumentBuilder<CommandSourceStack, ?> register()
	{
		//@fm:off
		return Commands.literal(SubCommands.invulnerable.toString()).requires((cs) -> cs.hasPermission(4))
				.then(CommandArg.ENTITIES.arg()
				.then(CommandArg.BOOLEAN.arg()
						.executes(ctx -> invulnerable(ctx.getSource(), CommandArg.ENTITIES.get(ctx), CommandArg.BOOLEAN.get(ctx)))));
		//@fm:on
	}

	/**
	 * @param sender       The command executor
	 * @param targets      The entities to make (in)vulnerable
	 * @param invulnerable whether to make the entities (in)vulnerable
	 * @return Something
	 */
	private static int invulnerable(CommandSourceStack sender, Collection<? extends Entity> targets, Boolean invulnerable)
	{
		String translationKey = invulnerable ? "on" : "off";
		for (Entity ent : targets)
		{
			ent.setInvulnerable(invulnerable);
			CommandUtils.sendIm(sender, TEXT_RESPONSE + translationKey, ent.getDisplayName());
		}
		return 1;
	}
}
