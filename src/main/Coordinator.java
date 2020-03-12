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
import output.TextOutputPrinter;

public class Coordinator {
	
	private GameMap gameMap;
	private InputReciever input;
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
				Instructions instructions = input.getInstruction();
				
				InstructionType type = instructions.getType();
				if (type.equals(InstructionType.SELECT)) {
					Position pos = instructions.getPosition();
					selectedUnit = gameMap.getTileAtPosition(pos).getUnit();
				}
				if (type.equals(InstructionType.DESELECT)) {
					selectedUnit = null;
				}
				if (type.equals(InstructionType.ATTACK)) {
					int ATTACKRANGE = 1;
					Unit enemyUnit = null;
					
					if (selectedUnit != null) {
						/*The following block of code is a TEMPORARY way to scan for enemy units.
						  It will be changed in the future so that the player may choose between
						  different targets, if possible. */
						Unit atkRangeDummyUnit = new Unit(selectedUnit);
						atkRangeDummyUnit.getStats().getMov().setCurrentValue(ATTACKRANGE);
						List<Tile> attackableTiles = logic.calculateValidTileToMoveTo(atkRangeDummyUnit);
						for (Tile tile : attackableTiles) {
							if (tile.hasUnit()) {
								if (!tile.getUnit().getOwner().equals(logic.getCurrentOwner())) {
									enemyUnit = tile.getUnit();
								}
							}
						}
						
						
						BattleForecast forecast = new BattleForecast(selectedUnit, enemyUnit);
						textOutput.printBattleForecast(forecast);
						
						BattleInstance instance = new BattleInstance(forecast);
						
						enemyUnit.takeDamage(instance.getAttDamageDone());
						if (!enemyUnit.isDead()) {
							selectedUnit.takeDamage(instance.getDefDamageDone());
						}
						textOutput.printBattleInstance(instance);
						
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
