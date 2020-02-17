package logicLayer;

public class Tile 
{
	private Unit unit;
	private Pos position;
	
	public Unit getUnit()
	{
		return unit;
	}
	public Pos getPos()
	{
		return position;
	}
	public void setUnit(Unit newUnit)
	{
		unit=newUnit;
	}
	public void setPos(Unit newPos)
	{
		position=newPos;
	}
}
