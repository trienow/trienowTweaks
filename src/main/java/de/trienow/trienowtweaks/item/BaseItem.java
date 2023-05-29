package de.trienow.trienowtweaks.item;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

public class BaseItem extends Item
{
	public BaseItem(String name)
	{
		setUnlocalizedName(TrienowTweaks.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(TrienowTweaks.trienowTab);
	}

	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(Objects.requireNonNull(getRegistryName()), "inventory"));
	}
}
