package de.trienow.trienowtweaks.armor;

import de.trienow.trienowtweaks.entity.model.ModelDrToast;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.function.Consumer;

/**
 * @author (c) trienow 2016 - 2022
 */
public class ArmorDrToast extends ItemArmorBase
{
	public ArmorDrToast(EquipmentSlot slot)
	{
		super("drtoast", ArmorMaterial.DR_TOAST, slot, new Properties());
	}

	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer)
	{
		consumer.accept(new ArmorRenderProperties(ModelDrToast::makeModel));
	}

	@Override
	public boolean isDamageable(ItemStack stack)
	{
		return false;
	}
}
