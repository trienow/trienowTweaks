package de.trienow.trienowtweaks.events;

import de.trienow.trienowtweaks.capabilities.IPlayerCapability;
import de.trienow.trienowtweaks.capabilities.PlayerCapabilityProvider;
import de.trienow.trienowtweaks.config.Config;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.network.PacketReqPlayerCaps;
import de.trienow.trienowtweaks.utils.LevelUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author (c) trienow 2016 - 2023
 */
@Mod.EventBusSubscriber
public class CommonEvents
{
	@SubscribeEvent
	public static void onAttachCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> evt)
	{
		if (evt.getObject() instanceof Player)
		{
			evt.addCapability(new ResourceLocation(TrienowTweaks.MODID, "player_capability"), new PlayerCapabilityProvider());
		}
	}

	@SubscribeEvent
	public static void onPlayerClone(final PlayerEvent.Clone evt)
	{
		if (!evt.getEntity().level.isClientSide())
		{
			evt.getOriginal().reviveCaps();
			//noinspection CodeBlock2Expr
			evt.getOriginal().getCapability(IPlayerCapability.PLAYER_CAP).ifPresent((pcap0) -> {
				evt.getEntity().getCapability(IPlayerCapability.PLAYER_CAP)
						.ifPresent(pcap0::clone);
			});
			evt.getOriginal().invalidateCaps();
		}
	}

	@SubscribeEvent
	public static void onPlayerLogin(final PlayerLoggedInEvent evt)
	{
		Player sidedPlayer = evt.getEntity();
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
	public static void onEntitySpawn(final EntityJoinLevelEvent evt)
	{
		// When accessing blocks or chunks while the world is loading (EVEN IF THEY ARE THERE) the loading stalls.
		Level level = evt.getLevel();
		Entity ent = evt.getEntity();
		if (level.isClientSide())
		{
			if (ent instanceof Player player)
			{
				new PacketReqPlayerCaps(player.getUUID()).sendToServer();
			}
		}
		else
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
