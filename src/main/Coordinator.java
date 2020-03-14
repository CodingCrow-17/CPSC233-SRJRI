package main;
import java.util.List;
import logicLayer.BattleForecast;
import logicLayer.BattleInstance;
import logicLayer.GameLogic;
import logicLayer.GameMap;
import logicLayer.Position;
import logicLayer.Tile;
import logicLayer.Unit;
import inputLayer.InputReciever;
import inputLayer.InstructionType;
import inputLayer.Instructions;
import inputLayer.TextInputReciever;
import output.TextOutputPrinter;

public class Coordinator {
	
	private GameMap gameMap;
	private InputReciever input;
	private TextInputReciever tInput = new TextInputReciever();
	private TextOutputPrinter textOutput = new TextOutputPrinter();
	private GameLogic logic;
	
	public Coordinator(GameMap gameMap, InputReciever inputReceiver) {
		this.gameMap = gameMap;
		this.input = inputReceiver;
		this.logic = new GameLogic(gameMap);
	}
	
	public void startGameLoop() {
		input.printStartingMessage();
		for (int i = 0; i<99; i++) {
			//player turn
			textOutput.printCurrentTurnOwner(String.valueOf(logic.getCurrentOwner().getType()));
			textOutput.printMap(gameMap);
			Unit selectedUnit = null;
			while(true) {
				InstructionType type = tInput.setInstruction();
				if (type.equals(InstructionType.ENDTURN)) {
					break;
				}
				if (type.equals(InstructionType.SELECT)) {
					Position pos = tInput.selectPosition();
					selectedUnit = gameMap.getTileAtPosition(pos).getUnit();
					System.out.println(selectedUnit.getName() + " selected.");
				}
				if (type.equals(InstructionType.DESELECT)) {
					selectedUnit = null;
				}
				if (type.equals(InstructionType.MOVE)) {
					if (selectedUnit != null) {
						if (!selectedUnit.getHasMoved()) {
							boolean canMove = false;
							List<Tile> moveableTiles = logic.calculateValidTileToMoveTo(selectedUnit);
							Position endPosition = tInput.selectPosition();
							for (Tile tile : moveableTiles) {
								if (tile.equals(gameMap.getTileAtPosition(endPosition))) {
									canMove = true;
								}
							}
							if (canMove) {
								selectedUnit.moveTo(gameMap.getTileAtPosition(endPosition));
							}
							else {
								System.out.println("That tile is outside " + selectedUnit.getName() + "'s move range!");
							}
						}
						else {
							System.out.println(selectedUnit.getName() + " has already moved!");
						}
					}
					else {
						System.out.println("No unit selected!");
					}
				}
				if (type.equals(InstructionType.ATTACK)) {
					int ATTACKRANGE = 1;
					Unit enemyUnit = null;
					
					if (selectedUnit != null) {
						/*The following block of code is a TEMPORARY way to scan for enemy units.
						  It will be changed in the future so that the player may choose between
						  different targets, if possible. */
						int mov = selectedUnit.getStats().getMov().getCurrentValue();
						selectedUnit.getStats().getMov().setCurrentValue(ATTACKRANGE);
						List<Tile> attackableTiles = logic.calculateValidTileToMoveTo(selectedUnit);
						selectedUnit.getStats().getMov().setCurrentValue(mov);
						for (Tile tile : attackableTiles) {
							if (tile.hasUnit()) {
								if (!tile.getUnit().getOwner().equals(logic.getCurrentOwner())) {
									enemyUnit = tile.getUnit();
								}
							}
						}
						
						if (enemyUnit != null) {
							selectedUnit.setHasMoved(true);
							BattleForecast forecast = new BattleForecast(selectedUnit, enemyUnit);
							textOutput.printBattleForecast(forecast);
							
							BattleInstance instance = new BattleInstance(forecast);
							BattleInstance instance2 = new BattleInstance(instance);
							
							if (!selectedUnit.isDead()) {
								enemyUnit.takeDamage(instance.getAttDamageDone());
							}
							if (!enemyUnit.isDead()) {
								selectedUnit.takeDamage(instance.getDefDamageDone());
							}
							if (forecast.willHitTwice(selectedUnit,enemyUnit) && !selectedUnit.isDead()) {
								enemyUnit.takeDamage(instance2.getAttDamageDone());
							}
							if (forecast.willHitTwice(enemyUnit,selectedUnit) && !enemyUnit.isDead()) {
								selectedUnit.takeDamage(instance2.getDefDamageDone());
							}
							textOutput.printBattleInstance(instance, instance2);
						}
						else {
							System.out.println("No enemy units nearby!");
						}
						
					}
					else {
						System.out.println("No unit selected!");
					}
				}
				
				
				if (checkIfPlayerTurnIsOver() == true){
					break;
				}
				textOutput.printMap(gameMap);
			}
			logic.switchOwner();
			//enemy turn
			textOutput.printCurrentTurnOwner(String.valueOf(logic.getCurrentOwner().getType()));
			textOutput.printMap(gameMap);
			while (true) {
				//TODO create enemy logic to go here
				break;
			}
			logic.switchOwner();
		}
		input.close();
	}
	

	
	boolean checkIfPlayerTurnIsOver() {
		return logic.getCurrentOwner().checkIfAllUnitsMoved();
	}
}
