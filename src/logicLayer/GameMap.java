package logicLayer;

public class GameMap
{
	private Tile[][] tiles;
	private int turnCount;
	
	public GameMap(Tile[][] tile)
	{
		tiles = tile;
		turnCount=1;
	}
	
	public void increaseTurn()
	{
		turnCount++;
	}
	
	public Tile[][] getTiles()
	{
		return tiles;
	}
}
