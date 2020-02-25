package logicLayer;

public class LogicLayer{

	GameMap gameMap;
	
	public LogicLayer(GameMap gameMap) {
		this.gameMap = gameMap;
	}
	
	public void moveTo(Position startPosition, Position endPosition) {
		if (checkIfUnitIsAtPosition(startPosition) == true) {
//			Unit unit = gameMap.getTileAtPosition(position).getUnit();
			
		}
	}

	public void performCombat(Position unitPosition, Position enemyPosition) {
		
	}

	public void removeUnit() {
		
	}

	public void forecastCombat(Position unitPosition, Position enemyPosition) {
		
	}
	
	private boolean checkIfUnitIsAtPosition(Position position) {
		return gameMap.getTileAt(position).hasUnit();
	}
	
}
