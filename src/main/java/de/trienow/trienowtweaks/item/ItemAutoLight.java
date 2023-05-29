package de.trienow.trienowtweaks.item;

import java.util.List;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ItemAutoLight extends BaseItem implements IBauble
{
	byte activeTick = 0;

	public ItemAutoLight()
	{
		super("auto_light");
		maxStackSize = 1;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player)
	{
		if (player.world.isRemote || !player.onGround)
			return;

		if (activeTick >= -85)
		{
			try
			{
				activeTick = -100;
				BlockPos pos = player.getPosition();
				if (pos.getY() > 256)
					return;

				Chunk chunk = player.world.getChunkFromBlockCoords(pos);
				int lightLevel = Math.max(chunk.getLightFor(EnumSkyBlock.BLOCK, pos), (chunk.getLightFor(EnumSkyBlock.SKY, pos) - player.world.calculateSkylightSubtracted(1f)));
				if (lightLevel <= 7 && player.world.isAirBlock(pos))
					player.world.setBlockState(pos, AtomBlocks.blockTEGenericLight.getDefaultState());
			}
			catch (Exception ex)
			{
				TrienowTweaks.logger.warn("[TT][ITEMAUTOLIGHT] " + ex.getMessage());
				activeTick = -100;
			}
		}
		else
			activeTick++;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.RED + "Places an invisible light source, when it is dark.");
		tooltip.add(TextFormatting.GOLD + "Is only active, when in the amulet bauble slot.");
		tooltip.add(TextFormatting.YELLOW + "Currently it has infinite uses.");
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
	{
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
	{
		return true;
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0)
	{
		return BaubleType.AMULET;
	}
}
