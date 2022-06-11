package de.trienow.trienowtweaks.network;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

/**
 * @author (c) trienow 2022
 * Thanks to the minecraft forge docs, for explaining how to work with packets so well!
 */
public class PacketHandler
{
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(TrienowTweaks.MODID, "main"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals);

	private static int id = 0;

	public static void register()
	{
		INSTANCE.registerMessage(id++, PacketPlayerCaps.class, PacketPlayerCaps::encoder, PacketPlayerCaps::decoder, PacketPlayerCaps::messageConsumer, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
		INSTANCE.registerMessage(id++, PacketReqPlayerCaps.class, PacketReqPlayerCaps::encoder, PacketReqPlayerCaps::decoder, PacketReqPlayerCaps::messageConsumer, Optional.of(NetworkDirection.PLAY_TO_SERVER));
	}
}
