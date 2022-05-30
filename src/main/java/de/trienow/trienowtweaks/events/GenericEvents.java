package de.trienow.trienowtweaks.events;

import de.trienow.trienowtweaks.config.Config;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.utils.LevelUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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

		ServerPlayer player = (ServerPlayer) sidedPlayer;

		// SET EXACT SPAWN POINT
		CompoundTag playerPersisted = player.getPersistentData();

		if (playerPersisted == null)
		{
			playerPersisted = new CompoundTag();
		}

		if (!playerPersisted.getBoolean(TAG_PLAYER_EXACT_SPAWN))
		{
			if (Config.getServerConfig().exactSpawnpoint.get())
			{
				Level level = player.level;
				BlockPos spawn = LevelUtils.getSpawn(level);

				player.teleportTo(spawn.getX() + 0.5f, spawn.getY() + 0.5f, spawn.getZ() + 0.5f);
				player.setRespawnPosition(level.dimension(), spawn, 0.0F, true, false);
				TrienowTweaks.LOG.info("Moving " + player.getDisplayName().getString() + " to exact spawnpoint");
			}

			playerPersisted.putBoolean(TAG_PLAYER_EXACT_SPAWN, true);
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
