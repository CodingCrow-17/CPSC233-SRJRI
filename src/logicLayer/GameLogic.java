package logicLayer;

import java.util.ArrayList;
import java.util.List;

public class GameLogic{

	private GameMap gameMap;
	private Owner currentOwner;
	private int currentIndex = -1;
	
	public GameLogic(GameMap gameMap) {
		this.gameMap = gameMap;
		switchOwner();
	}
	
	public Owner getCurrentOwner() {
		return currentOwner;
	}
	
	public void moveTo(Position startPosition, Position endPosition) {
		Tile startTile = gameMap.getTileAtPosition(startPosition);
		Tile finalTile = gameMap.getTileAtPosition(endPosition);
		if (startTile != null && finalTile != null) {
			if (checkIfUnitIsOnTile(startTile) == true) {
				if(checkIfUnitIsOnTile(finalTile) == false) {
					Unit unit = startTile.getUnit();
					if (unit.getHasMoved() == false) {
						if (unit.getOwner().equals(currentOwner)){
							List<Tile> validTilesToMoveTo = calculateValidTileToMoveTo(unit);
							if (validTilesToMoveTo.contains(finalTile)) {
								finalTile.setUnit(unit);
								startTile.setUnit(null);
								unit.moveTo(finalTile);
							}
							else {
								System.out.println("Out of move range!");
							}
						}
						else {
							System.out.println("You don't own that unit!");
						}
					}
					else {
						System.out.println("That unit has already moved!");
					}
				}
				else {
					System.out.println("You can't move to a tile that already has a unit!");
				}
			}
			else {
				System.out.println("You don't have a unit there!");
			}	
		}
		else {
			System.out.println("Your position is out of bounds!");
		}
	}

	public void performCombat(Position unitPosition, Position enemyPosition) {
		//TODO
	}

	public void removeUnit() {
		//TODO
	}
	
	
	private boolean checkIfUnitIsOnTile(Tile tile) {
		return tile.hasUnit();
	}
	
	private List<Tile> calculateValidTileToMoveTo(Unit unit) {
		List<Tile> tileRange = new ArrayList<Tile>();
		Direction direction = Direction.NONE;
		Tile startTile = unit.getTile();	
		int movStamina = unit.getStats().getMov().getCurrentValue()+1;
		tileRange.add(startTile);
		calculateMovRange(tileRange, startTile, movStamina, direction);
		return tileRange;
	}
	
	private void calculateMovRange(List<Tile> tileRange, Tile startTile, int movStamina, Direction direction) {
		if (movStamina == 0) { // base case
			return;
		}
		else {
			movStamina--;
			if (direction.equals(Direction.NONE)) {
				calculateMovRange(tileRange, startTile, movStamina, Direction.UP);
				calculateMovRange(tileRange, startTile, movStamina, Direction.RIGHT);
				calculateMovRange(tileRange, startTile, movStamina, Direction.DOWN);
				calculateMovRange(tileRange, startTile, movStamina, Direction.LEFT);
			}
			if (direction.equals(Direction.RIGHT)) {
				Tile newTile= gameMap.getTileAtCoordinates(startTile.getPos().getYPosition()+1,
						startTile.getPos().getXPosition());
				if (newTile != null) {
					tileRange.add(newTile);
					calculateMovRange(tileRange, newTile, movStamina, Direction.UP);
					calculateMovRange(tileRange, newTile, movStamina, Direction.RIGHT);
					calculateMovRange(tileRange, newTile, movStamina, Direction.DOWN);	
				}
			}
			if (direction.equals(Direction.UP)) {
				Tile newTile= gameMap.getTileAtCoordinates(startTile.getPos().getYPosition(),
						startTile.getPos().getXPosition()-1);
				if (newTile != null) {
					tileRange.add(newTile);
					calculateMovRange(tileRange, newTile, movStamina, Direction.RIGHT);
					calculateMovRange(tileRange, newTile, movStamina, Direction.LEFT);
					calculateMovRange(tileRange, newTile, movStamina, Direction.UP);	
				}
			}
			if (direction.equals(Direction.LEFT)) {
				Tile newTile= gameMap.getTileAtCoordinates(startTile.getPos().getYPosition()-1,
						startTile.getPos().getXPosition());
				if (newTile != null) {
					tileRange.add(newTile);
					calculateMovRange(tileRange, newTile, movStamina, Direction.UP);
					calculateMovRange(tileRange, newTile, movStamina, Direction.LEFT);
					calculateMovRange(tileRange, newTile, movStamina, Direction.DOWN);
				}
			}
			if (direction.equals(Direction.DOWN)) {
				Tile newTile= gameMap.getTileAtCoordinates(startTile.getPos().getYPosition(),
						startTile.getPos().getXPosition()+1);
				if (newTile != null) {
					tileRange.add(newTile);
					calculateMovRange(tileRange, newTile, movStamina, Direction.RIGHT);
					calculateMovRange(tileRange, newTile, movStamina, Direction.DOWN);
					calculateMovRange(tileRange, newTile, movStamina, Direction.LEFT);	
				}
			}
		}
	}
	
	public void switchOwner() {
		currentIndex++;
		if (currentIndex >= gameMap.getOwners().size()) {
			currentIndex = 0;
		}
		currentOwner = gameMap.getOwners().get(currentIndex);
		currentOwner.refreshAllUnits();
	}
}
