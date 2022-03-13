package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.armor.ArmorDrToast;
import de.trienow.trienowtweaks.armor.ArmorKnight;
import de.trienow.trienowtweaks.item.ItemAutoFood;
import de.trienow.trienowtweaks.item.ItemAutoLight;
import de.trienow.trienowtweaks.item.ItemWeWand;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

@SuppressWarnings("SameParameterValue")
public class AtomItems
{
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TrienowTweaks.MODID);

	public static final RegistryObject<Item> WE_WAND = ITEMS.register("we_wand", ItemWeWand::new);

	public static final RegistryObject<Item> AUTO_LIGHT = ITEMS.register("auto_light", ItemAutoLight::new);
	public static final RegistryObject<Item> AUTO_FOOD = ITEMS.register("auto_food", ItemAutoFood::new);

	public static final RegistryObject<Item> DRTOAST_HEAD = register("drtoast", EquipmentSlot.HEAD, ArmorDrToast::new);

	public static final RegistryObject<Item> KNIGHT_HEAD = register("knight", EquipmentSlot.HEAD, ArmorKnight::new);
	public static final RegistryObject<Item> KNIGHT_CHEST = register("knight", EquipmentSlot.CHEST, ArmorKnight::new);
	public static final RegistryObject<Item> KNIGHT_LEGS = register("knight", EquipmentSlot.LEGS, ArmorKnight::new);
	public static final RegistryObject<Item> KNIGHT_FEET = register("knight", EquipmentSlot.FEET, ArmorKnight::new);

	public static void init(IEventBus modEventBus)
	{
		ITEMS.register(modEventBus);
	}

	private static <I extends Item> RegistryObject<Item> register(String baseName, EquipmentSlot slot, Function<EquipmentSlot, ArmorItem> creator)
	{
		switch (slot)
		{
			case HEAD -> baseName += "_helmet";
			case CHEST -> baseName += "_chestplate";
			case LEGS -> baseName += "_leggings";
			case FEET -> baseName += "_boots";
		}

		return ITEMS.register(baseName, () -> creator.apply(slot));
	}
}
