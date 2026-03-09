package de.trienow.trienowtweaks.entity.layer;

public enum LayerTtType
{
	NONE(0),
	TOAST(1),
	KNIGHT(2),
	AMOGUS(3);

	private final int id;

	LayerTtType(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	@SuppressWarnings("EnhancedSwitchMigration")
	public static LayerTtType fromId(int id)
	{
		switch (id)
		{
			case 1:
				return TOAST;
			case 2:
				return KNIGHT;
			case 3:
				return AMOGUS;
			case 0:
			default:
				return NONE;
		}
	}
}
