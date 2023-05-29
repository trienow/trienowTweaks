package de.trienow.trienowtweaks.item.armor;

import de.trienow.trienowtweaks.armor.ModelKnight;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorCustomBase extends ItemArmorBase
{
	private final String baseName;

	public ItemArmorCustomBase(String baseName, EntityEquipmentSlot equipmentSlotIn)
	{
		super(baseName, equipmentSlotIn);
		this.baseName = baseName;
	}

	protected ModelBiped[] models = null;

	@Override
	public ArmorMaterial getArmorMaterial()
	{
		return EnumHelper.addArmorMaterial(baseName, TrienowTweaks.MODID + ":" + baseName, 100000, new int[] { 8, 8, 8, 8 }, 100, null, 2.0f);
	}

	@Override
	@SideOnly(value = Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
	{
		int slotIndex = armorSlot.ordinal();
		ModelBiped model = getArmorModelForSlot(slotIndex);
		if (model == null)
		{
			model = provideArmorModelForSlot(armorSlot);
		}
		return model;
	}

	@SideOnly(value = Side.CLIENT)
	private ModelBiped getArmorModelForSlot(int slot)
	{
		if (models == null)
		{
			models = new ModelBiped[4];
		}
		return models[slot - 2];
	}

	@SideOnly(value = Side.CLIENT)
	private ModelBiped provideArmorModelForSlot(EntityEquipmentSlot slot)
	{
		int slotNo = slot.ordinal();
		models[slotNo -= 2] = new ModelKnight(slot);
		return models[slotNo];
	}
}
