package main;
import logicLayer.GameLogic;
import logicLayer.GameMap;
import logicLayer.Position;
import inputLayer.InputReciever;
import output.Output;
import output.TextOutputPrinter;

public class Coordinator {
	
	private GameMap gameMap;
	private InputReciever input;
	private Output output;
	private GameLogic logic;
	
	public Coordinator(GameMap gameMap, InputReciever inputReceiver, Output output) {
		this.gameMap = gameMap;
		this.input = inputReceiver;
		this.logic = new GameLogic(gameMap);
		this.output = output;
	}
	
	public void startGameLoop() {
		input.printStartingMessage();
		for (int i = 0; i<99; i++) {
			//player turn
			output.printCurrentTurnOwner(String.valueOf(logic.getCurrentOwner().getType()));
			output.printMap(gameMap);
			while(true) {
				int[] instructions = input.getInstruction();
				if (instructions != null) {
					performMoveToCommand(instructions);
					if (checkIfPlayerTurnIsOver() == true){
						break;
					}
					output.printMap(gameMap);
				}
			}
			logic.switchOwner();
			//enemy turn
			output.printCurrentTurnOwner(String.valueOf(logic.getCurrentOwner().getType()));
			output.printMap(gameMap);
			while (true) {
				//TODO create enemy logic to go here
				break;
			}
			logic.switchOwner();
		}
		input.close();
	}
	
	private void performMoveToCommand(int[] positionArray){
		Position initialPosition = new Position(positionArray[0], positionArray[1]);
		Position finalPosition = new Position(positionArray[2], positionArray[3]);
		logic.moveTo(initialPosition, finalPosition);
	}
	
	boolean checkIfPlayerTurnIsOver() {
		return logic.getCurrentOwner().checkIfAllUnitsMoved();
	}
}
