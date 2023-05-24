package de.trienow.trienowtweaks.armor;

import de.trienow.trienowtweaks.entity.model.ModelKnight;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

/**
 * @author (c) trienow 2016 - 2023
 */
public class ArmorKnight extends ArmorItem
{

	public ArmorKnight(ArmorItem.Type slot)
	{
		super(ArmorMaterial.KNIGHT, slot, new Properties());
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer)
	{
		consumer.accept(new ArmorRenderProperties(ModelKnight::makeModel));
	}

	@Override
	public boolean isDamageable(ItemStack stack)
	{
		return false;
	}
}
