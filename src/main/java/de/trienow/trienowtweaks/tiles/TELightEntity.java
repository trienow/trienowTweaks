package de.trienow.trienowtweaks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TELightEntity extends TileEntity
{
	public int[] posArray;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		if (posArray != null)
		{
			compound.setIntArray("lightPos", posArray);
		}
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		if (compound.hasKey("lightPos"))
		{
			posArray = compound.getIntArray("lightPos");
		}
	}
}
