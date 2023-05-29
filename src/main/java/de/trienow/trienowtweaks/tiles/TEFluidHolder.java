package de.trienow.trienowtweaks.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.TileFluidHandler;

/**
 * @author (c) trienow 2018 - 2021
 */
public class TEFluidHolder extends TileFluidHandler implements ITickable, ICapabilityProvider
{
	private int tick = 0;

	private int lastSyncedFluidAmt = 0;

	/**
	 * Constructs an object of type TEFluidHolder.java
	 */
	public TEFluidHolder()
	{
		tank = new FluidTank(FluidRegistry.WATER, 0, 1000);
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		//The client does not need most tags, stored in the TE. Only the fluid amount matters here, because of Hwyla.
		return new SPacketUpdateTileEntity(pos, 1, tank.writeToNBT(new NBTTagCompound()));
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public void update()
	{
		if (!world.isRemote && tick > 20)
		{
			tick = 0;

			IBlockState blockState = world.getBlockState(pos.up());
			ResourceLocation reLoc = blockState.getBlock().getRegistryName();
			if (reLoc != null && "botania:altar".equals(reLoc.toString()))
			{
				TileEntity te = world.getTileEntity(pos.up());
				if (te != null)
				{
					NBTTagCompound teData = te.writeToNBT(new NBTTagCompound());

					if (!teData.getBoolean("hasWater") && !teData.getBoolean("hasLava"))
					{
						FluidStack stack = tank.drain(1000, false);
						if (stack != null && stack.amount == 1000)
						{
							//Drain 1000mB from Tank
							tank.drain(1000, true);

							teData.setBoolean("hasWater", true);
							te.readFromNBT(teData);
							world.notifyBlockUpdate(pos.up(), blockState, blockState, 5);

							lastSyncedFluidAmt = -1;
						}
					}
				}
			}

			if (lastSyncedFluidAmt != tank.getFluidAmount())
			{
				markDirty();
				IBlockState state = getBlockType().getDefaultState();
				world.notifyBlockUpdate(pos, state, state, 4);
				lastSyncedFluidAmt = tank.getFluidAmount();
			}
		}
		else
		{
			tick++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
		{
			return (T) tank;
		}

		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;

		return super.hasCapability(capability, facing);
	}
}
