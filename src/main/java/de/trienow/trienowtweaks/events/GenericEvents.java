package de.trienow.trienowtweaks.events;

import de.trienow.trienowtweaks.config.Config;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.utils.LevelUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author (c) trienow 2016 - 2022
 */
@Mod.EventBusSubscriber
public class GenericEvents
{
	private static final String TAG_PLAYER_EXACT_SPAWN = TrienowTweaks.MODID + ":setExactSpawn";

	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent evt)
	{
		Player sidedPlayer = evt.getPlayer();
		if (sidedPlayer.level.isClientSide())
		{
			return;
		}

		// SET EXACT SPAWN POINT
		if (Config.getServerConfig().exactSpawnpoint.get())
		{
			ServerPlayer player = (ServerPlayer) sidedPlayer;
			Level level = player.level;

			final BlockPos respawnPos = player.getRespawnPosition();
			final BlockPos worldspawnPos = LevelUtils.getSpawn(level);

			if (respawnPos == null || worldspawnPos == respawnPos)
			{
				player.teleportTo(worldspawnPos.getX() + 0.5f, worldspawnPos.getY() + 0.5f, worldspawnPos.getZ() + 0.5f);
				player.setRespawnPosition(level.dimension(), worldspawnPos, 0.0F, true, false);
				TrienowTweaks.LOG.info("Moving " + player.getDisplayName().getString() + " to exact spawnpoint");
			}
		}
	}

	// ENTITY PROHIBITATOR
	@SubscribeEvent
	public static void onEntitySpawn(EntityJoinWorldEvent evt)
	{
		// When accessing blocks or chunks while the world is loading (EVEN IF THEY ARE THERE) the loading stalls.
		Level level = evt.getWorld();
		if (level.isClientSide())
		{
			return;
		}

		Entity ent = evt.getEntity();
		if (!(ent instanceof Player) && ent.getClassification(true) == MobCategory.MONSTER)
		{
			BlockPos pos = ent.blockPosition();
			pos = new BlockPos((pos.getX() >> 4) * 16, 255, (pos.getZ() >> 4) * 16);
			ChunkPos cPos = new ChunkPos(pos);

			if (level.hasChunk(cPos.x, cPos.z))
			{
				ChunkSource cs = level.getChunkSource();

				//Calling the normal cs.getChunk method makes the initial world generation fall into a deadlock towards the end.
				ChunkAccess chunk = cs.getChunkNow(cPos.x, cPos.z);

				if (chunk != null)
				{
					if (chunk.getBlockState(pos).getBlock() instanceof de.trienow.trienowtweaks.blocks.BlockEntityProhibitator)
					{
						evt.setCanceled(true);
					}
				}
			}
		}
	}
}
