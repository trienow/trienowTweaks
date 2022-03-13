package de.trienow.trienowtweaks.commands.commandTT;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.trienow.trienowtweaks.commands.CommandArg;
import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.utils.LevelUtils;
import de.trienow.trienowtweaks.utils.NumberUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;

import static de.trienow.trienowtweaks.main.TrienowTweaks.LOG;

/**
 * @author (c) trienow 2019 - 2022
 */
class Random
{
	private static final String TEXT_FAIL = "cmd." + TrienowTweaks.MODID + ".tt.random.fail";
	private static final String TEXT_SUCCESS = "cmd." + TrienowTweaks.MODID + ".tt.random.success";

	public static ArgumentBuilder<CommandSourceStack, ?> register()
	{
		//@fm:off
		return Commands.literal(SubCommands.random.toString()).requires((cs) -> cs.hasPermission(4))
				.then(CommandArg.PLAYER.arg().executes(ctx -> execRandom(ctx.getSource(), CommandArg.PLAYER.get(ctx))))
				.executes(ctx -> execRandom(ctx.getSource(), null));
		//@fm:on
	}

	/**
	 * @param sender The command executor
	 * @param target The player, which is targeted
	 * @return 1, because success?
	 * @throws CommandSyntaxException When neither the targets or sender were players
	 */
	private static int execRandom(CommandSourceStack sender, ServerPlayer target) throws CommandSyntaxException
	{
		target = target == null ? sender.getPlayerOrException() : target;

		Level level = target.level;
		WorldBorder border = level.getWorldBorder();
		BlockPos spawnPoint = LevelUtils.getSpawn(level);
		java.util.Random rand = level.random;
		int iterations = 0;

		while (true)
		{
			if (iterations > 2000)
			{
				TranslatableComponent reason = new TranslatableComponent(TEXT_FAIL + rand.nextInt(5));
				CommandUtils.sendIm(sender, TEXT_FAIL, reason);
				break;
			}
			else
			{
				iterations++;
			}

			int x = NumberUtils.getRandomNumberInRange(rand, (int) border.getMinX() + 1, (int) border.getMaxX());
			int z = NumberUtils.getRandomNumberInRange(rand, (int) border.getMinZ() + 1, (int) border.getMaxZ());
			BlockPos xz = new BlockPos(x, 0, z);

			if (spawnPoint.closerThan(xz, 128.0F))
			{
				continue;
			}

			LevelChunk chunk = level.getChunkAt(xz);
			if (!level.hasChunk(x >> 4, z >> 4))
			{
				LOG.warn("[TT] /tt random: GENNING");
				chunk.postProcessGeneration();
			}

			BlockPos pos = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, xz);
			if (pos.getY() < 30 || level.containsAnyLiquid(new AABB(pos).inflate(1, 1, 1).inflate(-1, -1, -1)))
			{
				continue;
			}

			if (!level.getBlockState(pos.below()).entityCanStandOn(level, pos.below(), target))
			{
				continue;
			}

			// Is air present at the position, where the player should be ported to?
			BlockPos testPos = pos;
			boolean noAir = false;
			for (int i = 0; i < 5; i++)
			{
				testPos = testPos.above();
				if (!level.getBlockState(testPos).isAir())
				{
					noAir = true;
					break;
				}
			}

			if (noAir)
			{
				continue;
			}

			// WE DID IT!
			final Biome biome = level.getBiome(pos).value();
			double tx = pos.getX() + 0.5f;
			double ty = pos.getY() + 3;
			double tz = pos.getZ() + 0.5f;

			LOG.info("[TT] /tt random: Teleporting {} to [{} {} {}] ({}) issued by {}. Tried {} times.",
					target.getDisplayName().toString(),
					tx,
					ty,
					tz,
					biome.getRegistryName().toString(),
					sender.getDisplayName().toString(),
					iterations);

			String biomeName = biome.getRegistryName().getPath().replaceAll("_", " ");
			Component cBiome = new TextComponent(biomeName).withStyle(ChatFormatting.GREEN);
			Component cX = new TextComponent("" + pos.getX()).withStyle(ChatFormatting.AQUA);
			Component cY = new TextComponent("" + pos.getY()).withStyle(ChatFormatting.AQUA);
			Component cZ = new TextComponent("" + pos.getZ()).withStyle(ChatFormatting.AQUA);

			CommandUtils.sendIm(target, TEXT_SUCCESS + rand.nextInt(4), cBiome, cX, cY, cZ);
			target.teleportTo(tx, ty, tz);
			break;
		}

		return 1;
	}
}
