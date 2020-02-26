package logicLayer;

public class Tile 
{
	private Unit unit;
	private Position position;
	
	public Tile(int xCor, int yCor) {
		Position position = new Position(xCor, yCor);
		this.position = position;
		this.unit = null;
	}
	
	public Tile(Unit unit, Position position) {
		this.unit = new Unit(unit);
		this.position = position;
	}
	
	public Tile(Unit unit, int xCor, int yCor) {
		Position position = new Position(xCor, yCor);
		this.unit = new Unit(unit);
		this.position = position;
	}
	
	public boolean hasUnit()
	{
		if (unit==null){
			return false;
		}
		else {
			return true;	
		}
	}
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
}
