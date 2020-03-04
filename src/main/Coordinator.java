package main;
import logicLayer.GameLogic;
import logicLayer.GameMap;
import logicLayer.Position;
import inputLayer.InputReciever;
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
			while(true) {
				int[] instructions = input.getInstruction();
				performMoveToCommand(instructions);
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
	
	private void performMoveToCommand(int[] positionArray){
		Position initialPosition = new Position(positionArray[0], positionArray[1]);
		Position finalPosition = new Position(positionArray[2], positionArray[3]);
		logic.moveTo(initialPosition, finalPosition);
	}
	
	boolean checkIfPlayerTurnIsOver() {
		return logic.getCurrentOwner().checkIfAllUnitsMoved();
	}
}
