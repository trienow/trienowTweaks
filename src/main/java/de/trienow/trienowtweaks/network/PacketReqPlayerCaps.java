package de.trienow.trienowtweaks.network;

import de.trienow.trienowtweaks.capabilities.IPlayerCapability;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * @author trienow 2023
 * Thanks to the minecraft forge docs, for explaining how to work with packets so well!
 */
public record PacketReqPlayerCaps(UUID playerUuid)
{
	public static void encoder(PacketReqPlayerCaps syncPcap, FriendlyByteBuf buf)
	{
		buf.writeUUID(syncPcap.playerUuid);
	}

	public static PacketReqPlayerCaps decoder(FriendlyByteBuf buf)
	{
		return new PacketReqPlayerCaps(buf.readUUID());
	}

	public static void messageConsumer(PacketReqPlayerCaps reqPcaps, Supplier<NetworkEvent.Context> ctxSupplier)
	{
		//No need to enqueue this on the main thread
		ServerPlayer sender = ctxSupplier.get().getSender();
		Player player = null;

		if (sender != null)
		{
			player = sender.level().getPlayerByUUID(reqPcaps.playerUuid);
		}

		if (player != null)
		{
			player.getCapability(IPlayerCapability.PLAYER_CAP).ifPresent((pcap) -> new PacketPlayerCaps(pcap.getLayerTtRenderMode(), reqPcaps.playerUuid).sendToPlayer(sender));
		}
		ctxSupplier.get().setPacketHandled(true);
	}

	public void sendToServer()
	{
		PacketHandler.INSTANCE.sendToServer(this);
	}
}