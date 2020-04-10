package userInterface;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public abstract class CommandSelection extends Group{

	protected SelectionMarker selectionMarker = null;
	protected CommandOption cancelSelection;
	protected CommandOption[] options;
	protected boolean enabled;
	
	protected int rectangleHeight;
	protected int rectangleWidth;
	
	public CommandSelection(int width, int height, int optionCount) {
		super();
		rectangleHeight = height/optionCount;
		rectangleWidth = width;
		selectionMarker = new SelectionMarker(0,0, rectangleWidth, rectangleHeight,0,optionCount,Color.YELLOW);
	}
	
	protected CommandOption findSelectedCommand() {
		CommandOption selection = null;
		for (int i = 0; i < options.length; i++) {
			if (options[i].getRowPostion() == selectionMarker.getCurrentPosition().getYPosition()) {
				selection = options[i];
			}
		}
		return selection;
	}
	
	protected boolean isCancelSelected() {
		return this.findSelectedCommand().equals(cancelSelection);
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