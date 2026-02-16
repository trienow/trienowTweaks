package de.trienow.trienowtweaks.atom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.trienow.trienowtweaks.datacomponents.AutoFoodData;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AtomDataComponents
{
	private static final DeferredRegister.DataComponents DC = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, TrienowTweaks.MODID);

	public static final Supplier<DataComponentType<AutoFoodData>> AUTO_FOOD = DC.registerComponentType(
			"auto_food",
			builder -> builder.persistent(C.AUTO_FOOD_CODEC).networkSynchronized(C.AUTO_FOOD_STREAM_CODEC)
	);

	public static void init(IEventBus bus)
	{
		DC.register(bus);
	}

	public static class C
	{
		public static final Codec<AutoFoodData> AUTO_FOOD_CODEC = RecordCodecBuilder.create(instance ->
				instance.group(
						Codec.BYTE.fieldOf("init").forGetter(AutoFoodData::init),
						Codec.BYTE.fieldOf("warn").forGetter(AutoFoodData::warn)
				).apply(instance, AutoFoodData::new));

		public static final StreamCodec<ByteBuf, AutoFoodData> AUTO_FOOD_STREAM_CODEC = StreamCodec.composite(
				ByteBufCodecs.BYTE, AutoFoodData::init,
				ByteBufCodecs.BYTE, AutoFoodData::warn,
				AutoFoodData::new
		);
	}
}
