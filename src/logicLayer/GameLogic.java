package logicLayer;

import java.util.ArrayList;
import java.util.List;

import customExceptions.InvalidAttackException;
import customExceptions.InvalidMoveException;
import customExceptions.InvalidSelectException;

public class GameLogic{

	private GameMap gameMap;
	private Owner currentOwner;
	private int currentIndex = -1;
	private Unit selectedUnit = null;
	private Unit tempUnit = null;
	
	public GameLogic(GameMap gameMap) {
		this.gameMap = gameMap;
		switchOwner();
	}
	
	public boolean hasSelectedUnit() {
		return selectedUnit != null;
	}
	
	public Unit getSelectedUnit() {
		return selectedUnit;
	}
	
	public Owner getCurrentOwner() {
		return currentOwner;
	}
	
	public void selectUnitAtPosition(Position position) throws InvalidSelectException{
		Unit temp = getUnitAtPosition(position);
		if(temp.getOwner().equals(currentOwner) && temp.getHasMoved() == false) {
			this.selectedUnit = temp;
			tempUnit = new Unit(selectedUnit);
		}
		else {
			selectedUnit = null;
			throw new InvalidSelectException("You can't select that unit!");		
		}
	}
	
	public Unit getUnitAtPosition(Position position) throws InvalidSelectException{
		Unit temp = gameMap.getTileAtPosition(position).getUnit();
		if (temp != null) {
			return temp;
		}
		else {
			throw new InvalidSelectException("There's no unit there!");
		}
	}
	
	public void deselectUnit() {
		if (tempUnit.getTile().equals(selectedUnit.getTile()) == false){
			tempUnit.getTile().setUnit(null);
		}
		this.tempUnit = null;
		this.selectedUnit = null;
	}
	
	public void moveSelectedUnitTo(Position position) throws InvalidMoveException {
		Tile destinationTile = gameMap.getTileAtPosition(position);
		if (selectedUnit.getHasMoved() == false) {
			List<Tile> validTilesToMoveTo = calculateValidTileToMoveTo(selectedUnit);
			if (validTilesToMoveTo.contains(destinationTile)) {
				tempUnit.moveTo(destinationTile);
			}
			else {
				throw new InvalidMoveException("Can't move to a tile that far!");
			}
		}	
		else {
			throw new InvalidMoveException("You've already moved this turn!");
		}
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
								System.out.println("Succesfully moved unit!");
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
	
	public BattleForecast forcastBattle(Position enemyPosition) throws InvalidAttackException {
		boolean isValidAttack = false;
		for (Position pos : calculateValidTileToAttack()) {
			if (pos.getXPosition() == enemyPosition.getInversePosition().getXPosition() &&
					pos.getYPosition() == enemyPosition.getInversePosition().getYPosition()) {
				isValidAttack = true;
				break;
			}
		}
		if (isValidAttack) {
			BattleForecast forecast = new BattleForecast(tempUnit, gameMap.getTileAtPosition(enemyPosition).getUnit());
			return forecast;
		}
		else {
			throw new InvalidAttackException("There's no enemy there to hit!");
		}
		
	}
	
	public void performCombat(Position enemyPosition) throws InvalidAttackException {
		boolean isValidAttack = false;
		for (Position pos : calculateValidTileToAttack()) {
			if (pos.getXPosition() == enemyPosition.getInversePosition().getXPosition() &&
					pos.getYPosition() == enemyPosition.getInversePosition().getYPosition()) {
				isValidAttack = true;
				break;
			}
		}
		if (isValidAttack) {
			BattleForecast forecast = new BattleForecast(tempUnit, gameMap.getTileAtPosition(enemyPosition).getUnit());
			BattleInstance instance = new BattleInstance(forecast);
		}
		else {
			throw new InvalidAttackException("There's no enemy there to hit!");
		}
	}
	
	public void haveSelectedUnitEndTurn() {
		selectedUnit.copyOtherUnit(this.tempUnit);
		this.deselectUnit();
	}
	
	
	private boolean checkIfUnitIsOnTile(Tile tile) {
		return tile.hasUnit();
	}
	
	public List<Position> findValidTileToMoveToPositions(){
		List<Tile> tiles = calculateValidTileToMoveTo(selectedUnit);
		return tileListToPositionList(tiles);
		
	}
	
	public List<Position> calculateValidTileToAttack(){
		List<Tile> tiles = new ArrayList<Tile>();
		List<Tile> tilesToRemove = new ArrayList<Tile>();
		int attackRange = tempUnit.getAttackRange()+1;
		Tile startTile = tempUnit.getTile();
		calculateRange(tiles, startTile, attackRange, Direction.NONE);
		for(Tile tile : tiles) {
			if (tile.hasUnit()) {
				if (tile.getUnit().getOwner().getType().equals(selectedUnit.getOwner().getType())){
					tilesToRemove.add(tile);
				}
			}
			else {
				tilesToRemove.add(tile);
			}
		}
		for(Tile tile : tilesToRemove) {
			tiles.remove(tile);
		}
		return tileListToPositionList(tiles);
	}
	
	public List<Tile> calculateValidTileToMoveTo(Unit unit) {
		List<Tile> tiles = new ArrayList<Tile>();
		List<Tile> tilesToRemove = new ArrayList<Tile>();
		Direction direction = Direction.NONE;
		Tile startTile = unit.getTile();	
		int movStamina = unit.getStats().getMov().getCurrentValue()+1;
		tiles.add(startTile);
		calculateRange(tiles, startTile, movStamina, direction);
		for(Tile tile : tiles) {
			if (tile.hasUnit()) {
				if (tile.getUnit().equals(this.tempUnit) == false){
					tilesToRemove.add(tile);
				}
			}
		}
		for(Tile tile : tilesToRemove) {
			tiles.remove(tile);
		}
		return tiles;
	}
	
	private void calculateRange(List<Tile> tileRange, Tile startTile, int movStamina, Direction direction) {
		if (movStamina == 0) { // base case
			return;
		}
		else {
			movStamina--;
			if (direction.equals(Direction.NONE)) {
				calculateRange(tileRange, startTile, movStamina, Direction.UP);
				calculateRange(tileRange, startTile, movStamina, Direction.RIGHT);
				calculateRange(tileRange, startTile, movStamina, Direction.DOWN);
				calculateRange(tileRange, startTile, movStamina, Direction.LEFT);
			}
			if (direction.equals(Direction.RIGHT)) {
				Tile newTile= gameMap.getTileAtCoordinates(startTile.getPos().getYPosition()+1,
						startTile.getPos().getXPosition());
				if (newTile != null) {
					tileRange.add(newTile);
					calculateRange(tileRange, newTile, movStamina, Direction.UP);
					calculateRange(tileRange, newTile, movStamina, Direction.RIGHT);
					calculateRange(tileRange, newTile, movStamina, Direction.DOWN);	
				}
			}
			if (direction.equals(Direction.UP)) {
				Tile newTile = gameMap.getTileAtCoordinates(startTile.getPos().getYPosition(),
						startTile.getPos().getXPosition()-1);
				if (newTile != null) {
					tileRange.add(newTile);
					calculateRange(tileRange, newTile, movStamina, Direction.RIGHT);
					calculateRange(tileRange, newTile, movStamina, Direction.LEFT);
					calculateRange(tileRange, newTile, movStamina, Direction.UP);	
				}
			}
			if (direction.equals(Direction.LEFT)) {
				Tile newTile= gameMap.getTileAtCoordinates(startTile.getPos().getYPosition()-1,
						startTile.getPos().getXPosition());
				if (newTile != null) {
					tileRange.add(newTile);
					calculateRange(tileRange, newTile, movStamina, Direction.UP);
					calculateRange(tileRange, newTile, movStamina, Direction.LEFT);
					calculateRange(tileRange, newTile, movStamina, Direction.DOWN);
				}
			}
			if (direction.equals(Direction.DOWN)) {
				Tile newTile= gameMap.getTileAtCoordinates(startTile.getPos().getYPosition(),
						startTile.getPos().getXPosition()+1);
				if (newTile != null) {
					tileRange.add(newTile);
					calculateRange(tileRange, newTile, movStamina, Direction.RIGHT);
					calculateRange(tileRange, newTile, movStamina, Direction.DOWN);
					calculateRange(tileRange, newTile, movStamina, Direction.LEFT);	
				}
			}
		}
	}
	
	private List<Position> tileListToPositionList(List<Tile> tiles) {
		List<Position> positions = new ArrayList<Position>();
		for (Tile tile : tiles) {
			positions.add(tile.getPos());
		}
		return positions;
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
