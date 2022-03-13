package de.trienow.trienowtweaks.commands.commandTT;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;

/**
 * @author (c) trienow 2016 - 2022
 */
public class CommandTT
{
	public static LiteralArgumentBuilder<CommandSourceStack> register()
	{
		return LiteralArgumentBuilder.<CommandSourceStack>literal("tt")
				.then(Spawn.register())
				.then(AddMotion.register())
				.then(Help.register())
				.then(Heal.register())
				.then(Random.register())
				.then(SetAbsorption.register())
				.then(Invulnerable.register());
	}
}
