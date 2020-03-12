package userInterface;

import inputLayer.InstructionType;
import inputLayer.Instructions;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import main.Coordinator;

public class HandleKeyPresses implements EventHandler<KeyEvent> {

	private static final String BEGIN_LETTER = "b";
	private static final char MOVE_UP_LETTER = 'w';
	private static final char MOVE_DOWN_LETTER = 's';
	private static final char MOVE_RIGHT_LETTER = 'd';
	private static final char MOVE_LEFT_LETTER = 'a';
	private static final char SELECT_LETTER = '5';
	
	
	
	Grid grid;
	CommandSelection commandSelection;
	Coordinator coordinator;
	boolean hasStarted = false;
	Instructions instruction;
	
	public HandleKeyPresses(Grid grid, CommandSelection commandSelection,Coordinator coordinator, Instructions instruction){
		this.grid = grid;
		this.commandSelection = commandSelection;
		this.coordinator = coordinator;
		this.instruction = instruction;
	}
	
	
	@Override
	public void handle(KeyEvent event) {
		System.out.println(event.getCharacter());
		if (hasStarted == false && event.getCharacter().equals(BEGIN_LETTER)) {
			hasStarted = true;
			coordinator.startGameLoop();
		}
		else {
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
						break;
				}
			}
			else if (commandSelection.isEnabled()){
				switch (choice){
				case(MOVE_UP_LETTER):
					grid.getSelectionMarker().moveSelectionUp();
					break;
				case(MOVE_DOWN_LETTER):
					grid.getSelectionMarker().moveSelectionDown();
					break;
				}
			}
		}
	}
	
	private void createSelectInstruction() {
		this.instruction = new Instructions(InstructionType.SELECT, grid.getSelectionMarker().getCurrentPosition());
	}

}
