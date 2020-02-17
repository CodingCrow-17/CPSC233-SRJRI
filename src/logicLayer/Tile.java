package logicLayer;

public class Tile 
{
	private Unit unit;
	private Position position;
	
	public Unit getUnit()
	{
		return unit;
	}
	public Position getPos()
	{
		return position;
	}
	public void setUnit(Unit newUnit)
	{
		unit=newUnit;
	}
	public void setPosition(Position newPos)
	{
		position = newPos;
	}
}
