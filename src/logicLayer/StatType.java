package logicLayer;

public enum StatType
{
	HP,ATTACK,DEFENSE,SPEED,DEXTERITY,MOVEMENT;
	
	public static String toString(StatType stat)
	{
		switch (stat)
		{
			case HP:
				return "hp";
			case ATTACK:
				return "attack";
			case DEFENSE:
				return "defense";
			case SPEED:
				return "speed";
			case DEXTERITY:
				return "dexterity";
			case MOVEMENT:
				return "movement";
			default:
				return "";
		}
	}
	
	public static String toShortenedString(StatType stat)
	{
		switch (stat)
		{
			case HP:
				return "hp";
			case ATTACK:
				return "att";
			case DEFENSE:
				return "def";
			case SPEED:
				return "spd";
			case DEXTERITY:
				return "dex";
			case MOVEMENT:
				return "mov";
			default:
				return "";
		}
	}
}
