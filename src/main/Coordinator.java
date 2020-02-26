package main;
import logicLayer.LogicLayer;
import logicLayer.Position;

public class Coordinator {
	
	LogicLayer logic;
	
	public Coordinator(LogicLayer logic) {
		this.logic = logic;
	}
	
	public void startGameLoop() {
		while (true) {
			
		}
	}
	
	public void parseInput(String userInput) {
		
	}
	
	private void performMoveToCommand(int[] positionArray){
		Position initialPosition = new Position(positionArray[0], positionArray[1]);
		Position finalPosition = new Position(positionArray[2], positionArray[3]);
		logic.moveTo(initialPosition, finalPosition);
	}
}
