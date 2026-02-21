package de.trienow.trienowtweaks.atom;

import com.mojang.serialization.Codec;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class AtomAttachments
{
	private static final DeferredRegister<AttachmentType<?>> ATT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, TrienowTweaks.MODID);

	public static final Supplier<AttachmentType<Integer>> LAYER_TT = ATT_TYPES.register(
			"layer_tt", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT.fieldOf("layer_tt"))
					.sync(ByteBufCodecs.INT)
					.build()
	);

	public static void init(IEventBus bus)
	{
		ATT_TYPES.register(bus);
	}
}
