package logicLayer;

public class LogicLayer{

	private GameMap gameMap;
	private Owner currentOwner;
	private int currentIndex = -1;
	
	public LogicLayer(GameMap gameMap) {
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
							if (unit.getStats().getMov() >= calculateMovCostBetweenTile(startTile, finalTile)) {
								finalTile.setUnit(unit);
								startTile.setUnit(null);
								unit.moveTo(finalTile);
							}
							else {
								System.out.println("Out of mov range!");
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
					System.out.println("You can't move to a place that already has a unit!");
				}
			}
			else {
				System.out.println("You don't have a unit there!");
			}	
		}
		else {
			System.out.println("Your position are out of bounds!");
		}
	}

	public void performCombat(Position unitPosition, Position enemyPosition) {
		//TODO
	}

	public void removeUnit() {
		//TODO
	}

	public void forecastCombat(Position unitPosition, Position enemyPosition) {
		//TODO
	}
	
	private boolean checkIfUnitIsOnTile(Tile tile) {
		return tile.hasUnit();
	}
	
	private int calculateMovCostBetweenTile(Tile startTile, Tile finalTile) {
		int xDiff = Math.abs(startTile.getPos().getXPosition() - finalTile.getPos().getXPosition());
		int yDiff = Math.abs(startTile.getPos().getYPosition() - finalTile.getPos().getYPosition());
		int moveCost = xDiff+yDiff;
		return moveCost;
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
