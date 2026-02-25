package de.trienow.trienowtweaks.item;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.compat.CompatManager;
import de.trienow.trienowtweaks.compat.curios.ICuriosProxy;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @author trienow 2017 - 2023
 */
public class ItemAutoLight extends Item
{
	private int activeTick = 0;

	public ItemAutoLight(ResourceLocation registryName)
	{
		super(new Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, registryName)));
	}

	@Override public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot)
	{
		if (activeTick >= -90)
		{
			if (!entity.onGround() || !(entity instanceof ServerPlayer player))
			{
				return;
			}

			try
			{
				BlockPos pos = player.blockPosition();
				if (level.isOutsideBuildHeight(pos))
				{
					return;
				}

				int lightLevel = level.getBrightness(LightLayer.BLOCK, pos);
				if (lightLevel <= 7)
				{
					lightLevel = level.getBrightness(LightLayer.SKY, pos) - level.getSkyDarken();
				}

				if (lightLevel <= 7 && level.getBlockState(pos).isAir())
				{
					level.setBlock(pos, AtomBlocks.GENERIC_LIGHT.get().defaultBlockState(), Block.UPDATE_ALL);
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

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		tooltipAdder.accept(Component.translatable("item.trienowtweaks.auto_light.tooltip0"));
		tooltipAdder.accept(Component.translatable("item.trienowtweaks.auto_light.tooltip1"));
	}

	@Override public InteractionResult use(Level level, Player player, InteractionHand hand)
	{
		ItemStack heldItem = player.getItemInHand(hand);
		boolean success = CompatManager.curiosProxy.trySetStackInSlot(ICuriosProxy.ID_NECKLACE, player, heldItem);
		return success ? InteractionResult.SUCCESS : InteractionResult.FAIL;
	}
}
