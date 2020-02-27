package main;
import logicLayer.LogicLayer;
import logicLayer.GameMap;
import logicLayer.Position;
import inputLayer.TextInputReciever;

public class Coordinator {
	
	private GameMap gameMap;
	private TextInputReciever textInput = new TextInputReciever();
	private LogicLayer logic;
	
	public Coordinator(GameMap gameMap) {
		this.gameMap = gameMap;
		this.logic = new LogicLayer(gameMap);
	}
	
	public void startGameLoop() {
		textInput.printStartingMessage();
		for (int i = 0; i<99; i++) {
			//output prints the thing
			int[] instructions = textInput.getInstruction();
			performMoveToCommand(instructions);
		}
		textInput.close();
	}
	
	public void parseInput(String userInput) {
		
	}
	
	private void performMoveToCommand(int[] positionArray){
		Position initialPosition = new Position(positionArray[0], positionArray[1]);
		Position finalPosition = new Position(positionArray[2], positionArray[3]);
		logic.moveTo(initialPosition, finalPosition);
	}
}
