package userInterface.graphicalUserInterface;

import customExceptions.InvalidInputException;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import logicLayer.Position;
import userInterface.Instruction;
import userInterface.InstructionType;
import userInterface.UserLogicBridge;
import userInterface.graphicalUserInterface.customFxClasses.Grid;
import userInterface.graphicalUserInterface.customFxClasses.InformationDisplay;

public class HandleKeyPresses implements EventHandler<KeyEvent> {

	private static final char MOVE_UP_LETTER = 'w';
	private static final char MOVE_DOWN_LETTER = 's';
	private static final char MOVE_RIGHT_LETTER = 'd';
	private static final char MOVE_LEFT_LETTER = 'a';
	private static final char SELECT_LETTER = 'k';
	private static final char CANCEL_LETTER = 'b';
	
	private Grid grid;
	private InformationDisplay informationDisplay;
	private UserLogicBridge coordinator;
	
	public HandleKeyPresses(Grid grid, InformationDisplay informationDisplay,UserLogicBridge coordinator){
		this.grid = grid;
		this.informationDisplay = informationDisplay;
		this.coordinator = coordinator;
	}
	
	@Override
	public void handle(KeyEvent event) {
		char choice = event.getCharacter().charAt(0);
		if (grid.isEnabled() == true) {
			switch (choice){
				case(MOVE_UP_LETTER):
					grid.getSelectionMarker().moveSelectionUp();
					break;
				case(MOVE_DOWN_LETTER):
					grid.getSelectionMarker().moveSelectionDown();
					break;
				case(MOVE_RIGHT_LETTER):
					grid.getSelectionMarker().moveSelectionRight();
					break;
				case(MOVE_LEFT_LETTER):
					grid.getSelectionMarker().moveSelectionLeft();
					break;
				case(SELECT_LETTER):
					readInstructionFromGrid();
					break;
				case(CANCEL_LETTER):
					cancelGridInstruction();
					break;
			}
		}
		else if (grid.getUnitCommandSelection().isEnabled() == true){
			switch (choice){
				case(MOVE_UP_LETTER):
					grid.getUnitCommandSelection().getSelectionMarker().moveSelectionUp();
					break;
				case(MOVE_DOWN_LETTER):
					grid.getUnitCommandSelection().getSelectionMarker().moveSelectionDown();
					break;
				case(SELECT_LETTER):
					readInstructionFromCommandSelection();
					break;
			}
		}
		else if(grid.getMetaMenu().isEnabled() == true) {
			switch (choice){
				case(MOVE_UP_LETTER):
					grid.getMetaMenu().getSelectionMarker().moveSelectionUp();
					break;
				case(MOVE_DOWN_LETTER):
					grid.getMetaMenu().getSelectionMarker().moveSelectionDown();
					break;
				case(SELECT_LETTER):
					readInstructionFromMetaMenu();
					break;
			}
		}
		else if(grid.getConfirmMenu().isEnabled() == true) {
			switch (choice){
			case(MOVE_UP_LETTER):
				grid.getConfirmMenu().getSelectionMarker().moveSelectionUp();
				break;
			case(MOVE_DOWN_LETTER):
				grid.getConfirmMenu().getSelectionMarker().moveSelectionDown();
				break;
			case(SELECT_LETTER):
				readInstructionFromConfirmMenu();
				break;
		}
		}
	}
	
	private void readInstructionFromGrid() {
		if (grid.hasHighlightedMoveTiles()) {
			createMoveInstruction();
		}
		else if (grid.hasHighlightedAttackTiles()) {
			createDisplayAttackForecastInstruction();
		}
		else if (grid.findSelectedUnitMarker() != null){
			createSelectInstruction();
		}
		else {
			focusOnMetaCommandMenu();
		}
	}
	
	private void cancelGridInstruction() {
		if (grid.hasHighlightedMoveTiles()) {
			grid.resetMovementOfSelectedUnitMarker();
			this.focusOnCommandSelection();
		}
		else if (grid.hasHighlightedAttackTiles()) {
		}
		else if (grid.findSelectedUnitMarker() != null){
		}
		else {
		}
		grid.resetHightlight();
		grid.getUnitSelectionMarker().disableMarker();
	}

	private void createDisplayAttackForecastInstruction() {
		try {
			Position position = grid.getSelectionMarker().getCurrentPosition();
			Instruction instruction = new Instruction(InstructionType.DISPLAY_ATTACK_FORECAST, position);
			coordinator.interpretLabelDisplayInstruction(instruction, informationDisplay);
			this.focusOnConfirmMenu();
		} catch (InvalidInputException e) {
			System.out.print(e.getMessage());
		}	
	}

	private void createSelectInstruction() {
		try {
			Position position = grid.getSelectionMarker().getCurrentPosition();
			Instruction instruction = new Instruction(InstructionType.SELECT, position);
			coordinator.interpretRegularInstruction(instruction);
			grid.moveUnitSelectionMarker();
			focusOnCommandSelection();	
		} catch (InvalidInputException e) {
			System.out.print(e.getMessage());
		}
		createUnitInfoDisplayInstruction();
	}
	
	private void createUnitInfoDisplayInstruction() {
		Position position = grid.getSelectionMarker().getCurrentPosition();
		Instruction instruction = new Instruction(InstructionType.DISPLAY_UNIT_INFO, position);
		try {
			coordinator.interpretLabelDisplayInstruction(instruction,informationDisplay);
		} catch (InvalidInputException e) {
		}
	}
	
	private void createMoveInstruction() {
		try {
			Position position = grid.getSelectionMarker().getCurrentPosition();
			Instruction instruction = new Instruction(InstructionType.MOVE_TO, position);
			coordinator.interpretRegularInstruction(instruction);
			grid.moveSelectedUnitMarker();

			focusOnCommandSelection();
		} catch (InvalidInputException e) {
			System.out.print(e.getMessage());
		}
	}
	
	private void readInstructionFromCommandSelection() {
		if (grid.getUnitCommandSelection().isMoveSelected()) {
			createDisplayMoveOptionsInstruciton();
		}
		else if (grid.getUnitCommandSelection().isCancelSelected()) {
			grid.getUnitSelectionMarker().disableMarker();
			createCancelInstruction();
			focusOnGrid();
		}
		else if (grid.getUnitCommandSelection().isAttackSelected()) {
			createDisplayAttackOptionsInstruction();
		}
		else if (grid.getUnitCommandSelection().isWaitSelected()) {
			createWaitInstruction();
		}
	}
	
	private void createDisplayMoveOptionsInstruciton() {
		Instruction instruction = new Instruction(InstructionType.FIND_MOVE_TILES, null);
		coordinator.interpretGridDisplayInstruction(instruction,grid);
		focusOnGrid();
	}
	
	private void createDisplayAttackOptionsInstruction() {
		Instruction instruction = new Instruction(InstructionType.FIND_ATTACK_TILES, null);
		coordinator.interpretGridDisplayInstruction(instruction,grid);
		if (grid.hasHighlightedAttackTiles()) {
			focusOnGrid();
		}
	}
	
	private void createCancelInstruction() {
		Instruction instruction = new Instruction(InstructionType.CANCEL, null);
		try {
			coordinator.interpretRegularInstruction(instruction);
			grid.resetMovementOfSelectedUnitMarker();
			grid.getUnitSelectionMarker().disableMarker();
		} catch (InvalidInputException e) {}
		focusOnGrid();
	}
	
	private void createWaitInstruction() {
		Instruction instruction = new Instruction(InstructionType.WAIT, null);
		try {
			coordinator.interpretRegularInstruction(instruction);
			grid.getUnitSelectionMarker().disableMarker();
		} catch (InvalidInputException e) {}
		focusOnGrid();
	}
	
	private void readInstructionFromMetaMenu() {
		if (grid.getMetaMenu().isEndTurnSelected()) {
			createEndTurnInstruction();
		}
		else if (grid.getMetaMenu().isCancelSelected()) {
			focusOnGrid();
		}
	}
	
	private void createEndTurnInstruction() {
		Instruction instruction = new Instruction(InstructionType.END_TURN, null);
		try {
			coordinator.interpretRegularInstruction(instruction);
		} catch (InvalidInputException e) {}
		
		focusOnGrid();
	}
	
	private void readInstructionFromConfirmMenu() {
		if (grid.getConfirmMenu().isConfirmSelected()) {
			if (grid.hasHighlightedAttackTiles()) {
				createAttackInstruction();
			}
		}
		else {
			this.focusOnCommandSelection();
		}
	}
	
	private void createAttackInstruction() {
		try {
			Position position = grid.getSelectionMarker().getCurrentPosition();
			Instruction instruction = new Instruction(InstructionType.ATTACK, position);
			coordinator.interpretLabelDisplayInstruction(instruction, informationDisplay);
			grid.resetHightlight();
			grid.getUnitSelectionMarker().disableMarker();
			grid.removeDeadUnitMarkers();
			this.focusOnGrid();
		} catch (InvalidInputException e) {
			System.out.print(e.getMessage());
		}	
	}
	
	private void focusOnGrid() {
		grid.enable();
		grid.hideCommandSelectionMenu();
		grid.hideMetaCommandMenu();
		grid.hideConfirmMenu();
	}
	
	private void focusOnCommandSelection() {
		grid.disable();
		grid.hideConfirmMenu();
		grid.hideMetaCommandMenu();
		grid.displayCommandSelectionMenu();
	}
	
	private void focusOnMetaCommandMenu() {
		grid.disable();
		grid.hideConfirmMenu();
		grid.hideCommandSelectionMenu();
		grid.displayMetaCommandMenu();
	}
	
	private void focusOnConfirmMenu() {
		grid.disable();
		grid.hideCommandSelectionMenu();
		grid.hideMetaCommandMenu();
		grid.displayConfirmMenu();;
	}
}