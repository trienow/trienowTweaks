package de.trienow.trienowtweaks.compat.curios;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class CuriosProxy implements ICuriosProxy
{
	@Override
	public boolean isModLoaded()
	{
		return true;
	}

	@Override
	public void onEnqueueIMC(final InterModEnqueueEvent evt)
	{
		TrienowTweaks.LOG.info("Registering with curios!");

		final Supplier<SlotTypeMessage> SLOT_HELMET = () -> new SlotTypeMessage.Builder(ID_HELMET)
				.size(1)
				.icon(new ResourceLocation(CURIOS, "slot/helmet"))
				.build();

		final Supplier<SlotTypeMessage> SLOT_NECKLACE = () -> new SlotTypeMessage.Builder(ID_NECKLACE)
				.size(1)
				.icon(new ResourceLocation(CURIOS, "slot/necklace"))
				.build();

		InterModComms.sendTo(CURIOS, SlotTypeMessage.REGISTER_TYPE, SLOT_HELMET);
		InterModComms.sendTo(CURIOS, SlotTypeMessage.REGISTER_TYPE, SLOT_NECKLACE);
	}

	@Override
	public boolean trySetStackInSlot(String identifier, LivingEntity wearer, ItemStack stack)
	{
		if (!wearer.level.isClientSide() && wearer instanceof Player player)
		{
			final int inCount = stack.getCount();
			final AtomicBoolean success = new AtomicBoolean(false);
			final ICuriosHelper curiosHelper = CuriosApi.getCuriosHelper();

			curiosHelper.getCuriosHandler(player).ifPresent(itemHandler -> itemHandler.getStacksHandler(identifier).ifPresent(stackHandler -> {
				IDynamicStackHandler stacks = stackHandler.getStacks();
				for (int i = 0; i < stackHandler.getSlots(); i++)
				{
					ItemStack outStack = stacks.insertItem(i, stack.copy(), false);
					if (outStack.getCount() < inCount)
					{
						//Set the referenced stack to the amount of the remaining stack
						stack.setCount(outStack.getCount());
						success.set(true);
						break;
					}
				}
			}));
			return success.get();
		}

		return false;
	}
}
