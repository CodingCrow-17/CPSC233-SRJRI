package userInterface;

import inputLayer.InstructionType;
import inputLayer.Instructions;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import main.Coordinator;

public class HandleKeyPresses implements EventHandler<KeyEvent> {

	private static final char MOVE_UP_LETTER = 'w';
	private static final char MOVE_DOWN_LETTER = 's';
	private static final char MOVE_RIGHT_LETTER = 'd';
	private static final char MOVE_LEFT_LETTER = 'a';
	private static final char SELECT_LETTER = 'k';
	
	
	private Grid grid;
	private CommandSelection commandSelection;
	private boolean hasStarted = false;
	private GraphicalInterface application;
	
	public HandleKeyPresses(Grid grid, CommandSelection commandSelection, GraphicalInterface application){
		this.grid = grid;
		this.commandSelection = commandSelection;
		this.application = application;
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
			}
		}
		else if (commandSelection.isEnabled() == true){
			switch (choice){
				case(MOVE_UP_LETTER):
					commandSelection.getSelectionMarker().moveSelectionUp();
					break;
				case(MOVE_DOWN_LETTER):
					commandSelection.getSelectionMarker().moveSelectionDown();
					break;
				case(SELECT_LETTER):
					commandSelection.getSelectionMarker().getCurrentPosition();
					readInstructionFromCommandSelection();
					break;
			}
		}
	}
	
	private void readInstructionFromGrid() {
		if (grid.hasHighlightedMoveTiles()) {
			createMoveInstruction();
		}
		else {
			createSelectInstruction();
		}
	}
	
	private void createSelectInstruction() {
		boolean wasSuccessful = application.performSelectToCommand(grid.getSelectionMarker().getCurrentPosition());
		if (wasSuccessful == true) {
			focusOnCommandSelection();	
		}
	}
	
	private void createMoveInstruction() {
		boolean wasSuccessful = application.performMoveToTileCommand(grid.getSelectionMarker().getCurrentPosition());
		if (wasSuccessful == true) {
		}
	}
	
	private void readInstructionFromCommandSelection() {
		if (commandSelection.isMoveSelected()) {
			createDisplayMoveOptionsInstruciton();
		}
	}
	
	private void createDisplayMoveOptionsInstruciton() {
		boolean wasSuccessful = application.retrieveValidTilesToMoveTo();
		if (wasSuccessful) {
			focusOnGrid();	
		}
	}

	private void focusOnGrid() {
		grid.enable();
		commandSelection.disable();
	}
	
	private void focusOnCommandSelection() {
		grid.disable();
		commandSelection.enable();
	}
}
