package de.trienow.trienowtweaks.atom;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static de.trienow.trienowtweaks.main.TrienowTweaks.MODID;

/**
 * @author trienow 2023
 */
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AtomCreativeTab
{
	private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
	public static RegistryObject<CreativeModeTab> TRIENOWTAB = registerTrienowtab();

	private static RegistryObject<CreativeModeTab> registerTrienowtab()
	{
		return CREATIVE_MODE_TABS.register("trienowtab", () -> {
			//noinspection CodeBlock2Expr
			return CreativeModeTab.builder()
					.title(Component.translatable("item_group." + MODID + ".trienowtab"))
					.icon(() -> new ItemStack(AtomItemBlocks.STREETLAMP_FIRE.get()))
					.build();
		});
	}

	public static void init(IEventBus modEventBus)
	{
		CREATIVE_MODE_TABS.register(modEventBus);
	}

	@SubscribeEvent
	public static void registerCreativeModeTabContents(BuildCreativeModeTabContentsEvent evt)
	{
		//Thanks to ChampionAsh5357 for the 1.19.4 to 1.20.1 migration tips on this. https://gist.github.com/ChampionAsh5357/cf818acc53ffea6f4387fe28c2977d56
		if (evt.getTab() == TRIENOWTAB.get())
		{
			//ItemBlocks
			evt.accept(AtomItemBlocks.GENERIC_LIGHT.get());
			evt.accept(AtomItemBlocks.INVISIBLE_WALL.get());
			evt.accept(AtomItemBlocks.RAILROAD_TRUSS_WOODEN.get());
			evt.accept(AtomItemBlocks.RAILROAD_TRUSS_BLACK.get());
			evt.accept(AtomItemBlocks.RAILROAD_TRUSS_BRIGHT.get());
			evt.accept(AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get());
			evt.accept(AtomItemBlocks.STREETLAMP_FLESH.get());
			evt.accept(AtomItemBlocks.STREETLAMP_FIRE.get());
			evt.accept(AtomItemBlocks.STREETLAMP_GLOWSTONE.get());
			evt.accept(AtomItemBlocks.FAKE_FIRE.get());
			evt.accept(AtomItemBlocks.MINECART_KILLER.get());
			evt.accept(AtomItemBlocks.COMPACT_CRAFTER.get());
			evt.accept(AtomItemBlocks.ITEM_DETECTOR.get());
			evt.accept(AtomItemBlocks.ENTITY_PROHIBITATOR.get());
			evt.accept(AtomItemBlocks.TORCH_SOLAMNIA.get());

			//Items
			evt.accept(AtomItems.WE_WAND.get());
			evt.accept(AtomItems.AUTO_LIGHT.get());
			evt.accept(AtomItems.AUTO_FOOD.get());
			evt.accept(AtomItems.DRTOAST_HEAD.get());
			evt.accept(AtomItems.KNIGHT_HEAD.get());
			evt.accept(AtomItems.KNIGHT_CHEST.get());
			evt.accept(AtomItems.KNIGHT_LEGS.get());
			evt.accept(AtomItems.KNIGHT_FEET.get());
		}
	}
}
