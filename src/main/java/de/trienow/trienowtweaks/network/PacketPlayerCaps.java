package de.trienow.trienowtweaks.network;

import de.trienow.trienowtweaks.capabilities.IPlayerCapability;
import de.trienow.trienowtweaks.entity.layer.LayerTtRenderMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * @author (c) trienow 2023
 * Thanks to the minecraft forge docs, for explaining how to work with packets so well!
 */
public record PacketPlayerCaps(LayerTtRenderMode renderMode, UUID playerUuid)
{
	public static void encoder(PacketPlayerCaps syncPcap, FriendlyByteBuf buf)
	{
		buf.writeEnum(syncPcap.renderMode);
		buf.writeUUID(syncPcap.playerUuid);
	}

	public static PacketPlayerCaps decoder(FriendlyByteBuf buf)
	{
		return new PacketPlayerCaps(buf.readEnum(LayerTtRenderMode.class), buf.readUUID());
	}

	public static void messageConsumer(PacketPlayerCaps msg, Supplier<NetworkEvent.Context> ctxSupplier)
	{
		//No need to enqueue this on the main thread
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handlePacketClient(msg));
		ctxSupplier.get().setPacketHandled(true);
	}

	private static void handlePacketClient(PacketPlayerCaps syncPcap)
	{
		ClientLevel level = Minecraft.getInstance().level;
		Player clientPlayer = level != null ? level.getPlayerByUUID(syncPcap.playerUuid) : null;
		if (clientPlayer != null)
		{
			clientPlayer.getCapability(IPlayerCapability.PLAYER_CAP).ifPresent((pcap) -> pcap.setLayerTtRenderMode(syncPcap.renderMode));
		}
	}

	public void sendToAll()
	{
		PacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), this);
	}

	public void sendToPlayer(ServerPlayer serverPlayer)
	{
		PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), this);
	}
}
