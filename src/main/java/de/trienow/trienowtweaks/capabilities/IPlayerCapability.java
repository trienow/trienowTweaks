package de.trienow.trienowtweaks.capabilities;

import de.trienow.trienowtweaks.entity.layer.LayerTtRenderMode;
import de.trienow.trienowtweaks.utils.ICloneable;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.capabilities.CapabilityManager;
import net.neoforged.neoforge.common.capabilities.CapabilityToken;
import org.jetbrains.annotations.NotNull;

/**
 * @author (c) trienow
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

	@NotNull
	Tag serializeNBT();

	void deserializeNBT(Tag nbt);
}
