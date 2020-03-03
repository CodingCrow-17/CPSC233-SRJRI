package logicLayer;

public enum StatType
{
	HP,ATTACK,DEFENSE,SPEED,MOVEMENT;
	
	public String toString(StatType stat)
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
			case MOVEMENT:
				return "movement";
			default:
				return "";
		}
	}
	
}
