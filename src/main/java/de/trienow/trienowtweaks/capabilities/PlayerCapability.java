package de.trienow.trienowtweaks.capabilities;

import de.trienow.trienowtweaks.entity.layer.LayerTtRenderMode;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

/**
 * @author (c) trienow 2022
 */
public class PlayerCapability implements IPlayerCapability
{
	private LayerTtRenderMode layerTtRenderMode = LayerTtRenderMode.SHOW;

	/**
	 * @return Determines how layer tt is rendered
	 */
	@Override
	public LayerTtRenderMode getLayerTtRenderMode()
	{
		return layerTtRenderMode;
	}

	/**
	 * @param renderMode Determines how layer tt is rendered
	 */
	@Override
	public void setLayerTtRenderMode(LayerTtRenderMode renderMode)
	{
		layerTtRenderMode = renderMode;
	}

	@Override
	public Tag serializeNBT()
	{
		CompoundTag main = new CompoundTag();

		main.putInt("layerTtRenderMode", layerTtRenderMode.getId());

		return main;
	}

	@Override
	public void deserializeNBT(Tag nbt)
	{
		if (nbt instanceof CompoundTag main)
		{
			layerTtRenderMode = LayerTtRenderMode.fromId(main.getInt("layerTtRenderMode"));
		}
	}

	/**
	 * Clones this object's properties into the destination object
	 *
	 * @param dest The destination object
	 */
	@Override
	public void clone(IPlayerCapability dest)
	{
		dest.setLayerTtRenderMode(layerTtRenderMode);
	}
}
