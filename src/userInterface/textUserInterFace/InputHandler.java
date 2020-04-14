package userInterface.textUserInterFace;

import java.util.Scanner;

import customExceptions.InvalidInputException;
import logicLayer.Position;
import userInterface.Instruction;
import userInterface.InstructionType;
import userInterface.UserLogicBridge;
import userInterface.textUserInterFace.customExceptions.ImproperTextInputException;

public class InputHandler {
			
	private boolean gridInstructionsEnabled = true;
	private boolean unitInstructionsEnabled = false;
	private boolean metaInstructionEnabled = false;
	
	private static final String GRID_COMMAND_HELP = "Help: Enter either select, open meta menu. Other inputs are invalid";
	private static final String UNIT_COMMAND_HELP = "Help: Enter either move, attack, wait, cancel. Other inputs are invalid";
	private static final String META_COMMAND_HELP = "Help: Enter either end turn, cancel. Other inputs are invalid";
	private static final String CONFIRM_COMMAND_HELP = "Confirm attack, yes or no";
	
	private final Scanner scn = new Scanner(System.in);
	
	private UserLogicBridge coordinator;
	private GridPrinter grid;
	private ExtraInfoTextDisplay infoDisplay;
	
	public InputHandler(UserLogicBridge coordinator, GridPrinter grid, ExtraInfoTextDisplay infoDisplay){
		this.coordinator = coordinator;
		this.grid = grid;
		this.infoDisplay = infoDisplay;
		this.enableGridInstructions();
	}
	
	public void listenForNextInstruction() {
		System.out.println(this.getCurrentHelpMenu());
		while(true) {
			System.out.println("Enter command here:");
			String stringType = scn.nextLine().toUpperCase();
			try {
				readInstruction(stringType);
				System.out.println("Successfully executed command");
				break;
			} catch (ImproperTextInputException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private Position readPosition() {
		Position position = new Position(0,0);
		int xCoordinate = -1;
		while (xCoordinate<0) {
			System.out.println("Enter X Position:");
			String stringValue = scn.nextLine();
			xCoordinate = stringToInt(stringValue);
		}
		int yCoordinate = -1;
		while (yCoordinate<0) {
			System.out.println("Enter Y Position:");
			String stringValue = scn.nextLine();
			yCoordinate = stringToInt(stringValue);
		}
		position.setXPosition(xCoordinate);
		position.setYPosition(yCoordinate);
		return position;
	}
	
	private int stringToInt(String num) {
		try {
			int x = Integer.parseInt(num);
			return x;
		}
		catch (Exception e) {
			System.out.println("That's not a integer");
			return -1;
		}
	}
	
	private void readInstruction(String stringType) throws ImproperTextInputException {
		if (this.gridInstructionsEnabled) {
			this.readGridInstuction(stringType);
		}
		else if (this.unitInstructionsEnabled) {
			this.readUnitInstructions(stringType);
		}
		else if (this.metaInstructionEnabled) {
			this.readMetaInstruction(stringType);
		}
		else {
			throw new ImproperTextInputException();
		}
	}

	private void readGridInstuction(String stringType) throws ImproperTextInputException {
		if (stringType.equalsIgnoreCase("select")) {
			createSelectInstruction();
			
		}
		else if (stringType.equalsIgnoreCase("open meta menu")) {
			this.enableMetaInstructions();
		}
		else {
			throw new ImproperTextInputException();
		}
	}
	
	private void createSelectInstruction() {
		grid.printMap();
		Position position = this.readPosition();
		try {
			Instruction instruction = new Instruction(InstructionType.SELECT, position);
			coordinator.interpretRegularInstruction(instruction);
			this.grid.selectGridUnitAt(position.getInversePosition());
			this.enableUnitInstructions();
		} catch (InvalidInputException e) {
			System.out.println("You can't command this unit at the moment");
		}
		createUnitInfoDisplayInstruction(position);
	}
	
	private void createUnitInfoDisplayInstruction(Position position) {
		Instruction instruction = new Instruction(InstructionType.DISPLAY_UNIT_INFO, position);
		try {
			coordinator.interpretLabelDisplayInstruction(instruction,infoDisplay);
		} catch (InvalidInputException e) {
		}
	}
	
	private void readUnitInstructions(String stringType) throws ImproperTextInputException {
		if (stringType.equalsIgnoreCase("move")) {
			createDisplayMoveOptionsInstruciton();
		}
		else if ((stringType.equalsIgnoreCase("attack"))) {
			createDisplayAttackOptionsInstruction();
		}
		else if ((stringType.equalsIgnoreCase("wait"))){
			createWaitInstruction();
		}
		else if ((stringType.equalsIgnoreCase("cancel"))){
			createCancelInstruction();
		}
		else {
			throw new ImproperTextInputException();
		}
	}
	
	private void createDisplayMoveOptionsInstruciton() {
		Instruction instruction = new Instruction(InstructionType.FIND_MOVE_TILES, null);
		coordinator.interpretGridDisplayInstruction(instruction,grid);
		createMoveInstruction();
	}
	
	private void createMoveInstruction() {
		try {
			Position position = this.readPosition();
			Instruction instruction = new Instruction(InstructionType.MOVE_TO, position);
			coordinator.interpretRegularInstruction(instruction);
			grid.getSelectedGridUnit().moveTo(position);
			grid.printMap();
			this.enableUnitInstructions();
		} catch (InvalidInputException e) {
			System.out.print(e.getMessage());
		}
	}
	
	private void createDisplayAttackOptionsInstruction() throws ImproperTextInputException {
		Instruction instruction = new Instruction(InstructionType.FIND_ATTACK_TILES, null);
		coordinator.interpretGridDisplayInstruction(instruction,grid);
		if (grid.getHasHighlightedAttackTiles()) {
			createDisplayAttackForecastInstruction();
		}
		else {
			throw new ImproperTextInputException("No enemy units in range to attack");
		}
	}
	
	private void createDisplayAttackForecastInstruction() {
		try {
			Position position = this.readPosition();
			Instruction instruction = new Instruction(InstructionType.DISPLAY_ATTACK_FORECAST, position);
			coordinator.interpretLabelDisplayInstruction(instruction, infoDisplay);
			confirmAttack(position);
		} catch (InvalidInputException e) {
			System.out.print(e.getMessage());
		}	
	}
	
	private void confirmAttack(Position position) {
		System.out.println(CONFIRM_COMMAND_HELP);
		while (true) {
			String confirmMessage = scn.nextLine();
			if (confirmMessage.equalsIgnoreCase("Yes") || confirmMessage.equalsIgnoreCase("y")) {
				createAttackInstruction(position);
				break;
			}
			else if (confirmMessage.equalsIgnoreCase("No") || confirmMessage.equalsIgnoreCase("n")) {
				break;
			}
			else {
				System.out.println("Invalid answer");
			}
		}
	}
	
	private void createAttackInstruction(Position position) {
		try {
			Instruction instruction = new Instruction(InstructionType.ATTACK, position);
			coordinator.interpretLabelDisplayInstruction(instruction, infoDisplay);
			grid.removeDeadGridUnits();
			grid.printMap();
			this.enableGridInstructions();
		} catch (InvalidInputException e) {
			System.out.print(e.getMessage());
		}	
	}
	
	private void createWaitInstruction() {
		Instruction instruction = new Instruction(InstructionType.WAIT, null);
		try {
			coordinator.interpretRegularInstruction(instruction);
			grid.deselectGridUnit();
		} catch (InvalidInputException e) {}
		this.enableGridInstructions();
	}
	
	private void createCancelInstruction() {
		Instruction instruction = new Instruction(InstructionType.CANCEL, null);
		try {
			coordinator.interpretRegularInstruction(instruction);
			grid.getSelectedGridUnit().resetPosition();
		} catch (InvalidInputException e) {}
		this.enableGridInstructions();
		grid.printMap();
	}
	
	private void readMetaInstruction(String stringType) {
		if (stringType.equalsIgnoreCase("end turn")) {
			createEndTurnInstruction();
		}
		else if (stringType.equalsIgnoreCase("cancel")) {
			enableGridInstructions();
		}
	}
	
	private void createEndTurnInstruction() {
		Instruction instruction = new Instruction(InstructionType.END_TURN, null);
		coordinator.interpretGridDisplayInstruction(instruction, grid);
		this.enableGridInstructions();
		grid.printMap();
	}
	
	private String getCurrentHelpMenu() {
		if(this.gridInstructionsEnabled) {
			return InputHandler.GRID_COMMAND_HELP;
		}
		else if (this.unitInstructionsEnabled) {
			return InputHandler.UNIT_COMMAND_HELP;
		}
		else if (this.metaInstructionEnabled) {
			return InputHandler.META_COMMAND_HELP;
		}
		else {
			return "";
		}
	}
	
	private void enableGridInstructions() {
		this.gridInstructionsEnabled = true;
		this.unitInstructionsEnabled = false;
		this.metaInstructionEnabled = false;
	}
	
	private void enableUnitInstructions() {
		this.gridInstructionsEnabled = false;
		this.unitInstructionsEnabled = true;
		this.metaInstructionEnabled = false;
	}
	
	private void enableMetaInstructions() {
		this.gridInstructionsEnabled = false;
		this.unitInstructionsEnabled = false;
		this.metaInstructionEnabled = true;
	}
}
