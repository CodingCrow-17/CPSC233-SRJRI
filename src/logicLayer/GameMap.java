package logicLayer;

public class GameMap
{
	private Tile[][] tiles;
	private int turnCount;
	
	public GameMap(Tile[][] tile)
	{
		tiles = tiles;
		turnCount=1;
	}
	
	public Tile getTileAt(Position position)
	{
		return tiles[position.getX()][position.getY()];
	}
	
	public void incrementTurn()
	{
		turnCount++;
	}
	
	public Tile[][] getTiles()
	{
		return tiles;
	}
}
