package de.trienow.trienowtweaks.capabilities;

import de.trienow.trienowtweaks.entity.layer.LayerTtRenderMode;
import de.trienow.trienowtweaks.utils.ICloneable;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

/**
 * @author (c) trienow 2022
 */
public interface IPlayerCapability extends ICloneable<IPlayerCapability>
{
	Capability<IPlayerCapability> PLAYER_CAP = CapabilityManager.get(new CapabilityToken<>()
	{
	});

	/**
	 * @return Determines how layer tt is rendered
	 */
	LayerTtRenderMode getLayerTtRenderMode();

	/**
	 * @param renderMode Determines how layer tt is rendered
	 */
	void setLayerTtRenderMode(LayerTtRenderMode renderMode);

	Tag serializeNBT();

	void deserializeNBT(Tag nbt);
}
