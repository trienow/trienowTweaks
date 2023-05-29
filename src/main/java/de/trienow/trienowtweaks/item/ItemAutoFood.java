package de.trienow.trienowtweaks.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemAutoFood extends BaseItem implements IBauble
{
	// ACTIVATION: 0 = Inactive 1 = Active
	// WARN: 0 = OK 1 = SEMI 2 = ZERO

	byte codeBoom = -100;
	byte iter = -100;

	public ItemAutoFood()
	{
		super("auto_food");
		maxStackSize = 1;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player)
	{
		if (player.world.isRemote || !(player instanceof EntityPlayer))
			return;

		EntityPlayer ply = (EntityPlayer) player;

		if (codeBoom > 0)
		{
			if (iter > -90)
			{
				searchForFood(itemstack, ply);
			}
			else
				iter++;

			int currentItemDamage = itemstack.getItemDamage();
			int maxDamage = getMaxDamage(itemstack);

			if (currentItemDamage + 50 > maxDamage)
				NBTMessage(itemstack, player, NBTMessage.WARN);

			if (currentItemDamage < maxDamage)
			{
				if (ply.getFoodStats().needFood())
				{
					FoodStats food = ply.getFoodStats();
					int hunger = 20 - food.getFoodLevel();

					int decrAmt = Math.min(hunger, (maxDamage - currentItemDamage));

					itemstack.damageItem(decrAmt, player);
					// food.setFoodLevel(food.getFoodLevel() + decrAmt);
					food.addStats(decrAmt, 1f);
				}
			}
			else
			{
				NBTMessage(itemstack, player, NBTMessage.DEATH);
			}

			codeBoom = 1;
		}
		else
			codeBoom++;
	}

	protected void searchForFood(ItemStack itemstack, EntityPlayer ply)
	{
		//The smaller the damage, the smaller the value. The more food stored, the smaller the value!
		int currentItemDamage = itemstack.getItemDamage();

		if (currentItemDamage < 1)
			return;

		int maxDamage = getMaxDamage(itemstack);
		InventoryPlayer inv = ply.inventory;

		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			inv.getStackInSlot(i);
			if (inv.getStackInSlot(i).getItem() instanceof ItemFood)
			{
				ItemStack foodStack = inv.getStackInSlot(i);
				ItemFood food = (ItemFood) foodStack.getItem();
				int healAmt = food.getHealAmount(foodStack);

				if (healAmt < 1 || healAmt > currentItemDamage)
					continue;

				int itemsToFill = currentItemDamage / healAmt;
				int itemsToConsume = Math.min(itemsToFill, foodStack.getCount());
				inv.decrStackSize(i, itemsToConsume);
				currentItemDamage = itemstack.getItemDamage() - (healAmt * itemsToConsume);
				itemstack.setItemDamage(currentItemDamage);

				//Reset warning status, if damage is low enough
				if (currentItemDamage < maxDamage * 0.9)
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

	protected void NBTMessage(ItemStack stack, EntityLivingBase player, NBTMessage nbta)
	{
		NBTTagCompound tc = NBTInit(stack);
		byte warn = tc.getByte("warn");

		switch (nbta)
		{
			case OK:
				if (warn > 0)
				{
					tc.setByte("warn", (byte) 0);
					stack.setTagCompound(tc);
				}
				break;
			case WARN:
				if (warn < 1)
				{
					player.sendMessage(new TextComponentString(TextFormatting.GOLD + "You might slowly be getting hungry..."));
					tc.setByte("warn", (byte) 1);
					stack.setTagCompound(tc);
				}
				break;
			case DEATH:
				if (warn < 2)
				{
					player.sendMessage(new TextComponentString(TextFormatting.RED + "You have no more food stored!"));
					tc.setByte("warn", (byte) 2);
					stack.setTagCompound(tc);
				}
				break;
		}
	}

	protected boolean NBTActive(ItemStack stack, NBTActive nbta)
	{
		NBTTagCompound tc = NBTInit(stack);
		if (nbta == NBTActive.ACTIVATE)
		{
			tc.setByte("active", (byte) 1);
			stack.setTagCompound(tc);
			return true;
		}

		if (nbta == NBTActive.DEACTIVATE)
		{
			tc.setByte("active", (byte) 0);
			stack.setTagCompound(tc);
			return false;
		}

		return tc.getByte("active") == 1;
	}

	protected NBTTagCompound NBTInit(ItemStack stack)
	{
		NBTTagCompound tc = stack.getTagCompound();
		if (tc == null || !tc.hasKey("warn") || !tc.hasKey("active"))
		{
			tc = new NBTTagCompound();
			tc.setByte("warn", (byte) 0);
			tc.setByte("active", (byte) 0);
			stack.setTagCompound(tc);
		}

		return tc;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		int max = stack.getMaxDamage();
		int uses = max - stack.getItemDamage();
		TextFormatting tf = TextFormatting.OBFUSCATED;

		if (max * 0.75 < uses)
			tf = TextFormatting.DARK_GREEN;
		else if (max * 0.5 < uses)
			tf = TextFormatting.GREEN;
		else if (max * 0.25 < uses)
			tf = TextFormatting.YELLOW;
		else if (max * 0.125 < uses)
			tf = TextFormatting.GOLD;
		else if (max * 0.06 < uses)
			tf = TextFormatting.RED;
		else if (max * 0.03 < uses)
			tf = TextFormatting.DARK_RED;

		tooltip.add(TextFormatting.ITALIC + "" + TextFormatting.RED + "Yummy... Watermelon!");
		tooltip.add(TextFormatting.GOLD + "Is only active, when in the head bauble slot.");
		tooltip.add("Can feed you " + tf + TextFormatting.BOLD + uses + TextFormatting.RESET + TextFormatting.GRAY + " more times.");
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		return (((double) stack.getItemDamage()) / ((double) getMaxDamage(stack)));
	}

	@Override
	public int getMaxDamage(ItemStack stack)
	{
		return 1000;
	}

	@Override
	public boolean isDamageable()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean showDurabilityBar(ItemStack stack)
	{
		return stack.getItemDamage() != 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return NBTActive(stack, NBTActive.READ);
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0)
	{
		return BaubleType.HEAD;
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player)
	{
		NBTActive(itemstack, NBTActive.ACTIVATE);
		codeBoom = 10;
		onWornTick(itemstack, player);
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
	{
		NBTActive(itemstack, NBTActive.DEACTIVATE);
	}

	protected enum NBTMessage
	{
		OK,
		WARN,
		DEATH
	}

	protected enum NBTActive
	{
		READ,
		ACTIVATE,
		DEACTIVATE
	}
}
