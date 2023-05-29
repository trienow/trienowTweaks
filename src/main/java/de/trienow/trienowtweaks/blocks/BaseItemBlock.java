package de.trienow.trienowtweaks.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author trienow 2017
 */
public abstract class BaseItemBlock extends BaseBlock
{
	/**
	 * Constructs an object of type BaseItemBlock.java
	 */
	public BaseItemBlock(String name, Material materialIn, boolean showInCreativeTab)
	{
		super(name, materialIn, showInCreativeTab);
	}

	/**
	 * Registers a new <b>Item</b> Model with custom meta and a custom resource location
	 *
	 * @param meta      The Meta, the item model has
	 * @param stateName The prefix of the item resource location
	 */
	@SideOnly(Side.CLIENT)
	protected void initModel(int meta, String stateName)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), meta, new ModelResourceLocation(getRegistryName() + "_" + stateName, "inventory"));
	}

	@Override
	public abstract void initModel();

	/**
	 * Gets the localized name for the {@link ItemStack}
	 *
	 * @param stack The {@link ItemStack} to be named
	 * @return Returns the localized name {@link String}
	 */
	@SideOnly(Side.CLIENT)
	public abstract String getDisplayName(ItemStack stack);

	@Override
	public abstract void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items);

}
