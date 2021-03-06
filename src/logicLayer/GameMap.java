package logicLayer;

import java.util.List;

public class GameMap
{
	private Tile[][] tiles;
	private int turnCount;
	private List<Owner> owners;

	public GameMap(Tile[][] tiles) {
		this.tiles = tiles;
		turnCount = 0;
	}

	public GameMap(Tile[][] tiles, List<Owner> players){
		this.tiles = tiles;
		this.owners = players;
		turnCount = 0;
	}
	
	public List<Owner> getOwners(){
		return owners;
	}
	
	public int getTurnCount() {
		return turnCount;
	}
	
	public Tile getTileAtCoordinates(int xCoordinate, int yCoordinate) {
		Position position = new Position(xCoordinate, yCoordinate);
		return getTileAtPosition(position);
	}
	
	public Tile getTileAtPosition(Position position){
		int xCoordinate = position.getXPosition();
		int yCoordinate = position.getYPosition();
		if (yCoordinate >= 0 && yCoordinate < tiles.length) {
			if (xCoordinate >= 0 && xCoordinate < tiles[0].length) {
				Tile[] row = tiles[yCoordinate];
				
				return row[xCoordinate];
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	
	public void incrementTurn(){
		turnCount++;
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}
}
