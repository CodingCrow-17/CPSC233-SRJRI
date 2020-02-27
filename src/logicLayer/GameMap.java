package logicLayer;

import java.util.List;

public interface GameMap {
	void incrementTurn();
	Tile[][] getTiles();
	List<Owner> getOwners();
	int getTurnCount();
	Tile getTileAtCoordinates(int xCoordinate, int yCoordinate);
	Tile getTileAtPosition(Position position);
	
}
