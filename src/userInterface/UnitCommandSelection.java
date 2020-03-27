package userInterface;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logicLayer.Tile;

public class UnitCommandSelection extends CommandSelection{
	
	private static final int OPTION_COUNT = 4;
	
	private CommandOption attackSelection = null;
	private CommandOption moveSelection = null;
	private CommandOption waitSelection = null;
	
	
	public UnitCommandSelection(int width, int height) {
		super(width, height,OPTION_COUNT);
		
		moveSelection = CommandOption.generateCommandOption("move", rectangleWidth, rectangleHeight, 0);
		attackSelection = CommandOption.generateCommandOption("attack", rectangleWidth, rectangleHeight, 1);
		waitSelection = CommandOption.generateCommandOption("wait", rectangleWidth, rectangleHeight, 2);
		cancelSelection = CommandOption.generateCommandOption("cancel", rectangleWidth, rectangleHeight, 3);
		CommandOption[] options = {moveSelection, attackSelection, waitSelection, cancelSelection};
		this.options = options;
		
		selectionMarker = new SelectionMarker(0,0, rectangleWidth, rectangleHeight,0,4,Color.YELLOW);
		this.getChildren().addAll(this.options);
		this.getChildren().add(selectionMarker);
		this.disable();
		
	}

	public boolean isMoveSelected() {
		return findSelectedCommand().equals(moveSelection);
	}
	
	public boolean isAttackSelected() {
		return findSelectedCommand().equals(attackSelection);
	}
	
	public boolean isWaitSelected() {
		return findSelectedCommand().equals(waitSelection);
	}
	
}
