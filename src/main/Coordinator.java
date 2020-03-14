package main;
import logicLayer.GameLogic;
import logicLayer.GameMap;
import logicLayer.Position;
import logicLayer.Unit;
import inputLayer.InputReciever;
import inputLayer.InstructionType;
import inputLayer.Instructions;
import inputLayer.TextInputReciever;
import output.Output;
import output.TextOutputPrinter;

public class Coordinator {
	
	private GameMap gameMap;
	private InputReciever input;
	private TextOutputPrinter textOutput = new TextOutputPrinter();
	private TextInputReciever tInput = new TextInputReciever();
	private GameLogic logic;
	
	 	public Coordinator(GameMap gameMap, InputReciever inputReceiver, Output output) {
		this.gameMap = gameMap;
		this.input = inputReceiver;
		this.logic = new GameLogic(gameMap);
	}
	
	public void startGameLoop() {
		input.printStartingMessage();
		for (int i = 0; i<1; i++) {
			//player turn
			textOutput.printCurrentTurnOwner(String.valueOf(logic.getCurrentOwner().getType()));
			textOutput.printMap(gameMap);
			System.out.println(" hi I'm:: " + this);
			while(true) {
				Instructions instruction = input.getNextInstruction();
				InstructionType type = instruction.getType();
				Position position = instruction.getPosition();
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
	
	public void performMoveToCommand(int[] positionArray){
		Position initialPosition = new Position(positionArray[0], positionArray[1]);
		Position finalPosition = new Position(positionArray[2], positionArray[3]);
		logic.moveTo(initialPosition, finalPosition);
	}

	
	public boolean checkIfPlayerTurnIsOver() {
		return logic.getCurrentOwner().checkIfAllUnitsMoved();
	}
}
