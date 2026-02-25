package de.trienow.trienowtweaks.item;

import de.trienow.trienowtweaks.atom.AtomDataComponents;
import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.compat.CompatManager;
import de.trienow.trienowtweaks.compat.curios.ICuriosProxy;
import de.trienow.trienowtweaks.datacomponents.AutoFoodData;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @author trienow 2017 - 2023
 */
public class ItemAutoFood extends Item
{
	byte checkLimiter = -20;

	public ItemAutoFood(ResourceLocation registryName)
	{
		super(new Properties()
				.stacksTo(1)
				.durability(500)
				.component(AtomDataComponents.AUTO_FOOD.get(), new AutoFoodData((byte) 0, (byte) 0)).setId(ResourceKey.create(Registries.ITEM, registryName))
		);
	}

	@Override public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot)
	{
		if (checkLimiter > 0)
		{
			if (!(entity instanceof ServerPlayer player))
			{
				return;
			}

			int maxDamage = getMaxDamage(stack);

			AutoFoodData autoFoodData = stack.get(AtomDataComponents.AUTO_FOOD);

			if (autoFoodData.init() == (byte) 0)
			{
				stack.setDamageValue(maxDamage);
				AutoFoodData updated = new AutoFoodData((byte) 1, (byte) 2);
				stack.set(AtomDataComponents.AUTO_FOOD, updated);
			}

			searchForFood(stack, player);

			int currentItemDamage = stack.getDamageValue();
			if (currentItemDamage > maxDamage * 0.9)
			{
				nbtMessage(stack, player, NBTMessage.WARN);
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
				nbtMessage(stack, player, NBTMessage.DEATH);
			}

			checkLimiter = -20;
		}
		else
		{
			checkLimiter++;
		}
	}

	protected void searchForFood(ItemStack itemstack, ServerPlayer ply)
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
			FoodProperties food = foodStack.get(DataComponents.FOOD);
			if (food != null)
			{
				int healAmt = food != null ? food.nutrition() : 0;
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
					nbtMessage(itemstack, ply, NBTMessage.OK);

					//Break, since lowering the damage isn't possible anymore
					if (currentItemDamage < 1)
					{
						break;
					}
				}
			}
		}
	}

	private void nbtMessage(ItemStack stack, ServerPlayer player, NBTMessage nbta)
	{
		AutoFoodData autoFoodData = stack.get(AtomDataComponents.AUTO_FOOD);

		switch (nbta)
		{
			case OK ->
			{
				if (autoFoodData.warn() > 0)
				{
					AutoFoodData updated = new AutoFoodData(autoFoodData.init(), (byte) 0);
					stack.set(AtomDataComponents.AUTO_FOOD, updated);
				}
			}
			case WARN ->
			{
				if (autoFoodData.warn() < 1)
				{
					CommandUtils.sendIm(player, "item.trienowtweaks.auto_food.warning");
					AutoFoodData updated = new AutoFoodData(autoFoodData.init(), (byte) 1);
					stack.set(AtomDataComponents.AUTO_FOOD, updated);
				}
			}
			case DEATH ->
			{
				if (autoFoodData.warn() < 2)
				{
					CommandUtils.sendIm(player, "item.trienowtweaks.auto_food.danger");
					AutoFoodData updated = new AutoFoodData(autoFoodData.init(), (byte) 2);
					stack.set(AtomDataComponents.AUTO_FOOD, updated);
				}
			}
		}
	}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		int max = stack.getMaxDamage();
		int uses = max - stack.getDamageValue();
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

		tooltipAdder.accept(Component.translatable("item.trienowtweaks.auto_food.tooltip0"));
		tooltipAdder.accept(Component.translatable("item.trienowtweaks.auto_food.tooltip1", tf.toString(), uses));
	}

	@Override public InteractionResult use(Level level, Player player, InteractionHand hand)
	{
		ItemStack heldItem = player.getItemInHand(hand);
		boolean success = CompatManager.curiosProxy.trySetStackInSlot(ICuriosProxy.ID_HELMET, player, heldItem);
		if (success)
		{
			return InteractionResult.SUCCESS;
		}
		else
		{
			return InteractionResult.FAIL;
		}
	}

	private enum NBTMessage
	{
		OK,
		WARN,
		DEATH
	}
}
