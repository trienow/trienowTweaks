package de.trienow.trienowtweaks.item.armor;

import de.trienow.trienowtweaks.armor.ModelDrToast;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemArmorDrToast_head extends ItemArmorBase
{
	static final String baseName = "drtoast";

	public ItemArmorDrToast_head()
	{
		super(baseName, EntityEquipmentSlot.HEAD);
		setCreativeTab(TrienowTweaks.trienowTab);
	}

	protected ModelBiped[] models = null;

	@Override
	public ArmorMaterial getArmorMaterial()
	{
		return EnumHelper.addArmorMaterial(baseName, TrienowTweaks.MODID + ":" + baseName, 100000, new int[] { 100, 100, 100, 100 }, 100, null, 100.0f);
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
		models[slotNo -= 2] = new ModelDrToast(slot);
		return models[slotNo];
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.GOLD + "It's Sandwich time!");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
