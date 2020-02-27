package logicLayer;

import java.util.List;

public class GameMap
{
	private Tile[][] tiles;
	private int turnCount;
	private List<Owner> owners;

	
<<<<<<< HEAD
	public GameMap(Tile[][] tiles)
=======
	public GameMap(Tile[][] tiles, List<Owner> players)
>>>>>>> refs/heads/master
	{
		this.tiles = tiles;
<<<<<<< HEAD
		turnCount=1;
=======
		this.owners = players;
		turnCount=0;
>>>>>>> refs/heads/master
	}
	
	public List<Owner> getOwners(){
		return owners;
	}
	
	public Tile getTileAtCoordinates(int xCoordinate, int yCoordinate) {
		Position position = new Position(xCoordinate, yCoordinate);
		return getTileAtPosition(position);
	}
	
	public Tile getTileAtPosition(Position position)
	{
		int xCoordinate = position.getXPosition();
		int yCoordinate = position.getYPosition();
		if (xCoordinate >= 0 || xCoordinate < tiles.length
				&& yCoordinate >= 0 || yCoordinate < tiles.length) {
			return tiles[position.getYPosition()][position.getXPosition()];
		}
		else {
			return null;
		}
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
