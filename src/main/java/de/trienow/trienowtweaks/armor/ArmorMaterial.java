package de.trienow.trienowtweaks.armor;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * @author (c) trienow 2016 - 2022
 */
@SuppressWarnings("SameParameterValue")
public enum ArmorMaterial implements net.minecraft.world.item.ArmorMaterial
{
	DR_TOAST("drtoast", 30, new int[] { 2, 0, 0, 0 }, 16, SoundEvents.ARMOR_EQUIP_LEATHER, 1.75f, Ingredient.EMPTY),
	KNIGHT("knight", 35, new int[] { 3, 6, 8, 3 }, 16, SoundEvents.ARMOR_EQUIP_GOLD, 2.5f, Ingredient.EMPTY);

	private static final int[] MAX_DAMAGE_ARRAY = new int[] { 12, 15, 15, 12 };
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final Ingredient repairMaterial;

	ArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountsIn, int enchantabilityIn, SoundEvent equipSoundIn, float pToughness, Ingredient repairMaterialSupplier)
	{
		this.name = new ResourceLocation(TrienowTweaks.MODID, nameIn).toString();
		this.maxDamageFactor = maxDamageFactorIn;
		this.damageReductionAmountArray = damageReductionAmountsIn;
		this.enchantability = enchantabilityIn;
		this.soundEvent = equipSoundIn;
		this.toughness = pToughness;
		this.repairMaterial = repairMaterialSupplier;
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlot slotIn)
	{
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlot slotIn)
	{
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	@Override
	public int getEnchantmentValue()
	{
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound()
	{
		return this.soundEvent;
	}

	@Override
	public Ingredient getRepairIngredient()
	{
		return this.repairMaterial;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public float getToughness()
	{
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance()
	{
		return 0f;
	}
}
