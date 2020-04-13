package userInterface.graphicalUserInterface.customFxClasses;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CommandOption extends Group{

	private int rowPostion = 0;
	
	public static CommandOption generateCommandOption(String string, int width, int height, int rowPostion) {
		StackPane pane = new StackPane();
		Text text = new Text(string);
		text.setTextAlignment(TextAlignment.CENTER);
		Rectangle rectangle = createRectangle(width, height);
		pane.getChildren().addAll(rectangle, text);
		CommandOption option = new CommandOption(pane, rowPostion);
		option.setTranslateY(rowPostion*height);
		return option;
	}
	
	private static Rectangle createRectangle(int width, int height) {
		Rectangle rectangle = new Rectangle(0, 0, width, height);
		rectangle.setFill(Color.WHITE);
		rectangle.setStroke(Color.BLACK);
		rectangle.setStrokeWidth(1);
		return rectangle;
	}
	
	public CommandOption(Node node, int position) {
		super(node);
		this.rowPostion = position;
	}
	
	public int getRowPostion() {
		return rowPostion;
	}
	
}
