package de.trienow.trienowtweaks.network;

import de.trienow.trienowtweaks.capabilities.IPlayerCapability;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * @author (c) trienow 2022
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

	public static void messageConsumer(PacketReqPlayerCaps msg, Supplier<NetworkEvent.Context> ctxSupplier)
	{
		//No need to enqueue this on the main thread
		DistExecutor.unsafeRunWhenOn(Dist.DEDICATED_SERVER, () -> () -> handlePacketClient(ctxSupplier.get().getSender(), msg));
		ctxSupplier.get().setPacketHandled(true);
	}

	private static void handlePacketClient(ServerPlayer sender, PacketReqPlayerCaps reqPcaps)
	{
		Player player = sender.getLevel().getPlayerByUUID(reqPcaps.playerUuid);
		if (player instanceof Player)
		{
			player.getCapability(IPlayerCapability.PLAYER_CAP).ifPresent((pcap) -> new PacketPlayerCaps(pcap.getLayerTtRenderMode(), reqPcaps.playerUuid).sendToPlayer(sender));
		}
	}

	public void sendToServer()
	{
		PacketHandler.INSTANCE.sendToServer(this);
	}
}