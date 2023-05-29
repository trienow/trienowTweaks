package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.item.armor.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AtomItemArmor
{
	public static final ItemArmorCustom_boots itemArmorCustom_boots = new ItemArmorCustom_boots();
	public static final ItemArmorCustom_chestplate itemArmorCustom_chestplate = new ItemArmorCustom_chestplate();
	public static final ItemArmorCustom_helmet itemArmorCustom_helmet = new ItemArmorCustom_helmet();
	public static final ItemArmorCustom_leggings itemArmorCustom_leggings = new ItemArmorCustom_leggings();

	public static final ItemArmorDrToast_head itemArmorDrToast_head = new ItemArmorDrToast_head();

	public static final ItemArmorBase[] itemArmorList = { itemArmorCustom_boots, itemArmorCustom_chestplate, itemArmorCustom_helmet, itemArmorCustom_leggings, itemArmorDrToast_head };

	/**
	 * Initializes the Armor Models
	 */
	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		for (ItemArmorBase itemArmorBase : itemArmorList)
		{
			itemArmorBase.initModel();
		}
	}
}
