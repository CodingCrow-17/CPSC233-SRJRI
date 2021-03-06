package userInterface.graphicalUserInterface.customFxClasses;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logicLayer.Position;

public class SelectionMarker extends Group{

	private static final Color DE_SELECTED_COLOUR = Color.TRANSPARENT;
	private Rectangle selectionMarkerRepresentation;
	
	private Color selectionColour;
	private int height;
	private int width;
	private int rowsToSelectFrom;
	private int columnsToSelectFrom;
	private Position currentPosition;
	private boolean isEnabled;
	
	public SelectionMarker(int xCor, int yCor, int width, int height, int rowsToSelectFrom, int columnsToSelectFrom, Color selectionColour) {
		super();
		this.selectionColour = selectionColour;
		currentPosition = new Position(xCor, yCor);
		this.height = height;
		this.width = width;
		this.rowsToSelectFrom = rowsToSelectFrom;
		this.columnsToSelectFrom = columnsToSelectFrom;
		selectionMarkerRepresentation = new Rectangle(0,0, width, height);
		selectionMarkerRepresentation.setFill(Color.TRANSPARENT);
		selectionMarkerRepresentation.setStrokeWidth(1);
		selectionMarkerRepresentation.setStroke(this.selectionColour);
		this.getChildren().add(selectionMarkerRepresentation);
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}
	
	public void setCurrentPosition(Position position) {
		this.currentPosition = new Position(position);
		this.setTranslateX(currentPosition.getXPosition()*width);
		this.setTranslateY(currentPosition.getYPosition()*height);
	}
	
	public void enableMarker() {
		selectionMarkerRepresentation.setStroke(this.selectionColour);
		isEnabled = true;
	}
	
	public void disableMarker() {
		selectionMarkerRepresentation.setStroke(DE_SELECTED_COLOUR);
		isEnabled = false;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public void moveSelectionUp() {
		if(this.getTranslateY() != 0) {
			this.setTranslateY(this.getTranslateY() - height);
			currentPosition.movePositionUp();
		}
	}
	
	public void moveSelectionDown() {
		if(this.getTranslateY() != (columnsToSelectFrom-1)*height) {
			this.setTranslateY(this.getTranslateY() + height);
			currentPosition.movePositionDown();
		}
	}
	
	public void moveSelectionRight() {
		if(this.getTranslateX() != (rowsToSelectFrom - 1)*width) {
			this.setTranslateX(this.getTranslateX() + width);
			currentPosition.movePositionRight();
		}
	}
	
	public void moveSelectionLeft() {
		if(this.getTranslateX() != 0) {
			this.setTranslateX(this.getTranslateX() - width);
			currentPosition.movePositionLeft();
		}
	}	
}
