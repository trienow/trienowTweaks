package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.blocks.*;
import de.trienow.trienowtweaks.blocks.flavors.FlavorRailroadTruss;
import de.trienow.trienowtweaks.blocks.flavors.FlavorStreetlamp;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author trienow 2019 - 2023
 */
public class AtomBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TrienowTweaks.MODID);

    public static final RegistryObject<Block> GENERIC_LIGHT = BLOCKS.register("generic_light", BlockGenericLight::new);
    public static final RegistryObject<Block> INVISIBLE_WALL = BLOCKS.register("invisible_wall", BlockInvisibleWall::new);

    public static final RegistryObject<Block> RAILROAD_TRUSS_WOODEN = register(FlavorRailroadTruss.WOODEN);
    public static final RegistryObject<Block> RAILROAD_TRUSS_BLACK = register(FlavorRailroadTruss.BLACK);
    public static final RegistryObject<Block> RAILROAD_TRUSS_BRIGHT = register(FlavorRailroadTruss.BRIGHT);
    public static final RegistryObject<Block> RAILROAD_TRUSS_PURPLE = register(FlavorRailroadTruss.PURPLE);

    public static final RegistryObject<Block> STREETLAMP_FLESH = BLOCKS.register("streetlamp_" + FlavorStreetlamp.FLESH, BlockStreetlamp::new);
    public static final RegistryObject<Block> STREETLAMP_FIRE = BLOCKS.register("streetlamp_" + FlavorStreetlamp.FIRE, BlockStreetlamp::new);
    public static final RegistryObject<Block> STREETLAMP_GLOWSTONE = BLOCKS.register("streetlamp_" + FlavorStreetlamp.GLOWSTONE, BlockStreetlamp::new);

    public static final RegistryObject<Block> FAKE_FIRE = BLOCKS.register("fake_fire", BlockFakeFire::new);
    public static final RegistryObject<Block> MINECART_KILLER = BLOCKS.register("minecart_killer", BlockMinecartKiller::new);
    public static final RegistryObject<Block> COMPACT_CRAFTER = BLOCKS.register("compact_crafter", BlockCompactCrafter::new);
    public static final RegistryObject<Block> ITEM_DETECTOR = BLOCKS.register("item_detector", BlockItemDetector::new);
    public static final RegistryObject<Block> ENTITY_PROHIBITATOR = BLOCKS.register("entity_prohibitator", BlockEntityProhibitator::new);
    public static final RegistryObject<Block> TORCH_SQUARED = BLOCKS.register("torch_squared", BlockTorchSquared::new);

    public static void init(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }

    private static RegistryObject<Block> register(FlavorRailroadTruss flavor) {
        return BLOCKS.register("railroad_truss_" + flavor, () -> new BlockRailroadTruss(flavor));
    }
}
