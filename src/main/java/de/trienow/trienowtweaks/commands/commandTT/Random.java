package de.trienow.trienowtweaks.commands.commandTT;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.trienow.trienowtweaks.commands.CommandArg;
import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.utils.NumberUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;

import java.util.Optional;

import static de.trienow.trienowtweaks.main.TrienowTweaks.LOG;

/**
 * @author trienow 2019 - 2023
 */
class Random
{
	private static final String TEXT_START = "cmd." + TrienowTweaks.MODID + ".tt.random.start";
	private static final String TEXT_FAIL = "cmd." + TrienowTweaks.MODID + ".tt.random.fail";
	private static final String TEXT_SUCCESS = "cmd." + TrienowTweaks.MODID + ".tt.random.success";

	public static ArgumentBuilder<CommandSourceStack, ?> register()
	{
		//@fm:off
		return Commands.literal(SubCommands.random.toString()).requires((cs) -> cs.hasPermission(2))
				.then(CommandArg.PLAYER.arg().executes(ctx -> execRandom(ctx.getSource(), CommandArg.PLAYER.get(ctx))))
				.executes(ctx -> execRandom(ctx.getSource(), null));
		//@fm:on
	}

	/**
	 * @param sender The command executor
	 * @param target The player, which is targeted
	 * @return 1, because success?
	 * @throws CommandSyntaxException When neither the targets nor sender were players
	 */
	private static int execRandom(CommandSourceStack sender, ServerPlayer target) throws CommandSyntaxException
	{
		target = target == null ? sender.getPlayerOrException() : target;

		CommandUtils.sendIm(target, TEXT_START);

		ServerLevel level = target.level();
		WorldBorder border = level.getWorldBorder();
		BlockPos spawnPoint = level.getLevelData().getSpawnPos();
		RandomSource rand = level.random;
		int iterations = 0;

		while (true)
		{
			if (iterations > 2000)
			{
				Component reason = Component.translatable(TEXT_FAIL + rand.nextInt(5));
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
				chunk.postProcessGeneration(level);
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
			final Holder<Biome> biome = level.getBiome(pos);
			double tx = pos.getX() + 0.5f;
			double ty = pos.getY() + 3;
			double tz = pos.getZ() + 0.5f;

			String biomeName = "<UNKNOWN>";
			Optional<ResourceKey<Biome>> biomeResourceKey = biome.unwrapKey();
			if (biomeResourceKey.isPresent())
			{
				biomeName = biomeResourceKey.get().location().getPath().replaceAll("_", " ");
			}

			LOG.info("[TT] /tt random: Teleporting {} to [{} {} {}] ({}) issued by {}. Tried {} times.",
					target.getDisplayName().toString(),
					tx,
					ty,
					tz,
					biomeName,
					sender.getDisplayName().toString(),
					iterations);

			Component cBiome = Component.literal(biomeName).withStyle(ChatFormatting.GREEN);
			Component cX = Component.literal("" + pos.getX()).withStyle(ChatFormatting.AQUA);
			Component cY = Component.literal("" + pos.getY()).withStyle(ChatFormatting.AQUA);
			Component cZ = Component.literal("" + pos.getZ()).withStyle(ChatFormatting.AQUA);

			CommandUtils.sendIm(target, TEXT_SUCCESS + rand.nextInt(4), cBiome, cX, cY, cZ);
			target.teleportTo(tx, ty, tz);
			break;
		}

		return 1;
	}
}
