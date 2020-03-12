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
	
	private Group attackSelection = null;
	private Group moveSelection = null;
	private Group waitSelection = null;
	private Group cancelSelection = null;
	private SelectionMarker selectionMarker = null;
	
	private boolean enabled;
	
	public CommandSelection(int width, int height) {
		super();
		
		rectangleHeight = height/4;
		rectangleWidth = width-1;
		
		this.setUpGroup(0, 0, "move", moveSelection);
		this.setUpGroup(0, rectangleHeight, "attack", attackSelection);
		this.setUpGroup(0, 2*rectangleHeight, "wait", waitSelection);
		this.setUpGroup(0, 3*rectangleHeight, "cancel", cancelSelection);
		
		selectionMarker = new SelectionMarker(0,0, rectangleWidth, rectangleHeight,0,4);
		
		this.getChildren().add(selectionMarker);
		this.disable();
		
	}
	
	private void setUpGroup(int xCoor, int yCoor, String string, Group group) {
		StackPane pane = new StackPane();
		Text text = new Text(string);
		text.setX(0);
		text.setY(yCoor);
		text.setTextAlignment(TextAlignment.CENTER);
		Rectangle rectangle = createRectangleAt(xCoor, yCoor);
		pane.getChildren().addAll(rectangle, text);
		group = new Group(pane);
		group.setTranslateY(yCoor);
		this.getChildren().add(group);
		System.out.println(this.getChildren().size());
	}
	
	private Rectangle createRectangleAt(int xCoor, int yCoor) {
		Rectangle rectangle = new Rectangle(0, yCoor, rectangleWidth, rectangleHeight);
		rectangle.setFill(Color.WHITE);
		rectangle.setStroke(Color.BLACK);
		return rectangle;
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
