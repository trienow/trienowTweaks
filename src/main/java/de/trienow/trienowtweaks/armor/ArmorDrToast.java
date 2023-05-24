package de.trienow.trienowtweaks.armor;

import de.trienow.trienowtweaks.entity.model.ModelDrToast;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

/**
 * @author (c) trienow 2016 - 2023
 */
public class ArmorDrToast extends ArmorItem
{
	public ArmorDrToast(ArmorItem.Type slot)
	{
		super(ArmorMaterial.DR_TOAST, slot, new Properties());
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer)
	{
		consumer.accept(new ArmorRenderProperties(ModelDrToast::makeModel));
	}

	@Override
	public boolean isDamageable(ItemStack stack)
	{
		return false;
	}
}
