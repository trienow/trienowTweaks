package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.tiles.TEFluidHolder;
import de.trienow.trienowtweaks.tiles.TEItemDetector;
import de.trienow.trienowtweaks.tiles.TELightEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author (c) trienow 2018 - 2020
 */
public class AtomTileEntities
{
	public static final Map<String, Class<? extends TileEntity>> tileEntityMap = new TreeMap<String, Class<? extends TileEntity>>()
	{
		{
			put("base_tile_entity", TELightEntity.class);
			put("base_item_detector_te", TEItemDetector.class);
			put("fluid_holder", TEFluidHolder.class);
		}
	};

	public static void registerTileEntities()
	{
		for (String tileId : tileEntityMap.keySet())
		{
			GameRegistry.registerTileEntity(tileEntityMap.get(tileId), TrienowTweaks.MODID + ":" + tileId);
		}
	}
}
