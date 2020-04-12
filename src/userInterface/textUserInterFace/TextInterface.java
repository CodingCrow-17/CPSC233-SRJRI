package userInterface.textUserInterFace;

import logicLayer.GameMap;
import main.StartUpClass;
import userInterface.Instruction;
import userInterface.UserLogicBridge;

public class TextInterface {
	
	private UserLogicBridge coordinator;
	private GridPrinter grid;
	private ExtraInfoTextDisplay informationDisplay = new ExtraInfoTextDisplay();

	public void start() {
		GameMap gameMap = StartUpClass.initializeGameMap();
		this.coordinator = new UserLogicBridge(gameMap);
		this.grid = new GridPrinter(gameMap.getTiles());
		InputHandler handler = new InputHandler(coordinator, grid, informationDisplay);
		
		System.out.println("SRJRI project");
		grid.printMap();
		while (true) {
			handler.listenForNextInstruction();
		}
	}
	
}
