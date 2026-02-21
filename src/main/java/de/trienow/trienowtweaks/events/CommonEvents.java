package de.trienow.trienowtweaks.events;

import de.trienow.trienowtweaks.atom.AtomAttachments;
import de.trienow.trienowtweaks.config.ServerConfig;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/**
 * @author trienow 2016 - 2023
 */
@EventBusSubscriber
public class CommonEvents
{
	@SubscribeEvent
	public static void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent evt)
	{
		Player sidedPlayer = evt.getEntity();
		Level level = sidedPlayer.level();
		if (!level.isClientSide())
		{
			// SET EXACT SPAWN POINT
			if (ServerConfig.CONFIG.exactSpawnpoint.get())
			{
				ServerPlayer player = (ServerPlayer) sidedPlayer;
				final BlockPos respawnPos = player.getRespawnConfig().pos();
				final BlockPos worldspawnPos = level.getLevelData().getSpawnPos();

				if (respawnPos == null || worldspawnPos == respawnPos)
				{
					player.teleportTo(worldspawnPos.getX() + 0.5f, worldspawnPos.getY() + 0.5f, worldspawnPos.getZ() + 0.5f);
					player.setRespawnPosition(new ServerPlayer.RespawnConfig(level.dimension(), worldspawnPos, 0.0F, true), false);
					TrienowTweaks.LOG.info("Moving {} to exact spawnpoint", player.getDisplayName().getString());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onClone(final PlayerEvent.Clone evt)
	{
		if (evt.isWasDeath() && evt.getOriginal().hasData(AtomAttachments.LAYER_TT))
		{
			evt.getEntity().setData(AtomAttachments.LAYER_TT, evt.getOriginal().getData(AtomAttachments.LAYER_TT));
		}
	}

	// ENTITY PROHIBITATOR
	@SubscribeEvent
	public static void onEntitySpawn(final EntityJoinLevelEvent evt)
	{
		// When accessing blocks or chunks while the world is loading (EVEN IF THEY ARE THERE) the loading stalls.
		Level level = evt.getLevel();
		Entity ent = evt.getEntity();
		if (!level.isClientSide())
		{
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

					if (chunk != null && chunk.getBlockState(pos).getBlock() instanceof de.trienow.trienowtweaks.blocks.BlockEntityProhibitator)
					{
						evt.setCanceled(true);
					}
				}
			}
		}
	}
}
