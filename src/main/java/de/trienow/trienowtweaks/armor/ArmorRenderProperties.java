package de.trienow.trienowtweaks.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * @author (c) trienow 2022
 */
record ArmorRenderProperties(Function<EquipmentSlot, HumanoidModel<LivingEntity>> factory) implements IItemRenderProperties
{
	@Nullable
	@Override
	public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default)
	{
		return factory.apply(armorSlot);
	}
}