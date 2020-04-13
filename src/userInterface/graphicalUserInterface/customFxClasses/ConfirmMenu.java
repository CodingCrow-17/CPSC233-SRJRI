package userInterface.graphicalUserInterface.customFxClasses;

public class ConfirmMenu extends CommandSelection {

	private static final int OPTION_COUNT = 2;
	private CommandOption confirmSelection = null;
	
	public ConfirmMenu(int width, int height) {
		super(width, height, OPTION_COUNT);
		confirmSelection = CommandOption.generateCommandOption("Confirm", rectangleWidth, rectangleHeight, 0);
		cancelSelection = CommandOption.generateCommandOption("Nevermind", rectangleWidth, rectangleHeight, 1);
		CommandOption[] options = {confirmSelection, cancelSelection};
		this.options = options;
		this.getChildren().addAll(this.options);
		this.getChildren().add(selectionMarker);
		this.disable();
	}

	public boolean isConfirmSelected() {
		return findSelectedCommand().equals(confirmSelection);
	}
}
