package userInterface.graphicalUserInterface.customFxClasses;

public class MetaCommandSelection extends CommandSelection {

	private static final int OPTION_COUNT = 2;
	
	private CommandOption endTurnSelection = null;
	
	public MetaCommandSelection(int width, int height) {
		super(width, height, OPTION_COUNT);
		endTurnSelection = CommandOption.generateCommandOption("endTurn", rectangleWidth, rectangleHeight, 0);
		cancelSelection = CommandOption.generateCommandOption("cancel", rectangleWidth, rectangleHeight, 1);
		CommandOption[] options = {endTurnSelection, cancelSelection};
		this.options = options;
		this.getChildren().addAll(this.options);
		this.getChildren().add(selectionMarker);
		this.disable();
	}

	public boolean isEndTurnSelected() {
		return findSelectedCommand().equals(endTurnSelection);
	}
	
}
