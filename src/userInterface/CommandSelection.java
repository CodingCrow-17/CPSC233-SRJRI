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

public class CommandSelection extends Group{
	
	private int rectangleHeight;
	private int rectangleWidth;
	
	private CommandOption attackSelection = null;
	private CommandOption moveSelection = null;
	private CommandOption waitSelection = null;
	private CommandOption cancelSelection = null;
	private CommandOption[] options;
	private SelectionMarker selectionMarker = null;
	
	private boolean enabled;
	
	public CommandSelection(int width, int height) {
		super();
		
		rectangleHeight = height/4;
		rectangleWidth = width;
		
		moveSelection = CommandOption.generateCommandOption("move", rectangleWidth, rectangleHeight, 0);
		attackSelection = CommandOption.generateCommandOption("attack", rectangleWidth, rectangleHeight, 1);
		waitSelection = CommandOption.generateCommandOption("wait", rectangleWidth, rectangleHeight, 2);
		cancelSelection = CommandOption.generateCommandOption("cancel", rectangleWidth, rectangleHeight, 3);
		CommandOption[] options = {moveSelection, attackSelection, waitSelection, cancelSelection};
		this.options = options;
//		this.setUpGroup(0, 0, "move", moveSelection);
//		this.setUpGroup(0, rectangleHeight, "attack", attackSelection);
//		this.setUpGroup(0, 2*rectangleHeight, "wait", waitSelection);
//		this.setUpGroup(0, 3*rectangleHeight, "cancel", cancelSelection);
		
		selectionMarker = new SelectionMarker(0,0, rectangleWidth, rectangleHeight,0,4);
		
		this.getChildren().addAll(moveSelection, attackSelection, waitSelection, cancelSelection, selectionMarker);
		this.disable();
		
	}

	public boolean isMoveSelected() {
		return findSelectedCommand().equals(moveSelection);
	}
	
	private CommandOption findSelectedCommand() {
		CommandOption selection = null;
		for (int i = 0; i < options.length; i++) {
			if (options[i].getRowPostion() == selectionMarker.getCurrentPosition().getYPosition()) {
				selection = options[i];
			}
		}
		return selection;
	}
	
	public SelectionMarker getSelectionMarker() {
		return this.selectionMarker;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	public void enable() {
		selectionMarker.enableMarker();
		enabled = true;
	}
	public void disable() {
		selectionMarker.disableMarker();
		enabled = false;
	}
}
