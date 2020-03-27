package userInterface;

import inputLayer.InstructionType;
import customExceptions.InvalidInputException;
import inputLayer.Instruction;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import logicLayer.Position;
import main.Coordinator;

public class HandleKeyPresses implements EventHandler<KeyEvent> {

	private static final char MOVE_UP_LETTER = 'w';
	private static final char MOVE_DOWN_LETTER = 's';
	private static final char MOVE_RIGHT_LETTER = 'd';
	private static final char MOVE_LEFT_LETTER = 'a';
	private static final char SELECT_LETTER = 'k';
	private static final char CANCEL_LETTER = 'b';
	
	private Grid grid;
	private Label informationDisplay;
	private GuiLogicCoordinator coordinator;
	
	public HandleKeyPresses(Grid grid, Label informationDisplay,GuiLogicCoordinator coordinator){
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
					grid.resetHightlight();
					grid.getUnitSelectionMarker().disableMarker();
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
	}
	
	private void readInstructionFromGrid() {
		if (grid.hasHighlightedMoveTiles()) {
			createMoveInstruction();
		}
		else if (grid.findSelectedUnitMarker() != null){
			createSelectInstruction();
		}
		else {
			focusOnMetaCommandMenu();
		}
	}
	
	private void createSelectInstruction() {
		
		try {
			grid.moveUnitSelectionMarker();
			Position position = grid.getSelectionMarker().getCurrentPosition();
			Instruction instruction = new Instruction(InstructionType.SELECT, position);
			coordinator.interpretRegularInstruction(instruction);
			grid.moveUnitSelectionMarker();
			focusOnCommandSelection();	
		} catch (InvalidInputException e) {}
	}
	
	private void createMoveInstruction() {
		try {
			Position position = grid.getSelectionMarker().getCurrentPosition();
			Instruction instruction = new Instruction(InstructionType.MOVE, position);
			coordinator.interpretRegularInstruction(instruction);
			grid.moveSelectedUnitMarker();

			focusOnCommandSelection();
//			grid.getUnitSelectionMarker().disableMarker();
//			focusOnGrid();
		} catch (InvalidInputException e) {}
	}
	
	private void readInstructionFromCommandSelection() {
		if (grid.getUnitCommandSelection().isMoveSelected()) {
			createDisplayMoveOptionsInstruciton();
		}
		else if (grid.getUnitCommandSelection().isCancelSelected()) {
			grid.getUnitSelectionMarker().disableMarker();
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
	
	private void focusOnGrid() {
		grid.enable();
		grid.hideCommandSelectionMenu();
		grid.hideMetaCommandMenu();
	}
	
	private void focusOnCommandSelection() {
		grid.disable();
		grid.displayCommandSelectionMenu();
	}
	
	private void focusOnMetaCommandMenu() {
		grid.disable();
		grid.displayMetaCommandMenu();
	}
}
