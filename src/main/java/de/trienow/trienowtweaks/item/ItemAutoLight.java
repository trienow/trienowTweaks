package de.trienow.trienowtweaks.item;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.compat.CompatManager;
import de.trienow.trienowtweaks.compat.curios.ICuriosProxy;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author trienow 2017 - 2023
 */
public class ItemAutoLight extends Item
{
	private int activeTick = 0;

	public ItemAutoLight()
	{
		super(new Properties().stacksTo(1));
	}

	@Override
	public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected)
	{
		if (activeTick >= -90)
		{
			if (pLevel.isClientSide() || !pEntity.onGround() || !(pEntity instanceof Player))
			{
				return;
			}

			try
			{
				Player player = (Player) pEntity;
				BlockPos pos = player.blockPosition();
				if (pos.getY() > pLevel.getMaxBuildHeight() || pos.getY() < pLevel.getMinBuildHeight())
				{
					return;
				}

				int lightLevel = pLevel.getBrightness(LightLayer.BLOCK, pos);
				if (lightLevel <= 7)
				{
					lightLevel = pLevel.getBrightness(LightLayer.SKY, pos) - pLevel.getSkyDarken();
				}

				if (lightLevel <= 7 && pLevel.getBlockState(pos).isAir())
				{
					pLevel.setBlock(pos, AtomBlocks.GENERIC_LIGHT.get().defaultBlockState(), Block.UPDATE_ALL);
				}
				activeTick = -100;
			}
			catch (Exception ex)
			{
				TrienowTweaks.LOG.warn("[TT][ITEMAUTOLIGHT] " + ex.getMessage());
				activeTick = -200;
			}
		}
		else
		{
			activeTick++;
		}
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
	{
		pTooltipComponents.add(Component.translatable("item.trienowtweaks.auto_light.tooltip0"));
		pTooltipComponents.add(Component.translatable("item.trienowtweaks.auto_light.tooltip1"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
	{
		ItemStack heldItem = pPlayer.getItemInHand(pUsedHand);
		boolean success = CompatManager.curiosProxy.trySetStackInSlot(ICuriosProxy.ID_NECKLACE, pPlayer, heldItem);
		if (success)
		{
			return InteractionResultHolder.success(heldItem);
		}
		else
		{
			return InteractionResultHolder.fail(heldItem);
		}
	}
}
