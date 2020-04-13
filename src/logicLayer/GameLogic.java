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
		if (selectedUnit.isDead()) {
			selectedUnit.getTile().setUnit(null);
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
	
	private void removeUnit(Unit unit) {
		unit.getTile().setUnit(null);
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
	
	public BattleInstance performCombat(Position enemyPosition) throws InvalidAttackException {
		boolean isValidAttack = false;
		for (Position pos : calculateValidTileToAttack()) {
			if (pos.getXPosition() == enemyPosition.getInversePosition().getXPosition() &&
					pos.getYPosition() == enemyPosition.getInversePosition().getYPosition()) {
				isValidAttack = true;
				break;
			}
		}
		if (isValidAttack) {
			Unit enemy = gameMap.getTileAtPosition(enemyPosition).getUnit();
			BattleInstance instance = new BattleInstance(tempUnit, enemy);
			instance.runBattle();
			
			if (enemy.isDead()) {
				removeUnit(enemy);
			}
			return instance;
		}
		else {
			throw new InvalidAttackException("There's no enemy there to hit!");
		}
	}
	
	public void haveSelectedUnitEndTurn() {
		selectedUnit.copyOtherUnit(this.tempUnit);
		this.deselectUnit();
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
	
	private List<Tile> calculateValidTileToMoveTo(Unit unit) {
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
