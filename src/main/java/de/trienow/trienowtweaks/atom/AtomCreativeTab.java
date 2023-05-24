package de.trienow.trienowtweaks.atom;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static de.trienow.trienowtweaks.main.TrienowTweaks.MODID;

/**
 * @author (c) trienow 2023
 */
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtomCreativeTab
{
	@SubscribeEvent
	public static void registerCreativeModeTab(CreativeModeTabEvent.Register evt)
	{
		//Thanks to ChampionAsh5357 for the 1.19.3 to 1.19.4 migration tips on this.
		//https://gist.github.com/ChampionAsh5357/163a75e87599d19ee6b4b879821953e8
		evt.registerCreativeModeTab(new ResourceLocation(MODID, "trienowtab"), builder -> {
			builder.title(Component.translatable("item_group." + MODID + ".trienowtab"));
			builder.icon(() -> new ItemStack(AtomItems.WE_WAND.get()));
			builder.displayItems((params, output) -> {
				//ItemBlocks
				output.accept(AtomItemBlocks.GENERIC_LIGHT.get());
				output.accept(AtomItemBlocks.INVISIBLE_WALL.get());
				output.accept(AtomItemBlocks.RAILROAD_TRUSS_WOODEN.get());
				output.accept(AtomItemBlocks.RAILROAD_TRUSS_BLACK.get());
				output.accept(AtomItemBlocks.RAILROAD_TRUSS_BRIGHT.get());
				output.accept(AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get());
				output.accept(AtomItemBlocks.STREETLAMP_FLESH.get());
				output.accept(AtomItemBlocks.STREETLAMP_FIRE.get());
				output.accept(AtomItemBlocks.STREETLAMP_GLOWSTONE.get());
				output.accept(AtomItemBlocks.FAKE_FIRE.get());
				output.accept(AtomItemBlocks.MINECART_KILLER.get());
				output.accept(AtomItemBlocks.COMPACT_CRAFTER.get());
				output.accept(AtomItemBlocks.ITEM_DETECTOR.get());
				output.accept(AtomItemBlocks.ENTITY_PROHIBITATOR.get());
				output.accept(AtomItemBlocks.TORCH_SOLAMNIA.get());

				//Items
				output.accept(AtomItems.WE_WAND.get());
				output.accept(AtomItems.AUTO_LIGHT.get());
				output.accept(AtomItems.AUTO_FOOD.get());
				output.accept(AtomItems.DRTOAST_HEAD.get());
				output.accept(AtomItems.KNIGHT_HEAD.get());
				output.accept(AtomItems.KNIGHT_CHEST.get());
				output.accept(AtomItems.KNIGHT_LEGS.get());
				output.accept(AtomItems.KNIGHT_FEET.get());
			});
		});
	}
}
