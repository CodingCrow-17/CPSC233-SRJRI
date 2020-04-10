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
	
	public Tile(Unit unit, Position position, TileType type) {
		this.unit = new Unit(unit);
		this.position = position;
		this.type = type;
	}
	
	public Tile(Unit unit, int xCor, int yCor) {
		Position position = new Position(xCor, yCor);
		this.unit = new Unit(unit);
		this.position = position;
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
		unit=newUnit;
	}

	private TileType getTileType() {
		type = TileType.toString();
		return type;
	}
}
