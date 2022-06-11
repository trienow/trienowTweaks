package de.trienow.trienowtweaks.entity.layer;

/**
 * @author (c) trienow 2022
 */
public enum LayerTtRenderMode
{
	SHOW(0),
	PREFER_ARMOR(1),
	HIDE(2);

	private final int id;

	LayerTtRenderMode(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	@SuppressWarnings("EnhancedSwitchMigration")
	public static LayerTtRenderMode fromId(int id)
	{
		switch (id)
		{
			case 1:
				return PREFER_ARMOR;
			case 2:
				return HIDE;
			case 0:
			default:
				return SHOW;
		}
	}
}
