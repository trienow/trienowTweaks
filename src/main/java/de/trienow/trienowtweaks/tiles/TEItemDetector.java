package de.trienow.trienowtweaks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TEItemDetector extends TileEntity
{
	public int amt;

	public TEItemDetector()
	{
		this.amt = 1;
	}

	public TEItemDetector(int amt)
	{
		this.amt = amt;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		if (amt != 0)
		{
			compound.setInteger("amt", amt);
		}
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		if (compound.hasKey("amt"))
		{
			amt = compound.getInteger("amt");
		}
	}
}
