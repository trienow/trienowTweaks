package de.trienow.trienowtweaks.events;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.main.Config;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

@Mod.EventBusSubscriber
public class GenericEvents
{
	private static final String TAG_PLAYER_EXACT_SPAWN = TrienowTweaks.MODID + ":setExactSpawn";

	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent evt)
	{
		if (!evt.player.world.isRemote)
		{
			Config.updateExecutionTime();
			EntityPlayer player = evt.player;

			// SET EXACT SPAWN POINT
			NBTTagCompound playerData = player.getEntityData();
			NBTTagCompound playerPersisted = playerData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);

			if (!playerPersisted.getBoolean(TAG_PLAYER_EXACT_SPAWN))
			{
				BlockPos spawn = player.world.getSpawnPoint();

				player.setPositionAndUpdate(spawn.getX() + 0.5f, spawn.getY() + 0.5f, spawn.getZ() + 0.5f);
				player.setSpawnPoint(spawn, true);
				TrienowTweaks.logger.info("Moving " + player.getDisplayNameString() + " to exact spawnpoint");

				playerPersisted.setBoolean(TAG_PLAYER_EXACT_SPAWN, true);
				playerData.setTag(EntityPlayer.PERSISTED_NBT_TAG, playerPersisted);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerLogout(PlayerLoggedOutEvent evt)
	{
		if (!evt.player.world.isRemote)
			Config.updateExecutionTime();
	}

	// ENTITY PROHIBITATOR
	@SubscribeEvent
	public static void onEntitySpawn(EntityJoinWorldEvent evt)
	{
		if (!evt.getWorld().isRemote && !(evt.getEntity() instanceof EntityPlayer) && (evt.getEntity().isCreatureType(EnumCreatureType.MONSTER, true) || evt.getEntity()
				.isCreatureType(EnumCreatureType.MONSTER, false)))
		{
			BlockPos pos = evt.getEntity().getPosition();
			int newX = pos.getX() % 16;
			int newZ = pos.getZ() % 16;

			newX = (newX < 0 ? 16 + newX : newX);
			newZ = (newZ < 0 ? 16 + newZ : newZ);
			pos = new BlockPos(pos.getX() - newX, 255, pos.getZ() - newZ);

			if (evt.getWorld().getBlockState(pos) == AtomBlocks.blockEntityProhibitator.getDefaultState())
				evt.setCanceled(true);
		}
	}
}
