package de.trienow.trienowtweaks.armor;

import de.trienow.trienowtweaks.entity.model.ModelKnight;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.function.Consumer;

/**
 * @author (c) trienow 2016 - 2022
 */
public class ArmorKnight extends ItemArmorBase
{

	public ArmorKnight(EquipmentSlot slot)
	{
		super("knight", ArmorMaterial.KNIGHT, slot, new Properties());
	}

	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer)
	{
		consumer.accept(new ArmorRenderProperties(ModelKnight::makeModel));
	}

	@Override
	public boolean isDamageable(ItemStack stack)
	{
		return false;
	}
}
