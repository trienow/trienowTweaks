package de.trienow.trienowtweaks.integration.hwyla;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.List;

/**
 * @author (c) trienow 2018
 */
public class FluidHolderProvider implements IWailaDataProvider
{
	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		TileEntity te = accessor.getTileEntity();
		if (te != null)
		{
			IFluidHandler fh = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN);

			if (fh != null)
			{
				FluidStack fluidInformation = fh.getTankProperties()[0].getContents();

				int storedWater = fluidInformation == null ? 0 : fluidInformation.amount;

				currenttip.add("Stored " + TextFormatting.DARK_AQUA + "Water" + TextFormatting.RESET + ": " + TextFormatting.DARK_AQUA + storedWater + TextFormatting.RESET + "/1000mB");
			}
		}

		return IWailaDataProvider.super.getWailaBody(itemStack, currenttip, accessor, config);
	}
}
