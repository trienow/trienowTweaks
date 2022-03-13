package de.trienow.trienowtweaks.armor;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

/**
 * @author (c) trienow 2016 - 2022
 */
public class ItemArmorBase extends ArmorItem
{
	protected ItemArmorBase(String baseName, ArmorMaterial materialIn, EquipmentSlot slot, Properties props)
	{
		super(materialIn, slot, props.tab(TrienowTweaks.ITEM_GROUP).stacksTo(1));
	}
}
