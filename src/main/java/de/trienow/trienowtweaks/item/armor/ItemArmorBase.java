package de.trienow.trienowtweaks.item.armor;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

public class ItemArmorBase extends ItemArmor
{
	public ItemArmorBase(String baseName, EntityEquipmentSlot equipmentSlotIn)
	{
		super(getMaterial(baseName), 3, equipmentSlotIn);
		setMaxStackSize(1);
		setCreativeTab(TrienowTweaks.trienowTab);

		switch (equipmentSlotIn)
		{
			case HEAD:
				baseName += "_helmet";
				break;
			case CHEST:
				baseName += "_chestplate";
				break;
			case LEGS:
				baseName += "_leggings";
				break;
			case FEET:
				baseName += "_boots";
				break;
			default:
				break;
		}

		setUnlocalizedName(TrienowTweaks.MODID + "." + baseName);
		this.setRegistryName(baseName);
	}

	public static ArmorMaterial getMaterial(String baseName)
	{
		return EnumHelper.addArmorMaterial(baseName, TrienowTweaks.MODID + ":" + baseName, 1000, new int[] { 10, 10, 10, 10 }, 10, null, 10.0f);
	}

	@Override
	public boolean isDamageable()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(Objects.requireNonNull(getRegistryName()), "inventory"));
	}
}
