package main;
import logicLayer.GameLogic;
import logicLayer.GameMap;
import logicLayer.Position;
import logicLayer.Unit;
import inputLayer.InputReciever;
import inputLayer.InstructionType;
import inputLayer.Instructions;
import output.Output;

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
		for (int i = 0; i<1; i++) {
			//player turn
			output.printCurrentTurnOwner(String.valueOf(logic.getCurrentOwner().getType()));
			output.printMap(gameMap);
			System.out.println("printed map!");
			while(true) {
				Instructions instructions = input.getNextInstruction();
				InstructionType type = instructions.getType();
				Position position = instructions.getPosition();
				if (type.equals(InstructionType.SELECT)) {
					logic.selectUnitAtPosition(position);
					System.out.println("selected unit!");
				}
				if (type.equals(InstructionType.DESELECT)) {
					logic.deselectUnit();
				}
				
				if (checkIfPlayerTurnIsOver() == true){
					break;
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
