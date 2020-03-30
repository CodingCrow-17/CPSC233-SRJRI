package logicLayer;

public class Tile 
{
	private Unit unit;
	private Position position;
	private TileType type;
	
	public Tile(int xCor, int yCor, TileType type) {
		Position position = new Position(xCor, yCor);
		this.position = position;
		this.unit = null;
		this.type = type;
	}
	
	public TileType getType() {
		return type;
	}
	
	public boolean hasUnit()
	{
		return (unit != null);
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
		unit = newUnit;
	}
}
