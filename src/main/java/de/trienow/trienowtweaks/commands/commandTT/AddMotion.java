package de.trienow.trienowtweaks.commands.commandTT;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import de.trienow.trienowtweaks.commands.CommandArg;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;

import static de.trienow.trienowtweaks.commands.CommandUtils.sendIm;

/**
 * @author (c) trienow 2019 - 2022
 */
class AddMotion
{
	public static ArgumentBuilder<CommandSourceStack, ?> register()
	{
		//@fm:off
		return Commands.literal(SubCommands.addMotion.toString()).requires((cs) -> cs.hasPermission(4))
				.then(CommandArg.ENTITIES.arg()
				.then(Commands.argument("x motion", FloatArgumentType.floatArg())
				.then(Commands.argument("y motion", FloatArgumentType.floatArg())
				.then(Commands.argument("z motion", FloatArgumentType.floatArg())
				.then(CommandArg.HEARTS.arg()
				.executes(ctx -> execute(ctx.getSource(),
										CommandArg.ENTITIES.get(ctx),
										FloatArgumentType.getFloat(ctx, "x motion"),
										FloatArgumentType.getFloat(ctx, "y motion"),
										FloatArgumentType.getFloat(ctx, "z motion"),
										CommandArg.HEARTS.get(ctx))))))));
		//@fm:on
	}

	/**
	 * Executes the command
	 *
	 * @param cs         The command sender
	 * @param ents       The entities to add motion to
	 * @param x          The velocity on the x-Axis
	 * @param y          The velocity on the y-Axis
	 * @param z          The velocity on the z-Axis
	 * @param absorption The absorption half-hears given to the entities
	 * @return 1, because success?
	 */
	private static int execute(CommandSourceStack cs, Collection<? extends Entity> ents, float x, float y, float z, float absorption)
	{
		for (Entity ent : ents)
		{
			if (ent instanceof Player player)
			{
				player.setAbsorptionAmount(player.getAbsorptionAmount() + absorption);
			}

			ent.setDeltaMovement(x, y, z);

			Vec3 motion = ent.getDeltaMovement();
			sendIm(cs, "cmd." + TrienowTweaks.MODID + ".tt.addmotion", ent.getDisplayName(), String.format("%.2f", motion.z), String.format("%.2f", motion.y), String.format("%.2f", motion.z));
		}

		return 1;
	}
}
