package de.trienow.trienowtweaks.item;

import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.compat.CompatManager;
import de.trienow.trienowtweaks.compat.curios.ICuriosProxy;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author trienow 2017 - 2023
 */
public class ItemAutoFood extends Item
{
	byte checkLimiter = -20;

	public ItemAutoFood()
	{
		super(new Properties().stacksTo(1).defaultDurability(500));
	}

	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (checkLimiter > 0)
		{
			if (worldIn.isClientSide() || !(entityIn instanceof Player player))
			{
				return;
			}

			int maxDamage = getMaxDamage(stack);

			CompoundTag tag = NBTInit(stack);
			if (tag.getByte("init") == 0)
			{
				stack.setDamageValue(maxDamage);
				tag.putByte("init", (byte) 1);
				tag.putByte("warn", (byte) 2);
			}

			searchForFood(stack, player);

			int currentItemDamage = stack.getDamageValue();
			if (currentItemDamage > maxDamage * 0.9)
			{
				NBTMessage(stack, player, NBTMessage.WARN);
			}

			if (currentItemDamage < maxDamage)
			{
				if (player.getFoodData().needsFood())
				{
					FoodData food = player.getFoodData();
					int hunger = 20 - food.getFoodLevel();
					int decrAmt = Math.min(hunger, (maxDamage - currentItemDamage));

					stack.setDamageValue(currentItemDamage + decrAmt);
					food.eat(decrAmt, 1f);
					food.setSaturation(5f);
				}
			}
			else
			{
				NBTMessage(stack, player, NBTMessage.DEATH);
			}

			checkLimiter = -20;
		}
		else
		{
			checkLimiter++;
		}
	}

	protected void searchForFood(ItemStack itemstack, Player ply)
	{
		int currentItemDamage = itemstack.getDamageValue();
		if (currentItemDamage < 1)
		{
			return;
		}

		int maxDamage = getMaxDamage(itemstack);
		Inventory inv = ply.getInventory();

		for (int i = 0; i < inv.getContainerSize(); i++)
		{
			ItemStack foodStack = inv.getItem(i);
			if (foodStack.isEdible())
			{
				FoodProperties food = foodStack.getItem().getFoodProperties();
				int healAmt = food != null ? food.getNutrition() : 0;
				healAmt /= 2; //Because AutoFood is a bit OP, food should only be half it's value

				if (healAmt < 1 || healAmt > currentItemDamage)
				{
					continue;
				}

				int itemsToFill = currentItemDamage / healAmt;
				int itemsToConsume = Math.min(itemsToFill, foodStack.getCount());
				inv.removeItem(i, itemsToConsume);
				currentItemDamage -= healAmt * itemsToConsume;
				itemstack.setDamageValue(currentItemDamage);

				//Reset warning status, if damage is low enough
				if (currentItemDamage < maxDamage * 0.8)
				{
					NBTMessage(itemstack, ply, NBTMessage.OK);

					//Break, since lowering the damage isn't possible anymore
					if (currentItemDamage < 1)
					{
						break;
					}
				}
			}
		}
	}

	private void NBTMessage(ItemStack stack, Player player, NBTMessage nbta)
	{
		final CommandSourceStack cPlayer = player.createCommandSourceStack();
		CompoundTag tc = NBTInit(stack);
		byte warn = tc.getByte("warn");

		switch (nbta)
		{
			case OK ->
			{
				if (warn > 0)
				{
					tc.putByte("warn", (byte) 0);
				}
			}
			case WARN ->
			{
				if (warn < 1)
				{
					CommandUtils.sendIm(cPlayer, "item.trienowtweaks.auto_food.warning");
					tc.putByte("warn", (byte) 1);
				}
			}
			case DEATH ->
			{
				if (warn < 2)
				{
					CommandUtils.sendIm(cPlayer, "item.trienowtweaks.auto_food.danger");
					tc.putByte("warn", (byte) 2);
				}
			}
		}
	}

	private CompoundTag NBTInit(ItemStack stack)
	{
		boolean update = false;
		CompoundTag tc = stack.getOrCreateTag();

		if (!tc.contains("warn", CompoundTag.TAG_BYTE))
		{
			tc.putByte("warn", (byte) 0);
		}

		if (!tc.contains("init", CompoundTag.TAG_BYTE))
		{
			tc.putByte("init", (byte) 0);
		}

		if (update)
		{
			stack.setTag(tc);
		}

		return tc;
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
	{
		int max = pStack.getMaxDamage();
		int uses = max - pStack.getDamageValue();
		ChatFormatting tf;

		if (max * 0.75 < uses)
			tf = ChatFormatting.DARK_GREEN;
		else if (max * 0.5 < uses)
			tf = ChatFormatting.GREEN;
		else if (max * 0.25 < uses)
			tf = ChatFormatting.YELLOW;
		else if (max * 0.125 < uses)
			tf = ChatFormatting.GOLD;
		else if (max * 0.06 < uses)
			tf = ChatFormatting.RED;
		else
			tf = ChatFormatting.DARK_RED;

		pTooltipComponents.add(Component.translatable("item.trienowtweaks.auto_food.tooltip0"));
		pTooltipComponents.add(Component.translatable("item.trienowtweaks.auto_food.tooltip1", tf, uses));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
	{
		ItemStack heldItem = pPlayer.getItemInHand(pUsedHand);
		boolean success = CompatManager.curiosProxy.trySetStackInSlot(ICuriosProxy.ID_HELMET, pPlayer, heldItem);
		if (success)
		{
			return InteractionResultHolder.success(heldItem);
		}
		else
		{
			return InteractionResultHolder.fail(heldItem);
		}
	}

	private enum NBTMessage
	{
		OK,
		WARN,
		DEATH
	}
}
