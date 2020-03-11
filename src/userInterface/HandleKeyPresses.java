package userInterface;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Coordinator;

public class HandleKeyPresses implements EventHandler<KeyEvent> {

	private static final String BEGIN_LETTER = "b";
	private static final char MOVE_UP_LETTER = 'w';
	private static final char MOVE_DOWN_LETTER = 's';
	private static final char MOVE_RIGHT_LETTER = 'd';
	private static final char MOVE_LEFT_LETTER = 'a';
	
	
	
	Grid grid;
	Coordinator coordinator;
	boolean hasStarted = false;
	
	public HandleKeyPresses(Grid grid, Coordinator coordinator){
		this.grid = grid;
		this.coordinator = coordinator;
	}
	
	
	@Override
	public void handle(KeyEvent event) {
		System.out.println(event.getCharacter());
		if (hasStarted == false && event.getCharacter().equals(BEGIN_LETTER)) {
			hasStarted = true;
//			coordinator.startGameLoop();
		}
		else {
			char choice = event.getCharacter().charAt(0);
			switch (choice){
				case(MOVE_UP_LETTER):
					grid.moveSelectionUp();
					break;
				case(MOVE_DOWN_LETTER):
					grid.moveSelectionDown();
					break;
				case(MOVE_RIGHT_LETTER):
					grid.moveSelectionRight();
					break;
				case(MOVE_LEFT_LETTER):
					grid.moveSelectionLeft();
					break;
			}
		}
	}

}
