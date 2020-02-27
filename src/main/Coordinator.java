package main;
import logicLayer.GameLogic;
import logicLayer.GameMap;
import logicLayer.Position;
import inputLayer.TextInputReciever;
import output.TextOutputPrinter;

public class Coordinator {
	
	private GameMap gameMap;
	private TextInputReciever textInput = new TextInputReciever();
	private TextOutputPrinter textOutput = new TextOutputPrinter();
	private GameLogic logic;
	
	public Coordinator(GameMap gameMap) {
		this.gameMap = gameMap;
		this.logic = new GameLogic(gameMap);
	}
	
	public void startGameLoop() {
		textInput.printStartingMessage();
		for (int i = 0; i<99; i++) {
			//player turn
			textOutput.printCurrentTurnOwner(String.valueOf(logic.getCurrentOwner().getType()));
			while(true) {
				System.out.println("loop one");
				textOutput.printMap(gameMap);
				System.out.println("loop two");
				int[] instructions = textInput.getInstruction();
				performMoveToCommand(instructions);
				if (checkIfPlayerTurnIsOver() == true){
					break;
				}
			}
			logic.switchOwner();
			//enemy turn
			textOutput.printCurrentTurnOwner(String.valueOf(logic.getCurrentOwner().getType()));
			while (true) {
				textOutput.printMap(gameMap);
				//TODO create enemy logic to go here
				break;
			}
			logic.switchOwner();
		}
		textInput.close();
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
